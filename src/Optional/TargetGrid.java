package Optional;

import Common.Coordinate;
import Common.ShotOutcome;
import Common.ShotResult;

public class TargetGrid extends Grid {
    public TargetGrid(){
        super();
    }

    public void receiveShotResult(ShotResult result){
        Cell relevantCell = cellAtCoordinate(result.getShot());
        if(result.getOutcome() == ShotOutcome.MISS){
            relevantCell.setState(CellState.MISS);
        } else if(result.getOutcome() == ShotOutcome.HIT || result.getOutcome() == ShotOutcome.SUNK){
            relevantCell.setState(CellState.HIT);
        }
    }

    public boolean isValidShot(Coordinate c){
        return cellAtCoordinate(c).getState() == CellState.EMPTY;
    }
}
