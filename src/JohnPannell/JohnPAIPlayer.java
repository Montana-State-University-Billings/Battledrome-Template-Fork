package JohnPannell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Common.Player;
import Common.Coordinate;
import Common.ShotResult;
import Optional.AutomaticShipFactory;
import Optional.OceanGrid;
import Optional.Ship;

public class JohnPAIPlayer implements Player {

	private List<Coordinate> shotsToTake = new ArrayList<>();
	private OceanGrid oceanGrid = new OceanGrid();
	private List<Ship> ships = new ArrayList<>();
	private AutomaticShipFactory factory = new AutomaticShipFactory();

	public JohnPAIPlayer(){
        // populate shots to take
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){
				if((x+y)%2 == 0){
					try{
						shotsToTake.add(new Coordinate(x,y));
					} catch (Exception e){
						// ignore - shouldn't happen
					}
				}
            }
        }
		Collections.shuffle(shotsToTake);
		for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){
				if((x+y)%2 == 1){
					try{
						shotsToTake.add(new Coordinate(x,y));
					} catch (Exception e){
						// ignore - shouldn't happen
					}
				}
            }
        }   
	}

	@Override
    public void placeShips() {
		this.ships = factory.getShips();
        oceanGrid.assignShips(this.ships);
    }

    @Override
    public Coordinate takeShot() {
        Coordinate newShot = shotsToTake.remove(0);
		return newShot;    
    }

    @Override
    public ShotResult receiveShot(Coordinate shot) {
        return oceanGrid.receiveShot(shot);
    }

	@Override
	public String getName() {
		return "CheckerBot 2.0";
	}

	@Override
	public void receiveShotResult(ShotResult result) {
		// This AI doesn't care how the shots go... not that smart!
	}

	@Override
	public boolean shipsAreSunk() {
		for(Ship ship : ships){
            if(!ship.isSunk()){
                return false;
            }
        }
        return true;
	}
}

