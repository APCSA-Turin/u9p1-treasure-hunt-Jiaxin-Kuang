package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        grid = new Sprite[size][size];
        for(int i = 0; i < size; i ++){
            for(int j = 0; j < size; j ++){
                grid[i][j] = new Dot(i, j);
            }
        }
        this.size = size;
    }
 
    public Sprite[][] getGrid(){
        return grid;
    }

    public void placeSprite(Sprite s){ //place sprite in new spot 
        grid[s.getRow(size)][s.getX()] = s;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        int reverseX = 0;
        int reverseY = 0;
        if(direction.equals("w")){
            reverseY ++;
        }
        else if(direction.equals("a")){
            reverseX ++;
        }
        else if(direction.equals("d")){
            reverseX --;
        }
        else{
            reverseY --;
        }
        placeSprite(s);
        System.out.println(s.getRow(size) + reverseY);
        System.out.println(s.getX() + reverseX);
        grid[s.getRow(size) + reverseY][s.getX() + reverseX] = new Dot(s.getRow(size) + reverseY, s.getX() + reverseX);
    }

    public void display() { //print out the current grid to the screen 
        for(Sprite[] row : grid){
            for(Sprite s : row){
                if(s instanceof Dot){
                    System.out.print("⬜");
                }
                else if(s instanceof Player){
                    System.out.print("🐱");
                }
                else if(s instanceof Enemy){
                    System.out.print("🐶");
                }
                else if(s instanceof Trophy){
                    System.out.print("🐭");
                }
                else{
                    System.out.print("🐟");
                }
            }
            System.out.println();
        }
    }
    
    public void gameover(){ //use this method to display a loss
        System.out.println("Loss");
    }

    public void win(){ //use this method to display a win
        System.out.println("Win"); 
    }
}