/**
 * Forme géométrique abstraite
 */
package application;

import javafx.scene.canvas.GraphicsContext;

abstract public class FormeGeo {

	protected double x,y; //Position de la forme
	protected boolean selected=false;
	
	FormeGeo(double x, double y){
		this.x=x;
		this.y=y;
	}
	
	public double getX() {return x;}
	
	public double getY() {return y;}
	
	
	public void setX(double x) {this.x=x;}
	
	public void setY(double y) {this.y=y;}
	
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

