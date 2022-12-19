package com.example.hangman1;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class Logic {

    String name;
    String secretWord;
    int wrongAttempt = 0;
    char[] secretWordToArray;
    int maxChances = 8;
    boolean userWon = false;
    final String dash = "-";
    List<String> correctGuesses = new ArrayList<>();
    List<String> wrongGuesses = new ArrayList<>();
    User user = new User();

    public Logic() throws UnknownHostException {
         this.name = user.getName();
         this.secretWord = user.getSecretWord();

    }


    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
        secretWordToArray = dash.repeat(secretWord.length()).toCharArray();
    }

    public String getSecretWord() {
        return secretWord;
    }

    public int getWrongAttempt() {
        return wrongAttempt;
    }

    public int getMaxChances() {
        return maxChances;
    }

    public static final int CODE_WON = 0;
    public static final int CODE_LOST = 1;
    public static final int CODE_CORRECT_GUESS = 2;
    public static final int CODE_WRONG_GUESS = 3;
    public static final int CODE_INVALID = 4;
    public static final int CODE_DUPLICATE_GUESS = 5;

    public int submit(String input) {

        int result = 0;
        if (wrongAttempt <= maxChances) {

            if (input == null || "".equals(input.trim())) {
                // messageOfInput.setText("Invalid input. Please try again.");
                return CODE_INVALID;
            }

            if (correctGuesses.contains(input) || wrongGuesses.contains(input)) {
                //messageOfInput.setText("You guessed this letter before. Try another one!");
                return CODE_DUPLICATE_GUESS; // don't do anything and just loop again
            }

            if (secretWord.contains(input)) {
                // count the guess as correct only if it's one character
                if (input.length() == 1) {
                    correctGuesses.add(input);
                    result = CODE_CORRECT_GUESS;
                    //messageOfInput.setText("You guessed right. Guess next letter: ");
                } else {
                    // if it's multiple characters, then it's not a correct guess even if it's part of the secret word
                    wrongGuesses.add(input);
                    wrongAttempt++;
                    result = CODE_WRONG_GUESS;
                    //messageOfInput.setText("You guessed wrong. you have spent " + wrongAttempt + "/" + maxChances + " of your chances to guess wrong! Guess a new letter: ");
                }
            } else {
                wrongGuesses.add(input);
                wrongAttempt++;
                result = CODE_WRONG_GUESS;
                //messageOfInput.setText("You guessed wrong. you have spent " + wrongAttempt + "/" + maxChances + " of your chances to guess wrong, Guess a new letter: ");
            }


            // process the guesses and keep the results as a word.
            for (String guess : correctGuesses) {
                char guessedChar = guess.charAt(0);
                int i = 0;
                for (char secretLetter : secretWord.toCharArray()) {
                    if (secretLetter == guessedChar) {
                        secretWordToArray[i] = secretLetter;
                    }
                    i++;
                }
            }

            // not needed. instead, the game class will call the getWordToDisplay() method when needed
            //showSecretWordIndashFormat.setText(new String(secretWordToArray).toUpperCase());

            // Check if wins
            if (!new String(secretWordToArray).contains(dash)) {
                userWon = true;
                //messageOfInput.setText("Congrats " + playerName + "!  You won the game");
                //wrongAttempt = 0;
                return CODE_WON;
            }

            // Check if loses
            if (wrongAttempt >= maxChances) {
                //hangmanDrawing.getImageOfHangman(wrongAttempt);
                //messageOfInput.setText("Sorry! You did not win. The correct word is: " + secretWord);
                //wrongAttempt = 0;
                return CODE_LOST;
            }
        }

        return result;

    }

    public String getWordToDisplay() {
        return new String(secretWordToArray).toUpperCase();
    }

}


