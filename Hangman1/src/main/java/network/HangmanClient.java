package network;


import java.net.*;
import java.io.*;

import com.example.hangman1.Application;
import com.example.hangman1.IHangman;
import com.example.hangman1.VirtualKeyboard;

public class HangmanClient implements Runnable {


    Socket client;
    private DataInputStream in;
    private DataOutputStream out;



    public HangmanClient(String address, int port) {

        // establish a connection
        try {

            client = new Socket("127.0.0.1", 5000);
            System.out.println("Connected");

            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());
            // sends output to the socket
            Application.launch();

            in.read("test".getBytes());
        } catch(UnknownHostException u) {
            System.out.println(u);

        } catch(IOException i) {
            System.out.println(i);
        }




        // close the connection
        try {
            in.close();
            out.close();
            client.close();
        }
        catch(IOException i) {
            System.out.println(i);
        }
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        HangmanClient hangmanClient = new HangmanClient("127.0.0.1", 6666);
        hangmanClient.run();
    }
}

