package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.model.ElectionAlliance;

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
	
	public List<Object[]> getSegregateAlianceParties(List<String> subTypes,List<Long> electionYear,List<Long> electionScopeIds){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT e.election_id as electionId,et.election_type as electionType ,e.election_year as electionYear" +
				",g.group_id as groupId,g.group_name as groupName,ag.party_id as partyId,p.short_name as partyName ");
		sb.append(" from  election_alliances ea, groups g, alliance_groups ag ,election e ,election_scope es,election_type et,party p ");
		sb.append("where " +
				"e.election_scope_id = es.election_scope_id and  es.election_type_id = et.election_type_id and ");
		sb.append(" ea.election_id = e.election_id and ea.group_id  =g.group_id and ag.group_id = g.group_id and ag.party_id =p.party_id " );
		if(subTypes != null && subTypes.size() >0)
			sb.append(" and e.sub_type in (:subTypes) ");
		if(electionYear != null && electionYear.size() >0)
			sb.append(" and e.election_year in (:electionYear) ");
		if(electionScopeIds != null && electionScopeIds.size() >0L)
			sb.append(" and e.election_scope_id in (:electionScopeIds)  ");
		Query query=getSession().createSQLQuery(sb.toString())
				.addScalar("electionId", Hibernate.LONG)
				.addScalar("electionType",Hibernate.STRING)
				.addScalar("electionYear",Hibernate.STRING)
				.addScalar("groupId",Hibernate.LONG)
				.addScalar("groupName",Hibernate.STRING)
				.addScalar("partyId",Hibernate.LONG)
				.addScalar("partyName",Hibernate.STRING);
		if(subTypes != null && subTypes.size() >0)
		query.setParameterList("subTypes", subTypes);
		if(electionYear != null && electionYear.size() >0)
			query.setParameterList("electionYear", electionYear);
		if(electionScopeIds != null && electionScopeIds.size() >0L)
			query.setParameterList("electionScopeIds", electionScopeIds);
		return query.list();
	}
}
