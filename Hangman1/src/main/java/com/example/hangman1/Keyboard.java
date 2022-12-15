package com.example.hangman1;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.Arrays;
import java.util.List;

public class Keyboard {

    String code;
    public void getKeyPressed(KeyEvent kev) {
        code = kev.getCode().toString();
        final List<String> buttonArr = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","X","Y","Z");
        for(int i = 0; i < buttonArr.size(); i++) {
            if (kev.getCode().toString().equals(buttonArr.get(i))) {
                System.out.println(buttonArr.get(i));
            }
        }
    }
}
