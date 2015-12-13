package com.owlengine.net;

import java.io.IOException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;

final class OwlConnection {
    
    private static final int DEFAULT_TIMEOUT = 5000;
    
    private Client client;
    
    public OwlConnection(OwlNetProtocol protocol, Listener listener, String ip, int portTcp, int portUdp) throws IOException {
        client = new Client(protocol.getClientWriteBufferSize(), protocol.getClientObjectBufferSize());
        client.start();
        protocol.register(client);
        
        client.addListener(listener);
        
        if(protocol.useUDP()){
            client.connect(DEFAULT_TIMEOUT, ip, portTcp, portUdp);
        }
        else{
            client.connect(DEFAULT_TIMEOUT, ip, portTcp);
        }
    }
    
    public void close(){
        client.close();
    }
    
    public boolean isConnected(){
        return client.isConnected();
    }

    public void sendTCP(Object obj) {
        client.sendTCP(obj);
    }

    public void sendUDP(Object obj) {
        client.sendUDP(obj);
    }
}
