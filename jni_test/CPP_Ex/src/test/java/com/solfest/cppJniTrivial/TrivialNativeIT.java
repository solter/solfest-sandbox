package com.solfest.cppJniTrivial;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple integration test for the native methods.
 */
public class TrivialNativeIT {

    private final Logger logger = LoggerFactory.getLogger(TrivialNativeIT.class);
    TrivialNative underTest;

    @Before
    public void init(){
        underTest = new TrivialNative();
    }

    @Test
    public void testNoStateNative(){
        String returned = underTest.noStateNative();
        logger.info("noStateNative() returned: " + returned);

        assertTrue("Return from stateless call was empty", !returned.isEmpty()); 
    }

    @Test
    public void testStateNative(){
        String returned = underTest.stateNative(1);
        logger.info("stateNative(1) returned : " + returned);
        returned = underTest.stateNative(2);
        logger.info("stateNative(2) returned : " + returned);

        assertTrue("Stateful native call did not report sum", returned.contains("3")); 
    }

}
