package com.example.project;

//Enemy is a subclass of Sprite 
public class Enemy extends Sprite{ 
    //Constructor initializes a dot with x and y variables for their location
    public Enemy(int x, int y) {
        super(x,y);
    }

    //Overrides Sprite's getCoords() method 
    @Override
    //returns "Enemy:(x,y)" (X and y match the enemy's x and y variables)
    public String getCoords(){ 
        return "Enemy:" + super.getCoords();
    }

    //Overrides Sprite's getRowCol() method 
    @Override
    //return "Enemy:[row][col]" (Row and col match the enemy's equivalent location of their x and y on a 2D array)"
    public String getRowCol(int size){ 
        return "Enemy:" + super.getRowCol(size);
    }  
}