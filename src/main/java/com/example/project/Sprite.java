package com.example.project;

public class Sprite {
    //x and y represent a Sprite's location (X = column of grid, Y = row of grid)
    private int x, y;

    //Constructor initializes x and y variables 
    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Getter method for x
    public int getX(){
        return x;
    }

    //Getter method for y
    public int getY(){
        return y;
    }

    //Setter method for x 
    public void setX(int newX){
        x = newX;
    }

    //Setter method for y
    public void setY(int newY){
        y = newY;
    }

    //returns "(x,y)" (X and y match the x and y variables)
    public String getCoords(){ 
        return "(" + x + "," + y + ")";
    }

    //return "[row][col]" (Row and col match the equivalent location of the x and y on a 2D array)"
    public String getRowCol(int size){ 
        return "[" + (size - y - 1) + "]" + "[" + x + "]";
    }

    //Changes y into row for grid class to modify display 
    public int getRow(int size){
        return size - y - 1;
    }

    //Empty because move only applies to Player and Enemy and they act differently from each other 
    public void move(String direction){ 
    }

    //Empty because interact only applies to Player
    public void interact(){ 
    }
}

