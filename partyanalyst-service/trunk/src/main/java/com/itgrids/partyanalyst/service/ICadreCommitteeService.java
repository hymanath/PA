package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itgrids.partyanalyst.dto.AccessedPageLoginTimeVO;
import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeReportVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeRolesInfoVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CommitteeApprovalVO;
import com.itgrids.partyanalyst.dto.CommitteeResultVO;
import com.itgrids.partyanalyst.dto.CommitteeSummaryVO;
import com.itgrids.partyanalyst.dto.EventCreationVO;
import com.itgrids.partyanalyst.dto.EventDocumentVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.InviteesVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO1;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.UserEventDetailsVO;
import com.itgrids.partyanalyst.dto.VO;


public interface ICadreCommitteeService {
	public String genarateOTP(Long userId, Long mobileNo);
	public String validateOTPForUser(Long userId, Long mobileNo,Long refNo, Long otpNumber);
	public List<String> getAgeRangeDetailsForCadre();
	public List<GenericVO> getAllCasteDetailsForState();
	public CadreCommitteeVO getCadreDetailsByTdpCadreId(Long tdpCadreId);
	public List<LocationWiseBoothDetailsVO> getLocationsList(Long constituencyId,String level);
	public  List<LocationWiseBoothDetailsVO> getAllAffiliatedCommittiesInALocation(Long levelId,Long levelValue);
	public LocationWiseBoothDetailsVO getCommitteeMembersInfo(Long committeeId);
	public LocationWiseBoothDetailsVO getMainCommitteeMembersInfo(Long levelId,Long levelValue);
	public List<LocationWiseBoothDetailsVO> getAllTdpCommitteeDesignations();
	public ResultStatus saveCadreCommitteDetails(Long userId,Long tdpCadreId,Long tdpCommitteeRoleId);
	public List<CadrePreviousRollesVO> getCadreEligiableRoles(Long tdpCadreId);
	public List<GenericVO> getCadsteDetailsByGroupId(Long casteGroupId);
	public List<GenericVO> getPanchayatDetailsByMandalId(Long MandalId);
	public List<Long> getBoothsInPanchayatId(Long panchayatId);
	public List<LocationWiseBoothDetailsVO> getMandalMunicCorpDetails1(Long constituencyId);
	/*public CadreCommitteeVO searchTdpCadreDetailsBySearchCriteriaForCadreCommitte(Long locationLevel,Long locationValue, String searchName,String memberShipCardNo,
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategoryId,Long fromAge,Long toAge,String houseNo,String gender);*/
	public List<SelectOptionVO> getBasicCadreCommitteesDetails();
	public List<IdNameVO> getLocationsOfCommitteeLevel(Long levelId,Long constiId);
	public List<IdNameVO> getConstituenciesOfState(Long levelId);
	public ResultStatus saveMandalLevelAffliactedElectrolInfo(Long tdpCadreId,Long tdpBasicCommitteeId);
	public ResultStatus saveMandalLevelElectrolInfo(Long tdpCadreId,List<CadrePreviousRollesVO> eligibleRoles);
	public CadreCommitteeReportVO getCommitteeDetailsByLocation(String state,List<Long> levelIds,String startDateString,String endDateString,Long userId,String accessType,Long accessValue,List<Long> enrollmentIdsList);
	
	public String checkIsVacancyForDesignation(Long tdpCommitteeRoleId);
	
	//public ResultStatus cadreCommitteeIncreasedPositionsOrChangeDesignations(final Long tdpCommitteeRoleId,final Long requestUserId,final Long currentmaxPositions,final Long requestedMaxPositions,final String type,final List<LocationWiseBoothDetailsVO> changeDesignationsList);
	public LocationWiseBoothDetailsVO getMainCommitteeMembersInfoRequest(Long levelId,Long levelValue);
	public LocationWiseBoothDetailsVO getCommitteeMembersInfoRequest(Long committeeId);

	public CadreCommitteeReportVO getTotalCommitteeDetailsByLocation(String state,Long userId,String accessType,Long accessValue,List<Long> enrollmentIdsList,String startDateStr,String endDateStr);
	
	public List<CadreCommitteeMemberVO> getCommitteeMemberDetails(Long basicCommitteeTypeId,Long locationId,Long levelId,String status,List<Long> committeeEnrollmentIdsLst,String startDate,String endDate);
	public CommitteeResultVO setCommitteConfirmation(Long basicCommitteeTypeId,Long locationId,Long levelId,List<Long> committeeEnrollmentIdsLst,String startDate,String endDate);
	public List<CadreCommitteeMemberVO> deleteCadreRole(Long tdpCommitteeMemberId,List<Long> committeeEnrollmentIdsLst,String startDate,String endDate);
	public List<CommitteeApprovalVO> getCommitteesForApproval(Long startNo, Long endNo,Long requestUserId);
	public String updateCommitteePosCount(final Long roleId, final Long maxCount, final String type, final Long increasedPosId, final Long approveCount);
	public CommitteeApprovalVO getStatusCountsOfApproval();
	
	
	public List<CommitteeSummaryVO> getSummaryDetails(String accessValue,String reqLocationType,String startDate,String endDate, List<Long> committeeEnrollmentIdsLst,List<Long>  levelIdsLsit);
	public List<LocationWiseBoothDetailsVO> getStartedCommitteesCountInALocation(String accessValue);
	public List<CommitteeSummaryVO> gettingMandalAndMuncipalAndDivisonSummary(String constituencyId,List<Long> committeeEnrollmentIdsLst,String startDate,String endDate,String reqLocationTypeStr , List<Long> levelIdsList);
	public List<LocationWiseBoothDetailsVO> getMandalMuncipalDivisonTotalCommittees(String accessValue,Map<Long,CommitteeSummaryVO> affilatedMap);
	//public List<CadreCommitteeReportVO> getMembersRangeCountByLocation(String state,List<Long> levelIds,Long committeeId,String startdateStr,String endDateStr);
	//public List<CadreCommitteeReportVO> getStartedAffliCommitteesCountByLocation(String state,List<Long> levelIds,String startdateStr,String endDateStr);
	public List<CadreCommitteeMemberVO> getCommitteeDetailsByStatus(Long basicCommitteeTypeId,String status,Long levelId,String accessValue,List<Long> committeeEnrollmentIdsLst,String startDate,String endDate);
	
	public List<CommitteeSummaryVO> getDistrictWiseCommittesSummary(String state,String startDate, String endDate,Long userId,String accessType, Long accessValue,String mandalCheck, String villageCheck,String districtCommCheck,List<Long> committeeSpanTypeIdsList );
	public ResultStatus cadreCommitteeIncreasedPositionsOrChangeDesignations(final Long tdpCommitteeRoleId,final Long requestUserId,final Long currentmaxPositions,final Long requestedMaxPositions,final String type,final List<LocationWiseBoothDetailsVO> changeDesignationsList,final Long committeeId );
	public Long gettingCommitteeIdForMainCommittee(Long levelId,Long levelValue);
	
	//public List<CommitteeSummaryVO> getConstituencyWiseCommittesSummary(String state,String startDate, String endDate);
	public List<CommitteeApprovalVO> changeDesignationRecordsForAUser(Long userId,Long startNo,Long endNo);
	public List<IdNameVO> getAllDistricts();
	public List<IdNameVO> getAllConstituencysForADistrict(Long districtId);
	public CommitteeSummaryVO getConstituencySummary(Long reprtType, Long constituencyId,Long userId,Long committeeTypeId,List<Long> enrollIdsList,String startDateStr,String endDateStr);
	public LocationWiseBoothDetailsVO getAffiliatedCommitteMembersInfo(List<Long> affiliIds);
	public List<AccessedPageLoginTimeVO> getElctoralInfoForALocation(Long locationValue);

	public List<LocationWiseBoothDetailsVO> getMandalsByConstituency(Long constituencyId );
	public List<LocationWiseBoothDetailsVO> getPanchayatWardByMandalId(String mandalId,Long constituencyId);
	public List<CadreCommitteeMemberVO> getElectrolsOfPanchayatAndWards(Long locationId,Long locationType,Long basicCommitteeTypeId);
	public List<CadreCommitteeMemberVO> getComitteeMembersInfoByCommiteTypeAndLocation(Long locationId,Long locationType,Long basicCommitteeTypeId,String status,List<Long> committeeEnrollmentIdsLst,String startDate,String endDate);
	public ResultStatus updateCandidateDesignation(final Long committeeId,final List<LocationWiseBoothDetailsVO> changeDesignationsList,final Long userId);
	public LocationWiseBoothDetailsVO getAllCommitteeMembersInfoInALoc(Long locationLvl,Long locationVal);
	
	public List<CommitteeSummaryVO> getConstituencyWiseCommittesSummary(String state,String startDate, String endDate,Long userId, String accessType,Long accessValue,String mandalCheck,String villageCheck,String reqLocationTypeStr,List<Long> committeeEnrollmentIdsLst,List<Long> levelIdsList);
	public ResultStatus  approvingChangeDesignations(final Long cadreCommitteeIncreasedPositionsId,final String approvedStatus);
	public CommitteeApprovalVO statusForChangeDesignationsApproval();
	public String userAccessTypeDetailsForDashBoard(Long userId, String accessType,Long accessValue);
	public List<CadreCommitteeReportVO> getMembersRangeCountByLocation(String state,List<Long> levelIds,Long committeeId,String startDateStr,String endDateStr,String accessType,Long accessValue,Long userId,String committeeType,List<Long> committeeSpanTypeIdsLsit);
	public List<CadreCommitteeReportVO> getStartedAffliCommitteesCountByLocation(String state,List<Long> levelIds,String startDateStr,String endDateStr,String accessType,Long accessValue,Long userId,String committeeType,List<Long> committeeSpanTypeIdsLsit);
//	public CadreCommitteeRolesInfoVO getCommitteeRoleAgeWiseDetailsByLocationType(String userAccessType,String castePercentage,Long committeeTypeId,List<Long> positionIdsList,List<Long> casteCategoryIdsList,List<Long> casteCategoryGroupIdsList,List<Long> casteIdsList,Long locationLevelId,Long userId, Long accessValue);
	public BasicVO getAccessLocationValuesByState(String accessType,Long accessValue,Long stateId,Long userId,List<Long> enrollIdsList);
	public CadreCommitteeRolesInfoVO getCommitteeRoleAgeWiseDetailsByLocationType(String userAccessType,String locationValue,Long committeeTypeId,List<Long> positionIdsList,List<Long> casteCategoryIdsList,List<Long> casteCategoryGroupIdsList, 
			List<Long> casteIdsList,Long locationLevelId,Long userId, Long accessValue,String selectedRadio,List<Long> enrollIdsList,String startDateStr,String endDateStr);
	public String getDistrictName(Long  districtId);
	public List<CommitteeSummaryVO> getCommitteeSummaryInfoByUserAccess(Long accessValue,String accessType,List<Long> committeeEnrollmentIdsLst,String startDate,String endDate,List<Long> committeeLevelIdsList,List<Long> mainOrAfflCommitteIds);
	public CadreCommitteeVO searchTdpCadreDetailsBySearchCriteriaForCadreCommitte(Long locationLevel,Long locationId, String searchName,String memberShipCardNo,
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender,int startIndex,int maxIndex,boolean isRemoved,Long enrollmentId,String searchType);
	public List<BasicVO> getAllCommittees();
	public List<BasicVO> getCommitteeRoles();
	public List<CommitteeSummaryVO> getConstituencyWiseCommittesSummaryForDistrict(String state,String startDate, String endDate,Long userId, String accessType,Long accessValue,String mandalCheck,String villageCheck,List<Long> committeeEnrollmentIdsLst);
	public List<CommitteeSummaryVO> getConstituencyWiseCommittesSummaryForMandal(String state,String startDate, String endDate,Long userId, String accessType,Long accessValue,String mandalCheck,String villageCheck,List<Long> committeeEnrollmentIdsLst);
	 public ResultStatus sendSmsForInvitees(Long userId,List<Long> mobileNoList,String message);
	// public List<TdpCadreVO> createGroupForMahanaduInvities(Long userId,Long committeeLevelId, Long committeeValue,List<CadreCommitteeMemberVO> committeeList,Long presentLocationId,String groupName,String searchType,Long stateId, Long districtId, Long constituencyId, Long mandalId,  Long panchayatId,Integer startIndex, Integer maxIndex);
	 public List<TdpCadreVO>  getEventInviteesList(Long userId,String accessLevel,String accessValue, Long stateId,List<InviteesVO> inviteesVOList,Long eventId,String actionType,String stateStr,String reportType,Integer startIndex,Integer maxIndex);
	 public List<IdNameVO> getPublicRepresenttativesList();
	 public List<IdNameVO> getPartyEventGroups(Long userId);
	 public List<IdNameVO> getPartyEvents(Long userId);
	 public ResultStatus createNewEvent(final Long userId,final  UserEventDetailsVO userEventDetailsVO,final String actionType);
	 public ResultStatus updateEventSettings(final Long userId,final  UserEventDetailsVO userEventDetailsVO,final String actionType);
	 public ResultStatus createANewUserForEvents(final Long userId,final String firstName,final String lastName,final String userName,final String password,final String mobileNo);
	 public ResultStatus assignEventForUser(final Long userId,final  UserEventDetailsVO userEventDetailsVO);
	 public EventCreationVO getPrePopulatingValuesOfEvents(Long eventId);
	 public List<CadreCommitteeMemberVO> getCommitteeMemberPerformanceDetails(Long locationLevelId, Long locationLevelValue);
	 public List<VO> getDistrictNamesIds(Long userId);
	 public List<String> getConstituencyByDistrict(Long districtId);
	 public String calculateMandalPerc(Set<Long> mandalIds,Long constituencyId,List<Long> partyIds,Long electionId);
	 public String calculateMunicPerc(Set<Long> municIds,Long constituencyId,List<Long> partyIds,Long electionId);
	 public String calculatePancPerc(Set<Long> panchayatIds,Long constituencyId,List<Long> partyIds,Long electionId);
	 public String calculateBootPerc(List<Long> boothIds,Long constituencyId,List<Long> partyIds,Long electionId);
	 public String calculateWardPerc(Set<Long> wardIds,Long constituencyId,List<Long> partyIds,Long electionId);
	 public List<GenericVO> getPanchayatDetailsByMandalIdAddingParam(Long MandalId);
	 public List<LocationWiseBoothDetailsVO> getPanchayatWardDivisionDetailsOfSubLocation(List<Long> constituencyIds, List<Long> mandals, List<Long> localBodys);
	 public void getLocationsInfo(List<Long> constituencyIds,List<Long> divisionLclIds,
				Map<Long,List<Long>> localBodiesMap,Map<Long,List<Long>> divisionIdsMap,Map<Long,List<Long>> mandalIdsMap);
	 public List<LocationWiseBoothDetailsVO> getLocationsOfSublevelConstituencyMandal(Long stateId, List<Long> districtIds, List<Long> constituencyIds, String mandalStr, Long locationLevelId);
	 public List<IdNameVO> getDistrictsOfStateWithSplitted(Long stateId);
	 public List<LocationWiseBoothDetailsVO> getConstituencyOfDistrict(Long stateId, List<Long> districtIds);
	 public List<IdNameVO> getLocationNameByLocationIds(List<Long> locationIds, Long locationLevel);
	 
	 public String getLocationName(Long LocationTypeId,Long locationValue);
	 public List<IdNameVO> getAllCadreDeleteReasons();
	 public ResultStatus saveRemovingCadreDetailsAction(Long cadreId,Long reasonId,String remark,Long userId);
	 public List<Long> getAllRemovedCadre();
	 public void getLocationNameByLocationTypeAndId(Long committeeLevelId,Long locationValue,String location);
	 public ResultStatus saveActivityDetails(final ActivityVO activityVO,final Long userId);
	 public BasicVO getActivityTypeList();
	 public List<IdNameVO> getActivityLevelsList();
	 public List<IdNameVO> getActivitiesListByTypeAndLevel(Long activityTypeId,Long  activityLevelId);
	 //public LocationWiseBoothDetailsVO getActivityLocationDetails(String isChecked,Long activityScopeId,Long activityLevelId,String searchBy,Long locationId,
		//	 String searchDateStr,String searchEndDateStr,Long constituencyId);
	 public List<LocationWiseBoothDetailsVO> getMandalMunicCorpDetailsNew(Long constituencyId);
	 public List<LocationWiseBoothDetailsVO> getPanchayatWardDivisionDetailsNew(Long constituencyId);
	 public CadreCommitteeMemberVO getAllCommitteeMembInfoInLocation(Long activityLevelId,List<Long> constituencyIds,List<LocationWiseBoothDetailsVO> mandalList,
				List<LocationWiseBoothDetailsVO> panchayatList);
	 public List<CadreCommitteeMemberVO> getComitteeMembersInfoInActivity(Long locationId,Long locationType,Long basicCommitteeTypeId,Long constituencyId);
	 public List<ActivityVO> asemblyConstWiseActivities(String startDateString,String endDateString,Long activityScopeId,Long activityLevelId,String accessType,Long accessValue,Long stateId,Long userId);
	 public List<LocationWiseBoothDetailsVO> getMandalMunicCorpDetailsByConstituencyList(List<Long> constituencyIds);
	 public List<EventDocumentVO> getEventDocumentsForLocation(EventDocumentVO inputVo,Long activityMemberId,Long stateId,Long userTypeId);
	 public List<BasicVO> getDistrictsByUserId(Long userId,String isAdmin,String accessType,Long accessValue);
	 public BasicVO getLocationsHierarchyForEvent(EventDocumentVO inputVo,String type);
	 public List<BasicVO> getAvailableDates(EventDocumentVO inputVo,Long activityMemberId,Long stateId,Long userTypeId);
	 public List<ActivityVO> getDistrictWiseActivities(String startDateString,String endDateString,Long activityScopeId,Long activityLevelId,String accessType,Long accessValue,Long stateId,Long userId);
	 public List<IdNameVO> getAllCastes();
	 public ResultStatus updateMobileNumberAndCasteForCadre(Long cadreId,String mobileNo,Long casteId,Long userId);
	 public List<LocationWiseBoothDetailsVO> getConstituencyByDistrictId(Long districtId);
	 public List<BasicVO> getAllCommitteesForLevelId(Long levelId);
	 
	 public List<IdNameVO> getStatesForLocationLevel(String accessType,Long accessValue);
	 public List<LocationWiseBoothDetailsVO> getMandalMunicCorpDetailsOfConstituencies(List<Long> constituencyIds,String type);
	 public LocationWiseBoothDetailsVO1 getActivityLocationDetails(String isChecked,Long activityScopeId,Long activityLevelId,String searchBy,Long locationId,
			 String searchStartDateStr,String searchEndDateStr,Long constituencyId,Long optionId,Long questionId);
	 public LocationWiseBoothDetailsVO1 getBetweenDatesOfActivityScope(Long activityScopeId);
	 public LocationWiseBoothDetailsVO1 getActivityLocationDetailsNew(String isChecked,Long activityScopeId,Long activityLevelId,String searchBy,Long locationId,
			 String searchStartDateStr,String searchEndDateStr,Long constituencyId,Long optionId,Long questionId,List<String> datesList);
	 public List<LocationWiseBoothDetailsVO> getSubLevelForConstituency(Long stateId, List<Long> districtIds, List<Long> constituencyIds, Long locationLevelId);
	 public List<CadreCommitteeVO> getFieldMonitoringMapReportDetails(Long constitunecyId,Long fieldUserId);
	 public List<LocationWiseBoothDetailsVO> getPanchayatList(Long tehsilId);
	 public List<LocationWiseBoothDetailsVO> getMandalMunicCorpDetails(Long constituencyId);
	 public List<CadreCommitteeVO> updateSearchTdpCadreDetailsBySearchCriteriaForCadreCommitte(String searchType,String searchValue);
	 public LocationWiseBoothDetailsVO getCommitteeMembersInfoNEW(Long committeeId);
	 public List<CadreCommitteeVO> getCadreEnrollmentYears();
	 public List<CadreCommitteeVO> getCommitteeDetailsByEnrollementId(List<Long> enrollYearIds);
	 public LocationWiseBoothDetailsVO getCommitteeMembersAvailableInfo1(Long levelId,Long levelValue,Long committeeEnrollmentId,String startDate,String endDate,Long basicCommitteetypeId);
	 public List<LocationWiseBoothDetailsVO> getCommitteeCreationDetails(Long committeeTypeId,List<Long> committeeLevlIdsList,List<Long> designationsList,Long locationLvlId,List<Long> loctnLevlValues,
				List<Long> committeeEnrollmntIds,Long stateId,String searchType);
	 public List<BasicVO> userWiseDetailsForDashBoard(Long userId, String accessType, String accessValue);
	 public List<LocationWiseBoothDetailsVO> getTdpCommitteePanchayatWardByMandal(String mandalId,Long constituencyId,Long enrollmentId);
	 public List<IdNameVO> getConstituenciesByActivityId(Long activityId);
	 public List<IdNameVO> getPanchayatBymandalId(Long constituencyId,Long activityScopeId);
	 public List<IdNameVO> getMandalsByConstituencyId(Long constituencyId,Long activityScopeId);
	 public List<LocationWiseBoothDetailsVO> getActivityLocationDetails(Long levelId,Long locationId,Long activityScopeId,String searchType,String checkedValue);
	 public List<IdNameVO> getDistrictsByActivityId(Long activityId);
	 public String saveActivityLocationDetails(final ActivityVO activityVO,final Long userId);
	 public List<ActivityVO> asemblyConstWiseActivitiesCount(Long activityScopeId,Long activityLevelId,String accessType,Long accessValue,Long stateId,Long userId);
	 public List<ActivityVO> getDistrictWiseActivitiesCount(Long activityScopeId,Long activityLevelId,String accessType,Long accessValue,Long stateId,Long userId);
	 
	 public String updateCommitteeMemberDesignationByCadreId(final Long tdpCadreId,final Long userId);
	 public List<TdpCadreVO>  getPartyLeadersDeatails(Long userId,Long levelId,List<Long> locationIdsList,Long representativeTypeId,List<Long> designationIdsList,
				List<Long> committeeLevelIdsList,List<Long> enrollmentIdsList, List<Long> committeeTypeIdsList, Long stateId, int firstIndex,int maxIndex,String reportType);

	 public List<IdNameVO> getCommitteeLevelDetils();
	 public List<IdNameVO> getCommitteeTypeDetils();
	 public ResultStatus saveElectionBoothCommitteeDetails(Long userId,Long boothId,Long tdpCadreId,Long boothInchrgRoleId,List<Long> boothEnrollmentYrIds,String isOtherRange);
	 public List<CadreCommitteeVO> getBoothsForMandals(Long mandalId,Long constituencyId);
	 
	 public List<IdNameVO> getMultplConstituencesByDistctIds(List<Long> districtIds);
	 public List<LocationWiseBoothDetailsVO> getMultiMandalsByConstituencyLst(List<Long> constituencyIds);
	 public List<LocationWiseBoothDetailsVO> getMultiMandalMunicCorpDetailsLst(List<Long> constituencyIds);
	 public  List<LocationWiseBoothDetailsVO> getMultplePanchayatWardByMandalIdsLst(List<Long> constituencyIds,List<Long> mandalIds);
	 public CadreCommitteeVO getCadreDetailsForBothsCommittee(Long locationLevel,Long locationId, String searchName,String memberShipCardNo,
				String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender,int startIndex,int maxIndex,boolean isRemoved,Long enrollmentId,String searchType);
	 public ResultStatus removeMbrFromCurentLocation(Long userId,Long tdpCadreId);
	 public LocationWiseBoothDetailsVO getTotalBoothsCountByConstituenctIds(Long constituencyId);
	 public List<LocationWiseBoothDetailsVO> getTdpCommitteeMunicipalityByWards(String mandalId,Long constituencyId,Long enrollmentId);
	 public CadreCommitteeVO getCadreVoterBthSerilNo(Long locationId,String houseNo,String gender);
	 public CadreCommitteeVO getSerialNoAvailbleCadreRangeWise(Long mandalId, Long boothId,String range,String gender);
	 public CadreCommitteeVO getCommitteeCountDetailsByLevelId(Long constituencyId,List<Long> levelIds,List<Long> levelValues,List<Long> basicCommitteeIds,List<Long> cmiteEnrlmntYearIds);
}
