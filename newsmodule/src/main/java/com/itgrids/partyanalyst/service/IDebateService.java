package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.DebateVO;

public interface IDebateService {

	public DebateVO getDebateDetailsForSelected(Long debateId);
}
