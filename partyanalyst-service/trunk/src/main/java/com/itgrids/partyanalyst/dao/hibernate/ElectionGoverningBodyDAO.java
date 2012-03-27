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
	
	@SuppressWarnings("unchecked")
	public List<ElectionGoverningBody> getAllCandidateDetails(Long candidateId)
	{
		return getHibernateTemplate().find("select model from ElectionGoverningBody model where (model.election.electionScope.electionType.electionType='Assembly' or " +
				" model.election.electionScope.electionType.electionType='Parliament') and model.candidate.candidateId = ? order by model.fromDate desc ",candidateId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllStatesForMinisters()
	{
		
		return getHibernateTemplate().find("select distinct model.election.electionScope.state.stateId,model.election.electionScope.state.stateName " +
				"  from ElectionGoverningBody model where model.election.electionScope.electionType.electionType = ? order by model.election.electionScope.state.stateName","Assembly");
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllYearsAndElecIdsForAssembly(Long stateId)
	{
		
		return getHibernateTemplate().find("select distinct model.election.electionId,model.election.electionYear from ElectionGoverningBody model " +
				"   where model.election.electionScope.state.stateId = ? order by model.election.electionYear ",stateId);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllYearsAndElecIdsForParliament()
	{
		
		return getHibernateTemplate().find("select distinct model.election.electionId,model.election.electionYear from ElectionGoverningBody model " +
				"   where model.election.electionScope.electionType.electionType = ? order by model.election.electionYear ","Parliament");
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ElectionGoverningBody> getAllMinistersDetails(Long electionId)
	{
		return getHibernateTemplate().find("select model from ElectionGoverningBody model where model.election.electionId = ?",electionId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getMinistryYearsForAssembly(Long stateId)
	{
		return getHibernateTemplate().find("select distinct(model.election.electionId),model.election.electionYear from ElectionGoverningBody model where " +
				" model.election.electionScope.electionType.electionType = 'Assembly' and model.election.electionScope.state.stateId = ?",stateId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getMinistryYearsForParliament()
	{
		return getHibernateTemplate().find("select distinct(model.election.electionId),model.election.electionYear from ElectionGoverningBody model where " +
				" model.election.electionScope.electionType.electionType = 'Parliament'");
	}
	public List<Object[]> getChiefMinisters(Long stateId){
		return getHibernateTemplate().find("select model.candidate.candidateId ,model.candidate.lastname,model.party.partyId,model.party.shortName,model.fromDate,model.election.electionId from ElectionGoverningBody model where model.positionScope.electionGoverningBodyPosition.governingBodyPosition = 'Chief Minister' and model.status = 'Working' and model.election.electionScope.state.stateId=?",stateId);
	}
}
