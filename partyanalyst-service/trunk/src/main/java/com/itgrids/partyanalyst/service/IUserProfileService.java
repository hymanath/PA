package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.PublicProfileStreemVO;
import com.itgrids.partyanalyst.dto.UserProfileVO;

public interface IUserProfileService {
	public List<UserProfileVO> getPartyAnalystLatestUpdates(Date fromDate,Date toDate,Long userId);
	
	public List<ProblemBeanVO> getStreamingDataForPublicProfileByProfileId(Long userId,int startIndex, int maxIndex);
	
	public String getUserConnectStatus(Long profileId, Long userId);
	
	public ProblemBeanVO getRecentConnectedPeople(Long userId);
	
	public List<CandidateVO> getBlockRequestDetails(Long userId);
	
	public List<CandidateVO> getCandidatesToSubscribe(Long userId,Long stateId,String name,String type,Integer startIndex,Integer endIndex);\
	
	public List<PublicProfileStreemVO> getPublicProfileDataStreaming(Long userId,Date todate,Date fromDate);
	
	public Long getUserAcessViw(Long userId);
	
	public Long checkWeaterUserConnectedOrNot(Long sourcrId,Long targetId);
	
	public Long getUserTypeAcessViw(Long userId);
}
