package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LedOverviewVo;
import com.itgrids.dto.LightMonitoringVO;
import com.itgrids.dto.ResultVO;
import com.itgrids.model.LightsVendor;


public interface ILightMonitoring {
	
	public List<LightMonitoringVO> getBasicLedOverviewDetails(String fromDate,String toDate,String locationType,Long locationValues,List<Long> lightVendorIdList);	
    public ResultVO saveRealtimeStatusByVillages();
    public List<LedOverviewVo> getLedOverviewForStartedLocationsDetailsCounts(String startDate,String endDate,String locationType, Long locationValues,List<Long> lightVendorIdList);
	public List<LightMonitoringVO> getAllLevelWiseDataOverView(String locationType,String filterType, List<Long> locationIds,String fromDateStr,String toDateStr,List<Long> lightVendorIdList);
	public List<LightMonitoringVO> getLocationBasedOnSelection(String locationType,String filterType, List<Long> filterValues,String subLocationType,String fromDateStr,String toDateStr,List<Long> lightVendorIdList);
	public InputVO checkIdDataExist(String startDate,String endDate);
	public LightMonitoringVO getCompanyWiseLightMonitoringDtls(String fromDate,String toDate,String locationType,List<Long> locationValues,List<Long> lightVendorIds);
	public List<LightsVendor> getLightsVendorList();
	public List<LightMonitoringVO> getTimePeriodWiseLightsDetails(String startDate,String endDate, String locationType, List<Long> locationValues,List<Long> lightMonitoringIds);		
	public String getLatestInsertedTime();
	public List<IdNameVO> getMandalsByDistrict(InputVO inputVO);
	public List<IdNameVO> getPanchayatsByTehsil(InputVO inputVO);
	public IdNameVO saveLEDVendorWiseDetails(InputVO inputVO,Long userId,Long vendorId);
	public List<LightMonitoringVO> getLevelWiseVendorDetails(InputVO inputVO,Long lightVendorId);
	public List<LightMonitoringVO> getTodayVendorDetails(Long vendorId);
	public LightMonitoringVO getDateWiseVendorDetails(InputVO inputVO,Long vendorId);
	public List<LightMonitoringVO> getVendorDashBoardOverviewDetails(InputVO inputVO);
	public List<LightMonitoringVO> getDashBoardVendorDetailsByLocationId(InputVO inputVO);
	public List<LightMonitoringVO> getDashBoardLevelWiseVendorDetails(InputVO inputVO);
	public List<LightMonitoringVO> getDateWiseVendorDetailsByLocationId(InputVO inputVO);
}
