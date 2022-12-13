package com.example.hangman1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Arrays;
import java.util.List;

public class Alphabet {
/*
   private Button A = new Button("A");
   private Button B = new Button("B");
   private Button C = new Button("C");
   private Button D = new Button("D");
   private Button E = new Button("E");
   private Button F = new Button("F");
    private Button G = new Button("G");
    private Button H = new Button("H");
    private Button I = new Button("I");
    private Button J = new Button("J");
    private Button K = new Button("K");
    private Button L = new Button("L");
    private Button M = new Button("M");
    private Button N = new Button("N");
    private Button O = new Button("O");
    private Button P = new Button("P");
    private Button Q = new Button("Q");
    private Button R = new Button("R");
    private Button S = new Button("S");
    private Button T = new Button("T");
    private Button U = new Button("U");
    private Button V = new Button("V");
    private Button W = new Button("W");
    private Button X = new Button("X");
    private Button Y = new Button("Y");
    private Button Z = new Button("Z");
    private Button[] rowOne = {A,B,C,D,E,F,G,H};
    private Button[] rowTwo = {I,J,K,L,M,N,O,P};
    private Button[] rowThree = {Q,R,S,T,U,V,W,X,Y,Z};
    */
Button A,B,C,D,E,F,G,H, // 8
        I,J,K,L,M,N,O,P, // 8
        Q,R,S,T,U,V,W,X,Y,Z; // 10
private Button[] buttons1 = {A,B,C,D,E,F,G,H};
private Button[] buttons2 = {I,J,K,L,M,N,O,P};
private Button[] buttons3 = {Q,R,S,T,U,V,W,X,Y,Z};
private final List<String> buttonArr = Arrays.asList("A","B","C","D","E","F","G","H");
private final List<String> buttonArr2 = Arrays.asList("I","J","K","L","M","N","O","P");
private final List<String> buttonArr3 = Arrays.asList("Q","R","S","T","U","V","W","X","Y","Z");
   /* public Button[] createButtons(Button[] array) {
        Button[] arr = new Button[array.length];
        for(int i = 0; i < array.length; i++) {
            arr[i] = array[i];
        }
        return arr;
    }*/
    public Button[] returnAndSetBtnName(String name) {
        //Button[] btnArr = createButtons(buttons1);
        //Button[] btnArr2 = createButtons(buttons2);
        //Button[] btnArr3 = createButtons(buttons3);
        Button[] arr = new Button[10];

        switch (name) {
            case "one": {
                for(int i = 0; i < buttons1.length; i++) {
                    buttons1[i] = new Button(buttonArr.get(i));
                }
                arr = buttons1;
            }
            break;
            case "two": {
                for(int i = 0; i < buttons2.length; i++) {
                    buttons2[i] = new Button(buttonArr2.get(i));
                }
                arr = buttons2;
            }
            break;
            case "three": {
                for(int i = 0; i < buttons3.length; i++) {
                    buttons3[i] = new Button(buttonArr3.get(i));
                }
                arr = buttons3;
            }
            break;
        }
        return arr;
    }
/*
    public Button[] returnButtonArr(String arr) {
        switch(arr) {
            case "rowOne":
                return rowOne;
            case "rowTwo":
                return rowTwo;
            case "rowThree":
                return rowThree;
        }
        return null;
    }

 */
}
