Blackjack Game Simulator

Description

A console-based Blackjack game simulation developed in Java. The game allows a player to place bets, receive cards, and make decisions to “Hit” or “Stay” to achieve a hand value as close to 21 as possible without exceeding it. The dealer follows standard Blackjack rules, hitting until reaching a minimum of 17 points. The game continues until the player decides to quit or runs out of money.

Features

	•	Single-Player Gameplay: Play against a computer-controlled dealer.
	•	Betting System: Place bets within your available funds.
	•	Ace Value Optimization: Aces can be valued at 1 or 11 to benefit your hand.
	•	JavaDoc Documentation: Comprehensive documentation for all classes and methods.
	•	User-Friendly Interface: Clear and concise console outputs for an enhanced gaming experience.

Requirements

	•	Java Development Kit (JDK) 8 or higher.
```
Project Structure

BlackjackGame/
│
├── src/
│   ├── BlackjackGameSimulator.java
│   ├── Card.java
│   ├── Deck.java
│   ├── Hand.java
│   ├── Participant.java
│   ├── Player.java
│   ├── Dealer.java
│   └── Game.java
│
├── bin/         (Contains compiled `.class` files)
├── javadocs/    (Contains generated JavaDoc files)
└── README.md
```
How to Compile and Run

	1.	Navigate to the Project Directory:
Ensure you are in the root directory of the project where the src/ folder is located.
2.	Compile the Source Files:
Use the javac command to compile all Java source files in the src/ directory. This will place the compiled .class files in the bin/ directory.
```
javac -d bin src/*.java
```

	3.	Run the Game:
Use the java command to run the main class BlackjackGameSimulator from the bin/ directory.
```
java -cp bin src.BlackjackGameSimulator

```

Generating JavaDocs

To generate JavaDoc documentation for the project:

	1.	Navigate to the Project Directory:
Ensure you are in the root directory of the project.
2.	Run the JavaDoc Tool:
Use the javadoc command to generate documentation for all Java source files in the src/ directory. The generated documentation will be placed in the javadocs/ directory.
```
javadoc -private -d javadocs src/*.java
```

	3.	View the Documentation:
Open the index.html file in the javadocs/ directory using a web browser to view the generated JavaDoc.

Author

Kaden Godinez

License 

This project is licensed under the MIT.


