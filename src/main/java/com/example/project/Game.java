package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size){ //the constructor should call initialize() and play()
        this.size = size;
        initialize();
        play();
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){ //write your game logic here
        Scanner scanner = new Scanner(System.in);

        while(true){
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop
            grid.display();
            boolean touchedTrophy = false;
            System.out.println(player.getCoords());
            System.out.println(player.getRowCol(size));
            System.out.println("Treasure Collected: " + player.getTreasureCount());
            System.out.println("Lives remaining: " + player.getLives());
            System.out.print("Enter w,a,s,d to move and q to exit: ");
            String direction = scanner.nextLine();
            if(player.isValid(size, direction)){
                player.move(direction);
                Sprite s = grid.getSprite(player.getRow(size), player.getX());
                if(s instanceof Trophy){
                    touchedTrophy = true;
                }
                player.interact(size, direction, 2, s);
                grid.placeSprite(player, direction);
            }
            if((player.getLives() == 0) || (player.getTreasureCount() < 2 && touchedTrophy)){
                grid.gameover();
                break;
            }
            if(player.getWin()){
                grid.win();
                break;
            }
        }
    }

    public void initialize(){
        grid = new Grid(size); 
        player = new Player(0, 0);
        enemies = new Enemy[]{new Enemy(5, 5), new Enemy(7, 8)};
        treasures = new Treasure[]{new Treasure(2, 2), new Treasure(1, 7)};
        trophy = new Trophy(9, 9);

        grid.placeSprite(player);
        grid.placeSprite(enemies[0]);
        grid.placeSprite(enemies[1]);
        grid.placeSprite(treasures[0]);
        grid.placeSprite(treasures[1]);
        grid.placeSprite(trophy);
    }

    public static void main(String[] args) {
        Game game = new Game(10);
    }
}