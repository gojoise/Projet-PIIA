package application;

import javafx.scene.paint.Color;

public class Shape {
	
	
	private String type = "";
	private Color color;
	
	private int larg;
	private int haut;
	
	private int ray;
	
	
	
	public Shape() {
		super();
	}
	/**
	 * Met les champs principaux via le controleur
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
	
	public void setRect(int l,int h) {
		larg=l;
		haut=h;
	}
	public void setDis(int r) {
		ray=r;
	}
	
	public String getType() {
		return type;
	}
	public Color getColor() {
//		if(color==Color.BLACK)return "black";
//		else if(color==Color.BLUE)return "blue";
//		else if(color==Color.RED)return "red";
//		else if(color==Color.GREEN)return "green";
//		else return "";
		return color;
	}
	/**
	 * 
	 * @return ray
	 */
	public int getRay() {
		return ray;
	}
	
	public int getLarg() {
		return larg;
	}
	
	public int getHaut() {
		return haut;
	}
	
	@Override
	public String toString() {
		return "Type : "+type+"Couleur :"+color.toString()+"LargeurxHauteur :"+ larg+"x"+haut;
		
	}
}
