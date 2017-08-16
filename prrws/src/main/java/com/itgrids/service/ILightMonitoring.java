package com.itgrids.service;

import java.util.Date;
import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.LedOverviewVo;
import com.itgrids.dto.LightMonitoringVO;
import com.itgrids.dto.ResultVO;


public interface ILightMonitoring {
	
	public List<LightMonitoringVO> getBasicLedOverviewDetails(String fromDate,String toDate);	
    public ResultVO saveRealtimeStatusByVillages();
    public List<LedOverviewVo> getLedOverviewForStartedLocationsDetailsCounts(String startDate,String endDate);
	public List<LightMonitoringVO> getAllLevelWiseDataOverView(String locationType,String filterType, Long locationId,String fromDateStr,String toDateStr );
	public List<LightMonitoringVO> getLocationBasedOnSelection(String locationType,String filterType, Long filterValue);
}
