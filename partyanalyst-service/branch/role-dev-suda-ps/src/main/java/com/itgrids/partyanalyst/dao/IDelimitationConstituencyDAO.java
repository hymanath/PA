package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.DelimitationConstituency;

public interface IDelimitationConstituencyDAO extends GenericDao<DelimitationConstituency, Long>{
	public List<DelimitationConstituency> findDelimitationConstituencyByConstituencyID(Long constituencyID);
	
	public List<DelimitationConstituency> findByElectionScopeIdStateIdAndElectionYear(Long electionScopeId, Long stateId, Long electionYear);

	public List getConstituenciesByDistrictID(Long districtID);
}
