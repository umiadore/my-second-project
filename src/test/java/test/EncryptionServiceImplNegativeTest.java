package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.EncryptionService;
import service.EncryptionServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class EncryptionServiceImplNegativeTest {

    private EncryptionService encryptionService;  

    @BeforeEach
    public void setUp() {
        encryptionService = new EncryptionServiceImpl(null);  
    }

    @Test
    public void testCheckPasswordFailure() {
        String password = "CorrectPass123!";  
        String wrongPassword = "WrongPass123!";  
        String encryptedPassword = encryptionService.encryptedPass(password);  

        boolean result = encryptionService.checkPassword(encryptedPassword, wrongPassword);  
        assertFalse(result);  
    }
}