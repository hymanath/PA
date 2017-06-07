package com.itgrids.controllers.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.FundSchemeVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.service.IFundManagementDashboardService;

@EnableAutoConfiguration
@Controller
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
	  public @ResponseBody LocationFundDetailsVO getLocationWiseFundDetails(@RequestBody InputVO inputVO)
	  {		
		
		
		LocationFundDetailsVO  returnVO =  fundManagementDashboardService.getLocationWiseFundDetails(inputVO);
		return returnVO;
	  }

	@RequestMapping(value = "/getTotalFunds", method = RequestMethod.POST,
	produces = MediaType.APPLICATION_JSON_VALUE,
	consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody LocationFundDetailsVO getTotalFunds(@RequestBody InputVO inputVO)
	  {		
		
		LocationFundDetailsVO  returnVO = fundManagementDashboardService.getTotalFunds(inputVO);
		return returnVO;
	  }
	@PostMapping("/getAverageFundForAnyLevel")
	public @ResponseBody LocationFundDetailsVO getAverageFundForAnyLevel(@RequestBody InputVO inputVO){
		LocationFundDetailsVO locationFundDetailsVO = fundManagementDashboardService.getAverageFundForAnyLevel(inputVO);
		return locationFundDetailsVO;
	}
	@PostMapping("/getAverageFundForScheme")
	public @ResponseBody LocationFundDetailsVO getAverageFundForScheme(@RequestBody InputVO inputVO){
		LocationFundDetailsVO locationFundDetailsVO = fundManagementDashboardService.getAverageFundForScheme(inputVO);
		return locationFundDetailsVO;
	}
	
	@RequestMapping(value = "/getTotalLocationsByScopeId", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
			  public @ResponseBody LocationFundDetailsVO getTotalLocationsByScopeId(@RequestBody InputVO inputVO)
			  {		
				LocationFundDetailsVO  returnVO = fundManagementDashboardService.getTotalLocationsByScopeId(inputVO );
				return returnVO;
			  }
	
	@RequestMapping(value = "/getSchemeWiseHighestAndLowestFund", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
			  public @ResponseBody LocationFundDetailsVO getSchemeWiseHighestAndLowestFund(@RequestBody InputVO inputVO)
			  {		
				
				LocationFundDetailsVO  returnVO = fundManagementDashboardService.getSchemeWiseHighestAndLowestFund(inputVO);
				return returnVO;
			  }
	
	@RequestMapping(value = "/getTotalSchemes", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
			public @ResponseBody LocationFundDetailsVO getTotalSchemes(@RequestBody InputVO inputVO)
			  {		
				LocationFundDetailsVO  returnVO = fundManagementDashboardService.getTotalSchemes(inputVO);
				return returnVO;
			  }
	
		@RequestMapping(value="/getFinancialYearWiseScheameDetails", method = RequestMethod.POST,
		produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<FundSchemeVO> getFinancialYearWiseScheameDetails(@RequestBody InputVO inputVO){
			List<FundSchemeVO> ajaxResult = fundManagementDashboardService.getFinancialYearWiseSchemeDetails(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),inputVO.getSchemeIdsList(),
					inputVO.getFromDateStr(),inputVO.getToDateStr(),inputVO.getLevelId(),inputVO.getLevelValues());
			return ajaxResult;
		}	
		
		@RequestMapping(value="/getDistrictIdName", method = RequestMethod.POST,
		        produces = MediaType.APPLICATION_JSON_VALUE,
		        consumes = MediaType.APPLICATION_JSON_VALUE)
		        public @ResponseBody List<LocationFundDetailsVO> getDistrictIdName(@RequestBody Map<String,String> map){
			    Long stateId = Long.valueOf(map.get("stateId"));
		             List<LocationFundDetailsVO>  districtList=fundManagementDashboardService.getDistrictIdName(stateId);
		             return districtList;
		        }  
		@RequestMapping(value="/getConstituencies", method = RequestMethod.POST,
		        produces = MediaType.APPLICATION_JSON_VALUE,
		        consumes = MediaType.APPLICATION_JSON_VALUE)
		        public @ResponseBody List<LocationFundDetailsVO> getConstituencies(@RequestBody Map<String,String> map){
		           Long districtId = Long.valueOf(map.get("districtId"));
		             List<LocationFundDetailsVO>  constisList=fundManagementDashboardService.getConstituencies(districtId);
		             return constisList;
		        }
		
}
