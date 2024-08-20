package repository;

import entity.User;

// methods to add, that will save x find the user.
public interface DataRepository {

    void saveUser (User user);
    User findUserByUsername (String username);

}
