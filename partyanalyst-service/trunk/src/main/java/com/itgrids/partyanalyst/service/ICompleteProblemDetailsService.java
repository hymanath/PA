package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.CompleteProblemDetailsVO;

public interface ICompleteProblemDetailsService {
	public CompleteProblemDetailsVO getProblemCompleteDetails(Long problemId,Long userId,String userStatus);
}
