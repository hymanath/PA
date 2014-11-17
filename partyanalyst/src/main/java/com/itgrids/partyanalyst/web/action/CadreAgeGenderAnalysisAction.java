package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class CadreAgeGenderAnalysisAction implements ServletRequestAware {
	
	private HttpServletRequest request;
	
	private ICadreDashBoardService cadreDashBoardService;
	private List<CadreRegisterInfo> result;
	private JSONObject jObj;
	private String task;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ICadreDashBoardService getCadreDashBoardService() {
		return cadreDashBoardService;
	}

	public void setCadreDashBoardService(
			ICadreDashBoardService cadreDashBoardService) {
		this.cadreDashBoardService = cadreDashBoardService;
	}

	public List<CadreRegisterInfo> getResult() {
		return result;
	}

	public void setResult(List<CadreRegisterInfo> result) {
		this.result = result;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
public String execute(){
		
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		
		if(noaccess){
			return "error";
		}
		return Action.SUCCESS;
	}
	

	public String getLocationWiseGenderAgeInfo(){
		try{
			
				
			jObj = new JSONObject(getTask());
		
			result =  cadreDashBoardService.getLocationWiseAgeRangeAndGenderCount(jObj.getString("type"),jObj.getLong("stateId"),jObj.getLong("constituencyId"));
		
		 }catch(Exception e){
			 
		  }
		return Action.SUCCESS;
	}
}
