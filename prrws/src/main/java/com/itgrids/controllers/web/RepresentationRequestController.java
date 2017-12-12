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
	
	
	@RequestMapping(value ="/saveRepresentRequestDetails",method = RequestMethod.POST)
    public @ResponseBody ResponseVO saveRepresentRequestDetails(@ModelAttribute  RepresentationRequestVO representationRequestVO) {
				representationRequestVO.setUserId(1L);
    	 return representationRequestService.saveRepresentRequestDetails(representationRequestVO);
    }
	@RequestMapping(value ="/getAllDistrictsInState",method = RequestMethod.POST)
    public @ResponseBody List<LocationFundDetailsVO> getAllDistrictsInState(@RequestBody Map<String,String> inputMap ) {
    	 return locationDetailsService.getAllDistrictsInState(Long.valueOf(inputMap.get("stateId")),String.valueOf(inputMap.get("searchType")),Long.valueOf(inputMap.get("searchId"))); 
    }
	@RequestMapping(value ="/getConstituencyNamesByDistrictId",method = RequestMethod.POST)
    public @ResponseBody List<LocationVO> getConstituencyNamesByDistrictId(@RequestBody Map<String,String> inputMap ) {
    	 return locationDetailsService.getConstituencyNamesByDistrictId(Long.valueOf(inputMap.get("districtId")),String.valueOf(inputMap.get("searchType")),Long.valueOf(inputMap.get("searchId")));
    }
	@RequestMapping(value ="/getTehsilsAndLocalElectionBodyForConstituencyId",method = RequestMethod.POST)
    public @ResponseBody List<KeyValueVO> getTehsilsAndLocalElectionBodyForConstituencyId(@RequestBody Map<String,String> inputMap ) {
    	 return locationDetailsService.getTehsilsAndLocalElectionBodyForConstituencyId(Long.valueOf(inputMap.get("constituencyId")),String.valueOf(inputMap.get("searchType")),Long.valueOf(inputMap.get("searchId")));
    }
	@RequestMapping(value ="/getPanchayatsByTehsilId",method = RequestMethod.POST)
    public @ResponseBody List<KeyValueVO> getPanchayatsByTehsilId(@RequestBody Map<String,String> inputMap ) {
    	 return locationDetailsService.getPanchayatsByTehsilId(Long.valueOf(inputMap.get("tehsilId")));
    }
	@RequestMapping(value ="/getPmDepartmentList",method = RequestMethod.POST)
    public @ResponseBody List<KeyValueVO> getPmDepartmentDetailsList(@RequestBody Map<String,String> inputMap ) {
       return locationDetailsService.getPmDepartmentList(String.valueOf(inputMap.get("searchType")));
    }
	@RequestMapping(value ="/getPetitionDesignationList",method = RequestMethod.POST)
    public @ResponseBody List<KeyValueVO> getPetitionDesignationList(@RequestBody Map<String,String> inputMap ) {
       return locationDetailsService.getPetitionDesignationList(String.valueOf(inputMap.get("searchType")));
    }
	@RequestMapping(value ="/getPetitionReferredMemberDetails",method = RequestMethod.POST)
    public @ResponseBody List<RepresentationRequestVO> getPetitionReferredMemberDetails(@RequestBody RepresentationRequestVO dataVo ) {
    	 return representationRequestService.getPetitionReferredMemberDetails(dataVo);
    }
	@RequestMapping(value ="/getRepresentativeSearchWiseDetails",method = RequestMethod.POST)
    public @ResponseBody List<RepresentationRequestVO> getRepresentativeSearchWiseDetails(@RequestBody InputVO dataVo) {
    	 return representationRequestService.getRepresentativeSearchWiseDetails(dataVo);
    }
	@RequestMapping(value ="/representationRequestEntryViewMembers", method = RequestMethod.GET)
    public String representationRequestEntryMembers(ModelMap model) {
		return "representationRequestEntryViewMembers";
    }
	@RequestMapping(value ="/getRepresentationRequestDetailsByRepresentationRequestId",method = RequestMethod.POST)
    public @ResponseBody RepresentationRequestVO getRepresentationRequestDetailsByRepresentationRequestId(@RequestBody Map<String,String> inputMap ) {
    	 return representationRequestService.getRepresentationRequestDetailsByRepresentationRequestId(Long.valueOf(inputMap.get("representationMemberId")));
    }
	@RequestMapping(value ="/getParliamentIdsByConstituencyList",method = RequestMethod.POST)
    public @ResponseBody List<KeyValueVO> getParliamentByDistricList(@RequestBody Map<String,String> inputMap ) {
       return locationDetailsService.getParliamentIdsByConstituencyList();
    }
	@RequestMapping(value ="/representationRequestEdit", method = RequestMethod.GET)
    public String representationRequestEdit(ModelMap model) {
		return "representationRequestEdit";
    }
	@RequestMapping(value ="/getPmSubjectList",method = RequestMethod.POST)
    public @ResponseBody List<KeyValueVO> getPmSubjectList(@RequestBody Map<String,String> inputMap ) {
       return locationDetailsService.getPmSubjectList();
    }
	@RequestMapping(value ="/getPmSubSubjectList",method = RequestMethod.POST)
    public @ResponseBody List<KeyValueVO> getPmSubSubjectList(@RequestBody Map<String,String> inputMap ) {
       return locationDetailsService.getPmSubSubjectList(Long.valueOf(inputMap.get("subjectId")));
    }
	@RequestMapping(value ="/getPmLeadDetailsList",method = RequestMethod.POST)
    public @ResponseBody List<KeyValueVO> getPmLeadDetailsList(@RequestBody Map<String,String> inputMap ) {
       return locationDetailsService.getPmLeadDetailsList();
    }
	@RequestMapping(value ="/getPmBriefLeadList",method = RequestMethod.POST)
    public @ResponseBody List<KeyValueVO> getPmBriefLeadList(@RequestBody Map<String,String> inputMap ) {
       return locationDetailsService.getPmBriefLeadList();
    }
	@RequestMapping(value ="/getPmGrantList",method = RequestMethod.POST)
    public @ResponseBody List<KeyValueVO> getPmGrantList(@RequestBody Map<String,String> inputMap ) {
       return locationDetailsService.getPmGrantList();
    }
	@RequestMapping(value ="/getPmStatusList",method = RequestMethod.POST)
    public @ResponseBody List<KeyValueVO> getPmStatusList(@RequestBody Map<String,String> inputMap ) {
       return locationDetailsService.getPmStatusList();
    }
	@RequestMapping(value ="/getWorkTypeList",method = RequestMethod.POST)
    public @ResponseBody List<KeyValueVO> getWorkTypeList(@RequestBody Map<String,String> inputMap ) {
       return locationDetailsService.getWorkTypeList();
    }
}