package com.codecool.battleship;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

import com.codecool.battleship.controllers.Game;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void onHelloButtonClick(javafx.event.ActionEvent actionEvent) {
        System.out.println("Old method");
    }

    public void switchToGamePlayervsAI(javafx.event.ActionEvent actionEvent) throws IOException {
        Game game = new Game();
        root = FXMLLoader.load(getClass().getResource("plaver-vs-ai.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(game.createContent(true));
//        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToMenu(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void gameLostScreen(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("game-lost.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gameWonScreen(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("game-won.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
