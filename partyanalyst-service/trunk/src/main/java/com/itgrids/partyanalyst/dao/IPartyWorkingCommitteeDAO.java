package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyWorkingCommittee;

public interface IPartyWorkingCommitteeDAO extends GenericDao<PartyWorkingCommittee, Long> {

	public List<PartyWorkingCommittee> getWorkingCommitteeForParty(Long partyId);
}
