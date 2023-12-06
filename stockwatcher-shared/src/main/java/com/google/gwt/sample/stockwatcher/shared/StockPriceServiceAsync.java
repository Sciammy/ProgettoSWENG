package com.google.gwt.sample.stockwatcher.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface StockPriceServiceAsync {

	void addAccount(String user, String pwd, AsyncCallback<Void> callback);

	void controlAccount(String user, AsyncCallback<Boolean> callback);

	void login(String checkUser, String checkPwd, AsyncCallback<Boolean> asyncCallback);

	void test(AsyncCallback<Void> asyncCallback);

	void loadCarte(String contesto, boolean cercaAcquirenti, boolean soloPersonali, AsyncCallback<String[]> asyncCallback);

	void addCard(String contesto, boolean desiderata, String actualUser, String stringCard, AsyncCallback<Void> asyncCallback);

	void rimuoviCarta(String contesto, boolean desiderata, String stringCard, AsyncCallback<Boolean> asyncCallback);

	void addProposta(String scambioString, AsyncCallback<Void> asyncCallback);

	void loadProposteScambi(AsyncCallback<String[]> asyncCallback);

	void gestisciScambio(String context, boolean accettato, String scambio, String proponente, List<String> actualCard, String actualUser,
			List<String> newCard, AsyncCallback<Void> asyncCallback);

	void loadCartePersonali(String contesto, AsyncCallback<String[]> asyncCallback);

	void loadDeckList(String deckName, String contesto, AsyncCallback<String> asyncCallback);

	void loadCarteFromId(List<Integer> cardsId, String contesto, AsyncCallback<String[]> asyncCallback);

  void controlDeckName(String deckName, String contesto, AsyncCallback<Boolean> asyncCallback);

	void addNewDeck(String deckName, String stringDeck, String contesto, String actualUser, AsyncCallback<Void> asyncCallback);

	void changeDeckName(String oldDeckName, String newDeckName, String contesto, String newDeckString, AsyncCallback<Void> asyncCallback);

	void removeDeck(String deck, String contesto, AsyncCallback<Void> asyncCallback);

	void updateDeck(String deckName, String deckString, String contesto, AsyncCallback<Void> asyncCallback);

	void loadDeckPersonali(String contesto, AsyncCallback<String[]> asyncCallback);

}
