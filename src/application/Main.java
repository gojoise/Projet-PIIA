package application;

	
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import control.Controleur;
import control.menuControl;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	//Nous créons des variable de classes afin de pouvoir y accéder partout
	//Ceci afin de pouvoir y positionner les éléments que nous avons fait
	//Il y a un BorderPane car le conteneur principal de notre IHM
	//est un BorderPane, nous reparlerons de l'objet Stage
	private Stage stagePrincipal;
	private BorderPane conteneurPrincipal;

	
	@Override
	public void start(Stage primaryStage) {
		stagePrincipal = primaryStage;
		stagePrincipal.setTitle("titre");
		
		initialisationConteneurPrincipal();
		initialisationCanevas();

		stagePrincipal.centerOnScreen();
		stagePrincipal.show();
		System.out.println("done !");
	}

	private void initialisationConteneurPrincipal() {
		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(Main.class.getResource("../vue/MainWindow.fxml"));
		try {
			conteneurPrincipal = loader.load();
			menuControl mC = loader.getController();
			mC.setMain(this);
			Scene scene = new Scene(conteneurPrincipal);
			stagePrincipal.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 private void initialisationCanevas() {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("../vue/ZoneDessin.fxml"));
	        try {
	            BorderPane conteneurDessin = (BorderPane) loader.load();
	            Controleur ctrl = loader.getController();
	            ctrl.setMain(this);
	            ctrl.setCanvas(); 
	            
	            
	            conteneurPrincipal.setCenter(conteneurDessin);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
				e.printStackTrace();
			}
	    }
	 
	public void newCanv() {
		/*
		 * set un nouveau canvas dans le controleur
		 */
	}
	 
	
	public void quit() {
		//On affiche un message car on est poli.
		Alert bye = new Alert(AlertType.INFORMATION);
		bye.setTitle("Au revoir !");
		bye.setHeaderText("See you soon...");
		bye.showAndWait();
		
		//Et on clos le stage principal, donc l'application
		stagePrincipal.close();
		System.out.println("fermer !");
	}
	public void openImg() {
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(stagePrincipal);
		System.out.println(selectedFile.toString());
		
		/*
		 * récupérer l'image avec le selected file tostring
		 * puis la stocker dans une var image
		 * utiliser la var image pour dessiner l'image dans le canevas
		 */
	}
	public void saveFile() {
		System.out.println("save");
		FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = 
                new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //récupère le canevas
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../vue/ZoneDessin.fxml"));
        try {
        	Controleur ctrl = loader.getController();
            ctrl.setMain(this);
            Canvas currentcanvas=ctrl.getCanvas();
            //Show save file dialog
            File file = fileChooser.showSaveDialog(stagePrincipal);
    	    if(file != null){
    	        WritableImage wi = new WritableImage((int)currentcanvas.getWidth(),(int)currentcanvas.getHeight());
    	        try {                    ImageIO.write(SwingFXUtils.fromFXImage(currentcanvas.snapshot(null,wi),null),"png",file);
    	        } catch (IOException e) {
    	            e.printStackTrace();
    	        }
    	    }
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
       
	}
	
	public Stage getStage() {
		return stagePrincipal;
	}
	public static void main(String[] args) {
		launch(args);
	}

	public void setCursor(String string) {
		switch (string) {
		case "move":
			stagePrincipal.getScene().setCursor(Cursor.MOVE);
			break;

		default:
			break;
		}
		
	}
}
