package dukeTheGame;

public class ApplicationMain {

	//setDuke and setKnight have to be extending from one method
	public static void main(String[] args) {
		
		int gameStage = 0; 
		FieldColor currentPlayer = FieldColor.WHITE;
		
		System.out.println("Frame initialization started");
		// initialize game window
		Screen window = new Screen();
		window.frame.setVisible(true);
		
		//confirm successful instalation of window
		System.out.println("Frame initialized");
		gameStage ++;
		System.out.println("Current stage: " + gameStage);
		
		InputHandler inputHandler = new InputHandler();
		InputHandler.addMouseListenersToBoard(window.cells);
		
		//inputHandler.setDuke(window.board[1][2], currentPlayer);
		
		System.out.println("White player please choose a place for duke");
		
		System.out.println("Duke set");
		
		// there should be a cell pointer to the one with duke
		System.out.println("Knight set");
	}

}