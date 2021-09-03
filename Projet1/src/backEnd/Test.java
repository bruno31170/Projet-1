package backEnd;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;




public class Test {

	//public static void main(String[] args) {

		//Methode de tri 1

		List<String> maList = Arrays.asList("girafe", "chameau", "chat", "poisson", "cachalot", "loci");


		System.out.println(maList);		
		maList.sort(Collator.getInstance());
		System.out.println(maList);


		System.out.println(method(maList, "ch"));



	}	

	//Methode de recherche

	public static List<String> method(List<String> lines, String l){ 

		List<String> result = lines.stream()                
				.filter(line -> line.startsWith(l))     
				.collect(Collectors.toList());              

		return result;
	}	

	
	// lecture simple d'un file en java8

			try (BufferedReader bufferedReader = new BufferedReader(new FileReader("noms.DON"));){		//try with ressource il va fermer le buffered reader automatiquement
				List<String> tab = new ArrayList<String>();
				String line;
				
				while((line = bufferedReader.readLine()) != null) {
					tab.add(line);
				}
				
					for (String string : tab) {
						System.out.println(string);	
						
						}
					System.out.println(tab);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//Recuperer les informations du fichier separement dans un tableau
			
			List<String> maList = new ArrayList<String>();
			
			String [] line;
			
			maList.add("BOULET Bruno");
			maList.add("TOUR Tibo");
			maList.add("VIOLETA Ana");

			for (int i = 0; i < maList.size(); i++) {
				
				line = maList.get(i).split(" ");

				for (int j = 0; j < line.length-1; j++) {
									
					Stagiaire stagiaire = new Stagiaire (line[j], line[j+1]);
					System.out.println(stagiaire);
				}
				
			
			
			}
			
			if (identifient.containsKey("user".getText())) {
				
				System.out.println("test");
				
				if (identifient.get("user".getText()).equals("user1".getText())) {
					
					System.out.println("juste");
				}
					else {
						System.out.println("faux");
					}
			
}

}

	




