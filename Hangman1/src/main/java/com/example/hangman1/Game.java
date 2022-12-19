package com.example.hangman1;


import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import model.User;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    HangmanDrawing hangmanDrawing = new HangmanDrawing();
    VirtualKeyboard vk = new VirtualKeyboard();
    SpellChecker sp= new SpellChecker();
    Logic logic = new Logic();
    String playerName;
    Label playerNameLabel;
    TextField playerNameField;
    final String dash = "-";
    String input;
    Label guessACharacterLabel;
    Label messageOfInput;
    Label showSecretWordIndashFormat;
    TextField guessACharacterField;
    Button submitCharacter;
    Button submitPlayerName;
    boolean userWon = false;
    private final static int MODE_WORD = 1;
    private final static int MODE_GUESS = 2;
    private int mode = MODE_WORD;
    TextField textFieldOfKeyboardKeyPress;
    PasswordField secretWordField;

    public String getSecretWord() {
        return secretWordField.getText();
    }

    public void ceuvh() {
        String hello = getSecretWord();
    }

    public Pane startGame() throws UnknownHostException {
        User user = new User();
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
            user.setName(playerName);
            playerNameField.setText("");
        });
        HBox enterPlayersName = new HBox(playerNameLabel, playerNameField, submitPlayerName);
        enterPlayersName.setPadding(new Insets(0, 10, 10, 10));
        playerNameField.setFont(new Font("Arial", 15));


        // Get language of the secret word
        Label lblLanguage = new Label("Select Language");
        String languages[] = {"Swedish", "English"};
        ComboBox comboBox = new ComboBox(FXCollections.observableArrayList(languages));
        comboBox.setValue("Swedish");
        HBox spellcheckingOfWord = new HBox(lblLanguage, comboBox);
        spellcheckingOfWord.setPadding(new Insets(240, 10, 10, 10));

        //Get the secret word and also check spelling of the word
        Label SecretWordLabel = new Label("Enter secret Word for the player:");
        secretWordField = new PasswordField();
        secretWordField.setOnMouseClicked(mouseEvent -> {
            textFieldOfKeyboardKeyPress = secretWordField;
            user.setSecretWord(secretWordField.getText());
            mode = MODE_WORD;

        });
        Button submitSecretWord = new Button("submit secret word");
        submitSecretWord.setOnAction(e -> {
            if ("".equals(secretWordField.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please enter the secret word first!");
                alert.show();
            }if (!sp.SpellCheck(secretWordField.getText(), (String) comboBox.getValue())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Word invalid. Please check your spelling");
                    alert.show();
        }else {
                String secretWord = secretWordField.getText().toUpperCase();
                logic = new Logic();
                logic.setSecretWord(secretWord);
                showSecretWordIndashFormat.setText(logic.getWordToDisplay());
                guessACharacterLabel.setText("Welcome to the game " + " " + user.getName() + "!" + " Guess a letter:");
                hangmanDrawing.show();
            }
        });
        HBox secretWordHbox = new HBox(SecretWordLabel, secretWordField, submitSecretWord);



        //Show secret word in the form of dash
        showSecretWordIndashFormat = new Label();
        showSecretWordIndashFormat.setFont(new Font("Arial", 25));
        HBox secretWordInDash = new HBox(showSecretWordIndashFormat);
        secretWordInDash.setPadding(new Insets(10, 20, 10, 10));

        // Get input of player and check the input with secret word
        guessACharacterLabel = new Label("Guess a letter");
        guessACharacterField = new TextField();
        guessACharacterField.setOnMouseClicked(mouseEvent -> {
            textFieldOfKeyboardKeyPress = guessACharacterField;
            mode = MODE_GUESS;
        });
        submitCharacter = new Button("Submit");
        submitCharacter.setOnAction(actionEvent -> {
            input = guessACharacterField.getText().toUpperCase();
            int submitResult = logic.submit(input);
            switch (submitResult) {
                case Logic.CODE_INVALID -> {
                    messageOfInput.setText("Invalid input. Please try again.");
                }
                case Logic.CODE_DUPLICATE_GUESS -> {
                    messageOfInput.setText("You guessed this letter before. Try another one!");
                }
                case Logic.CODE_CORRECT_GUESS -> {
                    messageOfInput.setText("You guessed right. Guess next letter: ");
                    showSecretWordIndashFormat.setText(logic.getWordToDisplay());
                }
                case Logic.CODE_WRONG_GUESS -> {
                    messageOfInput.setText("You guessed wrong. you have spent " + logic.getWrongAttempt() + "/" + logic.getMaxChances() + " of your chances to guess wrong! Guess a new letter: ");
                    hangmanDrawing.getImageOfHangman(logic.getWrongAttempt());
                }
                case Logic.CODE_LOST -> {
                    messageOfInput.setText("Sorry "+ playerName + "! you did not win. The correct word is: " + logic.getSecretWord());
                    hangmanDrawing.getImageOfHangman(logic.getWrongAttempt());
                }
                case Logic.CODE_WON -> {
                    messageOfInput.setText("Congrats " + playerName + "!  You won the game");
                    showSecretWordIndashFormat.setText(logic.getWordToDisplay());
                    hangmanDrawing.hide();

                }
            }
            guessACharacterField.setText("");
        });
        HBox guessACharacter = new HBox(guessACharacterLabel, guessACharacterField, submitCharacter);

        // Show the result of comparing input with secret word
        messageOfInput = new Label();
        messageOfInput.setFont(new Font("Arial", 14));
        messageOfInput.setMaxWidth(400);
        messageOfInput.setWrapText(true);
        HBox messageOfInputHB = new HBox(messageOfInput);
        messageOfInputHB.setPadding(new Insets(0, 10, 10, 10));

        //Show image of hangman
        ImageView hangmanImageView = hangmanDrawing.getImageOfHangman(0);

        //Add background to scene
        Image img = new Image("background-prison-cell.jpg");
        BackgroundImage bgi = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background bg = new Background(bgi);

        VBox keyboard = new VirtualKeyboard().createKeyBoard(actionEvent -> {
            String alph = ((Button) actionEvent.getSource()).getText();
            if (mode == MODE_WORD) {
                textFieldOfKeyboardKeyPress.setText(textFieldOfKeyboardKeyPress.getText() + alph);
            } else {
                textFieldOfKeyboardKeyPress.setText(alph);
            }
        });

        StackPane root = new StackPane();
        root.setBackground(bg);
        VBox vBox = new VBox(spellcheckingOfWord, enterPlayersName, secretWordHbox, guessACharacter, secretWordInDash, messageOfInputHB, keyboard, hangmanImageView);
        vBox.setPadding(new Insets(20, 30, 20, 20));
        root.getChildren().add(vBox);
        vBox.setSpacing(10);
        return root;
    }
}

