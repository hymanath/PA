package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AssemblyLocalElectionBody;

public interface IAssemblyLocalElectionBodyDAO  extends GenericDao<AssemblyLocalElectionBody, Long> {
	
	@SuppressWarnings("unchecked")
	List findByConstituencyId(Long constituencyId, String year);
	//List findWardsByLocalElectionBody(Long LocalElectionBodyId, String year);
	
	public List<Long> getAllLocalElectionBodiesForAConstituencyForLatestElectionYear(List<Long> constituencyIds);
	
}
