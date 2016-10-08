package dukeTheGame;

public class Unit {
	public String unitType= "Default name";//name for testing only
	public String getUnitType(){return unitType;}
	public char unitPlayerColor = 'N';
	
	void addUnit(char playerColor, String unitType){
		this.unitPlayerColor = playerColor;
		this.unitType = unitType;
	}
	
	enum jak_pionek{non};
	enum gracz{non}
}