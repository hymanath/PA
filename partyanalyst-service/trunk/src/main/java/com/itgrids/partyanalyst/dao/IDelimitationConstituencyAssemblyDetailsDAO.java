package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituencyAssemblyDetails;

public interface IDelimitationConstituencyAssemblyDetailsDAO extends GenericDao<DelimitationConstituencyAssemblyDetails, Long>{

	public List<Constituency> findAssemblyConstituencies(Long parliamentConstituencyId, Long electionYear);
	
	public List<Constituency> findAssemblyConstituenciesByDelimitationConstituencyId(Long delimitationConstituencyId);
	
	public List findAssembliesConstituencies(Long parliamentConstituencyId);
	
	public List findLatestParliamentForAssembly(Long assemblyId);
	
	@SuppressWarnings("unchecked")
	public List findParliamentConstituenciesForAAssemblyConstituency(Long constituencyId);
	
	@SuppressWarnings("unchecked")
	public List findParliamentForAssemblyForTheGivenYear(Long assemblyId,Long electionYear);

	public List getAllAssembliesOfParliament(Long parliamentId);
	
	public List findParliamentConstituencyForListOfAssemblyConstituency(String assemblyId,Long electionYear);
	
	public List<Constituency> findAssemblyConstituenciesIdsAndNames(
			Long parliamentConstituencyId, Long electionYear);
	
	public List findDistrictsOfParliamentConstituency(Long parliamentId);
	
	public List<Long> findAssembliesConstituenciesForAListOfParliamentConstituency(List<Long> parliamentConstituencyIds);
	
	public List findParliamentConstituenciesByDistrictId(Long districtId,Long year);
	
	public List findParliamentForAssemblyForTheMaxOfGivenYear(Long assemblyId);
	
	public List<Object[]> findDistrictsOfParliamentConstituencies(Long parliamentId);
	
	public List findParliamentByAssemblyIdAndElectionYear(Long assemblyId, Long electionYear);
	
	public List<Object[]> findLatestParliamentForAssembly(List<Long> assemblyIds);
	
	public List<Object[]> findLatestAssemblyForParliament(List<Long> assemblyIds,Long parliamentId);
	
	public List<Long> findLatestParliamentByAssembly(Long assemblyId);
	
	public List<Long> findAllParliamentForAssembly(List<Long> assemblyIds);
	
	public List<Long> findAllParliamentForAssembliesForTheGivenYear(List<Long> assemblyIds,Long electionYear);
	
	public List<Long> findAllAssembliesForParliamentForTheGivenYear(List<Long>  assemblyIds,Long parliamentId,Long electionYear);
	
	public List<Object[]> findAllParliamentDetailsAssembliesForTheGivenYear(List<Long> assemblyIds,Long electionYear);
	
	public List<Object[]> findAssemblyInfoByDelimitationConstituencyId(Long delimitationConstituencyId);
	
	public List<Object[]> getAllAssemblyDetailsOfParliament(Long parliamentId,Long electionYear);
	
	public List<Object[]> findLatestParliamentsByAssemblyIds(List<Long> assemblyIds,List<Long> notIds);
	
	public List<Object[]> findAssParlIdsForAListOfParlConstis(List<Long> parliamentConstituencyIds,List<Long> notIds);
	public List getLatestParConstituenciesByStateIdForregion(String electionType , Long stateID,String region);
	public List<Object[]> getPcListByRegion(Long regionId);
	public List<Long> findAssembliesConstituenciesByParliament(Long parliamentConstituencyId) ;
	
	public List<Object[]> findAssembliesConstituenciesListForAListOfParliamentConstituency(List<Long> parliamentConstituencyIds);
	
	public List<Object[]> findLatestParliamentsForAssembly(List<Long> assemblyIds);
	
	public List<Long> findDistrictsOfParliamentConstituencies(List<Long> parliamentIds);
	public List<Long> findDistrictsBYParliament(Long parliamentId);
	
	
}
