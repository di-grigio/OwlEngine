package com.owlengine.net;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

final class OwlServer {

    private Server server;
    
    public OwlServer(OwlNetProtocol protocol, Listener listener, Class<? extends Connection> connectionHandler, int portTcp, int portUdp) throws IOException {
        server = new Server(protocol.getServerWriteBufferSize(), protocol.getServerObjectBufferSize()) {
            @Override
            protected Connection newConnection() {
                try {
                    return connectionHandler.newInstance();
                }
                catch (InstantiationException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                
                return null;
            };
        };
        
        protocol.register(server);
        server.addListener(listener);
        
        if(protocol.useUDP()){
            server.bind(portTcp, portUdp);
        }
        else{
            server.bind(portTcp);
        }
        
        server.start();
    }
    
    public void close(){
        server.close();
    }

    public void sendTCP(int connectionId, Object obj) {
        server.sendToTCP(connectionId, obj);
    }

    public void sendUDP(int connectionId, Object obj) {
        server.sendToUDP(connectionId, obj);
    }
    
    public void serverSendToAllTCP(Object obj){
        server.sendToAllTCP(obj);
    }
    
    public void serverSendToAllUDP(Object obj){
        server.sendToAllUDP(obj);
    }
}