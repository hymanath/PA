package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.CadreAmountDetailsVO;
import com.itgrids.partyanalyst.dto.CadreDataAnalysisVO;
import com.itgrids.partyanalyst.dto.TabRecordsStatusVO;

public interface ILeaderCadreDashBoardService {

	public void testMethod();
	public List<CadreAmountDetailsVO> getLoationWiseLeaderCadreDetails(String locationtype,Long stateId,String accessType,String accessValue,Date fromDate,Date toDate);
	public List<CadreAmountDetailsVO> getSubLevelLoationWiseLeaderCadreDetails(String type,Long id,String accessType,String accessValue,Date fromDate,Date toDate);
	
	public List<CadreAmountDetailsVO> getLocationWiseToDayDetails(String locationtype,Long stateId,String fromTask,String accessValue,Long accessType);
	public List<CadreAmountDetailsVO> getLocationWiseAsOfNowDetails(String locationtype,Long stateId,String accessValue,Long accessType);
	public CadreAmountDetailsVO getDuplicateUsersInLocation(Date fromDate,Date toDate);
	
	public List<CadreAmountDetailsVO> getYouthMahilaInfo(String locationtype,Long stateId,String accessType,String accessValue,Date fromDate,Date toDate);
	public List<CadreAmountDetailsVO> getSubLevelLoationWiseYouthMahilaInfo(String type,Long id,String accessType,String accessValue,Date fromDate,Date toDate);
	public CadreAmountDetailsVO getCasteWiseDetails(Long stateId,String locationtype);
	public CadreAmountDetailsVO getSubLoctaionCasteWiseDetails(Long stateId,String locationtype,Long Id);
	public CadreAmountDetailsVO getUsersInLocation(Long userId,Date fromDate,Date toDate,Long locationId,Long constituencyId,String type);
	public List<CadreDataAnalysisVO> getCadreBoothAnalysisReport(Long stateId);
	public List<CadreDataAnalysisVO> getBoothInfo(List<Long> boothIds,Long constituencyId);
	public List<CadreAmountDetailsVO> getBoothWiseDetails(Long constituencyId);
	//public  TabRecordsStatusVO getMISReport(String batchCode);
	public  TabRecordsStatusVO getMISReport(String batchCode,Long Id,String type); 
	public List<CadreAmountDetailsVO> getLocationWiseAsOfNowDetailsInfo(String locationtype,Long stateId,String accessType,Long accessValue);
}
