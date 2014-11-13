package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreBasicInformationVO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;
import com.itgrids.partyanalyst.dto.WSResultVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class CadreDashBoardAction implements ServletRequestAware {

	private static Logger LOG = Logger.getLogger(CadreDashBoardAction.class);
	
	private HttpServletRequest request;
	
	private ICadreDashBoardService cadreDashBoardService;
	private List<CadreRegisterInfo> result;
	private CadreRegisterInfo info;
	private EntitlementsHelper entitlementsHelper;
	private List<CadreRegisterInfo> resultList;
	private JSONObject jObj;
	private String task;
	private CadreRegisterInfo resultVO;
	private List<CadreBasicInformationVO> usersList;
	private String getState;
	private ResultStatus resultStatus;
	private List<SurveyTransactionVO> surveyTransactionVOList = new ArrayList<SurveyTransactionVO>();
	private List<SelectOptionVO> surveyUsersList = new ArrayList<SelectOptionVO>();
	private WSResultVO wsResultVO;
	
	public List<SurveyTransactionVO> getSurveyTransactionVOList() {
		return surveyTransactionVOList;
	}

	public void setSurveyTransactionVOList(
			List<SurveyTransactionVO> surveyTransactionVOList) {
		this.surveyTransactionVOList = surveyTransactionVOList;
	}

	public List<SelectOptionVO> getSurveyUsersList() {
		return surveyUsersList;
	}

	public void setSurveyUsersList(List<SelectOptionVO> surveyUsersList) {
		this.surveyUsersList = surveyUsersList;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<CadreBasicInformationVO> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<CadreBasicInformationVO> usersList) {
		this.usersList = usersList;
	}

	public String getGetState() {
		return getState;
	}

	public void setGetState(String getState) {
		this.getState = getState;
	}

	public CadreRegisterInfo getResultVO() {
		return resultVO;
	}

	public void setResultVO(CadreRegisterInfo resultVO) {
		this.resultVO = resultVO;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	
	public List<CadreRegisterInfo> getResultList() {
		return resultList;
	}

	public void setResultList(List<CadreRegisterInfo> resultList) {
		this.resultList = resultList;
	}

	public ICadreDashBoardService getCadreDashBoardService() {
		return cadreDashBoardService;
	}

	public void setCadreDashBoardService(
			ICadreDashBoardService cadreDashBoardService) {
		this.cadreDashBoardService = cadreDashBoardService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<CadreRegisterInfo> getResult() {
		return result;
	}

	public void setResult(List<CadreRegisterInfo> result) {
		this.result = result;
	}

	public CadreRegisterInfo getInfo() {
		return info;
	}

	public void setInfo(CadreRegisterInfo info) {
		this.info = info;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
    
	public WSResultVO getWsResultVO() {
		return wsResultVO;
	}

	public void setWsResultVO(WSResultVO wsResultVO) {
		this.wsResultVO = wsResultVO;
	}

	public String execute(){
		
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADREDASHBOARD")){
			noaccess = true ;
		}
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		
		if(regVO.getAccessType()!=null && regVO.getAccessValue()!=null){
			getState = cadreDashBoardService.getStateBasedOnLocation(regVO.getAccessType(), regVO.getAccessValue());
		}
		
		if(noaccess){
			return "error";
		}
		return Action.SUCCESS;
	}
	
	
	
	public String getCadreRegistrationReport(){
		
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		
		if(regVO == null)
			return Action.INPUT;
		
		if(!entitlementsHelper.checkForEntitlementToViewReport(regVO,IConstants.TDP_CADRE_2014_ADMIN))
			return Action.ERROR;
		
		if(regVO.getAccessType()!=null && regVO.getAccessValue()!=null)
			getState = cadreDashBoardService.getStateBasedOnLocation(regVO.getAccessType(), regVO.getAccessValue());
		
			//wsResultVO = cadreDashBoardService.getAllParliamentsForAssembly();
		
		return Action.SUCCESS;
	}
	
	public String getConstituencyWiseAgeGenderCasteCount(){
		  try{
			  RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
				boolean noaccess = false;
				if(regVO==null){
					return "input";
				}if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADREDASHBOARD")){
					noaccess = true ;
					
				}
				if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
					noaccess = false;
				}
				if(noaccess){
					return "input";
				}
			  jObj = new JSONObject(getTask());				
			  Long constituencyId = jObj.getLong("constituencyId");	
						  
			  resultList = cadreDashBoardService.getDetailsForConstituency(constituencyId);
			  
		  }catch(Exception e){
			  LOG.error("Exception rised in getConstituencyWiseAgeGenderCasteCount ",e);
		  }
			return Action.SUCCESS;
	}
	
	public String getDistrictWiseAgeGenderCasteCount(){
		  try{
			  jObj = new JSONObject(getTask());				
			  Long districtId = jObj.getLong("districtId");	
			 
			  resultList = cadreDashBoardService.getDetailsForDistricts(districtId);
		  }catch(Exception e){
			  LOG.error("Exception rised in getDistrictWiseAgeGenderCasteCount ",e);
		  }
			return Action.SUCCESS;
	}
  
	public String getCadreDashBoardBasicInfo(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			boolean noaccess = false;
			if(regVO==null){
				return "input";
			}if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADREDASHBOARD")){
				noaccess = true ;
				
			}
			if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
				noaccess = false;
			}
			if(noaccess){
				return "input";
			}
			String accessType = regVO.getAccessType();
			Long accessValue = Long.valueOf(regVO.getAccessValue());
			String task = request.getParameter("task");
			if(task.equalsIgnoreCase("basicInfo")){
		       result = cadreDashBoardService.getDashBoardBasicInfo(accessType,accessValue);
			}else if(task.equalsIgnoreCase("recentlyRegistered")){
				 result = cadreDashBoardService.getRecentlyRegisteredCadresInfo(Integer.parseInt(request.getParameter("startIndex")), Integer.parseInt(request.getParameter("maxIndex")),accessType,accessValue);
				// result = cadreDashBoardService.getRecentlyRegisteredCadresInfo();
			}else if(task.equalsIgnoreCase("assemblyWise")){
				result = cadreDashBoardService.getAssemblyWiseCompletedPercentage(Long.parseLong(request.getParameter("assemblyId")),Long.parseLong(request.getParameter("stateId")), accessType, regVO.getAccessValue(),request.getParameter("percType"));
			}else if(task.equalsIgnoreCase("districtWise")){
				result = cadreDashBoardService.getDistrictWiseCompletedPercentage(Long.parseLong(request.getParameter("districtId")),Long.parseLong(request.getParameter("stateId")), accessType, regVO.getAccessValue(),request.getParameter("percType"));
			}else if(task.equalsIgnoreCase("workStartedConstituency")){
				result = cadreDashBoardService.getWorkStartedConstituencyCount(accessType,accessValue);
			}else if(task.equalsIgnoreCase("candidateDataCollectionInfo")){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				List<Long> locationIds = getIds(request.getParameter("locationId").trim());
				
				if(request.getParameter("sourceType").equalsIgnoreCase("ONLINE"))
				result = cadreDashBoardService.getCandidateDataCollectionInfoForOnlineUsers(Long.parseLong(request.getParameter("locationType")),locationIds,sdf.parse(request.getParameter("fromDate")),sdf.parse(request.getParameter("toDate")),request.getParameter("sourceType"));
				else
				result = cadreDashBoardService.getCandidateDataCollectionInfo(Long.parseLong(request.getParameter("locationType")),locationIds,sdf.parse(request.getParameter("fromDate")),sdf.parse(request.getParameter("toDate")),request.getParameter("sourceType"));
				


			}else if(task.equalsIgnoreCase("slowPerformanceUsers")){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				List<Long> locationIds = getIds(request.getParameter("locationId").trim());
				result = cadreDashBoardService.getSlowUserDetails(Long.parseLong(request.getParameter("locationType")),locationIds,sdf.parse(request.getParameter("fromDate")),sdf.parse(request.getParameter("toDate")),Long.parseLong(request.getParameter("targetRecords")));
			}
		}catch(Exception e){
			LOG.error("Exception rised in getCadreDashBoardBasicInfo ",e);
	}
		return Action.SUCCESS;
	}
	
	public String getWorkingMembersInfo(){
	  try{
		  RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			boolean noaccess = false;
			if(regVO==null){
				return "input";
			}if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADREDASHBOARD")){
				noaccess = true ;
				
			}
			if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
				noaccess = false;
			}
			if(noaccess){
				return "input";
			}
		String task = request.getParameter("task");
		if(task.equalsIgnoreCase("workingCount")){
			info = cadreDashBoardService.getWorkingMembersInfo(request.getParameter("hours"),regVO.getAccessType(),Long.valueOf(regVO.getAccessValue()));
			//info = cadreDashBoardService.getWorkingMembersDetails(request.getParameter("hours"));
		}
	  }catch(Exception e){
		  LOG.error("Exception rised in getWorkingMembersInfo ",e);
	  }
		return Action.SUCCESS;
	}
    
	public String getLocationWiseRegistrationInfo(){
		try{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
		return "input";
		}if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADREDASHBOARD")){
		noaccess = true ;

		}
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
		noaccess = false;
		}
		if(noaccess){
		return "input";
		}
		Long userCountValue=null;
		String countValue=request.getParameter("userCountValue");
		if( countValue!= null && countValue!=""){
			userCountValue=Long.parseLong(countValue);
		}
		

		String task = request.getParameter("task");
		if(task.equalsIgnoreCase("assemblyInfo")){
		result = cadreDashBoardService.getLocationWiseRegistrationInfo(getIds(request.getParameter("ids")),"assembly",request.getParameter("fromDate"),request.getParameter("toDate"),true,userCountValue);
		}else if(task.equalsIgnoreCase("districtInfo")){
		result = cadreDashBoardService.getLocationWiseRegistrationInfo(getIds(request.getParameter("ids")),"district",request.getParameter("fromDate"),request.getParameter("toDate"),true,userCountValue);
		}else if(task.equalsIgnoreCase("panchayatInfo")){
		result = cadreDashBoardService.getLocationWiseRegistrationInfo(getIds(request.getParameter("ids")),"panchayat",request.getParameter("fromDate"),request.getParameter("toDate"),true,userCountValue);
		}else if(task.equalsIgnoreCase("boothInfo")){
		result = cadreDashBoardService.getLocationWiseRegistrationInfo(getIds(request.getParameter("ids")),"booth",request.getParameter("fromDate"),request.getParameter("toDate"),true,userCountValue);
		}else if(task.equalsIgnoreCase("mandalInfo")){
		result = cadreDashBoardService.getLocationWiseRegistrationInfo(getIds(request.getParameter("ids")),"mandal",request.getParameter("fromDate"),request.getParameter("toDate"),true,userCountValue);
		}else if(task.equalsIgnoreCase("stateInfo")){
		result = cadreDashBoardService.getStateWiseRegistrationInfo(getIds(request.getParameter("ids")),request.getParameter("fromDate"),request.getParameter("toDate"),userCountValue);
		}else if(task.equalsIgnoreCase("boothNames")){
		result = cadreDashBoardService.getBoothsInConstituencies(Long.parseLong(request.getParameter("constituencyId")));
		}else if(task.equalsIgnoreCase("panchayatNames")){
		result = cadreDashBoardService.getPanchayatsInConstituencies(Long.parseLong(request.getParameter("constituencyId")));
		}else if(task.equalsIgnoreCase("assemblyNames")){
		result = cadreDashBoardService.getAssemblyConstituencies(request.getParameter("type"));
		}
		}catch(Exception e){
		LOG.error("Exception rised in getLocationWiseRegistrationInfo ",e);
		}
		return Action.SUCCESS;
		}

	
	public List<Long> getIds(String idsString){
		List<Long> ids = new ArrayList<Long>();
		String[] idsArray = idsString.split(",");
		
		for(String id:idsArray){
			ids.add(Long.valueOf(id.trim()));
		}
		
		return ids;
	}
	
	public String getRepInfo(){
		try{
			 RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
				boolean noaccess = false;
				if(regVO==null){
					return "input";
				}if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADREDASHBOARD")){
					noaccess = true ;
					
				}
				if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
					noaccess = false;
				}
				if(noaccess){
					return "input";
				}
			String task = request.getParameter("task");
			if(task.equalsIgnoreCase("castGroupConsti")){
			    resultList =  cadreDashBoardService.getCastGroupWiseCadreCount(Long.parseLong(request.getParameter("id")),"constituency");
			}else if(task.equalsIgnoreCase("castGroupDist")){
			    resultList =  cadreDashBoardService.getCastGroupWiseCadreCount(Long.parseLong(request.getParameter("id")),"district");
			}else if(task.equalsIgnoreCase("ageConsti")){
				resultList =  cadreDashBoardService.getConstituencyWiseAgeRangeCount(Long.parseLong(request.getParameter("id")));
			}else if(task.equalsIgnoreCase("genderConsti")){
				resultList =  cadreDashBoardService.getConstituencyWiseGenderCadreCount(Long.parseLong(request.getParameter("id")));
			}else if(task.equalsIgnoreCase("casteConsti")){
				resultList =  cadreDashBoardService.getConstituencyWiseCastCadreCount(Long.parseLong(request.getParameter("id")));
			}else if(task.equalsIgnoreCase("ageDistrict")){
				resultList =  cadreDashBoardService.getDistrictWiseAgeRangeCount(Long.parseLong(request.getParameter("id")));
			}else if(task.equalsIgnoreCase("gendDistrict")){
				resultList =  cadreDashBoardService.getDistrictWiseGenderCadreCount(Long.parseLong(request.getParameter("id")));
			}else if(task.equalsIgnoreCase("castDistrict")){
				resultList =  cadreDashBoardService.getDistrictWiseCastCadreCount(Long.parseLong(request.getParameter("id")));
			}
		 }catch(Exception e){
			  LOG.error("Exception rised in getRepInfo ",e);
		  }
		return Action.SUCCESS;
	}
	public String getRegisteredDetailsByLocation(){
		try{
			int startIndex = Integer.parseInt(request.getParameter("startIndex").trim());
			int maxIndex = Integer.parseInt(request.getParameter("maxIndex").trim());
			String orderBy = request.getParameter("sort").trim();
			String orderType = request.getParameter("dir").trim();
			List<Long> locationIds = getIds(request.getParameter("locationIds").trim());
			resultVO = cadreDashBoardService.getRegisteredDetailsByLocation(request.getParameter("locationType"),locationIds,startIndex,maxIndex,orderBy,orderType);
		}catch(Exception e){
			LOG.error("Exception rised in getRegisteredDetailsByLocation ",e);
		}
		return Action.SUCCESS;
	}
	public String getDataForSubLocations(){
		try{
			Long fromLocationId = Long.parseLong(request.getParameter("fromLocationId").trim());
			Long constituencyId = Long.parseLong(request.getParameter("constituencyId").trim());
			String fromLocation = request.getParameter("fromLocation").trim();
			String toLocation = request.getParameter("toLocation").trim();
			String fromDateStr = request.getParameter("fromDateStr").trim();
			String toDateStr = request.getParameter("toDateStr").trim();
			result = cadreDashBoardService.getDataForSubLocations(fromLocation,fromLocationId,toLocation,fromDateStr,toDateStr,constituencyId);
		}catch(Exception e){
			LOG.error("Exception rised in getDataForSubLocations ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getRegisteredInfo(){
		try{
			Long locationId = Long.parseLong(request.getParameter("locationId").trim());
			String locationType = request.getParameter("locationType").trim();
			int startIndex = Integer.parseInt(request.getParameter("startIndex").trim());
			int maxIndex = Integer.parseInt(request.getParameter("maxIndex").trim());
		
			info = cadreDashBoardService.getRegisteredInfo(locationId,locationType,startIndex,maxIndex);
		}catch(Exception e){
			LOG.error("Exception rised in getRegisteredInfo ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getConstituencySurveyUsers()
	{
		try{
			jObj = new JSONObject(getTask());
			usersList = cadreDashBoardService.getConstituencySurveyUsers(jObj.getLong("constituencyId"));
		}
		catch (Exception e) {
			LOG.error("Exception rised in getCadreSurveyUsers ",e);
		}
		return Action.SUCCESS;
	}
	
	public String saveCadreSurveyUserAssignInfo()
	{
		try{
			jObj = new JSONObject(getTask());
			JSONArray jsonArray = jObj.getJSONArray("inputArr");
			for(int i=0;i<jsonArray.length();i++)
			{
				if(!jsonArray.isNull(i))
				{
					JSONObject jSONObject= jsonArray.getJSONObject(i);
					CadreRegisterInfo vo = new CadreRegisterInfo();
					vo.setId(jSONObject.getLong("userId"));
					vo.setName(jSONObject.getString("name"));
					vo.setNumber(jSONObject.getString("mobileNo"));
					vo.setFromDate(jSONObject.getString("startDate"));
					resultStatus = cadreDashBoardService.saveCadreSurveyUserAssignInfo(vo);
				}
			}
			
		}
		catch (Exception e) {
			LOG.error("Exception rised in getCadreSurveyUsers ",e);
		}
		return Action.SUCCESS;
	}
	public String getAssignedUsersForCadresurveyUser()
	{
		try{
			jObj = new JSONObject(getTask());
			resultList = cadreDashBoardService.getAssignedUsersForCadresurveyUser(jObj.getLong("constituencyId"),jObj.getLong("userId"));
		}
		catch (Exception e) {
			LOG.error("Exception rised in getCadreSurveyUsers ",e);
		}
		return Action.SUCCESS;
	}
	
	public String assignUserExecute(){
		
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		return Action.SUCCESS;
	}
	
	public String getLocationswiseUsersList()
	{
		try{
			 jObj = new JSONObject(getTask());				
			 
			String usersType = jObj.getString("usersType");
			String areaType = jObj.getString("areaType");
			Long stateTypeId = jObj.getLong("stateTypeId");
			String fromdateStr = jObj.getString("fromdateStr");
			String todateStr = jObj.getString("todateStr");

			surveyTransactionVOList = cadreDashBoardService.getLocationswiseUsersList(usersType,areaType,stateTypeId,fromdateStr,todateStr);
			
		}catch(Exception e){
			LOG.error("Exception rised in getLocationswiseUsersList ",e);
		}
		return Action.SUCCESS;
	}
	
	public String gettingUserDetailsByLocation()
	{
		try{
			 jObj = new JSONObject(getTask());				
			 
			String location = jObj.getString("location");
			Long locationId = jObj.getLong("locationId");
			String type = jObj.getString("type");
			String dateString = jObj.getString("dateString");
			
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			Date date=sdf.parse(dateString);
            wsResultVO = cadreDashBoardService.gettingUserDetailsByLocation(location,locationId,type,date);
			
		}catch(Exception e){
			LOG.error("Exception rised in gettingUserDetailsByLocation ",e);
		}
		return Action.SUCCESS;
	
	}
	
}
