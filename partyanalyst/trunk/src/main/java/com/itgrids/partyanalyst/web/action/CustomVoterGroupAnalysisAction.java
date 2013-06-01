package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.service.impl.CustomVoterGroupAnalysisService;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.service.ICustomVoterGroupAnalysisService;
import com.opensymphony.xwork2.ActionSupport;

public class CustomVoterGroupAnalysisAction extends ActionSupport implements ServletRequestAware{

	private HttpSession session;
	
	private HttpServletRequest request;
	
	private String task;
	
	JSONObject jobj;
	private static final Logger log = Logger.getLogger(VotersAnalysisAction.class);
	private ICustomVoterGroupAnalysisService customVoterGroupAnalysisService;
	private VotersDetailsVO votersDetailsVO;
	private List<VoterCastInfoVO> castInfoVOsList;
	private Long customVoterGroupId;
	
	@Override
	public void setServletRequest(HttpServletRequest arg) {
		this.request = arg;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getJobj() {
		return jobj;
	}

	public void setJobj(JSONObject jobj) {
		this.jobj = jobj;
	}
	
	public ICustomVoterGroupAnalysisService getCustomVoterGroupAnalysisService() {
		return customVoterGroupAnalysisService;
	}

	public void setCustomVoterGroupAnalysisService(
			ICustomVoterGroupAnalysisService customVoterGroupAnalysisService) {
		this.customVoterGroupAnalysisService = customVoterGroupAnalysisService;
	}

	public VotersDetailsVO getVotersDetailsVO() {
		return votersDetailsVO;
	}

	public void setVotersDetailsVO(VotersDetailsVO votersDetailsVO) {
		this.votersDetailsVO = votersDetailsVO;
	}

	public List<VoterCastInfoVO> getCastInfoVOsList() {
		return castInfoVOsList;
	}

	public void setCastInfoVOsList(List<VoterCastInfoVO> castInfoVOsList) {
		this.castInfoVOsList = castInfoVOsList;
	}
	
	public Long getCustomVoterGroupId() {
		return customVoterGroupId;
	}

	public void setCustomVoterGroupId(Long customVoterGroupId) {
		this.customVoterGroupId = customVoterGroupId;
	}

	public String execute()
	{
		return ActionSupport.SUCCESS;
	}
	
	public String ajaxHandler()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
		 return ERROR;
		Long userId = user.getRegistrationID();
		try{
		  jobj = new JSONObject(getTask());
		}catch (Exception e) {
		  e.printStackTrace();
		  Log.error("Exception Occured in ajaxHandler() method, Exception - "+e);
		}
		if(jobj.getString("task").equalsIgnoreCase("getCasteWiseCustomVotersCount"))
			castInfoVOsList = customVoterGroupAnalysisService.getCasteWiseCustomVotersCount(jobj.getLong("customVoterGroupId"),userId);
		
		return ActionSupport.SUCCESS;
	}
	
	
	public String getAgeWiseCustomVotersInGroup(){
		Long userId =null;
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		else  
			userId= user.getRegistrationID();
		String param;
		param = getTask();
		
		votersDetailsVO=new VotersDetailsVO();
		try{
			jobj = new JSONObject(param);
			Long customGroupId = Long.parseLong(jobj.getString("customGroupId"));
			Long publicationDateId=Long.parseLong(jobj.getString("publicationDateId"));
			votersDetailsVO=customVoterGroupAnalysisService.getAgeWiseCustomVotersInGroup(userId,customGroupId,publicationDateId);
			
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getAgeWiseCustomVotersInGroup() Method,Exception is- ",e);
		}
		
		return SUCCESS;
	}

}
