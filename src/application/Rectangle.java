/*
 * Une forme rectangulaire
 */
package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends FormeGeo {

	private double larg, haut;//la largeur et la hauteur du rectangle
	
	public Rectangle(double x, double y, double larg, double haut,Color c) {
		super(x,y,c);
		this.larg=larg;
		this.haut=haut;
	}
	
	double getLargeur() {return larg;}
	double getHauteur() {return haut;}
	void setLargeur(double larg) {this.larg=larg;}
	void setHauteur(double haut) {this.haut=haut;}
	
	public boolean estDedans(double x, double y) {
		return (x-this.x)<this.larg && (x-this.x)>0 && (y-this.y)<this.haut && (y-this.y)>0;
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(couleur);
		gc.fillRect(this.x,this.y,this.larg,this.haut);
	}
	
	
}
