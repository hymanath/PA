package com.itgrids.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itgrids.service.integration.external.WebServiceUtilService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class NregsDashboard {
	
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	
	@RequestMapping(value ="/test", method = RequestMethod.GET)
    public String fundManagementDashboardPage(ModelMap model) {
		webServiceUtilService.testMethod();
		return "fundManagementDashboard";
    }

}
