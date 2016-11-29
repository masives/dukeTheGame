package dukeTheGame;

import dukeTheGame.Screen.Cell;
import enums.FieldColor;
import enums.GameState;

public class GlobalsAndControl {
	public static final int ROWS = 4;
	public static final int COL = 4;
	
	public static GameState gameState = GameState.whiteSetup;
	public static FieldColor currentPlayer = FieldColor.WHITE;
	
	static Cell whiteDukePosition;
	static Cell blackDukePosition;
	
	/*class DukePosition{
		int row;
		int col;
	}*/
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
}
