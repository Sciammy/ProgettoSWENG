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
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

import java.util.ArrayList;
import java.util.List;


public class StockWatcher implements EntryPoint {

    private Card actualCard;
    private String actualUser;
    private VerticalPanel mainPanel = new VerticalPanel();
    private FlexTable cardsFlexTable = new FlexTable();
    private HorizontalPanel addPanel = new HorizontalPanel();
	private HorizontalPanel persistPanel = new HorizontalPanel();
    private TextBox newSymbolTextBox = new TextBox();
    private TextBox pwdTextBox = new TextBox();
    private Button iscrivitiButton = new Button("iscriviti");
    private Button addStockButton = new Button("add");
	private Button loadSymbolsButton = new Button("Load");
	private Button loadPWDButton = new Button("Load PWD");
	private Button saveSymbolsButton = new Button("Save");
    private Label lastUpdatedLabel = new Label();
    private List<Card> cards = new ArrayList<>();
    private StockPriceServiceAsync stockPriceSvc = GWT.create(StockPriceService.class);
    private Label errorMsgLabel = new Label();
    private Button loginButton = new Button("Login");
    private TextBox checkUserTextBox = new TextBox();
    private TextBox checkPwdTextBox = new TextBox();
    private Button toIscrizioneButton = new Button("Non sei utente? Iscriviti!");
    private Button test = new Button("start");
    private Button cercaCarte = new Button("Cerca Carte");
    private Button creaDeck = new Button("Crea Deck");
    private Button scambi = new Button("Gestione Scambi");
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
    private Label artistLabel = new Label("Artist:");
    private Label nameLabel = new Label("Name:");
    private Label textLabel = new Label("Text:");
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
    private CheckBox checkPersonal = new CheckBox("Solo Carte Personali");
    private Button cercaProprietari = new Button("Cerca Proprietari"); //fa comparire una lista di soggetti ciascuno col pulsantino che manda agli scambi
    private Button cercaAcquirenti = new Button("Cerca Acquirenti"); //idem come sopra, ma con i soggetti che vogliono la carta
    //devo aggiungere anche il gioco della carta, così a seconda, mette nella "tabella" giusta
    //queste robe ancora non le ho fatte, ma mi appunto come vorrei implementarle




    /**
     * Entry point method.
     */
    public void onModuleLoad() {
    	
    	
        addPanel.add(cercaCarte);
        addPanel.add(creaDeck);
        addPanel.add(scambi);
        addPanel.add(home);
    	
    	
    	
        // Create table for stock data.
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
				
				
				
				boolean soloPersonali = checkPersonal.getValue();
				//soloPersonali lo passo al metodo e se è true invece che leggere da magic cards, legge da magic personal
				//il metodo di parsing deve essere sempre uguale
				
				stockPriceSvc.loadCarteMagic(soloPersonali, new AsyncCallback<String[]>() {
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
							addCarteMagic(magicCard, addPanel);
							}
							else {
							addCarteMagic(magicCard, addPanel);
							}

						}
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
        
        
        
        aggiungiCarta.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				actualCard.setCondizione(listBox.getSelectedValue());
				actualCard.setProprietario(actualUser);
				String stringCard = "";
				if(actualCard instanceof Magic_card)
				{stringCard = convertMagicCardToJsonString((Magic_card) actualCard);}
				else if (actualCard instanceof Pokemon_card)
				{}
				else // if (actualCard instanceof Yugioh_card) NON SERVE, SE NON è MAGIC O POKEMON ALLORA è YUGI. 
				{}
				
				//MAGARI MI TIRO DIETRO UNA STRINGA PER DIRE SE METTERE NEL DATASET MAGIC, YUGIOH O POKEMON

				
				stockPriceSvc.addCard(actualUser, stringCard, new AsyncCallback<Void>() {
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
        
        
        
        rimuoviCarta.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				
				String stringCard = "";
				if(actualCard instanceof Magic_card)
				{stringCard = convertMagicCardToJsonString((Magic_card) actualCard);}
				else if (actualCard instanceof Pokemon_card)
				{}
				else // if (actualCard instanceof Yugioh_card) NON SERVE, SE NON è MAGIC O POKEMON ALLORA è YUGI. 
				{}
				
				
				stockPriceSvc.rimuoviCarta(stringCard, new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("test failed: "
										+ caught.getMessage());
							}

							@Override
							public void onSuccess(Void result) {
								
									Window.alert("Rimossa");							
								
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


        int id = (int) jsonObject.get("ID").isNumber().doubleValue();
        
        Magic_card mc = new Magic_card(artist, name, text, types, rarity, hasFoil, isAlternative, isFullArt, isPromo, isReprint);
        mc.setTipoGioco("Magic");
        mc.setID(id);
        mc.setCondizione(condizione);
        mc.setProprietario(proprietario);

        return mc;
    }
    
    
    
    public String convertMagicCardToJsonString(Magic_card magicCard) {
        JSONObject jsonObject = new JSONObject();

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
        jsonObject.put("condizione", new JSONString(magicCard.getCondizione()));
        jsonObject.put("proprietario", new JSONString(magicCard.getProprietario()));
        //jsonObject.put("isReprint", new JSONString(magicCard.getTipoGioco()));
        jsonObject.put("ID", new JSONNumber(magicCard.getID()));

        return jsonObject.toString();
    }
    
    
    private void addCarteMagic(Magic_card carta, Panel panel) {
		// TODO Auto-generated method stub
		
		
		
		int row = cardsFlexTable.getRowCount();
        cards.add(carta);
        cardsFlexTable.setText(row, 1, carta.getName());
        cardsFlexTable.setText(row, 0, "Magic");
        cardsFlexTable.setText(row, 2, carta.getTypes());
        cardsFlexTable.setText(row, 4, String.valueOf(carta.getID()));
        //cardsFlexTable.getCellFormatter().addStyleName(row, 1, "watchListNumericColumn");
        cardsFlexTable.getCellFormatter().addStyleName(row, 2, "watchListNumericColumn");
        //cardsFlexTable.getCellFormatter().addStyleName(row, 3, "watchListRemoveColumn");
        
 
        //questo button X è fraintendibile e andrebbe tolto
        Button removeStockButton = new Button("x");
        removeStockButton.addStyleDependentName("remove");
        removeStockButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                int removedIndex = cards.indexOf(carta);
                cards.remove(removedIndex);
                cardsFlexTable.removeRow(removedIndex +1);
            }
        });
        cardsFlexTable.setWidget(row, 3, removeStockButton);
       // cardsFlexTable.setText(row, 3, carta.getProprietario());
        
        
        
        
        
        Button expand = new Button("o");
        //removeStockButton.addStyleDependentName("remove");
        expand.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	int c = cards.indexOf(carta);
            	actualCard = carta;
                linkToMagicCard((Magic_card) cards.get(c), panel);                
            }
        });
        cardsFlexTable.setWidget(row, 4, expand);
               
        
	}
    
    private void hideAllElements(Panel panel) {
        for (Widget widget : panel) {
            widget.setVisible(false);
        }        
      //  iscrivitiButton.getElement().getStyle().clearDisplay();
       

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
    	scambi.setVisible(true);  
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

    	

    }
    
    
}