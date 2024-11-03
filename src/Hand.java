package src;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a hand of playing cards for either the player or the dealer.
 * Manages the cards in the hand and calculates the total point value.
 * Handles Ace value adjustments to prevent busting.
 *
 * @author Kaden Godinez
 */
public class Hand {
    private List<Card> cards;
    private boolean printAceAdjustmentMessages = true;

    /**
     * Constructs a new, empty Hand.
     */
    public Hand() {
        this.cards = new ArrayList<>();
    }

    /**
     * Sets whether Ace adjustment messages should be printed.
     * Useful for controlling verbosity for the dealer.
     *
     * @param print True to print Ace adjustment messages, false to suppress them.
     */
    public void setPrintAceAdjustmentMessages(boolean print) {
        this.printAceAdjustmentMessages = print;
    }

    /**
     * Adds a card to the hand and adjusts for Aces if necessary.
     *
     * @param card The Card to add to the hand.
     * @return The new total point value of the hand after adjustments.
     */
    public int addCard(Card card) {
        if (card != null) {
            cards.add(card);
            return adjustForAce();
        }
        return calculateTotal();
    }

    /**
     * Adjusts the value of Aces from 11 to 1 if the total exceeds 21.
     *
     * @return The adjusted total point value of the hand.
     */
    private int adjustForAce() {
        int total = calculateTotal();
        int aces = 0;
        for (Card card : cards) {
            if (card.getRank().equalsIgnoreCase("Ace")) {
                aces++;
            }
        }

        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
            if (printAceAdjustmentMessages) {
                System.out.println("Adjusting Ace value from 11 to 1 to prevent bust.");
            }
        }

        return total;
    }

    /**
     * Calculates the total point value of the hand without adjusting Aces.
     *
     * @return The total point value of the hand.
     */
    public int calculateTotal() {
        int total = 0;
        for (Card card : cards) {
            total += card.getValue();
        }
        return total;
    }

    /**
     * Displays the cards in the hand.
     * Optionally hides the first card and shows the total point value.
     *
     * @param hideFirstCard Whether to hide the first card (useful for dealer's initial hand).
     * @param showTotal     Whether to display the total point value of the hand.
     */
    public void displayHand(boolean hideFirstCard, boolean showTotal) {
        for (int i = 0; i < cards.size(); i++) {
            if (i == 0 && hideFirstCard) {
                System.out.println("Hidden Card");
            } else {
                System.out.println(cards.get(i));
            }
        }
        if (showTotal) {
            System.out.println("Total: " + calculateTotal());
        }
    }

    /**
     * Retrieves the list of cards in the hand.
     *
     * @return A List of Card objects in the hand.
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Clears all cards from the hand, resetting it for a new round.
     */
    public void reset() {
        cards.clear();
    }
}