package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AffiliatedCadreVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.ByeElectionVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CadrePrintInputVO;
import com.itgrids.partyanalyst.dto.CadrePrintVO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreTabRecordsStatusVO;
import com.itgrids.partyanalyst.dto.CadreTravelsVO;
import com.itgrids.partyanalyst.dto.CardNFCDetailsVO;
import com.itgrids.partyanalyst.dto.CardPrintUserVO;
import com.itgrids.partyanalyst.dto.CardSenderVO;
import com.itgrids.partyanalyst.dto.CasteDetailsVO;
import com.itgrids.partyanalyst.dto.GISUserTrackingVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.KeyValuePairVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.MissedCallCampaignVO;
import com.itgrids.partyanalyst.dto.MissedCallsDetailsVO;
import com.itgrids.partyanalyst.dto.PartyMeetingWSVO;
import com.itgrids.partyanalyst.dto.RegistrationQueriesVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.RtcUnionInputVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SinkVO;
import com.itgrids.partyanalyst.dto.SurveyCadreResponceVO;
import com.itgrids.partyanalyst.dto.TabRecordsStatusVO;
import com.itgrids.partyanalyst.dto.TdpCadreFamilyDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.UserDetailsVO;
import com.itgrids.partyanalyst.dto.VoterInfoVO;
import com.itgrids.partyanalyst.dto.VoterSearchVO;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.TdpCadreBackupDetails;

public interface ICadreRegistrationService {

	public SurveyCadreResponceVO saveCadreRegistration(final List<CadreRegistrationVO> cadreRegistrationVO,String registrationType);
	
	public List<VoterInfoVO> getSearchDetailsCadreRegistration(Long constituencyId, String seachType, String candidateName, String voterCardId, String houseNo,Long panchayatId,Long boothId,String villagesCovered,Integer startIndex,Integer maxIndex);
	
	public List<VoterInfoVO> getCandidateInfoBySearchCriteria(String searchType, Long candidateId,String staticContentLoc,String constituencyId,Long tdpMemberTypeId);
	
	public List<GenericVO> getBoothCoverdVillagesDetails(List<Long> boothIds);
	
	public List<GenericVO> getBoothForPanchayats(Long constituencyId, Long locationId);
	
	public List<GenericVO> getConstiteuncyDetailsByConstiteuncy(Long constituencyId);
	
	public List<SelectOptionVO> getOptionDetailsForCadre();	
	
	public List<SelectOptionVO> getElectionOptionDetailsForCadre();
	
	public List<SelectOptionVO> getElectionYearsByElectionType(Long electionTypeId);

	public List<GenericVO> getExistingCadreInfo(String candidateName,Long constituencyId,Long panchayatId,Long boothId,String isPresentCadre, String enrollmentNo);
	
	public CadrePrintVO getCadreDetailsForPrinting(String memberCardNo);
	
	public List<CadrePrintVO> getSelectedLevelCadreDetails1(Long panchayatId,String type);
	
	public List<CadrePrintVO> getSelectedLevelCadreDetails(Long panchayatId);
	
	public String tagCardIdForNFCReader(String cardNumber,Long voterId);
	
	public ResultStatus saveNewCadreSurveyUser(final Long userId, final String surveyUserName, final String  password, final String mobileNo);

	public List<GenericVO> getSurveyCadreUsersList();
	
	public List<SelectOptionVO> getSurveyCadreAssignedConstituencyList();
	
	public List<GenericVO> getSurveyCadreAssignedUsersList(Long constiteuncyId);
	
	public ResultStatus releaseCadreSurveyUser(final Long cadreSurveyUserAssignedId);
	
	public ResultStatus assignUserForLocation(final Long surveyUserId, final Long levelId, final Long levelValue,final Long constituencyId,final String TabNo);
	
	public List<SelectOptionVO> getSubRegionsInConstituency(Long constituencyId, String scope) ;
	
	public String isTabAssignedAlready(String TabNo);
	
	public List<BasicVO> constituencyListForElection(Long electionId,Long constituencyId);
	
	public List<BasicVO> participatedCandList(Long electionId,Long constituencyId);
	
	public List<GenericVO> getCandidateDetailsForElection(Long candidateId, Long electionId);
	
	public List<SelectOptionVO> getCadreLevelsForCadreSearch();
	
	public List<SelectOptionVO> getCandidateInfoByNomination(Long electionId,Long nominationId);	
	public List<SelectOptionVO> getAllRelationDetails();
	
	public List<SelectOptionVO> getCadreCommitteRoles(Long levelId,Long committeeId);
	
	public List<SelectOptionVO> getCadreCommitteDetails(Long levelId);
	
	public TdpCadreBackupDetails updateRequestDetailsForBackup(List<CadreRegistrationVO> inputResponseList,String registrationType);
	
	public String getCadreImageByPreviousEnrolId(String enrolmentId,String staticContentLoc);
	
	public List<GenericVO> getBoothsForMultipleLocations(Long constituencyId, List<Long> locationIds);
	
	public String checkNFCNumberForVoterId(Long voterId);
	
	public SurveyCadreResponceVO tdpCardSenderSavingLogic(final CardSenderVO cardSenderVO);

	public String delinkNFCNumber(String cardNumber,Long voterId);

	public List<CasteDetailsVO> getAllCastes();
	public List<Long> getVoterIdByVoterCard(String voterCardId);
	public List<CadreRegisterInfo> getDistrictsByStateWiseAction(Long stateId);
	public List<CadreRegisterInfo> getConstsByStateWiseAction(Long stateId,RegistrationVO userVO);
	
	public List<CadrePrintVO> getSelectedLevelCadreDetailsBySelection(CadrePrintInputVO input);
	
	public String updateSmsJobStatus(String mobile,String jobcode,String dateTime,int status);
	
	public List<CadreRegisterInfo> getConstituenciesByStateBasedOnAccess(Long stateId,String accessLevel,Long accessValue);
	
	public List<CadreRegisterInfo> getDistrictsByStateBasedOnAccess(Long stateId,String accessLevel,Long accessValue);
	
	public String updateTabLoginUserDetails(TabRecordsStatusVO tabRecordsStatusVO);
	
	public String updateTabUserDetails(List<TabRecordsStatusVO> tabRecordsStatusVOList);
	
	public List<CadrePrintVO> getTDPCadreDetailsBySearch(CadrePrintInputVO input);
	
	public String updatePrintedCardDetails(List<CardNFCDetailsVO> inputList);
	
	public List<SinkVO> sinkMissingData(List<SinkVO> inputs);
	
	public String  saveTabUsersLoginKeyDetails(TabRecordsStatusVO recordsStatusVO);
	
	public TdpCadreVO searchTdpCadreDetailsBySearchCriteria(Long constituencyId,String name,String memberShipCardNo, String voterCardNo, String refNo, String mobileNo);
		
	public List<UserDetailsVO> getCadreSurveyUserDetails(List<UserDetailsVO> cadreSurveyUserIds);
	
	public boolean checkHasAccess(Long userId);
	
	public String updateCadreTravelDiscountDetails(final CadreTravelsVO input);
	
	public String cancellationOfTicketDetails(final CadreTravelsVO input);
	public TdpCadreVO searchTdpCadreDetailsByVoterCardNo(String voterCardNo, String isFamilyVoter);
	public SurveyCadreResponceVO saveCommitteCadreRegistration(final Long userId,final List<CadreRegistrationVO> cadreRegistrationVO,List<CadrePreviousRollesVO> cadreRoles , String registrationType);
	public List<GenericVO> getExistingCadreInfoForCommittee(String candidateName,Long constituencyId,Long panchayatId,Long boothId,String isPresentCadre,String enrollmentNo,Long areaId);
	public String saveRegistrationStatus(CadreRegistrationVO vo);
	//public CadreCommitteeMemberVO getBoothsCurrentStatus(Long constituencyId);
	
	//public ByeElectionVO getByeEleBoothsCurrentStatusSummary(Long accessValue);
	public ByeElectionVO getByeEleBoothsCurrentStatusReport(Long accessValue,Long typeId,String type);
	
	public List<CadreCommitteeMemberVO> getClustesAndDivisionNames(Long typeId);
	//public ByeElectionVO getByeEleBoothsCurrentStatusSummaryInfo(Long accessValue,String status);
	public ByeElectionVO getByeEleBoothsCurrentStatusSummaryInfo1(Long accessValue,String status,Long typeId,String type);
	public ByeElectionVO getByeEleBoothsErrorInfo();
	public ByeElectionVO getByeElelatestUpdates(Integer startIndex,Integer maxIndex);
	
	public ByeElectionVO getMessagesInfo(Integer startIndex,Integer maxIndex);
	
	public String saveMissedCallDetails(MissedCallCampaignVO vo);
	
	
	public MissedCallsDetailsVO getMissedCallDetail(String fromDateStr,String toDateStr,Long stateId);
	public List<MissedCallsDetailsVO> getMissedCallDetailByDistrict(String fromDateStr,String toDateStr,Long stateId,String task);

	public List<MissedCallsDetailsVO> missedCallDetailsForADistrict(Long districtId,String startDateString,String endDateString);
	public String updatePrintedCardInfo(final List<CardNFCDetailsVO> inputList);
	public List<CadrePrintVO> getTDPCadreDetailsForSearch(CadrePrintInputVO input);
	public List<CadrePrintVO> getTDPCadreDetailsByMemberShip(CadrePrintInputVO input);
	public List<CardPrintUserVO> getCardPrintCountForAllUsers(CardPrintUserVO inputVo);
	public List<CardPrintUserVO> getCardPrintCountByUser(CardPrintUserVO inputVo);
	public List<CadreRegistrationVO> searchCadreDetailsForFamilyDetlsUpdate(String mobileNo,String voterId,String membership);
	public ResultStatus updateCadreFamilyInfo(List<TdpCadreFamilyDetailsVO> inputList,Long userId);
	public List<SelectOptionVO> getBloodGroups();
	
	public List<TdpCadreFamilyDetailsVO> getFamilyDetailsByCadreId(Long tdpCadreId);
	public CardPrintUserVO getCadrePrintDetails(String startDate,String endDate,List<Long> enrollmentYearIds);
	public  List<CadreRegistrationVO> getCadreWithFamilyDetailsOfEachCadre(Long cadreId);
	public ResultStatus updateMobileNumberForCadre(Long tdpCadreId,String mobileNo,Long userId);
	public String sendSMS(String mobileNo,String message);
	
	public SurveyCadreResponceVO saveAfflicatedCadreRegistration(final List<CadreRegistrationVO> cadreRegistrationVOList,final String registrationType);
	public List<VoterInfoVO> getSearchDetailsCadreRegistrationRTC(Long constituencyId, String seachType, String candidateName, String voterCardId, String houseNo,Long panchayatId,Long boothId,String isPresentCadre,Integer startIndex,Integer maxIndex,Long memberTypeId);
	public List<GenericVO> getCadreMemberTypeListByYear();
	public List<TdpCadreVO> getLocationwiseCadreRegistraionDetails(List<Long> membereTypeIdsList,String searchTypeStr,String startDate,String toDate,String searchDatType);
	public AffiliatedCadreVO getCadreCountsByTdpMemberType(String searchType);
	public List<PartyMeetingWSVO> getRegistrationCadreDetails(RtcUnionInputVO inputVO);
	//public List<TdpCadreVO> getLocationwiseCadreRegistraionDetails(List<Long> membereTypeIdsList,String searchTypeStr,String startDate,String toDate);
	//public List<GenericVO> getCadreMemberTypeListByYear(Long year);
	public AffiliatedCadreVO getAllTotalCountsForAll(String searchType);
	public List<TdpCadreVO> getLocationwiseCadreRegistraionDetailsForAffliatedCadre(List<Long> membereTypeIdsList,String searchTypeStr,String startDate,String toDate);
	public ResultStatus saveRegistrationQueriesForm(final RegistrationQueriesVO regQueriesVO);
	//public PaymentGatewayVO getPaymentBasicInfoByPaymentGateWayType(Long gateWayId,String randomNo,String enrollId);
	//public ResultStatus updatePaymenntStatus(Long userId,String memberShipNo);
	public ResultStatus updatePaymenntStatus(final Long userId,final String memberShipNo,final String AuthDesc, final String moduleStr,final String subTypeStr,final String enrollmentNumber);
	public String  checkPaymentStatus(String memberShipNo);
	public String  updatePaymentStatus(Long tdpCadreId);
	public List<PartyMeetingWSVO> getRegistrationCadreDetailsSourceWise(RtcUnionInputVO inputVO);
	public List<VoterSearchVO> getVotersBySearch(Long constituencyId,Long mandalId,Long villageId,Long boothId,String name,String hNo,String voterCardNo,Long cadreSurveyUserId);
	public List<IdAndNameVO> getConstitencyWiseTehsil(Long constituencyId);
	public List<IdAndNameVO> getStateWiseDistrict(Long stateId);
	public List<IdAndNameVO> getDistrictWiseConstituency(Long districtId);
	public List<TdpCadreVO> getTdpCadresBySearch(Long cadreSurveyUserId,String membershipNo,String mobileNo,String voterId);
	 public List<IdAndNameVO> getPanchayatOrConsList(Long mandalOrMunpaId,String typeId);
	 public List<IdAndNameVO> getBoothsList(Long panchayatId);
	 public List<IdAndNameVO> getStateWiseConstituency();
	 public List<VoterSearchVO> getOnlineCadreRegistrationVoterDetails(String voterCardNo);
	 public List<VoterSearchVO> getOnliCadRegistrSearchVoteDetails(Long constituencyId,Long mandalId,Long villageId,Long boothId, String type, String typeVal);
	 public List<KeyValueVO> getStateWiseAssemblyConstituency(Long stateId);
	 public List<GISUserTrackingVO> getLatestLattitudeLangitudeOfTabUser(GISUserTrackingVO VO);
	 public ResultStatus syncCadreTabRecordsStatus(List<CadreTabRecordsStatusVO> cadreTabRecordsStatusList);
	 public List<GISUserTrackingVO> getLatestLattitudeLangitudeOfTabUserAgentDetails(Long constituencyId,String startDate,String endDate);
	 public void saveDataToHistoryTable(TdpCadre tdpCadre);
	 //public List<IdAndNameVO> getAllDistricts();
	 public List<CadreRegisterInfo> getDistrictsByState(Long stateId);
	 public  String sendSMSInTelugu(String mobileNo,String msg);
	 public List<KeyValuePairVO> getStateWiseDistrictsForUsers(Long stateId,Long userId);
	 public List<KeyValuePairVO> getConstituenciesByDistrictForUser(Long districtId,Long userId);
}

