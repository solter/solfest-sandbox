package com.solfest.spi;

import com.solfest.resourceAPI.nativeAPI;
import com.solfest.resourceAPI.nativeFactory;

import javax.naming.Reference;

import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.ConnectionManager;

import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NativeFactoryImpl implements nativeFactory {
    /** serialization id */
    private static final long serialVersionUID = 1337L;
    /** Reference to this object */
    private Reference reference;
    /** Managed connection factory to generate new managed connections */
    private ManagedConnectionFactory mcf;
    /** Connection manager to allocate new connections */
    private ConnectionManager cm;
    /** fancy slf4j logger */
    private final Logger = LoggerFactory.getLogger(NativeFactoryImpl.class);
    /** poor man's logger */
    private PrintWriter ServerLogger;

    /**
     * Constructor to initialize with objects used to create new connections.
     *
     * @param mcf managed connection factory to generate new managed connections
     * @param cm connection manager to allocate new connections
     * @param out reference to the printwriter provided by the server
     */
    public NativeFactoryImpl(ManagedConnectionFactory mcf, ConnectionManager cm, PrintWriter out){
        this.mcf = mcf;
        this.cm = cm;
        this.ServerLogger = out;

        doubleLog("initializing NativeFactoryImpl");
    }

    /**
     * Generate a NativeAPI instance.
     *
     * Use the connection manager to allocate a connection utilizing
     * SolfestMCF rather than generating a new nativeAPI.
     */
    @Override
    public nativeAPI getNatives(){
        doubleLog("getNatives called");
        try {
            return (nativeAPI) cm.allocateConnection(mcf, new SolfestCRI());
        } catch (ResourceException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    // Reference methods to enable JNDI bindings
    /** {@inheritDoc}
     */
    @Override
    public void setReference(Reference reference){
        this.reference = reference;
    }

    /** {@inheritDoc}
     */
    @Override
    public Reference getReference(){
        return this.reference;
    }

    /**
     * Log to both the ServerLogger and the Logger with the same message.
     */
    private doubleLog(String msg){
        ServerLogger.println("NativeFactoryImpl - INFO: " + msg);
        Logger.info(msg);
    }
}
