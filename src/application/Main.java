package application;

	
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import control.Controleur;
import control.menuControl;

public class Main extends Application {
	//Nous créons des variable de classes afin de pouvoir y accéder partout
	//Ceci afin de pouvoir y positionner les éléments que nous avons fait
	//Il y a un BorderPane car le conteneur principal de notre IHM
	//est un BorderPane, nous reparlerons de l'objet Stage
	private Stage stagePrincipal;
	private BorderPane conteneurPrincipal;
	private Canvas currentcanvas ;

	
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
	            AnchorPane conteneurDessin = (AnchorPane) loader.load();
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
		currentcanvas= new Canvas(800,600);
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
	}
	public void saveFile() {
		System.out.println("save");
		FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = 
                new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
      
        //Show save file dialog
        File file = fileChooser.showSaveDialog(stagePrincipal);
	    if(file != null){
	        WritableImage wi = new WritableImage((int)currentcanvas.getWidth(),(int)currentcanvas.getHeight());
	        try {                    ImageIO.write(SwingFXUtils.fromFXImage(currentcanvas.snapshot(null,wi),null),"png",file);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public Stage getStage() {
		return stagePrincipal;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
