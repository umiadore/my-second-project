package service;

public interface ActionService {


    boolean registered (String login, String password);

    boolean authorized (String login, String password);

    boolean recoverPass (String login, String password);

}
