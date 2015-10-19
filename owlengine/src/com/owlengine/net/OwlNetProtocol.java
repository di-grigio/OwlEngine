package com.owlengine.net;

import com.esotericsoftware.kryonet.EndPoint;

public interface OwlNetProtocol {

    public boolean useUDP();
    public void register(EndPoint endpoint);
}
