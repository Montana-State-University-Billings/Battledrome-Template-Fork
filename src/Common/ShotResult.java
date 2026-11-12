package Common;

import java.util.Objects;
import java.util.Optional;

public final class ShotResult {

    private final ShotOutcome outcome;
    private final Coordinate shot;
    private final String shipName; // non-null only when outcome == SUNK

    private ShotResult(ShotOutcome outcome, Coordinate shot, String shipName) {
        this.outcome = Objects.requireNonNull(outcome, "outcome");
        this.shot = Objects.requireNonNull(shot, "shot");
        this.shipName = shipName;
    }

    public static ShotResult miss(Coordinate shot) {
        return new ShotResult(ShotOutcome.MISS, shot, null);
    }

    public static ShotResult hit(Coordinate shot) {
        return new ShotResult(ShotOutcome.HIT, shot, null);
    }

    public static ShotResult sunk(Coordinate shot, String shipName) {
        return new ShotResult(ShotOutcome.SUNK, shot,
                Objects.requireNonNull(shipName, "shipName"));
    }

    public ShotOutcome getOutcome() { 
        return outcome; 
    }

    public Coordinate getShot() { 
        return shot; 
    }

    // empty unless the shot sank a ship
    public Optional<String> getShipName() { return Optional.ofNullable(shipName); }
}

