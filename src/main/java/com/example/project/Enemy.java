package com.example.project;

//Enemy is a subclass of Sprite 
public class Enemy extends Sprite{ 
    private boolean isAlive = true;
    //Constructor initializes a dot with x and y variables for their location
    public Enemy(int x, int y) {
        super(x,y);
    }

    public void setDead(){
        isAlive = false;
    }

    public boolean isAlive(){
        return isAlive;
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

    //Loops through each of the four directions gotten from calculateAllDirections() that arranged the directions from worst to best and then uses canMove() to see which of the moves starting from the best move is a valid move 
    //First valid move becomes where enemy moves for the round
    public String move(String playerDirection, Player player, Grid grid, int size) {
        if (!isAlive) {
            return "";
        }
        String[] directionRank = calculateAllDirections(player, size);
        for (String direction : directionRank){
            if (canMove(direction, grid, size)){
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
                return direction;
            }
        }
        return ""; 
    }
    
    //Arranges four directions from best to worst for enemy to move based on their vertical and horizontal relationship to player
    private String[] calculateAllDirections(Player player, int size){
        int verticalDistance = player.getRow(size) - getRow(size);
        int horizontalDistance = player.getX() - getX();
        boolean verticalPriority = Math.abs(verticalDistance) > Math.abs(horizontalDistance);
        
        if(verticalPriority){
            if(verticalDistance > 0){
                return new String[]{"s", "d", "a", "w"}; 
            } 
            else{
                return new String[]{"w", "d", "a", "s"}; 
            }
        } 
        else{
            if (horizontalDistance > 0){
                return new String[]{"d", "s", "w", "a"}; 
            } else {
                return new String[]{"a", "s", "w", "d"}; 
            }
        }
    }

    //Sees if enemy can move in direction by detecting whether the direction has an unmovable dot, treasure, trophy, or another enemy
    private boolean canMove(String direction, Grid grid, int size){
        int newRow = getRow(size);
        int newCol = getX();
        
        if(direction.equals("w")){
            newRow --;
        }
        else if(direction.equals("a")){
            newCol --;
        }
        else if(direction.equals("s")){
            newRow ++;
        }
        else if(direction.equals("d")){
            newCol ++;
        }
        else if(direction.equals("")){
            return false;
        }

        return newRow >= 0 && newRow < size && newCol >= 0 && newCol < size && !(grid.getSprite(newRow, newCol) instanceof UnmovableDot) && !(grid.getSprite(newRow, newCol) instanceof Treasure) && !(grid.getSprite(newRow, newCol) instanceof Enemy);
    }
}