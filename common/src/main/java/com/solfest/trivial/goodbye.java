package com.solfest.trivial;

import com.solfest.executables.IRunnableBean;
import com.solfest.executables.ExecutableException;

import java.util.List;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class goodbye implements IRunnableBean {

    private final Logger = LoggerFactory.getLogger(goodbye.class);
    private List<String> results;

    public goodbye(){
        Logger.info("goodbye initializing");
        results = new LinkedList<String>();
    }

    @Override
    public int Execute() throws ExecutableException{
        Logger.info("goodbye Executing");
        results.add("goodbye " + results.size() + "th run");
        return results.size();
    }

    @Override
    public String getResult(int i) throws ExecutableException{
        Logger.info("goodbye retrieving result " + i);
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
