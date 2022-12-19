package model;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class User implements Serializable {


    private int id;
    private String name;
    private InetAddress address;
    private String secretWord;
    private boolean userWinner;




    public User() throws UnknownHostException {
        this.id = id;
        this.address = InetAddress.getLocalHost();
        this.userWinner = false;
    }

    public User(String name, String secretWord)  {

        this.name = name;
        this.secretWord = secretWord;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public InetAddress getAddress() {return address;}


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
