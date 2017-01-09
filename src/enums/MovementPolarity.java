package enums;

public enum MovementPolarity {
	NONE(300),//random value for errors only
	WHITE(0),
	BLACK(1);
	
	int polarityValue;
	public int getValue(){
		return polarityValue;
	}
	MovementPolarity(int polarity){
		polarityValue = polarity;
	}
}