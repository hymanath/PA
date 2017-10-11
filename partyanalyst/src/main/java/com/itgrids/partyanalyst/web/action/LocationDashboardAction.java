package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.core.api.service.IAlertLocationDashboardService;
import com.itgrids.core.api.service.ILocationDashboardService;
import com.itgrids.core.api.service.ILocationWiseCasteInfoService;
import com.itgrids.core.api.service.ILocationWiseElectionInformationDetalsService;
import com.itgrids.core.api.service.IMeetingLocationDashboardService;
import com.itgrids.core.api.service.INominatedPostLocationDashboardService;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
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
import com.itgrids.partyanalyst.dto.LocationAlertVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MeetingsVO;
import com.itgrids.partyanalyst.dto.NominatedPostCandidateDtlsVO;
import com.itgrids.partyanalyst.dto.NominatedPostDashboardVO;
import com.itgrids.partyanalyst.dto.NominatedPostDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Teja Kollu
 * @param <ILocationWiseElectionInformationDetalsService>
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
	private List<NominatedPostDetailsVO> nominatedPostDetailsVOList = new ArrayList<NominatedPostDetailsVO>();
    private NominatedPostDashboardVO nominatedPostDashboardVO;
    private NominatedPostDetailsVO nominatedPostDetailsVO;
	private LocationVO locationVo;
	private ILocationWiseCasteInfoService locationWiseCasteInfoService;
	private LocationAlertVO locationAlertVO;
	private IAlertLocationDashboardService alertLocationDashboardService;
	private String successMsg;
	private IMeetingLocationDashboardService meetingLocationDashboardService;
	private CandidateDetailsForConstituencyTypesVO typeVo;
	private INominatedPostLocationDashboardService nominatedPostLocationDashboardService;
	private List<NominatedPostCandidateDtlsVO> nominatedPostCandList;
	private KeyValueVO keyValueVO;
	private List<ElectionInformationVO> informationVo;
	private ILocationWiseElectionInformationDetalsService locationWiseElectionInformationDetalsService;
	private List<LocationAlertVO> locationAlertVOList;
	private List<AlertCoreDashBoardVO> alertVOList;
	
	public List<LocationAlertVO> getLocationAlertVOList() {
		return locationAlertVOList;
	}
	public void setLocationAlertVOList(List<LocationAlertVO> locationAlertVOList) {
		this.locationAlertVOList = locationAlertVOList;
	}
	public KeyValueVO getKeyValueVO() {
		return keyValueVO;
	}
	public ILocationWiseElectionInformationDetalsService getLocationWiseElectionInformationDetalsService() {
		return locationWiseElectionInformationDetalsService;
	}
	public void setLocationWiseElectionInformationDetalsService(ILocationWiseElectionInformationDetalsService locationWiseElectionInformationDetalsService) {
		this.locationWiseElectionInformationDetalsService = locationWiseElectionInformationDetalsService;
	}
	public void setKeyValueVO(KeyValueVO keyValueVO) {
		this.keyValueVO = keyValueVO;
	}
	public List<NominatedPostCandidateDtlsVO> getNominatedPostCandList() {
		return nominatedPostCandList;
	}
	public void setNominatedPostCandList(
			List<NominatedPostCandidateDtlsVO> nominatedPostCandList) {
		this.nominatedPostCandList = nominatedPostCandList;
	}
	public INominatedPostLocationDashboardService getNominatedPostLocationDashboardService() {
		return nominatedPostLocationDashboardService;
	}
	public void setNominatedPostLocationDashboardService(
			INominatedPostLocationDashboardService nominatedPostLocationDashboardService) {
		this.nominatedPostLocationDashboardService = nominatedPostLocationDashboardService;
	}
	public IMeetingLocationDashboardService getMeetingLocationDashboardService() {
		return meetingLocationDashboardService;
	}
	public void setMeetingLocationDashboardService(
			IMeetingLocationDashboardService meetingLocationDashboardService) {
		this.meetingLocationDashboardService = meetingLocationDashboardService;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public LocationAlertVO getLocationAlertVO() {
		return locationAlertVO;
	}
	public void setLocationAlertVO(LocationAlertVO locationAlertVO) {
		this.locationAlertVO = locationAlertVO;
	}
	public IAlertLocationDashboardService getAlertLocationDashboardService() {
		return alertLocationDashboardService;
	}
	public void setAlertLocationDashboardService(
			IAlertLocationDashboardService alertLocationDashboardService) {
		this.alertLocationDashboardService = alertLocationDashboardService;
	}
	public ILocationWiseCasteInfoService getLocationWiseCasteInfoService() {
		return locationWiseCasteInfoService;
	}
	public void setLocationWiseCasteInfoService(
			ILocationWiseCasteInfoService locationWiseCasteInfoService) {
		this.locationWiseCasteInfoService = locationWiseCasteInfoService;
	}
	public NominatedPostDetailsVO getNominatedPostDetailsVO() {
		return nominatedPostDetailsVO;
	}
	public void setNominatedPostDetailsVO(
			NominatedPostDetailsVO nominatedPostDetailsVO) {
		this.nominatedPostDetailsVO = nominatedPostDetailsVO;
	}
	public List<NominatedPostDetailsVO> getNominatedPostDetailsVOList() {
		return nominatedPostDetailsVOList;
	}
	public void setNominatedPostDetailsVOList(
			List<NominatedPostDetailsVO> nominatedPostDetailsVOList) {
		this.nominatedPostDetailsVOList = nominatedPostDetailsVOList;
	}

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
		
	public NominatedPostDashboardVO getNominatedPostDashboardVO() {
		return nominatedPostDashboardVO;
	}
	public void setNominatedPostDashboardVO(
			NominatedPostDashboardVO nominatedPostDashboardVO) {
		this.nominatedPostDashboardVO = nominatedPostDashboardVO;
	}
	
	public LocationVO getLocationVo() {
		return locationVo;
	}
	public void setLocationVo(LocationVO locationVo) {
		this.locationVo = locationVo;
	}
	
	public CandidateDetailsForConstituencyTypesVO getTypeVo() {
		return typeVo;
	}
	public void setTypeVo(CandidateDetailsForConstituencyTypesVO typeVo) {
		this.typeVo = typeVo;
	}
	
	public List<ElectionInformationVO> getInformationVo() {
		return informationVo;
	}
	public void setInformationVo(List<ElectionInformationVO> informationVo) {
		this.informationVo = informationVo;
	}
	public List<AlertCoreDashBoardVO> getAlertVOList() {
		return alertVOList;
	}
	public void setAlertVOList(List<AlertCoreDashBoardVO> alertVOList) {
		this.alertVOList = alertVOList;
	}
	public String getCandidateAndPartyInfoForConstituency(){
		  try{
			  jObj=new JSONObject(getTask());
			  Long locationTypeId = jObj.getLong("locationTypeId");
			  Long locationValue = jObj.getLong("locationValue");
			  List<Long> representativeTypeIds = convertJsonStringList(jObj.getJSONArray("representativeTypeIds"));  
			  List<Long> roleIds = convertJsonStringList(jObj.getJSONArray("roleIds"));  
			  List<Long> committeeIds = convertJsonStringList(jObj.getJSONArray("committeeIds"));  
			  List<Long> enrollmentYears = convertJsonStringList(jObj.getJSONArray("enrollmentYears")); 
			  Long basicCommoteeId=jObj.getLong("basicCommoteeId");
			  Long enrollmentId=jObj.getLong("enrollmentId");

			  candidateDetailsForConstituencyTypesVO = locationDashboardService.getCandidateAndPartyInfoForConstituency(locationValue,locationTypeId,representativeTypeIds,roleIds,committeeIds,enrollmentYears,basicCommoteeId,enrollmentId);
			  
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
			locationVotersVOList = locationWiseCasteInfoService.getVotersAndcadreAgeWiseCount(jObj.getLong("locationTypeId"),jObj.getLong("locationValue"),jObj.getLong("publicationDateId"),
					jObj.getLong("enrollmentYearId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getVotersAndcadreAgeWiseCount", e);
		}
		 return Action.SUCCESS;
	 }
	 public String getVotersAndCadreCasteWiseCount(){
		 try {
			jObj = new JSONObject(getTask());
			
			locationVotersVOList = locationWiseCasteInfoService.getVotersAndCadreCasteWiseCount(jObj.getLong("locationTypeId"),jObj.getLong("locationValue"),
					jObj.getLong("publicationDateId"),jObj.getLong("enrollmentYearId"),jObj.getLong("casteGroupId"),jObj.getString("assendingType"));
		} catch (Exception e) {
			LOG.error("Exception raised at getVotersAndCadreCasteWiseCount", e);
		}
		 return Action.SUCCESS;
	 }
	 
	 public String getCasteNAgeWiseVoterNCadreCounts(){
		 try {
			 jObj = new JSONObject(getTask());
			 locationVotersVOList = locationWiseCasteInfoService.getCasteNAgeWiseVoterNCadreCounts(jObj.getLong("locationTypeId"),jObj.getLong("locationValue"),jObj.getLong("publicationDateId"),jObj.getLong("casteGroupId"),jObj.getLong("casteId")
					 ,jObj.getLong("enrollmentYearId"));
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
			 locationVotersVOList = locationDashboardService.getEnrollmentYearAgeGroupWiseCadres(jObj.getLong("locationTypeId"),jObj.getLong("locationValue"),jObj.getLong("enrollmentYearId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getEnrollmentYearAgeGroupWiseCadres", e);
		}
		 return Action.SUCCESS;
	 }
	 
	 public String  getLocationWiseMeetingsCount(){
		 try {
			jObj = new JSONObject(getTask());
			List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValues"));
			String fromDateStr =jObj.getString("fromDate");
			String toDateStr =jObj.getString("toDate");
			//locationVotersVOList = locationDashboardService.getLocationWiseMeetingsCount(jObj.getLong("locationTypeId"),locationValues,fromDateStr,toDateStr);
			locationVotersVOList = meetingLocationDashboardService.getLocationWiseMeetingsCount(jObj.getLong("locationTypeId"),locationValues,fromDateStr,toDateStr);
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
			 Long stateId = jObj.getLong("stateId");
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  
			 tourDesignationList = locationDashboardService.getLocationWiseTourMembersComplainceDtls(locationTypeId,locationValuesList,fromDateStr,toDateStr,year,stateId);
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
			 Long publicationDateId = jObj.getLong("publicationDateId");
			 govtSchemeMemberBenefitList = locationDashboardService.getGovtSchemeWiseBenefitMembersCount(locationTypeId,locationValue,publicationDateId);
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
			 Long publicationDateId = jObj.getLong("publicationDateId");
			 govtSchemeMemberBenefitList = locationDashboardService.getMandalWiseBenefitMembersCount(locationTypeId,locationValue,govtSchemeId,publicationDateId);
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
			List<Long> partyIds = convertJsonStringList(jObj.getJSONArray("partyIds"));
			List<Long> electionYrs = convertJsonStringList(jObj.getJSONArray("electionYrs"));
			//String electionType =jObj.getString("electionSubType");
			List<String> subTypes=  new ArrayList<String>();
			JSONArray jsonArray = jObj.getJSONArray("electionSubTypeArr");
			if(jsonArray!=null && jsonArray.length()>0){
				for(int i =0; i< jsonArray.length();i++){
					subTypes.add(jsonArray.getString(i).toString());        
				}
			}
			electioninformationList=locationDashboardService.getElectionInformationLocationWise(jObj.getString("fromDate"),jObj.getString("toDate"),
					jObj.getLong("locationId"),jObj.getLong("locationValue"),electionScopeIds,partyIds,electionYrs,subTypes);
		
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
	
	 public String getVotersCastGroupWiseCount(){
		 try {
			 jObj = new JSONObject(getTask());
			 locationVotersVOList = locationWiseCasteInfoService.getVotersCastGroupWiseCount(jObj.getLong("locationTypeId"),jObj.getLong("locationValue"),jObj.getLong("publicationDateId"),jObj.getLong("enrollmentyearId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getEnrollmentYearAgeGroupWiseCadres", e);
		}
		 return Action.SUCCESS;
	 }
	
	public String getLocationWiseNominatedPostAnalysisDetails(){
	    try{
	      jObj = new JSONObject(getTask());
	      
	      Long boardLevelId = jObj.getLong("boardLevelId");
	      List<Long> locationValuesList = new ArrayList<Long>(0); 
	      JSONArray levelValues = jObj.getJSONArray("levelValues");
	      
	        if(levelValues != null && levelValues.length()> 0){
	          for(int i = 0;i<levelValues.length();i++){
	            locationValuesList.add(new Long(levelValues.getInt(i)));
	          }
	        }   
	        List<Long> statusIds = new ArrayList<Long>(0); 
		      JSONArray statusIdsArr = jObj.getJSONArray("statusIds");
		      
		        if(statusIdsArr != null && statusIdsArr.length()> 0){
		          for(int i = 0;i<statusIdsArr.length();i++){
		        	  statusIds.add(new Long(statusIdsArr.getInt(i)));
		          }
		        }
	      Long levelId = jObj.getLong("levelId");
	      String type = jObj.getString("dataType");
		  String fromDateStr = jObj.getString("fromDateStr");
		  String year = jObj.getString("year");
		  String toDateStr = jObj.getString("toDateStr");
	      
	      nominatedPostDetailsVOList = locationDashboardService.getLocationWiseNominatedPostAnalysisDetails(locationValuesList,boardLevelId,levelId,type,statusIds,fromDateStr,toDateStr,year);  
	      
	    }catch(Exception e){
	      LOG.error("Entered into getAllNominatedStatusData method of LocationDashboardAction Action",e);
	    }
	    return Action.SUCCESS;
	  }
	
	public String getAllNominatedStatusListLevelWiseData(){
		try{
			jObj = new JSONObject(getTask());			
			Long boardLevelId = jObj.getLong("boardLevelId");
			 String fromDateStr = jObj.getString("fromDateStr");
			 String year = jObj.getString("year");
			 String toDateStr = jObj.getString("toDateStr");
			
			List<Long> locationValuesList = new ArrayList<Long>(0); 
			JSONArray levelValues = jObj.getJSONArray("levelValues");			
		    if(levelValues != null && levelValues.length()> 0){
		    	for(int i = 0;i<levelValues.length();i++){
		    		locationValuesList.add(new Long(levelValues.getInt(i)));
		    	}
		    }			
			Long levelId = jObj.getLong("levelId");
			nominatedPostDashboardVO = locationDashboardService.getAllNominatedStatusListLevelWiseData(boardLevelId,locationValuesList,levelId,fromDateStr,toDateStr,year);  
			
		}catch(Exception e){
			LOG.error("Entered into getAllNominatedStatusListLevelWiseData method of NominatedPostProfileAction Action",e);
		}
 		return Action.SUCCESS;
	}


	public String getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails(){
	    try{
	      jObj = new JSONObject(getTask());
	      List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValues"));
	      List<Long> statusIdsList = convertJsonStringList(jObj.getJSONArray("statusIds"));
	        
	      Long levelId = jObj.getLong("levelId");
	      String type =jObj.getString("type");
	      String fromDateStr = jObj.getString("fromDateStr");
		  String year = jObj.getString("year");
		  String toDateStr = jObj.getString("toDateStr");
	      nominatedPostDetailsVOList = locationDashboardService.getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails(locationValuesList,levelId,statusIdsList,type,fromDateStr,toDateStr,year);  
	      
	    }catch(Exception e){
	      LOG.error("Entered into getLocationWiseNominatedPostCandidateDetails method of NominatedPostProfileAction Action",e);
	    }
	    return Action.SUCCESS;
	  }
	public String getAreaWiseDashboardCandidatesCountView(){
	    try{
	      jObj = new JSONObject(getTask());
	      
	      List<Long> locationValuesList =  convertJsonStringList(jObj.getJSONArray("levelValues"));
	        List<Long> statusIds =convertJsonStringList(jObj.getJSONArray("statusIds"));
		      
	      Long levelId = jObj.getLong("levelId");
	      String fromDateStr = jObj.getString("fromDateStr");
		  String year = jObj.getString("year");
		  String toDateStr = jObj.getString("toDateStr");
	      nominatedPostDetailsVO = locationDashboardService.getAreaWiseDashboardCandidatesCountView(levelId,locationValuesList,statusIds,fromDateStr,toDateStr,year);  
	      
	    }catch(Exception e){
	      LOG.error("Entered into getAreaWiseDashboardCandidatesCountView method of LocationDashboardAction Action",e);
	    }
	    return Action.SUCCESS;
	  }

	public String getAllLocationWiseCount(){
		try{
			jObj = new JSONObject(getTask());			
					
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("levelValues"));			
		  		
			Long levelId = jObj.getLong("levelId");
			Long publicationDateId= jObj.getLong("publicationDateId");
			locationVo =  locationDashboardService.getAllLocationWiseCount(locationValuesList, levelId,publicationDateId);
			
		}catch(Exception e){
			LOG.error("Entered into getAllLocationWiseCount method of LocationDashboardAction Action",e);
		}
		return Action.SUCCESS;
	}	
	public String getTotalAlertDetailsForConstituencyInfo(){
		 try{
			  RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
				if(regVo!=null && regVo.getRegistrationID()!=null){
					Long userId = regVo.getRegistrationID();
				}
				jObj = new JSONObject(getTask());
				String fromDateStr = jObj.getString("fromDateStr");
				String year = jObj.getString("year");
				String toDateStr = jObj.getString("toDateStr");
				Long locationTypeId = jObj.getLong("locationTypeId");
				JSONArray alertTypeIdsStr = jObj.getJSONArray("alertTypeIdsStr");  
				List<Long> alertTypeIds = new ArrayList<Long>();
				if(alertTypeIdsStr != null && alertTypeIdsStr.length() > 0){
					for (int i = 0; i < alertTypeIdsStr.length(); i++){
						alertTypeIds.add(Long.parseLong(alertTypeIdsStr.getString(i)));        
					} 
				}
				JSONArray locationValuesArr = jObj.getJSONArray("locationValuesArr");  
				List<Long> locationValues = new ArrayList<Long>();
				if(locationValuesArr != null && locationValuesArr.length() > 0){
					for (int i = 0; i < locationValuesArr.length(); i++){
						locationValues.add(Long.parseLong(locationValuesArr.getString(i)));        
					} 
				}
				locationAlertVO = alertLocationDashboardService.getTotalAlertDetailsForConstituencyInfo(fromDateStr,toDateStr,locationValues,alertTypeIds,locationTypeId,year);
				
		 }catch(Exception e){
			 successMsg = "failure";
			 LOG.error("Exception Occured in getTotalAlertDetailsForConstituencyInfo() method, Exception - ",e);
		 }
		 return Action.SUCCESS;
	 }
	public String getPartyWiseMPandMLACandidatesCounts(){
		try{
			jObj = new JSONObject(getTask());			
					
			List<Long> electionIdsList = convertJsonStringList(jObj.getJSONArray("electionIds"));				
			Long loactionTypeId = jObj.getLong("loactionTypeId");
			Long loctionValue= jObj.getLong("loctionValue");
			List<Long> electionScopeIds = convertJsonStringList(jObj.getJSONArray("electionScopeIds"));
			//String type=jObj.getString("type");
			typeVo =  locationDashboardService.getPartyWiseMPandMLACandidatesCount(electionIdsList,electionScopeIds,loactionTypeId,loctionValue);
			
		}catch(Exception e){
			LOG.error("Entered into getPartyWiseMPandMLACandidatesCount method of LocationDashboardAction Action",e);
		}
		return Action.SUCCESS;
	}
	public String getNominatedPositionWiseCandidates(){
	     try{
	       jObj = new JSONObject(getTask());
	        List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  
	        nominatedPostCandList = nominatedPostLocationDashboardService.getNominatedPositionWiseCandidates(locationValues,jObj.getString("fromDateStr"),jObj.getString("toDateStr"),jObj.getLong("locationTypeId"),jObj.getString("year"),jObj.getLong("boardLevelId"),
	        		jObj.getLong("startIndex"),jObj.getLong("endIndex"));
	     }catch(Exception e){
	       LOG.error("Exception raised at getNominatedPositionWiseCandidates() of LocationDashboardAction{}", e);
	     }
	     return Action.SUCCESS;
	   }
	
	public String getElectionYears(){
	     try{
	       jObj = new JSONObject(getTask());
	      List<String> subTypes = new ArrayList<String>();
	       JSONArray jsonArray = jObj.getJSONArray("electionSubTypeArr");
	       if(jsonArray!=null && jsonArray.length()>0){
	         for(int i =0; i< jsonArray.length();i++){
	           subTypes.add(jsonArray.getString(i).toString());        
	         }
	       } 
	       keyValueVO = locationDashboardService.getElectionYears(subTypes);
	     }catch(Exception e){
	       LOG.error("Exception raised at getElectionYears() of LocationDashboardAction{}", e);
	     }
	     return Action.SUCCESS;
	   }
	
	public String getElectionInformationLocationWiseVoterShare() {

		try {
			jObj = new JSONObject(getTask());
			List<Long> electionScopeIds = convertJsonStringList(jObj.getJSONArray("electionScopeIds"));
			List<Long> partyIds = convertJsonStringList(jObj.getJSONArray("partyIds"));
			List<Long> electionYrs = convertJsonStringList(jObj.getJSONArray("electionYrs"));
			// String electionType =jObj.getString("electionSubType");
			List<String> subTypes = new ArrayList<String>();
			JSONArray jsonArray = jObj.getJSONArray("electionSubTypeArr");
			if (jsonArray != null && jsonArray.length() > 0) {
				for (int i = 0; i < jsonArray.length(); i++) {
					subTypes.add(jsonArray.getString(i).toString());
				}
			}
			electioninformationList = locationDashboardService.getElectionInformationLocationWiseVoterShare(jObj.getString("fromDate"),
							jObj.getString("toDate"),jObj.getLong("locationId"),jObj.getLong("locationValue"), electionScopeIds,partyIds, electionYrs, subTypes);


		} catch (Exception e) {
			LOG.error(
					"Exception occured in getElectionInformationLocationWise() method ",
					e);
		}
		return Action.SUCCESS;

	}
	public String getLevelWisePostsOverView(){
	     try{
	       jObj = new JSONObject(getTask());
	        List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  
	        nominatedPostCandList = nominatedPostLocationDashboardService.getLevelWisePostsOverView(locationValues,jObj.getString("fromDateStr"),jObj.getString("toDateStr"),jObj.getLong("locationTypeId"),jObj.getLong("boardLevelId"));
	     }catch(Exception e){
	       LOG.error("Exception raised at getLevelWisePostsOverView() of LocationDashboardAction{}", e);
	     }
	     return Action.SUCCESS;
	   }
	public String getDepartmentWisePostAndApplicationDetails(){
	     try{
	       jObj = new JSONObject(getTask());
	        List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  
	        nominatedPostDetailsVOList = nominatedPostLocationDashboardService.getDepartmentWisePostAndApplicationDetails(locationValues,jObj.getString("fromDateStr"),jObj.getString("toDateStr"),jObj.getLong("locationTypeId"),jObj.getString("year"),jObj.getLong("boardLevelId"),
	        		jObj.getLong("deptId"));
	     }catch(Exception e){
	       LOG.error("Exception raised at getDepartmentWisePostAndApplicationDetails() of LocationDashboardAction{}", e);
	     }
	     return Action.SUCCESS;
	   }
	
	public String getLevelWiseGoIssuedPostions(){
	     try{
	       jObj = new JSONObject(getTask());
	        List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  
	        List<Long> statusList = convertJsonStringList(jObj.getJSONArray("statusIds")); 
	        nominatedPostCandList = nominatedPostLocationDashboardService.getLevelWiseGoIssuedPostions(locationValues,jObj.getString("fromDateStr"),jObj.getString("toDateStr"),jObj.getLong("locationTypeId"),jObj.getString("year"),jObj.getLong("boardLevelId"),statusList);
	     }catch(Exception e){
	       LOG.error("Exception raised at getLevelWiseGoIssuedPostions() of LocationDashboardAction{}", e);
	     }
	     return Action.SUCCESS;
	   }
	public String getConstituencyWiseInsuranceWiseIssueTypeCounts(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  
			grivenceVO = locationDashboardService.getConstituencyWiseInsuranceWiseIssueTypeCounts(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationTypeId"),locationValues,jObj.getString("year"));
		}catch(Exception e){
			LOG.error("Exception raised at getConstituencyWiseInsuranceWiseIssueTypeCounts() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
	
	public String getGrivenceDetails(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  

			grivenceVO = locationDashboardService.getGrivenceDetails(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationTypeId"),locationValuesList,jObj.getString("year"));
		}catch(Exception e){
			LOG.error("Exception raised at getGrivenceDetails() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
	public String getLevelWiseGrievanceCounts(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  

			grivenceVO = locationDashboardService.getLevelWiseGrievanceCounts(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationTypeId"),locationValuesList,jObj.getString("year"));
		}catch(Exception e){
			LOG.error("Exception raised at getLevelWiseGrievanceCounts() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
	public String getLocationWiseGrivenceTrustIssueTypesCounts(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  
			grivenceVO = locationDashboardService.getLocationWiseGrivenceTrustIssueTypesCounts(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationTypeId"),locationValues,jObj.getString("year"));
		}catch(Exception e){
			LOG.error("Exception raised at getConstituencyWiseInsuranceWiseIssueTypeCounts() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
	public String getLocationWiseElectionResults(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  
			List<Long> yearsList = convertJsonStringList(jObj.getJSONArray("yearsArr")); 
			List<Long> electionScopeIdsList = convertJsonStringList(jObj.getJSONArray("electionScopeIdsArr"));
			List<Long> yearIdsList = convertJsonStringList(jObj.getJSONArray("yearsArr"));
			List<Long> partyIdsList = convertJsonStringList(jObj.getJSONArray("partyIdsArr"));
			List<String> subTypeList = new ArrayList<String>();
			JSONArray jsonArray = jObj.getJSONArray("electionSubTypeArr");
			if (jsonArray != null && jsonArray.length() > 0) {
				for (int i = 0; i < jsonArray.length(); i++) {
					subTypeList.add(jsonArray.getString(i).toString());
				}
			}
			informationVo = locationDashboardService.getLocationWiseElectionResults(electionScopeIdsList,subTypeList,jObj.getLong("lelevlId"),locationValuesList,yearIdsList,partyIdsList);
		}catch(Exception e){
			LOG.error("Exception raised at getLevelWiseGrievanceCounts() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
	
	@SuppressWarnings("null")
	public String getElectionInformationLocationWiseStatus(){
		try{
			
			jObj = new JSONObject(getTask());
			List<Long> partyIdList = convertJsonStringList(jObj.getJSONArray("partyIdsList"));
			List<Long> electionYearsList = convertJsonStringList(jObj.getJSONArray("electionYears"));
			List<Long> electionScopeIds = convertJsonStringList(jObj.getJSONArray("electionScopeIds"));
			List<String> subTypeList = new ArrayList<String>();
			JSONArray jsonArray = jObj.getJSONArray("electionSubTypeArr");
			if (jsonArray != null && jsonArray.length() > 0) {
				for (int i = 0; i < jsonArray.length(); i++) {
					subTypeList.add(jsonArray.getString(i).toString());
				}
			}
			electioninformationList = locationWiseElectionInformationDetalsService.getElectionInformationLocationWiseStatus(jObj.getLong("locationTypeId"),jObj.getLong("locationValue"),
					partyIdList,electionYearsList,electionScopeIds,subTypeList,jObj.getString("searchType"));

		}catch(Exception e){
			LOG.error("Exception raised at getElectionInformationLocationWiseStatus() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
	
	public String getDesignationWiseAlertsOverview(){
		 try{
			  RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
				if(regVo!=null && regVo.getRegistrationID()!=null){
					Long userId = regVo.getRegistrationID();
				}
				jObj = new JSONObject(getTask());
				String fromDateStr = jObj.getString("fromDateStr");
				String year = jObj.getString("year");
				String toDateStr = jObj.getString("toDateStr");
				Long locationTypeId = jObj.getLong("locationTypeId");
				JSONArray alertTypeIdsStr = jObj.getJSONArray("alertTypeIdsStr");  
				List<Long> alertTypeIds = new ArrayList<Long>();
				if(alertTypeIdsStr != null && alertTypeIdsStr.length() > 0){
					for (int i = 0; i < alertTypeIdsStr.length(); i++){
						alertTypeIds.add(Long.parseLong(alertTypeIdsStr.getString(i)));        
					} 
				}
				JSONArray locationValuesArr = jObj.getJSONArray("locationValuesArr");  
				List<Long> locationValues = new ArrayList<Long>();
				if(locationValuesArr != null && locationValuesArr.length() > 0){
					for (int i = 0; i < locationValuesArr.length(); i++){
						locationValues.add(Long.parseLong(locationValuesArr.getString(i)));        
					} 
				}
				locationAlertVOList = alertLocationDashboardService.getDesignationWiseAlertsOverview(fromDateStr,toDateStr,locationValues,alertTypeIds,locationTypeId,year);
				
		 }catch(Exception e){
			 successMsg = "failure";
			 LOG.error("Exception Occured in getDesignationWiseAlertsOverview() method, Exception - ",e);
		 }
		 return Action.SUCCESS;
	 }	
	public String getElectionDetailsData(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValue = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  
			electioninformationList = locationDashboardService.getElectionDetailsData(jObj.getString("electionYear"), jObj.getLong("locationTypeId"),locationValue,jObj.getLong("electionId"));
		}catch(Exception e){
			LOG.error("Exception raised at getConstituencyWiseInsuranceWiseIssueTypeCounts() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
	public String getAlertOverviewClick(){
		 try{
			  RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
				if(regVo!=null && regVo.getRegistrationID()!=null){
					Long userId = regVo.getRegistrationID();
				}
				jObj = new JSONObject(getTask());
				String fromDateStr = jObj.getString("fromDateStr");
				String year = jObj.getString("year");
				String toDateStr = jObj.getString("toDateStr");
				Long locationTypeId = jObj.getLong("locationTypeId");
				JSONArray alertTypeIdsStr = jObj.getJSONArray("alertTypeIdsStr");  
				List<Long> alertTypeIds = new ArrayList<Long>();
				if(alertTypeIdsStr != null && alertTypeIdsStr.length() > 0){
					for (int i = 0; i < alertTypeIdsStr.length(); i++){
						alertTypeIds.add(Long.parseLong(alertTypeIdsStr.getString(i)));        
					} 
				}
				JSONArray locationValuesArr = jObj.getJSONArray("locationValuesArr");  
				List<Long> locationValues = new ArrayList<Long>();
				if(locationValuesArr != null && locationValuesArr.length() > 0){
					for (int i = 0; i < locationValuesArr.length(); i++){
						locationValues.add(Long.parseLong(locationValuesArr.getString(i)));        
					} 
				}
				JSONArray statusIdsArr = jObj.getJSONArray("statusIdsArr");
				List<Long> statusIdsList = new ArrayList<Long>();
				if(statusIdsArr != null && statusIdsArr.length() > 0){
					for (int i = 0; i < statusIdsArr.length(); i++){
						statusIdsList.add(Long.parseLong(statusIdsArr.getString(i)));        
					} 
				}
				JSONArray impactIdsArr = jObj.getJSONArray("impactIdsArr");
				List<Long> impactIdsList = new ArrayList<Long>();
				if(impactIdsArr != null && impactIdsArr.length() > 0){
					for (int i = 0; i < impactIdsArr.length(); i++){
						impactIdsList.add(Long.parseLong(impactIdsArr.getString(i)));        
					} 
				}
				String type=jObj.getString("type");
				Long designationId=jObj.getLong("designationId");
				JSONArray alertCategeryIdsArr = jObj.getJSONArray("alertCategeryIdsArr");
				List<Long> alertCategeryIdsList = new ArrayList<Long>();
				if(alertCategeryIdsArr != null && alertCategeryIdsArr.length() > 0){
					for (int i = 0; i < alertCategeryIdsArr.length(); i++){
						alertCategeryIdsList.add(Long.parseLong(alertCategeryIdsArr.getString(i)));        
					} 
				}
				String otherCategory =jObj.getString("otherCategory");
				alertVOList = alertLocationDashboardService.getAlertOverviewClick(fromDateStr,toDateStr,locationValues,alertTypeIds,locationTypeId,year,statusIdsList,impactIdsList,type,designationId,alertCategeryIdsList,otherCategory);
				
		 }catch(Exception e){
			 successMsg = "failure";
			 LOG.error("Exception Occured in getAlertOverviewClick() method, Exception - ",e);
		 }
		 return Action.SUCCESS;
	 }
}
