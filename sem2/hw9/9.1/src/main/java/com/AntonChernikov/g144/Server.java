package com.AntonChernikov.g144;

import javafx.stage.Stage;

/** Class describing work with server */
public class Server extends GameConnection {
    private int port;

    public Server(int port, Stage stage) {
        super(stage);
        this.port = port;
    }

    @Override
    protected String getIP() {
        return null;
    }

    @Override
    protected int getPort() {
        return port;
    }
    @Override
    protected boolean isServer() {
        return true;
    }
}