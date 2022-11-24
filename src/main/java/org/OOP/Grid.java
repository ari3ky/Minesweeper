package org.OOP;

import java.util.Random;

public class Grid { //defines board and tracks state of tiles
    private final Tile[][] tiles; //2d array of tiles, could be mines or nearby mines
    private final int width;
    private final int height; // horizontal and vertical tiles
    private int mineCount, totalRevealed; //mines remaining on grid, revealed tiles

    public Grid(int width, int height) { //sets grid width and height
        this.height = height;
        this.width = width;
        tiles = new Tile[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = new Tile();
            }
        }
        mineCount = 0;
        totalRevealed = 0;
    }

    public void printGrid() {
        for (int y = 0; y < height; y++) {  //print tiles for grid
            for (int x = 0; x < width; x++) {
                System.out.print(tiles[x][y] + " |");
                // tiles[x][y] = new Tile(); ruined my code..
            }
            System.out.println(" |" + (y)); //print vertical row numbers after line

        }
        for (int x = 0; x < width; x++) { //driving me mad, fix later
            System.out.print("_  "); //line for better user visual alignment
        }

        System.out.println();// moves the x-axis numbers to next line
        //numbers each column on x-axis
        for (int x = -1; x < width - 1; x++) {
            System.out.print((x + 1) + "  "); // losing my shit with spacing
            if (x + 1 < 8) {
                System.out.print("");
            }
        }
        System.out.println();
    }


    public void revealTile(TilePosition tilePosition) {
        if (tiles[tilePosition.x][tilePosition.y].getIsAdj() != 0) {
            totalRevealed++;
            tiles[tilePosition.x][tilePosition.y].reveal(); //reveals specified cell
        } else {
            showAroundSpot(tilePosition);
        }

    }

    // gets if x,y tiles are flagged
    public boolean isTileFlag(TilePosition tilePosition) {
        return tiles[tilePosition.x][tilePosition.y].getIsFlag();
    }

    //flag tile toggle
    public void flagTile(TilePosition tilePosition) {
        tiles[tilePosition.x][tilePosition.y].toggleIsFlag();
    }


    //gets if tile at x,y is revealed
    public boolean isTileRevealed(TilePosition tilePosition) {
        return tiles[tilePosition.x][tilePosition.y].getRevealed();


    }

    //gets if tile at x, y is mined
    public boolean isTileMined(TilePosition tilePosition) {
        return tiles[tilePosition.x][tilePosition.y].getIsMine();
    }

    //addmine to x, y, if tile already mined, don't add mine.
    public void addMine(TilePosition tilePosition) {
        if (isTileMined(tilePosition)) {
            return;
        }
        // gets group of tiles +1/-1 around x,y
        int minX = Math.max(0, tilePosition.x - 1); //min value is larger than or == 0, where x is left of tilePosition
        int maxX = Math.min(width - 1, tilePosition.x + 1); //sets tileposition to the right of original position or to the max value
        int minY = Math.max(0, tilePosition.y - 1);// same as above but for Y
        int maxY = Math.min(height - 1, tilePosition.y + 1); //same as above but for Y

        //increase Adjacents for surrounding tiles
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                tiles[x][y].addIsAdj();

            }


        }
        tiles[tilePosition.x][tilePosition.y].setMine();
        mineCount++;
    }


    //place max number of mines (specified in maxMines) randomly.
    public void placeMines(int maxMines) {
        Random rand = new Random();
        for (int i = 0; i < maxMines; i++) {
            addMine(new TilePosition(rand.nextInt(width), rand.nextInt(height)));

        }

    }

    // checks if x,y co-ordinates are within valid range
    public boolean validP(TilePosition tilePosition) {
        return tilePosition.x >= 0 && tilePosition.y >= 0 && tilePosition.x < height && tilePosition.y < width;
    }

    //uncovers all covered tiles either at end game or just for testing if I enable it
    //by removing comment before the call in Input
    public void showAll() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y].reveal();
            }
        }
    }

    public void showAroundSpot(TilePosition tilePosition) {
        //gets group of tiles +/-1 around x,y, same as above
        int minX = Math.max(0, tilePosition.x - 1);
        int maxX = Math.min(width - 1, tilePosition.x + 1);
        int minY = Math.max(0, tilePosition.y - 1);
        int maxY = Math.min(height - 1, tilePosition.y + 1);



        //iterate through surrounding tiles
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                //checks if x,y haven't been revealed
                if (!tiles[x][y].getRevealed()) { //prevents already revealed tiles from being revealed
                    tiles[x][y].reveal();
                    totalRevealed++;

                //reveals all adjacent connected tiles that aren't mines
                if(tiles[x][y].getIsAdj() ==0){
                    TilePosition tp = new TilePosition(x,y);
                    revealTile(tp);
                }

                }


            }
        }
    }

    //checks if tiles have been revealed, except for mines and in doing so returns true
    public boolean isWon() {
        return totalRevealed + mineCount == width * height;
    }


}