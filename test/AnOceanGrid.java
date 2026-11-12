import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Common.Coordinate;
import Common.ShotOutcome;
import Common.ShotResult;
import Optional.AutomaticShipFactory;
import Optional.OceanGrid;
import Optional.Ship;
import Optional.ShipFactory;

public class AnOceanGrid {
    OceanGrid grid;

    @BeforeEach
    public void init(){
        this.grid = new OceanGrid();
        Ship ship = new Ship("Submarine", 3);
        ship.setCoordinates(List.of(new Coordinate(4,5), new Coordinate(5,5), new Coordinate(6,5)));
        grid.assignShips(List.of(ship));
    }

    @Test
    public void canRegisterAHitOnAShip(){
        ShotResult result = grid.receiveShot(new Coordinate(4, 5));
        assertEquals(ShotOutcome.HIT, result.getOutcome());
    }

    @Test
    public void canNoteMissOnAShip(){
        ShotResult result = grid.receiveShot(new Coordinate(2, 1));
        assertEquals(ShotOutcome.MISS, result.getOutcome());
    }

    @Test
    public void canSinkAShip(){
        grid.receiveShot(new Coordinate(4, 5));
        grid.receiveShot(new Coordinate(5, 5));
        ShotResult result = grid.receiveShot(new Coordinate(6, 5));
        assertEquals(ShotOutcome.SUNK, result.getOutcome());
        assertEquals("Submarine", result.getShipName().orElseThrow());
    }

    @Test
    void canDetectWhenAllShipsAreSunk(){
        ShipFactory sf = new AutomaticShipFactory();
        grid = new OceanGrid();
        
        // put ships on grid
        List<Ship> ships = sf.getShips();
        grid.assignShips(ships);

        // shoot all of their coordinates
        for(Ship ship : ships){
            for(Coordinate c : ship.getCoordinates()){
                grid.receiveShot(c);
            }
        }

        // test for all sunk
        assertTrue(ships.stream().allMatch(Ship::isSunk));
    }

    @Test
    void canDetectWhenNotAllShipsAreSunk(){
        ShipFactory sf = new AutomaticShipFactory();
        grid = new OceanGrid();
        
        // put ships on grid
        List<Ship> ships = sf.getShips();
        grid.assignShips(ships);

        // don't shoot at any of them

        // test for all sunk
        assertFalse(ships.stream().allMatch(Ship::isSunk));
    }

    @Test
    public void testPrintOccupied(){
        System.out.println(grid);
    }

    @SuppressWarnings("unused")
    @Test
    public void testPrintOccupiedAndHit(){
        grid.receiveShot(new Coordinate(4, 5));
        System.out.println(grid);
    }

    @SuppressWarnings("unused")
    @Test
    public void testPrintOccupiedAndHitAndMiss(){
        grid.receiveShot(new Coordinate(4, 5));
        grid.receiveShot(new Coordinate("C3"));
        System.out.println(grid);
    }

    @Test
    public void testPrintOfFiveShips(){
        ShipFactory sf = new AutomaticShipFactory();
        List<Ship> ships = sf.getShips();
        grid = new OceanGrid();
        assertTrue(5 == ships.size());
        grid.assignShips(ships);
        System.out.println(grid);
    }
}
