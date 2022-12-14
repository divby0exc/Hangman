package network;



import java.net.*;
import java.io.*;

public class HangmanServer {

    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private DataInputStream in = null;


    public HangmanServer(int port)
    {
        // Open socket listener.
        try
        {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = serverSocket.accept();
            System.out.println("Client accepted");

            // client socket input
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String line = "";

            // Exit code 0.
            while (!line.equals("0"))
            {
                try
                {
                    line = in.readUTF();
                    System.out.println(line);

                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[]) throws IOException {
        HangmanServer hangmanServer = new HangmanServer(6666);
        //ServerSocket serverSocket = new ServerSocket(6666);
    }
}

