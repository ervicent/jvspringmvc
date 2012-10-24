package com.naskar.springmvc.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        return mav;
    }
	
	@RequestMapping("/home/welcome")
    public ModelAndView welcome() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("welcome");
        
        mav.addObject("message", "Bem vindo!!!");
        
        return mav;
    }

}
