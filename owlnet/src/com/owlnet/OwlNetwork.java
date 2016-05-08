package com.owlnet;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class OwlNetwork {
	
	private final OwlNetProtocol protocol;
	private OwlServer server;
	private OwlConnection connection;
	
	public OwlNetwork(OwlNetProtocol protocol) {
		this.protocol = protocol;
	}
	
	public void serverStart(Listener listener, Class<? extends Connection> connectionHandler, int portTcp, int portUdp) throws IOException {
	    server = new OwlServer(protocol, listener, connectionHandler, portTcp, portUdp);
	}
	
	public void serverClose(){
		if(server != null){
			server.close();
			server = null;
		}
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

    public void connectionConnect(Listener listener, String ip, int portTcp, int portUdp) throws IOException {
        connection = new OwlConnection(protocol, listener, ip, portTcp, portUdp);
    }
    
    public void connectionClose(){
    	if(connection != null){
    		if(connection.isConnected()){
    			connection.close();
    		}
    		
    		connection = null;
    	}
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
