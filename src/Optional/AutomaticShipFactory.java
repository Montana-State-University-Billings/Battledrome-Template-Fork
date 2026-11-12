package Optional;

import Common.Coordinate;
import Common.Direction;

public class AutomaticShipFactory extends ShipFactory {

    private static final int MAX_ATTEMPTS_PER_SHIP = 1000;

    @Override
    protected int maxPlacementAttempts() {
        return MAX_ATTEMPTS_PER_SHIP;
    }

    @Override
    protected Coordinate getStartCoordinate(Ship ship) {
        return Coordinate.randomCoordinate();
    }

    @Override
    protected Direction getStartDirection(Ship ship) {
        return Direction.randomDirection();
    }
}
