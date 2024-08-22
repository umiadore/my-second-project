package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.DataRepository;
import service.EmailServiceImpl;
import service.EncryptionService;
import service.RecoverPassImpl;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class RecoverPassImplNegativeTest {

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
    public void testRecoverPasswordFailure() {
        String username = "nonexistentUser";  

        
        when(dataRepository.findUserByUsername(username)).thenReturn(null); 

        
        recoverPassImpl.recoverPassword(username);

        

    }
}