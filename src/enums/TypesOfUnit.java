package enums;

public enum TypesOfUnit {
	DUKE("slide"),
	KNIGHT("walk"),
	EMPTY;
	
	String movementType;
	int[][] movementPattern;
	
	public String getMovementType(){
		return movementType;
	}
	
	TypesOfUnit(){
		movementType=null;
		movementPattern=null;
	}
	TypesOfUnit(String movementType){
		this.movementType = movementType;
	}
	
	TypesOfUnit(String movementType, int[][] movementPattern){
		this.movementType = movementType;
		this.movementPattern = movementPattern;				
	}
}