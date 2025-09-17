package blackjack_refactored;

import java.util.Scanner;

/**
 * Represents a human player in the Blackjack game.  In addition to the
 * functionality provided by {@link Participant}, a player tracks their
 * available money and current bet and prompts the user for decisions.
 */
public class Player extends Participant {
    private int money;
    private int currentBet;
    private final Scanner scanner;

    /**
     * Creates a new player with the specified starting money and shared
     * {@link Scanner} for user input.  Sharing a scanner avoids mixing
     * multiple input streams.
     */
    public Player(int startingMoney, Scanner scanner) {
        super();
        this.money = startingMoney;
        this.scanner = scanner;
    }

    /**
     * Returns the player's available funds.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Returns the amount currently wagered for the next round.
     */
    public int getCurrentBet() {
        return currentBet;
    }

    /**
     * Places a bet for the current round.  If the requested bet exceeds the
     * player's available funds, the bet is limited to the available amount.
     * The bet amount is subtracted from the player's money.  The caller can
     * return value to display or otherwise act upon the actual bet placed.
     *
     * @param betAmount the amount the player wishes to bet
     * @return the actual bet that was placed
     */
    public int placeBet(int betAmount) {
        if (betAmount > money) {
            currentBet = money;
        } else {
            currentBet = betAmount;
        }
        money -= currentBet;
        return currentBet;
    }

    /**
     * Adds the specified amount of winnings to the player's money.  In
     * standard Blackjack, winnings are typically paid at 1:1 (so a bet of
     * \$10 yields \$20 on a win).  The caller is responsible for computing
     * the correct payout.
     */
    public void addWinnings(int amount) {
        money += amount;
    }

    /**
     * Prompts the player to decide whether to hit or stay.  Keeps asking
     * until a valid response ('h' or 's') is received.
     *
     * @return the player's decision: "h" for hit or "s" for stay
     */
    public String decideHitOrStay() {
        while (true) {
            System.out.print("Do you want to hit or stay? (h/s): ");
            String decision = scanner.nextLine().trim().toLowerCase();
            if (decision.equals("h") || decision.equals("s")) {
                return decision;
            }
            System.out.println("Invalid input. Please enter 'h' to hit or 's' to stay.");
        }
    }

    /**
     * Executes the player's turn: prompts for hit/stay decisions until the
     * player chooses to stay or busts by exceeding 21.  Messages and totals
     * are printed to the console here, as this class encapsulates the
     * player's interaction logic.
     */
    @Override
    public void playTurn(Deck deck) {
        while (hand.calculateTotal() < 21) {
            String decision = decideHitOrStay();
            if (decision.equals("h")) {
                Card newCard = deck.dealCard();
                if (newCard == null) {
                    System.out.println("No more cards in the deck.");
                    break;
                }
                System.out.println("You drew: " + newCard);
                hand.addCard(newCard);
                System.out.println("Total: " + hand.calculateTotal());
                if (hand.calculateTotal() > 21) {
                    System.out.println("You busted!");
                    break;
                }
            } else {
                System.out.println("You chose to stay.");
                break;
            }
        }
    }
}
