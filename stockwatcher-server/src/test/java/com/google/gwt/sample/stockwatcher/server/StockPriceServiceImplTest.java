package com.google.gwt.sample.stockwatcher.server;

import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class StockPriceServiceImplTest extends Mockito {

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher rd;
    @Mock
    ServletOutputStream servletOut;
    @Mock
    ServletConfig sg;

    @org.junit.Test
    public void controlAccount() {
        try {
            StockPriceServiceImpl servlet = new StockPriceServiceImpl();
            servlet.init(sg);
            assertTrue(servlet.controlAccount("test"));
        } catch (Exception ex) {

        }
    }

    @org.junit.Test
    public void addAccount() {
        try {
            StockPriceServiceImpl servlet = new StockPriceServiceImpl();
            servlet.init(sg);
            servlet.addAccount("test", "test");
            assertTrue(servlet.controlAccount("test"));
        } catch (Exception ex) {

        }
    }

    @org.junit.Test
    public void login() {
        try {
            StockPriceServiceImpl servlet = new StockPriceServiceImpl();
            servlet.init(sg);
            assertTrue(servlet.login("test","test"));
        } catch (Exception ex) {

        }
    }

    @org.junit.Test
    public void loadCarteMagic() {
    }

    @org.junit.Test
    public void addCard() {
    }

    @org.junit.Test
    public void rimuoviCarta() {
    }

    @org.junit.Test
    public void addProposta() {
    }

    @org.junit.Test
    public void loadProposteScambi() {
    }

    @org.junit.Test
    public void gestisciScambio() {
    }
}