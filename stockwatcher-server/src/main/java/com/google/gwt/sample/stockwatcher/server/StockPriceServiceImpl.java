package com.google.gwt.sample.stockwatcher.server;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import com.google.gwt.sample.stockwatcher.shared.StockPriceService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

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
	}
	

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
	public String[] loadCarteMagic(boolean soloPersonali) { //SECONDO ME LA PAGINA è DA CAMBIARE, COSì DA AVERE TRE PULSANTI: UNO PER CARTE POKEMON UNO MAGIC E UNO YUGIOH, MAGARI TUTTE E TRE COMUNQUE SULLA STESSA TABELLA, SVUOTANDOLA DI VOLTA IN VOLTA. (POI IMPLEMENTARE UN FILTRO O CHE SO IO)
		// TODO Auto-generated method stub
		DB db = getDB();
		Map<Integer, String> cardMap;
		if(soloPersonali) {
		cardMap = db.hashMap(PERSONAL_MAGICCARD_TREEMAP_NAME, Serializer.INTEGER, Serializer.STRING).createOrOpen();}
		else {
		cardMap = db.hashMap(MAGIC_HASHMAP_NAME, Serializer.INTEGER, Serializer.STRING).createOrOpen();
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
	public void addCard(String actualUser, String stringCard) {
		// TODO Auto-generated method stub
		
		
		
		DB db = getDB();		//PERSONAL_CARD_TREEMAP_NAME
		
		Map<Integer, String> cardPersonalMap = db.hashMap(PERSONAL_MAGICCARD_TREEMAP_NAME)
                .keySerializer(Serializer.INTEGER)
                .valueSerializer(Serializer.STRING)
                .createOrOpen();
		
		 int newKey = 1;
		 while (cardPersonalMap.containsKey(newKey)) {
	            newKey++;
	        }
		 
			System.out.println("chiave: " + newKey + "; Valore: " +stringCard);

		 
		 cardPersonalMap.put(newKey, stringCard);
		 db.commit();
		 		
		//ora provo a leggere la più alta delle chiavi e a inserire come chiave il numero successivo, poi 
		//actualuser non si può fare, lo devo mettere nella carta, quindi di là metto il get e set e la variabile per user. Poi vie
		//stringcard
		//poi printo per avere chiaro cosa sto facendo 
		
	}


	@Override
	public void rimuoviCarta(String stringCard) {
		// TODO Auto-generated method stub
		
		DB db = getDB();
		
		Map<Integer, String> cardPersonalMap = db.hashMap(PERSONAL_MAGICCARD_TREEMAP_NAME)
                .keySerializer(Serializer.INTEGER)
                .valueSerializer(Serializer.STRING)
                .createOrOpen();
		
		Iterator<Map.Entry<Integer, String>> iterator = cardPersonalMap.entrySet().iterator();
		while (iterator.hasNext()) {
		    Map.Entry<Integer, String> entry = iterator.next();
		    if (stringCard.equals(entry.getValue())) {
		        // Remove the entry if the value matches the target
		        iterator.remove();
		    }
		}
		
		 db.commit();
		
	}
	
	
	
	
	
}
