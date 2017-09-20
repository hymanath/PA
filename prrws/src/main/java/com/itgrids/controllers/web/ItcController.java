package com.itgrids.controllers.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itgrids.service.IItcDashboardService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class ItcController {
	
	private static final Logger LOG = Logger.getLogger(ItcController.class);
	
	@Autowired
	private IItcDashboardService itcDashboardService;
	
	@RequestMapping(value ="/itcDashboard",method = RequestMethod.GET)
    public String drainsDashboard(ModelMap model) {
		return "ItcDashboard";
    }
}
