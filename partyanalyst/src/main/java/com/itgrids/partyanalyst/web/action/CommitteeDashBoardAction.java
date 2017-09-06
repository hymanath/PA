package com.itgrids.partyanalyst.web.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.ByeElectionVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeReportVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeRolesInfoVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CommitteeResultVO;
import com.itgrids.partyanalyst.dto.CommitteeSummaryVO;
import com.itgrids.partyanalyst.dto.EventDocumentVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO1;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IActivityService;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ITdpCadreReportService;
import com.itgrids.partyanalyst.webservice.android.abstractservice.IWebServiceHandlerService1;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CommitteeDashBoardAction extends ActionSupport implements ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3145620802823411748L;
	private HttpServletRequest         			request;
	private EntitlementsHelper 					entitlementsHelper;
	private JSONObject							jObj;
	private String 								task,pageAccessType;
	private LocationWiseBoothDetailsVO          locationWiseBoothDetailsVO;
	private List<LocationWiseBoothDetailsVO>    locationWiseBoothDetailsVOList;
	private ICadreCommitteeService				cadreCommitteeService;
	@Autowired 
	private IWebServiceHandlerService1 			webServiceHandlerService1;
	private static final Logger         		LOG = Logger.getLogger(CommitteeDashBoardAction.class);
	
	private CadreCommitteeReportVO          	cadreCommitteeReportVO;
	private List<CommitteeSummaryVO>   		 	districtWiseSummaryList,constiWiseSummaryList;
	private List<CadreCommitteeReportVO> 		cadreCommitteeReportVOList;
	private List<CadreCommitteeMemberVO> 		cadreCommitteeMemberVOList;
	private List<IdNameVO>  					idNameVOList;
	private CommitteeSummaryVO 					constiSummaryVO;
	private ITdpCadreReportService 				tdpCadreReportService;
	private String 								accessConstituency;
	private Long								accessConstituencyId;
	private ICadreRegistrationService 			cadreRegistrationService;
	private CadreCommitteeMemberVO 				boothsInfo;
	private ByeElectionVO 						byeEleInfo;
	private BasicVO 							basicVO = new BasicVO();
	private CadreCommitteeRolesInfoVO			cadreCommitteeRolesInfoVO;
	private Long								distId;
	private List<String> 						constiList;
	private ActivityVO 							activityVO = new ActivityVO() ;
	private InputStream 						inputStream;
	private ResultStatus 						resultStatus;
	private List<ActivityVO>                    activitiesVOList;
	private List<EventDocumentVO> docsList;
	private List<BasicVO>                       basicVOList = null;
	private IActivityService activityService;
	private LocationWiseBoothDetailsVO1          locationWiseBoothDetailsVO1;
	private List<String>						ageRangeList;
	private List<SelectOptionVO>				cadreRolesVOList;
	private List<LocationWiseBoothDetailsVO>    locations;
	private List<IdNameVO>  castes;
	private List<GenericVO>						genericVOList;
	private List<CadreCommitteeVO> 				committeeVoList;
	private CommitteeResultVO committeeResultVO;
	private CadreCommitteeVO cadreCommitteeVO;
	
	
	public CadreCommitteeVO getCadreCommitteeVO() {
		return cadreCommitteeVO;
	}
	public void setCadreCommitteeVO(CadreCommitteeVO cadreCommitteeVO) {
		this.cadreCommitteeVO = cadreCommitteeVO;
	}
	public List<CadreCommitteeVO> getCommitteeVoList() {
		return committeeVoList;
	}
	public void setCommitteeVoList(List<CadreCommitteeVO> committeeVoList) {
		this.committeeVoList = committeeVoList;
	}
	public List<GenericVO> getGenericVOList() {
		return genericVOList;
	}
	public void setGenericVOList(List<GenericVO> genericVOList) {
		this.genericVOList = genericVOList;
	}
	public List<String> getAgeRangeList() {
		return ageRangeList;
	}
	public void setAgeRangeList(List<String> ageRangeList) {
		this.ageRangeList = ageRangeList;
	}
	public List<SelectOptionVO> getCadreRolesVOList() {
		return cadreRolesVOList;
	}
	public void setCadreRolesVOList(List<SelectOptionVO> cadreRolesVOList) {
		this.cadreRolesVOList = cadreRolesVOList;
	}
	public List<LocationWiseBoothDetailsVO> getLocations() {
		return locations;
	}
	public void setLocations(List<LocationWiseBoothDetailsVO> locations) {
		this.locations = locations;
	}
	public List<IdNameVO> getCastes() {
		return castes;
	}
	public void setCastes(List<IdNameVO> castes) {
		this.castes = castes;
	}
	public LocationWiseBoothDetailsVO1 getLocationWiseBoothDetailsVO1() {
		return locationWiseBoothDetailsVO1;
	}
	public void setLocationWiseBoothDetailsVO1(
			LocationWiseBoothDetailsVO1 locationWiseBoothDetailsVO1) {
		this.locationWiseBoothDetailsVO1 = locationWiseBoothDetailsVO1;
	}
	public void setActivityService(IActivityService activityService) {
		this.activityService = activityService;
	}
	public List<BasicVO> getBasicVOList() {
		return basicVOList;
	}
	public void setBasicVOList(List<BasicVO> basicVOList) {
		this.basicVOList = basicVOList;
	}
	public List<EventDocumentVO> getDocsList() {
		return docsList;
	}
	public void setDocsList(List<EventDocumentVO> docsList) {
		this.docsList = docsList;
	}
	public List<LocationWiseBoothDetailsVO> getLocationWiseBoothDetailsVOList() {
		return locationWiseBoothDetailsVOList;
	}
	public void setLocationWiseBoothDetailsVOList(
			List<LocationWiseBoothDetailsVO> locationWiseBoothDetailsVOList) {
		this.locationWiseBoothDetailsVOList = locationWiseBoothDetailsVOList;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public ActivityVO getActivityVO() {
		return activityVO;
	}
	public void setActivityVO(ActivityVO activityVO) {
		this.activityVO = activityVO;
	}
	public List<String> getConstiList() {
		return constiList;
	}
	public void setConstiList(List<String> constiList) {
		this.constiList = constiList;
	}
	public Long getDistId() {
		return distId;
	}
	public void setDistId(Long distId) {
		this.distId = distId;
	}
	public CadreCommitteeRolesInfoVO getCadreCommitteeRolesInfoVO() {
		return cadreCommitteeRolesInfoVO;
	}
	public void setCadreCommitteeRolesInfoVO(
			CadreCommitteeRolesInfoVO cadreCommitteeRolesInfoVO) {
		this.cadreCommitteeRolesInfoVO = cadreCommitteeRolesInfoVO;
	}
	public BasicVO getBasicVO() {
		return basicVO;
	}
	public void setBasicVO(BasicVO basicVO) {
		this.basicVO = basicVO;
	}
	public ByeElectionVO getByeEleInfo() {
		return byeEleInfo;
	}
	public void setByeEleInfo(ByeElectionVO byeEleInfo) {
		this.byeEleInfo = byeEleInfo;
	}
	
	public String getPageAccessType() {
		return pageAccessType;
	}
	public void setPageAccessType(String pageAccessType) {
		this.pageAccessType = pageAccessType;
	}
	
	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}
	public String getAccessConstituency() {
		return accessConstituency;
	}
	public void setAccessConstituency(String accessConstituency) {
		this.accessConstituency = accessConstituency;
	}
	public Long getAccessConstituencyId() {
		return accessConstituencyId;
	}
	public void setAccessConstituencyId(Long accessConstituencyId) {
		this.accessConstituencyId = accessConstituencyId;
	}
	public ITdpCadreReportService getTdpCadreReportService() {
		return tdpCadreReportService;
	}
	public void setTdpCadreReportService(
			ITdpCadreReportService tdpCadreReportService) {
		this.tdpCadreReportService = tdpCadreReportService;
	}
	public CommitteeSummaryVO getConstiSummaryVO() {
		return constiSummaryVO;
	}
	public void setConstiSummaryVO(CommitteeSummaryVO constiSummaryVO) {
		this.constiSummaryVO = constiSummaryVO;
	}
	public List<CommitteeSummaryVO> getConstiWiseSummaryList() {
		return constiWiseSummaryList;
	}
	public void setConstiWiseSummaryList(
			List<CommitteeSummaryVO> constiWiseSummaryList) {
		this.constiWiseSummaryList = constiWiseSummaryList;
	}
	public List<CommitteeSummaryVO> getDistrictWiseSummaryList() {
		return districtWiseSummaryList;
	}
	public void setDistrictWiseSummaryList(
			List<CommitteeSummaryVO> districtWiseSummaryList) {
		this.districtWiseSummaryList = districtWiseSummaryList;
	}
	public List<CadreCommitteeReportVO> getCadreCommitteeReportVOList() {
		return cadreCommitteeReportVOList;
	}
	public void setCadreCommitteeReportVOList(
			List<CadreCommitteeReportVO> cadreCommitteeReportVOList) {
		this.cadreCommitteeReportVOList = cadreCommitteeReportVOList;
	}
	public LocationWiseBoothDetailsVO getLocationWiseBoothDetailsVO() {
		return locationWiseBoothDetailsVO;
	}
	public void setLocationWiseBoothDetailsVO(
			LocationWiseBoothDetailsVO locationWiseBoothDetailsVO) {
		this.locationWiseBoothDetailsVO = locationWiseBoothDetailsVO;
	}
	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}
	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
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
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public CadreCommitteeReportVO getCadreCommitteeReportVO() {
		return cadreCommitteeReportVO;
	}
	public void setCadreCommitteeReportVO(
			CadreCommitteeReportVO cadreCommitteeReportVO) {
		this.cadreCommitteeReportVO = cadreCommitteeReportVO;
	}
	
	
	public List<CadreCommitteeMemberVO> getCadreCommitteeMemberVOList() {
		return cadreCommitteeMemberVOList;
	}
	public void setCadreCommitteeMemberVOList(
			List<CadreCommitteeMemberVO> cadreCommitteeMemberVOList) {
		this.cadreCommitteeMemberVOList = cadreCommitteeMemberVOList;
	}
	
	
	public List<IdNameVO> getIdNameVOList() {
		return idNameVOList;
	}
	public void setIdNameVOList(List<IdNameVO> idNameVOList) {
		this.idNameVOList = idNameVOList;
	}
	
	public CadreCommitteeMemberVO getBoothsInfo() {
		return boothsInfo;
	}
	public void setBoothsInfo(CadreCommitteeMemberVO boothsInfo) {
		this.boothsInfo = boothsInfo;
	}
	
	
	public List<ActivityVO> getActivitiesVOList() {
		return activitiesVOList;
	}
	public void setActivitiesVOList(List<ActivityVO> activitiesVOList) {
		this.activitiesVOList = activitiesVOList;
	}
	
	public CommitteeResultVO getCommitteeResultVO() {
		return committeeResultVO;
	}
	public void setCommitteeResultVO(CommitteeResultVO committeeResultVO) {
		this.committeeResultVO = committeeResultVO;
	}
	public String execute(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("TDP_COMMITTEE_ADMIN".trim()) || entitlements.contains("TDP_COMMITTEE_AREAWISE_ACCESS".trim()) 
					|| entitlements.contains("COMMITTEE_DETAILED_REPORT".trim()) || entitlements.contains("COMMITTEE_MGT".trim()) 
					|| entitlements.contains("COMMITTEE_DETAILED_REPORT_GROUP".trim()) || entitlements.contains("PARTY_ACTIVITY_UPDATE".trim()))){
				noaccess = true ;
			}
		/*if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"TDP_COMMITTEE_ADMIN") ||
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"TDP_COMMITTEE_AREAWISE_ACCESS") ||
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"COMMITTEE_DETAILED_REPORT") ||
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"COMMITTEE_MGT") ||
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"COMMITTEE_DETAILED_REPORT_GROUP") ||
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"PARTY_ACTIVITY_UPDATE"))){
			noaccess = true ;
		}*/
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		pageAccessType = cadreCommitteeService.userAccessTypeDetailsForDashBoard(regVO.getRegistrationID(),regVO.getAccessType(),Long.valueOf(regVO.getAccessValue().trim()));
		String accessType = regVO.getAccessType();
		if(accessType.equalsIgnoreCase("MLA") || accessType.equalsIgnoreCase("MP"))
		{
			pageAccessType = accessType;
		}
		
		}	
		return Action.SUCCESS;
	}
	
	
	public String getDashBoardLocationWiseDetailsAction(){
		try{
			
			boolean noaccess = false;
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO != null)
			{
				String accessType = regVO.getAccessType();
				Long accessValue = Long.valueOf(regVO.getAccessValue());
				
				jObj = new JSONObject(getTask());
				JSONArray levelIdsArr = jObj.getJSONArray("levelIdsArr");			
				List<Long> levelIds = new ArrayList<Long>();
					
				if(levelIdsArr !=null && levelIdsArr.length() >0){
					for(int i=0; i<levelIdsArr.length(); i++ ){
						levelIds.add(Long.valueOf(levelIdsArr.get(i).toString().trim()));
					}
				}   
				String state =jObj.getString("state");
				String startDate=jObj.getString("startDate");
				String endDate=jObj.getString("endDate");
				
				List<Long> committeeSpanTypeIdsLsit = new ArrayList<Long>(0);
				try {
					JSONArray committeeSpanTypeIdsArr = jObj.getJSONArray("committeeSpanTypeIdsList");
					if(committeeSpanTypeIdsArr != null && committeeSpanTypeIdsArr.length()>0){
						for (int i = 0; i < committeeSpanTypeIdsArr.length(); i++) {
							committeeSpanTypeIdsLsit.add(committeeSpanTypeIdsArr.get(i) != null ? Long.valueOf(committeeSpanTypeIdsArr.get(i).toString()):0L);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				cadreCommitteeReportVO = cadreCommitteeService.getCommitteeDetailsByLocation(state,levelIds,startDate,endDate,regVO.getRegistrationID(),accessType,accessValue,committeeSpanTypeIdsLsit);
			}
			else
			{
				noaccess = true;
			}
			
			if(noaccess)
					return Action.ERROR;
			
		}catch(Exception e){
			LOG.error("Exception Occured In getDashBoardLocationWiseDetailsAction method "+e);
		}
		return Action.SUCCESS;
	}
	
	
	public String getTotalCommitteeCntsByState(){
		try{
			boolean noaccess = false;
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO != null)
			{
				String accessType = regVO.getAccessType();
				Long accessValue = Long.valueOf(regVO.getAccessValue());
				
				jObj = new JSONObject(getTask());
				
				String state =jObj.getString("state");
				String startDateStr = null;
				String endDateStr = null;
				List<Long> committeeSpanTypeIdsLsit = new ArrayList<Long>(0);
				try {
					startDateStr = jObj.getString("startDate");
					endDateStr = jObj.getString("endDate");
					JSONArray committeeSpanTypeIdsArr = jObj.getJSONArray("committeeSpanTypeIdsList");
					if(committeeSpanTypeIdsArr != null && committeeSpanTypeIdsArr.length()>0){
						for (int i = 0; i < committeeSpanTypeIdsArr.length(); i++) {
							committeeSpanTypeIdsLsit.add(committeeSpanTypeIdsArr.get(i) != null ? Long.valueOf(committeeSpanTypeIdsArr.get(i).toString()):0L);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				cadreCommitteeReportVO = cadreCommitteeService.getTotalCommitteeDetailsByLocation(state,regVO.getRegistrationID(),accessType,accessValue,committeeSpanTypeIdsLsit,startDateStr,endDateStr);
			}
			else{
				noaccess = true;
			}
			
			if(noaccess){
				return "error";
			}
			
		}catch(Exception e){
			LOG.error("Exception Occured In getDashBoardLocationWiseDetailsAction method "+e);
		}
		return Action.SUCCESS;
	}
	
	public String getDistrictWiseCommittesSummary(){
		LOG.debug(" Entered Into getDistrictWiseCommittesSummary");
		try{
			
			boolean noaccess = false;
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO != null)
			{
				String accessType = regVO.getAccessType();
				Long accessValue = Long.valueOf(regVO.getAccessValue());
				
				jObj = new JSONObject(getTask());
				String state =jObj.getString("state");
				String mandalCheck = jObj.getString("mandalCheck");
				String villageCheck =  jObj.getString("villageCheck");
				String districtCommCheck = jObj.getString("districtCommCheck");
				String startDate = jObj.getString("startDate");
				String endDate = jObj.getString("endDate");
				//Long districtId = jObj.getLong("districtId");
				//Long constiteuncyId = jObj.getLong("constiteuncyId");
				//Long mandalId = jObj.getLong("mandalId");
				//Long panchayatId = jObj.getLong("panchayatId");
				
				List<Long> committeeSpanTypeIdsLsit = new ArrayList<Long>(0);
				try {
					JSONArray committeeSpanTypeIdsArr = jObj.getJSONArray("committeeSpanTypeIdsList");
					if(committeeSpanTypeIdsArr != null && committeeSpanTypeIdsArr.length()>0){
						for (int i = 0; i < committeeSpanTypeIdsArr.length(); i++) {
							committeeSpanTypeIdsLsit.add(committeeSpanTypeIdsArr.get(i) != null ? Long.valueOf(committeeSpanTypeIdsArr.get(i).toString()):0L);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				districtWiseSummaryList = cadreCommitteeService.getDistrictWiseCommittesSummary(state, startDate,endDate,regVO.getRegistrationID(),accessType,accessValue,mandalCheck,villageCheck,districtCommCheck,committeeSpanTypeIdsLsit);
			}
			else{
				noaccess = true;
			}
			
			if(noaccess){
				return Action.ERROR;
			}
			
		}catch (Exception e) {
			LOG.error(" Exception Raised In getDistrictWiseCommittesSummary" +e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getStartedAffliCommitteesCountByLocation(){
		try{
			jObj = new JSONObject(getTask());
			JSONArray levelIdsArr = jObj.getJSONArray("levelIdsArr");			
			List<Long> levelIds = new ArrayList<Long>();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO != null)
			{
				String accessType = regVO.getAccessType();
				Long accessValue = Long.valueOf(regVO.getAccessValue());
					
			if(levelIdsArr !=null && levelIdsArr.length() >0){
				for(int i=0; i<levelIdsArr.length(); i++ ){
					levelIds.add(Long.valueOf(levelIdsArr.get(i).toString().trim()));
				}
			}
			String state =jObj.getString("state");
			String startdate=jObj.getString("startDate");
			String endDate=jObj.getString("endDate");
			String committeeType = jObj.getString("committeeType");
			List<Long> committeeSpanTypeIdsLsit = new ArrayList<Long>();
			JSONArray committeeEnrollmentIds=jObj.getJSONArray("committeeSpanTypeIdsList");
			if(committeeEnrollmentIds!=null &&  committeeEnrollmentIds.length()>0){
				for( int i=0;i<committeeEnrollmentIds.length();i++){
					committeeSpanTypeIdsLsit.add(Long.valueOf(committeeEnrollmentIds.getString(i))); 
				}
			}
			cadreCommitteeReportVOList = cadreCommitteeService.getStartedAffliCommitteesCountByLocation(state,levelIds,startdate,endDate,accessType,accessValue,regVO.getRegistrationID(), committeeType,committeeSpanTypeIdsLsit);
			}
			
		}catch(Exception e){
			LOG.error("Exception Occured In getDashBoardLocationWiseDetailsAction method "+e);
		}
		return Action.SUCCESS;
	}
	
	public String getMembersRangeCountByLocation(){
		try{
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			jObj = new JSONObject(getTask());
			JSONArray levelIdsArr = jObj.getJSONArray("levelIdsArr");			
			List<Long> levelIds = new ArrayList<Long>();
				
			if(levelIdsArr !=null && levelIdsArr.length() >0){
				for(int i=0; i<levelIdsArr.length(); i++ ){
					levelIds.add(Long.valueOf(levelIdsArr.get(i).toString().trim()));
				}
			}
			String state =jObj.getString("state");
			Long committeeId = jObj.getLong("committeeId");
			String startdate=jObj.getString("startDate");
			String endDate=jObj.getString("endDate");
			String committeeType = jObj.getString("committeeType");
			String accessType = regVo.getAccessType();
			Long accessValue = Long.valueOf(regVo.getAccessValue());
			List<Long> committeeSpanTypeIdsLsit = new ArrayList<Long>();
			JSONArray committeeEnrollmentIds=jObj.getJSONArray("committeeSpanTypeIdsList");
			if(committeeEnrollmentIds!=null &&  committeeEnrollmentIds.length()>0){
				for( int i=0;i<committeeEnrollmentIds.length();i++){
					committeeSpanTypeIdsLsit.add(Long.valueOf(committeeEnrollmentIds.getString(i))); 
				}
			}
			cadreCommitteeReportVOList = cadreCommitteeService.getMembersRangeCountByLocation(state,levelIds,committeeId,startdate,endDate,accessType,accessValue,regVo.getRegistrationID(),committeeType,committeeSpanTypeIdsLsit);
		
			
		}catch(Exception e){
			LOG.error("Exception Occured In getDashBoardLocationWiseDetailsAction method "+e);
		}
		return Action.SUCCESS;
	}



	
	public String getConstituencyWiseCommittesSummary(){
		LOG.debug(" Entered Into getConstituencyWiseCommittesSummary");
		try{
			boolean noaccess = false;
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			List<Long> committeeEnrollmentIdsLst = new ArrayList<Long>();
			List<Long> levelIdsList = new ArrayList<Long>();
			String reqLocationTypeStr =  null;
			if(regVO != null)
			{
				String accessType = regVO.getAccessType();
				Long accessValue = Long.valueOf(regVO.getAccessValue());
				
				jObj = new JSONObject(getTask());
				String state =jObj.getString("state");
				String mandalCheck = jObj.getString("mandalCheck");
				String villageCheck =  jObj.getString("villageCheck");
				String startDate = jObj.getString("startDate");
				String endDate = jObj.getString("endDate");
			
				
				JSONArray committeeEnrollmentIds=jObj.getJSONArray("committeeEnrollmentId");
				if(committeeEnrollmentIds!=null &&  committeeEnrollmentIds.length()>0){
					for( int i=0;i<committeeEnrollmentIds.length();i++){
						committeeEnrollmentIdsLst.add(Long.valueOf(committeeEnrollmentIds.getString(i))); 
					}
				}
				
			
			reqLocationTypeStr = jObj.getString("reqLocationTypeStr");
			
			JSONArray levelIdLsit=jObj.getJSONArray("levelIds");
			if(levelIdLsit!=null &&  levelIdLsit.length()>0){
				for( int i=0;i<levelIdLsit.length();i++){
					levelIdsList.add(Long.valueOf(levelIdLsit.getString(i))); 
				}
			}
			
				constiWiseSummaryList = cadreCommitteeService.getConstituencyWiseCommittesSummary(state, startDate, endDate,regVO.getRegistrationID(),accessType,accessValue,mandalCheck,villageCheck,reqLocationTypeStr,committeeEnrollmentIdsLst,levelIdsList);
			}
			else
			{
				noaccess = true;
			}
			pageAccessType = cadreCommitteeService.userAccessTypeDetailsForDashBoard(regVO.getRegistrationID(),regVO.getAccessType(),Long.valueOf(regVO.getAccessValue().trim()));
			String accessType = regVO.getAccessType();
			if(accessType.equalsIgnoreCase("MLA") || accessType.equalsIgnoreCase("MP"))
			{
				pageAccessType = accessType;
			}
			if(noaccess)
				return Action.ERROR;
			
		}catch (Exception e) {
			LOG.error(" Exception Raised In getConstituencyWiseCommittesSummary" +e);
		}
		
		return Action.SUCCESS;
	}
	
	

	public String getConstituencyWiseCommittesSummaryForDistrict(){
		LOG.debug(" Entered Into getConstituencyWiseCommittesSummaryForDistrict");
		try{
			boolean noaccess = false;
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO != null)
			{
				String accessType = regVO.getAccessType();
				Long accessValue = Long.valueOf(regVO.getAccessValue());
				
				jObj = new JSONObject(getTask());
				String state =jObj.getString("state");
				String mandalCheck = jObj.getString("mandalCheck");
				String villageCheck =  jObj.getString("villageCheck");
				String startDate = jObj.getString("startDate");
				String endDate = jObj.getString("endDate");
				
				List<Long> committeeEnrollmentIdsLst = new ArrayList<Long>();
				JSONArray committeeEnrollmentIds=jObj.getJSONArray("committeeEnrollmentIdsArr");
				if(committeeEnrollmentIds!=null &&  committeeEnrollmentIds.length()>0){
					for( int i=0;i<committeeEnrollmentIds.length();i++){
						committeeEnrollmentIdsLst.add(Long.valueOf(committeeEnrollmentIds.getString(i))); 
					}
				}
				
				if(jObj.getString("task").equalsIgnoreCase("District"))
				constiWiseSummaryList = cadreCommitteeService.getConstituencyWiseCommittesSummaryForDistrict(state, startDate, endDate,regVO.getRegistrationID(),jObj.getString("accessType"),jObj.getLong("accessValue"),mandalCheck,villageCheck,committeeEnrollmentIdsLst);
				if(jObj.getString("task").equalsIgnoreCase("Constituency"))
					constiWiseSummaryList = cadreCommitteeService.getConstituencyWiseCommittesSummaryForMandal(state, startDate, endDate,regVO.getRegistrationID(),jObj.getString("accessType"),jObj.getLong("accessValue"),mandalCheck,villageCheck,committeeEnrollmentIdsLst);	
			}
			
			
		}catch (Exception e) {
			LOG.error(" Exception Raised In getConstituencyWiseCommittesSummary" +e);
		}
		
		return Action.SUCCESS;
	}
	public String getSummaryDetailsPopUp(){
		try{
			jObj = new JSONObject(getTask());
			
			List<Long> committeeEnrollmentIdsLst = new ArrayList<Long>();
			JSONArray committeeEnrollmentIds=jObj.getJSONArray("committeeEnrollmentId");
			if(committeeEnrollmentIds!=null &&  committeeEnrollmentIds.length()>0){
				for( int i=0;i<committeeEnrollmentIds.length();i++){
					committeeEnrollmentIdsLst.add(Long.valueOf(committeeEnrollmentIds.getString(i))); 
				}
			}
			
			List<Long> levelIdsLsit = new ArrayList<Long>();
			JSONArray levelIdLsit=jObj.getJSONArray("levelIds");
			if(levelIdLsit!=null &&  levelIdLsit.length()>0){
				for( int i=0;i<levelIdLsit.length();i++){
					levelIdsLsit.add(Long.valueOf(levelIdLsit.getString(i))); 
				}
			}
			
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate"); 
			
			districtWiseSummaryList = cadreCommitteeService.getSummaryDetails(jObj.getString("constituencyId"),jObj.getString("reqLocationType"),startDate,endDate,committeeEnrollmentIdsLst,levelIdsLsit);
		}catch(Exception e){
			LOG.error("Exception Occured In getSummaryDetailsPopUp method "+e);			
		}
		return Action.SUCCESS;
	}
	public String gettingMandalAndMuncipalAndDivisonSummaryPopUp(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> committeeEnrollmentIdsLst = new ArrayList<Long>();
			JSONArray committeeEnrollmentIds=jObj.getJSONArray("committeeEnrollmentId");
			if(committeeEnrollmentIds!=null &&  committeeEnrollmentIds.length()>0){
				for( int i=0;i<committeeEnrollmentIds.length();i++){
					committeeEnrollmentIdsLst.add(Long.valueOf(committeeEnrollmentIds.getString(i))); 
				}
			}
			
			List<Long> levelIdsLsit = new ArrayList<Long>();
			JSONArray levelIdLsit=jObj.getJSONArray("levelIds");
			if(levelIdLsit!=null &&  levelIdLsit.length()>0){
				for( int i=0;i<levelIdLsit.length();i++){
					levelIdsLsit.add(Long.valueOf(levelIdLsit.getString(i))); 
				}
			}
			
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate"); 
			districtWiseSummaryList= cadreCommitteeService.gettingMandalAndMuncipalAndDivisonSummary(jObj.getString("constituencyId"),committeeEnrollmentIdsLst,startDate,endDate,jObj.getString("reqLocationType"),levelIdsLsit);
		}catch(Exception e){
			LOG.error("Exception Occured In gettingMandalAndMuncipalAndDivisonSummary method "+e);			
		}
		return Action.SUCCESS;
	}
public String getCommitteeDetailsByStatusPopUp(){

		
		try{
			jObj = new JSONObject(getTask());
			List<Long> committeeEnrollmentIdsLst = new ArrayList<Long>();
			JSONArray committeeEnrollmentIds=jObj.getJSONArray("committeeEnrollmentId");
			if(committeeEnrollmentIds!=null &&  committeeEnrollmentIds.length()>0){
				for( int i=0;i<committeeEnrollmentIds.length();i++){
					committeeEnrollmentIdsLst.add(Long.valueOf(committeeEnrollmentIds.getString(i))); 
				}
			}
			String startDate = jObj.getString("fromDate");
			String endDate = jObj.getString("toDate");
			if(jObj.getString("task").equalsIgnoreCase("memberCnt")){
				cadreCommitteeMemberVOList = cadreCommitteeService.getCommitteeDetailsByStatus(jObj.getLong("basicCommitteetypeId"),jObj.getString("status"),jObj.getLong("levelId"),jObj.getString("constituencyId"),committeeEnrollmentIdsLst,startDate,endDate);
			}
			else if(jObj.getString("task").equalsIgnoreCase("memberInfo")){
				cadreCommitteeMemberVOList = cadreCommitteeService.getCommitteeMemberDetails(jObj.getLong("basicCommitteetypeId"),jObj.getLong("locationId"),jObj.getLong("levelId"),jObj.getString("status"),committeeEnrollmentIdsLst,startDate,endDate);
			}
			/*else if(jObj.getString("task").equalsIgnoreCase("committeComplete")){
				cadreCommitteeMemberVOList = cadreCommitteeService.setCommitteConfirmation(jObj.getLong("basicCommitteetypeId"),jObj.getLong("locationId"),jObj.getLong("levelId"),committeeEnrollmentIdsLst,startDate,endDate);
			}*/
			else if(jObj.getString("task").equalsIgnoreCase("deleterole")){
				cadreCommitteeMemberVOList = cadreCommitteeService.deleteCadreRole(jObj.getLong("tdpcommitteeMemberId"),committeeEnrollmentIdsLst,startDate,endDate);
			}
				
		}catch(Exception e){
			LOG.error("Exception occured in getCommitteeDetailsByStatus() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}

   public String committeeConfirmation(){
	   
	   try{
		   
		   jObj = new JSONObject(getTask());
			List<Long> committeeEnrollmentIdsLst = new ArrayList<Long>();
			JSONArray committeeEnrollmentIds=jObj.getJSONArray("committeeEnrollmentId");
			if(committeeEnrollmentIds!=null &&  committeeEnrollmentIds.length()>0){
				for( int i=0;i<committeeEnrollmentIds.length();i++){
					committeeEnrollmentIdsLst.add(Long.valueOf(committeeEnrollmentIds.getString(i))); 
				}
			}
			String startDate = jObj.getString("fromDate");
			String endDate = jObj.getString("toDate");
			committeeResultVO = cadreCommitteeService.setCommitteConfirmation(jObj.getLong("basicCommitteetypeId"),jObj.getLong("locationId"),jObj.getLong("levelId"),committeeEnrollmentIdsLst,startDate,endDate);
	  }catch (Exception e) {
		  LOG.error("Exception occured in committeeConfirmation() At CadreCommitteeAction ",e);
	  }
	   return Action.SUCCESS;
   }

public String constituencyCommitteeSummaryAction()
{
	RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
	boolean noaccess = false;
	boolean cadreUser = false;
	
	if(regVO==null){
		return "input";
	}
	
	List<String> entitlements = null;
	if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
		entitlements = regVO.getEntitlements();
		if(!entitlements.contains("CADRE_COMMITTEE_MANAGEMENT".trim()) && !entitlements.contains("TDP_COMMITTEE_ADMIN".trim())){
			noaccess = true ;
		}
		
		if(entitlements.contains("CADRE_COMMITTEE_MANAGEMENT".trim())){
			cadreUser = true;
			if(accessConstituencyId == null || accessConstituencyId.longValue() ==0){
				List<BasicVO> accLoc = getUserAccessConstituencies();
				accessConstituency = accLoc.get(0).getName();
				accessConstituencyId = accLoc.get(0).getId();
			}
		}
		
		if(entitlements.contains("TDP_COMMITTEE_AREAWISE_ACCESS".trim())){
			noaccess = false ;
		}
	
	/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADRE_COMMITTEE_MANAGEMENT") && !entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"TDP_COMMITTEE_ADMIN")){
		noaccess = true ;
	}
	
	if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADRE_COMMITTEE_MANAGEMENT")){
		cadreUser = true;
		List<BasicVO> accLoc = getUserAccessConstituencies();
		accessConstituency = accLoc.get(0).getName();
		accessConstituencyId = accLoc.get(0).getId();
	}
	
	if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"TDP_COMMITTEE_AREAWISE_ACCESS")){
		noaccess = false ;
	}*/
	if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
		noaccess = false;
	}
	pageAccessType = cadreCommitteeService.userAccessTypeDetailsForDashBoard(regVO.getRegistrationID(),regVO.getAccessType(),Long.valueOf(regVO.getAccessValue().trim()));
	String accessType = regVO.getAccessType();
	if(accessType.equalsIgnoreCase("MLA") || accessType.equalsIgnoreCase("MP"))
	{
		pageAccessType = accessType;
	}
	if(noaccess){
		return "error";
	}
	
	}
	//ageRangeList = cadreCommitteeService.getAgeRangeDetailsForCadre();
	//genericVOList = cadreCommitteeService.getAllCasteDetailsForState();
	//cadreRolesVOList = cadreCommitteeService.getBasicCadreCommitteesDetails();
	locations = cadreCommitteeService.getAllTdpCommitteeDesignations();
	castes=cadreCommitteeService.getAllCastes();
	
	return Action.SUCCESS;
}


public String getUserAccessListConstituency()
{
	try{
	HttpSession session = request.getSession();
	RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
	jObj = new JSONObject(getTask());
	
	JSONArray enrollmentIdsArr = jObj.getJSONArray("enrollmentIdsArr");
    List<Long> enrollIdsList = new ArrayList<Long>(0);
	if(enrollmentIdsArr != null && enrollmentIdsArr.length()>0)
      {
        for (int i = 0; i < enrollmentIdsArr.length(); i++)
        {
          Long value = enrollmentIdsArr.get(i) != null ? Long.valueOf(enrollmentIdsArr.get(i).toString().trim()):0L;
          enrollIdsList.add(value);
        }
      } 

	basicVO = cadreCommitteeService.getAccessLocationValuesByState(user.getAccessType(),Long.valueOf(user.getAccessValue()),jObj.getLong("stateId"),user.getRegistrationID(),enrollIdsList);
	
	}
	catch(Exception e)
	{
		
	}
	return Action.SUCCESS;
}
public String getUserAccessDistrictList()
{
	try{
	HttpSession session = request.getSession();
	RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
	jObj = new JSONObject(getTask());

	basicVOList = cadreCommitteeService.getDistrictsByUserId(user.getRegistrationID(),user.getIsAdmin(),user.getAccessType(),Long.valueOf(user.getAccessValue()));
	
	}
	catch(Exception e)
	{
		
	}
	return Action.SUCCESS;
}
public List<BasicVO> getUserAccessConstituencies(){
	HttpSession session = request.getSession();
	RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
	return tdpCadreReportService.getAccessLocationValues(user.getAccessType(),Long.valueOf(user.getAccessValue().trim()));	
}

public String getAllDistricts(){
	try{
		
		idNameVOList= cadreCommitteeService.getAllDistricts();
	}catch(Exception e){
		LOG.error("Exception Occured In getAllDistricts method "+e);			
	}
	return Action.SUCCESS;
}
public String getAllConstituencysForADistrict(){
	try{
		Long districtId=Long.parseLong(request.getParameter("districtId"));
		idNameVOList= cadreCommitteeService.getAllConstituencysForADistrict(districtId);
	}catch(Exception e){
		LOG.error("Exception Occured In getAllConstituencysForADistrict method "+e);			
	}
	return Action.SUCCESS;
}

	public String getConstituencyCommitteeSummary(){
		try{
			jObj = new JSONObject(getTask());
			HttpSession session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			
			JSONArray enrollmentIdsArr = jObj.getJSONArray("enrollmentIdsArr");
		    List<Long> enrollIdsList = new ArrayList<Long>(0);
			if(enrollmentIdsArr != null && enrollmentIdsArr.length()>0)
		      {
		        for (int i = 0; i < enrollmentIdsArr.length(); i++)
		        {
		          Long value = enrollmentIdsArr.get(i) != null ? Long.valueOf(enrollmentIdsArr.get(i).toString().trim()):0L;
		          enrollIdsList.add(value);
		        }
		      }
			
			constiSummaryVO = cadreCommitteeService.getConstituencySummary(jObj.getLong("reportType"), jObj.getLong("constituencyId"),user.getRegistrationID(),jObj.getLong("committeeTypeId"),enrollIdsList,jObj.getString("startDate"),jObj.getString("endDate"));
		}catch(Exception e){
			LOG.error("Exception Occured In getSummaryDetailsPopUp method "+e);			
		}
		return Action.SUCCESS;
	} 
	public String gettingCadreDetails(){

		try{
			jObj = new JSONObject(getTask());
			List<Long> committeeEnrollmentIdsLst = new ArrayList<Long>();
			JSONArray committeeEnrollmentIds=jObj.getJSONArray("committeeEnrollmentId");
			if(committeeEnrollmentIds!=null &&  committeeEnrollmentIds.length()>0){
				for( int i=0;i<committeeEnrollmentIds.length();i++){
					committeeEnrollmentIdsLst.add(Long.valueOf(committeeEnrollmentIds.getString(i))); 
				}
			}
			if(jObj.getString("type").equalsIgnoreCase("electrols"))
			cadreCommitteeMemberVOList = cadreCommitteeService.getElectrolsOfPanchayatAndWards(jObj.getLong("locationId"),jObj.getLong("locationType"),jObj.getLong("basicCommitteeTypeId"));
			else 
			cadreCommitteeMemberVOList = cadreCommitteeService.getComitteeMembersInfoByCommiteTypeAndLocation(jObj.getLong("locationId"),jObj.getLong("locationType"),jObj.getLong("basicCommitteeTypeId"),"status",committeeEnrollmentIdsLst,jObj.getString("startDate"),jObj.getString("endDate"));
			
		}catch(Exception e){
			LOG.error("Exception occured in getCommitteeDetailsByStatus() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	
	
	
	public String gettingPerformanceOfCadre(){
		try{
			LOG.debug(" Entered Into gettingPerformanceOfCadre");
			
			/*jObj = new JSONObject(getTask());
			if(jObj.getString("type").equalsIgnoreCase("electrols"))
				cadreCommitteeMemberVOList = cadreCommitteeService.getElectrolsOfPanchayatAndWards(jObj.getLong("locationId"),jObj.getLong("locationType"),jObj.getLong("basicCommitteeTypeId"));
			else 
				cadreCommitteeMemberVOList = cadreCommitteeService.getComitteeMembersInfoByCommiteTypeAndLocation(jObj.getLong("locationId"),jObj.getLong("locationType"),jObj.getLong("basicCommitteeTypeId"),"status");*/
			jObj = new JSONObject(getTask());
			cadreCommitteeMemberVOList = cadreCommitteeService.getCommitteeMemberPerformanceDetails(jObj.getLong("locationTypeId"),jObj.getLong("locationId"));
			
		}catch(Exception e){
			LOG.error(" Exception Raised in gettingPerformanceOfCadre ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String saveFieldMessages(){		
		try{ 
			CadreRegistrationVO vo = new CadreRegistrationVO();
			vo.setMobileNumber(request.getParameter("mobilenumber"));
			vo.setArea(request.getParameter("message"));
			LOG.error("In Action mobilenumber"+vo.getMobileNumber()+",message"+vo.getArea());
			if((vo.getMobileNumber() != null && vo.getMobileNumber().trim().length() >0) || (vo.getArea() != null && vo.getArea().trim().length() >0)){
				accessConstituency = webServiceHandlerService1.saveStatus(vo);
				return Action.SUCCESS;
			}else{
				accessConstituency =  "Invalid Inputs !";
				return Action.SUCCESS;
			}
		}
		catch(Exception e){
			LOG.error("Exception occured in saveFieldMessages ",e);
			accessConstituency =  "Error Occured";
		}
	
	   return Action.SUCCESS;
	}
	
	public String getBoothsCurrentStatus(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			boolean noaccess = false;
			
			jObj = new JSONObject(getTask());
			//if(jObj.getString("task").equalsIgnoreCase("summary"))
				 //boothsInfo = cadreRegistrationService.getBoothsCurrentStatusSummary(new Long(regVO.getAccessValue()));
			//else
		//  boothsInfo = cadreRegistrationService.getBoothsCurrentStatus(new Long(regVO.getAccessValue()));
		}catch(Exception e){
			LOG.error("Exception occured in getBoothsCurrentStatus ",e);
		}
	   return Action.SUCCESS;
	}
	
	public String currentBoothsStatus(){
		boolean noaccess = false;
		RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
		
		if(regVo==null){
			return "input";
		}
		
		List<String> entitlements = null;
		if(regVo.getEntitlements() != null && regVo.getEntitlements().size()>0){
			entitlements = regVo.getEntitlements();
			if(!(entitlements.contains("TIRUPATHI_BYEELECTION".trim()))){
				noaccess = true ;
			}
		
		/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"TIRUPATHI_BYEELECTION")){
			noaccess = true ;
		}*/
		if(regVo.getIsAdmin() != null && regVo.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		}
		   return Action.SUCCESS;
		}
	public String getByeElectionBoothsCurrentStatus(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			boolean noaccess = false;
			
			jObj = new JSONObject(getTask());
			/*if(jObj.getString("task").equalsIgnoreCase("ByeElesummary"))
				 byeEleInfo = cadreRegistrationService.getByeEleBoothsCurrentStatusSummary(new Long(regVO.getAccessValue()));*/
			 if(jObj.getString("task").equalsIgnoreCase("ByeEleInfo"))
				//byeEleInfo = cadreRegistrationService.getByeEleBoothsCurrentStatus(new Long(regVO.getAccessValue()));
				byeEleInfo= cadreRegistrationService.getByeEleBoothsCurrentStatusReport(new Long(regVO.getAccessValue()),jObj.getLong("typeId"),jObj.getString("type"));
			else if(jObj.getString("task").equalsIgnoreCase("ByeElesummaryStatusInfo"))
				byeEleInfo= cadreRegistrationService.getByeEleBoothsCurrentStatusSummaryInfo1(new Long(regVO.getAccessValue()),jObj.getString("status"),jObj.getLong("typeId"),jObj.getString("type"));
			else if(jObj.getString("task").equalsIgnoreCase("byeEleerrorInfo"))
				byeEleInfo= cadreRegistrationService.getByeEleBoothsErrorInfo();
			else if(jObj.getString("task").equalsIgnoreCase("byeElelatestUpdates"))
				byeEleInfo= cadreRegistrationService.getByeElelatestUpdates(jObj.getInt("startIndex"),jObj.getInt("maxIndex"));
			
			
			
		}catch(Exception e){
			LOG.error("Exception occured in getBoothsCurrentStatus ",e);
		}
	   return Action.SUCCESS;
	}
	
	public String getClustesAndDivisionNames(){
		try{
			jObj = new JSONObject(getTask());
			cadreCommitteeMemberVOList = cadreRegistrationService.getClustesAndDivisionNames(jObj.getLong("typeId"));
		}catch(Exception e){
			LOG.error("Exception occured in getClustesAndDivisionNames ",e);
		}
	   return Action.SUCCESS;
	}
	
	public String getMessagesInfo(){
		try{
			Integer startIndex = null;
			Integer maxIndex = null;
			String startIndexStr = request.getParameter("startIndex").trim();
			String maxIndexStr = request.getParameter("maxIndex").trim();
			if(startIndexStr.length() > 0){
				startIndex = Integer.parseInt(startIndexStr);
			}
			if(maxIndexStr.length() > 0){
				maxIndex = Integer.parseInt(maxIndexStr);
			}
			request.getParameter("maxIndex");
			byeEleInfo = cadreRegistrationService.getMessagesInfo(startIndex,maxIndex);
		}catch(Exception e){
			LOG.error("Exception getMessagesInfo ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getRoleWiseDetails()
	{
		try {
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			boolean noaccess = false;
			
			if(regVO == null)
				noaccess = true;
			if(noaccess)
				return "error";
			
			jObj = new JSONObject(getTask());
			
			JSONArray rolesListArr = jObj.getJSONArray("rolesArr");
			JSONArray casteCategoryArr = jObj.getJSONArray("casteCategoryArr");
			JSONArray casteCategoryGroupArr = jObj.getJSONArray("casteCategoryGroupArr");
			JSONArray casteIdsArr = jObj.getJSONArray("casteIdsArr");
			JSONArray enrollmentIdsArr = jObj.getJSONArray("enrollmentIdsArr");
			
			List<Long> positionIdsList = new ArrayList<Long>();
			List<Long> casteCategoryIdsList  = new ArrayList<Long>();
			List<Long> casteCategoryGroupIdsList  = new ArrayList<Long>();
			List<Long> casteIdsList  = new ArrayList<Long>();
			List<Long> enrollIdsList = new ArrayList<Long>(0);
			
			Long locationLevelId  = jObj.getLong("locationLevelId");
			Long committeeTypeId = jObj.getLong("committeeTypeId");
			String userAccessType = jObj.getString("userAccessType");
			String castePercentage = jObj.getString("castePercentage");
			String selectedRadio = jObj.getString("selectedRadio");
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			if(casteIdsArr != null && casteIdsArr.length()>0)
			{
				for (int i = 0; i < casteIdsArr.length(); i++)
				{
					Long value = casteIdsArr.get(i) != null ? Long.valueOf(casteIdsArr.get(i).toString().trim()):0L;
					casteIdsList.add(value);
				}
			}
			
			if(casteCategoryGroupArr != null && casteCategoryGroupArr.length()>0)
			{
				for (int i = 0; i < casteCategoryGroupArr.length(); i++)
				{
					Long value = casteCategoryGroupArr.get(i) != null ? Long.valueOf(casteCategoryGroupArr.get(i).toString().trim()):0L;
					casteCategoryGroupIdsList.add(value);
				}
			}
			
			if(rolesListArr != null && rolesListArr.length()>0)
			{
				for (int i = 0; i < rolesListArr.length(); i++)
				{
					Long value = rolesListArr.get(i) != null ? Long.valueOf(rolesListArr.get(i).toString().trim()):0L;
					positionIdsList.add(value);
				}
			}
			
			if(casteCategoryArr != null && casteCategoryArr.length()>0)
			{
				for (int i = 0; i < casteCategoryArr.length(); i++)
				{
					Long value = casteCategoryArr.get(i) != null ? Long.valueOf(casteCategoryArr.get(i).toString().trim()):0L;
					casteCategoryIdsList.add(value);
				}
			}
			if(enrollmentIdsArr != null && enrollmentIdsArr.length()>0)
			{
				for (int i = 0; i < enrollmentIdsArr.length(); i++)
				{
					Long value = enrollmentIdsArr.get(i) != null ? Long.valueOf(enrollmentIdsArr.get(i).toString().trim()):0L;
					enrollIdsList.add(value);
				}
			}
			
			cadreCommitteeRolesInfoVO = cadreCommitteeService.getCommitteeRoleAgeWiseDetailsByLocationType(userAccessType,castePercentage,committeeTypeId,positionIdsList,
					casteCategoryIdsList,casteCategoryGroupIdsList,casteIdsList,locationLevelId,regVO.getRegistrationID(),Long.valueOf(regVO.getAccessValue()),selectedRadio,enrollIdsList,startDate,endDate);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception getRoleWiseDetails ",e);
		}
		return Action.SUCCESS;
	}
	public String committeeMemberDetailExe()
	{
		try{
			
		/*	RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVo == null)
				return Action.ERROR;
			*/
		}
		catch(Exception e)
		{
			LOG.error("Exception in committeeMemberDetailExe() method",e);
		}
		return Action.SUCCESS;
	}
	
	public String peformanceOfCadreAction(){
		return Action.SUCCESS;
	}
	
	public String getConstituencyByDistrict(){
		try{
			jObj = new JSONObject(getTask());
			constiList = cadreCommitteeService.getConstituencyByDistrict(jObj.getLong("districtId"));
		}catch(Exception e){
			LOG.error("Exception occured in getConstituencyByDistrict ",e);
		}
	   return Action.SUCCESS;
	}
	
	public String updateActivity(){

		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("PARTY_ACTIVITY_UPDATE".trim()) || entitlements.contains("ACTIVITY_ENTRY_ENTITLEMENT".trim()))){
				noaccess = true ;
			}
			
			
		/*if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"PARTY_ACTIVITY_UPDATE") || 
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"ACTIVITY_ENTRY_ENTITLEMENT"))){
			noaccess = true ;
		}*/
		
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		pageAccessType = cadreCommitteeService.userAccessTypeDetailsForDashBoard(regVO.getRegistrationID(),regVO.getAccessType(),Long.valueOf(regVO.getAccessValue().trim()));
		String accessType = regVO.getAccessType();
		if(accessType.equalsIgnoreCase("MLA") || accessType.equalsIgnoreCase("MP"))
		{
			pageAccessType = accessType;
		}
		
		basicVO = cadreCommitteeService.getActivityTypeList();
		idNameVOList = cadreCommitteeService.getActivityLevelsList();
		}
		return Action.SUCCESS;
	}
	
	public String getActivityDetails(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			boolean noaccess = false;
			if(regVO==null){
				return "input";
			}
			if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
				noaccess = false;
			}
			if(noaccess){
				return "error";
			}
			
			jObj = new JSONObject(getTask());
			Long activityTypeId =jObj.getLong("activityTypeId");
			Long activityLevelId = jObj.getLong("activityLevelId");
			idNameVOList = cadreCommitteeService.getActivitiesListByTypeAndLevel(activityTypeId,activityLevelId);
		}catch(Exception e){
			LOG.error("Exception occured in getActivityDetails ",e);
		}
	   return Action.SUCCESS;
	}
	
	public String getLocationDetailsForActivity()
	{
		try {
			jObj = new JSONObject(getTask());
			String checkedValue =jObj.getString("checkedValue");
			Long activityScopeId = jObj.getLong("activityScopeId");
			Long activityLevelId =jObj.getLong("activityLevelId");
			String searchBy =jObj.getString("searchBy");
			Long locationId = jObj.getLong("locationId");
			String searchStartDateStr=jObj.getString("startDate");
			String searchEndDateStr=jObj.getString("endDate");
			Long constituencyId = jObj.getLong("constituencyId");
			Long optionId = jObj.getLong("optionId");
			Long questionId = jObj.getLong("questionId");
			
			if(searchStartDateStr != null && searchStartDateStr.trim().length() > 0 && searchEndDateStr != null && searchEndDateStr.trim().length() > 0){
				locationWiseBoothDetailsVO1 = cadreCommitteeService.getActivityLocationDetails(checkedValue,activityScopeId,activityLevelId,searchBy,
						locationId,searchStartDateStr,searchEndDateStr,constituencyId,optionId,questionId);
			}else{
				locationWiseBoothDetailsVO1 = cadreCommitteeService.getActivityLocationDetails(checkedValue,activityScopeId,activityLevelId,searchBy,
						locationId,null,null,constituencyId,optionId,questionId);
			}
			
			
		} catch (Exception e) {
			LOG.error("Exception occured in getLocationDetailsForActivity ",e);
		}
		return Action.SUCCESS;
	}
	public String asemblyConstWiseActivities(){
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
			
			jObj = new JSONObject(getTask());
			String startDateString =jObj.getString("startDate");
			String endDateString = jObj.getString("endDate");
			Long activityScopeId =jObj.getLong("activityScopeId");
			Long activityLevelId =jObj.getLong("activityLevelId");	
			Long levelId = jObj.getLong("levelId");
			Long stateId=jObj.getLong("stateId");
			if(levelId == 3l){
				//activitiesVOList=cadreCommitteeService.getDistrictWiseActivities(startDateString,endDateString,activityScopeId,activityLevelId,user.getAccessType(),Long.valueOf(user.getAccessValue()),stateId,user.getRegistrationID());
				activitiesVOList=cadreCommitteeService.getDistrictWiseActivitiesCount(activityScopeId,activityLevelId,user.getAccessType(),Long.valueOf(user.getAccessValue()),stateId,user.getRegistrationID());
			}else{
				//activitiesVOList=cadreCommitteeService.asemblyConstWiseActivities(startDateString,endDateString,activityScopeId,activityLevelId,user.getAccessType(),Long.valueOf(user.getAccessValue()),stateId,user.getRegistrationID());
				activitiesVOList=cadreCommitteeService.asemblyConstWiseActivitiesCount(activityScopeId,activityLevelId,user.getAccessType(),Long.valueOf(user.getAccessValue()),stateId,user.getRegistrationID());
			}
				
			
		} catch (Exception e) {
			LOG.error("Exception occured in getLocationDetailsForActivity ",e);
		}
		return Action.SUCCESS;
	}
	public String getComitteeMembersInfoInActivity(){

		try{
			jObj = new JSONObject(getTask());
			
			cadreCommitteeMemberVOList = cadreCommitteeService.getComitteeMembersInfoInActivity(jObj.getLong("locationId"),jObj.getLong("locationType"),jObj.getLong("basicCommitteeTypeId"),jObj.getLong("constituencyId"));
			
		}catch(Exception e){
			LOG.error("Exception occured in getCommitteeDetailsByStatus() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getEventDocumentsForLocation()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
			jObj = new JSONObject(getTask());
			EventDocumentVO inputVo = new EventDocumentVO();
			inputVo.setLocationScope(jObj.getString("locationScope"));
			inputVo.setLocationValue(jObj.getLong("locationValue"));
			inputVo.setActivityId(jObj.getLong("activityId"));	
			inputVo.setStrDate(jObj.getString("fromDateStr"));	
			inputVo.setEndDate(jObj.getString("toDateStr"));
			inputVo.setDay(jObj.getLong("day"));
			
			inputVo.setStartIndex(jObj.getInt("startIndex"));
			inputVo.setMaxIndex(jObj.getInt("maxIndex"));
			inputVo.setCallFrom(jObj.getString("callFrom"));
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long stateId = jObj.getLong("stateId");
			Long userTypeId = jObj.getLong("userTypeId");
			
			
			docsList=cadreCommitteeService.getEventDocumentsForLocation(inputVo,activityMemberId,stateId,userTypeId);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getEventDocumentsForLocation ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getLocationsHierarchyForEvent()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
			jObj = new JSONObject(getTask());
			EventDocumentVO inputVo = new EventDocumentVO();
			inputVo.setLocationScope(jObj.getString("locationScope"));
			inputVo.setLocationValue(jObj.getLong("locationValue"));
			inputVo.setActivityId(jObj.getLong("activityId"));	
			inputVo.setStrDate(jObj.getString("fromDateStr"));	
			inputVo.setEndDate(jObj.getString("toDateStr"));
			basicVO=cadreCommitteeService.getLocationsHierarchyForEvent(inputVo,"");
			
		} catch (Exception e) {
			LOG.error("Exception occured in getLocationsHierarchyForEvent ",e);
		}
		return Action.SUCCESS;
	}
	public String activitiesQuestionResponces(){
		
		basicVO = cadreCommitteeService.getActivityTypeList();
		idNameVOList = cadreCommitteeService.getActivityLevelsList();
		
		return Action.SUCCESS;
	}
	public String getAvailableDatesForActivities()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
			jObj = new JSONObject(getTask());
			EventDocumentVO inputVo = new EventDocumentVO();
			inputVo.setLocationScope(jObj.getString("locationScope"));
			inputVo.setLocationValue(jObj.getLong("locationValue"));
			inputVo.setActivityId(jObj.getLong("activityId"));	
			inputVo.setStrDate(jObj.getString("fromDateStr"));	
			inputVo.setEndDate(jObj.getString("toDateStr"));
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long stateId = jObj.getLong("stateId");
			Long userTypeId = jObj.getLong("userTypeId");
			
			basicVOList=cadreCommitteeService.getAvailableDates(inputVo,activityMemberId,stateId,userTypeId);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getAvailableDatesForActivities ",e);
		}
		return Action.SUCCESS;
	}
	
	public String uploadActivityDocuments(){
		try {
				jObj = new JSONObject(getTask());
				resultStatus = activityService.uploadActivityDocuments(jObj.getLong("activityScopeId"),jObj.getString("sourceFolderName"),null);
		} catch (Exception e) {
			LOG.error("Exception occured in uploadActivityDocuments ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAllActivities(){
		try{
			
			idNameVOList = activityService.getAllActivities();
			
		}catch (Exception e) {
			LOG.error("Exception occured in getAllActivities ",e);
		}
		return Action.SUCCESS; 
	}
	public String getAllActivityLevelsByActivity(){
		try{
			jObj = new JSONObject(getTask());
			idNameVOList = activityService.getAllActivityLevelsByActivity(jObj.getLong("activityId"));
			
		}catch (Exception e) {
			LOG.error("Exception occured in getAllActivityLevelsByActivity ",e);
		}
		return Action.SUCCESS; 
	}
	
	public String updateActivityNew(){		
		return Action.SUCCESS;
	}
	
	public String getBetweenDatesOfActivityScope(){
		try{			
			jObj = new JSONObject(getTask());
			locationWiseBoothDetailsVO1 = cadreCommitteeService.getBetweenDatesOfActivityScope(jObj.getLong("scopeId"));
		}catch(Exception e){
			LOG.error("Exception occured in getBetweenDatesOfActivityScope ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getLocationDetailsForActivityNew()
	{
		try {
			jObj = new JSONObject(getTask());
			String checkedValue =jObj.getString("checkedValue");
			Long activityScopeId = jObj.getLong("activityScopeId");
			Long activityLevelId =jObj.getLong("activityLevelId");
			String searchBy =jObj.getString("searchBy");
			Long locationId = jObj.getLong("locationId");
			String searchStartDateStr=jObj.getString("startDate");
			String searchEndDateStr=jObj.getString("endDate");
			Long constituencyId = jObj.getLong("constituencyId");
			Long optionId = jObj.getLong("optionId");
			Long questionId = jObj.getLong("questionId");
			
			JSONArray datesArr = jObj.getJSONArray("datesArr");			
			List<String> dates = new ArrayList<String>();
				
			if(datesArr !=null && datesArr.length() >0){
				for(int i=0; i<datesArr.length(); i++ ){
					dates.add(datesArr.get(i).toString().trim());
				}
			}   
			
			if(searchStartDateStr != null && searchStartDateStr.trim().length() > 0 && searchEndDateStr != null && searchEndDateStr.trim().length() > 0){
				locationWiseBoothDetailsVO1 = cadreCommitteeService.getActivityLocationDetailsNew(checkedValue,activityScopeId,activityLevelId,searchBy,
						locationId,searchStartDateStr,searchEndDateStr,constituencyId,optionId,questionId,dates);
			}else{
				locationWiseBoothDetailsVO1 = cadreCommitteeService.getActivityLocationDetailsNew(checkedValue,activityScopeId,activityLevelId,searchBy,
						locationId,null,null,constituencyId,optionId,questionId,dates);
			}
			
			
		} catch (Exception e) {
			LOG.error("Exception occured in getLocationDetailsForActivity ",e);
		}
		return Action.SUCCESS;
	}
	public String getCadreEnrollmentYears(){
		try{			
			jObj = new JSONObject(getTask());
			
			committeeVoList = cadreCommitteeService.getCadreEnrollmentYears();
		}catch(Exception e){
			LOG.error("Exception occured in getCadreEnrollmentYears ",e);
		}
		return Action.SUCCESS;
	}
	public String getCommitteeDetailsByEnrollementId(){
		try{			
			jObj = new JSONObject(getTask());
			List<Long> enrollYearIdsList = new ArrayList<Long>(0);
			JSONArray EnrollIdsArr = jObj.getJSONArray("enrollmentIdsArr");
			if(EnrollIdsArr != null && EnrollIdsArr.length()>0)
			{
				for (int i = 0; i < EnrollIdsArr.length(); i++)
				{
					Long value = EnrollIdsArr.get(i) != null && !EnrollIdsArr.get(i).toString().equalsIgnoreCase("null") ? Long.valueOf(EnrollIdsArr.get(i).toString().trim()):0L;
					enrollYearIdsList.add(value);
				}
			}
			committeeVoList = cadreCommitteeService.getCommitteeDetailsByEnrollementId(enrollYearIdsList);
		}catch(Exception e){
			LOG.error("Exception occured in getCommitteeDetailsByEnrollementId ",e);
		}
		return Action.SUCCESS;
	}
	public String getCommitteeMembersAvailableInfo(){
		try {
			jObj = new JSONObject(getTask());
			locationWiseBoothDetailsVO = cadreCommitteeService.getCommitteeMembersAvailableInfo1(jObj.getLong("levelId"),jObj.getLong("levelValue"),jObj.getLong("enrollmentYrId"),jObj.getString("startDate"),jObj.getString("endDate"),
					jObj.getLong("basicCommitteetypeId"));
			
		} catch (Exception e) {
			LOG.error("Exception occured in getCommitteeMembersAvailableInfo() At CadreCommitteeAction ",e);
		}		
		return Action.SUCCESS;
	}
	public String getMembersRangeCountByLocation1(){
		try{
			//RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			jObj = new JSONObject(getTask());
			JSONArray levelIdsArr = jObj.getJSONArray("levelIdsArr");			
			List<Long> levelIds = new ArrayList<Long>();
				
			if(levelIdsArr !=null && levelIdsArr.length() >0){
				for(int i=0; i<levelIdsArr.length(); i++ ){
					levelIds.add(Long.valueOf(levelIdsArr.get(i).toString().trim()));
				}
			}
			String state =jObj.getString("state");
			Long committeeId = jObj.getLong("committeeId");
			String startdate=jObj.getString("startDate");
			String endDate=jObj.getString("endDate");
			String committeeType = jObj.getString("committeeType");
			List<Long> committeeSpanTypeIdsLsit = new ArrayList<Long>();
			JSONArray committeeEnrollmentIds=jObj.getJSONArray("committeeSpanTypeIdsList");
			if(committeeEnrollmentIds!=null &&  committeeEnrollmentIds.length()>0){
				for( int i=0;i<committeeEnrollmentIds.length();i++){
					committeeSpanTypeIdsLsit.add(Long.valueOf(committeeEnrollmentIds.getString(i))); 
				}
			}
			Long activityMemberId = jObj.getLong("activityMemberId");
			String userTypeId = jObj.getString("userTypeId");
			cadreCommitteeReportVOList = cadreCommitteeService.getMembersRangeCountByLocation(state,levelIds,committeeId,startdate,endDate,userTypeId,null,activityMemberId,committeeType,committeeSpanTypeIdsLsit);
		
			
		}catch(Exception e){
			LOG.error("Exception Occured In getDashBoardLocationWiseDetailsAction method "+e);
		}
		return Action.SUCCESS;
	}
	public String getStartedAffliCommitteesCountByLocation1(){
		try{
			jObj = new JSONObject(getTask());
			JSONArray levelIdsArr = jObj.getJSONArray("levelIdsArr");			
			List<Long> levelIds = new ArrayList<Long>();
			//RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			//if(regVO != null)
			{
				//String accessType = regVO.getAccessType();
				//Long accessValue = Long.valueOf(regVO.getAccessValue());
					
			if(levelIdsArr !=null && levelIdsArr.length() >0){
				for(int i=0; i<levelIdsArr.length(); i++ ){
					levelIds.add(Long.valueOf(levelIdsArr.get(i).toString().trim()));
				}
			}
			String state =jObj.getString("state");
			String startdate=jObj.getString("startDate");
			String endDate=jObj.getString("endDate");
			String committeeType = jObj.getString("committeeType");
			List<Long> committeeSpanTypeIdsLsit = new ArrayList<Long>();
			JSONArray committeeEnrollmentIds=jObj.getJSONArray("committeeSpanTypeIdsList");
			if(committeeEnrollmentIds!=null &&  committeeEnrollmentIds.length()>0){
				for( int i=0;i<committeeEnrollmentIds.length();i++){
					committeeSpanTypeIdsLsit.add(Long.valueOf(committeeEnrollmentIds.getString(i))); 
				}
			}
			Long activityMemberId = jObj.getLong("activityMemberId");
			String userTypeId = jObj.getString("userTypeId");
			cadreCommitteeReportVOList = cadreCommitteeService.getStartedAffliCommitteesCountByLocation(state,levelIds,startdate,endDate,userTypeId,null,activityMemberId,committeeType,committeeSpanTypeIdsLsit);
			}
			
		}catch(Exception e){
			LOG.error("Exception Occured In getDashBoardLocationWiseDetailsAction method "+e);
		}
		return Action.SUCCESS;
	}

	public String getUserWiseDetails()
	{
		try{
		HttpSession session = request.getSession();
		RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
		jObj = new JSONObject(getTask());

		basicVOList = cadreCommitteeService.userWiseDetailsForDashBoard(user.getRegistrationID(),user.getAccessType(),user.getAccessValue());
		
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured In getUserWiseDetails method " +e);
		}
		return Action.SUCCESS;
	}
	public String getConstituenciesByActivity(){
		try{
			HttpSession session = request.getSession();
			RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
			jObj = new JSONObject(getTask());
			Long activityId =jObj.getLong("activityId");
			
			idNameVOList = new ArrayList<IdNameVO>(0);
			
			List<IdNameVO> tempidNameVOList = cadreCommitteeService.getConstituenciesByActivityId(activityId);
			
			BasicVO userAccessLocationsVo = cadreCommitteeService.getAccessLocationValuesByState(user.getAccessType(),Long.valueOf(user.getAccessValue()),jObj.getLong("stateId"),user.getRegistrationID(),null);
			if(userAccessLocationsVo != null && userAccessLocationsVo.getHamletVoterInfo().size()>0){
				List<Long> accessIds = new ArrayList<Long>(0);
				for (BasicVO vo : userAccessLocationsVo.getHamletVoterInfo()) {
					accessIds.add(vo.getId());
				}
				
				if(tempidNameVOList != null && tempidNameVOList.size()>0){
					for (IdNameVO vo : tempidNameVOList) {
						if(accessIds.contains(vo.getId()))
							idNameVOList.add(vo);
					}
				}
			}else{
				idNameVOList.addAll(tempidNameVOList);
			}
			
		}catch(Exception e){
			LOG.error("Exception occured in getConstituenciesByActivity ",e);
		}
	   return Action.SUCCESS;
	}
	public String getMandalsByConstituency(){
		try{
			jObj = new JSONObject(getTask());
			Long constituencyId =jObj.getLong("constituencyId");
			Long activityScopeId = jObj.getLong("activityScopeId");
			
			idNameVOList = cadreCommitteeService.getMandalsByConstituencyId(constituencyId,activityScopeId);
		}catch(Exception e){
			LOG.error("Exception occured in getMandalsByConstituency ",e);
		}
	   return Action.SUCCESS;
	}
	public String getPanchayatsByMandals(){
		try{
			jObj = new JSONObject(getTask());
			Long mandalId =jObj.getLong("mandalId");
			Long activityScopeId = jObj.getLong("activityScopeId");
			
			idNameVOList = cadreCommitteeService.getPanchayatBymandalId(mandalId,activityScopeId);
		}catch(Exception e){
			LOG.error("Exception occured in getPanchayatsByMandals ",e);
		}
	   return Action.SUCCESS;
	}
	public String getActivityLocationDetails(){
		try{
			jObj = new JSONObject(getTask());
			locationWiseBoothDetailsVOList=cadreCommitteeService.getActivityLocationDetails(jObj.getLong("activityLevelId"),jObj.getLong("locationId"),jObj.getLong("activityScopeId"),jObj.getString("searchBy"),jObj.getString("checkedValue"));
		}catch (Exception e) {
			LOG.error("Exception occured in getActivityLocationDetails ",e);
		}
		return Action.SUCCESS;
	}
	public String getDistrictsListByActivity(){
		try{
			jObj = new JSONObject(getTask());
			Long activityId =jObj.getLong("activityId");
			
			idNameVOList = cadreCommitteeService.getDistrictsByActivityId(activityId);
		}catch(Exception e){
			LOG.error("Exception occured in getDistrictsListByActivity ",e);
		}
	   return Action.SUCCESS;
	}
	public String getCommitteeCountDetails(){
		try{
			jObj = new JSONObject(getTask());
			
			Long constituencyId =jObj.getLong("constituencyId");
			
			List<Long> levelIdsList = new ArrayList<Long>();
			JSONArray levelIds = jObj.getJSONArray("levelIds");
			if(levelIds!=null &&  levelIds.length()>0){
				for( int i=0;i<levelIds.length();i++){
					levelIdsList.add(Long.valueOf(levelIds.getString(i))); 
				}
			}
			
			List<Long> levelValuesList = new ArrayList<Long>();
			JSONArray levelValues = jObj.getJSONArray("levelValues");
			if(levelValues!=null &&  levelValues.length()>0){
				for( int i=0;i<levelValues.length();i++){
					levelValuesList.add(Long.valueOf(levelValues.getString(i))); 
				}
			}
			
			List<Long> basicCommittesList = new ArrayList<Long>();
			JSONArray basicCommittes = jObj.getJSONArray("basicCommittesIds");
			if(basicCommittes!=null &&  basicCommittes.length()>0){
				for( int i=0;i<basicCommittes.length();i++){
					basicCommittesList.add(Long.valueOf(basicCommittes.getString(i))); 
				}
			}
			List<Long> cmiteEnrlmntYearIdsList = new ArrayList<Long>();
			JSONArray cmiteEnrlmntYears = jObj.getJSONArray("cmiteEnrlmntYearIds");
			if(cmiteEnrlmntYears!=null &&  cmiteEnrlmntYears.length()>0){
				for( int i=0;i<cmiteEnrlmntYears.length();i++){
					cmiteEnrlmntYearIdsList.add(Long.valueOf(cmiteEnrlmntYears.getString(i))); 
				}
			}
			
			cadreCommitteeVO = cadreCommitteeService.getCommitteeCountDetailsByLevelId(constituencyId,levelIdsList,levelValuesList,basicCommittesList,cmiteEnrlmntYearIdsList);
		}catch(Exception e){
			LOG.error("Exception occured in getCommitteeCountDetails ",e);
		}
	   return Action.SUCCESS;
	}
}
