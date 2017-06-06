package com.itgrids.controllers.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.service.IFundManagementDashboardService;

@EnableAutoConfiguration
@RestController
@RequestMapping("/")
public class FundManagementDashboardController {
	@Autowired
	private IFundManagementDashboardService fundManagementDashboardService;
	
	
	@RequestMapping(value ="/fundManagementDashboard", method = RequestMethod.GET)
    public String fundManagementDashboardPage(ModelMap model) {
      
		return "fundManagementDashboard";
    }
	
	@PostMapping("/getLocationWiseAmountDetails")
	public @ResponseBody List<LocationVO> getLocationWiseAmountDetails(@RequestBody InputVO inputVO){
		List<LocationVO> locationList = fundManagementDashboardService.getLocationWiseAmountDetails(inputVO);
		return locationList;
	}
	@RequestMapping(value = "/getLocationWiseFundDetails", method = RequestMethod.POST,
	produces = MediaType.APPLICATION_JSON_VALUE,
	consumes = MediaType.APPLICATION_JSON_VALUE)
	  public @ResponseBody LocationFundDetailsVO getLocationWiseFundDetails(@RequestBody Map<String,String> map)
	  {		
		Long financialYrId = Long.valueOf(map.get("financialYrId"));
		Long departmentId = Long.valueOf(map.get("departmentId"));
		Long sourceId = Long.valueOf(map.get("sourceId"));
		String startDate = map.get("startDate");
		String endDate = map.get("endDate");
		Long locationScopeId = Long.valueOf(map.get("locationScopeId"));
		String type = map.get("type");
		LocationFundDetailsVO  returnVO = fundManagementDashboardService.getLocationWiseFundDetails(financialYrId, departmentId, sourceId, startDate,
				 endDate, locationScopeId, type );
		return returnVO;
	  }

	@RequestMapping(value = "/getTotalFunds", method = RequestMethod.POST,
	produces = MediaType.APPLICATION_JSON_VALUE,
	consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody LocationFundDetailsVO getTotalFunds(@RequestBody Map<String,String> map)
	  {		
		Long financialYrId = Long.valueOf(map.get("financialYrId"));
		Long departmentId = Long.valueOf(map.get("departmentId"));
		Long sourceId = Long.valueOf(map.get("sourceId"));
		String startDate = map.get("startDate");
		String endDate = map.get("endDate");
	
		LocationFundDetailsVO  returnVO = fundManagementDashboardService.getTotalFunds(financialYrId, departmentId, sourceId, startDate,endDate);
		return returnVO;
	  }
	
}
