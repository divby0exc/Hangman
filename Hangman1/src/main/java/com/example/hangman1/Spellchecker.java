package com.example.hangman1;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Spellchecker {
    public static boolean SpellCheck(String word, String language) {

        File swedishDictionary = new File("src/main/resources/swedish-dictionary.txt");
        File englishDictionary = new File("src/main/resources/english-dictionary.txt");



        boolean isFound = false;

        if(language.equalsIgnoreCase("Swedish")) {
            try {
                Scanner scanner = new Scanner(swedishDictionary);
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
        }

        if(language.equalsIgnoreCase("English")) {
            try {
                Scanner scanner = new Scanner(englishDictionary);
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
        }

        /* if(english && swedish){
            //if wanting to play with both swedish and english
        } */

        if (isFound) {
            System.out.println("Looks good");
        } else {
            System.out.println("Not spelled correctly");
        }
        return isFound;
    }

}