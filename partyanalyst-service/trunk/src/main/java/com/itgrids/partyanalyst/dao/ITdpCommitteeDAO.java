package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.model.TdpCommittee;

public interface ITdpCommitteeDAO  extends GenericDao<TdpCommittee, Long>{
	public List<Object[]> getAllAffiliatedCommittiesInALocation(Long levelId,Long levelValue);
	public List<Long> getMainCommittiesInALocation(Long levelId,Long levelValue);
	public List<Long> getTdpCommittee(Long tdpBasicCommitteeId,Long tdpCommitteeLevelId,Long tdpCommitteeLevelValue);
	//public Long getTotalCommitteesCountByLocation(String state,List<Long> levelIds,List<Long> districtIds);
	
	public List<Object[]> getTotalCommitteesPanchayatLevel(Long constituencyId);
	/*public List<Object[]> mandalWiseList(Long constituencyId,List mandalIds);
	public List<Object[]> muncipalList(Long constituencyId,List muncipalIds);
	public List<Object[]> divisionsList(Long constituencyId,List divisionIds);*/
	public List<Object[]> getLocationWiseDetails(List<Long> locationValues,Long locationTypeId);
	public List<Object[]> getLocationWiseVillageDetails(Long constituencyId);
	//public List<Object[]> getTotalCompletedCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds);
	public List<Object[]> getLocationWiseVillageStartedDetails(Long constituencyId);
	public List<Object[]> getLocationWiseMandalDetails(List<Long> locationIds,Long levelId);
	public List<Object[]> getLocationWiseMandalStartedDetails(List<Long> locationIds,Long levelId);
	public List<Object[]> getLocationsByTypeIdAndLevel(Long levelId,Long committeTypeId,List<Long> locationValues,String status);
	public List<Object[]> getLocationByTypeIdAndLevel(List<Long> levelIds,Long committeTypeId,Long constituencyId,String status);
	public List<Object[]> committeesCountByDistrict(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> districtIds);
	public String gettingConfirmedCommittee(Long tdpCommitteeId);
	
	public List<Object[]> committeesCountByConstituency(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> constiIds);
	//public List<Object[]> getCompletedAffliCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate );
	public List<Object[]> getCommitteesCountByDistrictIdAndLevel(List<Long> districtIds,List<Long> levelIds);
	public List<Object[]> getCommitteesCountByConstituencyIdAndLevel(List<Long> constituencyIds,List<Long> levelIds);
	public List<Object[]> committeesCountByLocationIds(Long levelId,List<Long> levelValues,Date startDate,Date endDate,String type);
	public List<Object[]> totalCommitteesCountByLocationIds(Long levelId,List<Long> levelValues);
	
	public List<Object[]> getTotalCompletedCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList);
	
	public Long getTotalCommitteesCountByLocation(String state,List<Long> levelIds,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList);
	public List<Object[]> getCompletedAffliCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList);
	public List<Object[]> getStartedAffliCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList);
	public List<Object[]> getCommitteesCountForMandalByConstituencyIdAndLevel(List<Long> constituencyIds,List<Long> levelIds,String locationType);
	public List<Object[]> committeesCountForMandalByConstituency(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> constiIds,String locationType);
	
	public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValue(List<Long> locationIdsList,List<Long> locationValuesList);
	
	public List<Object[]> getCommitteesCountByLevelIdAndLevelValue(Long levelId,Long levelValue,Long constituencyId);
	
	public List<Object[]> getStartedOrComplcommitteesCountByLevelIdAndLevelValue(Long levelId,Long levelValue,Long constituencyId,String status);
	public List<Object[]> getcommitteesCountByDistrict(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> districtIds);
	public List<Object[]> getcommitteesCountByConstituency(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> constiIds);
	public List<Object[]> getCommitteesCountByLevelIdAndLevelValueForVillage(Long levelId,Long tehsilId,Long constituencyId);
	public List<Object[]> getStartedOrComplcommitteesCountByLevelIdAndLevelValueForVillage(Long levelId,Long tehsilId,Long constituencyId,String status);
	public List<Object[]> getCommitteesCountByLevelIdAndLevelValueForVillageMunicipal(Long levelId,Long tehsilId,Long constituencyId);
	public List<Object[]> getStartedOrComplcommitteesCountByLevelIdAndLevelValueForVillageMunicipal(Long levelId,Long tehsilId,Long constituencyId,String status);
	public List<Object[]> getCommitteesForLevelId(Long levelId);
	
	public Long getCommitteesCumulativeBasicReportChartQuery(Long userAccessLevelId ,List<Long> committeeLevelValueIds,String state,Long basicCommitteeId,Date startDate,Date endDate,String status);
	public List<Object[]> getCommitteesCumulativeOverallReportChartsQuery(Long userAccessLevelId ,List<Long> committeeLevelValueIds,List<Long> userAccessRequiredCommitteeLevelIds,String state,List<Long> basicCommitteeIds,Date startDate,Date endDate,String status);
	public List<Object[]> getCommitteesComparativeBascicReportChartQuery(Long userAccessLevelId ,List<Long> committeeLevelValueIds,String state,List<Long> basicCommitteeIds,Date date);
	public List<Object[]> getCommitteesComparativeOverallReportChartQuery(Long userAccessLevelId ,List<Long> committeeLevelValueIds,List<Long> userAccessRequiredCommitteeLevelIds,String state,List<Long> basicCommitteeIds,Date date);

    //CORE DASHBOARD NEW
	public List<Object[]> getCommitteesTotalOrStartedOrCompletedCount(CommitteeInputVO committeeBO,String status);
	public List<Object[]> levelWiseBasicCommitteesCount(CommitteeInputVO committeeBO,String status);
	public Long getCountsForCommittees(CommitteeInputVO committeeBO,String status);
	public List<Object[]> districtWiseCommitteesCount(CommitteeInputVO committeeBO,String status);
	public List<Object[]> getLocationWiseCommitteesCountByLocIds(CommitteeInputVO committeeBO);
	public List<Object[]> committeesPerformanceCohort(CommitteeInputVO committeeBO);
	public List<Object[]> getCommitteeLevelWiseCountsByLocIds(CommitteeInputVO committeeBO);
	public List<Object[]> getCumulativeCommitteesCountsByLocIds(CommitteeInputVO committeeBO);
}
