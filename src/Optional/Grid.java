package Optional;

import Common.Coordinate;

public abstract class Grid {
    private Cell[][] cells = new Cell[10][10];

    public Grid(){
        for(int row = 0; row < 10; row++){
            for(int column = 0; column < 10; column++){
                Cell cell = new Cell();
                cells[row][column] = cell;
            }
        }
    }
    
    protected Cell cellAtCoordinate(Coordinate c){
        return cells[c.getRow()][c.getColumn()];
    }

    public String toString(){
        // print out console representation of the grid
        String result = new String();
        result += getHeader();

        for(int row = 0; row < 10; row++){
            result += getRow(row);
        }
        return result;
    }

    private String getRow(int rowNumber){
		char letter = (char)(rowNumber + 65);
        String result = "| " + letter + " |";
        for(int column = 0; column < 10; column++){
            switch(cells[rowNumber][column].getState()){
                case EMPTY -> result += "   .";
                case HIT -> result += " * .";
                case MISS -> result += " o .";
                case OCCUPIED -> result += " # .";
            }
        }
        result += "\n";
        return result;
    }

    private String getHeader(){
        String result = getBorder();
        // first column
        result += "|   |";
        for(int column = 1; column < 11; column++){
			result += String.format("%2d", column);
			result += " |";
        }
        result += "\n";
        result += getBorder();
        return result;
    }

    private String getBorder(){
        // blank column
        String result = "+---+";
        // letter columns
        for(int column = 0; column < 10; column++){
            result += "---+";
        }
        result += "\n";
        return result;
    }
}
