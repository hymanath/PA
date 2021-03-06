package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BloodBankDashBoardVO;
import com.itgrids.partyanalyst.dto.BloodBankVO;
import com.itgrids.partyanalyst.dto.DonationsInBloodBankVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IBloodBankService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class BloodBankAction extends ActionSupport implements ServletRequestAware{

	private static final Logger         		LOG = Logger.getLogger(BloodBankAction.class);

	private HttpServletRequest         			request;
	private HttpSession 						session;
	private IBloodBankService                   bloodBankService;
	private JSONObject							jObj;
	private String 								task;
	private List<IdNameVO>						idNameList;
	
	private List<BloodBankVO> bloodBankVOList;
	private BloodBankVO bloodBankVO;
	private BloodBankDashBoardVO bloodBankDashBoardVO;
	private Long count;
	private List<BloodBankDashBoardVO> bloodBankDashBoardvoList = new ArrayList<BloodBankDashBoardVO>();
	private DonationsInBloodBankVO donationsInBloodBankVO;
	private ResultStatus resultStatus;
	private EntitlementsHelper 					entitlementsHelper;
	
	private List<IdNameVO> idNameVOList;
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	public DonationsInBloodBankVO getDonationsInBloodBankVO() {
		return donationsInBloodBankVO;
	}
	public void setDonationsInBloodBankVO(
			DonationsInBloodBankVO donationsInBloodBankVO) {
		this.donationsInBloodBankVO = donationsInBloodBankVO;
	}
	public List<BloodBankDashBoardVO> getBloodBankDashBoardvoList() {
		return bloodBankDashBoardvoList;
	}
	public void setBloodBankDashBoardvoList(
			List<BloodBankDashBoardVO> bloodBankDashBoardvoList) {
		this.bloodBankDashBoardvoList = bloodBankDashBoardvoList;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public BloodBankDashBoardVO getBloodBankDashBoardVO() {
		return bloodBankDashBoardVO;
	}
	public void setBloodBankDashBoardVO(BloodBankDashBoardVO bloodBankDashBoardVO) {
		this.bloodBankDashBoardVO = bloodBankDashBoardVO;
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
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public IBloodBankService getBloodBankService() {
		return bloodBankService;
	}
	public void setBloodBankService(IBloodBankService bloodBankService) {
		this.bloodBankService = bloodBankService;
	}
	
	public String bloodBankRegistration(){
		
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		boolean noaccess = false;
		 List<String> entitlements = null;
			if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
				entitlements = regVO.getEntitlements();
				if(!(entitlements.contains("BLOOD_BANK_REGISTRATION_ENTITLEMENT".trim()) || entitlements.contains("BLOOD_BANK_REGISTRATION_ADMIN_ENTITLEMENT".trim()))){
					noaccess = true ;
				}
			
		/*if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"BLOOD_BANK_REGISTRATION_ENTITLEMENT") || 
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"BLOOD_BANK_REGISTRATION_ADMIN_ENTITLEMENT"))){
			noaccess = true ;
		}*/
		
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
	}
		return Action.SUCCESS;
	}
	
	public String bloodBankDashBoard(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		boolean noaccess = false;
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("BLOOD_BANK_DASHBOARD_ENTITLEMENT".trim()) || entitlements.contains("BLOOD_BANK_DASHBOARD_ADMIN_ENTITLEMENT".trim()))){
				noaccess = true ;
			}
		
		
		/*if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"BLOOD_BANK_DASHBOARD_ENTITLEMENT") || 
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"BLOOD_BANK_DASHBOARD_ADMIN_ENTITLEMENT"))){
			noaccess = true ;
		}*/
		
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		}
		return Action.SUCCESS;
	}
	
	public String bloodBankBleading(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		boolean noaccess = false;
		 List<String> entitlements = null;
			if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
				entitlements = regVO.getEntitlements();
				if(!(entitlements.contains("BLOOD_BANK_BLEEDING_ENTITLEMENT".trim()) || entitlements.contains("BLOOD_BANK_BLEEDING_ADMIN_ENTITLEMENT".trim()))){
					noaccess = true ;
				}
			
		/*if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"BLOOD_BANK_BLEEDING_ENTITLEMENT") || 
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"BLOOD_BANK_BLEEDING_ADMIN_ENTITLEMENT"))){
			noaccess = true ;
		}*/
		
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
	}
		return Action.SUCCESS;
	}
	
	public List<BloodBankVO> getBloodBankVOList() {
		return bloodBankVOList;
	}
	public void setBloodBankVOList(List<BloodBankVO> bloodBankVOList) {
		this.bloodBankVOList = bloodBankVOList;
	}
	
	public BloodBankVO getBloodBankVO() {
		return bloodBankVO;
	}
	public void setBloodBankVO(BloodBankVO bloodBankVO) {
		this.bloodBankVO = bloodBankVO;
	}
	public List<IdNameVO> getIdNameList() {
		return idNameList;
	}
	public void setIdNameList(List<IdNameVO> idNameList) {
		this.idNameList = idNameList;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public List<IdNameVO> getIdNameVOList() {
		return idNameVOList;
	}
	public void setIdNameVOList(List<IdNameVO> idNameVOList) {
		this.idNameVOList = idNameVOList;
	}
	
	public String getOccuations(){
		try{
			bloodBankVOList=bloodBankService.getOccupationList();
		}catch(Exception e){
		 LOG.info("Error raised at getOccuations() in BloodBankAction",e);	
		}
		return Action.SUCCESS;
	}
	public String getEducationQualifications(){
		try {
         	bloodBankVOList=bloodBankService.getEducationalQualificationsList();
		} catch (Exception e) {
			 LOG.info("Error raised at getEducationsQualifications() in BloodBankAction",e);
		}
		return Action.SUCCESS;
	}
	public String getCadreDetails(){
		
		try {
			jObj= new JSONObject(getTask());	
			String searchType =jObj.getString("searchType");
			String searchValue =jObj.getString("searchValue");
			Long enrollmentId = jObj.getLong("enrollmentId");
			
			bloodBankVO=bloodBankService.getCadreDetails(enrollmentId,searchType.trim(),searchValue.trim());
		} catch (Exception e) {
			 LOG.info("Error raised at getCadreDetails() in BloodBankAction",e);
		}
		return Action.SUCCESS;
	}
	public String getBloodDonarsSummary(){
		try {
			jObj = new JSONObject(getTask());
			bloodBankDashBoardVO = bloodBankService.getBloodDonarsCountsSummary(jObj.getLong("campId"));
		} catch (Exception e) {
			LOG.error("Exception eaised at  getBloodDonarsSummary()", e);
		}
		return Action.SUCCESS;
	}
	public String getAcceptanceStatus(){
		try {
			idNameList = bloodBankService.getAcceptanceStatus();
		} catch (Exception e) {
			LOG.error("Exception eaised at  getAcceptanceStatus()", e);
		}
		return Action.SUCCESS;
	}
	public String getBloodBagType(){
		try {
			idNameList = bloodBankService.getBloodBagType();
		} catch (Exception e) {
			LOG.error("Exception eaised at  getBloodBagType()", e);
		}
		return Action.SUCCESS;
	}
	public String getBloodBagQuantity(){
		try {
			idNameList = bloodBankService.getBloodBagQuantity();
		} catch (Exception e) {
			LOG.error("Exception eaised at  getBloodBagQuantity()", e);
		}
		return Action.SUCCESS;
	}
	public String gettotalCollectedBloodDetails(){
		try {
			jObj= new JSONObject(getTask());
			Long campId = jObj.getLong("bloodBankCampId");
			
			bloodBankDashBoardVO = bloodBankService.gettotalCollectedBloodDetails(campId);
		} catch (Exception e) {
			LOG.error("Exception eaised at  gettotalCollectedBloodDetails", e);
		}
		return Action.SUCCESS;
	}
	
	public String getBloodDonatedOtherThanBloodBank(){
		try {
			jObj= new JSONObject(getTask());
			Long campId = jObj.getLong("bloodBankCampId");
			count = bloodBankService.getBloodDonatedOtherThanBloodBank(campId);
		} catch (Exception e) {
			LOG.error("Exception eaised at  getBloodDonatedOtherThanBloodBank", e);
		}
		return Action.SUCCESS;
	}
	
	public String getBloodDonorInEmergency(){
		try {
			jObj= new JSONObject(getTask());
			Long campId = jObj.getLong("bloodBankCampId");
			count = bloodBankService.getBloodDonorInEmergency(campId);
		} catch (Exception e) {
			LOG.error("Exception eaised at  getBloodDonorInEmergency", e);
		}
		return Action.SUCCESS;
	}
	
	public String getCalledForDonationCount(){
		try {
			jObj= new JSONObject(getTask());
			Long campId = jObj.getLong("bloodBankCampId");
			count = bloodBankService.getCalledForDonationCount(campId);
		} catch (Exception e) {
			LOG.error("Exception eaised at  getCalledForDonationCount", e);
		}
		return Action.SUCCESS;
	}
	
	public String gettotalCollectedBloodBagsInfo(){
		try {
			jObj= new JSONObject(getTask());
			Long campId = jObj.getLong("bloodBankCampId");
			
			bloodBankDashBoardVO = bloodBankService.gettotalCollectedBloodBagsInfo(campId);
		} catch (Exception e) {
			LOG.error("Exception eaised at  gettotalCollectedBloodBagsInfo", e);
		}
		return Action.SUCCESS;
	}
	
	public String getBloodDonorDetailsByAgeGroupingInfo(){
		try {
			jObj= new JSONObject(getTask());
			Long campId = jObj.getLong("bloodBankCampId");
			
			bloodBankDashBoardvoList = bloodBankService.getBloodDonorDetailsByAgeGroupingInfo(campId);
		} catch (Exception e) {
			LOG.error("Exception eaised at  getBloodDonorDetailsByAgeGroupingInfo", e);
		}
		return Action.SUCCESS;
	}
	
	public String getBleedingCadreDetails(){
		List<Long> statusId = new ArrayList<Long>();
		try {
			jObj= new JSONObject(getTask());
			Long campId = jObj.getLong("campId");
			JSONArray statusIds = jObj.getJSONArray("statusIdList");
			for(int i=0;i<statusIds.length();i++){
					JSONObject jobj1 = new JSONObject(statusIds.get(i).toString());
					statusId.add(jobj1.getLong("id"));
				}
			bloodBankVOList = bloodBankService.getBleedingCadreDetails(statusId,campId,jObj.getString("dates"));
		} catch (Exception e) {
			LOG.error("Exception eaised at  getBleedingCadreDetails() method of BloodBankAction", e);
		}
		return Action.SUCCESS;
	}
	public String saveCadreDetails(){
		try{
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			jObj = new JSONObject(getTask());
	    	JSONArray cadreDtlsArr = jObj.getJSONArray("cadreDtlsArr");
	    	BloodBankVO bankVO=new BloodBankVO();
	    	if(cadreDtlsArr!=null && cadreDtlsArr.length()>0){
	    			bankVO.setName(cadreDtlsArr.getString(0));
	    			bankVO.setRelativeName(cadreDtlsArr.getString(1));
	    			bankVO.setSex(cadreDtlsArr.getString(2));
	    			String age=cadreDtlsArr.getString(3);
	    			if(age!=null && !age.trim().isEmpty() && age.trim().length()!=0){
	    				bankVO.setAge(Long.valueOf(age.trim()));
	    		  }
	    			if(cadreDtlsArr.getString(4) !=null && !cadreDtlsArr.getString(4).isEmpty()){
	    				bankVO.setDob(cadreDtlsArr.getString(4));
	    			}	    			
	    			if(cadreDtlsArr.getString(5) !=null && !cadreDtlsArr.getString(5).isEmpty() && cadreDtlsArr.getString(5) !="0"){
	    				bankVO.setMarried(cadreDtlsArr.getString(5));
	    			}	    				    		
	    			bankVO.setAddress(cadreDtlsArr.getString(6));
	    			bankVO.setMobile(cadreDtlsArr.getString(7));
	    			bankVO.setEmail(cadreDtlsArr.getString(8));
	    			bankVO.setEducationId(Long.valueOf(cadreDtlsArr.getString(9)));
	    			bankVO.setOccupationId(Long.valueOf(cadreDtlsArr.getString(10)));
	    			String donationsInBloodBankCount=cadreDtlsArr.getString(11);
	    			if(donationsInBloodBankCount!=null && !donationsInBloodBankCount.trim().isEmpty() && donationsInBloodBankCount.trim().length()!=0){
	    				bankVO.setDonationsInBloodBank(Long.valueOf(donationsInBloodBankCount.trim()));
	    			}
	    			bankVO.setDonationsInOtherPlaces(cadreDtlsArr.getString(12));
	    			bankVO.setLastDonation(cadreDtlsArr.getString(13));
	    			bankVO.setBloodComponentId(Long.valueOf(cadreDtlsArr.getString(14)));
	    			bankVO.setWillingEmergencyDonation(cadreDtlsArr.getString(15));
	    			bankVO.setWillingToCallDonation(cadreDtlsArr.getString(16));
	    			bankVO.setRemarks(cadreDtlsArr.getString(17));
	    			bankVO.setMembershipNo(cadreDtlsArr.getString(18));
	    			bankVO.setId((Long.valueOf(cadreDtlsArr.getString(19))));//campId
	   	}
	   	resultStatus=bloodBankService.saveBloodBankCadreDetails(bankVO,regVO.getRegistrationID());
		}catch(Exception e){
			LOG.info("Error raised at saveCadreDetails() in BloodBankAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getBloodComponents(){
		try{
			bloodBankVOList=bloodBankService.getBloodComponentList();
		}catch (Exception e) {
			 LOG.info("Error raised at getEducationsQualifications() in BloodBankAction",e);
		}
		return Action.SUCCESS;
	}
	public String getNumberOfTimesCollectedBlood(){
		try {
			jObj= new JSONObject(getTask());
			Long campId = jObj.getLong("bloodBankCampId");
			
			donationsInBloodBankVO = bloodBankService.getNumberOfTimesCollectedBlood(campId);
		} catch (Exception e) {
			LOG.error("Exception eaised at  getNumberOfTimesCollectedBlood", e);
		}
		return Action.SUCCESS;
	}
	public String saveBleedingDetails(){
		List<Long> statusId = new ArrayList<Long>();
		BloodBankVO bloodBankVO = new BloodBankVO();
		try {
			jObj= new JSONObject(getTask());
			String membershipNo = jObj.getString("membershipNo");
			Long status = jObj.getLong("status");
			String bloodBagNo = jObj.getString("bloodBagNo");
			Long bloodBagTypeId = jObj.getLong("bloodBagTypeId");
			Long bloodBagQuantityId = jObj.getLong("bloodBagQuantityId");
			Long quantityId = jObj.getLong("quantityId");
			String registrationNo=jObj.getString("registrationNo");
			String remarks=jObj.getString("remarks");
			
			bloodBankVO.setStatusId(status);
			bloodBankVO.setBagNo(bloodBagNo);
			bloodBankVO.setBagTypeId(bloodBagTypeId);
			bloodBankVO.setBloodBankQuantityId(bloodBagQuantityId);
			bloodBankVO.setQuantity(quantityId);
			bloodBankVO.setMembershipNo(membershipNo);
			bloodBankVO.setRegistrationNo(registrationNo);
			bloodBankVO.setRemarks(remarks);
			resultStatus = bloodBankService.saveBleedingDetails(bloodBankVO);
		} catch (Exception e) {
			LOG.error("Exception eaised at  saveBleedingDetails() method of BloodBankAction", e);
		}
		return Action.SUCCESS;
	}
	public String updatePrintstatus(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO == null){
				return "error";
			}
			jObj = new JSONObject(getTask());
			resultStatus=bloodBankService.updatePrintstatus(jObj.getLong("bloodBankId"),regVO.getRegistrationID());
		}catch (Exception e) {
			 LOG.info("Error raised at updatePrintstatus() in BloodBankAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getDistrictWiseBloodDonorCounts(){
		try {
			jObj= new JSONObject(getTask());
			Long campId = jObj.getLong("bloodBankCampId");
			Long stateId = jObj.getLong("stateId");
			String type = jObj.getString("type");
			
			bloodBankDashBoardVO = bloodBankService.getDistrictWiseBloodDonorCounts(campId,stateId,type);
		} catch (Exception e) {
			LOG.error("Exception eaised at  getDistrictWiseBloodDonorCounts", e);
		}
		return Action.SUCCESS;
	}
	
	public String getPrePopulateTehDataDetails(){
		try{
			 LOG.info("Error raised at getPrePopulateTehDataDetails() in BloodBankAction");
			 
			jObj = new JSONObject(getTask());
			String searchType = jObj.getString("searchType");
			
			bloodBankVOList = bloodBankService.getPrePopulateDataDetails(searchType,jObj.getLong("statusId"),jObj.getString("date"),jObj.getLong("campId"));
		}catch (Exception e) {
			 LOG.info("Error raised at getPrePopulateTehDataDetails() in BloodBankAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getBloodBankCampDates(){
		try {
			
			jObj = new JSONObject(getTask());
			idNameVOList = bloodBankService.getBloodBankCampDates(jObj.getLong("campId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getBloodBankCampDates", e);
		}
		return Action.SUCCESS;
	}
}

