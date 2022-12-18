package com.example.hangman1;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VirtualKeyboard {
    private static final List<String> lettersRow1 = new ArrayList<>(Arrays.asList("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"));
    private static final List<String> lettersRow2 = new ArrayList<>(Arrays.asList("A", "S", "D", "F", "G", "H", "J", "K", "L"));
    private static final List<String> lettersRow3 = new ArrayList<>(Arrays.asList("Z", "X", "C", "V", "B", "N", "M"));

    public VBox createKeyBoard(EventHandler<ActionEvent> keyPressedEventHandler){

        HBox keyboardRow1 = createButtonsOfRaw(lettersRow1,keyPressedEventHandler);
        HBox keyboardRow2 = createButtonsOfRaw(lettersRow2,keyPressedEventHandler);
        HBox keyboardRow3 = createButtonsOfRaw(lettersRow3,keyPressedEventHandler);
        keyboardRow1.setPadding(new Insets(10, 10, 10, 10));
        keyboardRow2.setPadding(new Insets(-20, 10, 10, 10));
        keyboardRow3.setPadding(new Insets(-20, 10, 10, 10));

        VBox vBox = new VBox(keyboardRow1, keyboardRow2, keyboardRow3 );
        return vBox;
    }

    public HBox createButtonsOfRaw(List<String> letters, EventHandler<ActionEvent> keyPressedEventHandler) {
        HBox hBox = new HBox();
        for (String letter : letters) {
            Button button = new Button(letter);
            button.setStyle("-fx-text-fill: #0000ff");
            button.setPrefSize(40,40);
            button.setOnAction(keyPressedEventHandler);

            hBox.getChildren().add(button);
        }
        return hBox;
    }


}
