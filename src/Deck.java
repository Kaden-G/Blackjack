package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a standard deck of 52 playing cards used in the Blackjack game.
 * Provides functionalities to shuffle the deck and deal cards.
 *
 * @author kadengodinez
 */
public class Deck {
    private List<Card> cards;

    /**
     * Constructs a new Deck, initializing it with 52 standard playing cards.
     */
    public Deck() {
        this.cards = new ArrayList<>();
        initializeDeck();
    }

    /**
     * Initializes the deck with 52 cards (13 ranks in each of the 4 suits).
     */
    private void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {
                "Ace", "2", "3", "4", "5", "6", "7",
                "8", "9", "10", "Jack", "Queen", "King"
        };

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Shuffles the deck randomly.
     */
    public void shuffle() {
        Collections.shuffle(cards);
        System.out.println("The deck has been shuffled.");
    }

    /**
     * Deals (removes and returns) the top card from the deck.
     *
     * @return The top Card from the deck, or null if the deck is empty.
     */
    public Card dealCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        } else {
            System.out.println("The deck is empty. No more cards to deal.");
            return null;
        }
    }

    /**
     * Retrieves the number of remaining cards in the deck.
     *
     * @return The count of remaining cards.
     */
    public int remainingCards() {
        return cards.size();
    }
}