/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 08,2010
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyElectionResult;

public interface IPartyElectionResultDAO extends GenericDao<PartyElectionResult, Long> {
	
	public List<PartyElectionResult> getByElectionAndParty(Long electionId,Long partyId);

}
