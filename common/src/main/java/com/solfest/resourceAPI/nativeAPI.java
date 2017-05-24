package com.solfest.resourceAPI;

/**
 * The interface used to communicate with the native library.
 */
public interface nativeAPI{
    public String statelessCall(int[] input1);
    public String stateSet(double input2);
    public double stateGet();
}
