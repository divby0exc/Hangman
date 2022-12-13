package com.example.hangman1;

import javafx.scene.control.TextField;

public class RestrictiveTextField extends TextField {
    @Override
    public void appendText(String s) {
        if(!(s.length() >1)) {
            super.appendText(s);
        }
    }
}
