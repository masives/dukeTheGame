package dukeTheGame;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Screen {
	// constants for board size, same as in InputHandler which is a bad solution
	public static final int ROWS = 4;
	public static final int COL = 4;
	
	//gui elements and array for cells
	JFrame frame;
	Cell cells[][] = new Cell[ROWS][COL];
	
	public Screen() {
		initializeFrame();
		assignCells();
	}

	private void initializeFrame() {
		// top level frame which will hold the board
		frame = new JFrame("Duke");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// layout based  on constants for the board only (no place for draw button or message box at the moment)
		frame.getContentPane().setLayout(new GridLayout(ROWS, COL, 2, 2)); 
	}
	private void assignCells(){
		for(int i = 0; i < ROWS; i++){
			for (int j = 0; j < COL; j++){
				cells[i][j] = new Cell();
				frame.getContentPane().add(cells[i][j].panel);
				cells[i][j].assignCoordinates(i, j);
				cells[i][j].updateLabel();
			}
		}
	}
	
	public class Cell{	
		int row;
		int col;
		
		void assignCoordinates(int row, int col){
			this.row = row;
			this.col = col;
		}
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel();
		
		void updateLabel(){
			label.setText("row: " + this.row + " col: " + this.col + "\n unit type: " + this.unitType + "\n color: " + this.color);
		}
		
		TypesOfUnit unitType;
		FieldColor color;
			
		public Cell(){
			panel.add(label);
			unitType = TypesOfUnit.EMPTY;
			color = FieldColor.EMPTY;
		}
	}
}