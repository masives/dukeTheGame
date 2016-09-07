package dukeTheGame;


public class ApplicationMain {
	public static void main(String[] args) {
				
		//initialize game window
		Screen window = new Screen();
		window.frame.setVisible(true);
		
		System.out.println("Frame initialized");
		window.board[1][1].unitExist = true; //for testing selection method
		System.out.println(window.board[1][1].unitExist);
		//initialize game function
		//game loop
		
	}
}
