package model;


public class Player extends User {

    private String secretWord;


    public Player(String name, String secretWord) {
        super();
        this.secretWord = getSecretWord();

    }

    public String toString() {
        return secretWord;
    }
}