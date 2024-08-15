package service;

public class EncryptionServiceImpl implements EncryptionService {

    // we use any type of encryption
    @Override
    public String hiddenPass(String password) {
        return password;
    }
}
