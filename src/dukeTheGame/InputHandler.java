package dukeTheGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static dukeTheGame.GlobalsAndControl.ROWS;
import static dukeTheGame.GlobalsAndControl.COL;
import static dukeTheGame.GlobalsAndControl.gameState;
import static dukeTheGame.GlobalsAndControl.currentPlayer;

import dukeTheGame.Screen.Cell;
import enums.FieldColor;
import enums.GameState;
import enums.TypesOfUnit;

public class InputHandler {
	//initial value of state in game is when the white is about to show a duke
	
	void addMouseListenersToBoard(Cell[][] cells){
		for(int i = 0; i < ROWS; i++){
			for (int j = 0; j < COL; j++){
				addMouseListener(cells[i][j]);
			}
		}
	}
	
	private void addMouseListener(final Cell cell){
		cell.panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent panelClicked) {
				handleInput(cell);			 
			}
		});
	}
	
	static void handleInput(Cell cell){
		switch(gameState){
		case whiteSetup:
			setupPlayer(cell);
			break;
		default:
			System.out.println("something went wrong");
		}
		//temporary helping commands, will get rid of them once ecerything works properly
		System.out.println("Clicked cell: " + cell.label.getText());
		System.out.println("");

		System.out.println("Current player:" + currentPlayer);
		System.out.println("Current game state:" + gameState);
	}
	
	static boolean wasDukeSet = false;
	static int numberOfKnightsSet = 0;	
	static void setupPlayer(Cell clickedCell){		
		if (wasDukeSet == false){
			setUnit(clickedCell, TypesOfUnit.DUKE);
			wasDukeSet = true;
		}
		else if (numberOfKnightsSet != 2){
			setUnit(clickedCell, TypesOfUnit.KNIGHT);
			numberOfKnightsSet ++;
		}
		else
			gameState = GameState.blackSetup;
	}
	
	//draw function will be usefull later in game loop when units are drawn
	void DrawUnit(Cell unitDestination, TypesOfUnit unitType){
		Cell currentDuke = whichDukeColor();
		if (isDrawValid() == true)
			setUnit(unitDestination, unitType);
		else
			System.out.println("Invalid unit placement, must adjacent to duke");
	}
	
	//most likely won't work and will be rendered unneeded
	private Cell whichDukeColor(){
		if (GlobalsAndControl.currentPlayer == FieldColor.WHITE)
			return GlobalsAndControl.whiteDukePosition;
		else
			return GlobalsAndControl.blackDukePosition;
	}
	//function aiming at checking if the clicked cell is adjacent to duke, currently no way of invoking it
	private boolean isDrawValid(){
		
		return false;
	}
	
	static void setUnit(Cell dukeDestination, TypesOfUnit unitType) {
		dukeDestination.unitType = unitType;
		dukeDestination.color = GlobalsAndControl.currentPlayer;
		dukeDestination.updateLabel();
		
		if (unitType == TypesOfUnit.DUKE)
			updateDukePosition(dukeDestination);
		
		}
	private static void updateDukePosition(Cell dukeDestination){
		if (GlobalsAndControl.currentPlayer == FieldColor.WHITE){
			GlobalsAndControl.whiteDukePosition = dukeDestination; 
		}
		else{
			GlobalsAndControl.blackDukePosition = dukeDestination;
		}
	}
}