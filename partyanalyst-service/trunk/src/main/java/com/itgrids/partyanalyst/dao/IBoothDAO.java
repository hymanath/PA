package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dao.columns.enums.BoothColumnNames;
import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Constituency;

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
	
	public List<Object[]> getDistrictsPanchayatAndLebIds(Long distictId,Long publicationDateId);
	
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

	 public List<Object[]> getBoothCountInfoByConstiIds(List<Long> constituencyIds);
	 
	public List<Object[]> getmandalDetailsByBoothIds(List<Long> boothIds);
	public List<Object[]> getMuncipalityDetyailsByBoothIds(List<Long> boothIds);
	public List<Object[]> getTotalBoothsDetailsByConstituencyId(Long constituencyId);
	
	public List<Long> getBoothIdsByConstituencyIdsAndPublicationId(List<Long> constituencyIds,Long publicationDateId);

	public List<Object[]> getBoothsInAConstituency(Long constituencyId,Long publicationDateId);
	
	public List<Object[]> getBoothNamesByIds(List<Long> boothIds);
	
	public List<Object[]> getAllDetails();
	
	public List<Object[]> getAllBoothsInUrban(Long constituencyId,Long publicationId);
	
	public List<Object[]> getAllBoothsInMuncipalities(List<Long> localBodyIds,Long publicationId);
	
	public Long getConstituencyIdByLocationIdAndType(Long locationId,String locationType);
		
	public List<Object[]> getAllLocalBodies(Long constituencyId,Long publicationId);
	
	public List<Object[]> getAllBoothsInPanchayat(List<Long> panchayatIds,Long publicationId);
	
	public List<Long> getAllBoothIdsByConsti(Long constituencyId,Long publicationId);
	
	public List<Long> getAllBoothIdsInPanchayat(List<Long> panchayatIds,Long publicationId);
	
	public List<Long> getAllBoothIdsInLocalBodies(List<Long> localBodyIds,Long publicationId);
	
	public List<Long> getAllBoothsInAMandal(Long tehsilId,Long publicationId,Long constituencyId);
	
	public List<Long> getAllPanchayatsInAMandal(Long tehsilId,Long publicationId,Long constituencyId);
	
	public List<Long> getAllTehsilsInAConstituency(Long constituencyId,Long publicationId);
	
	public List<Long> getLBSInAConstituency(Long constituencyId,Long publicationId);
	
	public List<Long> getAllPanchayatsInAConstituency(Long constituencyId,Long publicationId);
	
	public List<Long> getAllBoothsInAConstituency(Long constituencyId,Long publicationId);
	
	public List<Object[]> getAllDetails1();
	
	public List<Long> getLocalbodiesByConstituencyIds(List<Long> constiIds,Long publicationDateId);
	
	public List<Long> getTehsilsByConstituencyIds(List<Long> constiIds,Long publicationDateId);
	
	public List<Long> getPanchayatsByConstituencyIds(List<Long> constituencyIds,Long publicationDateId);
	
	public List<Object[]> getBoothsInAMuncipality(List<Long> boothIds);
	
	public List<Object[]> getBooths(List<Long> boothIds);
	
	public List<Object[]> getTotalVotersInBooths(Long constituencyId,Long publicationDateId);
	
	public List<Long> getAllBoothIdsInALocalBody(Long localBodyId,Long publicationId,Long constituencyId);
	
	public List<String> getUnregisteredBoothsByBooths(List<Long> boothIdsList,Long constituencyId, Long publicationDateId, Long tehsilID,String tehsilType);
	
	public List<Object[]> findBoothInfoByConstituencyAndPublication(Long constituencyId, Long publicationDateId);
	
	public List<Object[]> getBoothNames(List<Long> boothIds);
	
	public List<Object[]> getAllTehsilsDetailsInAConstituency(Long constituencyId,Long publicationId);
	
	public List<Object[]> getConstituenciesNameByType(List<Long> ids,String type);
	
	public List<Object[]> getAllBoothsOfAConstituency(Long constituencyId,Long publicationDateId);
	public List<Long> getLocationIds(Long id,String type,Long publicationId);
	
	public List<Object[]> getAllTheBoothsDetailsByConstituencyIdForCTP(Long constituencyId);
	
	public List<Object[]> getAllPublicationsForConstituencies(List<Long> constiIds);
	
	public List<Object[]> getTotalBoothsCountByConstituencyIdsForCTP(List<Long> constituencyIds);
	
	public List<Object[]> getAllBoothsByTehsilOrLclBdyId(Long locationId,Long publicationId,String type);
	
	public List<Object[]> findTehsilsByDistrictIdAndPublicationDateId(Long districtId, Long publicationDateId);
	
	public List<Object[]> getAllLocalBodiesByDistrictIdAndPublicationDateId(Long districtId,Long publicationId);
	
	public List<Long> getBoothIdsByConstituencyIdsForCTP(List<Long> constituencyIds);
	
	public List<Long> getBoothsDetailIds(String type,Long constituencyId,Set<Long> locationVal);
	public Long getBoothResults(List<Long> boothIds,Long electionId);
	public Long getCandidateBoothResults(List<Long> boothIds,List<Long> partyIds,Long electionId);
	public List<Object[]> getBoothsInfo(Long constituencyId,Set<String> boothIds);
	
	public List<Object[]> getPanchaytsInfoByStateId(Long publicationId,Long stateId);
	
	public List<Long> getConstituencyDetailsByTehsilId(Long tehsilId);
	 public List<Long> getLocalElectionBody(Long tehsilId);
	 public Long getLocalElectionBodyDetails(Long localElectionBody,Long constituencyId,Long publicationDateId);
	 
	 public List<Long> getPanchayatsIdsListByTehsilId(List<Long> tehsilIdsList,Long publicationId);
	 public List<Object[]> getTehsilsIdsAndLocalBodyIdsListByDistricts(List<Long> districtIdsList, Long publicationDateId);
	 public List<Object[]> getTehsilsIdsAndLocalBodyIdsListByConstituencyIds(List<Long> constituencyIdsList, Long publicationDateId);
	 public List<Long> getConstituencyForPanchayat(Long panchayatId);
	 public Long getLocalElectionBodyByConstituency(Long constituencyId);
	 public List<Object[]> getWardsByConstituencies(List<Long> constituencyIds,Long latestPublicationId);
	 
	 public Long getBoothsCountByDivisionIds(List<Long> divisionIds,Long publicationId);
	 public List<Object[]>  getBoothWiseTotalVoters(List<Long> totalBooths,Long wardId);
	 public List<Long> getAllBoothIdsByWard(Long wardId,Long publicationId);
	 public List<Object[]> getBoothsDataByDivisionId(Long divisionId,Long publicationId);
	 public List<Object[]> getTotalVotersByBooths(List<Long> boothIds);
	 public List<Object[]> getMandalMuncipalityIds(Long boothId);
	 public List<Long> getConstituencyIdByTehsilId(Long tehsilId,Long publicationDateId);
	 public List getBoothLocations(Long localElebodyId,String voterIdCardNo);
	 public List<Object[]> getTehsilListByConstituency(Long constituencyId,Long publicationId);
	 public List<Object[]> getLEBListByConstituency(Long constituencyId,Long publicationId);
	 
	 public List<Object[]> getBoothsByLocalElectionBody(Long localElectionBody,Long publicationDateId);
	 public List<Object[]> getBoothsByTehsilId(Long tehsilId,Long publicationDateId);
	 public List<Object[]> getboothList(Long localElecBdyId);
	 public List<Object[]> getBoothListFrPanchayat(Long panchayId);
	 public List<Object[]> getWardsByLocalElecBody(Long id,Long publicationDateId,Long constituencyId);
	 
	 public List<Constituency> getConstituencyIdByTehsilId(Long tehsilId);
	 public List<Constituency> getConstituencyIdByLebId(Long lebId);
	 public List<Object[]> getBoothsForTehsilId(List<Long> tehsilIds,Long constituencyId);
	 public List<Object[]> getBoothsForMuncipality(List<Long> lcalElcBdyId,Long constituencyId);
	 public Long getTotalBoothsByConstituencyId(Long constituencyId);
	 public List<Object[]> getBoothsForMuncipalitys(List<Long> lcalElcBdyId,Long constituencyId);
	 public List<Object[]> getBoothsForMuncipalityWise(List<Long> lcalElcBdyId,Long constituencyId);
	 public List<Long>  getBoothCommitteesTotalCount(Long userAccessLevelId,Set<Long> userAccessLevelValues);
	 public Long  getBoothCommitteesOnlyTotalCount(Long userAccessLevelId,Set<Long> userAccessLevelValues);
	 public List<Object[]> getLocationWiseCommitteesCountByLocIds(CommitteeInputVO committeeBO);
	 public List<Object[]> levelWiseBasicCommitteesCount(CommitteeInputVO committeeBO);
	 public List<Object[]> committeesPerformanceCohort(CommitteeInputVO committeeBO);
	 public List<Object[]> getVoterDetailsByBoothId(Long boothId);
	 public List<Object[]> getVoterSerialNoByVoterIdsList(List<Long> voterIdsList,Long boothId);
	 public List<Object[]> getTotalVotersForLocations(Set<Long> locationIdSet,Long locationTypeId,Long publicationDateId);
	 public List<Object[]> getTehsilAndLEBIdsByConstituency(List<Long> constituencyIds,Long publicationDateId);
	 public List<Object[]> getPanchayatByMandal(List<Long> mandalIds,Long publicationDateId);
	 public List<Object[]> getMunciplaitiesByLeb(List<Long> lebIds,Long publicationDateId);
	 public List<Object[]> getLocationWiseMandalAndConstituency(List<Long> boothIds,String searchType, boolean islocalBody);
	 public List<Object[]> getLocationWiseMandalAndpanchayat(List<Long> panchayatIds,String searchType);
	 public List<Long> getConstituencyIdsByLebId(Long lebId);
}
