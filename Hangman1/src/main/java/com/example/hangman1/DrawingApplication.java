package com.example.hangman1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class DrawingApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Canvas canvas = new Canvas(480, 480);
        GraphicsContext gc = canvas.getGraphicsContext2D();


        gc.setFill(Color.BLACK);


        Button saveButton = new Button("Save");
        saveButton.setLayoutX(240);
        saveButton.setLayoutY(240);


        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            double x = e.getX();
            double y = e.getY();
            gc.fillOval(x, y, 10, 10);


        });


        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        root.getChildren().add(saveButton);


        primaryStage.setTitle("Drawing Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
