package dukeTheGame;

import static dukeTheGame.GlobalsAndControl.COL;
import static dukeTheGame.GlobalsAndControl.ROWS;
import static dukeTheGame.GlobalsAndControl.currentPlayer;
import static dukeTheGame.InputHandler.clickedCell;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import dukeTheGame.Screen.Cell;
import enums.FieldColor;
import enums.MovementPolarity;
import enums.TypesOfUnit;

public class GameLoopHandler {
	static Cell selectedCell = null;
	static Cell cellToBeMoved;
	static boolean drawButtonClicked = false;
	
	static List<Cell> targetCells = new ArrayList<Cell>();
	public static void gameLoopHandler(){
		if (drawButtonClicked==true){
			System.out.println("Where do you want to put the unit?");
		}
		else if (selectedCell != null){
			if(checkMovement()){
				moveUnit();
				changePlayer();
				//setPanelColorsToDefault();
			}
			else{
				//function for wiping the current selection
				System.out.println("It's not movable");
			}
		}
		else if (clickedCell.unitType!=null && clickedCell.color==currentPlayer){
			selectedCell = clickedCell;
			System.out.println("Unit selected, please choose your destination");
			revealPosibleMovement();
		}
		//also if the draw is picked there should be a function for drawing
		else
		System.out.println("Move your unit by clicking it or draw unit");
	}
	
	
	static void revealPosibleMovement(){//works only on "move type", currently there's movement for knight and duke, very ugly code
		int[] knightOne = {0,1,  0,-1,  1,0,  -1,0};
		
		int targetRow = 0;
		int targetCol = 0;
		if(selectedCell.unitType == TypesOfUnit.KNIGHT){
			for(int i = 0; i <knightOne.length; i++){
				if(i%2==0 || i ==0){
					targetRow= clickedCell.row + knightOne[i];
				}
				else if(i%2==1){
					targetCol= clickedCell.col + knightOne[i];
					if(Screen.cells[targetRow][targetCol].color != GlobalsAndControl.currentPlayer){
						targetCells.add(Screen.cells[targetRow][targetCol]);
						//targetting and highlighting should be done by seperate function!
						Screen.cells[targetRow][targetCol].panel.setBackground(Color.RED);
						System.out.println("Now select movement place");
					}
				}
				else
					System.out.println("Something went wrong");
			}
		}
		else if (selectedCell.unitType == TypesOfUnit.DUKE){//it's only for duke vertical movement
			targetRow = clickedCell.row;
			int currentCol = clickedCell.col;
			//create boundaries
			int leftMost = 0;
			int rightMost = COL;
			for(int i=0; i < COL;i++){
				if(Screen.cells[targetRow][i].unitType !=TypesOfUnit.EMPTY){
					if (i < currentCol){
						leftMost = i+1;
						System.out.println("Leftmost changed to " + leftMost);
					}
					else if(i > currentCol){
						rightMost = i;
						System.out.println("Rightmost changed to: "+ rightMost);
					}
					else
						System.out.println("Issue occured while trying to set boundaries");
				}
			}
			System.out.println("leftmost is: " + leftMost + " rightmost if: " + rightMost);
			for(int i=leftMost; i < rightMost;i++){
				targetCells.add(Screen.cells[targetRow][targetCol]);
				Screen.cells[targetRow][i].panel.setBackground(Color.RED);
				System.out.println("Now select movement place");
			}
		}
		else
			System.out.println("Error in checking movement.");
	}
	
	private static boolean checkMovement(){
		for(Cell targetCell:targetCells){
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
	}
	
	private static void copyUnit(){
		cellToBeMoved.color = currentPlayer;
		cellToBeMoved.unitType = selectedCell.unitType;
		cellToBeMoved.movementPolarity = selectedCell.movementPolarity;
		changeMovementPolarity(cellToBeMoved.movementPolarity);
		cellToBeMoved.updateLabel();//to be removed later, for visibility purpose only
	}
	
	private static void deleteUnit(){
		selectedCell.color = FieldColor.EMPTY;
		selectedCell.unitType = TypesOfUnit.EMPTY;
		selectedCell.movementPolarity = MovementPolarity.NONE;
		selectedCell.updateLabel();//to be removed later
	}
	
	private static void changeMovementPolarity(MovementPolarity currentPolarity){
		if(currentPolarity==MovementPolarity.BLACK)
			currentPolarity =MovementPolarity.WHITE;
		else if (currentPolarity==MovementPolarity.WHITE)
			currentPolarity =MovementPolarity.BLACK;
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
	
	/*static void setPanelColorsToDefault(){
		
		for(int i=0; i<ROWS; i++){
			for(int j=0; j<COL; j++){
				//Screen.cells[i][j].panel.setBackground(Color.BLACK);
				System.out.println(Screen.cells[0][0].panel.getBackground());
			}
	}*/
	//not used function for returning movement array for "move" type movement. Will be reused with other types of movement
	/*private static int[] getUnitMovement(){
		List<Integer> unitMovement = new ArrayList<Integer>();
		int[] movementArray;
		int[] dummyPlaceHolder = {0};
		int[] knightMovementOne = {0,1,  0,-1,  1,0,  -1,0};
		
		if (clickedCell.unitType == TypesOfUnit.KNIGHT){
			return knightMovementOne;
		}
		else if(clickedCell.unitType == TypesOfUnit.DUKE){
			
			return dummyPlaceHolder;
		}
		else
		return dummyPlaceHolder;
	}*/
	
	
}
