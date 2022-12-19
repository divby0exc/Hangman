package model;

import builder.UserBuilder;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class UserSecret implements IModel<User> {


    private static User user;
    private List<User> users = new ArrayList<>(4);
    private List<String> userSecretList = new ArrayList<>();

    private UserSecret()  {
        users.add(new User(user.getName(), user.getSecretWord()));
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
        Random r = new Random(userSecretList.size());
        String random = String.valueOf(users.get(r.nextInt()));
        if (random != user.getSecretWord()) {
            user.setSecretWord(random);
        }
        return null;
    }

    @Override
    public String addSecret() {
        userSecretList.add(user.getSecretWord());
        return null;
    }

    @Override
    public void clearSecrets() {
        userSecretList.clear();
    }


    public static void main(String[] args) throws UnknownHostException {

        Scanner sc = new Scanner(System.in);
        user = new User();
        System.out.println("Input name: ");
        user.setName(sc.next());
        System.out.println("Input secret: ");
        user.setSecretWord(sc.next());
        System.out.println(user.getName() + user.getSecretWord());


    }

}
