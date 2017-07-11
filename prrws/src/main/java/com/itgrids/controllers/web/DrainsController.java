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

import com.itgrids.dto.DrainsVO;
import com.itgrids.dto.InputVO;
import com.itgrids.service.IDrainsService;
import com.itgrids.service.integration.external.WebServiceUtilService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class DrainsController {
	
	private static final Logger LOG = Logger.getLogger(DrainsController.class);
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	
	@Autowired
	private IDrainsService drainsService;
	
	@RequestMapping(value ="/DrainsDashboard", method = RequestMethod.GET)
    public String drainsDashboard(ModelMap model) {
      
		return "Drains";
    }
	@PostMapping("/getDrainsInfobyLocation")
	public @ResponseBody List<DrainsVO> getDrainsInfoByLocation(@RequestBody InputVO inputVO)
	{
		
		List<DrainsVO> drainsVO=null;
		try {
			drainsVO = drainsService.getDrainsInfobyLocation(inputVO);
			
		} catch (Exception e) {
			LOG.error("Exception raised at  getDrainsInfobyLocation - DrainsController controller", e);
		}
		return drainsVO;
	}
	

}
