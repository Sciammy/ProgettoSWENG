package com.google.gwt.sample.stockwatcher.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import com.google.gson.Gson;
import com.google.gwt.core.client.impl.AsyncFragmentLoader.Logger;
import com.google.gwt.sample.stockwatcher.shared.StockPrice;
import com.google.gwt.sample.stockwatcher.shared.StockPriceService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
@WebServlet(name = "stockPrices", urlPatterns = "/app/stockPrices")
public class StockPriceServiceImpl extends RemoteServiceServlet implements StockPriceService, MapDBConstants {
	private static final long serialVersionUID = 4192379456341403664L;
	private static final double MAX_PRICE = 100.0; // $100.00
	private static final double MAX_PRICE_CHANGE = 0.02; // +/- 2%

	
	/** Cambio Contenct con Key */

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
	 //   db.close(); se lo chiudo poi risulta chiuso e non me lo riapre
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
	
	
	
	public void test() {
		
		
	}
	
	
	
	
	
}
