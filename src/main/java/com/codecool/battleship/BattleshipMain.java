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



    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(root);
//            Scene scene = new Scene(game.createContent(true));
            stage.setScene(scene);
            stage.setTitle("Battleship WIP");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Scene scene = new Scene(game.createContent(true));
//        stage.setTitle("Battleship WIP");
//        stage.setScene(scene);
//        stage.setResizable(true);
//        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



}