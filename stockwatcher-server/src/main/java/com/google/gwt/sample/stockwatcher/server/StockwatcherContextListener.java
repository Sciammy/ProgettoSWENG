package com.google.gwt.sample.stockwatcher.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;




@WebListener
public class StockwatcherContextListener implements ServletContextListener,  MapDBConstants {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		 
		if(new File("magic_cards.json").exists() && new File("pokemon_cards.json").exists() && new File("yugioh_cards.json").exists() ) {
			try {
								
				Gson gson = new Gson();
				
				Magic_card[] magicCards;
				Pokemon_card[] pokemonCards;
				Yugioh_card[] yugiohCards;


				
				try (BufferedReader br = new BufferedReader(new FileReader("magic_cards.json"))) {
				    magicCards = gson.fromJson(br, Magic_card[].class);

		//		    for (Magic_card card : magicCards) {
			//	        System.out.println(card.toString());
				//    }
				}
				
				try (BufferedReader br2 = new BufferedReader(new FileReader("pokemon_cards.json"))) {
					pokemonCards = gson.fromJson(br2, Pokemon_card[].class); }
				

				try (BufferedReader br3 = new BufferedReader(new FileReader("yugioh_cards.json"))) {
					yugiohCards = gson.fromJson(br3, Yugioh_card[].class); }
				
			//	System.out.println("PRINTED");
				
				DB db = DBMaker.fileDB(new File(DB_FILENAME)).make();
				
				//DB db = DBMaker.fileDB(new File(DB_CARD_FILENAME)).make();
				//Map<String, Magic_card> cardMap = db.treeMap(ID_TREEMAP_NAME, Serializer.STRING, Serializer.JAVA).create();
				
				Map<String, String> cardMap = db.hashMap(ID_TREEMAP_NAME)
                        .keySerializer(Serializer.STRING)
                        .valueSerializer(Serializer.STRING)
                        .create();
				
			//	System.out.println("MAPPA CREATA");
				MagicCardSerializer serialMC = new MagicCardSerializer();
				PokemonCardSerializer serialPM = new PokemonCardSerializer();
				YugiohCardSerializer serialYO = new YugiohCardSerializer();


				
				   for (int i = 0; i < magicCards.length; i++) {
					   String json = serialMC.serialize(magicCards[i]);
		                cardMap.put(Integer.toString(i)+"MC", json);
		            }
				   
				   for (int i = 0; i < pokemonCards.length; i++) {
					   String json = serialPM.serialize(pokemonCards[i]);
		                cardMap.put(Integer.toString(i)+"PM", json);
		            }
				   
				   for (int i = 0; i < yugiohCards.length; i++) {
					   String json = serialYO.serialize(yugiohCards[i]);
		                cardMap.put(Integer.toString(i)+"YO", json);
		            }

				   
				   db.commit();	
					System.out.println("DATI INSERITI");

				   
				   
				 
				//	System.out.println("Map contents:"); printo per assicurarmi di aver preso tutto dentro
				    for (Entry<String, String> entry : cardMap.entrySet())  {
				       System.out.println("CHIAVE: " + entry.getKey() + ", VALORE: " + entry.getValue());
				    }	
				
				
		            			
				db.close(); 	
				
		        
		        
		     	deleteFile("magic_cards.json");
		     	deleteFile("pokemon_cards.json");
		     	deleteFile("yugioh_cards.json");
		        
		        
				
				
			} catch (IOException e) {
				System.out.println("ERROOOOOOORE " + e);
				
				}	
		}
		
		
			
			
			
	}
	

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	
	public void deleteFile(String path) {
		File fileToDelete = new File(path);

        // Check if the file exists before attempting to delete it
        if (fileToDelete.exists()) {
            // Attempt to delete the file
            boolean isDeleted = fileToDelete.delete();

            // Check if the file was successfully deleted
            if (isDeleted) {
                System.out.println("File " + path + " deleted successfully.");
            } else {
                System.err.println("Failed to delete the file " + path);
            }
        } else {
            System.err.println("File" + path + "does not exist.");
        }
	}
	
}


