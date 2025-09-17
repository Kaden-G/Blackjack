package blackjack_refactored;

/**
 * Represents the dealer in the Blackjack game.  The dealer has slightly
 * different rules from the player: the dealer reveals their hidden card
 * only after the player's turn and must hit until reaching at least 17.
 */
public class Dealer extends Participant {

    /**
     * Constructs a new dealer with an empty hand.  No special behaviour is
     * needed beyond what {@link Participant} provides.
     */
    public Dealer() {
        super();
    }

    /**
     * Reveals the dealer's full hand by printing all cards and the total.
     */
    public void revealHiddenCard() {
        System.out.println("Dealer's hand:");
        hand.displayHand(false, true);
    }

    /**
     * Plays the dealer's turn.  The dealer must draw until their total is
     * at least 17.  Messages are printed to indicate draws and the
     * running total.  If the dealer busts, a message is printed.
     *
     * @param deck the deck from which to draw cards
     */
    @Override
    public void playTurn(Deck deck) {
        System.out.println("Dealer's turn:");
        while (hand.calculateTotal() < 17) {
            Card newCard = deck.dealCard();
            if (newCard == null) {
                System.out.println("No more cards in the deck.");
                break;
            }
            System.out.println("Dealer draws: " + newCard);
            hand.addCard(newCard);
            System.out.println("Dealer's total: " + hand.calculateTotal());
            if (hand.calculateTotal() > 21) {
                System.out.println("Dealer busted!");
                return;
            }
        }
        if (hand.calculateTotal() >= 17 && hand.calculateTotal() <= 21) {
            System.out.println("Dealer stays.");
        }
    }
}
