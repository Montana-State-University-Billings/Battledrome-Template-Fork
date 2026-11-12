package Optional;

import Common.Coordinate;
import Common.ShotResult;

public class TargetGrid extends Grid {
    
    public TargetGrid(){
        super();
    }

    public void receiveShotResult(ShotResult result){
        Coordinate location = result.getShot();
        Cell affectedCell = cellAtLocation(location);
        switch(result.getOutcome()){
            case MISS:
                affectedCell.setState(CellState.MISS);
                break;
            case HIT:
                affectedCell.setState(CellState.HIT);
                break;
            case SUNK:
                affectedCell.setState(CellState.HIT);
                break;
        }
    }

    public boolean isValidShot(Coordinate location){
        if(cellAtLocation(location).getState() == CellState.EMPTY){
            return true;
        }
        return false;
    }
}
