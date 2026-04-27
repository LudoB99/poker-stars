# Poker Stars

[![build](https://github.com/LudoB99/poker-stars/actions/workflows/maven.yml/badge.svg)](https://github.com/LudoB99/poker-stars/actions/workflows/maven.yml)
[![codecov](https://codecov.io/gh/LudoB99/poker-stars/graph/badge.svg)](https://codecov.io/gh/LudoB99/poker-stars)

Un jeu de poker Texas Hold'em développé en Java 21 avec Maven. Comprend une API REST Spring Boot et un frontend HTML/CSS/JS vanilla pour jouer en solo contre un bot.

## Prérequis

- Java 21+
- Maven 3.x

## Lancer l'application web

```bash
mvn spring-boot:run
```

Puis ouvrir [http://localhost:8080](http://localhost:8080) dans votre navigateur.

Autrement, construire et exécuter le JAR :

```bash
mvn package -DskipTests
java -jar target/poker-stars-1.0-SNAPSHOT.jar
```

## Comment jouer

- Chaque main débute avec les deux joueurs qui misent une blind de 10 jetons (pot = 20).
- **Passer** — continuer à la prochaine rue sans frais (le bot fait de même).
- **Relancer +20** — miser 20 jetons ; le bot suit toujours.
- **Se coucher** — se coucher et céder le pot au bot.
- Rues : Pré-Flop → Flop (3 cartes) → Turn (4 cartes) → River (5 cartes) → Abattage (showdown).
- Les jetons sont conservés entre les mains ; les deux joueurs sont réinitialisés à 1 000 si l'un d'eux est à court.

## Points de terminaison de l'API

| Méthode | Chemin | Description |
|---------|--------|-------------|
| `POST` | `/api/game/start` | Démarrer une nouvelle main (les jetons persistent entre les mains dans la session) |
| `POST` | `/api/game/action` | Envoyer une action : `{"action": "fold" \| "check" \| "raise"}` |

## Commandes de développement

### Compiler
```bash
mvn compile
```

### Exécuter les tests
```bash
mvn test
```

### Exécuter les tests + générer un rapport de couverture
```bash
mvn test
# Rapport : target/site/jacoco/index.html
```

### Empaqueter (construire le JAR)
```bash
mvn package
```

### Ignorer les tests lors de l'empaquetage
```bash
mvn package -DskipTests
```

### Vérifier le style du code
```bash
mvn checkstyle:check
```

### Formater le code source
```bash
mvn formatter:format
```

### Nettoyer les artefacts de construction
```bash
mvn clean
```

### Construction complète avec tests
```bash
mvn clean test
```
