package com.solfest.cppJniTrivial;

import com.solfest.resourceAPI.ITrivialNative;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrivialNative implements ITrivialNative {

    /** Logger, accessed and utilized by the native methods **/
    static final Logger logger = LoggerFactory.getLogger(TrivialNative.class);

    /** Native methods */

    /** A stateless call into CPP.
     * 
     * @return a silly string to demonstrate it's possible
     */
    public native String noStateNative();

    /** A stateful call into CPP.
     * 
     * @param input arbitrary input to shove into C
     * @return a silly string to demonstrate it's possible
     */ 
    public native String stateNative(int input); 

    // Load the library
    static{ NarSystem.loadLibrary(); }
}
