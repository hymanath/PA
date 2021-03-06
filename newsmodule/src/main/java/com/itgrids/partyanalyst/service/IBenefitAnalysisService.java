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
	 
	public List<BenfitVO> getDistrictWiseBenifit(Date fromDate,Date toDate,Long stateId,Long partyId);
	 
	public List<BenfitVO> getConstituencyWiseBenifit(Date fromDate,Date toDate,Long stateId,Long partyId,String name);
	
	public List<NewsActivityVO> getLocationBenifitNews(Date fromDate,Date toDate,Long locationId,Long benfitId,int startIndex,int maxIndex,String name,Long partyId);
	
	public NewsActivityVO getAllBenefitNewsDetails(Date fromDate,Date toDate,Long partyId,Long benfitId,List<Long> districtIds,List<Long> acIds,List<Long> pcIds,List<Long> categoryIds,List<Long> candidateGrp1Ids,List<Long> candidateGrp2Ids,List<Long> candidateGrp3Ids,Long stateId);
}
