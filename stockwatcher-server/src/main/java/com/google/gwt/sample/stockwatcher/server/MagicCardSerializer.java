package com.google.gwt.sample.stockwatcher.server;



import com.google.gson.Gson;

//...

public class MagicCardSerializer implements CardSerializer<Magic_card>{

 private static final Gson gson = new Gson();

 @Override
 public String serialize(Magic_card card) {
     return gson.toJson(card);
 }

 @Override
 public  Magic_card deserialize(String json) {
     return gson.fromJson(json, Magic_card.class);
 }
}

