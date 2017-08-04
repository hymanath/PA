package com.itgrids.led.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.LedOverviewVo;
import com.itgrids.dto.LightMonitoringVO;
import com.itgrids.dto.ResultVO;
import com.itgrids.led.service.ILightMonitoring;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

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
	
	
	@RequestMapping(value = "/saveRealtimeStatusByVillages", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  ResultVO   saveRealtimeStatusByVillages()
	{
		ResultVO  voList= lightMonitoring.saveRealtimeStatusByVillages();
		return voList;
	}

	@RequestMapping(value = "/getBasicLedOverviewDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  List<LightMonitoringVO>  getBasicLedOverviewDetails()
	{
		List<LightMonitoringVO>  voList= lightMonitoring.getBasicLedOverviewDetails();
		return voList;
	}	
	
	@RequestMapping(value = "/getLedOverviewForStartedLocationsDetailsCounts", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  List<LedOverviewVo>  getLedOverviewForStartedLocationsDetailsCountsv()
	{
		List<LedOverviewVo>  voList= lightMonitoring.getLedOverviewForStartedLocationsDetailsCounts();
		return voList;
	}
	
	

}
