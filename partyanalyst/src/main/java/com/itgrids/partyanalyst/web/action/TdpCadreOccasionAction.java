package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;



import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TdpCadreFamilyDetailsVO;

import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ICandidateUpdationDetailsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class TdpCadreOccasionAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private HttpSession session;
	private JSONObject jobj;
	private String 	   task;
	private ICadreRegistrationService cadreRegistrationService ;
	private List<GenericVO> 	educationList;
	private List<SelectOptionVO> bloodGroups,occupationsList;
	private ICandidateUpdationDetailsService candidateUpdationDetailsService;
	private IStaticDataService staticDataService;
	private ResultStatus result;
	
	
	
	
	public ResultStatus getResult() {
		return result;
	}
	public void setResult(ResultStatus result) {
		this.result = result;
	}
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public List<GenericVO> getEducationList() {
		return educationList;
	}
	public void setEducationList(List<GenericVO> educationList) {
		this.educationList = educationList;
	}
	public List<SelectOptionVO> getBloodGroups() {
		return bloodGroups;
	}
	public void setBloodGroups(List<SelectOptionVO> bloodGroups) {
		this.bloodGroups = bloodGroups;
	}
	public List<SelectOptionVO> getOccupationsList() {
		return occupationsList;
	}
	public void setOccupationsList(List<SelectOptionVO> occupationsList) {
		this.occupationsList = occupationsList;
	}
	public ICandidateUpdationDetailsService getCandidateUpdationDetailsService() {
		return candidateUpdationDetailsService;
	}
	public void setCandidateUpdationDetailsService(
			ICandidateUpdationDetailsService candidateUpdationDetailsService) {
		this.candidateUpdationDetailsService = candidateUpdationDetailsService;
	}
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
			 educationList = candidateUpdationDetailsService.gettingEducationDetails();
			 occupationsList = staticDataService.getAllOccupations();
			 bloodGroups = cadreRegistrationService.getBloodGroups();
			 
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
				 vo.setAge(obj.getLong("age"));
				// vo.setBloodGroupId(obj.getLong("bloodGroup"));
				 vo.setCasteStateId(obj.getLong("casteStateId"));
				 vo.setDob(obj.getString("dob"));
				 vo.setName(obj.getString("name"));
				 vo.setEducationId(obj.getLong("education"));
				 vo.setEmail(obj.getString("email"));
				 vo.setGender(obj.getString("gender"));
				 vo.setMarriageDay(obj.getString("marriageDay"));
				 vo.setWhatsappStatus(obj.getString("whatsappStatus"));
				 vo.setMobileNo(obj.getString("mobileNo"));
				 vo.setPartyMemberSince(obj.getString("partyMemberSince"));
				 vo.setVotercardNo(obj.getString("voterId"));
				 vo.setRelationId(obj.getLong("relationId"));
				 vo.setTdpCadreId(obj.getLong("tdpCadreId"));
				 vo.setOccupationId(obj.getLong("occupationId"));
				 inputList.add(vo);
				 
				 }
			 }
			 result =  cadreRegistrationService.updateCadreFamilyInfo(inputList,regVo.getRegistrationID());
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	 }
	 
	 
	   
}
