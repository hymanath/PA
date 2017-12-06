package com.itgrids.controllers.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.AddressVO;
import com.itgrids.dto.AmsVO;
import com.itgrids.dto.BasicVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KPIVO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.NregaLocationOverviewVO;
import com.itgrids.dto.RangeVO;
import com.itgrids.dto.RwsClickVO;
import com.itgrids.dto.StatusVO;
import com.itgrids.dto.WaterSourceVO;
import com.itgrids.dto.WorksVO;
import com.itgrids.service.IUserService;
import com.itgrids.tpi.rws.service.IRWSNICService;
import com.itgrids.tpi.rws.service.IRwsWorksSchedulerService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class RuralWaterSupplyDashBoardController {
	private static final Logger LOG = Logger.getLogger(RuralWaterSupplyDashBoardController.class);
	@Autowired
	private IRWSNICService rWSNICService;
	@Autowired
	private IUserService userServiceImpl;
	@Autowired
	private IRwsWorksSchedulerService rWSNICService2;
	
	@GetMapping("/ruralWaterSupplyDashBoard")
	public String ruralWaterSupplyDashBoardPage(ModelMap model,HttpSession session){
		if (null != session.getAttribute("locationTypeId")  && !session.getAttribute("locationTypeId").equals("")){
			AddressVO addressVO = userServiceImpl.getOriginalLocationIdForTempId(Long.valueOf(session.getAttribute("locationTypeId").toString()),session.getAttribute("locationValue").toString(),session.getAttribute("fromPage").toString(),session.getAttribute("toPage").toString());
			model.addAttribute("addressVO", addressVO);
		}
		return "ruralWaterSupplyDashBoard";
	}
	
	@GetMapping("/swatchBharatIHHLInfo")
	public String swatchBharatIHHLInfo(ModelMap model){
		return "swatchBharatIHHL";
	}
	
	@GetMapping("/swatchBharatPaymentsInfo")
	public String swatchBharatPaymentsInfo(ModelMap model){
		return "swatchBharatPayments";
	}
	@GetMapping("/worksDashBoard")
	public String worksDashBoard(ModelMap model){
		return "worksDashBoard";
	}
	@GetMapping("/waterSourcesDashBoard")
	public String waterSourcesDashBoard(ModelMap model){
		return "waterSourcesDashBoard";
	}
	@GetMapping("/assetsDashBoard")
	public String assetsDashBoard(ModelMap model){
		return "assetsDashBoard";
	}
	@GetMapping("/jalavaniDashBoard")
	public String jalavaniDashBoard(ModelMap model){
		return "jalavaniDashBoard";
	}
	
	@PostMapping("/getHabitationCoverageByStatusByLocationType")
	public @ResponseBody List<LocationVO> getHabitationCoverageByStatusByLocationType(@RequestBody InputVO vo) {
		List<LocationVO> locationVOList = null;
		try {
			locationVOList = rWSNICService.getHabitationCoverageByStatusByLocationType(vo);
			//locationVOList = rWSNICService.getHabitationCoverageStatus(vo);

		} catch (Exception e) {
			LOG.error(
					"Exception raised at getHabitationCoverageByStatusByLocationType - RuralWaterSupplyDashBoardController controller",
					e);
		}
		return locationVOList;
	}

	@PostMapping("/getLabTestDetails")
	public @ResponseBody BasicVO getLabTestDetails(@RequestBody InputVO vo) {
		BasicVO basicVO = null;
		try {
			basicVO = rWSNICService.getLabTestDetails(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getLabTestDetails - RuralWaterSupplyDashBoardController controller", e);
		}
		return basicVO;
	}

	@PostMapping("/getHabitationSupplyDetails")
	public @ResponseBody BasicVO getHabitationSupplyDetails(@RequestBody InputVO inputVO) {
		try {
			return rWSNICService.getHabitationSupplyDetails(inputVO);
		} catch (Exception e) {
			LOG.error("Exception raised at getHabitationSupplyDetails - RuralWaterSupplyDashBoardController controller",
					e);
		}
		return null;
	}

	@PostMapping("/getSchemesDetails")
	public @ResponseBody List<BasicVO> getSchemesDetails(@RequestBody InputVO inputVO) {
		try {
			return rWSNICService.getSchemesDetails(inputVO);
		} catch (Exception e) {
			LOG.error("Exception raised at getSchemesDetails - RuralWaterSupplyDashBoardController controller", e);
		}
		return null;
	}

	@PostMapping("/getSchemeWiseWorkDetails")
	public @ResponseBody List<BasicVO> getSchemeWiseWorkDetails(@RequestBody InputVO inputVO) {
		try {
			return rWSNICService.getSchemeWiseWorkDetails(inputVO);

		} catch (Exception e) {
			LOG.error("Exception raised at getSchemeWiseWorkDetails - RuralWaterSupplyDashBoardController controller",
					e);
		}
		return null;
	}
	/*
	 * Get Assets Info date between dates controller
	 * 
	 */

	@PostMapping("/getAssetInfoBetweenDates")
	public @ResponseBody List<BasicVO> getAssetInfoBetweenDates(@RequestBody InputVO vo) {
		List<BasicVO> assetInfo = null;
		try {
			assetInfo = rWSNICService.getAssetsInfo(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getAssetsInfoBetweenDates - RuralWaterSupplyDashBoardController controller",
					e);
		}
		return assetInfo;
	}

	@PostMapping("/getAlertDetailsOfCategoryByStatusWise")
	public @ResponseBody List<StatusVO> getAlertDetailsOfCategoryByStatusWise(@RequestBody InputVO vo) {
		List<StatusVO> alertInfo = null;
		try {
			alertInfo = rWSNICService.getAlertDetailsOfCategoryByStatusWise(vo);
		} catch (Exception e) {
			LOG.error(
					"Exception raised at getAlertDetailsOfCategoryByStatusWise - RuralWaterSupplyDashBoardController controller",
					e);
		}
		return alertInfo;
	}

	@PostMapping("/getAlertFeedbackStatusDetails")
	public @ResponseBody List<StatusVO> getAlertFeedbackStatusDetails(@RequestBody InputVO vo) {
		List<StatusVO> alertInfo = null;
		try {
			alertInfo = rWSNICService.getAlertFeedbackStatusDetails(vo);
		} catch (Exception e) {
			LOG.error(
					"Exception raised at getAlertFeedbackStatusDetails - RuralWaterSupplyDashBoardController controller",
					e);
		}
		return alertInfo;
	}
	
	/*
	 * Get water Souce Info controller
	 * 
	 */

	@PostMapping("/getWaterSourceInfo")
	public @ResponseBody WaterSourceVO getWaterSourceInfo(@RequestBody InputVO vo) {
		try {
			return rWSNICService.getWaterSourceInfo(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getWaterSourceInfo - RuralWaterSupplyDashBoardController controller",e);
		}
		return null;
	}
	
	@PostMapping("/getKeyPerformanceIndicatorsInfo")
	public @ResponseBody List<KPIVO> getKeyPerformanceIndicatorsInfo(@RequestBody InputVO vo) {
		List<KPIVO> kpiVoList = null;
		try {
			kpiVoList = rWSNICService.getKeyPerformanceIndicatorsInfo(vo);

		} catch (Exception e) {
			LOG.error(
					"Exception raised at getKeyPerformanceIndicatorsInfo - RuralWaterSupplyDashBoardController controller",
					e);
		}
		return kpiVoList;
	}
	
	@PostMapping("/getStressedHabitationsInfoByLocationType")
	public @ResponseBody StatusVO getStressedHabitationsInfoByLocationType(@RequestBody InputVO vo) {
		try {
			return  rWSNICService.getStressedHabitationsInfoByLocationType(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at stressedHabitationsInfo - RuralWaterSupplyDashBoardController controller",
					e);
		}
		return null;
	}
	
	
	@PostMapping("/getPlanofActionForStressedHabitations")
	public @ResponseBody List<StatusVO> getPlanofActionForStressedHabitations(@RequestBody InputVO vo) {
		List<StatusVO> statusVOList = null;
		try {
			statusVOList = rWSNICService.getPlanofActionForStressedHabitations(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getPlanofActionForStressedHabitations - RuralWaterSupplyDashBoardController controller",e);
		}
		return statusVOList;
	}
	
	@PostMapping("/getLocationWiseAlertStatusCounts")
	public @ResponseBody List<LocationVO> getLocationWiseAlertStatusCounts(@RequestBody InputVO vo) {
		List<LocationVO> locationVOList = null;
		try {
			locationVOList = rWSNICService.getLocationWiseAlertStatusCounts(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getPlanofActionForStressedHabitations - RuralWaterSupplyDashBoardController controller",e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getLocationBasedOnSelection")
	public @ResponseBody List<RangeVO> getLocationBasedOnSelection(@RequestBody InputVO vo) {
		List<RangeVO> rangeVOList = null;
		try {
			rangeVOList = rWSNICService.getLocationBasedOnSelection(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getLocationBasedOnSelection - RuralWaterSupplyDashBoardController controller",e);
		}
		return rangeVOList;
	}
	
	@PostMapping("/getHamletWiseIvrStatusCounts")
	public @ResponseBody List<StatusVO> getHamletWiseIvrStatusCounts(@RequestBody InputVO vo) {
		try {
			return rWSNICService.getHamletWiseIvrCounts(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getHamletWiseIvrStatusCounts - RuralWaterSupplyDashBoardController controller",e);
		}
		return null;
	}
	@PostMapping("/getAlertsOfCategoryByStatusWise")
	public @ResponseBody List<AmsVO> getAlertsOfCategoryByStatusWise(@RequestBody InputVO vo){
		try {
			return rWSNICService.getAlertsOfCategoryByStatusWise(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getAlertsOfCategoryByStatusWise - RuralWaterSupplyDashBoardController controller",e);
		}
		return null;
	}
	
	@PostMapping("/getOnclickWorkDetails")
	public @ResponseBody List<RwsClickVO> getOnclickWorkDetails(@RequestBody InputVO vo){
		try {
			return rWSNICService.getOnclickWorkDetails(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getOnclickWorkDetails - RuralWaterSupplyDashBoardController controller",e);
		}
		return null;
	}
	
	@PostMapping("/getOnclickTargetsAcheievementsDetails")
	public @ResponseBody List<RwsClickVO> getOnclickTargetsAcheievementsDetails(@RequestBody InputVO vo){
		try {
			return rWSNICService.getOnclickTargetsAcheievementsDetails(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getOnclickTargetsAcheievementsDetails - RuralWaterSupplyDashBoardController controller",e);
		}
		return null;
	}
	//
	@PostMapping("/getOnclickStressedTargetsAcheievementsDetails")
	public @ResponseBody List<RwsClickVO> getOnclickStressedTargetsAcheievementsDetails(@RequestBody InputVO vo){
		try {
			return rWSNICService.getOnclickStressedTargetsAcheievementsDetails(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getOnclickStressedTargetsAcheievementsDetails - RuralWaterSupplyDashBoardController controller",e);
		}
		return null;
	}
	
	@PostMapping("/getOnclickHabitationsupplyDetails")
	public @ResponseBody List<RwsClickVO> getOnclickHabitationsupplyDetails(@RequestBody InputVO vo){
		try {
			return rWSNICService.getOnclickHabitationsupplyDetails(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getOnclickHabitationsupplyDetails - RuralWaterSupplyDashBoardController controller",e);
		}
		return null;
	}
	
	@PostMapping("/getSchemeDetailsByTypeOfAssestName")
	public @ResponseBody List<RwsClickVO> getSchemeDetailsByTypeOfAssestName(@RequestBody InputVO vo){
		try {
			return rWSNICService.getSchemeDetailsByTypeOfAssestName(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getSchemeDetailsByTypeOfAssestName - RuralWaterSupplyDashBoardController controller",e);
		}
		return null;
	}
	
	@PostMapping("/getAssetDetailsByAssetType")
	public @ResponseBody List<RwsClickVO> getAssetDetailsByAssetType(@RequestBody InputVO vo){
		try {
			return rWSNICService.getAssetDetailsByAssetType(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getAssetDetailsByAssetType - RuralWaterSupplyDashBoardController controller",e);
		}
		return null;
	}
	
	@PostMapping("/getHabitationDetailsByStatusByLocationType")
	public @ResponseBody List<RwsClickVO> getHabitationDetailsByStatusByLocationType(@RequestBody InputVO vo){
		try {
			return rWSNICService.getHabitationDetailsByStatusByLocationType(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getHabitationDetailsByStatusByLocationType - RuralWaterSupplyDashBoardController controller",e);
		}
		return null;
	}
	
	@PostMapping("/getWaterSourceDeatilsLocationWise")
	public @ResponseBody List<RwsClickVO> getWaterSourceDeatilsLocationWise(@RequestBody InputVO vo){
		try {
			return rWSNICService.getWaterSourceDeatilsLocationWise(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getWaterSourceDeatilsLocationWise - RuralWaterSupplyDashBoardController controller",e);
		}
		return null;
	}
	
	@PostMapping("/getAllPrrwsDistricts")
	public @ResponseBody List<KeyValueVO> getAllPrrwsDistricts(@RequestBody IdNameVO vo){
		try {
			return rWSNICService.getAllPrrwsDistricts();

		} catch (Exception e) {
			LOG.error("Exception raised at getAllPrrwsDistricts - RuralWaterSupplyDashBoardController controller",e);
		}
		return null;
	}
	
	@PostMapping("/getConstituenciesForDistrict")
	public @ResponseBody List<KeyValueVO> getConstituenciesForDistrict(@RequestBody IdNameVO vo){
		try {
			return rWSNICService.getConstituenciesForDistrict(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getConstituenciesForDistrict - RuralWaterSupplyDashBoardController controller",e);
		}
		return null;
	}
	
	@PostMapping("/getTehsilsForConstituency")
	public @ResponseBody List<KeyValueVO> getTehsilsForConstituency(@RequestBody IdNameVO vo){
		try {
			return rWSNICService.getTehsilsForConstituency(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getTehsilsForConstituency - RuralWaterSupplyDashBoardController controller",e);
		}
		return null;
	}
	
	@PostMapping("/getWaterSourceDeatils2")
	public @ResponseBody List<WaterSourceVO> getWaterSourceDeatils2(@RequestBody InputVO vo){
		try {
			return rWSNICService.getWaterSourceDeatils2(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getTehsilsForConstituency - RuralWaterSupplyDashBoardController controller",e);
		}
		return null;
	}
	
	@PostMapping("/getLocationHamletIvrStatusList")
	public @ResponseBody List<RwsClickVO> getHamletWiseIvrStatusList(@RequestBody InputVO vo) {
		try {
			return rWSNICService.getHamletWiseIvrStatusList(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseHamletLis - RuralWaterSupplyDashBoardController controller",e);
		}
		return null;
	}
	
	@PostMapping("/getIHHLlocationLvlWiseData")
	public @ResponseBody List<NregaLocationOverviewVO> getIHHLlocationLvlWiseData(@RequestBody InputVO inputVO) {
		try {
			return rWSNICService.getIHHLlocationLvlWiseData(inputVO);
		} catch (Exception e) {
			LOG.error("Exception raised at getIHHLlocationLvlWiseData - RuralWaterSupplyDashBoardController controller", e);
		}
		return null;
	}
	
	@PostMapping("/getIHHLOverviewData")
	public @ResponseBody NregaLocationOverviewVO getIHHLOverviewData(@RequestBody InputVO inputVO) {
		try {
			return rWSNICService.getIHHLOverviewData(inputVO);
		} catch (Exception e) {
			LOG.error("Exception raised at getIHHLOverviewData - RuralWaterSupplyDashBoardController controller", e);
		}
		return null;
	}
	
	@PostMapping("/getSBPaymentsAbstract")
	public @ResponseBody NregaLocationOverviewVO getSBPaymentsAbstract(@RequestBody InputVO inputVO) {
		try {
			return rWSNICService.getSBPaymentsAbstract(inputVO);
		} catch (Exception e) {
			LOG.error("Exception raised at getSBPaymentsAbstract - RuralWaterSupplyDashBoardController controller", e);
		}
		return null;
	}
	
	@PostMapping("/getSBPaymentsLevelsWiseData")
	public @ResponseBody List<NregaLocationOverviewVO> getSBPaymentsLevelsWiseData(@RequestBody InputVO inputVO) {
		try {
			return rWSNICService.getSBPaymentsLevelsWiseData(inputVO);
		} catch (Exception e) {
			LOG.error("Exception raised at getSBPaymentsLevelsWiseData - RuralWaterSupplyDashBoardController controller", e);
		}
		return null;
	}
	
	@GetMapping("/habitationCoverageStatus")
	public String habitationCoverageStatusDashBoardPage(ModelMap model,HttpSession session){
		return "habitationCoverageStatus";
	}
	@GetMapping("/getAllData")
	public void getAllData(){
		rWSNICService.getAllData();
	}
	@GetMapping("/keyPerfomanceDashBoard")
	public String keyPerfomanceDashBoard(ModelMap model){
		return "keyPerfomanceDashBoard";
	}
	@GetMapping("/getStressedHabitationDetailsByStatusByLocationType")
	public void getStressedHabitationDetailsByStatusByLocationType(){
		rWSNICService.getStressedHabitationDetailsByStatusByLocationType();
	}
	@GetMapping("/updateAllHabitationData")
	public void updateAllHabitationData(){
		rWSNICService.updateAllHabitationData();
	}
	@GetMapping("/updateAllTressedHabitationData")
	public void updateAllTressedHabitationData(){
		rWSNICService.updateAllTressedHabitationData();
	}
	
	@PostMapping("/getExceededTargetWorksDetails")
	public @ResponseBody IdNameVO getExceededTargetWorksDetails() {
		try {
			return rWSNICService.getExceededTargetWorksDetails();
		} catch (Exception e) {
			LOG.error("Exception raised at getExceededTargetWorksDetails - RuralWaterSupplyDashBoardController controller", e);
		}
		return null;
	}
	@PostMapping("/getExceedWorkDetailsLocationWise")
	public @ResponseBody List<IdNameVO> getExceededWorkDetailsLocationWise(@RequestBody InputVO inputVO) {
	  try {
			 return rWSNICService.getExceededWorkDetailsLocationWise(inputVO);
	  } catch (Exception e) {
			LOG.error("Exception raised at getExceededWorkDetailsLocationWise - getExceededWorkDetailsLocationWise controller", e);
	  }
	 return null;
	}
	
	@PostMapping("/getWorksDataInsertionService")
	public @ResponseBody List<IdNameVO> getWorksDataInsertionService(@RequestBody InputVO inputVO) {
	  try {
			 return rWSNICService2.getWorksDataInsertion(inputVO);
	  } catch (Exception e) {
			LOG.error("Exception raised at getExceededWorkDetailsLocationWise - getExceededWorkDetailsLocationWise controller", e);
	  }
	 return null;
	}
	
	@PostMapping("/getExceedWorkDetailsLocationWise2")
	public @ResponseBody List<IdNameVO> getExceededWorkDetailsLocationWise2(@RequestBody InputVO inputVO) {
	  try {
			 return rWSNICService.getExceededWorkDetailsLocationWise2(inputVO);
	  } catch (Exception e) {
			LOG.error("Exception raised at getExceededWorkDetailsLocationWise - getExceededWorkDetailsLocationWise controller", e);
	  }
	 return null;
	}
	
	@PostMapping("/getOnClickExceedWorkDetails")
	public @ResponseBody List<IdNameVO> getOnClickExceedWorkDetails(@RequestBody InputVO inputVO) {
	  try {
			 return rWSNICService.getOnClickExceedWorkDetails(inputVO);
	  } catch (Exception e) {
			LOG.error("Exception raised at getExceededWorkDetailsLocationWise - getExceededWorkDetailsLocationWise controller", e);
	  }
	 return null;
	}
	
	@PostMapping("/getSchemeWiseWorkDetails2")
	public @ResponseBody List<WorksVO> getSchemeWiseWorkDetails2(@RequestBody InputVO inputVO) {
		try {
			return rWSNICService.getSchemeWiseWorkDetails2(inputVO);

		} catch (Exception e) {
			LOG.error("Exception raised at getSchemeWiseWorkDetails - RuralWaterSupplyDashBoardController controller",
					e);
		}
		return null;
	}
}
