package dukeTheGame;

public class ApplicationMain {

	public static void main(String[] args) {
		
		Screen window = new Screen();
		window.frame.setVisible(true);
				
		InputHandler inputHandler = new InputHandler();
		inputHandler.addMouseListenersToBoard(window.cells);
		
		System.out.println("White player please choose a place for duke");
		
	}
}