package Optional;

import java.util.ArrayList;
import java.util.List;

import Common.Coordinate;
import Common.ShotResult;

public class OceanGrid extends Grid {
    private List<Ship> ships = new ArrayList<>();

    public OceanGrid(){
        super();
    }

    public void assignShips(List<Ship> ships){
        for (Ship ship : ships) {
            this.ships.add(ship);
            for (Coordinate c : ship.getCoordinates()) {
                Cell cell = cellAtCoordinate(c);
                cell.setState(CellState.OCCUPIED);
                cell.setShip(ship);
            }
        }
    }

    public ShotResult receiveShot(Coordinate c){
        Cell cell = cellAtCoordinate(c);
        ShotResult retVal;
        if(cell.getState() == CellState.OCCUPIED){
            // A HIT or SUNK
            Ship ship = cell.getShip();
            ship.registerHitAt(c);
            cell.setState(CellState.HIT);
            if(ship.isSunk()){
                retVal = ShotResult.sunk(c, ship.getName());
            } else {
                retVal = ShotResult.hit(c);
            }
            return retVal;
        }

        // anything else is a MISS
        cell.setState(CellState.MISS);
        retVal = ShotResult.miss(c);
        return retVal;
    }
}
