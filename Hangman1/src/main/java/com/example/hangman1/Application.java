package com.example.hangman1;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage)
    {

        try {

            // set title for the stage
            stage.setTitle("Jail Man");

            // create a label
            Label chars = new Label("_ _ _ _ _ _ _ _");
            Label alphabet  = new Label("A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z");
            // create a text field
            TextField textfield = new TextField("Input: ");

            // set preferred column count
            textfield.setPrefColumnCount(10);

            // create a button
            Button button = new Button("Guess!");

            // add the label, text field and button
            VBox vbox = new VBox(chars, alphabet, textfield, button);

            // set spacing
            vbox.setSpacing(10);

            // set alignment for the HBox
            vbox.setAlignment(Pos.BOTTOM_CENTER);


            // create a scene
            Scene scene = new Scene(vbox, 860, 520);

            // create a input stream
            FileInputStream input = new FileInputStream("/home/paraply/IdeaProjects/Hangman/Hangman1/src/main/resources/Prison-jail.png");

            // create a image
            Image image = new Image(input);

            // create a background image
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT);

            // create Background
            Background background = new Background(backgroundimage);

            // set background
            vbox.setBackground(background);

            // set the scene
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        }

        catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }



}