package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.model.TdpCommittee;

public interface ITdpCommitteeDAO  extends GenericDao<TdpCommittee, Long>{
	public List<Object[]> getAllAffiliatedCommittiesInALocation(Long levelId,Long levelValue);
	public List<Long> getMainCommittiesInALocation(Long levelId,Long levelValue,List<Long> enrollmentIds,Date startDate,Date endDate);
	public List<Long> getTdpCommittee(Long tdpBasicCommitteeId,Long tdpCommitteeLevelId,Long tdpCommitteeLevelValue);
	//public Long getTotalCommitteesCountByLocation(String state,List<Long> levelIds,List<Long> districtIds);
	
	public List<Object[]> getTotalCommitteesPanchayatLevel(Long constituencyId);
	/*public List<Object[]> mandalWiseList(Long constituencyId,List mandalIds);
	public List<Object[]> muncipalList(Long constituencyId,List muncipalIds);
	public List<Object[]> divisionsList(Long constituencyId,List divisionIds);*/
	public List<Object[]> getLocationWiseDetails(List<Long> locationValues,Long locationTypeId);
	public List<Object[]> getLocationWiseVillageDetails(Long constituencyId,String reqLocationType,Date startDate,Date endDate, 
			 List<Long> committeeEnrollmentIdsLst,List<Long> levelIdsLsit);
	//public List<Object[]> getTotalCompletedCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds);
	public List<Object[]> getLocationWiseVillageStartedDetails(Long constituencyId,String reqLocationType,Date startDate,Date endDate, 
			 List<Long> committeeEnrollmentIdsLst,List<Long> levelIdsLsit);
	public List<Object[]> getLocationWiseMandalDetails(List<Long> locationIds,Long levelId,List<Long> committeeEnrollmentIdsLst,Date startDate,Date endDate);
	public List<Object[]> getLocationWiseMandalStartedDetails(List<Long> locationIds,Long levelId,List<Long> committeeEnrollmentIdsLst,Date startDate,Date endDate);
	public List<Object[]> getLocationsByTypeIdAndLevel(Long levelId,Long committeTypeId,List<Long> locationValues,String status,List<Long> committeeEnrollmentIdsLst,Date stDate,Date edDate);
	public List<Object[]> getLocationByTypeIdAndLevel(List<Long> levelIds,Long committeTypeId,Long constituencyId,String status,List<Long> committeeEnrollmentIdsLst,Date stDate,Date edDate);
	public List<Object[]> committeesCountByDistrict(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> districtIds,List<Long> committeeSpanTypeIdsList);
	public String gettingConfirmedCommittee(Long tdpCommitteeId);
	
	public List<Object[]> committeesCountByConstituency(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> constiIds,String reqAreaType,List<Long> enrollementIdsList );
	//public List<Object[]> getCompletedAffliCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate );
	public List<Object[]> getCommitteesCountByDistrictIdAndLevel(List<Long> districtIds,List<Long> levelIds,List<Long> committeeSpanTypeIdsList);
	public List<Object[]> getCommitteesCountByConstituencyIdAndLevel(List<Long> constituencyIds,List<Long> levelIds, String reqAreaTypeStr, List<Long> committeeSpanTypeIdsList);
	public List<Object[]> committeesCountByLocationIds(Long levelId,List<Long> levelValues,Date startDate,Date endDate,String type,String reqLocationTypeStr, List<Long> committeeEnrollmentIdsLst,List<Long> levelIdsList);
	public List<Object[]> committeesCountByLocationIds1(Long levelId,List<Long> levelValues,Date startDate,Date endDate,String type,String reqLocationTypeStr, List<Long> committeeEnrollmentIdsLst,List<Long> levelIdsList);

	public List<Object[]> totalCommitteesCountByLocationIds(Long levelId,List<Long> levelValues,String reqLocationTypeStr, List<Long> committeeEnrollmentIdsLst,List<Long> levelIdsList);
	public List<Object[]> totalCommitteesCountByLocationIds1(Long levelId,List<Long> levelValues,String reqLocationTypeStr, List<Long> committeeEnrollmentIdsLst,List<Long> levelIdsList);

	public List<Object[]> getTotalCompletedCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList ,List<Long> enrollmentIdsList);
	
	public Long getTotalCommitteesCountByLocation(String state,List<Long> levelIds,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList ,List<Long> enrollmentIdsList,Date startDate,Date endDate);
	public List<Object[]> getCompletedAffliCommitteesCountByLocation(String stateId,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList,List<Long> committeeSpanTypeIdsLsit);
	public List<Object[]> getStartedAffliCommitteesCountByLocation(String stateId,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList,List<Long> committeeSpanTypeIdsLsit);
	public List<Object[]> getCommitteesCountForMandalByConstituencyIdAndLevel(List<Long> constituencyIds,List<Long> levelIds,String locationType);
	public List<Object[]> committeesCountForMandalByConstituency(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> constiIds,String locationType);
	
	public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValue(List<Long> locationIdsList,List<Long> locationValuesList);
	
	public List<Object[]> getCommitteesCountByLevelIdAndLevelValue(Long levelId,Long levelValue,Long constituencyId,List<Long> committeeEnrollmentYrIds);
	
	public List<Object[]> getStartedOrComplcommitteesCountByLevelIdAndLevelValue(Long levelId,Long levelValue,Long constituencyId,String status,List<Long> committeeEnrollmentYrIds);
	public List<Object[]> getcommitteesCountByDistrict(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> districtIds,List<Long> committeeEnrollmentYrIds);
	public List<Object[]> getcommitteesCountByConstituency(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> constiIds,List<Long> committeeEnrollmentYrIds);
	public List<Object[]> getCommitteesCountByLevelIdAndLevelValueForVillage(Long levelId,Long tehsilId,Long constituencyId,List<Long> committeeEnrollmentYrIds);
	public List<Object[]> getStartedOrComplcommitteesCountByLevelIdAndLevelValueForVillage(Long levelId,Long tehsilId,Long constituencyId,String status,List<Long> committeeEnrollmentYrIds);
	public List<Object[]> getCommitteesCountByLevelIdAndLevelValueForVillageMunicipal(Long levelId,Long tehsilId,Long constituencyId,List<Long> committeeEnrollmentYrIds);
	public List<Object[]> getStartedOrComplcommitteesCountByLevelIdAndLevelValueForVillageMunicipal(Long levelId,Long tehsilId,Long constituencyId,String status,List<Long> committeeEnrollmentYrIds);
	public List<Object[]> getCommitteesForLevelId(Long levelId);
	
	public Long getCommitteesCumulativeBasicReportChartQuery(Long userAccessLevelId ,List<Long> committeeLevelValueIds,String state,Long basicCommitteeId,Date startDate,Date endDate,String status);
	public List<Object[]> getCommitteesCumulativeOverallReportChartsQuery(Long userAccessLevelId ,List<Long> committeeLevelValueIds,List<Long> userAccessRequiredCommitteeLevelIds,String state,List<Long> basicCommitteeIds,Date startDate,Date endDate,String status);
	public List<Object[]> getCommitteesComparativeBascicReportChartQuery(Long userAccessLevelId ,List<Long> committeeLevelValueIds,String state,List<Long> basicCommitteeIds,Date date);
	public List<Object[]> getCommitteesComparativeOverallReportChartQuery(Long userAccessLevelId ,List<Long> committeeLevelValueIds,List<Long> userAccessRequiredCommitteeLevelIds,String state,List<Long> basicCommitteeIds,Date date);

    //CORE DASHBOARD NEW
	public List<Object[]> getCommitteesTotalOrStartedOrCompletedCount(CommitteeInputVO committeeBO);
	public List<Object[]> levelWiseBasicCommitteesCount(CommitteeInputVO committeeBO);
	public Long getCountsForCommittees(CommitteeInputVO committeeBO,String status);
	public List<Object[]> districtWiseCommitteesCount(CommitteeInputVO committeeBO,String status);
	public List<Object[]> getLocationWiseCommitteesCountByLocIds(CommitteeInputVO committeeBO);
	public List<Object[]> committeesPerformanceCohort(CommitteeInputVO committeeBO);
	public List<Object[]> getCommitteeLevelWiseCountsByLocIds(CommitteeInputVO committeeBO);
	public List<Object[]> getCumulativeCommitteesCountsByLocIds(CommitteeInputVO committeeBO);
	public List<Object[]> getTopPoorCommitteeLocations(CommitteeInputVO committeeBO);
	
	
	 public List<Long> getTdpCommitteeId(Long tdpBasicCommitteeId,Long tdpCommitteeLevelId,Long tdpCommitteeLevelValue,Long tdpCommitteeEnrollmentId);
	 public List<Object[]> getTdpCommitteeDetailsByEnrollmentId(List<Long> enrollmentIds);
	 public List<Long> getCommitteeIds(Long levelId,Long levelValue,Long committeeEnrollmentId,Date startDate,Date endDate,Long basicCommitteetypeId);
	 public List<Object[]> getLocationsWiseMandalDetails(List<Long> locationIds, List<Long> committeeLevelIdsList,List<Long> committeeEnrollmentIdsLst,Date startDate,Date endDate, String  accessType , List<Long> mainOrAfflCommitteIds );
	 public List<Object[]> getLocationsWiseMandalStartedDetails(List<Long> locationIds, List<Long> committeeLevelIdsList,List<Long> committeeEnrollmentIdsLst,Date startDate,Date endDate,String  accessType , List<Long> mainOrAfflCommitteIds );
     public Long getCommitteeConfirmRuleIdByCommitteeId(Long tdpCommitteeId);
     public List<Object[]> getTdpCommitteeMandalByConstituency(Long constituencyId,Long enrollmentId,String committeeType);
     public List<Object[]> getTdpCommitteeAllPanchayatsInMandals(List<Long> ids,Long enrollmentId,Long constituencyId);
     public List<Object[]> getTdpCommitteeLocalBodiesByConstituency(Long constituencyId,Long enrollmentId,String committeeType);
     public List<Object[]> getTdpCommitteeWardsInLocalElectionBody(List<Long> localBodyIds,Long constituencyId);
     public List<Object[]> getLocationWiseCommittees(String locationType,Long locationId,Long tdpCommitteeEnrollmentYearId);
     public List<Object[]> getLocationWiseCommitteesCnt(String locationType,Long locationId, Long tdpCommitteeEnrollmentYearId);
  
     public List<Object[]> getCommitteeCandidatesByLevelWiseDetails(List<Long> roleIds,List<Long> committeeIds,Long basicCommoteeId,Long enrollmentId,List<Long> enrollmentYears);
     public List<Object[]> getAvailableCommitteeDetails(String type , List<Long> enrollmentYearIdsList, Long committeeLevelId,List<Long> basicCommitteeTypeIdsList,List<Long> committeeTypeIdsList,Long locationScopeId,List<Long> locationValuesList);
     public List<Object[]> getCommitteeMembersAddedStatusDetails(List<Long> enrollmentYearIdsList, Long committeeLevelId,List<Long> basicCommitteeTypeIdsList,List<Long> committeeTypeIdsList,Long locationScopeId,List<Long> locationValuesList);
}
