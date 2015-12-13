package com.owlengine.test.net1;

import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;
import com.owlengine.OwlCycle;
import com.owlengine.OwlFrame;
import com.owlengine.config.OwlConfig;
import com.owlengine.net.OwlNetProtocol;
import com.owlengine.net.OwlNetwork;
import com.owlengine.tools.Log;

/*
 * $title: Test Net 1
 * $author: Leo-di-Grigio
 * $todo: Basic Owl Networking
 * $lastcheck 0.1.16: 
 */
public class TestNet1 {

    public static void main(String[] args) {
        new OwlFrame(new Config(), new Cycle());
    }
}

class Config extends OwlConfig {
    
    @Override
    public String frameTitle() {
        return "OwlEngine - Test Network 1";
    }
}

class Cycle extends OwlCycle {
    
    private static final String IP = "127.0.0.1";
    private static final int PORT_TCP = 9600;
    private static final int PORT_UDP = 9600;

    private OwlNetwork network;
    
    private ServerListener serverListener;
    private ClientListener clientListener;
    
    @Override
    public void setup() {
        network = new OwlNetwork(new Protocol());
        serverListener = new ServerListener();
        clientListener = new ClientListener();
        
        runServer();
        runConnection();
    }
    
    private void runServer() {
        try {
            network.serverStart(serverListener, ServerConnectionHandler.class, PORT_TCP, PORT_UDP);
            Log.debug("OwlNetwork server binded at tcp: " + PORT_TCP + " udp: " + PORT_UDP);
        }
        catch (IOException e) {
            Log.err("Ports already binded");
            e.printStackTrace();
        }
    }

    private void runConnection() {
        try {
            network.connectionConnect(clientListener, IP, PORT_TCP, PORT_UDP);
            Log.debug("Connected successful to \"" + IP + "\"");
        }
        catch (IOException e) {
            Log.err("Cant connect to \"" + IP + "\"");
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        
    }
    
    @Override
    public void dispose() {
        
    }
}

class Protocol implements OwlNetProtocol {

    private static final boolean UDP = true;
    
    @Override
    public void register(EndPoint endpoint) {
        Kryo kryo = endpoint.getKryo();
        kryo.register(Message.class);
    }

    @Override
    public boolean useUDP() {
        return UDP;
    }
    
    private static final class Message {
        
    }

    @Override
    public int getClientWriteBufferSize() {
        return 8192;
    }

    @Override
    public int getClientObjectBufferSize() {
        return 2048;
    }

    @Override
    public int getServerWriteBufferSize() {
        return 8192;
    }

    @Override
    public int getServerObjectBufferSize() {
        return 2048;
    }
}

class ServerListener extends Listener {
    
    @Override
    public void connected(Connection connection) {
        Log.debug("ServerListener: connected");
    }
    
    @Override
    public void received(Connection connection, Object obj) {
        Log.debug("ServerListener: received");
    }
    
    @Override
    public void disconnected(Connection connection) {
        Log.debug("ServerListener: disconnected");
    }
}

class ClientListener extends Listener {
    
    @Override
    public void connected(Connection connection) {
        Log.debug("ClientListener: connected");
    }
    
    @Override
    public void received(Connection connection, Object obj) {
        Log.debug("ClientListener: received");
    }
    
    @Override
    public void disconnected(Connection connection) {
        Log.debug("ClientListener: disconnected");
    }
}