package com.codecool.battleship.controllers;

import com.codecool.battleship.BattleshipMain;
import com.codecool.battleship.models.Board;
import com.codecool.battleship.models.Ship;
import com.codecool.battleship.models.Square;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Random;

public class Game {


    private boolean running = false;
    private Board AIBoard, playerBoard, player2Board;

    private int shipsToPlace = 5;

    private boolean AITurn = false;

    private Random random = new Random();

    private VBox rows = new VBox();
    public Button winButton;


    public Parent createContent(boolean vsAi) throws IOException {
        Parent defaultRoot = new BorderPane();
        if (vsAi) {
            BorderPane root = new BorderPane();
            root.setPrefSize(600, 800);

            root.setRight(new Text("RIGHT SIDEBAR - CONTROLS"));

            HBox buttonBox = new HBox();



            AIBoard = new Board(true, event -> {
                Button menuButton = new Button("Back to Menu");
                menuButton.setAlignment(Pos.CENTER);
                menuButton.setPrefSize(100,100);
                menuButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Menu Button pressed");

                    }
                });

                if (!running)
                    return;

                Square cell = (Square) event.getSource();
                if (cell.wasShot)
                    return;

                AITurn = !cell.shoot();

                if (playerBoard.ships == 0){
                    System.out.println("You lost !");
                    root.setCenter(new Text("You lost ... "));
                    root.setRight(new Text(""));
                    root.setBottom(buttonBox);
                    buttonBox.getChildren().add(menuButton);
                    buttonBox.setAlignment(Pos.CENTER);

                }

                if (AIBoard.ships == 0) {
                    System.out.println("YOU WIN");
                    root.setCenter(new Text("Congratulations, you won !"));
                    root.setRight(new Text(""));
                    root.setBottom(buttonBox);
                    buttonBox.getChildren().add(menuButton);
                    buttonBox.setAlignment(Pos.CENTER);
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
        } else {
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

            }
        }
    }


}
