package dukeTheGame;
import static dukeTheGame.GameLoopHandler.posibleMovementCells;
import static dukeTheGame.GameLoopHandler.selectedCell;
import static dukeTheGame.GlobalsAndControl.COL;
import static dukeTheGame.GlobalsAndControl.ROWS;
import static dukeTheGame.InputHandler.clickedCell;

import enums.MovementPolarity;
import enums.TypesOfUnit;

public class MovementHandler {
	static int[] movementPattern;
	static int targetRow = 0;
	static int targetCol = 0;
	
	public static void revealPosibleMovement(){	
		if(selectedCell.unitType.getMovementType().equals("walk")){
			movementPattern=MovementPatternDb.getMovementPattern();
			revealWalkTypeMovement();
		}
		else if (selectedCell.unitType == TypesOfUnit.DUKE){//it's only for duke vertical movement
			revealStrafeTypeMovement();
		}
		else
			System.out.println("Error in checking movement.");
		
		System.out.println("Now select movement place");
	}
	
	private static void revealWalkTypeMovement(){
		for(int i = 0; i <movementPattern.length; i++){
			if(i%2==0 || i ==0){
				targetRow= clickedCell.row + movementPattern[i];
				System.out.println("target row:" + targetRow);
			}
			else if(i%2==1){
				targetCol= clickedCell.col + movementPattern[i];
				System.out.println("target col:" + targetCol);
				//check bounds
				if(checkBounds() && checkFriendlyCollision())//collision checking can be added to this if statement
						posibleMovementCells.add(Screen.cells[targetRow][targetCol]);
			}
			else
				System.out.println("Something went wrong");
		}
	}
	
	private static boolean checkBounds(){
		if ((0 <= targetRow && targetRow < ROWS) && (0 <= targetCol && targetCol < COL))
			return true;
		else
			return false;
	}
	
	private static boolean checkFriendlyCollision(){
		if (Screen.cells[targetRow][targetCol].color != GlobalsAndControl.currentPlayer)
			return true;
		else
			return false;
	}
	
	private static void revealStrafeTypeMovement(){//it's only for duke vertical movement
		int leftMost = 0;
		int rightMost = COL;
		//horizontal
		if (clickedCell.movementPolarity == MovementPolarity.WHITE){
			targetRow = clickedCell.row;
			int currentCol = clickedCell.col;
			for(int i=0; i < COL;i++){
				if(Screen.cells[targetRow][i].unitType !=TypesOfUnit.EMPTY){
					if (i < currentCol)
						leftMost = i+1;
					else if(i > currentCol)
						rightMost = i;
					else
						System.out.println("Issue occured while trying to set boundaries");
				}
			}
			for(int i=leftMost; i < rightMost;i++){
				if(i!=currentCol)
					posibleMovementCells.add(Screen.cells[targetRow][i]);
			}
		}
		//vertical
		else if (clickedCell.movementPolarity == MovementPolarity.BLACK){
			int topmost =0;
			int downmost = ROWS;
			targetCol = clickedCell.col;
			int currentRow = clickedCell.row;
			for(int i=0; i < ROWS;i++){
				if(Screen.cells[i][targetCol].unitType !=TypesOfUnit.EMPTY){
					if (i < topmost)
						leftMost = i+1;
					else if(i > downmost)
						rightMost = i;
					else
						System.out.println("Issue occured while trying to set boundaries");
				}
			}
			for(int i=topmost; i < downmost;i++){
				if(i!=currentRow)
					posibleMovementCells.add(Screen.cells[i][targetCol]);
			}
		}
		System.out.println("Now select movement place");
	}
}