package com.google.gwt.sample.stockwatcher.server;



import com.google.gson.Gson;

//...

public class PokemonCardSerializer implements CardSerializer<Pokemon_card>{

    private static final Gson gson = new Gson();

    @Override
    public  String serialize(Pokemon_card card) {
        return gson.toJson(card);
    }

    @Override
    public  Pokemon_card deserialize(String json) {
        return gson.fromJson(json, Pokemon_card.class);
    }
}

