package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AccessedPageLoginTimeVO;
import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.CasteDetailsVO;
import com.itgrids.partyanalyst.dto.CommitteeApprovalVO;
import com.itgrids.partyanalyst.dto.CommitteeSummaryVO;
import com.itgrids.partyanalyst.dto.EventCreationVO;
import com.itgrids.partyanalyst.dto.GISUserTrackingVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationQueriesVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.UserEventDetailsVO;
import com.itgrids.partyanalyst.dto.VO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ICandidateUpdationDetailsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.ITdpCadreReportService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreCommitteeAction   extends ActionSupport implements ServletRequestAware{
	
	private static final Logger         		LOG = Logger.getLogger(CadreCommitteeAction.class);
	private HttpServletRequest         			request;
	private HttpSession 						session;
	private ICadreCommitteeService   		 	cadreCommitteeService;
	private IStaticDataService					staticDataService;
	private ICandidateUpdationDetailsService 	candidateUpdationDetailsService;
	private ICadreRegistrationService			cadreRegistrationService;
	private CadreCommitteeVO 					cadreCommitteeVO;
	private TdpCadreVO							tdpCadreVO;
	private Long 								tdpCadreId;
	private JSONObject							jObj;
	private String 								task;
	private List<String>						ageRangeList;
	private List<GenericVO>						genericVOList,educationList;
	private List<SelectOptionVO>				selectOptionVOList;
	private List<SelectOptionVO>				eletionTypesList;
	private List<SelectOptionVO>				cadreRolesVOList;
	private List<CasteDetailsVO>				casteDetailsVo;	
	
	private ITdpCadreReportService              tdpCadreReportService;
	private List<LocationWiseBoothDetailsVO>    locations;
	private LocationWiseBoothDetailsVO          membersInfo;
	private List<BasicVO>                       constituencies;
	private Long                                committeeMngtType;
	private EntitlementsHelper 					entitlementsHelper;
	private String 								panchayatId;
	private ResultStatus 						status;
	private String 								panchayatName;				
	private Long 								assemblyId;
	private Long 								committeeTypeId;
	private Long 								committeeId;
	private String 								result1,result2,result3,result4,result5,result6;
	private List<CadreRegisterInfo>             result;
	private ICadreDashBoardService              cadreDashBoardService;
	private List<Long>  						wardResult;
	private List<LocationWiseBoothDetailsVO>    locationWiseBoothDetailsVO;
	private List<IdNameVO>						idNameVOList;
	private List<IdNameVO>						constituenciesList;
	private List<CommitteeApprovalVO>			approvalRecordsList;
	private String								finalStatus;
	private CommitteeApprovalVO					statusFinalVO;
	private List<LocationWiseBoothDetailsVO> committeesCountsInfo;
	private List<CommitteeSummaryVO> returnList;
	private List<AccessedPageLoginTimeVO>       returnResult;
	private Long 								mandalId;
	private String                              reqLocationType;
	private String                              reqLocationValue;
	private String                              globalLocationName;
	private List<BasicVO> basicList;
	private List<TdpCadreVO> commiteeMembersList = new ArrayList<TdpCadreVO>();
	
    private EventCreationVO eventCreationVO;
	private List<VO> resultList;
	private Long locationId;
	private ActivityVO activityVO = new ActivityVO();
	private List<IdNameVO>  castes;
	private List<GISUserTrackingVO> gisTrackVolst;
	private Long constitunecyId;
	private String startDate;
	private String endDate;
	private List<CadreCommitteeVO>  cadreCommitteeList;
	private Long locationValue;
	private LocationWiseBoothDetailsVO  locationVO;
	
	public Long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}

	public List<CadreCommitteeVO> getCadreCommitteeList() {
		return cadreCommitteeList;
	}

	public void setCadreCommitteeList(List<CadreCommitteeVO> cadreCommitteeList) {
		this.cadreCommitteeList = cadreCommitteeList;
	}

	public Long getConstitunecyId() {
		return constitunecyId;
	}

	public void setConstitunecyId(Long constitunecyId) {
		this.constitunecyId = constitunecyId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<GISUserTrackingVO> getGisTrackVolst() {
		return gisTrackVolst;
	}

	public void setGisTrackVolst(List<GISUserTrackingVO> gisTrackVolst) {
		this.gisTrackVolst = gisTrackVolst;
	}

	public ActivityVO getActivityVO() {
		return activityVO;
	}

	public void setActivityVO(ActivityVO activityVO) {
		this.activityVO = activityVO;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public List<VO> getResultList() {
		return resultList;
	}

	public void setResultList(List<VO> resultList) {
		this.resultList = resultList;
	}

	public List<TdpCadreVO> getCommiteeMembersList() {
		return commiteeMembersList;
	}

	public void setCommiteeMembersList(List<TdpCadreVO> commiteeMembersList) {
		this.commiteeMembersList = commiteeMembersList;
	}

	public List<BasicVO> getBasicList() {
		return basicList;
	}

	public void setBasicList(List<BasicVO> basicList) {
		this.basicList = basicList;
	}

	public List<AccessedPageLoginTimeVO> getReturnResult() {
		return returnResult;
	}

	public void setReturnResult(List<AccessedPageLoginTimeVO> returnResult) {
		this.returnResult = returnResult;
	}

	public CommitteeApprovalVO getStatusFinalVO() {
		return statusFinalVO;
	}

	public void setStatusFinalVO(CommitteeApprovalVO statusFinalVO) {
		this.statusFinalVO = statusFinalVO;
	}

	public String getFinalStatus() {
		return finalStatus;
	}

	public void setFinalStatus(String finalStatus) {
		this.finalStatus = finalStatus;
	}

	public List<CommitteeApprovalVO> getApprovalRecordsList() {
		return approvalRecordsList;
	}

	public void setApprovalRecordsList(List<CommitteeApprovalVO> approvalRecordsList) {
		this.approvalRecordsList = approvalRecordsList;
	}

	public List<IdNameVO> getConstituenciesList() {
		return constituenciesList;
	}

	public void setConstituenciesList(List<IdNameVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}

	public List<IdNameVO> getIdNameVOList() {
		return idNameVOList;
	}

	public void setIdNameVOList(List<IdNameVO> idNameVOList) {
		this.idNameVOList = idNameVOList;
	}
	
	public List<CommitteeSummaryVO> getReturnList() {
		return returnList;
	}

	public void setReturnList(List<CommitteeSummaryVO> returnList) {
		this.returnList = returnList;
	}
	
	public List<LocationWiseBoothDetailsVO> getCommitteesCountsInfo() {
		return committeesCountsInfo;
	}

	public void setCommitteesCountsInfo(
			List<LocationWiseBoothDetailsVO> committeesCountsInfo) {
		this.committeesCountsInfo = committeesCountsInfo;
	}
	public String getResult4() {
		return result4;
	}

	public void setResult4(String result4) {
		this.result4 = result4;
	}

	public String getResult5() {
		return result5;
	}

	public void setResult5(String result5) {
		this.result5 = result5;
	}

	public String getResult6() {
		return result6;
	}

	public void setResult6(String result6) {
		this.result6 = result6;
	}

	public String getResult1() {
		return result1;
	}

	public void setResult1(String result1) {
		this.result1 = result1;
	}

	public String getResult2() {
		return result2;
	}

	public void setResult2(String result2) {
		this.result2 = result2;
	}

	public String getResult3() {
		return result3;
	}

	public void setResult3(String result3) {
		this.result3 = result3;
	}

	public Long getCommitteeTypeId() {
		return committeeTypeId;
	}

	public void setCommitteeTypeId(Long committeeTypeId) {
		this.committeeTypeId = committeeTypeId;
	}

	public Long getCommitteeId() {
		return committeeId;
	}

	public void setCommitteeId(Long committeeId) {
		this.committeeId = committeeId;
	}

	
	public List<LocationWiseBoothDetailsVO> getLocationWiseBoothDetailsVO() {
		return locationWiseBoothDetailsVO;
	}

	public void setLocationWiseBoothDetailsVO(
			List<LocationWiseBoothDetailsVO> locationWiseBoothDetailsVO) {
		this.locationWiseBoothDetailsVO = locationWiseBoothDetailsVO;
	}

	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}

	public ResultStatus getStatus() {
		return status;
	}

	public void setStatus(ResultStatus status) {
		this.status = status;
	}

	public String getpanchayatId() {
		return panchayatId;
	}

	public void setpanchayatId(String panchayatId) {
		this.panchayatId = panchayatId;
	}
	
	
	
	public List<Long> getWardResult() {
		return wardResult;
	}

	public void setWardResult(List<Long> wardResult) {
		this.wardResult = wardResult;
	}

	public ICadreDashBoardService getCadreDashBoardService() {
		return cadreDashBoardService;
	}

	public void setCadreDashBoardService(
			ICadreDashBoardService cadreDashBoardService) {
		this.cadreDashBoardService = cadreDashBoardService;
	}

	public List<CadreRegisterInfo> getResult() {
		return result;
	}

	public void setResult(List<CadreRegisterInfo> result) {
		this.result = result;
	}

	public List<GenericVO> getEducationList() {
		return educationList;
	}

	public void setEducationList(List<GenericVO> educationList) {
		this.educationList = educationList;
	}

	public List<CasteDetailsVO> getCasteDetailsVo() {
		return casteDetailsVo;
	}

	public void setCasteDetailsVo(List<CasteDetailsVO> casteDetailsVo) {
		this.casteDetailsVo = casteDetailsVo;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public void setCandidateUpdationDetailsService(
			ICandidateUpdationDetailsService candidateUpdationDetailsService) {
		this.candidateUpdationDetailsService = candidateUpdationDetailsService;
	}

	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}

	public List<GenericVO> getGenericVOList() {
		return genericVOList;
	}

	public void setGenericVOList(List<GenericVO> genericVOList) {
		this.genericVOList = genericVOList;
	}

	public List<SelectOptionVO> getSelectOptionVOList() {
		return selectOptionVOList;
	}

	public void setSelectOptionVOList(List<SelectOptionVO> selectOptionVOList) {
		this.selectOptionVOList = selectOptionVOList;
	}

	public List<SelectOptionVO> getEletionTypesList() {
		return eletionTypesList;
	}

	public void setEletionTypesList(List<SelectOptionVO> eletionTypesList) {
		this.eletionTypesList = eletionTypesList;
	}

	public List<SelectOptionVO> getCadreRolesVOList() {
		return cadreRolesVOList;
	}

	public void setCadreRolesVOList(List<SelectOptionVO> cadreRolesVOList) {
		this.cadreRolesVOList = cadreRolesVOList;
	}

	public List<String> getAgeRangeList() {
		return ageRangeList;
	}

	public void setAgeRangeList(List<String> ageRangeList) {
		this.ageRangeList = ageRangeList;
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

	public TdpCadreVO getTdpCadreVO() {
		return tdpCadreVO;
	}

	public void setTdpCadreVO(TdpCadreVO tdpCadreVO) {
		this.tdpCadreVO = tdpCadreVO;
	}
	
	public Long getTdpCadreId() {
		return tdpCadreId;
	}

	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}

	public CadreCommitteeVO getCadreCommitteeVO() {
		return cadreCommitteeVO;
	}

	public void setCadreCommitteeVO(CadreCommitteeVO cadreCommitteeVO) {
		this.cadreCommitteeVO = cadreCommitteeVO;
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

	public void setCadreCommitteeService(ICadreCommitteeService cadreCommitteeService) {
			this.cadreCommitteeService = cadreCommitteeService;
	}
	
	public void setTdpCadreReportService(
			ITdpCadreReportService tdpCadreReportService) {
		this.tdpCadreReportService = tdpCadreReportService;
	}

	public List<LocationWiseBoothDetailsVO> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationWiseBoothDetailsVO> locations) {
		this.locations = locations;
	}

	public List<BasicVO> getConstituencies() {
		return constituencies;
	}

	public void setConstituencies(List<BasicVO> constituencies) {
		this.constituencies = constituencies;
	}
	
	public LocationWiseBoothDetailsVO getMembersInfo() {
		return membersInfo;
	}

	public void setMembersInfo(LocationWiseBoothDetailsVO membersInfo) {
		this.membersInfo = membersInfo;
	}

	public Long getCommitteeMngtType() {
		return committeeMngtType;
	}

	public void setCommitteeMngtType(Long committeeMngtType) {
		this.committeeMngtType = committeeMngtType;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public Long getAssemblyId() {
		return assemblyId;
	}

	public void setAssemblyId(Long assemblyId) {
		this.assemblyId = assemblyId;
	}

	public Long getMandalId() {
		return mandalId;
	}

	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}

	public String getReqLocationType() {
		return reqLocationType;
	}

	public void setReqLocationType(String reqLocationType) {
		this.reqLocationType = reqLocationType;
	}

	public String getReqLocationValue() {
		return reqLocationValue;
	}

	public void setReqLocationValue(String reqLocationValue) {
		this.reqLocationValue = reqLocationValue;
	}

	public String getGlobalLocationName() {
		return globalLocationName;
	}

	public void setGlobalLocationName(String globalLocationName) {
		this.globalLocationName = globalLocationName;
	}
	
	

	public EventCreationVO getEventCreationVO() {
		return eventCreationVO;
	}

	public void setEventCreationVO(EventCreationVO eventCreationVO) {
		this.eventCreationVO = eventCreationVO;
	}
    
	public List<IdNameVO> getCastes() {
		return castes;
	}

	public void setCastes(List<IdNameVO> castes) {
		this.castes = castes;
	}
	
	public LocationWiseBoothDetailsVO getLocationVO() {
		return locationVO;
	}

	public void setLocationVO(LocationWiseBoothDetailsVO locationVO) {
		this.locationVO = locationVO;
	}

	public String execute()
	{	
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if((!entitlements.contains("CADRE_COMMITTEE_MANAGEMENT".trim()) && !entitlements.contains("COMMITTEE_MGT".trim()))){
				noaccess = true ;
			}
		
		/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADRE_COMMITTEE_MANAGEMENT")
				&& !entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"COMMITTEE_MGT")){
			noaccess = true ;
		}*/
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		
		if(noaccess){
			return "error";
		}
		ageRangeList = cadreCommitteeService.getAgeRangeDetailsForCadre();
		genericVOList = cadreCommitteeService.getAllCasteDetailsForState();
		cadreRolesVOList = cadreCommitteeService.getBasicCadreCommitteesDetails();
		locations = cadreCommitteeService.getAllTdpCommitteeDesignations();
		List<BasicVO> accLoc = getUserAccessConstituencies();
		finalStatus = accLoc.get(0).getName();
		locationValue = accLoc.get(0).getId();
		if(panchayatId == null) //default values for prepopulate fields
		{
			panchayatId = "0";
			committeeTypeId = 0L;
			committeeId = 0L;
			result3 = "0";
		}
	}	
		return Action.SUCCESS;
	}
	
	public String cadreSearchExe()
	{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains(IConstants.TDP_CADRE_SEARCH.trim()) || entitlements.contains("COMMITTEE_MGT".trim()) || entitlements.contains("CADRE_SEARCH_ENT".trim()))){
				noaccess = true ;
			}
		
		/*if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),IConstants.TDP_CADRE_SEARCH)
				|| entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"COMMITTEE_MGT")
				|| entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADRE_SEARCH_ENT"))){
			noaccess = true ;
		}*/
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		
		if(noaccess){
			return "error";
		}
		ageRangeList = cadreCommitteeService.getAgeRangeDetailsForCadre();
		genericVOList = cadreCommitteeService.getAllCasteDetailsForState();
		cadreRolesVOList = cadreCommitteeService.getBasicCadreCommitteesDetails();
		locations = cadreCommitteeService.getAllTdpCommitteeDesignations();
		castes=cadreCommitteeService.getAllCastes();
		List<BasicVO> accLoc = getUserAccessConstituencies();
		if(accLoc!=null && accLoc.size()>0){
			finalStatus = accLoc.get(0).getName();
		}
		if(panchayatId == null) //default values for prepopulate fields
		{
			panchayatId = "0";
			committeeTypeId = 0L;
			committeeId = 0L;
			result3 = "0";
		}
	}
		return Action.SUCCESS;
	}
	public String newCadreSearchExe(){
		
		selectOptionVOList = staticDataService.getConstituencies(1l,1L);
		
		return Action.SUCCESS;
	}
	
	public List<BasicVO> getUserAccessConstituencies(){
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(locationId != null && locationId > 0)
		return tdpCadreReportService.getAccessLocationValues("MLA",locationId);	
		else
		{
			return tdpCadreReportService.getAccessLocationValues(user.getAccessType(),Long.valueOf(user.getAccessValue().trim()));		
		}
	}
	
	public String getCommitteLocations(){
		try{
			 Long locationId = 0l;
		   String locationType = request.getParameter("locationType");
		   if(request.getParameter("locationId") != null)
		   locationId = new Long(request.getParameter("locationId"));
		   constituencies = getUserAccessConstituencies();
		  if(locationId > 0)
		  {
			  locations = cadreCommitteeService.getLocationsList(locationId,locationType);  
		  }
		  else
		   locations = cadreCommitteeService.getLocationsList(constituencies.get(0).getId(),locationType);
		   
		}catch(Exception e){
			LOG.error("Exception rised in getComitteLocations",e);
		}
			return Action.SUCCESS;
		
	}
	
	public String getCadreProfileDetails()
	{
		try {
			session = request.getSession();
			RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO != null) 
			{
				genericVOList = candidateUpdationDetailsService.gettingEducationDetails();
				selectOptionVOList = staticDataService.getAllOccupations();
				eletionTypesList = cadreRegistrationService.getElectionOptionDetailsForCadre();
				cadreRolesVOList = cadreRegistrationService.getCadreLevelsForCadreSearch();
				educationList = cadreCommitteeService.getAllCasteDetailsForState();
				cadreCommitteeVO = cadreCommitteeService.getCadreDetailsByTdpCadreId(Long.valueOf(request.getParameter("tdpCadreId")));
				panchayatName = cadreCommitteeVO.getElectrolLocation();
				if(committeeMngtType != null && committeeMngtType.longValue() == 2l){
				   cadreCommitteeVO.setEligibleRoles(cadreCommitteeService.getCadreEligiableRoles(Long.valueOf(request.getParameter("tdpCadreId"))));
				  // locations = cadreCommitteeService.getAllTdpCommitteeDesignations();
				}
				//constituenciesList = cadreCommitteeService.getConstituenciesOfState();
				assemblyId = getUserAccessConstituencies().get(0).getId();
			} 
			else{
				return ERROR;
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured in getCadreProfileDetails() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	public String getSearchDetails()
	{
		try {
			
			int startIndex = 0;
			int maxIndex = 0;
			jObj = new JSONObject(getTask());
			Long locationLevel = jObj.getLong("locationLevel");
			Long locationValue = jObj.getLong("locationValue");
			String searchName = jObj.getString("searchName");
			String mobileNo = jObj.getString("mobileNo");
			
			Long casteStateId = jObj.getLong("casteStateId");
			String casteCategory = jObj.getString("casteCategory");
			Long fromAge = jObj.getLong("fromAge");
			Long toAge = jObj.getLong("toAge");
			String houseNo = jObj.getString("houseNo");
			String memberShipCardNo = jObj.getString("memberShipCardNo");
			String trNumber = jObj.getString("trNumber");
			String voterCardNo = jObj.getString("voterCardNo");
			String gender = jObj.getString("gender");
			boolean isRemoved = jObj.getBoolean("removedStatus");
			Long enrollmentId = jObj.getLong("enrollmentId");
			
			if(jObj.getString("task").equalsIgnoreCase("tdpCadreSearch"))
			{
				
				startIndex = jObj.getInt("startIndex");
				maxIndex = jObj.getInt("maxIndex");
				
			}
		//	tdpCadreVO = cadreDetailsService.searchTdpCadreDetailsBySearchCriteriaForCommitte(locationLevel,locationValue, searchName,memberShipCardNo, 
			//		voterCardNo, trNumber, mobileNo,casteStateId,casteCategory,fromAge,toAge,houseNo,gender);
			
			cadreCommitteeVO = cadreCommitteeService.searchTdpCadreDetailsBySearchCriteriaForCadreCommitte(locationLevel,locationValue, searchName,memberShipCardNo, 
							voterCardNo, trNumber, mobileNo,casteStateId,casteCategory,fromAge,toAge,houseNo,gender,startIndex,maxIndex,isRemoved,enrollmentId,null);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getSearchDetails() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getCadreSearchDetailsForCommittee()
	{
		try {
			
			int startIndex = 0;
			int maxIndex = 0;
			jObj = new JSONObject(getTask());
			Long locationLevel = jObj.getLong("locationLevel");
			Long locationValue = jObj.getLong("locationValue");
			String searchName = jObj.getString("searchName");
			String mobileNo = jObj.getString("mobileNo");
			
			Long casteStateId = jObj.getLong("casteStateId");
			String casteCategory = jObj.getString("casteCategory");
			Long fromAge = jObj.getLong("fromAge");
			Long toAge = jObj.getLong("toAge");
			String houseNo = jObj.getString("houseNo");
			String memberShipCardNo = jObj.getString("memberShipCardNo");
			String trNumber = jObj.getString("trNumber");
			String voterCardNo = jObj.getString("voterCardNo");
			String gender = jObj.getString("gender");
			boolean isRemoved = jObj.getBoolean("removedStatus");
			Long enrollmentId = jObj.getLong("enrollmentId");
			
			if(jObj.getString("task").equalsIgnoreCase("tdpCadreSearch"))
			{	
				startIndex = jObj.getInt("startIndex");
				maxIndex = jObj.getInt("maxIndex");
				
			}
		    String searchType = "committeeSearch";
			
			cadreCommitteeVO = cadreCommitteeService.searchTdpCadreDetailsBySearchCriteriaForCadreCommitte(locationLevel,locationValue, searchName,memberShipCardNo, 
							voterCardNo, trNumber, mobileNo,casteStateId,casteCategory,fromAge,toAge,houseNo,gender,startIndex,maxIndex,isRemoved,enrollmentId ,searchType);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getCadreSearchDetailsForCommittee() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getCadreDetails()
	{
		try {
			
			int startIndex = 0;
			int maxIndex = 0;
			jObj = new JSONObject(getTask());
			Long locationLevel = jObj.getLong("locationLevel");
			Long locationValue = jObj.getLong("locationValue");
			//String searchName = jObj.getString("searchName");
			String mobileNo = jObj.getString("mobileNo");
			
			//Long casteStateId = jObj.getLong("casteStateId");
			//String casteCategory = jObj.getString("casteCategory");
			//Long fromAge = jObj.getLong("fromAge");
			//Long toAge = jObj.getLong("toAge");
			//String houseNo = jObj.getString("houseNo");
			String memberShipCardNo = jObj.getString("memberShipCardNo");
			//String trNumber = jObj.getString("trNumber");
			String voterCardNo = jObj.getString("voterCardNo");
			//String gender = jObj.getString("gender");
			boolean isRemoved = jObj.getBoolean("removedStatus");
			Long enrollmentId = jObj.getLong("enrollmentId");
			if(jObj.getString("task").equalsIgnoreCase("tdpCadreSearch"))
			{
				
				startIndex = jObj.getInt("startIndex");
				maxIndex = jObj.getInt("maxIndex");
				
			}
		//	tdpCadreVO = cadreDetailsService.searchTdpCadreDetailsBySearchCriteriaForCommitte(locationLevel,locationValue, searchName,memberShipCardNo, 
			//		voterCardNo, trNumber, mobileNo,casteStateId,casteCategory,fromAge,toAge,houseNo,gender);
			
			cadreCommitteeVO = cadreCommitteeService.searchTdpCadreDetailsBySearchCriteriaForCadreCommitte(locationLevel,locationValue, "",memberShipCardNo, 
							voterCardNo, "", mobileNo,0l,"",0l,0l,"","",startIndex,maxIndex,isRemoved,enrollmentId,null);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getCadreDetails() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String generateOTP()
	{
		try {
			session = request.getSession();
			RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO != null) 
			{
				jObj = new JSONObject(getTask());
				Long mobileNo = jObj.getLong("mobileNo");
				task = cadreCommitteeService.genarateOTP(registrationVO.getRegistrationID(),mobileNo);
			} 
			else{
				return ERROR;
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured in generateOTP() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String validateOTPForMobile()
	{
		try {
			session = request.getSession();
			RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO != null) 
			{
				jObj = new JSONObject(getTask());
				Long mobileNo = jObj.getLong("mobileNo");
				Long refNo = jObj.getLong("refNo");
				Long otpNo = jObj.getLong("otpNo");
				task = cadreCommitteeService.validateOTPForUser(registrationVO.getRegistrationID(),mobileNo,refNo,otpNo);
			} 
			else{
				return ERROR;
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured in validateOTPForMobile() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getAllAffiliatedCommitties(){
		try {
			 String locationType = request.getParameter("locationType").trim();
			 String locationValue = request.getParameter("locationValue").trim();
			 Long locationLvl = null;
			 if(locationType.equalsIgnoreCase("mandal")){
				 String type =locationValue.substring(0,1).trim();
				 if(type.equalsIgnoreCase("1")){
					 locationLvl = 7l;
				 }else if(type.equalsIgnoreCase("2")){
					 locationLvl = 5l;
				 }else{
					 locationLvl = 9l;
				 }
			 }else if(locationType.equalsIgnoreCase("district")){
			     locationLvl = 11l;
			 }else if(locationType.equalsIgnoreCase("state")){
				     locationLvl = 10l;
			 }else{
				 if(locationValue.substring(0,1).trim().equalsIgnoreCase("1")){
					 locationLvl = 6l;
				 }else{
					 locationLvl = 8l;
				 }
			 }
			locations = cadreCommitteeService.getAllAffiliatedCommittiesInALocation(locationLvl,locationValue != null && locationValue.trim().length()>0 ?Long.valueOf(locationValue.substring(1)):0L);
		} catch (Exception e) {
			LOG.error("Exception occured in getAllAffiliatedCommitties() At CadreCommitteeAction ",e);
		}		
		return Action.SUCCESS;
	}
	
	public String getCommitteMembersInfo(){
		try {
			String committeeType = request.getParameter("committeeType").trim();
			if(committeeType.equalsIgnoreCase("main")){
				 String locationType = request.getParameter("locationType").trim();
				 String locationValue = request.getParameter("locationValue").trim();
				 Long locationLvl = null;
					if(locationType.equalsIgnoreCase("mandal")){
					 String type =locationValue.substring(0,1).trim();
					 if(type.equalsIgnoreCase("1")){
						 locationLvl = 7l;
					 }else if(type.equalsIgnoreCase("2")){
						 locationLvl = 5l;
					 }else{
						 locationLvl = 9l;
					 }
				 }else if(locationType.equalsIgnoreCase("district")){
					     locationLvl = 11l;
				 }else if(locationType.equalsIgnoreCase("state")){
					     locationLvl = 10l;
				 }else{
					 if(locationValue.substring(0,1).trim().equalsIgnoreCase("1")){
						 locationLvl = 6l;
					 }else{
						 locationLvl = 8l;
					 }
				 }
				 membersInfo = cadreCommitteeService.getMainCommitteeMembersInfo(locationLvl,Long.valueOf(locationValue.substring(1)));
			}else{
				membersInfo = cadreCommitteeService.getCommitteeMembersInfoNEW(Long.valueOf(request.getParameter("locationValue")));
			}
		} catch (Exception e) {
			LOG.error("Exception occured in getCommitteMembersInfo() At CadreCommitteeAction ",e);
		}		
		return Action.SUCCESS;
	}
	public String getAllCommitteeMembersInfoInALoc(){
		try {
				 String locationType = request.getParameter("locationType").trim();
				 String locationValue = request.getParameter("locationValue").trim();
				 Long locationLvl = null;
				 if(locationType.equalsIgnoreCase("mandal")){
					 String type =locationValue.substring(0,1).trim();
					 if(type.equalsIgnoreCase("1")){
						 locationLvl = 7l;
					 }else if(type.equalsIgnoreCase("2")){
						 locationLvl = 5l;
					 }else{
						 locationLvl = 9l;
					 }
				 }else if(locationType.equalsIgnoreCase("district")){
				     locationLvl = 11l;
				 }else if(locationType.equalsIgnoreCase("state")){
					     locationLvl = 10l;
				 }else{
					 if(locationValue.substring(0,1).trim().equalsIgnoreCase("1")){
						 locationLvl = 6l;
					 }else{
						 locationLvl = 8l;
					 }
				 }
				
				  membersInfo = cadreCommitteeService.getAllCommitteeMembersInfoInALoc(locationLvl,Long.valueOf(locationValue.substring(1)));
				 //membersInfo = cadreCommitteeService.getAllCommitteesFinalizedMembersInfoInALoc(locationLvl,Long.valueOf(locationValue.substring(1)));
				 
		} catch (Exception e) {
			LOG.error("Exception occured in getCommitteMembersInfo() At CadreCommitteeAction ",e);
		}		
		return Action.SUCCESS;
	}
	public String getTdpCommitteeDesignations(){
		try {
			locations = cadreCommitteeService.getAllTdpCommitteeDesignations();
		}catch (Exception e) {
			LOG.error("Exception occured in getTdpCommitteeDesignations() At CadreCommitteeAction ",e);
		}
		return Action.SUCCESS;
	}
	public String getCasteDetailsBygroupId(){
		try{
			jObj = new JSONObject(getTask());
			genericVOList = cadreCommitteeService.getCadsteDetailsByGroupId(jObj.getLong("casteGroupId"));
		}
		catch(Exception e){	
			LOG.error("Exception occured in getCasteDetailsBygroupId() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String saveCadreCommitteDetails()
	{
		
		try{
			session = request.getSession();
			RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if(registrationVO == null)
			{
				return Action.ERROR;
			}
			
			jObj = new JSONObject(getTask());
			status = cadreCommitteeService.saveCadreCommitteDetails(registrationVO.getRegistrationID(),jObj.getLong("tdpCadreId"),jObj.getLong("roleId"));
		}
		catch(Exception e){	
			LOG.error("Exception occured in saveCadreCommitteDetails() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String allConstituencys(){
		try{
			if(task.equalsIgnoreCase("assemblyNames")){
				result = cadreDashBoardService.getAssemblyConstituencies(request.getParameter("type"));
				}
		}catch(Exception e){
				LOG.error("Exception rised in allConstituencys method ",e);
		}
		return Action.SUCCESS;
		}
	public String getPanchayatiesByMandalOrMuncipality(){
		
		try{
			jObj = new JSONObject(getTask());
			genericVOList = cadreCommitteeService.getPanchayatDetailsByMandalId(jObj.getLong("mandalId"));
		}
		catch(Exception e){	
			LOG.error("Exception occured in getPanchayatiesByMandalOrMuncipality() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;	
	}
	public String getBoothsInPanchayat(){
		try{
			jObj = new JSONObject(getTask());
			wardResult=cadreCommitteeService.getBoothsInPanchayatId(jObj.getLong("panchayatId"));
		}catch(Exception e){
			LOG.error("Exception occured in getPanchayatiesByMandalOrMuncipality() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	public String getMandalMunicCorpDetails(){
		
		try{
			jObj = new JSONObject(getTask());
			locationWiseBoothDetailsVO=cadreCommitteeService.getMandalMunicCorpDetails1(jObj.getLong("constituencyId"));
		}catch(Exception e){
			LOG.error("Exception occured in getMandalMunicCorpDetails() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	public String sessionChecking(){
		
		session = request.getSession();	
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		
		
		return Action.SUCCESS;
	}

	public String updateElectrolsDetails()
	{
		try {
			session = request.getSession();	
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
				return ERROR;
			jObj = new JSONObject(getTask());
			
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			JSONArray designationArr = jObj.getJSONArray("designationArr");
			JSONArray fromDatArr = jObj.getJSONArray("fromDatArr");
			JSONArray toDatArr = jObj.getJSONArray("toDatArr");
			
			List<Long> designationIds = new LinkedList<Long>();
			List<String> fromDateStrList = new LinkedList<String>();
			List<String> toDateStrList = new LinkedList<String>();
			
			if(designationArr != null && designationArr.length()>0)
			{
				for (int i = 0; i < designationArr.length(); i++)
				{
					designationIds.add(Long.valueOf(designationArr.get(i).toString().trim()));
				}
			}
			
			if(fromDatArr != null && fromDatArr.length()>0)
			{
				for (int i = 0; i < fromDatArr.length(); i++)
				{
					fromDateStrList.add(fromDatArr.get(i).toString().trim());
				}
			}
			
			if(toDatArr != null && toDatArr.length()>0)
			{
				for (int i = 0; i < toDatArr.length(); i++)
				{
					toDateStrList.add(toDatArr.get(i).toString().trim());
				}
			}
			
			List<CadrePreviousRollesVO> eligibleRoles = new ArrayList<CadrePreviousRollesVO>();
			if(designationIds != null && designationIds.size()>0)
			{
				for (int i = 0; i < designationIds.size(); i++)
				{
					CadrePreviousRollesVO eligibleRoleVO = new CadrePreviousRollesVO();
					eligibleRoleVO.setDesignationLevelId(designationIds.get(i));
					eligibleRoleVO.setFromDateStr(fromDateStrList.get(i));
					eligibleRoleVO.setToDateStr(toDateStrList.get(i));
					eligibleRoles.add(eligibleRoleVO);
				}
			}
			
			status = cadreCommitteeService.saveMandalLevelElectrolInfo(tdpCadreId,eligibleRoles);
			
		} catch (Exception e) {
			LOG.error("Exception occured in updateElectrolsDetails() At CadreCommitteeAction ",e);
		}
		return Action.SUCCESS;
	}	
	public String updatenonAfilaiatedElectrols()
	{
		try {
			session = request.getSession();	
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
				return ERROR;
			jObj = new JSONObject(getTask());
			
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			Long afiliatedCommitteeId = jObj.getLong("afiliatedCommitteeId");
			
			status = cadreCommitteeService.saveMandalLevelAffliactedElectrolInfo(tdpCadreId,afiliatedCommitteeId);
		}catch(Exception e)
		{
			LOG.error("Exception occured in updatenonAfilaiatedElectrols() At CadreCommitteeAction ",e);
		}
		return Action.SUCCESS;
	}
	public String assignCadreToCommitee()
	{	
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("CADRE_COMMITTEE_MANAGEMENT".trim()))){
				noaccess = true ;
			}
		
		/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADRE_COMMITTEE_MANAGEMENT")){
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
	
	public String getLocationsForCommitteeLevel(){
		session = request.getSession();	
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		
		try{
			jObj = new JSONObject(getTask());
			Long locLevel =  jObj.getLong("level");
			Long constiId = jObj.getLong("constiId");
			idNameVOList = cadreCommitteeService.getLocationsOfCommitteeLevel(locLevel, constiId);
		}catch(Exception e){
			LOG.error("Exception occured in getPanchayatiesByMandalOrMuncipality() At CadreCommitteeAction ",e);
		}
		
		
		return Action.SUCCESS;
	}
	
	public String getConstituenciesByLocationLevel(){
		session = request.getSession();	
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		
		try{
			jObj = new JSONObject(getTask());
			Long locLevel =  jObj.getLong("level");
			
			
			idNameVOList = cadreCommitteeService.getConstituenciesOfState(locLevel);
		}catch(Exception e){
			LOG.error("Exception occured in getPanchayatiesByMandalOrMuncipality() At CadreCommitteeAction ",e);
		}
		
		
		return Action.SUCCESS;
	}
	
	public String committeeUpdateApproveAction(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("TDP_COMMITTEE_ADMIN".trim()))){
				noaccess = true ;
			}
		
		/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"TDP_COMMITTEE_ADMIN")){
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
	//---cadreCommitteeRequest---
			public String cadreCommitteeRequest()
			{
				RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
				boolean noaccess = false;
				if(regVO==null){
					return "input";
				}
				List<String> entitlements = null;
				if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
					entitlements = regVO.getEntitlements();
					if(!(entitlements.contains("CADRE_COMMITTEE_MANAGEMENT".trim()))){
						noaccess = true ;
					}
				
				/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADRE_COMMITTEE_MANAGEMENT")){
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
			public String getCommitteMembersInfoRequest()
			{
				try {
					String committeeType = request.getParameter("committeeType").trim();
					
					if(committeeType.equalsIgnoreCase("main")){
						 String locationType = request.getParameter("locationType").trim();
						 String locationValue = request.getParameter("locationValue").trim();
						 Long locationLvl = null;
						 if(locationType.equalsIgnoreCase("mandal")){
							 if(locationValue.substring(0,1).trim().equalsIgnoreCase("1")){
								 locationLvl = 7l;
							 }else if(locationValue.substring(0,1).trim().equalsIgnoreCase("2")){
								 locationLvl = 5l;
							 }else{
								 locationLvl = 9l;
							 }
						 }else if(locationType.equalsIgnoreCase("district")){
						     locationLvl = 11l;
						 }else if(locationType.equalsIgnoreCase("state")){
							     locationLvl = 10l;
						 }else{
							 if(locationValue.substring(0,1).trim().equalsIgnoreCase("1")){
								 locationLvl = 6l;
							 }else{
								 locationLvl = 8l;
							 }
						 }
						
						 
						 membersInfo = cadreCommitteeService.getMainCommitteeMembersInfoRequest(locationLvl,Long.valueOf(locationValue.substring(1)));
					}else{
						membersInfo = cadreCommitteeService.getCommitteeMembersInfoRequest(Long.valueOf(request.getParameter("locationValue")));
					}
				} catch (Exception e) {
					LOG.error("Exception occured in getCommitteMembersInfo() At CadreCommitteeAction ",e);
				}		
				return Action.SUCCESS;
				
				
				
			}
			public String cadreCommitteeIncreasedPositionsOrChangeDesignations()
			{
				try {
					session = request.getSession();
					RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
					List<LocationWiseBoothDetailsVO> changeDesignationsList=new ArrayList<LocationWiseBoothDetailsVO>();
					if (registrationVO != null) 
					{
						jObj = new JSONObject(getTask());
						Long requestUserId=registrationVO.getRegistrationID(); 
						if(jObj.getString("type").equalsIgnoreCase("positionsIncreased"))
							status = cadreCommitteeService.cadreCommitteeIncreasedPositionsOrChangeDesignations(jObj.getLong("tdpCommitteeRoleId"),requestUserId,jObj.getLong("currentmaxPositions"),jObj.getLong("requestedMaxPositions"),jObj.getString("type"),null,null);
						else if(jObj.getString("type").equalsIgnoreCase("changeDesignations")){
							 Long committeeId=0l;
							 JSONArray jArray = jObj.getJSONArray("requestArray");
							 for (int i = 0; i < jArray.length(); i++) 
							 {
								JSONObject obj = jArray.getJSONObject(i);
								LocationWiseBoothDetailsVO changeDesignationsVO=new LocationWiseBoothDetailsVO();
								changeDesignationsVO.setLocationId(obj.getLong("tdpCommitteeMemberId"));//tdpCommitteeMemberId
								changeDesignationsVO.setTotal(obj.getLong("tdpCadreId"));
								changeDesignationsVO.setPopulation(obj.getLong("currentRole"));//currentRole
								changeDesignationsVO.setVotesPolled(obj.getLong("newRole"));//newRole
								changeDesignationsList.add(changeDesignationsVO);
							 }
							 String committeeType=jObj.getString("globalReqCommitteeType");
							 if(committeeType.equalsIgnoreCase("main")){
								 String locationType=jObj.getString("globalReqLocationType").trim();
							     String locationValue=jObj.getString("globalReqLocationValue").trim();
								
								 Long locationLvl = null;
								 if(locationType.equalsIgnoreCase("mandal")){
									 String type =locationValue.substring(0,1).trim();
									 if(type.equalsIgnoreCase("1")){
										 locationLvl = 7l;
									 }else if(type.equalsIgnoreCase("2")){
										 locationLvl = 5l;
									 }else{
										 locationLvl = 9l;
									 }
								 }else if(locationType.equalsIgnoreCase("district")){
								     locationLvl = 11l;
								 }else if(locationType.equalsIgnoreCase("state")){
									     locationLvl = 10l;
								 }else{
									 if(locationValue.substring(0,1).trim().equalsIgnoreCase("1")){
										 locationLvl = 6l;
									 }else{
										 locationLvl = 8l;
									 }
								 }
								 committeeId= cadreCommitteeService.gettingCommitteeIdForMainCommittee(locationLvl,Long.valueOf(locationValue.substring(1)));
							}else{
								    
								committeeId =Long.valueOf(request.getParameter("globalReqLocationValue"));
							}
							 
							 
							status=cadreCommitteeService.cadreCommitteeIncreasedPositionsOrChangeDesignations(null,requestUserId,null,null,jObj.getString("type"),changeDesignationsList,committeeId);   
						}
					} 
					else{
						return ERROR;
					}
					
				} catch (Exception e) {
					LOG.error("Exception occured in cadreCommitteeInsertedPositions() At CadreCommitteeAction ",e);
				}
				
				return Action.SUCCESS;
			}
	
	public String getCommitteesForApproval(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("TDP_COMMITTEE_ADMIN".trim()))){
				noaccess = true ;
			}
		
		/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"TDP_COMMITTEE_ADMIN")){
			noaccess = true ;
		}*/
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		
		if(noaccess){
			return "error";
		}
		
		try{
			jObj = new JSONObject(getTask());
			Long startNo =  jObj.getLong("startNo");
			Long endNo = jObj.getLong("endNo");
			approvalRecordsList = cadreCommitteeService.getCommitteesForApproval(startNo, endNo,null);
		}catch (Exception e) {
			LOG.error(" Exception Raised in getCommitteesForApproval " + e);
		}
	}
		return Action.SUCCESS;
	}
	
	public String checkIsVacancyForDesignation()
	{
		try {
			jObj = new JSONObject(getTask());
			Long designationId =  jObj.getLong("designationId");
			
			task = cadreCommitteeService.checkIsVacancyForDesignation(designationId);
			
		} catch (Exception e) {
			LOG.error("Exception occured in checkIsVacancyForDesignation() At CadreCommitteeAction ",e);
		}
		return Action.SUCCESS;
}
	
	public String updateCommitteePosCount(){
		try {
			jObj = new JSONObject(getTask());
			Long roleId =  jObj.getLong("tdpCommitteeRoleId");
			Long maxCount =  jObj.getLong("maxCount");
			String type =  jObj.getString("type");
			Long increasedPosId = jObj.getLong("increasedPosId");
			Long apprvCount = jObj.getLong("approveCount");
			
			finalStatus = cadreCommitteeService.updateCommitteePosCount(roleId,maxCount,type,increasedPosId,apprvCount);
			
		} catch (Exception e) {
			LOG.error("Exception occured in updateCommitteePosCount() At CadreCommitteeAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String statusCountOfApproval(){
		try {
			statusFinalVO = cadreCommitteeService.getStatusCountsOfApproval();
			
		} catch (Exception e) {
			LOG.error("Exception occured in updateCommitteePosCount() At statusCountOfApproval ",e);
		}
		return Action.SUCCESS;
	}
	
	
	
	public String gettingRequestsDetailsForAUser(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("CADRE_COMMITTEE_MANAGEMENT".trim()))){
				noaccess = true ;
			}
		
		/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADRE_COMMITTEE_MANAGEMENT")){
			noaccess = true ;
		}*/
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		
		if(noaccess){
			return "error";
		}
		
		try{
			
			Long requestUserId=regVO.getRegistrationID();
			String type=request.getParameter("type");
			if(type.equalsIgnoreCase("positionsIncreased"))
			  approvalRecordsList = cadreCommitteeService.getCommitteesForApproval(null, null,requestUserId);
			else if(type.equalsIgnoreCase("changeDesignations"))
			  approvalRecordsList = cadreCommitteeService.changeDesignationRecordsForAUser(requestUserId,null,null);	
		}
		catch (Exception e) {
			LOG.error(" Exception Raised in getCommitteesForApproval " + e);
		}
	}
		return Action.SUCCESS;
	}
	
public String getSummaryDetails(){
		

	session = request.getSession();	
	try {
		jObj = new JSONObject(getTask());

		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user != null){
			
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
			
			if(jObj.getString("locationId") != null && jObj.getLong("locationId") > 0)
			{
				returnList = cadreCommitteeService.getSummaryDetails(jObj.getString("locationId"),reqLocationType,startDate,endDate, 
						 committeeEnrollmentIdsLst,levelIdsLsit);
			}
			else
			{
			returnList = cadreCommitteeService.getSummaryDetails(user.getAccessValue(),reqLocationType, startDate,endDate, 
					  committeeEnrollmentIdsLst,levelIdsLsit);
			}
		}else{
			return ERROR;
		}
	}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getStartedCommitteesCountInALocation(){
		try {
			jObj = new JSONObject(getTask());
		
		session = request.getSession();	
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user != null){
			
			if(jObj.getString("locationId")!= null && jObj.getLong("locationId") > 0)
			{
			committeesCountsInfo= cadreCommitteeService.getStartedCommitteesCountInALocation(jObj.getString("locationId"));
			}
			else
				committeesCountsInfo= cadreCommitteeService.getStartedCommitteesCountInALocation(user.getAccessValue());	
		}else{
			return ERROR;
		}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
	}
	
	public String gettingMandalAndMuncipalAndDivisonSummary(){
		
		session = request.getSession();	
		try {
			jObj = new JSONObject(getTask());
			List<Long> committeeEnrollmentIdsLst = new ArrayList<Long>();
			JSONArray committeeEnrollmentIds=jObj.getJSONArray("committeeEnrollmentId");
			if(committeeEnrollmentIds!=null &&  committeeEnrollmentIds.length()>0){
				for( int i=0;i<committeeEnrollmentIds.length();i++){
					committeeEnrollmentIdsLst.add(Long.valueOf(committeeEnrollmentIds.getString(i))); 
				}
			}
			String startDate = jObj.getString("startDate"); 
			String endDate = jObj.getString("endDate");
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		
		String reqLocationTypeStr = jObj.getString("reqLocationTypeStr");
		List<Long> levelIdsList = new ArrayList<Long>();
		JSONArray levelIdLsit=jObj.getJSONArray("levelIds");
		if(levelIdLsit!=null &&  levelIdLsit.length()>0){
			for( int i=0;i<levelIdLsit.length();i++){
				levelIdsList.add(Long.valueOf(levelIdLsit.getString(i))); 
			}
		}
		
		
		if(user != null){
			if(jObj.getString("locationId") != null && jObj.getLong("locationId") > 0)
			{
			returnList= cadreCommitteeService.gettingMandalAndMuncipalAndDivisonSummary(jObj.getString("locationId"),committeeEnrollmentIdsLst,startDate,endDate,reqLocationTypeStr,levelIdsList);
			}
			else
			{
				returnList= cadreCommitteeService.gettingMandalAndMuncipalAndDivisonSummary(user.getAccessValue(),committeeEnrollmentIdsLst,startDate,endDate,reqLocationTypeStr,levelIdsList);	
			}
		}else{
			return ERROR;
		}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String getCommitteSummaryInfo(){
		try{
		session = request.getSession();	
		jObj = new JSONObject(getTask());
		List<Long> committeeEnrollmentIdsLst = new ArrayList<Long>();
		JSONArray committeeEnrollmentIds=jObj.getJSONArray("committeeEnrollmentId");
		if(committeeEnrollmentIds!=null &&  committeeEnrollmentIds.length()>0){
			for( int i=0;i<committeeEnrollmentIds.length();i++){
				committeeEnrollmentIdsLst.add(Long.valueOf(committeeEnrollmentIds.getString(i))); 
			}
		}
		
		List<Long> committeeLevelIdsList = new ArrayList<Long>(0);
		JSONArray committeeLevelIdList=jObj.getJSONArray("committeeLevelIdsList");
		if(committeeLevelIdList!=null &&  committeeLevelIdList.length()>0){
			for( int i=0;i<committeeLevelIdList.length();i++){
				committeeLevelIdsList.add(Long.valueOf(committeeLevelIdList.getString(i))); 
			}
		}
		List<Long> mainOrAfflCommitteIds = new ArrayList<Long>(0);
		JSONArray mainOrAffliCommitteIds=jObj.getJSONArray("mainOrAfflCommitteIds");
		if(mainOrAffliCommitteIds!=null &&  mainOrAffliCommitteIds.length()>0){
			for( int i=0;i<mainOrAffliCommitteIds.length();i++){
				mainOrAfflCommitteIds.add(Long.valueOf(mainOrAffliCommitteIds.getString(i))); 
			}
		}
		
		
		String startDate = jObj.getString("startDate");
		String endDate = jObj.getString("endDate"); 
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user != null && (user.getEntitlements().contains("TDP_COMMITTEE_STATE_DISTRICT_ACCESS") || user.getEntitlements().contains("COMMITTEE_MGT")
				 || user.getEntitlements().contains("TDP_COMMITTEE_ADMIN") || (user.getIsAdmin() != null && user.getIsAdmin().equalsIgnoreCase("true")))){
			
			if(jObj.getString("locationId") != null && jObj.getLong("locationId") > 0)
			{
				returnList= cadreCommitteeService.getCommitteeSummaryInfoByUserAccess(jObj.getLong("locationId"),jObj.getString("reqLocationType"),committeeEnrollmentIdsLst,startDate,endDate,committeeLevelIdsList,mainOrAfflCommitteIds);
			}
			else
			{
				returnList= cadreCommitteeService.getCommitteeSummaryInfoByUserAccess(new Long(user.getAccessValue()),user.getAccessType(),committeeEnrollmentIdsLst,startDate,endDate,committeeLevelIdsList,mainOrAfflCommitteIds);
			}
		}else{
			return ERROR;
		}
		}
		catch(Exception e)
		{
			LOG.error(e);
		}
		return Action.SUCCESS;
	}

	
	/*public String getMandalMuncipalDivisonTotalCommittees(){
		session = request.getSession();	
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user != null){
			locations = cadreCommitteeService.getMandalMuncipalDivisonTotalCommittees(user.getAccessValue());
		}else{
			return ERROR;
		}
		return Action.SUCCESS;
	}*/
	public String getAffiliatedCommitteMembersInfo(){
		try{
			jObj = new JSONObject(getTask());
			JSONArray ids = jObj.getJSONArray("ids");
			
			List<Long> affiliIds = new ArrayList<Long>();
			
			if(ids !=null && ids.length() >0){
				for(int i=0; i<ids.length(); i++ ){
					affiliIds.add(Long.valueOf(ids.get(i).toString().trim()));
				}
			}
			membersInfo = cadreCommitteeService.getAffiliatedCommitteMembersInfo(affiliIds);
		}
		catch (Exception e) {
			LOG.error("Exception raised in getAffiliatedCommitteMembersInfo()",e);
		}
		return Action.SUCCESS;
		
	}
	
	public String getElctoralInfoForALocation(){
		try{
			jObj = new JSONObject(getTask());
			Long locationValue = jObj.getLong("locationId");
			returnResult=cadreCommitteeService.getElctoralInfoForALocation(locationValue);
		}catch (Exception e) {
			LOG.error("Exception raised in getElctoralInfoForALocation()",e);
		}
		
		return Action.SUCCESS;
	}
	public String getMandalsByConstituency(){
		try{
			HttpSession session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			jObj = new JSONObject(getTask());
			if(jObj.getString("locationId") != null &&  jObj.getLong("locationId") > 0)
			{
			constituencies = tdpCadreReportService.getAccessLocationValues("MLA",jObj.getLong("locationId") );
			}
			else
			{
				constituencies = tdpCadreReportService.getAccessLocationValues(user.getAccessType(),Long.valueOf(user.getAccessValue().trim()));
			
			}
			
			/*constituencies = getUserAccessConstituencies();*/
		//	if(jObj.getString("locationId") != null &&  jObj.getLong("locationId") > 0){
				locations=cadreCommitteeService.getMandalsByConstituency(constituencies.get(0).getId());
			//}
			
		}catch(Exception e){
			LOG.error("Exception occured in getMandalsByConstituency() method ",e);
		}
		
		return Action.SUCCESS;
	}
	public String getPanchayatWardByMandal(){
		try{
			jObj = new JSONObject(getTask());
			String mandalId = jObj.getString("mandalId");
			Long constituencyId = jObj.getLong("constituencyId");
			locations=cadreCommitteeService.getPanchayatWardByMandalId(mandalId,constituencyId);
			
		}catch(Exception e){	
			LOG.error("Exception occured in getPanchayatWardByMandal() method ",e);
		}
		return Action.SUCCESS;
	}	
	public String updateCommitteeMemberDesignation(){
		try{
			session = request.getSession();
			RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			List<LocationWiseBoothDetailsVO> changeDesignationsList=new ArrayList<LocationWiseBoothDetailsVO>();
			jObj = new JSONObject(getTask());
			JSONArray jArray = jObj.getJSONArray("requestArray");
			 for (int i = 0; i < jArray.length(); i++) 
			 {
				JSONObject obj = jArray.getJSONObject(i);
				LocationWiseBoothDetailsVO changeDesignationsVO=new LocationWiseBoothDetailsVO();
				changeDesignationsVO.setLocationId(obj.getLong("tdpCommitteeMemberId"));//tdpCommitteeMemberId
				changeDesignationsVO.setTotal(obj.getLong("tdpCadreId"));
				//changeDesignationsVO.setPopulation(obj.getLong("currentRole"));//currentRole
				changeDesignationsVO.setVotesPolled(obj.getLong("newRole"));//newRole
				changeDesignationsList.add(changeDesignationsVO);
			 }
			 status = cadreCommitteeService.updateCandidateDesignation(jObj.getLong("committeeId"),changeDesignationsList,registrationVO.getRegistrationID());
		}catch(Exception e){
			LOG.error(" Exception Raised in updateCommitteeMemberDesignation" , e);
			status = new ResultStatus();
			status.setResultCode(0);
		}
		 return Action.SUCCESS;
	}
	
	
	public String getCommitteesForApprovalForChangeDesignations(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("TDP_COMMITTEE_ADMIN".trim()))){
				noaccess = true ;
			}
		
		/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"TDP_COMMITTEE_ADMIN")){
			noaccess = true ;
		}*/
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		
		if(noaccess){
			return "error";
		}
		
		try{
			jObj = new JSONObject(getTask());
			Long startNo =  jObj.getLong("startNo");
			Long endNo = jObj.getLong("endNo");
			approvalRecordsList = cadreCommitteeService.changeDesignationRecordsForAUser(null,startNo,endNo);	
		}catch (Exception e) {
			LOG.error(" Exception Raised in getCommitteesForApprovalForChangeDesignations " , e);
		}
	}	
		return Action.SUCCESS;
	}
	
	
	
	
	public String approvingChangeDesignations(){
		
		try {
			jObj = new JSONObject(getTask());
			Long cadreCommitteeIncreasedPositionsId =  jObj.getLong("cadreCommitteeIncreasedPositionsId");
			String approvedStatus=jObj.getString("status");
            status = cadreCommitteeService.approvingChangeDesignations(cadreCommitteeIncreasedPositionsId,approvedStatus);
			
		} catch (Exception e) {
			LOG.error("Exception occured in approvingChangeDesignations() At CadreCommitteeAction ",e);
		}
		return Action.SUCCESS;
	}
	public String statusForChangeDesignationsApproval(){
		try {
			
			statusFinalVO = cadreCommitteeService.statusForChangeDesignationsApproval();
		} catch (Exception e) {
			LOG.error("Exception occured in statusForChangeDesignationsApproval ",e);
		}
		return Action.SUCCESS;
	}
	
	
	public String stateDistrictLvlCommittee(){

		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		
		if(regVO==null){
			return "input";
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("TDP_COMMITTEE_STATE_DISTRICT_ACCESS".trim())) && !(entitlements.contains("COMMITTEE_MGT".trim()))){
				return "error";
			}
		/*if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"TDP_COMMITTEE_STATE_DISTRICT_ACCESS")) 
				&&  !(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"COMMITTEE_MGT"))){
			return "error";
		}*/
			
		if(regVO.getAccessType().equalsIgnoreCase("DISTRICT") && reqLocationType.equalsIgnoreCase("district")){
			
			reqLocationType = "district";
			if(locationId != null && locationId > 0)
			{
				reqLocationValue = "1"+locationId;
				globalLocationName = cadreCommitteeService.getDistrictName(Long.valueOf(locationId))+" District";
			}
			else
			{
			reqLocationValue = "1"+regVO.getAccessValue();
			globalLocationName = cadreCommitteeService.getDistrictName(Long.valueOf(regVO.getAccessValue().trim()))+" District";
			}
		}else if(regVO.getAccessType().equalsIgnoreCase("STATE")){
			reqLocationType = "state";
			reqLocationValue = "2"+regVO.getAccessValue();
			if(regVO.getAccessValue().equalsIgnoreCase("1")){
				globalLocationName = "Andhra Pradesh State";
			}else{
				globalLocationName = "Telangana State";
			}
		}
		
		finalStatus = globalLocationName;
	}
		return Action.SUCCESS;
	}
	public String cadreSearch()
	{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		
		if(regVO==null){
			return "input";
		}
		return Action.SUCCESS;
	}
	public String getCommittees()
	{
		try{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		
		if(regVO==null){
			return "input";
		}
		jObj = new JSONObject(getTask());
		if(jObj.getString("task").equalsIgnoreCase("allCommittess"))
		basicList =  cadreCommitteeService.getAllCommittees();
		else if(jObj.getString("task").equalsIgnoreCase("roles"))
			basicList =  cadreCommitteeService.getCommitteeRoles();
		
		}
		catch(Exception e)
		{
			LOG.error(e);
		}
		return Action.SUCCESS;
	}
	
	public String sendSmsForInvotees()
	{
		try {
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");			
			if(regVO==null){
				return "input";
			}
			
			jObj = new JSONObject(getTask());
			JSONArray mobileNoArr  = jObj.getJSONArray("mobileNumbersArr");
			String message = jObj.getString("message");
			List<Long> mobileNoList = new ArrayList<Long>();
			if(mobileNoArr!= null && mobileNoArr.length()>0)
			{
				for(int i=0;i<mobileNoArr.length();i++)
				{
					mobileNoList.add(Long.valueOf(mobileNoArr.getString(i).trim()));
				}
			}
			
			status = cadreCommitteeService.sendSmsForInvitees(regVO.getRegistrationID(),mobileNoList,message);
			
		} catch (Exception e) {
			LOG.error("Exception occured in sendSmsForInvotees ",e);
		}
		
		return Action.SUCCESS;
	}	
	
	public String getEventGroups()
	{
		try {
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");			
			if(regVO==null){
				return Action.ERROR;				
			}
			idNameVOList = cadreCommitteeService.getPartyEventGroups(regVO.getRegistrationID());
		} catch (Exception e) {
			LOG.error("Exception occured in getPublicRepresentatives ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getPublicRepresentativesTypes()
	{
		try {
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");			
			if(regVO==null){
				return Action.ERROR;				
			}
			
			idNameVOList = cadreCommitteeService.getPublicRepresenttativesList();
		} catch (Exception e) {
			LOG.error("Exception occured in getPublicRepresentatives ",e);
		}
		return Action.SUCCESS;
	}
	
	 public String getEventsForUser()
	{
		try {
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");			
			if(regVO==null){
				return Action.ERROR;				
			}
			
			idNameVOList = cadreCommitteeService.getPartyEvents(regVO.getRegistrationID());
		} catch (Exception e) {
			LOG.error("Exception occured in getPublicRepresentatives ",e);
		}
		return Action.SUCCESS;
	}
	 
	public String getMandalDetailsByConstituency(){
		try{
			jObj = new JSONObject(getTask());
			locations=cadreCommitteeService.getMandalsByConstituency(jObj.getLong("constituencyId"));
		}catch(Exception e){
			LOG.error("Exception occured in getMandalsByConstituency() method ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String createORUpdateANewEvent()
	{
		try{
			HttpSession session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
			{
				return Action.ERROR;
			}
			jObj = new JSONObject(getTask());
			UserEventDetailsVO userEventDetailsVO = new UserEventDetailsVO();
			userEventDetailsVO.setEventId(jObj.getLong("eventId"));
			userEventDetailsVO.setEventName(jObj.getString("eventName"));
			userEventDetailsVO.setStatus(jObj.getString("description"));
			userEventDetailsVO.setEntryLimit(jObj.getLong("entryLimit"));
			userEventDetailsVO.setStartDate(jObj.getString("startDate"));
			userEventDetailsVO.setEndDate(jObj.getString("endDate"));
			userEventDetailsVO.setStartTime(jObj.getString("startTime"));
			//userEventDetailsVO.setStartTime("07:00:00");
			userEventDetailsVO.setEndTime(jObj.getString("endTime"));
			//userEventDetailsVO.setEndTime("20:00:00");
			userEventDetailsVO.setServerWorkMode(jObj.getString("serverMode"));
			userEventDetailsVO.setTabWorkMode(jObj.getString("tabMode"));
			userEventDetailsVO.setMainEventId(jObj.getLong("mainEventId"));
			String actionType = jObj.getString("actionType");
			status=cadreCommitteeService.createNewEvent(user.getRegistrationID(),userEventDetailsVO,actionType);
		}catch(Exception e){
			LOG.error("Exception occured in getMandalsByConstituency() method ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String careateNewUserForEvents()
	{
		try{
			HttpSession session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
			{
				return Action.ERROR;
			}
			jObj = new JSONObject(getTask());
			String firstName = jObj.getString("firstName");
			String lastName = jObj.getString("lastName");
			String userName = jObj.getString("userName");
			String password = jObj.getString("password");
			String mobileNo = jObj.getString("mobileNo");
			
			status=cadreCommitteeService.createANewUserForEvents(user.getRegistrationID(),firstName,lastName,userName,password,mobileNo);
		}catch(Exception e){
			LOG.error("Exception occured in getMandalsByConstituency() method ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String updateEventSettings()
	{
		try{
			HttpSession session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
			{
				return Action.ERROR;
			}
			jObj = new JSONObject(getTask());
			UserEventDetailsVO userEventDetailsVO = new UserEventDetailsVO();
			userEventDetailsVO.setEventId(jObj.getLong("eventId"));
			//userEventDetailsVO.setRFID(jObj.getString("rfid"));
			
			JSONArray rfidActionsArr = jObj.getJSONArray("rfidActionsArr");
			
			if(rfidActionsArr != null && rfidActionsArr.length()>0)
			{
				List<UserEventDetailsVO> sublist = new ArrayList<UserEventDetailsVO>();
				for(int i=0;i<rfidActionsArr.length();i++)
				{
					UserEventDetailsVO vo = new UserEventDetailsVO();
					
					JSONObject eachObject = rfidActionsArr.getJSONObject(i);
					
					vo.setRFID(eachObject.getString("rfid"));
					vo.setRegText(eachObject.getString("regText"));
					vo.setSectorNo(eachObject.getLong("sectorNo"));
					vo.setBlockNo(eachObject.getLong("blockNo"));
					
					sublist.add(vo);
				}
				
				if(sublist != null && sublist.size()>0)
				{
					userEventDetailsVO.setSubList(sublist);
				}
			}
			
			String actionType = jObj.getString("actionType");
			
			status=cadreCommitteeService.updateEventSettings(user.getRegistrationID(),userEventDetailsVO,actionType);
		}catch(Exception e){
			LOG.error("Exception occured in getMandalsByConstituency() method ",e);
		}
		
		return Action.SUCCESS;
	}
	
	
	public String assignEventForUser()
	{
		try{
			HttpSession session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
			{
				return Action.ERROR;
			}
			jObj = new JSONObject(getTask());
			UserEventDetailsVO userEventDetailsVO = new UserEventDetailsVO();
			userEventDetailsVO.setEventId(jObj.getLong("eventId"));
			userEventDetailsVO.setUserId(jObj.getLong("userId"));
			status=cadreCommitteeService.assignEventForUser(user.getRegistrationID(),userEventDetailsVO);
		}catch(Exception e){
			LOG.error("Exception occured in getMandalsByConstituency() method ",e);
		}
		
		return Action.SUCCESS;
	}
	public String prePopulatingValuesOfEvents(){
		try{
			jObj = new JSONObject(getTask());
			
			Long eventId=jObj.getLong("eventId");
			
			eventCreationVO=cadreCommitteeService.getPrePopulatingValuesOfEvents(eventId);
			
		}catch (Exception e) {
			LOG.error("Exception occured in prePopulatingValuesOfEvents() method in cadreCommitteAction Class ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getDistrictNamesIds(){
		try {
			HttpSession session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
			{
				return Action.ERROR;
			}
			resultList = cadreCommitteeService.getDistrictNamesIds(user.getRegistrationID());
		} catch (Exception e) {
			LOG.error("Exception raised in getDistrictNamesIds",e);
		}
		return Action.SUCCESS;
	}
	
	
	public String cadreInfoExe()
	{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		
		if(regVO==null){
			return "input";
		}
		if(regVO != null && !(regVO.getEntitlements().contains("COMMITTEE_MGT") || regVO.getEntitlements().contains("COMMITTEE_MANAGEMENT_MULTIPLE_AREAS_ENTITLEMENT")))
		{
			return Action.ERROR;
		}
			return Action.SUCCESS;
		
	}
	public String getPanchayatDetailsByMandalIdAddingParam(){
		
		try{
			jObj = new JSONObject(getTask());
			genericVOList = cadreCommitteeService.getPanchayatDetailsByMandalIdAddingParam(jObj.getLong("mandalId"));
		}
		catch(Exception e){	
			LOG.error("Exception occured in getPanchayatDetailsByMandalIdAddingParam() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;	
	}
	
	public String getAllCadreDeleteReasons(){
		try{
			idNameVOList = cadreCommitteeService.getAllCadreDeleteReasons();
		}
		catch(Exception e){	
			LOG.error("Exception occured in getAllCadreDeleteReasons() At CadreCommitteeAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String saveRemovingCadreDetails(){
		try{
			jObj = new JSONObject(getTask());
			
			Long cadreId = jObj.getLong("cadreId");
			Long reasonId = jObj.getLong("reasonId");
			String remark = jObj.getString("remarkTxt");
			
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			if(regVO==null){
				return "error";
			}
			if(regVO != null && (regVO.getEntitlements().contains("CADRE_DELETE_ENTITLEMENT_GROUP") || regVO.getEntitlements().contains("CADRE_DELETE_ENTITLEMENT")))
			{
				status = cadreCommitteeService.saveRemovingCadreDetailsAction(cadreId,reasonId,remark,regVO.getRegistrationID());
			}
		}
		catch(Exception e){	
			LOG.error("Exception occured in getAllCadreDeleteReasons() At CadreCommitteeAction",e);
		}
		return Action.SUCCESS;
		
	}
	public String updateMobileNumberAndCasteForCadre(){
		try{
			jObj = new JSONObject(getTask());
			
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			String mobileNo = jObj.getString("mobileNo");
			Long   casteId =  jObj.getLong("casteId");
			
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			if(regVO==null){
				return "error";
			}
			if(regVO != null && (regVO.getEntitlements().contains("CADRE_DELETE_ENTITLEMENT_GROUP") || regVO.getEntitlements().contains("CADRE_DELETE_ENTITLEMENT")))
			{
				status = cadreCommitteeService.updateMobileNumberAndCasteForCadre(tdpCadreId,mobileNo,casteId,regVO.getRegistrationID());
			}
		}
		catch(Exception e){	
			LOG.error("Exception occured in getAllCadreDeleteReasons() At CadreCommitteeAction",e);
		}
		return Action.SUCCESS;
	}
	public String saveActivityDetails(){
		
		try {
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			Long userId = regVO.getRegistrationID();
			try {
				 jObj = new JSONObject(getTask());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(jObj != null){
				activityVO = new ActivityVO();
				activityVO.setActivityLevelId(jObj.getLong("activityScopeId"));
				activityVO.setConstituencyId(jObj.getLong("contituencyId"));
				
				ActivityVO locationVO = new ActivityVO();
				locationVO.setConductedDate(jObj.getString("conductedDateStr").trim());
				locationVO.setPlannedDate(jObj.getString("plannedDateStr").trim());
				locationVO.setLocationValue(jObj.getLong("locationValue"));				
				locationVO.setLocationLevel(jObj.getLong("activityLevelId"));
				locationVO.setDay(jObj.getLong("day"));
				activityVO.getActivityVoList().add(locationVO);
			}
			 
			status = cadreCommitteeService.saveActivityDetails(activityVO,userId);
			
		} catch (Exception e) {
			LOG.error("Exception occured in saveActivityDetails() At CadreCommitteeAction",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getLevelIdDetailsForCadreCommittee() {
		try{ 
          jObj = new JSONObject(getTask());
			
			Long levelId = jObj.getLong("levelId");
			
		basicList =  cadreCommitteeService.getAllCommitteesForLevelId(levelId);
		}
		catch(Exception e)
		{
			LOG.error(e);
		}
		return Action.SUCCESS;
	}
	
	public String saveRegistrationFeedbackQueriesDetails() {
		try{ 
          jObj = new JSONObject(getTask());
			
		String name = jObj.getString("name");
		String mobile = jObj.getString("number");
		String email = jObj.getString("emalValue");
		String description = jObj.getString("textArea");
		
		RegistrationQueriesVO regQueriesVO = new RegistrationQueriesVO();
		regQueriesVO.setName(name);
		regQueriesVO.setMobileNo(mobile);
		regQueriesVO.setEmail(email);
		regQueriesVO.setDescription(description);
			
		status =  cadreRegistrationService.saveRegistrationQueriesForm(regQueriesVO);
		}
		catch(Exception e)
		{
			LOG.error(e);
		}
		return Action.SUCCESS;
	}
	public String getLatestLattitudeLangitudeOfTabUserAgentDetails() {
		try{ 
          jObj = new JSONObject(getTask());
			
			Long constitunecyId = jObj.getLong("constitunecyId");
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate"); 
			gisTrackVolst =  cadreRegistrationService.getLatestLattitudeLangitudeOfTabUserAgentDetails(constitunecyId,startDate,endDate);
		}
		catch(Exception e)
		{
			LOG.error("Exception occured in getLatestLattitudeLangitudeOfTabUserAgentDetails() At CadreCommitteeAction",e);
		}
		return Action.SUCCESS;
	}
	public String tdpAgentAreaMap(){
		return Action.SUCCESS;
	}
	public String tdpUserWiseMap(){
		return Action.SUCCESS;
	}
	public String tdpUserWiseReportMap(){
		return Action.SUCCESS;
	}
	public String getFieldMonitoringMapReportDetails()
	{
	try{ 
          jObj = new JSONObject(getTask());
			
			Long constitunecyId = jObj.getLong("constitunecyId");
			Long fieldUserId = jObj.getLong("fieldUserId");
			cadreCommitteeList =  cadreCommitteeService.getFieldMonitoringMapReportDetails(constitunecyId,fieldUserId);
		}
		catch(Exception e)
		{
			LOG.error("Exception occured in getFieldMonitoringMapReportDetails() At CadreCommitteeAction",e);
		}
		return Action.SUCCESS;
	}
	public String getUpdateSearchDetails()
	{
		try {
			
			int startIndex = 0;
			int maxIndex = 0;
			jObj = new JSONObject(getTask());
			String searchType = jObj.getString("searchType");
			String searchValue = jObj.getString("searchValue");
			
			
			cadreCommitteeList = cadreCommitteeService.updateSearchTdpCadreDetailsBySearchCriteriaForCadreCommitte(searchType,searchValue);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getUpdateSearchDetails() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	public String committeeCreationDetails(){
		return Action.SUCCESS;
	}
	
	public String getCommitteeCreationDetails()
	{
		try {
			
			int startIndex = 0;
			int maxIndex = 0;
			jObj = new JSONObject(getTask());
			Long committeeTypeId =jObj.getLong("committeeTypeId") ;
			List<Long> committeeLevlIdsList1 = new ArrayList<Long>();
			List<Long> designationsList1 = new ArrayList<Long>();
			Long locationLvlId =jObj.getLong("locationLvlId") ;
			List<Long> loctnLevlValues1 = new ArrayList<Long>();
			List<Long> committeeEnrollmntIds1 = new ArrayList<Long>();
			Long stateId =jObj.getLong("stateId") ;
			String searchType =jObj.getString("searchType") ;
			
			
			JSONArray committeeLevlIdsList = jObj.getJSONArray("committeeLevlIdsList");
			if(committeeLevlIdsList != null && committeeLevlIdsList.length()>0)
						{
							for (int i = 0; i < committeeLevlIdsList.length(); i++)
							{
								committeeLevlIdsList1.add(Long.valueOf(committeeLevlIdsList.get(i).toString().trim()));
							}
						} 
			JSONArray designationsList = jObj.getJSONArray("designationsList");
			if(designationsList != null && designationsList.length()>0)
						{
							for (int i = 0; i < designationsList.length(); i++)
							{
								designationsList1.add(Long.valueOf(designationsList.get(i).toString().trim()));
							}
						} 
			JSONArray loctnLevlValues = jObj.getJSONArray("loctnLevlValues");
			if(loctnLevlValues != null && loctnLevlValues.length()>0)
						{
							for (int i = 0; i < loctnLevlValues.length(); i++)
							{
								loctnLevlValues1.add(Long.valueOf(loctnLevlValues.get(i).toString().trim()));
							}
						} 
			JSONArray committeeEnrollmntIds = jObj.getJSONArray("committeeEnrollmntIds");
			if(committeeEnrollmntIds != null && committeeEnrollmntIds.length()>0)
						{
							for (int i = 0; i < committeeEnrollmntIds.length(); i++)
							{
								committeeEnrollmntIds1.add(Long.valueOf(committeeEnrollmntIds.get(i).toString().trim()));
							}
						} 
 			
			locations = cadreCommitteeService.getCommitteeCreationDetails(committeeTypeId,committeeLevlIdsList1,designationsList1,locationLvlId,loctnLevlValues1
					,committeeEnrollmntIds1,stateId,searchType);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getCommitteeCreationDetails() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	public String getTdpCommitteeMandalByConstituency(){
		try{
			HttpSession session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			jObj = new JSONObject(getTask());
			Long constituencyId = jObj.getLong("locationId");
			Long enrollmentId = jObj.getLong("enrollmentId");
			String committeeType = jObj.getString("committeeType");
				constituencies = tdpCadreReportService.getTdpCommitteeMandalByConstituency(constituencyId,enrollmentId,committeeType);
		}catch(Exception e){
			LOG.error("Exception occured in getTdpCommitteeMandalByConstituency() method ",e);
		}
		
		return Action.SUCCESS;
	}
	public String getTdpCommitteePanchayatWardByMandal(){
		try{
			jObj = new JSONObject(getTask());
			String mandalId = jObj.getString("mandalId");
			Long constituencyId = jObj.getLong("constituencyId");
			Long enrollmentId = jObj.getLong("enrollmentId");
			locations = cadreCommitteeService.getTdpCommitteePanchayatWardByMandal(mandalId,constituencyId,enrollmentId);
			
		}catch(Exception e){	
			LOG.error("Exception occured in getTdpCommitteePanchayatWardByMandal() method ",e);
		}
		return Action.SUCCESS;
	}
public String saveActivityLocationDetails(){
		
		try {
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			Long userId = regVO.getRegistrationID();
			/*try {
				 jObj = new JSONObject(getTask());
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			//if(jObj != null){
				//activityVO = new ActivityVO();
				//activityVO.setTable(jObj.getString("tableName"));
				/*ActivityVO locationVO = new ActivityVO();
				locationVO.setConductedDate(jObj.getString("conductedDateStr").trim());
				locationVO.setPlannedDate(jObj.getString("plannedDateStr").trim());
				locationVO.setLocationValue(jObj.getLong("locationValue"));				
				locationVO.setLocationLevel(jObj.getLong("activityLevelId"));
				locationVO.setDay(jObj.getLong("day"));
				activityVO.getActivityVoList().add(locationVO);*/
			//}
			 if(activityVO.getTypes() == null){
				 activityVO.setTypes("6");
			 }
			result1 = cadreCommitteeService.saveActivityLocationDetails(activityVO,userId);
			
		} catch (Exception e) {
			LOG.error("Exception occured in saveActivityDetails() At CadreCommitteeAction",e);
		}
		
		return Action.SUCCESS;
	}

public String updateCommitteeMemberDesignationByCadreId(){
	
	try {
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		Long userId = regVO.getRegistrationID();
		
		jObj = new JSONObject(getTask());
		Long tdpCadreId = jObj.getLong("tdpCadreId");
		
		finalStatus = cadreCommitteeService.updateCommitteeMemberDesignationByCadreId(tdpCadreId,userId);
		
	} catch (Exception e) {
		LOG.error("Exception occured in updateCommitteeMemberDesignationByCadreId() At CadreCommitteeAction",e);
	}
	
	return Action.SUCCESS;
}

	public String getPartyLeadersDeatails(){
		try {
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			Long userId = regVO.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			Long levelId = jObj.getLong("levelId");
			Long representativeTypeId = jObj.getLong("representativeTypeId");
			Long stateId = jObj.getLong("stateId");
			JSONArray locationIdArr = jObj.getJSONArray("locationIds");
			int firstIndex = jObj.getInt("firstIndex");
			int maxIndex = jObj.getInt("maxIndex");
			String reportType  = jObj.getString("reportType");
			List<Long> locationIdsList = new ArrayList<Long>(0);
			if(locationIdArr != null && locationIdArr.length()>0){
				for (int i = 0; i < locationIdArr.length(); i++) {
					locationIdsList.add(locationIdArr.get(i) != null ? Long.valueOf(locationIdArr.get(i).toString()):0L);
				}
			}
			
			JSONArray designationIdArr = jObj.getJSONArray("designationIds");
			List<Long> designationIdsList = new ArrayList<Long>(0);
			if(designationIdArr != null && designationIdArr.length()>0){
				for (int i = 0; i < designationIdArr.length(); i++) {
					designationIdsList.add(designationIdArr.get(i) != null ? Long.valueOf(designationIdArr.get(i).toString()):0L);
				}
			}
			
			JSONArray committeeLevelIdArr = jObj.getJSONArray("committeeLevelIdsArr");
			List<Long> committeeLevelIdsList = new ArrayList<Long>(0);
			if(committeeLevelIdArr != null && committeeLevelIdArr.length()>0){
				for (int i = 0; i < committeeLevelIdArr.length(); i++) {
					committeeLevelIdsList.add(committeeLevelIdArr.get(i) != null ? Long.valueOf(committeeLevelIdArr.get(i).toString()):0L);
				}
			}
			
			JSONArray committeeTypeIdArr = jObj.getJSONArray("committeeTypeIdsArr");
			List<Long> committeeTypeIdsList = new ArrayList<Long>(0);
			if(committeeTypeIdArr != null && committeeTypeIdArr.length()>0){
				for (int i = 0; i < committeeTypeIdArr.length(); i++) {
					committeeTypeIdsList.add(committeeTypeIdArr.get(i) != null ? Long.valueOf(committeeTypeIdArr.get(i).toString()):0L);
				}
			}
			
			JSONArray enrollmentIdArr = jObj.getJSONArray("enrollmentIdsArr");
			List<Long> enrollmentIdsList = new ArrayList<Long>(0);
			if(enrollmentIdArr != null && enrollmentIdArr.length()>0){
				for (int i = 0; i < enrollmentIdArr.length(); i++) {
					enrollmentIdsList.add(enrollmentIdArr.get(i) != null ? Long.valueOf(enrollmentIdArr.get(i).toString()):0L);
				}
			}
			
			
			commiteeMembersList = cadreCommitteeService.getPartyLeadersDeatails(regVO.getRegistrationID(),levelId,locationIdsList,representativeTypeId,designationIdsList,committeeLevelIdsList,enrollmentIdsList,committeeTypeIdsList,stateId,firstIndex,maxIndex,reportType);
			
		} catch (Exception e) {
			LOG.error("Exception occured in updateCommitteeMemberDesignationByCadreId() At CadreCommitteeAction",e);
		}
		
		return Action.SUCCESS;
		
	}
	
	public String getCommitteeLevelDetils(){
		try{
			jObj = new JSONObject(getTask());
			idNameVOList = cadreCommitteeService.getCommitteeLevelDetils();
		}catch(Exception e){
			LOG.error("Exception occured in getCommitteeLevelDetils() At CadreCommitteeAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getCommitteeTypeDetils(){
		try{
			jObj = new JSONObject(getTask());
			idNameVOList = cadreCommitteeService.getCommitteeTypeDetils();
		}catch(Exception e){
			LOG.error("Exception occured in getCommitteeTypeDetils() At CadreCommitteeAction",e);
		}
		return Action.SUCCESS;
	}
	public String getElectionBoothCommitteeDetails(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		boolean noaccess = false;
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("BOOTH_INCHARGE_COMMITTEE_ENTITLEMENT") || entitlements.contains("BOOTH_INCHARGE_COMMITTEE_ADMIN_ENTITLEMENT"))){
				noaccess = true ;
			}
		
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
			
		ageRangeList = cadreCommitteeService.getAgeRangeDetailsForCadre();
		genericVOList = cadreCommitteeService.getAllCasteDetailsForState();
		cadreRolesVOList = cadreCommitteeService.getBasicCadreCommitteesDetails();
		locations = cadreCommitteeService.getAllTdpCommitteeDesignations();
		List<BasicVO> accLoc = getUserAccessConstituencies();
		finalStatus = accLoc.get(0).getName();
		locationValue = accLoc.get(0).getId();
		if(panchayatId == null) //default values for prepopulate fields
		{
			panchayatId = "0";
			committeeTypeId = 0L;
			committeeId = 0L;
			result3 = "0";
		}
			return Action.SUCCESS;
		}else{
			return "error";
		}
		
	}
	public String saveElectionBoothCommitteeDetails(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			Long userId = regVO.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			JSONArray enrollmentYrIdArr = jObj.getJSONArray("enrollmentYrIds");
			List<Long> enrollmentYrIds = new ArrayList<Long>(0);
			if(enrollmentYrIdArr != null && enrollmentYrIdArr.length()>0){
				for (int i = 0; i < enrollmentYrIdArr.length(); i++) {
					enrollmentYrIds.add(Long.valueOf(enrollmentYrIdArr.get(i).toString().trim()));
				}
			}
			
			status = cadreCommitteeService.saveElectionBoothCommitteeDetails(userId,jObj.getLong("boothId"),jObj.getLong("tdpCadreId"),jObj.getLong("boothIncrgRoleId"),enrollmentYrIds,jObj.getString("isOtherRange"));
		}catch(Exception e){
			LOG.error("Exception occured in saveElectionBoothCommitteeDetails() At CadreCommitteeAction",e);
		}
		return Action.SUCCESS;
	}
	public String getBoothsForMandal(){
		try{
			jObj = new JSONObject(getTask());
			cadreCommitteeList = cadreCommitteeService.getBoothsForMandals(jObj.getLong("mandalId"),jObj.getLong("constituencyId"));
		}catch(Exception e){
			LOG.error("Exception occured in getBoothsForMandal() At CadreCommitteeAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getMultplConstituencesByDistctIds(){
		try{
			jObj = new JSONObject(getTask());
			
			JSONArray districtIdsArr = jObj.getJSONArray("districtIds");
			List<Long> districtIdsLst = new ArrayList<Long>(0);
			if(districtIdsArr != null && districtIdsArr.length()>0){
				for (int i = 0; i < districtIdsArr.length(); i++) {
					districtIdsLst.add(Long.valueOf(districtIdsArr.get(i).toString().trim()));
				}
			}
			idNameVOList = cadreCommitteeService.getMultplConstituencesByDistctIds(districtIdsLst);
		}catch(Exception e){
			LOG.error("Exception occured in getMultplConstituencesByDistctIds() At CadreCommitteeAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getMultpleMandalsByConstituencyIds(){
		try{
			jObj = new JSONObject(getTask());
			
			JSONArray constituencyIdsArr = jObj.getJSONArray("constituencyIds");
			List<Long> constituencyIdsLst = new ArrayList<Long>(0);
			if(constituencyIdsArr != null && constituencyIdsArr.length()>0){
				for (int i = 0; i < constituencyIdsArr.length(); i++) {
					constituencyIdsLst.add(Long.valueOf(constituencyIdsArr.get(i).toString().trim()));
				}
			}
			locations = cadreCommitteeService.getMultiMandalsByConstituencyLst(constituencyIdsLst);
		}catch(Exception e){
			LOG.error("Exception occured in getMultpleMandalsByConstituencyIds() At CadreCommitteeAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getMultplePanchayatWardByMandalIds(){
		try{
			jObj = new JSONObject(getTask());
			
			JSONArray constituencyIdsArr = jObj.getJSONArray("constituencyIds");
			List<Long> constituencyIdsLst = new ArrayList<Long>(0);
			if(constituencyIdsArr != null && constituencyIdsArr.length()>0){
				for (int i = 0; i < constituencyIdsArr.length(); i++) {
					constituencyIdsLst.add(Long.valueOf(constituencyIdsArr.get(i).toString().trim()));
				}
			}
			JSONArray mandalIdsArr = jObj.getJSONArray("mandalIds");
			List<Long> mandalIdsList = new ArrayList<Long>();
			if(mandalIdsArr != null && mandalIdsArr.length()>0){
				for (int i = 0; i < mandalIdsArr.length(); i++) {
					mandalIdsList.add(Long.valueOf(mandalIdsArr.get(i).toString().trim()));
					
				}
			}
			locations = cadreCommitteeService.getMultplePanchayatWardByMandalIdsLst(constituencyIdsLst,mandalIdsList);
		}catch(Exception e){
			LOG.error("Exception occured in getMultplePanchayatWardByMandalIds() At CadreCommitteeAction",e);
		}
		return Action.SUCCESS;
	}
	public String getCadreSearchDetailsForBoothsCommittee()
	{
		try {
			
			int startIndex = 0;
			int maxIndex = 0;
			jObj = new JSONObject(getTask());
			Long locationLevel = jObj.getLong("locationLevel");
			Long locationValue = jObj.getLong("locationValue");
			String searchName = jObj.getString("searchName");
			String mobileNo = jObj.getString("mobileNo");
			
			Long casteStateId = jObj.getLong("casteStateId");
			String casteCategory = jObj.getString("casteCategory");
			Long fromAge = jObj.getLong("fromAge");
			Long toAge = jObj.getLong("toAge");
			String houseNo = jObj.getString("houseNo");
			String memberShipCardNo = jObj.getString("memberShipCardNo");
			String trNumber = jObj.getString("trNumber");
			String voterCardNo = jObj.getString("voterCardNo");
			String gender = jObj.getString("gender");
			boolean isRemoved = jObj.getBoolean("removedStatus");
			Long enrollmentId = jObj.getLong("enrollmentId");
			
			if(jObj.getString("task").equalsIgnoreCase("tdpCadreSearch"))
			{	
				startIndex = jObj.getInt("startIndex");
				maxIndex = jObj.getInt("maxIndex");
				
			}
		    String searchType = "committeeSearch";
			
			cadreCommitteeVO = cadreCommitteeService.getCadreDetailsForBothsCommittee(locationLevel,locationValue, searchName,memberShipCardNo, 
							voterCardNo, trNumber, mobileNo,casteStateId,casteCategory,fromAge,toAge,houseNo,gender,startIndex,maxIndex,isRemoved,enrollmentId ,searchType);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getCadreSearchDetailsForCommittee() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	public String removeMbrFromCurentLocation(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			Long userId = regVO.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			status = cadreCommitteeService.removeMbrFromCurentLocation(userId,jObj.getLong("tdpCadreId"));
		}catch(Exception e){
			LOG.error("Exception occured in saveElectionBoothCommitteeDetails() At CadreCommitteeAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getTotalBoothsCountByConstituency(){
		try{
			jObj = new JSONObject(getTask());
			//RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			//Long userId = regVO.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			Long constituencyId = jObj.getLong("constituencyId");
			
			locationVO = cadreCommitteeService.getTotalBoothsCountByConstituenctIds(constituencyId);
		}catch(Exception e){
			LOG.error("Exception occured in saveElectionBoothCommitteeDetails() At CadreCommitteeAction",e);
		}
		return Action.SUCCESS;
	}
	public String getTdpCommitteeMunicipalityByWards(){
		try{
			jObj = new JSONObject(getTask());
			String mandalId = jObj.getString("mandalId");
			Long constituencyId = jObj.getLong("constituencyId");
			Long enrollmentId = jObj.getLong("enrollmentId");
			locations = cadreCommitteeService.getTdpCommitteeMunicipalityByWards(mandalId,constituencyId,enrollmentId);
			
		}catch(Exception e){	
			LOG.error("Exception occured in getTdpCommitteeMunicipalityByWards() method ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getCadreVoterBthSerilNo()
	{
		try {
			jObj = new JSONObject(getTask());
			Long locationValue = jObj.getLong("locationValue");
			String houseNo = jObj.getString("houseNo");
			String gender = jObj.getString("gender");
			cadreCommitteeVO = cadreCommitteeService.getCadreVoterBthSerilNo(locationValue,houseNo,gender);
		} catch (Exception e) {
			LOG.error("Exception occured in getCadreVoterBthSerilNo() At CadreCommitteeAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getSerialNoAvailbleCadreRangeWise()
	{
		try {
			jObj = new JSONObject(getTask());
			Long mandalId = jObj.getLong("mandalId");
			Long boothId = jObj.getLong("boothId");
			String range = jObj.getString("range");
			String gender = jObj.getString("gender");
			cadreCommitteeVO = cadreCommitteeService.getSerialNoAvailbleCadreRangeWise(mandalId,boothId,range,gender);
		} catch (Exception e) {
			LOG.error("Exception occured in getSerialNoAvailbleCadreRangeWise() At CadreCommitteeAction ",e);
		}
		return Action.SUCCESS;
	}
}