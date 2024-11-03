package src;

/**
 * Represents the dealer in the Blackjack game.
 * Manages the dealer's hand and enforces dealer-specific rules during their turn.
 *
 * @author Kaden Godinez
 */
public class Dealer extends Participant {

    /**
     * Constructs a Dealer with an empty Hand.
     * Suppresses Ace adjustment messages to maintain clean output.
     */
    public Dealer() {
        super();
        this.hand.setPrintAceAdjustmentMessages(false); // Suppress Ace adjustment messages for dealer
    }

    /**
     * Reveals the dealer's full hand by displaying all cards and the total point value.
     */
    public void revealHiddenCard() {
        System.out.println("Dealer's hand:");
        hand.displayHand(false, true); // Show all cards and total
    }

    /**
     * Executes the dealer's turn.
     * Dealer must hit until their total is at least 17.
     * Displays each draw and the updated total once.
     *
     * @param deck The Deck from which to draw cards.
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
                break;
            }
        }
        if (hand.calculateTotal() >= 17 && hand.calculateTotal() <= 21) {
            System.out.println("Dealer stays.");
        }
    }
}