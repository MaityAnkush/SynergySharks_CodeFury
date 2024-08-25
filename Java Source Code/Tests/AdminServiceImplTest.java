package org.example;

import com.subscriptionapp.dao.AdminDAO;
import com.subscriptionapp.dao.AdminDAOImpl;
//import com.subscriptionapp.model.;
import com.subscriptionapp.dao.AdminDAO;
//import com.example.dao.AdminDAOImpl;
//import com.example.model.Admin;
import com.subscriptionapp.service.AdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Test class for AdminServiceImpl
public class AdminServiceImplTest {

    private AdminServiceImpl adminService;
    private AdminDAO adminDAO;
    private Connection connection;

    // Set up the in-memory database and initialize necessary components before each test
    @BeforeEach
    public void setUp() throws Exception {
        // Establish an in-memory database connection
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");

        // Initialize the AdminDAO implementation
        adminDAO = new AdminDAOImpl();
        
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE admins (id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), password VARCHAR(255), role VARCHAR(255))");
        }

        // Initialize the AdminServiceImpl with the DAO
        adminService = new AdminServiceImpl();
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    // Test case for adding an admin
    @Test
    public void testAddAdmin() throws Exception {
        Admin admin = new Admin(1, "adminUser", "adminPass", "ADMIN");
        adminService.addAdmin(admin);

        List<Admin> admins = adminService.getAllAdmins();
        assertEquals(1, admins.size());
        assertEquals(admin.getUsername(), admins.get(0).getUsername());
        assertEquals(admin.getRole(), admins.get(0).getRole());
    }

    // Test case for retrieving an admin by ID
    @Test
    public void testGetAdminById_ValidId() throws Exception {
        Admin admin = new Admin(1, "adminUser", "adminPass", "ADMIN");
        adminService.addAdmin(admin);

        Admin fetchedAdmin = adminService.getAdminById(1);
        assertNotNull(fetchedAdmin);
        assertEquals(admin.getUsername(), fetchedAdmin.getUsername());
    }

    // Test case for handling retrieval of an admin with an invalid ID
    @Test
    public void testGetAdminById_InvalidId() throws Exception {
        assertThrows(Exception.class, () -> {
            adminService.getAdminById(999);
        });
    }

    // Test case for retrieving all admins
    @Test
    public void testGetAllAdmins() throws Exception {
        Admin admin1 = new Admin(1, "adminUser1", "adminPass1", "ADMIN");
        Admin admin2 = new Admin(2, "adminUser2", "adminPass2", "ADMIN");
        adminService.addAdmin(admin1);
        adminService.addAdmin(admin2);

        List<Admin> admins = adminService.getAllAdmins();
        assertEquals(2, admins.size());
    }

    // Test case for removing an admin
    @Test
    public void testRemoveAdmin() throws Exception {
        Admin admin = new Admin(1, "adminUser", "adminPass", "ADMIN");
        adminService.addAdmin(admin);

        List<Admin> admins = adminService.getAllAdmins();
        assertEquals(1, admins.size());

        adminService.removeAdmin(1);
        admins = adminService.getAllAdmins();
        assertTrue(admins.isEmpty());
    }


}
