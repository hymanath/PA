package com.itgrids.controllers.web;

import java.util.List;

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

import com.itgrids.dto.BasicVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.service.IRuralWaterSupplyDashBoardService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class RuralWaterSupplyDashBoardController {
	private static final Logger LOG = Logger.getLogger(RuralWaterSupplyDashBoardController.class);
	@Autowired
	private IRuralWaterSupplyDashBoardService ruralWaterSupplyDashBoardService;
	
	@RequestMapping(value ="/ruralWaterSupplyDashBoard", method = RequestMethod.GET)
    public String ruralWaterSupplyDashBoardPage(ModelMap model) {
      
		return "ruralWaterSupplyDashBoard";
    }
	
	@PostMapping("/getHabitationCoverageByStatusByLocationType")
	public @ResponseBody List<LocationVO> getHabitationCoverageByStatusByLocationType(InputVO vo){
		List<LocationVO> locationVOList = null;
		try {
			locationVOList = ruralWaterSupplyDashBoardService.getHabitationCoverageByStatusByLocationType(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getHabitationCoverageByStatusByLocationType - RuralWaterSupplyDashBoardController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getLabTestDetails")
	public @ResponseBody BasicVO getLabTestDetails(InputVO vo){
		BasicVO basicVO = null;
		try {
			basicVO = ruralWaterSupplyDashBoardService.getLabTestDetails(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getLabTestDetails - RuralWaterSupplyDashBoardController controller", e);
		}
		return basicVO;
	}
	
	@PostMapping("/getHabitationSupplyDetails")
	public @ResponseBody BasicVO getHabitationSupplyDetails(@RequestBody InputVO inputVO){
		try {
			return ruralWaterSupplyDashBoardService.getHabitationSupplyDetails(inputVO);
		} catch (Exception e) {
			LOG.error("Exception raised at getHabitationSupplyDetails - RuralWaterSupplyDashBoardController controller", e);
		}
		return null;
	}
	@PostMapping("/getSchemesDetails")
	public @ResponseBody List<BasicVO> getSchemesDetails(@RequestBody InputVO inputVO){
		try {
			return ruralWaterSupplyDashBoardService.getSchemesDetails(inputVO);
		} catch (Exception e) {
			LOG.error("Exception raised at getSchemesDetails - RuralWaterSupplyDashBoardController controller", e);
		}
		return null;
	}
	@PostMapping("/getSchemeWiseWorkDetails")
	public @ResponseBody List<BasicVO> getSchemeWiseWorkDetails(@RequestBody InputVO inputVO){
		try {
			return ruralWaterSupplyDashBoardService.getSchemeWiseWorkDetails(inputVO);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getSchemeWiseWorkDetails - RuralWaterSupplyDashBoardController controller", e);
		}
		return null;
	}
}
