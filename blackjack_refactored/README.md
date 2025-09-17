Blackjack Game Simulator – Refactored Version
============================================

This directory contains a cleaned‑up and slightly modernized rewrite of the original console‑based Blackjack game.
The goals of this refactor are:

* **Type safety** – raw collections have been replaced with generic `List<Card>` to catch errors at compile time.
* **Enums for card metadata** – suits and ranks are now defined as enums (`Suit` and `Rank`) rather than free‑form strings.  Each rank carries its point value, removing the switch‑statement in the original `Card` class.
* **Separation of concerns** – logic classes no longer print their own status messages.  Instead, the `Game` class manages user interaction, and model classes focus solely on game state.  This makes it easier to change the user interface (for example, to a GUI) without touching the core logic.

Feel free to compare these files with the originals to see what has been updated.
