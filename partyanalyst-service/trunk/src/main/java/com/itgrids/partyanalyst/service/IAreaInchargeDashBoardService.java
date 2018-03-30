package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AreaInchargeVO;
import com.itgrids.partyanalyst.dto.ResultStatus;




public interface IAreaInchargeDashBoardService {
	public AreaInchargeVO getAreaInchargeDetails(Long voterId,String mobileNo,String memberShipId);
	public ResultStatus savingAssigningBooths(final Long cadreId, final List<Long> boothIds);
	public AreaInchargeVO getAssignedAndUnAssignedBooths(Long levelId,Long levelValue);
	public AreaInchargeVO editAssignedInchargeDetails(Long tdpCadreId,Long levelId,Long levelValue);
	public String deleteAreaInchargeAssignBooths(Long candidateId,Long boothId);
	public String removeAreaIncharge(Long candidateId);
	public List<AreaInchargeVO> getAreaInchargeAssignedBoothDetails(Long levelId,Long levelValue);
	public AreaInchargeVO getAreaInchargesStatusWiseCount(Long levelId,Long levelValue);
	
}
