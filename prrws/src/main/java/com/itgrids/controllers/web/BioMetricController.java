package com.itgrids.controllers.web;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import com.itgrids.service.IBioMetricService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class BioMetricController {

	   private static final Logger LOG = Logger.getLogger(BioMetricController.class);
	
	  /* @Autowired
	   private IBioMetricService bioMetricService;*/
	   
	   @RequestMapping(value ="/bioMetricDashBoard",method = RequestMethod.GET)
	   public String bioMetricDashBoard() {
		   return "bioMetricDashBoard";
	   }
	   
	 /*  @RequestMapping(value ="/saveBioMetricEmployeeDetails",method = RequestMethod.GET)
	    public String saveBioMetricEmployeeDetails(ModelMap model) {
		   bioMetricService.saveBioMetricEmployeeDetails();
		   return "success";
	    }
	   @RequestMapping(value ="/saveBioMetricEmployeeAttendenceDetails",method = RequestMethod.GET)
	    public String saveBioMetricEmployeeAttendenceDetails(ModelMap model) {
		   bioMetricService.saveBioMetricEmployeeAttendenceDetails();
		   return "success";
	    }*/
	   
}
