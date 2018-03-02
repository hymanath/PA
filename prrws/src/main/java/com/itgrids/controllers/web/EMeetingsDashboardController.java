package com.itgrids.controllers.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.EMeetingsVO;
import com.itgrids.dto.InputVO;
import com.itgrids.service.IEMeetingsDashboardService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class EMeetingsDashboardController {

	private static final Logger LOG = Logger.getLogger(EMeetingsDashboardController.class);
	
	@Autowired
	private IEMeetingsDashboardService eMeetingsDashboardService;
	
	@RequestMapping(value ="/eMeetingsDashboard",method = RequestMethod.GET)
    public String eMeetingDashboard(ModelMap model) {
		return "eMeetingsDashboard";
    }
	
   @RequestMapping(value = "/getEMeetingsOverViewDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  EMeetingsVO  getEMeetingsOverViewDetails(@RequestBody InputVO inputVo) {
	   EMeetingsVO  reusltVO = eMeetingsDashboardService.getEMeetingsOverViewDetails(inputVo);
	   return reusltVO;
   }
   
   @RequestMapping(value = "/getEMeetingsLevelWiseOverViewDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  List<EMeetingsVO>  getEMeetingsLevelWiseOverViewDetails(@RequestBody InputVO inputVo) {
	   List<EMeetingsVO>  returnList = eMeetingsDashboardService.getEMeetingsLevelWiseOverViewDetails(inputVo);
	   return returnList;
   }
   
   @RequestMapping(value = "/getLevelsWiseConductedMeetingDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  EMeetingsVO  getLevelsWiseConductedMeetingDetails(@RequestBody InputVO inputVo) {
	   EMeetingsVO  reusltVO = eMeetingsDashboardService.getLevelsWiseConductedMeetingDetails(inputVo);
	   return reusltVO;
   }
   
   @RequestMapping(value = "/getMeetingDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  EMeetingsVO  getMeetingDetails(@RequestBody InputVO inputVo) {
	   EMeetingsVO  reusltVO = eMeetingsDashboardService.getMeetingDetails(inputVo);
	   return reusltVO;
   }
}
