package com.example.hangman1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;

public class HelloController {
    @FXML
    private Label chars;

    @FXML
    public Label alphabet;
    @FXML
    public BackgroundImage background;

    @FXML
    protected void onHelloButtonClick() {
        background.getImage();
        chars.getText();
        alphabet.getText();
    }
}