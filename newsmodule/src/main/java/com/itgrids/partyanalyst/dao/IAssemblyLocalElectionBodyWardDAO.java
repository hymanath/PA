package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AssemblyLocalElectionBodyWard;

public interface IAssemblyLocalElectionBodyWardDAO extends GenericDao<AssemblyLocalElectionBodyWard, Long> {
	@SuppressWarnings("unchecked")
	public List findByLocalElectionBody(Long localElectionBodyId, String year);
	
	public List findByConstituencyIdAndYear(Long constituencyId, String wardType);
	
	public List findByAssemblyLocalElectionBodyWardAndYear(Long assemblyLocalElectionBodyId, Long wardId, String year);
	
	public List findByAssemblyLocalElectionBody(Long localElectionBodyId, String year);
	
	public Integer deleteByWardsAndConstituency(List<Long> assemblyLocalElectionBodyWardIds);
	
	public List getAssemblyLocalElectionBodyWardIds(Long constituencyId, List<Long> wardIds);
	
	public List<Object[]> getWardsByAssemblyLocalElectionBody(Long id);
	
	public List<Long> getWardIdsByAssemblyLocalElectionBody(Long id,Long constituencyId);
}
