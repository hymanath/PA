package com.itgrids.controllers.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.PrExpenditureVO;
import com.itgrids.service.IPRExpenditureService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class PRExpenditureController {
	private static final Logger LOG = Logger.getLogger(PRExpenditureController.class);
	@Autowired
	private IPRExpenditureService prExpenditureService;
	
	@RequestMapping(value ="/prExpenditureDashboard", method = RequestMethod.GET)
    public String getPRExpenditureDashboard(ModelMap model,HttpSession session) {
		return "prExpenditureDashboard";
    }
	@PostMapping("/getTotalAmountForOverview")
	public @ResponseBody PrExpenditureVO getTotalAmountForOverview(@RequestBody InputVO inputVO){
		PrExpenditureVO prExpenditureVO = prExpenditureService.getTotalAmountForOverview(inputVO);
		return prExpenditureVO;
	}
	@RequestMapping(value ="/getLocationWisePrExpenditureDtls",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PrExpenditureVO> getLocationWisePrExpenditureDls(@RequestBody InputVO inputVO) {
		List<PrExpenditureVO> resultList = null;
		try {
			resultList = prExpenditureService.getLocationWisePrExpenditureDtls(inputVO);
		} catch (Exception e) {
			LOG.error("Exception occured at getLocationWisePrExpenditureDls() in PRExpenditureController class",e);
		}
		return resultList;
	}
}
