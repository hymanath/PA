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
import com.itgrids.dto.LabourBudgetOverViewVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsOverviewVO;
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
			LOG.error("Exception raised at getNREGSProjectsOverview - NREGSController controller", e);
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
	@PostMapping("/getNregsNtrsOverview")
	public @ResponseBody NregsOverviewVO getNregsNtrsOverview(@RequestBody InputVO vo){
		NregsOverviewVO finalVo = null;
		try {
			finalVo = nregsTcsService.getNregsNtrsOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsNtrsOverview - NREGSController controller", e);
		}
		return finalVo;
	}
	@PostMapping("/getNregsNtrsData")
	public @ResponseBody List<NregsDataVO> getNregsNtrsData(@RequestBody InputVO vo){
		List<NregsDataVO> ntrsDataList = null;
		try {
			ntrsDataList = nregsTcsService.getNregsNtrsData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsNtrsData - NREGSController controller", e);
		}
		return ntrsDataList;
	}
	@PostMapping("/getNregsAnganwadiOverview")
	public @ResponseBody NregsOverviewVO getNregsAnganwadiOverview(@RequestBody InputVO vo){
		NregsOverviewVO finalVo = null;
		try {
			finalVo = nregsTcsService.getNregsAnganwadiOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsAnganwadiOverview - NREGSController controller", e);
		}
		return finalVo;
	}
	@PostMapping("/getNregsAnganwadiData")
	public @ResponseBody List<NregsDataVO> getNregsAnganwadiData(@RequestBody InputVO vo){
		List<NregsDataVO> anganwadiDataList = null;
		try {
			anganwadiDataList = nregsTcsService.getNregsAnganwadiData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsAnganwadiData - NREGSController controller", e);
		}
		return anganwadiDataList;
	}

	@PostMapping("/getCCRoadsOverview")
	public @ResponseBody FarmPondOverviewVO getCCRoadsOverview(@RequestBody InputVO vo){
		FarmPondOverviewVO formobject = null;
		try {
			formobject = nregsTcsService.getCCRoadsOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getCCRoadsOverview - CCRoadsOverviewcontroller", e);
		}
		return formobject;
	}
	@PostMapping("/getCCRoadsData")
	public @ResponseBody List<NregsDataVO>   getCCRoadsData(@RequestBody InputVO vo){
		List<NregsDataVO> formVOList = null;
		try {
			formVOList = nregsTcsService. getCCRoadsData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getCCRoadsData - CCRoadsDataController controller", e);
		}
		return formVOList;
	}
	@PostMapping("/getNregsMandalBuildingOverview")
	public @ResponseBody NregsOverviewVO getNregsMandalBuildingOverview(@RequestBody InputVO vo){
		NregsOverviewVO finalVo = null;
		try {
			finalVo = nregsTcsService.getNregsMandalBuildingOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsMandalBuildingOverview - NREGSController controller", e);
		}
		return finalVo;
	}
	@PostMapping("/getNregsMandalBuildingData")
	public @ResponseBody List<NregsDataVO> getNregsMandalBuildingData(@RequestBody InputVO vo){
		List<NregsDataVO> mandalBuildingDataList = null;
		try {
			mandalBuildingDataList = nregsTcsService.getNregsMandalBuildingData(vo);
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsMandalBuildingData - NREGSController controller", e);
		}
		return mandalBuildingDataList;
	}
	@PostMapping("/getNregaGPBuilingsOverview")
	public @ResponseBody NregsOverviewVO getNregaGPBuilingsOverview(@RequestBody InputVO vo){
		NregsOverviewVO GpBuildVO = null;
		try {
			GpBuildVO = nregsTcsService.getNregaGPBuilingsOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaGPBuilingsOverview - NREGSController controller", e);
		}
		return GpBuildVO;
	}
	
	@PostMapping("/getNregaLevelsOverviewForGPBuilding")
	public @ResponseBody List<NregsDataVO> getNregaLevelsOverviewForGPBuilding(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsOverviewForGPBuilding(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSProjectsOverview - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNregaRaisingOfPerinnialFodderOverview")
	public @ResponseBody NregsOverviewVO getNregaRaisingOfPerinnialFodderOverview(@RequestBody InputVO vo){
		NregsOverviewVO GpBuildVO = null;
		try {
			GpBuildVO = nregsTcsService.getNregaRaisingOfPerinnialFodderOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaRaisingOfPerinnialFodderOverview - NREGSController controller", e);
		}
		return GpBuildVO;
	}
	
	@PostMapping("/getNregaProductionOfBricksOverview")
	public @ResponseBody NregsOverviewVO getNregaProductionOfBricksOverview(@RequestBody InputVO vo){
		NregsOverviewVO GpBuildVO = null;
		try {
			GpBuildVO = nregsTcsService.getNregaProductionOfBricksOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaProductionOfBricksOverview - NREGSController controller", e);
		}
		return GpBuildVO;
	}
	
	@PostMapping("/getNregaSilkWormOverview")
	public @ResponseBody NregsOverviewVO getNregaSilkWormOverview(@RequestBody InputVO vo){
		NregsOverviewVO GpBuildVO = null;
		try {
			GpBuildVO = nregsTcsService.getNregaSilkWormOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaSilkWormOverview - NREGSController controller", e);
		}
		return GpBuildVO;
	}
	
	@PostMapping("/getNregaLevelsOverviewForProductionOfBricks")
	public @ResponseBody List<NregsDataVO> getNregaLevelsOverviewForProductionOfBricks(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsOverviewForProductionOfBricks(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsOverviewForProductionOfBricks - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNregaLevelsOverviewForRaisingOfPerinnialFodder")
	public @ResponseBody List<NregsDataVO> getNregaLevelsOverviewForRaisingOfPerinnialFodder(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsOverviewForRaisingOfPerinnialFodder(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsOverviewForRaisingOfPerinnialFodder - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNregaLevelsOverviewForSilkWarm")
	public @ResponseBody List<NregsDataVO> getNregaLevelsOverviewForSilkWarm(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsOverviewForSilkWarm(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsOverviewForSilkWarm - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	@PostMapping("/getNregsSericultureOverview")
	public @ResponseBody NregsOverviewVO getNregsSericultureOverview(@RequestBody InputVO vo){
		NregsOverviewVO finalVo = null;
		try {
			finalVo = nregsTcsService.getNregsSericultureOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsSericultureOverview - NREGSController controller", e);
		}
		return finalVo;
	}
	@PostMapping("/getNregsSericultureData")
	public @ResponseBody List<NregsDataVO> getNregsSericultureData(@RequestBody InputVO vo){
		List<NregsDataVO> sericultureData = null;
		try {
			sericultureData = nregsTcsService.getNregsSericultureData(vo);
	
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsSericultureData - NREGSController controller", e);
		}
		return sericultureData;
	}
	@PostMapping("/getNregsHousingOverview")
	public @ResponseBody NregsOverviewVO getNregsHousingOverview(@RequestBody InputVO vo){
		NregsOverviewVO finalVo = null;
		try {
			finalVo = nregsTcsService.getNregsHousingOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsHousingOverview - NREGSController controller", e);
		}
		return finalVo;
	}
	@PostMapping("/getNregsHousingData")
	public @ResponseBody List<NregsDataVO> getNregsHousingData(@RequestBody InputVO vo){
		List<NregsDataVO> housingData = null;
		try {
			housingData = nregsTcsService.getNregsHousingData(vo);
	
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsHousingData - NREGSController controller", e);
		}
		return housingData;
	}
}
