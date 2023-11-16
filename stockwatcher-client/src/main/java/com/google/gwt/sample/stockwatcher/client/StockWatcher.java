package com.google.gwt.sample.stockwatcher.client;
import com.google.gwt.user.client.ui.Widget;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.sample.stockwatcher.shared.StockPrice;
import com.google.gwt.sample.stockwatcher.shared.StockPriceService;
import com.google.gwt.sample.stockwatcher.shared.StockPriceServiceAsync;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class StockWatcher implements EntryPoint {

	private VerticalPanel mainPanel = new VerticalPanel();
	private FlexTable stocksFlexTable = new FlexTable();
	private HorizontalPanel addPanel = new HorizontalPanel();
	private TextBox newSymbolTextBox = new TextBox();
	private TextBox pwdTextBox = new TextBox();
	private Button iscrivitiButton = new Button("iscriviti");
	private List<String> stocks = new ArrayList<>();
	private StockPriceServiceAsync stockPriceSvc = GWT.create(StockPriceService.class);
	private Label errorMsgLabel = new Label();
	private Button loginButton = new Button("Login");
	private TextBox checkUserTextBox = new TextBox();
	private TextBox checkPwdTextBox = new TextBox();
	private Button toIscrizioneButton = new Button("Non sei utente? Iscriviti!");
	private Button test = new Button("start");


	/** Entry point method. */

    public void onModuleLoad() {


		addPanel.add(newSymbolTextBox);
		addPanel.add(pwdTextBox);
		addPanel.add(iscrivitiButton);
		addPanel.addStyleName("addPanel");


		/** Main panel. */
		errorMsgLabel.setStyleName("errorMessage");
		errorMsgLabel.setVisible(false);
		mainPanel.add(errorMsgLabel);
		//mainPanel.add(stocksFlexTable);
		mainPanel.add(addPanel);

		/** Associate the Main panel with the HTML host page.*/
		RootPanel.get("stockList").add(mainPanel);

		/** Move cursor focus to the input box.*/
		newSymbolTextBox.setFocus(true);


		/** Add Login panel to main page.*/

        addPanel.add(checkUserTextBox);
        addPanel.add(checkPwdTextBox);
        addPanel.add(loginButton);
        addPanel.add(toIscrizioneButton);
        addPanel.add(test);

		/** Hide element and show only Login form. -> linkToLogin method */
		linkToLogin(addPanel);

		/** Hide element on click and show only Subscribtion form. -> linkToIscrizione method*/

		toIscrizioneButton.addClickHandler(new ClickHandler() {
    		@Override
    		public void onClick(ClickEvent event) {
    		     linkToIscrizione(addPanel);
    		}});


		/** onClick Login Method.*/
        
        loginButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				stockPriceSvc.login(checkUserTextBox.getText().toUpperCase().trim(), checkPwdTextBox.getText().trim(),
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
									hideAllElements(addPanel);
								}
								else
									Window.alert("user o pwd errati");
							}
						});
			}
		});




		/** onClick Test Method.*/

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


		/** onClick Subscribtion method*/
    
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
				
					if(result)// To check -> è sempre false e da' problemi di lettura
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
    
    
 


    
}

    private void hideAllElements(Panel panel) {
        for (Widget widget : panel) {
            widget.setVisible(false);
        }

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
        test.setVisible(true);
    }
    
    
}