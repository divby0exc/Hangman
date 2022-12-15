package model;

import java.util.Scanner;

public class Player extends User {

    String myWord;

    public Player(String name, String myWord) {
        super(name, myWord);
        System.out.println("Input secret: ");
        this.myWord = new Scanner(System.in).next();

    }

}
