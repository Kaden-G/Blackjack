package src;

/**
 * Abstract class representing a participant in the Blackjack game.
 * Can be a player or the dealer.
 * Provides a Hand and defines the playTurn method to be implemented by subclasses.
 *
 * @author Kaden Godinez
 */
public abstract class Participant {
    protected Hand hand;

    /**
     * Constructs a Participant with an empty Hand.
     */
    public Participant() {
        this.hand = new Hand();
    }

    /**
     * Retrieves the Hand of the participant.
     *
     * @return The Hand object of the participant.
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Defines the actions taken by the participant during their turn.
     * Must be implemented by subclasses.
     *
     * @param deck The Deck from which to draw cards.
     */
    public abstract void playTurn(Deck deck);
}