package org.OOP;

public class Tile {
    //if tile should be revealed
    private boolean revealed;

    //if tile is mine, avoid when revealing
    private boolean isMine;

    //number of nearby mines +/-1 of tile
    private int isAdj;

    private boolean isFlag;


    public Tile() {
        defTile(); //default tile

    }

    public void defTile() {
        revealed = false;
        isMine = false;
        isAdj = 0;
        isFlag = false;

    }




    public boolean getIsMine() {
        return isMine;
    }

    public void setMine() {
         isMine = true;
    }

    public void addIsAdj() {
        isAdj++; //Adjacent tiles + 1
    }


    public int getIsAdj() {
        return isAdj; //returns value of adjacent tiles (+1)
    }
    public void reveal() {
        revealed = true;
    }

    public boolean getIsFlag(){
        return isFlag;
    }

    //toggles flag between true/false
    public void toggleIsFlag() {
        isFlag = !isFlag;
    }

    public boolean getRevealed() {
        return revealed;
    }
    //changes state of tile when revealed or is mine/flag toggled
    public String toString() {
        if(revealed) {
            if (isMine) {       //adds the mine
                return "*";
            } else {
                return "" + isAdj;
            }
            } else if(isFlag) {
                return "F";

        } else {
            return "?";
        }
        //return "\uD83D\uDCA3"; the mine
    }
}
