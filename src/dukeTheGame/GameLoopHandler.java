package dukeTheGame;

import static dukeTheGame.GlobalsAndControl.currentPlayer;
import static dukeTheGame.InputHandler.clickedCell;
import static dukeTheGame.MovementHandler.revealPosibleMovement;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import dukeTheGame.Screen.Cell;
import enums.FieldColor;
import enums.MovementPolarity;
import enums.TypesOfUnit;

public class GameLoopHandler {
	static Cell targetCell = null;
	static boolean drawButtonClicked = false;
	
	static List<Cell> possibleDrawCells = new ArrayList<Cell>();
	static List<Cell> posibleMovementCells = new ArrayList<Cell>();//for move type units
	//TODO additional lists to be implemented - for striking and optionally jumping
	static Cell cellToBeMoved;
	
	public static void gameLoopHandler() throws ParserConfigurationException, SAXException, IOException{
		if (drawButtonClicked==true){
			System.out.println("Where do you want to put the unit?");
			//highlighting and checking movement are triggered by mouse listener
			//places are shown during clicking the button (on Screen)
			//check if clicked button is on the checking list
			//if places shown draw assign unit and unclick button
			System.out.println("Checking draw");
			
			if(checkDraw()) {
				System.out.println("Draw check passed");
				DrawHandler.addUnitToCell(clickedCell);
				DrawHandler.deleteFromPool();
				cancelSelection();
				changePlayer();
			}
			else {
				drawButtonClicked = false;
				cancelSelection();
			}
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
	//can be merged into one function with checkMovement
	private static boolean checkDraw(){
		for(Cell targetCell:possibleDrawCells){
			if(clickedCell==targetCell){
				cellToBeMoved = targetCell;
				return true;
			}
		}
		return false;
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
		System.out.println("Moving unit");
		if(clickedCell.unitType == TypesOfUnit.DUKE){
			System.out.println("Game over");
			endGame(currentPlayer);
		}
		else if(targetCell.unitType == TypesOfUnit.DUKE){
			updateDukePosition();
		}		
		copyUnit();
		deleteUnit();
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
		if(targetCell.color == FieldColor.WHITE){
			GlobalsAndControl.whiteDukePosition = clickedCell;
			System.out.println("Current white duke position is, row:" + GlobalsAndControl.whiteDukePosition.row + " col: "+ GlobalsAndControl.whiteDukePosition.col);
		}
		else if (targetCell.color == FieldColor.BLACK){
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
		possibleDrawCells.clear();
	}
	static void endGame(FieldColor winner){
		//show message
		Object[] options = { "Play again", "Exit" };
		int buttonClicked= JOptionPane.showOptionDialog(null, "Congrats, " + currentPlayer.toString() + " player wins!", "Game over",
		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		null, options, options[0]);
		
		
		//restart the game
		if(buttonClicked ==0){
			System.out.println("restarting the game");
			//TODO:implement restart
		}
		//end restart the game
		else
			System.exit(0);
	}
}
