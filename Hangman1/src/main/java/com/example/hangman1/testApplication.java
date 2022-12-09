package com.example.hangman1;


import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class testApplication extends javafx.application.Application{

    public void start(Stage stage) throws Exception {

        //Adding every state of hangman
        Image firstMiss = new Image("1.png");
        Image secondMiss = new Image("2.png");
        Image thirdMiss  = new Image("3.png");
        Image fourthMiss = new Image("4.png");
        Image fifthMiss = new Image("5.png");
        Image sixthMiss = new Image("6.png");
        Image seventhMiss = new Image("7.png");
        Image eighthMiss = new Image("8.png");

        //Imageview
        ImageView imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);

        // What image to display depending on how many wrongGuesses
        int wrongGuesses = 8;
        switch (wrongGuesses) {
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


        Group root = new Group();
        Scene scene = new Scene(root, Color.RED);
        root.getChildren().add(imageView);
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch();}
}
