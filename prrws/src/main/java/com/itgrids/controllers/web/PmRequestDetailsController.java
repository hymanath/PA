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
import com.itgrids.dto.PmRequestVO;
import com.itgrids.dto.RepresentationRequestVO;
import com.itgrids.dto.RepresenteeViewVO;
import com.itgrids.dto.ResponseVO;
import com.itgrids.service.ILocationDetailsService;
import com.itgrids.service.IPmRequestDetailsService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class PmRequestDetailsController {

	   private static final Logger LOG = Logger.getLogger(PmRequestDetailsController.class);
	   @Autowired
	   private ILocationDetailsService locationDetailsService;
	   
	   @Autowired
		private IPmRequestDetailsService pmRequestDetailsService; 
	   
	   @RequestMapping(value ="/representationRequestEntry", method = RequestMethod.GET)
	    public String mgnregsDashBoardPage(ModelMap model) {
			return "representationRequestEntry";
	    }
 
	   	@RequestMapping(value ="/saveRepresentRequestDetails",method = RequestMethod.POST)
	    public @ResponseBody ResponseVO saveRepresentRequestDetails(@ModelAttribute  PmRequestVO pmRequestVO) {
			pmRequestVO.setUserId(1L);
			return pmRequestDetailsService.saveRepresentRequestDetails(pmRequestVO);
	    }
		@RequestMapping(value ="/setPmRepresenteeDataToResultView",method = RequestMethod.POST)
		 public @ResponseBody PmRequestVO setPmRepresenteeDataToResultView(@RequestBody Map<String,String> inputMap ) {
	    	 return pmRequestDetailsService.setPmRepresenteeDataToResultView(Long.valueOf(inputMap.get("petitionId")));
	    }
	   	@RequestMapping(value ="/getPetitionReferredMemberDetails",method = RequestMethod.POST)
	    public @ResponseBody List<RepresentationRequestVO> getPetitionReferredMemberDetails(@RequestBody RepresentationRequestVO dataVo ) {
	    	 return pmRequestDetailsService.getPetitionReferredMemberDetails(dataVo);
	    }
	   @RequestMapping(value ="/representationRequestEntryViewMembers", method = RequestMethod.GET)
	    public String representationRequestEntryMembers(ModelMap model) {
			return "representationRequestEntryViewMembers";
	    }
	   @RequestMapping(value ="/getRepresentativeSearchWiseDetails",method = RequestMethod.POST)
	    public @ResponseBody List<RepresenteeViewVO> getRepresentativeSearchWiseDetails(@RequestBody InputVO dataVo) {
	    	 return pmRequestDetailsService.getRepresentativeSearchWiseDetails(dataVo);
	    }
	   @RequestMapping(value ="/getPetitionDesignationList",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getPetitionDesignationList(@RequestBody Map<String,String> inputMap ) {
	       return locationDetailsService.getPmDesignations();
	    }
	    @RequestMapping(value ="/getDistrictBySearchType",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getDistrictBySearchType(@RequestBody Map<String,String> inputMap ) {
	       return locationDetailsService.getDistrictBySearchType(inputMap.get("searchType"));
	    }
	    @RequestMapping(value ="/getConstituenciesBySearchTypeAndDistrict",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getConstituenciesBySearchTypeAndDistrict(@RequestBody Map<String,String> inputMap ) {
	       return locationDetailsService.getConstituenciesBySearchTypeAndDistrictId(inputMap.get("searchType"),Long.valueOf(inputMap.get("locationId")));
	    }
	    @RequestMapping(value ="/getMandalsBySearchTypeAndConstituency",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getMandalsBySearchTypeAndConstituency(@RequestBody Map<String,String> inputMap ) {
	       return locationDetailsService.getMandalsBySearchTypeAndConstituencyId(inputMap.get("searchType"),Long.valueOf(inputMap.get("locationId")));
	    }
	    @RequestMapping(value ="/getDesignationsBySearchType",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getDesignationsBySearchType(@RequestBody Map<String,String> inputMap ) {
	       return locationDetailsService.getDesignationsBySearchType(inputMap.get("searchType"));
	    }
	    @RequestMapping(value ="/getDepartmentsBySearchType",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getDepartmentsBySearchType(@RequestBody Map<String,String> inputMap ) {
	       return locationDetailsService.getDepartmentsBySearchType(inputMap.get("searchType"));
	    }
}
