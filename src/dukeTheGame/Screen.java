package dukeTheGame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Screen {

	JFrame frame;
	
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

		// constants for board size
		final int ROWS = 4;
		final int COL = 4;

		// top level frame which will hold the board
		frame = new JFrame("Duke");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// layout based  on constants for the board only (no place for draw button or message box at the moment)
		frame.getContentPane().setLayout(new GridLayout(ROWS, COL, 2, 2)); 

		Cell board[][] = new Cell[ROWS][COL];
		Index index = new Index();
		
		for(int i = 0; i < ROWS; i++){
			index.setRow(i);
			for (int j = 0; j < COL; j++){
				board[i][j] = new Cell();
				frame.getContentPane().add(board[i][j].panel);
				index.setCol(j);
				board[i][j].setLabel("row:" + index.getRowId() + " col" + index.getColId());
				
				System.out.println(board[i][j].panel.getMouseListeners());//each board panel has a different mouse listener, but how to use this?
			}
		}
	}
	
	public class Index{
		private int rowId = 0;
		private int colId = 0;
		public void setRow(int row){rowId = row;}
		public void setCol(int col){colId = col;}
		public int getRowId(){return rowId;}
		public int getColId(){return colId;}
	}
	
	public class Cell{	
		
		//main part of cell
		JPanel panel;
		JLabel label;
		//setter for label name, for convenience
		void setLabel(String newLabel){label.setText(newLabel);}
		
		//constructor for initializing cell content with memory, and mouselistener
		public Cell(){
			panel = new JPanel();
			label = new JLabel();
			panel.add(label);
			//add MouseListener, this should be done outside of a class so it can provide input to input handler?
			panel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent panelClicked) {
					// TODO Auto-generated method stub
					System.out.println(label.getText());
					super.mouseClicked(panelClicked);
				}
			});
		}		
	}
	
	class Unit{
		String name;
		//movement
	}
}


