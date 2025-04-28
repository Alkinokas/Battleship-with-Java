package battleship;
import java.util.Scanner;

public class Game {
    private final Player player1;
    private final Player player2;

    public Game() {
        // each player has it's personal battlefield and game engine
        Battlefield battlefield1 = new Battlefield();
        Battlefield battlefield2 = new Battlefield();

        GameEngine engine1 = new GameEngine(battlefield1);
        GameEngine engine2 = new GameEngine(battlefield2);

        this.player1 = new Player("Player 1", battlefield1, engine2);
        this.player2 = new Player("Player 2", battlefield2, engine1);
    }

    public void start() {
        // player1 places ships
        System.out.println(player1.getName() + ", place your ships to the game field");
        System.out.println();
        player1.ask();
        waitForEnter();

        // player2 places ships
        System.out.println(player2.getName() + ", place your ships to the game field");
        System.out.println();
        player2.ask();
        waitForEnter();

        while (true) {
            playTurn(player1, player2);
            if (player1.hasWon()) {
                break;
            }

            playTurn(player2, player1);
            if (player2.hasWon()) {
                break;
            }
        }
    }

    private void playTurn(Player current, Player opponent) {

//        waitForEnter();

        // show opponents hidden grid
        opponent.getOwnField().printGrid(opponent.getOwnField().getHiddenBattleField());
        System.out.println("---------------------");

        // show your own grid
        current.getOwnField().printGrid(current.getOwnField().getGrid());

        // make user move
        System.out.println(current.getName() + ", it's your turn:");

        current.takeShot(opponent);

        waitForEnter();
    }

    public void waitForEnter() {
        System.out.printf("Press Enter and pass the move to another player\n...\n");
        new Scanner(System.in).nextLine();
        System.out.println();
    }

    public static void main(String[] args){
        Game game = new Game();
        game.start();
    }
}
