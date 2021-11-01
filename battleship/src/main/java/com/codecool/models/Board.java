package com.codecool.models;

import java.util.ArrayList;

public class Board {

    ArrayList<Square> ocean;

    public Board() {
        this.ocean = new ArrayList<Square>();
    }

    private boolean isPlacementOk(Ship ship, int x, int y){
        // check if square are EMPTY
        return true;
    }

}
