package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyWorkingCommitteeDAO;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyWorkingCommittee;

public class PartyWorkingCommitteeDAO  extends GenericDaoHibernate<PartyWorkingCommittee, Long> implements IPartyWorkingCommitteeDAO  {

	public PartyWorkingCommitteeDAO() {
		super(PartyWorkingCommittee.class);
		
	}

	@SuppressWarnings("unchecked")
	public List<PartyWorkingCommittee> getWorkingCommitteeForParty(Long partyId) {
		return getHibernateTemplate().find("from PartyWorkingCommittee model where model.party.partyId = ?", partyId);
	}

}
