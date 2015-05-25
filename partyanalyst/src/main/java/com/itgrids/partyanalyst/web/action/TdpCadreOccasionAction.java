package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;



import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class TdpCadreOccasionAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private HttpSession session;
	private JSONObject jobj;
	private String 	   task;
	private ICadreRegistrationService cadreRegistrationService ;
	
	
	
	
	public ICadreRegistrationService getCadreRegistrationService() {
		return cadreRegistrationService;
	}
	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public JSONObject getJobj() {
		return jobj;
	}
	public void setJobj(JSONObject jobj) {
		this.jobj = jobj;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	
	 public String execute()
	 {
		try{
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	 }
	 
	 @SuppressWarnings("deprecation")
	public String updateCadreFamilyInfo()
	 {
		 try{
			jobj = new JSONObject(getTask());
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			List<TdpCadreFamilyDetailsVO> inputList = new ArrayList<TdpCadreFamilyDetailsVO>();
			 JSONArray arr = jobj.getJSONArray("dataArr");
			 if(arr != null && arr.length() > 0)
			 {
				 for(int i=0;i<arr.length() ;i++)
				 {
				 JSONObject obj = arr.getJSONObject(i);
				 TdpCadreFamilyDetailsVO vo = new TdpCadreFamilyDetailsVO();
				 //vo.set
				 }
			 }
		   cadreRegistrationService.updateCadreFamilyInfo(inputList,regVo.getRegistrationID());
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	 }
	
}
