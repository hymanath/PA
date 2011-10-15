package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.ApprovalInfoVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IDataApprovalService {

	ResultStatus saveProblemsApprovalData(ApprovalInfoVO approvalInfoVOToSave);
		ProblemBeanVO getAllProblemComments (Long problemHistoryId, int startIndex, int maxResult) throws Exception;
	public List<ApprovalInfoVO> userApprovalDetailsbetweenDates(String fromDate,String toDate);
	public List<ApprovalInfoVO> scrutinizePostedApprovals(List<Long> approvalDetailsIds,String approvedStatus); 
	public ProblemBeanVO getCountOfPosts(Long problemHistoryId);
	public ResultStatus checkApprovalStatus(ApprovalInfoVO approvalInfoVO);
	public Boolean checkUserFileUploadRight(Long userId, Long problemHistoryId);
}
