package com.itgrids.controllers.web;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.AddressVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.PrisDataVo;
import com.itgrids.dto.PrisOverviewVo;
import com.itgrids.service.IPrisSurveyDashBaordService;
import com.itgrids.service.IUserService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class PrisSurveyDashBoardController {
	private static final Logger LOG = Logger.getLogger(PrisSurveyDashBoardController.class);
	
	@Autowired
	private IPrisSurveyDashBaordService surveyDashBaordService;
	@Autowired
	private IUserService userServiceImpl;
	
	@GetMapping("/prisDashBoard")
	public String SurveyDashBoardPage(ModelMap model,HttpSession session) {
		if (null != session.getAttribute("locationTypeId")  && !session.getAttribute("locationTypeId").equals("")){
			AddressVO addressVO = userServiceImpl.getOriginalLocationIdForTempId(Long.valueOf(session.getAttribute("locationTypeId").toString()),session.getAttribute("locationValue").toString(),session.getAttribute("fromPage").toString(),session.getAttribute("toPage").toString());
			model.addAttribute("addressVO", addressVO);
		}
		return "prisDashBoard";
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
	@PostMapping("/getPIRSSurveyInfo")
	public @ResponseBody PrisOverviewVo getPIRSSurveyInfo(@RequestBody InputVO inputVO){
		PrisOverviewVo returnVo = null;
		try {
			returnVo = surveyDashBaordService.getPIRSSurveyInfo(inputVO);
		} catch (Exception e){
			LOG.error("Exception raised at getPIRSSurveyInfo - PrisSurveyDashBoardController controller", e);
		}
		return returnVo;
	}
	
}
