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

import backEnd.Stagiaire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainControllerAnnuaireAdministrateur {


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
		private Button btnModifier;
		@FXML
		private Button btnSupprimer;
		@FXML
		private TableView<Stagiaire> tbStagiaire;
		@FXML
		private TableColumn<Stagiaire, String> colNom;
		@FXML
		private TableColumn<Stagiaire, String> colPrenom;
		
	
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
		
		//Classe qui permet de lire le fichier
		public static List<String> lireFichier() {
		List<String> fichier = new ArrayList<>();														//Liste qui va réceptionner toutes les lignes du fichier

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("noms2.DON"));){		//try with ressource il va fermer le buffered reader automatiquement
			
			String line;																		//On déclare une variable String pour y affecter chaque ligne du fichier

			while((line = bufferedReader.readLine()) != null) { 							//line reçoit la valeur en String de la ligne, si elle est différente de null (et donc qu'il y a encore des chose à lire, on continue, ATTENTION A NE PAS METTRE D'ESPACE DANS LE FICHIER)
				fichier.add(line);															// on ajoute le contenu de la ligne dans la liste (en supposant que NOM et PRENOM soient bien espacés)
			}

		} catch (IOException e) {																// Erreur si le fichier n'est pas trouvé
			e.printStackTrace();
		}
		return fichier;																			// On retourne la liste


	}
	//Methode supprimerStagiaire
	public void supprimerStagiaire() {
		
		
		List<String> fichier = supprimerElement(tfNom.getText());					
		Collections.sort(fichier);							

		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("noms2.DON"))){	

			for (String ligne : fichier) {
				bufferedWriter.write(ligne);
				bufferedWriter.newLine();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
	public static List<String> supprimerElement(String nom) {
		List<String> fichier = new ArrayList<>();
		String[] analyse;
		int compteur = 0;

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("noms2.DON"));) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				analyse = line.split(" ");
				if (analyse[0].equalsIgnoreCase(nom) && compteur == 0) {
					compteur++;
				} else {
					fichier.add(line);
				}
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fichier;

	}

	//HandleButton
		@FXML
		public void handleButtonAction (ActionEvent event) {
			
			if (event.getSource() == btnAjouter) {
				
				ajouterStagiaire();
			}
			else if (event.getSource() == btnSupprimer) {
				
				supprimerStagiaire();
			}
			else if (event.getSource() == btnRechercher) {
				
				recherche();
			}
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
