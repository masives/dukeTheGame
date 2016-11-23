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
	
	void addMouseListenersToBoard(Cell[][] cells){
		for(int i = 0; i < ROWS; i++){
			for (int j = 0; j < COL; j++){
				addMouseListener(cells[i][j]);
			}
		}
	}
	
	void addMouseListener(final Cell cell){
		cell.panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent panelClicked) {
				handleInput(cell);				 
			}
		});
	}
	
	static void handleInput(Cell cell){
		switch(gameState){
		case whiteSetup:
			setupPlayer(cell);
			break;
		default:
			System.out.println("something went wrong");
		}
		System.out.println("Clicked cell: " + cell.label.getText());
	}
	
	static boolean wasDukeSet = false;
	static int numberOfKnightsSet = 0;	
	static void setupPlayer(Cell clickedCell){		
		if (wasDukeSet == false){
			setUnit(clickedCell, TypesOfUnit.DUKE);
			//updateDukePosition(clickedCell);
			wasDukeSet = true;
		}
		else if (numberOfKnightsSet != 2){
			setUnit(clickedCell, TypesOfUnit.KNIGHT);
			numberOfKnightsSet ++;
		}
		else
			gameState = GameState.blackSetup;
	}
	
	//to be inmplemented later, leave for until the listener is done correctly
	static DukePosition whiteDukePosition;
	static DukePosition blackDukePosition;
	
	class DukePosition{
		int row;
		int col;
	}
	static void updateDukePosition(Cell dukeCell){
		if (currentPlayer == FieldColor.WHITE){
			whiteDukePosition.row = dukeCell.row;
			whiteDukePosition.col = dukeCell.col;
		}
		else{
			blackDukePosition.row = dukeCell.row;
			blackDukePosition.col = dukeCell.col;
		}
	}
		
	
	static void setUnit(Cell dukeDestination, TypesOfUnit unit) {
		dukeDestination.unitType = unit;
		dukeDestination.color = currentPlayer;
		dukeDestination.updateLabel();
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
