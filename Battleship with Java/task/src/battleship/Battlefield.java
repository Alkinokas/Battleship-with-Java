package battleship;

import java.util.Arrays;

public class Battlefield {

    private final int SIZE = 10;
    private char[][] grid;
    private char[][] hiddenBattleField;

    public Battlefield() {
        this.grid = createGrid();
        this.hiddenBattleField = createGrid();
    }

    public char[][] getGrid(){
        return grid;
    }

    public char[][] getHiddenBattleField(){
        return hiddenBattleField;
    }

    public char[][] createGrid() {
        char[][] matrix = new char[SIZE][SIZE];

        // Fills matrix's all elements with desired char
        for (char[] row : matrix) {
            Arrays.fill(row, '~');
        }

        return matrix;
    }

    public void printGrid(char[][] grid) {
        char rowAlph = 'A';
        int colInt = 1;

        // Print column headers (numbers)
        System.out.print("  ");
        for (char[] row : grid) {
            System.out.print(colInt + " ");
            colInt++;
        }
        System.out.println(); // new line after header

        // Print row label (letters)
        for (char[] row : grid) {
            System.out.print(rowAlph + " ");
            for (char element : row) {
                System.out.print(element + " ");
            }
            rowAlph++;
            System.out.println(); // new line after each row
        }
        System.out.println(); // new line for more readable output
    }

    public void updateGrid(String start, int length, boolean isHorizontal) {

        if (isHorizontal) {
            int rowHorizontal = start.charAt(0) - 'A';
            int columnHorizontal = start.charAt(1) - '0' - 1;

            for (int i = 0; i < length; i++) {
                grid[rowHorizontal][columnHorizontal] = 'O';
                columnHorizontal++;
            }
        } else {
            int columnVertical = Integer.parseInt(String.valueOf(start.substring(1))) - 1;
            int rowVertical = start.charAt(0) - 'A';

            for (int j = 0; j < length; j++) {
                grid[rowVertical][columnVertical] = 'O';
                rowVertical++;
            }
        }
    }

    public boolean checkGrid(String start, int length, boolean isHorizontal) {
        int row = start.charAt(0) - 'A';
        int col = Integer.parseInt(start.substring(1)) - 1;

        for (int c = 0; c < length; c++) {
            // checks if there is already a ship there
            if (grid[row][col] == 'O') {
                System.out.println("Error! There is a ship already there!");
            }

            // checks surrounding cells (above, below, left, right, diagonal)
            for (int i = Math.max(0, row - 1); i <= Math.min(SIZE - 1, row + 1); i++) {
                for (int j = Math.max(0, col - 1); j <= Math.min(SIZE - 1, col + 1); j++) {
                    if (grid[i][j] == 'O') {
                        System.out.println("Error! Too close to another ship! Try again:");
                        return false;
                    }
                }
            }

            // Move to the next cell
            if (isHorizontal) {
                col++; // move right
            } else {
                row++; // move down
            }
        }
        return true;
    }
}
