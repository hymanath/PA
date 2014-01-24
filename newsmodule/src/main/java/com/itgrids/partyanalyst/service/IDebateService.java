package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.DebateDetailsVO;
import com.itgrids.partyanalyst.dto.DebateVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IDebateService {

	public DebateVO getDebateDetailsForSelected(Long debateId);
	
	public ResultStatus saveDebateDetails(final DebateDetailsVO debateDetailsVO);
}
