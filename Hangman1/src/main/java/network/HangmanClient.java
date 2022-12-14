package network;


import java.net.*;
import java.io.*;
import com.example.hangman1.MainHangmanGame;
import model.User;

public class HangmanClient extends Thread {


    private Socket clientSocket;
    private DataInputStream input;
    private DataOutputStream out;
    private InetAddress address;


    public HangmanClient(String address, int port) throws UnknownHostException {
        // establish a connection
        try {
            clientSocket = new Socket(address, port);
            System.out.println("Connected");
            MainHangmanGame hangmanGame = new MainHangmanGame();
            hangmanGame.startGame();
            input = new DataInputStream(clientSocket.getInputStream());
            // sends output to the socket
            out = new DataOutputStream(clientSocket.getOutputStream());

        } catch(UnknownHostException u) {
            System.out.println(u);

        } catch(IOException i) {
            System.out.println(i);
        }


        String line = "";

        // Reads until exit code 0.
        while (!line.equals("0")) {
            try {
                line = input.readUTF();
                out.writeUTF(line);
            }
            catch(IOException i) {
                System.out.println(i);
            }
        }

        // close the connection
        try {
            input.close();
            out.close();
            clientSocket.close();
        }
        catch(IOException i) {
            System.out.println(i);
        }
    }

    @Override
    public void run() {
        System.out.println("Thread " + "1");
    }

    static User user;

    static {
        try {
            user = new User();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args[]) throws UnknownHostException {

        //String ip =
        //HangmanClient hangmanClient = new HangmanClient(ip, 5000);
        Thread t1 = new HangmanClient(String.valueOf(user.getAddress()), 5000);
        Thread t2 = new HangmanClient(String.valueOf(user.getSecretWord()), 5000);


    }

}

