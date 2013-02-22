package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dao.columns.enums.BoothColumnNames;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Voter;

public interface IBoothDAO extends GenericDao<Booth, Long>{
	
	public List<Booth> findByPartNo(Object partNo);
	
	public List<Booth> findByPartName(Object partName);
	
	public List<Booth> findByVillageName(Object villageName);
	
	public List<Booth> findByProperty(BoothColumnNames propertyName, Object value);
	
	public List<Booth> findByTehsil(Long tehsilId);
	
	public List<Booth> findByTehsilAndPartNo(String tehsilName, String partNo);

	public List findTehsilsByElectionAndConstituency(String electionYear,Long constituencyId);
	
	public List findLocalBodiesByElectionAndConstituency(String electionYear,Long constituencyId, String localBodyTypes);
	
	public List<Object[]> getBoothsBasedOnConstituencyAndPublicationDate(Long constituencyId,Long publicationDateId);
	
	public List findLocalBodyWardsByElectionAndConstituency(String electionYear,Long constituencyId, String localBodyTypes);

	public List findByConstituencyAndElectionYear(Long constituencyId, Long year);
	
	public List findByPartNoConstituencyIdAndYear(Long constituencyId, Long year, String partNo);
	
	@SuppressWarnings("unchecked")
	public List findBoothIdPartNoConstituencyIdAndYear(Long constituencyId, Long year, String partNo);

	public List<Booth> findbyConstituencyNameDistrictIdPartnoAndElectionYear(
			String acName, Long districtId, Long electionYear, String partNumber);
	
	public List findbyConstituencyNameDistrictIdAndElectionYear(
			String acName, Long districtId, Long electionYear);
	
	public List findBoothInfoByConstituencyIdAndYear(Long constituencyId, Long year);

	public int updateLocalBodyInfoByBoothIdsAndWardId(Long localBody, List<Long> boothIds);
	
	public List<Booth> findByBoothIds(List<Long> boothIds);
	
	public List findVotersInfoForConstituencyInAnYearByLocalElectionBody(Long constituencyId, Long year, String localBodyTypes);
	
	public List findVotersInfoForConstituencyInAnYearByLocalElectionBodyWard(Long constituencyId, Long year, String wardType);
	
	public List findAssemblyConstituenciesDetailsForParliament(String assemblyIds, String electionYear);
	
	public List findVoterInformationByMandalIdsAndDelimitationYear (String mandalsIds,String year, Long constituencyId);
	
	public List<Long> findByConstituencyElectionAndPartNo(
			Long constituencyId, Long electionYear, String partNos);
	
	public List findBoothsInfoForAMandalByConstituencyAndYear(Long tehsilId, Long year, Long constituencyId);
	
	public List findBoothsInfoForALocalElectionBodyByConstituencyAndYear(Long localBodyId, Long year, Long constituencyId);
	
	public List findBoothsInfoForALocalBodyWardByConstituencyAndYear(Long localBodyWardId, Long year, Long constituencyId);
	
	public List<Long> getCountOfPartNumbersInAConstituency(Long constituencyId, Long electionYear);
	
	public List<Object> getPartNumbersAndVillagesCoveredInAConstituency(Long constituencyId, Long electionYear);
	
	public int updateVillagesCoveredInfoInAConstituency(Long constituencyId, String villagesCovered,String partNo,Long electionYear);
	
	public List findBoothsInLocalElectionBodies(String localElectionBodyIds, String  constituencyIds, Long year);
	public List findBoothsInTehsils(String tehsilIds, String  constituencyIds, Long year);
	public List getVillageToBoothByBooths(String boothIds);
	public List getLocalElectionBodyToBoothByBooths(String localElectionBodyIds);
	public int removeMappingToLocalBody(List<Long> boothIds);
	public List<Booth> getPollingStationByConstituencyId(Long constituencyId);
	
	public List<Booth> getPollingStationByMandalId(Long mandalId);
	
	public List findByConstituencyAndElectionYearAndPubDtId(Long constituencyId,Long year,Long publicationDtId);
	
	public List findByPartNoConstituencyIdAndYearAndPubDtId(Long constituencyId, Long year, String partNo,Long publicationDtId);
	
	public List<Booth> getBoothsByPublicationDateTehsilConstituenctPartNos(Long publicationDateId,Long thesilId,Long constituencyId,String partNos);
	
	public List<Object[]> getPublicationDetailsBasedOnConstituency(Long constituencyId);

	
	public List<Booth> findByPublicationDateConstituencyAndPartNo(Long stateId,
			Long districtId, String constituencyName ,Long publicationDateId,String  partNo);
	
	public List<Object[]> findBoothsInLocalElectionBodyByPublicationDateId(
			Long localElectionBodyId, Long publicationDateId);
	
	/*public List<Long> getTehsilIdsByConstituencyIdAndPublicationDateId(
			Long constituencyId, Long publicationDateId);*/
	
	public List<Object[]> getBoothsInAMandalByPublication(Long mandalId, Long publicationId);
	
	public List<Object[]> getBoothsInAConstituencyByPublication(Long constituencyId, Long publicationId);
	
	public List<Booth> getBoothDetailsByBoothId(Long boothid);
	
	public List<Object[]> getBoothsInAPanchayat(Long panchayatId,Long publicationDateId);
	
	public List<Object[]> getBoothsInAMunicipality(Long lclElecBodyId,Long publicationDateId);
	
	public List<Long> getStateIdByPublicationId(Long publicationDateId);
	
	public List<Object[]> getBoothInfo(Long publicationId,Long constituencyId,String partNo);
	
	public List<Long> getTotalaVotesByBoothIds(List<Long> boothIds);

	
    public List<Object[]> getBoothsInAPanchayatForPresentElectionYear(Long panchayatId,Long year);

	public List<Object[]> getBoothsCount(Long id,Long publicationDateId,String type);
	 
	public List<Object[]> getBoothIdsByPanchayatIdsInAPublication(List<Long> panchayatIds,Long publicationDateId);
	
	public List<Long> checkForUrbanBooth(Long boothId,Long publicationDateId);
	
	public List<Object[]> getBoothIdsInLocalBodiesForAPublication(List<Long> localBodiesList, Long publicationDateId);
	 
	public List<Long> getAllPublicationDetailsForConstituency(Long constituencyId);

	public List<Long> getBoothIdsByPanchayatIdsListOrLocalEleBodyIdsList(String type, Long publicationDateId, List<Long> panchayatIdsList);
	
	public List<Object[]> getWardsByLocalElecBodyId(Long id,Long publicationDateId);
	public List<Object[]> getWardsByLocalElecBodyIds(List<Long> ids,Long publicationDateId);
		
	public List<Object[]> getBoothsForWard(Long wardId,Long publicationDateId);
	
	public List<Long> getBoothIdsForWard(Long wardId,Long publicationDateId);
	
	public List<Object[]> getBoothInWard(Long wardId, Long publicationDateId);
	
	public List<Object[]> getWardsInMuncipality(Long wardId, Long publicationDateId);
	
	public List<Object> getNoOfWardsInMuncipality(Long id, Long publicationDateId);
	
	public List<Long> getWardIdsByLocalElectionBodyIds(List<Long> localElectionBodyIds);
	
	public List<Long> getWardIdsByLocalEleBodyIdsList(List<Long> localEleBodyIds, Long publicationDateId);

	public List<Long> getBoothsCountByPublicationId(String type,Long id,Long publicationDateId);
	
	public List<Booth> getBoothByPartNoPublicationId(Long constituencyId,Long publicationId,String partNo);
	
	public List<Object[]> getBoothIdsAndPartNosOfAConstituencyInAPublication(Long constituencyId,Long publicationDateId);
	
	public Booth getBoothByPartNoAndPublicationIdInAConstituency(String partNo, Long constituencyId, Long publicationDateId);
	

    public List<Object[]> getBoothsInAPanchayatByPublicationId(Long panchayatId,Long publicationId);
    
	public List<Object[]> findBoothsInfoForALocalBodyWardByConstituencyAndPublicationId(Long wardId , Long constituencyId , Long publicationId);
}
