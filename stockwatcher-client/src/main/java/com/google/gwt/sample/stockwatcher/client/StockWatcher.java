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
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import java.util.ArrayList;
import java.util.List;


public class StockWatcher implements EntryPoint {

    private Card actualCard;
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
    private TextBox pwdTextBox = new TextBox();
    private Button iscrivitiButton = new Button("iscriviti");
    private Label lastUpdatedLabel = new Label();
    private List<Card> cards = new ArrayList<>();
    private List<Card> cards2 = new ArrayList<>();
    private List<Scambio> scambiL = new ArrayList<>();
    private StockPriceServiceAsync stockPriceSvc = GWT.create(StockPriceService.class);
    private Label errorMsgLabel = new Label();
    private Button loginButton = new Button("Login");
    private TextBox checkUserTextBox = new TextBox();
    private TextBox checkPwdTextBox = new TextBox();
    private Button toIscrizioneButton = new Button("Non sei utente? Iscriviti!");
    private Button test = new Button("start");
    private Button cercaCarte = new Button("Cerca Carte");
    private Button creaDeck = new Button("Crea Deck");
    private Button mostraScambi = new Button("Gestione Scambi");
    private Button home = new Button("HOME");
    private Button showMagicButton = new Button("Magic");
    private Button showPokemonButton = new Button("Pokemon");
    private Button showYugiohButton = new Button("Yugi-Oh");
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




    /**
     * Entry point method.
     */
    public void onModuleLoad() {
    	
    	
        addPanel.add(cercaCarte);
        addPanel.add(creaDeck);
        addPanel.add(mostraScambi);
        addPanel.add(home);
    	
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
        
        
        // Assemble Add Stock panel.
        addPanel.add(newSymbolTextBox);
        addPanel.add(pwdTextBox);
        //addPanel.add(addStockButton);
        addPanel.add(iscrivitiButton);
        addPanel.addStyleName("addPanel");

		// Assemble Persistence panel
		//persistPanel.add(saveSymbolsButton);
		//persistPanel.add(loadSymbolsButton);
		//persistPanel.add(loadPWDButton);

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
        
        
        addPanel.add(checkUserTextBox);
        addPanel.add(checkPwdTextBox);
        addPanel.add(loginButton);
        addPanel.add(toIscrizioneButton);
        
        
        addPanel.add(showMagicButton); 
        addPanel.add(showPokemonButton); 
        addPanel.add(showYugiohButton); 
        addPanel.add(textBox);
        addPanel.add(cardsFlexTable);
        addPanel.add(checkPersonal);
        addPanel.add(checkDesiderate);
        addPanel.add(cardsUserFlexTable);

       // addPanel.add(test);
        
        //info carte
        addPanel.add(artistLabel);
        addPanel.add(artistTextBox);
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
        addPanel.add(userTextBox);
        
        addPanel.add(condizioneLabel);
        addPanel.add(condizioneTextBox);
        
        addPanel.add(personalTextLabel);
        addPanel.add(personalTextBox);

        
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
        
        linkToLogin(addPanel);
     
        userTextBox.setReadOnly(true);


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

        
        showMagicButton.addClickHandler(new ClickHandler() {
			@Override 
			public void onClick(ClickEvent event) {
				
				boolean soloDesiderate = checkDesiderate.getValue();
				//boolean cercaAcquirenti = false;
				boolean soloPersonali = checkPersonal.getValue();
				//soloPersonali lo passo al metodo e se è true invece che leggere da magic cards, legge da magic personal
				//il metodo di parsing deve essere sempre uguale
				
				stockPriceSvc.loadCarteMagic(soloDesiderate, soloPersonali, new AsyncCallback<String[]>() {
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
						//cardsFlexTable.clear(true);
				    	
						for (String carta : carte) {			    		     
							 //addStock(symbol); 
							Magic_card magicCard = parseJsonString(carta);
							if(soloPersonali)
							{if(magicCard.getProprietario() == actualUser)
							addCarte(magicCard, addPanel, cardsFlexTable, false);
							}
							else if(soloDesiderate) {
								if(magicCard.getAcquirente() == actualUser)
									addCarte(magicCard, addPanel, cardsFlexTable, false);
							}
							else {
							addCarte(magicCard, addPanel, cardsFlexTable, false);
							}

						}
					}

				
				});
			}
		});
		

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
						
						int rowCount = scambiFlexTable.getRowCount();
				    	for(int i=rowCount -1 ; i > 0; i--)
				    	{scambiFlexTable.removeRow(i);}
				    	scambiL.clear();
						//cardsFlexTable.clear(true);
				    	
						for (String scambio : scambi) {			    		     
							 //addStock(symbol); 
							
							Scambio skmb = parseScambioJsonString(scambio);
							
							if(skmb.getRicevente() == actualUser) {
							//scambiL.add(skmb);							
							addScambio(skmb);
							}
							
							
														

						}
						
						linkToListaScambi(addPanel);
					}

			

				
				});
			}
		});
        
    	
        
        
        
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
        
        
        
        
        cercaProprietari.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				boolean soloPersonali = true;
				String magic = actualCard.getTipoGioco();
				 boolean cercaAcquirenti = false;
				
				/*
				if(actualCard instanceof Magic_card)
				{}
				else if (actualCard instanceof Pokemon_card)
				{}
				else // if (actualCard instanceof Yugioh_card) NON SERVE, SE NON è MAGIC O POKEMON ALLORA è YUGI. 
				{}*/
				
				//MAGARI MI TIRO DIETRO UNA STRINGA PER DIRE SE METTERE NEL DATASET MAGIC, YUGIOH O POKEMON

				stockPriceSvc.loadCarteMagic(cercaAcquirenti, soloPersonali, new AsyncCallback<String[]>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Cannot load cards: "
								+ caught.getMessage());
					}

					@Override
					public void onSuccess(String[] carte) {	
						
						int rowCount = cardsUserFlexTable.getRowCount();
				    	for(int i=rowCount -1 ; i > 0; i--)
				    	{cardsUserFlexTable.removeRow(i);}
				    	cards.clear();
						//cardsFlexTable.clear(true);
				    	
						for (String carta : carte) {			    		     
							 //addStock(symbol); 
							
							if ( magic.equals("Magic") ) {							
							Magic_card card = parseJsonString(carta);
							
							
							if(card.getName() == actualCard.getName())
							addCartaUser(cercaAcquirenti, card, addPanel);
							}
							
							//addCarteMagic(magicCard, addPanel);
							

						}
					}

				
				});
			}
		});
        
        
        
        cercaAcquirenti.addClickHandler(new ClickHandler() {
     			@Override
     			public void onClick(ClickEvent event) {
     				
     				boolean soloPersonali = false;
     				boolean cercaAcquirenti = true;
     				String magic = actualCard.getTipoGioco();
     				
     				
     				/*
     				if(actualCard instanceof Magic_card)
     				{}
     				else if (actualCard instanceof Pokemon_card)
     				{}
     				else // if (actualCard instanceof Yugioh_card) NON SERVE, SE NON è MAGIC O POKEMON ALLORA è YUGI. 
     				{}*/
     				
     				//MAGARI MI TIRO DIETRO UNA STRINGA PER DIRE SE METTERE NEL DATASET MAGIC, YUGIOH O POKEMON

     				stockPriceSvc.loadCarteMagic(cercaAcquirenti, soloPersonali, new AsyncCallback<String[]>() {
     					@Override
     					public void onFailure(Throwable caught) {
     						Window.alert("Cannot load cards: "
     								+ caught.getMessage());
     					}

     					@Override
     					public void onSuccess(String[] carte) {	
     						
     						int rowCount = cardsUserFlexTable.getRowCount();
     				    	for(int i=rowCount -1 ; i > 0; i--)
     				    	{cardsUserFlexTable.removeRow(i);}
     				    	cards.clear();
     						//cardsFlexTable.clear(true);
     				    	
     						for (String carta : carte) {			    		     
     							 //addStock(symbol); 
     							
     							if ( magic.equals("Magic") ) {							
     							Magic_card card = parseJsonString(carta);
     							
     							
     							if(card.getName() == actualCard.getName())
     							addCartaUser(cercaAcquirenti, card, addPanel);
     							}
     							
     							//addCarteMagic(magicCard, addPanel);
     							

     						}
     					}

     				
     				});
     			}
     		});
        
        
        
        cartaDesiderata.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				boolean desiderata = true;
				//actualCard.setCondizione(listBox.getSelectedValue());
				actualCard.setAcquirente(actualUser);
				actualCard.setCondizione(null);
				actualCard.setProprietario(null);
				actualCard.setTestoPersonale(null);
				String stringCard = "";
				if(actualCard instanceof Magic_card)
				{stringCard = convertCardToJson((Magic_card) actualCard).toString();}
				else if (actualCard instanceof Pokemon_card)
				{}
				else // if (actualCard instanceof Yugioh_card) NON SERVE, SE NON è MAGIC O POKEMON ALLORA è YUGI. 
				{}
				
				//MAGARI MI TIRO DIETRO UNA STRINGA PER DIRE SE METTERE NEL DATASET MAGIC, YUGIOH O POKEMON

				
				stockPriceSvc.addCard(desiderata, actualUser, stringCard, new AsyncCallback<Void>() {
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
        
        
        
        aggiungiCarta.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				boolean desiderata = false;
				actualCard.setTestoPersonale(personalTextBox.getText());
				actualCard.setCondizione(listBox.getSelectedValue());
				actualCard.setProprietario(actualUser);
				actualCard.setAcquirente(null);
				String stringCard = "";
				if(actualCard instanceof Magic_card)
				{stringCard = convertCardToJson((Magic_card) actualCard).toString();}
				else if (actualCard instanceof Pokemon_card)
				{}
				else // if (actualCard instanceof Yugioh_card) NON SERVE, SE NON è MAGIC O POKEMON ALLORA è YUGI. 
				{}
				
				//MAGARI MI TIRO DIETRO UNA STRINGA PER DIRE SE METTERE NEL DATASET MAGIC, YUGIOH O POKEMON

				
				stockPriceSvc.addCard(desiderata, actualUser, stringCard, new AsyncCallback<Void>() {
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
        
        
        rimuoviCartaDesiderata.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				boolean desiderata = true;
				actualCard.setAcquirente(actualUser);
				String stringCard = "";
				if(actualCard instanceof Magic_card)
				{stringCard = convertCardToJson((Magic_card) actualCard).toString();}
				else if (actualCard instanceof Pokemon_card)
				{}
				else // if (actualCard instanceof Yugioh_card) NON SERVE, SE NON è MAGIC O POKEMON ALLORA è YUGI. 
				{}
				
				
				stockPriceSvc.rimuoviCarta(desiderata, stringCard, new AsyncCallback<Boolean>() {
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
        
        
        
        rimuoviCarta.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				boolean desiderata = false;
				String stringCard = "";
				if(actualCard instanceof Magic_card)
				{stringCard = convertCardToJson((Magic_card) actualCard).toString();}
				else if (actualCard instanceof Pokemon_card)
				{}
				else // if (actualCard instanceof Yugioh_card) NON SERVE, SE NON è MAGIC O POKEMON ALLORA è YUGI. 
				{}
				
				
				stockPriceSvc.rimuoviCarta(desiderata, stringCard, new AsyncCallback<Boolean>() {
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
        
        
        
        test.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				stockPriceSvc.test(new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("test failed: "
										+ caught.getMessage());
							}

							@Override
							public void onSuccess(Void result) {
								
									Window.alert("test ok!");							
								
							}
						});
			}
		});
        

    
    
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

    // Parse the 'richiesta' list (similar to 'offerta')
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
    
    
    
    public Magic_card parseJsonString(String jsonString) {
        JSONValue jsonValue = JSONParser.parseStrict(jsonString);
        JSONObject jsonObject = jsonValue.isObject();

        String artist = jsonObject.get("artist").isString().stringValue();
        String name = jsonObject.get("name").isString().stringValue();
        String text = jsonObject.get("text").isString().stringValue();
        String types = jsonObject.get("types").isString().stringValue();
        String rarity = jsonObject.get("rarity").isString().stringValue();
        String hasFoil = jsonObject.get("hasFoil").isString().stringValue();
        String isAlternative = jsonObject.get("isAlternative").isString().stringValue();
        String isFullArt = jsonObject.get("isFullArt").isString().stringValue();
        String isPromo = jsonObject.get("isPromo").isString().stringValue();
        String isReprint = jsonObject.get("isReprint").isString().stringValue();
        String condizione = jsonObject.containsKey("condizione") ? jsonObject.get("condizione").isString().stringValue() : null;
        String proprietario = jsonObject.containsKey("proprietario") ? jsonObject.get("proprietario").isString().stringValue() : null;
        String acquirente = jsonObject.containsKey("acquirente") ? jsonObject.get("acquirente").isString().stringValue() : null;
        String testoPersonale = jsonObject.containsKey("testoPersonale") ? jsonObject.get("testoPersonale").isString().stringValue() : null;




        int id = (int) jsonObject.get("ID").isNumber().doubleValue();
        
        Magic_card mc = new Magic_card(artist, name, text, types, rarity, hasFoil, isAlternative, isFullArt, isPromo, isReprint);
        mc.setTipoGioco("Magic");
        mc.setID(id);
        mc.setCondizione(condizione);
        mc.setProprietario(proprietario);
        mc.setAcquirente(acquirente);
        mc.setTestoPersonale(testoPersonale);

        return mc;
    }
    
    
    


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


    
    
    public JSONObject convertCardToJson(Card carta) {
        JSONObject jsonObject = new JSONObject();
        
        if(carta instanceof Magic_card) {
        //	Magic_card magicCard = (magicCard)carta;
        	Magic_card magicCard = (Magic_card) carta;

        jsonObject.put("artist", new JSONString(magicCard.getArtist()));
        jsonObject.put("name", new JSONString(magicCard.getName()));
        jsonObject.put("text", new JSONString(magicCard.getText()));
        jsonObject.put("types", new JSONString(magicCard.getTypes()));
        jsonObject.put("rarity", new JSONString(magicCard.getRarity()));
        jsonObject.put("hasFoil", new JSONString(magicCard.getHasFoil()));
        jsonObject.put("isAlternative", new JSONString(magicCard.getIsAlternative()));
        jsonObject.put("isFullArt", new JSONString(magicCard.getIsFullArt()));
        jsonObject.put("isPromo", new JSONString(magicCard.getIsPromo()));
        jsonObject.put("isReprint", new JSONString(magicCard.getIsReprint()));
        if (magicCard.getAcquirente() != null) {
        jsonObject.put("acquirente", new JSONString(magicCard.getAcquirente()));}
        if (magicCard.getCondizione() != null) {
        jsonObject.put("condizione", new JSONString(magicCard.getCondizione()));}
        if (magicCard.getProprietario() != null) {
        jsonObject.put("proprietario", new JSONString(magicCard.getProprietario()));}
        //jsonObject.put("isReprint", new JSONString(magicCard.getTipoGioco()));
        jsonObject.put("ID", new JSONNumber(magicCard.getID()));
        if(magicCard.getTestoPersonale() != null) {
        	jsonObject.put("testoPersonale", new JSONString(magicCard.getTestoPersonale()));
        }
        
        }

        return jsonObject;
    }
    
    
    private void addCarte(Card carta, Panel panel, FlexTable FlexTable, boolean scambia) { //devo basarlo su due diverse liste e devo mettere forse l'oggetto "scambio"
		// TODO Auto-generated method stub
		
		
		
		int row = FlexTable.getRowCount();
		 if(FlexTable == cardsFlexTable)
        cards.add(carta);
		 else
		cards2.add(carta);
		 
        FlexTable.setText(row, 1, carta.getName());
        FlexTable.setText(row, 0, carta.getTipoGioco());
        
        if(carta instanceof Magic_card) {
        	Magic_card MC = (Magic_card) carta;
        FlexTable.setText(row, 2, MC.getTypes());}
        
        
        FlexTable.setText(row, 4, String.valueOf(carta.getID()));
        //cardsFlexTable.getCellFormatter().addStyleName(row, 1, "watchListNumericColumn");
        FlexTable.getCellFormatter().addStyleName(row, 2, "watchListNumericColumn");
        //cardsFlexTable.getCellFormatter().addStyleName(row, 3, "watchListRemoveColumn");
        
 
        //questo button X è fraintendibile e andrebbe tolto
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
       // cardsFlexTable.setText(row, 3, carta.getProprietario());
        
        
        if(FlexTable == cardsFlexTable ||FlexTable == cardsFlexTable2)
        if(scambia) {
        	 Button add = new Button("+");
             //removeStockButton.addStyleDependentName("remove");
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
        //removeStockButton.addStyleDependentName("remove");
        expand.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	int c = cards.indexOf(carta);
            	actualCard = carta;
                linkToMagicCard((Magic_card) cards.get(c), panel);                
            }
        });
        FlexTable.setWidget(row, 4, expand);
        }
               
        
	}
    

	private void addScambio(Scambio skmb) {
		// TODO Auto-generated method stub
		
		
		int row = scambiFlexTable.getRowCount();
        scambiL.add(skmb);

        
        /*
         *     	//table for scambi scambiFlexTable
        scambiFlexTable.setText(0, 0, "Offerente");
        scambiFlexTable.setText(0, 1, "Carte Offerte");
        scambiFlexTable.setText(0, 2, "Carte Richieste");
        scambiFlexTable.setText(0, 3, "Rifiuta");
        scambiFlexTable.setText(0, 4, "Accetta");
         */

        
        scambiFlexTable.setText(row, 0, skmb.getProponente());
        scambiFlexTable.setText(row, 1, skmb.getTitoloOfferta());
        scambiFlexTable.setText(row, 2, skmb.getTitoloRichiesta());

        Button accetta = new Button("Accetta");
        //removeStockButton.addStyleDependentName("remove");
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
        //removeStockButton.addStyleDependentName("remove");
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
	
	
	
    
    
    
    private void addCartaUser(boolean cercaAcquirenti, Magic_card carta, Panel panel) {
		// TODO Auto-generated method stub
		
    	cardsUserFlexTable.setVisible(true);
    	
    	/*
    	 *         cardsUserFlexTable.setText(0, 0, "User");
        cardsUserFlexTable.setText(0, 1, "Condizione");
        cardsUserFlexTable.setText(0, 3, "delete");
        cardsUserFlexTable.setText(0, 4, "expand");*/
    	
		
		
		int row = cardsUserFlexTable.getRowCount();
        cards.add(carta);
        
        if(cercaAcquirenti) {
        cardsUserFlexTable.setText(row, 0, carta.getAcquirente());
        	
        }
        else {
        cardsUserFlexTable.setText(row, 0, carta.getProprietario());
        }
        cardsUserFlexTable.setText(row, 1, carta.getCondizione());
       // cardsUserFlexTable.setText(row, 2, carta.getTypes());
       // cardsUserFlexTable.setText(row, 4, String.valueOf(carta.getID()));
        //cardsFlexTable.getCellFormatter().addStyleName(row, 1, "watchListNumericColumn");
        cardsUserFlexTable.getCellFormatter().addStyleName(row, 2, "watchListNumericColumn");
        //cardsFlexTable.getCellFormatter().addStyleName(row, 3, "watchListRemoveColumn");
        
 
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
       // cardsFlexTable.setText(row, 3, carta.getProprietario());
        
        
        
        Button scambia ;       
      scambia = new Button("Scambia");
        //removeStockButton.addStyleDependentName("remove");
        scambia.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	
            	if(actualUser == carta.getAcquirente()||actualUser == carta.getProprietario())
            		Window.alert("Non puoi scambiare carte con te stesso!"); 
            	else
            	{            	
            	//int c = cards.indexOf(carta);
            	actualCard = carta;
            	 if(cercaAcquirenti)
            		 linkToScambio(panel, carta.getAcquirente(), carta, cardsFlexTableOffer);
            		// addCarteMagic(carta, panel, cardsFlexTableOffer, true);
            	 else
            		 linkToScambio(panel, carta.getProprietario(), carta, cardsFlexTableRequest);
            		 //addCarteMagic(carta, panel, cardsFlexTableRequest, true);
               // linkToMagicCard((Magic_card) cards.get(c), panel); 
            }
            }
        });
       

    cardsUserFlexTable.setWidget(row, 3, scambia);
        
               
        
	}
    
    private void hideAllElements(Panel panel) {
        for (Widget widget : panel) {
            widget.setVisible(false);
        }        
      //  iscrivitiButton.getElement().getStyle().clearDisplay();
       

    }
    
    
    private void linkToListaScambi(Panel panel) {
    	hideAllElements(panel);
    	home.setVisible(true);
    	scambiFlexTable.setVisible(true);
    }
    
    
    private void linkToMagicCard(Magic_card card, Panel panel) {
    	
    	
    	
    	hideAllElements(panel);
    	home.setVisible(true);  
    	cercaCarte.setVisible(true);
    	//textBox.setVisible(true);
    	userTextBox.setVisible(true);
    	//textBox.setText(card.toString());
    	artistTextBox.setVisible(true);
    	nameTextBox.setVisible(true);
    	textTextBox.setVisible(true);
    	typesTextBox.setVisible(true);
    	rarityTextBox.setVisible(true);
    	hasFoilTextBox.setVisible(true);
    	isAlternativeTextBox.setVisible(true);
    	isFullArtTextBox.setVisible(true);
    	isPromoTextBox.setVisible(true);
    	isReprintTextBox.setVisible(true);
    	idTextBox.setVisible(true);

    	artistLabel.setVisible(true);
    	nameLabel.setVisible(true);
    	textLabel.setVisible(true);
    	typesLabel.setVisible(true);
    	rarityLabel.setVisible(true);
    	hasFoilLabel.setVisible(true);
    	isAlternativeLabel.setVisible(true);
    	isFullArtLabel.setVisible(true);
    	isPromoLabel.setVisible(true);
    	isReprintLabel.setVisible(true);
    	idLabel.setVisible(true);
    	listBox.setVisible(true);
    	
        personalTextLabel.setVisible(true);
        personalTextBox.setVisible(true);
    	
        if(card.getTestoPersonale() != null) {
        	personalTextBox.setText(card.getTestoPersonale());
        	personalTextBox.setReadOnly(true);
        }

    	
    	artistTextBox.setText(card.getArtist());
        nameTextBox.setText(card.getName());
        textTextBox.setText(card.getText());
        typesTextBox.setText(card.getTypes());
        rarityTextBox.setText(card.getRarity());
        hasFoilTextBox.setText(card.getHasFoil());
        isAlternativeTextBox.setText(card.getIsAlternative());
        isFullArtTextBox.setText(card.getIsFullArt());
        isPromoTextBox.setText(card.getIsPromo());
        isReprintTextBox.setText(card.getIsReprint());
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
    	checkUserTextBox.setVisible(true);
        checkPwdTextBox.setVisible(true);
        loginButton.setVisible(true);  
        toIscrizioneButton.setVisible(true);
    }
    
    
    private void linkToHome(Panel panel) {
    	
    	hideAllElements(panel);
    	cercaCarte.setVisible(true);
    	creaDeck.setVisible(true);
    	mostraScambi.setVisible(true);  
    	userTextBox.setVisible(true);

    }
    
    
    private void linkToSearch(Panel panel) {
    	
    	hideAllElements(panel);
    	home.setVisible(true);  
    	cardsFlexTable.setVisible(true); 
    	showMagicButton.setVisible(true); 
        showPokemonButton.setVisible(true); 
        showYugiohButton.setVisible(true); 
        checkPersonal.setVisible(true);
    	userTextBox.setVisible(true);
    	checkDesiderate.setVisible(true);    	

    }
    
    
    private void gestioneScambi(boolean accettato, Scambio skmb) {
    	
    	
    	String scambio = convertScambioToJsonString(skmb);
    	
    	List<String> actualCard = new ArrayList<>(); 
    	List<String> newCard = new ArrayList<>();
    	for(Card carta : skmb.getOfferta()) {
    		//lo parso e lo metto in una lista
    		actualCard.add(convertCardToJson(carta).toString());
    		if(accettato) {
    		carta.setProprietario(actualUser);}
    		newCard.add(convertCardToJson(carta).toString()); //versione con il nuovo proprietario
    	}
    	
    	
    	for(Card carta : skmb.getRichiesta()) {
    		//lo parso e lo metto in una lista
    		actualCard.add(convertCardToJson(carta).toString());
    		if(accettato) {
    		carta.setProprietario(skmb.getProponente());}
    		newCard.add(convertCardToJson(carta).toString());
    	}
    	
    	
    	
		stockPriceSvc.gestisciScambio (accettato,scambio,  skmb.getProponente(), actualCard, actualUser, newCard, new AsyncCallback<Void>() {
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
    	
    	//addCarteMagic(carta, panel, cardsFlexTableOffer, true);
    //	boolean find = false;
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
    	
		//Window.alert("il nome della carta in questione è " + cartaBase.getName()); 

        
        //lancio un load carte personali sulla cardsFlexTable e una versione simile che però hanno come proprietario solo acquirente sulla cardsFlexTable2
        //a quel punto devo implementare il pulsante + (che fa come la X ma in più aggiunge nella tabella corrispondente) (in cui metto un IF,se siamo nella tab X va in offerta, altrimenti in domanda)
        //poi implementare il pulsante invia che crea la classe, la parsa e la mette in DB 
        
        //forse dovrei caricare subito l'oggetto scambio, così uso le sue due liste per gestire i pulsantini delle tabelle di scambio
        //comunque non posso passare già la carta scambiata così com'è, devo pescare il nome, pescarla dalla lista delle carte possedure e prenderla da lì, se presente. altrimenti messaggio di errore
        //poi devo depennarla dalla liste delle carte a sinistra.
        
   
        
		boolean soloDesiderate = false;
		//boolean cercaAcquirenti = false;
		boolean soloPersonali = true;
		//soloPersonali lo passo al metodo e se è true invece che leggere da magic cards, legge da magic personal
		//il metodo di parsing deve essere sempre uguale
		
		stockPriceSvc.loadCarteMagic(soloDesiderate, soloPersonali, new AsyncCallback<String[]>() {
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
				//cardsFlexTable.clear(true);
		    	boolean find = false;
				for (String carta : carte) {			    		     
					 //addStock(symbol); 
					
					Magic_card magicCard = parseJsonString(carta);
					
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
				if(!find) {
					Window.alert("Stai provando a cedere una carta che non hai");
				}
			}
		
		});
		

        
    }
    
    
}