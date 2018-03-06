package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.DocumentVO;
import com.itgrids.dto.GovtMainWorkVO;
import com.itgrids.dto.GovtWorksVO;
import com.itgrids.dto.LocationAddressVO;
import com.itgrids.dto.MobileAppInputVO;
import com.itgrids.dto.MobileAppLoginVO;
import com.itgrids.dto.ResultStatus;
import com.itgrids.dto.SmallVO;
import com.itgrids.dto.WorkStatusVO;

public interface IUnderGroundDrainageService {
	public MobileAppLoginVO checkLogin(MobileAppInputVO inputVO);
	//public List<GovtWorksVO> getWorkDetailsOfMobileAppUser(Long mobileAppUserId,Long workTypeId);
	public ResultStatus saveWorkDetails(GovtWorksVO govtWorksVO);
	public ResultStatus updateWorkStatus(List<WorkStatusVO> WorkStatusVOList);
	public List<WorkStatusVO> getAllTheStatusOfGovtWork(Long workId);
	public List<GovtMainWorkVO> getUsersGovtMainWorks(Long userId,Long workTypeId);
	public List<GovtWorksVO> getAllGovtWorksOfGovtMainWork(Long userId,Long mainWorkId);
	public List<DocumentVO> updateWorkStatusDocuments(List<WorkStatusVO> WorkStatusVOList);
	public List<WorkStatusVO> getStatusWiseDayReport(MobileAppInputVO inputVO);
	public List<SmallVO> getSubUsers(Long userTypeId,List<Long> userIds,Long workTypeId);
	public List<LocationAddressVO> getUsersAssignedLocations(List<Long> userIds);
	public List<SmallVO> getAllWorkZonesOfLocations(Long locationScopeId,List<Long> locationIds);
	public List<SmallVO> getImgesForMobAppDashboard(MobileAppInputVO inptVO);
	
	
	public List<GovtMainWorkVO> getWorkTypeWiseCompletedDetails();	
	public List<DocumentVO> getLocationStatusDayWiseKms(MobileAppInputVO inptuVO);
	public List<DocumentVO> getLocationLevelStatusDayWiseKms(MobileAppInputVO inputVO);
}
