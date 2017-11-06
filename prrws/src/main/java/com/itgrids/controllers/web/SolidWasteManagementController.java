package com.itgrids.controllers.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itgrids.service.ISolidWasteManagementService;

//import com.itgrids.service.IBioMetricService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class SolidWasteManagementController {

	   private static final Logger LOG = Logger.getLogger(SolidWasteManagementController.class);
	
	   @Autowired
	   private ISolidWasteManagementService solidWasteManagementService;
	   
	   @GetMapping("/solidWasteManagementDashboard")
	   public String solidWasteManagementDashboard() {
		   return "solidWasteManagementDashboard";
	   }
	   
	   
}
