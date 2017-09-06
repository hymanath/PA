package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCommitteeMember;

public interface ITdpCommitteeMemberDAO  extends GenericDao<TdpCommitteeMember, Long>{
	public List<Object[]> getCommitteeRolesGenderWiseDetailsByLocation(List<Long> positionIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType,String segrigatStr,Long descriptionLevelId,List<Long> enrollIdsList,Date startDate,Date endDate);
	public List<Object[]> getCommitteeRoleAgerangeWiseDetailsByLocationType(List<Long> positionIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType,String segrigatStr,List<Long> enrollIdsList,Date startDate,Date endDate);
	public List<Object[]> getCommitteeRoleCasteNameWiseDetailsByLocationType(List<Long> positionIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType,String segrigatStr,List<Long> enrollIdsList,Date startDate,Date endDate);
	public List<Object[]> getCommitteeRoleCasteCategoryNameWiseDetailsByLocationType(List<Long> positionIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType,String segrigatStr,List<Long> enrollIdsList,Date startDate,Date endDate);
	
	public List<Object[]> getRoleWiseAllocatedMembersCount(Set<Long> committeeRoleIds);
	public List<Object[]> getMembersInfo(Set<Long> committeeRoleIds);
	public List<Object[]> getMemberInfo(Long tdpCadreId);
	public List<TdpCommitteeMember> getTdpCommitteeMemberByTdpCadreId(Long tdpCadreId);
	public List<Object[]> getTdpCommitteeMemberForTdpCadreIdList(List<Long> tdpCadreIdsList);
	//public List<Object[]> getStartedCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds);
	
	//public Long getMembersCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds);
	//public List<Object[]> getMembersCountInCommitteeByLocation(String state,List<Long> levelIds,Long committeeId,Date startDate,Date endDate);
	public List<Object[]> getMembersInfoForRequest(Set<Long> committeeRoleIds);
	public List<Long> getTdpCommitteIds(Long levelId,Long locationVal,Long committeeTypeId,List<Long> committeeEnrollmentIdsLst,Date stDate,Date edDate);
	public Integer deleteCadreRole(Long Id,List<Long> committeeEnrollmentIdsLst,Date stDate, Date edDate);
	public List<Object[]> getStartedCommitteesCountInALocation(Long constituencyId);
/*	public List<Object[]> getMandalTotalCommittees(Long constituencyId,List mandalIds);
	public List<Object[]> getMuncipalTotalCommittees(Long constituencyId,List muncipalIds);
	public List<Object[]> getDivisonTotalCommittees(Long constituencyId,List divisionIds);*/
	public List<Object[]> getLocationWiseStartedCount(List<Long> locationIds,Long locationTypeId);
	public List<Object[]> basicCommitteeDetails();
	public List<Object[]> getVillageStartedCount(Long constituencyId);
	public List<Object[]> getStartedAffliCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate);
	public List<Object[]> getComitteeMembersByCommiteTypeAndLocation(Long levelId,List<Long> locationVals,Long committeeTypeId,String committeeMemberStatus,List<Long> committeeEnrollmentIdsLst,Date stDate,Date edDate);
	public List<Object[]> getComitteeMembersInfoByCommiteTypeAndLocation(Long levelId,Long locationVal,Long committeeTypeId,String status,List<Long> committeeEnrollmentIdsLst,Date startDate,Date endDate);
	public Integer updateTdpComitte(List<Long> tdpCommitteeIds);
	public List<Object[]> membersCountDistrictWise(List<Long> levelIds, Date startDate, Date endDate, List<Long> districtIds,List<Long> committeeSpanTypeIdsList);
	public List<Object[]> getCommitteStatusAndId(Long tdpCommitteMemberId,List<Long> committeeEnrollmentIdsLst,Date stDate,Date edDate);
	public Long getCommitteMembers(Long tdpCommitteeId);
	public List<Object[]> getCommitteeDetails(Long committeeId);
	
	public List<Object[]> membersCountConstituencyWise(List<Long> levelIds, Date startDate, Date endDate, List<Long> constiIds,String reqLocationTypeStr, List<Long>  committeeEnrollmentIdsLst);
	public List<Object[]> totalMainMembersCountLocationsWise(Long levelId, Date startDate, Date endDate,List<Long> levelValues,String reqLocationTypeStr,List<Long> committeeEnrollmentIdsLst,List<Long>levelIdsList);
	public List<Object[]> totalMainMembersCountLocationsWise1(Long levelId, Date startDate, Date endDate,List<Long> levelValues,String reqLocationTypeStr,List<Long> committeeEnrollmentIdsLst,List<Long>levelIdsList);
	
	public List<Object[]> getCommitteeMembersCountByLocationAndCommitteeType(Long levelId,List<Long> locationVals,Long committeeTypeId,List<Long> enrollIdsList,Date startDate,Date endDate);
	public List<Object[]> getCommitteePresidentAndVicePresidentsCount(List<Long> locationIds, Long locationLevel,Long committeeTypeId,List<Long> enrollIdsList,Date startDate,Date endDate);
	public List<Object[]> getAffiliCommMembersInfo(Set<Long> committeeRoleIds);
	public List<Object[]> getPresidentsAndVPInfoForCommittee(Long levelId,Long locationVal,Long committeeTypeId);
	public List<Object[]> getAllCommitteeMembersInfoInALoc(Long locationLvl,Long locationVal);
	public List<Object[]> getCommitteePresidentAndGS(List<Long> locationIds, Long locationLevel,Long committeeTypeId,List<Long> enrollIdsList,Date startDate,Date endDate);
	public List<Object[]> getAllMembersInMainCommWithPresidentAndGeneralSecretaryRole(Long locationType,Long locationVal,String status);
	
	public List<Object[]> getStartedCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList,List<Long> enrollmentIdsList);
	public Long getMembersCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate ,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList ,List<Long> enrollmentIdsList);
	public List<Object[]> getMembersCountInCommitteeByLocation(String state,List<Long> levelIds,Long committeeId,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList,List<Long> committeeSpanTypeIdsLsit);
	public List<Object[]> getStartedCommitteesMembersCountByLocation(String stateId,List<Long> levelIds,Long committeeId,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList,List<Long> committeeSpanTypeIdsLsit);
	
	public List<Object[]> getCasteCategoryInfoForLocations(Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,String userAccessType,String segrigatStr,String searchType,List<Long> enrollIdsList);
	public List<Object[]> getCadreAgerangeInfoForLocations(Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,String userAccessType,String segrigatStr,List<Long> enrollIdsList);
	public List<Object[]> getCasteInfoForLocations(Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,String userAccessType, String segrigatStr,String searchType,List<Long> enrollIdsList);
	public List<Object[]> membersCountMandalWise(List<Long> levelIds, Date startDate, Date endDate, List<Long> constiIds,String locationType);
	
	public List<Object[]> getCommiteeMembersDetailsByPostionsAndCommiteeLevel(List<Long> committeeLevelIds,List<Long> committeeValueList,Long committeeId,List<Long> commiteeRoleIds,List<Long> districtIds,Integer startIndex,Integer maxIndex);
	
	//public List<Object[]> getCommiteeMembersCountDetailsByPostionsAndCommiteeLevel(List<Long> committeeLevelIds,List<Long> committeeValueList,Long committeeId,List<Long> commiteeRoleIds,List<Long> districtIds,Integer startIndex,Integer maxIndex);
	
	public List<Object[]> cadreMemberDetailsForPerformance(Long locationLevelId, Long locationLevelValue);
	public List<Object[]> cadreMemberBoothDetailsForPerformance(Long locationLevelId, Long locationLevelValue);
	public  List<Object[]> getPartyPositionBycadre(Long cadreId);
	
	//public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValue(List<Long> locationLevelIdsList,List<Long> locationLevelValuesList);
	
	public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValue(Long levelId,Long locationValue,Long constituencyId,List<Long> committeeEnrollmentYrIds);
	public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValueForVillage(Long levelId,Long tehsilId,Long constituencyId,List<Long> committeeEnrollmentYrIds);
	public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValueForWards(Long levelId,Long localElectionBody,Long constituencyId,List<Long> committeeEnrollmentYrIds);
	public List<Object[]> getRoleWiseAllocatedMembersCount(List<Long> cadreIds);
	public List<Object[]> getMembersInfoByTdpCadreIdsList(List<Long> tdpCadreIdsList);
	public List<Object[]> getMembersInfoByTdpCadreIdsList1(List<Long> tdpCadreIdsList);
	public List<Object[]> getTdpCommitteeMemberPosition(Long cadreId);
	public List<Object[]> getPartyPositionsBycadreIdsList(List<Long> cadreIdsList);
	public List<Object[]> getAllCommitteeMembInfoInLocation(Long locationLvl,List<Long> locationVal);
	public List<Object[]> getComitteeMembersInfoInActivity(Long levelId,Long locationVal,Long committeeTypeId);
	public List<Object[]> getCommitteeMemberDetailsByPositionsForCentral(List<Long> committeeLevelIds,List<Long> commiteeRoleIds);
	public List<Object[]> getDesignationsForCadreCommittee(List<Long> tdpCadreIds);
	public List<Object[]> getDesignationsForPublicRepresentative(List<Long> tdpCadreIds);
	
	
	public List<Object[]> getTotalEligibleMembersForTrainingCampProgram(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId);
	public List<Object[]> getLevelWiseTotalEligibleMembersForTrainingCampProgram(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId);
	public List<Object[]> getTotalEligibleMembersForTrainingCampProgramByDistrict(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId, String status, Long distId);
	public List<Object[]> getUserWiseTotalEligibleMembersForTrainingCampProgram(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId);
	public List<Object[]> getTotalEligibleMembersForTrainingCampProgramByLocationType(Long userAccessLevelId,List<Long> userAccessLevelValues,String locationType,Long stateId);
	
	
	public List<Object[]> getTotalEligibleMembersForTrainingCampProgramByUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId, String status, Long locationId,String locationType,Long userType,String levelType,List<Long> trainingProgramIds);
	public List<Object[]> getFinalizedMembersInfoForCommitteeRoleIds(Set<Long> committeeRoleIds);
	public List<Object[]> getRoleWiseProposedAndFinalizedMembersCounts(Set<Long> committeeRoleIds);
	public List<Object[]> getAllCommitteesMembersInfoInALocByStatus(Long locationLvl,Long locationVal,String committeeMemberStatus);
	public Long getCandiCountForACommitteeRoleByStatus(Long committeeRoleId , String committeeMemberStatus);
	public List<Object[]> getStatusWiseCandiCountForACommitteeRole(Long committeeRoleId);
	public List<Object[]> getTdpCommitteeMemberForTdpCadreIdList(List<Long> tdpCadreIdsList , Long committeeEnrollmentId);
	public List<Object[]> getCommitteeCreationDetails(Long committeeTypeId,List<Long> committeeLevlIdsList,
			List<Long> designationsList,Long locationLvlId,List<Long> loctnLevlValue,List<Long> committeeEnrollmntIds,Long stateId,String searchType);
	public List<Object[]> getProposedAndFinalyzedCount(Long committeeTypeId,List<Long> committeeLevlIdsList,
			List<Long> designationsList,Long locationLvlId,List<Long> loctnLevlValue,List<Long> committeeEnrollmntIds,Long stateId,String searchType);
	public List<Object[]> getRoleWiseCommitteeMembersCount(Long tdpCommitteeId);
	public List<Object[]> getActiveMemberDetailsByCadreId(Long tdpCadreId);
	public List<Object[]> getFinilizedCommittesByRole(Long constituencyId,List<Long> levelIds,List<Long> levelValues,List<Long> basicCommitteeIds,List<Long> cmiteEnrlmntYearIds);
}
