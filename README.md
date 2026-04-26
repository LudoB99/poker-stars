# Poker Stars

A Texas Hold'em poker game implemented in Java 21 with Maven.

## Requirements

- Java 21+
- Maven 3.x

## Running the game

```bash
mvn package -DskipTests
java -jar target/poker-stars-1.0-SNAPSHOT.jar
```

## Development commands

### Compile
```bash
mvn compile
```

### Run tests
```bash
mvn test
```

### Run tests + generate coverage report
```bash
mvn test
# Report: target/site/jacoco/index.html
```

### Package (build JAR)
```bash
mvn package
```

### Skip tests when packaging
```bash
mvn package -DskipTests
```

### Check code style
```bash
mvn checkstyle:check
```

### Format source code
```bash
mvn formatter:format
```

### Clean build artifacts
```bash
mvn clean
```

### Full clean build with tests
```bash
mvn clean test
```

## Project structure

```
src/
├── main/java/cegepst/
│   ├── Main.java               # Entry point
│   ├── domain/                 # Core value types
│   │   ├── Card.java
│   │   ├── Rank.java           # ACE (14) through TWO (2)
│   │   ├── Suit.java
│   │   └── HandType.java       # ROYAL_FLUSH (10) through HIGH_CARD (1)
│   ├── entities/               # Game entities
│   │   ├── Deck.java
│   │   ├── Player.java
│   │   ├── Dealer.java
│   │   └── Game.java
│   ├── validation/             # Hand evaluation
│   │   ├── HandValidator.java  # Interface
│   │   ├── HandEvaluator.java  # Singleton; evaluates best 5-card hand from 7
│   │   └── EvaluatedHand.java  # Record: type + cards + kickers
│   └── ui/
│       └── Printer.java
└── test/java/cegepst/
    ├── domain/                 # CardTest, RankTest, SuitTest, HandTypeTest
    ├── entities/               # DeckTest, PlayerTest, DealerTest, HandEvaluatorTest
    └── validation/             # EvaluatedHandTest
```

## Hand evaluation

`HandEvaluator` generates all C(7,5) = 21 five-card combinations from the 7 available cards (2 hole + 5 community) and returns the highest-ranked hand. Hand types ranked from best to worst:

| Hand | Rank |
|------|------|
| Royal Flush | 10 |
| Straight Flush | 9 |
| Four of a Kind | 8 |
| Full House | 7 |
| Flush | 6 |
| Straight | 5 |
| Three of a Kind | 4 |
| Two Pair | 3 |
| One Pair | 2 |
| High Card | 1 |
