package com.solfest.services;

/**
 * Exception used by the Bean Service.
 */
public class BeanServiceException extends Exception {
    public BeanServiceException(String msg){ super(msg); }
    public BeanServiceException(String msg, Throwable cause){ super(msg, cause); }
}
