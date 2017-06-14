package com.itgrids.controllers.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.FundMatrixVO;
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
					inputVO.getFromDateStr(),inputVO.getToDateStr(),inputVO.getSearchLevelId(),inputVO.getLevelValues(),inputVO.getOrder(),inputVO.getSortingType(),inputVO.getBlockLevelId());
			return ajaxResult;
		}	
		
		@RequestMapping(value="/getFinancialYearWiseDeptsWiseSchemeDetails", method = RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_VALUE,
				consumes = MediaType.APPLICATION_JSON_VALUE)
				public @ResponseBody List<FundSchemeVO> getFinancialYearWiseDeptsWiseSchemeDetails(@RequestBody InputVO inputVO){
					List<FundSchemeVO> ajaxResult = fundManagementDashboardService.getFinancialYearWiseDeptsWiseSchemeDetails(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),inputVO.getSchemeIdsList(),
							inputVO.getFromDateStr(),inputVO.getToDateStr(),inputVO.getSearchLevelId(),inputVO.getLevelValues(),inputVO.getOrder(),inputVO.getSortingType(),inputVO.getBlockLevelId());
					return ajaxResult;
				}	
		
		@RequestMapping(value="/getAllDistrictByStateId", method = RequestMethod.POST,
		        produces = MediaType.APPLICATION_JSON_VALUE,
		        consumes = MediaType.APPLICATION_JSON_VALUE)
		        public @ResponseBody List<LocationFundDetailsVO> getAllDistrictByStateId(@RequestBody Map<String,Long> map){
		             List<LocationFundDetailsVO>  districtList=fundManagementDashboardService.getAllDistrictByStateId(map.get("stateId"));
		             return districtList;
		        }  
		@RequestMapping(value="/getAllConstituenciesByDistrictId", method = RequestMethod.POST,
		        produces = MediaType.APPLICATION_JSON_VALUE,
		        consumes = MediaType.APPLICATION_JSON_VALUE)
		        public @ResponseBody List<LocationFundDetailsVO> getAllConstituenciesByDistrictId(@RequestBody Map<String,Long> map){
		             List<LocationFundDetailsVO>  constisList=fundManagementDashboardService.getAllConstituenciesByDistrictId(map.get("districtId"));
		             return constisList;
		        }

		@RequestMapping(value="/getAllFiniancialYears", method = RequestMethod.POST,
		        produces = MediaType.APPLICATION_JSON_VALUE,
		        consumes = MediaType.APPLICATION_JSON_VALUE)
		        public @ResponseBody List<LocationVO> getAllFiniancialYears(){
		             List<LocationVO>  financialYearList=fundManagementDashboardService.getAllFiniancialYears();
		             return financialYearList;
		        }

		@RequestMapping(value="/getAllDepartments", method = RequestMethod.POST,
		        produces = MediaType.APPLICATION_JSON_VALUE,
		        consumes = MediaType.APPLICATION_JSON_VALUE)
		        public @ResponseBody List<LocationFundDetailsVO> getAllDepartments(){
			    
		             List<LocationFundDetailsVO>  districtList=fundManagementDashboardService.getAllDepartments();
		             return districtList;
		        }  
		@PostMapping("/getAllSubLocationsBySuperLocationId")
		public @ResponseBody List<LocationFundDetailsVO> getAllSubLocationsBySuperLocationId(@RequestBody InputVO inputVO){
			List<LocationFundDetailsVO> locationFundDetailsVOList = fundManagementDashboardService.getAllSubLocationsBySuperLocationId(inputVO);
			return locationFundDetailsVOList;
		}
		@PostMapping("/compareFundsBetweenFinancialYears")
		public @ResponseBody List<FundMatrixVO> compareFundsBetweenFinancialYears(@RequestBody InputVO inputVO){
			List<FundMatrixVO> fundMatrixVOList = fundManagementDashboardService.compareFundsBetweenFinancialYears(inputVO);
			return fundMatrixVOList;
		}
		@RequestMapping(value = "/getLocationWiseFundSanctionDetails", method = RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_VALUE,
				consumes = MediaType.APPLICATION_JSON_VALUE)
				  public @ResponseBody List<LocationVO> getLocationWiseFundSanctionDetails(@RequestBody InputVO inputVO)
				  {		
			          List<LocationVO>  finalReturnList =  fundManagementDashboardService.getLocationWiseFundSanctionDetails(inputVO);
					return finalReturnList;
				  }
		@PostMapping("/getLocationWiseAmountAndCountDetails")
		public @ResponseBody List<LocationVO> getLocationWiseAmountAndCountDetails(@RequestBody InputVO inputVO){
			List<LocationVO> locationList = fundManagementDashboardService.getLocationWiseAmountAndCountDetails(inputVO);
			return locationList;
		}
		
}
