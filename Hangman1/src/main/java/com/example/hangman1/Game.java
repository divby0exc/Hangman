package com.example.hangman1;


import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class Game {
    HangmanDrawing hangmanDrawing = new HangmanDrawing();
    String playerName;
    String secretWord="";
    Label playerNameLabel;
    TextField playerNameField;
    int wrongAttempt = 0;
    char[] secretWordToArray;
    final String dash = "-";
    String input;
    Label guessACharacterLabel;
    Label messageOfInput;
    Label showSecretWordIndashFormat;
    TextField guessACharacterField;
    Button submitCharacter;
    Button submitPlayerName;
    boolean userWon = false;
    List<String> correctGuesses = new ArrayList<>();
    List<String> wrongGuesses = new ArrayList<>();
    private final static int MODE_WORD = 1;
    private final static int MODE_GUESS = 2;
    private int mode = MODE_WORD;
    int maxChances = 8;

    VirtualKeyboard vk = new VirtualKeyboard();

    TextField textFieldOfKeyboardKeyPress;

    public Pane startGame() {

        // Register player name
        playerNameLabel = new Label("Enter the Name of player:");
        playerNameField = new TextField();
        playerNameField.setOnMouseClicked(mouseEvent -> {
            textFieldOfKeyboardKeyPress = playerNameField;
            mode = MODE_WORD;
        });
        submitPlayerName = new Button("submit player");
        submitPlayerName.setOnAction(actionEvent -> {
            playerName = (playerNameField.getText());
            playerNameField.setText("");});
        HBox enterPlayersName = new HBox(playerNameLabel, playerNameField, submitPlayerName);
        enterPlayersName.setPadding(new Insets(0, 10, 10, 10));
        playerNameField.setFont(new Font("Arial", 15));

        //Get the secret word and change
        Label SecretWordLabel = new Label("Enter secret Word for the player:");
        PasswordField secretWordField = new PasswordField();
        secretWordField.setOnMouseClicked(mouseEvent -> {
            textFieldOfKeyboardKeyPress = secretWordField;
            mode = MODE_WORD;
        });
        Button submitSecretWord = new Button("submit secret word");
        submitSecretWord.setOnAction(e -> {
            if ("".equals(secretWordField.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please enter the secret word first!");
            } else {
                secretWord= secretWordField.getText().toUpperCase();
                secretWordToArray = dash.repeat(secretWord.length()).toCharArray();
                showSecretWordIndashFormat.setText(new String(secretWordToArray));
                guessACharacterLabel.setText("Welcome to the game "+ " "+ playerName+ "!" +" Guess a letter:");

            }
        });

        HBox secretWordHbox= new HBox(SecretWordLabel,secretWordField,submitSecretWord);

        // Check spelling of the secret word
        Label lblLanguage = new Label("Select Language");
        String languages[] = {"Swedish", "English"};
        ComboBox comboBox = new ComboBox(FXCollections.observableArrayList(languages));
        comboBox.setValue("Swedish");
        HBox spellcheckingOfWord= new HBox( lblLanguage,comboBox);
        spellcheckingOfWord.setPadding(new Insets(240, 10, 10, 10));

        //Show secret word in the form of dash
        showSecretWordIndashFormat = new Label();
        showSecretWordIndashFormat.setFont(new Font("Arial", 25));
        HBox secretWordInDash = new HBox(showSecretWordIndashFormat);
        secretWordInDash.setPadding(new Insets(10, 20, 10, 10));

        // Get input of player and show the result
        guessACharacterLabel = new Label("Guess a letter");
        guessACharacterField = new TextField();
        guessACharacterField.setOnMouseClicked(mouseEvent -> {
            textFieldOfKeyboardKeyPress = guessACharacterField;
            mode = MODE_GUESS;
        });
        submitCharacter = new Button("Submit");
        submitCharacter.setOnAction(actionEvent -> {
            input = guessACharacterField.getText().toUpperCase();
            submit();
            guessACharacterField.setText("");
        });
        HBox guessACharachter = new HBox(guessACharacterLabel, guessACharacterField, submitCharacter);

        messageOfInput = new Label();
        messageOfInput.setFont(new Font("Arial", 14));
        messageOfInput.setMaxWidth(400);
        messageOfInput.setWrapText(true);
        HBox messageOfInputHB= new HBox(messageOfInput);
        messageOfInputHB.setPadding(new Insets(0, 10, 10, 10));

        //Show image of hangman
        ImageView hangmanImageView = hangmanDrawing.getImageOfHangman(wrongAttempt);
        HBox hangmanDrawing = new HBox(hangmanImageView);

        //Add background to scene
        Image img = new Image("background-prison-cell.jpg");
        BackgroundImage bgi = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background bg = new Background(bgi);

        VBox keyboard = new VirtualKeyboard().createKeyBoard(actionEvent -> {
            String alph = ((Button)actionEvent.getSource()).getText();
            if (mode == MODE_WORD){
                textFieldOfKeyboardKeyPress.setText(textFieldOfKeyboardKeyPress.getText() + alph);
            } else {
                textFieldOfKeyboardKeyPress.setText(alph);
            }
        });

        StackPane root = new StackPane();
        root.setBackground(bg);
        VBox vBox = new VBox(spellcheckingOfWord,enterPlayersName,secretWordHbox, guessACharachter, secretWordInDash, messageOfInputHB, keyboard, hangmanDrawing);
        vBox.setPadding(new Insets(20, 30, 20, 20));
        root.getChildren().add(vBox);
        vBox.setSpacing(10);
        return root;
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
                    //if (String.valueOf(secretLetter).equalsIgnoreCase(String.valueOf(guessedChar))) {
                    if (secretLetter == guessedChar){
                        secretWordToArray[i] = secretLetter;
                    }
                    i++;
                }
            }
            showSecretWordIndashFormat.setText(new String(secretWordToArray).toUpperCase());

            if (!new String(secretWordToArray).contains(dash)) {
                userWon = true;
                messageOfInput.setText("Congrats " + playerName +"!  You won the game" );
                wrongAttempt = 0;
                return;
            }

            if (wrongAttempt >= maxChances) {
                hangmanDrawing.getImageOfHangman(wrongAttempt);
                messageOfInput.setText("Sorry! You did not win. The correct word is: " + secretWord);
                wrongAttempt = 0;
                return;
            }
        }
        hangmanDrawing.getImageOfHangman(wrongAttempt);
    }
}


