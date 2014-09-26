package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.BenfitVO;
import com.itgrids.partyanalyst.dto.NewsActivityVO;

public interface IBenefitAnalysisService {
	
	public List<BenfitVO> getCategoryWiseBenifit(Date fromDate,Date toDate,Long stateId,List<Long> partyIds);
	
	public List<BenfitVO> getCandidateGroupWiseBenifit(Long groupId,Date fromDate,Date toDate,Long stateId,Long partyId);
	
	public List<NewsActivityVO> getCandidateGroupWiseBenifitNews(Date fromDate,Date toDate,Long candidateId,Long benfitId,int startIndex,int maxIndex);
	
	public List<BenfitVO> getCandidateGroups();

	 public List<NewsActivityVO> getCategoryBenifitWiseNews(Date fromDate,Date toDate,Long partyId,Long categoryId,Long benfitId,Long stateId);
}
