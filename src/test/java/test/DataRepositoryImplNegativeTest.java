package test;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.DataRepository;
import repository.DataRepositoryImpl;

import static org.junit.jupiter.api.Assertions.*;

public class DataRepositoryImplNegativeTest {

    private DataRepository dataRepository;  

    @BeforeEach
    public void setUp() {
        dataRepository = new DataRepositoryImpl();  
    }

    @Test
    public void testFindUserByUsernameNotFound() {
        
        User user = dataRepository.findUserByUsername("nonexistentUser");  
        assertNull(user);  
    }

    @Test
    public void testUsernameTaken() {
        
        User user = new User("user1", "password");  
        dataRepository.saveUser(user);  
        assertTrue(dataRepository.isUsernameTaken("user1"));  
        assertFalse(dataRepository.isUsernameTaken("user2"));  
    }
}
