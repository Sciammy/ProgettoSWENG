package com.google.gwt.sample.stockwatcher.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("stockPrices")
public interface StockPriceService extends RemoteService {

	void addAccount(String user, String pwd);

	boolean controlAccount(String user);

	boolean login(String checkUser, String checkPwd);

	void test();

	String[] loadCarte(String contesto, boolean cercaAcquirenti, boolean soloPersonali);

	void addCard(String contesto, boolean desiderata, String actualUser, String stringCard);

	boolean rimuoviCarta(String contesto, boolean desiderata, String stringCard);

	void addProposta(String scambioString);

	String[] loadProposteScambi();

	void gestisciScambio(String context, boolean accettato, String scambio, String proponente, List<String> actualCard, String actualUser,
			List<String> newCard);

	String[] loadCartePersonali(String contesto);

	String loadDeckList(String deckName, String contesto);

	String[] loadCarteFromId(List<Integer> cardsId, String contesto);

	boolean controlDeckName(String deckName, String contesto);

	void addNewDeck(String deckName, String stringDeck, String contesto, String actualUser);

	void changeDeckName(String oldDeckName, String newDeckName, String newDeckString, String contesto);

	void updateDeck(String deckName, String deckString, String contesto);

	void removeDeck(String deck, String contesto);

	String[] loadDeckPersonali(String contesto);

}
