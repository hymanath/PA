package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.service.impl.UserService;
public class UserAction extends ActionSupport implements ServletRequestAware {
	
	private HttpServletRequest request;
	private UserService userService;
	private List<String> type = new ArrayList<String>();
	private List<String> gender = new ArrayList<String>();
	
	public void setUserService(UserService userService){
		this.userService  = userService;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}
	
	public List<String> getGender() {
		return gender;
	}
	public void setGender(List<String> gender) {
		this.gender = gender;
	}

	
	public String registration(){
		if(type.size()==0){
			type.add("COUNTRY");
			type.add("STATE");
			type.add("DISTRICT");
			type.add("MLA");
			type.add("MP");			
		}
		
		if(gender.size() == 0){
			gender.add("Male");
			gender.add("Female");
		}		
		
		return Action.SUCCESS;
	}
	
}
