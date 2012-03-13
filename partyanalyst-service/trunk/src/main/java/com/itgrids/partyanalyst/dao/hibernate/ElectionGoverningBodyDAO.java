package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IElectionGoverningBodyDAO;
import com.itgrids.partyanalyst.model.ElectionGoverningBody;

public class ElectionGoverningBodyDAO extends GenericDaoHibernate<ElectionGoverningBody, Long>
											implements IElectionGoverningBodyDAO{

	public ElectionGoverningBodyDAO(){
		super(ElectionGoverningBody.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllMinistersIdsAndMinistry(Long electionId)
	{
		
		return getHibernateTemplate().find("select model.candidate.candidateId,model.candidate.lastname,model.positionScope.electionGoverningBodyPosition.governingBodyPosition ," +
				" model.party.partyId ,model.party.shortName from ElectionGoverningBody model where model.election.electionId = ?",electionId);
	}
	public List<Object[]> getAllStatesForMinisters()
	{
		
		return getHibernateTemplate().find("select distinct model.election.electionScope.state.stateId,model.election.electionScope.state.stateName " +
				"  from ElectionGoverningBody model where model.election.electionScope.electionType.electionType = ? order by model.election.electionScope.state.stateName","Assembly");
	}
	public List<Object[]> getAllYearsAndElecIdsForAssembly(Long stateId)
	{
		
		return getHibernateTemplate().find("select distinct model.election.electionId,model.election.electionYear from ElectionGoverningBody model " +
				"   where model.election.electionScope.state.stateId = ? order by model.election.electionYear ",stateId);
	}
	public List<Object[]> getAllYearsAndElecIdsForParliament()
	{
		
		return getHibernateTemplate().find("select distinct model.election.electionId,model.election.electionYear from ElectionGoverningBody model " +
				"   where model.election.electionScope.electionType.electionType = ? order by model.election.electionYear ","Parliament");
	}
}
