///https://www.codevoila.com/post/62/xml-processing-in-java-jaxp-dom-example

package dukeTheGame;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dukeTheGame.Screen.Cell;
import enums.MovementPolarity;
import enums.TypesOfUnit;

public class MovementXmlParser{

	public static void provideMovementInfo(Cell targetCell){
	//all results are returned to MovementHandler variables
		
	TypesOfUnit queriedUnitType = targetCell.unitType; //to be later used for testing as a condition
	MovementPolarity queriedPolarity = targetCell.movementPolarity;
	
	DocumentBuilderFactory df;
	DocumentBuilder builder;
	Document document;
	
	df = DocumentBuilderFactory.newInstance();
	try {
		builder = df.newDocumentBuilder();
		document = builder.parse("Movement.xml");
	
		
		NodeList movementNodeList = document.getElementsByTagName("entry");
			
		for (int i =0; i < movementNodeList.getLength(); i++){
			Element movementItem = (Element) movementNodeList.item(i); //create element reference to i-th entry 
			System.out.println("Element accessed: " + movementItem.getAttribute("id"));
			
			NodeList movementItemChildNodes = movementItem.getChildNodes(); //access nodes of the entry
			
			for (int j = 0; j < movementItemChildNodes.getLength(); j++){
				Node childNode = movementItemChildNodes.item(j);
				//check if unitType and polarity matches 
	            if ("unitType".equals(childNode.getNodeName())) {
	            	TypesOfUnit unitTypeInXml = parseUnitType(childNode);//
	            	if (unitTypeInXml !=queriedUnitType){
	            		System.out.println("Breaking on unit");
	            		break;
	            	}
	            }
	            
	            if ("polarity".equals(childNode.getNodeName())) {
	            	MovementPolarity polarityInXml = parsePolarity(childNode);
	            	if (queriedPolarity!=polarityInXml){
	            		System.out.println("Breaking on polarity");
	            		break;
	            	}
	            }
	            //movement processing
	            if ("movementWalk".equals(childNode.getNodeName())) {
	            	MovementHandler.movementWalk = parseArray(childNode);
	                System.out.println("MovementHandler.movementWalk: " + MovementHandler.movementWalk.length);
	                for(int k=0; k<MovementHandler.movementWalk.length;k++){
	                	System.out.print(MovementHandler.movementWalk[k]+" ,");
	                }
	            }
		            
	            if ("movementJump".equals(childNode.getNodeName())) {
	            	MovementHandler.movementJump = parseArray(childNode);
	            	System.out.println("movementJump: " + childNode.getFirstChild().getNodeValue());
	            } 
	            //movementStrike placeholder
	            if ("movementStrike".equals(childNode.getNodeName())) {
	            	MovementHandler.movementStrike = parseArray(childNode);
	            	System.out.println("movementStrike: " + childNode.getFirstChild().getNodeValue());
	            }
	            //horizontalStrafe placeholder
	            if ("horizontalStrafe".equals(childNode.getNodeName())) {
	            	MovementHandler.horizontalStrafe=true;
	            	System.out.println("horizontalStrafe: " + childNode.getFirstChild().getNodeValue());
	            }
	            //verticalStrafe placeholder
	            if ("verticalStrafe".equals(childNode.getNodeName())) {
	            	MovementHandler.verticalStrafe=true;
	        	System.out.println("verticalStrafe: " + childNode.getFirstChild().getNodeValue());
	            }
			}
		System.out.println();
		}
	}
	catch (ParserConfigurationException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	 catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	
	static TypesOfUnit parseUnitType(Node childNode){
		//parsed toUpper since enums are uppercase
		TypesOfUnit parsedUnitType = TypesOfUnit.valueOf(childNode.getFirstChild().getNodeValue().toUpperCase()); 
		return parsedUnitType;
	}
	
	static int[] parseArray(Node childNode){
		String movementArrayInNode = childNode.getFirstChild().getNodeValue();
		
		if(movementArrayInNode!=null){
			String movementString = movementArrayInNode;
	    	String[] movementStringTrimmer = movementString.trim().split(",");
	    	//horrible solution but cannot find anything better now :(
	    	for (int k = 0; k < movementStringTrimmer.length; k++){
	    		movementStringTrimmer[k] = movementStringTrimmer[k].trim();
	    	}
	    	
	    	int[] movementArray = new int[movementStringTrimmer.length];
	    	
	    	for (int k = 0; k < movementStringTrimmer.length; k++){
	    		movementArray[k] = Integer.parseInt(movementStringTrimmer[k]);
	    	}
	    		
	        return movementArray;
		}
		else
			return null;
	}
	static MovementPolarity parsePolarity(Node childNode){
		
		String polarityValue = childNode.getFirstChild().getNodeValue();
		int currentPolarity = Integer.parseInt(polarityValue);
		if (currentPolarity==0)
			return MovementPolarity.WHITE;
		else if (currentPolarity==1)
			return MovementPolarity.BLACK;
		else{
			System.out.println("Issue occured while parsing polarity string");
			return null;
		}
	}
}
