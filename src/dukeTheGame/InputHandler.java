package dukeTheGame;

import static dukeTheGame.GlobalsAndControl.COL;
import static dukeTheGame.GlobalsAndControl.ROWS;
import static dukeTheGame.GlobalsAndControl.currentPlayer;
import static dukeTheGame.GlobalsAndControl.gameState;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import dukeTheGame.Screen.Cell;

public class InputHandler {
	
	static Cell clickedCell; //last clicked cell
	
	void addMouseListenersToBoard(Cell[][] cells){
		
		for(int i = 0; i < ROWS; i++){
			for (int j = 0; j < COL; j++){
				addMouseListener(cells[i][j]);
			}
		}
	}
	
	private void addMouseListener(final Cell cell){
		cell.panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent panelClicked) {
				handleInput(cell);			 
			}
		});
	}
	
	static void handleInput(Cell cell){
		clickedCell = cell;
		System.out.println(GameLoopHandler.drawButtonClicked);
		
		switch(gameState){
		case PLAYER_SETUP:
			InitialPlayerSetup.setupPlayer();
			break;
		case GAME_LOOP:
			GameLoopHandler.gameLoopHandler();
			break;
		default:
			System.out.println("something went wrong");
		}
		//temporary helping commands, will get rid of them once ecerything works properly
		System.out.println("Clicked cell: " + cell.label.getText());
		System.out.println("");

		System.out.println("Current player:" + currentPlayer);
		System.out.println("Current game state:" + gameState);
		System.out.println("");
	}
}