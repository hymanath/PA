package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.VehicleTrackingVO;
import com.itgrids.service.IVehicleTrackingDashboardService;
import com.itgrids.service.IWebserviceHandlerService;
import com.itgrids.utils.IConstants;

@Transactional
@Service
public class VehicleTrackingDashboardService implements IVehicleTrackingDashboardService{
	
	private static final Logger LOG = Logger.getLogger(VehicleTrackingDashboardService.class);

	@Autowired
	private IWebserviceHandlerService webserviceHandlerService;
	
	/*
	 * Author : Nandhini.k,
	 * @Description : To Get Vehicle Tracking OverView Details,
	 * Date : 14-MARCH-2018.
	 */
	
	public VehicleTrackingVO getVehicleTrackingOverviewDetails(){
		VehicleTrackingVO finalVO = new VehicleTrackingVO();
		try {
			String dataStr = null;
			String finalStr = null;
			JSONObject dataObj = null;
			JSONObject finalObj = null;
			String url = null;
			String output = null;
			
			//Vehicle Details
			 url = "http://rwstracking.com/dashboard/API/getvehicles.php";
			 output = webserviceHandlerService.callWebService(url, null,IConstants.REQUEST_METHOD_GET);
			
			if(output == null){
	 	    	throw new RuntimeException("Webservice Data Not Found."+url);
	 	    }else{
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONObject outputObj = new JSONObject(output);
	 	    		dataStr = outputObj.has("data") ? outputObj.get("data").toString():null;
	 	    		if(dataStr != null){
	 	    			dataObj = new JSONObject(dataStr);
	 	    		}
	 	    		finalStr = dataObj.has("vehicles") ? dataObj.get("vehicles").toString():null;
	 	    		if(finalStr != null){
	 	    			finalObj = new JSONObject(finalStr);
	 	    		}
	 	    		
	 	    		if(finalObj != null && !finalObj.toString().isEmpty()){
	 	    			finalVO.setTotalVehicles(finalObj.has("total_vehicles") ? Long.valueOf(!finalObj.get("total_vehicles").toString().equalsIgnoreCase("null") ? finalObj.get("total_vehicles").toString():"0"):0);
	 	    			finalVO.setOnFieldVehicles(finalObj.has("onfiled_vehicles") ? Long.valueOf(!finalObj.get("onfiled_vehicles").toString().equalsIgnoreCase("null") ? finalObj.get("onfiled_vehicles").toString():"0"):0);
	 	    			finalVO.setOffFieldVehicles(finalObj.has("offfield_vehicles") ? Long.valueOf(!finalObj.get("offfield_vehicles").toString().equalsIgnoreCase("null") ? finalObj.get("offfield_vehicles").toString():"0"):0);
	 	    			finalVO.setOnFieldVehiclePerc(finalObj.has("onfiled_vehicles_percent") ? !finalObj.get("onfiled_vehicles_percent").toString().equalsIgnoreCase("null") ? finalObj.get("onfiled_vehicles_percent").toString():"0":"0");
	 	    			finalVO.setOffFieldVehiclePerc(finalObj.has("offfield_vehicles_percent") ? !finalObj.get("offfield_vehicles_percent").toString().equalsIgnoreCase("null") ? finalObj.get("offfield_vehicles_percent").toString():"0":"0");
	 	    		}
	 	    	 }	
	 	    }
			
			//Water Details
			 url = "http://rwstracking.com/dashboard/API/getwater.php";
			 output = webserviceHandlerService.callWebService(url, null,IConstants.REQUEST_METHOD_GET);
			
			if(output == null){
	 	    	throw new RuntimeException("Webservice Data Not Found."+url);
	 	    }else{
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONObject outputObj = new JSONObject(output);
	 	    		dataStr = outputObj.has("data") ? outputObj.get("data").toString():null;
	 	    		if(dataStr != null){
	 	    			dataObj = new JSONObject(dataStr);
	 	    		}
	 	    		finalStr = dataObj.has("water") ? dataObj.get("water").toString():null;
	 	    		if(finalStr != null){
	 	    			finalObj = new JSONObject(finalStr);
	 	    		}
	 	    		
	 	    		if(finalObj != null && !finalObj.toString().isEmpty()){
	 	    			finalVO.setTargetWater(finalObj.has("target_water_supply") ? !finalObj.get("target_water_supply").toString().equalsIgnoreCase("null") ? finalObj.get("target_water_supply").toString():"0":"0");
	 	    			finalVO.setSuppliedWater(finalObj.has("supplied_water") ? !finalObj.get("supplied_water").toString().equalsIgnoreCase("null") ? finalObj.get("supplied_water").toString():"0":"0");
	 	    			finalVO.setNotSuppliedWater(finalObj.has("not_supplied_water") ? !finalObj.get("not_supplied_water").toString().equalsIgnoreCase("null") ? finalObj.get("not_supplied_water").toString():"0":"0");
	 	    			finalVO.setSuppliedWaterPerc(finalObj.has("supplied_water_percent") ? !finalObj.get("supplied_water_percent").toString().equalsIgnoreCase("null") ? finalObj.get("supplied_water_percent").toString():"0":"0");
	 	    			finalVO.setNotSuppliedWaterPerc(finalObj.has("not_supplied_water_percent") ? !finalObj.get("not_supplied_water_percent").toString().equalsIgnoreCase("null") ? finalObj.get("not_supplied_water_percent").toString():"0":"0");
	 	    		}
	 	    	 }	
	 	    }
			
			//Trips And Stressed Habitation Details
			 url = "http://rwstracking.com/dashboard/API/gettrips.php";
			 output = webserviceHandlerService.callWebService(url, null,IConstants.REQUEST_METHOD_GET);
			
			if(output == null){
	 	    	throw new RuntimeException("Webservice Data Not Found."+url);
	 	    }else{
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONObject outputObj = new JSONObject(output);
	 	    		dataStr = outputObj.has("data") ? outputObj.get("data").toString():null;
	 	    		if(dataStr != null){
	 	    			dataObj = new JSONObject(dataStr);
	 	    		}
	 	    		finalStr = dataObj.has("trips") ? dataObj.get("trips").toString():null;
	 	    		if(finalStr != null){
	 	    			finalObj = new JSONObject(finalStr);
	 	    		}
	 	    		
	 	    		if(finalObj != null && !finalObj.toString().isEmpty()){
	 	    			finalVO.setTargetTrips(finalObj.has("target_trips") ? Long.valueOf(!finalObj.get("target_trips").toString().equalsIgnoreCase("null") ? finalObj.get("target_trips").toString():"0"):0);
	 	    			finalVO.setInProgressTrips(finalObj.has("inprogress_trips") ? Long.valueOf(!finalObj.get("inprogress_trips").toString().equalsIgnoreCase("null") ? finalObj.get("inprogress_trips").toString():"0"):0);
	 	    			finalVO.setCompltedTrips(finalObj.has("completed_trips") ? Long.valueOf(!finalObj.get("completed_trips").toString().equalsIgnoreCase("null") ? finalObj.get("completed_trips").toString():"0"):0);
	 	    			finalVO.setInProgressTripsPerc(finalObj.has("inprogress_trips_percent") ? !finalObj.get("inprogress_trips_percent").toString().equalsIgnoreCase("null") ? finalObj.get("inprogress_trips_percent").toString():"0":"0");
	 	    			finalVO.setCompltedTripsPerc(finalObj.has("completed_trips_percent") ? !finalObj.get("completed_trips_percent").toString().equalsIgnoreCase("null") ? finalObj.get("completed_trips_percent").toString():"0":"0");
	 	    			finalVO.setTotalStressedHabs(finalObj.has("total_stressed_habitations") ? Long.valueOf(!finalObj.get("total_stressed_habitations").toString().equalsIgnoreCase("null") ? finalObj.get("total_stressed_habitations").toString():"0"):0);
	 	    			finalVO.setCoveredHabs(finalObj.has("covered_habs") ? Long.valueOf(!finalObj.get("covered_habs").toString().equalsIgnoreCase("null") ? finalObj.get("covered_habs").toString():"0"):0);
	 	    			finalVO.setNotCoveredHabs(finalObj.has("not_covered_habs") ? Long.valueOf(!finalObj.get("not_covered_habs").toString().equalsIgnoreCase("null") ? finalObj.get("not_covered_habs").toString():"0"):0);
	 	    			finalVO.setCoveredHabsPerc(finalObj.has("covered_habs_percent") ? !finalObj.get("covered_habs_percent").toString().equalsIgnoreCase("null") ? finalObj.get("covered_habs_percent").toString():"0":"0");
	 	    			finalVO.setNotCoveredHabsPerc(finalObj.has("not_covered_habs_percent") ? !finalObj.get("not_covered_habs_percent").toString().equalsIgnoreCase("null") ? finalObj.get("not_covered_habs_percent").toString():"0":"0");
	 	    		}
	 	    	 }	
	 	    }
			
		} catch (Exception e) {
			LOG.error("Exception occured at getVehicleTrackingOverviewDetails() in  VehicleTrackingDashboardService class",e);
		}
		return finalVO;
	}
	
	/*
	 * Author : Nandhini.k,
	 * @Description : To Get On Field Vehicles Details,
	 * Date : 14-MARCH-2018
	 */
	
	public VehicleTrackingVO getOnFieldVehiclesDetails(){
		VehicleTrackingVO finalVO = new VehicleTrackingVO();
		try {
			JSONObject dataObj = null;
			JSONObject finalObj = null;
			
			String url = "http://rwstracking.com/dashboard/API/gettankers.php";
			String output = webserviceHandlerService.callWebService(url, null,IConstants.REQUEST_METHOD_GET);
			
			if(output == null){
	 	    	throw new RuntimeException("Webservice Data Not Found."+url);
	 	    }else{
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONObject outputObj = new JSONObject(output);
	 	    		String dataStr = outputObj.has("data") ? outputObj.get("data").toString():null;
	 	    		if(dataStr != null){
	 	    		     dataObj = new JSONObject(dataStr);
	 	    		}
	 	    		String finalStr = dataObj.has("tankers") ? dataObj.get("tankers").toString():null;
	 	    		if(finalStr != null){
	 	    			finalObj = new JSONObject(finalStr);
	 	    		}
	 	    		
	 	    		if(finalObj != null && !finalObj.toString().isEmpty()){
	 	    			finalVO.setOnFieldVehicles(finalObj.has("onfiled_vehicles") ? Long.valueOf(!finalObj.get("onfiled_vehicles").toString().equalsIgnoreCase("null") ? finalObj.get("onfiled_vehicles").toString():"0"):0);
	 	    			finalVO.setWithGPSTracking(finalObj.has("gps_tracking_vehicles") ? Long.valueOf(!finalObj.get("gps_tracking_vehicles").toString().equalsIgnoreCase("null") ? finalObj.get("gps_tracking_vehicles").toString():"0"):0);
	 	    			finalVO.setWithGPSInProgress(finalObj.has("trips_inprogress_vehicles") ? Long.valueOf(!finalObj.get("trips_inprogress_vehicles").toString().equalsIgnoreCase("null") ? finalObj.get("trips_inprogress_vehicles").toString():"0"):0);
	 	    			finalVO.setWithGPSComplted(finalObj.has("trips_completed_vehicles") ? Long.valueOf(!finalObj.get("trips_completed_vehicles").toString().equalsIgnoreCase("null") ? finalObj.get("trips_completed_vehicles").toString():"0"):0);
	 	    			finalVO.setInProgressOnLineVehicleStatus(finalObj.has("inprogress_online_vehicles_status") ? Long.valueOf(!finalObj.get("inprogress_online_vehicles_status").toString().equalsIgnoreCase("null") ? finalObj.get("inprogress_online_vehicles_status").toString():"0"):0);
	 	    			finalVO.setInProgressOffLineVehicleStatus(finalObj.has("inprogress_offline_vehicles_status") ? Long.valueOf(!finalObj.get("inprogress_offline_vehicles_status").toString().equalsIgnoreCase("null") ? finalObj.get("inprogress_offline_vehicles_status").toString():"0"):0);
	 	    			finalVO.setWithOutGPSTracking(finalObj.has("without_gps_tracking_vehicles") ? Long.valueOf(!finalObj.get("without_gps_tracking_vehicles").toString().equalsIgnoreCase("null") ? finalObj.get("without_gps_tracking_vehicles").toString():"0"):0);
	 	    			finalVO.setWithOutGPSInProgress(finalObj.has("inprogress_offline_vehicles") ? Long.valueOf(!finalObj.get("inprogress_offline_vehicles").toString().equalsIgnoreCase("null") ? finalObj.get("inprogress_offline_vehicles").toString():"0"):0);
	 	    			finalVO.setWithOutGPSComplted(finalObj.has("offline_trips_completed_vehicles") ? Long.valueOf(!finalObj.get("offline_trips_completed_vehicles").toString().equalsIgnoreCase("null") ? finalObj.get("offline_trips_completed_vehicles").toString():"0"):0);
	 	    			finalVO.setWithGPSInProgressPerc(finalObj.has("trips_inprogress_vehicles_percent") ? !finalObj.get("trips_inprogress_vehicles_percent").toString().equalsIgnoreCase("null") ? finalObj.get("trips_inprogress_vehicles_percent").toString():"0":"0");
	 	    			finalVO.setWithGPSCompltedPerc(finalObj.has("trips_completed_vehicles_percent") ? !finalObj.get("trips_completed_vehicles_percent").toString().equalsIgnoreCase("null") ? finalObj.get("trips_completed_vehicles_percent").toString():"0":"0");
	 	    			finalVO.setWithOutGPSInProgressPerc(finalObj.has("inprogress_offline_vehicles_percent") ? !finalObj.get("inprogress_offline_vehicles_percent").toString().equalsIgnoreCase("null") ? finalObj.get("inprogress_offline_vehicles_percent").toString():"0":"0");
	 	    			finalVO.setWithOutGPSCompltedPerc(finalObj.has("offline_trips_completed_vehicles_percent") ? !finalObj.get("offline_trips_completed_vehicles_percent").toString().equalsIgnoreCase("null") ? finalObj.get("offline_trips_completed_vehicles_percent").toString():"0":"0");
	 	    		}
	 	    	 }	
	 	    }
			
		} catch (Exception e) {
			LOG.error("Exception occured at getOnFieldVehiclesDetails() in  VehicleTrackingDashboardService class",e);
		}
		return finalVO;		
	}
	
	/*
	 * Author : Nandhini.k,
	 * @Description : To Get Day Wise Off Field Vehicles Details,
	 * Date : 14-MARCH-2018
	 */
	
	public List<VehicleTrackingVO> getDayWiseOffFieldVehicleDetails(){
		List<VehicleTrackingVO> returnList = new ArrayList<VehicleTrackingVO>(0);
		try {
			
		} catch (Exception e) {
			LOG.error("Exception occured at getDayWiseOffFieldVehicleDetails() in  VehicleTrackingDashboardService class",e);
		}
		return returnList;
	}
}
