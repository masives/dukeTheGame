package dukeTheGame;
import static dukeTheGame.GlobalsAndControl.ROWS;
import static dukeTheGame.GlobalsAndControl.COL;
import static dukeTheGame.InputHandler.clickedCell;
import static dukeTheGame.GameLoopHandler.selectedCell;
import static dukeTheGame.GameLoopHandler.posibleMovementCells;

import java.awt.Color;

import enums.TypesOfUnit;

public class MovementHandler {
	public static void revealPosibleMovement(){//works only on "move type", currently there's movement for knight and duke, very ugly code
		int[] knightOne = {0,1,  0,-1,  1,0,  -1,0};
		
		int targetRow = 0;
		int targetCol = 0;
		
		if(selectedCell.unitType == TypesOfUnit.KNIGHT){
			for(int i = 0; i <knightOne.length; i++){
				if(i%2==0 || i ==0){
					targetRow= clickedCell.row + knightOne[i];
					System.out.println("target row:" + targetRow);
				}
				else if(i%2==1){
					targetCol= clickedCell.col + knightOne[i];
					System.out.println("target col:" + targetCol);
					//check bounds
					if((0 <= targetRow && targetRow < ROWS) && (0 <= targetCol && targetCol < COL)){
						if(Screen.cells[targetRow][targetCol].color != GlobalsAndControl.currentPlayer){
							posibleMovementCells.add(Screen.cells[targetRow][targetCol]);
							Screen.cells[targetRow][targetCol].panel.setBackground(Color.RED);
						}
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
					posibleMovementCells.add(Screen.cells[targetRow][targetCol]);
					Screen.cells[targetRow][i].panel.setBackground(Color.RED);
			}
			System.out.println("Now select movement place");
		}
		else
			System.out.println("Error in checking movement.");
	}
}