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
}
