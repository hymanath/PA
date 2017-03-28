package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyWorkingCommitteeDesignationDAO;
import com.itgrids.partyanalyst.model.PartyWorkingCommitteeDesignation;

public class PartyWorkingCommitteeDesignationDAO extends GenericDaoHibernate<PartyWorkingCommitteeDesignation, Long> implements IPartyWorkingCommitteeDesignationDAO{

	public PartyWorkingCommitteeDesignationDAO() {
		super(PartyWorkingCommitteeDesignation.class);
		
	}

	@SuppressWarnings("unchecked")
	public List<PartyWorkingCommitteeDesignation> getDesignationsForPartyCommittee(Long partyWorkingCommitteeId) {
		
		return getHibernateTemplate().find("from PartyWorkingCommitteeDesignation model where model.partyWorkingCommittee.partyWorkingCommitteeId = ?", partyWorkingCommitteeId);
	}

	@SuppressWarnings("unchecked")
	public List getAllDesignationsForPartyCommitee() {
		return getHibernateTemplate().find("select distinct model.designation from PartyWorkingCommitteeDesignation model");
	}

	
}
