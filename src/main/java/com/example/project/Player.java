package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite{
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x,y);
        numLives = 2;
        treasureCount  = 0; 
        win = false;
    }

    public int getTreasureCount(){
        return treasureCount;
    }
    public int getLives(){
        return numLives;
    }

    public boolean getWin(){
        return win;
    }

    @Override
    public String getCoords(){ //returns "Enemy:"+coordinates
        return "Player:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size){ //return "Enemy:"+row col
        return "Player:" + super.getRowCol(size);
    }

    //move method should override parent class, sprite
    @Override 
    public void move(String direction) { //move the (x,y) coordinates of the player
        if(direction.equals("w")){
            super.setY(super.getY() + 1);
        }
        else if(direction.equals("a")){
            super.setX(super.getX() - 1);
        }
        else if(direction.equals("s")){
            super.setY(super.getY() - 1);
        }
        else if(direction.equals("d")){
            super.setX(super.getX() + 1);
        }
        else{
            System.out.println("Not valid move option");
        }
    }

    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
    //numTreasures is the total treasures at the beginning of the game
        if(obj instanceof Enemy){
            numLives --;
        }
        else if(!(obj instanceof Trophy) && obj instanceof Treasure){
            treasureCount ++;
        }
        else if(obj instanceof Trophy && treasureCount >= numTreasures){
            win = true;
        }
    }

    public boolean isValid(int size, String direction){ //check grid boundaries
        if(direction.equals("w") && getY() + 1 < size){
            return true;
        }
        else if(direction.equals("a") && getX() - 1 > -1){
            return true;
        }
        else if(direction.equals("s") && getY() - 1 > -1){
            return true;
        }
        else if(direction.equals("d") && getX() + 1 < size){
            return true;
        }
        return false;
    }
}



