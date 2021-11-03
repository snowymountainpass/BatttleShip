package com.codecool.battleship;

import com.codecool.battleship.controllers.Game;
import com.codecool.battleship.models.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Random;


public class BattleshipMain extends Application {

    Game game = new Game();



    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(game.createContent());
        stage.setTitle("Battleship WIP");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



}