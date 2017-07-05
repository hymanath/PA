package com.itgrids.controllers.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.PrisDataVo;
import com.itgrids.dto.PrisOverviewVo;
import com.itgrids.service.IPrisSurveyDashBaordService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class PrisSurveyDashBoardController {
	private static final Logger LOG = Logger.getLogger(PrisSurveyDashBoardController.class);
	
	@Autowired
	private IPrisSurveyDashBaordService surveyDashBaordService;
	
	@GetMapping("/SurveyDashBoard")
	public String SurveyDashBoardPage(ModelMap model) {

		return "SurveyDashBoard";
	}
	
	@PostMapping("/getPrisSurveyBasicData")
	public @ResponseBody PrisDataVo getPrisSurveyBasicData(@RequestBody InputVO inputVO){
		PrisDataVo returnVo = null;
		try {
			returnVo = surveyDashBaordService.getPrisSurveyBasicData(inputVO);
		} catch (Exception e){
			LOG.error("Exception raised at getPrisSurveyBasicData - PrisSurveyDashBoardController controller", e);
		}
		return returnVo;
	}
	@PostMapping("/getPrisLocationWiseOverview")
	public @ResponseBody PrisOverviewVo getPrisLocationWiseOverview(@RequestBody InputVO inputVO){
		PrisOverviewVo returnVo = null;
		try {
			returnVo = surveyDashBaordService.getPrisLocationWiseOverview(inputVO);
		} catch (Exception e){
			LOG.error("Exception raised at getPrisLocationWiseOverview - PrisSurveyDashBoardController controller", e);
		}
		return returnVo;
	}
	
}
