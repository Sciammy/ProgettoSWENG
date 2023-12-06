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

// Here name and the last element of path must be the same value
// used in the @RemoteServiceRelativePath used to annotate the service remote
// interface (the one that extends RemoteService)
@WebServlet(name = "stockPrices", urlPatterns = "/app/stockPrices")
public class StockPriceServiceImpl extends RemoteServiceServlet implements StockPriceService, MapDBConstants {
	private static final long serialVersionUID = 4192379456341403664L;


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


	@Override
	public String[] loadCarte(String contesto, boolean cercaAcquirenti, boolean soloPersonali) { 

		DB db = getDB();
		Map<Integer, String> cardMap;
		System.out.println("entrato");
		if(soloPersonali) {
			if(contesto.equals("Magic"))
					cardMap = db.hashMap(PERSONAL_MAGICCARD_TREEMAP_NAME, Serializer.INTEGER, Serializer.STRING).createOrOpen();
			else if (contesto.equals("Pokemon"))
				    cardMap = db.hashMap(PERSONAL_POKEMONCARD_TREEMAP_NAME, Serializer.INTEGER, Serializer.STRING).createOrOpen();
			else
				cardMap = db.hashMap(PERSONAL_YUGIOHCARD_TREEMAP_NAME, Serializer.INTEGER, Serializer.STRING).createOrOpen();
		}
		else {
			if(contesto.equals("Magic"))
					cardMap = db.hashMap(MAGIC_HASHMAP_NAME, Serializer.INTEGER, Serializer.STRING).createOrOpen();
			else if (contesto.equals("Pokemon")) {
				cardMap = db.hashMap(POKEMON_HASHMAP_NAME, Serializer.INTEGER, Serializer.STRING).createOrOpen();
			System.out.println("Pokemon preso");}
			else
				cardMap = db.hashMap(YUGIOH_HASHMAP_NAME, Serializer.INTEGER, Serializer.STRING).createOrOpen();
		}
		if(cercaAcquirenti) {
			if(contesto.equals("Magic"))
					cardMap= db.hashMap(PERSONAL_DESIDERATA_MAGICCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();
			else if (contesto.equals("Pokemon"))
				cardMap= db.hashMap(PERSONAL_DESIDERATA_POKEMONCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();
				else
				cardMap= db.hashMap(PERSONAL_DESIDERATA_YUGIOHCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();

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
	public String[] loadCartePersonali(String contesto) {
			// TODO Auto-generated method stub

			DB db = getDB();
			Map<Integer, String> cardMap;
			System.out.println("entrato");
			if(contesto.equals("Magic"))
				cardMap = db.hashMap(PERSONAL_MAGICCARD_TREEMAP_NAME, Serializer.INTEGER, Serializer.STRING).createOrOpen();
			else if (contesto.equals("Pokemon"))
			    cardMap = db.hashMap(PERSONAL_POKEMONCARD_TREEMAP_NAME, Serializer.INTEGER, Serializer.STRING).createOrOpen();
			else
				cardMap = db.hashMap(PERSONAL_YUGIOHCARD_TREEMAP_NAME, Serializer.INTEGER, Serializer.STRING).createOrOpen();

			List<String> symbols = new ArrayList<String>();
			Set<Integer> keys = cardMap.keySet();
			for(Integer key : keys) {
				symbols.add(cardMap.get(key));
				System.out.println("chiave: " + key + "; Valore: " +cardMap.get(key));
			}
			return symbols.toArray(new String[0]);
	}

	@Override
	public String[] loadDeckPersonali(String contesto) {
		// TODO Auto-generated method stub

		DB db = getDB();
		Map<String, String> deckMap;
		System.out.println("entrato");
		if(contesto.equals("Magic"))
			deckMap = db.hashMap(PERSONAL_MAGIC_DECK_HASHMAP_NAME, Serializer.STRING, Serializer.STRING).createOrOpen();
		else if (contesto.equals("Pokemon"))
		    deckMap = db.hashMap(PERSONAL_POKEMON_DECK_HASHMAP_NAME, Serializer.STRING, Serializer.STRING).createOrOpen();
		else
			deckMap = db.hashMap(PERSONAL_YUGIOH_DECK_HASHMAP_NAME, Serializer.STRING, Serializer.STRING).createOrOpen();

		List<String> symbols = new ArrayList<String>();
		Set<String> keys = deckMap.keySet();
		for(String key : keys) {
			symbols.add(deckMap.get(key));
			System.out.println("chiave: " + key + "; Valore: " +deckMap.get(key));
		}
		return symbols.toArray(new String[0]);
	}

	@Override
	public String loadDeckList(String deckName, String contesto) {

		deckName = deckName.toUpperCase();
		DB db = getDB();
		Map<String, String> deckMap;
		System.out.println("inside");
		if(contesto.equals("Magic")) {
			deckMap = db.hashMap(PERSONAL_MAGIC_DECK_HASHMAP_NAME, Serializer.STRING, Serializer.STRING)
			.createOrOpen();
		} else if (contesto.equals("Pokemon")) {
		    deckMap = db.hashMap(PERSONAL_POKEMON_DECK_HASHMAP_NAME, Serializer.STRING, Serializer.STRING)
		    .createOrOpen();
		} else {
			deckMap = db.hashMap(PERSONAL_YUGIOH_DECK_HASHMAP_NAME, Serializer.STRING, Serializer.STRING)
			.createOrOpen();
		}
		String deckStringResult = "";
		Set<String> keys = deckMap.keySet();
		for(String key : keys) {
			if(key.equals(deckName)) {
				deckStringResult = deckMap.get(key);
			}
		}
		return deckStringResult;
	}

	public String[] loadCarteFromId(List<Integer> cardsId, String contesto) {
		//preso in input un array con Id delle carte, restituisce un array con i file Json delle corrispondenti
		DB db = getDB();
		System.out.println("in arrivo dal main: " + cardsId.toString());
		Map<Integer, String> cardMap;
		if(contesto.equals("Magic")) {
			cardMap = db.hashMap(MAGIC_HASHMAP_NAME, Serializer.INTEGER, Serializer.STRING)
			.createOrOpen();
		} else if (contesto.equals("Pokemon")) {
			cardMap = db.hashMap(POKEMON_HASHMAP_NAME, Serializer.INTEGER, Serializer.STRING)
		    .createOrOpen();
		} else {
			cardMap = db.hashMap(YUGIOH_HASHMAP_NAME, Serializer.INTEGER, Serializer.STRING)
			.createOrOpen();
		}
		List<String> cardsJson = new ArrayList<String>();
		Set<Integer> keys = cardMap.keySet();
		for(Integer cardInDeck : cardsId) {		
			if(keys.contains(cardInDeck)) {
				cardsJson.add(cardMap.get(cardInDeck));
				System.out.println("chiave: " + cardInDeck + "; Valore: " +cardMap.get(cardInDeck));
			}
		}
		return cardsJson.toArray(new String[0]);
	}





	@Override
	public void addCard(String contesto, boolean desiderata, String actualUser, String stringCard) {

		DB db = getDB();
		Map<Integer, String> cardPersonalMap;
		if(!desiderata) {
			if(contesto.equals("Magic"))
		cardPersonalMap = db.hashMap(PERSONAL_MAGICCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();
			else if (contesto.equals("Pokemon"))
				cardPersonalMap = db.hashMap(PERSONAL_POKEMONCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();
			else
				cardPersonalMap = db.hashMap(PERSONAL_YUGIOHCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();

		}
		else {
			if(contesto.equals("Magic"))
			cardPersonalMap = db.hashMap(PERSONAL_DESIDERATA_MAGICCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();
			else if (contesto.equals("Pokemon"))
				cardPersonalMap = db.hashMap(PERSONAL_DESIDERATA_POKEMONCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();
			else
				cardPersonalMap = db.hashMap(PERSONAL_DESIDERATA_YUGIOHCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();
		}

		//prendo il primo intero disponibile
		 int newKey = 1;
		 while (cardPersonalMap.containsKey(newKey)) {
	            newKey++;
	        }

		 cardPersonalMap.put(newKey, stringCard);
		 db.commit();

	}



	@Override
	public boolean rimuoviCarta(String contesto, boolean desiderata, String stringCard) {
		// TODO Auto-generated method stub

		DB db = getDB();
		boolean deleted = false;
		Map<Integer, String> cardPersonalMap;
		if(!desiderata) {
			if(contesto.equals("Magic"))
		cardPersonalMap = db.hashMap(PERSONAL_MAGICCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();
			else if (contesto.equals("Pokemon"))
				cardPersonalMap = db.hashMap(PERSONAL_POKEMONCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();
			else
				cardPersonalMap = db.hashMap(PERSONAL_YUGIOHCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();

		}
		else {
			if(contesto.equals("Magic"))
			cardPersonalMap = db.hashMap(PERSONAL_DESIDERATA_MAGICCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();
			else if (contesto.equals("Pokemon"))
				cardPersonalMap = db.hashMap(PERSONAL_DESIDERATA_POKEMONCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();
			else
				cardPersonalMap = db.hashMap(PERSONAL_DESIDERATA_YUGIOHCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();
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

		DB db = getDB();

		Map<Integer, String> ScambioPropostaMap = db.hashMap(PROPOSTA_TREEMAP_NAME)
                .keySerializer(Serializer.INTEGER)
                .valueSerializer(Serializer.STRING)
                .createOrOpen();

		int newKey = 1;
		 while (ScambioPropostaMap.containsKey(newKey)) {
	            newKey++;
	        }
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
	public void gestisciScambio(String context, boolean accettato, String scambio, String proponente, List<String> actualCard,
			String actualUser, List<String> newCard) {

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

			Map<Integer, String> ScambioPropostaMap2;

			if(context.equals("Magic"))
			ScambioPropostaMap2	= db.hashMap(PERSONAL_MAGICCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();
			else if(context.equals("Pokemon"))
			ScambioPropostaMap2	= db.hashMap(PERSONAL_POKEMONCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();
			else
			ScambioPropostaMap2	= db.hashMap(PERSONAL_YUGIOHCARD_TREEMAP_NAME).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.STRING).createOrOpen();

			for(String carta : actualCard) {
				Iterator<Map.Entry<Integer, String>> iterator2 = ScambioPropostaMap2.entrySet().iterator();
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
			 while (ScambioPropostaMap2.containsKey(newKey)) {
		            newKey++;
		        }
			for(String carta : newCard){
				 ScambioPropostaMap2.put(newKey, carta);
				 newKey++;
			}
		}
		 db.commit();
	}

	@Override
	public boolean controlDeckName(String deckName, String contesto) {
		//controlla che il nome scelto per il deck non esista già per quel gioco
		//resituisce TRUE-> il nome esiste già | FALSE-> il nome è nuovo
		DB db = getDB();
		Map<String, String> deckMap;
		boolean deckExists = false;

		if(contesto.equals("Magic")) {
			deckMap = db.hashMap(PERSONAL_MAGIC_DECK_HASHMAP_NAME)
					.keySerializer(Serializer.STRING)
					.valueSerializer(Serializer.STRING)
					.createOrOpen();
			deckExists = deckMap.containsKey(deckName);
		}else if (contesto.equals("Pokemon")) {
			deckMap = db.hashMap(PERSONAL_POKEMON_DECK_HASHMAP_NAME)
					.keySerializer(Serializer.STRING)
					.valueSerializer(Serializer.STRING)
					.createOrOpen();
			deckExists = deckMap.containsKey(deckName);
		}else {
			deckMap = db.hashMap(PERSONAL_YUGIOH_DECK_HASHMAP_NAME)
					.keySerializer(Serializer.STRING)
					.valueSerializer(Serializer.STRING)
					.createOrOpen();
			deckExists = deckMap.containsKey(deckName);
		}

		return deckExists;
	}

	@Override
	public void addNewDeck(String deckName, String stringDeck, String contesto, String actualUser) {
		deckName = deckName.toUpperCase();
		if(!controlDeckName(deckName, contesto)) {
		//aggiunge un nuovo deck al db per il determinato gioco
			DB db = getDB();
			Map<String, String> mapDeck;

			if(contesto.equals("Magic")) {
				mapDeck = db.hashMap(PERSONAL_MAGIC_DECK_HASHMAP_NAME)
					.keySerializer(Serializer.STRING)
					.valueSerializer(Serializer.STRING)
					.createOrOpen();
				mapDeck.put(deckName, stringDeck);
			}else if (contesto.equals("Pokemon")) {
				mapDeck = db.hashMap(PERSONAL_POKEMON_DECK_HASHMAP_NAME)
					.keySerializer(Serializer.STRING)
					.valueSerializer(Serializer.STRING)
					.createOrOpen();
				mapDeck.put(deckName, stringDeck);
			}else {
				mapDeck = db.hashMap(PERSONAL_YUGIOH_DECK_HASHMAP_NAME)
					.keySerializer(Serializer.STRING)
					.valueSerializer(Serializer.STRING)
					.createOrOpen();
				mapDeck.put(deckName, stringDeck);
			}
			System.out.println("- added new " + contesto + " deck: " + stringDeck + "/ made by: " + actualUser);
			db.commit();
		}
	}

	@Override
	public void changeDeckName(String oldDeckName, String newDeckName, String newDeckString, String contesto){
		//crea una nuove voce nel Db( con lo stesso Value) e cancella quella vecchia
		//essendo il nome anche Chiave del deck occorre un metodo dedicato
		oldDeckName = oldDeckName.toUpperCase();
		newDeckName = newDeckName.toUpperCase();
		if(!controlDeckName(newDeckName, contesto)) {
			DB db = getDB();
			Map<String, String> mapDeck;
			if(contesto.equals("Magic")) {
				mapDeck = db.hashMap(PERSONAL_MAGIC_DECK_HASHMAP_NAME)
					.keySerializer(Serializer.STRING)
					.valueSerializer(Serializer.STRING)
					.createOrOpen();
				if(mapDeck.containsKey(oldDeckName)) {
					mapDeck.put(newDeckName, newDeckString);
					mapDeck.remove(oldDeckName);
					System.out.println(oldDeckName +" aggiornato in " + newDeckName + ": \n" + mapDeck.get(newDeckName));
				}
			}else if (contesto.equals("Pokemon")) {
				mapDeck = db.hashMap(PERSONAL_POKEMON_DECK_HASHMAP_NAME)
					.keySerializer(Serializer.STRING)
					.valueSerializer(Serializer.STRING)
					.createOrOpen();
				if(mapDeck.containsKey(oldDeckName)) {
					mapDeck.put(newDeckName, newDeckString);
					mapDeck.remove(oldDeckName);
					System.out.println(oldDeckName +" aggiornato in " + newDeckName + ": \n" + mapDeck.get(newDeckName));
				}
			}else {
				mapDeck = db.hashMap(PERSONAL_YUGIOH_DECK_HASHMAP_NAME)
					.keySerializer(Serializer.STRING)
					.valueSerializer(Serializer.STRING)
					.createOrOpen();
				if(mapDeck.containsKey(oldDeckName)) {
					mapDeck.put(newDeckName, newDeckString);
					mapDeck.remove(oldDeckName);
					System.out.println(oldDeckName +" aggiornato in " + newDeckName + ": \n" + mapDeck.get(newDeckName));
				}
			}
			db.commit();
		}
	}

	@Override
	public void removeDeck(String deck, String contesto) {
		deck = deck.toUpperCase();
		DB db = getDB();
		Map<String, String> mapDeck;
		if(contesto.equals("Magic")) {
			mapDeck = db.hashMap(PERSONAL_MAGIC_DECK_HASHMAP_NAME)
				.keySerializer(Serializer.STRING)
				.valueSerializer(Serializer.STRING)
				.createOrOpen();
			if(mapDeck.containsKey(deck)) {
				mapDeck.remove(deck);
				System.out.println(deck + " - eliminato con successo");
			}
		}else if (contesto.equals("Pokemon")) {
			mapDeck = db.hashMap(PERSONAL_POKEMON_DECK_HASHMAP_NAME)
				.keySerializer(Serializer.STRING)
				.valueSerializer(Serializer.STRING)
				.createOrOpen();
			if(mapDeck.containsKey(deck)) {
				mapDeck.remove(deck);
				System.out.println(deck + " - eliminato con successo");
			}
		}else {
			mapDeck = db.hashMap(PERSONAL_YUGIOH_DECK_HASHMAP_NAME)
				.keySerializer(Serializer.STRING)
				.valueSerializer(Serializer.STRING)
				.createOrOpen();
			if(mapDeck.containsKey(deck)) {
				mapDeck.remove(deck);
				System.out.println(deck + " - eliminato con successo");
			}
		}
		db.commit();
	}

	@Override
	public void updateDeck(String deckName, String deckString, String contesto) {
		//per aggiungere o rimuovere una carta dal deck si aggiorna il Json di quel deck
		deckName = deckName.toUpperCase();
		DB db = getDB();
		Map<String, String> mapDeck;
		if(contesto.equals("Magic")) {
			mapDeck = db.hashMap(PERSONAL_MAGIC_DECK_HASHMAP_NAME)
				.keySerializer(Serializer.STRING)
				.valueSerializer(Serializer.STRING)
				.createOrOpen();
			if(mapDeck.containsKey(deckName)) {
				mapDeck.replace(deckName, deckString);
				System.out.println(deckName + " - aggiornato con successo: " + mapDeck.get(deckName));
			}
		}else if (contesto.equals("Pokemon")) {
			mapDeck = db.hashMap(PERSONAL_POKEMON_DECK_HASHMAP_NAME)
				.keySerializer(Serializer.STRING)
				.valueSerializer(Serializer.STRING)
				.createOrOpen();
			if(mapDeck.containsKey(deckName)) {
				mapDeck.replace(deckName, deckString);
				System.out.println(deckName + " - aggiornato con successo: " + mapDeck.get(deckName));
			}
		}else {
			mapDeck = db.hashMap(PERSONAL_YUGIOH_DECK_HASHMAP_NAME)
				.keySerializer(Serializer.STRING)
				.valueSerializer(Serializer.STRING)
				.createOrOpen();
			if(mapDeck.containsKey(deckName)) {
				mapDeck.replace(deckName, deckString);
				System.out.println(deckName + " - aggiornato con successo: " + mapDeck.get(deckName));
			}
		}
		db.commit();
	}






	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}



}
