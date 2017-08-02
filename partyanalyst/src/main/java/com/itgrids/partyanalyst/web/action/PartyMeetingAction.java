package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.MeetingTrackingVO;
import com.itgrids.partyanalyst.dto.PMMinuteVO;
import com.itgrids.partyanalyst.dto.PartyMeetingDataVO;
import com.itgrids.partyanalyst.dto.PartyMeetingStatusVO;
import com.itgrids.partyanalyst.dto.PartyMeetingSummaryVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingWSVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IPartyMeetingService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyMeetingAction extends ActionSupport  implements ServletRequestAware{
	
	private static final Logger LOG = Logger.getLogger(PartyMeetingAction.class);
	
	private HttpServletRequest  request;
	private HttpSession session;
	private JSONObject	jObj;
	private String 		task;
	private IPartyMeetingService partyMeetingService;
	private PartyMeetingVO partyMeetingVO;
	private String status;
	private PartyMeetingSummaryVO partyMeetingsSummary; 
	private MeetingTrackingVO meetingTrackingVO;
	private PartyMeetingWSVO partyMeetingWSVO;
	private List<PartyMeetingsVO> partyMeetingVOsList;
	private List<PartyMeetingVO> partyMeetingVOList;
	private List<PartyMeetingStatusVO> meetingStatusVOs;
	private List<PMMinuteVO> pmMinutelist;
	
	
	
	public List<PMMinuteVO> getPmMinutelist() {
		return pmMinutelist;
	}

	public void setPmMinutelist(List<PMMinuteVO> pmMinutelist) {
		this.pmMinutelist = pmMinutelist;
	}

	public List<PartyMeetingVO> getPartyMeetingVOList() {
		return partyMeetingVOList;
	}

	public void setPartyMeetingVOList(List<PartyMeetingVO> partyMeetingVOList) {
		this.partyMeetingVOList = partyMeetingVOList;
	}

	public PartyMeetingWSVO getPartyMeetingWSVO() {
		return partyMeetingWSVO;
	}

	public void setPartyMeetingWSVO(PartyMeetingWSVO partyMeetingWSVO) {
		this.partyMeetingWSVO = partyMeetingWSVO;
	}

	public MeetingTrackingVO getMeetingTrackingVO() {
		return meetingTrackingVO;
	}

	public void setMeetingTrackingVO(MeetingTrackingVO meetingTrackingVO) {
		this.meetingTrackingVO = meetingTrackingVO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public IPartyMeetingService getPartyMeetingService() {
		return partyMeetingService;
	}

	public PartyMeetingVO getPartyMeetingVO() {
		return partyMeetingVO;
	}

	public void setPartyMeetingService(IPartyMeetingService partyMeetingService) {
		this.partyMeetingService = partyMeetingService;
	}

	public void setPartyMeetingVO(PartyMeetingVO partyMeetingVO) {
		this.partyMeetingVO = partyMeetingVO;
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
	
	
	
	public PartyMeetingSummaryVO getPartyMeetingsSummary() {
		return partyMeetingsSummary;
	}

	public void setPartyMeetingsSummary(PartyMeetingSummaryVO partyMeetingsSummary) {
		this.partyMeetingsSummary = partyMeetingsSummary;
	}
	
	public List<PartyMeetingsVO> getPartyMeetingVOsList() {
		return partyMeetingVOsList;
	}

	public void setPartyMeetingVOsList(List<PartyMeetingsVO> partyMeetingVOsList) {
		this.partyMeetingVOsList = partyMeetingVOsList;
	}

	public String execute(){
		return Action.SUCCESS;
	}
	
	public List<PartyMeetingStatusVO> getMeetingStatusVOs() {
		return meetingStatusVOs;
	}

	public void setMeetingStatusVOs(List<PartyMeetingStatusVO> meetingStatusVOs) {
		this.meetingStatusVOs = meetingStatusVOs;
	}
	
	public String getPartyMeetingsOverViewForCadre()
	{
		try {
			 jObj = new JSONObject(getTask());
		     Long tdpCadreId = jObj.getLong("tdpCadreId");
		     partyMeetingVO = partyMeetingService.getPartyMeetingsForCadrePeople(tdpCadreId);
		} catch (Exception e) {
			LOG.error(" Exception occured in getPartyMeetingDetailsForCadre in PartyMeetingAction Class.",e);
		}
		return Action.SUCCESS;
	}
	
	public String getPartyMeetingDetailsForCadre()
	{
		try {
			 jObj = new JSONObject(getTask());
		     Long tdpCadreId = jObj.getLong("tdpCadreId");
		     partyMeetingVO = partyMeetingService.getPartyMeetingDetailsBySearchType(tdpCadreId);
		} catch (Exception e) {
			LOG.error(" Exception occured in getPartyMeetingDetailsForCadre in PartyMeetingAction Class.",e);
		}
		return Action.SUCCESS;
	}
	
	public String getMeetingWiseDetailsByMeetingTypeId()
	{
		try {
			 jObj = new JSONObject(getTask());
		     Long meetingTypeId = jObj.getLong("meetingTypeId");
		     Long tdpCadreId = jObj.getLong("tdpCadreId");
		    partyMeetingVO = partyMeetingService.getMeetingTypeWiseDescription(meetingTypeId,tdpCadreId);
		} catch (Exception e) {
			LOG.error(" Exception occured in getPartyMeetingDetailsForCadre in PartyMeetingAction Class.",e);
		}
		return Action.SUCCESS;
	}
	
	public String updateMeetingPoint(){
		try {
			LOG.info("Entered into updateMeetingPoint");
			jObj = new JSONObject(getTask());
			Long minuteId = jObj.getLong("minuteId");
			String minuteText = jObj.getString("minuteText");
			Long loggedUser=0l;
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVo!=null && regVo.getRegistrationID()!=null){
				loggedUser = regVo.getRegistrationID();
			}
			
			status = partyMeetingService.updateMeetingPoint(minuteId,minuteText,loggedUser,jObj.getLong("partyMeetingId"),jObj.getLong("levelId"),jObj.getLong("levelValue"),
					jObj.getString("isActionable"),jObj.getLong("statusId"),
					jObj.getLong("stateId"),jObj.getLong("districtId"),jObj.getLong("constituencyId"),jObj.getLong("tehsilId"),jObj.getLong("panchayatId"),jObj.getLong("isGovtParty"));
		} catch (Exception e) {
			LOG.error("Exception raise at updateMeetingPoint", e);
		}
		return Action.SUCCESS;
	}
	
	public String deleteMeetingMinutePoint(){
		try {
			LOG.info("Entered into deleteMeetingMinutePoint");
			jObj = new JSONObject(getTask());
			Long minuteId = jObj.getLong("minuteId");
			Long loggedUser=0l;
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVo!=null && regVo.getRegistrationID()!=null){
				loggedUser = regVo.getRegistrationID();
			}
			
			status = partyMeetingService.deleteMeetingMinutePoint(minuteId,loggedUser);
		} catch (Exception e) {
			LOG.error("Exception raise at deleteMeetingMinutePoint", e);
		}
		return Action.SUCCESS;
	}
	
	public String updateMeetingAtrPoint(){
		try{
			LOG.info("Entered into updateMeetingAtrPoint");
			jObj = new JSONObject(getTask());
			
			Long loggedUser=0l;
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVo!=null && regVo.getRegistrationID()!=null){
				loggedUser = regVo.getRegistrationID();
			}
			
			status = partyMeetingService.updateMeetingAtrPoint(jObj.getLong("atrId"),jObj.getString("request"),jObj.getString("ActionTaken"),jObj.getString("raisedBy"),loggedUser,jObj.getLong("locationId"),jObj.getLong("partyMeetingId"),jObj.getLong("locationscope"));
		}catch (Exception e) {
			LOG.error("Exception raise at updateMeetingAtrPoint", e);
		}
		return Action.SUCCESS;
	}
	
	public String deleteMeetingAtrPoint(){
		try{
			LOG.info("Entered into deleteMeetingAtrPoint");
			jObj = new JSONObject(getTask());
			
			Long loggedUser=0l;
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVo!=null && regVo.getRegistrationID()!=null){
				loggedUser = regVo.getRegistrationID();
			}
			
			status = partyMeetingService.deleteMeetingAtrPoint(jObj.getLong("atrId"),loggedUser);
		}catch (Exception e) {
			LOG.error("Exception raise at deleteMeetingAtrPoint", e);
		}
		return Action.SUCCESS;
	}
	
	public String deletePartyMeetingDocument(){
		try{
			LOG.info("Entered into deletePartyMeetingDocument");
			jObj = new JSONObject(getTask());
			
			status = partyMeetingService.deletePartyMeetingDocument(jObj.getLong("docId"));
		}catch (Exception e) {
			LOG.error("Exception raised at deletePartyMeetingDocument",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAtrPointsForAMeeting(){
		try {
			LOG.info("Entered into getAtrPointsForAMeeting");
			jObj = new JSONObject(getTask());
			
			partyMeetingVO = partyMeetingService.getAtrPointsForAMeeting(jObj.getLong("partyMeetingId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getAtrPointsForAMeeting",e);
		}
		return Action.SUCCESS;
	}
	
	public String getDocumentDetailsForAMeeting(){
		try {
			LOG.info("Entered into getDocumentDetailsForAMeeting");
			jObj = new JSONObject(getTask());			
			
			String accessType=null;
			String accessValue=null;
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO !=null){
				accessType = regVO.getAccessType();
				accessValue = regVO.getAccessValue();
			}
			
			partyMeetingVO = partyMeetingService.getDocumentDetailsForAMeeting(jObj.getLong("partyMeetingId"),accessType,accessValue);
		} catch (Exception e) {
			LOG.error("Exception raised at getDocumentDetailsForAMeeting",e);
		}
		return Action.SUCCESS;
	}
	
	public String getTheMinutePointsForAMeeting(){
		try {
			LOG.info("Entered into getTheMinutePointsForAMeeting");
			jObj = new JSONObject(getTask());
			
			String accessType=null;
			String accessValue=null;
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO !=null){
				accessType = regVO.getAccessType();
				accessValue = regVO.getAccessValue();
			}
			
			partyMeetingVO = partyMeetingService.getTheMinutePointsForAMeeting(jObj.getLong("partyMeetingId"),accessType,accessValue);
		} catch (Exception e) {
			LOG.error("Exception raised at getTheMinutePointsForAMeeting",e);
		}
		return Action.SUCCESS;
	}
	
	public String getMeetingSummaryForLocation(){
		try {
			LOG.info("Entered into getMeetingSummaryForLocation");
			jObj = new JSONObject(getTask());
			
			/*Long locationLevel = jObj.getLong("locationLevel");
			
			List<Long> locationIds = new ArrayList<Long>();
			JSONArray jsonArray = jObj.getJSONArray("locationLevelValues");
			for (int i = 0; i < jsonArray.length(); i++) {
				Long locationId = Long.valueOf(jsonArray.get(i).toString());
				locationIds.add(locationId);
			}
			
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");*/
			
			Long meetinglevel=jObj.getLong("meetinglevel");
			Long typeOfMeeting=jObj.getLong("typeOfMeeting");
			Long locationLevel=jObj.getLong("locationLevel");
			Long stateId=jObj.getLong("stateId");
			Long distId=jObj.getLong("distId");
			Long constId=jObj.getLong("constId");
			Long manTowDivId=jObj.getLong("manTowDivId");
			Long wardPanId=jObj.getLong("wardPanId");
			String startDate=jObj.getString("startDate");
			String endDate=jObj.getString("endDate");
			
			partyMeetingsSummary = partyMeetingService.getMeetingSummaryForLocation(typeOfMeeting,locationLevel,stateId,distId,constId,manTowDivId,wardPanId,startDate,endDate,meetinglevel);
		} catch (Exception e) {
			LOG.error("Exception raised at getMeetingSummaryForLocation",e);
		}
		return Action.SUCCESS;
	}
	
	public String getMeetingCumulativeSummaryForLocation(){
		try {
			LOG.info("Entered into getMeetingCumulativeSummaryForLocation");
			jObj = new JSONObject(getTask());
			
			Long meetinglevel=jObj.getLong("meetinglevel");
			Long typeOfMeeting=jObj.getLong("typeOfMeeting");
			Long locationLevel=jObj.getLong("locationLevel");
			Long stateId=jObj.getLong("stateId");
			Long distId=jObj.getLong("distId");
			Long constId=jObj.getLong("constId");
			Long manTowDivId=jObj.getLong("manTowDivId");
			Long wardPanId=jObj.getLong("wardPanId");
			String startDate=jObj.getString("startDate");
			String endDate=jObj.getString("endDate");
			
			partyMeetingsSummary = partyMeetingService.getMeetingSummaryForLocationCumulative(typeOfMeeting,locationLevel,stateId,distId,constId,manTowDivId,wardPanId,startDate,endDate,meetinglevel);
		} catch (Exception e) {
			LOG.error("Exception raised at getMeetingCumulativeSummaryForLocation",e);
		}
		return Action.SUCCESS;
	}
	
	public String getGroupingSummaryOfLocation(){
		try {
			LOG.info("Entered into getMeetingSummaryForLocation");
			jObj = new JSONObject(getTask());
			
			Long meetinglevel=jObj.getLong("meetinglevel");
			Long typeOfMeeting=jObj.getLong("typeOfMeeting");
			Long locationLevel=jObj.getLong("locationLevel");
			Long stateId=jObj.getLong("stateId");
			Long distId=jObj.getLong("distId");
			Long constId=jObj.getLong("constId");
			Long manTowDivId=jObj.getLong("manTowDivId");
			Long wardPanId=jObj.getLong("wardPanId");
			String startDate=jObj.getString("startDate");
			String endDate=jObj.getString("endDate");
			String groupLocationType = jObj.getString("groupingLocationType");
			
			partyMeetingsSummary = partyMeetingService.getMeetingSummaryForGrouping(typeOfMeeting,locationLevel,stateId,distId,constId,manTowDivId,wardPanId,startDate,endDate,groupLocationType,meetinglevel);
		} catch (Exception e) {
			LOG.error("Exception raised at getMeetingSummaryForLocation",e);
		}
		return Action.SUCCESS;
	}
	
	public String getSummaryForAMeeting(){
		try {
			LOG.info("Entered into getSummaryForAMeeting Action");
			jObj = new JSONObject(getTask());
			
			String accessType=null;
			String accessValue=null;
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO !=null){
				accessType = regVO.getAccessType();
				accessValue = regVO.getAccessValue();
			}
			
			partyMeetingVO = partyMeetingService.getSummaryForAMeeting(jObj.getLong("meetingId"),jObj.getString("type"),accessType,accessValue);
		} catch (Exception e) {
			LOG.error("Entered into getSummaryForAMeeting Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAreaWiseConductedPartyMeetingDetailsForCadre(){
		try {
			LOG.info("Entered into getSummaryForAMeeting Action");
			jObj = new JSONObject(getTask());
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			String searchTypeStr=jObj.getString("searchTypeStr");
			 Long committeeLevelId=jObj.getLong("committeeLevelId");
			 Long committeeLevelValue = jObj.getLong("committeeLevelValue");
			 String formDateStr=jObj.getString("formDateStr");
			 String toDateStr =jObj.getString("toDateStr");
			 String isFirst=jObj.getString("isFirst");
			 int firstRecord =jObj.getInt("firstRecord");
			 int maxResult =jObj.getInt("maxResult");
			 
			meetingTrackingVO = partyMeetingService.getPartyMeetingsDetailsForCadreByCommitteeLevel(tdpCadreId,searchTypeStr , committeeLevelId, committeeLevelValue, formDateStr, toDateStr, isFirst, firstRecord, maxResult);
		} catch (Exception e) {
			LOG.error("Entered into getSummaryForAMeeting Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String getTdpCadreDetailsForPartyMeeting()
	{
		try{
			jObj = new JSONObject(getTask());
			JSONArray designationsArr = jObj.getJSONArray("designationsArr");
			List<String> designationsList = new ArrayList<String>(0);
			if(designationsArr != null && designationsArr.length()>0){
				
				for (int i=0;i<=designationsArr.length()-1;i++) {
					String designationStr = designationsArr.get(i) != null ? designationsArr.get(i).toString():"";
					if(designationStr != null && designationStr.trim().length()>0)
						designationsList.add(designationStr.trim());
							
				}
			}
			partyMeetingWSVO =  partyMeetingService.getTdpCadreDetailsForPartyMeeting(jObj.getLong("meetingId"),jObj.getString("searchType"),designationsList);
		}
		catch (Exception e) {
			LOG.error("Entered into getTdpCadreDetailsForPartyMeeting Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String getLevelWiseMeetingDetails(){
		try{
			
			RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
			Long userId=0l;
			if(regVO!=null){
				userId = regVO.getRegistrationID();
			}
			jObj = new JSONObject(getTask());
			
			partyMeetingVOList = partyMeetingService.getLevelWiseMeetingDetails(jObj.getString("fromDate"),jObj.getString("toDate"),userId,jObj.getLong("stateId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getLevelWiseMeetingDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String updateConductedDetails(){
		try{
			
			jObj = new JSONObject(getTask());
			
			status = partyMeetingService.updateConductedDetails(jObj.getLong("meetingId"),jObj.getString("isConducted"),jObj.getString("remarks"),jObj.getString("conductedDate"));
			
		}catch (Exception e) {
			LOG.error("Entered into updateConductedDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String updateConductedStatus(){
		try{
			
			Long loggedUser=0l;
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVo!=null && regVo.getRegistrationID()!=null){
				loggedUser = regVo.getRegistrationID();
			}
			
			jObj = new JSONObject(getTask());
			
			status = partyMeetingService.updateConductedStatus(jObj.getLong("meetingId"),jObj.getString("isConducted"),loggedUser);
			
		}catch (Exception e) {
			LOG.error("Entered into updateConductedDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String updateConductedDate(){
		try{
			
			Long loggedUser=0l;
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVo!=null && regVo.getRegistrationID()!=null){
				loggedUser = regVo.getRegistrationID();
			}
			
			jObj = new JSONObject(getTask());
			
			status = partyMeetingService.updateConductedDate(jObj.getLong("meetingId"),jObj.getString("conductedDate"),loggedUser);
			
		}catch (Exception e) {
			LOG.error("Entered into updateConductedDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String updateConductedReason(){
		try{
			
			Long loggedUser=0l;
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVo!=null && regVo.getRegistrationID()!=null){
				loggedUser = regVo.getRegistrationID();
			}
			
			jObj = new JSONObject(getTask());
			String remarks = jObj.getString("remarks");
			
			status = partyMeetingService.updateConductedReason(jObj.getLong("meetingId"),remarks,loggedUser);
			
		}catch (Exception e) {
			LOG.error("Entered into updateConductedDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getLocationWisePartyMeetings(){
		try{
			
			jObj = new JSONObject(getTask());
			String locationType = jObj.getString("locationType");
			Long   locationValue = jObj.getLong("locationValue");
			String startDateString = jObj.getString("startDateString");
			String endDateString = jObj.getString("endDateString");
			partyMeetingVOsList = partyMeetingService.getLocationWisePartyMeetings(locationType,locationValue,startDateString,endDateString);
		}catch (Exception e) {
			LOG.error("Entered into updateConductedDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
	//List<PartyMeetingStatusVO> getMeetingDetailsForALevelByLocationId(String locationType,Long locationValue,Long partyMeetingLevelId,int month,int year,String startDateString,String endDateString)
	public String getMeetingDetailsForALevelByLocationId(){
		try{
			
			jObj = new JSONObject(getTask());
			String locationType = jObj.getString("locationType");
			Long   locationValue = jObj.getLong("locationValue");
			
			JSONArray partyMeetingLevelIdsArr = jObj.getJSONArray("partyMeetingLevelIds");
			List<Long> partyMeetingLevelIds = new ArrayList<Long>(0);
			if(partyMeetingLevelIdsArr != null && partyMeetingLevelIdsArr.length()>0){
				for (int i=0;i<partyMeetingLevelIdsArr.length();i++) {
					partyMeetingLevelIds.add(Long.parseLong(partyMeetingLevelIdsArr.get(i).toString()));
				}
			}
			
			int   month = jObj.getInt("month");
			int   year = jObj.getInt("year");
			meetingStatusVOs = partyMeetingService.getMeetingDetailsForALevelByLocationId(locationType, locationValue, partyMeetingLevelIds, month, year);  
		}catch (Exception e) {  
			LOG.error("Entered into updateConductedDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getPartyMeetingMinuteRetrieveDetails(){
		try{
			
			jObj = new JSONObject(getTask());
			Long minuteId = jObj.getLong("minuteId");
		
			pmMinutelist = partyMeetingService.getPartyMeetingMinuteRetrieveDetails(minuteId);  
		}catch (Exception e) {  
			LOG.error("Entered into updateConductedDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getUpdationDetails(){
		try{
			
			jObj = new JSONObject(getTask());
			JSONArray distIdsListsArr = jObj.getJSONArray("distIdsList");
			List<Long> distIds = new ArrayList<Long>(0);
			if(distIdsListsArr != null && distIdsListsArr.length()>0){
				for (int i=0;i<distIdsListsArr.length();i++) {
					distIds.add(Long.parseLong(distIdsListsArr.get(i).toString()));
				}
			}
			JSONArray constIdsListsArr = jObj.getJSONArray("constIdsList");
			List<Long> constIds = new ArrayList<Long>(0);
			if(constIdsListsArr != null && constIdsListsArr.length()>0){
				for (int i=0;i<constIdsListsArr.length();i++) {
					constIds.add(Long.parseLong(constIdsListsArr.get(i).toString()));
				}
			}
			String locationType = jObj.getString("locationType");
			String thridPartyStatus =jObj.getString("thridPartyStatus");
			
			partyMeetingVOList = partyMeetingService.getUpdateDetails(jObj.getLong("levelId"),jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("status"),distIds,constIds,locationType,thridPartyStatus);
		}catch(Exception e){
			LOG.error("Entered into  Action",e);
		}
		return Action.SUCCESS;
	}
	public String getDocumentList(){
		try{
			jObj = new JSONObject(getTask());
			partyMeetingVO = partyMeetingService.getDocumentsForMeetingId(jObj.getLong("partyMeetingId"));
		}catch(Exception e){
			LOG.error("Entered into getDocumentList Action",e);
		}
		return Action.SUCCESS;
	}
}
