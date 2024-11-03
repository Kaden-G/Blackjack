package src;

import java.util.Scanner;

/**
 * Represents a human player in the Blackjack game.
 * Manages the player's money, current bet, and decision-making during their turn.
 *
 * @author Kaden Godinez
 */
public class Player extends Participant {
    private int money;
    private int currentBet;
    private Scanner scanner; // Shared Scanner instance

    /**
     * Constructs a Player with the specified starting money and Scanner.
     *
     * @param startingMoney The initial amount of money the player has.
     * @param scanner        The Scanner object for user input.
     */
    public Player(int startingMoney, Scanner scanner) {
        super();
        this.money = startingMoney;
        this.scanner = scanner;
    }

    /**
     * Retrieves the current amount of money the player has.
     *
     * @return The player's current money.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Places a bet for the current round.
     * Adjusts the bet if it exceeds the player's available money.
     *
     * @param betAmount The amount the player wishes to bet.
     */
    public void placeBet(int betAmount) {
        if (betAmount > money) {
            System.out.println("Insufficient funds. Placing maximum bet of $" + money + ".");
            currentBet = money;
        } else {
            currentBet = betAmount;
        }
        money -= currentBet;
        System.out.println("Current Bet: $" + currentBet + ", Remaining Money: $" + money + ".");
    }

    /**
     * Retrieves the current bet amount.
     *
     * @return The current bet.
     */
    public int getCurrentBet() {
        return currentBet;
    }

    /**
     * Adds winnings to the player's money.
     *
     * @param amount The amount won.
     */
    public void addWinnings(int amount) {
        money += amount;
        System.out.println("Added winnings: $" + amount + ", New Money Total: $" + money + ".");
    }

    /**
     * Prompts the player to decide whether to hit or stay.
     * Continues to prompt until a valid input ('h' or 's') is received.
     *
     * @return The player's decision: "h" for hit or "s" for stay.
     */
    public String decideHitOrStay() {
        String decision = "";
        while (true) {
            System.out.print("Do you want to hit or stay? (h/s): ");
            decision = scanner.nextLine().trim().toLowerCase();
            if (decision.equals("h") || decision.equals("s")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'h' to hit or 's' to stay.");
            }
        }
        return decision;
    }

    /**
     * Executes the player's turn, allowing them to hit or stay until they choose to stay or bust.
     *
     * @param deck The Deck from which to draw cards.
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