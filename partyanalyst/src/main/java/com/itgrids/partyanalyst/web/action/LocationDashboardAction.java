package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.core.api.service.ILocationDashboardService;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.BenefitCandidateVO;
import com.itgrids.partyanalyst.dto.BoothInchargesVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.ConstituencyCadreVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ElectionInformationVO;
import com.itgrids.partyanalyst.dto.GrivenceStatusVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MeetingsVO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Teja Kollu
 *
 */
public class LocationDashboardAction extends ActionSupport implements ServletRequestAware{

	private static final Logger         		LOG = Logger.getLogger(LocationDashboardAction.class);
	private HttpServletRequest         			request;
	private HttpSession 						session;
	private JSONObject							jObj;
	private String 								task;
	private Long locationLevelId;
	private ILocationDashboardService locationDashboardService;
	private List<CandidateDetailsForConstituencyTypesVO> candidateDetailsForConstituencyTypesVO;
	private ConstituencyInfoVO constituencyDetails;
	private IConstituencyPageService constituencyPageService;
	private List<ConstituencyElectionResultsVO> constituencyElectionResultsVOList = new ArrayList<ConstituencyElectionResultsVO>();
	private List<LocationVotersVO> locationVotersVOList;
	private List<KeyValueVO> keyValueVOList;
	private List<ToursBasicVO> tourDesignationList;
	private List<BenefitCandidateVO> govtSchemeMemberBenefitList;
	private List<ConstituencyCadreVO> cadreDtlsList;
	private List<BasicVO> enrollmentYears;
	private CommitteeBasicVO committeeBasicVO;
	private List<MeetingsVO> meetingsVO;
	private GrivenceStatusVO insuranceVO;
	private List<GrivenceStatusVO> grivenceVO;
	private List<BasicVO> activityStatusList;
	private List<BasicVO> electionTypes;
	private List<LocationWiseBoothDetailsVO> locationVOList;
	private List<ElectionInformationVO> electioninformationList;
	private ICadreCommitteeService cadreCommitteeService;
	private List<BasicVO> publicationsData;
	private BoothInchargesVO boothInchargesVO;
	private List<BoothInchargesVO> boothInchargesVOList;

	public List<LocationWiseBoothDetailsVO> getLocationVOList() {
		return locationVOList;
	}
	public List<BasicVO> getPublicationsData() {
		return publicationsData;
	}
	public void setPublicationsData(List<BasicVO> publicationsData) {
		this.publicationsData = publicationsData;
	}
	public void setLocationVOList(List<LocationWiseBoothDetailsVO> locationVOList) {
		this.locationVOList = locationVOList;
	}
	public List<BasicVO> getElectionTypes() {
		return electionTypes;
	}
	public void setElectionTypes(List<BasicVO> electionTypes) {
		this.electionTypes = electionTypes;
	}
	public List<BasicVO> getActivityStatusList() {
		return activityStatusList;
	}
	public void setActivityStatusList(List<BasicVO> activityStatusList) {
		this.activityStatusList = activityStatusList;
	}
	public List<GrivenceStatusVO> getGrivenceVO() {
		return grivenceVO;
	}
	public void setGrivenceVO(List<GrivenceStatusVO> grivenceVO) {
		this.grivenceVO = grivenceVO;
	}
	public GrivenceStatusVO getInsuranceVO() {
		return insuranceVO;
	}
	public void setInsuranceVO(GrivenceStatusVO insuranceVO) {
		this.insuranceVO = insuranceVO;
	}
	public List<MeetingsVO> getMeetingsVO() {
		return meetingsVO;
	}
	public void setMeetingsVO(List<MeetingsVO> meetingsVO) {
		this.meetingsVO = meetingsVO;
	}
	public CommitteeBasicVO getCommitteeBasicVO() {
		return committeeBasicVO;
	}
	public void setCommitteeBasicVO(CommitteeBasicVO committeeBasicVO) {
		this.committeeBasicVO = committeeBasicVO;
	}
	public List<BasicVO> getEnrollmentYears() {
		return enrollmentYears;
	}
	public void setEnrollmentYears(List<BasicVO> enrollmentYears) {
		this.enrollmentYears = enrollmentYears;
	}
	public List<KeyValueVO> getKeyValueVOList() {
		return keyValueVOList;
	}
	public void setKeyValueVOList(List<KeyValueVO> keyValueVOList) {
		this.keyValueVOList = keyValueVOList;
	}
	public List<LocationVotersVO> getLocationVotersVOList() {
		return locationVotersVOList;
	}
	public void setLocationVotersVOList(List<LocationVotersVO> locationVotersVOList) {
		this.locationVotersVOList = locationVotersVOList;
	}
	
	public List<ConstituencyElectionResultsVO> getConstituencyElectionResultsVOList() {
		return constituencyElectionResultsVOList;
	}
	public void setConstituencyElectionResultsVOList(
			List<ConstituencyElectionResultsVO> constituencyElectionResultsVOList) {
		this.constituencyElectionResultsVOList = constituencyElectionResultsVOList;
	}
	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}
	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}
	public ConstituencyInfoVO getConstituencyDetails() {
		return constituencyDetails;
	}
	public void setConstituencyDetails(ConstituencyInfoVO constituencyDetails) {
		this.constituencyDetails = constituencyDetails;
	}
	public List<CandidateDetailsForConstituencyTypesVO> getCandidateDetailsForConstituencyTypesVO() {
		return candidateDetailsForConstituencyTypesVO;
	}
	public void setCandidateDetailsForConstituencyTypesVO(List<CandidateDetailsForConstituencyTypesVO> candidateDetailsForConstituencyTypesVO) {
		this.candidateDetailsForConstituencyTypesVO = candidateDetailsForConstituencyTypesVO;
	}
	
	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}
	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
	public ILocationDashboardService getLocationDashboardService() {
		return locationDashboardService;
	}
	public void setLocationDashboardService(
			ILocationDashboardService locationDashboardService) {
		this.locationDashboardService = locationDashboardService;
	}
	public Long getLocationLevelId() {
		return locationLevelId;
	}
	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
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
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}
	
	public String locationDashboard()
	{
		return Action.SUCCESS;
	}
	public String districtDashboard()
	{
		return Action.SUCCESS;
	} 
	public List<ToursBasicVO> getTourDesignationList() {
		return tourDesignationList;
	}
	public void setTourDesignationList(List<ToursBasicVO> tourDesignationList) {
		this.tourDesignationList = tourDesignationList;
	}
	public List<BenefitCandidateVO> getGovtSchemeMemberBenefitList() {
		return govtSchemeMemberBenefitList;
	}
	public void setGovtSchemeMemberBenefitList(
			List<BenefitCandidateVO> govtSchemeMemberBenefitList) {
		this.govtSchemeMemberBenefitList = govtSchemeMemberBenefitList;
	}
	public List<ConstituencyCadreVO> getCadreDtlsList() {
		return cadreDtlsList;
	}
	public void setCadreDtlsList(List<ConstituencyCadreVO> cadreDtlsList) {
		this.cadreDtlsList = cadreDtlsList;
	}
	
	public List<ElectionInformationVO> getElectioninformationList() {
		return electioninformationList;
	}
	public void setElectioninformationList(List<ElectionInformationVO> electioninformationList) {
		this.electioninformationList = electioninformationList;
	}
	
	public BoothInchargesVO getBoothInchargesVO() {
		return boothInchargesVO;
	}
	public void setBoothInchargesVO(BoothInchargesVO boothInchargesVO) {
		this.boothInchargesVO = boothInchargesVO;
	}
	
	public List<BoothInchargesVO> getBoothInchargesVOList() {
		return boothInchargesVOList;
	}

	public void setBoothInchargesVOList(List<BoothInchargesVO> boothInchargesVOList) {
		this.boothInchargesVOList = boothInchargesVOList;
	}
	
	public String getCandidateAndPartyInfoForConstituency(){
		  try{
			  jObj=new JSONObject(getTask());
			  Long locationTypeId = jObj.getLong("locationTypeId");
			  Long locationValue = jObj.getLong("locationValue");
			  candidateDetailsForConstituencyTypesVO = locationDashboardService.getCandidateAndPartyInfoForConstituency(locationValue,locationTypeId);
			  
		  }catch(Exception e){
			  LOG.error("Entered into getCandidateAndPartyInfoForConstituency method in locationDashboardAction....");
		  }
		  return Action.SUCCESS;
	  }
	 public String getDetailedElectionInformation(){
		  try{
			  jObj=new JSONObject(getTask());
			  Long constituencyId = jObj.getLong("constituencyId");
			  constituencyElectionResultsVOList = constituencyPageService.getConstituencyElectionResults(constituencyId);
			  
		  }catch(Exception e){
			  LOG.error("Entered into getDetailedElectionInformatiopn method in locationDashboardAction....");
		  }
		  return Action.SUCCESS;
	  }
	 
	 public String getVotersAndcadreAgeWiseCount(){
		 try {
			jObj = new JSONObject(getTask());
			locationVotersVOList = locationDashboardService.getVotersAndcadreAgeWiseCount(jObj.getLong("constituencyId"),jObj.getLong("publicationDateId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getVotersAndcadreAgeWiseCount", e);
		}
		 return Action.SUCCESS;
	 }
	 
	 public String getVotersAndCadreCasteWiseCount(){
		 try {
			jObj = new JSONObject(getTask());
			locationVotersVOList = locationDashboardService.getVotersAndCadreCasteWiseCount(jObj.getString("type"),jObj.getLong("constituencyId"),jObj.getLong("publicationDateId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getVotersAndCadreCasteWiseCount", e);
		}
		 return Action.SUCCESS;
	 }
	 
	 public String getCasteGroupNAgeWiseVoterNCadreCounts(){
		 try {
			jObj = new JSONObject(getTask());
			locationVotersVOList = locationDashboardService.getCasteGroupNAgeWiseVoterNCadreCounts(jObj.getLong("constituencyId"),jObj.getLong("publicationDateId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getCasteGroupNAgeWiseVoterNCadreCounts", e);
		}
		return Action.SUCCESS;
	 }
	 
	 public String getCasteNAgeWiseVoterNCadreCounts(){
		 try {
			 jObj = new JSONObject(getTask());
			 locationVotersVOList = locationDashboardService.getCasteNAgeWiseVoterNCadreCounts(jObj.getLong("constituencyId"),jObj.getLong("publicationDateId"),jObj.getLong("casteGroupId"),jObj.getLong("casteId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getCasteNAgeWiseVoterNCadreCounts", e);
		}
		 return Action.SUCCESS;
	 }
	 
	 public String getEnrollmentYearWiseCadres(){
		 try {
			 keyValueVOList = locationDashboardService.getEnrollmentYearWiseCadres();
		} catch (Exception e) {
			LOG.error("Exception raised at getEnrollmentYearWiseCadres", e);
		}
		 return Action.SUCCESS;
	 }
	 
	 public String getEnrollmentYearAgeGroupWiseCadres(){
		 try {
			 jObj = new JSONObject(getTask());
			 locationVotersVOList = locationDashboardService.getEnrollmentYearAgeGroupWiseCadres(jObj.getLong("constituencyId"),jObj.getLong("enrollmentYearId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getEnrollmentYearAgeGroupWiseCadres", e);
		}
		 return Action.SUCCESS;
	 }
	 
	 public String  getLocationWiseMeetingsCount(){
		 try {
			jObj = new JSONObject(getTask());
			List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValues"));
			locationVotersVOList = locationDashboardService.getLocationWiseMeetingsCount(jObj.getLong("locationTypeId"),locationValues);
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseMeetingsCount", e);
		}
		 return Action.SUCCESS;
	 }
	 public String  getLocationWiseTourMembersComplainceDtls(){
		 try {
			 jObj = new JSONObject(getTask());
			 Long locationTypeId = jObj.getLong("locationTypeId");
			 String fromDateStr = jObj.getString("fromDate");
			 String toDateStr = jObj.getString("toDate");
			 String year = jObj.getString("year");
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  
			 tourDesignationList = locationDashboardService.getLocationWiseTourMembersComplainceDtls(locationTypeId,locationValuesList,fromDateStr,toDateStr,year);
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseTourMembersComplainceDtls in LocationDashboardAction class", e);
		}
		 return Action.SUCCESS;
	 }
	 public String  getGovtSchemeWiseBenefitMembersCount(){
		 try {
			 jObj = new JSONObject(getTask());
			 Long locationTypeId = jObj.getLong("locationTypeId");
			 Long locationValue = jObj.getLong("locationValue");
			 govtSchemeMemberBenefitList = locationDashboardService.getGovtSchemeWiseBenefitMembersCount(locationTypeId,locationValue);
		} catch (Exception e) {
			LOG.error("Exception raised at getGovtSchemeWiseBenefitMembersCount in LocationDashboardAction class", e);
		}
		 return Action.SUCCESS;
	 }
	 public String  getMandalWiseBenefitMembersCount(){
		 try {
			 jObj = new JSONObject(getTask());
			 Long locationTypeId = jObj.getLong("locationTypeId");
			 Long locationValue = jObj.getLong("locationValue");
			 Long govtSchemeId = jObj.getLong("govtSchemeId");
			 govtSchemeMemberBenefitList = locationDashboardService.getMandalWiseBenefitMembersCount(locationTypeId,locationValue,govtSchemeId);
		} catch (Exception e) {
			LOG.error("Exception raised at getMandalWiseBenefitMembersCount in LocationDashboardAction class", e);
		}
		 return Action.SUCCESS;
	 }
	 public String  getLocationTypeWiseCadreCount(){
		 try {
			jObj = new JSONObject(getTask());
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  

			cadreDtlsList = locationDashboardService.getLocationTypeWiseCadreCount(jObj.getLong("locationTypeId"),locationValuesList,jObj.getString("year"));
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationTypeWiseCadreCount in LocationDashboardAction class", e);
		}
		 return Action.SUCCESS;
	 }
	 public String  getAgeRangeGenerAndCasteGroupByCadreCount(){
		 try {
			jObj = new JSONObject(getTask());
			cadreDtlsList = locationDashboardService.getAgeRangeGenerAndCasteGroupByCadreCount(jObj.getLong("locationTypeId"),jObj.getLong("locationValue"),jObj.getLong("enrollmentYearId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getAgeRangeGenerAndCasteGroupByCadreCount in LocationDashboardAction class", e);
		}
		 return Action.SUCCESS;
	 }
	 
	 public String getNominatedPostStatusWiseCount(){
		 try{
			 jObj = new JSONObject(getTask());
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValues"));  
			 keyValueVOList = locationDashboardService.getNominatedPostStatusWiseCount(
					 jObj.getLong("locationTypeId"),locationValuesList,jObj.getString("fromDateStr"),jObj.getString("toDateStr"),jObj.getString("year"));
			 }catch(Exception e){
			 LOG.error("Exception raised at getNominatedPostStatusWiseCount() of LocationDashboardAction{}", e);
		 }
		 return Action.SUCCESS;
	 }
	 public String getNominatedPostApplicationStatusWiseCount(){
		 try{
			 jObj = new JSONObject(getTask());
				List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValues"));  
 
			 keyValueVOList = locationDashboardService.getNominatedPostApplicationStatusWiseCount(
					 jObj.getLong("locationTypeId"),locationValuesList,jObj.getString("fromDateStr"),jObj.getString("toDateStr"),jObj.getString("year"));
		 }catch(Exception e){
			 LOG.error("Exception raised at getNominatedPostApplicationStatusWiseCount() of LocationDashboardAction{}", e);
		 }
		 return Action.SUCCESS;
	 }
	 public String getPositionWiseMemberCount(){
		 try{
			 jObj = new JSONObject(getTask());
				List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  
			 keyValueVOList = locationDashboardService.getPositionWiseMemberCount(locationValues,jObj.getString("fromDateStr"),jObj.getString("toDateStr"),jObj.getLong("locationTypeId"),jObj.getString("year"));
		 }catch(Exception e){
			 LOG.error("Exception raised at getPositionWiseMemberCount() of LocationDashboardAction{}", e);
		 }
		 return Action.SUCCESS;
	 }
	 
	 public String getEnrollmentYearsList(){
		 try{
			 jObj = new JSONObject(getTask());
			 enrollmentYears = locationDashboardService.getEnrollmentIds();
		 }catch(Exception e){
			 LOG.error("Exception raised at getEnrollemtYears() of LocationDashboardAction{}", e);
		 }
		 return Action.SUCCESS;
	 }
	 
	 public String getElectionTypesList(){
		 try{
			 jObj = new JSONObject(getTask());
			 electionTypes = locationDashboardService.getElectionTypes();
		 }catch(Exception e){
			 LOG.error("Exception raised at getElectionTypes() of LocationDashboardAction{}", e);
		 }
		 return Action.SUCCESS;
	 }
	 
	 public String getPublicationsList(){
		 try{
			 jObj = new JSONObject(getTask());
			 publicationsData = locationDashboardService.getPublications();
		 }catch(Exception e){
			 LOG.error("Exception raised at getElectionTypes() of LocationDashboardAction{}", e);
		 }
		 return Action.SUCCESS;
	 } 
	 
	 public String getLocationWiseCommitteesCount(){
		 try{
			 jObj = new JSONObject(getTask());
			 committeeBasicVO = locationDashboardService.getLocationWiseCommitteesCount(jObj.getString("locationType"), jObj.getLong("locationId"), jObj.getLong("enrollmentId"));
		 }catch(Exception e){
			 LOG.error("Exception raised at getLocationWiseCommitteesCount() of LocationDashboardAction{}",e);
		 }
		return Action.SUCCESS;
	 }
	 
	public String getLevelWiseMeetingStatusCounts(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  
			
			meetingsVO = locationDashboardService.getLevelWiseMeetingStatusCounts(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationTypeId"),locationValues,jObj.getString("year"));
		}catch(Exception e){
			LOG.error("Exception raised at getLocationWiseMeetingCount() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
		 
	 }	
	
	public String getLocationWiseInsuranceStatusCounts(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  
			insuranceVO = locationDashboardService.getLocationWiseInsuranceStatusCounts(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationTypeId"),locationValues,jObj.getString("year"));
		}catch(Exception e){
			LOG.error("Exception raised at getLocationWiseInsuranceStatusCount() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
	
	public String getLocationWiseGrivanceTrustStatusCounts(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  

			grivenceVO = locationDashboardService.getGrivenceTrustStatusCounts(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationTypeId"),locationValuesList,jObj.getString("year"));
		}catch(Exception e){
			LOG.error("Exception raised at getLocationWiseInsuranceStatusCount() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
	
	public String getLocationWiseActivityStatusList(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValues")); 
			
			activityStatusList = locationDashboardService.getLocationWiseActivitysStatus(jObj.getString("fromDate"), jObj.getString("toDate"),jObj.getString("year"),locationValuesList, jObj.getLong("locationId"));
		}catch(Exception e){
			LOG.error("Exception raised at getActivityStatusList() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAllDistrictsForLoationDashBoard(){
		try{
			jObj = new JSONObject(getTask());
			locationVOList= locationDashboardService.getAllDistricts(jObj.getLong("stateId"));
		}catch(Exception e){
			LOG.error("Exception Occured In getAllDistricts method "+e);			
		}
		return Action.SUCCESS;
	}
	public String getConstituenciesByDistrictForLoationDashBoard(){
		try{
			jObj = new JSONObject(getTask());
			locationVOList= locationDashboardService.getAllConstituenciesByDistrict(jObj.getLong("districtId"));
		}catch(Exception e){
			LOG.error("Exception Occured In getAllDistricts method "+e);			
		}
		return Action.SUCCESS;
	}
	public String getMandalsByConstituencyForLoationDashBoard(){
		try{
			jObj = new JSONObject(getTask());
			locationVOList = cadreCommitteeService.getMandalsByConstituency(jObj.getLong("constituencyId"));
			locationVOList.remove(0);
		}catch(Exception e){
			LOG.error("Exception occured in getMandalsByConstituency ",e);
		}
	   return Action.SUCCESS;
	}
	public String getPanchayatWardByMandalForLoationDashBoard(){
		try{
			jObj = new JSONObject(getTask());
			String mandalId = jObj.getString("mandalId");
			Long constituencyId = jObj.getLong("constituencyId");
			locationVOList=cadreCommitteeService.getPanchayatWardByMandalId(mandalId,constituencyId);
			locationVOList.remove(0);
		}catch(Exception e){	
			LOG.error("Exception occured in getPanchayatWardByMandal() method ",e);
		}
		return Action.SUCCESS;
	}
	public String getElectionInformationLocationWise(){
		
		try{
			jObj = new JSONObject(getTask());
			List<Long> electionScopeIds = convertJsonStringList(jObj.getJSONArray("electionScopeIds"));
					
			electioninformationList=locationDashboardService.getElectionInformationLocationWise(jObj.getString("fromDate"),jObj.getString("toDate"),
					jObj.getLong("locationId"),jObj.getLong("locationValue"),electionScopeIds);
		
//getElectionInformationLocationWise(String fromDate, String toDate, Long locationTypeId,Long locationValue, List<Long> electionScopeIds)			
			
		}catch(Exception e){
			LOG.error("Exception occured in getElectionInformationLocationWise() method ",e);
		}
		return Action.SUCCESS;
		
	}
	
	public List<Long> convertJsonStringList(JSONArray jsonArray){
		List<Long> idsList=  new ArrayList<Long>();
		if(jsonArray!=null && jsonArray.length()>0){
			for(int i =0; i< jsonArray.length();i++){
				idsList.add(Long.parseLong(jsonArray.getString(i)));        
			}
		}
		return idsList;
		
	}
	
	public String getBoothAssignInchargeCount(){
		
		try{
			jObj = new JSONObject(getTask());
			List<Long> committeeEnrollmentYearsIdsLst = convertJsonStringList(jObj.getJSONArray("committeeEnrollmentYearsIdsLst"));
					
			boothInchargesVO =locationDashboardService.getBoothAssignInchargeCount(jObj.getString("fromDate"),jObj.getString("toDate"), jObj.getLong("locationId"),jObj.getLong("locationValue"),
					committeeEnrollmentYearsIdsLst);
		}catch(Exception e){
			LOG.error("Exception occured in getBoothAssignInchargeCount() method ",e);

		}
		return Action.SUCCESS;
	}
	
	public String getElectionBoothCommitteeInchargesCount(){
		
		try{
			jObj = new JSONObject(getTask());
			List<Long> boothCommitteeEnrollmentYearsIdsLst = convertJsonStringList(jObj.getJSONArray("boothcommitteeEnrollmentYearsIdsLst"));
					
			boothInchargesVOList=locationDashboardService.getBoothCommitteeInchargesCount( jObj.getLong("locationId"),jObj.getLong("locationValue"),
					boothCommitteeEnrollmentYearsIdsLst,jObj.getString("fromDate"),jObj.getString("toDate"));
		}catch(Exception e){
			LOG.error("Exception occured in getBoothAssignInchargeCount() method ",e);

		}
		return Action.SUCCESS;
	}
	
	public String getAllParlimentsForLocationDashBoard(){
		
		try{	
			locationVOList = locationDashboardService.getAllParlimentsForLocationDashBoard();
		}catch(Exception e){
			LOG.error("Exception occured in getAllParlimentsForLocationDashBoard() method ",e);

		}
		return Action.SUCCESS;
	}
	public String getAllConstituencyByParlimentId(){
		
		try{
			jObj = new JSONObject(getTask());
			locationVOList = locationDashboardService.getAllConstituencyByParlimentId(jObj.getLong("parlimentId"));
		}catch(Exception e){
			LOG.error("Exception occured in getAllParlimentsForLocationDashBoard() method ",e);

		}
		return Action.SUCCESS;
	}
}
