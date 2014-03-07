package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AgeRangeVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IVotersInformationService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class VotersInformationAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	
    private String result;
	private ResultStatus rs;
	
    private IVotersInformationService votersInformationService;
	
	public void setServletRequest(HttpServletRequest request) {
	   this.request=request;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public IVotersInformationService getVotersInformationService() {
		return votersInformationService;
	}

	public void setVotersInformationService(IVotersInformationService votersInformationService) {
		this.votersInformationService = votersInformationService;
	}

	
	public String execute() throws Exception
	{
		return  "success";
		
	}
	
	public ResultStatus getRs() {
		return rs;
	}

	public void setRs(ResultStatus rs) {
		this.rs = rs;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String AjaxHandler()
	{
		String param;
		param = getTask();
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return ERROR;
		Long userID = user.getRegistrationID();
		if(user.getMainAccountId() != null){
			userID = user.getMainAccountId();
		}
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		if(jObj.getString("task").equalsIgnoreCase("gettingVoterDetails"))
		{
			try{
				
			    JSONArray ageRangeValuesArray = jObj.getJSONArray("ageRangeIds");
			       List<String> allAgeRanges = new ArrayList<String>();
			    for(int i=0; i<ageRangeValuesArray.length(); i++)
				{
					String ageRange = (ageRangeValuesArray.get(i).toString());
					allAgeRanges.add(ageRange);
				}
				
			    String path = IWebConstants.STATIC_CONTENT_FOLDER_URL ;
			    rs=votersInformationService.gettingVotersInfoService(jObj.getString("constituencyId"),jObj.getString("publicationId"),jObj.getString("ageOrCasteRadio"),allAgeRanges,Boolean.parseBoolean(jObj.getString("partialId")),path);
			   }
			catch(Exception e)
			{e.printStackTrace();}
		}
			
		return Action.SUCCESS;
	}

}
