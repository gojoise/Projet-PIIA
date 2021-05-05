package control;


import java.util.HashSet;
import java.util.Set;

import application.Shape;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ShapeController {
		@FXML
		private GridPane gp;
		@FXML
		private ComboBox<String> color;
		@FXML
		private ComboBox<String> type;
		
		private String la;
		private String ha;
		private String ra;
		
		private TextField t1,t2,t3;


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
			//on applique les caractéristiques sur notre forme.
			shape.setFields(type.getSelectionModel().getSelectedItem(),color.getSelectionModel().getSelectedItem());
			
			if(type.getSelectionModel().getSelectedItem()=="rectangle") {
				la=t1.getText();
				ha=t2.getText();
				shape.setRect(Integer.parseInt(la),Integer.parseInt(ha));
			}
			if(type.getSelectionModel().getSelectedItem()=="disque") {
				ra=t3.getText();
				shape.setDis(Integer.parseInt(ra));
			}

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
	    
	    @FXML
	    public void handleClick() {
	    	/*
	    	 * Code inspiré de https://stackoverflow.com/questions/40516514/remove-a-row-from-a-gridpane/40517410
	    	 */
	    	Set<Node> deleteNodes = new HashSet<>();
	        for (Node child : gp.getChildren()) {
	            // get index from child
	            Integer rowIndex = GridPane.getRowIndex(child);

	            // handle null values for index=0
	            int r = rowIndex == null ? 0 : rowIndex;
	            
	            
	            /*
	             * selection ligne 2
	             */
	            if (r > 2) {
	                // decrement rows for rows after the deleted row
	                GridPane.setRowIndex(child, r-1);
	            } else if (r == 2) {
	                // collect matching rows for deletion
	                deleteNodes.add(child);
	            }
	            /*
	             * selection ligne 3
	             */
	            if (r > 3) {
	                // decrement rows for rows after the deleted row
	                GridPane.setRowIndex(child, r-1);
	            } else if (r == 3) {
	                // collect matching rows for deletion
	                deleteNodes.add(child);
	            }
	        }

	        // remove nodes from row
	        gp.getChildren().removeAll(deleteNodes);

	    	
	    	
	    	if(type.getSelectionModel().getSelectedItem()=="rectangle") {
	    		Label l1 = new Label("largeur");
	    		Label l2 = new Label("hauteur");
	    		
	    		t1 = new TextField("largeur ?");
	    		t2 = new TextField("hauteur ?");
	    		
	    		
	    		gp.add(l1,0,2);
	    		gp.add(l2,0,3);
	    		gp.add(t1,1,2);
	    		gp.add(t2,1,3);
	    		
	    		
	    	}
	    	if(type.getSelectionModel().getSelectedItem()=="disque") {
	    		Label l3 = new Label("rayon");
	    		
	    		t3 = new TextField("rayon ?");
	    		
	    		gp.add(l3,0,2);
	    		gp.add(t3,1,2);

	    	}
	    }
	    

}
