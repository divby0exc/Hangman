package model;

import java.net.InetAddress;
import java.util.HashMap;

public interface IModel<User> {


    HashMap<User, Secret> getAll();

    InetAddress getAddress();
    String getSecret();
    String addSecret();




}
