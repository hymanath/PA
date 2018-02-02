package com.itgrids.controllers.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itgrids.service.IUnderGroundDrainageService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class UnderGroundDrainageController {
	private static final Logger LOG = Logger.getLogger(UnderGroundDrainageController.class);
	
	@Autowired
	private IUnderGroundDrainageService underGroundDrainageService;
	
}
