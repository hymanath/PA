package com.itgrids.controllers.web;

import java.util.ArrayList;
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
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InnovationSocietyDtlsVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.ItecCMeoDBDetailsVO;
import com.itgrids.dto.ItecEOfficeVO;
import com.itgrids.dto.ItecPromotionDetailsVO;
import com.itgrids.dto.MeesevaDtlsVO;
import com.itgrids.dto.MeesevaKPIDtlsVO;
import com.itgrids.model.MeesevaKpiCenters;
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
		
		@RequestMapping(value ="/PReOfficeDashboard",method = RequestMethod.GET)
	    public String prEOffice(ModelMap model) {
			return "PReOffice";
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
	   public @ResponseBody  CmEoDBDtlsVO  getCMeoDBSectorWiseStatusDetais(@RequestBody InputVO inputVO) {
		   CmEoDBDtlsVO  resultVO = itcDashboardService.getCMeoDBSectorWiseStatusDetais(inputVO);
		   return resultVO;
	   }
	   
	   @RequestMapping(value = "/getMeesevaSLADepartmentDetails", method = RequestMethod.POST)
	   public @ResponseBody  List<MeesevaDtlsVO>  getMeesevaSLADepartmentDetails() {
		   List<MeesevaDtlsVO>  resultList = itcDashboardService.getMeesevaSLADepartmentDetails();
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getMeesevaKPIOverViewDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  MeesevaKPIDtlsVO  getMeesevaKPIOverViewDetails(@RequestBody InputVO inputVO) {
		   MeesevaKPIDtlsVO  resultVO = itcDashboardService.getMeesevaKPIOverViewDetails(inputVO);
		   return resultVO;
	   }
	   
	   @RequestMapping(value = "/getMeesevaKPITargetAchieveDetails", method = RequestMethod.POST)
	   public @ResponseBody  List<MeesevaKPIDtlsVO>  getMeesevaKPITargetAchieveDetails() {
		   List<MeesevaKPIDtlsVO>  resultList = itcDashboardService.getMeesevaKPITargetAchieveDetails();
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getMeesevaKPILocationWiseDetails", method = RequestMethod.POST)
	   public @ResponseBody  List<MeesevaKPIDtlsVO>  getMeesevaKPILocationWiseDetails() {
		   List<MeesevaKPIDtlsVO>  resultList = itcDashboardService.getMeesevaKPILocationWiseDetails();
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getMeesevaSLACatWiseAbstarctDetails", method = RequestMethod.POST)
	   public @ResponseBody  MeesevaDtlsVO  getMeesevaSLACatWiseAbstarctDetails() {
		   MeesevaDtlsVO  resultVO = itcDashboardService.getMeesevaSLACatWiseAbstarctDetails();
		   return resultVO;
	   }
	   
	   @RequestMapping(value = "/getMeesevaSLAServiceWiseDetails", method = RequestMethod.POST)
	   public @ResponseBody  List<MeesevaDtlsVO>  getMeesevaSLAServiceWiseDetails() {
		   List<MeesevaDtlsVO>  resultList = itcDashboardService.getMeesevaSLAServiceWiseDetails();
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getEOfcDepartOverviewDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ItecEOfficeVO>  getEOfcDepartOverviewDetails(@RequestBody InputVO inputVO) {
		   List<ItecEOfficeVO>  returnList = itcDashboardService.getEOfcDepartOverviewDetails(inputVO);
		   return returnList;
	   }
	   
	   @RequestMapping(value = "/getEofficeDesginationWiseDetailsFrDepartment", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ItecEOfficeVO>  getEofficeDesginationWiseDetailsFrDepartment(@RequestBody InputVO inputVO) {
		   List<ItecEOfficeVO>  returnList = itcDashboardService.getEofficeDesginationWiseDetailsFrDepartment(inputVO);
		   return returnList;
	   }
	   
	   @RequestMapping(value = "/getMeesevaKPIOnlineServiceDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<MeesevaKPIDtlsVO>  getMeesevaKPIOnlineServiceDetails(@RequestBody InputVO inputVO) {
		   List<MeesevaKPIDtlsVO>  resultList = itcDashboardService.getMeesevaKPIOnlineServiceDetails(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getMeesevaKPIOnlineDeptWiseCuntDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<MeesevaKPIDtlsVO>  getMeesevaKPIOnlineDeptWiseCuntDetails(@RequestBody InputVO inputVO) {
		   List<MeesevaKPIDtlsVO>  resultList = itcDashboardService.getMeesevaKPIOnlineDeptWiseCuntDetails(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getMeesevaKPIMobileSevicesDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<MeesevaKPIDtlsVO>  getMeesevaKPIMobileSevicesDetails(@RequestBody InputVO inputVO) {
		   List<MeesevaKPIDtlsVO>  resultList = itcDashboardService.getMeesevaKPIMobileSevicesDetails(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getMeesevaKPIMobileDeptSevicesDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<MeesevaKPIDtlsVO>  getMeesevaKPIMobileDeptSevicesDetails(@RequestBody InputVO inputVO) {
		   List<MeesevaKPIDtlsVO>  resultList = itcDashboardService.getMeesevaKPIMobileDeptSevicesDetails(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getMeesevaKPIOnlineServiceOverviewCount", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  MeesevaKPIDtlsVO  getMeesevaKPIOnlineServiceOverviewCount(@RequestBody InputVO inputVO) {
		   MeesevaKPIDtlsVO  resultVO = itcDashboardService.getMeesevaKPIOnlineServiceOverviewCount(inputVO);
		   return resultVO;
	   }
	   
	   @RequestMapping(value = "/getMeesevaKPIMobileAppServiceOverviewCount", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  MeesevaKPIDtlsVO  getMeesevaKPIMobileAppServiceOverviewCount(@RequestBody InputVO inputVO) {
		   MeesevaKPIDtlsVO  resultVO = itcDashboardService.getMeesevaKPIMobileAppServiceOverviewCount(inputVO);
		   return resultVO;
	   }
	   
	   @RequestMapping(value = "/getMeesevaKPIOnlineServiceYearWiseDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<MeesevaKPIDtlsVO>  getMeesevaKPIOnlineServiceYearWiseDetails(@RequestBody InputVO inputVO) {
		   List<MeesevaKPIDtlsVO>  resultList = itcDashboardService.getMeesevaKPIOnlineServiceYearWiseDetails(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getMeesevaKPIMobileSevicesYearWiseDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<MeesevaKPIDtlsVO>  getMeesevaKPIMobileSevicesYearWiseDetails(@RequestBody InputVO inputVO) {
		   List<MeesevaKPIDtlsVO>  resultList = itcDashboardService.getMeesevaKPIMobileSevicesYearWiseDetails(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getMeesevaKPINewOnlineServiceOverviewCount", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  MeesevaKPIDtlsVO  getMeesevaKPINewOnlineServiceOverviewCount(@RequestBody InputVO inputVO) {
		   MeesevaKPIDtlsVO  resultVO = itcDashboardService.getMeesevaKPINewOnlineServiceOverviewCount(inputVO);
		   return resultVO;
	   }
	   
	   @RequestMapping(value = "/getMeesevaKPINewOnlineServiceYearWiseDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<MeesevaKPIDtlsVO>  getMeesevaKPINewOnlineServiceYearWiseDetails(@RequestBody InputVO inputVO) {
		   List<MeesevaKPIDtlsVO>  resultList = itcDashboardService.getMeesevaKPINewOnlineServiceYearWiseDetails(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getMeesevaKPINewOnlineDeptWiseCuntDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<MeesevaKPIDtlsVO>  getMeesevaKPINewOnlineDeptWiseCuntDetails(@RequestBody InputVO inputVO) {
		   List<MeesevaKPIDtlsVO>  resultList = itcDashboardService.getMeesevaKPINewOnlineDeptWiseCuntDetails(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/savingEofcDataDetails", method = RequestMethod.POST)
	   public @ResponseBody  IdNameVO  savingEofcDataDetails() {
		   IdNameVO  finalVO = itcDashboardService.savingEofcDataDetails();
		   return finalVO;
	   }
	   
	   @RequestMapping(value = "/getEOfcDepartOverviewDetailsNew", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ItecEOfficeVO>  getEOfcDepartOverviewDetailsNew(@RequestBody InputVO inputVO) {
		   List<ItecEOfficeVO>  returnList = itcDashboardService.getEOfcDepartOverviewDetailsNew(inputVO);
		   return returnList;
	   }
	   
	   @RequestMapping(value = "/getEofficeDesginationWiseDetailsFrDepartmentNew", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ItecEOfficeVO>  getEofficeDesginationWiseDetailsFrDepartmentNew(@RequestBody InputVO inputVO) {
		   List<ItecEOfficeVO>  returnList = itcDashboardService.getEofficeDesginationWiseDetailsFrDepartmentNew(inputVO);
		   return returnList;
	   }
	   
	   @RequestMapping(value = "/getEOfcPrAndRdDepartsOverviewDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ItecEOfficeVO>  getEOfcPrAndRdDepartsOverviewDetails(@RequestBody InputVO inputVO) {
		   List<ItecEOfficeVO>  returnList = itcDashboardService.getEOfcPrAndRdDepartsOverviewDetails(inputVO);
		   return returnList;
	   }
	   
	   @RequestMapping(value = "/getEofficePrAndRdDeptDesginationDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ItecEOfficeVO>  getEofficePrAndRdDeptDesginationDetails(@RequestBody InputVO inputVO) {
		   List<ItecEOfficeVO>  returnList = itcDashboardService.getEofficePrAndRdDeptDesginationDetails(inputVO);
		   return returnList;
	   }
	   @RequestMapping(value = "/saveMeesevaKPIDetails", method = RequestMethod.POST)
	   public @ResponseBody  IdNameVO  saveMeesevaKPIDetails() {
		   IdNameVO  finalVO = null;
		   
		String[] districtIdsArr =  {"01","02","03","04","05","06","07","08","09","10","11","12","13"};
			List<String> districtIds = new ArrayList<String>(0);
			if(districtIdsArr != null && districtIdsArr.length > 0){
				for (int i = 0; i < districtIdsArr.length; i++) {
					districtIds.add(districtIdsArr[i]);
				}
			}
		   
			if(districtIds != null && !districtIds.isEmpty()){
				for (String districtIdStr : districtIds) {
					 finalVO = itcDashboardService.saveMeesevaKPIDetails(districtIdStr);
				}
			}
			finalVO.setName("SUCCESS");
		   return finalVO;
	   }
	   
	   @RequestMapping(value = "/getMeesevaKPIOverViewDetailsNew", method = RequestMethod.POST)
	   public @ResponseBody  MeesevaKPIDtlsVO  getMeesevaKPIOverViewDetailsNew() {
		   MeesevaKPIDtlsVO  resultVO = itcDashboardService.getMeesevaKPIOverViewDetailsNew();
		   return resultVO;
	   }
	   
	   @RequestMapping(value = "/getMeesevaKPILocationWiseDetailsNew", method = RequestMethod.POST)
	   public @ResponseBody  List<MeesevaKPIDtlsVO>  getMeesevaKPILocationWiseDetailsNew() {
		   List<MeesevaKPIDtlsVO>  resultList = itcDashboardService.getMeesevaKPILocationWiseDetailsNew();
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getMeesevaCentersForDistrict", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<MeesevaKPIDtlsVO>  getMeesevaCentersForDistrict(@RequestBody InputVO inputVO) {
		   List<MeesevaKPIDtlsVO>  resultList = itcDashboardService.getMeesevaCentersForDistrict(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/saveMeesevaKPITargetAchievementValues", method = RequestMethod.POST)
	   public @ResponseBody  IdNameVO  saveMeesevaKPITargetAchievementValues() {
		   IdNameVO  finalVO = null;
			 finalVO = itcDashboardService.saveMeesevaKPITargetAchievementValues();
			 return finalVO;
	   }
	   
	   @RequestMapping(value = "/getApInnovationOverviewDetails", method = RequestMethod.POST)
	   public @ResponseBody  List<ApInnovationSocietyOverviewVO>  getApInnovationOverviewDetails() {
		   List<ApInnovationSocietyOverviewVO>  resultList = itcDashboardService.getApInnovationOverviewDetails();
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getApInnovationIncubatorsXLr8APDetails", method = RequestMethod.POST)
	   public @ResponseBody  List<ApInnovationSocietyOverviewVO>  getApInnovationIncubatorsXLr8APDetails() {
		   List<ApInnovationSocietyOverviewVO>  resultList = itcDashboardService.getApInnovationIncubatorsXLr8APDetails();
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getApInnovationIncubatorsOtherBlockDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ApInnovationSocietyOverviewVO>  getApInnovationIncubatorsOtherBlockDetails(@RequestBody InputVO inputVO) {
		   List<ApInnovationSocietyOverviewVO>  resultList = itcDashboardService.getApInnovationIncubatorsOtherBlockDetails(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getApInnovationCohortWiseDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ApInnovationSocietyOverviewVO>  getApInnovationCohortWiseDetails(@RequestBody InputVO inputVO) {
		   List<ApInnovationSocietyOverviewVO>  resultList = itcDashboardService.getApInnovationCohortWiseDetails(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getApInnovationBootCampDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ApInnovationSocietyOverviewVO>  getApInnovationBootCampDetails(@RequestBody InputVO inputVO) {
		   List<ApInnovationSocietyOverviewVO>  resultList = itcDashboardService.getApInnovationBootCampDetails(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getApInnovationEventDetails", method = RequestMethod.POST)
	   public @ResponseBody  List<ApInnovationSocietyOverviewVO>  getApInnovationEventDetails() {
		   List<ApInnovationSocietyOverviewVO>  resultList = itcDashboardService.getApInnovationEventDetails();
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getApInnovationActivityDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	   public @ResponseBody  List<ApInnovationSocietyOverviewVO>  getApInnovationActivityDetails(@RequestBody InputVO inputVO) {
		   List<ApInnovationSocietyOverviewVO>  resultList = itcDashboardService.getApInnovationActivityDetails(inputVO);
		   return resultList;
	   }
	   
	   @RequestMapping(value = "/getApInnovationIndicatorDetails", method = RequestMethod.POST)
	   public @ResponseBody  List<ApInnovationSocietyOverviewVO>  getApInnovationIndicatorDetails() {
		   List<ApInnovationSocietyOverviewVO>  resultList = itcDashboardService.getApInnovationIndicatorDetails();
		   return resultList;
	   }
}
