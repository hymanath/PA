package com.itgrids.controllers.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationAddressVO;
import com.itgrids.dto.PanchayatTaxVO;
import com.itgrids.dto.TaxesVO;
import com.itgrids.dto.VehicleTrackingVO;
import com.itgrids.dto.panchayatTaxInputVO;
import com.itgrids.service.ITaxesDashBoardService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class TaxesDashboardController {
	
	private static final Logger LOG = Logger.getLogger(TaxesDashboardController.class);
	
	@Autowired
	private ITaxesDashBoardService taxesDashBoardService;
	
	
	@RequestMapping(value ="/panchayatTaxDashboard",method = RequestMethod.GET)
    public String taxesDashboard(ModelMap model) {
		return "taxesDashboard";
    }
	
   @RequestMapping(value = "/getTaxesAndCategoryWiseOverViewDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  TaxesVO  getTaxesAndCategoryWiseOverViewDetails(@RequestBody InputVO inputVo) {
	TaxesVO  reusltVO = taxesDashBoardService.getTaxesAndCategoryWiseOverViewDetails(inputVo);
	   return reusltVO;
   }
   
   @RequestMapping(value = "/getTaxesIndicatorDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  List<TaxesVO>  getTaxesIndicatorDetails(@RequestBody InputVO inputVo) {
	List<TaxesVO>  reusltList = taxesDashBoardService.getTaxesIndicatorDetails(inputVo);
	   return reusltList;
   }
   
   @RequestMapping(value = "/getTaxesDefaultOverviewDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  List<TaxesVO>  getTaxesDefaultOverviewDetails(@RequestBody InputVO inputVo) {
	List<TaxesVO>  reusltList = taxesDashBoardService.getTaxesDefaultOverviewDetails(inputVo);
	   return reusltList;
   }

   @RequestMapping(value = "/getPanchyatTaxDashboardFilterWiseDetails", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  List<PanchayatTaxVO>  getPanchyatTaxDashboardFilterWiseDetails(@RequestBody panchayatTaxInputVO inputVo) {
	List<PanchayatTaxVO>  reusltList = taxesDashBoardService.getPanchyatTaxDashboardFilterWiseDetails(inputVo);
	   return reusltList;
   }
   
   @RequestMapping(value ="/vehicleTrackingDashboard",method = RequestMethod.GET)
   public String vehicleDashboard(ModelMap model) {
		return "vehicleTrackingDashboard";
   }
   
   @RequestMapping(value = "/getVehicletrackingDetails", method = RequestMethod.POST)
   public @ResponseBody  VehicleTrackingVO  getVehicletrackingDetails() {
	   VehicleTrackingVO  reusltVO = taxesDashBoardService.getVehicletrackingDetails();
	   return reusltVO;
   }
   
   @RequestMapping(value = "/getLocationIdAndName", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody  List<LocationAddressVO>  getLocationIdAndName(@RequestBody InputVO inputVo){
	   List<LocationAddressVO> returnList =  taxesDashBoardService.getLocationIdAndName(inputVo);
	   return returnList;
   }
   
   
}
