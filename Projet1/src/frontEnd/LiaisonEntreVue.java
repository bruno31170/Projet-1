package frontEnd;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LiaisonEntreVue extends Application {
	
	  private BorderPane PanelAnnuaire = new BorderPane();
	    private BorderPane panel1 = new BorderPane();
	    private BorderPane panel2 = new BorderPane();
	    
	    private Label message1 = new Label("connexion reussi");
	    private Button btnConnexion = new Button("Connexion");
	    
	    private Button btnBack= new Button("Retour");
	    private Label message2 = new Label("connexion reussi");
	
	    @Override
	    public void start(Stage stage) throws Exception {
	       
	        /*Actions Boutons*/
	        
	        btnConnexion.setOnAction(new EventHandler<ActionEvent>() {
	
	            @Override
	            public void handle(ActionEvent arg0) {
	                
	                PanelAnnuaire.setCenter(panel2);
	                
	            
	            
	            }
	        });
	        
	       
	        Parent root = FXMLLoader.load(getClass().getResource("InterfaceAnnuaireStagiaire.fxml"));
	        PanelAnnuaire.setCenter(root);
	        Scene scene = new Scene(root);
	        stage.setTitle("Annuaire Stagiaire");
	       
	        //stage.setScene(scene);
	        stage.show();
	
	    }
	    	
		
	//public static void main(String[] args) {
		launch(args);
	}
}	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	        
	    
	   
	


