package com.google.gwt.sample.stockwatcher.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface StockPriceServiceAsync {
	
	void addAccount(String user, String pwd, AsyncCallback<Void> callback);
	
	void controlAccount(String user, AsyncCallback<Boolean> callback);

	void login(String checkUser, String checkPwd, AsyncCallback<Boolean> asyncCallback);

	void test(AsyncCallback<Void> asyncCallback);

	/**
	 * @param asyncCallback
	 */

	void loadCarteMagic(boolean cercaAcquirenti, boolean soloPersonali, AsyncCallback<String[]> asyncCallback);

	void addCard(boolean desiderata, String actualUser, String stringCard, AsyncCallback<Void> asyncCallback);

	void rimuoviCarta(boolean desiderata, String stringCard, AsyncCallback<Boolean> asyncCallback);

	void addProposta(String scambioString, AsyncCallback<Void> asyncCallback);

	void loadProposteScambi(AsyncCallback<String[]> asyncCallback);

	void gestisciScambio(boolean accettato, String scambio, String proponente, List<String> actualCard, String actualUser,
			List<String> newCard, AsyncCallback<Void> asyncCallback);

}
