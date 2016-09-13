package dukeTheGame;

public class Unit {
	String unitType= "Default name";//name for testing only
	public String getUnitType(){return unitType;}
	char unitPlayerColor = 'N';
	
	void addUnit(char playerColor, String unitType){
		this.unitPlayerColor = playerColor;
		this.unitType = unitType;
	}
	
}