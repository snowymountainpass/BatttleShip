package com.codecool.battleship;

import com.codecool.battleship.controllers.Game;
import com.codecool.battleship.models.Board;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Random;


public class BattleshipMain extends Application {

//    Game game = new Game(); // Game game = new Game (AIEnabled=true);

    public static Stage primaryStage;


    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Battleship");
            stage.show();

            primaryStage = stage;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }



}