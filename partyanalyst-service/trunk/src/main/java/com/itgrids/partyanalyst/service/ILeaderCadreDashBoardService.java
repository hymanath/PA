package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreAmountDetailsVO;
import com.itgrids.partyanalyst.dto.CallCenterVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;

public interface ILeaderCadreDashBoardService {

	public void testMethod();
	public List<CadreAmountDetailsVO> getLoationWiseLeaderCadreDetails(String locationtype,Long stateId);
	public List<CadreAmountDetailsVO> getSubLevelLoationWiseLeaderCadreDetails(String type,Long id);
	
	public List<CadreAmountDetailsVO> getLocationWiseToDayDetails(String locationtype,Long stateId,String date);
	public List<CadreAmountDetailsVO> getLocationWiseAsOfNowDetails(String locationtype,Long stateId);
	
}
