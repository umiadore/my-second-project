package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.DataRepository;
import service.RegistrationService;
import service.RegistrationServiceImpl;
import service.EncryptionService;
import service.DataProcessing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegistrationServiceImplNegativeTest {

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
    public void testRegisterUserUsernameTaken() {
        String username = "existinguser";
        String password = "NewPass123!";

        when(dataRepository.isUsernameTaken(username)).thenReturn(true);

        boolean result = registrationService.registerUser(username, password);
        assertFalse(result);
    }
}