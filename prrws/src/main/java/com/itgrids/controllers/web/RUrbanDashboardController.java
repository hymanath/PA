package com.itgrids.controllers.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itgrids.service.IRUrbanDashboardService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class RUrbanDashboardController {

	private static final Logger LOG = Logger.getLogger(RUrbanDashboardController.class);
	
	@Autowired
	private IRUrbanDashboardService rUrbanDashboardService;
}
