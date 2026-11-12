package Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Common.Coordinate;
import Common.Direction;
import Common.InvalidCoordinateException;

import java.util.HashSet;

public abstract class ShipFactory {
    
    public final List<Ship> getShips(){

        // local variables - every time you call this method, you will get five new ships
        List<Ship> ships = new ArrayList<>();
        Ship.specifications.forEach((k,v) -> ships.add(new Ship(k, v)));
        
        Set<Coordinate> occupied = new HashSet<>();
        for(Ship ship : ships){
            int attempts = 0;
            while(true){
                if(++attempts > maxPlacementAttempts()){
                    throw new IllegalStateException(
                        String.format("Unable to place %s after %d attempts.", ship.getName(), maxPlacementAttempts()));
                }
                try {
                    Coordinate start = getStartCoordinate(ship);
                    Direction dir = getStartDirection(ship);
                    List<Coordinate> coords = start.coordsInDirection(ship.getLength(), dir);
                    
                    if(coordinatesOverlapExistingCoordinates(coords, occupied)){
                        placementRejected(ship, "would overlap another ship; please try again.");
                        continue; 
                    }

                    // on grid and not overlapped - this is success
                    occupied.addAll(coords);
                    ship.setCoordinates(coords);
                    placementAccepted(ship);
                    break;
                } catch (InvalidCoordinateException e){
                    placementRejected(ship, "would run off the grid; please try again.");
                }   
            }
        }
        return ships;
    }

    protected abstract Coordinate getStartCoordinate(Ship ship);

    protected abstract Direction getStartDirection(Ship ship);

    protected boolean coordinatesOverlapExistingCoordinates(List<Coordinate> coords, Set<Coordinate> occupied){
        for(Coordinate c: coords){
            if(occupied.contains(c)){
                return true;
            }
        }
        
        return false;
    }

    // Maximum placement attempts per ship.  Unlimited by default (a person may take many tries);
    // automatic placement overrides this so a bug fails loudly instead of hanging.
    protected int maxPlacementAttempts() {
        return Integer.MAX_VALUE;
    }

    protected void placementRejected(Ship ship, String reason) {
        // empty implementation so automatic placement can just proceed.  Overridden in manual placement to present correct error message.
    }

    protected void placementAccepted(Ship ship){
        // again: empty implementation since I don't want the automatic
        // placement to do anything.  During manual placement, I want this hook
        // so I can put the ship on an ocean grid and display it.
    }
}
