package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
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
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class VoterModificationAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	private VoterModificationVO voterModificationVO;
	JSONObject jObj;
	private static final Logger LOG = Logger.getLogger(VoterModificationAction.class);
	private List<VoterAgeRangeVO> voterAgeRangeVOList;
	private Long constituencyId;
	private Long fromPublicationDateId;
	private Long toPublicationDateId;
	private Long locationValue;
	private Long localElectionBodyId;
	private String locationType;
	private List<VoterModificationAgeRangeVO> voterModificationAgeRangeVOList;
	private VoterModificationGenderInfoVO voterModificationGenderInfoVO;
	private List<VoterModificationGenderInfoVO> voterModificationGenderInfoVOList;
	private List<VoterVO> voterVOs;
	private String locationName;
	private String fromPublicationName;
	private String toPublicationName;
	private String locationTypeVariable;
	private SelectOptionVO selectOptionVO;
	
	private StaticDataService staticDataService;
	private IVoterModificationService voterModificationService;
	private List<VoterAdderdOrDeletedRengesInfoVO> voterAdderdOrDeletedRengesInfoVOList;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	/**
	 * @return the task
	 */
	public String getTask() {
		return task;
	}
	/**
	 * @param task the task to set
	 */
	public void setTask(String task) {
		this.task = task;
	}
	/**
	 * @return the voterModificationVO
	 */
	public VoterModificationVO getVoterModificationVO() {
		return voterModificationVO;
	}
	/**
	 * @param voterModificationVO the voterModificationVO to set
	 */
	public void setVoterModificationVO(VoterModificationVO voterModificationVO) {
		this.voterModificationVO = voterModificationVO;
	}
	/**
	 * @return the voterAgeRangeVOList
	 */
	public List<VoterAgeRangeVO> getVoterAgeRangeVOList() {
		return voterAgeRangeVOList;
	}
	/**
	 * @param voterAgeRangeVOList the voterAgeRangeVOList to set
	 */
	public void setVoterAgeRangeVOList(List<VoterAgeRangeVO> voterAgeRangeVOList) {
		this.voterAgeRangeVOList = voterAgeRangeVOList;
	}
	/**
	 * @return the constituencyId
	 */
	public Long getConstituencyId() {
		return constituencyId;
	}
	/**
	 * @param constituencyId the constituencyId to set
	 */
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	/**
	 * @return the fromPublicationDateId
	 */
	public Long getFromPublicationDateId() {
		return fromPublicationDateId;
	}
	/**
	 * @param fromPublicationDateId the fromPublicationDateId to set
	 */
	public void setFromPublicationDateId(Long fromPublicationDateId) {
		this.fromPublicationDateId = fromPublicationDateId;
	}
	/**
	 * @return the toPublicationDateId
	 */
	public Long getToPublicationDateId() {
		return toPublicationDateId;
	}
	/**
	 * @param toPublicationDateId the toPublicationDateId to set
	 */
	public void setToPublicationDateId(Long toPublicationDateId) {
		this.toPublicationDateId = toPublicationDateId;
	}
	/**
	 * @return the locationValue
	 */
	public Long getLocationValue() {
		return locationValue;
	}
	/**
	 * @param locationValue the locationValue to set
	 */
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	/**
	 * @return the localElectionBodyId
	 */
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	/**
	 * @param localElectionBodyId the localElectionBodyId to set
	 */
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	/**
	 * @return the locationType
	 */
	public String getLocationType() {
		return locationType;
	}
	/**
	 * @param locationType the locationType to set
	 */
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	/**
	 * @return the voterModificationAgeRangeVOList
	 */
	public List<VoterModificationAgeRangeVO> getVoterModificationAgeRangeVOList() {
		return voterModificationAgeRangeVOList;
	}
	/**
	 * @param voterModificationAgeRangeVOList the voterModificationAgeRangeVOList to set
	 */
	public void setVoterModificationAgeRangeVOList(
			List<VoterModificationAgeRangeVO> voterModificationAgeRangeVOList) {
		this.voterModificationAgeRangeVOList = voterModificationAgeRangeVOList;
	}
	/**
	 * @return the voterModificationGenderInfoVO
	 */
	public VoterModificationGenderInfoVO getVoterModificationGenderInfoVO() {
		return voterModificationGenderInfoVO;
	}
	/**
	 * @param voterModificationGenderInfoVO the voterModificationGenderInfoVO to set
	 */
	public void setVoterModificationGenderInfoVO(
			VoterModificationGenderInfoVO voterModificationGenderInfoVO) {
		this.voterModificationGenderInfoVO = voterModificationGenderInfoVO;
	}
	/**
	 * @return the voterModificationGenderInfoVOList
	 */
	public List<VoterModificationGenderInfoVO> getVoterModificationGenderInfoVOList() {
		return voterModificationGenderInfoVOList;
	}
	/**
	 * @param voterModificationGenderInfoVOList the voterModificationGenderInfoVOList to set
	 */
	public void setVoterModificationGenderInfoVOList(
			List<VoterModificationGenderInfoVO> voterModificationGenderInfoVOList) {
		this.voterModificationGenderInfoVOList = voterModificationGenderInfoVOList;
	}
	/**
	 * @return the voterVOs
	 */
	public List<VoterVO> getVoterVOs() {
		return voterVOs;
	}
	/**
	 * @param voterVOs the voterVOs to set
	 */
	public void setVoterVOs(List<VoterVO> voterVOs) {
		this.voterVOs = voterVOs;
	}
	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}
	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	/**
	 * @return the fromPublicationName
	 */
	public String getFromPublicationName() {
		return fromPublicationName;
	}
	/**
	 * @param fromPublicationName the fromPublicationName to set
	 */
	public void setFromPublicationName(String fromPublicationName) {
		this.fromPublicationName = fromPublicationName;
	}
	/**
	 * @return the toPublicationName
	 */
	public String getToPublicationName() {
		return toPublicationName;
	}
	/**
	 * @param toPublicationName the toPublicationName to set
	 */
	public void setToPublicationName(String toPublicationName) {
		this.toPublicationName = toPublicationName;
	}
	/**
	 * @return the staticDataService
	 */
	public StaticDataService getStaticDataService() {
		return staticDataService;
	}
	/**
	 * @param staticDataService the staticDataService to set
	 */
	public void setStaticDataService(StaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	/**
	 * @return the locationTypeVariable
	 */
	public String getLocationTypeVariable() {
		return locationTypeVariable;
	}
	/**
	 * @param locationTypeVariable the locationTypeVariable to set
	 */
	public void setLocationTypeVariable(String locationTypeVariable) {
		this.locationTypeVariable = locationTypeVariable;
	}
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
	
	
	/**
	 * @return the voterModificationService
	 */
	public IVoterModificationService getVoterModificationService() {
		return voterModificationService;
	}
	/**
	 * @param voterModificationService the voterModificationService to set
	 */
	public void setVoterModificationService(
			IVoterModificationService voterModificationService) {
		this.voterModificationService = voterModificationService;
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
	
	public String ajaxHandler()
	{
		String param;
		param = getTask();
		
		try{
			jObj = new JSONObject(param);
			if(jObj.getString("task").equalsIgnoreCase("getVoterInfo"))
			{
				Long constituencyId = jObj.getLong("constituencyId");
				Long fromPublicationDateId = jObj.getLong("fromPublicationDateId");
				Long toPublicationDateId = jObj.getLong("toPublicationDateId");
				String locationType = jObj.getString("locationType");
				Long locationValue = jObj.getLong("locationValue");
				voterAgeRangeVOList = voterModificationService.getVoterInfoByPublicationDateList(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in ajaxHandler() Method, Exception - "+e);
		}
		
		return Action.SUCCESS;
	}

}
