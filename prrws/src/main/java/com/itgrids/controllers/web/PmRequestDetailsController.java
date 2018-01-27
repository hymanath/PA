package com.itgrids.controllers.web;

import java.util.ArrayList;
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
import com.itgrids.dto.MenuVO;
import com.itgrids.dto.PetitionHistoryVO;
import com.itgrids.dto.PetitionTrackingVO;
import com.itgrids.dto.PmRequestEditVO;
import com.itgrids.dto.PmRequestVO;
import com.itgrids.dto.RepresentationRequestVO;
import com.itgrids.dto.RepresenteeViewVO;
import com.itgrids.dto.ResponseVO;
import com.itgrids.dto.ResultStatus;
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
		 public @ResponseBody PmRequestEditVO setPmRepresenteeDataToResultView(@RequestBody Map<String,String> inputMap ,HttpServletRequest request) {
		    	Long userId =null;
		    	HttpSession session=request.getSession();
				UserVO userVO = (UserVO) session.getAttribute("USER"); 
				
				if(userVO != null){
					userId = userVO.getUserId();
				}else{
					return null;
				}
		    	 return pmRequestDetailsService.setPmRepresenteeDataToResultView(Long.valueOf(inputMap.get("petitionId")),String.valueOf(inputMap.get("pageType")),userId);
	    }
	   	@RequestMapping(value ="/getPetitionReferredMemberDetails",method = RequestMethod.POST)
	    public @ResponseBody List<RepresentationRequestVO> getPetitionReferredMemberDetails(@RequestBody RepresentationRequestVO dataVo ,HttpServletRequest request) {
	    	Long userId =null;
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
				return null;
			}
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
	    public @ResponseBody List<RepresenteeViewVO> getRepresentativeSearchWiseDetails(@RequestBody InputVO dataVo,HttpServletRequest request) {
	    	Long userId =null;
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			
			if(userVO != null){
				userId = userVO.getUserId();
				dataVo.setLocationId(userId);
			}else{
				return null;
			}
			
	    	 return pmRequestDetailsService.getRepresentativeSearchWiseDetails(dataVo);
	    }
	   @RequestMapping(value ="/getPetitionDesignationList",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getPetitionDesignationList(@RequestBody Map<String,String> inputMap ,HttpServletRequest request) {
	    	Long userId =null;
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
				return null;
			}
	       return locationDetailsService.getPmDesignations(inputMap.get("searchType"));
	    }
	    @RequestMapping(value ="/getDistrictBySearchType",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getDistrictBySearchType(@RequestBody InputVO inputVO ,HttpServletRequest request) {
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			Long userId =null;
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
				return null;
			}
			List<Long> deptIds = null;
			if(inputVO.getDeptIdsList() != null && inputVO.getDeptIdsList().size() >0){
				deptIds= inputVO.getDeptIdsList();
			}else{
				KeyValueVO deptVO = pmRequestDetailsService.getDeptIdsListBYUserIds(userId);
				deptIds = deptVO.getDeptIdsList();
			}
	    	return locationDetailsService.getDistrictBySearchType(inputVO,deptIds);
	    }
	    @RequestMapping(value ="/getConstituenciesBySearchTypeAndDistrict",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getConstituenciesBySearchTypeAndDistrict(@RequestBody InputVO inputVO,HttpServletRequest request ) {
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			Long userId =null;
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
				return null;
			}
			List<Long> deptIds = null;
			if(inputVO.getDeptIdsList() != null && inputVO.getDeptIdsList().size() >0){
				 deptIds= inputVO.getDeptIdsList();
			}else if(inputVO.getDesignationIds()==null){
			KeyValueVO deptVO = pmRequestDetailsService.getDeptIdsListBYUserIds(userId);
			deptIds = deptVO.getDeptIdsList();
			}
	    	return locationDetailsService.getConstituenciesBySearchTypeAndDistrictId(inputVO, inputVO.getSearchLvlVals(),deptIds,inputVO.getDesignationIds(),inputVO.getType(),inputVO.getStatusIds());
	    }
	    @RequestMapping(value ="/getMandalsBySearchTypeAndConstituency",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getMandalsBySearchTypeAndConstituency(@RequestBody InputVO inputVO,HttpServletRequest request ) {
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			Long userId =null;
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
				return null;
			}
			List<Long> deptIds = null;
			if(inputVO.getDeptIdsList() != null && inputVO.getDeptIdsList().size() >0){
				deptIds= inputVO.getDeptIdsList();
			}else{
				KeyValueVO deptVO = pmRequestDetailsService.getDeptIdsListBYUserIds(userId);
				deptIds = deptVO.getDeptIdsList();
			}
	    	return locationDetailsService.getMandalsBySearchTypeAndConstituencyId(inputVO.getFilterType(),inputVO.getSearchLvlVals(),deptIds,inputVO.getFromDate(),inputVO.getToDate(),inputVO.getDesignationIds(),inputVO.getpType(),inputVO.getStatusIds());
	    }
	    @RequestMapping(value ="/getDesignationsBySearchType",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getDesignationsBySearchType(@RequestBody InputVO inputVO,HttpServletRequest request ) {
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			Long userId =null;
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
				return null;
			}
			List<Long> deptIds = null;
			KeyValueVO deptVO = pmRequestDetailsService.getDeptIdsListBYUserIds(userId);
			 deptIds = deptVO.getDeptIdsList();
			 Long desigId = inputVO.getSourceId();
			 List<Long> statusIds = inputVO.getStatusIds();
	    	return locationDetailsService.getDesignationsBySearchType(inputVO.getReportType(),inputVO.getFromDate(),inputVO.getToDate(),deptIds,desigId,statusIds);
	    }
	    @RequestMapping(value ="/getDepartmentsBySearchType",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getDepartmentsBySearchType(@RequestBody InputVO inputVO,HttpServletRequest request ) {
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			Long userId =null;
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
				return null;
			}
			/*List<Long> deptIds = null;
			
			KeyValueVO deptVO = pmRequestDetailsService.getDeptIdsListBYUserIds(userId);
			 deptIds = deptVO.getDeptIdsList();*/
			List<Long> deptIds = null;
			Long deptId = inputVO.getDepartmentId();
			if(deptId != null && deptId.longValue() >0l){
				deptIds = new ArrayList<Long>();
				deptIds.add(deptId);
			}else{
				KeyValueVO deptVO = pmRequestDetailsService.getDeptIdsListBYUserIds(userId);
				deptIds = deptVO.getDeptIdsList();
			}
			List<Long> statusIds = inputVO.getStatusIds();
	    	return locationDetailsService.getDepartmentsBySearchType(inputVO.getReportType(),inputVO.getFromDate(),inputVO.getToDate(),deptIds,statusIds);
	    }
	    
	    @RequestMapping(value ="/getSubjectsBySearchType",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getSubjectsBySearchType(@RequestBody InputVO inputVO,HttpServletRequest request ) {
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			Long userId =null;
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
				return null;
			}
			List<Long> deptIds = null;
			KeyValueVO deptVO = pmRequestDetailsService.getDeptIdsListBYUserIds(userId);
			deptIds = deptVO.getDeptIdsList();
			
			List<Long> statusIds = inputVO.getStatusIds();
	    	return locationDetailsService.getSubjectsBySearchType(inputVO.getReportType(),inputVO.getFromDate(),inputVO.getToDate(),deptIds,statusIds,inputVO.getAssetType());
	    }
	    @RequestMapping(value ="/getStatusList",method = RequestMethod.POST)
	    public @ResponseBody List<RepresenteeViewVO> getStatusList(@RequestBody InputVO inputVO,HttpServletRequest request) {
	    	Long userId =null;
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
				return null;
			}
			//Long statusId = Long.valueOf(inputMap.get("statusId"));
	       return pmRequestDetailsService.getStatusList(inputVO.getStatusIds());
	    }
	    @RequestMapping(value ="/getRegistrationPersonDetails",method = RequestMethod.POST)
	    public @ResponseBody CadreRegistrationVO getRegistrationPersonDetails(@RequestBody Map<String,String> inputMap ,HttpServletRequest request) {
	    	Long userId =null;
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
				return null;
			}
	       return pmRequestDetailsService.getRegistrationPersonDetails(inputMap);
	    }
	    @RequestMapping(value ="/getCompleteOrStatusOverviewDetails",method = RequestMethod.POST)
	    public @ResponseBody RepresenteeViewVO getCompleteOrStatusOverviewDetails(@RequestBody Map<String,String> inputMap ,HttpServletRequest request) {
	    	Long userId =null;
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
				return null;
			}
	    	return pmRequestDetailsService.getCompleteOrStatusOverviewDetails(userId,inputMap.get("fromDate"),inputMap.get("toDate"));
	    }
	    @RequestMapping(value ="/representationsDashboard", method = RequestMethod.GET)
	    public String representationsDashboard(ModelMap model,HttpServletRequest request) {
		    UserVO uservo = (UserVO) request.getSession().getAttribute("USER");
			if (uservo==null){
		      return "petitionsLoginPage";
		    }
			return "representationsDashboard";
	    }
	    @RequestMapping(value ="/getDeptIdsListBYUserIdsLst",method = RequestMethod.POST)
	    public @ResponseBody KeyValueVO getDeptIdsListBYUserIds(@RequestBody Map<String,String> inputMap,HttpServletRequest request ) {
	    	Long userId =null;
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
				return null;
			}
	       return pmRequestDetailsService.getDeptIdsListBYUserIds(userId);
	    }
	    @RequestMapping(value ="/getPmDeptStatusIdsByUserIdsLst",method = RequestMethod.POST)
	    public @ResponseBody KeyValueVO getPmDeptStatusIdsByUserIds(@RequestBody Map<String,String> inputMap,HttpServletRequest request) {
	    	Long userId =null;
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
				return null;
			}
	       return pmRequestDetailsService.getPmDeptStatusIdsByUserIdsLst(userId,"true");
	    }
	    

	    @RequestMapping(value ="/updatePititionStatusDetails",method = RequestMethod.POST)
	    public @ResponseBody ResultStatus updatePetitionsStatusDetails(@RequestBody Map<String,String> inputMap,HttpServletRequest request) {
	    	Long userId =null;
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
				return null;
			}
	       return pmRequestDetailsService.updatePetitionsStatusDetails(userId,inputMap.get("petitionIdsList"),inputMap.get("remark"),Long.valueOf(inputMap.get("statusId")));
	    }
	    
	    @RequestMapping(value ="/getLeadWiseOverviewDetails",method = RequestMethod.POST)
	    public @ResponseBody List<RepresenteeViewVO> getLeadWiseOverviewDetails(@RequestBody Map<String,String> inputMap,HttpServletRequest request) {
	    	Long userId =null;
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
				return null;
			}
	       return pmRequestDetailsService.getLeadWiseOverviewDetails(userId,inputMap.get("fromDate"),inputMap.get("toDate"));
	    }
	    
	    @RequestMapping(value ="/updatePetitionStatusDetails",method = RequestMethod.POST)
	    public @ResponseBody ResultStatus updatePetitionsStatusDetails(@RequestBody PetitionTrackingVO inputVO,HttpServletRequest request) {
	    Long userId =null;
	    HttpSession session=request.getSession();
	    UserVO userVO = (UserVO) session.getAttribute("USER"); 
	    
	    if(userVO != null){
	    userId = userVO.getUserId();
	    }else{
	    	return null;
	    }
	    	return pmRequestDetailsService.updatePetitionsStatusDetails(userId,inputVO.getPetitionIdsList(),inputVO.getSubworkIdsList(),inputVO.getRemarks(),inputVO.getStatusId());
	    }
	    
	    @RequestMapping(value ="/getLoginUserAccessSubDeptDesignationDetail",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getLoginUserAccessSubDeptDesignationDetail(@RequestBody PetitionTrackingVO  dataVo,HttpServletRequest request ){
		    Long userId =null;
		    HttpSession session=request.getSession();
		    UserVO userVO = (UserVO) session.getAttribute("USER"); 
		    
		    if(userVO != null){
		    userId = userVO.getUserId();
		    }else{
		    	return null;
		    }
		    return pmRequestDetailsService.getLoginUserAccessSubDeptDesignationDetail(dataVo.getDeptIdsList(),userId);
	    }
	    
	    @RequestMapping(value ="/getDeptDesignationOfficerDetail",method = RequestMethod.POST)
	    public @ResponseBody List<KeyValueVO> getDeptDesignationOfficerDetail(@RequestBody Map<String,String> inputMap,HttpServletRequest request ){
		    Long userId =null;
		    HttpSession session=request.getSession();
		    UserVO userVO = (UserVO) session.getAttribute("USER"); 
		    
		    if(userVO != null){
		    userId = userVO.getUserId();
		    }else{
		    	return null;
		    }
		    return pmRequestDetailsService.getDeptDesignationOfficerDetail(Long.valueOf(inputMap.get("deptDesignationId")) , userId);
	    }
	    @RequestMapping(value ="/generateCoveringLetterForPetition",method = RequestMethod.POST)
	    public @ResponseBody ResultStatus generateCoveringLetterForPetition(@RequestBody InputVO inputVO,HttpServletRequest request ) {
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			Long userId =null;
			if(userVO != null){
				userId = userVO.getUserId();
				inputVO.setBlockLevelId(userId);
			}else{
		    	return null;
		    }
			List<Long> deptIds = null;
			KeyValueVO deptVO = pmRequestDetailsService.getDeptIdsListBYUserIds(userId);
			// deptIds = deptVO.getDeptIdsList();
			if(deptVO != null){
			 inputVO.setDeptIdsList(deptVO.getDeptIdsList());
			 inputVO.setDisplayType(deptVO.getDesignation());
			 if(inputVO.getDesignationIds() == null){
				 inputVO.setDesignationIds(new ArrayList<Long>());
			 }
			 if(inputVO.getAssetTypeList() == null){
				 inputVO.setAssetTypeList(new ArrayList<String>());
			 }
			 inputVO.getAssetTypeList().add(deptVO.getDesignation());
			 inputVO.getDesignationIds().add(deptVO.getDesignationId());
			 inputVO.setDeptCode(deptVO.getDeptName());
			}
	    	return pmRequestDetailsService.generateCoveringLetterForPetition(inputVO);
	    }
	    
	    @RequestMapping(value ="/endorsingSubWorksAndAssigningToOfficer",method = RequestMethod.POST)
	    public @ResponseBody ResultStatus endorsingSubWorksAndAssigningToOfficer(@ModelAttribute RepresenteeViewVO inputVO,HttpServletRequest request ) {
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			Long userId =null;
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
		    	return null;
		    }
			inputVO.setId(userId);
	    	return pmRequestDetailsService.endorsingSubWorksAndAssigningToOfficer(inputVO);
	    }
	    
	    @RequestMapping(value ="/getReferralWiseOverviewDetails",method = RequestMethod.POST)
	    public @ResponseBody RepresenteeViewVO getReferralWiseOverviewDetails(@RequestBody InputVO inputVO ,HttpServletRequest request) {
	    	Long userId =null;
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			
			if(userVO != null){
				userId = userVO.getUserId();
				inputVO.setLocationId(userId);
			}else{
				return null;
			}
	    	return pmRequestDetailsService.getReferralWiseOverviewDetails(inputVO);
	    }
	    @RequestMapping(value ="/getPetitionTrackingHistoryDetails",method = RequestMethod.POST)
	    public @ResponseBody List<PetitionHistoryVO> getPetitionTrackingHistoryDetails(@RequestBody PetitionTrackingVO  dataVo,HttpServletRequest request){
	    	return pmRequestDetailsService.getPetitionTrackingHistoryDetails(dataVo);
	    	
	    }
	    @RequestMapping(value ="/getBriefLeads",method = RequestMethod.POST)
	    public @ResponseBody List<RepresenteeViewVO> getBriefLeads(HttpServletRequest request ) {
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			Long userId =null;
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
				return null;
			}
			List<Long> deptIds = null;
			KeyValueVO deptVO = pmRequestDetailsService.getDeptIdsListBYUserIds(userId);
			deptIds = deptVO.getDeptIdsList();
		
	    	return pmRequestDetailsService.getBriefLeads(userId,deptIds);
	    }
	    @RequestMapping(value ="/userIdsByEntitlementsLogin",method = RequestMethod.POST)
	    public @ResponseBody List<MenuVO> userIdsByEntitlementsLogin(@RequestBody Map<String,String> inputMap ,HttpServletRequest request) {
	    	Long userId =null;
	    	HttpSession session=request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("USER"); 
			
			if(userVO != null){
				userId = userVO.getUserId();
			}else{
				return null;
			}
	       return pmRequestDetailsService.userIdsByEntitlementsLogin(userId);
	    }
	    
	    @RequestMapping(value ="/getPetitionAndWorkWiseHistoryDetails",method = RequestMethod.POST)
	    public @ResponseBody PetitionHistoryVO getPetitionAndWorkWiseHistoryDetails(@RequestBody PetitionTrackingVO  dataVo,HttpServletRequest request){
	    	return pmRequestDetailsService.getPetitionAndWorkWiseHistoryDetails(dataVo);
	    	
	    }
}
