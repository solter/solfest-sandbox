package com.solfest.trivial;

import com.solfest.executables.IRunnableBean;
import com.solfest.executables.ExecutableException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * A generic superclass to test IRunnableBeans.
 */
public class IRunnableBeanTest {
    protected IRunnableBean totest;

    /**
     * Test that the execute returns an increasing size list.
     */
    public void testExecute(){
        /** set up **/
        int result = -10;
        
        /** act (1) **/
        try{
            result = totest.Execute();
        }catch(ExecutableException ee){
            fail("Execute failed with exception : " + ee);
        }

        /** validate (1) **/
        assertTrue("Execute failed to return the correct number of calls", 
                   result == 1);

        /** act (2) **/
        try{
            result = totest.Execute();
        }catch(ExecutableException ee){
            fail("Execute failed with exception : " + ee);
        }

        /** validate (2) **/
        assertTrue("Execute failed to return the correct number of calls", 
                   result == 2);
    }

    /**
     * Test that get result works properly.
     */
    public void testGetResult(){
        /** set up **/
        int execute_result1 = -10, execute_result2 = -10;
        String get_result1 = null, get_result2 = null;

        /** act (1) **/
        try{
            execute_result1 = totest.Execute();
            get_result1 = totest.getResult(execute_result1);
        }catch(ExecutableException ee){
            fail("Execute failed with exception : " + ee);
        }

        /** validate (1) **/
        // make sure that the next result is not yet set
        try{
            get_result2 = totest.getResult(execute_result1 + 1);
            fail("Failed to throw exception when accessing non-existent result");
        }catch(ExecutableException ee){
            // do nothing, as the exception is the expected result
        }

        /** act (2) **/
        try{
            execute_result2 = totest.Execute();
            get_result2 = totest.getResult(execute_result2);
        }catch(ExecutableException ee){
            fail("Execute failed with exception : " + ee);
        }

        /** validate (2) **/
        // make sure that the next result is not yet set
        try{
            String trash = totest.getResult(execute_result2 + 1);
            fail("Failed to throw exception when accessing non-existent result");
        }catch(ExecutableException ee){
            // do nothing, as the exception is the expected result
        }

        // validate that the results are different
        assertTrue("The result did NOT change across multiple calls", !get_result2.equals(get_result1));
    }

}
