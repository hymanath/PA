package com.itgrids.controllers.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.NTRSujalaMotherPlantVO;
import com.itgrids.dto.NTRSujalaRduVO;
import com.itgrids.service.INTRSujalaDashboardService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class NTRSujalaDashboardController {

	private static final Logger LOG = Logger.getLogger(NTRSujalaDashboardController.class);
	
	@Autowired
	private INTRSujalaDashboardService ntrSujalaDashboardService;
	
	
	
   @RequestMapping(value = "/getLast30DaysMotherPlantDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  NTRSujalaMotherPlantVO  getLast30DaysMotherPlantDetails(@RequestBody InputVO inputVO) {
	   NTRSujalaMotherPlantVO  resultVO = ntrSujalaDashboardService.getLast30DaysMotherPlantDetails(inputVO);
	   return resultVO;
   }
	
   @RequestMapping(value = "/getLast30DaysRDUDetails", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  NTRSujalaRduVO  getLast30DaysRDUDetails(@RequestBody InputVO inputVO) {
	   NTRSujalaRduVO  resultVO = ntrSujalaDashboardService.getLast30DaysRDUDetails(inputVO);
	   return resultVO;
   }
}
