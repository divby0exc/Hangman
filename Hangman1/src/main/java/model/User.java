package model;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class User implements Serializable {


    private String name;
    private InetAddress address;
    private String myWord;
    private boolean userWinner;



    public User() throws UnknownHostException {
        this.address = InetAddress.getLocalHost();
        this.userWinner = false;
    }

    public User(String name, String myWord)  {
        this.name = name;
        this.myWord = myWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InetAddress getAddress() {return address;}

    public String getMyWord() {
        return myWord;
    }

    public void setMyWord(String myWord) {
        this.myWord = myWord;
    }

    public boolean isUserWinner() {
        return userWinner;
    }

}
