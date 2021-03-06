package com.itgrids.controllers.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LabourBudgetOverViewVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.NregaLocationOverviewVO;
import com.itgrids.dto.NregaPaymentsVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsOverviewVO;
import com.itgrids.dto.NregsProjectsVO;
import com.itgrids.dto.ResponseVO;
import com.itgrids.dto.UserVO;
import com.itgrids.dto.WaterTanksClorinationVO;
import com.itgrids.dto.WebserviceDetailsVO;
import com.itgrids.service.IConstituencyWiseWorkStatusService;
import com.itgrids.service.IUserService;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.service.integration.impl.INREGSTCSService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class NregsDashboardController {
	
	private static final Logger LOG = Logger.getLogger(NregsDashboardController.class);
	
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	
	@Autowired
	private INREGSTCSService nregsTcsService;
	@Autowired
	private IUserService userServiceImpl;
	@Autowired
	private IConstituencyWiseWorkStatusService constituencyWiseWorkStatusService;
		
	/*@RequestMapping(value ="/test", method = RequestMethod.GET)
    public String fundManagementDashboardPage(ModelMap model) {
		webServiceUtilService.testMethod();
		return "fundManagementDashboard";
    }*/

	/*@RequestMapping(value ="/MGNREGSDashboard", method = RequestMethod.GET)
    public String mgnregsDashBoardPage(ModelMap model,HttpSession session){
		if (null != session.getAttribute("locationTypeId")  && !session.getAttribute("locationTypeId").equals("")){
			AddressVO addressVO = userServiceImpl.getOriginalLocationIdForTempId(Long.valueOf(session.getAttribute("locationTypeId").toString()),session.getAttribute("locationValue").toString(),session.getAttribute("fromPage").toString(),session.getAttribute("toPage").toString());
			model.addAttribute("addressVO", addressVO);
		}
		return "MGNREGS";
    }*/
	
	@RequestMapping(value ="/MGNREGSDashboard", method = RequestMethod.GET)
    public String mgnregsDashBoardPage(ModelMap model) {
		return "MGNREGS";
    }
	
	@PostMapping("/getNREGSProjectsOverview")
	public @ResponseBody List<NregsProjectsVO> getNREGSProjectsOverview(@RequestBody InputVO vo){
		List<NregsProjectsVO> locationVOList = null;
		try {
			locationVOList = nregsTcsService.getNREGSProjectsOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSProjectsOverview - NREGSController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getLabourBudgetOverview")
	public @ResponseBody LabourBudgetOverViewVO getLabourBudgetOverview(@RequestBody InputVO vo){
		LabourBudgetOverViewVO labourBudgetOverViewVO = null;
		try {
			labourBudgetOverViewVO = nregsTcsService.getLabourBudgetOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getLabourBudgetOverview - NREGSController controller", e);
		}
		return labourBudgetOverViewVO;
	}
	
	@PostMapping("/getLabourBudgetExpenditure")
	public @ResponseBody List<IdNameVO> getLabourBudgetExpenditure(@RequestBody InputVO vo){
		List<IdNameVO> locationVOList = null;
		try {
			locationVOList = nregsTcsService.getLabourBudgetExpenditure(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getLabourBudgetExpenditure - NREGSController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getNregaLevelwiseOverviewForLabourBudgetData")
	public @ResponseBody List<NregsDataVO> getNregaLevelwiseOverviewForLabourBudgetData(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelwiseOverviewForLabourBudgetData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSProjectsOverview - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getWebserviceHealthDetails")
	public @ResponseBody List<WebserviceDetailsVO> getWebserviceHealthDetails(@RequestBody InputVO vo){
		try{
			List<WebserviceDetailsVO> detailsVOs = nregsTcsService.getWebserviceHealthDetails(vo);
			return detailsVOs;
		}catch(Exception e){
			LOG.error("Exception raised at getWebserviceHealthDetails - getAHOverview controller", e);
		}
		return null;
	}
	
	@PostMapping("/getMGNregsDistrWiseCount")
	public @ResponseBody NregsDataVO getMGNregsDistrWiseConsti(@RequestBody InputVO vo){
		NregsDataVO levlWiseVO = null;
		try {
			levlWiseVO = nregsTcsService.getMGNregsDistrWiseConsti(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getMGNregsDistrWiseConsti - NREGSController controller", e);
		}
		return levlWiseVO;
	}
	
	@PostMapping("/getNregaLevelsWiseData")
	public @ResponseBody List<NregsDataVO> getNregaLevelsWiseData(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsWiseData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseData - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNregasOverview")
	public @ResponseBody NregsOverviewVO getNregasOverview(@RequestBody InputVO vo){
		NregsOverviewVO overViewVO = null;
		try {
			overViewVO = nregsTcsService.getNregasOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregasOverview - NREGSController controller", e);
		}
		return overViewVO;
	}
	
	@PostMapping("/getNregaLevelsWiseDataFrNewCalls")
	public @ResponseBody List<NregsDataVO> getNregaLevelsWiseDataFrNewCalls(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsWiseDataFrNewCalls(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataFrNewCalls - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNregaLevelsWiseDataFrAgriculture")
	public @ResponseBody List<NregsDataVO> getNregaLevelsWiseDataFrAgriculture(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsWiseDataFrAgriculture(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataFrAgriculture - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNregaLevelsWiseDataFrHorticulture")
	public @ResponseBody List<NregsDataVO> getNregaLevelsWiseDataFrHorticulture(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsWiseDataFrHorticulture(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataFrHorticulture - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNregaLevelsWiseDataForCCRoads")
	public @ResponseBody List<NregsDataVO> getNregaLevelsWiseDataForCCRoads(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsWiseDataForCCRoads(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataForCCRoads - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNregaLevelsWiseDataFrAvenue")
	public @ResponseBody List<NregsDataVO> getNregaLevelsWiseDataFrAvenue(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsWiseDataFrAvenue(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataFrAvenue - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNREGSAbstractDataByType")
	public @ResponseBody List<NregsProjectsVO> getNREGSAbstractDataByType(@RequestBody InputVO vo){
		List<NregsProjectsVO> locationVOList = null;
		try {
			locationVOList = nregsTcsService.getNREGSAbstractDataByType(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSAbstractDataByType - NREGSController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getNregaLevelsWiseDataForNewFTOPayments")
	public @ResponseBody List<NregaPaymentsVO> getNregaLevelsWiseDataForNewFTOPayments(@RequestBody InputVO vo){
		List<NregaPaymentsVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsWiseDataForNewFTOPayments(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataForTimelyPayments - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	@PostMapping("/getNregaParliamentData")
	public @ResponseBody List<NregsDataVO> getNregaParliamentData(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaParliamentData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaParliamentData - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNREGSProjectsAbstractNew")
	public @ResponseBody List<NregsProjectsVO> getNREGSProjectsAbstractNew(@RequestBody InputVO vo){
		List<NregsProjectsVO> projectVOList = null;
		try {
			projectVOList = nregsTcsService.getNREGSProjectsAbstractNew(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSProjectsAbstractNew - NREGSController controller", e);
		}
		return projectVOList;
	}
	
	@PostMapping("/getAllNregaSubLocationDetails")
	public @ResponseBody List<LocationFundDetailsVO> getAllSubLocations(@RequestBody InputVO inputVO){
		List<LocationFundDetailsVO> locationFundDetailsVOList = nregsTcsService.getAllNregaSubLocationDetails(inputVO);
		return locationFundDetailsVOList;
	}
	
	@PostMapping("/getNregaLabourBudgetPanchatVsExpData")
	public @ResponseBody List<NregsDataVO> getNregaPanchatVsExpData(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaPanchatVsExpData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaPanchatVsExpData - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNregaLevelsWiseDataForFAPerformance")
	public @ResponseBody List<NregsDataVO> getNregaLevelsWiseDataForFAPerformance(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsWiseDataForFAPerformance(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataForFAPerformance - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNregaParliamentDataFrpayments")
	public @ResponseBody List<NregaPaymentsVO> getNregaParliamentDataFrpayments(@RequestBody InputVO vo){
		List<NregaPaymentsVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaParliamentDataFrpayments(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaParliamentDataFrpayments - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	@PostMapping("/getNregaPaymentsAbsAndOverview")
	public @ResponseBody NregaPaymentsVO getNregaPaymentsAbsAndOverview(@RequestBody InputVO vo){
		NregaPaymentsVO returnVO = null;
		try {
			returnVO = nregsTcsService.getNregaPaymentsAbsAndOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaPaymentsAbsAndOverview - NREGSController controller", e);
		}
		return returnVO;
	}
	@PostMapping("/getNREGSProjectsAbstractNewFrConstituency")
	public @ResponseBody List<NregsProjectsVO> getNREGSProjectsAbstractNewFrConstituency(@RequestBody InputVO vo){
		List<NregsProjectsVO> projectVOList = null;
		try {
			projectVOList = nregsTcsService.getNREGSProjectsAbstractNewFrConstituency(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSProjectsAbstractNewFrConstituency - NREGSController controller", e);
		}
		return projectVOList;
	}
	
	@PostMapping("/getNREGSAbstractDataByTypeFrConstituency")
	public @ResponseBody List<NregsProjectsVO> getNREGSAbstractDataByTypeFrConstituency(@RequestBody InputVO vo){
		List<NregsProjectsVO> locationVOList = null;
		try {
			locationVOList = nregsTcsService.getNREGSAbstractDataByTypeFrConstituency(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSAbstractDataByTypeFrConstituency - NREGSController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getNregaPanchatVsExpLevelWiseCountsData")
	public @ResponseBody List<NregsDataVO> getNregaPanchatVsExpLevelWiseCountsData(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaPanchatVsExpLevelWiseCountsData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaPanchatVsExpLevelWiseCountsData - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	@PostMapping("/getIhhlAbstractData")
	public @ResponseBody  NregaLocationOverviewVO getIhhlAbstractData(@RequestBody InputVO vo){
		NregaLocationOverviewVO  resultVO = null;
		try {
			resultVO =  nregsTcsService.getIhhlOverviewData(vo);
		} catch(Exception e){
			LOG.error("Exception raised at getIhhlAbstractData() - NREGSController controller", e);
		}
		return resultVO;
	}
	@PostMapping("/getIhhlOverviewData")
	public @ResponseBody  NregaLocationOverviewVO getIhhlOverviewData(@RequestBody InputVO vo){
		NregaLocationOverviewVO  resultVO = null;
		try {
			resultVO =  nregsTcsService.getIhhlOverviewData(vo);
		} catch(Exception e){
			LOG.error("Exception raised at NregsOverviewVO() - NREGSController controller", e);
		}
		return resultVO;
	}
	@PostMapping("/getLocationIhhlData")
	public @ResponseBody  List<NregaLocationOverviewVO> getLocationIhhlData(@RequestBody InputVO vo){
		List<NregaLocationOverviewVO>  resultList = null;
		try {
			resultList =  nregsTcsService.getLocationIhhlData(vo);
		} catch(Exception e){
			LOG.error("Exception raised at getLocationIhhlData() - NREGSController controller", e);
		}
		return resultList;
	}
	
	@PostMapping("/getNregsProjectsIWMPAbstract")
	public @ResponseBody List<NregsProjectsVO> getNregsProjectsIWMPAbstract(@RequestBody InputVO vo){
		List<NregsProjectsVO> projectVOList = null;
		try {
			projectVOList = nregsTcsService.getNregsProjectsIWMPAbstract(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsProjectsIWMPAbstract - NREGSController controller", e);
		}
		return projectVOList;
	}
	
	@PostMapping("/getLocationWiseWaterBudgetDetails")
	public @ResponseBody List<NregsDataVO> getLocationWiseWaterBudgetDetails(@RequestBody InputVO vo){
		List<NregsDataVO> projectVOList = null;
		try {
			projectVOList = nregsTcsService.getLocationWiseWaterBudgetDetails(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseWaterBudgetDetails - NREGSController controller", e);
		}
		return projectVOList;
	}
	@PostMapping("/getNregaPaymentsAbsAndOverviewDtls")
	public @ResponseBody List<NregaPaymentsVO> getNregaPaymentsAbsAndOverviewDtls(@RequestBody InputVO vo){
		List<NregaPaymentsVO> resultList = null;
		try {
			resultList = nregsTcsService.getNregaPaymentsAbsAndOverviewDtls(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaPaymentsAbsAndOverviewDtls - NREGSController controller", e);
		}
		return resultList;
	}
	@PostMapping("/getNregaPaymentsDtlsLocationWise")
	public @ResponseBody List<NregaPaymentsVO> getNregaPaymentsDtlsLocationWise(@RequestBody InputVO vo){
		List<NregaPaymentsVO> resultList = null;
		try {
			resultList = nregsTcsService.getNregaPaymentsDtlsLocationWise(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaPaymentsDtlsLocationWise - NREGSController controller", e);
		}
		return resultList;
	}
	
	@PostMapping("/getNREGSForestAbstact")
	public @ResponseBody List<NregsProjectsVO> getNREGSForestAbstact(@RequestBody InputVO vo){
		List<NregsProjectsVO> locationVOList = null;
		try {
			locationVOList = nregsTcsService.getNREGSForestAbstact(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSForestAbstact - NREGSController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getNregaForestLevelData")
	public @ResponseBody List<NregsDataVO> getNregaForestLevelData(@RequestBody InputVO vo){
		List<NregsDataVO> locationVOList = null;
		try {
			locationVOList = nregsTcsService.getNregaForestLevelData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaForestLevelData - NREGSController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getNregasForestOverview")
	public @ResponseBody NregsOverviewVO getNregasForestOverview(@RequestBody InputVO vo){
		NregsOverviewVO overViewVO = null;
		try {
			overViewVO = nregsTcsService.getNregasForestOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregasForestOverview - NREGSController controller", e);
		}
		return overViewVO;
	}
	
	@PostMapping("/getSessionToken")
	public @ResponseBody WaterTanksClorinationVO getSessionTokenDetails(@RequestBody InputVO vo){
		WaterTanksClorinationVO resultVO = null;
		try {
			resultVO = nregsTcsService.getSessionTokenDetails(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getSessionTokenDetails - NREGSController controller", e);
		}
		return resultVO;
	}
	
	@RequestMapping(value ="/waterTanksClorinationDashBoard", method = RequestMethod.GET)
    public String clorinationDashBoardPage(ModelMap model) {
		return "waterTanksClorination";
    }
	
	@PostMapping("/getWaterBodyCumulativeCounts")
	public @ResponseBody WaterTanksClorinationVO getWaterBodyCumulativeCounts(@RequestBody InputVO vo){
		WaterTanksClorinationVO resultVO = null;
		try {
			resultVO = nregsTcsService.getWaterBodyCumulativeCounts(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getWaterBodyCumulativeCounts - NREGSController controller", e);
		}
		return resultVO;
	}
	
	@PostMapping("/getDistrictWiseClorinationDetails")
	public @ResponseBody List<WaterTanksClorinationVO> getDistrictWiseClorinationDetails(@RequestBody InputVO vo){
		List<WaterTanksClorinationVO> returnList = null;
		try {
			returnList = nregsTcsService.getDistrictWiseClorinationDetails(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getDistrictWiseClorinationDetails - NREGSController controller", e);
		}
		return returnList;
	}
	
	@PostMapping("/getLocationWiseClorinationDetails")
	public @ResponseBody List<WaterTanksClorinationVO> getLocationWiseClorinationDetails(@RequestBody InputVO vo){
		List<WaterTanksClorinationVO> returnList = null;
		try {
			returnList = nregsTcsService.getLocationWiseClorinationDetails(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseClorinationDetails - NREGSController controller", e);
		}
		return returnList;
	}
	
	@PostMapping("/getNregaOtherMCCLevelData")
	public @ResponseBody List<NregsDataVO> getNregaOtherMCCLevelData(@RequestBody InputVO vo){
		List<NregsDataVO> locationVOList = null;
		try {
			locationVOList = nregsTcsService.getNregaOtherMCCLevelData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaOtherMCCLevelData - NREGSController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getNregaLevelsWiseDataFrCoffeePlantation")
	public @ResponseBody List<NregsDataVO> getNregaLevelsWiseDataFrCoffeePlantation(@RequestBody InputVO vo){
		List<NregsDataVO> locationVOList = null;
		try {
			locationVOList = nregsTcsService.getNregaLevelsWiseDataFrCoffeePlantation(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataFrCoffeePlantation - NREGSController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getNregaMCCNewComponetsLevelData")
	public @ResponseBody List<NregsDataVO> getNregaMCCNewComponetsLevelData(@RequestBody InputVO vo){
		List<NregsDataVO> locationVOList = null;
		try {
			locationVOList = nregsTcsService.getNregaMCCNewComponetsLevelData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaMCCNewComponetsLevelData - NREGSController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getNregaCovergancePROtherLevelData")
	public @ResponseBody List<NregsDataVO> getNregaCovergancePROtherLevelData(@RequestBody InputVO vo){
		List<NregsDataVO> locationVOList = null;
		try {
			locationVOList = nregsTcsService.getNregaCovergancePROtherLevelData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaCovergancePROtherLevelData - NREGSController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getWorkWiseAbstractForMCCOthers")
	public @ResponseBody List<NregsDataVO> getWorkWiseAbstractForMCCOthers(@RequestBody InputVO vo){
		List<NregsDataVO> locationVOList = null;
		try {
			locationVOList = nregsTcsService.getWorkWiseAbstractForMCCOthers(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getWorkWiseAbstractForMCCOthers - NREGSController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getNregaOtherMCCAbstarctData")
	public @ResponseBody List<NregsDataVO> getNregaOtherMCCAbstarctData(@RequestBody InputVO vo){
		List<NregsDataVO> locationVOList = null;
		try {
			locationVOList = nregsTcsService.getNregaOtherMCCAbstarctData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaOtherMCCLevelData - NREGSController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getManWorkDaysOfNrega")
	public @ResponseBody List<NregsDataVO> getManWorkDaysOfNrega(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getManWorkDaysOfNrega(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getManWorkDaysOfNrega - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	//Long statusId,String comment
		@PostMapping(value="/saveNregaComponentComments")
		private @ResponseBody InputVO  saveQuestionOptionsDetails(@RequestBody InputVO vo){
			try{
				return nregsTcsService.saveNregaComponentComments(vo.getLocationId(),vo.getSourceId(),vo.getCategory(),vo.getAssetType(),vo.getDisplayType());
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@PostMapping("/getNregaComponentStatus")
		public @ResponseBody List<InputVO> getNregaComponentStatus(@RequestBody InputVO vo){
			List<InputVO> statusList = null;
			try {
				statusList = nregsTcsService.getNregaComponentStatus(vo);
				
			} catch (Exception e) {
				LOG.error("Exception raised at getNregaComponentStatus - NREGSController controller", e);
			}
			return statusList;
		}
		
	@PostMapping("/getManWorkDaysOfNregaMonthWise")
	public @ResponseBody List<NregsDataVO> getManWorkDaysOfNregaMonthWise(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getManWorkDaysOfNregaMonthWise(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getManWorkDaysOfNregaMonthWise - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getManWorksExpenditureDetails")
	public @ResponseBody List<NregsDataVO> getManWorksExpenditureDetails(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getManWorksExpenditureDetails(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getManWorksExpenditureDetails - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getManWorksExpenditureAbstarct")
	public @ResponseBody List<NregsDataVO> getManWorksExpenditureAbstarct(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getManWorksExpenditureAbstarct(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getManWorksExpenditureAbstarct - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getAllDistricts")
	public @ResponseBody List<IdNameVO> getAllDistricts(){
		List<IdNameVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getAllDistricts();
			
		} catch (Exception e) {
			LOG.error("Exception raised at getAllDistricts - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getAllConstituiencesFrDistrict")
	public @ResponseBody List<IdNameVO> getAllConstituiencesFrDistrict(@RequestBody InputVO vo){
		List<IdNameVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getAllConstituiencesFrDistrict(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getAllConstituiencesFrDistrict - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getTehsilesFrConstituency")
	public @ResponseBody List<IdNameVO> getTehsilesFrConstituency(@RequestBody InputVO vo){
		List<IdNameVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getTehsilesFrConstituency(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getTehsilesFrConstituency - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getPanchayatsFrTehsil")
	public @ResponseBody List<IdNameVO> getPanchayatsFrTehsil(@RequestBody InputVO vo){
		List<IdNameVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getPanchayatsFrTehsil(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getPanchayatsFrTehsil - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@RequestMapping(value ="/panchayatExpenditureDashboard", method = RequestMethod.GET)
    public String mgnregsPanchayatWiseExpenditure(ModelMap model,HttpServletRequest request) {
		UserVO uservo = (UserVO) request.getSession().getAttribute("User");
		if(uservo != null){
			return "panchayatWiseExpenditure";
		}else{
			return "MGNREGSFieldLoginUpdates";
		}
		
    }
	
	@PostMapping("/getPanchayatsExpenditure")
	public @ResponseBody List<IdNameVO> getPanchayatsExpenditure(@RequestBody InputVO vo,HttpServletRequest request){
		List<IdNameVO> locationVOList = null;
		try {
			UserVO uservo = (UserVO) request.getSession().getAttribute("User");
			if(uservo != null){
				locationVOList = nregsTcsService.getPanchayatsExpenditure(vo,uservo.getPrDistrictId(),uservo.getAccessLvelId());
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getPanchayatsExpenditure - NREGSController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getPanchatVsExpData")
	public @ResponseBody List<NregsDataVO> getPanchatVsExpData(@RequestBody InputVO vo,HttpServletRequest request){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			UserVO uservo = (UserVO) request.getSession().getAttribute("User");
			if(uservo != null){
				levlWiseVOList = nregsTcsService.getPanchatVsExpData(vo,uservo.getPrDistrictId(),uservo.getAccessLvelId());
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getPanchatVsExpData - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping(value="/savePanchayatComponentComments")
	private @ResponseBody InputVO  savePanchayatComponentComments(@RequestBody InputVO vo,HttpServletRequest request){
		try{
			UserVO userVO = (UserVO) request.getSession().getAttribute("USER");
			if(userVO != null){
				return nregsTcsService.savePanchayatComponentComments(vo.getLocationId(),vo.getSourceId(),vo.getCategory(),vo.getAssetType(),vo.getDisplayType(),userVO.getUserId(),vo.getDivType());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/getFieldManDaysWorkDetails")
	public @ResponseBody List<NregsDataVO> getFieldManDaysWorkDetails(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getFieldManDaysWorkDetails(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getFieldManDaysWorkDetails - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getLocationWiseEmptyVacenciesDetails")
	public @ResponseBody List<NregsProjectsVO> getLocationWiseEmptyVacenciesDetails(@RequestBody InputVO vo){
		List<NregsProjectsVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getLocationWiseEmptyVacenciesDetails(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseEmptyVacenciesDetails - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/savingLabourBudgetRangeWiseExpenditureDetails")
	public @ResponseBody ResponseVO savingLabourBudgetRangeWiseExpenditureDetails(@RequestBody InputVO vo){
		ResponseVO responseVO = null;
		try {
			responseVO = constituencyWiseWorkStatusService.savingLabourBudgetRangeWiseExpenditureDetails(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at savingLabourBudgetRangeWiseExpenditureDetails - NREGSController controller", e);
		}
		return responseVO;
	}
	@PostMapping("/savingLabourBudgetRangeWiseExpenditureDetailsForEveryDayTemp")
	public @ResponseBody ResponseVO savingLabourBudgetRangeWiseExpenditureDetailsEveryDay(@RequestBody InputVO vo){
		ResponseVO responseVO = null;
		try {
			responseVO = constituencyWiseWorkStatusService.savingLabourBudgetRangeWiseExpenditureDetailsEveryDay(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at savingLabourBudgetRangeWiseExpenditureDetailsEveryDay - NREGSController controller", e);
		}
		return responseVO;
	}
	
	@PostMapping("/getComponentWiseOverview")
	public @ResponseBody NregsOverviewVO getComponentWiseOverview(@RequestBody InputVO vo,HttpServletRequest request){
		NregsOverviewVO locationVOList = null;
		try {
			UserVO uservo = (UserVO) request.getSession().getAttribute("User");
			if(uservo != null){
				locationVOList = constituencyWiseWorkStatusService.getComponentWiseOverview(vo,uservo.getPrDistrictId(),uservo.getAccessLvelId());
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getComponentWiseOverview - NREGSController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getComponentWiseLocationData")
	public @ResponseBody List<NregsDataVO> getComponentWiseLocationData(@RequestBody InputVO vo,HttpServletRequest request){
		List<NregsDataVO> locationVOList = null;
		try {
			UserVO uservo = (UserVO) request.getSession().getAttribute("User");
			if(uservo != null){
				locationVOList = constituencyWiseWorkStatusService.getComponentWiseLocationData(vo,uservo.getPrDistrictId(),uservo.getAccessLvelId());
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getComponentWiseLocationData - NREGSController controller", e);
		}
		return locationVOList;
	}
	@PostMapping("/saveNregaComponentsWiseAchvPerc")
	public @ResponseBody IdNameVO saveNregaComponentsWiseAchvPerc(){
		IdNameVO statusVO = null;
		try {
			statusVO = nregsTcsService.saveNregaComponentsWiseAchvPerc();
			
		} catch (Exception e) {
			LOG.error("Exception raised at saveNregaComponentsWiseAchvPerc - NREGSController controller", e);
		}
		return statusVO;
	}
	@PostMapping("/saveNregaComponentsWiseAchvPercTillToday")
	public @ResponseBody IdNameVO saveNregaComponentsWiseAchvPercTillToday(){
		IdNameVO statusVO = null;
		try {
			statusVO = nregsTcsService.saveNregaComponentsWiseAchvPercTillToday();
			
		} catch (Exception e) {
			LOG.error("Exception raised at saveNregaComponentsWiseAchvPercTillToday - NREGSController controller", e);
		}
		return statusVO;
	}
	@PostMapping("/getNregaComponentsData")
	public @ResponseBody List<NregsDataVO> getNregaComponentsData(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaComponentsData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaComponentsData - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	@PostMapping("/saveFAVacanciesPanchayatData")
	public @ResponseBody IdNameVO saveFAVacanciesPanchayatData(){
		IdNameVO statusVO = null;
		try {
			statusVO = nregsTcsService.saveFAVacanciesPanchayatData();
			
		} catch (Exception e) {
			LOG.error("Exception raised at saveFAVacanciesPanchayatData - NREGSController controller", e);
		}
		return statusVO;
	}
	@PostMapping("/savingFieldManDaysService")
	public @ResponseBody String savingFieldManDaysService(){
		String status = null;
		try {
			nregsTcsService.savingFieldManDaysService();
			
		} catch (Exception e) {
			LOG.error("Exception raised at savingFieldManDaysService - NREGSController controller", e);
		}
		return "Success";
	}
	@PostMapping("/getNregaPaymentsDepartmentWiseOverview")
	public @ResponseBody List<NregaPaymentsVO> getNregaPaymentsDepartmentWiseOverview(@RequestBody InputVO vo){
		List<NregaPaymentsVO> returnVO = null;
		try {
			returnVO = nregsTcsService.getNregaPaymentsDepartmentWiseOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaPaymentsDepartmentWiseOverview - NREGSController controller", e);
		}
		return returnVO;
	}
	@PostMapping("/getNregaPaymentsDeptDtlsLocationWise")
	public @ResponseBody List<NregaPaymentsVO> getNregaPaymentsDeptDtlsLocationWise(@RequestBody InputVO vo){
		List<NregaPaymentsVO> returnList = null;
		try {
			returnList = nregsTcsService.getNregaPaymentsDeptDtlsLocationWise(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaPaymentsDeptDtlsLocationWise - NREGSController controller", e);
		}
		return returnList;
	}
	
	@PostMapping("/getMaterialAvailabilityStatusForFinancialYear")
	public @ResponseBody List<NregsDataVO> getMaterialAvailabilityStatusForFinancialYear(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getMaterialAvailabilityStatusForFinancialYear(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getMaterialAvailabilityStatusForFinancialYear - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	@PostMapping("/getWorkCompletionData")
	public @ResponseBody List<NregsDataVO> getWorkCompletionData(@RequestBody InputVO vo){
		List<NregsDataVO> returnList = null;
		try {
			returnList = nregsTcsService.getWorkCompletionData(vo);
		} catch (Exception e){
			LOG.error("Exception raised at getWorkCompletionData - NREGSController controller",e);
		}
		return  returnList;
	}
}
