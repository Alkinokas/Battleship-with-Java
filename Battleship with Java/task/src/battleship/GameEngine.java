package battleship;
import java.util.Scanner;

public class GameEngine {
//
    private int shipSunk = 0;
    private final Battlefield battlefield;
//    private final Player player = new Player(battlefield, this);

    public GameEngine (Battlefield battlefield){
        this.battlefield = battlefield;
    }

    public Battlefield getBattlefield() {
        return battlefield;
    }

    public boolean isSunk(int row, int column, char[][] grid) {
//        char[][] grid = battlefield.getGrid();

        // Checks boundaries
        if (row < 0 || row >= grid.length || column < 0 || column >= grid[0].length) {
            return true;
        }

        // Checks if some ship cells are unhit
        if (grid[row][column] == 'O') {
            return false;
        }

        // If it's not a ship element
        if (grid[row][column] != 'X'){
            return true;
        }

        // Temporarily marks the cells as visited
            grid[row][column] = 'V';


            // Recursively check the four ajdacent cells (up, down, left, right)
            boolean up = isSunk(row - 1, column, grid);
            boolean down = isSunk(row + 1, column, grid);
            boolean left = isSunk(row, column - 1, grid);
            boolean right = isSunk(row, column + 1, grid);

            // Restores the cell back to 'X'

            grid[row][column] = 'X';

            // The ship is sunk only if all connected cells return true
            return up && down && left && right;
        }

        public int getShipSunk() {
            return shipSunk;
        }

        public void incrementShipSunk() {
        shipSunk++;
    }
}
