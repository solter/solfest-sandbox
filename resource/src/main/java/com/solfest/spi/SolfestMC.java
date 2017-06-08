package com.solfest.spi;

import com.solfest.resourceAPI.nativeAPI;
import com.solfest.resourceAPI.nativeFactory;

import javax.resource.ResourceException;
import javax.resource.NotSupportedException;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ConnectionEvent;

import javax.transaction.xa.XAResource;
import javax.security.auth.Subject;
import java.io.PrintWriter;
import java.io.Closeable;

import java.util.List;
import java.util.LinkedList;

import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static javax.resource.spi.ConnectionEvent.CONNECTION_CLOSED;

/**
 * ManagedConnection implementation, supporting one connection.
 */
public class SolfestMC implements ManagedConnection, Closeable{

    /** fancy slf4j logger */
    private final Logger logger = LoggerFactory.getLogger(SolfestMC.class);
    /** poor man's logger */
    private PrintWriter ServerLogger;
    /** Connection request info associated with this managed connection */
    protected ConnectionRequestInfo cxRequestInfo;
    /** All connection listeners */
    private List<ConnectionEventListener> listeners;
    /** The connection this manages */
    private NativeCaller activeConnection;

    public SolfestMC(PrintWriter out, ConnectionRequestInfo cxRequestInfo){
        // save references 
        this.ServerLogger = out;
        this.cxRequestInfo = cxRequestInfo;

        // initalize stuff
        listeners = new LinkedList<ConnectionEventListener>();
        activeConnection = new NativeCaller(out, this);

        doubleLog("SolfestMC initialized");
    }

    /** {@inheritDoc}
     */
    @Override
    public void associateConnection(Object connection) throws ResourceException{
        doubleLog("associating another connection with SolfestMC");
        // clean up the old connection
        if( activeConnection != null ){
            cleanup();
        }
        // save a reference to the given connection
        if( connection instanceof NativeCaller){
            activeConnection = (NativeCaller) connection;
        }else{
            throw new ResourceException("Cannot associate connection." +
                                        " Is of type " + connection.getClass() + 
                                        " but should be of type " + NativeCaller.class);
        }
    }

    /** {@inheritDoc}
     *
     * @param subject security subject - ignored as security is not supported
     */
    @Override
    public Object getConnection(Subject subject, ConnectionRequestInfo cxRequestInfo){
        doubleLog("SolfestMC retrieving connection");
        return activeConnection;
    }

    /** {@inheritDoc}
     */
    @Override
    public ManagedConnectionMetaData getMetaData(){
        return new SolfestMCMeta();
    }

    /** {@inheritDoc}
     */
    @Override
    public void addConnectionEventListener(ConnectionEventListener listener){
        doubleLog("Adding a connection event listener");
        this.listeners.add(listener);
    }

    /** {@inheritDoc}
     */
    @Override
    public void removeConnectionEventListener(ConnectionEventListener listener){
        doubleLog("removing a connection event listener");
        this.listeners.remove(listener);
    }

    /** {@inheritDoc}
     */
    @Override
    public void cleanup(){
        doubleLog("cleaning up connection");
        activeConnection.clear();
    }

    /** {@inheritDoc}
     */
    @Override
    public void destroy(){
        doubleLog("destroying connection");
        activeConnection.clear();
    }

    /** 
     * Inform all the listeners that this connection is closing.
     */
    @Override
    public void close(){
        doubleLog("closing connection");
        ConnectionEvent closingEvent = new ConnectionEvent(this, CONNECTION_CLOSED);
        closingEvent.setConnectionHandle(activeConnection);
        for(ConnectionEventListener spy : this.listeners){
            spy.connectionClosed(closingEvent);
        }
    }

    /**
     * Retrieve the server logger
     */
    @Override
    public PrintWriter getLogWriter(){ 
        return this.ServerLogger; 
    }

    /**
     * set the server logger
     */
    @Override
    public void setLogWriter(PrintWriter out){ 
        doubleLog("resetting the ServerLogger");
        this.ServerLogger = out; 
        doubleLog("ServerLogger reset");
    }

    /**
     * Log to both the ServerLogger and the Logger with the same message.
     */
    private void doubleLog(String msg){
        ServerLogger.println("SolfestMC - INFO: " + msg);
        logger.info(msg);
    }

    // UNSUPPORTED FUNCTIONALITY

    /** {@inheritDoc}
     */
    @Override
    public LocalTransaction getLocalTransaction()
        throws NotSupportedException{
        throw new NotSupportedException(SolfestMC.class + " does not support local transactions");
    }

    /** {@inheritDoc}
     */
    @Override
    public XAResource getXAResource()
        throws NotSupportedException{
        throw new NotSupportedException(SolfestMC.class + " does not support XAR transactions");
    }

}
