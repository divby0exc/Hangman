package model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class UserSecret implements IModel<User> {


    private Player player;
    private List<String> userSecretList = new ArrayList<>(4);


    private UserSecret()  {

    }

    @Override
    public List<String> getAll() {
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
    public String randomSecret() {
        Random r = new Random(userSecretList.size());
        String random = userSecretList.get(r.nextInt());
        if (random != player.getMyWord()) {
            player.setMyWord(random);
        }
        return null;
    }

    @Override
    public String addSecret() {
        String secret = player.myWord;
        userSecretList.add(secret);
        return null;
    }

    @Override
    public void clearSecrets() {
        userSecretList.clear();
    }

}
