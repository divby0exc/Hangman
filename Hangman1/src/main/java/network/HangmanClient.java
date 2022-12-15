package network;


import java.net.*;
import java.io.*;
import com.example.hangman1.Keyboard;
import com.example.hangman1.MainHangmanGame;
import javafx.application.Application;


public class HangmanClient implements Runnable {


    Socket client;
    private DataInputStream in;
    private DataOutputStream out;


    public HangmanClient(String address, int port) throws IOException {

        // establish a connection
        try {

            client = new Socket("127.0.0.1", 5000);
            System.out.println("Connected");

            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());
            // sends output to the socket

            String line;

            while ((line = String.valueOf(in.readChar())) != null) {
                if (line.equals("0")) {
                    System.out.println("good bye");
                    break;
                }
                System.out.println(line);

                // close the connection
                try {
                    in.close();
                    out.close();
                    client.close();
                } catch (IOException i) {
                    System.out.println(i);
                }
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
        MainHangmanGame mainHangmanGame = new MainHangmanGame();
        hangmanClient.run();
        mainHangmanGame.startGame();

    }
}