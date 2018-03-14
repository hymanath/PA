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
import com.itgrids.model.GovtWorkStatus;

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
	public List<DocumentVO> getLocationLevelSubDayWiseKms(MobileAppInputVO inputVO);
	public GovtMainWorkVO getStateLevelOverAllDetails(Long workTypeId);
	public List<DocumentVO> getRecentWorkDocuments(Long workTypeId);
	public List<GovtWorksVO> getStatusWiseWorksAndKms(Long workTypeId);
	public List<GovtWorksVO> getLocationWiseOverview(MobileAppInputVO inputVO);
	public GovtMainWorkVO getLocationLevelWiseOverviewDetails(Long locationScopeId,Long locationValue,Long workTypeId);
	public List<WorkStatusVO> getLocationLevelStatusWiseOverviewDetails(Long locationScopeId,Long locationValue,Long workTypeId);
	public List<WorkStatusVO> getWorkZoneStatusWiseKms(Long locationScopeId,Long locationValue,Long workTypeId);
	public List<DocumentVO> getLocationOverviewStatusDayWiseKms(String fromDate,String toDate,Long locationScopeId,Long locationValue,Long workTypeId);
	
	public GovtWorksVO getWorkZoneMainOverview(Long govtWorkId);
	public List<WorkStatusVO> getWorkZoneStatusDetailsInfo(Long govtWorkId,Long worktypeid);
	public List<DocumentVO> getWorkZoneDocumentDetailsInfo(Long govtWorkId);
	public List<GovtWorksVO> getWorkZoneWorkStategsDetailsInfo(String startDateStr,String endDateStr,Long govtWorkId,Long statusId);
	
	public List<SmallVO> getAllStatusOfWorkType(Long workTypeId);
	public List<SmallVO> getAllDistrictsOfAp();
	public List<SmallVO> getDivisionsOfDistrict(Long districtId);
	public List<SmallVO> getSubDivisionsOfDivision(Long divisionId);
	public List<SmallVO> getTehsilsOfSubDivision(Long subDivisonId);
}
