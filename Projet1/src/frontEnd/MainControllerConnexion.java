package frontEnd;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class MainControllerConnexion implements Initializable{

	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private AnchorPane rootPane;
	
	//Déclaration des composant du controller
	
	@FXML
	private TextField tfIdentifiant;
	@FXML
	private TextField tfMotDePasse;
	@FXML
	private Button btnConnexion;
	
	MainControllerAnnuaireStagiaire personne = new MainControllerAnnuaireStagiaire();
	
	//Methode si on appuie sur un bouton
		
	@FXML
	public void handleButtonAction (ActionEvent event) throws IOException  { 

		if (event.getSource() == btnConnexion) {

			if (connexion()== true) {
			Parent root = FXMLLoader.load(getClass().getResource("InterfaceAnnuaireStagiaire.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			personne.afficherStagiaire();
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION");
				alert.setContentText("Connexion echoue");
				alert.show();
			}
		}
		
		
		
	}
	
	
	
	//Methode initialisation du controller
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	//Methode connexion
	
	public boolean connexion() {

		HashMap<String, String> identifient = new HashMap<String, String>();

		identifient.put("user", "user1");
		identifient.put("admin", "admin1");

		if (identifient.containsKey(tfIdentifiant.getText())) {


			if (identifient.get(tfIdentifiant.getText()).equals(tfMotDePasse.getText())) {

				
				return true;

			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION");
				alert.setContentText("Mauvais mot de passe ");
				alert.show();
				
				return false;
			}


		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMATION");
			alert.setContentText("Mauvais identifiant ");
			alert.show();
			
			return false;
		}
	}
}

