package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.model.AllianceGroup;
import com.itgrids.partyanalyst.model.Party;

public class AllianceGroupDAO extends GenericDaoHibernate<AllianceGroup, Long> implements IAllianceGroupDAO {

	public AllianceGroupDAO() {
		super(AllianceGroup.class);
	}

	@SuppressWarnings("unchecked")
	public List<AllianceGroup> findByGroupId(final Long groupId) {
		return getHibernateTemplate().find("from AllianceGroup as model where model.group.groupId=?",groupId);
	}
	
	@SuppressWarnings("unchecked")
	public List findPartysByGroupId(Long groupId){
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName from AllianceGroup as model where model.group.groupId=?",groupId);
	}
	
	public List findAlliancePartiesByElectionAndParty(Long electionId, Long partyId){
		Object[] params = {electionId, partyId};
		return getHibernateTemplate().find("select model.group.groupId, model.group.groupName, model.party.partyId, model.party.shortName" +
				" from AllianceGroup model where model.group.groupId = (select model1.group.groupId from AllianceGroup model1 where" +
				" model1.group.groupId in(select model2.group.groupId from ElectionAlliance model2 where model2.election.electionId = "+electionId+") " +
				"and model1.party.partyId = "+partyId+")");
	}
	
	@SuppressWarnings("unchecked")
	public List<Party> findAlliancePartiesByElectionAndPartyForState(Long electionId, Long partyId, Long stateId){
		Object[] params = {electionId, stateId, partyId};
		return getHibernateTemplate().find("select model.party from AllianceGroup model where model.group.groupId = " +
				" (select model1.group.groupId from AllianceGroup model1 where" +
				" model1.group.groupId in(select model2.group.groupId from ElectionAlliance model2 where " +
				"model2.election.electionId = ? and model2.state.stateId = ?) and model1.party.partyId = ?)",params);
	}

	@SuppressWarnings("unchecked")
	public List findPartiesByGroup(Long groupId) {
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName from AllianceGroup model"+
				" where model.group.groupId = ?",groupId);
	}
	

	public List findAlliancePartiesByElectionStateAndPartyExcludeParty(Long electionId, Long partyId, Long stateId){
		Object[] params = {electionId, stateId, partyId, partyId};
		return getHibernateTemplate().find("select model.group, model.group.groupName, model.party.partyId, model.party.shortName" +
				" from AllianceGroup model where model.group.groupId = (select model1.group.groupId from AllianceGroup model1 where" +
				" model1.group.groupId in(select model2.group.groupId from ElectionAlliance model2 where model2.election.electionId = ? "+
						"and model2.state.stateId = ?) and model1.party.partyId = ?) and model.party.partyId != ?", params);
	}
	
	public List findAlliancePartiesByElectionAndPartyExcludeParty(Long electionId, Long partyId){
		Object[] params = {electionId, partyId, partyId};
		return getHibernateTemplate().find("select model.group, model.party.partyId, model.party.shortName" +
				" from AllianceGroup model where model.group.groupId = (select model1.group.groupId from AllianceGroup model1 where" +
				" model1.group.groupId in(select model2.group.groupId from ElectionAlliance model2 where model2.election.electionId = ?) " +
				"and model1.party.partyId = ?) and model.party.partyId != ?", params);
	}
	
	
	public List findAlliancePartiesByElectionStateAndPartyExcludeParty(List<Long> electionIds, Long partyId, Long stateId){
		StringBuilder query = new StringBuilder();
		query.append(" select model.group, model.group.groupName, model.party.partyId, model.party.shortName");
		query.append(" from AllianceGroup model where model.party.partyId != ? and model.group.groupId = ");
		query.append("(select model1.group.groupId from AllianceGroup model1 where model1.party.partyId = ? and");
		query.append(" model1.group.groupId in(select model2.group.groupId from ElectionAlliance model2 where model2.state.stateId = ? ");
		query.append(" and model2.election.electionId in (:electionIds) )) ");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setLong(0,partyId);
		queryObject.setLong(1,partyId);
		queryObject.setLong(2,stateId);
		queryObject.setParameterList("electionIds", electionIds);
		
		return queryObject.list();	
	}
	
}
