package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.DataProcessing;
import service.DataProcessingImpl;

import static org.junit.jupiter.api.Assertions.*;

public class DataProcessingImplPositiveTest {

    private DataProcessing dataProcessing;  

    @BeforeEach
    public void setUp() {
        dataProcessing = new DataProcessingImpl();  
    }

    @Test
    public void testValidEmail() {
        String email = "user@example.com";  
        assertTrue(dataProcessing.isValidEmail(email));  
    }

    @Test
    public void testValidUsername() {
        String username = "validUser";  
        assertTrue(dataProcessing.isValidUsername(username));  
    }

    @Test
    public void testNormalizeUsername() {
        String username = "  UserName  ";  
        assertEquals("username", dataProcessing.normalizeUsername(username));  
    }
}