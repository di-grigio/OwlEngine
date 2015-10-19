package com.owlengine.net;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public final class OwlNetwork {
	
	private OwlServer server;
	private OwlConnection connection;
	
	public OwlNetwork() {
	    
    }
	
	public void serverStart(OwlNetProtocol protocol, Listener listener, Class<? extends Connection> connectionHandler, int portTcp, int portUdp) throws IOException {
	    server = new OwlServer(protocol, listener, connectionHandler, portTcp, portUdp);
	}
	
	public void serverClose(){
	    server.close();
	}
	
	public void serverSendTCP(int connectionId, Object obj){
	    server.sendTCP(connectionId, obj);
	}
	
	public void serverSendUDP(int connectionId, Object obj){
        server.sendUDP(connectionId, obj);
    }
	
    public void serverSendToAllTCP(Object obj){
        server.serverSendToAllTCP(obj);
    }
    
    public void serverSendToAllUDP(Object obj){
        server.serverSendToAllUDP(obj);
    }

    public void connectionConnect(OwlNetProtocol protocol, Listener listener, String ip, int portTcp, int portUdp) throws IOException {
        connection = new OwlConnection(protocol, listener, ip, portTcp, portUdp);
    }
    
    public void connectionClose(){
        connection.close();
    }
    
    public boolean connectionIsConnected(){
        return connection.isConnected();
    }
    
    public void connectionSendTCP(Object obj){
        connection.sendTCP(obj);
    }
    
    public void connectionSendUDP(Object obj){
        connection.sendUDP(obj);
    }
}
