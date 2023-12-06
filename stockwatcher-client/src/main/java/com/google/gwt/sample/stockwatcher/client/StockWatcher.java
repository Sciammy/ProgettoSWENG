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
	private HorizontalPanel addPanel2 = new HorizontalPanel();
	private HorizontalPanel addPanel3 = new HorizontalPanel();
	private HorizontalPanel addPanel4 = new HorizontalPanel();
	private HorizontalPanel addPanel5 = new HorizontalPanel();
	private HorizontalPanel addPanel6 = new HorizontalPanel();
	private TextBox newSymbolTextBox = new TextBox();
	private TextBox pwdTextBox = new TextBox();
	private Button iscrivitiButton = new Button("iscriviti");
	private Label lastUpdatedLabel = new Label();
	private List<Card> cards = new ArrayList<>();
	private List<Card> cards2 = new ArrayList<>();
	private List<Scambio> scambiL = new ArrayList<>();
	private StockPriceServiceAsync stockPriceSvc = GWT.create(StockPriceService.class);
	private Label errorMsgLabel = new Label();
	private Label usrLabel = new Label();
	private Button loginButton = new Button("Login");
	private TextBox checkUserTextBox = new TextBox();
	private TextBox checkPwdTextBox = new TextBox();
	private Label pwdLabel = new Label();
	private Button toIscrizioneButton = new Button("Non sei utente? Iscriviti!");
	private Button cercaCarte = new Button("Cerca Carte");
	private Button creaDeck = new Button("Crea Deck");
	private Button mostraScambi = new Button("Gestione Scambi");
	private Button home = new Button("HOME");
	private Button MagicButton = new Button("Magic");
	private Button PokemonButton = new Button("Pokemon");
	private Button YugiohButton = new Button("Yugi-Oh");
	private Button showCardsButton = new Button("Mostra Carte");
	private TextBox textBox = new TextBox();
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
	private Label textLabel = new Label("Text:");
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
	private Label filtraTipoLabel = new Label("Filtra Tipo:");
	private TextBox filtraTipo = new TextBox();



	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {

		addPanel.add(cercaCarte);
		addPanel.add(creaDeck);
		addPanel.add(MagicButton);
		addPanel.add(PokemonButton);
		addPanel.add(YugiohButton);


		addPanel.add(mostraScambi);

		//table for scambi scambiFlexTable
		scambiFlexTable.setText(0, 0, "Offerente");
		scambiFlexTable.setText(0, 1, "Carte Offerte");
		scambiFlexTable.setText(0, 2, "Carte Richieste");
		scambiFlexTable.setText(0, 3, "Rifiuta");
		scambiFlexTable.setText(0, 4, "Accetta");
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
		cardsFlexTable.setCellSpacing(20);

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


		// Assemble Add Stock panel.
		addPanel.add(newSymbolTextBox);
		addPanel.add(pwdTextBox);
		//addPanel.add(addStockButton);
		addPanel.add(iscrivitiButton);
		addPanel.addStyleName("addPanel");
		addPanel2.addStyleName("addPanel2");
		addPanel3.addStyleName("addPanel3");
		addPanel4.addStyleName("addPanel4");
		addPanel4.addStyleName("addPanel5");
		addPanel4.addStyleName("addPanel6");




		// Assemble Main panel.
		errorMsgLabel.setStyleName("errorMessage");
		errorMsgLabel.setVisible(false);

		mainPanel.add(errorMsgLabel);
		mainPanel.add(addPanel);
		mainPanel.add(addPanel2);
		mainPanel.add(addPanel3);
		mainPanel.add(addPanel4);
		mainPanel.add(addPanel5);
		mainPanel.add(addPanel6);
		mainPanel.add(lastUpdatedLabel);

		// Associate the Main panel with the HTML host page.
		RootPanel.get("stockList").add(mainPanel);

		// Move cursor focus to the input box.
		newSymbolTextBox.setFocus(true);
		usrLabel.setText("Username: ");
		addPanel.add(usrLabel);
		addPanel.add(checkUserTextBox);
		pwdLabel.setText("Password: ");
		addPanel.add(pwdLabel);
		addPanel.add(checkPwdTextBox);
		addPanel.add(loginButton);
		addPanel.add(toIscrizioneButton);



		addPanel.add(showCardsButton);
		addPanel.add(textBox);
		addPanel.add(cardsFlexTable);
		addPanel.add(checkPersonal);
		addPanel.add(checkDesiderate);
		addPanel6.add(cardsUserFlexTable);


		//servono per filtrare durante la ricerca
		addPanel.add(filtraTipoLabel);
		addPanel.add(filtraTipo);



		// addPanel.add(test);

		//info carte

		//Artist
		addPanel2.add(artistLabel);
		addPanel2.add(artistTextBox);
		//Name
		addPanel2.add(nameLabel);
		addPanel2.add(nameTextBox);
		//Text
		addPanel2.add(textLabel);
		addPanel2.add(textTextBox);

		//Type
		addPanel2.add(typesLabel);
		addPanel2.add(typesTextBox);
		//Rarity
		addPanel2.add(rarityLabel);
		addPanel2.add(rarityTextBox);
		//Foil
		addPanel3.add(hasFoilLabel);
		addPanel3.add(hasFoilTextBox);

		addPanel3.add(isAlternativeLabel);
		addPanel3.add(isAlternativeTextBox);
		addPanel3.add(isFullArtLabel);
		addPanel3.add(isFullArtTextBox);
		addPanel3.add(isPromoLabel);
		addPanel3.add(isPromoTextBox);


		addPanel3.add(isReprintLabel);
		addPanel3.add(isReprintTextBox);
		addPanel3.add(idLabel);
		addPanel3.add(idTextBox);
		addPanel3.add(firstEditionLabel);
		addPanel3.add(firstEditionTxtBox);

		addPanel4.add(holoLabel);
		addPanel4.add(holoTxtBox);
		addPanel4.add(normalLabel);
		addPanel4.add(normalTxtBox);
		addPanel4.add(reverseLabel);
		addPanel4.add(reverseTxtBox);

		addPanel4.add(wPromoLabel);
		addPanel4.add(wPromoTxtBox);

		addPanel4.add(condizioneLabel);
		addPanel4.add(condizioneTextBox);

		addPanel4.add(personalTextLabel);
		addPanel4.add(personalTextBox);

		addPanel4.add(raceLabel);
		addPanel4.add(raceTxtBox);

		addPanel4.add(littleImage);
		addPanel4.add(largeImage);


		addPanel5.add(listBox);
		listBox.addItem("Sopravvissuta");
		listBox.addItem("Decente");
		listBox.addItem("Buona");
		listBox.addItem("Ottima");
		listBox.addItem("Sigillata");

		addPanel5.add(cercaProprietari);
		addPanel5.add(cercaAcquirenti);
		addPanel5.add(aggiungiCarta);
		addPanel5.add(rimuoviCarta);
		addPanel5.add(cartaDesiderata);
		addPanel5.add(rimuoviCartaDesiderata);

		addPanel6.add(cardsFlexTableOffer);
		addPanel6.add(cardsFlexTableRequest);
		addPanel6.add(cardsFlexTable2);
		addPanel6.add(inviaProposta);



		addPanel6.add(scambiFlexTable);

		addPanel.add(userTextBox);

		linkToLogin(addPanel);
		linkToLogin(addPanel2);
		linkToLogin(addPanel3);
		linkToLogin(addPanel4);
		linkToLogin(addPanel5);
		linkToLogin(addPanel6);

		userTextBox.setReadOnly(true);




		//questi sono solo link

		// Sara : li ripeto tutti per fare gli hide al cambio pagina

		toIscrizioneButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				linkToIscrizione(addPanel);
			}});

		toIscrizioneButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				linkToIscrizione(addPanel2);
			}});
		toIscrizioneButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				linkToIscrizione(addPanel3);
			}});
		toIscrizioneButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				linkToIscrizione(addPanel4);
			}});
		toIscrizioneButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				linkToIscrizione(addPanel5);
			}});
		toIscrizioneButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				linkToIscrizione(addPanel6);
			}});


		home.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				linkToHome(addPanel);
			}});
		home.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				linkToHome(addPanel2);
			}});
		home.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				linkToHome(addPanel3);
			}});
		home.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				linkToHome(addPanel4);
			}});
		home.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				linkToHome(addPanel5);
			}});
		home.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				linkToHome(addPanel6);
			}});



		YugiohButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Yugioh";
				linkToGeneralActions(addPanel);
			}});
		YugiohButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Yugioh";
				linkToGeneralActions(addPanel2);
			}});
		YugiohButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Yugioh";
				linkToGeneralActions(addPanel3);
			}});
		YugiohButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Yugioh";
				linkToGeneralActions(addPanel4);
			}});
		YugiohButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Yugioh";
				linkToGeneralActions(addPanel5);
			}});
		YugiohButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Yugioh";
				linkToGeneralActions(addPanel6);
			}});


		MagicButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Magic";
				linkToGeneralActions(addPanel);
			}});
		MagicButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Magic";
				linkToGeneralActions(addPanel2);
			}});
		MagicButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Magic";
				linkToGeneralActions(addPanel3);
			}});
		MagicButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Magic";
				linkToGeneralActions(addPanel4);
			}});
		MagicButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Magic";
				linkToGeneralActions(addPanel5);
			}});
		MagicButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Magic";
				linkToGeneralActions(addPanel6);
			}});


		PokemonButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Pokemon";
				linkToGeneralActions(addPanel);
			}});
		PokemonButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Pokemon";
				linkToGeneralActions(addPanel2);
			}});
		PokemonButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Pokemon";
				linkToGeneralActions(addPanel3);
			}});
		PokemonButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Pokemon";
				linkToGeneralActions(addPanel4);
			}});

		PokemonButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Pokemon";
				linkToGeneralActions(addPanel5);
			}});
		PokemonButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				contesto = "Pokemon";
				linkToGeneralActions(addPanel6);
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
						linkToListaScambi(addPanel6);
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
								addCartaUser(cercaAcquirenti, card, addPanel6);

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
								addCartaUser(cercaAcquirenti, card, addPanel6);
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

				stockPriceSvc.controlAccount(newSymbolTextBox.getText().toUpperCase().trim(), new AsyncCallback<Boolean>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Errore 1 ");
					}
					@Override
					public void onSuccess(Boolean result) {

						if(result)
						{Window.alert("Utente già presente");}
						else
						{

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

				} );



			}
		});



		//iscrizione - bisognerebbe mettere controllo su lunghezza pwd e che mail sia davvero una mail
		iscrivitiButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				stockPriceSvc.controlAccount(newSymbolTextBox.getText().toUpperCase().trim(), new AsyncCallback<Boolean>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Errore 1 ");
					}
					@Override
					public void onSuccess(Boolean result) {

						if(result)
						{Window.alert("Utente già presente");}
						else
						{

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

				} );



			}
		});


		cercaCarte.addClickHandler(new ClickHandler() {
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


			return mc;}


		//solo pokemon
		else if (tipoGioco.equals("Pokemon")) {

			String illustrator = jsonObject.containsKey("illustrator") ? jsonObject.get("illustrator").isString().stringValue() : null;

			String image = jsonObject.get("image").isString().stringValue();
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
		FlexTable.setText(row, 0, carta.getTipoGioco());

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
						linkToCard(cards.get(c), panel);
					}
				});
				FlexTable.setWidget(row, 4, expand);
			}


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
	private void linkToCard(Card card, Panel panel) {

		hideAllElements(panel);
		home.setVisible(true);
		cercaCarte.setVisible(true);
		textBox.setVisible(true);
		userTextBox.setVisible(true);
		//textBox.setText(card.toString());

		nameTextBox.setVisible(true);
		typesTextBox.setVisible(true);



		idTextBox.setVisible(true);

		nameLabel.setVisible(true);



		typesLabel.setVisible(true);



		idLabel.setVisible(true);
		listBox.setVisible(true);

		//questo dovrebbe essere quello compilabile dagli utenti
		personalTextLabel.setVisible(true);
		personalTextBox.setReadOnly(true);

		//valorizzato se presente nella carta
		if(card.getTestoPersonale() != null) {
			personalTextBox.setText(card.getTestoPersonale());
		}

		nameTextBox.setText(card.getName());

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

			if(YH.getSmallImageUrl() != null) {
				littleImage.setUrl(YH.getSmallImageUrl());
				littleImage.setVisible(true);
				largeImage.setUrl(YH.getImageUrl());
				largeImage.setVisible(true);
			}
		}


		idTextBox.setText(String.valueOf(card.getID()));

		if(card.getCondizione() != null) {
			condizioneLabel.setVisible(true);
			condizioneTextBox.setVisible(true);
			condizioneTextBox.setText(card.getCondizione());
			condizioneTextBox.setReadOnly(true);
			rimuoviCarta.setVisible(true);

		}

		rimuoviCartaDesiderata.setVisible(true);


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

		cercaProprietari.setVisible(true);
		cercaAcquirenti.setVisible(true);
		aggiungiCarta.setVisible(true);
		cartaDesiderata.setVisible(true);

	}



	private void linkToIscrizione(Panel panel) {

		hideAllElements(panel);
		newSymbolTextBox.setVisible(true);
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
		cercaCarte.setVisible(true);
		creaDeck.setVisible(true);
		mostraScambi.setVisible(true);
	}



	private void linkToHome(Panel panel) {

		hideAllElements(panel);
		mostraScambi.setVisible(true);
		userTextBox.setVisible(true);
		YugiohButton.setVisible(true);
		MagicButton.setVisible(true);
		PokemonButton.setVisible(true);
	}


	private void linkToSearch(Panel panel) {

		hideAllElements(panel);
		home.setVisible(true);
		cardsFlexTable.setVisible(true);
		showCardsButton.setVisible(true);
		checkPersonal.setVisible(true);
		userTextBox.setVisible(true);
		checkDesiderate.setVisible(true);
		filtraTipo.setVisible(true);
		filtraTipoLabel.setVisible(true);

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
							addCarte(magicCard, addPanel6, cardsFlexTable, true);
					}

					if(magicCard.getProprietario() == acquirente) {
						if(FT == cardsFlexTableRequest) {
							if(magicCard.getName() != cartaBase.getName())
								addCarte(magicCard, addPanel6, cardsFlexTable2, true);}
						else
							addCarte(magicCard, addPanel6, cardsFlexTable2, true);}

				}
				if(!find && FT == cardsFlexTableRequest) {
					Window.alert("Stai provando a cedere una carta che non hai");
				}
			}

		});

	}

}