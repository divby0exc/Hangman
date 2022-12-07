package com.example.hangman1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VirtualKeyboard extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        List<String> letters = new ArrayList<>(Arrays.asList("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"));
        List<String> letters1 = new ArrayList<>(Arrays.asList("A", "S", "D", "F", "G", "H", "J", "K", "L"));
        List<String> letters2 = new ArrayList<>(Arrays.asList("Z", "X", "C", "V", "B", "N", "M"));
        HBox row=creatRow(letters);
        row.setPadding(new Insets(10, 10,10,10));
        HBox row1=creatRow(letters1);
        row1.setPadding(new Insets(40, 40,10,10));
        HBox row2=creatRow(letters2);
        row2.setPadding(new Insets(70, 70,10,10));

        Group root = new Group();
        root.getChildren().add(row);
        root.getChildren().add(row1);
        row2.setMinWidth(100);
        root.getChildren().add(row2);

        Scene scene = new Scene(root, 400, 100, Color.BEIGE);
        stage.setTitle("Keyboard");
        stage.setScene(scene);
        stage.show();

    }
    public HBox creatRow(List<String> letters){
        HBox hBox = new HBox();
        for (String letter : letters){
            Button button = new Button(letter);
            button.setStyle("-fx-text-fill: #0000ff");
            hBox.getChildren().add(button);
        }
        return hBox;
    }

    public static void main(String[] args) {
        launch();
    }
}