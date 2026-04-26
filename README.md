# Poker Stars

[![build](https://github.com/LudoB99/poker-stars/actions/workflows/maven.yml/badge.svg)](https://github.com/LudoB99/poker-stars/actions/workflows/maven.yml)
[![codecov](https://codecov.io/gh/LudoB99/poker-stars/graph/badge.svg)](https://codecov.io/gh/LudoB99/poker-stars)

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
