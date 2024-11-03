package src;

import java.util.Scanner;

/**
 * Manages the flow of the Blackjack game.
 * Handles game initialization, player and dealer interactions, betting, and determining outcomes.
 *
 * @author Kaden Godinez
 */
public class Game {
    private Player player;
    private Dealer dealer;
    private Deck deck;
    private Scanner scanner;

    /**
     * Constructs a new Game instance with a Scanner for user input.
     */
    public Game() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the Blackjack game by displaying the welcome message and initiating the game loop.
     */
    public void startGame() {
        displayWelcomeMessage();
        System.out.print("Enter your starting money: ");
        int startingMoney = getValidIntegerInput();
        player = new Player(startingMoney, scanner); // Pass the shared Scanner
        dealer = new Dealer();
        deck = new Deck();
        deck.shuffle();
        playGameLoop();
    }

    /**
     * Displays the welcome message and game rules to the player.
     */
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

    /**
     * The main game loop handling rounds until the player decides to quit or runs out of money.
     */
    private void playGameLoop() {
        while (player.getMoney() > 0) {
            System.out.println("\nYou have $" + player.getMoney());
            System.out.print("Enter your bet: ");
            int bet = getValidIntegerInput();
            player.placeBet(bet);

            // Initialize hands by resetting them
            player.getHand().reset();
            dealer.getHand().reset();

            // Deal initial cards
            player.getHand().addCard(deck.dealCard());
            dealer.getHand().addCard(deck.dealCard());
            player.getHand().addCard(deck.dealCard());
            dealer.getHand().addCard(deck.dealCard());

            // Display initial hands
            System.out.println("\nYour hand:");
            player.getHand().displayHand(false, true); // Show total

            System.out.println("\nDealer's hand:");
            dealer.getHand().displayHand(true, false); // Hide first card, do not show total

            // Player's turn
            System.out.println("\n--- Player's Turn ---");
            player.playTurn(deck);

            // Check if player busted
            if (player.getHand().calculateTotal() > 21) {
                System.out.println("You lose this round.");
                continue;
            }

            // Dealer's turn
            System.out.println("\n--- Dealer's Turn ---");
            dealer.revealHiddenCard();
            dealer.playTurn(deck);

            // Determine winner
            determineWinner();

            // Check if player wants to continue
            if (!playerWantsToContinue()) {
                break;
            }

            // Reshuffle if deck is low
            if (deck.remainingCards() < 10) {
                System.out.println("\nThe deck is running low on cards.");
                System.out.println("Reshuffling the deck...");
                deck = new Deck();
                deck.shuffle();
            }
        }

        System.out.println("\nGame over! You leave with $" + player.getMoney() + ".");
        scanner.close(); // Close the Scanner when done
    }

    /**
     * Determines the winner of the round based on the total points of the player and dealer.
     * Updates the player's money accordingly.
     */
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

    /**
     * Prompts the player to decide whether to continue playing or quit.
     *
     * @return True if the player wants to continue, false otherwise.
     */
    private boolean playerWantsToContinue() {
        while (true) {
            System.out.print("\nDo you want to play another round? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'y' to continue or 'n' to quit.");
            }
        }
    }

    /**
     * Retrieves a valid positive integer input from the user.
     * Continues to prompt until a valid input is received.
     *
     * @return A valid positive integer entered by the user.
     */
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