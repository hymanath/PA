package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CompleteProblemDetailsVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UserCommentsInfoVO;

public interface ICompleteProblemDetailsService {
	
	public CompleteProblemDetailsVO getProblemCompleteDetails(Long problemId,Long userId,String userStatus,String queryReq);
	
	public List<UserCommentsInfoVO> getPostedComments(final Long problemId,final Long userId);
	
	public FileVO getProbleFileDetailsByProblemFileId(Long problemFileId);
	
	public ResultStatus upDateProbleFileDetails(Long fileId, String fileTitle, String fileDescription);
	
}
