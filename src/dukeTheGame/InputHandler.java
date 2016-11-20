package dukeTheGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import dukeTheGame.Screen.Cell;

public class InputHandler {
	public static final int ROWS = 4;
	public static final int COL = 4;
	//initial value of state in game is when the white is about to show a duke
	static GameState gameState = GameState.whiteSetup;
	static FieldColor currentPlayer = FieldColor.WHITE;
	
	static void addMouseListenersToBoard(Cell[][] cells){
		for(int i = 0; i < ROWS; i++){
			for (int j = 0; j < COL; j++){
				//create cell,  name it and add input handler
				//Cell targetCell = cells[ROWS][COL];
				addMouseListener(cells[i][j]);
			}
		}
	}
	
	static void addMouseListener(final Cell cell){
		cell.panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent panelClicked) {
				handleInput(cell);				 
				//i tu pytac o logike
			}
		});
	}
	
	static void handleInput(Cell cell){
		switch(gameState){
		case whiteSetup:
			setDuke(cell, currentPlayer);
			System.out.println("Where to place knight");
			setKnight(cell, currentPlayer);
			break;
		default:
			System.out.println("something went wrong");
		}
		System.out.println("Clicked cell: " + cell.label.getText());
		
	}
	
	//to be inmplemented later, leave for until the listener is done correctly
	DukePosition whiteDukePosition = new DukePosition();
	DukePosition blackDukePosition = new DukePosition();
	
	class DukePosition{
		int row;
		int col;
	}
		
	
	static void setDuke(Cell dukeDestination, FieldColor currentPlayer) {
		dukeDestination.unitType = TypesOfUnit.DUKE;
		dukeDestination.color = currentPlayer;
		}
	
	static void setKnight(Cell knightDestination, FieldColor currentPlayer) {
		knightDestination.unitType = TypesOfUnit.KNIGHT;
		knightDestination.color = currentPlayer;
		}
	
	enum GameState{
		whiteSetup,
		blackSetup,
		choiceOfField,
		drawUnit,
		moveUnit,
		non
	}
}
