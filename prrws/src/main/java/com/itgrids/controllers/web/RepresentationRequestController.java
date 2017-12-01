package com.itgrids.controllers.web;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.RepresentationRequestVO;
import com.itgrids.dto.ResponseVO;
import com.itgrids.service.ILocationDetailsService;
import com.itgrids.service.IRepresentationRequestService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class RepresentationRequestController {
	private static final Logger LOG = Logger.getLogger(RepresentationRequestController.class);
	
	@Autowired
	private IRepresentationRequestService representationRequestService;
	@Autowired
	private ILocationDetailsService locationDetailsService;
	
	
	@RequestMapping(value ="/representationRequestEntry", method = RequestMethod.GET)
    public String mgnregsDashBoardPage(ModelMap model) {
		return "representationRequestEntry";
    }
	
	@RequestMapping(value ="/saveRepresentRequestDetails",method = RequestMethod.POST)
    public @ResponseBody ResponseVO saveRepresentRequestDetails(@ModelAttribute  RepresentationRequestVO representationRequestVO) {
				representationRequestVO.setUserId(1L);
    	 return representationRequestService.saveRepresentRequestDetails(representationRequestVO);
    }
	@RequestMapping(value ="/getAllDistrictsInState",method = RequestMethod.POST)
    public @ResponseBody List<LocationFundDetailsVO> getAllDistrictsInState(@RequestBody Map<String,String> inputMap ) {
    	 return locationDetailsService.getAllDistrictsInState(Long.valueOf(inputMap.get("stateId")));
    }
	@RequestMapping(value ="/getConstituencyNamesByDistrictId",method = RequestMethod.POST)
    public @ResponseBody List<LocationVO> getConstituencyNamesByDistrictId(@RequestBody Map<String,String> inputMap ) {
    	 return locationDetailsService.getConstituencyNamesByDistrictId(Long.valueOf(inputMap.get("districtId")));
    }
	@RequestMapping(value ="/getTehsilsAndLocalElectionBodyForConstituencyId",method = RequestMethod.POST)
    public @ResponseBody List<KeyValueVO> getTehsilsAndLocalElectionBodyForConstituencyId(@RequestBody Map<String,String> inputMap ) {
    	 return locationDetailsService.getTehsilsAndLocalElectionBodyForConstituencyId(Long.valueOf(inputMap.get("constituencyId")));
    }
	@RequestMapping(value ="/getPanchayatsByTehsilId",method = RequestMethod.POST)
    public @ResponseBody List<KeyValueVO> getPanchayatsByTehsilId(@RequestBody Map<String,String> inputMap ) {
    	 return locationDetailsService.getPanchayatsByTehsilId(Long.valueOf(inputMap.get("tehsilId")));
    }
	@RequestMapping(value ="/getPetitionDepartmentList",method = RequestMethod.POST)
    public @ResponseBody List<KeyValueVO> getPetitionDepartmentDetailsList(@RequestBody Map<String,String> inputMap ) {
       return locationDetailsService.getPetitionDepartmentList();
    }
	@RequestMapping(value ="/getPetitionDesignationList",method = RequestMethod.POST)
    public @ResponseBody List<KeyValueVO> getPetitionDesignationList(@RequestBody Map<String,String> inputMap ) {
       return locationDetailsService.getPetitionDesignationList();
    }
	@RequestMapping(value ="/getPetitionReferredMemberDetails",method = RequestMethod.POST)
    public @ResponseBody List<RepresentationRequestVO> getPetitionReferredMemberDetails(@RequestBody RepresentationRequestVO dataVo ) {
    	 return representationRequestService.getPetitionReferredMemberDetails(dataVo);
    }
	@RequestMapping(value ="/getRepresentativeSearchWiseDetails",method = RequestMethod.POST)
    public @ResponseBody List<RepresentationRequestVO> getRepresentativeSearchWiseDetails(@RequestBody InputVO dataVo) {
    	 return locationDetailsService.getRepresentativeSearchWiseDetails(dataVo);
    }
}