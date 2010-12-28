package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ApprovalInfoVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IDataApprovalService {

	ResultStatus saveProblemsApprovalData(ApprovalInfoVO approvalInfoVOToSave);
	List<ApprovalInfoVO> getAllProblemComments (Long problemHistoryId) throws Exception;
}
