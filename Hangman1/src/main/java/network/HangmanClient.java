package network;


import java.net.*;
import java.io.*;
import com.example.hangman1.HangmanLogic;
import javafx.application.Application;


public class HangmanClient implements Runnable {


    Socket client;
    private DataInputStream in;
    private DataOutputStream out;


    public HangmanClient(String address, int port) throws IOException {

        // establish a connection
        try {

            client = new Socket("127.0.0.1", 8081);
            System.out.println("Connected");

            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());
            // sends output to the socket
            HangmanLogic hangmanLogic = new HangmanLogic();
            hangmanLogic.run();
                // close the connection
                try {
                    in.close();
                    out.close();
                    client.close();
                } catch (IOException i) {
                    System.out.println(i);
                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void run() {

    }

    public static void main(String[] args) throws IOException {
        HangmanClient hangmanClient = new HangmanClient("127.0.0.1", 5000);
        hangmanClient.run();

    }
}