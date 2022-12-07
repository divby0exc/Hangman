package com.example.hangman1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application extends javafx.application.Application {
    private Keyboard key = new Keyboard();
    private Alphabet al = new Alphabet();
    private MouseClickButtons mcb = new MouseClickButtons();
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("HANGMAN");
        
        GridPane pane = new GridPane();
        Button[] rowOne = al.returnAndSetBtnName("one");
        Button[] rowTwo = al.returnAndSetBtnName("two");
        Button[] rowThree = al.returnAndSetBtnName("three");

        mcb.keyListener(rowOne);
        mcb.keyListener(rowTwo);
        mcb.keyListener(rowThree);

        Line firstLine = new Line(727,697,727,194);
        pane.getChildren().add(firstLine);



        pane.addRow(0, rowOne);
        pane.addRow(1, rowTwo);
        pane.addRow(2, rowThree);
        Image img = new Image("C:\\Users\\DefconK1ll4\\Desktop\\Hangman\\Hangman1\\src\\main\\java\\com\\example\\hangman1\\background-prison-cell.jpg");
        BackgroundImage bgi = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        Background bg = new Background(bgi);
        pane.setBackground(bg);
        stage.setMaximized(true);
        Scene scene = new Scene(pane);

        scene.setOnMouseClicked((event) -> {
            System.out.println("X " + event.getX() + " Y " + event.getY());
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent kev) {
                key.getKeyPressed(kev);
            }
        });


            stage.setScene(scene);
            stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}