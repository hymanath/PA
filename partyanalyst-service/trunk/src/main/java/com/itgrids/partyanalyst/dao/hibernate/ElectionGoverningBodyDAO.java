package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

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
		return getHibernateTemplate().find("select model from ElectionGoverningBody model where model.election.electionId = ? order by model.candidate.lastname,positionScope.electionGoverningBodyPosition.governingBodyPosition",electionId);
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
	
	public List checkForPositionExistanceForCandidate(Long electionId,Long candidateId ,Long positionScopeId,Long partyId,String workingStatus	){
		
		Query queryObject = getSession().createQuery("select model.governingElectionBodyId from ElectionGoverningBody model "+
		"where model.election.electionId = ? and model.candidate.candidateId = ? and model.positionScope.positionScopeId = ? "+
				"and model.party.partyId = ? and model.status = ?"); 
		
		queryObject.setParameter(0, electionId);
		queryObject.setParameter(1, candidateId);
		queryObject.setParameter(2, positionScopeId);
		queryObject.setParameter(3, partyId);
		queryObject.setParameter(4, workingStatus);
		
		return queryObject.list();
		
					
	}
	
	public int updateCandidatePositionDetails(Long electionGoverningBodyId,Date	toDate,String workingStatus){
		
		Query queryObject = getSession()
				.createQuery(
						"update ElectionGoverningBody model set model.status = ? , model.toDate = ? where model.governingElectionBodyId = ?");
		
		
		queryObject.setParameter(0,workingStatus );
		queryObject.setParameter(1, toDate);
		queryObject.setParameter(2, electionGoverningBodyId);
		
		return queryObject.executeUpdate();
		
		
	}
	
	public List checkForMinisterData(String electionType,Long electionId){
		
		Query query = getSession().createQuery("select count(model.governingElectionBodyId) from ElectionGoverningBody model where model.election.electionId = ? and model.election.electionScope.electionType.electionType = ?");
		
		query.setParameter(0, electionId);
		query.setParameter(1, electionType);
		
		return query.list();
		
		
	}
}
