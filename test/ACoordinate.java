import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import Common.Coordinate;
import Common.Direction;

public class ACoordinate {
    
    @Test
    void canBeCreatedAtA1() throws Exception {
        Coordinate Coordinate = new Coordinate("A1");
        assertEquals(0, Coordinate.getRow());
        assertEquals(0, Coordinate.getColumn());
    }

    @Test
    void canBeCreatedAtA7() throws Exception {
        Coordinate Coordinate = new Coordinate("A7");
        assertEquals(0, Coordinate.getRow());
        assertEquals(6, Coordinate.getColumn());
    }

    @Test
    void canBeCreatedAtC4() throws Exception {
        Coordinate Coordinate = new Coordinate("C4");
        assertEquals(2, Coordinate.getRow());
        assertEquals(3, Coordinate.getColumn());
    }

    @Test
    void canBeCreatedAtD9() throws Exception {
        Coordinate Coordinate = new Coordinate("D9");
        assertEquals(3, Coordinate.getRow());
        assertEquals(8, Coordinate.getColumn());  
    }

    @Test
    void canBeCreatedLowerCase() throws Exception {
        Coordinate Coordinate = new Coordinate("c4");
        assertEquals(2, Coordinate.getRow());
        assertEquals(3, Coordinate.getColumn());
    }

    @Test
    void cannotBeCreatedWithInvalidRow(){
        assertThrows(Exception.class, () -> {
            @SuppressWarnings("unused")
            Coordinate Coordinate = new Coordinate("Z1");
        });
    }

    @Test
    void cannotBeCreatedWithInvalidColumn(){
        assertThrows(Exception.class, () -> {
            @SuppressWarnings("unused")
            Coordinate Coordinate = new Coordinate("A11");
        });
    }

    @Test
    void cannotBeCreatedWithCrazyInput(){
        assertThrows(Exception.class, () -> {
            @SuppressWarnings("unused")
            Coordinate Coordinate = new Coordinate("BadString");
        });
    }

    @Test
    void canBeCreatedWithRowColumn() throws Exception{
        Coordinate Coordinate = new Coordinate(1, 1);
        assertEquals("B2", Coordinate.toString());
    }

    @Test
    void isNotEqualToADifferentInstance() throws Exception{
        Coordinate c1 = new Coordinate("b7");
        Coordinate c2 = new Coordinate("b7");
        assertFalse(c1 == c2);
    }

    @Test
    void isEqualUsingEqualsAtSameRowColumn() throws Exception {
        Coordinate c1 = new Coordinate("b7");
        Coordinate c2 = new Coordinate("b7");
        assertTrue(c1.equals(c2));
    }

    @Test
    void hasSameCoordsGivesSameHashCode() throws Exception{
        Coordinate c1 = new Coordinate("b7");
        Coordinate c2 = new Coordinate("b7");
        assertTrue(c1.hashCode() == c2.hashCode());
    }

    @Test
    void generatesFourCoordinatesToSouth() throws Exception {
        Coordinate testStart = new Coordinate(2, 3);
        List<Coordinate> result = testStart.coordsInDirection(4, Direction.SOUTH);

        assertEquals(4, result.size());
        assertTrue(result.contains(new Coordinate(2, 3)));
        assertTrue(result.contains(new Coordinate(3, 3)));
        assertTrue(result.contains(new Coordinate(4, 3)));
        assertTrue(result.contains(new Coordinate(5, 3)));
    }

    @Test
    void generatesThreeCoordinatesToWest() throws Exception {
        Coordinate testStart = new Coordinate(2, 3);
        List<Coordinate> result = testStart.coordsInDirection(3, Direction.WEST);

        assertEquals(3, result.size());
        assertTrue(result.contains(new Coordinate(2, 3)));
        assertTrue(result.contains(new Coordinate(2, 2)));
        assertTrue(result.contains(new Coordinate(2, 1)));
    }

    @Test
    void generatesTwoCoordinatesToNorth() throws Exception {
        Coordinate testStart = new Coordinate(2, 3);
        List<Coordinate> result = testStart.coordsInDirection(2, Direction.NORTH);

        assertEquals(2, result.size());
        assertTrue(result.contains(new Coordinate(2, 3)));
        assertTrue(result.contains(new Coordinate(1, 3)));
    }

    @Test
    void generatesFiveCoordinatesToEast() throws Exception {
        Coordinate testStart = new Coordinate(2, 3);
        List<Coordinate> result = testStart.coordsInDirection(5, Direction.EAST);

        assertEquals(5, result.size());
        assertTrue(result.contains(new Coordinate(2, 3)));
        assertTrue(result.contains(new Coordinate(2, 4)));
        assertTrue(result.contains(new Coordinate(2, 5)));
        assertTrue(result.contains(new Coordinate(2, 6)));
        assertTrue(result.contains(new Coordinate(2, 7)));
    }

    @Test
    void throwsWithShipOffToLeft() {
        assertThrows(Exception.class, () -> {
            Coordinate start = new Coordinate(2,3);
            @SuppressWarnings("unused")
            List<Coordinate> result = start.coordsInDirection(5, Direction.WEST);
        });
    }

    @Test
    void throwsWithShipOffToTop() {
        assertThrows(Exception.class, () -> {
            Coordinate start = new Coordinate(2,3);
            @SuppressWarnings("unused")
            List<Coordinate> result = start.coordsInDirection(5, Direction.NORTH);
        });
    }

    @Test
    void throwsWithShipOffToRight() {
        assertThrows(Exception.class, () -> {
            Coordinate start = new Coordinate(3,8);
            @SuppressWarnings("unused")
            List<Coordinate> result = start.coordsInDirection(5, Direction.EAST);
        });
    }

    @Test
    void throwsWithShipOffToBottom() {
        assertThrows(Exception.class, () -> {
            Coordinate start = new Coordinate(8,8);
            @SuppressWarnings("unused")
            List<Coordinate> result = start.coordsInDirection(5, Direction.SOUTH);
        });
    }
}
