package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCommitteeMember;

public interface ITdpCommitteeMemberDAO  extends GenericDao<TdpCommitteeMember, Long>{
	public List<Object[]> getCommitteeRolesGenderWiseDetailsByLocation(List<Long> positionIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType,String segrigatStr,Long descriptionLevelId);
	public List<Object[]> getCommitteeRoleAgerangeWiseDetailsByLocationType(List<Long> positionIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType,String segrigatStr);
	public List<Object[]> getCommitteeRoleCasteNameWiseDetailsByLocationType(List<Long> positionIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType,String segrigatStr);
	public List<Object[]> getCommitteeRoleCasteCategoryNameWiseDetailsByLocationType(List<Long> positionIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType,String segrigatStr);
	
	public List<Object[]> getRoleWiseAllocatedMembersCount(Set<Long> committeeRoleIds);
	public List<Object[]> getMembersInfo(Set<Long> committeeRoleIds);
	public List<Object[]> getMemberInfo(Long tdpCadreId);
	public List<TdpCommitteeMember> getTdpCommitteeMemberByTdpCadreId(Long tdpCadreId);
	public List<Object[]> getTdpCommitteeMemberForTdpCadreIdList(List<Long> tdpCadreIdsList);
	//public List<Object[]> getStartedCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds);
	
	//public Long getMembersCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds);
	//public List<Object[]> getMembersCountInCommitteeByLocation(String state,List<Long> levelIds,Long committeeId,Date startDate,Date endDate);
	public List<Object[]> getMembersInfoForRequest(Set<Long> committeeRoleIds);
	public List<Long> getTdpCommitteIds(Long levelId,Long locationVal,Long committeeTypeId);
	public Integer deleteCadreRole(Long Id);
	public List<Object[]> getStartedCommitteesCountInALocation(Long constituencyId);
/*	public List<Object[]> getMandalTotalCommittees(Long constituencyId,List mandalIds);
	public List<Object[]> getMuncipalTotalCommittees(Long constituencyId,List muncipalIds);
	public List<Object[]> getDivisonTotalCommittees(Long constituencyId,List divisionIds);*/
	public List<Object[]> getLocationWiseStartedCount(List<Long> locationIds,Long locationTypeId);
	public List<Object[]> basicCommitteeDetails();
	public List<Object[]> getVillageStartedCount(Long constituencyId);
	public List<Object[]> getStartedAffliCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate);
	public List<Object[]> getComitteeMembersByCommiteTypeAndLocation(Long levelId,List<Long> locationVals,Long committeeTypeId,String status);
	public List<Object[]> getComitteeMembersInfoByCommiteTypeAndLocation(Long levelId,Long locationVal,Long committeeTypeId,String status);;
	public Integer updateTdpComitte(List<Long> tdpCommitteeIds);
	public List<Object[]> membersCountDistrictWise(List<Long> levelIds, Date startDate, Date endDate, List<Long> districtIds);
	public List<Object[]> getCommitteStatusAndId(Long tdpCommitteMemberId);
	public Long getCommitteMembers(Long tdpCommitteeId);
	public List<Object[]> getCommitteeDetails(Long committeeId);
	
	public List<Object[]> membersCountConstituencyWise(List<Long> levelIds, Date startDate, Date endDate, List<Long> constiIds);
	public List<Object[]> totalMainMembersCountLocationsWise(Long levelId, Date startDate, Date endDate,List<Long> levelValues);
	
	public List<Object[]> getCommitteeMembersCountByLocationAndCommitteeType(Long levelId,List<Long> locationVals);
	public List<Object[]> getCommitteePresidentAndVicePresidentsCount(List<Long> locationIds, Long locationLevel);
	public List<Object[]> getAffiliCommMembersInfo(Set<Long> committeeRoleIds);
	public List<Object[]> getPresidentsAndVPInfoForCommittee(Long levelId,Long locationVal,Long committeeTypeId);
	public List<Object[]> getAllCommitteeMembersInfoInALoc(Long locationLvl,Long locationVal);
	public List<Object[]> getCommitteePresidentAndGS(List<Long> locationIds, Long locationLevel);
	public List<Object[]> getAllMembersInMainCommWithPresidentAndGeneralSecretaryRole(Long locationType,Long locationVal);
	
	public List<Object[]> getStartedCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList);
	public Long getMembersCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate ,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList);
	public List<Object[]> getMembersCountInCommitteeByLocation(String state,List<Long> levelIds,Long committeeId,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList);
	public List<Object[]> getStartedCommitteesMembersCountByLocation(String state,List<Long> levelIds,Long committeeId,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList);
	
	public List<Object[]> getCasteCategoryInfoForLocations(Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,String userAccessType,String segrigatStr,String searchType);
	public List<Object[]> getCadreAgerangeInfoForLocations(Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,String userAccessType,String segrigatStr);
	public List<Object[]> getCasteInfoForLocations(Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,String userAccessType, String segrigatStr,String searchType);
	public List<Object[]> membersCountMandalWise(List<Long> levelIds, Date startDate, Date endDate, List<Long> constiIds,String locationType);
	
	public List<Object[]> getCommiteeMembersDetailsByPostionsAndCommiteeLevel(List<Long> committeeLevelIds,List<Long> committeeValueList,Long committeeId,List<Long> commiteeRoleIds,List<Long> districtIds,Integer startIndex,Integer maxIndex);
	
	//public List<Object[]> getCommiteeMembersCountDetailsByPostionsAndCommiteeLevel(List<Long> committeeLevelIds,List<Long> committeeValueList,Long committeeId,List<Long> commiteeRoleIds,List<Long> districtIds,Integer startIndex,Integer maxIndex);
	
	public List<Object[]> cadreMemberDetailsForPerformance(Long locationLevelId, Long locationLevelValue);
	public List<Object[]> cadreMemberBoothDetailsForPerformance(Long locationLevelId, Long locationLevelValue);
	public Object[] getPartyPositionBycadre(Long cadreId);
	
	//public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValue(List<Long> locationLevelIdsList,List<Long> locationLevelValuesList);
	
	public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValue(Long levelId,Long locationValue,Long constituencyId);
	public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValueForVillage(Long levelId,Long tehsilId,Long constituencyId);
	public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValueForWards(Long levelId,Long localElectionBody,Long constituencyId);
	public List<Object[]> getRoleWiseAllocatedMembersCount(List<Long> cadreIds);
	public List<Object[]> getMembersInfoByTdpCadreIdsList(List<Long> tdpCadreIdsList);
	
}
