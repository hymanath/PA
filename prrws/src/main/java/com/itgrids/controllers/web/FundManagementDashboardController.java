package com.itgrids.controllers.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import com.itgrids.dto.AddressVO;
import com.itgrids.dto.FundMatrixVO;
import com.itgrids.dto.FundSchemeVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.NregsFmsWorksVO;
import com.itgrids.dto.ResultVO;
import com.itgrids.service.IConstituencyWiseWorkStatusService;
import com.itgrids.service.IFundManagementDashboardService;
import com.itgrids.service.IFundSanctionMatrixReportService;
import com.itgrids.service.IUserService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class FundManagementDashboardController {
	@Autowired
	private IFundManagementDashboardService fundManagementDashboardService;
	@Autowired
	private IFundSanctionMatrixReportService fundSanctionMatrixReportService;
	@Autowired 
	private IUserService userServiceImpl;
	@Autowired 
	private IConstituencyWiseWorkStatusService constituencyWiseWorkStatusService;
	
	@RequestMapping(value ="/", method = RequestMethod.GET)
    public String indexPage(ModelMap model) {
		return "MGNREGS";
    }
	
	@RequestMapping(value ="/fundManagementDashboard", method = RequestMethod.GET)
    public String fundManagementDashboardPage(ModelMap model) {
		return "fundManagementDashboard";
    }
	
	@RequestMapping(value ="/newfundManagementSystemDashBoard", method = RequestMethod.GET)
	 public String newfundManagementDashboardPage(ModelMap model,HttpSession session){
		if (null != session.getAttribute("locationTypeId")  && !session.getAttribute("locationTypeId").equals("")){
			AddressVO addressVO = userServiceImpl.getOriginalLocationIdForTempId(Long.valueOf(session.getAttribute("locationTypeId").toString()),session.getAttribute("locationValue").toString(),session.getAttribute("fromPage").toString(),session.getAttribute("toPage").toString());
			model.addAttribute("addressVO", addressVO);
		}
		return "newfundManagementDashboard";
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
					inputVO.getFromDateStr(),inputVO.getToDateStr(),inputVO.getSearchLevelId(),inputVO.getLevelValues(),inputVO.getOrder(),inputVO.getSortingType(),inputVO.getBlockLevelId(),inputVO.getGovtSchmeIdsList(),
					inputVO.getSubProgramIdsList(),inputVO.getGlSearchLevelId(),inputVO.getGlSearchLevelValue(),inputVO.getViewType(),inputVO.getGrantTypeIdsList(),inputVO);
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
		@RequestMapping(value="/getFundSactionCount", method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<LocationFundDetailsVO> getFundSactionCount(@RequestBody InputVO inputVO){
			List<LocationFundDetailsVO> locationFundDetailsVOList = fundManagementDashboardService.getFundSactionCount(inputVO.getFinancialYrIdList());
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
		@RequestMapping(value="/getMinMaxDates", method = RequestMethod.POST,
		        produces = MediaType.APPLICATION_JSON_VALUE,
		        consumes = MediaType.APPLICATION_JSON_VALUE)
		        public @ResponseBody IdNameVO getMinMaxDates(){
			    
		            IdNameVO  minMaxDates =fundManagementDashboardService.getMinMaxDates();
		             return minMaxDates;
		        }  
		@RequestMapping(value = "/getGrantTypeHighestAndLowestFund", method = RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_VALUE,
				consumes = MediaType.APPLICATION_JSON_VALUE)
				  public @ResponseBody LocationFundDetailsVO getGrantTypeHighestAndLowestFund(@RequestBody InputVO inputVO)
				  {		
					
					LocationFundDetailsVO  returnVO = fundManagementDashboardService.getGrantTypeHighestAndLowestFund(inputVO);
					return returnVO;
				  }
		@PostMapping("/getAllSubLocations")
		public @ResponseBody List<LocationFundDetailsVO> getAllSubLocations(@RequestBody InputVO inputVO){
			List<LocationFundDetailsVO> locationFundDetailsVOList = fundManagementDashboardService.getAllSubLocations(inputVO);
			return locationFundDetailsVOList;
		}
		
		@RequestMapping(value="/getGovtSchemesDetails", method = RequestMethod.POST,
		        produces = MediaType.APPLICATION_JSON_VALUE,
		        consumes = MediaType.APPLICATION_JSON_VALUE)
		        public @ResponseBody List<LocationFundDetailsVO> getGovtSchemesDetails(){
			    
		             List<LocationFundDetailsVO>  govtSchemesList=fundManagementDashboardService.getGovtSchemesDetails();
		             return govtSchemesList;
		        } 
				
				@RequestMapping(value="/getGovtSubProgramsDetails", method = RequestMethod.POST,
		        produces = MediaType.APPLICATION_JSON_VALUE,
		        consumes = MediaType.APPLICATION_JSON_VALUE)
		        public @ResponseBody List<LocationFundDetailsVO> getGovtSubProgramsDetails(@RequestBody Map<String,Long> map){
			    
		             List<LocationFundDetailsVO>  govtSubProgramsList=fundManagementDashboardService.getGovtSubProgramsDetails(map.get("govtSchemesId"));
		             return govtSubProgramsList;
		        } 
				
				@RequestMapping(value = "/getALlProgramesAmountDetails", method = RequestMethod.POST,
						produces = MediaType.APPLICATION_JSON_VALUE,
						consumes = MediaType.APPLICATION_JSON_VALUE)
						  public @ResponseBody List<LocationFundDetailsVO> getALlProgramesAmountDetails(@RequestBody InputVO inputVO)
						  {		
							
							List<LocationFundDetailsVO>  returnList = fundManagementDashboardService.getALlProgramesAmountDetails(inputVO);
							return returnList;
						  }
		@PostMapping("/getAllSubLocationsOnsuperLocation")
		public @ResponseBody List<LocationFundDetailsVO> getAllSubLocationsOnsuperLocation(@RequestBody InputVO inputVO){
			List<LocationFundDetailsVO> locationFundDetailsVOList = fundManagementDashboardService.getAllSubLocationsOnsuperLocation(inputVO);
			return locationFundDetailsVOList;
		}
		
		@RequestMapping(value = "/getSchemeWiseOverviewDetails", method = RequestMethod.POST,
		produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE)
		  public @ResponseBody LocationFundDetailsVO getSchemeWiseOverviewDetails(@RequestBody InputVO inputVO)
		  {		
			LocationFundDetailsVO  returnVO = fundManagementDashboardService.getSchemeWiseOverviewDetails(inputVO);
			return returnVO;
		  }
		
		@RequestMapping(value="/getGovtGrantTypeDetails", method = RequestMethod.POST,
		produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<LocationFundDetailsVO> getGovtGrantTypeDetails(@RequestBody Map<String,Long> map){
		
		     List<LocationFundDetailsVO>  govtSubProgramsList=fundManagementDashboardService.getGovtGrantTypeDetails(map.get("programId"), map.get("govtSchemesId"));
		     return govtSubProgramsList;
		}
		
		@RequestMapping(value="/getGovtSchemsTypeDetails", method = RequestMethod.POST,
		produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<LocationFundDetailsVO> getGovtSchemsTypeDetails(@RequestBody Map<String,Long> map){
		
		     List<LocationFundDetailsVO>  govtSubProgramsList=fundManagementDashboardService.getGovtSchemsTypeDetails(map.get("programId"), map.get("grantTypeId"));
		     return govtSubProgramsList;
		}
		
		@PostMapping("/getMgnregsFMSWorksDetails")
		public @ResponseBody List<NregsFmsWorksVO> getMgnregsFMSWorksDetails(@RequestBody InputVO inputVO){
			List<NregsFmsWorksVO> fmsWorksVOList = fundManagementDashboardService.getMgnregsFMSWorksDetails(inputVO);
			return fmsWorksVOList;
		}
		
		@PostMapping("/getMgnregsFMSWorksDetailsByCategory")
		public @ResponseBody List<NregsFmsWorksVO> getMgnregsFMSWorksDetailsByCategory(@RequestBody InputVO inputVO){
			List<NregsFmsWorksVO> fmsWorksVOList = fundManagementDashboardService.getMgnregsFMSWorksDetailsByCategory(inputVO);
			return fmsWorksVOList;
		}
		@RequestMapping(value ="/ministerHomePage", method = RequestMethod.GET)
	    public String landingPage(ModelMap model) {
			return "landingPage";
	    }
	
		@PostMapping(value ="/getFavouriteComponents")
	    public @ResponseBody List<IdNameVO> getFavouriteComponents(@RequestBody IdNameVO inputVO) {
			List<IdNameVO> componentsList = userServiceImpl.getFavouriteComponents(inputVO);
			return componentsList;
	    }
		@PostMapping(value ="/saveFavouriteComponentDtls")
	    public @ResponseBody ResultVO saveFavouriteComponentDtls(@RequestBody IdNameVO inputVO) {
			ResultVO status = userServiceImpl.saveFavouriteComponentDtls(inputVO);
			return status;
	    }
		@PostMapping(value ="/deleteFavouriteComponent")
	    public @ResponseBody ResultVO deleteFavouriteComponent(@RequestBody IdNameVO inputVO) {
			ResultVO status = userServiceImpl.deleteFavouriteComponent(inputVO);
			return status;
	    }
		@PostMapping(value ="/saveFavouriteComponentOrderDtls")
	    public @ResponseBody ResultVO saveFavouriteComponentOrderDtls(@RequestBody IdNameVO inputVO) {
			ResultVO status = userServiceImpl.saveFavouriteComponentOrderDtls(inputVO);
			return status;
	    }
		@RequestMapping(value ="/constituencyWiseWorkStatus", method = RequestMethod.GET)
	    public String conStituencyWiseWorkStatus(ModelMap model) {
			return "constituencyWiseWorkStatus";//constituencyWiseWorkStatus
	    }	
		@PostMapping("/getLocationsNamesBySubLocation")
		public @ResponseBody List<LocationVO> getLocationsNamesBySubLocation(@RequestBody InputVO inputVO){
			List<LocationVO> loctionsList = constituencyWiseWorkStatusService.getLocationsNamesBySubLocation(inputVO);
			return loctionsList;
		}
		@PostMapping("/getFundManagementSystemWorkDetails")
		public @ResponseBody LocationVO getFundManagementSystemWorkDetails(@RequestBody InputVO inputVO){
			LocationVO loctions = constituencyWiseWorkStatusService.getFundManagementSystemWorkDetails(inputVO.getFinancialYrIdList(),inputVO.getDepartmentId(),inputVO.getFromDateStr(),inputVO.getToDateStr(),inputVO.getLocationId());
			return loctions;
		}
		
		@PostMapping("/getDistrictNameAndMlaNameByConsitutency")
		public @ResponseBody List<LocationVO> getDistrictNameAndMlaNameByConsitutency(@RequestBody InputVO inputVO){
			List<LocationVO> loctionsList = constituencyWiseWorkStatusService.getDistrictNameAndMlaNameByConsitutency(inputVO);
			return loctionsList;
		}
		@PostMapping("/getDepartmentDetails")
		public @ResponseBody List<LocationVO> getDepartmentDetails(){
			List<LocationVO> loctionsList = constituencyWiseWorkStatusService.getDepartmentNames();
			return loctionsList;
		}
}
