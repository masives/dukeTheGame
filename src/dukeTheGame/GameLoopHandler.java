package dukeTheGame;

import static dukeTheGame.GlobalsAndControl.COL;
import static dukeTheGame.GlobalsAndControl.ROWS;
import static dukeTheGame.GlobalsAndControl.currentPlayer;
import static dukeTheGame.InputHandler.clickedCell;
import static dukeTheGame.MovementHandler.revealPosibleMovement;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


import dukeTheGame.Screen.Cell;
import enums.FieldColor;
import enums.MovementPolarity;
import enums.TypesOfUnit;

public class GameLoopHandler {
	static Cell selectedCell = null;
	static boolean drawButtonClicked = false;
	
	static List<Cell> posibleMovementCells = new ArrayList<Cell>();
	static Cell cellToBeMoved;
	
	public static void gameLoopHandler(){
		if (drawButtonClicked==true){
			System.out.println("Where do you want to put the unit?");
		}
		else if (selectedCell != null){
			if(checkMovement()){
				moveUnit();
				changePlayer();
				setPanelColorsToDefault();
				posibleMovementCells.clear();
				selectedCell = null;
			}
			else{
				//function for wiping the current selection
				System.out.println("It's not movable");
				selectedCell = null;
				setPanelColorsToDefault();
			}
		}
		else if (clickedCell.unitType!=null && clickedCell.color==currentPlayer){
			selectedCell = clickedCell;
			revealPosibleMovement();
			for(Cell cell: posibleMovementCells){
				cell.panel.setBackground(Color.RED);
			}
			System.out.println("Unit selected, please choose your destination");
		}
		//also if the draw is picked there should be a function for drawing
		else
		System.out.println("Move your unit by clicking it or draw unit");
	}
	
	private static boolean checkMovement(){
		for(Cell targetCell:posibleMovementCells){
			if(clickedCell==targetCell){
				cellToBeMoved = targetCell;
				return true;
			}
		}
		return false;
	}
	
	private static void moveUnit(){
		copyUnit();
		deleteUnit();
		if(selectedCell.unitType == TypesOfUnit.DUKE){
			//endTheGame
		}
	}
	
	private static void copyUnit(){
		cellToBeMoved.color = currentPlayer;
		cellToBeMoved.unitType = selectedCell.unitType;
		cellToBeMoved.movementPolarity = selectedCell.movementPolarity;
		changeMovementPolarity();
		cellToBeMoved.updateLabel();//to be removed later, for visibility purpose only
	}
	private static void deleteUnit(){
		selectedCell.color = FieldColor.EMPTY;
		selectedCell.unitType = TypesOfUnit.EMPTY;
		selectedCell.movementPolarity = MovementPolarity.NONE;
		selectedCell.updateLabel();//to be removed later
	}
	
	private static void changeMovementPolarity(){//can I make a pointer and referance it or I have to reference it directly?
		if(cellToBeMoved.movementPolarity == MovementPolarity.BLACK)
			cellToBeMoved.movementPolarity = MovementPolarity.WHITE;
		else if (cellToBeMoved.movementPolarity == MovementPolarity.WHITE)
			cellToBeMoved.movementPolarity = MovementPolarity.BLACK;
		else
			System.out.println("Problem while changing polarity");
	}
	
	static void changePlayer(){
		if(currentPlayer==FieldColor.WHITE)
			currentPlayer=FieldColor.BLACK;
		else if (currentPlayer==FieldColor.BLACK)
			currentPlayer=FieldColor.WHITE;
		else
			System.out.println("There was an issue while changing player color");
	}
	
	static void setPanelColorsToDefault(){
		for(int i=0; i<ROWS; i++){
			for(int j=0; j<COL; j++){
				Screen.cells[i][j].panel.setBackground(new Color(238,238,238));
			}
		}
	}
}
