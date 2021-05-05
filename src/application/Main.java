package application;

	
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import control.Controleur;
import control.ShapeController;
import control.menuControl;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
	//Nous créons des variable de classes afin de pouvoir y accéder partout
	//Ceci afin de pouvoir y positionner les éléments que nous avons fait
	//Il y a un BorderPane car le conteneur principal de notre IHM
	//est un BorderPane, nous reparlerons de l'objet Stage
	private Stage stagePrincipal;
	private BorderPane conteneurPrincipal;
	private Controleur ctrl;
	public Shape currentShape = new Shape();
	
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
	            ctrl = loader.getController();
	            ctrl.setMain(this);
	            ctrl.setCanvas(800,600); 
	            
	            
	            conteneurPrincipal.setCenter(conteneurDessin);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
				e.printStackTrace();
			}
	    }
	 
	public void newCanv() {
		ctrl.setCanvas(1280, 720);
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
	public void openImg() throws MalformedURLException {
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(stagePrincipal);
		if(selectedFile !=null) {
		System.out.println(selectedFile.toString());
		
		/*
		 * récupérer l'image avec le selected file tostring 
		 */
		String localURL = selectedFile.toURI().toURL().toString();
		 /* puis la stocker dans une var image
		  */
		  Image useImage = new Image(localURL);
		 /* utiliser la var image pour dessiner l'image dans le canevas
		 */
	          ctrl.setMain(this);//Update du main (au cas où)
			  ctrl.putImage(useImage);
		}

		  /*
		 */
	}

	public void saveFile() {
		System.out.println("save");
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
		fileChooser.getExtensionFilters().add(extFilter);

		// récupère le canevas
		Canvas currentCanvas=ctrl.getCanvas();
		// Show save file dialog
		File file = fileChooser.showSaveDialog(stagePrincipal);
		if (file != null) {
			WritableImage wi = new WritableImage((int) currentCanvas.getWidth(), (int) currentCanvas.getHeight());
			try {
				ImageIO.write(SwingFXUtils.fromFXImage(currentCanvas.snapshot(null, wi), null), "png", file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Stage getStage() {
		return stagePrincipal;
	}
	public void setCursor(String string) {
		switch (string) {
		case "move":
			stagePrincipal.getScene().setCursor(Cursor.MOVE);
			ctrl.setTool(string);
			break;
		case "select":
			stagePrincipal.getScene().setCursor(Cursor.DEFAULT);
			ctrl.setTool(string);
			break;
		case "shape":
			stagePrincipal.getScene().setCursor(Cursor.CROSSHAIR);
			ctrl.setTool(string);
			break;
		case "delete":
			stagePrincipal.getScene().setCursor(Cursor.DEFAULT);
			ctrl.setTool(string);
			break;
		default:
			break;
		}
		
	}
	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 *
	 * @param person the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showEdit() {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("../vue/ShapeEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Person");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(stagePrincipal);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	        ShapeController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setShape(currentShape);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();
	        setCursor("shape");
	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}


}
