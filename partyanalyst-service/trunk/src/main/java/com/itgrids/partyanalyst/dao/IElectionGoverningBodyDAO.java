package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dao.hibernate.ElectionGoverningBodyDAO;
import com.itgrids.partyanalyst.model.ElectionGoverningBody;

public interface IElectionGoverningBodyDAO extends GenericDao<ElectionGoverningBody, Long>{

	public List<Object[]> getAllMinistersIdsAndMinistry(Long electionId);
	
	public List<Object[]> getAllStatesForMinisters();
	
	public List<Object[]> getAllYearsAndElecIdsForAssembly(Long stateId);
	
	public List<Object[]> getAllYearsAndElecIdsForParliament();
	
	public List<ElectionGoverningBody> getAllCandidateDetails(Long candidateId);
	
	public List<ElectionGoverningBody> getAllMinistersDetails(Long electionId);
	
	public List<Object[]> getMinistryYearsForAssembly(Long stateId);
	
	public List<Object[]> getMinistryYearsForParliament();
	
	
}
