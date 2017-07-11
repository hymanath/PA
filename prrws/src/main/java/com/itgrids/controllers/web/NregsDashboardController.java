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
import com.itgrids.dto.LabourBudgetOverViewVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsOverviewVO;
import com.itgrids.dto.NregsProjectsVO;
import com.itgrids.dto.WebserviceDetailsVO;
import com.itgrids.service.IUserService;
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
	@Autowired
	private IUserService userServiceImpl;
		
	/*@RequestMapping(value ="/test", method = RequestMethod.GET)
    public String fundManagementDashboardPage(ModelMap model) {
		webServiceUtilService.testMethod();
		return "fundManagementDashboard";
    }*/

	/*@RequestMapping(value ="/MGNREGSDashboard", method = RequestMethod.GET)
    public String mgnregsDashBoardPage(ModelMap model,HttpSession session){
		if (null != session.getAttribute("locationTypeId")  && !session.getAttribute("locationTypeId").equals("")){
			AddressVO addressVO = userServiceImpl.getOriginalLocationIdForTempId(Long.valueOf(session.getAttribute("locationTypeId").toString()),session.getAttribute("locationValue").toString(),session.getAttribute("fromPage").toString(),session.getAttribute("toPage").toString());
			model.addAttribute("addressVO", addressVO);
		}
		return "MGNREGS";
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
	
	@PostMapping("/getWebserviceHealthDetails")
	public @ResponseBody List<WebserviceDetailsVO> getWebserviceHealthDetails(@RequestBody InputVO vo){
		try{
			List<WebserviceDetailsVO> detailsVOs = nregsTcsService.getWebserviceHealthDetails(vo);
			return detailsVOs;
		}catch(Exception e){
			LOG.error("Exception raised at getWebserviceHealthDetails - getAHOverview controller", e);
		}
		return null;
	}
	
	@PostMapping("/getMGNregsDistrWiseCount")
	public @ResponseBody NregsDataVO getMGNregsDistrWiseConsti(@RequestBody InputVO vo){
		NregsDataVO levlWiseVO = null;
		try {
			levlWiseVO = nregsTcsService.getMGNregsDistrWiseConsti(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getMGNregsDistrWiseConsti - NREGSController controller", e);
		}
		return levlWiseVO;
	}
	
	@PostMapping("/getNregaLevelsWiseData")
	public @ResponseBody List<NregsDataVO> getNregaLevelsWiseData(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsWiseData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseData - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNregasOverview")
	public @ResponseBody NregsOverviewVO getNregasOverview(@RequestBody InputVO vo){
		NregsOverviewVO overViewVO = null;
		try {
			overViewVO = nregsTcsService.getNregasOverview(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregasOverview - NREGSController controller", e);
		}
		return overViewVO;
	}
	
	@PostMapping("/getNregaLevelsWiseDataFrNewCalls")
	public @ResponseBody List<NregsDataVO> getNregaLevelsWiseDataFrNewCalls(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsWiseDataFrNewCalls(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataFrNewCalls - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNregaLevelsWiseDataFrAgriculture")
	public @ResponseBody List<NregsDataVO> getNregaLevelsWiseDataFrAgriculture(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsWiseDataFrAgriculture(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataFrAgriculture - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNregaLevelsWiseDataFrHorticulture")
	public @ResponseBody List<NregsDataVO> getNregaLevelsWiseDataFrHorticulture(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsWiseDataFrHorticulture(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataFrHorticulture - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNregaLevelsWiseDataForCCRoads")
	public @ResponseBody List<NregsDataVO> getNregaLevelsWiseDataForCCRoads(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsWiseDataForCCRoads(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataForCCRoads - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNregaLevelsWiseDataFrAvenue")
	public @ResponseBody List<NregsDataVO> getNregaLevelsWiseDataFrAvenue(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsWiseDataFrAvenue(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataFrAvenue - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNREGSAbstractDataByType")
	public @ResponseBody List<NregsProjectsVO> getNREGSAbstractDataByType(@RequestBody InputVO vo){
		List<NregsProjectsVO> locationVOList = null;
		try {
			locationVOList = nregsTcsService.getNREGSAbstractDataByType(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSAbstractDataByType - NREGSController controller", e);
		}
		return locationVOList;
	}
	
	@PostMapping("/getNregaLevelsWiseDataForTimelyPayments")
	public @ResponseBody List<NregsDataVO> getNregaLevelsWiseDataForTimelyPayments(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsWiseDataForTimelyPayments(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataForTimelyPayments - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	@PostMapping("/getNregaParliamentData")
	public @ResponseBody List<NregsDataVO> getNregaParliamentData(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaParliamentData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaParliamentData - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNREGSProjectsAbstractNew")
	public @ResponseBody List<NregsProjectsVO> getNREGSProjectsAbstractNew(@RequestBody InputVO vo){
		List<NregsProjectsVO> projectVOList = null;
		try {
			projectVOList = nregsTcsService.getNREGSProjectsAbstractNew(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSProjectsAbstractNew - NREGSController controller", e);
		}
		return projectVOList;
	}
	
	@PostMapping("/getAllNregaSubLocationDetails")
	public @ResponseBody List<LocationFundDetailsVO> getAllSubLocations(@RequestBody InputVO inputVO){
		List<LocationFundDetailsVO> locationFundDetailsVOList = nregsTcsService.getAllNregaSubLocationDetails(inputVO);
		return locationFundDetailsVOList;
	}
	
	@PostMapping("/getNregaLabourBudgetPanchatVsExpData")
	public @ResponseBody List<NregsDataVO> getNregaPanchatVsExpData(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaPanchatVsExpData(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaPanchatVsExpData - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
	
	@PostMapping("/getNregaLevelsWiseDataForFAPerformance")
	public @ResponseBody List<NregsDataVO> getNregaLevelsWiseDataForFAPerformance(@RequestBody InputVO vo){
		List<NregsDataVO> levlWiseVOList = null;
		try {
			levlWiseVOList = nregsTcsService.getNregaLevelsWiseDataForFAPerformance(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataForFAPerformance - NREGSController controller", e);
		}
		return levlWiseVOList;
	}
}
