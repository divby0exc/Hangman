package com.example.hangman1;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application extends javafx.application.Application {
    private Keyboard key = new Keyboard();
    private Button A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;
    private List<Button> buttons = Arrays.asList(A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z);
    private final List<String> buttonArr = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","X","Y","Z");
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("HANGMAN");
        GridPane pane = new GridPane();

        Button A = new Button("A");Button B = new Button("B");Button C = new Button("C");Button D = new Button("D");Button E = new Button("E");
        Button F = new Button("F"); Button G = new Button("G");Button H = new Button("H");Button I = new Button("I");Button J = new Button("J");
        Button K = new Button("K");Button L = new Button("L");Button M = new Button("M");Button N = new Button("N");
        Button O = new Button("O");Button P = new Button("P");Button Q = new Button("Q");Button R = new Button("R");Button S = new Button("S");Button T = new Button("T");
        Button U = new Button("U");Button V = new Button("V");Button W = new Button("W");Button X = new Button("X");Button Y = new Button("Y");Button Z = new Button("Z");
        Button[] rowOne = new Button[8];
        for(int i = 0; i < 8; i++) {
            rowOne[i] = buttons.get(i);
        }
        //Button[] rowOne = {A,B,C,D,E,F,G,H};
        Button[] rowTwo = {I,J,K,L,M,N,O,P};
        Button[] rowThree = {Q,R,S,T,U,V,W,X,Y,Z};
        pane.addRow(0,rowOne);
        pane.addRow(1,rowTwo);
        pane.addRow(2,rowThree);
        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent kev) {
                key.getKeyPressed(kev);
            }
        });

            stage.setScene(scene);
            stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}