package com.itgrids.controllers.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	@RequestMapping("/petitionsLoginPage")
    public String petitionsLoginPage() {
        return "petitionsLoginPage";
    }
	
	@GetMapping("/petitionsLogout")
    public String petitionsLogout(HttpServletRequest request) {
		request.getSession().invalidate();
        return "petitionsLoginPage";
    }
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewDashboard(HttpServletRequest request) {
		UserVO uservo = (UserVO) request.getSession().getAttribute("USER");
		if (uservo==null){// default dashboard for users, direct url access 
	      return "MGNREGS";
	    }else{
	      return "MGNREGS";
	    }
    }

	/*@RequestMapping(value = "/MGNREGSDashboard", method = RequestMethod.GET)
    public String viewSaveComments(HttpServletRequest request) {
		UserVO uservo = (UserVO) request.getSession().getAttribute("User");
		if (uservo==null){
	      return "login";
	    }else{
	      return "MGNREGS";
	    }
    }*/
	
	
	
	@RequestMapping(value = "/userValidation1", method = RequestMethod.POST)
	public @ResponseBody UserVO doLogin(@RequestBody UserVO userData,HttpServletRequest request,ModelMap model) {
		String returnType ="loginPage";
		HttpSession httpSession = request.getSession();
		UserVO userVO = new UserVO();
		if(userData.getUserName().equalsIgnoreCase("itgrids") && userData.getPassword().equalsIgnoreCase("itgrids@123")){
			userVO.setUserId(1l);
			userVO.setUserName(userData.getUserName());
			userVO.setStatus("Valid user");
			userVO.setResponceCode(1L);
		}else{
			userVO.setResponceCode(0L);
			userVO.setStatus("Invalid user");
		}
		
		if(userVO != null && userVO.getStatus().equalsIgnoreCase("Valid user")){
			httpSession.setAttribute("USER", userVO);
		}
		return userVO;
		
	}
	
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserVO userAuthentication(@RequestBody User user,HttpServletRequest request) {
		UserVO userVO = userServiceImpl.userAuthentication(user.getUsername(), user.getPasswordHashText(),request);
		HttpSession session=request.getSession();
		 session.setAttribute("USER" , userVO); 
		return userVO;
	}
}
