package com.AntonChernikov.g144;

import javafx.stage.Stage;

/** Class describing work with client */
public class Client extends GameConnection {

    private String ip;
    private int port;

    public Client(String ip, int port, Stage stage) {
        super(stage);
        this.ip = ip;
        this.port = port;
    }

    @Override
    protected String getIP() {
        return ip;
    }

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    protected boolean isServer() {
        return false;
    }

}