package network;


import java.net.*;
import java.io.*;
import java.util.Scanner;


import model.Player;

public class HangmanClient {


    private Socket client;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    private String myWord;


    public HangmanClient(Socket client) throws IOException {
        // establish a connection
        this.client = client;
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        this.bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.name = name;

    }

    public void submitGuess() throws IOException {
        try {
            bufferedWriter.write(name);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner sc = new Scanner(System.in);
            while(client.isConnected()) {
                System.out.println("Input guess: ");
                String newGuess = sc.next();
                bufferedWriter.write(name + " guessed: " + newGuess);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeClient(client, bufferedReader, bufferedWriter);
        }
    }

    public void clientGame() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                while(client.isConnected()) {
                    try {
                        myWord = bufferedReader.readLine();
                    } catch(IOException e) {
                        closeClient(client, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeClient(Socket client, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }

            if (bufferedWriter != null) {
                bufferedWriter.close();
            }

            if (client != null) {
                client.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 1234);
        HangmanClient hangmanClient = new HangmanClient(socket);
        hangmanClient.submitGuess();

        hangmanClient.submitGuess();
    }



}