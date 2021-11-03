package com.codecool.battleship;

import com.codecool.battleship.models.Board;
import com.codecool.battleship.models.Ship;
import com.codecool.battleship.models.Square;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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

    private Parent createContent() {
        System.out.println("parent createContent running is: " + running );
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);

        root.setRight(new Text("RIGHT SIDEBAR - CONTROLS"));

        enemyBoard = new Board(true, event -> {
            System.out.println("enemy board running is: " + running );
            if (!running){
                System.out.println("Switching to player turn");
                return;}

            Square cell = (Square) event.getSource();
            if (cell.wasShot)
            { System.out.println("cell.wasShot is true");
                return;}

            enemyTurn = !cell.shoot();
            System.out.println("enemyTurn status : " + enemyTurn);

            if (enemyBoard.ships == 0) {
                System.out.println("YOU WIN");
                System.exit(0);
            }

            if (enemyTurn)
            { enemyMove();
                System.out.println("PC shooting");}
        });

        playerBoard = new Board(false, event -> {
            System.out.println("player board running is: " + running );
            if (running) {
                System.out.println("Switching to PC turn");
                return;}

            Square cell = (Square) event.getSource();
            if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                System.out.println("Player shooting");
                if (--shipsToPlace == 0) {
                    startGame();
                    System.out.println("Started game");
                }
            }
        });

        VBox vbox = new VBox(50, enemyBoard, playerBoard);
        System.out.println("Went out of VBOX");
        vbox.setAlignment(Pos.CENTER);

        root.setCenter(vbox);

        return root;
    }

    private void enemyMove() {
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Square cell = playerBoard.getSquare(x, y);
            if (cell.wasShot)
                continue;

            enemyTurn = cell.shoot();

            if (playerBoard.ships == 0) {
                System.out.println("YOU LOSE");
                System.exit(0);
            }
        }
    }

    private void startGame() {
        // place enemy ships
        int type = 5;

        while (type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (enemyBoard.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {
                type--;
            }
        }

        running = true;
    }

    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle("Battleship WIP");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }






    public static void main(String[] args) {
        launch(args);
    }
}