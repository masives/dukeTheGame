package dukeTheGame;
import static dukeTheGame.GameLoopHandler.posibleMovementCells;
import static dukeTheGame.GlobalsAndControl.COL;
import static dukeTheGame.GlobalsAndControl.ROWS;
import static dukeTheGame.GlobalsAndControl.currentPlayer;
import static dukeTheGame.InputHandler.clickedCell;

import java.util.Arrays;

import dukeTheGame.Screen.Cell;
import enums.FieldColor;
import enums.MovementPolarity;
import enums.TypesOfUnit;

public class MovementHandler {
	static int[] movementPattern;
	static int[] movementPatternThatWontBeChanged;
	static int targetRow = 0;
	static int targetCol = 0;

	static int[] movementWalk = null;
	static int[] movementJump = null;
	static int[] movementStrike = null;
	static boolean horizontalStrafe = false;
	static boolean verticalStrafe = false;

	
	public static void revealPosibleMovement(){	
		
		MovementXmlParser.provideMovementInfo(GameLoopHandler.targetCell);
		
		if(movementWalk!=null){
			//copy of is used to not change original pattern
			movementPattern = Arrays.copyOf(movementWalk, movementWalk.length);
			flipMovementIfNeeded();
			revealWalkTypeMovement();
		}
		else if (horizontalStrafe==true|| verticalStrafe==true){//it's only for duke vertical movement
			revealStrafeTypeMovement();
			//TODO: implement jumping and striking
		}
		else
			System.out.println("Error in checking movement.");
		//clean the state
		 movementWalk = null;
		clearMovement();
		System.out.println("Now select movement place");
	}
	
	private static void clearMovement() {
		movementWalk = null;
		movementJump = null;
		movementStrike = null;
		horizontalStrafe = false;
		verticalStrafe = false;
	}

	private static void flipMovementIfNeeded(){
		if(GlobalsAndControl.currentPlayer == FieldColor.WHITE){
		
			for(int i=0; i< movementPattern.length; i ++){
				if (i%2==0 || i ==0){
					System.out.println("Current number: " + movementPattern[i]);
					movementPattern[i]= -movementPattern[i];
					System.out.println("after conversion: " + movementPattern[i]);
				}
			}
		}
	}
	
	private static void revealWalkTypeMovement(){
		for(int i = 0; i <movementPattern.length; i++){
			if(i%2==0 || i ==0){
				targetRow= clickedCell.row + movementPattern[i];
			}
			else if(i%2==1){
				targetCol= clickedCell.col + movementPattern[i];
				if(checkBounds(targetRow, targetCol) && checkFriendlyCollision())//collision checking can be added to this if statement
					posibleMovementCells.add(Screen.cells[targetRow][targetCol]);
			}
			else
				System.out.println("Something went wrong");
		}
	}
	//changed to public to allow reuse in draw
	public static boolean checkBounds(int targetRow, int targetCol){
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
		int lowBound = 0;
		int highBound = getBound();
		int chosenAxis = getAxis();
		int targetLine = getTargetLine();
		
		/*function uses two separate iterators 
		because there should one of them for each axis
		(left for vertical movement and right for horizontal) is iterable
		*/
		for(int i=0; i < COL;i++){
			//considered cell for boundaries setting 
			Cell consideredCell = Screen.cells[getLeftStrafeIterator(i, targetLine)][getRightStrafeIterator(i, targetLine)];
			if(consideredCell.unitType !=TypesOfUnit.EMPTY){
				//conditional assigning is used to make enemy units targetable
				if (i < chosenAxis)				
					lowBound = (consideredCell.color == currentPlayer) ? i + 1 : i;
				else if(i > chosenAxis)
					highBound = (consideredCell.color == currentPlayer) ? i : i + 1;
				else
					System.out.println("Boundaries not changed");
			}
		}
		for(int i=lowBound; i < highBound;i++){
			if(i!=chosenAxis)
				posibleMovementCells.add(Screen.cells[getLeftStrafeIterator(i, targetLine)][getRightStrafeIterator(i, targetLine)]);
		}
	}
	
	static int getBound(){//despite rows and cols being the same now function will be useful for custom modes(not square board)
		int bound = (clickedCell.movementPolarity == MovementPolarity.WHITE) ? COL: ROWS;
		return bound;
	}
	
	static int getAxis(){
		int axis = (clickedCell.movementPolarity == MovementPolarity.WHITE) ? clickedCell.col :clickedCell.row;
		return axis;
	}
	
	static int getTargetLine(){
		int targetLine = (clickedCell.movementPolarity == MovementPolarity.WHITE) ? clickedCell.row : clickedCell.col;
		return targetLine;
	}
	
	static int getLeftStrafeIterator(int i, int targetLine){
		int leftIterator = (clickedCell.movementPolarity == MovementPolarity.WHITE) ? targetLine : i;
		return leftIterator;
	}
	static int getRightStrafeIterator(int i, int targetLine){
		int rightIterator = (clickedCell.movementPolarity == MovementPolarity.WHITE) ? i : targetLine;
		return rightIterator;
	}
}