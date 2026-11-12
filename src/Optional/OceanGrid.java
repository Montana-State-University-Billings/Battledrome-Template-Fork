package Optional;

import java.util.ArrayList;
import java.util.List;

import Common.Coordinate;
import Common.ShotResult;

public class OceanGrid extends Grid {
    private List<Ship> ships = new ArrayList<Ship>();

    public OceanGrid(){
        super();
    }

    public ShotResult receiveShot(Coordinate location){
        Cell cell = cellAtLocation(location);

        // shot lands on empty cell
        if(cell.getState() == CellState.EMPTY || cell.getState() == CellState.MISS){
            cell.setState(CellState.MISS);
            ShotResult result = ShotResult.miss(location);
            return result;
        }

        // shot lands on ship
        if(cell.getState() == CellState.OCCUPIED){
            cell.setState(CellState.HIT);
            cell.getShip().registerHit(location);
            if(cell.getShip().isSunk()){
                ShotResult result = ShotResult.sunk(location, cell.getShip().getName());
                return result;
            } else {
                ShotResult result = ShotResult.hit(location);
                return result;
            }
        }

        // only possibility left is a HIT, which is a duplicate shot
        return ShotResult.hit(location);
    }

    public void placeShips(List<Ship> ships){
        for(Ship ship : ships){
            this.ships.add(ship);
            for(Coordinate location: ship.getCoordinates()){
                cellAtLocation(location).setState(CellState.OCCUPIED);
                cellAtLocation(location).setShip(ship);
            }
        } 
    }

    public boolean shipsAreSunk(){
        for(Ship ship : ships){
            if(!ship.isSunk()){
                return false;
            }
        }
        return true;
    }
}
