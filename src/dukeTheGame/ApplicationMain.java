package dukeTheGame;

import dukeTheGame.Screen.Cell;

public class ApplicationMain {

	//setDuke and setKnight have to be extending from one method
	static void setDuke(Cell dukeDestination, char currentPlayer) {
		dukeDestination.unit = new Unit();
		dukeDestination.unitExist = true;
		dukeDestination.unit.unitType = "duke";
		dukeDestination.unit.unitPlayerColor = currentPlayer;
	}


	// checking bounds is questionable since it should be impossible to provide
	// input outside of bonds!!
	static boolean boundCheck(int knightRow, int knightCol) {
		if (knightRow >= 0 && knightRow <= 4 && knightCol >= 0 && knightCol >= 0) {
			System.out.println("Bounds ok");
			return true;
		} 
		else{
			System.out.println("Bounds not ok");
			return false;
		}
	}

	public static void main(String[] args) {

		System.out.println("Frame initialization started");
		// initialize game window
		Screen window = new Screen();
		window.frame.setVisible(true);

		System.out.println("Frame initialized");

		char currentPlayer = 'w'; // only options are w and b (white and black),
									// need to make these two only options

		// initial setting of figures

		System.out.println("White player please choose a place for duke");

		System.out.println("Duke set");
		// there should be a cell pointer to the one with duke
		System.out.println("Knight set");
	}
}