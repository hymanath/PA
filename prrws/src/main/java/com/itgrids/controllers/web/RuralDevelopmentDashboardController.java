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

import com.itgrids.dto.InputVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsOverviewVO;
import com.itgrids.dto.NregsProjectsVO;
import com.itgrids.service.integration.impl.IRuralDevelopmentService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class RuralDevelopmentDashboardController {
	
	private static final Logger LOG = Logger.getLogger(RuralDevelopmentDashboardController.class);

	@Autowired
	private IRuralDevelopmentService ruralDevelopmentService;
	
	@RequestMapping(value ="/RuralDevelopmentDashboard", method = RequestMethod.GET)
    public String getRuralDevelopmentDashboard(ModelMap model) {
		return "ruralDevelopmentDashboard";
    }
	
	@PostMapping("/getNtrJalaSiriAbstract")
	public @ResponseBody NregsProjectsVO  getNtrJalaSiriAbstract(@RequestBody InputVO inputVO){
		NregsProjectsVO returnVO = null;
		try {
			returnVO = ruralDevelopmentService.getNtrJalaSiriAbstract(inputVO);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNtrJalaSiriAbstract - RuralDevelopmentDashboardController", e);
		}
		return returnVO;
	}
	
	@PostMapping("/getNtrJalaSiriOverview")
	public @ResponseBody NregsOverviewVO getNtrJalaSiriOverview(@RequestBody InputVO inputVO){
		NregsOverviewVO returnVO = null;
		try {
			returnVO = ruralDevelopmentService.getNtrJalaSiriOverview(inputVO);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNtrJalaSiriOverview - RuralDevelopmentDashboardController", e);
		}
		return returnVO;
	}
	
	@PostMapping("/getNtrJalaSiriLvlWiseData")
	public @ResponseBody List<NregsDataVO> getNtrJalaSiriLvlWiseData(@RequestBody InputVO inputVO){
		List<NregsDataVO> lvlDataList = null;
		try{
			lvlDataList = ruralDevelopmentService.getNtrJalaSiriLvlWiseData(inputVO);
			
		}catch (Exception e) {
			LOG.error("Exception raised at getNtrJalaSiriLvlWiseData - RuralDevelopmentDashboardController", e);
		}
		return lvlDataList;
	}
}
