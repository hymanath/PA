package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.CasteDetailsVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TdpCadreFamilyDetailsVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ICandidateUpdationDetailsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
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
	private List<CasteDetailsVO> castesList = new ArrayList<CasteDetailsVO>();
	private EntitlementsHelper 					entitlementsHelper;
	
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	public List<CasteDetailsVO> getCastesList() {
		return castesList;
	}
	public void setCastesList(List<CasteDetailsVO> castesList) {
		this.castesList = castesList;
	}
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
			
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			boolean noaccess = false;
			if(regVO==null){
				return "input";
			}/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADRE_FAMILY_DETAILS_UPDATION")
					&& !entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADRE_FAMILY_DETAILS_UPDATION_GROUP")){
				noaccess = true ;
			}*/
			List<String> entitlements = null;
		    if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
		      entitlements = regVO.getEntitlements();
		      if(!entitlements.contains("CADRE_FAMILY_DETAILS_UPDATION") && !entitlements.contains("CADRE_FAMILY_DETAILS_UPDATION_GROUP")){
		    	  noaccess = true ;
		      }

			if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
				noaccess = false;
			}
			
			if(noaccess){
				return "error";
			}
			
			 educationList = candidateUpdationDetailsService.gettingEducationDetails();
			 occupationsList = staticDataService.getAllOccupations();
			 bloodGroups = cadreRegistrationService.getBloodGroups(); 
			 castesList =  cadreRegistrationService.getAllCastes();
			 
		}
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
				 
				 AddressVO addressDetails=new AddressVO();
				 
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
				 vo.setFacebookUrl(obj.getString("facebookUrl"));
				
				 addressDetails.setHouseNo(obj.getString("hNo") !=null ? obj.getString("hNo").toString() : null);
				 addressDetails.setStreet(obj.getString("street") !=null ? obj.getString("street").toString() : null);
				 addressDetails.setPinCodeStr(obj.getString("pincode") !=null ? obj.getString("pincode").toString() : null);
				 addressDetails.setStateId(obj.getLong("stateId"));
				 addressDetails.setDistrictId(obj.getLong("districtId"));
				 addressDetails.setConstituencyId(obj.getLong("constituencyId"));
				 addressDetails.setTehsilId(obj.getLong("mandalId"));
				 addressDetails.setPanchaytId(obj.getLong("panchayatId"));
				 addressDetails.setLandMarkStr(obj.getString("landMark") !=null ? obj.getString("landMark").toString() : null);
				 addressDetails.setLocalElectionBodyId(obj.getLong("localElectionBody"));
				 addressDetails.setWardId(obj.getLong("wardId"));
				 
				 vo.setAddressVo(addressDetails);
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
