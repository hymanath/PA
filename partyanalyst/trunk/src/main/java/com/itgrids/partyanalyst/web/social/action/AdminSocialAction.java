package com.itgrids.partyanalyst.web.social.action;

import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.AnnouncementVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SocialNetworkVO;
import com.itgrids.partyanalyst.social.service.ISocialService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AdminSocialAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware, ServletContextAware  {

	private  String category;
	private  String name;
	private  String profileID;
	private  String accountType;
	private SocialNetworkVO socialVO; 
	private ResultStatus results;
	private ISocialService socialService;
	
	public ResultStatus getResults() {
		return results;
	}

	public void setResults(ResultStatus results) {
		this.results = results;
	}

	
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
		this.socialVO.setCategory(category);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.socialVO.setName(name);
	}

	public String getProfileID() {
		return profileID;
	}

	public void setProfileID(String profileID) {
		this.profileID = profileID;
		this.socialVO.setProfileId(profileID);
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
		this.socialVO.setAccountType(accountType);
	}

	public SocialNetworkVO getSocialVO() {
		return socialVO;
	}

	public void setSocialVO(SocialNetworkVO socialVO) {
		this.socialVO = socialVO;
	}
	
	

	public ISocialService getSocialService() {
		return socialService;
	}

	public void setSocialService(ISocialService socialService) {
		this.socialService = socialService;
	}

	public String execute() {
		
		results = socialService.saveSocialInformation(socialVO);
		
		return Action.SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
}
