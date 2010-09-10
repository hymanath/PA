package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.model.AllianceGroup;

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
	public List findPartiesByGroup(Long groupId) {
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName from AllianceGroup model"+
				" where model.group.groupId = ?",groupId);
	}
	

	public List findAlliancePartiesByElectionStateAndPartyExcludeParty(Long electionId, Long partyId, Long stateId){
		Object[] params = {electionId, stateId, partyId, partyId};
		return getHibernateTemplate().find("select model.group, model.party.partyId, model.party.shortName" +
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

}
