package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.opensymphony.xwork2.ActionSupport;

public class PopulateVoterDataAction extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	private ConstituencyManagementVO constituencyManagementVO;
	private IVotersAnalysisService votersAnalysisService;
	private List<SelectOptionVO> constituencyList;
	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}
	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}

	private HttpSession session;
	private String task;
	JSONObject jObj;
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
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	
	@Override
	
		public void setServletRequest(HttpServletRequest arg0) {
			
			this.request=arg0;
		}
		public ConstituencyManagementVO getConstituencyManagementVO() {
			return constituencyManagementVO;
		}
		public void setConstituencyManagementVO(
				ConstituencyManagementVO constituencyManagementVO) {
			this.constituencyManagementVO = constituencyManagementVO;
		}
		public IVotersAnalysisService getVotersAnalysisService() {
			return votersAnalysisService;
		}
		public void setVotersAnalysisService(
				IVotersAnalysisService votersAnalysisService) {
			this.votersAnalysisService = votersAnalysisService;
		}

		public String execute()
		{
			
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			return ERROR;
			constituencyList = votersAnalysisService.getConstituenciesFromBoothPublicationVoter();
			constituencyList.add(0,new SelectOptionVO(0L,"Select Constituency"));
			return SUCCESS;
		}
}
