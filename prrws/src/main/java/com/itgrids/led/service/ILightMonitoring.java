package com.itgrids.led.service;

import java.util.Date;
import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.LedOverviewVo;
import com.itgrids.dto.LightMonitoringVO;
import com.itgrids.dto.ResultVO;


public interface ILightMonitoring {
	
	public List<LightMonitoringVO> getBasicLedOverviewDetails(String fromDate,String toDate);	
	//public List<LightMonitoringVO> getVillageIdBasedDetails();
      public ResultVO saveRealtimeStatusByVillages();
      public List<LedOverviewVo> getLedOverviewForStartedLocationsDetailsCounts(String startDate,String endDate);
   /*   public	List<LightMonitoringVO> getLevelWiseOverviewDetails(String fromDateStr, String toDateStr, String year,
				List<Long> locationValues, Long locationTypeId, Long searchlevelId, List<Long> searchLevelValues);
      
      public	List<LightMonitoringVO> getDistrictLevelWiseOverviewDetails(String fromDateStr, String toDateStr, String year,
  				List<Long> locationValues, Long locationTypeId);
	  List<LightMonitoringVO> getDistrictLevelWiseOverviewDetails(String fromDateStr, String toDateStr,
			List<Long> locationValues, Long locationTypeId);	*/
	public List<LightMonitoringVO> getAllLevelWiseDataOverView(String locationType, String displayType,
			String filterType, Long locationId);
}
