package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dao.columns.enums.BoothColumnNames;
import com.itgrids.partyanalyst.model.Booth;

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
	
	public List<Booth> getPollingStationByMandalId(Long mandalId, Long constituencyId);
	
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
	
	public List<Object[]> getBoothsInAMunicipality(Long lclElecBodyId,Long publicationDateId,Long constituencyId);
	
	public List<Long> getStateIdByPublicationId(Long publicationDateId);
	
	public List<Object[]> getBoothInfo(Long publicationId,Long constituencyId,String partNo);
	
	public List<Long> getTotalaVotesByBoothIds(List<Long> boothIds);

	
    public List<Object[]> getBoothsInAPanchayatForPresentElectionYear(Long panchayatId,Long year);

	public List<Object[]> getBoothsCount(Long id,Long publicationDateId,String type,Long constituencyId,Long tehsilId);
	 
	public List<Object[]> getBoothIdsByPanchayatIdsInAPublication(List<Long> panchayatIds,Long publicationDateId);
	
	public List<Long> checkForUrbanBooth(Long boothId,Long publicationDateId);
	
	public List<Object[]> getBoothIdsInLocalBodiesForAPublication(List<Long> localBodiesList, Long publicationDateId,Long constituencyId);
	 
	public List<Long> getAllPublicationDetailsForConstituency(Long constituencyId);

	public List<Long> getBoothIdsByPanchayatIdsListOrLocalEleBodyIdsList(String type, Long publicationDateId, List<Long> panchayatIdsList);
	
	public List<Object[]> getWardsByLocalElecBodyId(Long id,Long publicationDateId,Long constituencyId);
	public List<Object[]> getWardsByLocalElecBodyIds(List<Long> ids,Long publicationDateId,Long constituencyId);
		
	public List<Object[]> getBoothsForWard(Long wardId,Long publicationDateId);
	
	public List<Long> getBoothIdsForWard(Long wardId,Long publicationDateId);
	
	public List<Object[]> getBoothInWard(Long wardId, Long publicationDateId);
	
	public List<Object[]> getWardsInMuncipality(Long wardId, Long publicationDateId);
	
	public List<Object> getNoOfWardsInMuncipality(Long id, Long publicationDateId,Long constituencyId);
	
	public List<Long> getWardIdsByLocalElectionBodyIds(List<Long> localElectionBodyIds);
	
	public List<Long> getWardIdsByLocalEleBodyIdsList(List<Long> localEleBodyIds, Long publicationDateId);

	public List<Long> getBoothsCountByPublicationId(String type,Long id,Long publicationDateId,Long constituencyId);
	
	public List<Booth> getBoothByPartNoPublicationId(Long constituencyId,Long publicationId,String partNo);
	
	public List<Object[]> getBoothIdsAndPartNosOfAConstituencyInAPublication(Long constituencyId,Long publicationDateId);
	
	public Booth getBoothByPartNoAndPublicationIdInAConstituency(String partNo, Long constituencyId, Long publicationDateId);
	
	public List<Object[]> getBoothsInAPanchayatByPublicationId(Long panchayatId,Long publicationId);
    
	public List<Object[]> findBoothsInfoForALocalBodyWardByConstituencyAndPublicationId(Long wardId , Long constituencyId , Long publicationId);
	
	public List<Booth> getBoothsByPartNosAndPublicationIdInAConstituency(List<String> partNosList, Long constituencyId, Long publicationDateId);	
	
	public List<Object[]> getBoothLocations(String partNo,Long constituencyId);
	
	public Integer updatePanchayatByBoothId(Long boothId, Long panchayatId);
	
	public List getPartNoByBoothId(Long partNo);
	
	public List<Object[]> getBoothsInAMandalByPublicationAndConstId(Long mandalId, Long publicationId, Long constituencyId);
	public List<Object[]> getBoothsByPanchayatId(Long panchayatId);
	
	public List<String> getPartNoByPanchayatIdAndPublicationDateIdsList(Long locationValue, List<Long> publicationDateIdsList, Long constituencyId, String type);
	
	public List<Long> getWardsByLocalElecBodyIdAndPublicationIdsList(Long locationValue, List<Long> publicationDateIdsList, Long constituencyId);
	
	public List<Long> getBoothIdsByPanchayatIdAndPublicationDateIdsList(Long locationValue, List<Long> publicationDateIdsList, Long constituencyId, String type);
	
	public List getBoothIdByPartNo(String partNo);
	
	public List<Long> getBoothIdsByLocalValuesList(String locationType,Long locationValue,Long constituencyId,List<Long> publicationDateIdsList);
	
	public Long getBoothsInPanchayatDAO(long panchayatId);
	
	public List<String> getPartNosByBoothIdsList(Long constituencyId, Long publicationDateId, List<Long> boothIdsList);
	
	public List<Object[]> getBoothsInAPanchayatUsingConstituencyId(Long panchayatId,Long publicationDateId,Long constituencyId,String type,Long tehsilId);
	
	public List<Object[]> getBoothsInPanchayat(long panchayatId);
	
	public List<Object[]> getBoothIdsByLocalEleBodyId(Long id,Long publicationId,Long constituencyId);
	
	public List<Object[]> getBoothsByPanchayatIDConstiId(Long panchayatId,Long constituencyId,Long publicationId);
	
	public List<Object[]> getAllBoothsInSelectedType(Long id, Long level,Long publicationId);
	
	public List<Object[]> getAllBoothsForPanchayatsOrMandals(String type,List<Long> ids,Long publicationId);
	
	public List<Object[]> getBoothsForSelectedWards(List<Long> ids,Long publicationId);
		
	public List<String> getPartNosForBooths(List<Long> locationIds);
	
	public List<Object[]> getAllBoothsInAMuncipality(Long localBodyId,Long publicationId);
	
	public List<Long> getMandalsListByConstituencyId(Long constituencyId,Long publicationId);
	
	public List<Long> getPanchayatsListByConstituencyId(Long constituencyId,Long publicationId);
	
	public List<Long> getMuncipalitiesListByConstituencyId(Long constituencyId,Long publicationId);
	
	public List<Long> getBoothsListByConstituencyId(Long constituencyId,Long publicationId);
	
	public List<Long> getWardsListByConstituencyId(Long constituencyId,Long publicationId);
	
	public List<Object[]> getBoothsByBoothIdsList(List<Long> boothIdsList);
	
	public List<Object[]> getMuncipalitiesByMuncipalityIdsList(Long constituencyId,Long publicationId,List<Long> muncipalityIdsList);
	
	public List<Object[]> getWardsByWardIdsList(Long constituencyId,Long publicationId,List<Long> wardIdsList);
	
	public List<Object[]> getPanchayatiesCountByTahsilAndConstituencyId(Long constituencyId,Long tehsilId,Long publicationId,String type);
	
	public Long getTotalVotersInBooths(List<Long> boothIds);
	
	public String getBoothPartNoByBoothId(Long boothId);
	
	public List<Long> getPanchayatsByPanchayatIdsList(Long constituencyId,Long publicationId,List<Long> panchayatIdsList);
	
	public List<Object[]> getBoothsListByBoothIdsList(List<Long> boothIdsList);
	
	public List<Object[]> getBoothsInAMandalByPublicationAndConstIds(Long mandalId, Long publicationId, Long constituencyId);
	
	public List<Object[]> getPanchayatiesByMandalsListAndConstituencyId(List<Long> mandalsList,Long constituencyId,Long publicationDateId);
	
	public List<Object[]> getBoothsByPanchayatIdsListAndConstituencyIdInAPublication(List<Long> panchayatIdsList,Long constituencyId,Long publicationDateId);
	
	public List<Long> getPanchayatsCountByMandalId(Long mandalId,Long constituencyId,Long publicationDateId);
	
	public List<Long> getBoothsCountByLocationId(String type,Long id,Long constituencyId,Long publicationDateId);
	
	public List<Long> getWardsCountByLocalEleBodyId(Long localEleBodyId,Long publicationDateId,Long constituencyId);
	
	public List<Object[]> getBoothsForConstituencyList(List<Long> constituencyIds);
	
	public List<Object[]> getBoothsForLocalEleBodyByCOnstituencyId(Long constituencyId,Long localEleBodyId,Long publicationDateId);

	public List<Long> getWardIdsByLocalEleBodyId(Long localEleBodyId,Long publicationDateId,Long constituencyId);
	
	public List<Object[]> getConstityencysByBooths(List<Long> boothIds);
	
	public List<Long> getboothsByTehsilId(Long tehsilId);
	
	public List<Long> getboothsByWardId(Long tehsilId);
	
	public List<Object[]> getPanchayatsListByTehsilId(Long tehsilId,Long publicationId);
	
	public List<Object[]> getBoothsByPanchayat(Long panchayatId,Long publicationId);
	
	public List<Long> getBoothsByPanchayatId(Long panchayatId,Long publicationId);
	
	public Long getTotalVotesForBooth(List<Long> boothIdsList,Long constituencyId);
	
	public List<Object[]> getPanchayatsNamesListByConstituencyId(Long constituencyId,Long publicationId);
	
	public List<Object[]> getMuncipalitiesListNamesByConstituencyId(Long constituencyId,Long publicationId);
	
	public List<Long> getTehsildByConstituency(Long constituencyId,Long publicationId);
	
	public List<Booth> getboothsDetailsByTehsilId(Long tehsilId,Long publicationDateId);
	
	public Long getTotalVotesByBoothIdsList(List<Long> boothIdsList);
	
	public List<Object[]> getBoothIdsByPanchayatIds(List<Long> locationValues,Long constituencyId,List<Long> publicationDateIdsList);
	
	public List<Booth> getBoothsListByConstituencyId(Long constituencyId);
	
	public List<Long> getHamletIdsListByConstituencyId(Long constituencyId,Long publicationDateId);
	
	public Long getLatestPublicationDateIdForAConstituency(Long constituencyId);
	
	public List<Object[]> getTehsilsForAConstituencyForAPublication(Long constituencyId, Long publicationDateId);
	
	public List<Object[]> getPanchayatsForAConstituencyForAPublication(Long constituencyId, Long publicationDateId);
	
	public List<Object[]> getHamletsForAConstituencyForAPublication(Long constituencyId, Long publicationDateId);
	
	public List<Booth> getBoothOfAConstituencyInAPublication(Long constituencyId, Long publicationDateId);
	
	public List<Object[]> getTotalVotesForBooth(List<Long> boothIds);
		
	public List<Object[]> getBoothsForUrbanConstituencyes(List<Long> wardIds,Long constituencyId,Long publicationId);
	
	public List<Object[]> getBoothsAndWardsInUrbanConstituency(Long constituencyId,Long publicationId);
	
	public Long getTotalVotesForSelectedBooth(Long boothId);
	
	public List<Long> getBoothIdsByConstituencyIdAndPublicationId(Long constituencyId,Long publicationDateId);

	public List<Object[]> getDescriptionForMandalLevel(Long tehsilId,Long publicationId);
	
	public List<Object[]> getDescriptionForPanchayatLevel(Long panchayatId,Long publicationId);
	
	public List<Object[]> getDescriptionForBoothLevel(Long panchayatId,Long boothId);
	
	public List<Object[]> getPartialPanchayats(Long publicationId,Long constituencyId);
	
	public List<Long> getPanchayatsMandalId(Long mandalId,Long constituencyId,Long publicationDateId);
	
	public List<Long> getPartialBoothsForPanchayatLevel(Long panchayatId,Long publicationId);
	
	public List<Object[]> getPartialBoothsDetailsOfPanchayat(List<Long> panchayatId,Long publicationId);
	
	public List<Object[]> getDescriptionForHamletLevel(Long panchayatId,Long hamletId);
	
	public List<Object[]> getPanchayatByBoothId(Long boothId,Long publicationId);
	
	public Long findVotersCountByPublicationIdForPartialPanchayat(Long userId,Long panchayatId,Long publicationDateId);

	public List<Long> getTehsilsForAfterDelimation(Long constituencyId,Long year);
	
	public List<Long> getBoothsBeforDelimation(Long year , List<Long> tehsilIds);
		
	public List<Long> getBoothsInAPanchayat1(Long panchayatId,Long publicationDateId);
	
	public	List<Object[]> getboothNamesByBoothIds(List<Long> boothIds);
	
	public String getMuncipaltyName(Long constituencyId,Long publicationId);
	
	public List<Object[]> getTehsilsForConstituency(Long constituencyId,Long publicationDateId);
	
    public List<Object[]> getWardDetailsByLocalEleBodyId(Long localEleBodyId,Long publicationDateId,Long constituencyId);
	
	public List<Object[]> getPanchayatMandalDetails(Long constituencyId,Long publicationId);
	
	public List<Object[]> getMunicDetails(Long constituencyId,Long publicationId);
	
	public List<Object[]> getPanchayatsForConstituency(List<Long> mandalIds,Long publicationDateId);
	
	
	public List getConstituneycId(Long boothId);
	
	public List<Booth> getModelByBoothId(Long boothId);
	
	public List<Booth> getBoothDataForAPublication(Long publicationDateId);
	
	public Booth getBoothByConstituencyPublicationPartNo(Long constituencyId,Long publicationDateId,String partNo);
	
	public Long getBoothIdByConstituencyPublicationPartNo(Long constituencyId,Long publicationDateId,String partNo);
	
	public List<Long> getPanchayatsForAfterDelimation(Long constituencyId,Long year);
	
	public List<Long> getBoothsBeforDelimationByPanchayat(Long year , List<Long> panchayatis);
	
	public List<Object[]> getPanchayatAndLebIds(Long constituencyId,Long publicationDateId);
	
	public List<Object[]> getPincodesForBoothIdsList(List<Long> boothIdsList);
	
	public List<Long> getBoothIdByConstituencyPublication(Long constituencyId,Long publicationDateId);
	
	public List<Object[]> getPanchayatsByConstituencyAndPublication(Long constituencyId,Long publicationDateId);
	
	public List<Object[]> getBoothOfAConstituencyByPublication(Long constituencyId, Long publicationDateId,Long tehsilId,Long localElecBodyId);
	
	public List<Object[]> getTotalaVotesByBooths(List<Long> boothIds);
	
	public List<Object[]> getAllTheBoothsDetailsByConstituencyId(Long constituencyId,Long publicationDateId);
	public List<Object[]> getTotalaVotesDetailsByBoothIds(List<Long> boothIds);
	public Long getTotalVoter(Long boothId);
	public List<Object[]> getBoothDetailsByBoothIds(Set<Long> boothIds);
	public List<Object[]> getConstituencyWiseBoothCount();
	public List<Object[]> getPanchayatBoothDetails(Long constituencyId,Long publicationId);
	public List<Object[]> getPanchayatBooths(List<Long> boothIds) ;
	public List<Object[]> getBoothsByPanhcayats(List<Long> panchayatIds,Long publicationId) ;
	public List<Object[]> getTotalBoothsCountByConstituencyIds(List<Long> constituencyIds,Long publicationDateId);
	public List<Object[]> getTotalBoothsCountForPanchayatisByConstituencyId(Long constituencyId);


}
