package blackjack_refactored;

import java.util.Scanner;

/**
 * Manages the overall flow of a Blackjack game.  This class is
 * responsible for user interaction (printing messages and reading input),
 * dealing cards, prompting the player and dealer to take their turns,
 * determining winners and handling bets.  The model classes (Deck,
 * Player, Dealer, Hand and Card) do not perform any I/O themselves.
 */
public class Game {
    private Player player;
    private Dealer dealer;
    private Deck deck;
    private final Scanner scanner;

    /**
     * Constructs a new game with a fresh {@link Scanner} for user input.
     */
    public Game() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the Blackjack game by displaying the welcome message and
     * initiating the main game loop.
     */
    public void startGame() {
        displayWelcomeMessage();
        System.out.print("Enter your starting money: ");
        int startingMoney = getValidIntegerInput();
        player = new Player(startingMoney, scanner);
        dealer = new Dealer();
        deck = new Deck();
        deck.shuffle();
        System.out.println("The deck has been shuffled.");
        playGameLoop();
    }

    private void displayWelcomeMessage() {
        System.out.println("====================================");
        System.out.println("       Welcome to Blackjack!");
        System.out.println("====================================\n");
        System.out.println("** How to Play Blackjack **");
        System.out.println("1. The goal is to have a hand value as close to 21 as possible without exceeding it.");
        System.out.println("2. Number cards are worth their face value.");
        System.out.println("3. Face cards (King, Queen, Jack) are worth 10.");
        System.out.println("4. Aces can be worth 1 or 11, whichever benefits your hand more.");
        System.out.println("5. At the start, you and the dealer are dealt two cards each.");
        System.out.println("   - One of the dealer's cards remains hidden.");
        System.out.println("6. You can choose to 'Hit' to draw another card or 'Stay' to keep your current hand.");
        System.out.println("7. After your turn, the dealer reveals the hidden card and plays.");
        System.out.println("   - The dealer must hit until their total is at least 17.");
        System.out.println("8. The player wins by having a higher total than the dealer without busting,");
        System.out.println("   or if the dealer busts while the player does not.\n");
        System.out.println("Enjoy the game!\n");
    }

    private void playGameLoop() {
        while (player.getMoney() > 0) {
            System.out.println("\nYou have $" + player.getMoney());
            System.out.print("Enter your bet: ");
            int bet = getValidIntegerInput();
            int actualBet = player.placeBet(bet);
            if (actualBet < bet) {
                System.out.println("Insufficient funds. Placing maximum bet of $" + actualBet + ".");
            }
            System.out.println("Current Bet: $" + actualBet + ", Remaining Money: $" + player.getMoney() + ".");

            // reset hands
            player.getHand().reset();
            dealer.getHand().reset();

            // deal initial cards
            player.getHand().addCard(deck.dealCard());
            dealer.getHand().addCard(deck.dealCard());
            player.getHand().addCard(deck.dealCard());
            dealer.getHand().addCard(deck.dealCard());

            // display initial hands
            System.out.println("\nYour hand:");
            player.getHand().displayHand(false, true);
            System.out.println("\nDealer's hand:");
            dealer.getHand().displayHand(true, false);

            // player's turn
            System.out.println("\n--- Player's Turn ---");
            player.playTurn(deck);
            if (player.getHand().calculateTotal() > 21) {
                System.out.println("You lose this round.");
                // no winnings added; bet is already deducted
                if (!playerWantsToContinue()) {
                    break;
                }
                // reshuffle deck if necessary
                if (deck.remainingCards() < 10) {
                    System.out.println("\nThe deck is running low on cards.");
                    System.out.println("Reshuffling the deck...");
                    deck = new Deck();
                    deck.shuffle();
                    System.out.println("The deck has been shuffled.");
                }
                continue;
            }

            // dealer's turn
            System.out.println("\n--- Dealer's Turn ---");
            dealer.revealHiddenCard();
            dealer.playTurn(deck);

            // determine winner and adjust money
            determineWinner();

            // ask to continue
            if (!playerWantsToContinue()) {
                break;
            }
            // reshuffle deck if low
            if (deck.remainingCards() < 10) {
                System.out.println("\nThe deck is running low on cards.");
                System.out.println("Reshuffling the deck...");
                deck = new Deck();
                deck.shuffle();
                System.out.println("The deck has been shuffled.");
            }
        }
        System.out.println("\nGame over! You leave with $" + player.getMoney() + ".");
        scanner.close();
    }

    private void determineWinner() {
        int playerTotal = player.getHand().calculateTotal();
        int dealerTotal = dealer.getHand().calculateTotal();
        System.out.println("\n--- Determining Winner ---");
        System.out.println("Your total: " + playerTotal);
        System.out.println("Dealer's total: " + dealerTotal);
        if (dealerTotal > 21) {
            System.out.println("Dealer busted! You win $" + player.getCurrentBet() + ".");
            player.addWinnings(player.getCurrentBet() * 2);
        } else if (playerTotal > dealerTotal) {
            System.out.println("You win! You gain $" + player.getCurrentBet() + ".");
            player.addWinnings(player.getCurrentBet() * 2);
        } else if (playerTotal < dealerTotal) {
            System.out.println("Dealer wins! You lose $" + player.getCurrentBet() + ".");
        } else {
            System.out.println("It's a tie! Your bet is returned.");
            player.addWinnings(player.getCurrentBet());
        }
    }

    private boolean playerWantsToContinue() {
        while (true) {
            System.out.print("\nDo you want to play another round? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            }
            System.out.println("Invalid input. Please enter 'y' to continue or 'n' to quit.");
        }
    }

    private int getValidIntegerInput() {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine().trim());
                if (input > 0) {
                    return input;
                } else {
                    System.out.print("Please enter a positive integer: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
            }
        }
    }
}
