package network;


import java.net.*;
import java.io.*;
import com.example.hangman1.MainHangmanGame;

public class HangmanClient {


    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;


    public HangmanClient(String address, int port) {
        // establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");


            input = new DataInputStream(new MainHangmanGame().submit());

            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());

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
            socket.close();
        }
        catch(IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        HangmanClient hangmanClient = new HangmanClient("127.0.0.1", 5000);
    }
}
