package com.itgrids.controllers.web;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class SwachhBharatMissionIHHLController {
	
	private static final Logger LOG = Logger.getLogger(SwachhBharatMissionIHHLController.class);
	
	@RequestMapping(value ="/swachhBharatMissionIHHL",method = RequestMethod.GET)
    public String drainsDashboard(ModelMap model) {
		return "swachhBharatMissionIHHL";
    }
}
