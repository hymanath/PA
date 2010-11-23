/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 15, 2010
 */
package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.LocalUserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * 
 * @author Kamalakar Dandu
 * 
 *
 */

public class SaveLocalGroupAction extends ActionSupport implements ServletRequestAware, ServletContextAware{

	private static final long serialVersionUID = 3159320857101082076L;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	
	private String registrationId;
	private String resultStatus;
	private Long localUserGroupId;
	
	private String savedStatusMsg = "";
	private Boolean savedStatus = false;
	private LocalUserGroupDetailsVO localUserGroupDetailsVO = new LocalUserGroupDetailsVO();
	private IInfluencingPeopleService influencingPeopleService;
	private String groupScopeId;
	private String scopeState,scopeDistrict,scopeConstituency;
	private String scopeMandal,scopeVillage,scopeBooth;
	
		
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
		session = request.getSession();
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public Long getGroupCategoryId() {
		return this.localUserGroupDetailsVO.getGroupCategoryId();
	}
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Please select Group Category")
	public void setGroupCategoryId(Long groupCategoryId) {
		this.localUserGroupDetailsVO.setGroupCategoryId(groupCategoryId);
	}
	
	public String getLocalUserGroupName() {
		return this.localUserGroupDetailsVO.getLocalUserGroupName();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Group Name is Mandatory")
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[a-zA-Z ]+$", message = "Group Name should not contain special characters and numbers", shortCircuit = true)
	public void setLocalUserGroupName(String localGroupName) {
		this.localUserGroupDetailsVO.setLocalUserGroupName(localGroupName.trim());
	}
	
	public String getGroupDesc() {
		return this.localUserGroupDetailsVO.getGroupDesc();
	}
	public void setGroupDesc(String localGroupDesc) {
		this.localUserGroupDetailsVO.setGroupDesc(localGroupDesc.trim());
	}
	public String getGroupScopeId() {
		return this.localUserGroupDetailsVO.getGroupScopeId();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select Group Scope",shortCircuit=true)
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Group Scope Selection")
	public void setGroupScopeId(String groupScopeId) {
		this.groupScopeId = groupScopeId;
		this.localUserGroupDetailsVO.setGroupScopeId(groupScopeId);
	}
	public String getGroupScopeValueId() {
		return this.localUserGroupDetailsVO.getGroupScopeValueId();
	}
	
	public void setGroupScopeValueId(String groupScopeValueId) {
		this.localUserGroupDetailsVO.setGroupScopeValueId(groupScopeValueId);
	}
	
	public LocalUserGroupDetailsVO getLocalUserGroupDetailsVO() {
		return localUserGroupDetailsVO;
	}
	public void setLocalUserGroupDetailsVO(
			LocalUserGroupDetailsVO localUserGroupDetailsVO) {
		this.localUserGroupDetailsVO = localUserGroupDetailsVO;
	}
	public String getSavedStatusMsg() {
		return savedStatusMsg;
	}
	public void setSavedStatusMsg(String savedStatusMsg) {
		this.savedStatusMsg = savedStatusMsg;
	}
	public Boolean getSavedStatus() {
		return savedStatus;
	}
	public void setSavedStatus(Boolean savedStatus) {
		this.savedStatus = savedStatus;
	}
	public String getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public Long getLocalUserGroupId() {
		return localUserGroupDetailsVO.getLocalUserGroupId();
	}
	public void setLocalUserGroupId(Long localUserGroupId) {
		this.localUserGroupDetailsVO.setLocalUserGroupId(localUserGroupId);
	}
	public IInfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}
	public void setInfluencingPeopleService(
			IInfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		session = request.getSession();
		
	}
	public void setServletContext(ServletContext context) {
		this.context =	context;
	}
	
	public String getState() {
		return this.localUserGroupDetailsVO.getState();
	}
	
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid State Selection")
	public void setState(String state) {
		this.localUserGroupDetailsVO.setState(state);
	}
	
	public String getDistrict() {
		return localUserGroupDetailsVO.getDistrict();
	}
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid District Selection")
	public void setDistrict(String district) {
		this.localUserGroupDetailsVO.setDistrict(district);
	}
	
	public String getConstituency() {
		return localUserGroupDetailsVO.getConstituency();
	}
	
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Constituency Selection")
	public void setConstituency(String constituency) {
		this.localUserGroupDetailsVO.setConstituency(constituency);
	}
	
	public String getMandal() {
		return localUserGroupDetailsVO.getMandal();
	}
	
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Tehsil/Muncipality Selection")
	public void setMandal(String mandal) {
		this.localUserGroupDetailsVO.setMandal(mandal);
	}
	
	public String getVillageOrWard() {
		return localUserGroupDetailsVO.getVillageOrWard();
	}
	
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid ward or hamlet selection")
	public void setVillageOrWard(String villageOrWard) {
		this.localUserGroupDetailsVO.setVillageOrWard(villageOrWard);
	}
	
	public String getBooth() {
		return localUserGroupDetailsVO.getBooth();
	}
	public void setBooth(String booth) {
		this.localUserGroupDetailsVO.setBooth(booth);
	}
	
	public String getHouseNo() {
		return localUserGroupDetailsVO.getHouseNo();
	}
	public void setHouseNo(String houseNo) {
		this.localUserGroupDetailsVO.setHouseNo(houseNo.trim());
	}
	public String getStreetName() {
		return localUserGroupDetailsVO.getStreetName();
	}
	public void setStreetName(String streetName) {
		this.localUserGroupDetailsVO.setStreetName(streetName.trim());
	}
	public String getPincode() {
		return localUserGroupDetailsVO.getPincode();
	}
	
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "[0-9][0-9][0-9][0-9][0-9][0-9]", message = "Pin Code should contain digits ", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Pincode length should be 6 digits only", minLength = "6", maxLength = "6")
	public void setPincode(String pincode) {
		this.localUserGroupDetailsVO.setPincode(pincode);
	}
	public String getWindowTask() {
		return localUserGroupDetailsVO.getWindowTask();
	}
	public void setWindowTask(String windowTask) {
		this.localUserGroupDetailsVO.setWindowTask(windowTask);
	}
	
	public void setScopeState(String scopeState) {
		this.scopeState = scopeState;
	}
	public void setScopeDistrict(String scopeDistrict) {
		this.scopeDistrict = scopeDistrict;
	}
	public void setScopeConstituency(String scopeConstituency) {
		this.scopeConstituency = scopeConstituency;
	}
	public void setScopeMandal(String scopeMandal) {
		this.scopeMandal = scopeMandal;
	}
	public void setScopeVillage(String scopeVillage) {
		this.scopeVillage = scopeVillage;
	}
	public void setScopeBooth(String scopeBooth) {
		this.scopeBooth = scopeBooth;
	}
	public String execute() throws Exception{
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		localUserGroupDetailsVO.setRegistrationId(regVO.getRegistrationID().toString());
			
		if("2".equalsIgnoreCase(localUserGroupDetailsVO.getGroupScopeId()))
		{
			localUserGroupDetailsVO.setGroupScopeRange(IConstants.STATE_LEVEL);
			localUserGroupDetailsVO.setGroupScopeValueId(scopeState);
			
		}
		
		else if("3".equalsIgnoreCase(localUserGroupDetailsVO.getGroupScopeId()))
		{
			localUserGroupDetailsVO.setGroupScopeRange(IConstants.DISTRICT_LEVEL);
			localUserGroupDetailsVO.setGroupScopeValueId(scopeDistrict);
		}
		
		else if("4".equalsIgnoreCase(localUserGroupDetailsVO.getGroupScopeId()))
		{
			localUserGroupDetailsVO.setGroupScopeRange(IConstants.CONSTITUENCY_LEVEL);
			localUserGroupDetailsVO.setGroupScopeValueId(scopeConstituency);
		}
		
		else if("5".equalsIgnoreCase(localUserGroupDetailsVO.getGroupScopeId()))
		{
			localUserGroupDetailsVO.setGroupScopeRange(IConstants.MANDAL_LEVEL);
			localUserGroupDetailsVO.setGroupScopeValueId(scopeMandal.substring(1));
		}
		
		else if("6".equalsIgnoreCase(localUserGroupDetailsVO.getGroupScopeId()))
		{
			localUserGroupDetailsVO.setGroupScopeRange(IConstants.CENSUS_VILLAGE_LEVEL);
			localUserGroupDetailsVO.setGroupScopeValueId(scopeVillage.substring(1));
		}
		
		else if("7".equalsIgnoreCase(localUserGroupDetailsVO.getGroupScopeId()))
		{
			localUserGroupDetailsVO.setGroupScopeRange(IConstants.MUNCIPALITY_CORPORATION_LEVEL);
			localUserGroupDetailsVO.setGroupScopeValueId(scopeMandal.substring(1));
		}
		
		else if("8".equalsIgnoreCase(localUserGroupDetailsVO.getGroupScopeId()))
		{
			localUserGroupDetailsVO.setGroupScopeRange(IConstants.WARD);
			localUserGroupDetailsVO.setGroupScopeValueId(scopeVillage.substring(1));
		}
		
		else if("9".equalsIgnoreCase(localUserGroupDetailsVO.getGroupScopeId()))
		{
			localUserGroupDetailsVO.setGroupScopeRange(IConstants.BOOTH);
			localUserGroupDetailsVO.setGroupScopeValueId(scopeBooth);
		}
		
		localUserGroupDetailsVO = influencingPeopleService.saveLocalUserGroupDetailsTODB(localUserGroupDetailsVO);
		
		if(localUserGroupDetailsVO.getResultStatus().getExceptionEncountered() != null){
			resultStatus = "failure";
			savedStatusMsg = "Failed To ADD Group ..";
			savedStatus = false;
		}else{
			resultStatus = "success";
			savedStatusMsg = "Successfully Added ".concat(localUserGroupDetailsVO.getLocalUserGroupName()).concat(" ..");
			savedStatus = true;
		}
		
		return Action.SUCCESS;
	}
	

	public void validate()
	{
		boolean sstate = scopeState.equalsIgnoreCase("0");
		boolean sDistrict = sstate || scopeDistrict.equalsIgnoreCase("0");
		boolean sConstituency = sDistrict || scopeConstituency.equalsIgnoreCase("0");
		boolean sMandal = sConstituency || scopeMandal.equalsIgnoreCase("0");
		boolean sVillage = sMandal || scopeVillage.equalsIgnoreCase("0");
		boolean sBooth =  sMandal || scopeBooth.equalsIgnoreCase("0");
		
		
		if(groupScopeId.equalsIgnoreCase("2") && sstate)
		{
			addFieldError("groupScopeId","Please Select All Required Fields in Group Scope Details");
			
		}
		else if(groupScopeId.equalsIgnoreCase("3") && sDistrict)
		{
			addFieldError("groupScopeId","Please Select All Required Fields in Group Scope Details");
			
		}
		else if(groupScopeId.equalsIgnoreCase("4") && sConstituency)
		{
			addFieldError("groupScopeId","Please Select All Required Fields in Group Scope Details");
			
		}
		else if(groupScopeId.equalsIgnoreCase("5") && sMandal)
		{
			addFieldError("groupScopeId","Please Select All Required Fields in Group Scope Details");
			
		}
		else if(groupScopeId.equalsIgnoreCase("6") && sVillage)
		{
			addFieldError("groupScopeId","Please Select All Required Fields in Group Scope Details");
			
		}
		else if(groupScopeId.equalsIgnoreCase("7") && sMandal)
		{
			addFieldError("groupScopeId","Please Select All Required Fields in Group Scope Details");
			
		}
		else if(groupScopeId.equalsIgnoreCase("8") && sVillage)
		{
			addFieldError("groupScopeId","Please Select All Required Fields in Group Scope Details");
			
		}
		else if(groupScopeId.equalsIgnoreCase("9") && sBooth)
		{
			addFieldError("groupScopeId","Please Select All Required Fields in Group Scope Details");
			
		}
		
	}
}
