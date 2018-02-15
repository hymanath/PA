package com.itgrids.controllers.web;

import java.util.ArrayList;
import java.util.List;

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

import com.itgrids.dto.AlertVO;
import com.itgrids.dto.JalavaniAlertsInputVO;
import com.itgrids.service.IJalavaniDashboardService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class JalavaniDashboardController {
	private static final Logger LOG = Logger.getLogger(JalavaniDashboardController.class);
	
	@Autowired
	private IJalavaniDashboardService jalavaniDashboardService;
	
	@GetMapping("/jalavaniAlertsDashBoard")
	public String SurveyDashBoardPage(ModelMap model,HttpSession session) {
		return "jalavaniAlertsDashBoard";
	}
	@PostMapping("/getJalavaniDashBoardOverview")
	public @ResponseBody AlertVO getJalavaniDashBoardOverview(@RequestBody JalavaniAlertsInputVO inputVO){
		AlertVO returnVo = null;
		try {
			returnVo = jalavaniDashboardService.getJalavaniDashBoardOverview(inputVO);
		} catch (Exception e){
			LOG.error("Exception raised at getJalavaniDashBoardOverview - JalavaniDashboardController controller", e);
		}
		return returnVo;
	}
	@PostMapping("/getJalavaniCategoryWiseDetailsInfo")
	public @ResponseBody AlertVO getJalavaniCategoryWiseDetailsInfo(@RequestBody JalavaniAlertsInputVO inputVO){
		AlertVO returnVo = null;
		try {
			returnVo = jalavaniDashboardService.getJalavaniCategoryWiseDetailsInfo(inputVO);
		} catch (Exception e){
			LOG.error("Exception raised at getJalavaniCategoryWiseDetailsInfo - JalavaniDashboardController controller", e);
		}
		return returnVo;
	}
	@PostMapping("/getArticlesMonthlyOverviewInfoBySearchType")
	public @ResponseBody List<AlertVO> getArticlesMonthlyOverviewInfoBySearchType(@RequestBody JalavaniAlertsInputVO inputVO){
		List<AlertVO> returnList = new ArrayList<AlertVO>(0);
		try {
			returnList = jalavaniDashboardService.getArticlesMonthlyOverviewInfoBySearchType(inputVO);
		} catch (Exception e){
			LOG.error("Exception raised at getArticlesMonthlyOverviewInfoBySearchType - JalavaniDashboardController controller", e);
		}
		return returnList;
	}
}
