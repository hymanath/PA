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
}
