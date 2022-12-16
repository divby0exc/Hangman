package network;



import java.net.*;
import java.io.*;

public class HangmanServer {


    private ServerSocket serverSocket = null;
    private DataInputStream in = null;


    public HangmanServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }


    public void StartServer() {
        try {

            while (!serverSocket.isClosed()) {

                System.out.println("Server started, waiting for client...");
                //Listener.
                Socket client = serverSocket.accept();
                System.out.println("Client accepted");

                ClientHandler clientHandler = new ClientHandler(client);

                Thread thread = new Thread(clientHandler);
                thread.start();


                // client socket input
                in = new DataInputStream(
                        new BufferedInputStream(client.getInputStream()));
            }

        } catch (IOException i) {
            System.out.println(i);
        }
    }


    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws IOException {
        ServerSocket ss = new ServerSocket(1234);
        HangmanServer hangmanServer = new HangmanServer(ss);
        hangmanServer.StartServer();


    }


}

