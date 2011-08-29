package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CallCenterVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;

public interface ICallCenterService {

	public List<ProblemBeanVO> getProblemDetails(CallCenterVO callCenterVO);
	
}
