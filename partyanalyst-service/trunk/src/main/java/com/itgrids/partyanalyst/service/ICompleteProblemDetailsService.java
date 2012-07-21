package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CompleteProblemDetailsVO;
import com.itgrids.partyanalyst.dto.UserCommentsInfoVO;

public interface ICompleteProblemDetailsService {
	public CompleteProblemDetailsVO getProblemCompleteDetails(Long problemId,Long userId,String userStatus,String queryReq);
	public List<UserCommentsInfoVO> getPostedComments(final Long problemId,final Long userId);
}
