import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Common.Coordinate;
import Optional.AutomaticShipFactory;
import Optional.Ship;
import Optional.ShipFactory;

public class AShipFactory {

    ShipFactory factory;

    @BeforeEach
    void init(){
        factory = new AutomaticShipFactory();
    }

    @Test
    void producesFiveShips(){
        List<Ship> ships = factory.getShips();
        assertEquals(5, ships.size());
        assertEquals(Ship.specifications, ships.stream().collect(
            Collectors.toMap(Ship::getName, Ship::getLength)));
    }

    @Test
    void detectsOverlap(){
        List<Coordinate> coordinates = List.of(
            new Coordinate(2,3),
            new Coordinate(2,4),
            new Coordinate(2,5)
        );
        Set<Coordinate> occupied = Set.of(
            new Coordinate(1,3),
            new Coordinate(2,3),
            new Coordinate(3,3),
            new Coordinate(4,3)
        );
        assertTrue(overlap(coordinates, occupied));
    }

    @Test
    void detectsLackOfOverlap(){
        List<Coordinate> coordinates = List.of(
            new Coordinate(2,3),
            new Coordinate(2,4),
            new Coordinate(2,5)
        );
        Set<Coordinate> occupied = Set.of(
            new Coordinate(5,3),
            new Coordinate(5,4),
            new Coordinate(5,5),
            new Coordinate(5,6)
        );
        assertFalse(overlap(coordinates, occupied));
    }

    @Test
    void placesEveryShipWithoutOverlap(){
        List<Ship> ships = factory.getShips();
        Set<Coordinate> occupied = new HashSet<>();

        for(Ship ship : ships){
            assertEquals(ship.getLength(), ship.getCoordinates().size());
            for(Coordinate coordinate : ship.getCoordinates()){
                assertTrue(occupied.add(coordinate), "Ships must not overlap at " + coordinate);
            }
        }
    }

    @Test
    void createsFreshShipsForEachFleet(){
        List<Ship> firstFleet = factory.getShips();
        List<Ship> secondFleet = factory.getShips();

        assertEquals(firstFleet.size(), secondFleet.size());
        for(int i = 0; i < firstFleet.size(); i++){
            assertNotSame(firstFleet.get(i), secondFleet.get(i));
        }
    }

    private boolean overlap(List<Coordinate> coordinates, Set<Coordinate> occupied){
        return new TestableShipFactory().overlaps(coordinates, occupied);
    }

    private static class TestableShipFactory extends AutomaticShipFactory {
        boolean overlaps(List<Coordinate> coordinates, Set<Coordinate> occupied){
            return coordinatesOverlapExistingCoordinates(coordinates, occupied);
        }
    }
}
