package network;


import java.net.*;
import java.io.*;
import com.example.hangman1.MainHangmanGame;
import model.User;

public class HangmanClient implements Runnable {


    private Socket clientSocket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;



    public HangmanClient(String address, int port) {
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
    }

    public static void main(String args[]) {
        User user = new User();
        String ip = String.valueOf(user.getAddress());
        HangmanClient hangmanClient = new HangmanClient(ip, 5000);
        Thread t1 = new Thread(hangmanClient);
        Thread t2 = new Thread(hangmanClient);
    }

}

