package com.itgrids.controllers.web;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.LedOverviewVo;
import com.itgrids.dto.LightMonitoringVO;
import com.itgrids.dto.ResultVO;
import com.itgrids.service.ILightMonitoring;


@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class LightMonitoringController {

	private static final Logger logger  =  LoggerFactory.getLogger(LightMonitoringController.class);	
	@Autowired
	private ILightMonitoring  lightMonitoring;
	@RequestMapping(value ="/getlightsMonitoringDashboard", method = RequestMethod.GET)
    public String firstPage(ModelMap model) {
		return "lightsMonitoringDashboard";
    }
	
	
	@RequestMapping(value = "/saveRealtimeStatusByVillages", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  ResultVO   saveRealtimeStatusByVillages(){
		ResultVO  voList= lightMonitoring.saveRealtimeStatusByVillages();
		return voList;
	}

	@RequestMapping(value = "/getBasicLedOverviewDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  List<LightMonitoringVO>  getBasicLedOverviewDetails(@RequestBody InputVO inputVO){
		List<LightMonitoringVO>  voList= lightMonitoring.getBasicLedOverviewDetails(inputVO.getFromDate(),inputVO.getToDate());
		return voList;
	}	
	
	@RequestMapping(value = "/getLedOverviewForStartedLocationsDetailsCounts", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  List<LedOverviewVo>  getLedOverviewForStartedLocationsDetailsCountsv(@RequestBody InputVO inputVO){
		List<LedOverviewVo>  voList= lightMonitoring.getLedOverviewForStartedLocationsDetailsCounts(inputVO.getFromDate(),inputVO.getToDate());
		return voList;
	}
	
	@RequestMapping(value = "/getAllLevelWiseDataOverView", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  List<LightMonitoringVO> getAllLevelWiseDataOverView(@RequestBody InputVO inputVO){
		List<LightMonitoringVO>  voList= lightMonitoring.getAllLevelWiseDataOverView(inputVO.getLocationType() ,inputVO.getFilterType(), inputVO.getLocationId(),inputVO.getFromDate(),inputVO.getToDate());
		return voList;
	}
	
   @RequestMapping(value = "/getLocationBasedOnSelection", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  List<LightMonitoringVO> getAllFilterWiseDataOverView(@RequestBody InputVO inputVO){
    List<LightMonitoringVO>  voList= lightMonitoring.getLocationBasedOnSelection(inputVO.getLocationType() ,inputVO.getFilterType(), inputVO.getLocationId());
    return voList;
   }

	
	
}
		
	
	
	


