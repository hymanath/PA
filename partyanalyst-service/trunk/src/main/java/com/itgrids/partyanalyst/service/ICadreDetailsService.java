package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.BenefitCandidateVO;
import com.itgrids.partyanalyst.dto.BenefitVO;
import com.itgrids.partyanalyst.dto.CadreBasicPerformaceVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreDetailsVO;
import com.itgrids.partyanalyst.dto.CadreHealthDetailsVO;
import com.itgrids.partyanalyst.dto.CadreLocationVO;
import com.itgrids.partyanalyst.dto.CadreOverviewVO;
import com.itgrids.partyanalyst.dto.CadreReportVO;
import com.itgrids.partyanalyst.dto.CadreStatsVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.ComplaintStatusCountVO;
import com.itgrids.partyanalyst.dto.GrievanceAmountVO;
import com.itgrids.partyanalyst.dto.GrievanceDetailsVO;
import com.itgrids.partyanalyst.dto.GrievanceReportVO;
import com.itgrids.partyanalyst.dto.GrievanceSimpleVO;
import com.itgrids.partyanalyst.dto.IVRResponseVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.ImportantLeadersVO;
import com.itgrids.partyanalyst.dto.IvrOptionsVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.dto.MobileDetailsVO;
import com.itgrids.partyanalyst.dto.NtrTrustStudentVO;
import com.itgrids.partyanalyst.dto.QuestionAnswerVO;
import com.itgrids.partyanalyst.dto.RegisteredMembershipCountVO;
import com.itgrids.partyanalyst.dto.ReportVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TdpCadreFamilyDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.VerifierVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.WebServiceResultVO;
import com.itgrids.partyanalyst.model.UserAddress;

public interface ICadreDetailsService {
	/*public TdpCadreVO searchTdpCadreDetailsBySearchCriteriaForCommitte(Long locationLevel,Long locationValue, String searchName,String memberShipCardNo,
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategoryId,Long fromAge,Long toAge,String houseNo,String gender);*/
	public TdpCadreVO tdpCadreCastewiseCountDetailsBySearchCriteriaForCommitte(Long locationLevel,Long locationValue, String searchName,String memberShipCardNo, 
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender);
	public TdpCadreVO searchTdpCadreDetailsBySearchCriteriaForCommitte(Long locationLevel,Long locationValue, String searchName,String memberShipCardNo, 
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender,int startIndex,int maxIndex,boolean isRemoved,Long enrollmentId,String searchType);
	public TdpCadreVO searchTdpCadreDetailsBySearchCriteriaForCommitteForWebServiceCalls(Long locationLevel,Long locationValue, String searchName,
			String memberShipCardNo,String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,
			 Long fromAge,Long toAge,String houseNo,String gender,int startIndex,int maxIndex,boolean isRemoved,Long enrollmentId);
	public CadreCommitteeMemberVO cadreFormalDetailedInformation(Long cadreId,Long enrollmentYear,Long memberTypeId);
	public CadreCommitteeMemberVO complaintDetailsOfCadre(Long cadreId,String membershipId);
	public List<CadreCommitteeMemberVO> getEventDetailsOfCadre(Long cadreId);
	public VerifierVO getTdpCadreSurveyDetails(Long cadreId,Long surveyId,String searchTypeStr,Long boothId,String isPriority,String voterCardNo,Long constituencyId,String constiTypeStr);
	public List<CandidateDetailsVO>  getCandidateElectDetatails(Long cadreId);
	
	public RegisteredMembershipCountVO getTotalMemberShipRegistrationsInCadreLocation(Long cadreId,Long pcId,String pcType,Long yearId,Long publicationId,Long boothId);
	
	public List<RegisteredMembershipCountVO> getElectionPerformanceInCadreLocation(Long tdpCadreId,String voterCardNo);
	
	public List<GrievanceAmountVO> getApprovedFinancialSupprotForCadre(Long tdpCadreId);
	
	public List<TdpCadreFamilyDetailsVO> getCadreFamilyDetails(Long tdpCadreId);
	
	public WebServiceResultVO getCandidateAndLocationSummaryNews(String startDate,String endDate,String locationType,Long locationId,Long candidateId);
	
	public Long getCadreIdByMembershipId(String memberShipNo,Long constituencyId);
	
	public ComplaintStatusCountVO getCategoryWiseStatusCount(Long tdpCadreId);
	
	public List<CommitteeBasicVO> getLocationwiseCommitteesCount(String locationType,Long tdpCadreId,String electionType,Long locationId,List<Long> committeeEnrollmentYrIds);
	
	public VerifierVO getDeathsAndHospitalizationDetails(Long panchayatId,Long mandalId,Long constituencyId,Long parliamentId,Long districtId);
	
	public Long getTdpCadreIdBymembershipId(String membershipId);
	
	public CadreCommitteeMemberVO getCadresDetailsOfDeathsAndHospitalization(Long locationId,String locationType,Long insuranceTypeId);
	public CadreOverviewVO getTdpcadreDetailsByTdpCadreId(Long tdpCadreId,String houseNo, String voterCardNo);
	public BasicVO getParticipatedConstituency(Long tdpCadreId);
	public List<QuestionAnswerVO> getCandidateAndConstituencySurveyResult(Long candidateId,Long constituencyId,Long surveyId);
	public NtrTrustStudentVO getNtrTrustStudentDetailsInstitutionWise(List<Long> tdpCadreIds);
	public List<NtrTrustStudentVO> getStudentFormalDetailsByCadre(List<Long> familyCadreIds,Long institutionId);
	 
	public IVRResponseVO getIVRSummaryByTdpCadreId(Long tdpCadreId);
	public List<IVRResponseVO> getTotalIVRDetailsByTdpCadreId(Long tdpCadreId,int startIndex,int maxIndex);
	public CadreOverviewVO getVoterDetailsByVoterIdCardNum(String voterCardNo,String familyVoterCardNo,String memberType,Long memberTypeId);
	public List<IvrOptionsVO> getIvrSurveyInfoByTdpCadreId(Long tdpCadreId,Long entityTypeId,String searchType);
	public List<IvrOptionsVO> getTypeWiseIvrDetailsOFCadre(Long tdpCadreId);
	public String getVoterImageUrlByVoterId(Long voterId);
	public List<LocationVO> getCheckCandidateCadreDtls(Long tdpCadreId);
	public List<IdNameVO> getTrainingCampAttendenceInfoInCadreLocation(Long boothId,Long panchayatId,Long wardId,Long mandalId,Long lebId,Long constituencyId,Long parliamentId,Long districtId);
	public List<GrievanceDetailsVO> getDeathAndHospitalizationDetails(Long panchayatId,Long mandalId,Long lebId,Long constituencyId,Long parliamentId,Long districtId);
	//public List<IdNameVO> getDeathsAndHospitalizationStatusWiseDetails(Long locationValue,String searchType,String issueType);
	public VerifierVO getTdpCadreIvrSurveyDetails(Long cadreId);
	public VerifierVO getCandateParicipatedSurveyCount(Long cadreId);
	
	public List<GrievanceDetailsVO> getGrievanceStatusByTypeOfIssueLocation(Long districtId,Long assemblyId,Long parliamentId);
	public GrievanceDetailsVO getGrievanceStatusByTypeOfIssueAndCompleteStatusDetails(Long districtId,Long assemblyId,Long parliamentId);
	public GrievanceDetailsVO getDeathsAndHospitalizationStatusWiseDetailsInCadreLocation(Long panchayatId,Long mandalId,Long lebId,Long constituencyId,Long parliamentId,Long districtId);
	public List<GrievanceDetailsVO> getComplaintsDetailsByLocationAndStatus(Long locationId,String locationType,Long insuranceStatId,String issueType);
	public VerifierVO getSurveysOnCandidateCount(Long candidateId,Long cadreId);
	public List<CadreDetailsVO> getAppointmentsUserDetails(List<Long> appointmentUserIds, Long tdpcadreId);
	public List<QuestionAnswerVO> getSurveysOnCandidateDetails(Long candidateId,Long cadreId);
	//public List<GrievanceDetailsVO> getAllStatusDetailsForComplaint(Long complaintId);
		public GrievanceSimpleVO getAllStatusDetailsByComplaint(Long complaintId);
		public GrievanceSimpleVO getStatusTrackingDetailsOfInsuranceByComplaint(Long complaintId);
		public List<GrievanceDetailsVO> getComplaintsDetailsForGrievanceByLocationAndStatus(Long locationId,String locationType,String status,String issueType);
	public VerifierVO getIVRSurveysOnCandidateAreaCount(Long districtId);
	public VerifierVO getIVRSurveysOnCandidateAreaDetails(Long districtId,Long constiId,Long parliamentId,Long boothId);
	public List<GrievanceSimpleVO> getApprovedAmountDetailsByLocation(Long assemblyId,Long parliamentId,Long districtId);
	public List<GrievanceSimpleVO> getApprovedAmountDetailsForGovtAndWilfareByLocation(Long assemblyId,Long parliamentId,Long districtId);
	public List<GrievanceDetailsVO> getGrievanceBenifitsComplaintsInfoByLocation(Long locationId,String locationType,String typeOfIssue,String otherBenifit);
	public List<IdNameVO> getEventAttendanceOfCadre(Long cadreId,Long eventId);
	public List<ActivityVO> getCandateActivityAttendance(Long cadreId,Long activityLevelId,Long panchayatId,Long mandalId,Long lebId,Long  assemblyId,Long districtId,Long stateId,Long participatedAssemblyId,String activeCode);
	public MobileDetailsVO getMobileNumberDetailsByTdpCadre(Long tdpCadreId);
	public ResultStatus saveNewPublicRepresentativeDetails(final ImportantLeadersVO importantLeadersVO);
	public List<IdNameVO> getAllPublicRepresentatives();
	public List<IdNameVO> getAllImportantLeadersTypes();
	public List<IdNameVO> getVillagesInMandal(Long mandalId);
	public List<IdNameVO> getTehsilListByConstituency(Long constituencyId);
	public List<IdNameVO> getConstituenciesByDistrictId(Long districtId);
	public IdNameVO getLocations(Long importantLeadersTypeId,Long districtId,Long constituencyId,Long mandalId,Long lebId,Long villageId,Long wardId);
	public List<MahanaduEventVO> getCadreLocationWiseEventAttendeeCounts(Long eventId,String startDate,String endDate,Long locationId,String locationType,String searchType);
	public BasicVO getStartDateAndEndDate(Long eventId);
	public List<IdNameVO> getMainEvents();
	public Long getTotalVotersInALocation(Long locationId,String locationType,Long publicationId,Long constituencyId);
	public IdNameVO getImpCandAndPublRepresentativeDetailsByLocation(Long tehsilId,String searchType);
	public List<BasicVO> getcadreNotesInformationDetails(Long tdpCadreId,Integer startIndex,Integer maxIndex,Long userId);
	public ResultStatus saveCadreNotesInformationDetails(Long tdpCadreId,String notes,Long userId,Long primaryTdpCadrenotesId);
	public String deleteCadreNotesData(Long cadreNotes);
	public ResultStatus updateCadreNotesInfrmationAllDetails(Long notesId,String notes,Long UserId);
	public List<IdNameVO> getRegionScopes();
	public String saveImportantLeadersType(final String position,final Long levelId,final Long userId);
	public List<CadreReportVO> getCadreReportDetails(Long cadreId);
	public UserAddress saveUserAddressByLevelIdAndLevelValue(Long levelId,Long levelValue);
	public List<GrievanceReportVO> getGrievancePDFReport(String membershipId);
	public List<ReportVO> getNominatedPostReportFiles(Long tdpCadreId);
	//public String checkPositionExists(String position);
	public CadreLocationVO getCadreBasicLocationDetails(Long tdpCadreId);
	public List<CadreReportVO> getCadreHealthReport(Long tdpCadreId);
	public VoterCastInfoVO getParliamentAndDistrictwiseCasteInformation(String type,Long locationId,Long publicationDateId,Long userId);
	public List<VoterCastInfoVO> getCastNGenderWiseVotersCountByPublIdInALocFromIntermedTable(Long userId,Long reportLvlId,Long levelValue,Long publicationDateId,List<Long> assemblyIdsList);
	public List<SelectOptionVO> getTotalBAsicCastInfoLst(Long userId,Long publicationDateId,List<Long> assemblyIdsList);
	public List<SelectOptionVO> getVolunteerCadreDetilasInformation(Long cadreId);
	
	public String getMemberShipNumberByVoterNumberOrMobileNo(String voterCardNo,String mobileNo);
	public BenefitVO getBenefitDetailsAlongFamily(Long tdpCadreId,List<Long> famillyCadreIds);
	public BenefitVO getOwnAndParticipatedConstituenciesBenefitDetails(Long constId,Long pConstId);
	public List<BenefitVO> getLocalityBasedBenefitSchemesDetails(Long cadreId);
	public List<BenefitCandidateVO> getBenefitSchemesMembersDetails(Long locationLevelId,Long benefitId,Integer minValue,Integer maxValue);
	public List<BenefitVO> getAllConstBenefitDetailsForADist(Long distId);
	public Map<Long,CadreBasicPerformaceVO> getCadreCasteDetailsByTdpCadreIds(List<Long> tdpCadreIds);
	public Map<Long,List<CadreStatsVO>> getElectionPerformanceDetailsForCadreLocations(List<Long> tdpCadreIds);
	public Map<Long,List<CadreStatsVO>> getTotalMemberShipRegsInCadresLocations(List<Long> tdpCadreIds);
	
	public List<QuestionAnswerVO> getSurveysOnCandidateDetailsNew(Long candidateId,Long cadreId,Long stateId,Long districtId,Long constituencyId,
			Long mandalId,Long lebId,Long panchayatId,Long wardId);
	public String calculatePercentage(Long totalVoters,Long count);
	public Long saveUserAddressDetails(Long locationScopeId, Long locationValue);
	public Long kaizalaLocationAddressIdSaving(final Long locationScopeId, final Long locationValue);
	public Long kaizalaCommitteeLevelAddressSaving(final Long locationScopeId, final Long locationValue);
	
	public List<IdAndNameVO> getTdpCadreHealthDetailsByCadre(Long tdpCadreId);
	public List<QuestionAnswerVO> getSurveyQuestionWithMarksDetailsByTDpCadreId(Long cadreId);
	public List<CadreHealthDetailsVO> getTdpCadreHealthDetailsByCadreIds(Long tdpCadreId);
	public List<QuestionAnswerVO> getSurveyQuestionDetails(Long cadreId);
}
