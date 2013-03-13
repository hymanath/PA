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
	
	public List getLocalElectionBodyIdByConstituencyId(Long constituencyId,String type);
	
	public List getLocalElectionBodyId(Long assemblyLocalElectionBodyId);
	
	public List getAssemblyLocalElectionBodyId(Long localElectionBodyId);
	
	public List findByConstituencyIds(String constituencyIds);
	
	@SuppressWarnings("unchecked")
	public List findAssemblyLocalElectionBodyByLocalBodyAndConstituency(Long localBodyId,Long constituencyId);
	
	public Integer deleteByLocalElectionBodyAndConstituency(List<Long> localBodyIds, Long constituencyId);
	
	public Long getLocalElectionBodyIdByassemblyLocalElectionBodyId(Long assemblyLocalElectionBodyId);
	
	public List<Object[]> getLocalElecBodyName(String assemblyLocalElectionBodyId);
	
	public List<Long> getLocalElectionBodyIdByConstituency(Long constituencyId);
	
	public List<Object[]> getAssemblyLocalElectionBodyDetails(Long localElectionBodyId);
	
	public Long getTehsilValues(Long localElectionBodyId,Long constituencyId);
	
	public Long getLocalElectionBodyIdByAssemblyLocalElectionBodyId(Long assemblyLocalElectionBodyId);
}
