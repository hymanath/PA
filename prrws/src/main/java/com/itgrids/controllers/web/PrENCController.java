package com.itgrids.controllers.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.EncTargetsVO;
import com.itgrids.dto.EncVO;
import com.itgrids.dto.EncWorksVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.service.IPrENCService;
import com.itgrids.tpi.rws.service.IRwsWorksSchedulerService;


@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class PrENCController {

	
	private static final Logger LOG = Logger.getLogger(PrENCController.class);


	@Autowired
	private IPrENCService prENCService;
	
	@Autowired
	private IRwsWorksSchedulerService rwsWorksSchedulerService;
	
	@RequestMapping(value ="/EncDevelopmentDashboard", method = RequestMethod.GET)
    public String getEncDevelopmentDashboard(ModelMap model) {
		return "EncDevelopmentDashboard";
    }
	@RequestMapping(value ="/EncWorksDashboard", method = RequestMethod.GET)
    public String getEncWorksDashboard(ModelMap model) {
		return "EncWorksDashboard";
    }
	
	@PostMapping("/getLocationWiseRoadsInformation")
	public @ResponseBody List<EncVO>  getLocationWiseRoadsInformation(@RequestBody InputVO inputVO){
		List<EncVO> returnList = null;
		try {
			returnList = prENCService.getLocationWiseRoadsInformation(inputVO);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNtrJalaSiriAbstract - PrENCController", e);
		}
		return returnList;
	}
	@PostMapping("/getStateWiseRoadsInformation")
	public @ResponseBody EncVO  getStateWiseRoadsInformation(@RequestBody InputVO inputVO){
		EncVO returnList = null;
		try {
			returnList = prENCService.getStateWiseRoadsInformation(inputVO);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNtrJalaSiriAbstract - PrENCController", e);
		}
		return returnList;
	}
	
	@PostMapping("/getLocationWiseWorksInformation")
	public @ResponseBody List<EncWorksVO>  getLocationWiseWorksInformation(@RequestBody InputVO inputVO){
		List<EncWorksVO> returnList = null;
		try {
			returnList = prENCService.getLocationWiseWorksInformation(inputVO);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNtrJalaSiriAbstract - PrENCController", e);
		}
		return returnList;
	}

	@PostMapping("/getEncTargetsAchievement")
	public @ResponseBody List<EncTargetsVO>  getEncTargetsAchievement(@RequestBody InputVO inputVO){
		List<EncTargetsVO> returnList = null;
		try {
			returnList = prENCService.getEncTargetsAchievement(inputVO);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getEncTargetsAchievement - PrENCController", e);
		}
		return returnList;
	}
	
	@PostMapping("/getEncWorksInsertion")
	public @ResponseBody String  getEncWorksInsertion(){
		String result = "false";
		try {
			result = rwsWorksSchedulerService.getEncworkDataInsertion();
			
		} catch (Exception e) {
			LOG.error("Exception raised at getEncTargetsAchievement - PrENCController", e);
		}
		return result;
	}
	@PostMapping("/getExceededEncWorks")
	public @ResponseBody List<IdNameVO>  getExceededEncWorks(@RequestBody InputVO inputVO){
		List<IdNameVO> returnList = null;
		try {
			returnList = prENCService.getExceededEncWorks(inputVO);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getEncTargetsAchievement - PrENCController", e);
		}
		return returnList;

	}
}
	
