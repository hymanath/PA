package com.itgrids.eliteclub.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itgrids.eliteclub.service.impl.SmsThreadClass;

@Controller
public class HelloWorldController { 

	@Autowired
	SmsThreadClass smsThreadClass;
    @RequestMapping("/welcome")
    public String hello(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        smsThreadClass.activeteThread("123456",1,1);
        return "helloworld";
    }

}
