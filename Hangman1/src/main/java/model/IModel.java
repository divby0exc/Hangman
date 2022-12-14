package model;

import java.net.InetAddress;
import java.util.HashMap;

public interface IModel<User> {


    HashMap<InetAddress, Secret> getAll();

    InetAddress getAddress();
    String getSecret();
    String addSecret();




}
