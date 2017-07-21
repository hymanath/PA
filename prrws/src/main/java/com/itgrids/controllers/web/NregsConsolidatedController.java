package com.itgrids.controllers.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itgrids.service.integration.impl.INREGSConsolidatedService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class NregsConsolidatedController {

	private static final Logger LOG = Logger.getLogger(NregsConsolidatedController.class);
	
	@Autowired
	private INREGSConsolidatedService nregsConsolidatedService;
	
	@RequestMapping(value ="/NregaConsolidatedDashboard", method = RequestMethod.GET)
    public String getNregaConsolidatedDashboard(ModelMap model) {
		return "NregaConsolidated";
    }
}
