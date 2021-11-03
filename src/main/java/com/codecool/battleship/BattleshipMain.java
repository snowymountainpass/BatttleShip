package com.codecool.battleship;

import com.codecool.battleship.controllers.Game;
import com.codecool.battleship.models.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Random;


public class BattleshipMain extends Application {
    private Board enemyBoard, playerBoard;
    private boolean running = false;
    private boolean turn1 = false;
    private boolean turn2 = false;

    private int shipsToPlace = 5;

    private boolean enemyTurn = false;

    private Random random = new Random();

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