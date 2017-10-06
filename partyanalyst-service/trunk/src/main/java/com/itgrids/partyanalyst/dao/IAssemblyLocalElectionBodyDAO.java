package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AssemblyLocalElectionBody;
import com.itgrids.partyanalyst.model.Constituency;

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
	
	public Long getLocalBodyIdBasedOnConstituencyId(Long constituencyId);
	
	public List getAssemblyLocalElectionBodyIdsList(Long localElectionBodyId,Long constituencyId);
	
	public List<Object[]> getTehsilsForUrbanConstituency(List<Long> constituencyIds,String year);
	
	public List<Object[]> geLocalElectionBodyListForVotersAnalysis(Long constituencyId);
	
	public String getElectionTypeForMuncipality(Long constituencyId,Long localEleBodyId);
	
	public List<Long> getLocalEleBodyIdsListByConstituencyId(Long constituencyId,Long publicationDateId);
	
	public Object[] getMuncipalityDetails(Long constituencyId);
	
	public Long getAssemblyLocalElectionBodyIdByConstituency(Long constituencyId);
	
	public List<Object[]> getAssemblyLocationElectionBodyList(Long constituencyId);
	
	public List getLocalElectionBodyName(Long assemblyLocalElectionBodyId);
	
	public List<Object[]> getAssLocalEleBodyIdAndLocalEleBodyIdByConstituency(Long constituencyId);
	
	public List<Object[]> findByConstituencyIdForUrbanType(Long constituencyId,Long urbanTypeId);
	
	public List<Long> getLEBIdsByALEBIds(List<Long> assemblyLocalElectionBodyIds);
	public List<Object[]> getGHMCConstituencies();
	public List<Object[]> getAllLocalBodiesInAConstituency(Long constituencyId);
	
	public List<Object[]> getGreaterCitiesConstituencies();
	public List<Object[]> getAllLocalBodiesInAConstituencyList(List<Long> constituencyIds);
	
	public List<Long> getConstituencyIdByAssemblyLocalEleBodyId(Long localEleBodyId);
	public List<Object[]> getConstitencyWiseTowns(Long constituencyId);
	public List<Object[]> getLocalElectionBodyStateWise(Long stateId,List<Long> localElectionBodyIds);
	public List<Long> getLocalElectionBodyIdsStateWise(Long stateId);
	public List<Constituency> getConstituencyByAssemblyLocalEleBodyId(Long localEleBodyId);
	public List<Object[]> getMuuncipalityByConstituency(List<Long> constituencyIds);
	
}
