package com.example.hangman1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HangmanImage {
    static Image firstMiss = new Image("1.png");
    static Image secondMiss = new Image("2.png");
    static Image thirdMiss = new Image("3.png");
    static Image fourthMiss = new Image("4.png");
    static Image fifthMiss = new Image("5.png");
    static Image sixthMiss = new Image("6.png");
    static Image seventhMiss = new Image("7.png");
    static Image eighthMiss = new Image("8.png");
    Game game = new Game();
    static ImageView imageView = new ImageView();

    public static ImageView getImageView() {
        return imageView;
    }

    //get image of hangman drawing
    public static void getImage() {
        switch (Game.getWrongAttempt()) {
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
}
