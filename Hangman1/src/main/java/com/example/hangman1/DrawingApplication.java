package com.example.hangman1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
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
        saveButton.setTranslateX(200);
        saveButton.setTranslateY(200);


        saveButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(primaryStage);
            if (file != null) {
                try {
                    WritableImage image = new WritableImage(480, 480);
                    canvas.snapshot(null, image);
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });




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
