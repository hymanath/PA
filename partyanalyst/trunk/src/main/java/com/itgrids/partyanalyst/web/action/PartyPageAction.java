package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.PartyPageVO;
import com.itgrids.partyanalyst.dto.PartyResultInfoVO;
import com.itgrids.partyanalyst.dto.PartyVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
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

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {

	}

	@Override
	public void setServletContext(ServletContext arg0) {

	}
	public String getTask() {
		return task;
	}
	public String execute() throws Exception {
		Log.debug("Entered into party page action");
		request.setAttribute("partyId", partyId);
		partyVO = partyDetailsService.getPartyDetails(partyId);
		descriptions = partyDetailsService.getPartyProfileDescriptionById(partyId);
		fileVO = partyDetailsService.getPartyLatestVideos(partyId,0,20);

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
			Long stateId = new Long(jObj.getString("stateId"));
			Long partyId = new Long(jObj.getString("partyId"));
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
			Long electionId = new Long(jObj.getString("electionId"));
			Long electionTypeId = new Long(jObj.getString("electionTypeId"));
			Long partyId = new Long(jObj.getString("partyId"));
			Long stateId = new Long(jObj.getString("stateId"));
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
}
