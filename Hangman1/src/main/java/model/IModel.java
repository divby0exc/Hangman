package model;

import java.net.InetAddress;
import java.util.List;

public interface IModel<Player> {


    List<String> getAll();

    InetAddress getAddress();
    String getSecret();

    String randomSecret();

    String addSecret();

    void clearSecrets();
}
