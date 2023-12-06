package com.google.gwt.sample.stockwatcher.client;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.sample.stockwatcher.shared.StockPriceService;
import com.google.gwt.sample.stockwatcher.shared.StockPriceServiceAsync;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

import java.util.ArrayList;
import java.util.List;


public class StockWatcher implements EntryPoint {

    private Card actualCard;
    private Deck actualDeck;
    private String contesto;
    private Scambio SC;
    private String actualUser;
    private VerticalPanel mainPanel = new VerticalPanel();
    private FlexTable cardsFlexTable = new FlexTable();
    private FlexTable scambiFlexTable = new FlexTable();
    private FlexTable cardsUserFlexTable = new FlexTable();
    private FlexTable cardsFlexTableOffer = new FlexTable();
    private FlexTable cardsFlexTableRequest = new FlexTable();
    private FlexTable cardsFlexTable2 = new FlexTable();
    private HorizontalPanel addPanel = new HorizontalPanel();
	private HorizontalPanel persistPanel = new HorizontalPanel();
    private TextBox newSymbolTextBox = new TextBox();
    private PasswordTextBox pwdTextBox = new PasswordTextBox();
    private Button iscrivitiButton = new Button("Iscriviti");
    private Label lastUpdatedLabel = new Label();
    private List<Card> cards = new ArrayList<>();
    private List<Card> cards2 = new ArrayList<>();
    private List<Integer> cardsId = new ArrayList<>();
    private List<Scambio> scambiL = new ArrayList<>();
    private StockPriceServiceAsync stockPriceSvc = GWT.create(StockPriceService.class);
    private Label errorMsgLabel = new Label();
    private Button loginButton = new Button("Login");
    private Button logoutButton = new Button("Logout");
    private TextBox checkUserTextBox = new TextBox();
    private PasswordTextBox checkPwdTextBox = new PasswordTextBox();
    private Button toIscrizioneButton = new Button("Non sei utente? Iscriviti!");
    private Button cercaCarte = new Button("Cerca Carte");
    private Button buildDeck = new Button("Deck Builder");
    private Button mostraScambi = new Button("Gestione Scambi");
    private Button home = new Button("HOME");
    private Button MagicButton = new Button("Magic");
    private Button PokemonButton = new Button("Pokemon");
    private Button YugiohButton = new Button("Yu-Gi-Oh");
    private Button showCardsButton = new Button("Mostra Carte");
    private TextBox userTextBox = new TextBox();
    private TextBox artistTextBox = new TextBox();
    private TextBox nameTextBox = new TextBox();
    private TextBox textTextBox = new TextBox();
    private TextBox typesTextBox = new TextBox();
    private TextBox rarityTextBox = new TextBox();
    private TextBox hasFoilTextBox = new TextBox();
    private TextBox isAlternativeTextBox = new TextBox();
    private TextBox isFullArtTextBox = new TextBox();
    private TextBox isPromoTextBox = new TextBox();
    private TextBox isReprintTextBox = new TextBox();
    private TextBox idTextBox = new TextBox();
    private TextBox personalTextBox = new TextBox();
    private Label artistLabel = new Label("Artist:");
    private Label nameLabel = new Label("Name:");
    private Label textLabel = new Label("Card Text:");
    private Label personalTextLabel = new Label("Testo Personale:");
    private Label typesLabel = new Label("Types:");
    private Label rarityLabel = new Label("Rarity:");
    private Label hasFoilLabel = new Label("Has Foil:");
    private Label isAlternativeLabel = new Label("Is Alternative:");
    private Label isFullArtLabel = new Label("Is Full Art:");
    private Label isPromoLabel = new Label("Is Promo:");
    private Label isReprintLabel = new Label("Is Reprint:");
    private Label idLabel = new Label("ID:");
    private ListBox listBox = new ListBox();
    private Label condizioneLabel = new Label("Condizione:");
    private TextBox condizioneTextBox = new TextBox();
    private Button aggiungiCarta = new Button("Aggiungi Carta");
    private Button rimuoviCarta = new Button("Rimuovi Carta");
    private Button rimuoviCartaDesiderata = new Button("Rimuovi Carta dalle Desiderate");
    private CheckBox checkPersonal = new CheckBox("Solo Carte Personali");
    private CheckBox checkDesiderate = new CheckBox("Solo Carte Desiderate");
    private Button cartaDesiderata = new Button("Aggiungi alle desiderate");
    private Button cercaProprietari = new Button("Cerca Proprietari");
    private Button cercaAcquirenti = new Button("Cerca Acquirenti");
    private Button inviaProposta = new Button("Proponi Scambio");
    private Label firstEditionLabel = new Label("firstEdition: ");
    private Label holoLabel = new Label("holo:");
    private Label normalLabel = new Label("normal:");
    private Label reverseLabel = new Label("reverse:");
    private Label wPromoLabel = new Label("wPromo:");
    private TextBox firstEditionTxtBox = new TextBox();
    private TextBox holoTxtBox = new TextBox();
    private TextBox normalTxtBox = new TextBox();
    private TextBox reverseTxtBox = new TextBox();
    private TextBox wPromoTxtBox = new TextBox();
    private Label raceLabel = new Label("Race:");
    private TextBox raceTxtBox = new TextBox();
    private Image littleImage = new Image();
    private Image largeImage = new Image();
    private Button zoomInButton = new Button("Ingrandisci");
    private Button zoomOutButton = new Button("Rimpicciolisci");
    private Label filtraTipoLabel = new Label("Filtra Tipo:");
    private TextBox filtraTipo = new TextBox();
    private Button newDeck = new Button("Crea un nuovo Deck");
    private Button modDeck = new Button("Modifica Deck");
    private Button showDeck = new Button("Carica Lista Deck");
    private Button renameDeck = new Button("Cambia Nome del Deck");
    private Button createNewDeck = new Button("Crea nuovo Deck");
    private Button showMyCards = new Button("Mostra Collezione");
    private FlexTable cardsFlexTableCollection = new FlexTable();
    private FlexTable deckFlexTable = new FlexTable();
    private FlexTable deckListFlexTable = new FlexTable();
    private TextBox deckNameTextBox = new TextBox();
    private List<Deck> decks = new ArrayList<>();
    private TextBox currentDeckTextBox = new TextBox();
    private int deckID = 0;
    private List<Card> actualDeckList = new ArrayList<>();
    private Button deckMenu = new Button("DECK MENU");
    private Label myDeckList = new Label("DECKLIST: ");
    private Label myCollection = new Label("COLLEZIONE: ");
    private Label currentDeckName = new Label("Nome attuale: ");
    private Button backToDeckBuilding = new Button("MODIFICA DECK");
    private Label usrLabel = new Label();
    private Label pwdLabel = new Label();
    private FlexTable searchFlexTable = new FlexTable();
    private FlexTable cardDetailFlexTable = new FlexTable();
    private FlexTable cardButtonFlexTable = new FlexTable();
    private FlexTable deckbuildingFlexTable = new FlexTable();
    private FlexTable menuButtonFlexTable = new FlexTable();
    private Button cercaCarteInterno = new Button("CERCA CARTE");


    /**
     * Entry point method.
     */
    public void onModuleLoad() {

    	addPanel.add(home);
        addPanel.add(cercaCarte);
        addPanel.add(cercaCarteInterno);
        addPanel.add(buildDeck);
        addPanel.add(MagicButton);
        addPanel.add(PokemonButton);
        addPanel.add(YugiohButton);
        addPanel.add(mostraScambi);
        
    	//table for scambi scambiFlexTable
        scambiFlexTable.setText(0, 0, "Offerente");
        scambiFlexTable.setText(0, 1, "Carte Offerte");
        scambiFlexTable.setText(0, 2, "Carte Richieste");
        scambiFlexTable.setText(0, 3, "Accetta");
        scambiFlexTable.setText(0, 4, "Rifiuta" );
        scambiFlexTable.setCellPadding(6);

        scambiFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");
        scambiFlexTable.addStyleName("watchList");
        scambiFlexTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
        scambiFlexTable.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
        scambiFlexTable.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");

        // Create table for cards.
        cardsFlexTable.setText(0, 0, "Gioco");
        cardsFlexTable.setText(0, 1, "Nome");
        cardsFlexTable.setText(0, 2, "Tipo");
        cardsFlexTable.setText(0, 3, "delete");
        cardsFlexTable.setText(0, 4, "expand");
        cardsFlexTable.setCellPadding(6);

        cardsFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");
        cardsFlexTable.addStyleName("watchList");
        cardsFlexTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
        cardsFlexTable.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
        cardsFlexTable.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");


        //idem per cloni
        cardsFlexTable2.setText(0, 0, "Gioco");
        cardsFlexTable2.setText(0, 1, "Nome");
        cardsFlexTable2.setText(0, 2, "Tipo");
        cardsFlexTable2.setText(0, 3, "delete");
        cardsFlexTable2.setText(0, 4, "expand");
        cardsFlexTable2.setCellPadding(6);

        cardsFlexTable2.getRowFormatter().addStyleName(0, "watchListHeader");
        cardsFlexTable2.addStyleName("watchList");
        cardsFlexTable2.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
        cardsFlexTable2.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
        cardsFlexTable2.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");

        //clone2
        cardsFlexTableOffer.setText(0, 0, "Gioco");
        cardsFlexTableOffer.setText(0, 1, "Nome");
        cardsFlexTableOffer.setText(0, 2, "Tipo");
        cardsFlexTableOffer.setText(0, 3, "delete");
        cardsFlexTableOffer.setText(0, 4, "expand");
        cardsFlexTableOffer.setCellPadding(6);

        cardsFlexTableOffer.getRowFormatter().addStyleName(0, "watchListHeader");
        cardsFlexTableOffer.addStyleName("watchList");
        cardsFlexTableOffer.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
        cardsFlexTableOffer.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
        cardsFlexTableOffer.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");

        //clone3
        cardsFlexTableRequest.setText(0, 0, "Gioco");
        cardsFlexTableRequest.setText(0, 1, "Nome");
        cardsFlexTableRequest.setText(0, 2, "Tipo");
        cardsFlexTableRequest.setText(0, 3, "delete");
        cardsFlexTableRequest.setText(0, 4, "expand");
        cardsFlexTableRequest.setCellPadding(6);

        cardsFlexTableRequest.getRowFormatter().addStyleName(0, "watchListHeader");
        cardsFlexTableRequest.addStyleName("watchList");
        cardsFlexTableRequest.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
        cardsFlexTableRequest.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
        cardsFlexTableRequest.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");



        //tabella proprietari
        cardsUserFlexTable.setText(0, 0, "User");
        cardsUserFlexTable.setText(0, 1, "Condizione");
        cardsUserFlexTable.setText(0, 2, "delete");
        cardsUserFlexTable.setText(0, 3, "Scambia");

        cardsUserFlexTable.setCellPadding(6);

        cardsUserFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");
        cardsUserFlexTable.addStyleName("watchList");
        cardsUserFlexTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
        cardsUserFlexTable.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
        cardsUserFlexTable.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");



        //collezione proprie carte per il deck building
        cardsFlexTableCollection.setText(0, 0, "Gioco");
        cardsFlexTableCollection.setText(0, 1, "Nome");
        cardsFlexTableCollection.setText(0, 2, "Tipo");
        cardsFlexTableCollection.setText(0, 3, "expand");
        cardsFlexTableCollection.setText(0, 4, "Aggiungi al Deck");

        cardsFlexTableCollection.setCellPadding(6);

        cardsFlexTableCollection.getRowFormatter().addStyleName(0, "watchListHeader");
        cardsFlexTableCollection.addStyleName("watchList");
        cardsFlexTableCollection.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
        cardsFlexTableCollection.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");



        //tabella che mostra i propri deck
        deckFlexTable.setText(0,0, "Gioco");
        deckFlexTable.setText(0,1, "Nome");
        deckFlexTable.setText(0,2, "N° di Carte");
        deckFlexTable.setText(0,3, "Modifica");
        deckFlexTable.setText(0,4, "Rinomina");
        deckFlexTable.setText(0,5, "Elimina");

        deckFlexTable.setCellPadding(6);

        deckFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");
        deckFlexTable.addStyleName("watchList");



        //tabella che mostra la decklist
        deckListFlexTable.setText(0,0, "Gioco");
        deckListFlexTable.setText(0,1, "Nome");
        deckListFlexTable.setText(0,2, "Tipo");
        deckListFlexTable.setText(0,3, "expand");
        deckListFlexTable.setText(0,4, "Rimuovi dal Deck");

        deckListFlexTable.setCellPadding(6);

        deckListFlexTable.getRowFormatter().addStyleName(0, "watchListHeaderDeckList");
        deckListFlexTable.addStyleName("watchList");
        deckListFlexTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
        deckListFlexTable.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
        
        
        //tabella per i filtri di ricerca
        searchFlexTable.addStyleName("searchWatchlist");
        searchFlexTable.setCellPadding(10);
        searchFlexTable.setCellSpacing(10);
        
        //tabella per i dettagli della carta
        cardDetailFlexTable.addStyleName("searchWatchlist");
        cardDetailFlexTable.setCellPadding(10);
        cardDetailFlexTable.setCellSpacing(10);
        
        //tabella per le azioni sulla carta
        cardButtonFlexTable.addStyleName("searchWatchlist");
        cardButtonFlexTable.setCellPadding(10);
        cardButtonFlexTable.setCellSpacing(10);
        
        //tabella per la visualizzazione della decklist e della lista carta nel deckbuilding
		deckbuildingFlexTable.setCellPadding(10);
		deckbuildingFlexTable.setCellSpacing(10);
		deckbuildingFlexTable.addStyleName("searchWatchlist");
		
		//tabella per la navigazione tra schermate
		menuButtonFlexTable.setCellPadding(10);
		//menuButtonFlexTable.setCellSpacing(10);
		menuButtonFlexTable.addStyleName("searchWatchlist");

		// Assemble Main panel.
        errorMsgLabel.setStyleName("errorMessage");
        errorMsgLabel.setVisible(false);

        mainPanel.add(errorMsgLabel);
        mainPanel.add(addPanel);
		mainPanel.add(persistPanel);
        mainPanel.add(lastUpdatedLabel);

        // Associate the Main panel with the HTML host page.
        RootPanel.get("stockList").add(mainPanel);

        // Move cursor focus to the input box.
        newSymbolTextBox.setFocus(true);
        
        usrLabel.setText("Username: ");
        addPanel.add(usrLabel);
        addPanel.add(checkUserTextBox);
        addPanel.add(newSymbolTextBox);
        pwdLabel.setText("Password: ");
        addPanel.add(pwdLabel);
        addPanel.add(checkPwdTextBox);
        addPanel.add(pwdTextBox);
        addPanel.add(loginButton);
        addPanel.add(toIscrizioneButton);
        addPanel.add(iscrivitiButton);
        addPanel.addStyleName("addPanel");
        addPanel.add(showCardsButton);
                
        addPanel.add(cardsFlexTable);
        addPanel.add(searchFlexTable);
        addPanel.add(checkPersonal);
        addPanel.add(checkDesiderate);
        addPanel.add(cardsUserFlexTable);

        addPanel.add(deckMenu);
        addPanel.add(backToDeckBuilding);
        addPanel.add(showMyCards);
        addPanel.add(menuButtonFlexTable);
        addPanel.add(deckbuildingFlexTable);
        addPanel.add(myCollection);
        addPanel.add(modDeck);
        addPanel.add(showDeck);
        addPanel.add(renameDeck);
        addPanel.add(cardsFlexTableCollection);
        addPanel.add(deckFlexTable);
        addPanel.add(myDeckList);
        addPanel.add(deckListFlexTable);
        addPanel.add(newDeck);
        addPanel.add(deckNameTextBox);
        addPanel.add(createNewDeck);
        addPanel.add(currentDeckName);
        addPanel.add(currentDeckTextBox);
        currentDeckTextBox.setReadOnly(true);


        //servono per filtrare durante la ricerca
        addPanel.add(filtraTipoLabel);
        addPanel.add(filtraTipo);
        

        //info carte
        addPanel.add(artistLabel);
        addPanel.add(artistTextBox);
        addPanel.add(cardDetailFlexTable);
        addPanel.add(cardButtonFlexTable);
        addPanel.add(nameLabel);
        addPanel.add(nameTextBox);
        addPanel.add(textLabel);
        addPanel.add(textTextBox);
        addPanel.add(typesLabel);
        addPanel.add(typesTextBox);
        addPanel.add(rarityLabel);
        addPanel.add(rarityTextBox);
        addPanel.add(hasFoilLabel);
        addPanel.add(hasFoilTextBox);
        addPanel.add(isAlternativeLabel);
        addPanel.add(isAlternativeTextBox);
        addPanel.add(isFullArtLabel);
        addPanel.add(isFullArtTextBox);
        addPanel.add(isPromoLabel);
        addPanel.add(isPromoTextBox);
        addPanel.add(isReprintLabel);
        addPanel.add(isReprintTextBox);
        addPanel.add(idLabel);
        addPanel.add(idTextBox);


        addPanel.add(firstEditionLabel);
        addPanel.add(firstEditionTxtBox);

        addPanel.add(holoLabel);
        addPanel.add(holoTxtBox);

        addPanel.add(normalLabel);
        addPanel.add(normalTxtBox);

        addPanel.add(reverseLabel);
        addPanel.add(reverseTxtBox);

        addPanel.add(wPromoLabel);
        addPanel.add(wPromoTxtBox);

        addPanel.add(condizioneLabel);
        addPanel.add(condizioneTextBox);

        addPanel.add(personalTextLabel);
        addPanel.add(personalTextBox);

        addPanel.add(raceLabel);
        addPanel.add(raceTxtBox);

        addPanel.add(littleImage);
        addPanel.add(largeImage);
        addPanel.add(zoomInButton);
        addPanel.add(zoomOutButton);


        addPanel.add(listBox);
        listBox.addItem("Sopravvissuta");
        listBox.addItem("Decente");
        listBox.addItem("Buona");
        listBox.addItem("Ottima");
        listBox.addItem("Sigillata");

        addPanel.add(cercaProprietari);
        addPanel.add(cercaAcquirenti);
        addPanel.add(aggiungiCarta);
        addPanel.add(rimuoviCarta);
        addPanel.add(cartaDesiderata);
        addPanel.add(rimuoviCartaDesiderata);
        addPanel.add(cardsFlexTableOffer);
        addPanel.add(cardsFlexTableRequest);
        addPanel.add(cardsFlexTable2);
        addPanel.add(inviaProposta);
        addPanel.add(scambiFlexTable);
        addPanel.add(userTextBox);
        addPanel.add(logoutButton);
        
        linkToLogin(addPanel);

        userTextBox.setReadOnly(true);



    //questi sono solo link

        toIscrizioneButton.addClickHandler(new ClickHandler() {
    		@Override
    		public void onClick(ClickEvent event) {
    			
    		     linkToIscrizione(addPanel);
    		}});


        home.addClickHandler(new ClickHandler() {
    		@Override
    		public void onClick(ClickEvent event) {

    		     linkToHome(addPanel);
    		}});


        YugiohButton.addClickHandler(new ClickHandler() {
    		@Override
    		public void onClick(ClickEvent event) {

    			contesto = "Yugioh";
    		    linkToGeneralActions(addPanel);
    		}});


    	MagicButton.addClickHandler(new ClickHandler() {
    		@Override
    		public void onClick(ClickEvent event) {

    			contesto = "Magic";
    		    linkToGeneralActions(addPanel);
    		}});


    	PokemonButton.addClickHandler(new ClickHandler() {
    		@Override
    		public void onClick(ClickEvent event) {

    			contesto = "Pokemon";
    		    linkToGeneralActions(addPanel);
    		}});


        //questo prende le carte dal DB e le mette in una griglia/tabella
        showCardsButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				//legge lo stato dei chechbutton
				boolean soloDesiderate = checkDesiderate.getValue();
				boolean soloPersonali = checkPersonal.getValue();

				stockPriceSvc.loadCarte(contesto, soloDesiderate, soloPersonali, new AsyncCallback<String[]>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Cannot load cards: "
								+ caught.getMessage());
					}

					@Override
					public void onSuccess(String[] carte) {

						//pulisce tabella e lista
						int rowCount = cardsFlexTable.getRowCount();
				    	for(int i=rowCount -1 ; i > 0; i--)
				    	{cardsFlexTable.removeRow(i);}
				    	cards.clear();


						for (String carta : carte) {
							//trasformo la stringa in oggetto
							Card Card = parseJsonString(carta);

							String tipo = "";
							if (Card instanceof Magic_card) {
								Magic_card MC = (Magic_card) Card;
								tipo = MC.getTypes();
							}
							else if(Card instanceof Pokemon_card) {
								Pokemon_card PC = (Pokemon_card) Card;
								tipo = PC.getTypesString();
							}
							else {
								Yugioh_card YC = (Yugioh_card) Card;
								tipo = YC.getType();
							}

							if((tipo.toUpperCase()).contains((filtraTipo.getText()).toUpperCase()) ||filtraTipo.getText() == null ) {

							//faccio i filtri del caso e poi do in pasto a addCarte che mette in griglia
							if(soloPersonali){
								if(Card.getProprietario() == actualUser)
									addCarte(Card, addPanel, cardsFlexTable, false);
								}
								else if(soloDesiderate) {
									if(Card.getAcquirente() == actualUser)
										addCarte(Card, addPanel, cardsFlexTable, false);
								}
								else {
									addCarte(Card, addPanel, cardsFlexTable, false);
								}
							}
						}
					}
				});
			}
		});


    //prende la propria collezione di carte dal DB e le mostra in tabella
        showMyCards.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				stockPriceSvc.loadCartePersonali(contesto, new AsyncCallback<String[]>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Cannot load cards: "+ caught.getMessage());
					}

					@Override
					public void onSuccess(String[] carte) {

						//pulisce tabella e lista
						int rowCount = cardsFlexTableCollection.getRowCount();
				    	for(int i = rowCount-1; i > 0; i--){
				    		cardsFlexTableCollection.removeRow(i);
				    	}
				    	cards.clear();
				    	cardsId.clear();

				    	for (String carta : carte) {
							Card card = parseJsonString(carta);
							if(card.getProprietario() == actualUser) {
								addCollezioneCarte(card, addPanel, cardsFlexTableCollection,
										deckListFlexTable, true);
							}
						}
					}

				});
			}
		});


    //carica i deck e gli aggiunge in tabella
    showDeck.addClickHandler(new ClickHandler() {
 	    public void onClick(ClickEvent event) {

     	  stockPriceSvc.loadDeckPersonali(contesto, new AsyncCallback<String[]>() {
			    @Override
				  public void onFailure(Throwable caught) {
						 Window.alert("Cannot load deck: "+ caught.getMessage());
					}

					@Override
					public void onSuccess(String[] deck) {

						//pulisce tabella e lista
						int rowCount = deckFlexTable.getRowCount();
				    	for(int i = rowCount-1; i > 0; i--){
				    		deckFlexTable.removeRow(i);
				    	}
				    	decks.clear();

				    	if(deck.length != 0) {
				    		deckFlexTable.setVisible(true);
							for (String d : deck) {
								//trasformo la stringa in oggetto
								Deck mazzo = parseJsonStringToDeck(d);

								if(mazzo.getUtente() == actualUser) {
									addCollezioneDeck(mazzo, addPanel, deckFlexTable);
								}
							}
						}
					}

     		});
     	 }
    });



      //guarda gli scambi proposti all'utente loggato
    mostraScambi.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				stockPriceSvc.loadProposteScambi(new AsyncCallback<String[]>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Cannot load cards: "
								+ caught.getMessage());
					}

					@Override
					public void onSuccess(String[] scambi) {

						//pulizia griglia
						int rowCount = scambiFlexTable.getRowCount();
				    	for(int i=rowCount -1 ; i > 0; i--)
				    	{scambiFlexTable.removeRow(i);}
				    	scambiL.clear();


						for (String scambio : scambi) {

							//Window.alert(scambio); //test

							//trasformo stringa in oggetto
							Scambio skmb = parseScambioJsonString(scambio);

							if(skmb.getRicevente() == actualUser) {
								//se il ricevente è l'utente attuale, mette in griglia
							addScambio(skmb);
							}
						}
						//poi manda alla pagina corretta
						linkToListaScambi(addPanel);
					}

				});
			}
		});



        //il login valorizza la variabile actualUser e rimanda alla home
        loginButton.addClickHandler(new ClickHandler() {
        	
			@Override
			public void onClick(ClickEvent event) {
				String userTxt = checkUserTextBox.getText().toUpperCase().trim();
				stockPriceSvc.login(userTxt, checkPwdTextBox.getText().trim(),
						new AsyncCallback<Boolean>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("errore di login: "
										+ caught.getMessage());
							}

							@Override
							public void onSuccess(Boolean check) {
								if (check){
									Window.alert("loggato con successo");
									actualUser = userTxt;
									userTextBox.setText(userTxt);
									linkToHome(addPanel);
								}
								else
									Window.alert("user o pwd errati");
							}
						});
			}
		});
        
        //logout rimanda alla prima schermata dove si effettua l'accesso,
        //pulisce i correnti: user, card, deck e decklist
        logoutButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.alert("exit from account");
				checkUserTextBox.setText("");
				checkPwdTextBox.setText("");
				actualUser = "";
				actualCard = null;
				actualDeck = null;
				actualDeckList.clear();
				linkToLogin(addPanel);
			}    	
        });

        //cerca chi possiede l carta in esame e crea griglia con proprietari
        cercaProprietari.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				boolean soloPersonali = true;
				boolean cercaAcquirenti = false;

				stockPriceSvc.loadCarte(contesto, cercaAcquirenti, soloPersonali, new AsyncCallback<String[]>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Cannot load cards: "
								+ caught.getMessage());
					}

					@Override
					public void onSuccess(String[] carte) {

						//pulizia griglia precedente
						int rowCount = cardsUserFlexTable.getRowCount();
				    	for(int i=rowCount -1 ; i > 0; i--)
				    	{cardsUserFlexTable.removeRow(i);}
				    	cards.clear();


						for (String carta : carte) {

							Card card = parseJsonString(carta);

							if(card.getName() == actualCard.getName())
							addCartaUser(cercaAcquirenti, card, addPanel);

						}
					}

				});
			}
		});


        //guarda chi è interessato alla carta in esame e mette le info in griglia
        cercaAcquirenti.addClickHandler(new ClickHandler() {
     			@Override
     			public void onClick(ClickEvent event) {

     				boolean soloPersonali = false;
     				boolean cercaAcquirenti = true;

     				stockPriceSvc.loadCarte(contesto, cercaAcquirenti, soloPersonali, new AsyncCallback<String[]>() {
     					@Override
     					public void onFailure(Throwable caught) {
     						Window.alert("Cannot load cards: "
     								+ caught.getMessage());
     					}

     					@Override
     					public void onSuccess(String[] carte) {

     						//pulisco la tabella
     						int rowCount = cardsUserFlexTable.getRowCount();
     				    	for(int i=rowCount -1 ; i > 0; i--)
     				    	{cardsUserFlexTable.removeRow(i);}
     				    	cards.clear();

     						for (String carta : carte) {

     							Card card = parseJsonString(carta);

     							if(card.getName() == actualCard.getName() )
     							addCartaUser(cercaAcquirenti, card, addPanel);
     						}
     					}

     				});
     			}
     		});


        //mette la carta nell'elenco delle desiderate
        cartaDesiderata.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				boolean desiderata = true;
				actualCard.setAcquirente(actualUser);
				actualCard.setCondizione(null);
				actualCard.setProprietario(null);
				actualCard.setTestoPersonale(null);
				String stringCard = "";
				stringCard = convertCardToJson(actualCard).toString();

				stockPriceSvc.addCard(contesto, desiderata, actualUser, stringCard, new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("aggiunta carta fallita: "
										+ caught.getMessage());
							}

							@Override
							public void onSuccess(Void result) {
								Window.alert("carta inserita ok!");
							}
						});
			}
		});


        //propone scambio usando l'oggetto Scambio
        inviaProposta.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				String scambioString = convertScambioToJsonString(SC);

				stockPriceSvc.addProposta(scambioString, new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("aggiunta proposta fallita: "
										+ caught.getMessage());
							}

							@Override
							public void onSuccess(Void result) {

									Window.alert("proposta inserita ok!");

							}
						});
			}
		});


        //aggiunge carta personale
        aggiungiCarta.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				//raccoglie info da testo personale e condizione
				boolean desiderata = false;
				actualCard.setTestoPersonale(personalTextBox.getText());
				actualCard.setCondizione(listBox.getSelectedValue());
				actualCard.setProprietario(actualUser);
				actualCard.setAcquirente(null);
				String stringCard = "";

				stringCard = convertCardToJson(actualCard).toString();

				stockPriceSvc.addCard(contesto, desiderata, actualUser, stringCard, new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("aggiunta carta fallita: "
										+ caught.getMessage());
							}

							@Override
							public void onSuccess(Void result) {

									Window.alert("carta inserita ok!");
							}
						});
			}
		});

        //cancella la carta dalla lista dei desiderati
        rimuoviCartaDesiderata.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				boolean desiderata = true;
				actualCard.setAcquirente(actualUser);
				String stringCard = "";
				stringCard = convertCardToJson(actualCard).toString();

				stockPriceSvc.rimuoviCarta(contesto, desiderata, stringCard, new AsyncCallback<Boolean>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("test failed: "
										+ caught.getMessage());
							}

							@Override
							public void onSuccess(Boolean result) {
								if(result)
									Window.alert("Rimossa");
								else
									Window.alert("Carta non presente tra le desiderate");

							}
						});
			}
		});


        //cancella carta dalle possedute
        rimuoviCarta.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				boolean desiderata = false;
				String stringCard = "";

				stringCard = convertCardToJson(actualCard).toString();


				stockPriceSvc.rimuoviCarta(contesto, desiderata, stringCard, new AsyncCallback<Boolean>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("test failed: "
										+ caught.getMessage());
							}

							@Override
							public void onSuccess(Boolean result) {

								if(result)
									Window.alert("Rimossa");
								else
									Window.alert("Carta non presente tra le desiderate");
							}
						});
			}
		});






    //iscrizione - bisognerebbe mettere controllo su lunghezza pwd e che mail sia davvero una mail
    iscrivitiButton.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			
			if(newSymbolTextBox.getText().trim().isEmpty()) {
				Window.alert("inserire username");	
			} else if(pwdTextBox.getText().trim().isEmpty()){
				Window.alert("inserire password");
			} else {
			  stockPriceSvc.controlAccount(newSymbolTextBox.getText().toUpperCase().trim(), new AsyncCallback<Boolean>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Errore 1 ");
				}
				@Override
				public void onSuccess(Boolean result) {

					if(result){
						Window.alert("Utente già presente");
					} else {
						stockPriceSvc.addAccount(newSymbolTextBox.getText().toUpperCase().trim(), pwdTextBox.getText().trim(), 
								new AsyncCallback<Void>() {
							
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Errore: "
									+ caught.getMessage());
							}
							@Override
							public void onSuccess(Void result) {
								Window.alert("Utente Iscritto con Successo");
								linkToLogin(addPanel);
							}
						});
						 
					newSymbolTextBox.setText("");
					pwdTextBox.setText("");
					}
				}
			  });
			}
		}
	});

  //quando si conferma il nome per un nuovo deck, questo viene creato, dopo aver controllato che non ne esistano già altri con lo stesso nome
  createNewDeck.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			if(deckNameTextBox.getText().trim().isEmpty()) {
				Window.alert("inserire nome del deck");
			} else {
				String deckName = deckNameTextBox.getText().toUpperCase().trim();
				stockPriceSvc.controlDeckName(deckName, contesto, new AsyncCallback<Boolean>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Errore 1 ");
					}
					@Override
					public void onSuccess(Boolean result) {
						if(result){
							Window.alert("Pecchi di fantasia, nome del deck già in uso!");
						}else {
						
							actualDeck = new Deck(deckName, contesto, actualUser);
							actualDeck.setID(deckID);
							actualDeck.setName(deckName);
							actualDeck.setUtente(actualUser);
							actualDeck.setTipoGioco(contesto);
							String stringDeck = "";
							stringDeck = convertDeckToJson(actualDeck).toString();

							stockPriceSvc.addNewDeck(deckName, stringDeck, contesto, actualUser, new AsyncCallback<Void>() {
								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Errore: " + caught.getMessage());
								}
								@Override
								public void onSuccess(Void result) {
									deckID++;
									Window.alert("Nuovo Deck " + actualDeck.getName() + " creato con successo!");
									linkToDeckMenu(addPanel);
								}
							});
							deckNameTextBox.setText("");
						}
					}
				});
			}
		 }
	 });

       cercaCarte.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
        	
        	cardsFlexTable.removeAllRows();     	
            cardsFlexTable.setText(0, 0, "Gioco");
            cardsFlexTable.setText(0, 1, "Nome");
            cardsFlexTable.setText(0, 2, "Tipo");
            cardsFlexTable.setText(0, 3, "delete");
            cardsFlexTable.setText(0, 4, "expand");
            cardsFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");        
            cardsFlexTable.setCellPadding(6);
            cardsFlexTable.addStyleName("watchList");
            cardsFlexTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
            cardsFlexTable.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
            cardsFlexTable.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");
        	linkToSearch(addPanel);
        }
    });
       
       cercaCarteInterno.addClickHandler(new ClickHandler() {
    	   public void onClick(ClickEvent event) {
    		   linkToSearch(addPanel);
    	   }
       });


       checkDesiderate.addClickHandler(new ClickHandler() {
           public void onClick(ClickEvent event) {
        	   checkPersonal.setChecked(false);
           }
       });

       checkPersonal.addClickHandler(new ClickHandler() {
           public void onClick(ClickEvent event) {
        	   checkDesiderate.setChecked(false);
           }
       });


       buildDeck.addClickHandler(new ClickHandler() {
    	   public void onClick(ClickEvent event) {
    		   linkToDeckMenu(addPanel);
    	   }
       });


       backToDeckBuilding.addClickHandler(new ClickHandler() {
    	   public void onClick(ClickEvent event) {
    		   linkToCreateDeck(addPanel);
    	   }
       });


       newDeck.addClickHandler(new ClickHandler() {
    	   public void onClick(ClickEvent event) {
    		   linkToNameDeck(addPanel);
    	   }
       });


       renameDeck.addClickHandler(new ClickHandler() {
    	   public void onClick(ClickEvent event) {
    		   if(deckNameTextBox.getText().trim().isEmpty()) {
    			   Window.alert("inserire nome del deck");
    		   } else {

    			   String newDeckName = deckNameTextBox.getText().toUpperCase();

    			   stockPriceSvc.controlDeckName(newDeckName, contesto,  new AsyncCallback<Boolean>() {
    				   @Override
    				   public void onFailure(Throwable caught) {
    					   Window.alert("Errore 1");
    				   }
    				   @Override
    				   public void onSuccess(Boolean result) {
    					   if(result) {
    						   Window.alert("Nome già in uso, le chiediamo cortesemente di trovarne un altro.");
    					   }else {

    						   String oldName = actualDeck.getName();
    						   actualDeck.setName(newDeckName);
    						   String stringDeck = convertDeckToJson(actualDeck).toString();
    						   stockPriceSvc.changeDeckName(oldName, newDeckName, stringDeck, contesto, new AsyncCallback<Void>() {
    							   @Override
    							   public void onFailure(Throwable caught) {
    								   Window.alert("Errore: " + caught.getMessage());
    							   }
    							   @Override
    							   public void onSuccess(Void result) {
    								   Window.alert("Nome del Deck cambiato con successo in: " + newDeckName);
    								   linkToDeckMenu(addPanel);
    							   }
    						   });
    						   deckNameTextBox.setText("");
    					   }
    				   }
    			   });
    		   }
    	   }
       });

       deckMenu.addClickHandler(new ClickHandler() {
    	   public void onClick(ClickEvent event) {
    		   linkToDeckMenu(addPanel);
    	   }
       });

}

    //questi sono i metodi richiamati e non sono più azioni di pulsanti


//prende una stringa di scambio carte e la converte in oggetto
public Scambio parseScambioJsonString(String jsonString) {
    JSONValue jsonValue = JSONParser.parseStrict(jsonString);
    JSONObject jsonObject = jsonValue.isObject();

    String proponente = jsonObject.get("proponente").isString().stringValue();
    String ricevente = jsonObject.get("ricevente").isString().stringValue();

    // Parse the 'offerta' list
    List<Card> offerta = new ArrayList<>();
    if (jsonObject.containsKey("offerta") && jsonObject.get("offerta").isArray() != null) {
        JSONArray offertaArray = jsonObject.get("offerta").isArray();
        for (int i = 0; i < offertaArray.size(); i++) {
            JSONValue cardJson = offertaArray.get(i);
            if (cardJson.isObject() != null) {
                Card card = parseJsonString(cardJson.isObject().toString());
                offerta.add(card);
            }
        }
    }

    // Parse  'richiesta' list (tipo 'offerta')
    List<Card> richiesta = new ArrayList<>();
    if (jsonObject.containsKey("richiesta") && jsonObject.get("richiesta").isArray() != null) {
        JSONArray richiestaArray = jsonObject.get("richiesta").isArray();
        for (int i = 0; i < richiestaArray.size(); i++) {
            JSONValue cardJson = richiestaArray.get(i);
            if (cardJson.isObject() != null) {
                Card card = parseJsonString(cardJson.isObject().toString());
                richiesta.add(card);
            }
        }
    }

    Scambio scambio = new Scambio(proponente, ricevente);
    scambio.setOfferta(offerta);
    scambio.setRichiesta(richiesta);

    return scambio;
}


    //parsa la stringa in una carta
    public Card parseJsonString(String jsonString) {
        JSONValue jsonValue = JSONParser.parseStrict(jsonString);
        JSONObject jsonObject = jsonValue.isObject();

        //generico
        String name = jsonObject.get("name").isString().stringValue();
        String condizione = jsonObject.containsKey("condizione") ? jsonObject.get("condizione").isString().stringValue() : null;
        String proprietario = jsonObject.containsKey("proprietario") ? jsonObject.get("proprietario").isString().stringValue() : null;
        String acquirente = jsonObject.containsKey("acquirente") ? jsonObject.get("acquirente").isString().stringValue() : null;
        String testoPersonale = jsonObject.containsKey("testoPersonale") ? jsonObject.get("testoPersonale").isString().stringValue() : null;
       // String nomeDeck = jsonObject.containsKey("nomeDeck") ? jsonObject.get("nomeDeck").isString().stringValue() : null;

        int id = (int) jsonObject.get("ID").isNumber().doubleValue();

        String tipoGioco = jsonObject.containsKey("tipoGioco") ? jsonObject.get("tipoGioco").isString().stringValue() : null;

        //solo magic
        if(tipoGioco.equals("Magic")) {
        	String artist = jsonObject.get("artist").isString().stringValue();

        	String text = jsonObject.get("text").isString().stringValue();
        	String types = jsonObject.get("types").isString().stringValue();
        	String rarity = jsonObject.get("rarity").isString().stringValue();
        	String hasFoil = jsonObject.get("hasFoil").isString().stringValue();
        	String isAlternative = jsonObject.get("isAlternative").isString().stringValue();
        	String isFullArt = jsonObject.get("isFullArt").isString().stringValue();
        	String isPromo = jsonObject.get("isPromo").isString().stringValue();
        	String isReprint = jsonObject.get("isReprint").isString().stringValue();

        
        	Magic_card mc = new Magic_card(artist, name, text, types, rarity, hasFoil, isAlternative, isFullArt, isPromo, isReprint);
        	mc.setTipoGioco("Magic");
        	mc.setID(id);
        	mc.setCondizione(condizione);
        	mc.setProprietario(proprietario);
        	mc.setAcquirente(acquirente);
        	mc.setTestoPersonale(testoPersonale);

        	return mc;
        }

        //solo pokemon
        else if (tipoGioco.equals("Pokemon")) {

            String illustrator = jsonObject.containsKey("illustrator") ? jsonObject.get("illustrator").isString().stringValue() : null;
        	String image = jsonObject.containsKey("image") ? jsonObject.get("image").isString().stringValue() : null;
        	String rarity = jsonObject.get("rarity").isString().stringValue();

        	// Parse the 'variants' object
        	JSONObject variantsJson = jsonObject.get("variants").isObject();
        	Variants variants = parseVariantsJson(variantsJson);

        	// Parse the 'types' array
        	String[] types = null;
        	if (jsonObject.containsKey("types") && jsonObject.get("types").isArray() != null) {
        		JSONArray typesArray = jsonObject.get("types").isArray();
        	    types = new String[typesArray.size()];
        	    for (int i = 0; i < typesArray.size(); i++) {
        	        types[i] = typesArray.get(i).isString().stringValue();
        	    }
        	}

        	Pokemon_card pokemonCard = new Pokemon_card();
        	pokemonCard.setName(name);
        	pokemonCard.setIllustrator(illustrator);
        	pokemonCard.setImage(image);
        	pokemonCard.setRarity(rarity);
        	pokemonCard.setVariants(variants);
        	pokemonCard.setTypes(types);
        	pokemonCard.setTipoGioco("Pokemon");
        	pokemonCard.setID(id);
        	pokemonCard.setCondizione(condizione);
        	pokemonCard.setProprietario(proprietario);
        	pokemonCard.setAcquirente(acquirente);
        	pokemonCard.setTestoPersonale(testoPersonale);

        	return pokemonCard;
        }


        //solo yugioh
        else {
        	String type = jsonObject.get("type").isString().stringValue();
            String desc = jsonObject.get("desc").isString().stringValue();
            String race = jsonObject.get("race").isString().stringValue();

            String image_url = jsonObject.containsKey("image_url") ? jsonObject.get("image_url").isString().stringValue() : null;
            String small_image_url = jsonObject.containsKey("small_image_url") ? jsonObject.get("small_image_url").isString().stringValue() : null;

            Yugioh_card yugiohCard = new Yugioh_card();
            yugiohCard.setName(name);
            yugiohCard.setType(type);
            yugiohCard.setDesc(desc);
            yugiohCard.setRace(race);
            yugiohCard.setImageUrl(image_url);
            yugiohCard.setSmallImageUrl(small_image_url);

            yugiohCard.setTipoGioco("Yugioh");
            yugiohCard.setID(id);
            yugiohCard.setCondizione(condizione);
            yugiohCard.setProprietario(proprietario);
            yugiohCard.setAcquirente(acquirente);
            yugiohCard.setTestoPersonale(testoPersonale);

            return yugiohCard;
        }


    }

    //questo parsa le varianti che stanno dentro alla  carta pokemon
    private Variants parseVariantsJson(JSONObject variantsJson) {
        boolean firstEdition = variantsJson.get("firstEdition").isBoolean().booleanValue();
        boolean holo = variantsJson.get("holo").isBoolean().booleanValue();
        boolean normal = variantsJson.get("normal").isBoolean().booleanValue();
        boolean reverse = variantsJson.get("reverse").isBoolean().booleanValue();
        boolean wPromo = variantsJson.get("wPromo").isBoolean().booleanValue();

        Variants variants = new Variants();
        variants.setFirstEdition(firstEdition);
        variants.setHolo(holo);
        variants.setNormal(normal);
        variants.setReverse(reverse);
        variants.setwPromo(wPromo);

        return variants;
    }

    //parsa la stringa in un deck
    public Deck parseJsonStringToDeck(String jsonString) {
        JSONValue jsonValue = JSONParser.parseStrict(jsonString);
        JSONObject jsonObject = jsonValue.isObject();

        String name = jsonObject.get("name").isString().stringValue();
    	String utente = jsonObject.containsKey("utente") ? jsonObject.get("utente").isString().stringValue() : null;
        String tipoGioco = jsonObject.containsKey("tipoGioco") ? jsonObject.get("tipoGioco").isString().stringValue() : null;

        int id = (int) jsonObject.get("ID").isNumber().doubleValue();

        List<Integer> deckList = new ArrayList<>();
        if(jsonObject.containsKey("deckList") && jsonObject.get("deckList").isArray() != null) {
        	JSONArray deckListArray = jsonObject.get("deckList").isArray();
        	for(int i = 0; i < deckListArray.size(); i++) {
        		JSONValue cardId = deckListArray.get(i);
        		int parsedId = Integer.parseInt(cardId.isString().stringValue());
        		deckList.add(parsedId);
        	}
        }
        Deck parsedDeck = new Deck(name, tipoGioco, utente);
        parsedDeck.setDeckList(deckList);
        return parsedDeck;
    }

    //converte un oggetto Deck in una stringa
    public JSONObject convertDeckToJson(Deck mazzo) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("ID", new JSONNumber(mazzo.getID()));

        if (mazzo.getName() != null)
            jsonObject.put("name", new JSONString(mazzo.getName()));
        if (mazzo.getUtente() != null)
            jsonObject.put("utente", new JSONString(mazzo.getUtente()));
        if (mazzo.getTipoGioco() != null)
            jsonObject.put("tipoGioco", new JSONString(mazzo.getTipoGioco()));

        JSONArray deckListArray = new JSONArray();
        for (int cardId : mazzo.getDeckList()) {
            deckListArray.set(deckListArray.size(), new JSONString(Integer.toString(cardId)));
        }
        jsonObject.put("deckList", deckListArray);
        return jsonObject;
    }



    //converte un oggetto scambio in una stringa
    public String convertScambioToJsonString(Scambio scambio) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("proponente", new JSONString(scambio.getProponente()));
        jsonObject.put("ricevente", new JSONString(scambio.getRicevente()));

        // Serialize the 'offerta' list
        JSONArray offertaArray = new JSONArray();
        for (Card card : scambio.getOfferta()) {
            offertaArray.set(offertaArray.size(), convertCardToJson(card));
        }
        jsonObject.put("offerta", offertaArray);

        // Serialize the 'richiesta' list
        JSONArray richiestaArray = new JSONArray();
        for (Card card : scambio.getRichiesta()) {
            richiestaArray.set(richiestaArray.size(), convertCardToJson(card));
        }
        jsonObject.put("richiesta", richiestaArray);

        return jsonObject.toString();
    }



    //converte un oggetto carta in una stringa
    public JSONObject convertCardToJson(Card carta) {
        JSONObject jsonObject = new JSONObject();

        //generico
        if (carta.getAcquirente() != null) {
            jsonObject.put("acquirente", new JSONString(carta.getAcquirente()));}
            if (carta.getCondizione() != null) {
            jsonObject.put("condizione", new JSONString(carta.getCondizione()));}
            if (carta.getProprietario() != null) {
            jsonObject.put("proprietario", new JSONString(carta.getProprietario()));}
            //jsonObject.put("isReprint", new JSONString(magicCard.getTipoGioco()));
            jsonObject.put("ID", new JSONNumber(carta.getID()));
            if(carta.getTestoPersonale() != null) {
            	jsonObject.put("testoPersonale", new JSONString(carta.getTestoPersonale()));
            }

            jsonObject.put("name", new JSONString(carta.getName()));
            jsonObject.put("tipoGioco", new JSONString(carta.getTipoGioco()));

            //magic
        if(carta instanceof Magic_card) {
        	Magic_card magicCard = (Magic_card) carta;

        jsonObject.put("artist", new JSONString(magicCard.getArtist()));
        jsonObject.put("text", new JSONString(magicCard.getText()));
        jsonObject.put("types", new JSONString(magicCard.getTypes()));
        jsonObject.put("rarity", new JSONString(magicCard.getRarity()));
        jsonObject.put("hasFoil", new JSONString(magicCard.getHasFoil()));
        jsonObject.put("isAlternative", new JSONString(magicCard.getIsAlternative()));
        jsonObject.put("isFullArt", new JSONString(magicCard.getIsFullArt()));
        jsonObject.put("isPromo", new JSONString(magicCard.getIsPromo()));
        jsonObject.put("isReprint", new JSONString(magicCard.getIsReprint()));


        }
        //pokemon
        else  if(carta instanceof Pokemon_card) {
        	Pokemon_card pokemonCard = (Pokemon_card) carta;

        	    jsonObject.put("illustrator", new JSONString(pokemonCard.getIllustrator()));
        	    jsonObject.put("image", new JSONString(pokemonCard.getImage()));
        	    jsonObject.put("rarity", new JSONString(pokemonCard.getRarity()));

        	    // Create and populate the 'variants' JSON object
        	    JSONObject variantsJson = new JSONObject();
        	    variantsJson.put("firstEdition", JSONBoolean.getInstance(pokemonCard.getVariants().isFirstEdition()));
        	    variantsJson.put("holo", JSONBoolean.getInstance(pokemonCard.getVariants().isHolo()));
        	    variantsJson.put("normal", JSONBoolean.getInstance(pokemonCard.getVariants().isNormal()));
        	    variantsJson.put("reverse", JSONBoolean.getInstance(pokemonCard.getVariants().isReverse()));
        	    variantsJson.put("wPromo", JSONBoolean.getInstance(pokemonCard.getVariants().iswPromo()));

        	    jsonObject.put("variants", variantsJson);

        	    // Create and populate the 'types' JSON array
        	    if(pokemonCard.getTypes() != null) {
        	    JSONArray typesArray = new JSONArray();
        	    for (String type : pokemonCard.getTypes()) {
        	        typesArray.set(typesArray.size(), new JSONString(type));
        	    }
        	    jsonObject.put("types", typesArray);}

        }

        else { //yugi

        	Yugioh_card yugiohCard = (Yugioh_card) carta;

        	jsonObject.put("type", new JSONString(yugiohCard.getType()));
            jsonObject.put("desc", new JSONString(yugiohCard.getDesc()));
            jsonObject.put("race", new JSONString(yugiohCard.getRace()));
            jsonObject.put("image_url", new JSONString(yugiohCard.getImageUrl()));
            jsonObject.put("small_image_url", new JSONString(yugiohCard.getSmallImageUrl()));
        }

        return jsonObject;
    }


    //questo mette le carte in griglia
    private void addCarte(Card carta, Panel panel, FlexTable FlexTable, boolean scambia) { //devo basarlo su due diverse liste e devo mettere forse l'oggetto "scambio"

		int row = FlexTable.getRowCount();
		//questo è a seconda che si scambi o meno
		 if(FlexTable == cardsFlexTable)
        cards.add(carta);
		 else
		cards2.add(carta);

        FlexTable.setText(row, 1, carta.getName());
        String gioco = carta.getTipoGioco() + " | ";
        FlexTable.setText(row, 0, gioco);

        //il tipo funziona diverso per le tre tipologie
        if(carta instanceof Magic_card) {
        	Magic_card MC = (Magic_card) carta;
        FlexTable.setText(row, 2, MC.getTypes());}
        else if (carta instanceof Pokemon_card) {
        	Pokemon_card PK = (Pokemon_card) carta;
        	FlexTable.setText(row, 2, PK.getTypesString());
        }
        else {
        	Yugioh_card YC = (Yugioh_card) carta;
            FlexTable.setText(row, 2, YC.getType());
        }


        FlexTable.setText(row, 4, String.valueOf(carta.getID()));
        FlexTable.getCellFormatter().addStyleName(row, 2, "watchListNumericColumn");


        //questo button X è fraintendibile e forse andrebbe tolto perchè toglie solo dalla griglia
        Button removeStockButton = new Button("x");
        removeStockButton.addStyleDependentName("remove");
        removeStockButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                int removedIndex = cards.indexOf(carta);
                cards.remove(removedIndex);
                FlexTable.removeRow(removedIndex +1);
            }
        });

        if(scambia)
        	FlexTable.setText(row, 3, carta.getCondizione());
        else
        	FlexTable.setWidget(row, 3, removeStockButton);


        if(FlexTable == cardsFlexTable ||FlexTable == cardsFlexTable2)
        if(scambia) {
        	 Button add = new Button("+");
             add.addClickHandler(new ClickHandler() {
                 public void onClick(ClickEvent event) {
                     if(FlexTable == cardsFlexTable) {
                    	 int removedIndex = cards.indexOf(carta);
                         cards.remove(removedIndex);
                         FlexTable.removeRow(removedIndex +1);
                    	 addCarte(carta, panel, cardsFlexTableOffer, scambia);
                    	 SC.addCardToOfferta(carta);
                     }
                     else {
                     int removedIndex = cards2.indexOf(carta);
                     cards2.remove(removedIndex);
                     FlexTable.removeRow(removedIndex);
                    	 addCarte(carta, panel, cardsFlexTableRequest, scambia);
                    	 SC.addCardToRichiesta(carta);
                     }
                 }
             });
             FlexTable.setWidget(row, 4, add);
        }
        else{

        Button expand = new Button("o");
        expand.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	int c = cards.indexOf(carta);
            	actualCard = carta;
                linkToCard(cards.get(c), panel, false);
            }
        });
        FlexTable.setWidget(row, 4, expand);
        }


	}


  //questo mette le carte in griglia nel DeckBuilder
    private void addCollezioneCarte(Card carta, Panel panel,
    		FlexTable FlexTableCollection, FlexTable deckListFlexTable, boolean slim) {

		int row = FlexTableCollection.getRowCount();
		cards.add(carta);
		cardsId.add(carta.getID());
		
		String contestoString = carta.getTipoGioco() + " | ";
		FlexTableCollection.setText(row, 0, contestoString);
		FlexTableCollection.setText(row, 1, carta.getName());

		//il tipo funziona diverso per le tre tipologie
		if(carta instanceof Magic_card) {
			Magic_card MC = (Magic_card) carta;
			FlexTableCollection.setText(row, 2, "| " + MC.getTypes());}
		else if (carta instanceof Pokemon_card) {
			Pokemon_card PK = (Pokemon_card) carta;
			FlexTableCollection.setText(row, 2, "| " + PK.getTypesString());
		}
		else {
			Yugioh_card YC = (Yugioh_card) carta;
			FlexTableCollection.setText(row, 2, "| " + YC.getType());
		}

		//button expand per vedere i dettagli di una carta
		Button expand = new Button("o");
		expand.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int c = cards.indexOf(carta);
				actualCard = carta;
				linkToCard(cards.get(c), panel, slim);
			}
		});
		FlexTableCollection.setWidget(row, 3, expand);
		FlexTableCollection.getCellFormatter().addStyleName(row, 3, "watchListRemoveColumn");

		//button per aggiungere la carta al deck, solo se la carta può essere aggiunta
		Button addDeckButton = new Button ("+");
		addDeckButton.addStyleDependentName("add");
		addDeckButton.addClickHandler(new ClickHandler() {
    	public void onClick(ClickEvent event) {
    		/* 
    		*  Check per le copie della stessa carta in un deck, a secondo che il gioco sia:
    		*  yu-gi-oh (dove sono permesse solo 3 copie della stessa carta),
    		*  oppure Magic/Pokemon (che ne consentono 4);
    		*  
    		*  previo controllo la carta sia tra quelle possedute.
    		*/
    		int countCopies = 0;
    		boolean maxCopies = false;   //true-> numero di copie massimo raggiunto
    		boolean possedute = true;    //false-> non si possiedono sufficienti copie della carta
    		for(int identifier : actualDeck.getDeckList()) {
    			if(identifier == carta.getID()) { //si sta cercando di inserire una carta già presente
					countCopies++;
					possedute = false;
				}
    		}    		
	    	int countPossedute = 0;
			for(int idPosseduta : cardsId) {
				if(idPosseduta == carta.getID()) {
					countPossedute++;
				}
			}
			countCopies++; //si porta il numero di copie a +1, tenendo conto di quella che si sta cercando di aggiungere
			if(countPossedute >= countCopies) {
				possedute = true;
	    		if(carta.getTipoGioco().equals("Yugioh") && countCopies > 3) { 				
    				maxCopies = true;
	    		}else if(countCopies > 4) {
    				maxCopies = true;  				
	    		}
			}
 		
    		if(!maxCopies && possedute) {
    			 actualDeck.addCardToDeckList(carta.getID());

    			 String deckString = convertDeckToJson(actualDeck).toString();
    			 stockPriceSvc.updateDeck(actualDeck.getName(), deckString, contesto, new AsyncCallback<Void>() {
    				@Override
    				public void onFailure(Throwable caught) {
    					 Window.alert("Errore: "+ caught.getMessage());
     				}
     				@Override
     				public void onSuccess(Void result) {
     				//si aggiunge la carta alla tabella decklist
     					addDeckList(carta, panel, deckListFlexTable);
     					Window.alert(carta.getName() + " aggiunta al deck");
     				}
    			 });
    		}else {
    			Window.alert("Non è possibile inserire la carta");
    		}
    	  }
		});
		
		FlexTableCollection.setWidget(row, 4, addDeckButton);
		FlexTableCollection.getCellFormatter().addStyleName(row, 4, "watchListRemoveColumn");
	}


  //tabella relativa alla decklist, visualizza le carte del mazzo e i relativi comandi
    private void addDeckList(Card carta, Panel panel, FlexTable FlexTable) {

    	actualDeckList.add(carta);

		int row = FlexTable.getRowCount();
		String contestoString = carta.getTipoGioco() + " | ";
		FlexTable.setText(row, 0, contestoString);
		FlexTable.setText(row, 1, carta.getName());
		
		if(carta instanceof Magic_card) {
			Magic_card MC = (Magic_card) carta;
			FlexTable.setText(row, 2, "| "+MC.getTypes());
		}
		else if (carta instanceof Pokemon_card) {
			Pokemon_card PK = (Pokemon_card) carta;
        	FlexTable.setText(row, 2, "| "+PK.getTypesString());
		}
		else {
        	Yugioh_card YC = (Yugioh_card) carta;
            FlexTable.setText(row, 2, "| "+YC.getType());
		}

		//button expand per vedere i dettagli di una carta
		Button expand = new Button("o");
		expand.addClickHandler(new ClickHandler() {
          public void onClick(ClickEvent event) {
            	int c = actualDeckList.indexOf(carta);
            	actualCard = carta;
                linkToCard(actualDeckList.get(c), panel, true);
          }
		});
		FlexTable.setWidget(row, 3, expand);
		FlexTable.getCellFormatter().addStyleName(row, 3, "watchListRemoveColumn");

		//button per rimuovere la carta al deck
		Button removeFromDeck = new Button ("-");
		removeFromDeck.addStyleDependentName("Rimuovi dal Deck");
		removeFromDeck.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
        	//viene salvata la carta da rimuovere si aggiorna il deck
        	int removedIndex = actualDeckList.indexOf(carta);
		    
		    Card cardToRemove = actualDeckList.get(removedIndex);
		    actualDeck.removeCardFromDeckList(cardToRemove.getID(), actualUser);
		    String deckString = convertDeckToJson(actualDeck).toString();

		    stockPriceSvc.updateDeck(actualDeck.getName(), deckString, contesto, new AsyncCallback<Void>() {
               	@Override
			    public void onFailure(Throwable caught) {
              		Window.alert("Errore: "+ caught.getMessage());
			    }
			    @Override
			    public void onSuccess(Void result) {
			        Window.alert(carta.getName() + " rimossa dal deck");
		            actualDeckList.remove(removedIndex);
		            //FlexTable.removeRow(removedIndex);
		            FlexTable.removeRow(removedIndex +1);
			    }
            });
         }
      });
      FlexTable.setWidget(row, 4, removeFromDeck);
      FlexTable.getCellFormatter().addStyleName(row, 4, "watchListRemoveColumn");
	}


  //mette i deck in una tabella visualizzabile
    private void addCollezioneDeck(Deck deck, Panel panel, FlexTable flexTable) {

		int row = flexTable.getRowCount();
		decks.add(deck);
		String numberOfCards = Integer.toString(deck.getNumberOfCardsInTheDeck());
		String tipogiocoString = deck.getTipoGioco() + " | ";

		flexTable.setText(row, 0, tipogiocoString);
		flexTable.setText(row, 1, deck.getName());
		String nOfCards;
		if(deck.getTipoGioco().equals("Yugioh")) {
			nOfCards = numberOfCards + "/40";
		}else {
			nOfCards = numberOfCards + "/60";
		}
		flexTable.setText(row, 2, nOfCards);
		flexTable.getCellFormatter().addStyleName(row, 2, "watchListRemoveColumn");

		//button 'Modifica' per modificare la lista delle carte del deck
		Button modifica = new Button("M");
		modifica.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
            	int d = decks.indexOf(deck);
            	actualDeck = decks.get(d);
            	cardsFlexTableCollection.removeAllRows();
                cardsFlexTableCollection.setText(0, 0, "Gioco");
                cardsFlexTableCollection.setText(0, 1, "Nome");
                cardsFlexTableCollection.setText(0, 2, "Tipo");
                cardsFlexTableCollection.setText(0, 3, "expand");
                cardsFlexTableCollection.setText(0, 4, "Aggiungi al Deck");
                cardsFlexTableCollection.setCellPadding(6);
                cardsFlexTableCollection.getRowFormatter().addStyleName(0, "watchListHeader");
                cardsFlexTableCollection.addStyleName("watchList");
                cardsFlexTableCollection.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
                cardsFlexTableCollection.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
            	
            	linkToCreateDeck(panel);
			}
		});


		//button 'Rinomina' per cambiare il nome del deck
		Button rinomina = new Button("R");
		rinomina.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int d = decks.indexOf(deck);
				actualDeck = deck;
				linkToNameDeck(decks.get(d), panel);
			}
		});

		//button 'Elimina' per cancellare il deck
		Button elimina = new Button ("X");
		elimina.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
        		int removedIndex = decks.indexOf(deck);
        		String nomeDelDeck = decks.get(removedIndex).getName();
        		decks.remove(removedIndex);
        		flexTable.removeRow(removedIndex +1);

        		stockPriceSvc.removeDeck(deck.getName(), contesto, new AsyncCallback<Void>(){
            		@Override
        		  	public void onFailure(Throwable caught) {
        				   Window.alert("Errore: " + caught.getMessage());
        			  }
        			  @Override
        			  public void onSuccess(Void result) {
        				  Window.alert("'" + deck.getName() + "' è stato rimosso");
        			  }
            	});
			}
		});

		flexTable.setWidget(row, 3, modifica);
		flexTable.setWidget(row, 4, rinomina);
		flexTable.setWidget(row, 5, elimina);
    	flexTable.getCellFormatter().addStyleName(row, 3, "watchListRemoveColumn");
    	flexTable.getCellFormatter().addStyleName(row, 4, "watchListRemoveColumn");
    	flexTable.getCellFormatter().addStyleName(row, 5, "watchListRemoveColumn");
	}
    

    //mette gli scambi in una griglia
	private void addScambio(Scambio skmb) {

		int row = scambiFlexTable.getRowCount();
        scambiL.add(skmb);

        scambiFlexTable.setText(row, 0, skmb.getProponente());
        scambiFlexTable.setText(row, 1, skmb.getTitoloOfferta());
        scambiFlexTable.setText(row, 2, skmb.getTitoloRichiesta());

        Button accetta = new Button("Accetta");
        accetta.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {

            	gestioneScambi(true, skmb);
            	int removedIndex = scambiL.indexOf(skmb);
            	scambiL.remove(removedIndex);
            	scambiFlexTable.removeRow(removedIndex +1);

            }
        });
        scambiFlexTable.setWidget(row, 3, accetta);


        Button rifiuta = new Button("Rifiuta");
        rifiuta.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	gestioneScambi(false, skmb);
            	int removedIndex = scambiL.indexOf(skmb);
            	scambiL.remove(removedIndex);
            	scambiFlexTable.removeRow(removedIndex);
            }
        });
        scambiFlexTable.setWidget(row, 4, rifiuta);

	}


    //mette le carte in una griglia ma con info diverse
    private void addCartaUser(boolean cercaAcquirenti, Card carta, Panel panel) {

    	cardsUserFlexTable.setVisible(true);

		int row = cardsUserFlexTable.getRowCount();
        cards.add(carta);

        if(cercaAcquirenti) {
        cardsUserFlexTable.setText(row, 0, carta.getAcquirente());

        }
        else {
        cardsUserFlexTable.setText(row, 0, carta.getProprietario());
        }
        cardsUserFlexTable.setText(row, 1, carta.getCondizione());
        cardsUserFlexTable.getCellFormatter().addStyleName(row, 2, "watchListNumericColumn");


        //questo button X è fraintendibile e forse andrebbe tolto
        Button removeStockButton = new Button("x");
        removeStockButton.addStyleDependentName("remove");
        removeStockButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                int removedIndex = cards.indexOf(carta);
                cards.remove(removedIndex);
                cardsUserFlexTable.removeRow(removedIndex +1);
            }
        });
        cardsUserFlexTable.setWidget(row, 2, removeStockButton);


        Button scambia ;
      scambia = new Button("Scambia");
        scambia.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {

            	if(actualUser == carta.getAcquirente()||actualUser == carta.getProprietario())
            		Window.alert("Non puoi scambiare carte con te stesso!");
            	else
            	{
            	actualCard = carta;
            	 if(cercaAcquirenti)
            		 linkToScambio(panel, carta.getAcquirente(), carta, cardsFlexTableOffer);
            	 else
            		 linkToScambio(panel, carta.getProprietario(), carta, cardsFlexTableRequest);
            }
            }
        });
    cardsUserFlexTable.setWidget(row, 3, scambia);



	}

    //questo cancella tutto, si usa per passare in maniera fittizia da una pagina all'altra
    private void hideAllElements(Panel panel) {
        for (Widget widget : panel) {
            widget.setVisible(false);
        }


    }

    //ogni metodo linkToPage mette visibile le cose che servono
    private void linkToListaScambi(Panel panel) {
    	hideAllElements(panel);
    	home.setVisible(true);
    	scambiFlexTable.setVisible(true);
    }


    //questo è un po' più elaborato perchè cambia a seconda del tipo di carta
    private void linkToCard(Card card, Panel panel, boolean slim) {
    	//slim -> FALSE : la chiamata arriva da Cerca Carte e vanno mostrate tutte le voci
    	//slim -> TRUE : la chiamata arriva dal DeckBuilder e alcune informazioni e iterazioni sono superflue

    	hideAllElements(panel);
    	home.setVisible(true);

    	nameLabel.setVisible(true);
    	nameTextBox.setText(card.getName());
    	nameTextBox.setVisible(true);
    	typesLabel.setVisible(true);
    	typesTextBox.setVisible(true);
    	idLabel.setVisible(true);
    	idTextBox.setText(String.valueOf(card.getID()));
    	idTextBox.setVisible(true);
    	
    	cardButtonFlexTable.removeAllRows();
    	cardDetailFlexTable.removeAllRows();
    	cardButtonFlexTable.setVisible(true);
    	cardDetailFlexTable.setVisible(true);
    	cardDetailFlexTable.setWidget(0,0, nameLabel);
    	cardDetailFlexTable.setWidget(0,1, nameTextBox);
    	cardDetailFlexTable.setWidget(1,0, typesLabel);
    	cardDetailFlexTable.setWidget(1,1, typesTextBox);
    	cardDetailFlexTable.setWidget(2,0, idLabel);
    	cardDetailFlexTable.setWidget(2,1, idTextBox);
    	
    	int rc = 2; //rowCounter for the details table
    	int rb = 0; //rowCounter for the buttons table

    	if (card instanceof Magic_card) {
    		
    		Magic_card MC = (Magic_card) card;
    		artistTextBox.setText(MC.getArtist());
    		textTextBox.setText(MC.getText());
    		typesTextBox.setText(MC.getTypes());
    		rarityTextBox.setText(MC.getRarity());
    		hasFoilTextBox.setText(MC.getHasFoil());
    		isAlternativeTextBox.setText(MC.getIsAlternative());
    		isFullArtTextBox.setText(MC.getIsFullArt());
    		isPromoTextBox.setText(MC.getIsPromo());
    		isReprintTextBox.setText(MC.getIsReprint());
    		rarityLabel.setVisible(true);
    		rarityTextBox.setVisible(true);
    		isPromoLabel.setVisible(true);
    		isReprintLabel.setVisible(true);
    		isPromoTextBox.setVisible(true);
    		isReprintTextBox.setVisible(true);
    		hasFoilLabel.setVisible(true);
    		isAlternativeLabel.setVisible(true);
    		isFullArtLabel.setVisible(true);
    		hasFoilTextBox.setVisible(true);
    		isAlternativeTextBox.setVisible(true);
    		isFullArtTextBox.setVisible(true);
    		textLabel.setVisible(true);
    		textTextBox.setVisible(true);
    		artistLabel.setVisible(true);
    		artistTextBox.setVisible(true);
        	cardDetailFlexTable.setWidget(3,0, rarityLabel);
        	cardDetailFlexTable.setWidget(3,1, rarityTextBox);
        	cardDetailFlexTable.setWidget(4,0, isPromoLabel);
        	cardDetailFlexTable.setWidget(4,1, isPromoTextBox);
        	cardDetailFlexTable.setWidget(5,0, isReprintLabel);
        	cardDetailFlexTable.setWidget(5,1, isReprintTextBox);
        	cardDetailFlexTable.setWidget(6,0, hasFoilLabel);
        	cardDetailFlexTable.setWidget(6,1, hasFoilTextBox);
        	cardDetailFlexTable.setWidget(7,0, isAlternativeLabel);
        	cardDetailFlexTable.setWidget(7,1, isAlternativeTextBox);
        	cardDetailFlexTable.setWidget(8,0, isFullArtLabel);
        	cardDetailFlexTable.setWidget(8,1, isFullArtTextBox);
        	cardDetailFlexTable.setWidget(9,0, textLabel);
        	cardDetailFlexTable.setWidget(9,1, textTextBox);
        	cardDetailFlexTable.setWidget(10,0, artistLabel);
        	cardDetailFlexTable.setWidget(10,1, artistTextBox);
        	rc = 10;
    	}
    	else if (card instanceof Pokemon_card) {
    		
    		Pokemon_card PK = (Pokemon_card) card;
    		artistTextBox.setText(PK.getIllustrator());
    		typesTextBox.setText(PK.getTypesString());
    		rarityTextBox.setText(PK.getRarity());
    		rarityLabel.setVisible(true);
    		rarityTextBox.setVisible(true);

    		firstEditionLabel.setVisible(true);
    		holoLabel.setVisible(true);
    		normalLabel.setVisible(true);
    		reverseLabel.setVisible(true);
    		wPromoLabel.setVisible(true);
    		firstEditionTxtBox.setVisible(true);
    		holoTxtBox.setVisible(true);
    		normalTxtBox.setVisible(true);
    		reverseTxtBox.setVisible(true);
    		wPromoTxtBox.setVisible(true);
    		artistLabel.setVisible(true);
    		artistTextBox.setVisible(true);

    		firstEditionTxtBox.setText(String.valueOf((PK.getVariants()).isFirstEdition()));
    		holoTxtBox.setText(String.valueOf((PK.getVariants()).isHolo()));
    		normalTxtBox.setText(String.valueOf((PK.getVariants()).isNormal()));
    		reverseTxtBox.setText(String.valueOf((PK.getVariants()).isReverse()));
    		wPromoTxtBox.setText(String.valueOf((PK.getVariants()).iswPromo()));
    		
    		cardDetailFlexTable.setWidget(3,0, rarityLabel);
        	cardDetailFlexTable.setWidget(3,1, rarityTextBox);
        	cardDetailFlexTable.setWidget(4,0, firstEditionLabel);
        	cardDetailFlexTable.setWidget(4,1, firstEditionTxtBox);
        	cardDetailFlexTable.setWidget(5,0, holoLabel);
        	cardDetailFlexTable.setWidget(5,1, holoTxtBox);
        	cardDetailFlexTable.setWidget(6,0, normalLabel);
        	cardDetailFlexTable.setWidget(6,1, normalTxtBox);
        	cardDetailFlexTable.setWidget(7,0, reverseLabel);
        	cardDetailFlexTable.setWidget(7,1, reverseTxtBox);
        	cardDetailFlexTable.setWidget(8,0, wPromoLabel);
        	cardDetailFlexTable.setWidget(8,1, wPromoTxtBox);
        	cardDetailFlexTable.setWidget(9,0, artistLabel);
        	cardDetailFlexTable.setWidget(9,1, artistTextBox);
        	rc = 9;
      }
      else {
    	  
      	Yugioh_card YH = (Yugioh_card) card;
      	typesTextBox.setText(YH.getType());

      	textLabel.setVisible(true);
      	textTextBox.setVisible(true);
      	textTextBox.setText(YH.getDesc());
        raceLabel.setVisible(true);
        raceTxtBox.setVisible(true);
        raceTxtBox.setText(YH.getRace());
        raceTxtBox.setReadOnly(true);
        
    	cardDetailFlexTable.setWidget(3,0, textLabel);
    	cardDetailFlexTable.setWidget(3,1, textTextBox);        	
    	cardDetailFlexTable.setWidget(4,0, raceLabel);
    	cardDetailFlexTable.setWidget(4,1, raceTxtBox);    	
    	rc = 4;

        if(YH.getSmallImageUrl() != null) {
          littleImage.setUrl(YH.getSmallImageUrl());
          largeImage.setUrl(YH.getImageUrl());
          
          littleImage.setVisible(true);
          zoomInButton.setVisible(true);
          rc++;
          cardDetailFlexTable.setWidget(rc, 0, zoomInButton);
          cardDetailFlexTable.setWidget(rc, 1, littleImage);
          rc++;
          cardDetailFlexTable.setWidget(rc, 0, zoomOutButton);
          cardDetailFlexTable.setWidget(rc, 1, largeImage);
          
          //bottone per ingrandire l'immagine
          zoomInButton.addClickHandler(new ClickHandler() {
       		 public void onClick(ClickEvent event) {
       			 zoomInButton.setVisible(false);
       			 zoomOutButton.setVisible(true);
       			 littleImage.setVisible(false);
       			 largeImage.setVisible(true);
       			 
       		 }
       	  });
          
          //bottone per rimpicciolirla
          zoomOutButton.addClickHandler(new ClickHandler() {
        	 public void onClick(ClickEvent event) {
        		 zoomInButton.setVisible(true);
        		 zoomOutButton.setVisible(false);
       			 littleImage.setVisible(true);
       			 largeImage.setVisible(false);
        	 }
          });
          
        }
      }

      if(card.getCondizione() != null) {
      	condizioneLabel.setVisible(true);
      	condizioneTextBox.setVisible(true);
      	condizioneTextBox.setText(card.getCondizione());
      	condizioneTextBox.setReadOnly(true);
      	rc++;    	
    	cardDetailFlexTable.setWidget(rc,0, condizioneLabel);
    	cardDetailFlexTable.setWidget(rc,1, condizioneTextBox);
    	
      	if(!slim) {
      		rimuoviCarta.setVisible(true);
      		cardButtonFlexTable.setWidget(rb,0, rimuoviCarta);
      		rb++;
      	}
      	
      }


     // Set TextBoxes as read-only
      artistTextBox.setReadOnly(true);
      nameTextBox.setReadOnly(true);
      textTextBox.setReadOnly(true);
      typesTextBox.setReadOnly(true);
      rarityTextBox.setReadOnly(true);
      hasFoilTextBox.setReadOnly(true);
      isAlternativeTextBox.setReadOnly(true);
      isFullArtTextBox.setReadOnly(true);
      isPromoTextBox.setReadOnly(true);
      isReprintTextBox.setReadOnly(true);
      idTextBox.setReadOnly(true);
      firstEditionTxtBox.setReadOnly(true);
      holoTxtBox.setReadOnly(true);
      normalTxtBox.setReadOnly(true);
      reverseTxtBox.setReadOnly(true);
      wPromoTxtBox.setReadOnly(true);
      

      if(card.getTestoPersonale() != null) {
    	  personalTextBox.setText(card.getTestoPersonale());
      }

      rc += 2;
   	  cardDetailFlexTable.setWidget(rc, 0, personalTextLabel);
   	  cardDetailFlexTable.setWidget(rc, 1, personalTextBox);
      personalTextLabel.setVisible(true);
      personalTextBox.setVisible(true);
      
      if(!slim) {
    	  
      //il parametro slim->false indica di mostrare anche le funzionalità iterative e relative allo scambio
      	userTextBox.setVisible(true);
      	cercaCarteInterno.setVisible(true);
      	listBox.setVisible(true);
      	rimuoviCartaDesiderata.setVisible(true);
      	cercaProprietari.setVisible(true);
      	cercaAcquirenti.setVisible(true);
      	aggiungiCarta.setVisible(true);
      	cartaDesiderata.setVisible(true);  
      	String condizioneLabel = "condizione: ";
    	cardButtonFlexTable.setText(rb, 0, condizioneLabel);
    	rb++;
    	cardButtonFlexTable.setWidget(rb,0, listBox);
    	rb += 2;    	
    	cardButtonFlexTable.setWidget(rb,0, cercaProprietari);	
    	rb++;
    	cardButtonFlexTable.setWidget(rb,0, cercaAcquirenti);
    	rb++;
    	cardButtonFlexTable.setWidget(rb,0, aggiungiCarta);
    	rb++;
    	cardButtonFlexTable.setWidget(rb,0, cartaDesiderata);
    	rb++;
    	cardButtonFlexTable.setWidget(rb,0, rimuoviCartaDesiderata);
    	
      }else {
    	  
    	personalTextBox.setReadOnly(true);
      	deckMenu.setVisible(true);
        backToDeckBuilding.setVisible(true);
      }
      rb++;
      cardButtonFlexTable.setText(rb, 0, "Utente: ");
      rb++;
      cardButtonFlexTable.setWidget(rb, 0, userTextBox);
    }
    


    private void linkToIscrizione(Panel panel) {

    	hideAllElements(panel);
    	usrLabel.setVisible(true);
    	newSymbolTextBox.setVisible(true);
    	pwdLabel.setVisible(true);
        pwdTextBox.setVisible(true);
        iscrivitiButton.setVisible(true);
    }

    private void linkToLogin(Panel panel) {

    	hideAllElements(panel);
    	usrLabel.setVisible(true);
    	checkUserTextBox.setVisible(true);
    	pwdLabel.setVisible(true);
        checkPwdTextBox.setVisible(true);
        loginButton.setVisible(true);
        toIscrizioneButton.setVisible(true);
    }



    private void linkToGeneralActions(Panel panel) {

    	hideAllElements(panel);
    	home.setVisible(true);
    	cercaCarte.setVisible(true);
    	buildDeck.setVisible(true);
    	mostraScambi.setVisible(true);
    }



    private void linkToHome(Panel panel) {

    	hideAllElements(panel);
    	mostraScambi.setVisible(true);
    	userTextBox.setVisible(true);
    	YugiohButton.setVisible(true);
    	MagicButton.setVisible(true);
    	PokemonButton.setVisible(true);
    	logoutButton.setVisible(true);
    	
    	FlexTable userFlexTable = new FlexTable(); 
    	userFlexTable.setCellPadding(10);
    	userFlexTable.setCellSpacing(10);
    	userFlexTable.addStyleName("searchWatchlist");
    	
    	userFlexTable.setText(0, 0, "Utente: ");
    	userFlexTable.setWidget(1, 0, userTextBox);
    	userFlexTable.setWidget(2, 0, logoutButton);
    	addPanel.add(userFlexTable);
    	userFlexTable.setVisible(true);
    }


    private void linkToSearch(Panel panel) {

    	hideAllElements(panel);
    	home.setVisible(true);
    	showCardsButton.setVisible(true);
    	cardsFlexTable.setVisible(true);
    	
    	searchFlexTable.setWidget(0, 0, filtraTipoLabel);
    	searchFlexTable.setWidget(1, 0, filtraTipo);
    	searchFlexTable.setWidget(2, 0, checkPersonal);
    	searchFlexTable.setWidget(3, 0, checkDesiderate);
    	
    	searchFlexTable.setVisible(true);
        checkPersonal.setVisible(true);
    	checkDesiderate.setVisible(true);
        filtraTipo.setVisible(true);
        filtraTipoLabel.setVisible(true);

    }



   private void linkToCreateDeck(Panel panel) {

     hideAllElements(panel);
     home.setVisible(true);
     deckMenu.setVisible(true);
     userTextBox.setVisible(true);
     showMyCards.setVisible(true);
     myCollection.setVisible(true);
     deckListFlexTable.setVisible(true);
     myDeckList.setVisible(true);
     cardsFlexTableCollection.setVisible(true);
     currentDeckTextBox.setText(actualDeck.getName());
     currentDeckTextBox.setVisible(true);
     
     menuButtonFlexTable.clear();
     menuButtonFlexTable.setVisible(true);
     menuButtonFlexTable.setWidget(0, 0, deckMenu);
     for(int i = 1; i<5; i++) {
    	 menuButtonFlexTable.setText(i, 0, ".");
     }
     menuButtonFlexTable.setWidget(5, 0, showMyCards);
     menuButtonFlexTable.setText(6, 0, "- Deck: ");
     menuButtonFlexTable.setWidget(7, 0, currentDeckTextBox);
     menuButtonFlexTable.setText(8, 0, "- Utente: ");
     menuButtonFlexTable.setWidget(9, 0, userTextBox);
     
     deckbuildingFlexTable.setVisible(true);
     deckbuildingFlexTable.setWidget(0,0, myCollection);
     deckbuildingFlexTable.getCellFormatter().addStyleName(0, 0, "verticalTop");
     deckbuildingFlexTable.setWidget(0,1, cardsFlexTableCollection);
     deckbuildingFlexTable.setWidget(2,0, myDeckList);
     deckbuildingFlexTable.getCellFormatter().addStyleName(2, 0, "verticalTop");
     deckbuildingFlexTable.setWidget(2,1, deckListFlexTable);


     //carica il deck dal database - dato il nome
     stockPriceSvc.loadDeckList(actualDeck.getName(), contesto, new AsyncCallback<String>() {
       @Override
       public void onFailure(Throwable caught) {
         Window.alert("Cannot load deckList: "+ caught.getMessage());
         linkToDeckMenu(panel);
       }
       @Override
       public void onSuccess(String deckJson) {
         actualDeck = parseJsonStringToDeck(deckJson);
       }
     });


     //cariche le carte contenute nella decklist, partendo dagli Id salvati
     stockPriceSvc.loadCarteFromId(actualDeck.getDeckList(), contesto, new AsyncCallback<String[]>() {
    	 
       @Override
       public void onFailure(Throwable caught) {
         Window.alert("Cannot load cards: " + caught.getMessage());
       }
       @Override
       public void onSuccess(String[] cardsJson) {
         int rowCount = deckListFlexTable.getRowCount();
         for(int i = rowCount-1; i > 0; i--){
           deckListFlexTable.removeRow(i);
         }
         actualDeckList.clear();
         for(int i = 0; i < cardsJson.length; i++) {
        	 Card parsedCard = parseJsonString(cardsJson[i]);
             addDeckList(parsedCard, addPanel, deckListFlexTable);
         }
         /*
         for(String jsonCard : cardsJson) {
        	 Window.alert("sono il for");
           Card parsedCard = parseJsonString(jsonCard);
           addDeckList(parsedCard, addPanel, deckListFlexTable);
         }*/
       }
     });
   }


   private void linkToDeckMenu(Panel panel) {

       hideAllElements(panel);
       home.setVisible(true);
       showDeck.setVisible(true);
       newDeck.setVisible(true);
       userTextBox.setVisible(true);   
    }


   //il deck come parametro indica che si sta rinominando un deck già esistente
    private void linkToNameDeck(Deck deck, Panel panel) {

      hideAllElements(panel);
      home.setVisible(true);
      deckMenu.setVisible(true);
      deckNameTextBox.setVisible(true);
      renameDeck.setVisible(true);
      currentDeckName.setVisible(true);
      currentDeckTextBox.setText(deck.getName());
      currentDeckTextBox.setVisible(true);

      actualDeck = deck;
    }


    private void linkToNameDeck(Panel panel) {

      hideAllElements(panel);
      home.setVisible(true);
      deckMenu.setVisible(true);
      deckNameTextBox.setText("");
      deckNameTextBox.setVisible(true);
      createNewDeck.setVisible(true);
    }


    private void gestioneScambi(boolean accettato, Scambio skmb) {


    	String scambio = convertScambioToJsonString(skmb);

    	String context = "";

    	List<String> actualCards = new ArrayList<>();
    	List<String> newCard = new ArrayList<>();
    	for(Card carta : skmb.getOfferta()) {
    		//lo parso e lo metto in una lista
    		actualCards.add(convertCardToJson(carta).toString());
    		if(accettato) {
    		carta.setProprietario(actualUser);}
    		newCard.add(convertCardToJson(carta).toString()); //versione con il nuovo proprietario
    		context = carta.getTipoGioco();
    	}


    	for(Card carta : skmb.getRichiesta()) {
    		//lo parso e lo metto in una lista
    		actualCards.add(convertCardToJson(carta).toString());
    		if(accettato) {
    		carta.setProprietario(skmb.getProponente());}
    		newCard.add(convertCardToJson(carta).toString());
    	}



		stockPriceSvc.gestisciScambio (context, accettato,scambio,  skmb.getProponente(), actualCards, actualUser, newCard, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("aggiunta carta fallita: "
						+ caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {

					Window.alert("carta inserita ok!");

			}
		});
    }




    private void linkToScambio(Panel panel, String acquirente, Card cartaBase, FlexTable FT) {

    	SC = new Scambio(actualUser, acquirente);

    	if(FT == cardsFlexTableRequest) {
    		//find = true;
    		addCarte(cartaBase, panel, FT, true);
    		SC.addCardToRichiesta(cartaBase);
    	 	hideAllElements(panel);
        	home.setVisible(true);
        	cardsFlexTable.setVisible(true);
            cardsFlexTableOffer.setVisible(true);
            cardsFlexTableRequest.setVisible(true);
            cardsFlexTable2.setVisible(true);
            inviaProposta.setVisible(true);
    	}



		boolean soloDesiderate = false;
		boolean soloPersonali = true;
		//soloPersonali lo passo al metodo e se è true invece che leggere da magic cards, legge da magic personal
		//il metodo di parsing deve essere sempre uguale

		stockPriceSvc.loadCarte(contesto, soloDesiderate, soloPersonali, new AsyncCallback<String[]>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Cannot load cards: "
						+ caught.getMessage());
			}

			@Override
			public void onSuccess(String[] carte) {

				int rowCount = cardsFlexTable.getRowCount();
		    	for(int i=rowCount -1 ; i > 0; i--)
		    	{cardsFlexTable.removeRow(i);}
		    	cards.clear();

				int rowCount2 = cardsFlexTable2.getRowCount();
		    	for(int i=rowCount2 -1 ; i > 0; i--)
		    	{cardsFlexTable2.removeRow(i);}
		    	cards2.clear();
		    	boolean find = false;
				for (String carta : carte) {

					Card magicCard = parseJsonString(carta);

					if(magicCard.getProprietario() == actualUser) {

					  if(magicCard.getName() == cartaBase.getName()){
						  addCarte(magicCard, panel, FT, true);
						  SC.addCardToOfferta(magicCard);
					    	hideAllElements(panel);
					    	home.setVisible(true);
					    	cardsFlexTable.setVisible(true);
					        cardsFlexTableOffer.setVisible(true);
					        cardsFlexTableRequest.setVisible(true);
					        cardsFlexTable2.setVisible(true);
					        inviaProposta.setVisible(true);
					        find = true;
					  }
					  else
						  addCarte(magicCard, addPanel, cardsFlexTable, true);
					}

					if(magicCard.getProprietario() == acquirente) {
						if(FT == cardsFlexTableRequest) {
							if(magicCard.getName() != cartaBase.getName())
						addCarte(magicCard, addPanel, cardsFlexTable2, true);}
						else
							addCarte(magicCard, addPanel, cardsFlexTable2, true);}

				}
				if(!find && FT == cardsFlexTableOffer) {
					Window.alert("Stai provando a cedere una carta che non hai");
				}
			}

		});

    }

}
