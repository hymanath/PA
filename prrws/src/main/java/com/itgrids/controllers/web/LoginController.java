package com.itgrids.controllers.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.UserVO;
import com.itgrids.model.User;
import com.itgrids.service.IUserService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired 
	private IUserService iUserService;
	@Autowired 
	private IUserService userServiceImpl;

	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping("/loginPage")
    public String viewLogin() {
        return "login";
    }
	
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserVO userAuthentication(@RequestBody User user) {
		return iUserService.userAuthentication(user.getUsername(), user.getPasswordHashText());
	}
	@RequestMapping(value = "/getAssignedSearchIdByTypeId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAssignedSearchIdByTypeId(@RequestBody InputVO inputVO,ModelMap madelMap) {
		String resultStatus = "";
		String code = userServiceImpl.getAssignedSearchIdByTypeId(inputVO.getSearchLevelId(),inputVO.getSearchLevelValue(),inputVO.getFromPage(),inputVO.getToPage());
		
		madelMap.addAttribute("code", code);
		if(inputVO.getToPage() != null && inputVO.getToPage().equalsIgnoreCase("FMS") )//toPage checking
			resultStatus = "fundManagementDashboard"+" "+code; //jsp
		else if(inputVO.getToPage() != null && inputVO.getToPage().equalsIgnoreCase("MGNREGS"))
			resultStatus = "MGNREGS"+" "+code;
		else if(inputVO.getToPage() != null && inputVO.getToPage().equalsIgnoreCase("RWS"))
			resultStatus = "ruralWaterSupplyDashBoard"+" "+code;
		else if(inputVO.getToPage() != null && inputVO.getToPage().equalsIgnoreCase("ENC"))
			resultStatus = "prisSurveyDashBoard"+" "+code;
		return resultStatus;
	}
	
	
	
}
