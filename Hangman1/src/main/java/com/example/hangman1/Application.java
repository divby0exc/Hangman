package com.example.hangman1;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Application extends javafx.application.Application {
    SceneChoice sceneChoice = new SceneChoice();
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("HANGMAN");
        stage.getIcons().add(new Image("8.png"));

        // Start
        Scene gameMenu = sceneChoice.gameMenu(stage);
        stage.setScene(gameMenu);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}