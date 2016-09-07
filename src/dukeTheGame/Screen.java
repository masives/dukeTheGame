package dukeTheGame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	SelectedCell selected = new SelectedCell();
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
				board[i][j] = new Cell();
				frame.getContentPane().add(board[i][j].panel);
				board[i][j].setRow(i);
				board[i][j].setCol(j);
				board[i][j].setLabel("row:" + board[i][j].getRowId() + " col" + board[i][j].getColId());
				
				System.out.println(board[i][j].panel.getMouseListeners());//each board panel has a different mouse listener, but how to use this?
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
		
		//constructor for initializing cell with it's label, and mouselistener (should mouse listener be added later?)
		public Cell(){
			//panel = new JPanel();
			panel.add(label);
			
			//add MouseListener, this should be done outside of a class so it can provide input to input handler?
			panel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent panelClicked) {
					System.out.println(label.getText());
					selected.selection(getRowId(), getColId());
					selected.isUnitInCell(getRowId(), getColId());
					super.mouseClicked(panelClicked);
				}
			});
		}
		
		//unit part of cell
		boolean unitExist;
		Unit unit = new Unit();
	}
	
	//class for handling selection (mouse input)
	public class SelectedCell{
		boolean cellSelected;
		int row;
		int col;
		
		void selection(int row, int col){
			this.cellSelected = true;
			this.row = row;
			this.col = col;
			System.out.println("Current selected cell is: row-" +  this.row + " col-" + this.col);
		}
		void isUnitInCell(int row, int col){
			if (board[row][col].unitExist == true)
				System.out.println("Unit exists, it's: " + board[row][col].unit.getName());//will work only for board object, not reusable!
			else
				System.out.println("Cell is empty");
		}
	}	
}