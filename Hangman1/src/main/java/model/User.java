package model;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class User implements Serializable {

    private InetAddress address;
    private String secretWord;
    private boolean userWinner;


    public User() throws UnknownHostException {
        this.address = InetAddress.getLocalHost();
        this.userWinner = false;
    }

    public User(String secretWord)  {
        this.secretWord = secretWord;
    }


    public InetAddress getAddress() {return address;}

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }

    public boolean isUserWinner() {
        return userWinner;
    }

}
