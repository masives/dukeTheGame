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
	static Cell targetCell = null;
	static boolean drawButtonClicked = false;
	
	static List<Cell> posibleMovementCells = new ArrayList<Cell>();//for move type units
	//TODO additional lists to be implemented - for striking and optionally jumping
	static Cell cellToBeMoved;
	
	public static void gameLoopHandler(){
		if (drawButtonClicked==true){
			System.out.println("Where do you want to put the unit?");
			//TODO implement the drawing mechanincs
		}
		else if (targetCell != null){
			if(checkMovement()){
				//TODO after check movement will be check striking
				moveUnit();
				changePlayer();
				cancelSelection();
			}
			else{
				System.out.println("It's not movable, choose unit again");
				cancelSelection();
			}
		}
		else if (clickedCell.unitType!=null && clickedCell.color==currentPlayer){
			targetCell = clickedCell;
			revealPosibleMovement();
			for(Cell cell: posibleMovementCells){
				cell.panel.setBackground(Color.RED);
			}
			System.out.println("Unit selected, please choose your destination");
		}
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
		if(targetCell.unitType == TypesOfUnit.DUKE){
			//endTheGame
		}
		else if(clickedCell.unitType == TypesOfUnit.DUKE){
			updateDukePosition();
		}
	}
	
	private static void copyUnit(){
		cellToBeMoved.color = currentPlayer;
		cellToBeMoved.unitType = targetCell.unitType;
		cellToBeMoved.movementPolarity = targetCell.movementPolarity;
		changeMovementPolarity();
		cellToBeMoved.updateLabel();//to be removed later, for visibility purpose only
	}
	private static void deleteUnit(){
		targetCell.color = FieldColor.EMPTY;
		targetCell.unitType = TypesOfUnit.EMPTY;
		targetCell.movementPolarity = MovementPolarity.NONE;
		targetCell.updateLabel();//to be removed later
	}
	
	private static void changeMovementPolarity(){//can I make a pointer and referance it or I have to reference it directly?
		if(cellToBeMoved.movementPolarity == MovementPolarity.BLACK)
			cellToBeMoved.movementPolarity = MovementPolarity.WHITE;
		else if (cellToBeMoved.movementPolarity == MovementPolarity.WHITE)
			cellToBeMoved.movementPolarity = MovementPolarity.BLACK;
		else
			System.out.println("Problem while changing polarity");
	}
	
	private static void updateDukePosition(){
		if(clickedCell.color == FieldColor.WHITE){
			GlobalsAndControl.whiteDukePosition = clickedCell;
			System.out.println("Current white duke position is, row:" + GlobalsAndControl.whiteDukePosition.row + " col: "+ GlobalsAndControl.whiteDukePosition.col);
		}
		else if (clickedCell.color == FieldColor.BLACK){
			GlobalsAndControl.blackDukePosition = clickedCell;
			System.out.println("Current black duke position is, row:" + GlobalsAndControl.blackDukePosition.row + " col: "+ GlobalsAndControl.blackDukePosition.col);
		}
		else
			System.out.println("Problem occured while updating duke position");
	}
	
	static void changePlayer(){
		if(currentPlayer==FieldColor.WHITE)
			currentPlayer=FieldColor.BLACK;
		else if (currentPlayer==FieldColor.BLACK)
			currentPlayer=FieldColor.WHITE;
		else
			System.out.println("There was an issue while changing player color");
	}
	
	static void cancelSelection(){
		targetCell = null;
		Screen.setPanelColorsToDefault();
		posibleMovementCells.clear();
	}
	
	
}
