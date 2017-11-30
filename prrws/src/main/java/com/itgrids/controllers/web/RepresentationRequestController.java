package com.itgrids.controllers.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.RepresentationRequestVO;
import com.itgrids.dto.ResponseVO;
import com.itgrids.service.IRepresentationRequestService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class RepresentationRequestController {
	private static final Logger LOG = Logger.getLogger(RepresentationRequestController.class);
	
	@Autowired
	private IRepresentationRequestService representationRequestService;
	
	
	@RequestMapping(value ="/representationRequestEntry", method = RequestMethod.GET)
    public String mgnregsDashBoardPage(ModelMap model) {
		return "representationRequestEntry";
    }
	
	@RequestMapping(value ="/saveRepresentRequestDetails",method = RequestMethod.POST)
    public @ResponseBody ResponseVO saveRepresentRequestDetails(@ModelAttribute  RepresentationRequestVO representationRequestVO) {
    	 return representationRequestService.saveRepresentRequestDetails(representationRequestVO);
    }
}