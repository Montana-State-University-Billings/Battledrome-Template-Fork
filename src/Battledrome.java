import java.util.ArrayList;
import java.util.Random;

import Common.Player;
import Common.Coordinate;
import Common.ShotResult;

public class Battledrome {
	private ArrayList<Player> players = new ArrayList<Player>();
	private Random rnd = new Random();
	private int currentPlayerIndex = 0;
	private int player1victories = 0;
	private int player2victories = 0;
	private int player1turns = 0;
	private int player2turns = 0;

	public Battledrome(){

	}

	public void battle(){
		for(int x = 0; x < 1000; x++){
			Player player1 = new Easy.EasyAIPlayer();
			players.add(player1);
			Player player2 = new Rando.RandomAIPlayer();
			players.add(player2);
			currentPlayerIndex = rnd.nextInt(2);
			play();
			players.clear();
		}

		System.out.printf("Final score:\n\tPlayer 1: %d\n\tPlayer 2: %d\n", player1victories, player2victories);
		System.out.printf("Avg Win shots:\n\tPlayer 1: %d\n\tPlayer 2: %d\n", player1turns/(player1victories+1)/2, player2turns/(player2victories+1)/2);
	}

	public void play(){
		for(Player player : players){
			player.placeShips();
		}
		int turnCounter = 0;
		while(true){ // game loop

			// start turn
			Player currentPlayer = players.get(currentPlayerIndex);

			// send shot
			Coordinate shot = currentPlayer.takeShot();

			// return response
			Player otherPlayer;
			if(currentPlayerIndex == 0){
				otherPlayer = players.get(1);
			} else {
				otherPlayer = players.get(0);
			}
			ShotResult result = otherPlayer.receiveShot(shot);
			currentPlayer.receiveShotResult(result);

			// check for end of game
            if(otherPlayer.shipsAreSunk()){
                if(currentPlayerIndex == 0){
					player1victories += 1;
					player1turns += turnCounter;
					//System.out.println("- P1 Victory");
				} else {
					player2victories += 1;
					player2turns += turnCounter;
					//System.out.println("- P2 Victory");
				}
				turnCounter = 0;
                break;
            }

			// swap turns
			if(currentPlayerIndex == 0){
				currentPlayerIndex = 1;
			} else {
				currentPlayerIndex = 0;
			}
			turnCounter++;
		}
	}
}
