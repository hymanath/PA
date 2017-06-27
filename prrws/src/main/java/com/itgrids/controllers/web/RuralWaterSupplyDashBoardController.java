package com.itgrids.controllers.web;

import java.util.List;

import org.apache.log4j.Logger;
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

import com.itgrids.dto.BasicVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KPIVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.RangeVO;
import com.itgrids.dto.StatusVO;
import com.itgrids.tpi.rws.service.IRWSNICService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class RuralWaterSupplyDashBoardController {
	private static final Logger LOG = Logger.getLogger(RuralWaterSupplyDashBoardController.class);
	@Autowired
	private IRWSNICService rWSNICService;

	@GetMapping("/ruralWaterSupplyDashBoard")
	public String ruralWaterSupplyDashBoardPage(ModelMap model) {

		return "ruralWaterSupplyDashBoard";
	}

	@PostMapping("/getHabitationCoverageByStatusByLocationType")
	public @ResponseBody List<LocationVO> getHabitationCoverageByStatusByLocationType(@RequestBody InputVO vo) {
		List<LocationVO> locationVOList = null;
		try {
			locationVOList = rWSNICService.getHabitationCoverageByStatusByLocationType(vo);

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

	@RequestMapping(value = "/getWaterSourceInfo", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody StatusVO getWaterSourceInfo() {
		try {
			return rWSNICService.getWaterSourceInfo();

		} catch (Exception e) {
			LOG.error("Exception raised at getWaterSourceInfo - RuralWaterSupplyDashBoardController controller",
					e);
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
			LOG.error(
					"Exception raised at stressedHabitationsInfo - RuralWaterSupplyDashBoardController controller",
					e);
		}
		return null;
	}
	
	
	@PostMapping("/getPlanofActionForStressedHabitations")
	public @ResponseBody StatusVO getPlanofActionForStressedHabitations(@RequestBody InputVO vo) {
		StatusVO statusVO = null;
		try {
			statusVO = rWSNICService.getPlanofActionForStressedHabitations(vo);

		} catch (Exception e) {
			LOG.error("Exception raised at getPlanofActionForStressedHabitations - RuralWaterSupplyDashBoardController controller",e);
		}
		return statusVO;
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
}
