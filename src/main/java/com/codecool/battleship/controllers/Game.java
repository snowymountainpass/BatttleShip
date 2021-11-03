package com.codecool.battleship.controllers;

import com.codecool.battleship.SceneController;
import com.codecool.battleship.models.Board;
import com.codecool.battleship.models.Ship;
import com.codecool.battleship.models.Square;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class Game {


    private boolean running = false;
    private Board AIBoard, playerBoard, player2Board;

    private int shipsToPlace = 5;

    private boolean AITurn = false;

    private Random random = new Random();


    public Parent createContent(boolean vsAi) throws IOException {
        Parent defaultRoot = new BorderPane();
        if (vsAi) {
            BorderPane root = new BorderPane();
            root.setPrefSize(600, 800);

            root.setRight(new Text("RIGHT SIDEBAR - CONTROLS"));


            AIBoard = new Board(true, event -> {
                if (!running)
                    return;

                Square cell = (Square) event.getSource();
                if (cell.wasShot)
                    return;

                AITurn = !cell.shoot();

                if (AIBoard.ships == 0) {
                    System.out.println("YOU WIN");
//                    System.exit(0);
                }

                if (AITurn) {
                    try {
                        enemyMove();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            playerBoard = new Board(false, event -> {
                if (running)
                    return;

                Square cell = (Square) event.getSource();
                if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                    if (--shipsToPlace == 0) {
                        startGame();
                    }
                }
            });

            VBox vbox = new VBox(50, AIBoard, playerBoard);
            vbox.setAlignment(Pos.CENTER);

            root.setCenter(vbox);

            return root;
        }
        else {
            // player vs player mode to be implemented
        }
        return defaultRoot;
    }

    private void startGame() {
        // place enemy ships
        int nrOfShips = 5;

        while (nrOfShips > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (AIBoard.placeShip(new Ship(nrOfShips, Math.random() < 0.5), x, y)) {
                nrOfShips--;
            }
        }

        running = true;
    }


    private void enemyMove() throws IOException {


        while (AITurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Square cell = playerBoard.getSquare(x, y);
            if (cell.wasShot)
                continue;

            AITurn = cell.shoot();

            if (playerBoard.ships == 0) {

                System.out.println("YOU LOSE");
//                System.exit(0);


            }
        }
    }


}
