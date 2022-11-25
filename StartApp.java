package com.example.projetojogogalo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StartApp extends Application {
    @Override
    public void start(Stage stage) {
        Game game = new Game();
        Scene scene = new Scene(game);
        scene.setFill(Color.BLUE);
        stage.setScene(scene);
        stage.setTitle("TicTacToe");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
