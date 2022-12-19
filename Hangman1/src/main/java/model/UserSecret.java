package model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class UserSecret implements IModel<User> {


    User user = new User();
    private List<User> users = new ArrayList<>(4);
    private List<String> userSecretList = new ArrayList<>();

    public UserSecret() throws UnknownHostException {
        users.add(new User("foo", "bar"));
        users.add(new User("bar", "foo"));
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
        return user.getSecretWord();
    }

    @Override
    public String randomSecret() {
        Random r = new Random(users.size());
        User randomUser = users.get(r.nextInt());
        if (randomUser.getSecretWord() != user.getSecretWord()) {
            user.setSecretWord(randomUser.getSecretWord());
        }
        return null;
    }

    @Override
    public void addSecret() {
        userSecretList.add(user.getSecretWord());
    }

    @Override
    public void clearSecrets() {
        userSecretList.clear();
    }


}

