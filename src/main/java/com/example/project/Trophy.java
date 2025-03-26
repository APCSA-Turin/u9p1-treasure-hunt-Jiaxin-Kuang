package com.example.project;

//Trophy is a subclass of Trophy
//Inherits no unique methods from Treasure but is here for general classification that trophy falls under treasure category 
public class Trophy extends Treasure{ 
    //Constructor initializes a trophy with x and y variables for their location
    public Trophy(int x, int y){
        super(x,y);
    }
}
