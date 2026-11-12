package Common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Coordinate {

    // SRP: A Coordinate represents a position on a Battleship grid.

    private static final int GRID_SIZE = 10;
    private static final Random RANDOM = new Random();

    private final int row;
    private final int column;

    /*
        Internally, a coordinate keeps track of position with two 
        zero-based values for row and column.  Both values can 
        range from 0-9.

        Externally ("human values"), rows are represented by the 
        letters A-J, and columns are represented by the numbers 1-10
    */

    /**
     * @throws InvalidCoordinateException if the text is not a letter A-J
     *         followed by a number 1-10 (e.g. "B7", case-insensitive)
     */
    public Coordinate(String position) {
        if (position == null) {
            throw new InvalidCoordinateException("Coordinate is required.");
        }
        String normalizedPosition = position.strip().toLowerCase();
        // Strict format: one letter, then 1-10 with no leading zeros or extra characters
        if (!normalizedPosition.matches("[a-j](10|[1-9])")) {
            throw new InvalidCoordinateException(
                "Coordinate must be a letter A-J followed by a number 1-10: \"" + position + "\"");
        }
        this.row = rowForHumanValue(normalizedPosition);
        this.column = columnForHumanValue(normalizedPosition);
    }

    /**
     * @throws InvalidCoordinateException if row or column is outside 0-9
     */
    public Coordinate(int row, int column) {
        if (row < 0 || row >= GRID_SIZE) {
            throw new InvalidCoordinateException("Invalid row for coordinate: " + row);
        }
        if (column < 0 || column >= GRID_SIZE) {
            throw new InvalidCoordinateException("Invalid column for coordinate: " + column);
        }
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return Character.toString((char) ('A' + row)) + (column + 1);
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        Coordinate c = (Coordinate) o;
        // field comparison
        return row == c.row && column == c.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    // The format was already validated by the constructor's regex
    private static int rowForHumanValue(String input) {
        return input.charAt(0) - 'a';
    }

    private static int columnForHumanValue(String input) {
        return Integer.parseInt(input.substring(1)) - 1; // switch from 1-index to 0-index
    }

    /**
     * @throws InvalidCoordinateException if any resulting coordinate is off the grid
     */
    public List<Coordinate> coordsInDirection(int length, Direction direction) {
        Objects.requireNonNull(direction, "direction");
        List<Coordinate> retVal = new ArrayList<>();
        retVal.add(this);
        for (int i = 1; i < length; i++) {
            int nextRow = retVal.get(i - 1).getRow();
            int nextCol = retVal.get(i - 1).getColumn();
            switch (direction) {
                case NORTH -> nextRow -= 1;
                case SOUTH -> nextRow += 1;
                case EAST -> nextCol += 1;
                case WEST -> nextCol -= 1;
            }
            retVal.add(new Coordinate(nextRow, nextCol));
        }
        return retVal;
    }

    public static Coordinate randomCoordinate() {
        return new Coordinate(RANDOM.nextInt(GRID_SIZE), RANDOM.nextInt(GRID_SIZE));
    }
}
