package com.example.hangman1;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

import java.net.UnknownHostException;

public class GameCreator {

    public static Scene createGames(String secretWord, int numberOfPlayers) throws UnknownHostException {

        FlowPane flowPane = new FlowPane();
        for (int i = 0; i < numberOfPlayers; i++) {
            flowPane.getChildren().add(new Game(secretWord).startGame());
        }
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.fitToWidthProperty().set(true);
        scrollPane.setContent(flowPane);
        Scene scene = new Scene(scrollPane, 550, 200, Color.BEIGE);




        return scene;
    }

}
