package com.itgrids.controllers.web;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.KeyValueVO;
import com.itgrids.service.impl.LocationDetailsService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class PmRequestDetailsController {

	   private static final Logger LOG = Logger.getLogger(PmRequestDetailsController.class);
	   
	   @RequestMapping(value ="/representationRequestEntry", method = RequestMethod.GET)
	    public String mgnregsDashBoardPage(ModelMap model) {
			return "representationRequestEntry";
	    }
}
