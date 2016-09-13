package dukeTheGame;

import dukeTheGame.Screen.Cell;

public class ApplicationMain {

	static void setDuke(Cell dukeDestination, char currentPlayer) {
		dukeDestination.unit = new Unit();
		dukeDestination.unitExist = true;
		dukeDestination.unit.unitType = "duke";
		dukeDestination.unit.unitPlayerColor = currentPlayer;
	}

	// while using setKnight I'm forced to make it static (that it can't be
	// changed) why?
	static void setKnight(Cell dukeLocation, Cell knightLocation) {
		int dukeRow = dukeLocation.getRowId();
		int dukeCol = dukeLocation.getColId();
		int knightRow = knightLocation.getRowId();
		int knightCol = knightLocation.getRowId();
		// checking bounds is questionable since it should be impossible to
		// provide input outside of bonds!!
		if (boundCheck(knightRow, knightCol) == true) {
			// checking if it's tile adjancent to the duke one
			if (dukeRow == knightRow + 1 && dukeCol == knightCol || dukeRow == knightRow - 1 && dukeCol == knightCol
					|| dukeRow == knightRow && dukeCol == knightCol + 1
					|| dukeRow == knightRow && dukeCol == knightCol + 1) {
				System.out.println("Possible knight location");

			}
		} else
			System.out.println("Not possible knight location");
	}

	// checking bounds is questionable since it should be impossible to provide
	// input outside of bonds!!
	static boolean boundCheck(int knightRow, int knightCol) {
		if (knightRow >= 0 && knightRow <= 4 && knightCol >= 0 && knightCol >= 0) {
			return true;
		} else
			return false;
	}

	public static void main(String[] args) {

		// initialize game window
		Screen window = new Screen();
		window.frame.setVisible(true);

		System.out.println("Frame initialized");

		char currentPlayer = 'w'; // only options are w and b (white and black),
									// need to make these two only options

		// initial setting of figures

		System.out.println("White player please choose a place for duke");

		setDuke(window.board[1][2], currentPlayer);
		// there should be a cell pointer to the one with duke
		setKnight(window.board[1][2], window.board[0][2]);

	}
}