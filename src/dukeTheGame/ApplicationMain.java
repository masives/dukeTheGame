package dukeTheGame;

import static dukeTheGame.GlobalsAndControl.gameState;

import enums.FieldColor;
import enums.GameState;
import enums.TypesOfUnit;

public class ApplicationMain {

	public static void main(String[] args) {
		
		Screen window = new Screen();
		window.frame.setVisible(true);
				
		InputHandler inputHandler = new InputHandler();
		inputHandler.addMouseListenersToBoard(window.cells);
		MovementPatternDb.initiateMovementMaps();
		
		//System.out.println("White player please choose a place for duke");
		
		//initial setup scenario for testing
		GlobalsAndControl.currentPlayer = FieldColor.WHITE;
		InitialPlayerSetup.setUnit(Screen.cells[0][3], TypesOfUnit.DUKE);
		InitialPlayerSetup.setUnit(Screen.cells[0][2], TypesOfUnit.KNIGHT);
		InitialPlayerSetup.setUnit(Screen.cells[0][4], TypesOfUnit.KNIGHT);
		
		GlobalsAndControl.currentPlayer = FieldColor.BLACK;
		InitialPlayerSetup.setUnit(Screen.cells[5][3], TypesOfUnit.DUKE);
		InitialPlayerSetup.setUnit(Screen.cells[5][2], TypesOfUnit.KNIGHT);
		InitialPlayerSetup.setUnit(Screen.cells[5][4], TypesOfUnit.KNIGHT);
		
		GlobalsAndControl.gameState = GameState.GAME_LOOP;
		Screen.setPanelColorsToDefault();
		//end of initial setup scenario for testing
		
	}
}