package blackjack_refactored;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a collection of cards held by a player or dealer.  A hand is
 * responsible for tracking its cards and calculating their total point
 * value, including the special treatment of Aces (counted as 11 by
 * default but downgraded to 1 as needed to avoid busting).  The hand
 * exposes its cards as an unmodifiable list to avoid accidental
 * modification by consumers.
 */
public class Hand {
    private final List<Card> cards = new ArrayList<>();

    /**
     * Adds a card to this hand and adjusts for Aces if necessary.
     * Returning the new total allows callers to conveniently update
     * status displays.
     *
     * @param card the card to add (ignored if {@code null})
     * @return the new total value of the hand
     */
    public int addCard(Card card) {
        if (card != null) {
            cards.add(card);
            return adjustForAce();
        }
        return calculateTotal();
    }

    /**
     * Adjusts the total for Aces, reducing each Ace's value from 11 to 1
     * until the total drops below or equal to 21 or there are no more
     * Aces to adjust.  No output is produced here – callers are free to
     * display messages when a draw causes an Ace to be adjusted.
     *
     * @return the adjusted total
     */
    private int adjustForAce() {
        int total = calculateTotal();
        int aces = 0;
        for (Card card : cards) {
            if (card.getRank() == Card.Rank.ACE) {
                aces++;
            }
        }
        while (total > 21 && aces > 0) {
            total -= 10; // count an Ace as 1 instead of 11
            aces--;
        }
        return total;
    }

    /**
     * Calculates the raw total of the hand without adjusting for Aces.
     */
    public int calculateTotal() {
        int total = 0;
        for (Card card : cards) {
            total += card.getValue();
        }
        return total;
    }

    /**
     * Returns an unmodifiable view of the cards in this hand.  Consumers
     * cannot modify the returned list directly.
     */
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    /**
     * Removes all cards from this hand.
     */
    public void reset() {
        cards.clear();
    }

    /**
     * Displays the cards in the hand to the console.  This method is
     * intentionally kept simple and prints directly to {@link System#out},
     * as it is primarily used by the console‑based {@link Game} class.  If
     * you later wish to decouple the hand from console output, move this
     * logic into the UI layer.
     *
     * @param hideFirstCard whether to hide the first card (useful for the
     *                      dealer's initial hand)
     * @param showTotal     whether to show the total value at the end
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
}
