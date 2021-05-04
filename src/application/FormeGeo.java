/**
 * Forme géométrique abstraite
 */
package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

abstract public class FormeGeo {

	protected double x,y; //Position de la forme
	protected boolean selected=false;
	protected Color couleur;
	protected Color memory;
	
	FormeGeo(double x, double y,Color c){
		this.x=x;
		this.y=y;
		couleur=c;
		memory=couleur;
	}
	
	public double getX() {return x;}
	
	public double getY() {return y;}
	public Color getMem() {return memory;}
	
	public void setX(double x) {this.x=x;}
	
	public void setY(double y) {this.y=y;}
	
	public void setColor(Color c) {this.couleur=c;}
	
	/**
	 * Vérifie qu'un point est dans la forme
	 * @param x abscisse d'un point
	 * @param y ordonnée d'un point
	 * @return vrai si le point est dans la forme
	 */
	public abstract boolean estDedans(double x, double y);
	
	/**
	 * Dessine la forme
	 * @param gc GraphicsContext du canvas de la vue
	 */
	public abstract void draw(GraphicsContext gc);
}

