/**
 * Une forme de disque
 */
package application;

import javafx.scene.canvas.GraphicsContext;

public class Disque extends FormeGeo {
	
	private double rayon;
	
	public Disque(double x, double y, double rayon) {
		super(x,y);
		this.rayon=rayon;
	}
	
	double getRayon() {return this.rayon;}
	void setRayon(double rayon) {this.rayon=rayon;}
	
	public boolean estDedans(double x, double y) {
		//Le point est dans le disque si sa distance au centre 
		//est inférieure au rayon
		double xc=this.x+this.rayon;
		double yc=this.y+this.rayon;
		return (x-xc)*(x-xc) + (y-yc)*(y-yc) < this.rayon*this.rayon;
	}
	
	public void draw(GraphicsContext gc) {
		gc.fillOval(this.x, this.y, 2*this.rayon, 2*this.rayon);
	}

}
