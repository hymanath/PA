package com.itgrids.partyanalyst.dao;

import java.util.List;

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
	 
}
