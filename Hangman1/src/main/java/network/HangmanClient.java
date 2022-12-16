package network;


import java.net.*;
import java.io.*;
import java.util.Scanner;


public class HangmanClient {



    private Socket client;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;



    public HangmanClient(Socket client, String name) {

        // establish a connection
        try {
            this.client = client;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.name = name;


            /*HangmanLogic hangmanLogic = new HangmanLogic();
            hangmanLogic.run();
                // close the connection
                try {
                    in.close();
                    out.close();
                    client.close();
                } catch (IOException i) {
                    System.out.println(i);
                }*/

        } catch (IOException e) {
            closeClient(client, bufferedReader, bufferedWriter);
        }
    }

    public void submitGuess() throws IOException {
        try {
            bufferedWriter.write(name);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner sc = new Scanner(System.in);
            while(client.isConnected()) {
                String newGuess = sc.next();
                bufferedWriter.write(name + " guessed: " + newGuess);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeClient(client, bufferedReader, bufferedWriter);
        }
    }

    public void guessListener() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String listenForGuesses;

                while(client.isConnected()) {
                    try {
                        listenForGuesses = bufferedReader.readLine();
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
        /*HangmanClient hangmanClient = new HangmanClient("127.0.0.1", 5000);
        hangmanClient.run();*/
        Socket socket = new Socket("localhost", 1234);

        Scanner sc = new Scanner(System.in);
        System.out.println("Guess something: ");
        String name = sc.nextLine();
        HangmanClient hangmanClient = new HangmanClient(socket, name);
        hangmanClient.guessListener();
        hangmanClient.submitGuess();
    }


}