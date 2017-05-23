package com.solfest.spi;

import com.solfest.cci.SolfestCF;
import com.solfest.cci.SolfestConn;

import javax.resource.NotSupportedException;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;

import javax.transaction.xa.XAResource;
import javax.security.auth.Subject;
import java.io.PrintWriter;

import java.util.List;
import java.util.LinkedList;

/**
 * ManagedConnection implementation, supporting one connection.
 */
class SolfestMC implements ManagedConnection{

    private PrintWriter obsoleteLogger;
    protected ConnectionRequestInfo cxRequestInfo;

    private List<ConnectionEventListener> listeners;
    private SolfestConn activeConnection;

    public SolfestMC(ConnectionRequestInfo cxRequestInfo){
        this.cxRequestInfo = cxRequestInfo;
        listeners = new LinkedList<ConnectionEventListener>();
        activeConnection = null;
    }

    /** {@inheritDoc}
     */
    public void associateConnection(Object connection){
        // clean up the old connection
        if( activeConnection == null ){
            cleanup();
        }
        // save a reference to the given connection
        if( connection instanceof SolfestConn){
            activeConnection = connection;
        }else{
            throw new ResourceException("Cannot associate connection."
                                        " Is of type " + connection.class + 
                                        " but should be of type " + SolfestConn.class);
        }
    }

    /** {@inheritDoc}
     *
     * @param subject security subject - ignored as security is not supported
     */
    public Object getConnection(Subject subject, ConnectionRequestInfo cxRequestInfo){


    }

    /** {@inheritDoc}
     */
    public ManagedConnectionMetaData getMetaData(){

    }

    /** {@inheritDoc}
     */
    public void addConnectionEventListener(ConnectionEventListener listener){

    }

    /** {@inheritDoc}
     */
    public removeConnectionEventListener(ConnectionEventListener listener){

    }

    /** {@inheritDoc}
     */
    public void cleanup(){
        activeConnection = null;
    }

    /** {@inheritDoc}
     */
    public void destroy(){

    }

    /****** UNSUPPORTED FUNCTIONALITY *******/ 

    /**
     * Required to conform to interface. Not used
     */
    @Override
    public PrintWriter getLogWriter(){ return this.obsoleteLogger; }

    }

    /**
     * Required to conform to interface. Not used
     */
    @Override
    public void setLogWriter(PrintWriter out){ this.obsoleteLogger = out; }


    /** {@inheritDoc}
     */
    public LocalTransaction getLocalTransaction(){
        throw new NotSupportedException(this.class + " does not support local transactions");
    }

    /** {@inheritDoc}
     */
    public XAResource getXAResource(){
        throw new NotSupportedException(this.class + " does not support XAR transactions");
    }

}
