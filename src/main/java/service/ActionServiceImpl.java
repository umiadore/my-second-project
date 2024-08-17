package service;

import repository.DataRepository;

import java.util.Scanner;

public class ActionServiceImpl implements ActionService{

    private  EncryptionService encryptionService;
    private DataRepository dataRepository;

    public ActionServiceImpl(EncryptionService encryptionService, DataRepository dataRepository) {
        this.encryptionService = encryptionService;
        this.dataRepository = dataRepository;
    }

    @Override
    public boolean registered(String login, String password) {
        if (login == null && password == null ){
            return false;
        }
        if (password.length() < 6){
            return  false;
    }
return true; // just for now

        // we are encr. the pass with the encrpsrv , login and with the encrypted ppass wre are going to create the user. save via repositry
    }

    @Override
    public boolean authorized(String login, String password) {
        return false;
    }
}
