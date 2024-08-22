package test;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.DataRepository;
import service.RegistrationService;
import service.RegistrationServiceImpl;
import service.EncryptionService;
import service.DataProcessing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegistrationServiceImplPositiveTest {

    private RegistrationService registrationService;  
    private DataRepository dataRepository;  
    private EncryptionService encryptionService;  
    private DataProcessing dataProcessing;  

    @BeforeEach
    public void setUp() {
        dataRepository = mock(DataRepository.class);  
        encryptionService = mock(EncryptionService.class);  
        dataProcessing = mock(DataProcessing.class);  

        registrationService = new RegistrationServiceImpl(dataRepository, encryptionService, dataProcessing);  
    }

    @Test
    public void testRegisterUserSuccess() {
        String username = "newuser";  
        String password = "NewPass123!";  
        String encryptedPassword = "encryptedPassword";  

        
        when(encryptionService.encryptedPass(password)).thenReturn(encryptedPassword);
        when(dataRepository.isUsernameTaken(username)).thenReturn(false);  

        boolean result = registrationService.registerUser(username, password);  
        assertTrue(result);  

        
        verify(dataRepository).saveUser(argThat(user ->
                user.getUsername().equals(username) && user.getPassword().equals(encryptedPassword)
        ));
    }
}
