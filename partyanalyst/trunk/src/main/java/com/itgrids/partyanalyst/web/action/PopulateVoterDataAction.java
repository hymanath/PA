package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PopulateVoterDataAction extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	private ConstituencyManagementVO constituencyManagementVO;
	private IVotersAnalysisService votersAnalysisService;
	private List<SelectOptionVO> constituencyList;
	private ResultStatus resultStatus;
	
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
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
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
		
		public String insertVotersDataToIntermediateTables()
		{
			try{
				jObj = new JSONObject(getTask());
				
			}catch (Exception e) {
				e.printStackTrace();
				Log.error("Exception Occured in insertVotersDataToIntermediateTables() Method, Exception - "+e);
			}
			HttpSession session = request.getSession();
			RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
			if(regVO == null)
				return null;
			
			resultStatus = votersAnalysisService.insertVotersDataInIntermediateTables(jObj.getLong("id"), jObj.getLong("publicationDateId"));
			return Action.SUCCESS;
		}
		
		public String deleteVotersDataFromIntermediateTables()
		{
			try{
				jObj = new JSONObject(getTask());
			}catch (Exception e) {
				e.printStackTrace();
				Log.error("Exception Occured in deleteVotersDataInIntermediateTables() Method, Exception - "+e);
			}
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
				return null;
			//resultStatus = votersAnalysisService.deleteVotersDataFromIntermediateTables(jObj.getLong("id"), jObj.getLong("publicationDateId"));
			resultStatus = votersAnalysisService.deleteVoterInfoFromIntermediateTablesByConstituencyId(jObj.getLong("id"), jObj.getLong("publicationDateId"));
					
			return Action.SUCCESS;
		}
		
		public String deleteVotersCastDataFromIntermediateTables()
		{
			try{
				jObj = new JSONObject(getTask());
			}catch (Exception e) {
				e.printStackTrace();
				Log.error("Exception Occured in deleteVotersCastDataFromIntermediateTables() Method, Exception - "+e);
			}
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
				return null;
			resultStatus = votersAnalysisService.deleteVotersCastDataFromIntermediateTables(jObj.getLong("id"),jObj.getLong("publicationDateId"));
			return Action.SUCCESS;
		}
		
		public String deleteVotersPartyDataFromIntermediateTables(){
			try{
				jObj = new JSONObject(getTask());
			}
			catch(Exception e)
			{
				Log.error("Exception Occured in deleteVotersDataInIntermediateTables() Method, Exception -"+e);
			}
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
				return null;
			resultStatus = votersAnalysisService.deleteVotersPartyDataFromIntermediateTables(jObj.getLong("id"),jObj.getLong("publicationDateId"));
			return Action.SUCCESS;
		}
}
