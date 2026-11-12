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
        try {
            ShotResult result = ShotResult.miss(new Coordinate(4, 5));
            grid.receiveShotResult(result);
            boolean test = grid.isValidShot(new Coordinate(4, 5));
            assertFalse(test);
        } catch (Exception e) {
            assertFalse(true);
        }  
    }

    @Test 
    void isOKWithUniqueShot(){
        TargetGrid grid = new TargetGrid();
        try {
            ShotResult result = ShotResult.miss(new Coordinate(5, 5));
            grid.receiveShotResult(result);
            boolean test = grid.isValidShot(new Coordinate(4, 5));
            assertTrue(test);
        } catch (Exception e) {
            assertFalse(true);
        }
    }
    
    @Test
    void canPrintEmptyGrid(){
        TargetGrid grid = new TargetGrid();
        grid.print();
    }

    @Test
    void canPrintGridWithMisses(){
        TargetGrid grid = new TargetGrid();
       
        try {
            ShotResult result = ShotResult.miss(new Coordinate(4, 5));
            grid.receiveShotResult(result);
            result = ShotResult.miss(new Coordinate(5, 5));
            grid.receiveShotResult(result);
            result = ShotResult.miss(new Coordinate(6, 5));
            grid.receiveShotResult(result);
        } catch (Exception e) {
            assertFalse(true);
        } 
        grid.print();
    }

    @Test
    void canPrintGridWithHits(){
        TargetGrid grid = new TargetGrid();
        try {
            ShotResult result = ShotResult.hit(new Coordinate(4, 5));
            grid.receiveShotResult(result);
            result = ShotResult.hit(new Coordinate(5, 5));
            grid.receiveShotResult(result);
            result = ShotResult.hit(new Coordinate(6, 5));
            grid.receiveShotResult(result);
        } catch (Exception e) {
            assertFalse(true);
        } 
        grid.print();
    }
}
