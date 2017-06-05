package com.itgrids.controllers.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.LocationVO;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class LoginController {

	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String viewLogin() {
        return "login";
    }
	
}
