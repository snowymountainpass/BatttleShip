package com.codecool.battleship;

import com.codecool.battleship.models.Board;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import com.codecool.battleship.models.Board.Cell;

public class HelloApplication extends Application {
    private Board playerBoard;
    private boolean running = false;

    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);

        playerBoard = new Board(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            running = true;
        });

        VBox vbox = new VBox(50, playerBoard);
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
        stage.setResizable(false);
        stage.show();
    }






    public static void main(String[] args) {
        launch(args);
    }
}