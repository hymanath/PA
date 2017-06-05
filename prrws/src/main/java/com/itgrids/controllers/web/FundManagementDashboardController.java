package com.itgrids.controllers.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.LocationVO;
import com.itgrids.service.IFundManagementDashboardService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class FundManagementDashboardController {
	@Autowired
	private IFundManagementDashboardService fundManagementDashboardService;
	
	
	@PostMapping("/getLocationWiseAmountDetails")
	public @ResponseBody List<LocationVO> getLocationWiseAmountDetails(){
		String fromDateStr = "";
		String toDateStr = "";
		Long levelId = 0L;
		List<Long> levelValues = null;
		Long financialYrId = null;
		Long deptId = null;
		Long sourceId = null;
		List<LocationVO> locationList = fundManagementDashboardService.getLocationWiseAmountDetails(fromDateStr, toDateStr, levelId, levelValues, financialYrId, deptId, sourceId);
		return locationList;
	}
}
