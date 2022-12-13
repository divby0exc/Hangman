package com.example.hangman1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class  MouseClickButtons {
    Alphabet al = new Alphabet();

    public void keyListener(Button[] arr) {
        for(int i = 0; i < arr.length; i++) {
            String alp = arr[i].getText();
            arr[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println(alp);
                }
            });
        }
    }
}
