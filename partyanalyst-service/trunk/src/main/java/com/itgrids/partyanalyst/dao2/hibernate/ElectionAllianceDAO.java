package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.model.ElectionAlliance;
import com.itgrids.partyanalyst.model.Group;

public class ElectionAllianceDAO extends GenericDaoHibernate<ElectionAlliance, Long> implements
IElectionAllianceDAO {

	public ElectionAllianceDAO() {
		super(ElectionAlliance.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<ElectionAlliance> findByElectionYearAndType(final String electionYear, final Long electionType) {
		Object[] params = {electionYear, electionType};
		return getHibernateTemplate().find("from ElectionAlliance as model where model.election.electionYear=? " +
				"and model.election.electionScope.electionType.electionTypeId=? ",params); 
	}
	
	@SuppressWarnings("unchecked")
	public List<ElectionAlliance> findByElectionId(Long electionId) {
		Object[] params = {electionId};
		return getHibernateTemplate().find("from ElectionAlliance as model where model.election.electionId = ?",params); 
	}
	
	@SuppressWarnings("unchecked")
	public List findGroupIdByElection(Long electionId){
		return getHibernateTemplate().find("select model.group.groupId,model.group.groupName from ElectionAlliance as model"+
				" where model.election.electionId = ?",electionId);
	}

	@SuppressWarnings("unchecked")
	public List findAllianceGroupsInElections(String electionIds){
		return getHibernateTemplate().find("select model.election.electionScope.electionType.electionType, " +
				"model.election.electionYear, model.group.groupName, model.group.groupId from ElectionAlliance model where " +
				"model.election.electionId in ("+electionIds+")");
	}

	@SuppressWarnings("unchecked")
	public List findAllGroupsForAnElection(String electionType,
			String electionYear) {
		Object[] params = {electionType,electionYear};
		return getHibernateTemplate().find("select model.group.groupName, model.group.groupId"+
				" from ElectionAlliance model where model.election.electionScope.electionType.electionType = ?"+
				" and model.election.electionYear = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ElectionAlliance> getAllianceElectionByElectionIdAndStateId(Long electionId,Long stateId){
		Object[] params = {electionId,stateId};
		return getHibernateTemplate().find("from ElectionAlliance model where model.election.electionId = ? and" +
				" model.state.stateId = ?",params);
	}
	
	public List getAllAllianceElectionYearsForAParty(Long partyId,String electionSubType,Long stateId){
		StringBuilder query = new StringBuilder();
		query.append(" select model.election.electionYear,model.election.electionId,model.election.electionScope.electionType.electionTypeId");
		query.append(" from ElectionAlliance model where ");
		query.append(" model.group.groupId in (select model2.group.groupId from AllianceGroup model2 where model2.party.partyId = ?)");	
		query.append(" and model.election.elecSubtype = ? and model.state.stateId = ?");
		query.append(" order by model.election.electionYear desc");	
					
		Query queryObject = getSession().createQuery(query.toString());	
		queryObject.setLong(0,partyId);		
		queryObject.setString(1,electionSubType);	
		queryObject.setLong(2,stateId);	
		return queryObject.list();	
	}
	
	@SuppressWarnings("unchecked")
	public List<ElectionAlliance> findByElectionYearAndType(final String electionYear, final Long electionType, final Long stateId) {
		Object[] params = {stateId,electionYear, electionType};
		return getHibernateTemplate().find("from ElectionAlliance as model where model.election.electionScope.state.stateId = ? and " +
				"model.election.electionYear=? and model.election.electionScope.electionType.electionTypeId=? ",params); 
	}
	
	public List getAllAllianceElectionYearsForAPartyByElectionIds(Long partyId,List<Long> eleIds,Long stateId){
		StringBuilder query = new StringBuilder();
		query.append(" select model.election.electionYear,model.election.electionId,model.election.electionScope.electionType.electionTypeId");
		query.append(" from ElectionAlliance model where ");
		query.append(" model.group.groupId in (select model2.group.groupId from AllianceGroup model2 where model2.party.partyId =:partyId)");	
		query.append(" and model.election.electionId in (:eleIds) and model.state.stateId =:stateId");
		query.append(" order by model.election.electionYear desc");	
					
		Query queryObject = getSession().createQuery(query.toString());	
		queryObject.setParameter("partyId",partyId);		
		queryObject.setParameterList("eleIds",eleIds);	
		queryObject.setParameter("stateId",stateId);	
		return queryObject.list();	
	}
}
