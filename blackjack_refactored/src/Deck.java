package blackjack_refactored;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a standard deck of 52 playing cards.  The deck uses a
 * {@link List} of {@link Card} objects and exposes operations for
 * shuffling, dealing and querying the remaining size.  No printing is
 * performed in this class – callers are responsible for user interaction.
 */
public class Deck {
    private final List<Card> cards;

    /**
     * Constructs a new, fresh deck of 52 cards.  The deck is ordered
     * according to the natural ordering of the {@link Card.Rank} and
     * {@link Card.Suit} enums.
     */
    public Deck() {
        this.cards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Randomises the order of the cards in this deck.  This method does not
     * print or otherwise indicate that it has been called; callers may
     * optionally log a message when they shuffle the deck.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Deals (removes and returns) the top card from the deck, or {@code null}
     * if the deck is empty.  The caller should check for {@code null}
     * before using the returned card.
     */
    public Card dealCard() {
        return cards.isEmpty() ? null : cards.remove(0);
    }

    /**
     * Returns the number of cards remaining in the deck.
     */
    public int remainingCards() {
        return cards.size();
    }
}
