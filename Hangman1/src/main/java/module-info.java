module com.example.hangman1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.hangman1 to javafx.fxml;
    exports com.example.hangman1;
}