package com.itgrids.controllers.web;

import java.util.List;

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

import com.itgrids.dto.DrainsVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.StatusVO;
import com.itgrids.service.IDrainsService;
import com.itgrids.service.IUserService;
import com.itgrids.service.integration.external.WebServiceUtilService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class DrainsController {
	
	private static final Logger LOG = Logger.getLogger(DrainsController.class);
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	@Autowired
	private IUserService userServiceImpl;
	
	@Autowired
	private IDrainsService drainsService;
	
	@GetMapping(value ="/drainDashBoard")
    public String drainsDashboard(ModelMap model) {
      
		return "drainDashBoard";
    }
	
	@PostMapping("/getDrainsInfoStateWise")
	public @ResponseBody DrainsVO getDrainsInfoStateWise(@RequestBody InputVO inputVO)
	{
		
		DrainsVO drainsVO=null;
		try {
			drainsVO = drainsService.getDrainsInfoStateWise(inputVO);
			
		} catch (Exception e) {
			LOG.error("Exception raised at  getDrainsInfobyLocation - DrainsController controller", e);
		}
		return drainsVO;
	}
	@PostMapping("/getDrainsInfoLocationWise")
	public @ResponseBody List<DrainsVO> getDrainsInfoLocationWise(@RequestBody InputVO inputVO)
	{
		
		List<DrainsVO> drainsVO=null;
		try {
			drainsVO = drainsService.getDrainsInfoLocationWise(inputVO);
			
		} catch (Exception e) {
			LOG.error("Exception raised at  getDrainsInfobyLocations - DrainsController controller", e);
		}
		return drainsVO;
	}
	@PostMapping("/getDrainsIvrStatusCounts")
	public @ResponseBody List<StatusVO> getDrainsIvrStatusCounts(@RequestBody InputVO inputVO)
	{		
		try {
			return drainsService.getDrainsIvrStatusCounts(inputVO);			
		} catch (Exception e) {
			LOG.error("Exception raised at  getDrainsIvrStatusCounts - DrainsController controller", e);
		}
		return null;
	}
	@PostMapping("/getOverAllIvrDetails")
	public @ResponseBody List<StatusVO> getOverAllIvrDetails(@RequestBody InputVO inputVO){
		try {
			return drainsService.getOverAllIvrDetails(inputVO);			
		} catch (Exception e) {
			LOG.error("Exception raised at  getOverAllIvrDetails - DrainsController controller", e);
		}
		return null;
	} 
	@PostMapping("/getIvrSurveyDates")
	public @ResponseBody List<KeyValueVO> getIvrSurveyDates(@RequestBody InputVO inputVO){
		try {
			return drainsService.getIvrSurveyDates(inputVO);			
		} catch (Exception e) {
			LOG.error("Exception raised at  getIvrSurveyDates - DrainsController controller", e);
		}
		return null;
	}
	@PostMapping("/getIvrSurveyQuestions")
	public @ResponseBody List<KeyValueVO> getIvrSurveyQuestions(@RequestBody InputVO inputVO){
		try {
			return drainsService.getIvrSurveyQuestions(inputVO);			
		} catch (Exception e) {
			LOG.error("Exception raised at  getIvrSurveyQuestions - DrainsController controller", e);
		}
		return null;
	}

}
