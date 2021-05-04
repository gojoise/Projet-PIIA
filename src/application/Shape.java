package application;

import javafx.scene.paint.Color;

public class Shape {
	
	
	private String type = "";
	private Color color;
	
	
	public Shape() {
		super();
	}
	/**
	 * Met
	 * @param t le type transmis
	 * @param col la couleur transmise
	 */
	public void setFields(String t,String col) {
		switch (col) {
		case "blue":
			color=Color.BLUE;
			break;
		case "black":
			color=Color.BLACK;
			break;
		case "red":
			color=Color.RED;
			break;
		case "green":
			color=Color.GREEN;
			break;
		default:
			break;
		}
		//color=col;
		
		type=t;
	}
	
	public String getType() {
		return type;
	}
	public String getColor() {
		if(color==Color.BLACK)return "black";
		else if(color==Color.BLUE)return "blue";
		else if(color==Color.RED)return "red";
		else if(color==Color.GREEN)return "green";
		else return "";
	}
	
	@Override
	public String toString() {
		return "Type : "+type+"Couleur :"+color.toString();
		
	}
}
