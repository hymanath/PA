package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyWorkingCommitteeDesignation;

public interface IPartyWorkingCommitteeDesignationDAO extends GenericDao<PartyWorkingCommitteeDesignation , Long> {
	public List<PartyWorkingCommitteeDesignation> getDesignationsForPartyCommittee(Long partyWorkingCommitteeId);
	
	@SuppressWarnings("unchecked")
	public List getAllDesignationsForPartyCommitee();
}
