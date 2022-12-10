package com.example.hangman1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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

    String secretWordInDash=("-----");
    String secretWord;
    String alph;

    @Override
    public void start(Stage stage) throws IOException {
        List<String> letters = new ArrayList<>(Arrays.asList("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"));
        List<String> letters1 = new ArrayList<>(Arrays.asList("A", "S", "D", "F", "G", "H", "J", "K", "L"));
        List<String> letters2 = new ArrayList<>(Arrays.asList("Z", "X", "C", "V", "B", "N", "M"));
        HBox row=creatRow(letters);
        row.setPadding(new Insets(10, 10,10,10));
        HBox row1=creatRow(letters1);
        row1.setPadding(new Insets(-20, 10,10,10));
        HBox row2=creatRow(letters2);
        row2.setPadding(new Insets(-20, 10,10,10));


        Group root = new Group();
        root.getChildren().add(row);
        root.getChildren().add(row1);
        row2.setMinWidth(100);
        root.getChildren().add(row2);


        Label secWoDash = new Label(secretWordInDash);
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
            secretWord=(ownWord.getText());
            System.out.print(secretWord);
            secretWordInDash = createDashedWord(secretWord);
            secWoDash.setText(secretWordInDash);
        });
        HBox hBox1=new HBox(word,ownWord,button,secWoDash);
        hBox1.setPadding(new Insets(20, 20,10,10));

        VBox vBox= new VBox(row,row1,row2,hBox1);
        vBox.setPadding(new Insets(30, 30,10,10));
        root.getChildren().add(vBox);
        vBox.setSpacing(10);


        Scene scene = new Scene(root, 600, 200, Color.BEIGE);
        stage.setTitle("Keyboard");
        stage.setScene(scene);
        stage.show();

    }
    public HBox creatRow(List<String> letters){

        HBox hBox = new HBox();
        for (String letter : letters){
            Button button = new Button(letter);
            button.setStyle("-fx-text-fill: #0000ff");
            button.setOnAction(action -> {
                  alph=(button.getText());
                  System.out.print(alph);

            });
            hBox.getChildren().add(button);
        }
        return hBox;
    }
    public String createDashedWord(String word) {
        final String dash = "- ";
        int count = word.length();
        return dash.repeat(count);
    }


    public static void main(String[] args) {
        launch();
    }
}