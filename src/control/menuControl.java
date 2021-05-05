package control;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class menuControl implements Initializable {
	// Objet servant de référence à notre classe principale
	// afin de pouvoir récupérer le Stage principal.
	// et ainsi fermer l'application
	@FXML
	private BorderPane bp;
	private Main mainLink;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	public void setMain(Main main) {
		mainLink = main;
	}

	// Fermer l'application
	@FXML
	public void fermer() {
		mainLink.quit();
	}

	@FXML
	public void nouveau() {
		mainLink.newCanv();
	}

	@FXML
	public void openFile() throws MalformedURLException {
		mainLink.openImg();
	}

	@FXML
	public void saveFile() {
		mainLink.saveFile();
	}
	
	@FXML
	public void editShape() {
		mainLink.showEdit();
	}
	
	@FXML 
	public void setMove() {
		mainLink.setCursor("move");
	}
	@FXML 
	public void setSel() {
		mainLink.setCursor("select");
	}
	@FXML 
	public void delete() {
		mainLink.setCursor("delete");
	}
}
