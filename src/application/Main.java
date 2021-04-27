package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	//Nous cr�ons des variable de classes afin de pouvoir y acc�der partout
	//Ceci afin de pouvoir y positionner les �l�ments que nous avons fait
	//Il y a un BorderPane car le conteneur principal de notre IHM
	//est un BorderPane, nous reparlerons de l'objet Stage
	private Stage stagePrincipal;
	private BorderPane conteneurPrincipal;
	
	@Override
	public void start(Stage primaryStage) {
		stagePrincipal = primaryStage;
		//Ca ne vous rappelle pas une JFrame ?
		stagePrincipal.setTitle("titre");
		
		//Nous allons utiliser nos fichier FXML dans ces deux m�thodes
		initialisationConteneurPrincipal();
		initialisationContenu();
	}

	private void initialisationConteneurPrincipal() {
		//On cr�� un chargeur de FXML
		FXMLLoader loader = new FXMLLoader();
		//On lui sp�cifie le chemin relatif � notre classe
		//du fichier FXML a charger : dans le sous-dossier view
		loader.setLocation(Main.class.getResource("MainWindow.fxml"));
		try {
			//Le chargement nous donne notre conteneur
			conteneurPrincipal = loader.load();
			//On d�finit une sc�ne principale avec notre conteneur
			Scene scene = new Scene(conteneurPrincipal);
			//Que nous affectons � notre Stage
			stagePrincipal.setScene(scene);
			//Pour l'afficher
			stagePrincipal.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 private void initialisationContenu() {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("ZoneDessin.fxml"));
	        try {
	            //Nous r�cup�rons notre conteneur qui contiendra les donn�es
	            //Pour rappel, c'est un AnchorPane...
	            AnchorPane conteneurPersonne = (AnchorPane) loader.load();
	            //Qui nous ajoutons � notre conteneur principal
	            //Au centre, puisque'il s'agit d'un BorderPane
	            conteneurPrincipal.setCenter(conteneurPersonne);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
