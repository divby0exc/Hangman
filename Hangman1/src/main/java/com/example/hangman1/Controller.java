package com.example.hangman1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    @FXML
    private Label changeThis;
    private final List<String> buttonArr = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","X","Y","Z");
    @FXML
    private Button A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;
    private String ID;
    @FXML
    private final List<Button> getButton = new ArrayList<>();
    private String modText = "";


    public String getID() {
        setAllButtons();
        for (int i = 0; i < buttonArr.size()-1; i++) {
            if(getButton.get(i).getId().equals(buttonArr.get(i))) {
                ID = buttonArr.get(i);
            }
        }
        return ID;
    }


        //System.out.println(modText);
    public void printID() {
        System.out.println(getID());
    }
    public void setAllButtons() {
        getButton.add(A);getButton.add(B);getButton.add(C);getButton.add(D);getButton.add(E);
        getButton.add(F);getButton.add(G);getButton.add(H);getButton.add(I);getButton.add(J);
        getButton.add(K);getButton.add(L);getButton.add(M);getButton.add(N);getButton.add(O);
        getButton.add(P);getButton.add(Q);getButton.add(R);getButton.add(S);getButton.add(T);
        getButton.add(U);getButton.add(V);
    }


}