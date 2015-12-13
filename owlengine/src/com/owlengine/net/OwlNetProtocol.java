package com.owlengine.net;

import com.esotericsoftware.kryonet.EndPoint;

public interface OwlNetProtocol {

    public boolean useUDP();
    public void register(EndPoint endpoint);
    
    public int getClientWriteBufferSize();
    public int getClientObjectBufferSize();
    
    public int getServerWriteBufferSize();
    public int getServerObjectBufferSize();
}
