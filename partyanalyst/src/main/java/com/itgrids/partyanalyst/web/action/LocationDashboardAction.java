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
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationAlertVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MeetingsVO;
import com.itgrids.partyanalyst.dto.NominatedPostCandidateDtlsVO;
import com.itgrids.partyanalyst.dto.NominatedPostDashboardVO;
import com.itgrids.partyanalyst.dto.NominatedPostDetailsVO;
import com.itgrids.partyanalyst.dto.PartyMeetingDataVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.util.IWebConstants;
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
	private GrivenceStatusVO grveneVO;
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
	private PartyMeetingDataVO partyMeetingDataVO;
	private List<PartyMeetingDataVO> locationParMetingVOList;
	private ElectionInformationVO electionInformationVO;
	private List<CandidateDetailsForConstituencyTypesVO> typeVoList;
	private PartyBoothPerformanceVO boothResult;
	private List<ElectionInformationVO> listElectionInformationVO;
	private List<IdNameVO> IdNameVOList;
	
	
	public List<IdNameVO> getIdNameVOList() {
		return IdNameVOList;
	}
	public void setIdNameVOList(List<IdNameVO> idNameVOList) {
		IdNameVOList = idNameVOList;
	}
	public List<ElectionInformationVO> getListElectionInformationVO() {
		return listElectionInformationVO;
	}
	public void setListElectionInformationVO(List<ElectionInformationVO> listElectionInformationVO) {
		this.listElectionInformationVO = listElectionInformationVO;
	}
	public PartyBoothPerformanceVO getBoothResult() {
		return boothResult;
	}
	public void setBoothResult(PartyBoothPerformanceVO boothResult) {
		this.boothResult = boothResult;
	}
	public ElectionInformationVO getElectionInformationVO() {
		return electionInformationVO;
	}
	public void setElectionInformationVO(ElectionInformationVO electionInformationVO) {
		this.electionInformationVO = electionInformationVO;
	}
	public PartyMeetingDataVO getPartyMeetingDataVO() {
		return partyMeetingDataVO;
	}
	public void setPartyMeetingDataVO(PartyMeetingDataVO partyMeetingDataVO) {
		this.partyMeetingDataVO = partyMeetingDataVO;
	}
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
	public List<PartyMeetingDataVO> getLocationParMetingVOList() {
		return locationParMetingVOList;
	}
	public void setLocationParMetingVOList(List<PartyMeetingDataVO> locationParMetingVOList) {
		this.locationParMetingVOList = locationParMetingVOList;
	}
	public List<CandidateDetailsForConstituencyTypesVO> getTypeVoList() {
		return typeVoList;
	}
	public void setTypeVoList(
			List<CandidateDetailsForConstituencyTypesVO> typeVoList) {
		this.typeVoList = typeVoList;
	}
	public GrivenceStatusVO getGrveneVO() {
		return grveneVO;
	}
	public void setGrveneVO(GrivenceStatusVO grveneVO) {
		this.grveneVO = grveneVO;
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
			  List<Long> electionScopeIdsList = convertJsonStringList(jObj.getJSONArray("electionScopeIdsArr"));
			  constituencyElectionResultsVOList = constituencyPageService.getConstituencyElectionResults(constituencyId,electionScopeIdsList);
			  
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
	 
	 public String  getLocationWiseMeetingsCountDetails(){
		 try {
			jObj = new JSONObject(getTask());
			List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValues"));
			String fromDateStr =jObj.getString("fromDate");
			String toDateStr =jObj.getString("toDate");
			Long partyMeetingMainTypeId =jObj.getLong("partyMeetingMainTypeId"); 
			//locationVotersVOList = locationDashboardService.getLocationWiseMeetingsCount(jObj.getLong("locationTypeId"),locationValues,fromDateStr,toDateStr);
			//locationVotersVOList = meetingLocationDashboardService.getLocationWiseMeetingsCount(jObj.getLong("locationTypeId"),locationValues,fromDateStr,toDateStr);
			if(partyMeetingMainTypeId != null && partyMeetingMainTypeId.longValue() >0l && partyMeetingMainTypeId.longValue()==1l){
				partyMeetingDataVO = meetingLocationDashboardService.getLocationWiseCommitteeMeetings(jObj.getLong("locationTypeId"),locationValues,fromDateStr,toDateStr,partyMeetingMainTypeId);
			}else if(partyMeetingMainTypeId != null && partyMeetingMainTypeId.longValue() >0l && partyMeetingMainTypeId.longValue()==2l){
				partyMeetingDataVO = meetingLocationDashboardService.getLocationWiseStateMeetings(jObj.getLong("locationTypeId"),locationValues,fromDateStr,toDateStr,partyMeetingMainTypeId);
			}else if(partyMeetingMainTypeId != null && partyMeetingMainTypeId.longValue() >0l && partyMeetingMainTypeId.longValue()==3l){
				partyMeetingDataVO = meetingLocationDashboardService.getLocationWiseSpecialMeetings(jObj.getLong("locationTypeId"),locationValues,fromDateStr,toDateStr,partyMeetingMainTypeId);
			}
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
			 List<Long> publicationDateIdsLst = convertJsonStringList(jObj.getJSONArray("publicationDateIdsLst"));
			 enrollmentYears = locationDashboardService.getEnrollmentIds(publicationDateIdsLst);
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
	
	public String getAllElectionYears(){
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
							jObj.getString("toDate"),jObj.getLong("locationId"),jObj.getLong("locationValue"), electionScopeIds,partyIds, electionYrs, subTypes,jObj.getString("withAllaince"));


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
	        Long startIndex = jObj.getLong("startIndex");
	        Long endIndex = jObj.getLong("endIndex");
	        nominatedPostCandList = nominatedPostLocationDashboardService.getLevelWiseGoIssuedPostions(locationValues,jObj.getString("fromDateStr"),jObj.getString("toDateStr"),jObj.getLong("locationTypeId"),jObj.getString("year"),jObj.getLong("boardLevelId"),statusList,startIndex,endIndex);
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
			Long constituencyId = jObj.getLong("constituencyId");
			informationVo = locationDashboardService.getLocationWiseElectionResults(electionScopeIdsList,subTypeList,jObj.getLong("lelevlId"),locationValuesList,yearIdsList,partyIdsList,constituencyId,jObj.getString("withAllaince"));
		}catch(Exception e){
			LOG.error("Exception raised at getLevelWiseGrievanceCounts() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
	
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
					partyIdList,electionYearsList,electionScopeIds,subTypeList,jObj.getString("searchType"),jObj.getString("withAllaince"));

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
				List<Long> alertTypeIds =  convertJsonStringList(jObj.getJSONArray("alertTypeIdsStr"));  
				List<Long> locationValues =  convertJsonStringList(jObj.getJSONArray("locationValuesArr")); 
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
			List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));
			List<Long> partyIds =  convertJsonStringList(jObj.getJSONArray("partyIdsArr"));
			List<Long> electionYears = convertJsonStringList(jObj.getJSONArray("electionYearArr"));
			List<String> subTypes=  new ArrayList<String>();
			JSONArray jsonArray = jObj.getJSONArray("electionSubTypeArr");
			if(jsonArray!=null && jsonArray.length()>0){
				for(int i =0; i< jsonArray.length();i++){
					subTypes.add(jsonArray.getString(i).toString());        
				}
			}
			List<Long> electionScopeIds = convertJsonStringList(jObj.getJSONArray("electionScopeIdsArr"));
			
			electioninformationList = locationDashboardService.getElectionDetailsData(electionYears, jObj.getLong("locationTypeId"),locationValues,jObj.getLong("electionId"),subTypes,partyIds,electionScopeIds,jObj.getString("withAllaince"));
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
				List<Long> alertTypeIds = convertJsonStringList(jObj.getJSONArray("alertTypeIdsStr"));
				List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  
				List<Long> statusIdsList = convertJsonStringList(jObj.getJSONArray("statusIdsArr"));
				List<Long> impactIdsList = convertJsonStringList(jObj.getJSONArray("impactIdsArr"));
				String type=jObj.getString("type");
				Long designationId=jObj.getLong("designationId");
				List<Long> alertCategeryIdsList = convertJsonStringList(jObj.getJSONArray("alertCategeryIdsArr"));
				String otherCategory =jObj.getString("otherCategory");
				alertVOList = alertLocationDashboardService.getAlertOverviewClick(fromDateStr,toDateStr,locationValues,alertTypeIds,locationTypeId,year,statusIdsList,impactIdsList,type,designationId,alertCategeryIdsList,otherCategory);
				
		 }catch(Exception e){
			 successMsg = "failure";
			 LOG.error("Exception Occured in getAlertOverviewClick() method, Exception - ",e);
		 }
		 return Action.SUCCESS;
	 }
	
	public String getLocationWiseMeetingStatusDetails(){
		 try{
			  RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
				if(regVo!=null && regVo.getRegistrationID()!=null){
					Long userId = regVo.getRegistrationID();
				}
				jObj = new JSONObject(getTask());
				String fromDateStr = jObj.getString("fromDateStr");
				String toDateStr = jObj.getString("toDateStr");
				Long searchLocationId = jObj.getLong("searchLocationId");
				List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValuesArr")); 
				 Long partyMeetinLevelId = jObj.getLong("partyMeetinLevelId");
				 Long meetingTypeId = jObj.getLong("meetingTypeId");
				
				 locationParMetingVOList = meetingLocationDashboardService.getLocationWiseMeetingStatusDetails(searchLocationId,locationValues,fromDateStr,toDateStr,meetingTypeId,partyMeetinLevelId);
				
		 }catch(Exception e){
			 successMsg = "failure";
			 LOG.error("Exception Occured in getDesignationWiseAlertsOverview() method, Exception - ",e);
		 }
		 return Action.SUCCESS;
	 }
	
	public String getLocationWiseCrossVotingDetails(){
		 try{
			  RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
				if(regVo!=null && regVo.getRegistrationID()!=null){
					Long userId = regVo.getRegistrationID();
				}
				jObj = new JSONObject(getTask());
				//String fromDateStr = jObj.getString("fromDateStr");
				//String toDateStr = jObj.getString("toDateStr");
				 List<Long> electionYrs = convertJsonStringList(jObj.getJSONArray("electionYearArr"));
				 List<Long> parliamentIds = convertJsonStringList(jObj.getJSONArray("parliamentIdsArr"));
				 List<Long> assemblyIds = convertJsonStringList(jObj.getJSONArray("assemblyIdsArr"));
				 List<Long> partyIds = convertJsonStringList(jObj.getJSONArray("partyIdsArr"));
				 List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValue"));
				 
				String withAlliance = jObj.getString("withAlliance");
				JSONArray subTypesArr = jObj.getJSONArray("subTypesArr");  
				List<String> subTypes = new ArrayList<String>();
					for (int i = 0; i < subTypesArr.length(); i++){
						subTypes.add(subTypesArr.getString(i).toString());        
					} 
				
				 Long locationLevelId = jObj.getLong("locationLevelId");
				 List<Long> electionScopeIds = convertJsonStringList(jObj.getJSONArray("electionScopeIdsArr"));
				 listElectionInformationVO = locationWiseElectionInformationDetalsService.getLocationBasedCrossVotingReult( electionYrs,  locationLevelId, locationValues,null,subTypes,
						 partyIds,electionScopeIds,withAlliance);
				
		 }catch(Exception e){
			 successMsg = "failure";
			 LOG.error("Exception Occured in getLocationWiseCrossVotingDetails() method, Exception - ",e);
		 }
		 return Action.SUCCESS;
	 }
	public String getPartyWiseMPandMLACandidatesCountDetials(){
		try{
			jObj = new JSONObject(getTask());			
					
			List<Long> electionIdsList = convertJsonStringList(jObj.getJSONArray("electionIds"));				
			Long loactionTypeId = jObj.getLong("loactionTypeId");
			Long loctionValue= jObj.getLong("loctionValue");
			List<Long> electionScopeIds = convertJsonStringList(jObj.getJSONArray("electionScopeIds"));
			Long partyId = jObj.getLong("partyId");
			Long districtId = jObj.getLong("districtId");
			
			JSONArray electionYearArray = jObj.getJSONArray("electionYearsArr");  
			List<String> electionYearLst = new ArrayList<String>();
				for (int i = 0; i < electionYearArray.length(); i++){
					electionYearLst.add(electionYearArray.getString(i).toString());        
				} 
			
			typeVoList =  locationDashboardService.getPartyWiseMPandMLACandidatesCountDetials(electionIdsList,electionScopeIds,loactionTypeId,loctionValue,partyId,districtId,electionYearLst);
			
		}catch(Exception e){
			LOG.error("Entered into getPartyWiseMPandMLACandidatesCountDetials method of LocationDashboardAction Action",e);
		}
		return Action.SUCCESS;
	}
   public String getAllParliamentConstituencyByAllLevels(){
		
		try{	
			jObj = new JSONObject(getTask());
			Long loactionTypeId = jObj.getLong("loactionTypeId");
			List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValue"));
			locationVOList = locationDashboardService.getAllParliamentConstituencyByAllLevels(loactionTypeId,locationValues);
		}catch(Exception e){
			LOG.error("Exception occured in getAllParliamentConstituencyByAllLevels() method ",e);

		}
		return Action.SUCCESS;
	}
   
   public String getlocationBasedBoothWiseResult()
	{
		try {
			
			jObj = new JSONObject(getTask());
			Long constituencyId = jObj.getLong("constituencyId");
			Long electionScopeId =jObj.getLong("electionScopeId");
			JSONArray partyArr = jObj.getJSONArray("partyList");
			List<Long> partyIds = new ArrayList<Long>();
			
			if(partyArr != null && partyArr.length()>0)
			{
				for (int i = 0; i < partyArr.length(); i++) {
					partyIds.add(Long.valueOf(partyArr.get(i).toString()));
				}
			}
			
			

			String path = IWebConstants.STATIC_CONTENT_FOLDER_URL;

			List<PartyBoothPerformanceVO> PartyBoothPerformanceVOList1 = new ArrayList<PartyBoothPerformanceVO>();
			
			if(jObj.getString("task").equalsIgnoreCase("assemblyWiseResults") && electionScopeId >0l)
			{				
				List<PartyBoothPerformanceVO> boothResults = locationWiseElectionInformationDetalsService.getBoothWiseElectionResults(partyIds, constituencyId, jObj.getLong("electionyears"),jObj.getLong("electionScopeId"),
						jObj.getLong("locationTypeId"),jObj.getLong("locationValue"));
				if(boothResults != null && boothResults.size()>0)
				{
					for (PartyBoothPerformanceVO vo : boothResults) 
					{	
						path = IWebConstants.STATIC_CONTENT_FOLDER_URL+""+vo.getPartyName();
						PartyBoothPerformanceVO boothResult1 = locationWiseElectionInformationDetalsService.getVotingPercentageWiseBoothResultForParties(vo,true,path,partyIds);
												boothResult1 = locationWiseElectionInformationDetalsService.getVotingPercentageWiseBoothResultForParties(vo,false,null,partyIds);
						
						PartyBoothPerformanceVOList1.add(boothResult1);
					}
				}
				
				boothResult = locationWiseElectionInformationDetalsService.segrigateBoothWiseResults(PartyBoothPerformanceVOList1);
			}
			else if(jObj.getString("task").equalsIgnoreCase("parliamentWiseResults") && electionScopeId >0l)
			{			
				List<PartyBoothPerformanceVO> boothResults = locationWiseElectionInformationDetalsService.getBoothWiseElectionResults(partyIds, constituencyId, jObj.getLong("electionyears"),jObj.getLong("electionScopeId"),
						jObj.getLong("locationTypeId"),jObj.getLong("locationValue"));
				
				if(boothResults != null && boothResults.size()>0)
				{
					for (PartyBoothPerformanceVO vo : boothResults) 
					{	
						path = IWebConstants.STATIC_CONTENT_FOLDER_URL+""+vo.getPartyName();
						PartyBoothPerformanceVO boothResult1 = locationWiseElectionInformationDetalsService.getVotingPercentageWiseBoothResultForParties(vo,true,path,partyIds);
												boothResult1 = locationWiseElectionInformationDetalsService.getVotingPercentageWiseBoothResultForParties(vo,false,null,partyIds);
						
						PartyBoothPerformanceVOList1.add(boothResult1);
					}
				}
				
				boothResult = locationWiseElectionInformationDetalsService.segrigateBoothWiseResults(PartyBoothPerformanceVOList1);
			}else if(electionScopeId == 0l){
				List<PartyBoothPerformanceVO> PartyBoothPerformanceVOList2 = new ArrayList<PartyBoothPerformanceVO>();
				List<PartyBoothPerformanceVO> PartyBoothPerformanceVOList3 = new ArrayList<PartyBoothPerformanceVO>();
				
				//assembly booth wise result
				List<PartyBoothPerformanceVO> boothResults2 = locationWiseElectionInformationDetalsService.getBoothWiseElectionResults(partyIds, constituencyId, jObj.getLong("electionyears"),2L,
						jObj.getLong("locationTypeId"),jObj.getLong("locationValue"));
								
					if(boothResults2 != null && boothResults2.size()>0)
					{
						for (PartyBoothPerformanceVO vo : boothResults2) 
						{	
							path = IWebConstants.STATIC_CONTENT_FOLDER_URL+""+vo.getPartyName();
							PartyBoothPerformanceVO boothResult1 = locationWiseElectionInformationDetalsService.getVotingPercentageWiseBoothResultForParties(vo,true,path,partyIds);
													boothResult1 = locationWiseElectionInformationDetalsService.getVotingPercentageWiseBoothResultForParties(vo,false,null,partyIds);
							
							PartyBoothPerformanceVOList2.add(boothResult1);
						}
					}
				 
				
							
					// parliament booth wise result
					List<PartyBoothPerformanceVO> boothResults3 = locationWiseElectionInformationDetalsService.getBoothWiseElectionResults(partyIds, constituencyId, jObj.getLong("electionyears"),1L,
							jObj.getLong("locationTypeId"),jObj.getLong("locationValue"));
					if(boothResults3 != null && boothResults3.size()>0)
					{
						for (PartyBoothPerformanceVO vo : boothResults3) 
						{	
							path = IWebConstants.STATIC_CONTENT_FOLDER_URL+""+vo.getPartyName();
							PartyBoothPerformanceVO boothResult1 = locationWiseElectionInformationDetalsService.getVotingPercentageWiseBoothResultForParties(vo,true,path,partyIds);
													boothResult1 = locationWiseElectionInformationDetalsService.getVotingPercentageWiseBoothResultForParties(vo,false,null,partyIds);
							
							PartyBoothPerformanceVOList3.add(boothResult1);
						}
					}
				
				PartyBoothPerformanceVO assemblyBoothResultVO = locationWiseElectionInformationDetalsService.segrigateBoothWiseResults(PartyBoothPerformanceVOList2);
				PartyBoothPerformanceVO parliamentBoothResultVO = locationWiseElectionInformationDetalsService.segrigateBoothWiseResults(PartyBoothPerformanceVOList3);
				
				boothResult = locationWiseElectionInformationDetalsService.getBoothWiseElectionResultsForAssamblyAndParlaiment(assemblyBoothResultVO,parliamentBoothResultVO);
			}					
		} catch (Exception e) {
			LOG.error(" exception occured in ajaxHandler() in PartyBoothResult2Action action class.",e);
		}
		return SUCCESS;
	}
   
   public String getLocationWiseVotingDetails(){
		 try{
			  RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
				if(regVo!=null && regVo.getRegistrationID()!=null){
					Long userId = regVo.getRegistrationID();
				}
				jObj = new JSONObject(getTask());
				 List<Long> electionYrs = convertJsonStringList(jObj.getJSONArray("electionYearArr"));
				 List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValue"));
				 
				 JSONArray subTypesArr = jObj.getJSONArray("subTypesArr");  
				List<String> subTypes = new ArrayList<String>();
					for (int i = 0; i < subTypesArr.length(); i++){
						subTypes.add(subTypesArr.getString(i).toString());        
					} 
				
				 Long locationLevelId = jObj.getLong("locationLevelId");
				 String searchLevel = jObj.getString("searchLevel");
				 String clickType = jObj.getString("clickType");
				 List<Long> partyIds = convertJsonStringList(jObj.getJSONArray("partyIds"));
				 electioninformationList = locationWiseElectionInformationDetalsService.getLocationWiseVotingDetails( electionYrs, locationLevelId, locationValues, subTypes,searchLevel,clickType,partyIds);
				
		 }catch(Exception e){
			 successMsg = "failure";
			 LOG.error("Exception Occured in getLocationWiseVotingDetails() method, Exception - ",e);
		 }
		 return Action.SUCCESS;
	 }
   
   @SuppressWarnings("null")
	public String getElectionInformationLocationWiseStatusAndYearWise(){
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
			List<Long> locationIdsList = convertJsonStringList(jObj.getJSONArray("locationIds"));
			
			electioninformationList = locationWiseElectionInformationDetalsService.getElectionInformationLocationWiseStatusAndYearWise(jObj.getLong("locationTypeId"),jObj.getLong("locationValue"),
				partyIdList,electionYearsList,electionScopeIds,subTypeList,jObj.getString("searchType"),jObj.getString("statusType"),jObj.getString("year"),locationIdsList,jObj.getString("withAllaince"));
		}catch(Exception e){
			LOG.error("Exception raised at getElectionInformationLocationWiseStatusAndYearWise() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
   
   public String getAreaWisePartyMeetingsDetails(){
		
		try{	
			jObj = new JSONObject(getTask());
			Long locationScopeId = jObj.getLong("locationScopeId");
			List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValue"));
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			Long meetingLevelId = jObj.getLong("meetingLevelId");
			Long meetingTypeId = jObj.getLong("meetingTypeId");
			Long meetingMainTypeId = jObj.getLong("meetingMainTypeId");
			String searchFor = jObj.getString("searchFor");
			meetingsVO = locationDashboardService.getAreaWisePartyMeetingsDetails(locationScopeId,locationValues,startDate,endDate,meetingLevelId,meetingTypeId,meetingMainTypeId,searchFor);
		}catch(Exception e){
			LOG.error("Exception occured in getAreaWisePartyMeetingsDetails() method ",e);

		}
		return Action.SUCCESS;
	}
   

   public String getGrivenceOverviewDtls(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  
			grveneVO = locationDashboardService.getGrivenceOverviewDtls(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationTypeId"),locationValuesList,jObj.getString("year"),jObj.getLong("stateId"));
		}catch(Exception e){
			LOG.error("Exception raised at getGrivenceOverviewDtls() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
   public String getGrivenceComplaintCountDepartmentWise(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  

			grivenceVO = locationDashboardService.getGrivenceComplaintCountDepartmentWise(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationTypeId"),locationValuesList,jObj.getString("year"),jObj.getLong("stateId"));
		}catch(Exception e){
			LOG.error("Exception raised at getGrivenceComplaintCountDepartmentWise() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
   public String getGrivenceFinancialSupportDtls(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  

			grveneVO = locationDashboardService.getGrivenceFinancialSupportDtls(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationTypeId"),locationValuesList,jObj.getString("year"),jObj.getLong("stateId"));
		}catch(Exception e){
			LOG.error("Exception raised at getGrivenceFinancialSupportDtls() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
   public String getLocationWiseTypeOfIssueGrivenceComplaintCount(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  

			grivenceVO = locationDashboardService.getLocationWiseTypeOfIssueGrivenceComplaintCount(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationTypeId"),locationValuesList,jObj.getString("year"),jObj.getLong("stateId"));
		}catch(Exception e){
			LOG.error("Exception raised at getLocationWiseTypeOfIssueGrivenceComplaintCount() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
   public String getInsuranceOverviewDetails(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  

			grveneVO = locationDashboardService.getInsuranceOverviewDetails(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationTypeId"),locationValuesList,jObj.getString("year"),jObj.getLong("stateId"));
		}catch(Exception e){
			LOG.error("Exception raised at getInsuranceOverviewDetails() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
  public String getLocationWiseInsuranceIssueTypeComplaintCount(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  

			grivenceVO = locationDashboardService.getLocationWiseInsuranceIssueTypeComplaintCount(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationTypeId"),locationValuesList,jObj.getString("year"),jObj.getLong("stateId"));
		}catch(Exception e){
			LOG.error("Exception raised at getLocationWiseInsuranceIssueTypeComplaintCount() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
   public String getTrustEducationOverviewDetails(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  

			grveneVO = locationDashboardService.getTrustEducationOverviewDetails(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationTypeId"),locationValuesList,jObj.getString("year"),jObj.getLong("stateId"));
		}catch(Exception e){
			LOG.error("Exception raised at getTrustEducationOverviewDetails() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
  public String getLocationWiseTrustEducationComplaintCount(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  

			grivenceVO = locationDashboardService.getLocationWiseTrustEducationComplaintCount(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationTypeId"),locationValuesList,jObj.getString("year"),jObj.getLong("stateId"));
		}catch(Exception e){
			LOG.error("Exception raised at getLocationWiseTrustEducationComplaintCount() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
  public String getTrustEducationSubjectForDetails(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValuesArr"));  

			grveneVO = locationDashboardService.getTrustEducationSubjectForDetails(jObj.getString("fromDate"), jObj.getString("toDate"), jObj.getLong("locationTypeId"),locationValuesList,jObj.getString("year"),jObj.getLong("stateId"));
		}catch(Exception e){
			LOG.error("Exception raised at getTrustEducationSubjectForDetails() of LocationDashboardAction{}",e);
		}
		return Action.SUCCESS;
	}
  public String getElectionYearAndPartiesWiseDetails(){
	   try{
		   LOG.info("Entered into getElectionYearAndPartiesWiseDetails of locationDashboardAction");
		   jObj = new JSONObject(getTask());
		   List<Long> electionScopeIdsList = convertJsonStringList(jObj.getJSONArray("electionScopeIds"));
		   List<String> subTypeList = new ArrayList<String>();
		   JSONArray jsonArray = jObj.getJSONArray("electionSubTypeArr");
			if (jsonArray != null && jsonArray.length() > 0) {
				for (int i = 0; i < jsonArray.length(); i++) {
					subTypeList.add(jsonArray.getString(i).toString());
				}
			}
		   electioninformationList = locationDashboardService.getElectionYearWisePartyDetails(electionScopeIdsList,subTypeList);
	   }catch(Exception e){
		   LOG.error("Exception occured in getElectionYearAndPartiesWiseDetails() method of locationDashboardAction ",e);
	   }
	   return Action.SUCCESS;
  }
  
  public String getLocationBasedElectionDetails(){
	   try{
		   LOG.info("Entered into getLocationBasedElectionDetails of locationDashboardAction");
		   jObj = new JSONObject(getTask());
		   String locationType = jObj.getString("locationType");
		 
		   keyValueVOList = locationDashboardService.getLocationWiseElectionDetails(locationType);
	   }catch(Exception e){
		   LOG.error("Exception occured in getElectionYearAndPartiesWiseDetails() method of locationDashboardAction ",e);
	   }
	   return Action.SUCCESS;
 }
  
  public String  getLocationWiseSpecialMeetingsMeetingsExpanction(){
		 try {
			jObj = new JSONObject(getTask());
			List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValues"));
			String fromDateStr =jObj.getString("fromDate");
			String toDateStr =jObj.getString("toDate");
			Long partyMeetingMainTypeId =jObj.getLong("partyMeetingMainTypeId"); 
			Long partyMeetingTypeId =jObj.getLong("partyMeetingTypeId");
			
			partyMeetingDataVO = meetingLocationDashboardService.getLocationWiseSpecialMeetingsMeetingsExpanction(jObj.getLong("locationTypeId"),locationValues,fromDateStr,toDateStr,partyMeetingMainTypeId,partyMeetingTypeId);
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseSpecialMeetingsMeetingsExpanction", e);
		}
		 return Action.SUCCESS;
	 }
  public String getCastWiseCadreCount(){
		 try {
			// getCasteWiseCadreCounts(Long locationTypeId, List<Long> LocationValue,List<Long> enrollmentYearIds)
			 jObj = new JSONObject(getTask());
			 List<Long> enrollmentYearIds=convertJsonStringList(jObj.getJSONArray("enrollmentyearIds"));
			 List<Long> LocationValues=convertJsonStringList(jObj.getJSONArray("LocationValues"));
			 
			 locationVotersVOList = locationWiseCasteInfoService.getCasteWiseCadreCounts(jObj.getLong("locationTypeId"),LocationValues,enrollmentYearIds);
		} catch (Exception e) {
			LOG.error("Exception raised at getEnrollmentYearAgeGroupWiseCadres", e);
		}
		 return Action.SUCCESS;
	 }
  
  public String  getLocationWiseMeetingInviteeMembers(){
		 try {
			jObj = new JSONObject(getTask());
			List<Long> locationValues = convertJsonStringList(jObj.getJSONArray("locationValues"));
			String fromDateStr =jObj.getString("fromDate");
			String toDateStr =jObj.getString("toDate");
			Long partyMeetingMainTypeId =jObj.getLong("partyMeetingMainTypeId"); 
			Long partyMeetingTypeId =jObj.getLong("partyMeetingTypeId");
			Long partyMeetingId = jObj.getLong("partyMeetingId");
			String includePastMeetings =jObj.getString("includePastMeetings");
			String status =jObj.getString("status");
			Long sessionTypeId = jObj.getLong("sessionTypeId");
			
			IdNameVOList = meetingLocationDashboardService.getLocationWiseMeetingInviteeMembers(jObj.getLong("locationTypeId"),locationValues,fromDateStr,toDateStr,partyMeetingMainTypeId,
					partyMeetingTypeId,partyMeetingId,includePastMeetings,sessionTypeId,status);
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseMeetingInviteeMembers", e);
		}
		 return Action.SUCCESS;
	 }
  public String  getCategoryWiseGenderCount(){
		 try {
			jObj = new JSONObject(getTask());
			List<Long> locationValuesList = convertJsonStringList(jObj.getJSONArray("locationValuesArr")); 
			List<Long> enrollmentYearIdsList = convertJsonStringList(jObj.getJSONArray("enrollmentYearIdsArr")); 

			cadreDtlsList = locationDashboardService.getCategoryWiseGenderCount(jObj.getLong("locationScopeId"),locationValuesList,enrollmentYearIdsList);
		} catch (Exception e) {
			LOG.error("Exception raised at getCategoryWiseGenderCount in LocationDashboardAction class", e);
		}
		 return Action.SUCCESS;
	 }
}
