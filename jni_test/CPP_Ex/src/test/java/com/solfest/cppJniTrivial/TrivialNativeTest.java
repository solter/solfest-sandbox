package com.solfest.cppJniTrivial;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

/**
 * Simple JUnit tests for the native methods.
 */
public class TrivialNativeTest {

    TrivialNative underTest;

    @Before
    public void init(){
        underTest = new TrivialNative();
    }

    @Test
    public void testNoStateNative(){
        String returned = underTest.noStateNative();
        System.out.println("noStateNative() returned: " + returned);

        assertTrue("Return from stateless call was empty", !returned.isEmpty()); 
    }

    @Test
    public void testStateNative(){
        String returned = underTest.stateNative(1);
        System.out.println("stateNative(1) returned : " + returned);
        returned = underTest.stateNative(2);
        System.out.println("stateNative(2) returned : " + returned);

        assertTrue("Stateful native call did not report sum", returned.contains("3")); 
    }

}
