package com.solfest.spi;

import javax.resource.ResourceException;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.BootstrapContext;
import javax.resource.spi.ActivationSpec;
import javax.resource.spi.TransactionSupport;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.transaction.xa.XAResource;

@Connector(
    // descriptors
    description = "An example of a simple resource adapter (RA)",
    displayName = "ExampleRA",
    eisType = "access to native methods",
    vendorName = "GitHub (Solter)",
    version = "0.1.0",
    // disable all but the simplest bits of the RA
    licenseRequired = false,
    reauthenticationSupport = false,
    transactionSupport = TransactionSupport.TransactionSupportLevel.NoTransaction
)
class SolfestRA implements ResourceAdapter {

    // a local reference to the context used to start the RA
    private BootstrapContext context;
    
    /** 
     * Initializes the RA.
     *
     * @param context a bootstrap context containing references to useful facilities that could be used by the RA instance.
     */
    @Override
    public void start(BootstrapContext context){
        this.context = context;
    }

    /** 
     * Shuts down the RA gracefully.
     */
    @Override
    public void stop(){

    }

    /** {@inheritDoc}
     */
    @Override
    public void endpointActivation(MessageEndpointFactory endpointFactory, ActivationSpec spec)
        throws ResourceException{ }

    /** {@inheritDoc}
     */
    @Override
    public void endpointDeactivation(MessageEndpointFactory endpointFactory, ActivationSpec spec){ }

    /** 
     * Since no XAResource is implemented, this always returns null.
     */
    @Override
    public XAResource[] getXAResources(ActivationSpec[] specs){
        return null;
    }

}
