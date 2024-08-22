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

        
        when(dataProcessing.isValidUsername(username)).thenReturn(true);
        when(dataRepository.isUsernameTaken(username)).thenReturn(false);
        when(encryptionService.encryptedPass(password)).thenReturn(encryptedPassword);

        
        boolean result = registrationService.registerUser(username, password);

        
        assertTrue(result);

        
        verify(dataRepository).saveUser(argThat(user ->
                user.getUsername().equals(username) && user.getPassword().equals(encryptedPassword)
        ));
    }

    @Test
    public void testRegisterUserWithTakenUsername() {
        String username = "takenuser";
        String password = "NewPass123!";

        
        when(dataProcessing.isValidUsername(username)).thenReturn(true);
        when(dataRepository.isUsernameTaken(username)).thenReturn(true);

        
        boolean result = registrationService.registerUser(username, password);

        
        assertFalse(result);

        
        verify(dataRepository, never()).saveUser(any(User.class));
    }

    @Test
    public void testRegisterUserWithInvalidUsername() {
        String username = "invalid*user";
        String password = "NewPass123!";

        
        when(dataProcessing.isValidUsername(username)).thenReturn(false);

        
        boolean result = registrationService.registerUser(username, password);

        
        assertFalse(result);

        
        verify(dataRepository, never()).saveUser(any(User.class));
    }
}