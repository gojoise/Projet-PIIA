package control;

import application.Shape;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ShapeController {
		@FXML
		private ComboBox<String> color;
		@FXML
		private ComboBox<String> type;


	    private Stage dialogStage;
	    private boolean okClicked = false;
	    private Shape shape;

	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	    	color.getItems().removeAll(color.getItems());
	    	color.getItems().addAll("blue", "red", "green","black");
	        
	    	type.getItems().removeAll(type.getItems());
	    	type.getItems().addAll("rectangle", "disque");
	    }

	    /**
	     * Sets the stage of this dialog.
	     * 
	     * @param dialogStage
	     */
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	        
	        // Set the dialog icon.
	        this.dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
	    }
	    
	    
	    public void setShape(Shape sh) {
	    	shape=sh;
	    }

	    /**
	     * Returns true if the user clicked OK, false otherwise.
	     * 
	     * @return
	     */
	    public boolean isOkClicked() {
	        return okClicked;
	    }

	    /**
	     * Called when the user clicks ok.
	     */
		@FXML
		private void handleOk() {
			shape.setFields(type.getSelectionModel().getSelectedItem(),color.getSelectionModel().getSelectedItem());

			okClicked = true;
			dialogStage.close();
		}

	    /**
	     * Called when the user clicks cancel.
	     */
	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }

}
