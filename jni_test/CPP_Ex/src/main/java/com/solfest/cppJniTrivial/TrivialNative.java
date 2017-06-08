package com.solfest.cppJniTrivial;

import com.solfest.resourceAPI.ITrivialNative;

public class TrivialNative implements ITrivialNative {

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
