package dukeTheGame;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Screen {
	// constants for board size, moved to Screen class from initialize function
	final int ROWS = 4;
	final int COL = 4;
	
	//gui elements and selection handler
	JFrame frame;
	Cell board[][] = new Cell[ROWS][COL];
	InputHandler inputHandler = new InputHandler();
	/**
	 * Create the application.
	 */
	public Screen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// top level frame which will hold the board
		frame = new JFrame("Duke");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// layout based  on constants for the board only (no place for draw button or message box at the moment)
		frame.getContentPane().setLayout(new GridLayout(ROWS, COL, 2, 2)); 
		
		for(int i = 0; i < ROWS; i++){
			for (int j = 0; j < COL; j++){
				//create cell, index it, name it and add input handler
				board[i][j] = new Cell();
				frame.getContentPane().add(board[i][j].panel);
				board[i][j].setRow(i);
				board[i][j].setCol(j);
				board[i][j].setLabel("row:" + board[i][j].getRowId() + " col" + board[i][j].getColId());
				inputHandler.addMouseListener(board[i][j]);
			}
		}
	}
	
	public class Cell{	
		//indexing(localization) part of cell
		private int rowId;
		private int colId;
		public void setRow(int row){rowId = row;}
		public void setCol(int col){colId = col;}
		public int getRowId(){return rowId;}
		public int getColId(){return colId;}
		
		//gui part of cell, initialized 
		JPanel panel = new JPanel();;
		JLabel label = new JLabel();;
		//setter for label name, for convenience
		void setLabel(String newLabel){label.setText(newLabel);}
		
		//constructor for initializing cell with it's label
		public Cell(){
			panel.add(label);
		}
		
		//unit part of cell, instead of using boolean for unitExist unitobject wihout reference can check that
		boolean unitExist;
		Unit unit;
	}
}