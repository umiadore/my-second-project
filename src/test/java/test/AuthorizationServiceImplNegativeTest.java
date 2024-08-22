package test;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.DataRepository;
import service.AuthorizationService;
import service.AuthorizationServiceImpl;
import service.EncryptionService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthorizationServiceImplNegativeTest {

    private AuthorizationService authorizationService;
    private DataRepository dataRepository;
    private EncryptionService encryptionService;

    @BeforeEach
    public void setUp() {
        dataRepository = mock(DataRepository.class);
        encryptionService = mock(EncryptionService.class);
        authorizationService = new AuthorizationServiceImpl(dataRepository, encryptionService);
    }

    @Test
    public void testLoginUserFailure() {
        String username = "existinguser";
        String password = "WrongPass123!";
        User user = new User(username, "encryptedPassword");

        when(dataRepository.findUserByUsername(username)).thenReturn(user);
        when(encryptionService.encryptedPass(password)).thenReturn("wrongEncryptedPassword");

        boolean result = authorizationService.authorizedUser(username, password);
        assertFalse(result);
    }
}