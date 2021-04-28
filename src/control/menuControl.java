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
import javafx.scene.canvas.Canvas;
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
	@FXML private Canvas img;
    
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
	
	@FXML
	private void saveFile(){
		FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = 
                new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
      
        //Show save file dialog
        File file = fileChooser.showSaveDialog(stage);
	    if(file != null){
	        WritableImage wi = new WritableImage((int)img.getWidth(),(int)img.getHeight());
	        try {                    ImageIO.write(SwingFXUtils.fromFXImage(img.snapshot(null,wi),null),"png",file);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	}


