package com.google.gwt.sample.stockwatcher.server;



import com.google.gson.Gson;

//...

public class YugiohCardSerializer implements CardSerializer<Yugioh_card> {

 private static final Gson gson = new Gson();

 @Override
 public String serialize(Yugioh_card card) {
     return gson.toJson(card);
 }

 @Override
 public Yugioh_card deserialize(String json) {
     return gson.fromJson(json, Yugioh_card.class);
 }
}

