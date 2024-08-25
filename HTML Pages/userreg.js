// validation.js

document.getElementById("registrationForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent form submission

    // Validate required fields
    const firstName = document.getElementById("firstName").value.trim();
    const lastName = document.getElementById("lastName").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const address = document.getElementById("address").value.trim();
    const subscriptionType = document.getElementById("subscriptionType").value;
    const startDate = document.getElementById("startDate").value;

    if (!firstName || !lastName || !email || !password || !confirmPassword || !address || !subscriptionType || !startDate) {
        alert("Please fill out all required fields.");
        return;
    }

    // Validate email format (simple validation)
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (!emailPattern.test(email)) {
        alert("Please enter a valid email address.");
        return;
    }

    // Validate password length
    if (password.length < 6) {
        alert("Password must be at least 6 characters long.");
        return;
    }

    // Validate password match
    if (password !== confirmPassword) {
        alert("Passwords do not match.");
        return;
    }

    // If all validations pass
    alert("Registration successful!");
    // Here, you would typically submit the form data to the server
    // For now, the form submission is prevented for demo purposes
});
