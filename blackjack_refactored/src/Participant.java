package blackjack_refactored;

/**
 * Abstract base class representing a participant in the Blackjack game.  A
 * participant holds a {@link Hand} and defines an abstract {@link
 * #playTurn(Deck)} method that is implemented differently by players and
 * dealers.
 */
public abstract class Participant {
    protected final Hand hand;

    /**
     * Constructs a participant with a new, empty hand.
     */
    public Participant() {
        this.hand = new Hand();
    }

    /**
     * Returns this participant's hand.
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Defines the actions taken by the participant during their turn.
     * Subclasses must implement this to encapsulate their specific logic.
     *
     * @param deck the deck from which to draw cards
     */
    public abstract void playTurn(Deck deck);
}
