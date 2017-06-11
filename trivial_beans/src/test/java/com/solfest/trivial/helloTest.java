package com.solfest.trivial;

import org.junit.Test;
import org.junit.Before;

public class helloTest extends IRunnableBeanTest {
    @Before
    public void setup(){
        totest = new hello();
    }

    /**
     * Test that the execute returns an increasing size list.
     */
    @Test
    public void testGoodbyeExecute(){
        testExecute();
    }

    /**
     * Test that the getResult has state. 
     */
    @Test
    public void testGoodbyeGetResult(){
        testGetResult();
    }

}
