package org.example;

import com.subscriptionapp.dao.ProductDAO;
import com.subscriptionapp.dao.ProductDAOImpl;
import com.subscriptionapp.model.Product;
import com.subscriptionapp.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceImplTest {

    private ProductServiceImpl productService;
    private ProductDAO productDAO;

    @Before
    public void setUp() throws Exception {
        // Initialize the DAO
        productDAO = new ProductDAOImpl();

        // Initialize ProductServiceImpl and set ProductDAO
        productService = new ProductServiceImpl();
        productService.setProductDAO(productDAO);

        setUpDatabase();
    }

    @After
    public void tearDown() throws Exception {
        cleanUpDatabase();
    }

    private void setUpDatabase() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE products (id INT PRIMARY KEY, name VARCHAR(255), description VARCHAR(255), price DECIMAL, is_active BOOLEAN)");
        stmt.execute("INSERT INTO products (id, name, description, price, is_active) VALUES (1, 'Product1', 'Description1', 100.0, true)");
        stmt.execute("INSERT INTO products (id, name, description, price, is_active) VALUES (2, 'Product2', 'Description2', 200.0, true)");
        connection.close();
    }

    private void cleanUpDatabase() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        Statement stmt = connection.createStatement();
        stmt.execute("DROP TABLE products");
        connection.close();
    }

    @Test
    public void testGetProductById_ValidId() throws Exception {
        int productId = 1;
        Product result = productService.getProductById(productId);
        assertNotNull(result);
        assertEquals("Product1", result.getName());
        assertEquals("Description1", result.getDescription());
        assertEquals(100.0, result.getPrice(), 0);
        assertTrue(result.isActive());
    }

    @Test(expected = Exception.class)
    public void testGetProductById_InvalidId() throws Exception {
        int productId = 999;
        productService.getProductById(productId);
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> result = productService.getAllProducts();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetAllProducts_EmptyList() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        Statement stmt = connection.createStatement();
        stmt.execute("DELETE FROM products");
        connection.close();

        List<Product> result = productService.getAllProducts();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
