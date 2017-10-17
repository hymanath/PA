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

import com.itgrids.dto.InputVO;
import com.itgrids.dto.SwachhBharatMissionIHHLDtlsVO;
import com.itgrids.service.ISwachhBharatMissionIHHLService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class SwachhBharatMissionIHHLController {
	
		private static final Logger LOG = Logger.getLogger(SwachhBharatMissionIHHLController.class);
		
	   @Autowired
	   private ISwachhBharatMissionIHHLService swachhBharatMissionIHHLService;
		
		
	   @RequestMapping(value ="/swachhBharatMissionIHHL",method = RequestMethod.GET)
	   public String drainsDashboard(ModelMap model) {
			return "swachhBharatMissionIHHL";
	   }
	   @RequestMapping(value = "/getSwachhBharatMissionOverviewDtls", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  SwachhBharatMissionIHHLDtlsVO  getSwachhBharatMissionOverviewDtls(@RequestBody InputVO inputVO) {
			 SwachhBharatMissionIHHLDtlsVO  reusltVO = swachhBharatMissionIHHLService.getSwachhBharatMissionOverviewDtls(inputVO);
		    return reusltVO;
	   }
	   @RequestMapping(value = "/getLocationDetailsBasedOnCategory", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<SwachhBharatMissionIHHLDtlsVO>  getSwachhBharatMissionStatusOverviewDtls(@RequestBody InputVO inputVO) {
		 List<SwachhBharatMissionIHHLDtlsVO>  resultList = swachhBharatMissionIHHLService.getLocationDetailsBasedOnCategory(inputVO);
		 return resultList;
	   }
	   @RequestMapping(value = "/getIHHLCategoryWiseAnalysisBySelectedDate", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<SwachhBharatMissionIHHLDtlsVO>  getIHHLCategoryWiseAnalysis(@RequestBody InputVO inputVO) {
		 List<SwachhBharatMissionIHHLDtlsVO>  resultList = swachhBharatMissionIHHLService.getIHHLCategoryWiseAnalysisBySelectedDate(inputVO);
		 return resultList;
	   }
	   @RequestMapping(value = "/getIHHLAchivementProgressDtls", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<SwachhBharatMissionIHHLDtlsVO>  getIHHLAchivementProgressDtls(@RequestBody InputVO inputVO) {
		 List<SwachhBharatMissionIHHLDtlsVO>  resultList = swachhBharatMissionIHHLService.getIHHLAchivementProgressDtls(inputVO);
		 return resultList;
	   }
	   @RequestMapping(value = "/getSwachhBharatMissionLocationWiseDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<SwachhBharatMissionIHHLDtlsVO>  getSwachhBharatMissionLocationWiseDetails(@RequestBody InputVO inputVO) {
		   List<SwachhBharatMissionIHHLDtlsVO>  resultList = swachhBharatMissionIHHLService.getSwachhBharatMissionLocationWiseDetails(inputVO);
		    return resultList;
	   }
}
