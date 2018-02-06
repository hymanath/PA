package com.itgrids.controllers.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LedOverviewVo;
import com.itgrids.dto.LightMonitoringVO;
import com.itgrids.dto.ResultVO;
import com.itgrids.dto.UserVO;
import com.itgrids.model.LightsVendor;
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
		ResultVO  voList = lightMonitoring.saveRealtimeStatusByVillages();
		return voList;
	}

	@RequestMapping(value = "/getBasicLedOverviewDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  List<LightMonitoringVO>  getBasicLedOverviewDetails(@RequestBody InputVO inputVO){
		List<LightMonitoringVO>  voList = lightMonitoring.getBasicLedOverviewDetails(inputVO.getFromDate(),inputVO.getToDate(),inputVO.getLocationType(),inputVO.getLocationValue(),inputVO.getLightVendorIdList());
		return voList;
	}	
	
	@RequestMapping(value = "/getLedOverviewForStartedLocationsDetailsCounts", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  List<LedOverviewVo>  getLedOverviewForStartedLocationsDetailsCountsv(@RequestBody InputVO inputVO){
		List<LedOverviewVo>  voList = lightMonitoring.getLedOverviewForStartedLocationsDetailsCounts(inputVO.getFromDate(),inputVO.getToDate(),inputVO.getLocationType(),inputVO.getLocationValue(),inputVO.getLightVendorIdList());
		return voList;
	}
	
	@RequestMapping(value = "/getAllLevelWiseDataOverView", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  List<LightMonitoringVO> getAllLevelWiseDataOverView(@RequestBody InputVO inputVO){
		List<LightMonitoringVO>  voList = lightMonitoring.getAllLevelWiseDataOverView(inputVO.getLocationType() ,inputVO.getFilterType(), inputVO.getLocationIds(),inputVO.getFromDate(),inputVO.getToDate(),inputVO.getLightVendorIdList());
		return voList;
	}
	
   @RequestMapping(value = "/getLocationBasedOnSelection", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  List<LightMonitoringVO> getAllFilterWiseDataOverView(@RequestBody InputVO inputVO){
	   List<LightMonitoringVO>  voList = lightMonitoring.getLocationBasedOnSelection(inputVO.getLocationType() ,inputVO.getFilterType(), inputVO.getLocationIds(),inputVO.getSublocaType(),inputVO.getFromDate(),inputVO.getToDate(),inputVO.getLightVendorIdList());
	   return voList;
   }
   @RequestMapping(value = "/callWebService", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  ResultVO calllWebSerice(@RequestBody InputVO inputVO){
	   ResultVO  statusVO = lightMonitoring.saveRealtimeStatusByVillages();
       return statusVO;
   }
 	
   @RequestMapping(value = "/checkIsDataExist", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  InputVO  checkIsDataExist(@RequestBody InputVO inputVO){
		InputVO  statusVO = lightMonitoring.checkIdDataExist(inputVO.getFromDate(),inputVO.getToDate());
       return statusVO;
   }	
   
  @RequestMapping(value = "/getCompanyWiseLightMonitoringDtls", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  LightMonitoringVO  getNredCapLightMonitoringLocationWise(@RequestBody InputVO inputVO){
	   LightMonitoringVO  reusltVO=lightMonitoring.getCompanyWiseLightMonitoringDtls(inputVO.getFromDate(),inputVO.getToDate(),inputVO.getLocationType(),inputVO.getLocationValues(),inputVO.getLightVendorIdList());
       return reusltVO;
 }   
   @RequestMapping(value ="/getLightsVendors", method = RequestMethod.GET)
   public  @ResponseBody List<LightsVendor> getLightsVendors() {
	return lightMonitoring.getLightsVendorList();
   }
   @RequestMapping(value = "/getTimePeriodWiseLightsDetaisl", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  List<LightMonitoringVO>  getTimePeriodWiseLightsDetaisl(@RequestBody InputVO inputVO){
	   List<LightMonitoringVO>  voLists=lightMonitoring.getTimePeriodWiseLightsDetails(inputVO.getFromDate(),inputVO.getToDate(),inputVO.getLocationType(),inputVO.getLocationValues(),inputVO.getLightVendorIdList());
       return voLists;
 }
   @RequestMapping(value ="/getLatestInsertedTime", method = RequestMethod.GET)
   public @ResponseBody ResultVO getLatestInsertedTime(ModelMap model) {
	   ResultVO vo = new ResultVO();
	   vo.setMessage(lightMonitoring.getLatestInsertedTime());
	  return vo;
   }
   @RequestMapping(value = "/getMandalsByDistrict", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  List<IdNameVO>  getMandalsByDistrict(@RequestBody InputVO inputVO){
	   List<IdNameVO>  returnList = lightMonitoring.getMandalsByDistrict(inputVO);
       return returnList;
   }
   @RequestMapping(value = "/getPanchayatsByTehsil", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  List<IdNameVO>  getPanchayatsByTehsil(@RequestBody InputVO inputVO){
	   List<IdNameVO>  returnList = lightMonitoring.getPanchayatsByTehsil(inputVO);
       return returnList;
   }
   @RequestMapping(value = "/saveLEDVendorWiseDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  IdNameVO  saveLEDVendorWiseDetails(@RequestBody InputVO inputVO,HttpServletRequest request){
	   IdNameVO  resultVO = null;
	   UserVO userVO = (UserVO) request.getSession().getAttribute("USER");
	   if(userVO != null){
		   resultVO = lightMonitoring.saveLEDVendorWiseDetails(inputVO,userVO.getUserId(),userVO.getVendorId());
	   }
       return resultVO;
   }
   @RequestMapping(value = "/getTodayVendorDetails", method = RequestMethod.POST)
   public @ResponseBody  List<LightMonitoringVO>  getTodayVendorDetails(HttpServletRequest request){
	   List<LightMonitoringVO>  returnList = null;
	   UserVO userVO = (UserVO) request.getSession().getAttribute("USER");
	   if(userVO != null){
		   returnList = lightMonitoring.getTodayVendorDetails(userVO.getVendorId());
	   }
       return returnList;
   }
   @RequestMapping(value = "/getLevelWiseVendorDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  List<LightMonitoringVO>  getLevelWiseVendorDetails(@RequestBody InputVO inputVO,HttpServletRequest request){
	   List<LightMonitoringVO>  returnList = null;
	   UserVO userVO = (UserVO) request.getSession().getAttribute("USER");
	   if(userVO != null){
		   returnList = lightMonitoring.getLevelWiseVendorDetails(inputVO,userVO.getVendorId());
	   }
       return returnList;
   }
   @RequestMapping(value = "/getDateWiseVendorDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  LightMonitoringVO  getDateWiseVendorDetails(@RequestBody InputVO inputVO,HttpServletRequest request){
	   LightMonitoringVO  returnVO = null;
	   UserVO userVO = (UserVO) request.getSession().getAttribute("USER");
	   if(userVO != null){
		   returnVO = lightMonitoring.getDateWiseVendorDetails(inputVO,userVO.getVendorId());
	   }
       return returnVO;
   }
   @RequestMapping(value = "/getVendorDashBoardOverviewDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  List<LightMonitoringVO>  getVendorDashBoardOverviewDetails(@RequestBody InputVO inputVO){
	  List<LightMonitoringVO>  returnList = lightMonitoring.getVendorDashBoardOverviewDetails(inputVO);
       return returnList;
   }
   @RequestMapping(value = "/getDashBoardVendorDetailsByLocationId", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  List<LightMonitoringVO>  getDashBoardVendorDetailsByLocationId(@RequestBody InputVO inputVO){
	   List<LightMonitoringVO>  returnList = lightMonitoring.getDashBoardVendorDetailsByLocationId(inputVO);
       return returnList;
   }
   @RequestMapping(value = "/getDashBoardLevelWiseVendorDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  List<LightMonitoringVO>  getDashBoardLevelWiseVendorDetails(@RequestBody InputVO inputVO){
	   List<LightMonitoringVO>  returnList = lightMonitoring.getDashBoardLevelWiseVendorDetails(inputVO);
       return returnList;
   }
   @RequestMapping(value ="/lightMonitoringEntryDashboard", method = RequestMethod.GET)
   public String entryPage(ModelMap model,HttpServletRequest request) {
	   	UserVO uservo = (UserVO) request.getSession().getAttribute("User");
		if(uservo != null){
			return "lightMonitoringEntry";
		}else{
			return "MGNREGSFieldLoginUpdates";
		}
		//return "lightMonitoringEntry";
   }
   @RequestMapping(value = "/getDateWiseVendorDetailsByLocationId", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  List<LightMonitoringVO>  getDateWiseVendorDetailsByLocationId(@RequestBody InputVO inputVO){
	   List<LightMonitoringVO>  returnList = lightMonitoring.getDateWiseVendorDetailsByLocationId(inputVO);
       return returnList;
   }
   @RequestMapping(value ="/lightMonitoringVendorDashboard", method = RequestMethod.GET)
   public String ledVendorDashboardPage(ModelMap model) {
		return "lightMonitoringVendorDashboard";
   }
}

