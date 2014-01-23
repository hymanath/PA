package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.DebateVO;

public interface IDebateService {

	public List<DebateVO> getDebateDetailsForSelected(Long debateId);
}
