package service;

import repository.DataRepository;

public class ActionServiceImpl implements ActionService{

    private  EncryptionService encryptionService;
    private DataRepository dataRepository;
    @Override
    public boolean registered(String login, String password) {
        return false; // we are encr. the pass with the encrpsrv , login and with the encrypted ppass wre are going to create the user. save via repositry
    }

    @Override
    public boolean authorized(String login, String password) {
        return false;
    }
}
