package model;

import java.net.InetAddress;
import java.util.HashMap;

public interface IModel<User> {


    HashMap<InetAddress, String> getAll();

    InetAddress getAddress();
    String getSecret();
    String addSecret();




}
