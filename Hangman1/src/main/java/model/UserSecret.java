package model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

public class UserSecret {


    private static UserSecret instance;
    private User user = new User();

    private static HashMap<InetAddress, String> userSecretHashMap = new HashMap<>(4);


    private UserSecret() throws UnknownHostException {
        userSecretHashMap.put(user.getAddress(), user.getSecretWord());

    }

    public static UserSecret getInstance() throws UnknownHostException {
        if(instance == null) {
            instance = new UserSecret();
            System.out.println(userSecretHashMap);
        }
        return instance;
    }

    public static void main(String[] args) throws UnknownHostException {
        UserSecret.getInstance();
    }
}
