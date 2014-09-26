package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.BenfitVO;

public interface IBenefitAnalysisService {
	
	public List<BenfitVO> getCategoryWiseBenifit(Date fromDate,Date toDate,Long stateId,List<Long> partyIds);
	
	public List<BenfitVO> getCandidateGroupWiseBenifit(Long groupId,Date fromDate,Date toDate,Long stateId,Long partyId);
}
