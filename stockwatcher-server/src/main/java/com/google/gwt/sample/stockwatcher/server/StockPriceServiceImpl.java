package com.google.gwt.sample.stockwatcher.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import com.google.gson.Gson;
import com.google.gwt.core.client.impl.AsyncFragmentLoader.Logger;
import com.google.gwt.sample.stockwatcher.shared.StockPrice;
import com.google.gwt.sample.stockwatcher.shared.StockPriceService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

// Here name and the last element of path must be the same value 
// used in the @RemoteServiceRelativePath used to annotate the service remote 
// interface (the one that extends RemoteService)
@WebServlet(name = "stockPrices", urlPatterns = "/app/stockPrices")
public class StockPriceServiceImpl extends RemoteServiceServlet implements StockPriceService, MapDBConstants {
	private static final long serialVersionUID = 4192379456341403664L;
	private static final double MAX_PRICE = 100.0; // $100.00
	private static final double MAX_PRICE_CHANGE = 0.02; // +/- 2%

	
	//cambio content con key e vedo se migliora comunque ho la versione vecchia salvata
	@Override
	public boolean controlAccount(String user) {
	    DB db = getDB();
	    Map<String, String> map =  db.hashMap(USER_TREEMAP_NAME).keySerializer(Serializer.STRING)
	            .valueSerializer(Serializer.STRING)
	            .createOrOpen();
	    boolean userExists = map.containsKey(user); // Check if the user (key) exists

 

	    return userExists;
	}

	@Override
	public void addAccount(String user, String pwd) {
	    DB db = getDB();
	    Map<String, String> map =  db.hashMap(USER_TREEMAP_NAME).keySerializer(Serializer.STRING)
	            .valueSerializer(Serializer.STRING)
	            .createOrOpen();

	  

	    map.put(user, pwd);

	    db.commit(); // Always remember to commit after modifications
	 //   db.close(); se lo chiudo poi risulta chiuso e non me lo riapre
	}

	/*
	 * We store the DB in the servlet context
	 * to implement a poor man's singleton
	 */
	private DB getDB() {
		ServletContext context = this.getServletContext();
		synchronized (context) {
			DB db = (DB)context.getAttribute("DB");
			if(db == null) {
				db = DBMaker.fileDB(new File(DB_FILENAME)).closeOnJvmShutdown().make();
				context.setAttribute("DB", db);
			}
			return db;
		}
	}



	@Override
	public boolean login(String checkUser, String checkPwd) {
	    DB db = getDB();
	    Map<String, String> map = db.hashMap(USER_TREEMAP_NAME).keySerializer(Serializer.STRING)
	            .valueSerializer(Serializer.STRING)
	            .createOrOpen();
	    
	    // Log the state of the map and user existence
	    System.out.println("Map contents:");
	    for (Map.Entry<String, String> entry : map.entrySet()) {
	        System.out.println("User: " + entry.getKey() + ", Password: " + entry.getValue());
	    }	 

	    // Check if the user exists
	    if (map.containsKey(checkUser)) {
	        String storedPwd = map.get(checkUser);

	        // Check if the provided password matches the stored password for the user
	        if (storedPwd.equals(checkPwd)) {
	            return true;  // User exists and password is correct
	        }
	    }

	    return false;  // User does not exist or password is incorrect
	}
	
	
	
	public void test() {
		
		
	}



	@Override
	public String[] loadCarteMagic(boolean cercaAcquirenti, boolean soloPersonali) { //SECONDO ME LA PAGINA è DA CAMBIARE, COSì DA AVERE TRE PULSANTI: UNO PER CARTE POKEMON UNO MAGIC E UNO YUGIOH, MAGARI TUTTE E TRE COMUNQUE SULLA STESSA TABELLA, SVUOTANDOLA DI VOLTA IN VOLTA. (POI IMPLEMENTARE UN FILTRO O CHE SO IO)
		// TODO Auto-generated method stub
		DB db = getDB();
		Map<Integer, String> cardMap;
		if(soloPersonali) {
		cardMap = db.hashMap(PERSONAL_MAGICCARD_TREEMAP_NAME, Serializer.INTEGER, Serializer.STRING).createOrOpen();}
		else {
		cardMap = db.hashMap(MAGIC_HASHMAP_NAME, Serializer.INTEGER, Serializer.STRING).createOrOpen();
		}
		if(cercaAcquirenti) {
			cardMap= db.hashMap(PERSONAL_DESIDERATA_MAGICCARD_TREEMAP_NAME)
	                .keySerializer(Serializer.INTEGER)
	                .valueSerializer(Serializer.STRING)
	                .createOrOpen();
		}
		
		List<String> symbols = new ArrayList<String>();
		Set<Integer> keys = cardMap.keySet();
		for(Integer key : keys) {
			symbols.add(cardMap.get(key));
			System.out.println("chiave: " + key + "; Valore: " +cardMap.get(key));
		}
		return symbols.toArray(new String[0]);
		
		
			}



	@Override
	public void addCard(boolean desiderata, String actualUser, String stringCard) {
		// TODO Auto-generated method stub
		
		
		
		DB db = getDB();		//PERSONAL_CARD_TREEMAP_NAME
		
		
		Map<Integer, String> cardPersonalMap = db.hashMap(PERSONAL_MAGICCARD_TREEMAP_NAME)
                .keySerializer(Serializer.INTEGER)
                .valueSerializer(Serializer.STRING)
                .createOrOpen();
		if(desiderata) {
			
			cardPersonalMap = db.hashMap(PERSONAL_DESIDERATA_MAGICCARD_TREEMAP_NAME)
	                .keySerializer(Serializer.INTEGER)
	                .valueSerializer(Serializer.STRING)
	                .createOrOpen();
		}
		
		 int newKey = 1;
		 while (cardPersonalMap.containsKey(newKey)) {
	            newKey++;
	        }
		 
			//System.out.println("chiave: " + newKey + "; Valore: " +stringCard);

		 
		 cardPersonalMap.put(newKey, stringCard);
		 db.commit();
		 		
		//ora provo a leggere la più alta delle chiavi e a inserire come chiave il numero successivo, poi 
		//actualuser non si può fare, lo devo mettere nella carta, quindi di là metto il get e set e la variabile per user. Poi vie
		//stringcard
		//poi printo per avere chiaro cosa sto facendo 
		
	}



	@Override
	public boolean rimuoviCarta(boolean desiderata, String stringCard) {
		// TODO Auto-generated method stub
		
		DB db = getDB();
		boolean deleted = false;
		
		Map<Integer, String> cardPersonalMap = db.hashMap(PERSONAL_MAGICCARD_TREEMAP_NAME)
                .keySerializer(Serializer.INTEGER)
                .valueSerializer(Serializer.STRING)
                .createOrOpen();
		if(desiderata) {
			cardPersonalMap = db.hashMap(PERSONAL_DESIDERATA_MAGICCARD_TREEMAP_NAME)
	                .keySerializer(Serializer.INTEGER)
	                .valueSerializer(Serializer.STRING)
	                .createOrOpen();
		}
	
		
		Iterator<Map.Entry<Integer, String>> iterator = cardPersonalMap.entrySet().iterator();
		while (iterator.hasNext()) {
		    Map.Entry<Integer, String> entry = iterator.next();
		    if (stringCard.equals(entry.getValue())) {
		        // cancella se uguale al target
		        iterator.remove();
		        deleted = true;
		        break;
		    }
		}
		
		 db.commit();
		 return deleted;
		
	}



	@Override
	public void addProposta(String scambioString) {
		// TODO Auto-generated method stub
		
		
			DB db = getDB();		
		
		
		Map<Integer, String> ScambioPropostaMap = db.hashMap(PROPOSTA_TREEMAP_NAME)
                .keySerializer(Serializer.INTEGER)
                .valueSerializer(Serializer.STRING)
                .createOrOpen();
		
		
		int newKey = 1;
		 while (ScambioPropostaMap.containsKey(newKey)) {
	            newKey++;
	        }
		 
			//System.out.println("chiave: " + newKey + "; Valore: " +stringCard);

		 
		 ScambioPropostaMap.put(newKey, scambioString);
		 db.commit();
		 
		 
		 System.out.println("Map contents:");
		    for (Map.Entry<Integer, String> entry : ScambioPropostaMap.entrySet()) {
		        System.out.println( entry.getValue()
		        		+ "\n" );
		    }	
		
	}



	@Override
	public String[] loadProposteScambi() {
		// TODO Auto-generated method stub
		
		DB db = getDB();		
		
		
		Map<Integer, String> ScambioPropostaMap = db.hashMap(PROPOSTA_TREEMAP_NAME)
                .keySerializer(Serializer.INTEGER)
                .valueSerializer(Serializer.STRING)
                .createOrOpen();
		
		List<String> symbols = new ArrayList<String>();
		Set<Integer> keys = ScambioPropostaMap.keySet();
		for(Integer key : keys) {
			symbols.add(ScambioPropostaMap.get(key));
			System.out.println("chiave: " + key + "; Valore: " +ScambioPropostaMap.get(key));
		}
		return symbols.toArray(new String[0]);
		
	
	}



	@Override
	public void gestisciScambio(boolean accettato, String scambio, String proponente, List<String> actualCard,
			String actualUser, List<String> newCard) {
		// TODO Auto-generated method stub
		
		DB db = getDB();
		
		Map<Integer, String> ScambioPropostaMap = db.hashMap(PROPOSTA_TREEMAP_NAME)
                .keySerializer(Serializer.INTEGER)
                .valueSerializer(Serializer.STRING)
                .createOrOpen();
	
		//tolgo l'offerta
		Iterator<Map.Entry<Integer, String>> iterator = ScambioPropostaMap.entrySet().iterator();
		while (iterator.hasNext()) {
		    Map.Entry<Integer, String> entry = iterator.next();
		    if (scambio.equals(entry.getValue())) {
		        // cancella se uguale al target
		        iterator.remove();		        
		        break;
		    }
		}
		
		if(accettato) {//se rifiutato son in realtà a posto
		
			ScambioPropostaMap	= db.hashMap(PERSONAL_MAGICCARD_TREEMAP_NAME)
	                .keySerializer(Serializer.INTEGER)
	                .valueSerializer(Serializer.STRING)
	                .createOrOpen();
			
			for(String carta : actualCard) {
				Iterator<Map.Entry<Integer, String>> iterator2 = ScambioPropostaMap.entrySet().iterator();
				while (iterator2.hasNext()) { 
				    Map.Entry<Integer, String> entry = iterator2.next();
				    if (carta.equals(entry.getValue())) {
				        // cancella se uguale al target
				        iterator2.remove();		        
				        break;
				    }
				}
			}
			
			int newKey = 1;
			 while (ScambioPropostaMap.containsKey(newKey)) {
		            newKey++;
		        }
			
			for(String carta : newCard){
				
				 ScambioPropostaMap.put(newKey, carta);
				 newKey++;
				
			}
			
			
		}
		
		
		 db.commit();
		
		
		
	}
	
	
	
	
	
}
