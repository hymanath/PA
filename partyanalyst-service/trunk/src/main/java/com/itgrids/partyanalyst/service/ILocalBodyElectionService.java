/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 21, 2010
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.LocalBodyElectionResultsVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultsInConstituencyVO;

/*
 * Interface for Local Body Elections 
 */
public interface ILocalBodyElectionService {

	public LocalBodyElectionResultsVO getLocalBodyElectionResultsByLocalBodyTypeAndYear(Long localBodyId,Long stateId);
	
	public List<PartyElectionResultsInConstituencyVO> getLocalBodyElectionResultsInAnElection(Long localBodyId,Long stateId,Long electionId);
	
	public List<PartyElectionResultsInConstituencyVO> getLocalBodyElectionResultsForAPartyInAnElection(Long localBodyId,Long stateId,Long electionId,Long partyId);
	
	public List<PartyElectionResultsInConstituencyVO> getLocalBodyElectionResultsForAWardInAnElection(Long localBodyId, Long stateId, Long electionId, Long wardId);
}
