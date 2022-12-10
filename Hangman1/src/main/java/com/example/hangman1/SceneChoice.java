package com.example.hangman1;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.nio.file.Path;

import static java.lang.System.*;

public class SceneChoice {
    private Alphabet al = new Alphabet();
    private MouseClickButtons mcb = new MouseClickButtons();
    private Keyboard key = new Keyboard();
    private javafx.scene.Scene whichScene;
    private String howToPlayMultiplayer = "Du ska komma på ett ord för den andre spelarens gubbe. \nDen andre spelaren ska försöka gissa ordet genom att trycka på en bokstav i taget. \nDet går såklart bra att även trycka på tangenterna också. ";
    private String howToPlaySingleplayer = "Du får ett ord av datorn som du ska gissa. \nDu kan trycka på bokstäverna på skärmen eller på tangentbordet. ";
    private String howToPlayEnd = "\nDu har 10 försök på dig. Du ser när strecken ritas på skärmen \nmen även försöken ovanför bokstäverna så du slipper komma ihåg allt.";
    private String howToPlayInteractive = "I detta läge kan du bestämma själv hur många streck som ska ritas. \nDet betyder att ni bestämmer själva om det ska gå fort eller om ni vill rita mer streck så spelet blir längre. \nNär gubben är hängd så är spelet över. ";
    private boolean hard = false; // Unlimited word length and randomized words in both swedish and english
    private boolean intermediate = false; // Randomized words in swedish
    private boolean easy = true; // Limit word length and use simple words
    private boolean gameStarted = false;
    private String guessTheWord = "";
    String secretWord;
    String secretWordInDash = " ".repeat(10);



    public javafx.scene.Scene mainGame() {
        GridPane pane = new GridPane();
        Button[] rowOne = al.returnAndSetBtnName("one");
        Button[] rowTwo = al.returnAndSetBtnName("two");
        Button[] rowThree = al.returnAndSetBtnName("three");

        mcb.keyListener(rowOne);
        mcb.keyListener(rowTwo);
        mcb.keyListener(rowThree);

        pane.addRow(0, rowOne);
        pane.addRow(1, rowTwo);
        pane.addRow(2, rowThree);
        Image img = new Image("background-prison-cell.jpg");
        BackgroundImage bgi = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background bg = new Background(bgi);
        pane.setBackground(bg);

        //creating enter own word
        Label secWoDash = new Label(secretWordInDash);
        secWoDash.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"),
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        PasswordField ownWord = new PasswordField();
        Label word = new Label("Enter your word:");
        Button button = new Button("OK");
        button.setOnAction(action -> {
            secretWord=(ownWord.getText());
            System.out.println(secretWord);
            secretWordInDash = createDashedWord(secretWord);
            secWoDash.setText(secretWordInDash);
        });
        HBox hbox = new HBox(word,ownWord,button,secWoDash);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        pane.add(hbox,30,9);








        javafx.scene.Scene mainGameScene = new javafx.scene.Scene(pane);

        //Checking pixels to decide positions on the lines
        mainGameScene.setOnMouseClicked((event) -> {
            out.println("X " + event.getX() + " Y " + event.getY());
        });
        // Keyboard listener
        mainGameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent kev) {
                key.getKeyPressed(kev);
            }
        });

        return mainGameScene;

    }
    public javafx.scene.Scene gameMenu(Stage stage) {
        Canvas canvas = new Canvas(150,150);
        canvas.getGraphicsContext2D();
        VBox vBox = new VBox(canvas);
        Button playGame = new Button("SPELA!");
        playGame.setOnAction(e -> helpFuncToPlayListener(stage));
        Button mode = new Button("Svårighetsgrad");
        mode.setOnAction(e -> stage.setScene(mode(stage)));
        Button help = new Button("Hur spelar jag?");
        help.setOnAction(e -> stage.setScene(howToPlay(stage)));
        vBox.getChildren().addAll(playGame,mode,help);
        javafx.scene.Scene gameMenuScene = new javafx.scene.Scene(vBox);

        return gameMenuScene;
    }

    public javafx.scene.Scene mode(Stage stage) {
        Canvas canvas1 = new Canvas(150,150);
        canvas1.getGraphicsContext2D();
        VBox vBox = new VBox(canvas1);
        Button singleplayer = new Button("En person");
        singleplayer.setOnAction(e -> easy = true);
        Button multiplayer = new Button("Flera spelare");
        multiplayer.setOnAction(e -> stage.setScene(howToPlay(stage, "multi")));
        Button interactive = new Button("Rita själv");
        interactive.setOnAction(e -> stage.setScene(howToPlay(stage, "inter")));
        Button previous = new Button("Gå tillbaka");
        previous.setOnAction(e -> stage.setScene(gameMenu(stage)));
        vBox.getChildren().addAll(singleplayer,multiplayer,interactive,previous);
        javafx.scene.Scene howToPlayScene = new javafx.scene.Scene(vBox);

        return howToPlayScene;

    }
    public javafx.scene.Scene howToPlay(Stage stage) {
        Canvas canvas1 = new Canvas(150,150);
        canvas1.getGraphicsContext2D();
        HBox hBox = new HBox(canvas1);
        Button singleplayer = new Button("En person");
        singleplayer.setOnAction(e -> stage.setScene(howToPlay(stage, "en")));
        Button multiplayer = new Button("Flera spelare");
        multiplayer.setOnAction(e -> stage.setScene(howToPlay(stage, "multi")));
        Button interactive = new Button("Rita själv");
        interactive.setOnAction(e -> stage.setScene(howToPlay(stage, "inter")));
        Button previous = new Button("Gå tillbaka");
        previous.setOnAction(e -> stage.setScene(gameMenu(stage)));
        hBox.getChildren().addAll(singleplayer,multiplayer,interactive,previous);
        javafx.scene.Scene howToPlayScene = new javafx.scene.Scene(hBox);

        return howToPlayScene;
    }
    public javafx.scene.Scene howToPlay(Stage stage, String helpPlayers) {
        javafx.scene.Scene helpPlay = null;
        if(helpPlayers.equalsIgnoreCase("en")) {
            Canvas canvas = new Canvas(150,150);
            canvas.getGraphicsContext2D();
            HBox hBox = new HBox(canvas);
            Label label = new Label(howToPlaySingleplayer+howToPlayEnd);

            Button previous = new Button("Gå tillbaka");
            previous.setOnAction(e -> stage.setScene(howToPlay(stage)));
            hBox.getChildren().addAll(label, previous);
            helpPlay = new javafx.scene.Scene(hBox);
        } else if(helpPlayers.equalsIgnoreCase("multi")) {
            Canvas canvas = new Canvas();
            canvas.getGraphicsContext2D();
            HBox hBox = new HBox(canvas);
            Label label = new Label(howToPlayMultiplayer+howToPlayEnd);
            Button previous = new Button("Gå tillbaka");
            previous.setOnAction(e -> stage.setScene(howToPlay(stage)));
            hBox.getChildren().addAll(label, previous);
            helpPlay = new javafx.scene.Scene(hBox);
        } else if(helpPlayers.equalsIgnoreCase("inter")) {
            Canvas canvas = new Canvas();
            canvas.getGraphicsContext2D();
            HBox hBox = new HBox(canvas);
            Label label = new Label(howToPlayInteractive);
            Button previous = new Button("Gå tillbaka");
            previous.setOnAction(e -> stage.setScene(howToPlay(stage)));
            hBox.getChildren().addAll(label, previous);
            helpPlay = new javafx.scene.Scene(hBox);
        }
        return helpPlay;
    }
    public String createDashedWord(String word) {
        final String dash = "- ";
        int count = word.length();
        return dash.repeat(count);
    }



    public void helpFuncToPlayListener(Stage stage) {
        stage.setScene(mainGame());
        stage.setMaximized(true);
    }
    //Skapa 3 olika modes såklart
}