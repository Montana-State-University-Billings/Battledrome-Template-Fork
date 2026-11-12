import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import Common.Coordinate;
import Common.ShotResult;
import Optional.TargetGrid;

public class ATargetGrid {

    @Test
    void willNotSanctionADuplicateShot(){
        TargetGrid grid = new TargetGrid();
        ShotResult result = ShotResult.miss(new Coordinate(4, 5));
        grid.receiveShotResult(result);
        assertFalse(grid.isValidShot(new Coordinate(4, 5)));
    }

    @Test 
    void isOKWithUniqueShot(){
        TargetGrid grid = new TargetGrid();
        ShotResult result = ShotResult.miss(new Coordinate(5, 5));
        grid.receiveShotResult(result);
        assertTrue(grid.isValidShot(new Coordinate(4, 5)));
    }
    
    @Test
    void canPrintEmptyGrid(){
        TargetGrid grid = new TargetGrid();
        assertTrue(grid.toString().contains("   ."));
    }

    @Test
    void canPrintGridWithMisses(){
        TargetGrid grid = new TargetGrid();
        grid.receiveShotResult(ShotResult.miss(new Coordinate(4, 5)));
        grid.receiveShotResult(ShotResult.miss(new Coordinate(5, 5)));
        grid.receiveShotResult(ShotResult.miss(new Coordinate(6, 5)));
        assertTrue(grid.toString().contains(" o ."));
    }

    @Test
    void canPrintGridWithHits(){
        TargetGrid grid = new TargetGrid();
        grid.receiveShotResult(ShotResult.hit(new Coordinate(4, 5)));
        grid.receiveShotResult(ShotResult.hit(new Coordinate(5, 5)));
        grid.receiveShotResult(ShotResult.sunk(new Coordinate(6, 5), "Submarine"));
        assertTrue(grid.toString().contains(" * ."));
    }
}
