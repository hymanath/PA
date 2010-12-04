package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
public class UserAction extends ActionSupport implements ServletRequestAware {
	
	private HttpServletRequest request;
	private HttpSession session;
	private List<String> type = new ArrayList<String>();
	private List<String> gender = new ArrayList<String>();
	private List<SelectOptionVO> parties;
	private IStaticDataService staticDataService;
	private List<String> userType = new ArrayList<String>();
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		session = request.getSession();
	}
	
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
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

	public List<SelectOptionVO> getParties() {
		return parties;
	}
	public void setParties(List<SelectOptionVO> parties) {
		this.parties = parties;
	}	
	
	public List<String> getUserType() {
		return userType;
	}

	public void setUserType(List<String> userType) {
		this.userType = userType;
	}

	public String registration(){
		
		if(type.size()==0){
			type.add("COUNTRY");
			type.add("STATE");
			type.add("DISTRICT");
			type.add("MLA");
			type.add("MP");			
		}
		if(userType.size() == 0)
		{
			userType.add("Party");
			userType.add("Politician");			
		}
		if(gender.size() == 0){
			gender.add("Male");
			gender.add("Female");
		}		
		
		parties = staticDataService.getStaticParties();
		session = request.getSession();
		session.setAttribute("type", type);
		session.setAttribute("userType", userType);
		session.setAttribute("gender", gender);
		session.setAttribute("parties", parties);
		
		return Action.SUCCESS;
	}
	
}
