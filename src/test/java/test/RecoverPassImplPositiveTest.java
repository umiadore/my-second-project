package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.DataRepository;
import service.EmailServiceImpl;
import service.EncryptionService;
import service.RecoverPassImpl;
import entity.User;
import static org.mockito.Mockito.*;


public class RecoverPassImplPositiveTest {

    private RecoverPassImpl recoverPassImpl;
    private DataRepository dataRepository;
    private EmailServiceImpl emailServiceImpl;
    private EncryptionService encryptionService;

    @BeforeEach
    public void setUp() {
        
        dataRepository = mock(DataRepository.class);
        emailServiceImpl = mock(EmailServiceImpl.class);
        encryptionService = mock(EncryptionService.class);

        
        recoverPassImpl = new RecoverPassImpl(dataRepository, emailServiceImpl, encryptionService);
    }

    @Test
    public void testRecoverPasswordSuccess() {
        String username = "user1";
        User user = new User(username, "OldEncryptedPass");

        
        when(dataRepository.findUserByUsername(username)).thenReturn(user);

        
        when(encryptionService.encryptedPass(anyString())).thenReturn("NewEncryptedPass");

        
        recoverPassImpl.recoverPassword(username);

        
        verify(dataRepository).saveUser(argThat(savedUser ->
                savedUser.getUsername().equals(username) &&
                        savedUser.getPassword().equals("NewEncryptedPass")
        ));

        
        verify(emailServiceImpl).sendEmail(eq(username), anyString(), anyString());
    }
}