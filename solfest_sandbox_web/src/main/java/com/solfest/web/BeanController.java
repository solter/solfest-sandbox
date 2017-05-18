package com.solfest.web;

import com.solfest.services.BeanService;
import com.solfest.services.BeanServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BeanController {

    private final Logger logger = LoggerFactory.getLogger(BeanController.class);

    private BeanService beanService;

    public BeanController(BeanService beanService){
        this.beanService = beanService;
    }

    @RequestMapping(value = "/")
    public String index(Model model) {
        logger.debug("index redirecting to /beans");
        // redirect to the beans page
        return "redirect:/beans";
    }

    // list page
    @RequestMapping(value = "/beans")
    public String showAllBeans(Model model) {

        logger.debug("displaying all beans");
        String[] bean_names;
        bean_names = beanService.getBeanNames();
        model.addAttribute("bean_list", bean_names);
        // point to the list.jsp file
        return "beans/list";
    }

    @RequestMapping(value = "/beans/run/${bean_name}")
    public String runBean(@PathVariable("bean_name") String bean_name, Model model){
        logger.debug("running bean " + bean_name);
        // Run the bean
        int result_count;
        try{
            result_count = beanService.executeBean(bean_name);
        }catch(BeanServiceException bse){
            model.addAttribute("exception", bse);
            return "error";
        }
        // redirect to the bean's latest result
        return "redirect:/beans/result/" + bean_name + "/" + result_count;
    }

    @RequestMapping(value = "/beans/run/hello")
    public String runBean(Model model){
        String bean_name = "hello";
        logger.debug("running bean " + bean_name);
        // Run the bean
        int result_count;
        try{
            result_count = beanService.executeBean(bean_name);
        }catch(BeanServiceException bse){
            model.addAttribute("exception", bse);
            return "error";
        }
        // redirect to the bean's latest result
        return "redirect:/beans/result/" + bean_name + "/" + result_count;
    }

    @RequestMapping(value = "/beans/result/${bean_name}/{run_number}")
    public String displayBeanResult(@PathVariable("bean_name") String bean_name, 
                                    @PathVariable("run_number") int run_number, 
                                    Model model){

        logger.debug("displaying bean " + bean_name + " result number " + run_number);
        // Get the result
        String result;
        try{
            result = beanService.getBeanResult(bean_name, run_number);
        }catch(BeanServiceException bse){
            model.addAttribute("exception", bse);
            return "error";
        }
        model.addAttribute("result", result);
        // point to the result.jsp page
        return "beans/result";
    }

    @RequestMapping(value = "/beans/result/hello/{run_number}")
    public String displayBeanResult(@PathVariable("run_number") int run_number, 
                                    Model model){
        String bean_name = "hello";
        logger.debug("displaying bean " + bean_name + " result number " + run_number);
        // Get the result
        String result;
        try{
            result = beanService.getBeanResult(bean_name, run_number);
        }catch(BeanServiceException bse){
            model.addAttribute("exception", bse);
            return "error";
        }
        model.addAttribute("result", result);
        // point to the result.jsp page
        return "beans/result";
    }
}
