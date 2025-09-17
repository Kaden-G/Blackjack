package blackjack_refactored;

/**
 * Entry point for the refactored Blackjack game.  Instantiates a
 * {@link Game} and starts it.  This class contains no logic of its own.
 */
public class BlackjackGameSimulator {
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
