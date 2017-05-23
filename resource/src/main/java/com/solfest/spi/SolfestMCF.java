package com.solfest.spi;

import com.solfest.cci.SolfestCF;
import com.solfest.cci.SolfestConn;

import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;

import javax.security.auth.Subject;
import java.io.PrintWriter;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Define this as using a standard CCI interface
@ConnectionDefinition(
    connectionFactory = javax.resource.cci.ConnectionFactory.class,
    connectionFactoryImpl = SolfestCF.class,
    connection = javax.resource.cci.Connection.class,
    connectionImpl = SolfestConn.class
)
class SolfestMCF implements ManagedConnectionFactory {

    final static Logger logger = LoggerFactory.getLogger(SolfestMCF.class);
    private PrintWriter obsoleteLogger;

    /** {@inheritDoc}
     */
    @Override
    public Object createConnectionFactory(){
        return new SolfestCF();
    }

    /** {@inheritDoc}
     */
    @Override
    public Object createConnectionFactory(ConnectionManager cxManager){
        return new cxManager.allocateConnection(this, null);
    }
    
    /** {@inheritDoc}
     *
     * @param subject security subject - ignored as security is not supported
     */
    @Override
    public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo cxRequestInfo){
        return new SolfestMC(cxRequestInfo);
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
    public PrintWriter getLogWriter(){ return this.obsoleteLogger; }

    }

    /**
     * Required to conform to interface. Not used
     */
    @Override
    public void setLogWriter(PrintWriter out){ this.obsoleteLogger = out; }

}
