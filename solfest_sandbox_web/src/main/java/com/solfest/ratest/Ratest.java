package com.solfest.ratest;

import com.solfest.executables.IRunnableBean;
import com.solfest.resourceAPI.nativeFactory;
import com.solfest.resourceAPI.nativeAPI;

import java.util.List;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ratest implements IRunnableBean{

    private final Logger = LoggerFactory.getLogger(Ratest.class);
    private List<String> results;
    private nativeFactory resourceFactory;
    private nativeAPI activeConnection;

    public Ratest(nativeFactory factory){
        Logger.info("Ratest initializing");
        this.resourceFactory = factory;
        results<String> = new LinkedList<String>();
    }

    @Override
    public int Execute() throws ExecutableException{
        Logger.info("Ratest executing");
        String result = "";
        int count = results.size();
        if(count%2 == 1){
            Logger.info("performing stateless operation");
            try(nativeAPI runner = factory.getNatives();){
                int[] input = {1,2,3,4};
                result = "statelessCall result:\n    " + runner.statelessCall(input);
                results.add(result);
            }
        }else{
            Logger.info("performing stateful successive calls");
            try(nativeAPI runner = factory.getNatives();){
                result =  "setState result :\n    ";
                // multiples of pi
                result += runner.stateSet((count/2) * 3.14d);
                result += "\nstateGet result:\n    ";
                result += runner.stateGet();
                results.add(result);
            }
        }
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
