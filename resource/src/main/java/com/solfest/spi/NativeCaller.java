package com.solfest.spi;

import com.solfest.resourceAPI.nativeAPI;

import java.io.Closeable;
import java.io.IOException;

import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NativeCaller implements nativeAPI {

    /** State of the caller */
    private double my_state = 0.0d;
    /** fancy slf4j logger */
    private final Logger logger = LoggerFactory.getLogger(NativeCaller.class);
    /** poor man's logger */
    private PrintWriter ServerLogger;
    /** a closable object */
    private Closeable toclose;

    public NativeCaller(PrintWriter out, Closeable toclose){
        ServerLogger = out;
        this.toclose = toclose;
        doubleLog("NativeCaller initialized");
    }

    @Override
    public String statelessCall(int[] input1){
        doubleLog("NativeCaller stateless call made");
        String result = "[ ";
        for(int i : input1){
            result += i + ",";
        }
        result += "]";
        return result;
    }

    @Override
    public String stateSet(double input2){
        doubleLog("NativeCaller state set to " + input2);
        my_state = input2;
        return "State set to " + input2;
    }

    @Override
    public double stateGet(){
        doubleLog("NativeCaller state retrieved");
        return my_state;
    }

    public void clear(){
        doubleLog("NativeCaller cleared");
        my_state = 0.0d;
    }

    /**
     * Reset to the default value.
     */
    @Override
    public void close(){
        doubleLog("NativeCaller closed");
        clear();
        try{
            toclose.close();
        }catch(IOException ie){
            doubleLog("closing raised exception");
        }
    }

    /**
     * Log to both the ServerLogger and the Logger with the same message.
     */
    private void doubleLog(String msg){
        ServerLogger.println("NativeCaller - INFO: " + msg);
        logger.info(msg);
    }
}
