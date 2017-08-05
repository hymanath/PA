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

import com.itgrids.dto.DiseasesVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.service.IHealthMedicalAndFamilyWelfareService;

@Controller
@EnableAutoConfiguration
@RequestMapping("/")
public class HealthMedicalAndFamilyWelfareController {
	private static final Logger LOG = Logger.getLogger(DrainsController.class);
	@Autowired
	private IHealthMedicalAndFamilyWelfareService healthMedicalAndFamilyWelfareService;
	
	@RequestMapping(value ="/getdailySpikeReport", method = RequestMethod.GET)
    public String indexPage(ModelMap model) {
		return "dailySpikeReport";
    }
	@PostMapping("/getCaseCountDiseasesWise")
	public @ResponseBody List<DiseasesVO> getCaseCaDiseasesCount(@RequestBody InputVO inputVO){
		List<DiseasesVO> diseasesList = healthMedicalAndFamilyWelfareService.getCaseCountDiseasesWise(inputVO.getFromDate(),inputVO.getToDate(),inputVO.getDiseasesIdList(),inputVO.getDeptIdsList());
		return diseasesList;
	}
	@PostMapping("/getCaseCountLocationWise")
	public @ResponseBody List<DiseasesVO> getCaseCountLocationWise(@RequestBody InputVO inputVO){
		List<DiseasesVO> diseasesList = healthMedicalAndFamilyWelfareService.getCaseCountLocationWise(inputVO.getFromDate(),inputVO.getToDate(),inputVO.getDiseasesIdList(),inputVO.getDeptIdsList(),inputVO.getScopeId(),inputVO.getLocationId());
		return diseasesList;
	}
	@PostMapping("/getCaseCountDateWise")
	public @ResponseBody List<DiseasesVO> getCaseCountDateWise(@RequestBody InputVO inputVO){
		List<DiseasesVO> diseasesList = healthMedicalAndFamilyWelfareService.getCaseCountDateWise(inputVO.getFromDate(),inputVO.getToDate(),inputVO.getDiseasesIdList(),inputVO.getDeptIdsList(),inputVO.getRangeType());
		return diseasesList;
	}
	@PostMapping("/getLocationDtlsRankWise")
	public @ResponseBody List<DiseasesVO> getLocationDtlsRankWise(@RequestBody InputVO inputVO){
		List<DiseasesVO> diseasesList = healthMedicalAndFamilyWelfareService.getLocationDtlsRankWise(inputVO.getFromDate(),inputVO.getToDate(),inputVO.getDiseasesIdList(),inputVO.getDeptIdsList());
		return diseasesList;
	}
	@PostMapping("/getSubLocationsBySuperLocationId")
	public @ResponseBody List<LocationFundDetailsVO> getAllSubLocationsBySuperLocationId(@RequestBody InputVO inputVO){
		List<LocationFundDetailsVO> locationList = healthMedicalAndFamilyWelfareService.getAllSubLocationsBySuperLocationId(inputVO.getFromDate(),inputVO.getToDate(),inputVO.getDiseasesIdList(),inputVO.getDeptIdsList(),inputVO.getSuperLocationId());
		return locationList;
	}
}