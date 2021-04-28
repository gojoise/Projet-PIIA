/*
 * Le contr�leur
 */
package control;

import application.Disque;
import application.FormeGeo;
import application.Modele;
import application.Rectangle;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import application.Modele;
public class Controleur {

	private GraphicsContext gc; //GraphicsContext du Canvas de la vue
	private double cWidth, cHeight;//Hauteur, largeur du Canvas
	private Modele modele;
	
	private int formeIdx; //Index de la forme qui est en train d'�tre d�plac�e
	private boolean enDeplacement=false; //Si une forme est en train d'�tre d�plac�e
	private double x_souris, y_souris; //Coordonn�es pr�c�dentes de la souris
	
	/**
	 * Constructeur
	 * @param gc Le GraphicsContext du canvas de la Vue
	 * @param cWidth largeur du canvas
	 * @param cHeight hauteur du canvas
	 */
	public Controleur() {
		modele=new Modele();
	}
	
	public Controleur(GraphicsContext gc, double cWidth, double cHeight) {
		this.gc=gc;
		this.cWidth = cHeight;
		this.cHeight = cHeight;
		modele = new Modele();
		init();
	}
	
	/**
	 * Pour Tester avec des formes ajout�es d�s le d�but
	 */
	@FXML
	public void init() {
		modele.add(new Rectangle(50,50,50,30));
		modele.add(new Disque(100,100,50));
		modele.add(new Disque(300,200,80));
	}
	
	/**
	 * La fonction qui efface le canvas et redessine tous les �l�ments
	 * stock�s dans le mod�le. 
	 */
	@FXML
	public void draw() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0,this.cWidth,this.cHeight);
		gc.setFill(Color.BLACK);
		for (int i=0; i<modele.getSize();i++) {
			FormeGeo f=modele.get(i);
			f.draw(gc);
		}
	}
	
	/**
	 * Appel�e quand l'utilisateur appuie sur la souris pour attraper une forme
	 * � d�placer.
	 * @param e �v�nement souris
	 */
	@FXML
	public void attrape(MouseEvent e) {
		System.out.println("attrape");
		for (int i=0; i<modele.getSize();i++) {
			FormeGeo f=modele.get(i);
			if (f.estDedans(e.getX(), e.getY())) {
				formeIdx=i;
				enDeplacement=true;
				x_souris = e.getX();
				y_souris = e.getY();
				break;
			}
		}
	}

	/**
	 * Appel�e quand l'utilisateur d�place une forme
	 * @param e �v�nement souris
	 */
	@FXML
	public void deplace(MouseEvent e) {
		System.out.println("deplace");
		if (enDeplacement) {
			//On calcule le d�placement de la souris par rapport � sa derni�re
			//position
			double dx = e.getX() - x_souris;
			double dy = e.getY() - y_souris;
			//On r�cup�re la position actuelle de la forme
			double x_avant = modele.get(formeIdx).getX();
			double y_avant = modele.get(formeIdx).getY();
			//On d�place la forme du m�me d�placement que la souris
			modele.get(formeIdx).setX(x_avant+dx);
			modele.get(formeIdx).setY(y_avant+dy);
			//On retient les coordonn�es de la souris pour calculer le prochain d�placement
			x_souris = e.getX();
			y_souris = e.getY();
			this.draw();
		}
	}
	
	/**
	 * Appel� quand l'utilisateur l�che le bouton de souris 
	 * pour arr�ter le d�placement
	 * @param e �v�nement souris
	 */
	@FXML
	public void lache(MouseEvent e) {
		enDeplacement=false;
	}
	
}
