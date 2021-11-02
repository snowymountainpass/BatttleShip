package com.codecool.battleship;

import com.codecool.battleship.models.Board;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import com.codecool.battleship.models.Board.Cell;

public class BattleshipMain extends Application {
    private Board player1Board, player2Board;
    private boolean running = false;
    private boolean turn1 = false;
    private boolean turn2 = false;

    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);

        player1Board = new Board(false, event -> {
//            if (!running)
//                return;

            Cell cell = (Cell) event.getSource();


//            if (cell.wasShot)
//                return;

            if (player1Board.markStuff((event.getButton() == MouseButton.PRIMARY),cell.x, cell.y))
                System.out.println("Player1 board clicked");
                System.out.println("Player1 board x,y" + cell.x +" "+ cell.y);



//            running = true;
        });

        player2Board = new Board(false, event -> {
//            if (running)
//                return;

            Cell cell = (Cell) event.getSource();
            if (player2Board.markStuff((event.getButton() == MouseButton.PRIMARY),cell.x, cell.y))
                System.out.println("Player2 board clicked");

//            if(cell.wasShot)
//                return;

//            running = true;
        });

        VBox vbox = new VBox(50, player1Board, player2Board);
        vbox.setAlignment(Pos.CENTER);

        root.setCenter(vbox);

        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
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