package repository;

import entity.User;


public interface DataRepository {
    void saveUser(User user); 
    User findUserByUsername(String username); 
    boolean isUsernameTaken(String username); 
}
