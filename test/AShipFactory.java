import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Common.Coordinate;
import Optional.AutomaticShipFactory;
import Optional.Order;
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
    }

    @Test
    void detectsOverlap() throws Exception {
        Ship one = new Ship("Submarine", 3);
        one.setCoordinates(Set.of(
            new Coordinate(2,3),
            new Coordinate(2,4),
            new Coordinate(2,5)
        ));
        Ship two = new Ship("Destroyer", 4);
        two.setCoordinates(Set.of(
            new Coordinate(1,3),
            new Coordinate(2,3),
            new Coordinate(3,3),
            new Coordinate(4,3)
        ));
        assertTrue(factory.shipOverlapsExistingShip(one, List.of(two)));
    }

    @Test
    void detectsLackOfOverlap() throws Exception {
        Ship one = new Ship("Submarine", 3);
        one.setCoordinates(Set.of(
            new Coordinate(2,3),
            new Coordinate(2,4),
            new Coordinate(2,5)
        ));
        Ship two = new Ship("Destroyer", 4);
        two.setCoordinates(Set.of(
            new Coordinate(5,3),
            new Coordinate(5,4),
            new Coordinate(5,5),
            new Coordinate(5,6)
        ));
        assertFalse(factory.shipOverlapsExistingShip(one, List.of(two)));
    }

    @Test
    void returnsShipsOrderedByName(){
        List<Ship> ships = factory.getShipsSortedByName(Order.ASC);
        assertEquals(5, ships.size());
        assertEquals(ships.get(0).getName(), "Battleship");
        assertEquals(ships.get(1).getName(), "Carrier");
        assertEquals(ships.get(2).getName(), "Destroyer");
        assertEquals(ships.get(3).getName(), "Patrol Boat");
        assertEquals(ships.get(4).getName(), "Submarine");
    }

    @Test
    void returnsShipsOrderedByNameDesc(){
        List<Ship> ships = factory.getShipsSortedByName(Order.DESC);
        assertEquals(5, ships.size());
        assertEquals(ships.get(0).getName(), "Submarine");
        assertEquals(ships.get(1).getName(), "Patrol Boat");
        assertEquals(ships.get(2).getName(), "Destroyer");
        assertEquals(ships.get(3).getName(), "Carrier");
        assertEquals(ships.get(4).getName(), "Battleship");
    }

    @Test
    void returnsShipsOrderedByLength(){
        List<Ship> ships = factory.getShipsSortedByLength(Order.ASC);
        assertEquals(5, ships.size());
        assertEquals(ships.get(0).getLength(), 2);
        assertEquals(ships.get(1).getLength(), 3);
        assertEquals(ships.get(2).getLength(), 3);
        assertEquals(ships.get(3).getLength(), 4);
        assertEquals(ships.get(4).getLength(), 5);
    }

    @Test
    void returnsShipsOrderedByLengthDesc(){
        List<Ship> ships = factory.getShipsSortedByLength(Order.DESC);
        assertEquals(5, ships.size());
        assertEquals(ships.get(0).getLength(), 5);
        assertEquals(ships.get(1).getLength(), 4);
        assertEquals(ships.get(2).getLength(), 3);
        assertEquals(ships.get(3).getLength(), 3);
        assertEquals(ships.get(4).getLength(), 2);
    }
}
