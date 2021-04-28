package application;

	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

import control.Controleur;
import control.menuControl;

public class Main extends Application {
	//Nous créons des variable de classes afin de pouvoir y accéder partout
	//Ceci afin de pouvoir y positionner les éléments que nous avons fait
	//Il y a un BorderPane car le conteneur principal de notre IHM
	//est un BorderPane, nous reparlerons de l'objet Stage
	private Stage stagePrincipal;
	private BorderPane conteneurPrincipal;
	private Canvas drawArea ;
	
	@Override
	public void start(Stage primaryStage) {
		stagePrincipal = primaryStage;
		//Ca ne vous rappelle pas une JFrame ?
		stagePrincipal.setTitle("titre");
		
		//Nous allons utiliser nos fichier FXML dans ces deux méthodes
		initialisationConteneurPrincipal();
		initialisationContenu();
		

		
		//GraphicsContext gc= drawArea.getGraphicsContext2D();
		//Le contrôleur est créé à partir du GraphicsContext et des dimensions du canvas
		//Controleur control=new Controleur(gc, drawArea.getWidth(), drawArea.getHeight());
		//control.draw(); //On dessine le canvas
		stagePrincipal.centerOnScreen();
		stagePrincipal.show();
		System.out.println("done !");
	}

	private void initialisationConteneurPrincipal() {
		//On créé un chargeur de FXML
		FXMLLoader loader = new FXMLLoader();
		//On lui spécifie le chemin relatif à notre classe
		//du fichier FXML a charger : dans le sous-dossier view
		loader.setLocation(Main.class.getResource("../vue/MainWindow.fxml"));
		try {
			//Le chargement nous donne notre conteneur
			conteneurPrincipal = loader.load();
			//On définit une scène principale avec notre conteneur
			Scene scene = new Scene(conteneurPrincipal);
			//Que nous affectons à notre Stage
			stagePrincipal.setScene(scene);
			//Pour l'afficher
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 private void initialisationContenu() {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("../vue/ZoneDessin.fxml"));
	        try {
	            //Nous récupérons notre conteneur qui contiendra les données
	            //Pour rappel, c'est un AnchorPane...
	            AnchorPane conteneurDessin = (AnchorPane) loader.load();
	            //Qui nous ajoutons à notre conteneur principal
	            //Au centre, puisque'il s'agit d'un BorderPane
	            
	            conteneurPrincipal.setCenter(conteneurDessin);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getStage() {
		return stagePrincipal;
	}
}
