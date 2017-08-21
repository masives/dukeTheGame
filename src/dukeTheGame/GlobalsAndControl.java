package dukeTheGame;

import dukeTheGame.Screen.Cell;
import enums.FieldColor;
import enums.GameState;

public class GlobalsAndControl {
	public static final int ROWS = 6;
	public static final int COL = 6;
	
	public static GameState gameState = GameState.PLAYER_SETUP;
	public static FieldColor currentPlayer = FieldColor.WHITE;
	
	static Cell whiteDukePosition;//not used atm
	static Cell blackDukePosition;
	
	static void updateDukePosition(Cell dukeCell){
		if (GlobalsAndControl.currentPlayer == FieldColor.WHITE){
			whiteDukePosition.row = dukeCell.row;
			whiteDukePosition.col = dukeCell.col;
		}
		else{
			blackDukePosition.row = dukeCell.row;
			blackDukePosition.col = dukeCell.col;
		}
	}
	public static void resetGlobals(){
		gameState = GameState.PLAYER_SETUP;
		currentPlayer = FieldColor.WHITE;
		whiteDukePosition = null;
		blackDukePosition = null;
	}
}
