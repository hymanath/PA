package com.itgrids.service;

import java.util.Date;
import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.LedOverviewVo;
import com.itgrids.dto.LightMonitoringVO;
import com.itgrids.dto.ResultVO;


public interface ILightMonitoring {
	
	public List<LightMonitoringVO> getBasicLedOverviewDetails(String fromDate,String toDate,String locationType,Long locationValues);	
    public ResultVO saveRealtimeStatusByVillages();
    public List<LedOverviewVo> getLedOverviewForStartedLocationsDetailsCounts(String startDate,String endDate,String locationType, Long locationValues);
	public List<LightMonitoringVO> getAllLevelWiseDataOverView(String locationType,String filterType, List<Long> locationIds,String fromDateStr,String toDateStr );
	public List<LightMonitoringVO> getLocationBasedOnSelection(String locationType,String filterType, List<Long> filterValues,String subLocationType);
	public LightMonitoringVO getCompanyWiseLightMonitoringDtls(String startDate,String endDate, String locationType,Long locationValue);
}
