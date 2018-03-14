package com.itgrids.controllers.web;



import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class ItMinisterDashboardController {
	private static final Logger LOG = LoggerFactory.getLogger(ItMinisterDashboardController.class);
	
	
	@RequestMapping(value = "/itMinisterDashboard", method = RequestMethod.GET)
    public String NoOfRegisteredCompanyProperties(HttpServletRequest request) {
        return "itMinisterDashboard"; 
    }
	
}
