package com.codecool.models;
import com.codecool.utils.SquareStatus;

public class Square {
    private int xCoordinate;
    private int yCoordinate;
    private SquareStatus squareStatus;

    public Square (int xCoordinate, int yCoordinate, SquareStatus squareStatus){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.squareStatus = squareStatus.EMPTY;
    }

    public SquareStatus SquareStatus(){ return this.squareStatus; }

    public String getCharacter(SquareStatus squareStatus){
        switch (squareStatus){
            case EMPTY:
                System.out.println("Empty");
                return "Empty";
            case MISSED:
                System.out.println("MISSED");
                return "MISSED";
            case HIT:
                System.out.println("HIT");
                return "HIT";
            case OCEAN:
                System.out.println("OCEAN");
                return "OCEAN";
            case SHIP:
                System.out.println("SHIP");
                return "SHIP";

            default:
                return "";
        }
    }

    public int getxCoordinate() {
        return xCoordinate;
    }
    public int getyCoordinate(){
        return yCoordinate;
    }
}
