package com.google.gwt.sample.stockwatcher.server;

public interface  CardSerializer<T> {
	String serialize(T card);
	T deserialize(String json);
}
