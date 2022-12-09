package com.example.hangman1;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Spellchecker {


    public static boolean sweSpellCheck(String word) {
        File file = new File("src/main/resources/swedish-dictionary.txt");
        boolean isFound = false;

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(word)) {
                    isFound = true;
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (isFound) {
            System.out.println("String was found!");
        } else {
            System.out.println("String not found!");
        }
        return isFound;
    }

    public static boolean engSpellCheck(String word) {
        File file = new File("src/main/resources/english-dictionary.txt");
        boolean isFound = false;

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(word)) {
                    isFound = true;
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (isFound) {
            System.out.println("String was found!");
        } else {
            System.out.println("String not found!");
        }
        return isFound;
    }

}
