package dukeTheGame;

import static dukeTheGame.GlobalsAndControl.blackDukePosition;
import static dukeTheGame.GlobalsAndControl.currentPlayer;
import static dukeTheGame.GlobalsAndControl.gameState;
import static dukeTheGame.GlobalsAndControl.whiteDukePosition;
import static dukeTheGame.InputHandler.clickedCell;

import dukeTheGame.Screen.Cell;
import enums.FieldColor;
import enums.GameState;
import enums.MovementPolarity;
import enums.TypesOfUnit;

public class InitialPlayerSetup {

	static boolean wasDukeSet = false;
	static int numberOfKnightsSet = 0;
	
	static void setupPlayer(){		
		if (wasDukeSet == false)
			setDuke();
		else{
			setAndAddKnight();
			if(numberOfKnightsSet == 2 && currentPlayer == FieldColor.WHITE){
				changePlayer();
				wasDukeSet = false;
				numberOfKnightsSet = 0;
				System.out.println("Place the black duke in bottom row");
				}
			else if (numberOfKnightsSet == 2 && currentPlayer == FieldColor.BLACK){
				gameState = GameState.GAME_LOOP;
				changePlayer();
				System.out.println("Yup, it's the game baby! \nChoose unit to move or draw!");
			}
		}
	}
	
	static void setDuke(){
		if(checkDukeSetup()==true){
			setUnit(clickedCell, TypesOfUnit.DUKE);
			wasDukeSet = true;
			System.out.println("White duke set, now place knights by the duke");
		}
		else{
			if(currentPlayer == FieldColor.WHITE)
				System.out.println("Invalid duke placement, must be placed at top row");
			else
				System.out.println("Invalid duke placement, must be bottom row");
		}
	}
	
	private static boolean checkDukeSetup(){
		if((currentPlayer == FieldColor.WHITE && clickedCell.row == 0) ||
			(currentPlayer == FieldColor.BLACK && clickedCell.row == GlobalsAndControl.ROWS - 1)){
			return true;
		}
		else
			return false;
	}
	
	static void setAndAddKnight(){
		if (checkUnitSetup() ==true){
			setUnit(clickedCell, TypesOfUnit.KNIGHT);
			numberOfKnightsSet ++;
		}
	}
	
	private static boolean checkUnitSetup(){
		//two foctors come into play - if the cell is adjacent to duke and if already something is in there
		int rowDifference = currentDukePosition().row - clickedCell.row;
		int colDifference = currentDukePosition().col - clickedCell.col;
		int difference = Math.abs(rowDifference) + Math.abs(colDifference);
		
		if(difference == 1 && clickedCell.unitType == TypesOfUnit.EMPTY)
			return true;
		else{
			System.out.println("Invalid unit placement, must be on free tile adjacent to Duke");
			return false;
		}
	}
	
	static private Cell currentDukePosition(){
		if (currentPlayer == FieldColor.WHITE)
			return (whiteDukePosition);
		else if(currentPlayer == FieldColor.BLACK)
			return (blackDukePosition);
		else{
			System.out.println("Something went wrong with duke position recovery");
			return null;
		}
	}
	
	static void setUnit(Cell setDestination, TypesOfUnit unitType) {
		//there's no checking if the already exists unit on the choosen field
		setDestination.unitType = unitType;
		setDestination.color = currentPlayer;
		setDestination.updateLabel();
		setDestination.movementPolarity = MovementPolarity.WHITE;
		
		if (unitType == TypesOfUnit.DUKE)
			updateDukePosition(setDestination);
	}
	
	private static void updateDukePosition(Cell dukePosition){
		if (currentPlayer == FieldColor.WHITE)
			whiteDukePosition = dukePosition; 
		else
			blackDukePosition = dukePosition;
	}
	
	static void changePlayer(){
		if(currentPlayer == FieldColor.WHITE)
			currentPlayer = FieldColor.BLACK;
		else
			currentPlayer = FieldColor.WHITE;
	}
}
