# Battledrome

Battledrome is a Java Battleship game engine and starter project for creating
your own AI player. Add a player class in a package of your own, then run it
against the included players or another student's player.

## Get started

1. Create your own **private** fork of this project and open it in VS Code.
2. Keep the existing source files and add your code under `src/` in a package. Please create a package named after yourself... I'd make a package called `JohnPannell` and start my player class at `src/JohnPannell/JohnsPlayer.java`.
3. To run the battledrome, open `src/App.java` and select **Run** above `main` (or use the play button). The default battle runs 1,000 games and prints the final win counts.

The source folders are `src/` and `test/`; compiled classes go in `bin/`.
The included JUnit console runner is in `lib/`.

## Create your player

Your player must implement [`Common.Player`](src/Common/Player.java). For me, this might look like:

```java
package JohnPannell;

import Common.Player;

public class JohnsPlayer implements Player {
    // Implement all methods declared by Common.Player.
}
```

In VS Code, you can use the Java **Implement Methods** code action after adding
`implements Player`. Implement all six methods:

| Method | What your player should do |
| --- | --- |
| `getName()` | Return a name for your player. |
| `placeShips()` | Set up your fleet on your own board before the game starts. |
| `takeShot()` | Choose and return the next `Common.Coordinate` to attack. |
| `receiveShot(Coordinate shot)` | Apply the opponent's shot to your board and return a `Common.ShotResult`. |
| `receiveShotResult(ShotResult shotResult)` | Update your strategy using the result of your previous shot. |
| `shipsAreSunk()` | Return `true` when every ship in your fleet has been sunk. |

Each player should maintain its own board, fleet, and strategy state. The game
calls `placeShips()` once at the beginning of each game, then alternates shots:
the opponent processes a shot with `receiveShot()`, and the shooter receives
the result through `receiveShotResult()`. A game ends when a player's
`shipsAreSunk()` returns `true`.

Coordinates represent a 10-by-10 board. You can create one with zero-based row
and column values from `0` to `9`, or with a human-readable position such as
`new Coordinate("B7")` (rows A–J and columns 1–10). `ShotResult` reports a
`MISS`, `HIT`, or `SUNK` outcome; a sunk result also includes the ship's name.

The `Optional` package contains board, ship, and ship-factory classes you may
use. The included players use `AutomaticShipFactory` to create a standard
fleet. You may build on these components or implement your own, while keeping
your player in your own package.

## Included players

Two example players are ready to use:

- `Easy.EasyAIPlayer` (CheckerBot 2.0) takes a shuffled checkerboard pattern
  first, then fills in the remaining squares.
- `Rando.RandomAIPlayer` (Rando Loser 1.0) shoots at every square in a
  shuffled order.

Both use the provided automatic ship placement. They are examples to learn
from and opponents to test against.

## Pit your player against an included player

Open `src/Battledrome.java` and find the two player constructors inside
`battle()`:

```java
Player player1 = new Easy.EasyAIPlayer();
Player player2 = new Rando.RandomAIPlayer();
```

Replace either constructor with your fully qualified class name. For example,
to play my `JohnPannell.JohnsPlayer` against Rando:

```java
Player player1 = new JohnPannell.JohnsPlayer();
Player player2 = new Rando.RandomAIPlayer();
```

Run `App.java` again. The engine plays 1,000 games, randomly chooses which
player goes first in each game, and reports total wins for Player 1 and
Player 2. You can swap the constructors to test your player in the other slot,
or put your player on both sides to check for errors. To compare two different
strategies, set each constructor to the class you want to test.

The engine creates new player objects for each game, so initialize a fresh
board, fleet, and shot history in each player's constructor or `placeShips()`.
Do not rely on state from one game carrying over to the next.
