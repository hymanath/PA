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
import com.itgrids.partyanalyst.dto.CadreDashboardVO;
import com.itgrids.partyanalyst.dto.CadreDataSourceTypeVO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;
import com.itgrids.partyanalyst.dto.WSResultVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.service.IMahaNaduService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
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
	private ILoginService loginService;
	private List<GenericVO> genericVOList;
	private SurveyTransactionVO surveyTransactionVO;
	private Long stateId;
	private CadreManagementService cadreManagementService;
	private IMahaNaduService mahaNaduService;
	private List<CadreDashboardVO> cadreDashboardVOList;
	private List<CadreDataSourceTypeVO> cadreDataSourceTypeVOList;
	private List<IdAndNameVO> idAndNameVOList;
	
	
	public List<IdAndNameVO> getIdAndNameVOList() {
		return idAndNameVOList;
	}

	public void setIdAndNameVOList(List<IdAndNameVO> idAndNameVOList) {
		this.idAndNameVOList = idAndNameVOList;
	}

	public List<CadreDataSourceTypeVO> getCadreDataSourceTypeVOList() {
		return cadreDataSourceTypeVOList;
	}

	public void setCadreDataSourceTypeVOList(
			List<CadreDataSourceTypeVO> cadreDataSourceTypeVOList) {
		this.cadreDataSourceTypeVOList = cadreDataSourceTypeVOList;
	}

	public List<CadreDashboardVO> getCadreDashboardVOList() {
		return cadreDashboardVOList;
	}

	public void setCadreDashboardVOList(List<CadreDashboardVO> cadreDashboardVOList) {
		this.cadreDashboardVOList = cadreDashboardVOList;
	}

	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public SurveyTransactionVO getSurveyTransactionVO() {
		return surveyTransactionVO;
	}

	public void setSurveyTransactionVO(SurveyTransactionVO surveyTransactionVO) {
		this.surveyTransactionVO = surveyTransactionVO;
	}

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

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
    
	public List<GenericVO> getGenericVOList() {
		return genericVOList;
	}

	public void setGenericVOList(List<GenericVO> genericVOList) {
		this.genericVOList = genericVOList;
	}

	public void setMahaNaduService(IMahaNaduService mahaNaduService) {
		this.mahaNaduService = mahaNaduService;
	}

	public String execute(){
		
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("CADREDASHBOARD".trim()))){
				noaccess = true ;
			}
		/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADREDASHBOARD")){
			noaccess = true ;
		}*/
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		
		if(regVO.getAccessType()!=null && regVO.getAccessValue()!=null){
			getState = cadreDashBoardService.getStateBasedOnLocation(regVO.getAccessType(), regVO.getAccessValue());
		}
		
		if(noaccess){
			return "error";
		}
	}	
		return Action.SUCCESS;
	}
	
	
	
	public String getCadreRegistrationReport(){
		
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		
		if(regVO == null)
			return Action.INPUT;
		
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains(IConstants.TDP_CADRE_2014_ADMIN) || entitlements.contains("CADRE_2014_MP"))){
				return Action.ERROR;
			}
		/*if(!(entitlementsHelper.checkForEntitlementToViewReport(regVO,IConstants.TDP_CADRE_2014_ADMIN) || entitlementsHelper.checkForEntitlementToViewReport(regVO,"CADRE_2014_MP")))
			return Action.ERROR;
		*/
		if(regVO.getAccessType()!=null && regVO.getAccessValue()!=null)
			getState = cadreDashBoardService.getStateBasedOnLocation(regVO.getAccessType(), regVO.getAccessValue());
		
			//wsResultVO = cadreDashBoardService.getAllParliamentsForAssembly();
		}
		return Action.SUCCESS;
	}
	
	public String getConstituencyWiseAgeGenderCasteCount(){
		  try{
			  RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
				boolean noaccess = false;
				if(regVO==null){
					return "input";
				}
				List<String> entitlements = null;
				if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
					entitlements = regVO.getEntitlements();
					if(!(entitlements.contains("CADREDASHBOARD".trim()))){
						noaccess = true ;
					}
				/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADREDASHBOARD")){
					noaccess = true ;
					
				}*/
				if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
					noaccess = false;
				}
				if(noaccess){
					return "input";
				}
			  jObj = new JSONObject(getTask());				
			  Long constituencyId = jObj.getLong("constituencyId");	
						  
			  resultList = cadreDashBoardService.getDetailsForConstituency(constituencyId);
			  
		  }
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
			}
			List<String> entitlements = null;
			if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
				entitlements = regVO.getEntitlements();
				if(!(entitlements.contains("CADREDASHBOARD".trim()))){
					noaccess = true ;
				}
			/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADREDASHBOARD")){
				noaccess = true ;
				
			}*/
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
				result = cadreDashBoardService.getCandidateDataCollectionInfoForOnlineUsers(Long.parseLong(request.getParameter("locationType")),locationIds,sdf.parse(request.getParameter("fromDate")),sdf.parse(request.getParameter("toDate")),request.getParameter("sourceType"),Long.parseLong(request.getParameter("stateTypeId")));
				else
				result = cadreDashBoardService.getCandidateDataCollectionInfo(Long.parseLong(request.getParameter("locationType")),locationIds,sdf.parse(request.getParameter("fromDate")),sdf.parse(request.getParameter("toDate")),request.getParameter("sourceType"));
				


			}else if(task.equalsIgnoreCase("slowPerformanceUsers")){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				List<Long> locationIds = getIds(request.getParameter("locationId").trim());
				result = cadreDashBoardService.getSlowUserDetails(Long.parseLong(request.getParameter("locationType")),locationIds,sdf.parse(request.getParameter("fromDate")),sdf.parse(request.getParameter("toDate")),Long.parseLong(request.getParameter("targetRecords")));
			}
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
			}
			List<String> entitlements = null;
			if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
				entitlements = regVO.getEntitlements();
				if(!(entitlements.contains("CADREDASHBOARD".trim()))){
					noaccess = true ;
				}
			/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADREDASHBOARD")){
				noaccess = true ;
				
			}*/
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
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("CADREDASHBOARD".trim()))){
				noaccess = true ;
			}
		/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADREDASHBOARD")){
		noaccess = true ;

		}*/
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
				}
				List<String> entitlements = null;
				if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
					entitlements = regVO.getEntitlements();
					if(!(entitlements.contains("CADREDASHBOARD".trim()))){
						noaccess = true ;
					}
				/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADREDASHBOARD")){
					noaccess = true ;
					
				}*/
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
			}else if(task.equalsIgnoreCase("ageDistrictByAccess")){
				resultList =  cadreDashBoardService.getDistrictWiseAgeRangeCountByAccess(Long.parseLong(request.getParameter("id")),regVO.getAccessType(),regVO.getAccessValue());
			}else if(task.equalsIgnoreCase("castGroupDistByAccess")){
			    resultList =  cadreDashBoardService.getCastGroupWiseCadreCountByAccess(Long.parseLong(request.getParameter("id")),regVO.getAccessType(),regVO.getAccessValue());
			}else if(task.equalsIgnoreCase("castDistrictByAccess")){
				resultList =  cadreDashBoardService.getDistrictWiseCastCadreCountByAccess(Long.parseLong(request.getParameter("id")),regVO.getAccessType(),regVO.getAccessValue());
			}else if(task.equalsIgnoreCase("gendDistrictByAccess")){
				resultList =  cadreDashBoardService.getDistrictWiseGenderCadreCountByAccess(Long.parseLong(request.getParameter("id")),regVO.getAccessType(),regVO.getAccessValue());
			}
		}
		 }catch(Exception e){
			  LOG.error("Exception rised in getRepInfo ",e);
		  }
		return Action.SUCCESS;
	}
	
	
	public String getRepInfoForcastGroupDist(){
		try{
			 RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
				boolean noaccess = false;
				if(regVO==null){
					return "input";
				}
				List<String> entitlements = null;
				if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
					entitlements = regVO.getEntitlements();
					if(!(entitlements.contains("CADREDASHBOARD".trim()))){
						noaccess = true ;
					}
				/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADREDASHBOARD")){
					noaccess = true ;
					
				}*/
				if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
					noaccess = false;
				}
				if(noaccess){
					return "input";
				}
			String task = request.getParameter("task");
			
		
		   if(task.equalsIgnoreCase("CadrecastGroupDist")){
			   resultList =  cadreDashBoardService.getCastGroupWiseCadreCount(Long.parseLong(request.getParameter("id")),"district");
			}
		}
		 }catch(Exception e){
			  LOG.error("Exception rised in getRepInfo ",e);
		  }
		return Action.SUCCESS;
	}
	
	public String getRepInfoForcastDistrict(){
		try{
			 RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
				boolean noaccess = false;
				if(regVO==null){
					return "input";
				}
				List<String> entitlements = null;
				if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
					entitlements = regVO.getEntitlements();
					if(!(entitlements.contains("CADREDASHBOARD".trim()))){
						noaccess = true ;
					}
				/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADREDASHBOARD")){
					noaccess = true ;
					
				}*/
				if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
					noaccess = false;
				}
				if(noaccess){
					return "input";
				}
			String task = request.getParameter("task");
			
		
		   if(task.equalsIgnoreCase("CadrecastDistrict")){
				resultList =  cadreDashBoardService.getDistrictWiseCastCadreCount(Long.parseLong(request.getParameter("id")));
			}
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
	
	public String getDaywiseWebUserDetails(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO != null)
			{
				 jObj = new JSONObject(getTask());	
				 Long userId = jObj.getLong("userId");
				 String FdateStr = jObj.getString("fromDate");
				 String TdateStr = jObj.getString("toDate");
				 Long memberTypeId = jObj.getLong("memberTypeId");
				 if(userId == 0L )
				 {
					 userId =  regVO.getRegistrationID();
				 }
				 
				 surveyTransactionVO = cadreDashBoardService.getDaywiseWebUserDetails(userId,FdateStr, TdateStr,memberTypeId);
			}
						
		}catch(Exception e){
			LOG.error("Exception rised in getDaywiseWebUserDetails ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getLocationNameByIdAndType(){
		try{
			
			 jObj = new JSONObject(getTask());				
			 String type = jObj.getString("usersType");
			 String value = jObj.getString("areaType");
		
				getState = loginService.getLocationNameByIdAndType(type,value);
						
		}catch(Exception e){
			LOG.error("Exception rised in getLocationNameByIdAndType ",e);
		}
		return Action.SUCCESS;
	}
	public String gettingInActiveUsersDetails()
	{
		try{
			
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			if(regVO != null)
			{
				String hoursCount=request.getParameter("hoursCount");
				genericVOList=cadreDashBoardService.getInactiveUsersListDetails(hoursCount,regVO.getAccessType(),regVO.getAccessValue());
			}
			 
			
		}catch(Exception e){
			LOG.error("Exception rised in getLocationNameByIdAndType ",e);
		}
		return Action.SUCCESS;
		
		
	}	
	public String updateTabAllocationDetails(){
		try{
			
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		
			if(regVO==null){
				return "SUCCESS";
			}
			 task = cadreDashBoardService.updateTabAllocationDetails(Long.valueOf(request.getParameter("authId")),request.getParameter("cause"),regVO.getRegistrationID());
						
		}catch(Exception e){
			LOG.error("Exception rised in updateTabAllocationDetails ",e);
		}
		return Action.SUCCESS;
	}
	
	public String updateMAhaTabAllocationDetails(){
		try{
			
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		
			if(regVO==null){
				return "SUCCESS";
			}
			 task = mahaNaduService.updateTabAllocationDetails(Long.valueOf(request.getParameter("authId")),request.getParameter("cause"),regVO.getRegistrationID());
						
		}catch(Exception e){
			LOG.error("Exception rised in updateTabAllocationDetails ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAuthDetails(){
		try{
			
			result = cadreDashBoardService.getAuthDetails(Long.valueOf(request.getParameter("id")),request.getParameter("variable"));
						
		}catch(Exception e){
			LOG.error("Exception rised in getAuthDetails ",e);
		}
		return Action.SUCCESS;
	}
	public String getMahaNaduAuthDetails(){
		try{
			
			result = mahaNaduService.getAuthDetails(Long.valueOf(request.getParameter("id")),request.getParameter("variable"));
						
		}catch(Exception e){
			LOG.error("Exception rised in getAuthDetails ",e);
		}
		return Action.SUCCESS;
	}
	public String  tabAllocationDetails(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("TABDEALLOCATIONALTER".trim()))){
				noaccess = true ;
			}
		/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"TABDEALLOCATIONALTER")){
			noaccess = true ;
		}*/
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		}
		return Action.SUCCESS;
	}
	public String  tabAllocationForMahanadu(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("MAHANADUTABALLOCATION".trim()))){
				noaccess = true ;
			}
		/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"MAHANADUTABALLOCATION")){
			noaccess = true ;
		}*/
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		}
		return Action.SUCCESS;
	}
	public String registerAllUsers(){
		RegistrationVO user = (RegistrationVO) request.getSession().getAttribute("USER");
		task = cadreDashBoardService.registerAllUsers(user);
		return Action.SUCCESS;
	}
	
	public String execute1()
	{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		return Action.SUCCESS;		
	}
	
	public String getHourWiseUserColletedInfo(){
			
	try{
	
		jObj = new JSONObject(getTask());				
		 
		String fromDateStr = jObj.getString("fromDate");
		String toDateStr = jObj.getString("toDate");
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		Date date=sdf.parse(fromDateStr);
		Date date1=sdf.parse(toDateStr);	
		
		result = cadreDashBoardService.getRegisteredCountByUserForHourWise(date,date1);
					
	}catch(Exception e){
		LOG.error("Exception raised in getHourWiseUserColletedInfo ",e);
	}
	return Action.SUCCESS;
	
	}
	
	public String partyDashBoard(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			List<String> entitlements = null;
			if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
				entitlements = regVO.getEntitlements();
				if(!(entitlements.contains("PARTYCADREDASHBOARD".trim()))){
					return "error";
				}
			/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"PARTYCADREDASHBOARD")){
				return "error";
			}
			*/
			return Action.SUCCESS;
			}
		}catch(Exception e){
			LOG.error("Exception raised in partyDashBoard ",e);
		}
		return "input";
	}
	
	public String getTotalRegisterCadreInfo(){
		try{
			info = cadreDashBoardService.getTotalRegisterCadreInfo();
		}catch(Exception e){
			LOG.error("Exception raised in getTotalRegisterCadreInfo ",e);
		}
		return Action.SUCCESS;
	}
	
	

	public String getGHMCRegisteredCountsDetails(){
		try{
			jObj = new JSONObject(getTask());			
			String countType= jObj.getString("type");
			result = cadreDashBoardService.getGHMCRegisteredCountDetails(countType);
		}catch(Exception e){
			LOG.error("Exception raised in getGHMCRegisteredCountsDetails ",e);
		}
		return Action.SUCCESS;
	}
	public String getStateName()
	{
		try{
			jObj = new JSONObject(getTask());
		
			getState = cadreManagementService.getStateName(jObj.getLong("stateId"));	
		}
		catch(Exception e)
		{
			LOG.error(e);
		}
		return Action.SUCCESS;
	}
	public String getDashBoardBasicInfo()
	{
		try{
			jObj = new JSONObject(getTask());
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			String accessType = regVO.getAccessType();
			Long accessValue = Long.valueOf(regVO.getAccessValue());
			if(jObj.getString("task").equalsIgnoreCase("basicRegistrationInfo")){
				resultVO = cadreDashBoardService.getDashBoardBasicRegistrationInfo(accessType,accessValue,jObj.getLong("stateId"));
				}
		}
		catch(Exception e)
		{
			LOG.error(e);
		}
		return Action.SUCCESS;
	}
	
	public String get2016LocationWiseRegisteredCountsForConstitunecy()
	{
		try{
			jObj = new JSONObject(getTask());
			String type = jObj.getString("type");
			Long locationScopeId = jObj.getLong("locationScopeId");
			String locationType = jObj.getString("locationType");
			Long districtId = jObj.getLong("districtId");
			cadreDashboardVOList = cadreDashBoardService.get2016LocationWiseRegisteredCountsForConstitunecy(type, locationScopeId, locationType,districtId,null);
		}
		catch(Exception e)
		{
			LOG.error("Exception raised in get2016LocationWiseRegisteredCountsForConstitunecy ",e);
		}
		return Action.SUCCESS;
	}
	public String get2016LocationWiseRegisteredCounts()
	{
		try{
			jObj = new JSONObject(getTask());
			String type = jObj.getString("type");
			Long locationScopeId = jObj.getLong("locationScopeId");
			String locationType = jObj.getString("locationType");
			
			cadreDashboardVOList = cadreDashBoardService.get2016LocationWiseRegisteredCounts(type, locationScopeId, locationType);
		}
		catch(Exception e)
		{
			LOG.error(e);
		}
		return Action.SUCCESS;
	}
		
	public String getDataSourceTypeWiseRegisteredDetails()
	{
		try{
			jObj = new JSONObject(getTask());
			
			cadreDataSourceTypeVOList = cadreDashBoardService.getDataSourceTypeWiseRegisteredDetails();
		}
		catch(Exception e)
		{
			LOG.error(e);
		}
		return Action.SUCCESS;
	}
	
	public String getDataSourceTypeWiseCountsByType()
	{
		try{
			jObj = new JSONObject(getTask());
			String type = jObj.getString("type");
			idAndNameVOList = cadreDashBoardService.getDataSourceTypeWiseCountsByType(type);
		}
		catch(Exception e)
		{
			LOG.error(e);
		}
		return Action.SUCCESS;
	}
	public String newCadreDashBoard2016(){
	    RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
	    boolean noaccess = false;
	    if(regVO==null){
	      return "input";
	    }
	    List<String> entitlements = null;
	    if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
	      entitlements = regVO.getEntitlements();
	      if(!(entitlements.contains("CADRE_REGISTRATION_2016_DASHBOARD".trim()) || entitlements.contains("CADRE_REGISTRATION_2016_DASHBOARD_ADMIN_ENTITLEMENT".trim()))){
	        noaccess = true ;
	      }
	    
	      if(noaccess){
	        return "error";
	      }
	    }
	    return Action.SUCCESS;
	  }
	public String cadreSurveyuserAssignDetails(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("CADRE_TAB_LOCKING_USER_ENTITLEMENT")) || entitlements.contains("CADRE_TAB_LOCKING_USER_ADMIN_ENTITLEMENT")){
				noaccess = true ;
			}
		
			if(noaccess){
				return "error";
			}
		}
		return Action.SUCCESS;
	}
}
