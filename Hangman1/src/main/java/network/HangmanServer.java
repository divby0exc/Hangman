package network;



import java.net.*;
import java.io.*;

public class HangmanServer {

    private Socket		 socket = null;
    private ServerSocket server = null;
    private DataInputStream in	 = null;


    public HangmanServer(int port)
    {
        // Open socket listener.
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
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

    public static void main(String args[]) {
        HangmanServer hangmanServer = new HangmanServer(5000);

    }
}
