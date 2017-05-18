package com.solfest.executables;

public class ExecutableException extends Exception {
    public ExecutableException(){ super(); }
    public ExecutableException(String msg){ super(msg); }
    public ExecutableException(Throwable cause){ super(cause); }
    public ExecutableException(String msg, Throwable cause){ super(msg, cause); }
}
