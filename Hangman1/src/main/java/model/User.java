package model;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class User implements Serializable {

    //private String name;
    private InetAddress address;
    private boolean userWon;


    public User() {

    }

    public User(InetAddress address) throws UnknownHostException {
        this.address = InetAddress.getLocalHost();
        this.userWon = false;

    }

    public InetAddress getAddress() {return address;}

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