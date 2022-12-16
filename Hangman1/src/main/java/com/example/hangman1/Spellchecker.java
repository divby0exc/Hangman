package com.example.hangman1;



import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;


public class Spellchecker {
    public static boolean SpellCheck(String word, String language) {

        URL urlSE = Spellchecker.class.getClassLoader().getResource("swedish-dictionary.txt");
        URL urlEN = Spellchecker.class.getClassLoader().getResource("english-dictionary.txt");

        File swedishDictionary ;
        File englishDictionary ;
        try {
            swedishDictionary = new File(urlSE.toURI());
            englishDictionary = new File(urlEN.toURI());

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


        boolean isFound = false;


        if(language.equalsIgnoreCase("Swedish")) {
            try {
                Scanner scanner = new Scanner(swedishDictionary);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.contains(word.toLowerCase())) {
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
                    if (line.contains(word.toLowerCase())) {
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