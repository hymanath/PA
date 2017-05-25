package com.itgrids.controllers.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.TdpResolutionVo;
import com.itgrids.rest.Response;
import com.itgrids.service.IResolutionMailService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class LoginController {

	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IResolutionMailService resolutionMailService;
	
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String viewLogin() {
        return "login";
    }
	@RequestMapping(value = "/resolutionMail", method = RequestMethod.GET)
    public String viewSent() {
        return "sentResolution";
    }
	
	@RequestMapping(value = "/sentresolutionMail", method = RequestMethod.POST)
	@ResponseBody
    public Response sentMail(@RequestBody TdpResolutionVo tdpResolutionVo) {
		Response response = null;

		try {
			response = new Response(200, "success", resolutionMailService.sentEmails(tdpResolutionVo));
			
			return response;
		} catch (Exception e) {
			LOG.error("Error in getting example", e);
			response = new Response(207, "Failed", e.getMessage());
			return response;
		}

    }
}
