package model;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class User {

    private String name;
    private String secretWord;
    private boolean isReady;
    private InetAddress address;


    public User() {
        this.isReady = false;
    }
    public User(String name, InetAddress address) {
        this.name = name;
        this.address = address;
        this.isReady = true;
    }

    public User(String name, String address) {
        this.name = name;

        try {
            this.address = InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            System.out.println("Invalid address");
        }
        this.isReady = false;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isReady() {return isReady;}
    public void setReady(boolean isReady) {this.isReady = isReady;}
    public InetAddress getAddress() {return address;}
    public void setAddress(InetAddress address) {this.address = address;}
    public String getSecretWord() {
        return secretWord;
    }
    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }
}
