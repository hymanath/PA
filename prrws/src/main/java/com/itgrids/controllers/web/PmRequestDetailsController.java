package com.itgrids.controllers.web;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class PmRequestDetailsController {

	   private static final Logger LOG = Logger.getLogger(PmRequestDetailsController.class);
}
