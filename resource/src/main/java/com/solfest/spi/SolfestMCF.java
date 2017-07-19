package com.solfest.spi;

import com.solfest.resourceAPI.nativeAPI;
import com.solfest.resourceAPI.nativeFactory;

import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ConnectionDefinition;

import javax.security.auth.Subject;
import java.io.PrintWriter;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Define this as using a standard CCI interface
@ConnectionDefinition(
    connectionFactory = nativeFactory.class,
    connectionFactoryImpl = NativeFactoryImpl.class,
    connection = nativeAPI.class,
    connectionImpl = NativeCaller.class
)
public class SolfestMCF implements ManagedConnectionFactory {

    /** fancy slf4j logger */
    private final Logger logger = LoggerFactory.getLogger(SolfestMCF.class);
    /** poor man's logger */
    private PrintWriter ServerLogger = null;

    /** {@inheritDoc}
    */
    @Override
    public Object createConnectionFactory(){
        doubleLog("Creating native factory without providing a connection manager");
        return new NativeFactoryImpl(this, null, ServerLogger);
    }

    /** {@inheritDoc}
    */
    @Override
    public Object createConnectionFactory(ConnectionManager cxManager){
        doubleLog("Creating native factory");
        return new NativeFactoryImpl(this, cxManager, ServerLogger);
    }

    /** {@inheritDoc}
     *
     * @param subject security subject - ignored as security is not supported
     */
    @Override
    public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo cxRequestInfo){
        doubleLog("Creating managed connection");
        return new SolfestMC(ServerLogger, cxRequestInfo);
    }

    /**
     * Returns a ManagedConnection which utilized the same cxRequestInfo
     * as provided
     *
     * @param connectionSet set of connections to check
     * @param subject security subject - ignored as security is not supported
     * @param cxRequestInfo the connection requestion information to match
     */
    @Override
    public ManagedConnection matchManagedConnections(Set connectionSet, Subject subject, ConnectionRequestInfo cxRequestInfo){
        doubleLog("Matching connection");
        for( Object obj : connectionSet ){
            // it must be an instance of SolfestMC to match
            if (obj instanceof SolfestMC){
                SolfestMC mc = (SolfestMC) obj;
                ConnectionRequestInfo info = mc.cxRequestInfo;
                if( (cxRequestInfo == null && info == null ) ||
                        info.equals(cxRequestInfo)              ){
                    return mc;
                        } 
            }
        }
        return null;
    }

    /**
     * Required to conform to interface. Not used
     */
    @Override
    public PrintWriter getLogWriter(){ return this.ServerLogger; }

    /**
     * Required to conform to interface. Not used
     */
    @Override
    public void setLogWriter(PrintWriter out){ 
        out.println("SolfestMCF - INFO: Setting log writer");
        this.ServerLogger = out; 
    }

    /**
     * Log to both the ServerLogger and the Logger with the same message.
     */
    private void doubleLog(String msg){
        if( ServerLogger != null){
            ServerLogger.println("SolfestMCF - INFO: " + msg);
        }
        logger.info(msg);
    }
}
