import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
        try {
            ship.setCoordinates(Set.of(new Coordinate(4,5), new Coordinate(5,5), new Coordinate(6,5)));
            grid.placeShips(Arrays.asList(ship));
        } catch(Exception e){
            assertFalse(true);
        }
    }

    @Test
    public void canRegisterAHitOnAShip(){
        try {
            ShotResult result = grid.receiveShot(new Coordinate(4, 5));
            assertEquals(ShotOutcome.HIT, result.getOutcome());
        } catch(Exception e){
            assertFalse(true);
        } 
    }

    @Test
    public void canNoteMissOnAShip(){
        try {
            ShotResult result = grid.receiveShot(new Coordinate(2, 1));
            assertEquals(ShotOutcome.MISS, result.getOutcome());
        } catch(Exception e){
            assertFalse(true);
        }
    }

    @Test
    public void canSinkAShip(){
        try {
            ShotResult result = grid.receiveShot(new Coordinate(4, 5));
            result = grid.receiveShot(new Coordinate(5, 5));
            result = grid.receiveShot(new Coordinate(6, 5));
            assertEquals(ShotOutcome.SUNK, result.getOutcome());
            assertEquals("Submarine", result.getShipName());
        } catch(Exception e){
            assertFalse(true);
        }
    }

    @Test
    void canDetectWhenAllShipsAreSunk(){
        ShipFactory sf = new AutomaticShipFactory();
        grid = new OceanGrid();
        
        // put ships on grid
        List<Ship> ships = sf.getShips();
        grid.placeShips(ships);

        // shoot all of their coordinates
        for(Ship ship : ships){
            for(Coordinate c : ship.getCoordinates()){
                grid.receiveShot(c);
            }
        }

        // test for all sunk
        assertTrue(grid.shipsAreSunk());
    }

    @Test
    void canDetectWhenNotAllShipsAreSunk(){
        ShipFactory sf = new AutomaticShipFactory();
        grid = new OceanGrid();
        
        // put ships on grid
        List<Ship> ships = sf.getShips();
        grid.placeShips(ships);

        // don't shoot at any of them

        // test for all sunk
        assertFalse(grid.shipsAreSunk());
    }

    @Test
    public void testPrintOccupied(){
        grid.print();
    }

    @SuppressWarnings("unused")
    @Test
    public void testPrintOccupiedAndHit(){
        try {
            ShotResult result = grid.receiveShot(new Coordinate(4, 5));
            grid.print();
        } catch(Exception e){
            assertFalse(true);
        }
    }

    @SuppressWarnings("unused")
    @Test
    public void testPrintOccupiedAndHitAndMiss(){
        try {
            ShotResult result;
            result = grid.receiveShot(new Coordinate(4, 5));
            result = grid.receiveShot(new Coordinate("C3"));
            grid.print();
        } catch(Exception e){
            assertFalse(true);
        }
    }

    @Test
    public void testPrintOfFiveShips(){
        ShipFactory sf = new AutomaticShipFactory();
        List<Ship> ships = sf.getShips();
        grid = new OceanGrid();
        assertTrue(5 == ships.size());
        grid.placeShips(ships);
        grid.print();
    }
}
