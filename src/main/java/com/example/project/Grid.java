package com.example.project;

public class Grid{
    private Sprite[][] grid;
    private int size;

    //Constructor accepts size of grid and sets grid to have the same width and length as size 
    //All items in the grid will be set to dots 
    public Grid(int size) { 
        this.size = size;
        grid = new Sprite[size][size];
        for(int i = 0; i < size; i ++){
            for(int j = 0; j < size; j ++){
                grid[i][j] = new Dot(i, j);
            }
        }
    }
 
    //Getter method for grid 
    public Sprite[][] getGrid(){
        return grid;
    }

    //Gets sprite at a location 
    public Sprite getSprite(int y, int x){
        return grid[y][x];
    }

    //Places sprite at the location marked by its x and y values 
    public void placeSprite(Sprite s){ 
        grid[s.getRow(size)][s.getX()] = s;
    }

    //Place player sprite in a new spot based on direction and the old location of the sprite is changed to a dot 
    public void placeSprite(Sprite s, String direction){ 
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
        else if(direction.equals("s")){
            reverseY --;
        }
        placeSprite(s);
        grid[s.getRow(size) + reverseY][s.getX() + reverseX] = new Dot(s.getRow(size) + reverseY, s.getX() + reverseX);
    }

    //Displays the screen by looping through grid and checking which instance class each sprite is 
    // Empty space: ⬜
    // Player: 🐁
    // Enemy: 🐈
    // Trophy: 🏆
    // Treasure: 🧀
    public void display(){
        for(Sprite[] row : grid){
            for(Sprite s : row){
                if(s instanceof Dot){
                    System.out.print("⬜");
                }
                else if(s instanceof UnmovableDot){
                    System.out.print("⬛");
                }
                else if(s instanceof Player){
                    System.out.print("🐁");
                }
                else if(s instanceof Enemy){
                    System.out.print("🐈");
                }
                else if(s instanceof Trophy){
                    System.out.print("🏆");
                }
                else{
                    System.out.print("🧀");
                }
            }
            System.out.println();
        }
    }
    
    //Displays dialogue between cat and mouse after a loss
    public void gameover(){ 
        System.out.println("😽: Thank you for the delicious dinner!");
        System.out.println("🐭: 😵");
    }

    //Displays dialogue between cat and mouse after a win
    public void win(){ 
        System.out.println("🐭: Thank you helping me escape the cat!");
        System.out.println("😿: 😩🍴");
    }
}