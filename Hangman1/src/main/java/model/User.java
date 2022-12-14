package model;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class User implements Serializable {

    //private String name;
    private static InetAddress address;
    private static String secretWord;
    private boolean userWon;


    public User() {

    }

    public User(InetAddress address) throws UnknownHostException {
        this.address = InetAddress.getLocalHost();
        this.secretWord = new Secret().toString();
        this.userWon = false;

    }



    public static InetAddress getAddress() {return address;}

    public String getSecretWord() {
        return secretWord;
    }

    public boolean isUserWon() {
        return userWon;
    }

}

    /*public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
*/