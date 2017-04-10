package dukeTheGame;

import static dukeTheGame.GameLoopHandler.targetCell;

import java.util.HashMap;
import java.util.Map;

public class MovementPatternDb {
	static int[][] knight ={ 
			{-1,0,  0,-1,  0,1,  1,0},
			{-2,0,  -1,-1,  -1,1,  1,-1,  1,1}
			};
	
	static Map<String, int[][]> movementPatterns = new HashMap<>();
	
	static public void initiateMovementMaps(){
		movementPatterns.put("KNIGHT", knight);
	}
	
	static public int[] getMovementPattern(){
		int[] pattern = movementPatterns.get(targetCell.unitType.name())[targetCell.movementPolarity.getValue()];
		//TODO: reverse pattern function is in movement handler ATM
		return pattern;	
	}
}