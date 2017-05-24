package com.solfest.resourceAPI;

/**
 * The interface used to communicate with the native library.
 *
 * Note that it implements AutoClosable to clean up the native
 * resources upon release.
 */
public interface nativeAPI implements AutoClosable{
    public String statelessCall(int[] input1);
    public String stateSet(double input2);
    public double stateGet();
    @Override
    public void close();
}
