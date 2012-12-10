package com.itgrids.partyanalyst.web.social.action;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

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
	private List<SocialNetworkVO> parliamentInfoList;
	private Long constituencyId;
	private Long stateId;
	private Long districtId;
	private String electionType;
	private String status;
	JSONObject jObj = null;
	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String task = null;
	
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	 
	public ResultStatus getResults() {
		return results;
	}

	public void setResults(ResultStatus results) {
		this.results = results;
	}

	
	
	public List<SocialNetworkVO> getParliamentInfoList() {
		return parliamentInfoList;
	}

	public void setParliamentInfoList(List<SocialNetworkVO> parliamentInfoList) {
		this.parliamentInfoList = parliamentInfoList;
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
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public ISocialService getSocialService() {
		return socialService;
	}

	public void setSocialService(ISocialService socialService) {
		this.socialService = socialService;
	}

	public String execute() {
		
		//results = socialService.saveSocialInformation(socialVO);
		//parliamentInfoList=socialService.getParliamentInfo(constituencyId,electionType,rank);
		return Action.SUCCESS;
	}
	
	
	public String ajaxCallHandler()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(jObj.getString("task").equalsIgnoreCase("getParliamentInfo"))
			{
			Long stateId=jObj.getLong("stateId");
			Long districtId = jObj.getLong("districtId");
			Long constituencyId=jObj.getLong("constituencyId");
			String electionType=jObj.getString("electionType");
			String status=jObj.getString("status");
			parliamentInfoList = socialService.getParliamentInfo(electionType,stateId,districtId,constituencyId,status);
			
			}
		
		
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
