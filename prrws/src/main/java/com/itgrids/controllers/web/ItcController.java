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
import com.itgrids.dto.InnovationSocietyDtlsVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.ItecCMeoDBDetailsVO;
import com.itgrids.dto.ItecEOfficeVO;
import com.itgrids.dto.ItecPromotionDetailsVO;
import com.itgrids.dto.MeesevaDtlsVO;
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
	   @RequestMapping(value = "/getCMEDOBOverview", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  CmEoDBDtlsVO  getCMEDOBOverview(@RequestBody InputVO inputVo) {
		   CmEoDBDtlsVO  reusltVO = itcDashboardService.getCMEDOBOverview(inputVo);
		   return reusltVO;
	   }
	   @RequestMapping(value = "/getCMEDOBReportStatusWise", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ItecCMeoDBDetailsVO>  getCMEDOBReportStatusWise(@RequestBody InputVO inputVO) {
		   List<ItecCMeoDBDetailsVO>  resultList = itcDashboardService.getCMEDOBReportStatusWise(inputVO);
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
	   
	   @RequestMapping(value = "/getITSectorLeadCategoryWiseDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ItecPromotionDetailsVO>  getITSectorLeadCategoryWiseDetails(@RequestBody InputVO inputVO) {
		   List<ItecPromotionDetailsVO>  resultList = itcDashboardService.getITSectorLeadCategoryWiseDetails(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getITSectorSubLeadCategoryWiseDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ItecPromotionDetailsVO>  getITSectorSubLeadCategoryWiseDetails(@RequestBody InputVO inputVO) {
		   List<ItecPromotionDetailsVO>  resultList = itcDashboardService.getITSectorSubLeadCategoryWiseDetails(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getEOfcDepartWiseOverviewDetails", method = RequestMethod.POST)
	   public @ResponseBody  List<ItecEOfficeVO>  getEOfcDepartWiseOverviewDetails() {
		   List<ItecEOfficeVO>  returnList = itcDashboardService.getEOfcDepartWiseOverviewDetails();
		   return returnList;
	   }
	   
	   @RequestMapping(value = "/getEOfcDeptPendancyStatusWiseDetails", method = RequestMethod.POST)
	   public @ResponseBody  List<ItecEOfficeVO>  getEOfcDeptPendancyStatusWiseDetails() {
		   List<ItecEOfficeVO>  returnList = itcDashboardService.getEOfcDeptPendancyStatusWiseDetails();
		   return returnList;
	   }
	   
	   @RequestMapping(value = "/getEofficeDesignationWiseDetails", method = RequestMethod.POST)
	   public @ResponseBody  List<ItecEOfficeVO>  getEofficeDesignationWiseDetails() {
		   List<ItecEOfficeVO>  returnList = itcDashboardService.getEofficeDesignationWiseDetails();
		   return returnList;
	   }
	   
	   @RequestMapping(value = "/getEofficeDesignationWisePendencyDetails", method = RequestMethod.POST)
	   public @ResponseBody  List<ItecEOfficeVO>  getEofficeDesignationWisePendencyDetails() {
		   List<ItecEOfficeVO>  returnList = itcDashboardService.getEofficeDesignationWisePendencyDetails();
		   return returnList;
	   }
	   
	   @RequestMapping(value = "/getSectorWiseOverviewCountDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ItecPromotionDetailsVO>  getSectorWiseOverviewCountDetails(@RequestBody InputVO inputVO) {
		   List<ItecPromotionDetailsVO>  resultList = itcDashboardService.getSectorWiseOverviewCountDetails(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getCMeoDBOverviewDetails", method = RequestMethod.POST)
	   public @ResponseBody  List<ItecCMeoDBDetailsVO>  getCMeoDBOverviewDetails() {
		   List<ItecCMeoDBDetailsVO>  resultList = itcDashboardService.getCMeoDBOverviewDetails();
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getCMeoDBStatusDetails", method = RequestMethod.POST)
	   public @ResponseBody  List<ItecCMeoDBDetailsVO>  getCMeoDBStatusDetails() {
		   List<ItecCMeoDBDetailsVO>  resultList = itcDashboardService.getCMeoDBStatusDetails();
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getCMeoDBStatusCountDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ItecCMeoDBDetailsVO>  getCMeoDBStatusCountDetails(@RequestBody InputVO inputVO) {
		   List<ItecCMeoDBDetailsVO>  resultList = itcDashboardService.getCMeoDBStatusCountDetails(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getDepartmentWiseHierarchicalDetails", method = RequestMethod.POST)
	   public @ResponseBody  List<ItecEOfficeVO>  getDepartmentWiseHierarchicalDetails() {
		   List<ItecEOfficeVO>  returnList = itcDashboardService.getDepartmentWiseHierarchicalDetails();
		   return returnList;
	   }
	   
	   @RequestMapping(value = "/getCMeoDBStatusDetailsNew", method = RequestMethod.POST)
	   public @ResponseBody  List<ItecCMeoDBDetailsVO>  getCMeoDBStatusDetailsNew() {
		   List<ItecCMeoDBDetailsVO>  resultList = itcDashboardService.getCMeoDBStatusDetailsNew();
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getEofficeDesginationDetailsByDepartment", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ItecEOfficeVO>  getEofficeDesginationDetailsByDepartment(@RequestBody InputVO inputVO) {
		   List<ItecEOfficeVO>  resultList = itcDashboardService.getEofficeDesginationDetailsByDepartment(inputVO);
		   return resultList;
	   }
	   @RequestMapping(value = "/getCMeoDBSectorWiseStatusDetais", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<CmEoDBDtlsVO>  getCMeoDBSectorWiseStatusDetais(@RequestBody InputVO inputVO) {
		   List<CmEoDBDtlsVO>  resultList = itcDashboardService.getCMeoDBSectorWiseStatusDetais(inputVO);
		   return resultList;
	   }
}
