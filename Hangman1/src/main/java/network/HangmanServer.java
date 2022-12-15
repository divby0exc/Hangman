package network;



import java.net.*;
import java.io.*;

public class HangmanServer implements Runnable {


    public ServerSocket serverSocket = null;
    private DataInputStream in = null;


    public HangmanServer(int port)
    {
        try {
            //Listener.
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            Socket client = serverSocket.accept();
            System.out.println("Client accepted");

            // client socket input
            in = new DataInputStream(
                    new BufferedInputStream(client.getInputStream()));

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
            client.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    @Override
    public void run() {

    }

    public static void main(String args[]) throws IOException {
        HangmanServer hangmanServer = new HangmanServer(5000);
        hangmanServer.run();
        //ServerSocket serverSocket = new ServerSocket(6666);
    }


}

