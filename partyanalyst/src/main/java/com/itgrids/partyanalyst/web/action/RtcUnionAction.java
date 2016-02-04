package com.itgrids.partyanalyst.web.action;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadrePrintVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterInfoVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ICandidateUpdationDetailsService;
import com.itgrids.partyanalyst.service.IRtcUnionService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.ISurveyDataDetailsService;
import com.itgrids.partyanalyst.service.ISurveyDetailsService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class RtcUnionAction extends ActionSupport implements ServletRequestAware{

	private String task;
	private JSONObject jObj;
	private HttpServletRequest request;
	private HttpSession session;
	
	private IRtcUnionService rtcUnionService;
	private ISurveyDataDetailsService surveyDataDetailsService;
	private IConstituencyDAO constituencyDAO;
	
	private List<SelectOptionVO> 				selectOptionVOList,constituencyesList,eletionTypesList,cadreRolesVOList;
	private Long 								countDownTime;
	private String								DoneTime;
	private List<GenericVO> 					genericVOList;
	private ICandidateUpdationDetailsService	candidateUpdationDetailsService;
	private IStaticDataService					staticDataService;
	private ICadreRegistrationService   		cadreRegistrationService;
	
	private String								searchType;
	private String 								candidateId;
	private String 								constiteucnyId;
	private String 								candidateName;
	private String 								voterCardNo;
	private String								houseNo;
	private InputStream 						inputStream;
	
	private List<VoterInfoVO> 					voterInfoVOList;
	
	
	
	public List<VoterInfoVO> getVoterInfoVOList() {
		return voterInfoVOList;
	}
	public void setVoterInfoVOList(List<VoterInfoVO> voterInfoVOList) {
		this.voterInfoVOList = voterInfoVOList;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public List<SelectOptionVO> getCadreRolesVOList() {
		return cadreRolesVOList;
	}
	public void setCadreRolesVOList(List<SelectOptionVO> cadreRolesVOList) {
		this.cadreRolesVOList = cadreRolesVOList;
	}
	public List<SelectOptionVO> getEletionTypesList() {
		return eletionTypesList;
	}
	public void setEletionTypesList(List<SelectOptionVO> eletionTypesList) {
		this.eletionTypesList = eletionTypesList;
	}
	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public void setCandidateUpdationDetailsService(
			ICandidateUpdationDetailsService candidateUpdationDetailsService) {
		this.candidateUpdationDetailsService = candidateUpdationDetailsService;
	}
	public List<GenericVO> getGenericVOList() {
		return genericVOList;
	}
	public void setGenericVOList(List<GenericVO> genericVOList) {
		this.genericVOList = genericVOList;
	}
	public List<SelectOptionVO> getConstituencyesList() {
		return constituencyesList;
	}
	public void setConstituencyesList(List<SelectOptionVO> constituencyesList) {
		this.constituencyesList = constituencyesList;
	}
	public void setSurveyDataDetailsService(
			ISurveyDataDetailsService surveyDataDetailsService) {
		this.surveyDataDetailsService = surveyDataDetailsService;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public IRtcUnionService getRtcUnionService() {
		return rtcUnionService;
	}


	public String getDoneTime() {
		return DoneTime;
	}
	public void setDoneTime(String doneTime) {
		DoneTime = doneTime;
	}
	public Long getCountDownTime() {
		return countDownTime;
	}
	public void setCountDownTime(Long countDownTime) {
		this.countDownTime = countDownTime;
	}
	public List<SelectOptionVO> getSelectOptionVOList() {
		return selectOptionVOList;
	}
	public void setSelectOptionVOList(List<SelectOptionVO> selectOptionVOList) {
		this.selectOptionVOList = selectOptionVOList;
	}
	
	public void setRtcUnionService(IRtcUnionService rtcUnionService) {
		this.rtcUnionService = rtcUnionService;
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
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}
	
	public String execute(){
		return Action.SUCCESS;
	}

	public String rtcUnionSearch()
	{
		LOG.info("Entered into tdpCadreSearchPage method in CadreRegistrationAction Action");
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			
			if(user == null)
				return Action.INPUT;
			
			//if(entitlementsHelper.checkForEntitlementToViewReport(user,"CADRE_REGISTRATION_2014"))
			//{
				Long stateTypeId = 0L; // 0 for All, 1 for AP, 2 for TG 
				Long stateId = 1L;
				
				if(user.getAccessType().equalsIgnoreCase("MLA")){
					selectOptionVOList =	surveyDataDetailsService.getAssemblyOfLoggedUser(user.getAccessValue(),user.getAccessType());
				}else{
					selectOptionVOList = 	surveyDataDetailsService.getAssemblyConstituenciesByStateId(stateTypeId,stateId);
				}
				
			
	    		
	    		if(user.getRegistrationID().longValue() != 3930L) // party office userId
	    		{
	    			SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_AND_TIME_FORMAT_24HRS); 
		    		Date now = new DateUtilService().getCurrentDateAndTime();
		    		Date endDate = null;
		    		
	    			if(user.getAccessType().trim().equalsIgnoreCase(IConstants.STATE))
		    		{
		    			endDate = format.parse(IConstants.TG_CADRE_2014_END_DATE);
		    		}
		    		else if(user.getAccessType().trim().equalsIgnoreCase(IConstants.MLA))
		    		{
		    			Constituency constituency = constituencyDAO.get(Long.valueOf(user.getAccessValue()));
		    			if(constituency.getDistrict().getDistrictId() < 11L)
		    			{
		    				endDate = format.parse(IConstants.TG_CADRE_2014_END_DATE);
		    			}
		    			else
		    			{
		    				endDate = format.parse(IConstants.AP_CADRE_2014_END_DATE);
		    			}
		    			
		    			DoneTime = new SimpleDateFormat("dd-MM-yyyy").format(endDate);
		    		}
	    			
					Long diffInDates = endDate.getTime() - now.getTime() ;
				    Long remaingSeconds = diffInDates / 1000; // remaining seconds
				    
				    if(remaingSeconds >=0)
				    {
				    	countDownTime = remaingSeconds;
				    }
				    else
				    {
				    	countDownTime = 0L;
				    }
	    		}
	    		
			//}
			
		} catch (Exception e) {
			LOG.error("Exception raised in tdpCadreSearchPage method in CadreRegistrationAction Action",e);
		}
	
		return Action.SUCCESS;
	}
	
	public String rtcUnionRegistrationPage()
	{

		LOG.info("Entered into tdpCadreRegistrationPage method in CadreRegistrationAction Action");
		try {
			
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
				return INPUT;
			
		//	if(entitlementsHelper.checkForEntitlementToViewReport(user,"CADRE_REGISTRATION_2014"))
			//{
				constituencyesList = 	surveyDataDetailsService.getAssemblyConstituenciesByStateId(0l,1l);  
				genericVOList = candidateUpdationDetailsService.gettingEducationDetails();
				selectOptionVOList = staticDataService.getAllOccupations();
				eletionTypesList = cadreRegistrationService.getElectionOptionDetailsForCadre();
				cadreRolesVOList = cadreRegistrationService.getCadreLevelsForCadreSearch();
				voterInfoVOList = cadreRegistrationService.getCandidateInfoBySearchCriteria(searchType,Long.valueOf(candidateId),IWebConstants.STATIC_CONTENT_FOLDER_URL,constiteucnyId);
							
				if(user.getRegistrationID().longValue() != 3930L) // party office userId
	    		{
	    			SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_AND_TIME_FORMAT_24HRS); 
		    		Date now = new DateUtilService().getCurrentDateAndTime();
		    		Date endDate = null;
		    		
	    			if(user.getAccessType().trim().equalsIgnoreCase(IConstants.STATE))
		    		{
		    			endDate = format.parse(IConstants.TG_CADRE_2014_END_DATE);
		    		}
		    		else if(user.getAccessType().trim().equalsIgnoreCase(IConstants.MLA))
		    		{
		    			Constituency constituency = constituencyDAO.get(Long.valueOf(user.getAccessValue()));
		    			if(constituency.getDistrict().getDistrictId() < 11L)
		    			{
		    				endDate = format.parse(IConstants.TG_CADRE_2014_END_DATE);
		    			}
		    			else
		    			{
		    				endDate = format.parse(IConstants.AP_CADRE_2014_END_DATE);
		    			}	
		    			DoneTime = new SimpleDateFormat("dd-MM-yyyy").format(endDate);
		    		}
	    			
					Long diffInDates = endDate.getTime() - now.getTime() ;
				    Long remaingSeconds = diffInDates / 1000; // remaining seconds
				    
				    if(remaingSeconds >=0)
				    {
				    	countDownTime = remaingSeconds;
				    }
				    else
				    {
				    	countDownTime = 0L;
				    }
	    		}
				
				return Action.SUCCESS;
			//}
			
		} catch (Exception e) {
			LOG.error("Exception raised in tdpCadreRegistrationPage method in RtcUnionAction  Action",e);
		}
	
		return Action.ERROR;
	} 
	
}
