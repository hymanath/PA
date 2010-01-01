package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.excel.booth.VoterVO;

public interface IConstituencyManagementService {
	
	public List<VoterVO> createProblem(Long hamletId, String year);
	
}
