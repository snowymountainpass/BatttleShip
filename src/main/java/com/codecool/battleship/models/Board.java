package com.codecool.battleship.models;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Board extends Parent {

    private VBox rows = new VBox();
    private boolean enemy = false;
    public int ships = 5;

    public Board(boolean enemy, EventHandler<? super MouseEvent> handler) {
        this.enemy = enemy;
        for (int y = 0; y < 10; y++) {
            HBox row = new HBox();
            for (int x = 0; x < 10; x++) {
                Square c = new Square(x, y, this);
                c.setOnMouseClicked(handler);
                row.getChildren().add(c);
            }

            rows.getChildren().add(row);
        }

        getChildren().add(rows);
    }

    public boolean placeShip(Ship ship, int x, int y) {
        if (canPlaceShip(ship, x, y)) {
            int length = ship.type;

            if (ship.vertical) {
                for (int i = y; i < y + length; i++) {
                    Square square = getSquare(x, i);
                    square.ship = ship;
                    if (!enemy) {
                        square.setFill(Color.WHITE);
                        square.setStroke(Color.GREEN);
                    }
                }
            } else {
                for (int i = x; i < x + length; i++) {
                    Square square = getSquare(i, y);
                    square.ship = ship;
                    if (!enemy) {
                        square.setFill(Color.WHITE);
                        square.setStroke(Color.GREEN);
                    }
                }
            }

            return true;
        }

        return false;
    }

        public Square getSquare ( int x, int y){
            return (Square) ((HBox) rows.getChildren().get(y)).getChildren().get(x);
        }

        private Square[] getNeighbors ( int x, int y){
            Point2D[] points = new Point2D[]{
                    new Point2D(x - 1, y),
                    new Point2D(x + 1, y),
                    new Point2D(x, y - 1),
                    new Point2D(x, y + 1)
            };

            List<Square> neighbors = new ArrayList<Square>();

            for (Point2D p : points) {
                if (isValidPoint(p)) {
                    neighbors.add(getSquare((int) p.getX(), (int) p.getY()));
                }
            }

            return neighbors.toArray(new Square[0]);
        }

        private boolean canPlaceShip (Ship ship,int x, int y){
            int length = ship.type;

            if (ship.vertical) {
                for (int i = y; i < y + length; i++) {
                    if (!isValidPoint(x, i))
                        return false;

                    Square square = getSquare(x, i);
                    if (square.ship != null)
                        return false;

                    for (Square neighbor : getNeighbors(x, i)) {
                        if (!isValidPoint(x, i))
                            return false;

                        if (neighbor.ship != null)
                            return false;
                    }
                }
            } else {
                for (int i = x; i < x + length; i++) {
                    if (!isValidPoint(i, y))
                        return false;

                    Square square = getSquare(i, y);
                    if (square.ship != null)
                        return false;

                    for (Square neighbor : getNeighbors(i, y)) {
                        if (!isValidPoint(i, y))
                            return false;

                        if (neighbor.ship != null)
                            return false;
                    }
                }
            }

            return true;
        }

        private boolean isValidPoint (Point2D point){
            return isValidPoint(point.getX(), point.getY());
        }

        private boolean isValidPoint ( double x, double y){
            return x >= 0 && x < 10 && y >= 0 && y < 10;
        }


        public boolean markStuff ( boolean eventActivated, int x, int y){
            Square square = getSquare(x, y);
            if (eventActivated)
                square.setFill(Color.RED);

            return true;
        }
    }
