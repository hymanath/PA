package com.itgrids.controllers.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.UserVO;
import com.itgrids.model.User;
import com.itgrids.service.IUserService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired 
	private IUserService iUserService;

	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	
	//@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	@GetMapping("/loginPage")
    public String viewLogin() {
        return "login";
    }
	
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserVO userAuthentication(@RequestBody User user) {
		return iUserService.userAuthentication(user.getUsername(), user.getPasswordHashText());
	}
	
}
