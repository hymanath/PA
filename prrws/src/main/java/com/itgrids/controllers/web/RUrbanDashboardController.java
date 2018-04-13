package com.itgrids.controllers.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itgrids.service.IRUrbanDashboardService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class RUrbanDashboardController {

	private static final Logger LOG = Logger.getLogger(RUrbanDashboardController.class);
	
	@Autowired
	private IRUrbanDashboardService rUrbanDashboardService;
	
	@RequestMapping(value ="/rurbanDashboard",method = RequestMethod.GET)
    public String rUrbanDashboard(ModelMap model) {
		return "rUrbanDashboard";
    }
}
