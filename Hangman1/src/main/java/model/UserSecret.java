package model;

import java.net.InetAddress;
import java.util.HashMap;

public class UserSecret implements IModel<Secret>{


    HashMap<InetAddress, Secret> userSecretHashMap = new HashMap<>(4);


    public HashMap<InetAddress, Secret> UserSecret() {

        Secret secretWord = new Secret();
        userSecretHashMap.put(getAddress(), secretWord);

        return userSecretHashMap;
    }


    @Override
    public HashMap<Secret, Secret> getAll() {
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
}
