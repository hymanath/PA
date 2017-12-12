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

import com.itgrids.dto.PmRequestVO;
import com.itgrids.dto.ResponseVO;
import com.itgrids.service.IPmRequestDetailsService;
import com.itgrids.service.impl.PmRequestDetailsService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class PmRequestDetailsController {

	   private static final Logger LOG = Logger.getLogger(PmRequestDetailsController.class);
	   
	   @Autowired
	   private IPmRequestDetailsService pmRequestDetailsService;
	   
	   @RequestMapping(value ="/representationRequestEntry", method = RequestMethod.GET)
	    public String mgnregsDashBoardPage(ModelMap model) {
			return "representationRequestEntry";
	    }
	   
	   	@RequestMapping(value ="/saveRepresentRequestDetails",method = RequestMethod.POST)
	    public @ResponseBody ResponseVO saveRepresentRequestDetails(@ModelAttribute  PmRequestVO pmRequestVO) {
			pmRequestVO.setUserId(1L);
			return pmRequestDetailsService.saveRepresentRequestDetails(pmRequestVO);
	    }
	   
}
