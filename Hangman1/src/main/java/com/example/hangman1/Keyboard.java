package com.example.hangman1;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Keyboard implements Runnable {

    String code;
    public String getKeyPressed(KeyEvent kev) {
        code = kev.getCode().toString();
        final List<String> buttonArr = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","X","Y","Z");
        for(int i = 0; i < buttonArr.size(); i++) {
            if (kev.getCode().toString().equals(buttonArr.get(i))) {
                System.out.println(buttonArr.get(i));
            }
        }
        return code;
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
       Keyboard keyboard = new Keyboard();
       keyboard.run();
    }
}
