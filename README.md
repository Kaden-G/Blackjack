
````markdown
# Blackjack Game Simulator

A simple console-based Blackjack game written in Java.  
This project was built as a learning exercise in object-oriented design, focusing on separation of concerns, class hierarchies, and basic game logic.

---

## How to Play

- The game follows the standard rules of Blackjack:
  - Player starts with an initial balance of money.
  - Before each hand, the player places a bet.
  - Player and dealer are both dealt two cards (dealer shows one face-up).
  - Player may `Hit` (take another card) or `Stay` (end their turn).
  - If the player exceeds 21, they bust and lose their bet.
  - Dealer reveals their hand and must hit until reaching at least 17.
  - The closest to 21 without going over wins.

- Aces are counted as 11 unless that would cause a bust, in which case they count as 1.

---

## Features

- Object-oriented class structure:
  - `Card`, `Deck`, `Hand` classes model the physical game objects.
  - `Player` and `Dealer` extend from a common `Participant` base class.
  - `Game` orchestrates the overall gameplay and user interaction.

- Console input/output for a lightweight play experience.

- Betting system that tracks player’s money and winnings.

---

## Requirements

- Java 8 or newer
- A terminal or console that can run Java programs

---

## How to Build and Run

1. **Compile the game:**
   ```bash
   javac src/*.java
````

2. **Run the simulator:**

   ```bash
   java -cp src BlackjackGameSimulator
   ```

---

## Example Gameplay

```
Welcome to Blackjack!
You have $100.
Place your bet: 20
Dealing cards...

Your hand: [9♣][7♦] (total: 16)
Dealer shows: [K♠]

Hit or stay? h
You drew: [5♥]
Your hand: [9♣][7♦][5♥] (total: 21)

Dealer's hand: [K♠][6♣] (total: 16)
Dealer hits...
Dealer drew: [8♦]
Dealer busts with 24!

You win $20. Current balance: $120.
```

---

## Project Structure

```
src/
  BlackjackGameSimulator.java  # Entry point
  Game.java                    # Manages flow of the game
  Card.java                    # Represents a single playing card
  Deck.java                    # Represents a standard deck
  Hand.java                    # Represents a player/dealer hand
  Participant.java             # Abstract base for Player/Dealer
  Player.java                  # Human player logic
  Dealer.java                  # Dealer logic
```

---

## Latest Upgrades

This refactor introduces several improvements over the original version:

* **Type safety** – raw collections have been replaced with generic `List<Card>` to catch errors at compile time.
* **Enums for card metadata** – suits and ranks are strongly typed, and ranks carry their own point values and display names.
* **Cleaner separation of concerns** – core classes (`Deck`, `Hand`, `Card`) no longer print directly; all user interaction is managed in `Game`.
* **Simplified betting logic** – `Player` methods now return values instead of printing directly, so the game loop manages messages.
* **Refreshed game loop** – `Game` now clearly handles shuffling, dealing, turns, winner checks, and reshuffling when the deck is low.

---

## License

This project is open source and available under the MIT License.

```

