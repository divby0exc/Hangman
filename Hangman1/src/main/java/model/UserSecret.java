package model;

import builder.UserBuilder;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class UserSecret implements IModel<Player> {


    private Player player;
    private List<String> userSecretList = new ArrayList<>(4);


    private UserSecret()  {
        userSecretList.add(player.getMyWord());
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


    public static void main(String[] args) throws UnknownHostException {
        UserBuilder builder = new UserBuilder();
        builder.name("foo");

        User user = new UserBuilder()
                .name("foo")
                .myWord("secret")
                .address()
                .userWinner().build();
    }

}
