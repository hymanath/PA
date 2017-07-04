package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.core.api.service.ILocationDashboardService;
import com.itgrids.partyanalyst.dto.BenefitCandidateVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
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
			LOG.error("Exception raised at getCasteNAgeWiseVoterNCadreCounts", e);
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
	 //public String get
}
