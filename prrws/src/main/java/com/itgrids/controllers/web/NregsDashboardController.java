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

import com.itgrids.dto.FarmPondOverviewVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsOverviewVO;
import com.itgrids.dto.LabourBudgetOverViewVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsProjectsVO;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.service.integration.impl.INREGSTCSService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class NregsDashboardController {
	
	private static final Logger LOG = Logger.getLogger(NregsDashboardController.class);
	
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	
	@Autowired
	private INREGSTCSService nregsTcsService;
	
	
	/*@RequestMapping(value ="/test", method = RequestMethod.GET)
    public String fundManagementDashboardPage(ModelMap model) {
		webServiceUtilService.testMethod();
		return "fundManagementDashboard";
    }*/

	@RequestMapping(value ="/MGNREGSDashboard", method = RequestMethod.GET)
    public String mgnregsDashBoardPage(ModelMap model) {
      
		return "MGNREGS";
    }
	
	@PostMapping("/getNREGSProjectsOverview")
	public @ResponseBody List<NregsProjectsVO> getNREGSProjectsOverview(@RequestBody InputVO vo){
		List<NregsProjectsVO> locationVOList = null;
		try {
			locationVOList = nregsTcsService.getNREGSProjectsOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSProjectsOverview - NREGSController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getLabourBudgetOverview")
	public @ResponseBody LabourBudgetOverViewVO getLabourBudgetOverview(@RequestBody InputVO vo){
		LabourBudgetOverViewVO labourBudgetOverViewVO = null;
		try {
			labourBudgetOverViewVO = nregsTcsService.getLabourBudgetOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getLabourBudgetOverview - NREGSController controller", e);
		}
		return labourBudgetOverViewVO;
	}
	
	@PostMapping("/getLabourBudgetExpenditure")
	public @ResponseBody List<IdNameVO> getLabourBudgetExpenditure(@RequestBody InputVO vo){
		List<IdNameVO> locationVOList = null;
		try {
			locationVOList = nregsTcsService.getLabourBudgetExpenditure(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getLabourBudgetExpenditure - NREGSController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getFarmPondOverview")
	public @ResponseBody FarmPondOverviewVO getFarmPondOverview(@RequestBody InputVO vo){
		FarmPondOverviewVO formobject = null;
		try {
			formobject = nregsTcsService.getFarmPondOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSProjectsOverview - NREGSController controller", e);
		}
		return formobject;
	}
	@PostMapping("/getFarmPondData")
	public @ResponseBody List<NregsDataVO>  getFarmPondData(@RequestBody InputVO vo){
		List<NregsDataVO> formVOList = null;
		try {
			formVOList = nregsTcsService.getFarmPondData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getFarmPondData - NREGSController controller", e);
		}
		return formVOList;
	}
	@PostMapping("/getNregaIHHLOverview")
	public @ResponseBody NregsOverviewVO getNregaIHHLOverview(@RequestBody InputVO vo){
		NregsOverviewVO IhhlVO = null;
		try {
			IhhlVO = nregsTcsService.getNregaIHHLOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSProjectsOverview - getIHHLOverview controller", e);
		}
		return IhhlVO;
	}
	
	@PostMapping("/getNregaLevelsOverviewForIHHL")
	public @ResponseBody List<NregsDataVO> getNregaLevelsOverviewForIHHL(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsOverviewForIHHL(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSProjectsOverview - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNregaLevelwiseOverviewForLabourBudgetData")
	public @ResponseBody List<NregsDataVO> getNregaLevelwiseOverviewForLabourBudgetData(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelwiseOverviewForLabourBudgetData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSProjectsOverview - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	@PostMapping("/getNregsVermiOverview")
	public @ResponseBody NregsOverviewVO getNregsVermiOverview(@RequestBody InputVO vo){
		NregsOverviewVO finalVo = null;
		try {
			finalVo = nregsTcsService.getNregsVermiOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsVermiOverview - NREGSController controller", e);
		}
		return finalVo;
	}
	@PostMapping("/getNregsVermiData")
	public @ResponseBody List<NregsDataVO> getNregsVermiData(@RequestBody InputVO vo){
		List<NregsDataVO> vermiDataList = null;
		try {
			vermiDataList = nregsTcsService.getNregsVermiData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsVermiData - NREGSController controller", e);
		}
		return vermiDataList;
	}
}
