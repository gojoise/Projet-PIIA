package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Photo extends FormeGeo{

	private double larg, haut;//la largeur et la hauteur du rectangle
	private String URL;
	
	public Photo(double x, double y, double larg, double haut,String url) {
		super(x,y);
		this.larg=larg;
		this.haut=haut;
		URL=url;
	}
	
	double getLargeur() {return larg;}
	double getHauteur() {return haut;}
	void setLargeur(double larg) {this.larg=larg;}
	void setHauteur(double haut) {this.haut=haut;}
	
	public boolean estDedans(double x, double y) {
		return (x-this.x)<this.larg && (x-this.x)>0 && (y-this.y)<this.haut && (y-this.y)>0;
	}
	
	public void draw(GraphicsContext gc) {
		Image img = new Image(URL);
		gc.drawImage(img, this.x,this.y,this.larg,this.haut);
	}
	
	

}
