package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AccommodationVO;
import com.itgrids.partyanalyst.dto.ActionableVO;
import com.itgrids.partyanalyst.dto.ActivityAttendanceVO;
import com.itgrids.partyanalyst.dto.ActivityLoginVO;
import com.itgrids.partyanalyst.dto.ActivityWSVO;
import com.itgrids.partyanalyst.dto.AttendanceQuestionnariWSVO;
import com.itgrids.partyanalyst.dto.CadreAddressVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreOverviewVO;
import com.itgrids.partyanalyst.dto.CadrePrintInputVO;
import com.itgrids.partyanalyst.dto.CadreTabRecordsStatusVO;
import com.itgrids.partyanalyst.dto.CadreTravelsVO;
import com.itgrids.partyanalyst.dto.CadreVoterVO;
import com.itgrids.partyanalyst.dto.CardNFCDetailsVO;
import com.itgrids.partyanalyst.dto.CardPrintUserVO;
import com.itgrids.partyanalyst.dto.CasteDetailsVO;
import com.itgrids.partyanalyst.dto.EffectedBoothsResponse;
import com.itgrids.partyanalyst.dto.GISIssuesVO;
import com.itgrids.partyanalyst.dto.GISUserTrackingVO;
import com.itgrids.partyanalyst.dto.GISVisualizationDetailsVO;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.ImageVO;
import com.itgrids.partyanalyst.dto.InviteesVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.MobileAppUserSmsStatusVO;
import com.itgrids.partyanalyst.dto.MobileAppUserVO;
import com.itgrids.partyanalyst.dto.MobileAppUserVoterVO;
import com.itgrids.partyanalyst.dto.NtrTrustStudentVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingWSVO;
import com.itgrids.partyanalyst.dto.PollManagementVO;
import com.itgrids.partyanalyst.dto.RegisteredMembershipCountVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SurveyTrainingsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.TdpCadreWSVO;
import com.itgrids.partyanalyst.dto.UnionTabUserVO;
import com.itgrids.partyanalyst.dto.UserDetailsVO;
import com.itgrids.partyanalyst.dto.UserEventDetailsVO;
import com.itgrids.partyanalyst.dto.VerifierVO;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;
import com.itgrids.partyanalyst.dto.WSResultVO;
import com.itgrids.partyanalyst.dto.WebServiceResultVO;
import com.itgrids.partyanalyst.webservice.utils.VoterTagVO;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLocationTrackingVo;

public interface IWebServiceHandlerService {
	
	public String checkForUserAuthentication(String userName , String passWord);
	
	public ResultStatus checkUserAuthenticationAndUpdateAuthorisedTime(String userId,String macAdressId);
	
	public ResultStatus sendSmsToUser(String uniquecode);
	
	public ResultStatus updatePassword(String uniqueCode,String pwd,String accessKey);
	
	public String sendVoiceSMS(String uniqueCode,String mobileNos,String audioFilePath);
	
	public List<WSResultVO> getUserVoiceRecordedFiles(String uniqueCode);
	
	public ResultStatus sendSMS(String uniqueCode,String mobileNos,String message);
	
	public String getBaseUrlForApp(String appName);
	
	public VoterDetailsVO getVoterDetailsBasedOnVoterId(String voterCardNo);
	
	public String saveCadreFromAndroid(VoterDetailsVO voterDetails);
	
	public String updateVoterDetails(String uniqueCode,Long voterId,Long casteStateId,String mobileNumber);
	
	
	
	public String updateCadreDetails(String voterID, Long casteStateId,
			Long caddreLevelId, String mobileNo, String uniqueId);
	
	public String updateIPDetails(String voterID, Long casteStateId,
			Long caddreLevelId, String mobileNo, String uniqueId,Long partyId);
	
	public String updateFalgDetails(String uniqueId,String flagName,String flagColor,String voterIds);
	
	public String updateVoterMobileNumberAndCaste(String voterID,
			Long casteStateId,
			String mobileNo,String uniqueId);
	
	public String updateVoterTagDetails(VoterTagVO voterTagVO);
	
	public String updateVoterBoothActivitiesDetails(VoterTagVO voterTagVO);
	
	public String requestForAuthorisationAccesskey(String uniqueCode);
	
	public String verificationForAuthorisationAccessKey(String uniqueCode,String accesskey);
	
	public EffectedBoothsResponse getInfectedBoothsOfConstituency(Long constituencyId);
	public WSResultVO getLoginFieldDataUser(String uname,String pwd);
	public Object getVCadreDataByPanchayatId1(Long panchayatId,String type);
	public Object getVCadreDataByPanchayatId(Long panchayatId);
	public Object tagCardIdForNFCReader(String cardNo , Long voetrId);
	public Object getCadreDetailsForPrinting(String memberNo);
	public Object checkNFCNumberForVoterId(Long voterId);
	public Object delinkNFCNumber(String cardNo , Long voterId);
	public List<CasteDetailsVO> getAllCastes();
	
	public Object getVCadreDetailsBySelection(CadrePrintInputVO input);
	
	public Object getTDPCadreDetailsBySearch(CadrePrintInputVO input);
	public Object updatePrintedCardDetails(List<CardNFCDetailsVO> inputVOList);
	
	public Object getCadreSurveyUserDetails(List<UserDetailsVO> cadreSurveyUserIds);
    //public Object getMobileNoByMemberShip(String memberShipNo);
	public List<CadreAddressVO> getMobileNoByMemberShip(List<String> memberShipNo);
	//public CadreAddressVO getMemberDataByMemberShip(String memberShipNo,String address);
	public List<CadreAddressVO> getMemberDataByMemberShip(List<String> addressTrueList , List<String> addressFalseList);
	public Object updateCadreTravelDiscountDetails(CadreTravelsVO inputVO);
	
	public Object cancellationOfTicketDetails(CadreTravelsVO inputVO);
	public CadreAddressVO getMemberDataByRefNoAndMemberShipNo(String refNo,String memberShipNo);
	public UserEventDetailsVO validateUserForEvent(UserEventDetailsVO inpuVo);
	
	public UserEventDetailsVO insertEventAttendeeInfo(UserEventDetailsVO inputVo);
	public Object updatePrintedCardInfo(List<CardNFCDetailsVO> inputVOList);
	public Object getTDPCadreDetailsForSearch(CadrePrintInputVO input);
	public Object getTDPCadreDetailsByMemberShip(CadrePrintInputVO input);
	public Object getCardPrintCountForAllUsers(CardPrintUserVO inputVO);
	public Object getCardPrintCountByUser(CardPrintUserVO inputVO);
	public UserEventDetailsVO updateDatasyncurl(UserEventDetailsVO inpuVo);
	public VerifierVO getTdpCadreSurveyDetails(Long tdpCadreId,Long surveyId,String searchTypeStr,Long boothId,String isPriority,String voterCardNo,Long constituencyId,String constiTypeStr);
	public ResultStatus verifyEventSyncData(UserEventDetailsVO inputVO);
	public WebServiceResultVO getCandidateAndLocationSummaryNews(String startDate,String endDate,String locationType,Long locationId,Long candidateId);
	public CadreOverviewVO getTdpCadreOverViewDetails(CadreOverviewVO inputVO);
	public PartyMeetingVO getPartyMeetingsForCadrePeople(Long tdpCadreId);
	public PartyMeetingVO getParticipatedCandidateEventDetails(Long tdpCadreId);
	public List<CadreCommitteeMemberVO> getEventDetailsOfCadre(Long tdpCadreId);
	public PartyMeetingVO getMeetingTypeWiseDescription(Long tdpCadreId,Long partyMeetingTypeId);
	public PartyMeetingVO getPartyMeetingsForCadreOverview(Long tdpCadreId);
	public List<RegisteredMembershipCountVO> getElectionPerformanceInCadreLocation(Long tdpCadreId,String voterCardNo);
	public NtrTrustStudentVO getNtrTrustStudentDetailsInstitutionWise(List<Long> cadreIds);
	public List<NtrTrustStudentVO> getStudentFormalDetailsByCadre(List<Long> tdpCadreids,Long institutionId);
	public List<Long> getAllRemovedCadre();
	public SurveyTrainingsVO getAllRecordsOfCampProgramScheduleAndBatch(Long campId, Long programId, Long scheduleId, Long batchId);
	public List<Long> getTdpCadreMemberShipsIdsByEvent(Long eventId);
	public List<TdpCadreVO>  getWebServiceEventInviteesList(Long userId,String accessLevel,String accessValue, Long stateId,List<InviteesVO> inviteesVOList,Long eventId,String actionType,String stateStr,String reportType, Integer startIndex,Integer maxIndex);
	 
	public ActivityLoginVO checkActivityTabUserLogin(String userName, String password);
	public TdpCadreWSVO getActivityCadreDetails(String memberShipNo, String voterCardNo);
	
	public ActivityWSVO getUserActivityDetailsByUserId(Long userId);
	public ImageVO saveActivitiesImages(ImageVO inputVO);
	public ResultStatus savePublicActivityAttendance(ActivityAttendanceVO inputVo);
	public ResultStatus saveActivityQuestionAnswer(AttendanceQuestionnariWSVO aqWSVO);
	public List<MobileAppUserVO> checkMobileAppUser(MobileAppUserVO inputVO);
	public List<MobileAppUserVO> getMobileAppUserSmsDetails(MobileAppUserVO inputVO);
	public ResultStatus saveMobileAppUserVoterData(MobileAppUserVoterVO inputVo);
	public ResultStatus saveMobileAppUserSmsStatusData(MobileAppUserSmsStatusVO inputVo);
    public List<MobileAppUserVoterVO> updateBoothVoter(MobileAppUserVoterVO inputVO);
    public String saveUserLocationData(UserLocationTrackingVo inputVO);
    public String updateVoterVotedData(MobileAppUserVoterVO inputVO);
    public PollManagementVO getDivisonWiseOverview(MobileAppUserVoterVO inputVO);
    public List<CadreVoterVO> getVoterInfoForBooth(MobileAppUserVoterVO inputVO);
    public CadreOverviewVO getVoterDetailsByVoterIdCardNum(CadreOverviewVO inputVO);
    public UnionTabUserVO checkLoginUnionTabUser(UnionTabUserVO inputVo);
    public PartyMeetingWSVO getAttendedDetailsForPartyMeeting(Long partyMeetingId);
    public PartyMeetingWSVO getTdpCadreDetailsForPartyMeeting(Long partyMeetingId,String searchType);
    public List<AccommodationVO> getAccommodationTrackingInfoByNotificationType(AccommodationVO inputvo);
    public List<GISVisualizationDetailsVO> getMembershipDriveVisualizationDetails(GISVisualizationParameterVO inputVO);
    public List<IdAndNameVO> getStateWiseConstituency();
    public List<IdAndNameVO> getConstitencyWiseTehsil(Long constituencyId);
    public List<IdAndNameVO> getPanchayatOrConsList(Long panchaConsistId);
    public List<IdAndNameVO> getBoothsList(Long panchayatId);
    public List<KeyValueVO> getStateWiseAssemblyConstituency(Long stateId);
    public List<GISUserTrackingVO> getLatestLattitudeLangitudeOfTabUser(GISUserTrackingVO vo);
    public ResultStatus syncCadreTabRecordsStatus(List<CadreTabRecordsStatusVO> cadreTabRecordsStatusList);
    public List<IdAndNameVO> getStateWiseDistrict(Long stateId);
    public List<IdAndNameVO> getDistrictWiseConstituency(Long districtId);
    public List<GISVisualizationDetailsVO> getMembershipDriveDayWiseVisualizationDetails(GISVisualizationParameterVO inputVO);
    public GISUserTrackingVO getLocationWiseTabUserTrackingDetails(GISVisualizationParameterVO inputVO);
    public GISIssuesVO getCadreRegistrationIssuesStatusDetails(GISVisualizationParameterVO inputVO);
    
    public String setArticleDetailsIntoAlert(ActionableVO VO);
    public String getAlertStatusOfArticle(Long articleId);
}
