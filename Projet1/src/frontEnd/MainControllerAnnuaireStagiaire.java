package frontEnd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import backEnd.Personne;
import backEnd.Stagiaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainControllerAnnuaireStagiaire {

	//Declaration
	@FXML
	private TextField tfNom;
	@FXML
	private TextField tfPrenom;
	@FXML
	private Button btnAjouter;
	@FXML
	private Button btnImprimer;
	@FXML
	private Button btnRechercher;
	@FXML
	private TableView<Personne> tbStagiaire;
	@FXML
	private TableColumn<Personne, String> colNom;
	@FXML
	private TableColumn<Personne, String> colPrenom;

	

	
	//Methode ajouter
	
	public void ajouterStagiaire() {

		//Tri
		List<String> fichier = lireFichier();				// On récupère la liste de la méthode lireFichier
		System.out.println(fichier);
		
		fichier.add(tfNom.getText().toUpperCase() + " " + tfPrenom.getText());		// Il serait bon de forcer le prénom a avoir la 1ère lettre en majuscule
		
		Collections.sort(fichier);							// On met les éléments dans l'ordre
		System.out.println(fichier);
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("noms2.DON"))){	

			for (String ligne : fichier) {
				bufferedWriter.write(ligne);
				bufferedWriter.newLine();
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}	
	
	//Methode Lire fichier
	
	public List<String> lireFichier() {
		List<String> fichier = new ArrayList<>();														//Liste qui va réceptionner toutes les lignes du fichier

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("noms2.DON"));){		//try with ressource il va fermer le buffered reader automatiquement
																										//Classe qui permet de lire le fichier
			String line;																		//On déclare une variable String pour y affecter chaque ligne du fichier

			while((line = bufferedReader.readLine()) != null) { 							//line reçoit la valeur en String de la ligne, si elle est différente de null (et donc qu'il y a encore des chose à lire, on continue, ATTENTION A NE PAS METTRE D'ESPACE DANS LE FICHIER)
				fichier.add(line);															// on ajoute le contenu de la ligne dans la liste (en supposant que NOM et PRENOM soient bien espacés)
			}

		} catch (IOException e) {																// Erreur si le fichier n'est pas trouvé
			e.printStackTrace();
		}
		
		return fichier;																			// On retourne la liste
		
		
	}
	
	//HandleButton
	@FXML
	public void handleButtonAction (ActionEvent event) {
		
		if (event.getSource() == btnAjouter) {
			
			ajouterStagiaire();
		}
		else if (event.getSource() == btnRechercher) {
			
			recherche();
		}
		
	}
	
	//getStagiaire
	public ObservableList<Personne> getStagiaire () {
		
		ObservableList<Personne> listStagiaire = FXCollections.observableArrayList();														
		
		String[] x;
			
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("noms2.DON"));){		
																										
			String line;																		

			while((line = bufferedReader.readLine()) != null) { 							
				
				x= line.split(" ");
				
				
				
				listStagiaire.add(new Personne(x[0], x[1]));														
			}

		} catch (IOException e) {																
			e.printStackTrace();
		}
		
		System.out.println(listStagiaire);
		return listStagiaire;																			
		
		
	}
	
	
	
	
	
	//Methode afficher Stagiaire
	
	public void afficherStagiaire() { 
		
		TableView<Personne> table = new TableView<Personne>();
	      final ObservableList<Personne> data = FXCollections.observableArrayList(
	         new Personne("BOULET", "Bruno"),
	         new Personne("TONY", "Cassis")
	         
	      );
	      System.out.println(data);
	      
	      
	      colNom.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));

	     
	      colPrenom.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));
	      		
	      
	      
	      table.setItems(data);
	      table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	     // table.getColumns().addAll(colNom, colPrenom);
		
		
		
		
		
		
		
		
		
		
		//List<Personne> list = this.getStagiaire();
			
			//tbStagiaire.setItems(getStagiaire());
		
	      
	      
	        //TableView<Personne> table = new TableView <Personne>();
			
			
			
			//colNom.setCellValueFactory(new PropertyValueFactory<Personne, String>("nom"));
			
			//colPrenom.setCellValueFactory(new PropertyValueFactory<Personne, String>("prenom"));
			
			//tbStagiaire.getColumns().addAll();
			
			//System.out.println(tbStagiaire);
			
			//tbStagiaire.getItems().clear();
			//tbStagiaire.getItems().addAll(list);
			
		
			
}
	//Methode de recherche

			public List<String> recherche(){ 
				List<String> fichier = lireFichier(); 
				List<String> result = fichier.stream()                
						.filter(line -> line.startsWith(tfNom.getText()))     
						.collect(Collectors.toList());              
					System.out.println(result);
				return result;
			}
}


