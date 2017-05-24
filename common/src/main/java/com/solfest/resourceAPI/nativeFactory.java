package com.solfest.resourceAPI;

/**
 * Factory interface to generate nativeAPI's.
 *
 * This MUST be serializable and referenceable in order
 * for JNDI to work properly and safely.
 */
import java.io.Serializable;
import javax.resource.Referenceable;

public interface nativeFactory extends Serializable, Referenceable{
    public nativeAPI getNatives();
}
