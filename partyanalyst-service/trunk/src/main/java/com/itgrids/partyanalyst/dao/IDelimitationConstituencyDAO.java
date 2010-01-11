package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.DelimitationConstituency;

public interface IDelimitationConstituencyDAO extends GenericDao<DelimitationConstituency, Long>{
	public List<DelimitationConstituency> findDelimitationConstituencyByConstituencyID(Long constituencyID);
	
	public List<DelimitationConstituency> findByElectionScopeIdStateIdAndElectionYear(Long electionScopeId, Long stateId, Long electionYear);

	public List<Long> getConstituenciesByDistrictID(Long districtID);
	
	//public List getDelimitationConstituenciesByDistrictID(Long districtID, Long electionYear);
	
	public List getLatestDelimitationYear();

	}
