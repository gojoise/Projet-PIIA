/*
 * Le contrôleur
 */
package control;

import java.net.URL;
import java.util.ResourceBundle;

import application.Disque;
import application.FormeGeo;
import application.Main;
import application.Modele;
import application.Rectangle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import application.Modele;
public class Controleur implements Initializable {

	private GraphicsContext gc; //GraphicsContext du Canvas de la vue
	private double cWidth, cHeight;//Hauteur, largeur du Canvas
	private Modele modele;
	
	
	private int formeIdx; //Index de la forme qui est en train d'être déplacée
	private boolean enDeplacement=false; //Si une forme est en train d'être déplacée
	private double x_souris, y_souris; //Coordonnées précédentes de la souris
	private Main mainLink;
	private Canvas currentCanvas;
	
	@FXML
	private BorderPane bp;
	@Override
    public void initialize(URL location, ResourceBundle resources) {
	      
        modele=new Modele();
    }
	
	public void setCanvas() {
		currentCanvas = new Canvas(800, 600);
			cWidth=800;
			cHeight=600;

//        File file = new File("src/address/Images/Default/ExempleItem.png");
//        String localURL = file.toURI().toURL().toString();
//        Image useImage = new Image(localURL);
//        this.projet_PreviewImage.setImage(useImage);

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
	 * Pour Tester avec des formes ajoutées dès le début
	 */
	public void init() {
		modele.add(new Rectangle(50,50,50,30));
		modele.add(new Disque(100,100,50));
		modele.add(new Disque(300,200,80));
	}
	
	/**
	 * La fonction qui efface le canvas et redessine tous les éléments
	 * stockés dans le modèle. 
	 */
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
	 * Appelée quand l'utilisateur appuie sur la souris pour attraper une forme
	 * à déplacer.
	 * @param e évènement souris
	 */
	@FXML
	public void attrape(MouseEvent e) {
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
	 * Appelée quand l'utilisateur déplace une forme
	 * @param e évènement souris
	 */
	@FXML
	public void deplace(MouseEvent e) {
		if (enDeplacement) {
			//On calcule le déplacement de la souris par rapport à sa dernière
			//position
			double dx = e.getX() - x_souris;
			double dy = e.getY() - y_souris;
			//On récupère la position actuelle de la forme
			double x_avant = modele.get(formeIdx).getX();
			double y_avant = modele.get(formeIdx).getY();
			//On déplace la forme du même déplacement que la souris
			modele.get(formeIdx).setX(x_avant+dx);
			modele.get(formeIdx).setY(y_avant+dy);
			//On retient les coordonnées de la souris pour calculer le prochain déplacement
			x_souris = e.getX();
			y_souris = e.getY();
			this.draw();
		}
	}
	
	/**
	 * Appelé quand l'utilisateur lâche le bouton de souris 
	 * pour arrêter le déplacement
	 * @param e évènement souris
	 */
	@FXML
	public void lache(MouseEvent e) {
		enDeplacement=false;
	}
	
	public Canvas getCanvas() {
		return currentCanvas;
	}
	
}
