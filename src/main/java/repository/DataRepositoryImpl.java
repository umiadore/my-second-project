package repository;

import entity.User;

import java.util.HashMap;
import java.util.Map;

// to implement, Probably will be a Map
public class DataRepositoryImpl implements DataRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void saveUser(User user) {
        users.put(user.getUsername(), user);
        System.out.println("[DATABASE] User " + user.getUsername() + " has been successfully registered.");
    }

    @Override
    public User findUserByUsername(String username) {
        User user = users.get(username);
        if (user != null) {
            System.out.println("[DATABASE] User " + username + " found in the database.");
        } else {
            System.out.println("[DATABASE] User " + username + " not found in the database.");
        }
        return user;
    }
}