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
	
	public List<Long> findWardsByAssemblyLocalBody(Long localElectionBodyId, String year);
	
	public List<Object[]> findWardsByLocalBodyConstiId(Long localElectionBodyId, Long constituency);
	
	public List<Object[]> findWardsByLocalBodyIds(List<Long> localElectionBodyIds);
	
	public List<Object[]> findWardsByLocalBodyIdsAndConstituencyIds(List<Long> localElectionBodyIds, List<Long> constituencyIds) ;
	public List<Long> getWardsByconstituency(Long constituencyId);
	public List<Object[]> findWardsByLocalBodyConstiIds(List<Long> localElectionBodyId, List<Long> constituency) ;
	public List<Object[]> getWardDetailsById(List<Long> locationIds);
	public List<Object[]> findWardsByLocalBodyConstituncyListIds(Long localElectionBodyId, List<Long> constituencyIdsList) ;
	public List<Object[]> getWardsInLocalElectionBody(List<Long> localBodyIds,Long constituencyId);
}
