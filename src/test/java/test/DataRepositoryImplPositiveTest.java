package test;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.DataRepository;
import repository.DataRepositoryImpl;

import static org.junit.jupiter.api.Assertions.*;

public class DataRepositoryImplPositiveTest {

    private DataRepository dataRepository;  

    @BeforeEach
    public void setUp() {
        dataRepository = new DataRepositoryImpl();  
    }

    @Test
    public void testSaveUser() {
        User user = new User("user1", "password");  
        dataRepository.saveUser(user);  
        assertNotNull(dataRepository.findUserByUsername("user1"));  
    }

    @Test
    public void testFindUserByUsername() {
        User user = new User("user1", "password");  
        dataRepository.saveUser(user);  
        User foundUser = dataRepository.findUserByUsername("user1");  
        assertEquals("user1", foundUser.getUsername());  
    }
}