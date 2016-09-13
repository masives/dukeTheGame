package dukeTheGame;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import dukeTheGame.Screen.Cell;

public class InputHandler{
	public boolean cellSelected;
	int row;
	int col;
	Cell selectedCell;
	Cell setCell(Cell cell){return this.selectedCell = cell;}
	
	void addMouseListener(final Cell cell){
		
		cell.panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent panelClicked) {
				System.out.println(cell.label.getText());
				selection(cell);
				isUnitInCell(cell);
				super.mouseClicked(panelClicked);
				setCell(cell);
			}
		});
	}
	
	void selection(Cell cell){
		this.cellSelected = true;
		this.row = cell.getRowId();
		this.col = cell.getColId();
		System.out.println("Current selected cell is: row-" +  this.row + " col-" + this.col);
		if (this.selectedCell != null)
			//INPUT HANDLER HAS TO MAKE A CALL TO GENERAL METHOD DIRECTLY!!!!!!!
		this.selectedCell.panel.setBackground(Color.LIGHT_GRAY);
		cell.panel.setBackground(Color.red);
	}
		
	void isUnitInCell(Cell cell){
		if (cell.unitExist == true)
			System.out.println("Unit exists, it's: " + cell.unit.getUnitType());//will work only for board object, not reusable!
		else
			System.out.println("Cell is empty");
	}
}