/*
 * Le contr�leur
 */
package control;

import java.net.URL;
import java.util.ResourceBundle;

import application.Disque;
import application.FormeGeo;
import application.Main;
import application.Modele;
import application.Photo;
import application.Rectangle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import application.Modele;
public class Controleur implements Initializable {

	private GraphicsContext gc; //GraphicsContext du Canvas de la vue
	private double cWidth, cHeight;//Hauteur, largeur du Canvas
	private Modele modele;
	
	
	private int formeIdx; //Index de la forme qui est en train d'�tre d�plac�e
	private int selIdx=-1; //Index de la forme qui est s�lectionn�e
	private boolean enDeplacement=false; //Si une forme est en train d'�tre d�plac�e
	private double x_souris, y_souris; //Coordonn�es pr�c�dentes de la souris
	private Main mainLink;
	private Canvas currentCanvas;
	private String currentTool ="select";
	
	@FXML
	private BorderPane bp;
	@Override
    public void initialize(URL location, ResourceBundle resources) {
	      
        
    }
	
	public void setCanvas(int x,int y) {
		bp.setCenter(null);
		modele=new Modele();
		
		currentCanvas = new Canvas(x, y);
			cWidth=x;
			cHeight=y;
		currentCanvas.setOnMouseClicked(e -> {
	            try {
	                put(e);
	            } catch (Exception e1) {
	                e1.printStackTrace();
	            }
	        });
		currentCanvas.setOnMousePressed(e -> {
            try {
                attrape(e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

		currentCanvas.setOnMouseReleased(e -> {
            try {
                lache(e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

		currentCanvas.setOnMouseDragged(e -> {
            try {
                deplace(e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        bp.setCenter(currentCanvas);

        gc = currentCanvas.getGraphicsContext2D();
        init();
        draw();
        
 }
	
	public void setMain(Main main) {
		mainLink=main;
	}

	/**
	 * Pour Tester avec des formes ajout�es d�s le d�but
	 */
	public void init() {
		modele.add(new Rectangle(50,50,50,30,Color.BLUE));
		modele.add(new Disque(100,100,50,Color.RED));
		modele.add(new Disque(300,200,80,Color.GREEN));
	}
	
	/**
	 * La fonction qui efface le canvas et redessine tous les �l�ments
	 * stock�s dans le mod�le. 
	 */
	public void draw() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0,this.cWidth,this.cHeight);
		for (int i=0; i<modele.getSize();i++) {
			FormeGeo f=modele.get(i);
			if (i==selIdx) {
				f.draw(gc);
			}
			else {
				f.draw(gc);
			}

		}
	}
	/**
	 *Lorsque clique une fois, fait diff�rentes actions 
	 */
	@FXML
	public void put(MouseEvent e) {
		switch (currentTool) {
		case "select":
			for (int i=0; i<modele.getSize();i++) {
				FormeGeo f=modele.get(i);
				if (f.estDedans(e.getX(), e.getY())) {
					if(selIdx!=-1)modele.setUnSelected(modele.get(selIdx));
					selIdx=i;
					modele.setSelected(f);
					draw();
					break;
				}
			}

		break;
		case "shape":
			if(mainLink.currentShape.getType()=="rectangle") {
				Rectangle rec = new Rectangle(e.getX(), e.getY(), mainLink.currentShape.getLarg(), mainLink.currentShape.getHaut(),mainLink.currentShape.getColor());
				modele.add(rec);
				draw();
			}
			if(mainLink.currentShape.getType()=="disque") {
				Disque dis= new Disque(e.getX(), e.getY(),mainLink.currentShape.getRay(),mainLink.currentShape.getColor());
				modele.add(dis);
				draw();
			}
			break;
		case "delete":
			for (int i=0; i<modele.getSize();i++) {
				FormeGeo f=modele.get(i);
				if (f.estDedans(e.getX(), e.getY())) {
					modele.remove(f);
					draw();//update canvas
					break;
				}
			}
			break;
		default:
			break;
		}
	}
	
	
	/**
	 * Appel�e quand l'utilisateur appuie sur la souris pour attraper une forme
	 * � d�placer.
	 * @param e �v�nement souris
	 */
	@FXML
	public void attrape(MouseEvent e) {
		if(currentTool == "move") {
			for (int i=0; i<modele.getSize();i++) {
				FormeGeo f=modele.get(i);
				if (f.estDedans(e.getX(), e.getY()) && i==selIdx) {
					formeIdx=i;
					enDeplacement=true;
					x_souris = e.getX();
					y_souris = e.getY();
					break;
				}
			}
		}
		
		
		
	}

	/**
	 * Appel�e quand l'utilisateur d�place une forme
	 * @param e �v�nement souris
	 */
	@FXML
	public void deplace(MouseEvent e) {
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
	
	public void putImage(Image img) {
		double baseX=0,baseY=0;
		double imgHeight = img.getHeight();
		double imgWidth = img.getWidth();
		Photo ph = new Photo(baseX,baseY,imgWidth,imgHeight,img.getUrl());
		
		// Si l'hauteur ou la largeur de l'image est plus grande que le canvas on l'adapte
		if (currentCanvas.getHeight() < imgHeight ) currentCanvas.setHeight(imgHeight);
		if (currentCanvas.getWidth() < imgWidth ) currentCanvas.setWidth(imgWidth);
		
		modele.add(ph); //� ajouter pour que draw() n'oublie pas l'image
		this.draw();//update du canvas (�quivalent � repaint)
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
	
	public Canvas getCanvas() {
		return currentCanvas;
	}
	
	public void setTool(String name) {
		currentTool=name;
		System.out.println("Current tool is now set to : "+currentTool);
	}
	
}
