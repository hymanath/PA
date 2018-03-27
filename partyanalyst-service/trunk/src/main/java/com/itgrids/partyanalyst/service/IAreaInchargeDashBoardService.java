package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AreaInchargeVO;
import com.itgrids.partyanalyst.dto.ResultStatus;




public interface IAreaInchargeDashBoardService {
	public AreaInchargeVO getAreaInchargeDetails(Long voterId,String mobileNo,Long memberShipId);
	public ResultStatus savingAssigningBooths(final Long cadreId, final List<Long> boothIds);
	public AreaInchargeVO getAssignedAndUnAssignedBooths();
	
}
