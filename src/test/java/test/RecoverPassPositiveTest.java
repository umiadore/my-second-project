package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.DataRepository;
import service.EmailService;
import service.EncryptionService;
import service.RecoverPass;
import entity.User;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class RecoverPassPositiveTest {

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
    public void testRecoverPasswordSuccess() {
        String username = "user1";  

        
        when(dataRepository.findUserByUsername(username)).thenReturn(new User(username, "OldEncryptedPass"));

        
        when(encryptionService.encryptedPass(anyString())).thenReturn("NewEncryptedPass");

        
        recoverPass.recoverPassword(username);

        
        verify(dataRepository).saveUser(argThat(user -> user.getPassword().equals("NewEncryptedPass")));

        
        verify(emailService).sendEmail(eq(username), anyString(), anyString());
    }
}