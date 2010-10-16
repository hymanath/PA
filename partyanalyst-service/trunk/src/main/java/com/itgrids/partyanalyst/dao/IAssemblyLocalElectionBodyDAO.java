package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AssemblyLocalElectionBody;

public interface IAssemblyLocalElectionBodyDAO  extends GenericDao<AssemblyLocalElectionBody, Long> {
	
	@SuppressWarnings("unchecked")
	public List findByConstituencyId(Long constituencyId);
	//List findWardsByLocalElectionBody(Long LocalElectionBodyId, String year);
	
	public List<Long> getAllLocalElectionBodiesForAConstituencyForLatestElectionYear(List<Long> constituencyIds);
	
	@SuppressWarnings("unchecked")
	List findConstituencyByLocalELectionBody(Long LocalElectionBodyId, String year);

	public List<AssemblyLocalElectionBody> findByAssemblyLocalBodyAndYear(
			Long localBodyId, Long assemblyId, String year);
	
	
}
