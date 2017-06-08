package com.solfest.spi;

import javax.resource.spi.ManagedConnectionMetaData;

public class SolfestMCMeta implements ManagedConnectionMetaData{
    /** {@inheritDoc}
     */
    @Override
    public String getEISProductName(){
        return "Solfest Example Connection";
    }
    /** {@inheritDoc}
     */
    @Override
    public String getEISProductVersion(){
        return "1.0";
    }
    /** {@inheritDoc}
     *
     * Only support a single connection...
     */
    @Override
    public int getMaxConnections(){
        return 1;
    }
    /** {@inheritDoc}
     */
    @Override
    public String getUserName(){
        return null;
    }
}
