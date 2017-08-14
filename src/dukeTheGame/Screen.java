package dukeTheGame;

import static dukeTheGame.GlobalsAndControl.COL;
import static dukeTheGame.GlobalsAndControl.ROWS;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import enums.FieldColor;
import enums.MovementPolarity;
import enums.TypesOfUnit;

public class Screen {
	//gui elements and array for cells
	JFrame frame;
	static Cell cells[][] = new Cell[GlobalsAndControl.ROWS][GlobalsAndControl.COL];
	JButton button;
	
	public Screen() {
		initializeFrame();
		assignCells();
		addButton();
	}

	private void initializeFrame() {
		// top level frame which will hold the board
		frame = new JFrame("Duke");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// layout based  on constants for the board only (no place for draw button or message box at the moment)
		frame.getContentPane().setLayout(new GridLayout((GlobalsAndControl.ROWS +1), GlobalsAndControl.COL, 2, 2)); 
	}
	
	private void assignCells(){
		for(int i = 0; i < GlobalsAndControl.ROWS; i++){
			for (int j = 0; j < GlobalsAndControl.COL; j++){
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
		MovementPolarity movementPolarity;
			
		public Cell(){
			panel.add(label);
			unitType = TypesOfUnit.EMPTY;
			color = FieldColor.EMPTY;
			movementPolarity = movementPolarity.NONE;
		}
	}
	
	static void setPanelColorsToDefault(){
		for(int i=0; i<ROWS; i++){
			for(int j=0; j<COL; j++){
				if(Screen.cells[i][j].color == FieldColor.WHITE)
					Screen.cells[i][j].panel.setBackground(new Color(255,255,255));
				else if (Screen.cells[i][j].color == FieldColor.BLACK)
					Screen.cells[i][j].panel.setBackground(new Color(0,0,0));
				else	
				Screen.cells[i][j].panel.setBackground(new Color(238,238,238));
			}
		}
	}
	
	//button to be yet implemented
	void addButton(){
	JButton button = new JButton("Draw");
	frame.getContentPane().add(button);
	button.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			DrawHandler.showPossiblePlaces();
			
			if (GameLoopHandler.possibleDrawCells.isEmpty())
				System.out.println("You cannot put figure, there's no room");
			else if (DrawHandler.getCurrentPool().length == 0)
				System.out.println("No more figures to draw");
			else {
			GameLoopHandler.drawButtonClicked = true;
			System.out.println("Draw button clicked");
			}
		}
	});
	}
	
	
}