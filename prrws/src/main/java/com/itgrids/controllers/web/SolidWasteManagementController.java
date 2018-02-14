package com.itgrids.controllers.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.SolidWasteManagementVO;
import com.itgrids.dto.WebServiceDataVO;
import com.itgrids.service.ISolidWasteManagementService;

//import com.itgrids.service.IBioMetricService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class SolidWasteManagementController {

	   private static final Logger LOG = Logger.getLogger(SolidWasteManagementController.class);
	
	   @Autowired
	   private ISolidWasteManagementService solidWasteManagementService;
	   
	   @GetMapping(value="/solidWasteManagementDashboard")
	   public String solidWasteManagementDashboard() {
		   return "solidWasteManagementDashBoard";
	   }
	   @PostMapping("/getSolidInfoLocationWise")
		public @ResponseBody List<SolidWasteManagementVO> getSolidInfoLocationWise(@RequestBody InputVO inputVO)
		{
			
			List<SolidWasteManagementVO> solidWasteManagementVO=null;
			try {
				solidWasteManagementVO = solidWasteManagementService.getSolidInfoLocationWise(inputVO);
				
			} catch (Exception e) {
				LOG.error("Exception raised at  getSolidInfoLocationWise - SolidWasteManagementController controller", e);
			}
			return solidWasteManagementVO;
		}
	   
	   
	   @PostMapping("/getSolidWasteManagementOverAllCounts")
		public @ResponseBody SolidWasteManagementVO getSolidWasteManagementOverAllCounts(@RequestBody InputVO inputVO)
		{
			
		   SolidWasteManagementVO solidWasteManagementVO=null;
			try {
				solidWasteManagementVO = solidWasteManagementService.getSolidWasteManagementOverAllCounts(inputVO);
				
			} catch (Exception e) {
				LOG.error("Exception raised at  getSolidWasteManagementOverAllCounts - SolidWasteManagementController controller", e);
			   
			}
			return solidWasteManagementVO;
		}
	   @PostMapping("/getRfidTrackingOverAllTargetsData")
		public @ResponseBody List<SolidWasteManagementVO> getRfidTrackingOverAllTargetsData(@RequestBody InputVO inputVO)
		{
			
			List<SolidWasteManagementVO> solidWasteManagementVO=null;
			try {
				solidWasteManagementVO = solidWasteManagementService.getRfidTrackingOverAllTargetsData(inputVO);
				
			} catch (Exception e) {
				LOG.error("Exception raised at  getSolidInfoLocationWise - SolidWasteManagementController controller", e);
			}
			return solidWasteManagementVO;
		}

	   
	/*  @RequestMapping(value = "/saveRfidTrackingOverAllTargets", method = RequestMethod.POST)
	   public WebServiceDataVO saveItcWebServiceJsonData() {

		   WebServiceDataVO resultStatus = null;

	   	try {
	   		resultStatus = solidWasteManagementService.saveRfidTrackingOverAllTargets();
	   		
	   	}catch (Exception e) {
	   		
	   		resultStatus.setStatus("failure");
	   		LOG.error("Exception Occured in saveRfidTrackingOverAllTargets() in SolidWasteManagementController class.",e);
	   	}
	   	return resultStatus;
	   }*/
	   @RequestMapping(value = "/saveRfidTrackingOverAllTargets", method = RequestMethod.POST)
	   public WebServiceDataVO saveRfidTrackingOverAllTargets(){
		   WebServiceDataVO resultStatus = new WebServiceDataVO();
		   try {
		   		resultStatus = solidWasteManagementService.saveRfidTrackingOverAllTargets();
		   		
		   	}catch (Exception e) {
		   		
		   		resultStatus.setStatus("failure");
		   		LOG.error("Exception Occured in saveRfidTrackingOverAllTargets() in SolidWasteManagementController class.",e);
		   	}
			return resultStatus;
	   }
}
