package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.dto.ToursInputVO;
import com.itgrids.partyanalyst.service.ICoreDashboardToursService;
import com.itgrids.partyanalyst.service.IToursService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ToursAction extends ActionSupport implements ServletRequestAware {

	   private final static Logger LOG = Logger.getLogger(ToursAction.class);
	   private HttpServletRequest request;
	   private HttpSession session;
	   private JSONObject jObj;
	   private String task;
	     
	   private IToursService toursService;
	   private ICoreDashboardToursService coreDashboardToursService;
	   private ToursInputVO toursInputVO;
	   private ResultStatus	resultStatus;
	   private InputStream	inputStream;
	   private ToursBasicVO resultVO;
	   private List<ToursBasicVO> resultList;
	   private String successMsg;
	   private List<List<ToursBasicVO>> listOfTourBasicVoList;
	   private List<ToursBasicVO> listOfTourBasicVo;
	   
	  
	   private  List<AddressVO> addressVOList;
	
	   
	   public List<AddressVO> getAddressVOList() {
		return addressVOList;
	     }
	public void setAddressVOList(List<AddressVO> addressVOList) {
		this.addressVOList = addressVOList;
	}
	
	   public JSONObject getjObj() {
		   return jObj;
	   }
	   public void setjObj(JSONObject jObj) {
		   this.jObj = jObj;
	   }
	   public String getTask() {
		   return task;
	   }
	   public void setTask(String task) {
		   this.task = task;
	   }
	   public void setServletRequest(HttpServletRequest request) {
		   this.request = request;    
	   }
	   public void setToursService(IToursService toursService) {
		   this.toursService = toursService;
	   }
	   public HttpServletRequest getRequest() {
		   return request;
	   }
	   public void setRequest(HttpServletRequest request) {
		   this.request = request;
	   }  
	   public HttpSession getSession() {
		   return session;
	   }
	   public void setSession(HttpSession session) {
		   this.session = session;
	   }
	   public ToursInputVO getToursInputVO() {
		   return toursInputVO;
	   }   
	   public void setToursInputVO(ToursInputVO toursInputVO) {
		   this.toursInputVO = toursInputVO;
	   }
	   public ResultStatus getResultStatus() {
		   return resultStatus;
	   }
	   public void setResultStatus(ResultStatus resultStatus) {
		   this.resultStatus = resultStatus;
	   }
	   public IToursService getToursService() {
		   return toursService;
	   }
	   public ToursBasicVO getResultVO() {
		   return resultVO;
	   }
	   public void setResultVO(ToursBasicVO resultVO) {
		   this.resultVO = resultVO;
	   }
	   public List<ToursBasicVO> getResultList() {
		   return resultList;
	   }
	   public void setResultList(List<ToursBasicVO> resultList) {
		   this.resultList = resultList;
	   }
	   public InputStream getInputStream() {
		return inputStream;
	   }
	   public void setInputStream(InputStream inputStream) {
		   this.inputStream = inputStream;
	   }  
	   
	   public String getSuccessMsg() {
		   return successMsg;
	   }
	   public void setSuccessMsg(String successMsg) {
		   this.successMsg = successMsg;
	   }
	   public ICoreDashboardToursService getCoreDashboardToursService() {
		   return coreDashboardToursService;
	   }
	   public void setCoreDashboardToursService(
			   ICoreDashboardToursService coreDashboardToursService) {
		   this.coreDashboardToursService = coreDashboardToursService;
	   }
	   public List<List<ToursBasicVO>> getListOfTourBasicVoList() {
		   return listOfTourBasicVoList;
	   }
	   public void setListOfTourBasicVoList(
			List<List<ToursBasicVO>> listOfTourBasicVoList) {
		   this.listOfTourBasicVoList = listOfTourBasicVoList;
	   }
	   
	   public List<ToursBasicVO> getListOfTourBasicVo() {
		   return listOfTourBasicVo;
	   }
	   public void setListOfTourBasicVo(List<ToursBasicVO> listOfTourBasicVo) {
		   this.listOfTourBasicVo = listOfTourBasicVo;
	   }
	   public static Logger getLog() {
		   return LOG;
	   }
	//Business method
	   public String execute(){
		   RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		    boolean noaccess = false;
		    if(regVO==null){
		      return "input";
		    }
		    List<String> entitlements = null;
		    if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
		      entitlements = regVO.getEntitlements();
		      if(!(entitlements.contains("TOUR_USER_ENTITLEMENT"))){//|| entitlements.contains("CADRE_TAB_LOCKING_USER_ADMIN_ENTITLEMENT")
		        noaccess = true ;
		      }
		      if(noaccess){
		        return "error";
		      }
		    }
		    return Action.SUCCESS;
	   }
		public String savingTourDtlsApplication(){
			try { 
				final HttpSession session = request.getSession();
				/*final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				if(user == null || user.getRegistrationID() == null){
					return ERROR;
				}*/
				  
				Map<File,String> mapfiles = new HashMap<File,String>();
				MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
				Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
				String fileUrl = "" ;
				List<String> filePaths = null;
				while(fileParams.hasMoreElements()){
					String key = fileParams.nextElement();
			   			
					File[] files = multiPartRequestWrapper.getFiles(key);
					filePaths = new ArrayList<String>();
					if(files != null && files.length > 0)
						for(File f : files){
							String[] extension  =multiPartRequestWrapper.getFileNames(key)[0].split("\\.");
							String ext = "";
							if(extension.length > 1){
								ext = extension[extension.length-1];
									mapfiles.put(f,ext);
								}
							}
				}  
			     
				resultStatus = toursService.saveTourDtls(toursInputVO,1l,mapfiles);
				if(resultStatus!=null){
					if(resultStatus.getResultCode() == 0){
						successMsg = resultStatus.getMessage();
					}else if(resultStatus.getResultCode() == 1){
						successMsg = resultStatus.getMessage();  
					}
				}
	        
			 } catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception raised at savingNominatedPostProfileApplication", e);
			}
			
			return Action.SUCCESS;
		}
	public String getDesigationList(){
		try {
			jObj = new JSONObject(getTask());
			resultList = toursService.getDesigationList();
		} catch (Exception e) {
			LOG.error("Exception raised at getDesigationList()  of ToursAction", e);
		}
		return Action.SUCCESS;
	}  
	public String getConstituenciesList(){
		try {
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			resultList = toursService.getConstituenciesList(stateId);
		} catch (Exception e) {
			LOG.error("Exception raised at getConstituenciesList()  of ToursAction", e);
		}
		return Action.SUCCESS;
	}
	public String getCandidateList(){
		try{
			jObj = new JSONObject(getTask());
			Long designationId = jObj.getLong("designationId");
			resultList = toursService.getCandidateList(designationId);
		}catch (Exception e) {
			LOG.error("Exception raised at getCandidateList()  of ToursAction", e);
		}
	    return Action.SUCCESS;
    }
    public String getCandiateDetails(){
 	   try{
 		   jObj = new JSONObject(getTask());
			   Long candidateId = jObj.getLong("candidateId");
			   resultVO = toursService.getCandiateDetails(candidateId);
 	   }catch(Exception e){
 		   LOG.error("Exception raised at getCandiateDetails()  of ToursAction", e);  
 	   }
 	   return Action.SUCCESS;
    }
    public String getSearchMembersDetails(){
 	   try{
 		   jObj = new JSONObject(getTask());
			   Long locationId = jObj.getLong("locationId");
			   String searchType = jObj.getString("searchType");
			   String searchValue = jObj.getString("searchValue");
			   Long designationId =jObj.getLong("designationId");
			   resultList = toursService.getSearchMembersDetails(locationId,searchType,searchValue,designationId);
 	   }catch(Exception e){
 		   LOG.error("Exception raised at getSearchMembersDetails()  of ToursAction", e);  
 	   }
 	   return Action.SUCCESS;
    }
    public String getToursDetailsOverview(){
 	   try{
 		   jObj = new JSONObject(getTask());
			   String fromDateStr = jObj.getString("fromDate");
			   String toDate = jObj.getString("toDate");
			   resultList = toursService.getToursDetailsOverview(fromDateStr,toDate);
 	   }catch(Exception e){
 		   LOG.error("Exception raised at getToursDetailsOverview()  of ToursAction", e);  
 	   }
 	   return Action.SUCCESS;
    }
	public String getDesignationDtls(){  
		try{
			jObj = new JSONObject(getTask());
			Long desigId = jObj.getLong("desigId");
			String startDateStr = jObj.getString("startDateStr");
			String endDateStr = jObj.getString("endDateStr");
			resultVO = toursService.getDesignationDtls(desigId,startDateStr,endDateStr);
		}catch(Exception e){
			e.printStackTrace();  
			LOG.error("Exception raised at getDesignationDtls()  of ToursAction", e);
		}
		return Action.SUCCESS;
	}
	public String getMemDtls(){
		try{
			jObj = new JSONObject(getTask());
			Long desigId = jObj.getLong("desigId");
			String startDateStr = jObj.getString("startDateStr");
			String endDateStr = jObj.getString("endDateStr");
			resultList = toursService.getMemDtls(desigId,startDateStr,endDateStr);
		}catch(Exception e){
			e.printStackTrace();  
			LOG.error("Exception raised at getDesignationDtls()  of ToursAction", e);
		}
		return Action.SUCCESS;
	}
	public String getUniqueMemDtls(){  
		try{
			jObj = new JSONObject(getTask());
			Long candidateDtlsId = jObj.getLong("candidateDtlsId");
			resultVO = toursService.getUniqueMemDtls(candidateDtlsId);
		}catch(Exception e){  
			e.printStackTrace();  
			LOG.error("Exception raised at getDesignationDtls()  of ToursAction", e);
		}
		return Action.SUCCESS;
	}
	public String updateTourDtlsApplication(){
		try { 
			final HttpSession session = request.getSession();
			/*final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}*/
			  
			Map<File,String> mapfiles = new HashMap<File,String>();
			MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
			Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
			String fileUrl = "" ;
			List<String> filePaths = null;  
			while(fileParams.hasMoreElements()){
				String key = fileParams.nextElement();
		   			
				File[] files = multiPartRequestWrapper.getFiles(key);
				filePaths = new ArrayList<String>();
				if(files != null && files.length > 0)
					for(File f : files){
						String[] extension  =multiPartRequestWrapper.getFileNames(key)[0].split("\\.");
						String ext = "";
						if(extension.length > 1){
							ext = extension[extension.length-1];
								mapfiles.put(f,ext);
							}
						}
			}  
		     
			resultStatus = toursService.updateTourDtls(toursInputVO,1l,mapfiles);
			if(resultStatus!=null){
				if(resultStatus.getResultCode() == 0){  
					successMsg = resultStatus.getMessage();
				}else if(resultStatus.getResultCode() == 1){
					successMsg = resultStatus.getMessage();  
				}
			}    
        
		 } catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised at savingNominatedPostProfileApplication", e);
		}
		
		return Action.SUCCESS;
	}
	public String getDesigWiseMemberDtls(){  
		try {
			LOG.info("Entered into getToursBasicOverviewCountDetails()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long stateId = jObj.getLong("stateId");  
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long globalUserTypeId = jObj.getLong("globalUserTypeId");
			String level = jObj.getString("level");
			listOfTourBasicVoList = coreDashboardToursService.getDesigWiseMemberDtls(stateId,fromDate,toDate,activityMemberId,globalUserTypeId,level); 
			
		} catch (Exception e) { 
			LOG.error("Exception raised at getToursBasicOverviewCountDetails() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	public String getMemberDtlsForADesignation(){    
		try {
			LOG.info("Entered into getToursBasicOverviewCountDetails()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			List<Long> disigList = new ArrayList<Long>();
			JSONArray designationIdArray=jObj.getJSONArray("designationIds");
			if(designationIdArray!=null &&  designationIdArray.length()>0){
				for( int i=0;i<designationIdArray.length();i++){
					disigList.add(Long.valueOf(designationIdArray.getString(i)));
				}   
			}  
			String outPutType = jObj.getString("outPutType");
			listOfTourBasicVo = coreDashboardToursService.getMemberDtlsForADesignation(disigList,stateId,fromDate,toDate,activityMemberId,outPutType); 
			
		} catch (Exception e) { 
			LOG.error("Exception raised at getToursBasicOverviewCountDetails() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	public String getDesignationDtlsOfCandidate(){  
		try{
			jObj = new JSONObject(getTask());
			Long activityMemberId = jObj.getLong("activityMemberId");
			String startDateStr = jObj.getString("startDateStr");
			String endDateStr = jObj.getString("endDateStr");
			List<Long> disigList = new ArrayList<Long>();
			JSONArray designationIdArray=jObj.getJSONArray("desigIds");
			if(designationIdArray!=null &&  designationIdArray.length()>0){
				for( int i=0;i<designationIdArray.length();i++){
					disigList.add(Long.valueOf(designationIdArray.getString(i)));
				}   
			}   
			resultVO = coreDashboardToursService.getDesignationDtls(activityMemberId,disigList,startDateStr,endDateStr);
		}catch(Exception e){  
			e.printStackTrace();  
			LOG.error("Exception raised at getDesignationDtlsOfCandidate()  of ToursAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getLeaderAverageToursBasedOnAccessLevel(){  
		try {
			LOG.info("Entered into getLeaderAverageToursBasedOnAccessLevel()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long candidateId = jObj.getLong("candidateId");
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long userTypeId = jObj.getLong("userTypeId");
			resultVO = coreDashboardToursService.getLeaderAverageToursBasedOnAccessLevel(candidateId,stateId,fromDate,toDate,userTypeId); 
		} catch (Exception e) { 
			LOG.error("Exception raised at getLeaderAverageToursBasedOnAccessLevel() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	
	public String getDesignationLabelList(){  
		try{
			jObj = new JSONObject(getTask());
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long globalUserTypeId = jObj.getLong("globalUserTypeId");
			listOfTourBasicVo = coreDashboardToursService.getDesignationLabelList(activityMemberId,globalUserTypeId);
		}catch(Exception e){  
			e.printStackTrace();    
			LOG.error("Exception raised at getDesignationDtlsOfCandidate()  of ToursAction", e);
		}
		return Action.SUCCESS;
	}
	public String getUpdatedToursDetails(){
		return Action.SUCCESS;
	}
	
	//Apllication Needed Services
	
	public String getAllTourTypes(){
		try{
			idNameVoList = toursService.getAllTourTypes();
		}catch(Exception e){
			LOG.error("Exception raised at getAllTourTypes()  of ToursAction", e);
		}
		return Action.SUCCESS;
	}
	public String getAllTourCategorys(){
		try{
			idNameVoList = toursService.getAllTourCategorys();
		}catch(Exception e){
			LOG.error("Exception raised at getAllTourCategorys()  of ToursAction", e);
		}
		return Action.SUCCESS;
	}
	public String getAllCandidateLocations(){  
		try{
			jObj = new JSONObject(getTask());
			Long tourCategoryId = jObj.getLong("tourCategoryId");
			Long tourCandidateId = jObj.getLong("tourCandidateId");
			addressVOList = toursService.getAllCandidateLocations(tourCategoryId,tourCandidateId);
		}catch(Exception e){  
			e.printStackTrace();    
			LOG.error("Exception raised at getAllCondidateLocations()  of ToursAction", e);
		}
		return Action.SUCCESS;
	}
	
}