package com.example.project;
import java.util.Scanner;

//Game class 
public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    //Call constructor to initialize the game, setting up the grid, player, enemies, treasures, and trophies
    //Play lets users interact with the game by clicking w,a,s,d,q and clears screen after every update
    public Game(int size){ 
        this.size = size;
        initialize();
        play();
    }

    //Clears screen
    public static void clearScreen() { 
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                //Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                //Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Detects user keyboard clicks and changes the screen 
    public void play(){ 
        Scanner scanner = new Scanner(System.in); //Initializes scanner to detect keyboard clicks

        while(true){
            try {
                Thread.sleep(100); //Wait for 1/10 seconds before updating screen 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); //Clear the screen at the beggining of the while loop
            grid.display(); //Redisplays the screen
            boolean touchedTrophy = false; //Detects if trophy is touched
            System.out.println(player.getCoords()); //Prints coordinates
            System.out.println(player.getRowCol(size)); //Prints coordinates for 2D array
            System.out.println("Treasure Collected: " + player.getTreasureCount()); //Prints treasure count
            System.out.println("Lives remaining: " + player.getLives()); //Prints lives remaining
            System.out.print("Enter w,a,s,d to move and q to exit: "); //W -> up, a -> left, s -> down, d -> right, q -> quit
            String direction = scanner.nextLine(); //Waiting for player to type and enter their answer 
            if(player.isValid(size, direction)){ //If direction is valid (i.e. player not going out of boundary or writing anything other than w,a,s,d), player will move direction and any interactions with the Sprite present at the new location will be updated to see if player got a treasure or lost a life to an enemy 
                player.move(direction);
                Sprite s = grid.getSprite(player.getRow(size), player.getX());
                if(s instanceof Trophy){
                    touchedTrophy = true;
                }
                player.interact(size, direction, 2, s); 
                grid.placeSprite(player, direction); 
            } 
            if(direction.equals("q")){ //If direction is q, while loop is exited out of 
                System.out.println("Play again next time!");
                break;
            }
            if((player.getLives() == 0) || (player.getTreasureCount() < 2 && touchedTrophy)){ //Player loses if they lose all their lives or touched trophy before getting all treasures
                grid.gameover();
                break;
            }
            if(player.getWin()){ //If win variable is true from player object, player wins game
                grid.win();
                break;
            }
        }
    }

    public void initialize(){
        //Creates grid, one player, two enemies, two treasures, and one trophy
        grid = new Grid(size); 
        player = new Player(0, 0); //Player starts bottom left 
        enemies = new Enemy[]{new Enemy(3, 7), new Enemy(7, 8)};
        treasures = new Treasure[]{new Treasure(4, 2), new Treasure(1, 9)};
        trophy = new Trophy(9, 9); //Trophy starts top right 

        //Places all initialized sprites on the grid 
        grid.placeSprite(player);
        grid.placeSprite(enemies[0]);
        grid.placeSprite(enemies[1]);
        grid.placeSprite(treasures[0]);
        grid.placeSprite(treasures[1]);
        grid.placeSprite(trophy);
    }

    public static void main(String[] args) {
        //Runs the game by calling constructor 
        Game game = new Game(10);
    }
}