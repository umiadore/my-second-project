package repository;

import entity.User;

import java.util.HashMap;
import java.util.Map;

// to implement, probably will be a Map
public class DataRepositoryImpl implements DataRepository{

    private Map<String, User> data = new HashMap<>();

}
