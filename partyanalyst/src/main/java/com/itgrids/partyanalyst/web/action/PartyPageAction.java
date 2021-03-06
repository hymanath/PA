package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AdvVideoVO;
import com.itgrids.partyanalyst.dto.CandidateElectionResultVO;
import com.itgrids.partyanalyst.dto.CustomPageVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.PartyInfoVO;
import com.itgrids.partyanalyst.dto.PartyPageVO;
import com.itgrids.partyanalyst.dto.PartyResultInfoVO;
import com.itgrids.partyanalyst.dto.PartyVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IAdvVideoService;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IPartyDetailsService;
import com.itgrids.partyanalyst.service.impl.PartyDetailsService;
import com.itgrids.partyanalyst.service.impl.PartyResultService;
import com.itgrids.partyanalyst.utils.ElectionScopeLevelEnum;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyPageAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4697694648631810338L;
	private static final Logger LOG = Logger.getLogger(PartyPageAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private Long partyId;
	private IPartyDetailsService partyDetailsService;
	private PartyVO partyVO;
	private List<String> descriptions;
	private JSONObject jObj;
	private String task=null;
	private PartyResultService partyResultService;
    private List<PartyResultInfoVO> partyResultInfoVOList;
    private List<FileVO> fileVO;
    private ResultStatus result;
    private Long contentId;
    private List<CustomPageVO> customPages;
    private List<PartyInfoVO> partyInfoVOList = new ArrayList<PartyInfoVO>();
    private CandidateElectionResultVO candidateElectionResultVO;
    private Map<String,List<PartyInfoVO>> resultMap = new HashMap<String, List<PartyInfoVO>>();
    private List<SelectOptionVO> data;
    private Long stateId;
    private Long constituencyId;
    private ICandidateDetailsService candidateDetailsService;
    private Boolean isSubscribed;
    private String partyPageLoadingFirstTime;
    private IAdvVideoService advVideoService;
	private List<AdvVideoVO> advVideosList;
    
    
	public List<AdvVideoVO> getAdvVideosList() {
		return advVideosList;
	}
	public void setAdvVideosList(List<AdvVideoVO> advVideosList) {
		this.advVideosList = advVideosList;
	}
	public IAdvVideoService getAdvVideoService() {
		return advVideoService;
	}
	public void setAdvVideoService(IAdvVideoService advVideoService) {
		this.advVideoService = advVideoService;
	}
	public Boolean getIsSubscribed() {
		return isSubscribed;
	}

	public void setIsSubscribed(Boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public void setResultMap(Map<String,List<PartyInfoVO>> resultMap) {
		this.resultMap = resultMap;
	}

	public Map<String,List<PartyInfoVO>> getResultMap() {
		return resultMap;
	}

	public void setPartyInfoVOList(List<PartyInfoVO> partyInfoVOList) {
		this.partyInfoVOList = partyInfoVOList;
	}

	public List<PartyInfoVO> getPartyInfoVOList() {
		return partyInfoVOList;
	}
	public CandidateElectionResultVO getCandidateElectionResultVO() {
		return candidateElectionResultVO;
	}

	public void setCandidateElectionResultVO(
			CandidateElectionResultVO candidateElectionResultVO) {
		this.candidateElectionResultVO = candidateElectionResultVO;
	}

	public List<CustomPageVO> getCustomPages() {
		return customPages;
	}

	public void setCustomPages(List<CustomPageVO> customPages) {
		this.customPages = customPages;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
    
	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
		this.result = result;
	}

	public List<FileVO> getFileVO() {
		return fileVO;
	}

	public void setFileVO(List<FileVO> fileVO) {
		this.fileVO = fileVO;
	}

	public List<PartyResultInfoVO> getPartyResultInfoVOList() {
		return partyResultInfoVOList;
	}

	public void setPartyResultInfoVOList(
			List<PartyResultInfoVO> partyResultInfoVOList) {
		this.partyResultInfoVOList = partyResultInfoVOList;
	}

	public PartyResultService getPartyResultService() {
		return partyResultService;
	}

	public void setPartyResultService(PartyResultService partyResultService) {
		this.partyResultService = partyResultService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

	public IPartyDetailsService getPartyDetailsService() {
		return partyDetailsService;
	}

	public void setPartyDetailsService(IPartyDetailsService partyDetailsService) {
		this.partyDetailsService = partyDetailsService;
	}

	public PartyVO getPartyVO() {
		return partyVO;
	}

	public void setPartyVO(PartyVO partyVO) {
		this.partyVO = partyVO;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
    
	public List<SelectOptionVO> getData() {
		return data;
	}

	public void setData(List<SelectOptionVO> data) {
		this.data = data;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}
   
	public void setServletResponse(HttpServletResponse arg0) {

	}

	public void setServletContext(ServletContext arg0) {

	}
	public String getTask() {
		return task;
	}
	public String execute() throws Exception {
		if(!("tdpserver".equalsIgnoreCase(IConstants.DEPLOYED_HOST))){
			if(session.getAttribute("partyPageLoadingFirstTime") == null)
			{
				session.setAttribute("partyPageLoadingFirstTime", "true");
			}
			else
				session.setAttribute("partyPageLoadingFirstTime", "false");
		}else{
			session.setAttribute("specialPageLoadingFirstTime", "false");
		}
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		
		if(regVO != null){
			
			//Getting State and Constituency of a Logged 
		Long regId=regVO.getRegistrationID();
		RegistrationVO regisVO =  candidateDetailsService.getStateAndConstituency(regId);
		stateId=regisVO.getStateId();
		constituencyId=regisVO.getConstituencyId();
		String page="pPage";
		isSubscribed = candidateDetailsService.getSubscriptionDetails(regId,partyId,page);
	    }else{
	    	stateId=null;
			constituencyId=null;
	    }

		LOG.debug("Entered into party page action");
		request.setAttribute("partyId", partyId);
		partyVO = partyDetailsService.getPartyDetails(partyId);
		descriptions = partyDetailsService.getPartyProfileDescriptionById(partyId);
		fileVO = partyDetailsService.getPartyLatestVideos(partyId,0,20);
		customPages   = partyDetailsService.getCustomPagesOfAPartyPage(partyId);

		advVideosList = advVideoService.getTopAdvVideosForDisplaying();	
		return Action.SUCCESS;
	}

	public String ajaxCallHandler()
	{
		
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		
		if(jObj.getString("task").equalsIgnoreCase("getParliamentElectionResultsByState"))
		{
			ElectionScopeLevelEnum level = ElectionScopeLevelEnum.STATE_LEVEL;
			Long districtId=null;
			Long constituencyId = null;
			String partyShortName = jObj.getString("partyShortName");
			Long partyId = jObj.getLong("partyId");	
			Long electionId = jObj.getLong("electionId");
			Long countryId = jObj.getLong("countryId");
			Long stateId = jObj.getLong("stateId");
			Boolean hasAlliances = jObj.getBoolean("hasAlliances");
		    partyResultInfoVOList = partyResultService.getPartyResultsInfo(partyShortName, partyId, electionId, countryId, stateId, 
				districtId,constituencyId ,level,hasAlliances);
     	}
		else if(jObj.getString("task").equalsIgnoreCase("getNewsToDisplay"))
		{
			fileVO = partyDetailsService.getNewsToDisplay(jObj.getLong("partyId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),jObj.getString("queryType"));
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getAllNewsToDisplay") )
		{
			String startIndex    = request.getParameter("startIndex");
			String resultsCount  = request.getParameter("resultsCount");	
				
			fileVO = partyDetailsService.getAllNewsdetails(jObj.getLong("partyId"),Integer.parseInt(startIndex),Integer.parseInt(resultsCount),jObj.getString("queryType"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("getFirstThreePhotoGallaryDetail"))
		{
			fileVO = partyDetailsService.getFirstThreePhotoGallaryDetail(jObj.getLong("partyId"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("getPartyPhotoGallaryDetailWithOutGallerySizeZero"))
		{
			fileVO = partyDetailsService.getPartyPhotoGallaryDetailWithOutGallerySizeZero(jObj.getLong("partyId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.PHOTO_GALLARY);
		}
		else if(jObj.getString("task").equalsIgnoreCase("videoGalleriesForAParty")){
			
			fileVO = partyDetailsService.getPartyPhotoGallaryDetail(jObj.getLong("partyId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.VIDEO_GALLARY);
		}
        else if(jObj.getString("task").equalsIgnoreCase("getPartyPhotoGallaryDetail")){
			
        	fileVO = partyDetailsService.getPartyPhotoGallaryDetail(jObj.getLong("partyId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.PHOTO_GALLARY);
		}
        else if(jObj.getString("task").equalsIgnoreCase("getPartyManifesto")){
			
        	fileVO = partyDetailsService.getPartyManifestoInfo(jObj.getLong("partyId"));
		}
        else if(jObj.getString("task").equalsIgnoreCase("getPartyManifestoDetails")){
			
        	fileVO = partyDetailsService.getPartyManifestoInfo(jObj.getLong("partyId"));
		}
        else if(jObj.getString("task").equalsIgnoreCase("getSelectedStateDetails")){
			
        	fileVO = partyDetailsService.getSelectedState(jObj.getLong("partyId"));
		}
        else if(jObj.getString("task").equalsIgnoreCase("getCandidateDetailsForAsses")){
        	
        	candidateElectionResultVO = partyDetailsService.getCandidateDetailsForAsses(jObj.getLong("candidateId"),jObj.getLong("electionId"));
        	if(candidateElectionResultVO != null && regVO != null)
        	{
        		candidateElectionResultVO.setDistrictId(regVO.getRegistrationID());
        		candidateElectionResultVO.setDistrictName(regVO.getUserType());
        	}
        }
		
	return Action.SUCCESS;
}



	public String setEmailAlertsForUser(){
	
	try {
		jObj = new JSONObject(getTask());
	} catch (ParseException e) {
		e.printStackTrace();
	}
	
	session = request.getSession();
	RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
	
	String email = jObj.getString("emailId");
    result = partyDetailsService.subScribeEmailAlertForAUser(email,jObj.getLong("partyId"));
    
     return Action.SUCCESS;
}
	
	public String getPartyManifestoBasedOnStateId(){
		try {
			jObj = new JSONObject(getTask());
			Long stateId = Long.valueOf(jObj.getString("stateId"));
			Long partyId = Long.valueOf(jObj.getString("partyId"));
			fileVO = partyDetailsService.getPartyManifestoBasedOnStateIdAndPartyd(stateId,partyId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}

	public String getPartyRelatedManifestoFile(){
		
		try {
			PartyPageVO partyPageVO = new PartyPageVO();
			jObj = new JSONObject(getTask());
			Long electionId = Long.valueOf(jObj.getString("electionId"));
			Long electionTypeId = Long.valueOf(jObj.getString("electionTypeId"));
			Long partyId = Long.valueOf(jObj.getString("partyId"));
			Long stateId = Long.valueOf(jObj.getString("stateId"));
			partyPageVO.setPartyId(partyId);
			if(electionId !=0)
			partyPageVO.setElectionId(electionId);
			if(stateId !=0)
				partyPageVO.setStateId(stateId);
			if(electionTypeId !=0)
				partyPageVO.setElectionTypeId(electionTypeId);
			fileVO = partyDetailsService.getPartyManifestoDetailsBasedOnSelection(partyPageVO);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
	return Action.SUCCESS;
	}
	
	public String getPartyElectionProfile()
	{
		try {
			jObj = new JSONObject(getTask());
			Long partyId = Long.valueOf(jObj.getString("partyId"));
			resultMap = partyDetailsService.getPartyElectionResults(partyId,jObj.getBoolean("includeAlliances"),jObj.getBoolean("includeBielections"));
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getPartyElectionDetails()
	{
		try {
			jObj = new JSONObject(getTask());
			
			if(jObj.getString("task").trim().equalsIgnoreCase("statedetails"))
			     data = partyDetailsService.getStateDetails(jObj.getLong("partyId"));
			
			else if(jObj.getString("task").trim().equalsIgnoreCase("elecyears"))
				 data = partyDetailsService.getElecYears(jObj.getLong("partyId"),jObj.getLong("electionType"),jObj.getLong("stateId"));
			
			else if(jObj.getString("task").trim().equalsIgnoreCase("candidatedetails"))
				 data = partyDetailsService.getCandidateDetailsForAParty(jObj.getLong("partyId"),jObj.getLong("electionId"));
			
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
}
