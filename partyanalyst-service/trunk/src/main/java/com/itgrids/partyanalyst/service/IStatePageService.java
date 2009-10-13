/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 17, 2009
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.StateElectionResultsVO;
import com.itgrids.partyanalyst.dto.StatePageVO;
import com.itgrids.partyanalyst.model.Census;


public interface IStatePageService {

	public StatePageVO getStateDetails(Long stateId);
	
	public List<StateElectionResultsVO> getStateElectionResults(Long stateId);
	
	public List<PartyResultsVO> getElectionResults(Long electionId);
	
	public List<CensusVO> getCensusDetails(Long stateId,int year);
}
