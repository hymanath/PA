package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterAdderdOrDeletedRengesInfoVO;
import com.itgrids.partyanalyst.dto.VoterAgeRangeVO;
import com.itgrids.partyanalyst.dto.VoterModificationGenderInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationAgeRangeVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.IVoterModificationService;
import com.itgrids.partyanalyst.service.impl.StaticDataService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class VoterModificationReportAction extends ActionSupport implements ServletRequestAware{
	
	
	private static final long serialVersionUID = -8550656684382574240L;
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	private VoterModificationVO voterModificationVO;
	JSONObject jObj;
	private static final Logger LOG = Logger.getLogger(VoterModificationReportAction.class);
	private List<VoterAgeRangeVO> voterAgeRangeVOList;
	private Long constituencyId;
	private Long fromPublicationDateId;
	private Long toPublicationDateId;
	private Long locationValue;
	private Long localElectionBodyId;
	private String locationType;
	private IVoterModificationService voterModificationService;
	private List<VoterModificationAgeRangeVO> voterModificationAgeRangeVOList;
	private VoterModificationGenderInfoVO voterModificationGenderInfoVO;
	private List<VoterModificationGenderInfoVO> voterModificationGenderInfoVOList;
	private List<VoterVO> voterVOs;
	private String locationName;
	private String fromPublicationName;
	private String toPublicationName;
	private StaticDataService staticDataService;
	private String locationTypeVariable;
	private SelectOptionVO selectOptionVO;
	private List<VoterAdderdOrDeletedRengesInfoVO> voterAdderdOrDeletedRengesInfoVOList;
	
	
	
	/**
	 * @return the selectOptionVO
	 */
	public SelectOptionVO getSelectOptionVO() {
		return selectOptionVO;
	}
	/**
	 * @param selectOptionVO the selectOptionVO to set
	 */
	public void setSelectOptionVO(SelectOptionVO selectOptionVO) {
		this.selectOptionVO = selectOptionVO;
	}
	/**
	 * @return the voterAdderdOrDeletedRengesInfoVOList
	 */
	public List<VoterAdderdOrDeletedRengesInfoVO> getVoterAdderdOrDeletedRengesInfoVOList() {
		return voterAdderdOrDeletedRengesInfoVOList;
	}
	/**
	 * @param voterAdderdOrDeletedRengesInfoVOList the voterAdderdOrDeletedRengesInfoVOList to set
	 */
	public void setVoterAdderdOrDeletedRengesInfoVOList(
			List<VoterAdderdOrDeletedRengesInfoVO> voterAdderdOrDeletedRengesInfoVOList) {
		this.voterAdderdOrDeletedRengesInfoVOList = voterAdderdOrDeletedRengesInfoVOList;
	}
	public StaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(StaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public List<VoterVO> getVoterVOs() {
		return voterVOs;
	}
	public void setVoterVOs(List<VoterVO> voterVOs) {
		this.voterVOs = voterVOs;
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

	public void setServletRequest(HttpServletRequest arg0) {
		
		this.request=arg0;
	}
	
	public VoterModificationVO getVoterModificationVO() {
		return voterModificationVO;
	}
	public void setVoterModificationVO(VoterModificationVO voterModificationVO) {
		this.voterModificationVO = voterModificationVO;
	}
	public List<VoterAgeRangeVO> getVoterAgeRangeVOList() {
		return voterAgeRangeVOList;
	}
	public void setVoterAgeRangeVOList(List<VoterAgeRangeVO> voterAgeRangeVOList) {
		this.voterAgeRangeVOList = voterAgeRangeVOList;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getFromPublicationDateId() {
		return fromPublicationDateId;
	}
	public void setFromPublicationDateId(Long fromPublicationDateId) {
		this.fromPublicationDateId = fromPublicationDateId;
	}
	public Long getToPublicationDateId() {
		return toPublicationDateId;
	}
	public void setToPublicationDateId(Long toPublicationDateId) {
		this.toPublicationDateId = toPublicationDateId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	
	public IVoterModificationService getVoterModificationService() {
		return voterModificationService;
	}
	public void setVoterModificationService(
			IVoterModificationService voterModificationService) {
		this.voterModificationService = voterModificationService;
	}
	
	public List<VoterModificationAgeRangeVO> getVoterModificationAgeRangeVOList() {
		return voterModificationAgeRangeVOList;
	}
	public void setVoterModificationAgeRangeVOList(
			List<VoterModificationAgeRangeVO> voterModificationAgeRangeVOList) {
		this.voterModificationAgeRangeVOList = voterModificationAgeRangeVOList;
	}
	
	public VoterModificationGenderInfoVO getVoterModificationGenderInfoVO() {
		return voterModificationGenderInfoVO;
	}
	public void setVoterModificationGenderInfoVO(
			VoterModificationGenderInfoVO voterModificationGenderInfoVO) {
		this.voterModificationGenderInfoVO = voterModificationGenderInfoVO;
	}
	public List<VoterModificationGenderInfoVO> getVoterModificationGenderInfoVOList() {
		return voterModificationGenderInfoVOList;
	}
	public void setVoterModificationGenderInfoVOList(
			List<VoterModificationGenderInfoVO> voterModificationGenderInfoVOList) {
		this.voterModificationGenderInfoVOList = voterModificationGenderInfoVOList;
	}
	
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getFromPublicationName() {
		return fromPublicationName;
	}
	public void setFromPublicationName(String fromPublicationName) {
		this.fromPublicationName = fromPublicationName;
	}
	public String getToPublicationName() {
		return toPublicationName;
	}
	public void setToPublicationName(String toPublicationName) {
		this.toPublicationName = toPublicationName;
	}
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	public String getLocationTypeVariable() {
		if(locationTypeVariable != null)
			locationTypeVariable = locationTypeVariable.toLowerCase();
		return locationTypeVariable;
	}
	public void setLocationTypeVariable(String locationTypeVariable) {
		this.locationTypeVariable = locationTypeVariable;
	}
	
	public String execute()throws Exception
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION) || locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
		{
			localElectionBodyId = voterModificationService.getLocalElectionBodyIdByAssemblyLocalElectionBodyId(locationValue);
			locationTypeVariable = voterModificationService.getLocationTypeForLocalEleBodyByLocalEleBodyId(locationValue);
		}
		else
			locationTypeVariable = locationType;
		
		locationName = voterModificationService.getLocationNameByLocationValue(locationType, locationValue);
		fromPublicationName = voterModificationService.getPublicationNameByPublicationDateId(fromPublicationDateId);
		toPublicationName = voterModificationService.getPublicationNameByPublicationDateId(toPublicationDateId);
		
		return Action.SUCCESS;
	}
	
	public String getModifiedVotersCountBetweenPublications()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String locationType = jObj.getString("locationType");
		Long locationValue = jObj.getLong("locationValue");
		Long constituencyId = jObj.getLong("constituencyId");
		Long fromPublicationDateId = jObj.getLong("fromPublicationDateId");
		Long toPublicationDateId = jObj.getLong("toPublicationDateId");
		
		voterModificationVO = voterModificationService.getAddedAndDeletedVotersCountInBetweenPublicationsInALocation(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId);
		return Action.SUCCESS;
	} 

	public String ajaxHandler()
	{
		String param;
		param = getTask();
		
		try{
			jObj = new JSONObject(param);	
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in ajaxHandler() Method, Exception - "+e);
		}
		Long constituencyId = jObj.getLong("constituencyId");
		Long fromPublicationDateId = jObj.getLong("fromPublicationDateId");
		Long toPublicationDateId = jObj.getLong("toPublicationDateId");
		String locationType = jObj.getString("locationType");
		Long locationValue = jObj.getLong("locationValue");
		voterAgeRangeVOList = voterModificationService.getVoterInfoByPublicationDateList(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId);
		return Action.SUCCESS;
	}
	
	public String getAddedOrDeletedVoterInfoInALocation()
	{
		String param ;
		param = getTask();
		try{
			jObj = new JSONObject(param);		
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getAddedOrDeletedVoterInfoInALocation() Method, Exception - "+e);
		}
		
		Long constituencyId = jObj.getLong("constituencyId");
		Long fromPublicationDateId = jObj.getLong("fromPublicationDateId");
		Long toPublicationDateId = jObj.getLong("toPublicationDateId");
		String locationType = jObj.getString("locationType");
		Long locationValue = jObj.getLong("locationValue");
		
		voterModificationAgeRangeVOList = voterModificationService.getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId,"intermediate");
		return Action.SUCCESS;
	}
	
	public String getGenderWiseVoterModificationsBetweenPublications()
	{
		String param;
		param = getTask();
		try{
			jObj = new JSONObject(param);
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getGenderWiseVoterModificationsBetweenPublications() Method, Exception - "+e);
		}
		
		Long constituencyId = jObj.getLong("constituencyId");
		Long fromPublicationDateId = jObj.getLong("fromPublicationDateId");
		Long toPublicationDateId = jObj.getLong("toPublicationDateId");
		String locationType = jObj.getString("locationType");
		Long locationValue = jObj.getLong("locationValue");
		
		voterModificationGenderInfoVO = voterModificationService.getGenderWiseVoterModificationsBetweenPublications(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId,"intermediate");
		return Action.SUCCESS;
	}
	
	
	public String getGenderWiseVoterModificationsForEachPublication()
	{
		String param;
		param = getTask();
		try{
			jObj = new JSONObject(param);
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getGenderWiseVoterModificationsForEachPublication() Method, Exception - "+e);
		}
		Long constituencyId = jObj.getLong("constituencyId");
		Long fromPublicationDateId = jObj.getLong("fromPublicationDateId");
		Long toPublicationDateId = jObj.getLong("toPublicationDateId");
		String locationType = jObj.getString("locationType");
		Long locationValue = jObj.getLong("locationValue");
		
		voterModificationGenderInfoVOList = voterModificationService.getGenderWiseVoterModificationsForEachPublication(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId,"intermediate");
		return Action.SUCCESS;
	}
	
	public String getAllVoterInformationInALocation()
	{

		String param ;
		param = getTask();
		try{
			jObj = new JSONObject(param);		
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception Occured in getAllVoterInformationInALocation() Method, Exception - "+e);
		}
		
		Long constituencyId = jObj.getLong("constituencyId");
		Long fromPublicationDateId = jObj.getLong("fromPublicationDateId");
		Long toPublicationDateId = jObj.getLong("toPublicationDateId");
		String locationType = jObj.getString("locationType");
		Long locationValue = jObj.getLong("locationValue");
		String status = jObj.getString("status");
		
		//voterModificationAgeRangeVOList = voterModificationService.getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId);
		
		voterVOs = voterModificationService
				.getModifiedVotersInALocationBetweenPublucations(locationType,
						locationValue, constituencyId, fromPublicationDateId,
						toPublicationDateId, status);
		return Action.SUCCESS;
	
		//allVotersList	
		
	}
	
	
	
	public String getSubLevelsVoterModificationDetails()
	{
		
		String param ;
		param = getTask();
		try{
			jObj = new JSONObject(param);		
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception Occured in getAllVoterInformationInALocation() Method, Exception - "+e);
		}
		
		Long constituencyId = jObj.getLong("constituencyId");
		Long fromPublicationDateId = jObj.getLong("fromPublicationDateId");
		Long toPublicationDateId = jObj.getLong("toPublicationDateId");
		String locationType = jObj.getString("locationType");
		Long locationValue = jObj.getLong("locationValue");
		String status = jObj.getString("status");
		
		
		
		/*voterModificationVO = voterModificationService.getSubLevelsVoterModificationDetails(
				locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId);*/
		
		voterModificationVO = voterModificationService.getSubLevelsVoterModificationDetailsByLocationValue(
				locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId);
		return Action.SUCCESS;
		
	}
	
	public String getSelectedVoterDetails()
	{
		
		String param ;
		param = getTask();
		try{
			jObj = new JSONObject(param);		
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception Occured in getAllVoterInformationInALocation() Method, Exception - "+e);
		}
		
		String locationType = jObj.getString("locationType");
		Long locationValue = jObj.getLong("locationValue");		
		Long constituencyId = jObj.getLong("constituencyId");
		Long fromPublicationDateId = jObj.getLong("fromPublicationDateId");
		Long toPublicationDateId = jObj.getLong("toPublicationDateId");
		String forGender = jObj.getString("forGender");
		Long voterStatusId = jObj.getLong("voterStatusId");
		Long ageRangeId = jObj.getLong("ageRangeId");
		String gender = jObj.getString("gender");
		
		
		VoterModificationVO voterModificationVO = new VoterModificationVO();
		
		
		voterModificationVO.setLocationType(locationType);
		voterModificationVO.setLocationValue(locationValue);
		voterModificationVO.setConstituencyId(constituencyId);
		voterModificationVO.setPreviousPublicationId(fromPublicationDateId);
		voterModificationVO.setPresentPublicationId(toPublicationDateId);
		voterModificationVO.setIsForGender(forGender);
		voterModificationVO.setVoterStatusId(voterStatusId);
		voterModificationVO.setAgeRangeId(ageRangeId);
		voterModificationVO.setGender(gender);
		
		
		
		voterVOs = voterModificationService.getSelectedVotersDetails(voterModificationVO);
		
		
		return Action.SUCCESS;
		
	}
	
	public String getAddedOrDeletedVotersList()
	{
		try {
			jObj = new JSONObject(getTask());
			Long constituencyId = jObj.getLong("constituencyId");
			Long publicationId = jObj.getLong("publicationId");
			if(jObj.getString("task").equalsIgnoreCase("getAddedOrDeletedVoterDetails"))
			{
				voterAdderdOrDeletedRengesInfoVOList = voterModificationService.getReportForVotersAddedOrDeletedVotersForSelectdConstituency(constituencyId,publicationId,"");
			}
			else
			{
				voterAdderdOrDeletedRengesInfoVOList = voterModificationService.getReportForVotersAddedOrDeletedVotersForSelectdConstituency(constituencyId,publicationId,"panchayatWise");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Action.SUCCESS;
	}
	
	public String createPdf()
	{
		try {
			jObj = new JSONObject(getTask());
			Long constituencyId = jObj.getLong("constituencyId");
			Long publicationId = jObj.getLong("publicationId");
			String locationType =  jObj.getString("locationType");
			Long locationValue = jObj.getLong("locationValue");
			String path = IWebConstants.STATIC_CONTENT_FOLDER_URL ;
			selectOptionVO = voterModificationService.createPdf(constituencyId,publicationId,locationType,locationValue,"intermediate" ,path);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Action.SUCCESS;
	}
	
}
