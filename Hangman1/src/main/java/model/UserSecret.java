package model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserSecret implements IModel<User> {


    private static UserSecret instance;
    private User user = new User();
    private static HashMap<InetAddress, String> userSecretHashMap = new HashMap<>(4);


    private UserSecret() throws UnknownHostException {
        user.setSecretWord("test");
        userSecretHashMap.put(user.getAddress(), user.getSecretWord());
    }

    @Override
    public HashMap<InetAddress, String> getAll() {
        return null;
    }

    @Override
    public InetAddress getAddress() {
        return null;
    }

    @Override
    public String getSecret() {
        return null;
    }

    @Override
    public String addSecret() {
        return null;
    }

    public static void main(String[] args) throws UnknownHostException {

    }


}
