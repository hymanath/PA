/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 21, 2010
 */
package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.LocalBodyElectionResultsVO;

/*
 * Interface for Local Body Elections 
 */
public interface ILocalBodyElectionService {

	public LocalBodyElectionResultsVO getLocalBodyElectionResultsByLocalBodyTypeAndYear(Long localBodyId,Long stateId);
}
