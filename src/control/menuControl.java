package control;

import javafx.fxml.FXML;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class menuControl {
    //Objet servant de r�f�rence � notre classe principale
    //afin de pouvoir r�cup�rer le Stage principal.
	//et ainsi fermer l'application
    
    //M�thode qui sera utilis�e dans l'initialisation de l'IHM
    //dans notre classe principale
    
	//Fermer l'application
	@FXML
	public void fermer() {
		//On affiche un message car on est poli.
		Alert bye = new Alert(AlertType.INFORMATION);
		bye.setTitle("Au revoir !");
		bye.setHeaderText("See you soon...");
		bye.showAndWait();
		
		//Et on clos le stage principal, donc l'application
		System.out.println("fermer !");
	}
	
	@FXML
	public void nouveau() {
		
	}	
}

