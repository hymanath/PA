package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.CadreAmountDetailsVO;

public interface ILeaderCadreDashBoardService {

	public void testMethod();
	public List<CadreAmountDetailsVO> getLoationWiseLeaderCadreDetails(String locationtype,Long stateId,String accessType,String accessValue,Date fromDate,Date toDate);
	public List<CadreAmountDetailsVO> getSubLevelLoationWiseLeaderCadreDetails(String type,Long id,String accessType,String accessValue,Date fromDate,Date toDate);
	
	public List<CadreAmountDetailsVO> getLocationWiseToDayDetails(String locationtype,Long stateId,String fromTask);
	public List<CadreAmountDetailsVO> getLocationWiseAsOfNowDetails(String locationtype,Long stateId);
	public CadreAmountDetailsVO getDuplicateUsersInLocation(Date fromDate,Date toDate);
	public CadreAmountDetailsVO getUsersInLocation(Date reqFromDate,Date reqToDate,Long userId,Long locationId,String type,Long constituencyId) ;
	
}
