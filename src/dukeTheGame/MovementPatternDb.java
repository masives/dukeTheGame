package dukeTheGame;

import static dukeTheGame.GameLoopHandler.selectedCell;

public class MovementPatternDb {
	static int[] emptyPattern = null;
	static int[][] knight ={ 
			{-1,0,  0,-1,  0,1,  1,0},
			{-2,0,  -1,-1,  -1,1,  1,-1,  1,1}
			};
	
	static public int[] getMovementPattern(){
		switch(selectedCell.unitType){
		case KNIGHT:
			return knight[selectedCell.movementPolarity.getValue()];
		default:
			System.out.println("couldn't find movement pattern");
			return emptyPattern;
		}
	}
}