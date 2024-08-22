package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.EncryptionService;
import service.EncryptionServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class EncryptionServiceImplPositiveTest {

    private EncryptionService encryptionService;  

    @BeforeEach
    public void setUp() {
        encryptionService = new EncryptionServiceImpl(null);  
    }

    @Test
    public void testEncryptedPass() {
        String password = "TestPassword123!";  
        String encryptedPassword = encryptionService.encryptedPass(password);  
        assertNotEquals(password, encryptedPassword);  
        assertTrue(encryptedPassword.contains(":"));  
    }

    @Test
    public void testCheckPasswordSuccess() {
        String password = "TestPassword123!";  
        String encryptedPassword = encryptionService.encryptedPass(password);  
        assertTrue(encryptionService.checkPassword(encryptedPassword, password));  
    }
}