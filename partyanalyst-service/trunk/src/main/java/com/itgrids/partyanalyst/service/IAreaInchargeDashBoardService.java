package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AreaInchargeVO;
import com.itgrids.partyanalyst.dto.InchargeMemberVO;
import com.itgrids.partyanalyst.dto.ResultStatus;




public interface IAreaInchargeDashBoardService {
	public List<AreaInchargeVO> getAreaInchargeDetails(Long voterId,String mobileNo,String memberShipId);
	public ResultStatus savingAssigningBooths(final Long cadreId, final List<String> boothIds,final Long levelId,final Long levelValue);
	public AreaInchargeVO getAssignedAndUnAssignedBooths(Long levelId,Long levelValue);
	public AreaInchargeVO editAssignedInchargeDetails(Long tdpCadreId,Long levelId,Long levelValue);
	public String deleteAreaInchargeAssignBooths(Long candidateId,String boothId,Long levelId,Long levelValue);
	public List<AreaInchargeVO> getAreaInchargeAssignedBoothDetails(Long levelId,Long levelValue);
	public AreaInchargeVO getAreaInchargesStatusWiseCount(Long levelId,Long levelValue);
	public List<InchargeMemberVO> getBoothAssignedpercentageBaseConstituencies(String fromDateStr,String toDateStr,Long activityMemberId);
	
}
