package com.itgrids.controllers.web;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.BioMetricDashBoardDtlsVO;
import com.itgrids.service.IBioMetricService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class BioMetricController {

	    private static final Logger LOG = Logger.getLogger(BioMetricController.class);
	
	    @Autowired
	    private IBioMetricService bioMetricService;
	   
	    @RequestMapping(value ="/bioMetricDashBoard",method = RequestMethod.GET)
	    public String bioMetricDashBoard() {
		   return "bioMetricDashBoard";
	    }
	   
	    @RequestMapping(value ="/getBioMetricDashboardOverViewDtls",method = RequestMethod.POST)
	    public @ResponseBody BioMetricDashBoardDtlsVO getBioMetricDashboardOverViewDtls(@RequestBody Map<String,String> inputMap) {
	    	 return bioMetricService.getBioMetricDashboardOverViewDtls(inputMap.get("deptCode"));
	    }
	    
	    @RequestMapping(value ="/getEmployeeAttendenceTimePeriodWise",method = RequestMethod.POST)
	    public @ResponseBody List<BioMetricDashBoardDtlsVO> getEmployeeAttendenceTimePeriodWise(@RequestBody Map<String,String> inputMap) {
	    	 return bioMetricService.getEmployeeAttendenceTimePeriodWise(inputMap.get("deptCode"),inputMap.get("todayDate"));
	    }
	    
	    @RequestMapping(value ="/getDateWiseEmployeeAttendenceDetails",method = RequestMethod.POST)
	    public @ResponseBody List<BioMetricDashBoardDtlsVO> getDateWiseEmployeeAttendenceDetails(@RequestBody Map<String,String> inputMap) {
	    	 return bioMetricService.getDateWiseEmployeeAttendenceDetails(inputMap.get("fromDate"), inputMap.get("toDate"),inputMap.get("deptCode"));
	    }
	    
	    @RequestMapping(value ="/getEmployeeWiseAttendenceCount",method = RequestMethod.POST)
	    public @ResponseBody List<BioMetricDashBoardDtlsVO> getEmployeeWiseAttendenceCount(@RequestBody Map<String,String> inputMap) {
	    	 return bioMetricService.getEmployeeWiseAttendenceCount(inputMap.get("fromDate"), inputMap.get("toDate"),inputMap.get("deptCode"));
	    }
	    @RequestMapping(value ="/getIndividualEmployeeAttendenceDetails",method = RequestMethod.POST)
	    public @ResponseBody BioMetricDashBoardDtlsVO getIndividualEmployeeAttendenceDetails(@RequestBody Map<String,String> inputMap) {
	    	 return bioMetricService.getIndividualEmployeeAttendenceDetails(inputMap.get("fromDate"), inputMap.get("toDate"),inputMap.get("empId"),inputMap.get("deptCode"));
	    }
	    @RequestMapping(value ="/getEmployeeDetailsByEmployeeType",method = RequestMethod.POST)
	    public @ResponseBody List<BioMetricDashBoardDtlsVO> getEmployeeDetailsByEmployeeType(@RequestBody Map<String,String> inputMap) {
	    	 return bioMetricService.getEmployeeDetailsByEmployeeType(inputMap.get("deptCode"), inputMap.get("employeeType"));
	    }
	    @RequestMapping(value ="/getDateWisePresentEmployeeDetails",method = RequestMethod.POST)
	    public @ResponseBody List<BioMetricDashBoardDtlsVO> getDateWisePresentEmployeeDetails(@RequestBody Map<String,String> inputMap) {
	    	 return bioMetricService.getDateWisePresentEmployeeDetails(inputMap.get("deptCode"), inputMap.get("employeeType"),inputMap.get("fromDate"), inputMap.get("toDate"));
	    }
	    @RequestMapping(value ="/getTimePeriodWiseEmployeeAttendenceDetails",method = RequestMethod.POST)
	    public @ResponseBody List<BioMetricDashBoardDtlsVO> getTimePeriodWiseEmployeeAttendenceDetails(@RequestBody Map<String,String> inputMap) {
	    	 return bioMetricService.getTimePeriodWiseEmployeeAttendenceDetails(inputMap.get("deptCode"), inputMap.get("timePeriod"));
	    }
	   
}
