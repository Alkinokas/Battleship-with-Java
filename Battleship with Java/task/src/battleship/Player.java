package battleship;

import java.util.Scanner;

public class Player {

    private final String name;
    private final Battlefield ownField;
    private final Scanner scanner;
    private final GameEngine opponentEngine;

    public Player (String name, Battlefield ownField, GameEngine opponentEngine) {
        this.name = name;
        this.ownField = ownField;
        this.opponentEngine = opponentEngine;
        this.scanner = new Scanner(System.in);
    }

    public String getName() {
        return name;
    }

    public void ask() {
        boolean flag = true;
        ownField.printGrid(ownField.getGrid());

        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        System.out.println();

        while (flag) {
            String start5 = scanner.next();
            String end5 = scanner.next();
            System.out.println();

            Ship ship = new Ship(start5, end5);
            if (ship.getLength() != 5) {
                System.out.println("Error! Wrong cell length! Try again:");
                flag = true;
            } else {
                flag = ship.check(ownField);
            }
        }

        ownField.printGrid(ownField.getGrid());

        System.out.println("Enter the coordinates of the Battleship (4 cells):");
        System.out.println();
        flag = true;

        while (flag) {
            String start4 = scanner.next();
            String end4 = scanner.next();
            System.out.println();

            Ship ship = new Ship(start4, end4);
            if (ship.getLength() != 4) {
                System.out.println("Error! Wrong cell length! Try again:");
                flag = true;
            } else {
                flag = ship.check(ownField);
            }
        }

        ownField.printGrid(ownField.getGrid());

        System.out.println("Enter the coordinates of the Submarine (3 cells):");
        System.out.println();
        flag = true;

        while (flag) {
            String start3 = scanner.next();
            String end3 = scanner.next();
            System.out.println();

            Ship ship = new Ship(start3, end3);
            if (ship.getLength() != 3) {
                System.out.println("Error! Wrong cell length! Try again:");
                System.out.println();
                flag = true;
            } else {
                flag = ship.check(ownField);
            }
        }

        ownField.printGrid(ownField.getGrid());

        System.out.println("Enter the coordinates of the Cruiser (3 cells):");
        System.out.println();
        flag = true;

        while (flag) {
            String startCruiser = scanner.next();
            String endCruiser = scanner.next();
            System.out.println();

            Ship ship = new Ship(startCruiser, endCruiser);
            if (ship.getLength() != 3) {
                System.out.println("Error! Wrong cell length! Try again:");
                System.out.println();
                flag = true;
            } else {
                flag = ship.check(ownField);
            }
        }

        ownField.printGrid(ownField.getGrid());

        System.out.println("Enter the coordinates of the Destroyer (2 cells):");
        System.out.println();
        flag = true;

        while (flag) {
            String start2 = scanner.next();
            String end2 = scanner.next();
            System.out.println();

            Ship ship = new Ship(start2, end2);
            if (ship.getLength() != 2) {
                System.out.println("Error! Wrong cell length! Try again:");
                System.out.println();
                flag = true;
            } else {
                flag = ship.check(ownField);
            }
        }
        ownField.printGrid(ownField.getGrid());
//        System.out.println("The game starts!");
        System.out.println();


        // Clean the newline left over from scanner.next()
        scanner.nextLine();
    }

    public void takeShot(Player opponent) {
//        char[][] grid = opponent.getOwnField().getGrid();
//        char[][] hidden = opponent.getOwnField().getHiddenBattleField();

    // Main game loop: continue until all 5 ships are sunk
        boolean flag2 = true;
        boolean sunk = false;
        String userInput;

        // Loop until valid input is entered
        while (flag2) {
            userInput = scanner.nextLine().trim();  // Read user input and trim whitespace
            System.out.println();

            // Validate that input is not empty or too short (e.g., just "A")
            if (userInput.length() < 2) {
                System.out.println("Error! Invalid input! Try again:");
                continue;
            }

            char rowChar = userInput.charAt(0);        // First character is the row (A–J)
            String colPart = userInput.substring(1);   // Rest is the column number (1–10)

            int userInputRow;
            int userInputCol;

            try {
                int col = Integer.parseInt(colPart);   // Convert column to number

                // Validate row and column are within grid boundaries
                if ((rowChar < 'A' || rowChar > 'J') || col < 1 || col > 10) {
                    System.out.println("Error! Out of Grid! Try again:");
                    continue;
                }

                // Convert input into grid indices
                userInputRow = rowChar - 'A';
                userInputCol = col - 1;
                flag2 = false;  // Valid input

                // Get current state of grids
                char[][] grid = opponentEngine.getBattlefield().getGrid();
                char[][] hiddenBattleField = opponentEngine.getBattlefield().getHiddenBattleField();

                // Case 1: Hit
                if (grid[userInputRow][userInputCol] == 'O') {
                    grid[userInputRow][userInputCol] = 'X';                    // Mark hit on real grid
//                    hiddenBattleField[userInputRow][userInputCol] = 'X';       // Mark hit on visible grid

//                        opponentEngine.getBattlefield().printGrid(hiddenBattleField);

                    sunk = opponentEngine.isSunk(userInputRow, userInputCol, grid);    // Check if the ship is fully sunk
//                        opponentEngine.getBattlefield().printGrid(hiddenBattleField);                  // Show updated visible grid
                    System.out.println("You hit a ship!");

                    // If ship was sunk
                    if (sunk) {
                        opponentEngine.incrementShipSunk();  // Update sunk ship count
                        if (opponentEngine.getShipSunk() == 5) {
                            System.out.println("You sank the last ship. You won. Congratulations!");
                        } else {
                            System.out.println("You sank a ship! Specify a new target:");
                        }
                    }

                    System.out.println();

                    // Case 2: Already hit before
                } else if (grid[userInputRow][userInputCol] == 'X') {
//                        opponentEngine.getBattlefield().printGrid(hiddenBattleField);
                    System.out.println("You hit a ship!");
                    System.out.println();

                    // Case 3: Miss
                } else {
                    grid[userInputRow][userInputCol] = 'M';                     // Mark miss on real grid
//                    hiddenBattleField[userInputRow][userInputCol] = 'M';        // Mark miss on visible grid
//                        opponentEngine.getBattlefield().printGrid(hiddenBattleField);                   // Show updated visible grid
                    System.out.println("You missed!");
                    System.out.println();
                }

            } catch (NumberFormatException e) {
                // Case: Column was not a number (e.g., "A*")
                System.out.println("Error! Invalid input!");
            }
        }
    }

    public boolean hasWon() {
        return opponentEngine.getShipSunk() == 5;
    }

    public Battlefield getOwnField() {
        return ownField;
    }

    public GameEngine getOpponentEngine() {
        return opponentEngine;
    }
}
