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

}
