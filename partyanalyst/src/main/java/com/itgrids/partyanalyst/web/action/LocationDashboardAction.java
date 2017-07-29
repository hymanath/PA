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
import com.itgrids.partyanalyst.dto.AlertOverviewVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.BenefitCandidateVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.ConstituencyCadreVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.GrivenceStatusVO;
import com.itgrids.partyanalyst.dto.InsuranceStatusCountsVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
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
	private CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituencyTypesVO;
	private ConstituencyInfoVO constituencyDetails;
	private IConstituencyPageService constituencyPageService;
	private List<ConstituencyElectionResultsVO> constituencyElectionResultsVO;
	private List<LocationVotersVO> locationVotersVOList;
	private List<KeyValueVO> keyValueVOList;
	private List<ToursBasicVO> tourDesignationList;
	private List<BenefitCandidateVO> govtSchemeMemberBenefitList;
	private List<ConstituencyCadreVO> cadreDtlsList;
	private List<BasicVO> enrollmentYears;
	private CommitteeBasicVO committeeBasicVO;
	private List<AlertOverviewVO> alertVO;
	private InsuranceStatusCountsVO insuranceVO;
	private List<GrivenceStatusVO> grivenceVO;
	private List<BasicVO> activityStatusList;
	
	
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
	public InsuranceStatusCountsVO getInsuranceVO() {
		return insuranceVO;
	}
	public void setInsuranceVO(InsuranceStatusCountsVO insuranceVO) {
		this.insuranceVO = insuranceVO;
	}
	public List<AlertOverviewVO> getAlertVO() {
		return alertVO;
	}
	public void setAlertVO(List<AlertOverviewVO> alertVO) {
		this.alertVO = alertVO;
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
	public List<ConstituencyElectionResultsVO> getConstituencyElectionResultsVO() {
		return constituencyElectionResultsVO;
	}
	public void setConstituencyElectionResultsVO(
			List<ConstituencyElectionResultsVO> constituencyElectionResultsVO) {
		this.constituencyElectionResultsVO = constituencyElectionResultsVO;
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
	public CandidateDetailsForConstituencyTypesVO getCandidateDetailsForConstituencyTypesVO() {
		return candidateDetailsForConstituencyTypesVO;
	}
	public void setCandidateDetailsForConstituencyTypesVO(
			CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituencyTypesVO) {
		this.candidateDetailsForConstituencyTypesVO = candidateDetailsForConstituencyTypesVO;
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
	public String getCandidateAndPartyInfoForConstituency(){
		  try{
			  jObj=new JSONObject(getTask());
			  Long constituencyId = jObj.getLong("constituencyId");
			  candidateDetailsForConstituencyTypesVO = locationDashboardService.getCandidateAndPartyInfoForConstituency(constituencyId);
			  
		  }catch(Exception e){
			  LOG.error("Entered into getCandidateAndPartyInfoForConstituency method in locationDashboardAction....");
		  }
		  return Action.SUCCESS;
	  }
	 public String getDetailedElectionInformation(){
		  try{
			  jObj=new JSONObject(getTask());
			  Long constituencyId = jObj.getLong("constituencyId");
			  constituencyElectionResultsVO = constituencyPageService.getConstituencyElectionResults(constituencyId);
			  
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
			locationVotersVOList = locationDashboardService.getLocationWiseMeetingsCount(jObj.getString("locationtype"),jObj.getLong("constituencyId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseMeetingsCount", e);
		}
		 return Action.SUCCESS;
	 }
	 public String  getLocationWiseTourMembersComplainceDtls(){
		 try {
			 jObj = new JSONObject(getTask());
			 String locationType = jObj.getString("locationType");
			 Long locationValue = jObj.getLong("locationValue");
			 String fromDateStr = jObj.getString("fromDate");
			 String toDateStr = jObj.getString("toDate");
			 tourDesignationList = locationDashboardService.getLocationWiseTourMembersComplainceDtls(locationType,locationValue,fromDateStr,toDateStr);
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseTourMembersComplainceDtls in LocationDashboardAction class", e);
		}
		 return Action.SUCCESS;
	 }
	 public String  getGovtSchemeWiseBenefitMembersCount(){
		 try {
			 jObj = new JSONObject(getTask());
			 String locationType = jObj.getString("locationType");
			 Long locationValue = jObj.getLong("locationValue");
			 govtSchemeMemberBenefitList = locationDashboardService.getGovtSchemeWiseBenefitMembersCount(locationType,locationValue);
		} catch (Exception e) {
			LOG.error("Exception raised at getGovtSchemeWiseBenefitMembersCount in LocationDashboardAction class", e);
		}
		 return Action.SUCCESS;
	 }
	 public String  getMandalWiseBenefitMembersCount(){
		 try {
			 jObj = new JSONObject(getTask());
			 String locationType = jObj.getString("locationType");
			 Long locationValue = jObj.getLong("locationValue");
			 Long govtSchemeId = jObj.getLong("govtSchemeId");
			 govtSchemeMemberBenefitList = locationDashboardService.getMandalWiseBenefitMembersCount(locationType,locationValue,govtSchemeId);
		} catch (Exception e) {
			LOG.error("Exception raised at getMandalWiseBenefitMembersCount in LocationDashboardAction class", e);
		}
		 return Action.SUCCESS;
	 }
	 public String  getLocationTypeWiseCadreCount(){
		 try {
			jObj = new JSONObject(getTask());
			cadreDtlsList = locationDashboardService.getLocationTypeWiseCadreCount(jObj.getString("locationType"),jObj.getLong("locationValue"));
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationTypeWiseCadreCount in LocationDashboardAction class", e);
		}
		 return Action.SUCCESS;
	 }
	 public String  getAgeRangeGenerAndCasteGroupByCadreCount(){
		 try {
			jObj = new JSONObject(getTask());
			cadreDtlsList = locationDashboardService.getAgeRangeGenerAndCasteGroupByCadreCount(jObj.getString("locationType"),jObj.getLong("locationValue"),jObj.getLong("enrollmentYearId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getAgeRangeGenerAndCasteGroupByCadreCount in LocationDashboardAction class", e);
		}
		 return Action.SUCCESS;
	 }
	 
	 public String getNominatedPostStatusWiseCount(){
		 try{
			 jObj = new JSONObject(getTask());
			 JSONArray locationValues = jObj.getJSONArray("locationValues");  
				List<Long> locationValuesList = new ArrayList<Long>();
				if(locationValues != null && locationValues.length() > 0){
					for (int i = 0; i < locationValues.length(); i++){
						locationValuesList.add(Long.parseLong(locationValues.getString(i)));          
					}
				}
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
			 JSONArray locationValues = jObj.getJSONArray("locationValues");  
				List<Long> locationValuesList = new ArrayList<Long>();
				if(locationValues != null && locationValues.length() > 0){
					for (int i = 0; i < locationValues.length(); i++){
						locationValuesList.add(Long.parseLong(locationValues.getString(i)));          
					}
				}
					 
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
			 keyValueVOList = locationDashboardService.getPositionWiseMemberCount(jObj.getLong("constituencyId"),jObj.getString("fromDateStr"),jObj.getString("toDateStr"));
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
			alertVO = locationDashboardService.getLevelWiseMeetingStatusCounts(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationId"), jObj.getLong("locationValue"));
		}catch(Exception e){
			LOG.error("Exception raised at getLocationWiseMeetingCount() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
		 
	 }	
	
	public String getLocationWiseInsuranceStatusCounts(){
		try{
			jObj = new JSONObject(getTask());
			insuranceVO = locationDashboardService.getLocationWiseInsuranceStatusCounts(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationId"), jObj.getLong("locationValue"));
		}catch(Exception e){
			LOG.error("Exception raised at getLocationWiseInsuranceStatusCount() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
	
	public String getLocationWiseGrivanceTrustStatusCounts(){
		try{
			jObj = new JSONObject(getTask());
			grivenceVO = locationDashboardService.getGrivenceTrustStatusCounts(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationId"), jObj.getLong("locationValue"));
		}catch(Exception e){
			LOG.error("Exception raised at getLocationWiseInsuranceStatusCount() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
	
	public String getLocationWiseActivityStatusList(){
		try{
			jObj = new JSONObject(getTask());
			JSONArray locationValues = jObj.getJSONArray("locationValues");  
			List<Long> locationValuesList = new ArrayList<Long>();
			if(locationValues != null && locationValues.length() > 0){
				for (int i = 0; i < locationValues.length(); i++){
					locationValuesList.add(Long.parseLong(locationValues.getString(i)));          
				}
			}
			activityStatusList = locationDashboardService.getLocationWiseActivitysStatus(jObj.getString("fromDate"), jObj.getString("toDate"),jObj.getString("year"),locationValuesList, jObj.getLong("locationId"));
		}catch(Exception e){
			LOG.error("Exception raised at getActivityStatusList() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
	
}
