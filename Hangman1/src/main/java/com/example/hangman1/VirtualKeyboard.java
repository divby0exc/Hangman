package com.example.hangman1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VirtualKeyboard extends Application {

    String secretWordInDash;
    int wrongAttempt = 0;
    char[] secretWordArray;

    final String dash = "-";
    ;
    String secretWord;
    String alph;
    String input;
    Label inputCh;
    Label messageOfInput;
    Label secWoDash;
    TextField enterACharacter;
    Button submitCharacter;
    boolean userWon = false;
    List<String> correctGuesses = new ArrayList<>();
    List<String> wrongGuesses = new ArrayList<>();

    int maxChances = 8;
    Image firstMiss = new Image("1.png");
    Image secondMiss = new Image("2.png");
    Image thirdMiss  = new Image("3.png");
    Image fourthMiss = new Image("4.png");
    Image fifthMiss = new Image("5.png");
    Image sixthMiss = new Image("6.png");
    Image seventhMiss = new Image("7.png");
    Image eighthMiss = new Image("8.png");
    ImageView imageView = new ImageView();


    @Override
    public void start(Stage stage) throws IOException {
        List<String> letters = new ArrayList<>(Arrays.asList("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"));
        List<String> letters1 = new ArrayList<>(Arrays.asList("A", "S", "D", "F", "G", "H", "J", "K", "L"));
        List<String> letters2 = new ArrayList<>(Arrays.asList("Z", "X", "C", "V", "B", "N", "M"));
        HBox row = creatRow(letters);
        row.setPadding(new Insets(10, 10, 10, 10));
        HBox row1 = creatRow(letters1);
        row1.setPadding(new Insets(-20, 10, 10, 10));
        HBox row2 = creatRow(letters2);
        row2.setPadding(new Insets(-20, 10, 10, 10));


        Group root = new Group();
        root.getChildren().add(row);
        root.getChildren().add(row1);
        row2.setMinWidth(100);
        root.getChildren().add(row2);


        secWoDash = new Label(secretWordInDash);
        secWoDash.setFont(new Font("Arial", 25));
        /*secWoDash.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"),
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));*/

        PasswordField ownWord = new PasswordField();
        Label word = new Label("Enter your word:");
        word.setFont(new Font("Arial", 12));
        word.setTextAlignment(TextAlignment.CENTER);
        Button button = new Button("OK");
        button.setOnAction(action -> {
            secretWord = (ownWord.getText());
            System.out.print(secretWord);
            secretWordArray = dash.repeat(secretWord.length()).toCharArray();
            secWoDash.setText(new String(secretWordArray));

        });

        inputCh = new Label("Guess a character");
        messageOfInput = new Label();
        enterACharacter = new TextField();
        submitCharacter = new Button("Submit");
        submitCharacter.setOnAction(actionEvent -> {
            input = enterACharacter.getText();
            submit();
            enterACharacter.setText("");

        });


        HBox hBox1 = new HBox(word, ownWord, button, secWoDash);
        hBox1.setPadding(new Insets(20, 20, 10, 10));

        HBox inputplayer = new HBox(inputCh, enterACharacter, submitCharacter, messageOfInput);
        //hBox1.setPadding(new Insets(30, 30, 10, 10));

        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        HBox hangmanDrawing = new HBox(imageView);
        //hBox1.setPadding(new Insets(40, 40, 10, 10));




        VBox vBox = new VBox(row, row1, row2, hBox1, inputplayer,hangmanDrawing);
        vBox.setPadding(new Insets(30, 30, 10, 10));
        root.getChildren().add(vBox);
        vBox.setSpacing(10);


        Scene scene = new Scene(root, 600, 200, Color.BEIGE);
        stage.setTitle("Keyboard");
        stage.setScene(scene);
        stage.show();

    }

    public HBox creatRow(List<String> letters) {

        HBox hBox = new HBox();
        for (String letter : letters) {
            Button button = new Button(letter);
            button.setStyle("-fx-text-fill: #0000ff");
            button.setOnAction(action -> {
                alph = (button.getText());
                System.out.print(alph);

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
     * @param word
     * @return
     */
    private String spaced(String word){
        String output = "";
        for (char ch : word.toCharArray()) {
            output += String.format("%c ",ch);
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
                    messageOfInput.setText("\r\nYou guessed right. Guess next letter: ");
                } else {
                    // if it's multiple characters, then it's not a correct guess even if it's part of the secret word
                    wrongGuesses.add(input);
                    wrongAttempt++;
                    messageOfInput.setText("\r\nYou guessed wrong. you have spent " + wrongAttempt + "/" + maxChances + " of your chances to guess wrong! Guess a new letter: ");
                }
            } else {
                wrongGuesses.add(input);
                wrongAttempt++;
                messageOfInput.setText("\r\nYou guessed wrong. you have spent " + wrongAttempt + "/" + maxChances + " of your chances to guess wrong, Guess a new letter: ");
            }




            // process the guesses and keep the results as a word.
            for (String guess : correctGuesses) {
                char guessedChar = guess.charAt(0);
                int i = 0;
                for (char secretLetter : secretWord.toCharArray()) {
                    if (secretLetter == guessedChar) {
                        secretWordArray[i] = secretLetter;
//                        messageOfInput.setText("Congrats! You Won!");
                    }
                    i++;
                }
            }
            // present the word
            //printWordArray(secretWordArray);
            //secretWordInDash = createDashedWord(secretWordArray.toString());

            secWoDash.setText(new String(secretWordArray));

            if (!new String(secretWordArray).contains(dash)) {
                userWon = true;
                messageOfInput.setText("\r\nCongrats! You Won!");
                wrongAttempt =0;
                return;
            }

            if (wrongAttempt >= maxChances) {
                messageOfInput.setText("\r\nSorry! You did not win. The correct word is: \r\n" + secretWord);
                wrongAttempt =0;
                return;
            }
        }
        getImage();
    }

    public void getImage(){
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

    public static void main(String[] args) {
        launch();
    }
}