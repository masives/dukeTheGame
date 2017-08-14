package dukeTheGame;

import static dukeTheGame.InputHandler.clickedCell;
import static enums.TypesOfUnit.KNIGHT;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import dukeTheGame.Screen.Cell;
import enums.FieldColor;
import enums.MovementPolarity;
import enums.TypesOfUnit;

public class DrawHandler {
	static TypesOfUnit[] whiteDrawPool = {KNIGHT, KNIGHT, KNIGHT};
	static TypesOfUnit[] blackDrawPool = Arrays.copyOf(whiteDrawPool, whiteDrawPool.length);
	static Random drawRandomizer = new Random();
	static int usedDrawNumber;
	
	public static void showPossiblePlaces() {
		int currentDukeRow = getDukeRow();
		int currentDukeCol = getDukeCol();
		
		System.out.println("Checking places");
		System.out.println("White duke " + GlobalsAndControl.whiteDukePosition.label.getText());
		GameLoopHandler.possibleDrawCells =  addPossibleDrawPlaces(currentDukeRow, currentDukeCol);
	}
	//coloring has to made from red to white after selection
	
	public static void addUnitToCell(Cell selectedCell) {
		selectedCell.unitType = DrawHandler.randomizeFigure();
		selectedCell.color = GlobalsAndControl.currentPlayer;
		selectedCell.movementPolarity = MovementPolarity.WHITE;
		selectedCell.updateLabel();
	}
	
	static public TypesOfUnit randomizeFigure() {
		usedDrawNumber = drawRandomizer.nextInt(getCurrentPool().length);
		TypesOfUnit randomizedFigure = getCurrentPool()[usedDrawNumber];
		System.out.println("drawed number is: " + usedDrawNumber);
		return randomizedFigure;
	}
	
	static public void deleteFromPool() {
		//create new array smaller than currentPool
		TypesOfUnit[] currentPool = getCurrentPool();
		TypesOfUnit[] smallerPool = new TypesOfUnit[currentPool.length-1];
		//copy each element except the one picked
		int correction=0;//used for making i smaller when item is deleted from array
		for (int i =0; i <= smallerPool.length; i++) {
			if (i!=usedDrawNumber){
				smallerPool[i-correction]= currentPool[i];
			}
			else {
				correction = correction+1;
				System.out.println("correction applied");
			}
		}
		//change pointer of current pool to new array
		if (GlobalsAndControl.currentPlayer==FieldColor.WHITE)
			whiteDrawPool = smallerPool;
		else
			blackDrawPool = smallerPool;
	}
	
	public static TypesOfUnit[] getCurrentPool() {//used in GameLoopHandler
		if (GlobalsAndControl.currentPlayer==FieldColor.WHITE)
			return whiteDrawPool;
		else
			return blackDrawPool;
	}
	
	private static int getDukeRow() {
		if (GlobalsAndControl.currentPlayer == FieldColor.WHITE)
			return GlobalsAndControl.whiteDukePosition.row;
		else
			return GlobalsAndControl.blackDukePosition.row;
	}
	private static int getDukeCol() {
		if (GlobalsAndControl.currentPlayer == FieldColor.WHITE)
			return GlobalsAndControl.whiteDukePosition.col;
		else
			return GlobalsAndControl.blackDukePosition.col;
	}
	
	private static List<Cell> addPossibleDrawPlaces(int currentDukeRow, int currentDukeCol) {
		List<Cell> possibleDrawPlaces = new ArrayList<Cell>();
		//it checks for all 4 adjacent places
		int dukeCheckingArray[][] = {{-1,1,0,0},	{0,0,-1,1}};
		
		for (int i=0; i < dukeCheckingArray[0].length; i++) {//forced checking of inner array since outer array is shorter
			int checkedRow = currentDukeRow + dukeCheckingArray[0][i];
			int checkedCol = currentDukeCol + dukeCheckingArray[1][i];
			if(MovementHandler.checkBounds(checkedRow, checkedCol) && Screen.cells[checkedRow][checkedCol].unitType == TypesOfUnit.EMPTY) 
				possibleDrawPlaces.add(Screen.cells[checkedRow][checkedCol] );
		}
		
		for (Cell drawCell:possibleDrawPlaces) {
			//highlighting sellected cells
			drawCell.panel.setBackground(Color.RED);
		}
		return possibleDrawPlaces;
	}
}
