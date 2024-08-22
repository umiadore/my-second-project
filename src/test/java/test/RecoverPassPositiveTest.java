package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.DataRepository;
import service.EmailService;
import service.EncryptionService;
import service.RecoverPass;
import entity.User;
import static org.mockito.Mockito.*;


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
        User user = new User(username, "OldEncryptedPass");

        
        when(dataRepository.findUserByUsername(username)).thenReturn(user);

        
        when(encryptionService.encryptedPass(anyString())).thenReturn("NewEncryptedPass");

        
        recoverPass.recoverPassword(username);

        
        verify(dataRepository).saveUser(argThat(savedUser ->
                savedUser.getUsername().equals(username) &&
                        savedUser.getPassword().equals("NewEncryptedPass")
        ));

        
        verify(emailService).sendEmail(eq(username), anyString(), anyString());
    }
}