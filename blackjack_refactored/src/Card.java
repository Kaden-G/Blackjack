package blackjack_refactored;

/**
 * Represents a single playing card in the Blackjack game.  Rather than using
 * free‑form strings for suits and ranks, suits and ranks are strongly typed
 * with enums.  Each {@link Rank} carries its own point value and display
 * name so that the rules of Blackjack are encoded in the enum itself.
 */
public class Card {
    /**
     * Enumeration of the four standard card suits.  A custom
     * {@link #toString()} implementation capitalises the first letter and
     * lower‑cases the rest for user‑friendly display (e.g. "Hearts").
     */
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES;

        @Override
        public String toString() {
            String name = name().toLowerCase();
            return Character.toUpperCase(name.charAt(0)) + name.substring(1);
        }
    }

    /**
     * Enumeration of card ranks together with their point values and human‑
     * friendly display names.  The order of the constants reflects the
     * natural ordering of a deck (Ace through King).
     */
    public enum Rank {
        ACE(11, "Ace"),
        TWO(2, "2"),
        THREE(3, "3"),
        FOUR(4, "4"),
        FIVE(5, "5"),
        SIX(6, "6"),
        SEVEN(7, "7"),
        EIGHT(8, "8"),
        NINE(9, "9"),
        TEN(10, "10"),
        JACK(10, "Jack"),
        QUEEN(10, "Queen"),
        KING(10, "King");

        private final int value;
        private final String displayName;

        Rank(int value, String displayName) {
            this.value = value;
            this.displayName = displayName;
        }

        /**
         * Returns the point value associated with this rank in Blackjack.  For
         * example, face cards are worth 10 and Aces default to 11 (they may
         * later be adjusted down in {@link blackjack_refactored.Hand}).
         */
        public int getValue() {
            return value;
        }

        /**
         * Returns a human‑friendly name for this rank (e.g. "Queen").  Using
         * a separate field instead of {@link #name()} allows numeric ranks
         * like "2" to be displayed exactly as such.
         */
        public String getDisplayName() {
            return displayName;
        }
    }

    private final Suit suit;
    private final Rank rank;

    /**
     * Constructs a new {@code Card} of the given suit and rank.
     *
     * @param suit the card's suit; must not be {@code null}
     * @param rank the card's rank; must not be {@code null}
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Returns the suit of this card.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns the rank of this card.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Returns the point value of this card for Blackjack purposes.
     */
    public int getValue() {
        return rank.getValue();
    }

    /**
     * Returns a string of the form "Rank of Suit", such as "Ace of Hearts"
     * or "10 of Diamonds".  The {@link Suit#toString()} method provides a
     * nicely formatted suit name.
     */
    @Override
    public String toString() {
        return rank.getDisplayName() + " of " + suit;
    }
}
