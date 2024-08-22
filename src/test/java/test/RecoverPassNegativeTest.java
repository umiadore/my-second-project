package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.DataRepository;
import service.EmailService;
import service.EncryptionService;
import service.RecoverPass;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class RecoverPassNegativeTest {

    private RecoverPass recoverPass;  
    private DataRepository dataRepository;
    private EmailService emailService;
    private EncryptionService encryptionService;

    @BeforeEach
    public void setUp() {
        
        dataRepository = mock(DataRepository.class);
        emailService = mock(EmailService.class);
        encryptionService = mock(EncryptionService.class);

        
        recoverPass = new RecoverPass(dataRepository, emailService, encryptionService);
    }

    @Test
    public void testRecoverPasswordFailure() {
        String username = "nonexistentUser";  

        
        when(dataRepository.findUserByUsername(username)).thenReturn(null); 

        
        recoverPass.recoverPassword(username);

        

    }
}