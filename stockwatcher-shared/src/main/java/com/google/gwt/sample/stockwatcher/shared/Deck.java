package com.google.gwt.sample.stockwatcher.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/*e*/
public class Deck{
	protected String name;
	protected int ID;
	protected String tipoGioco;
	protected String utente;
	protected List<Integer> deckList; //insieme degli ID delle carte


	public Deck(String name, String tipoGioco, String utente) {
		this.name = name;
		this.tipoGioco = tipoGioco;
		this.utente = utente;
		this.deckList = new ArrayList<>();
	}



	public void setID(int ID) {
		this.ID = ID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTipoGioco(String tipoGioco) {
		this.tipoGioco = tipoGioco;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public void setDeckList(List<Integer> deckList) {
		this.deckList = deckList;
	}



	public int getID() {
	        return ID;
	}

	public String getName() {
		return name;
	}

	public String getUtente() {
		return utente;
	}

	public String getTipoGioco() {
		return tipoGioco;
	}

	public List<Integer> getDeckList(){
		return deckList;
	}

	public int getNumberOfCardsInTheDeck() {
		return deckList.size();
	}




	//add a single card to the deck
	public void addCardToDeckList(int cardToAdd) {
		this.deckList.add(cardToAdd);
	}

	//add a bunch of cards to the deck
	public void addCardToDeckList(List<Integer> cardsToAdd) {
		for(int cardId : cardsToAdd) {
			this.deckList.add(cardId);
		}
	}

	//remove a single card from the deck
	public void removeCardFromDeckList(int idCardToBeRemoved, String actualUser) {
		if(checkCardInDeckList(idCardToBeRemoved, actualUser)) {
			for(int i = 0; i < deckList.size(); i++) {
				if(deckList.get(i) == idCardToBeRemoved) {
					deckList.remove(i);
					break;
				}
			}
		}
	}

	//checks if a card is within the deckList, returning True if the condition is verified.
	public boolean checkCardInDeckList(int idCardToBeChecked, String actualUser) {
		boolean result = false;
		if(actualUser.equals(this.utente))
			for(int cardId : deckList)
				if(cardId == idCardToBeChecked)
					result = true;
		return result;
	}
}
