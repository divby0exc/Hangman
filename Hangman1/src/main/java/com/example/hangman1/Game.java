package com.example.hangman1;


import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;


public class Game {
    String secretWordInDash;
    int wrongAttempt = 0;
    char[] secretWordArray;
    final String dash = "-";
    ;
    String secretWord = "";
    String alph;
    String input;
    Label inputCh;
    Label messageOfInput;
    Label secWoDash;
    TextField enterACharacter;
    TextField enterYourName;
    Button submitCharacter;
    Button submitYourName;
    boolean userWon = false;
    Label showName= new Label();
    List<String> correctGuesses = new ArrayList<>();
    List<String> wrongGuesses = new ArrayList<>();
    String inputName;
    String name="";

    private final static int MODE_WORD = 1;
    private final static int MODE_GUESS = 2;
    private int state = MODE_WORD;

    int maxChances = 8;
    Image firstMiss = new Image("1.png");
    Image secondMiss = new Image("2.png");
    Image thirdMiss = new Image("3.png");
    Image fourthMiss = new Image("4.png");
    Image fifthMiss = new Image("5.png");
    Image sixthMiss = new Image("6.png");
    Image seventhMiss = new Image("7.png");
    Image eighthMiss = new Image("8.png");
    ImageView imageView = new ImageView();


    public Game(String secretWord) {
        this.secretWord = secretWord.toUpperCase();
    }


    public Pane startGame() {

        // Creating a virtual keyboard
        List<String> letters = new ArrayList<>(Arrays.asList("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"));
        List<String> letters1 = new ArrayList<>(Arrays.asList("A", "S", "D", "F", "G", "H", "J", "K", "L"));
        List<String> letters2 = new ArrayList<>(Arrays.asList("Z", "X", "C", "V", "B", "N", "M"));
        HBox row = createRow(letters);
        row.setPadding(new Insets(10, 10, 10, 10));
        HBox row1 = createRow(letters1);
        row1.setPadding(new Insets(-20, 10, 10, 10));
        HBox row2 = createRow(letters2);
        row2.setPadding(new Insets(-20, 10, 10, 10));

        secretWordArray = dash.repeat(secretWord.length()).toCharArray();
        secWoDash = new Label();
        secWoDash.setFont(new Font("Arial", 25));
        secWoDash.setText(new String(secretWordArray));

        // creating box for get input of player and show the result
        inputCh = new Label(" Guess a character:");
        inputCh.setFont(new Font("Arial", 14));
        messageOfInput = new Label();
        messageOfInput.setFont(new Font("Arial", 14));
        messageOfInput.setMaxWidth(400);
        messageOfInput.setWrapText(true);
        enterACharacter = new TextField();
        enterACharacter.setOnMouseClicked(mouseEvent -> {
            state = MODE_GUESS;
        });

        submitCharacter.setOnAction(actionEvent -> {
            input = (enterACharacter.getText().toUpperCase());
            submit();
            enterACharacter.setText("");
        });



        StackPane root = new StackPane();
        root.getChildren().add(row);
        root.getChildren().add(row1);
        root.getChildren().add(row2);

        HBox hBox1 = new HBox(secWoDash);
        hBox1.setPadding(new Insets(0, 20, 10, 10));
        HBox inputplayer = new HBox(inputCh, enterACharacter, submitCharacter);
        HBox messageOfInputHB= new HBox(messageOfInput);
        messageOfInputHB.setPadding(new Insets(0, 10, 10, 10));
        HBox hangmanDrawing = new HBox(imageView);
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        Image img = new Image("background-prison-cell.jpg");
        BackgroundImage bgi = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background bg = new Background(bgi);
        root.setBackground(bg);

        Label enterName= new Label("Enter your name:");
        enterYourName = new TextField();
        submitYourName = new Button("Submit");
        submitYourName.setOnAction(actionEvent -> {
            name = (enterYourName.getText());
            showName.setText("Welcome to game " + name);
            enterYourName.setText("");
        });
        enterYourName.setOnMouseClicked(mouseEvent -> {
            state = MODE_WORD;
        });


        HBox enterPlayerName= new HBox(enterName,enterYourName,submitYourName,showName);
        enterPlayerName.setPadding(new Insets(150, 10, 10, 10));

        VBox vBox = new VBox(enterPlayerName,inputplayer,hBox1,messageOfInputHB, row, row1, row2,hangmanDrawing);
        vBox.setPadding(new Insets(20, 30, 20, 20));
        root.getChildren().add(vBox);
        vBox.setSpacing(10);
        return root;
    }


    public HBox createRow(List<String> letters) {

        HBox hBox = new HBox();
        for (String letter : letters) {
            Button button = new Button(letter);
            button.setStyle("-fx-text-fill: #0000ff");
            button.setPrefSize(40,40);
            button.setOnAction(action -> {
                alph = (button.getText());
                if (state == MODE_WORD){
                    enterYourName.setText(enterYourName.getText() + alph);
                } else // MODE_GUESS
                    enterACharacter.setText(alph);
                // if player guess whole word
                //enterACharacter.setText(enterACharacter.getText()+alph);
            });
            hBox.getChildren().add(button);
        }
        return hBox;
    }

    public String createDashedWord(String word) {

        int count = word.length();
        return dash.repeat(count);
    }

    /**
     * Accepts a word and returns it with one space between each character
     *
     * @param word
     * @return
     */
    private String spaced(String word) {
        String output = "";
        for (char ch : word.toCharArray()) {
            output += String.format("%c ", ch);
        }
        return output;
    }

    public void submit() {

        if (wrongAttempt <= maxChances) {

            if (input == null || "".equals(input.trim())) {
                messageOfInput.setText("Invalid input. Please try again.");
                return;
            }

            if (correctGuesses.contains(input) || wrongGuesses.contains(input)) {
                messageOfInput.setText("You guessed this letter before. Try another one!");
                return; // don't do anything and just loop again
            }

            // if user guessed the whole word in one go, set the flag to true and skip the loop
            if (secretWord.equals(input)) {
                userWon = true;
                messageOfInput.setText("You won.");
                return;
            }

            if (secretWord.contains(input)) {
                // count the guess as correct only if it's one character
                if (input.length() == 1) {
                    correctGuesses.add(input);
                    messageOfInput.setText("You guessed right. Guess next letter: ");
                } else {
                    // if it's multiple characters, then it's not a correct guess even if it's part of the secret word
                    wrongGuesses.add(input);
                    wrongAttempt++;
                    messageOfInput.setText("You guessed wrong. you have spent " + wrongAttempt + "/" + maxChances + " of your chances to guess wrong! Guess a new letter: ");
                }
            } else {
                wrongGuesses.add(input);
                wrongAttempt++;
                messageOfInput.setText("You guessed wrong. you have spent " + wrongAttempt + "/" + maxChances + " of your chances to guess wrong, Guess a new letter: ");
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
            secWoDash.setText(new String(secretWordArray));

            if (!new String(secretWordArray).contains(dash)) {
                userWon = true;
                messageOfInput.setText("Congrats! You Won!");
                wrongAttempt = 0;
                return;
            }

            if (wrongAttempt >= maxChances) {
                getImage();
                messageOfInput.setText("Sorry! You did not win. The correct word is: " + secretWord);
                wrongAttempt = 0;
                return;
            }
        }
        getImage();
    }

    //get image of hangman drawing
    public void getImage() {
        switch (wrongAttempt) {
            case 0:
                break;
            case 1:
                imageView.setImage(firstMiss);
                break;
            case 2:
                imageView.setImage(secondMiss);
                break;
            case 3:
                imageView.setImage(thirdMiss);
                break;
            case 4:
                imageView.setImage(fourthMiss);
                break;
            case 5:
                imageView.setImage(fifthMiss);
                break;
            case 6:
                imageView.setImage(sixthMiss);
                break;
            case 7:
                imageView.setImage(seventhMiss);
                break;
            case 8:
                imageView.setImage(eighthMiss);
                // hasLost = true;
                break;

        }

    }
}

