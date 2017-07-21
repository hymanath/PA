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

import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.NregaConsolidatedInputVO;
import com.itgrids.dto.NregsProjectsVO;
import com.itgrids.service.integration.impl.INREGSConsolidatedService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class NregsConsolidatedController {

	private static final Logger LOG = Logger.getLogger(NregsConsolidatedController.class);
	
	@Autowired
	private INREGSConsolidatedService nregsConsolidatedService;
	
	@RequestMapping(value ="/NregaConsolidatedDashboard", method = RequestMethod.GET)
    public String getNregaConsolidatedDashboard(ModelMap model) {
		return "NregaConsolidated";
    }
	
	@PostMapping("/getAllConvergenceTypes")
	public @ResponseBody List<IdNameVO> getAllConvergenceTypes(){
		List<IdNameVO> list = null;
		try {
			list = nregsConsolidatedService.getAllConvergenceTypes();
			
		} catch (Exception e) {
			LOG.error("Exception raised at getAllConvergenceTypes - NregsConsolidatedController", e);
		}
		return list;
	}
	
	@PostMapping("/getComponentByConvergType")
	public @ResponseBody List<IdNameVO> getComponentByConvergType(@RequestBody NregaConsolidatedInputVO nregaConsolidatedInputVO){
		List<IdNameVO> list = null;
		try{
			list = nregsConsolidatedService.getComponentByConvergType(nregaConsolidatedInputVO);
			
		}catch(Exception e){
			LOG.error("Exception raised at getComponentByConvergType - NregsConsolidatedController",e);
		}
		return list;
	}
	
	@PostMapping("/getNREGSConsolidatedAbstrct")
	public @ResponseBody List<NregsProjectsVO> getNREGSConsolidatedAbstrct(@RequestBody NregaConsolidatedInputVO nregaConsolidatedInputVO){
		List<NregsProjectsVO> finalList = null;
		try{
			finalList = nregsConsolidatedService.getNREGSConsolidatedAbstrct(nregaConsolidatedInputVO);
			
		}catch(Exception e){
			LOG.error("Exception raised at getNREGSConsolidatedAbstrct - NregsConsolidatedController",e);
		}
		return finalList;
	}
	
}
