package com.solfest.services;

import com.solfest.executables.IRunnableBean;
import com.solfest.executables.ExecutableException;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanService implements ApplicationContextAware {

    private ApplicationContext context;

    public void setApplicationContext( ApplicationContext context )
        throws BeansException {
        this.context = context;
    }

    /**
     * Get all the executable beans.
     *
     * @return list of bean names
     */
    public String[] getBeanNames(){
        return context.getBeanNamesForType(IRunnableBean.class);
    }

    /**
     * Execute the named bean.
     *
     * @param bean_name the name of the bean
     * @return the total number of times this bean has been run.
     */
    public int executeBean(String bean_name) throws BeanServiceException{
        IRunnableBean bean;
        try{
            bean = (IRunnableBean) context.getBean(bean_name);
        }catch(Exception e){
            throw new BeanServiceException("Could not retrieve " + bean_name, e);
        }
        try{
            return bean.Execute();
        }catch(ExecutableException ee){
            throw new BeanServiceException(bean_name + " execution failed", ee);
        }
    }

    /**
     * Get a specific result from the named bean.
     *
     * @param bean_name the name of the bean
     * @param run_number the result number to be retrieved
     *
     * @return The result report for the run_number as a string
     */
    public String getBeanResult(String bean_name, int run_number) throws BeanServiceException{
        IRunnableBean bean;
        try{
            bean = (IRunnableBean) context.getBean(bean_name);
        }catch(Exception e){
            throw new BeanServiceException("Could not retrieve " + bean_name, e);
        }
        try{
            return bean.getResult(run_number);
        }catch(ExecutableException ee){
            throw new BeanServiceException(bean_name + " could not retrieve result number " + run_number, ee);
        }
    }
    
}
