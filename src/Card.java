package src;

/**
 * Represents a single playing card in the Blackjack game.
 * Each card has a suit, rank, and associated point value.
 *
 * @author kadengodinez
 */
public class Card {
    private String suit;
    private String rank;
    private int value;

    /**
     * Constructs a Card with the specified suit and rank.
     * Determines the card's point value based on its rank.
     *
     * @param suit The suit of the card (e.g., Hearts, Diamonds, Clubs, Spades).
     * @param rank The rank of the card (e.g., Ace, 2, ..., King).
     */
    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = determineValue(rank);
    }

    /**
     * Determines the point value of the card based on its rank.
     *
     * @param rank The rank of the card.
     * @return The point value corresponding to the card's rank.
     */
    private int determineValue(String rank) {
        switch (rank) {
            case "Ace":
                return 11;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
            case "Jack":
            case "Queen":
            case "King":
                return 10;
            default:
                return 0;
        }
    }

    /**
     * Retrieves the suit of the card.
     *
     * @return The suit of the card.
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Retrieves the rank of the card.
     *
     * @return The rank of the card.
     */
    public String getRank() {
        return rank;
    }

    /**
     * Retrieves the point value of the card.
     *
     * @return The point value of the card.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns a string representation of the card in the format "Rank of Suit".
     *
     * @return A string representing the card.
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}