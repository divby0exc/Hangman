package model;

import java.util.Scanner;

public class Secret extends User {

    private String secretWord;


    public Secret() {
        this.secretWord = new Scanner(System.in).next();
    }

}
