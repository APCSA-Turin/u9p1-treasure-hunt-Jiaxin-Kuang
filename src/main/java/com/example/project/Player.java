package com.example.project;

//Player is a subclass of Sprite 
public class Player extends Sprite{
    private int treasureCount;
    private int numLives;
    private boolean win;

    //Constructor initializes a player with x and y variables for their location
    //Each player is initialized with 2 lives, 0 treasures, and win state currently false 
    public Player(int x, int y) { 
        super(x,y);
        numLives = 2;
        treasureCount  = 0; 
        win = false;
    }

    //Getter method for treasureCount 
    public int getTreasureCount(){
        return treasureCount;
    }

    //Getter method for numLives
    public int getLives(){
        return numLives;
    }

    //Getter method for win
    public boolean getWin(){
        return win;
    }

    //Overrides Sprite's getCoords() method 
    @Override
    //returns "Enemy:(x,y)" (X and y match the player's x and y variables)
    public String getCoords(){ 
        return "Player:" + super.getCoords();
    }

    //Overrides Sprite's getRowCol() method 
    @Override
    //return "Enemy:[row][col]" (Row and col match the player's equivalent location of their x and y on a 2D array)"
    public String getRowCol(int size){ 
        return "Player:" + super.getRowCol(size);
    }

    //Overrides Sprite's move method 
    @Override 
    //Changes the player's x and y cordinates based on keyboard clicks 
    public void move(String direction) { 
        if(direction.equals("w")){
            setY(getY() + 1);
        }
        else if(direction.equals("a")){
            setX(getX() - 1);
        }
        else if(direction.equals("s")){
            setY(getY() - 1);
        }
        else if(direction.equals("d")){
            setX(getX() + 1);
        }
        else{
            System.out.println("Not valid move option");
        }
    }

    //Decides if anything happens if a player interacts with the object at their location
    //Dot: nothing happens
    //Treasure: treasureCount increases
    //Enemy: numLives decreases
    //Trophy: If treasureCount is sufficient, win = true, but if not, player nothing changes and game class will make player lose 
    public void interact(int size, String direction, int numTreasures, Object obj){ 
        if(obj instanceof Enemy){
            numLives --;
        }
        else if(obj instanceof Trophy && treasureCount >= numTreasures){
            win = true;
        }
        else if(!(obj instanceof Trophy) && obj instanceof Treasure){
            treasureCount ++;
        }
    }

    //Checks the grid boundaries, making sure player isn't moving off the grid 
    public boolean isValid(int size, String direction){ 
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

    //Checks player didn't move to an unmovable dot and didn't move to a trophy when they haven't gotten all their treasures yet
    public boolean isValid2(int size, String direction, Grid grid){
        if(direction.equals("w") && !(grid.getSprite(getRow(size) - 1, getX()) instanceof UnmovableDot) && !(grid.getSprite(getRow(size) - 1, getX()) instanceof Trophy && getTreasureCount() < 2)){
            return true;
        }
        else if(direction.equals("a") && !(grid.getSprite(getRow(size), getX() - 1) instanceof UnmovableDot) && !(grid.getSprite(getRow(size), getX() - 1) instanceof Trophy && getTreasureCount() < 2)){
            return true;
        }
        else if(direction.equals("s") && !(grid.getSprite(getRow(size) + 1, getX()) instanceof UnmovableDot) && !(grid.getSprite(getRow(size) + 1, getX()) instanceof Trophy && getTreasureCount() < 2)){
            return true;
        }
        else if(direction.equals("d") && !(grid.getSprite(getRow(size), getX() + 1) instanceof UnmovableDot) && !(grid.getSprite(getRow(size), getX() + 1) instanceof Trophy && getTreasureCount() < 2)){
            return true;
        }
            return false;
    }
}



