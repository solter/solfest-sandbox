package com.solfest.spi;

import javax.resource.spi.ConnectionRequestInfo;

/**
 * A simple ConnectionRequestInfo implementation
 * that garuntees all connections are equal.
 */
public SolfestCRI implements ConnectionRequestInfo{
    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int hashCode(){
        return 1337;
    }
}
