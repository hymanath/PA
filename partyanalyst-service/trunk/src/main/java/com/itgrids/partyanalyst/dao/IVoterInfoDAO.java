package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterInfo;

public interface IVoterInfoDAO extends GenericDao<VoterInfo, Long>{
	
	public List<VoterInfo> getVotersCount(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long constituencyId);
	
	public Long getTotalVotersByReportLevelValue(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long constituencyId);
	
	public Integer deleteVotersInfoByReportLevelValue(Long reportLevelId, List<Long> reportLevelValue, Long publicationDateId);
	
	public Long getVotersCountInALocation(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long constituencyId);
	
	public Long getFamiliesCountInALocation(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long constituencyId);
		
	public List<VoterInfo> getVotersMultipleCount(Long reportLevelId, Set<Long> reportLevelValues, Long publicationDateId,Long constituencyId);
	
	public List<Object[]> getVoterInfoByPublicationDateIds(Long reportLevelId, Long reportLevelValue, List<Long> publicationDateIds);
	
	public Integer deleteVotersInfoByConstituencyId(Long constituencyId, Long publicationDateId);
	
	public List<Object[]> getTotalVotersByPublicationDateIdsList(List<Long> publicationDateIdsList, Long reportLevelId, Long locationValue, Long constituencyId);
	
	public List<Object[]> getPublicationDetailsBasedOnConstituencyId(Long constituencyId);
	
	public List<Long> getConstituencyIds();
	
	public List<Long> getVoterPublicationIdsBetweenTwoPublications(Long fromPublicationDateId, Long toPublicationDateId);
	
	public List<Long> getPreviousPublicationIds(Long publicationDateId);
	
	public List<Object[]> getPanchayatWiseVotersCount(Long constituencyId, Long publicationDateId);
	
	public List<Long> getReportLevelValueByConstituencyId(Long constituencyId,Long publicationDateId,Long reportLevelValue);

	public List<Object[]> getVoterDetailedCountByLocation(Long reportLvlId,Long reportLevelValue,Long publicationDateId,Long constituencyId);
	
	public List<Long> getNONURBANConstituencyIds(Long electionTypeId,Long electionYear,Long countryId);
	
	public List<VoterInfo> getVoterInfoList(Long constituencyId,Long publicationDateId);
	
	public Long getTotalVotersForSelectdLevel(Long levelId,Long levelValue,Long publicationDateId,Long constituencyId);
	
	public List<Object[]> getTotalVotersForHamletBooth(Long constituencyId,Long publicationDateId,Long reportLevelId);
	
	public List<Object[]> getVoterCountInPanchayatLevel(Long constituencyId,Long publicationDateId,Long reportLevelId);
	
	public List<Long> getCountForSelectdCountRange(Long constituencyId,Long publicationId,Long minValue,Long maxValue,Long reportLevelId);
	
	public Long getLatestPublicationDate(Long constituencyId);
	
	public List<Object[]> getTotalVotersInAPanchayat(Long constituencyId,Long publicationId,Long levelId,List<Long> panchayatIds);
	
	public List<Object[]> getVotersCountInPunchayatAndLocalElecBody(Long constituencyId,Long publicationId);
	
	public List<Object[]> getVoterInfoByPublicationDateIdsNew(Long reportLevelId, Long reportLevelValue, List<Long> publicationDateIds);
	
	public List<Object[]> getVoterInfoByPublicationDateIdsNewMaleCount(Long reportLevelId, Long reportLevelValue, List<Long> publicationDateIds);
	
	public List<Object[]> getVoterInfoByPublicationDateIdsNewFemaleCount(Long reportLevelId, Long reportLevelValue, List<Long> publicationDateIds);
	
	public List<Object[]> getVotersCountInCustomWards(Long constituencyId,Long publicationId,Long voterReportLevelId);
	
	public List<Object[]> getPanchayatDetailsForConstituency(Long constituencyId,Long publicationId,Long mandalId);
	
	public List<Object[]> getVoterCountByLevels(Long constituencyId,Long publicationDateId,List<Long> reportLevelIds,Set<Long> locationIds);
	
	public List<Object[]> getFamiliesCountInAPanchayats(List<Long> panchayatIds,Long publicationDateId);
	public List<Object[]> getVotersCountForAllConstituencies(Long publicationDateId,List<Long> constituencyIds);
	public List<Object[]> getVotersCountByLocationType(Long publicationDateId,List<Long> locationIds,String locationType,Long constituencyId);
	
	public Long getVotersCountInADistrict(Long districtId, Long publicationDateId);
	
	public List<Object[]> getVotersCountInADistrictsList(List<Long> districtIdsList, Long publicationDateId);
	
	public List<Object[]> getVotersCountInConstituenciesByDistrictsList(List<Long> districtIdsList, Long publicationDateId);
	
	public List<Object[]> getVotersCountInATehsilList(List<Long> tehsilIdsList, Long publicationDateId);
	public List<Object[]> getVotersCountInALocalBodyList(List<Long> localbodyIdsList, Long publicationDateId);
	public List<Object[]> getVotersCountInBoothsList(List<Long> boothIds, Long publicationDateId);
	public List<Object[]> getVotersCountInPanchayatList(List<Long> panchayatIds, Long publicationDateId);
	public List<Object[]> getVotersCountInConstituencies(List<Long> constituencyIds, Long publicationDateId);
	
	public List<Object[]> getVotersCountInConstituenciesByDistrictsListAndConstituencies(List<Long> districtIdsList, Long publicationDateId, List<Long> constiIds);
	
	public VoterInfo getVoterInfoOfALocation(Long constituencyId,Long publicationDateId,Long reportLevelId,Long reportLevelValue);
	
	public List<VoterInfo> getVotersCountForMultipleLocs(Long reportLevelId, Set<Long> reportLevelValues, Long publicationDateId,Long constituencyId);
	
	public List<Object[]> getVoterCadreDetailsBySearchCriteria(Long stateId, String locationType,List<Long> locationIdsList);
	public List<Object[]> getVotersCountByLocationValues(Long reportLevelId, Set<Long> reportLevelValues, Long publicationDateId,Long constituencyId);
	public List<Object[]> getVotersCountForDistrict(Set<Long> districtIdsList, Long publicationDateId);
	
	public Long getVotersCountInAParliament(Long parliamentId, Long publicationDateId);
	public Long getTotalVotersInALocationWiseCount(Long reportLevelId, Long publicationDateId,List<Long> assemblyIds);
	public List<Object[]> getTotalVotersForlocationWiseData(Long locationScopeId,Long locationValue,boolean isLocationBodyId);
}
