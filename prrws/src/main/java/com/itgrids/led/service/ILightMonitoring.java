package com.itgrids.led.service;

import java.util.List;

import com.itgrids.dto.LedOverviewVo;
import com.itgrids.dto.LightMonitoringVO;
import com.itgrids.dto.ResultVO;


public interface ILightMonitoring {
	
	public List<LightMonitoringVO> getBasicLedOverviewDetails();	
	//public List<LightMonitoringVO> getVillageIdBasedDetails();
      public ResultVO saveRealtimeStatusByVillages();
      public List<LedOverviewVo> getLedOverviewForStartedLocationsDetailsCounts();

}
