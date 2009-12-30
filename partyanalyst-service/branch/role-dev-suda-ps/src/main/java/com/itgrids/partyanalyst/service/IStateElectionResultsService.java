/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 06, 2009
 */
package com.itgrids.partyanalyst.service;


import com.itgrids.partyanalyst.dto.StateElectionResultsVO;

public interface IStateElectionResultsService {

	public StateElectionResultsVO getStateElectionResults(Long electionId);
}