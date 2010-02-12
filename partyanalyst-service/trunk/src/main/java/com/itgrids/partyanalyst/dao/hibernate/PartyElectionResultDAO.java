/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 08,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyElectionResultDAO;
import com.itgrids.partyanalyst.model.PartyElectionResult;

public class PartyElectionResultDAO extends GenericDaoHibernate<PartyElectionResult, Long>
		implements IPartyElectionResultDAO {

	public PartyElectionResultDAO() {
		super(PartyElectionResult.class);
	}

	@SuppressWarnings("unchecked")
	public List<PartyElectionResult> getByElectionAndParty(Long electionId,Long partyId) {
		Object[] params = {electionId,partyId};
		return getHibernateTemplate().find("from PartyElectionResult model where model.election.electionId = ? and model.party.partyId = ?", params);
	}

	
}
