package com.example.hangman1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public  class HangmanDrawing {
    Image firstMiss = new Image("1.png");
    Image secondMiss = new Image("2.png");
    Image thirdMiss = new Image("3.png");
    Image fourthMiss = new Image("4.png");
    Image fifthMiss = new Image("5.png");
    Image sixthMiss = new Image("6.png");
    Image seventhMiss = new Image("7.png");
    Image eighthMiss = new Image("8.png");
    ImageView imageView = new ImageView();


    public ImageView getImageOfHangman(int wrongAttempt) {

        switch (wrongAttempt) {
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
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        return imageView;
    }
}
