package com.solfest.jniTest;

import com.solfest.executables.IRunnableBean;
import com.solfest.executables.ExecutableException;
import com.solfest.resourceAPI.ITrivialNative;

import java.util.List;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class jniTest implements IRunnableBean {

    private final Logger logger = LoggerFactory.getLogger(jniTest.class);
    private List<String> results;

    private ITrivialNative injected;

    public jniTest(ITrivialNative injected){
        logger.info("initializaing jniTest");
        this.injected = injected;
        results = new LinkedList<String>();
    }

    @Override
    public int Execute() throws ExecutableException{
        logger.info("running jniTest.Execute");
        logger.info(injected.noStateNative());
        results.add( injected.stateNative(results.size()) );
        return results.size(); 
    }

    @Override
    public String getResult(int i) throws ExecutableException{
        logger.info("jniTest retrieving result " + i);
        if(results.isEmpty()){
            throw new ExecutableException("No previous runs");
        }
        if(results.size() < i){
            throw new ExecutableException("There are only " + results.size() + "runs, but the " + i + "th run asked for");
        }
        if(results.size() < 1){
            throw new ExecutableException("Invalid index. Must be a positive integer.");
        }
        return results.get(i-1);
    }
}
