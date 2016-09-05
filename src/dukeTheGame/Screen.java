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
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Screen window = new Screen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
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
		// layout based  on constants
		frame.getContentPane().setLayout(new GridLayout(ROWS, COL, 2, 2)); 

		
		// arrays for panel and label(label for testing only?)
		JPanel panel[][] = new JPanel[ROWS][COL];
		JLabel label[][] = new JLabel[ROWS][COL];

		// array for mouse events inside the panels, so each one has it's own
		// event; not implemented yet
		MouseEvent panelClicked[][] = new MouseEvent[ROWS][COL];
		
		
		/* long loop for creating entire board 
		for (int i = 0; i < panel.length; i++) {
			for (int j = 0; j < panel[i].length; j++) {
				panel[i][j] = new JPanel();// instantiate panel in array?
				frame.getContentPane().add(panel[i][j]);

				label[i][j] = new JLabel();
				label[i][j].setText("Row: " + i + " Col:" + j);
				panel[i][j].add(label[i][j]);
				
				panel[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent panelClicked) {
						// TODO Auto-generated method stub
						System.out.println("panel clicked");
						super.mouseClicked(panelClicked);
					}
				});
			}
		}
		*/
		Cell board[][] = new Cell[ROWS][COL];
		
		for(int i = 0; i < ROWS; i++){
			for (int j = 0; j < COL; j++){
				board[i][j] = new Cell();
				frame.getContentPane().add(board[i][j].panel);
				board[i][j].setLabel("row:" + i + " col" + j);
				
				board[i][j].rowAdder();
				System.out.println(board[i][j].rowID);
				
				System.out.println(board[i][j].panel.getMouseListeners());//each board panel has a different mouse listener, but how to use this
			}
		}
			
		
		
		/*
		for (int i = 0; i < panel.length; i++) {
			for (int j = 0; j < panel[i].length; j++) {
				panel[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent panelClicked) {
						// TODO Auto-generated method stub
						System.out.println("clicked panel" + i + j); //wyrzuca problem, ze powinno byc final
						super.mouseClicked(panelClicked);
					}
				});
			}
		}
		*/
	}
	
	public class Cell{//to be implemented
		//main part of cell
		JPanel panel;
		JLabel label;
		//cell indexing
		int rowID = 0;
		int colID = 0;
		void rowAdder(){
			rowID++;
		}
		
		//setter for label name
		void setLabel(String newLabel){
			label.setText(newLabel);
		}
		//constructor for initializing cell content with memory, and mouselistener
		public Cell(){
			panel = new JPanel();
			label = new JLabel();
			panel.add(label);
			//add MouseListener
			panel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent panelClicked) {
					// TODO Auto-generated method stub
					System.out.println("panel clicked" + colID + rowID);
					super.mouseClicked(panelClicked);
				}
			});
		}		
	}
}


