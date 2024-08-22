package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.DataProcessing;
import service.DataProcessingImpl;

import static org.junit.jupiter.api.Assertions.*;

public class DataProcessingImplNegativeTest {

    private DataProcessing dataProcessing;  

    @BeforeEach
    public void setUp() {
        dataProcessing = new DataProcessingImpl();  
    }

    @Test
    public void testInvalidEmail() {
        String email = "userexample.com";  
        assertFalse(dataProcessing.isValidEmail(email));  
    }

    @Test
    public void testInvalidUsername() {
        String username = "u";  
        assertFalse(dataProcessing.isValidUsername(username));  
    }
}
