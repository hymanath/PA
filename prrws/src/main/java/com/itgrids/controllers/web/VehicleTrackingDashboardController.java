package com.itgrids.controllers.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.VehicleTrackingVO;
import com.itgrids.service.IVehicleTrackingDashboardService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class VehicleTrackingDashboardController {
	
	private static final Logger LOG = Logger.getLogger(VehicleTrackingDashboardController.class);
	
	@Autowired
	private IVehicleTrackingDashboardService vehicleTrackingDashboardService;
	
	@RequestMapping(value ="/getVehicleTrackingOverviewDetails" , method = RequestMethod.POST)
	public @ResponseBody VehicleTrackingVO getVehicleTrackingOverviewDetails(){
		VehicleTrackingVO returnVO = null;
		try {
			returnVO = vehicleTrackingDashboardService.getVehicleTrackingOverviewDetails();
			
		} catch (Exception e) {
			LOG.error("Exception raised at getVehicleTrackingOverviewDetails - VehicleTrackingDashboardController", e);
		}
		return returnVO;
	}
	
	@RequestMapping(value ="/getOnFieldVehiclesDetails" , method = RequestMethod.POST)
	public @ResponseBody VehicleTrackingVO getOnFieldVehiclesDetails(){
		VehicleTrackingVO returnVO = null;
		try {
			returnVO = vehicleTrackingDashboardService.getOnFieldVehiclesDetails();
			
		} catch (Exception e) {
			LOG.error("Exception raised at getOnFieldVehiclesDetails - VehicleTrackingDashboardController", e);
		}
		return returnVO;
	}
}
