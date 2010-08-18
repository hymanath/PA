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
	public List<PartyWorkingCommitteeDesignation> getDesignationsForPartyCommittee(
			Long partyId, Long partyWorkingCommitteeId) {
		Object params[] = {partyId, partyWorkingCommitteeId};
		return getHibernateTemplate().find("from PartyWorkingCommitteeDesignation model where model.partyWorkingCommittee.party.partyId = ? and model.partyWorkingCommittee.partyWorkingCommitteeId = ?", params);
	}

	
}
