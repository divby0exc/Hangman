package com.example.hangman1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class HangmanLogic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean userWon = false;
        final char dash = '-';

        System.out.println("Welcome to game!");
        String secretWord = randomWord();
        char[] secretWordArray = new char[secretWord.length()];
        for (int i = 0; i < secretWord.length(); i++) {
            secretWordArray[i] = dash;
        }

        System.out.println("secretWord is " + secretWord);

        printWordArray(secretWordArray);

        List<String> correctGuesses = new ArrayList<>();
        List<String> wrongGuesses = new ArrayList<>();
        int maxChances = 8;
        for (int guessAttempt = 1; guessAttempt <= maxChances; guessAttempt++) {
            System.out.println("\r\nChance " + guessAttempt + "/" + maxChances + ", Guess a letter: ");
            String input = scanner.next();
            System.out.println("user input is: "+input);

            if (input == null || "".equals(input.trim())) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }

            if (correctGuesses.contains(input) || wrongGuesses.contains(input)) {
                System.out.println("You guessed this letter before. Try another one!");
                guessAttempt--; // don't count this duplicate letter
                continue; // don't do anything and just loop again
            }

            // if user guessed the whole word in one go, set the flag to true and skip the loop
            if (secretWord.equals(input)) {
                userWon = true;
                break;
            }


            if (secretWord.contains(input)) {
                // count the guess as correct only if it's one character
                if (input.length() == 1) {
                    correctGuesses.add(input);
                } else {
                    // if it's multiple characters, then it's not a correct guess even if it's part of the secret word
                    wrongGuesses.add(input);
                }
            } else {
                wrongGuesses.add(input);
            }

            // process the guesses and keep the results as a word.
            for (String guess : correctGuesses) {
                char guessedChar = guess.charAt(0);
                int i = 0;
                for (char secretLetter : secretWord.toCharArray()) {
                    if (secretLetter == guessedChar) {
                        secretWordArray[i] = secretLetter;
                    }
                    i++;
                }
            }

            // present the word
            printWordArray(secretWordArray);

            // if no dash is left in the word, it means all letters are revealed and user wins
            if (!String.valueOf(secretWordArray).contains(String.valueOf(dash)))
            {
                userWon = true;
                break;
            }

        }

        if (userWon) {
            System.out.println("\r\nCongrats! You Won!");
        } else {
            System.out.println("\r\nSorry! You did not win. The correct word is: \r\n" + secretWord);
        }

    }

    private static void printWordArray(char[] secretWordArray){
        System.out.println("Word: ");
        for (char ch : secretWordArray) {
            System.out.print(ch + " ");
        }
    }

    private static String randomWord() {
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of("Hangman1/src/main/resources/word_list.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Random random = new Random();
        return lines.get(random.nextInt(lines.size())).toUpperCase();
    }

}

