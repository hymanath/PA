package com.itgrids.controllers.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.itgrids.dto.CadreRegistrationVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.PmRequestEditVO;
import com.itgrids.dto.PmRequestVO;
import com.itgrids.dto.RepresentationRequestVO;
import com.itgrids.dto.RepresenteeViewVO;
import com.itgrids.dto.ResponseVO;
import com.itgrids.dto.UserVO;
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
	    public String mgnregsDashBoardPage(ModelMap model,HttpServletRequest request) {
		    UserVO uservo = (UserVO) request.getSession().getAttribute("USER");
			if (uservo==null){
		      return "petitionsLoginPage";
		    }
			return "representationRequestEntry";
	    }
 
	   	@RequestMapping(value ="/saveRepresentRequestDetails",method = RequestMethod.POST)
	    public @ResponseBody ResponseVO saveRepresentRequestDetails(@ModelAttribute  PmRequestVO pmRequestVO,HttpServletRequest request) {
	   		UserVO uservo = (UserVO) request.getSession().getAttribute("USER");
	   		if(uservo == null)
	   			return null;
	   		else{
	   			pmRequestVO.setUserId(uservo.getUserId());
				return pmRequestDetailsService.saveRepresentRequestDetails(pmRequestVO);
	   		}
	    }
		@RequestMapping(value ="/setPmRepresenteeDataToResultView",method = RequestMethod.POST)
		 public @ResponseBody PmRequestEditVO setPmRepresenteeDataToResultView(@RequestBody Map<String,String> inputMap ) {
	    	 return pmRequestDetailsService.setPmRepresenteeDataToResultView(Long.valueOf(inputMap.get("petitionId")),String.valueOf(inputMap.get("pageType")));
	    }
	   	@RequestMapping(value ="/getPetitionReferredMemberDetails",method = RequestMethod.POST)
	    public @ResponseBody List<RepresentationRequestVO> getPetitionReferredMemberDetails(@RequestBody RepresentationRequestVO dataVo ) {
	    	 return pmRequestDetailsService.getPetitionReferredMemberDetails(dataVo);
	    }
	   @RequestMapping(value ="/representationRequestEntryViewMembers", method = RequestMethod.GET)
	    public String representationRequestEntryMembers(ModelMap model,HttpServletRequest request) {
		    UserVO uservo = (UserVO) request.getSession().getAttribute("USER");
			if (uservo==null){
		      return "petitionsLoginPage";
		    }
			return "representationRequestEntryViewMembers";
	    }
	   @RequestMapping(value ="/getRepresentativeSearchWiseDetails",method = RequestMethod.POST)
	    public @ResponseBody List<RepresenteeViewVO> getRepresentativeSearchWiseDetails(@RequestBody InputVO dataVo) {
	    	 return pmRequestDetailsService.getRepresentativeSearchWiseDetails(dataVo);
	    }
	   @RequestMapping(value ="/getPetitionDesignationList",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getPetitionDesignationList(@RequestBody Map<String,String> inputMap ) {
	       return locationDetailsService.getPmDesignations(inputMap.get("searchType"));
	    }
	    @RequestMapping(value ="/getDistrictBySearchType",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getDistrictBySearchType(@RequestBody Map<String,String> inputMap ,HttpServletRequest request) {
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			Long userId =null;
			if(userVO != null){
				userId = userVO.getUserId();
			}
			List<Long> deptIds = null;
			KeyValueVO deptVO = pmRequestDetailsService.getDeptIdsListBYUserIds(userId);
			 deptIds = deptVO.getDeptIdsList();
	    	return locationDetailsService.getDistrictBySearchType(inputMap.get("searchType"),deptIds);
	    }
	    @RequestMapping(value ="/getConstituenciesBySearchTypeAndDistrict",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getConstituenciesBySearchTypeAndDistrict(@RequestBody InputVO inputVO ) {
	       return locationDetailsService.getConstituenciesBySearchTypeAndDistrictId(inputVO.getFilterType(),inputVO.getSearchLvlVals());
	    }
	    @RequestMapping(value ="/getMandalsBySearchTypeAndConstituency",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getMandalsBySearchTypeAndConstituency(@RequestBody InputVO inputVO ) {
	    	return locationDetailsService.getMandalsBySearchTypeAndConstituencyId(inputVO.getFilterType(),inputVO.getSearchLvlVals());
	    }
	    @RequestMapping(value ="/getDesignationsBySearchType",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getDesignationsBySearchType(@RequestBody Map<String,String> inputMap ) {
	       return locationDetailsService.getDesignationsBySearchType(inputMap.get("searchType"));
	    }
	    @RequestMapping(value ="/getDepartmentsBySearchType",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getDepartmentsBySearchType(@RequestBody Map<String,String> inputMap ) {
	       return locationDetailsService.getDepartmentsBySearchType(inputMap.get("searchType"));
	    }
	    @RequestMapping(value ="/getStatusList",method = RequestMethod.POST)
	    public @ResponseBody List<RepresenteeViewVO> getStatusList() {
	       return pmRequestDetailsService.getStatusList();
	    }
	    @RequestMapping(value ="/getRegistrationPersonDetails",method = RequestMethod.POST)
	    public @ResponseBody CadreRegistrationVO getRegistrationPersonDetails(@RequestBody Map<String,String> inputMap ) {
	       return pmRequestDetailsService.getRegistrationPersonDetails(inputMap);
	    }
	    @RequestMapping(value ="/getCompleteOrStatusOverviewDetails",method = RequestMethod.POST)
	    public @ResponseBody RepresenteeViewVO getCompleteOrStatusOverviewDetails(@RequestBody Map<String,String> inputMap ,HttpServletRequest request) {
	    	Long userId =null;
	    	/*HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			
			if(userVO != null){
				userId = userVO.getUserId();
			}*/
	    	return pmRequestDetailsService.getCompleteOrStatusOverviewDetails(userId,inputMap.get("fromDate"),inputMap.get("toDate"));
	    }
	    @RequestMapping(value ="/representationsDashboard", method = RequestMethod.GET)
	    public String representationsDashboard(ModelMap model,HttpServletRequest request) {
		    /*UserVO uservo = (UserVO) request.getSession().getAttribute("USER");
			if (uservo==null){
		      return "petitionsLoginPage";
		    }*/
			return "representationsDashboard";
	    }
	    @RequestMapping(value ="/getDeptIdsListBYUserIdsLst",method = RequestMethod.POST)
	    public @ResponseBody KeyValueVO getDeptIdsListBYUserIds(@RequestBody Map<String,String> inputMap ) {
	       return pmRequestDetailsService.getDeptIdsListBYUserIds(Long.valueOf(inputMap.get("userId")));
	    }
	    @RequestMapping(value ="/getPmDeptStatusIdsByUserIdsLst",method = RequestMethod.POST)
	    public @ResponseBody KeyValueVO getPmDeptStatusIdsByUserIds(@RequestBody Map<String,String> inputMap ) {
	       return pmRequestDetailsService.getPmDeptStatusIdsByUserIdsLst(Long.valueOf(inputMap.get("userId")));
	    }
}
