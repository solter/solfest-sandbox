package com.solfest.executables;

public interface IRunnableBean {
    /**
     * Execute and save the result.
     *
     * @return the total number of times this has been run
     */
    public int Execute() throws ExecutableException;
    /**
     * Get one of the previous results.
     *
     * @param i the result number (zero indexed)
     * @return The result report for the ith run as a string
     */
    public String getResult(int i) throws ExecutableException;
}
