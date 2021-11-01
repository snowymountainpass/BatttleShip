package com.codecool;

import com.codecool.controllers.Battleship;
import com.codecool.views.Display;
import com.codecool.views.Input;

public class App {
    public static void main(String[] args) {
        Display display = new Display();
        Input input = new Input();

        Battleship battleship = new Battleship(display,input);
        battleship.run();
    }

}
