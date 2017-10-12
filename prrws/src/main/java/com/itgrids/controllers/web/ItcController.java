package com.itgrids.controllers.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.ApInnovationCenterVO;
import com.itgrids.dto.ApInnovationSocietyOverviewVO;
import com.itgrids.dto.CmEoDBDtlsVO;
import com.itgrids.dto.CohortDtlsVO;
import com.itgrids.dto.EofficeDtlsVO;
import com.itgrids.dto.InnovationSocietyDtlsVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.ItInformationDtlsVO;
import com.itgrids.dto.ItecOverviewVO;
import com.itgrids.dto.ItecPromotionDetailsVO;
import com.itgrids.dto.MeesevaDtlsVO;
import com.itgrids.dto.MeesevaKPIDtlsVO;
import com.itgrids.service.IItcDashboardService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class ItcController {
	
		private static final Logger LOG = Logger.getLogger(ItcController.class);
	
		@Autowired
		private IItcDashboardService itcDashboardService;
		
		@RequestMapping(value ="/itcDashboard",method = RequestMethod.GET)
	    public String drainsDashboard(ModelMap model) {
			return "ItcDashboard";
	    }

	   @RequestMapping(value = "/getPromotionsOverviewByDepartmentType", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  ItecOverviewVO  getPromotionsOverviewByDepartmentType(@RequestBody InputVO inputVO) {
		  ItecOverviewVO  reusltVO = itcDashboardService.getPromotionsOverviewByDepartmentType(inputVO);
	       return reusltVO;
	  }
	   @RequestMapping(value = "/getPromotionsDetailedDepartmentWise", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ItInformationDtlsVO>  getPromotionsDetailedDepartmentWise(@RequestBody InputVO inputVO) {
		   List<ItInformationDtlsVO>  resultList = itcDashboardService.getPromotionsDetailedDepartmentWise(inputVO);
	       return resultList;
	   }
	   @RequestMapping(value = "/getEOfficePendencyDtlsByDepartmentType", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  EofficeDtlsVO  getEOfficePendencyDtlsByDepartmentType(@RequestBody InputVO inputVO) {
		   EofficeDtlsVO  reusltVO = itcDashboardService.getEOfficePendencyDtlsByDepartmentType(inputVO);
		    return reusltVO;
	   }
	   @RequestMapping(value = "/getEOfficePendencyByDepartmentAndDayWise", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<EofficeDtlsVO>  getEOfficePendencyByDepartmentAndDayWise(@RequestBody InputVO inputVO) {
		   List<EofficeDtlsVO>  resultList = itcDashboardService.getEOfficePendencyByDepartmentAndDayWise(inputVO);
	    return resultList;
	   }
	   @RequestMapping(value = "/getMeesevaSLAOverviewDtls", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<MeesevaDtlsVO>  getMeesevaSLAOverviewDtls(@RequestBody InputVO inputVO) {
		   List<MeesevaDtlsVO>  resultList = itcDashboardService.getMeesevaSLAOverviewDtls(inputVO);
		   return resultList;
	   }
	   @RequestMapping(value = "/getMeesevaSLAMonitoringDtlsDepartmentWise", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody   List<MeesevaDtlsVO>  getMeesevaSLAMonitoringDtlsDepartmentWise(@RequestBody InputVO inputVO) {
		    List<MeesevaDtlsVO>  resultList = itcDashboardService.getMeesevaSLAMonitoringDtlsDepartmentWise(inputVO);
		   return resultList;
	   }
	   @RequestMapping(value = "/getMeesevaKPIIndicatorsProgressDtls", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<MeesevaKPIDtlsVO>  getMeesevaKPIIndicatorsProgressDtls(@RequestBody InputVO inputVO) {
		   List<MeesevaKPIDtlsVO>  resultList = itcDashboardService.getMeesevaKPIIndicatorsProgressDtls(inputVO);
		   return resultList;
	   }
	   @RequestMapping(value = "/getMeesevaKPIIndicatorsPeriodWise", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<MeesevaKPIDtlsVO>  getMeesevaKPIIndicatorsPeriodWise(@RequestBody InputVO inputVO) {
		   List<MeesevaKPIDtlsVO>  resultList = itcDashboardService.getMeesevaKPIIndicatorsPeriodWise(inputVO);
		   return resultList;
	   }
	   @RequestMapping(value = "/getCMEDOBOverview", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  CmEoDBDtlsVO  getCMEDOBOverview(@RequestBody InputVO inputVO) {
		   CmEoDBDtlsVO  reusltVO = itcDashboardService.getCMEDOBOverview(inputVO);
		   return reusltVO;
	   }
	   @RequestMapping(value = "/getCMEDOBReportStatusWise", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<CmEoDBDtlsVO>  getCMEDOBReportStatusWise(@RequestBody InputVO inputVO) {
		   List<CmEoDBDtlsVO>  resultList = itcDashboardService.getCMEDOBReportStatusWise(inputVO);
		   return resultList;
	   }
	   @RequestMapping(value = "/getAPInnovationSocietyOverview", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  ApInnovationSocietyOverviewVO  getAPInnovationSocietyOverview(@RequestBody InputVO inputVO) {
		   ApInnovationSocietyOverviewVO  reusltVO = itcDashboardService.getAPInnovationSocietyOverview(inputVO);
		   return reusltVO;
	   }
	   @RequestMapping(value = "/getAPISXLR8APDetailedData", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ApInnovationCenterVO>  getAPISXLR8APDetailedData(@RequestBody InputVO inputVO) {
		   List<ApInnovationCenterVO>  resultList = itcDashboardService.getAPISXLR8APDetailedData(inputVO);
		   return resultList;
	   }
	   @RequestMapping(value = "/getCampaignsDetailedData", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ApInnovationCenterVO>  getCampaignsDetailedData(@RequestBody InputVO inputVO) {
		   List<ApInnovationCenterVO>  resultList = itcDashboardService.getCampaignsDetailedData(inputVO);
		   return resultList;
	   }
	   @RequestMapping(value = "/getCampusInnovationCentersDetailedData", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ApInnovationCenterVO>  getCampusInnovationCentersDetailedData(@RequestBody InputVO inputVO) {
		   List<ApInnovationCenterVO>  resultList = itcDashboardService.getCampusInnovationCentersDetails(inputVO);
		   return resultList;
	   }
	   @RequestMapping(value = "/getCohortDetailsByCohortId", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<CohortDtlsVO>  getCohortDetailsByCohortId(@RequestBody InputVO inputVO) {
		   List<CohortDtlsVO>  resultList = itcDashboardService.getCohortDetailsByCohortId(inputVO);
		   return resultList;
	   }
	   @RequestMapping(value = "/getInnovationAwardsDetailedData", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<InnovationSocietyDtlsVO>  getInnovationAwardsDetailedData(@RequestBody InputVO inputVO) {
		   List<InnovationSocietyDtlsVO>  resultList = itcDashboardService.getInnovationAwardsDetailedData(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getITSectorWiseOverviewDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ItecPromotionDetailsVO>  getITSectorWiseOverviewDetails(@RequestBody InputVO inputVO) {
		   List<ItecPromotionDetailsVO>  resultList = itcDashboardService.getITSectorWiseOverviewDetails();
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getITSectorCategoryWiseDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ItecPromotionDetailsVO>  getITSectorCategoryWiseDetails(@RequestBody InputVO inputVO) {
		   List<ItecPromotionDetailsVO>  resultList = itcDashboardService.getITSectorCategoryWiseDetails(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getITDistrictWiseDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ItecPromotionDetailsVO>  getITDistrictWiseDetails(@RequestBody InputVO inputVO) {
		   List<ItecPromotionDetailsVO>  resultList = itcDashboardService.getITDistrictWiseDetails(inputVO);
		   return resultList;
	   }
}
