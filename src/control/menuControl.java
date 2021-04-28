package control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.File;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.WritableImage;
import javafx.scene.SnapshotParameters;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;

public class menuControl implements Initializable{
    //Objet servant de référence à notre classe principale
    //afin de pouvoir récupérer le Stage principal.
	//et ainsi fermer l'application
	@FXML private  BorderPane bp;
	private Stage stage;
    
    //Méthode qui sera utilisée dans l'initialisation de l'IHM
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
		stage.close();
		System.out.println("fermer !");
	}
	
	@FXML
	public void nouveau() {
		
	}
	
	@FXML
	public void openFile() {
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(stage);
		System.out.println(selectedFile.toString());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		stage = Main.getStage();	
	}
	
//	@FXML
//	private void saveFile(){
//	    FileChooser fc = new FileChooser();
//	    fc.setInitialDirectory(new File("res/maps"));
//	    fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG","*.png"));
//	    fc.setTitle("Save Map");
//	    File file = fc.showSaveDialog(stage);
//	    if(file != null){
//	        WritableImage wi = new WritableImage((int)WIDTH,(int)HEIGHT);
//	        try {
//	        	SnapshotParameters sp = new SnapshotParameters();
//	            sp.setFill(Color.TRANSPARENT);
//	            ImageIO.write(SwingFXUtils.fromFXImage(canvas.snapshot(sp, wi), null),"png", file);
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	}
	}


