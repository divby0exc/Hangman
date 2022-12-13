package model;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class User {

    private String name;
    private String secretWord;
    private InetAddress address;


    public User() {

    }
    public User(String name, InetAddress address) {
        this.name = name;
        this.address = address;
        this.secretWord = secretWord;
    }

    public User(String name, String address) {
        this.name = name;

        try {
            this.address = InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            System.out.println("Invalid address");
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public InetAddress getAddress() {return address;}
    public void setAddress(InetAddress address) {this.address = address;}
    public String getSecretWord() {
        return secretWord;
    }
    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }
}
