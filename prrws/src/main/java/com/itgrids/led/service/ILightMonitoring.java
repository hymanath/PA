package com.itgrids.led.service;

import java.util.List;


import com.itgrids.dto.LightMonitoringVO;
import com.itgrids.dto.ResultVO;


public interface ILightMonitoring {
	
	//public LightMonitoringVO getRealtimeStatusByVillages();	
	//public List<LightMonitoringVO> getVillageIdBasedDetails();
      public ResultVO saveRealtimeStatusByVillages();

}
