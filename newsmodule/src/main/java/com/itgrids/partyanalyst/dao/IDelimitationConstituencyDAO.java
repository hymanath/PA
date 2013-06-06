package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituency;

public interface IDelimitationConstituencyDAO extends GenericDao<DelimitationConstituency, Long>{
	public List<DelimitationConstituency> findDelimitationConstituencyByConstituencyID(Long constituencyID);
	
	public List<DelimitationConstituency> findDelimitationConstituencyByConstituencyID(
			Long constituencyID,Long year);
	
	public List<DelimitationConstituency> findByElectionScopeIdStateIdAndElectionYear(Long electionScopeId, Long stateId, Long electionYear);

	public List<Long> getConstituenciesByDistrictID(Long districtID);
	
	//public List getDelimitationConstituenciesByDistrictID(Long districtID, Long electionYear);
	
	public List<Constituency> getLatestConstituenciesForDistrict(Long districtId);
	
	public List getLatestConstituenciesByDistrictIds(String districtIds);
	
	@SuppressWarnings("unchecked")
	public List getLatestConstituenciesForADistrict(Long districtId);
	
	public List getConstituenciesByAreaTypeInDist(Long districtId, String areaType);
	
	public List getLatestDelimitationYear();
	
	public List<Constituency> getLatestAssemblyConstituenciesInState(Long stateId);
	
	public List getLatestConstituenciesByElectionTypeInState(Long electionTypeId, Long stateId);
	public List<Object> findDelimitationConstituencyByConstituencyIDForCensus(Long constituencyID,Long delimitationYear);
	
	public List<Long> getLatestConstituenciesForDistrictBasedOnYear(Long districtId,Long year);
	
	public List<Object[]> getLatestConstituenciesByElectionTypeInDistrict(Long electionTypeId, Long districtId);
	
	public List<Object[]> getLatestConstituenciesByElectionTypeAndYearInCountry(Long electionTypeId, Long countryId,Long electionYear);
	
	public List<Object[]> getLatestConstituenciesByElectionTypeAndYearInState(Long electionTypeId, Long stateId,Long electionYear);
	
	public List<Object[]> getLatestConstituenciesByElectionTypeAndYearInADistrict(Long electionTypeId, Long districtId,Long electionYear);
	
	public List<Object[]> getLatestConstituenciesByElectionTypeAndYear(Long electionTypeId, Long constituencyId,Long electionYear);
	
	@SuppressWarnings("unchecked")
	public List getLatestConstituencyByConstituencyNameAndDistrictIdAndElectionType(String constituencyName,Long districtId,String electionType);

}
