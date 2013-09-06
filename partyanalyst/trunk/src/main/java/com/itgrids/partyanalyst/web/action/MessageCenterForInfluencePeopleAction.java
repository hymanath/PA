package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SMSSearchCriteriaVO;
import com.itgrids.partyanalyst.service.IVoiceSmsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MessageCenterForInfluencePeopleAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String task;
	JSONObject jObj;
	private ResultStatus resultStatus;
	private HttpSession session;
	private HttpServletRequest request;
	private IVoiceSmsService voiceSmsService;
	private String startIndex;
	private String dir;
	private String sort;
	private String results;
	
	private String isAgeSelected;
	private String isCasteSelected;
	private String isFamilySelected;
	private String isNameSelected;
	private String isGenderSelected;
	private String isHouseNoSelected;
	
	private String startAge;
	private String  endAge;
	private String houseNo;
	private String name;
	private String casteIds;
	private String locationType;
	private String locationValue;
	private String gender;
	private Long parentLocationId;
	private SMSSearchCriteriaVO smsPeopleList; 	

	
	public Long getParentLocationId() {
		return parentLocationId;
	}
	public void setParentLocationId(Long parentLocationId) {
		this.parentLocationId = parentLocationId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public SMSSearchCriteriaVO getSmsPeopleList() {
		return smsPeopleList;
	}
	public void setSmsPeopleList(SMSSearchCriteriaVO smsPeopleList) {
		this.smsPeopleList = smsPeopleList;
	}
	
	public String getIsAgeSelected() {
		return isAgeSelected;
	}

	public void setIsAgeSelected(String isAgeSelected) {
		this.isAgeSelected = isAgeSelected;
	}

	public String getIsCasteSelected() {
		return isCasteSelected;
	}

	public void setIsCasteSelected(String isCasteSelected) {
		this.isCasteSelected = isCasteSelected;
	}

	public String getIsFamilySelected() {
		return isFamilySelected;
	}

	public void setIsFamilySelected(String isFamilySelected) {
		this.isFamilySelected = isFamilySelected;
	}

	public String getIsNameSelected() {
		return isNameSelected;
	}

	public void setIsNameSelected(String isNameSelected) {
		this.isNameSelected = isNameSelected;
	}

	public String getIsGenderSelected() {
		return isGenderSelected;
	}

	public void setIsGenderSelected(String isGenderSelected) {
		this.isGenderSelected = isGenderSelected;
	}

	public String getIsHouseNoSelected() {
		return isHouseNoSelected;
	}

	public void setIsHouseNoSelected(String isHouseNoSelected) {
		this.isHouseNoSelected = isHouseNoSelected;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
		
	public String getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public String getStartAge() {
		return startAge;
	}

	public void setStartAge(String startAge) {
		this.startAge = startAge;
	}

	public String getEndAge() {
		return endAge;
	}

	public void setEndAge(String endAge) {
		this.endAge = endAge;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCasteIds() {
		return casteIds;
	}

	public void setCasteIds(String casteIds) {
		this.casteIds = casteIds;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(String locationValue) {
		this.locationValue = locationValue;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
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

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public IVoiceSmsService getVoiceSmsService() {
		return voiceSmsService;
	}

	public void setVoiceSmsService(IVoiceSmsService voiceSmsService) {
		this.voiceSmsService = voiceSmsService;
	}

	public String execute(){
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
		 return ERROR;
		else
		return Action.SUCCESS;
	}

public String searchCandidatesForVoiceSms(){
		
	List<SMSSearchCriteriaVO> influencPeopleDetailsList = new ArrayList<SMSSearchCriteriaVO>();
	try
	{
		smsPeopleList = new SMSSearchCriteriaVO();	
		HttpSession session = request.getSession();			
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null)
			return Action.INPUT;
		
		
		Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
		String order = request.getParameter("dir");
		String columnName = request.getParameter("sort");
		Integer maxRecords = Integer.parseInt(request.getParameter("results"));
		
		SMSSearchCriteriaVO searchVO = new SMSSearchCriteriaVO();
		searchVO.setUserId(user.getRegistrationID());
		searchVO.setAgeSelected(Boolean.valueOf(request.getParameter("isAgeSelected")));
		searchVO.setCasteSelected(Boolean.valueOf(request.getParameter("isCasteSelected")));
		searchVO.setFamilySelected(Boolean.valueOf(request.getParameter("isFamilySelected")));
		searchVO.setNameSelected(Boolean.valueOf(request.getParameter("isNameSelected")));
		searchVO.setGenderSelected(Boolean.valueOf(request.getParameter("isGenderSelected")));
		searchVO.setConstituencyId(Long.valueOf((request.getParameter("parentLocationId"))));
		
		
		if(searchVO.isAgeSelected()){
			searchVO.setStartAge(Integer.parseInt(request.getParameter("startAge")));
			searchVO.setEndAge(Integer.parseInt(request.getParameter("endAge")));
		}
		
		if(searchVO.isFamilySelected())
			searchVO.setHouseNo(request.getParameter("houseNo"));
		
		if(searchVO.isNameSelected())
			searchVO.setName(request.getParameter("name"));
		
		if(searchVO.isCasteSelected())
			searchVO.setCasteIds(request.getParameter("casteIds"));
		
		if(searchVO.isGenderSelected())
			searchVO.setGender(request.getParameter("gender"));
		
		
		searchVO.setLocationType(request.getParameter("locationType"));
		searchVO.setLocationValue(Long.parseLong(request.getParameter("locationValue")));
		
		searchVO.setStartIndex(startIndex);
		searchVO.setMaxRecords(maxRecords);
		searchVO.setColumnName(columnName);
		searchVO.setOrder(order);
		
		influencPeopleDetailsList = voiceSmsService.getAllInfluencingPeopleDetailsForVoiceSMS(searchVO);
		int totalResultCount = influencPeopleDetailsList.get(0).getTotalResultsCount();
		influencPeopleDetailsList.remove(0);
		smsPeopleList.setResultVotersList(influencPeopleDetailsList);
		smsPeopleList.setTotalResultsCount(totalResultCount);
		
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return Action.SUCCESS;
	}	
}
