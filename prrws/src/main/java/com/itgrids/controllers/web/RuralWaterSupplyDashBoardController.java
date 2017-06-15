package com.itgrids.controllers.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itgrids.service.IRuralWaterSupplyDashBoardService;
import com.itgrids.service.impl.FundManagementDashboardService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class RuralWaterSupplyDashBoardController {
	private static final Logger LOG = Logger.getLogger(RuralWaterSupplyDashBoardController.class);
	@Autowired
	private IRuralWaterSupplyDashBoardService ruralWaterSupplyDashBoardService;
	
	@RequestMapping(value ="/ruralWaterSupplyDashBoard", method = RequestMethod.GET)
    public String ruralWaterSupplyDashBoardPage(ModelMap model) {
      
		return "ruralWaterSupplyDashBoard";
    }
	
	/*@PostMapping("/getLocationWiseAmountDetails")
	public void getHabitationCoverageByStatusByLocationType(){
		try {
			ruralWaterSupplyDashBoardService.getHabitationCoverageByStatusByLocationType();
		} catch (Exception e) {
			LOG.error("Exception raised at getHabitationCoverageByStatusByLocationType - RuralWaterSupplyDashBoardController controller", e);
		}
	}*/
}
