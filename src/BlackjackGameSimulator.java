package src;

/**
 * The main class to simulate the Blackjack game.
 * Initializes the game and manages the game loop.
 *
 * @author Kaden Godinez
 */
public class BlackjackGameSimulator {
    /**
     * The entry point of the Blackjack game.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}