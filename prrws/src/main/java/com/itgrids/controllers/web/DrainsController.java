package com.itgrids.controllers.web;

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
	
	@GetMapping(value ="/drainsDashboard")
    public String drainsDashboard(ModelMap model) {
      
		return "drainDashBoard";
    }
	@PostMapping("/getDrainsInfobyLocation")
	public @ResponseBody DrainsVO getDrainsInfoByLocation(@RequestBody InputVO inputVO)
	{
		
		DrainsVO drainsVO=null;
		try {
			drainsVO = drainsService.getDrainsInfobyLocation(inputVO);
			
		} catch (Exception e) {
			LOG.error("Exception raised at  getDrainsInfobyLocation - DrainsController controller", e);
		}
		return drainsVO;
	}
	

}
