package model;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Scanner;

public class UserSecret {

    User user = new User();

    HashMap<InetAddress, Secret> userSecretHashMap = new HashMap<>(4);


    public HashMap<InetAddress, Secret> UserSecret() {

        Secret secretWord = new Secret(new Scanner(System.in).next());

        userSecretHashMap.put(user.getAddress(), secretWord);

        return userSecretHashMap;
    }


}
