package org.OOP;

import java.util.Scanner;

public class Input {
    private Scanner scan;
    private Grid grid;
    private TilePosition TilePosition;

    //starts game with scanner
    //creates 8 x 8 grid with 10 randomly placed bombs on it
    public Input() {
        scan = new Scanner(System.in);
        grid = new Grid(8, 8);
        grid.placeMines(10);
        //grid.showAll();
    }

    //loops the game, printing grid and input
    public void playing() {
        boolean isFlag;
        TilePosition inputP;

        do {
            //if tileposition is flagged after x, y input, tileposition will be flagged
            //otherwise position will be used to reveal the target tile
            grid.printGrid();
            inputP = getInputP();
            isFlag = getStringOrQuit().equalsIgnoreCase("flag");
            if (isFlag) {
                grid.flagTile(inputP);
            } else if (grid.isTileFlag(inputP)) {
                System.out.println("You have already toggled a flag in this position. Un-flag to continue. ");
            } else {
                grid.revealTile(inputP);
            }

            // game is won if all non-mines revealed, lost if mine revealed
        } while (!grid.isWon() && (isFlag || !grid.isTileMined(inputP)));
        grid.showAll();
        grid.printGrid();
        if (grid.isWon()) {
            System.out.println("\uD83C\uDFC6 You win! \uD83C\uDFC6");
        } else {
            System.out.println("☠ R.I.P, Get good scrub  ☠");
        }
    }

    //try catch for anything else
    public boolean isInputValid(TilePosition TilePosition) {
        try {
            if (!grid.validP(TilePosition)) {
                System.out.println("Invalid Input");
                return false;
            }


        } catch (Exception e) {
            System.out.println("Invalid input, stay within grid X,Y range ");
        }
        //if tile already revealed, notify user
        if (grid.isTileRevealed(TilePosition)) {
            System.out.println("Scroll up, you have already revealed this... ");
            return false;
        }
        return true;
    }

    //loops until valid pair of integers inputted.
    //anything outside the range is invalid, values stored in .x and ,y
    public TilePosition getInputP() {
        TilePosition input = new TilePosition(0, 0);

        do {
            System.out.println("Enter X and Y co-ordinates and flag(optional) each separated by a space: ");
            if (!scan.hasNextInt()) {
                getStringOrQuit();
                System.out.println("Invalid X coordinate.");
                continue;
            }
            input.x = scan.nextInt();
            if (!scan.hasNextInt()) {
                getStringOrQuit();
                System.out.println("Invalid Y coordinate.");
                continue;
            }
            input.y = scan.nextInt();

        } while (!isInputValid(input));

        return input;
    }

    //checks if user inputs 'quit' and quits game
    //otherwise returns input
    public String getStringOrQuit() {
        String input = scan.nextLine().trim();
        if (input.equalsIgnoreCase("quit")) {
            System.out.println("Why you leave? Given up? ");
            System.exit(0);
        }
        return input;
    }




    /*Size - dependent on difficulty,
     Beginner 8 x 8 - with 10 mines
     Intermediate 16 x 16 with 40 mines
     Expert 30 x 16 with 99 mines
     */


    //how many mines
    //state of the board as is
    //number of mines not revealed yet
    //revealed mines, show number int in place

    //maybe have score in separate class & difficulty + scoreboard

}

