package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.meeting.service.ICadrePartyMeetingManagementService;
import com.itgrids.partyanalyst.model.PartyMeeting;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadrePartyMeetingManagementAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
    private ICadrePartyMeetingManagementService cadrePartyMeetingManagementService;
	private static Logger LOG = Logger.getLogger(CadrePartyMeetingManagementAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private List<SelectOptionVO> constituenciesListForADistrict;
	private JSONObject jObj;
	private String task;
	private String successMsg;
	private PartyMeetingVO partyMeetingVO;
	private List<IdNameVO> locations;
	private List<KeyValueVO> keyValueVOList = new ArrayList<KeyValueVO>();
	private ICadreCommitteeService cadreCommitteeService;
	private InputStream inputStream;
	private List<PartyMeetingsVO> partyMeetingVOList;
	private List<PartyMeetingVO> partyMeetingsVOList;
	private IRegionServiceData regionServiceDataImp=null;
	private  ResultStatus resultStatus;
	private List<IdAndNameVO> statusList=null;
	private IdAndNameVO idAndNameVO ;
	private String resultString="";
	
	
	
	
	public List<PartyMeetingVO> getPartyMeetingsVOList() {
		return partyMeetingsVOList;
	}
	public void setPartyMeetingsVOList(List<PartyMeetingVO> partyMeetingsVOList) {
		this.partyMeetingsVOList = partyMeetingsVOList;
	}
	public List<IdNameVO> getLocations() {
		return locations;
	}
	public void setLocations(List<IdNameVO> locations) {
		this.locations = locations;
	}
	public String getResultString() {
		return resultString;
	}
	public void setResultString(String resultString) {
		this.resultString = resultString;
	}
	public IdAndNameVO getIdAndNameVO() {
		return idAndNameVO;
	}
	public void setIdAndNameVO(IdAndNameVO idAndNameVO) {
		this.idAndNameVO = idAndNameVO;
	}
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	public List<SelectOptionVO> getConstituenciesListForADistrict() {
		return constituenciesListForADistrict;
	}
	public void setConstituenciesListForADistrict(
			List<SelectOptionVO> constituenciesListForADistrict) {
		this.constituenciesListForADistrict = constituenciesListForADistrict;
	}
	public List<PartyMeetingsVO> getPartyMeetingVOList() {
		return partyMeetingVOList;
	}
	public void setPartyMeetingVOList(List<PartyMeetingsVO> partyMeetingVOList) {
		this.partyMeetingVOList = partyMeetingVOList;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public PartyMeetingVO getPartyMeetingVO() {
		return partyMeetingVO;
	}
	public void setPartyMeetingVO(PartyMeetingVO partyMeetingVO) {
		this.partyMeetingVO = partyMeetingVO;
	}
	
	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}
	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
	
	public List<KeyValueVO> getKeyValueVOList() {
		return keyValueVOList;
	}
	public void setKeyValueVOList(List<KeyValueVO> keyValueVOList) {
		this.keyValueVOList = keyValueVOList;
	}

	public ICadrePartyMeetingManagementService getCadrePartyMeetingManagementService() {
		return cadrePartyMeetingManagementService;
	}
	public void setCadrePartyMeetingManagementService(
			ICadrePartyMeetingManagementService cadrePartyMeetingManagementService) {
		this.cadrePartyMeetingManagementService = cadrePartyMeetingManagementService;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
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
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}
	
	

	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public List<IdAndNameVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<IdAndNameVO> statusList) {
		this.statusList = statusList;
	}
	public String execute() {
		
			return Action.SUCCESS;
		
	}
    
 public String getDistrictsBasedOnStateId(){
	try {
		jObj = new JSONObject(getTask());
		Long stateId = jObj.getLong("stateId");
		keyValueVOList = cadrePartyMeetingManagementService.getDistrictsBasedOnStateId(stateId);
	} catch (Exception e) {
		LOG.error("Exception Occured in getDistrictsBasedOnStateId() method in CadrePartyMeetingAction Class", e);
	}
	return Action.SUCCESS;
	}

 public String getConstituencyBasedOnDistrictId(){
	try {
		jObj = new JSONObject(getTask());
		Long districtId = jObj.getLong("districtId");
		constituenciesListForADistrict = regionServiceDataImp.getConstituenciesByDistrictID1(districtId);
	} catch (Exception e) {
		LOG.error("Exception Occured in getConstituencyBasedOnDistrictId() method in CadrePartyMeetingAction Class", e);
	}
	return Action.SUCCESS;
	}
public String getMandalBasedOnConstituency(){
	try {
		
		jObj = new JSONObject(getTask());
		Long constituencyId = jObj.getLong("constituencyId");
		 Long activityScopeId=55l;
		locations = cadreCommitteeService.getMandalsByConstituencyId(constituencyId,activityScopeId);
		
	} catch (Exception e) {
		LOG.error("Exception Occured in getMandalBasedOnConstituency() method in CadrePartyMeetingAction Class", e);
	}
	return Action.SUCCESS;
	}

public String getPanchayatWardByMandal(){
 try{
	jObj = new JSONObject(getTask());
	Long mandalId =jObj.getLong("mandalId");
    Long activityScopeId=52l;
	locations = cadreCommitteeService.getPanchayatBymandalId(mandalId,activityScopeId);
	
 }catch(Exception e){	
	LOG.error("Exception occured in getPanchayatWardByMandal() method ",e);
 }
 return Action.SUCCESS;
}	



/*public String getInvitieDetailsFromExcelSheet(){
    try{
      File file = null;
      
      MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
             Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
             String fileUrl = "" ;
            List<String> filePaths = null;
             while(fileParams.hasMoreElements())
             {
               String key = fileParams.nextElement();
                   File[] files = multiPartRequestWrapper.getFiles(key);
                  filePaths = new ArrayList<String>();
                   if(files != null && files.length > 0)
                   for(File f : files)
                   {
                     String[] extension  =multiPartRequestWrapper.getFileNames(key)[0].split("\\.");
                           String ext = "";
                           if(extension.length > 1){
                             ext = extension[extension.length-1];
                             if(ext.equalsIgnoreCase("csv")){
                            	 file = f;
                            	 successMsg = cadrePartyMeetingManagementService.saveInvitiesDetails(file);
                             }
                             else{
                            	 successMsg="The File should be in csv format. So Please upload in correct Format.";
                             }
                            
                           }
                   }
             }
            
    		  //surveyAnalysisService.savingSurveyRandomBooths(file);
      if(successMsg != null){
         inputStream = new StringBufferInputStream(successMsg);
      }
    }catch(Exception e){
      LOG.error("Exception rised in savingSurveyBooths",e);
    }
    return Action.SUCCESS;  
  }*/
   public String downloadInviteesTemplate(){
	   try {
		   inputStream = new FileInputStream(new File("D:/test.csv"));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	   return Action.SUCCESS;  
   }
   
 public String savepartymeetingDetailsWithExcel(){//savepartymeetingDetailsWithExcel
	// try{
	if(partyMeetingVO != null){
	 PartyMeeting partyMeeting=cadrePartyMeetingManagementService.saveMeetingDetails(partyMeetingVO);
	 if(partyMeeting!=null && partyMeeting.getPartyMeetingId()!=null){
		 Long partyMeetingId=partyMeeting.getPartyMeetingId();
		 try{
		      File file = null;
		      MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
		             Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
		             String fileUrl = "" ;
		            List<String> filePaths = null;
		             while(fileParams.hasMoreElements())
		             {
		               String key = fileParams.nextElement();
		                   File[] files = multiPartRequestWrapper.getFiles(key);
		                  filePaths = new ArrayList<String>();
		                   if(files != null && files.length > 0)
		                   for(File f : files)
		                   {
		                     String[] extension  =multiPartRequestWrapper.getFileNames(key)[0].split("\\.");
		                           String ext = "";
		                           if(extension.length > 1){
		                             ext = extension[extension.length-1];
		                             if(ext.equalsIgnoreCase("csv")){
		                            	 file = f;
		                            	// successMsg = cadrePartyMeetingManagementService.saveInvitiesDetails(file);
		                            	 successMsg = cadrePartyMeetingManagementService.saveInvitiesDetails(partyMeetingVO,file,partyMeetingId);
		                             }
		                             else{
		                            	 successMsg="The File should be in csv format. So Please upload in correct Format.";
		                             }
		                            
		                           }
		                   }
		             }
		      if(successMsg != null){
		         inputStream = new StringBufferInputStream(successMsg);
		      }
		 }catch(Exception e){
		      LOG.error("Exception rised in savepartymeetingDetailsWithExcel",e);
		    }	 
	    }
	 }
	 return Action.SUCCESS;
 }
 
 public String getMeetingMainType(){
		try {
			partyMeetingVOList=cadrePartyMeetingManagementService.getMeetingMainType();
				} catch (Exception e) {
			LOG.error("Exception Occured in getCustomReportProgramForreportId() method, Exception - ",e); 
		}
		
		return Action.SUCCESS;
	}
 
 public String getMeetingSubType(){
		try {
			jObj = new JSONObject(getTask());
			Long partyMeetingMainTypeId = jObj.getLong("partyMeetingMainTypeId");
			partyMeetingVOList= cadrePartyMeetingManagementService.getMeetingSubType(partyMeetingMainTypeId);
			} catch (Exception e) {
			LOG.error("Exception Occured in getCustomReportProgramForreportId() method, Exception - ",e); 
		}
		return Action.SUCCESS;
	 }
 
		 public String getCadrePartyMeetngDeatils(){
		     try{
		       jObj = new JSONObject(getTask());
		       Long partyMeetingMainTypeId = jObj.getLong("partyMeetingMainTypeId");
		       String startDateStr=jObj.getString("startDateStr");
		       String endDateStr=jObj.getString("endDateStr");
		       int startIndex = jObj.getInt("startFromResult");
		       int maxIndex = jObj.getInt("maxIndex");
		       
		       partyMeetingVOList= cadrePartyMeetingManagementService.getCadrePartyMeetngDeatils(startDateStr,endDateStr,partyMeetingMainTypeId,startIndex,maxIndex);
		       } catch (Exception e) {
		       LOG.error("Exception Occured in getCadrePartyMeetngDeatils  method, Exception - ",e); 
		     }
		     return Action.SUCCESS;
		    }
 
 public String getPartyMeetingLevels(){
		try {	
			partyMeetingVOList= cadrePartyMeetingManagementService.getPartyMeetingLevels();
		} catch (Exception e) {
			LOG.error("Exception Occured in getPartyMeetingLevels  method, Exception - ",e); 
		}
		return Action.SUCCESS;
	 }
 
 public String getAllSessionType(){
		try {
			partyMeetingVOList= cadrePartyMeetingManagementService.getAllSessionType();
		} catch (Exception e) {
			LOG.error("Exception Occured in getAllSessionType  method, Exception - ",e); 
		}
		return Action.SUCCESS;
	 }
 
 public String getPartyMeetingsTabUserNameByDistrict(){
		try {
			
		
			partyMeetingVOList= cadrePartyMeetingManagementService.getPartyMeetingsTabUserNameByDistrict();
			} catch (Exception e) {
			LOG.error("Exception Occured in getPartyMeetingsTabUserNameByDistrict method, Exception - ",e); 
		}
		return Action.SUCCESS;
	 }
 
 public String getPartyMeetingDeatilesForMeetingEditByMeetingId(){
		try{
			jObj = new JSONObject(getTask());
			Long partyMeetingMainTypeId = jObj.getLong("partyMeetingMainTypeId");
			partyMeetingVOList= cadrePartyMeetingManagementService.getPartyMeetingDeatilesForMeetingEditByMeetingId(partyMeetingMainTypeId);
			} catch (Exception e) {
			LOG.error("Exception Occured in partyMeetingMainTypeId  method, Exception - ",e); 
		}
		return Action.SUCCESS;
	 }
 
 
 public String getPartyMeetingSession(){
		try{
			jObj = new JSONObject(getTask());
			Long partyMeetingSessionId = jObj.getLong("partyMeetingSessionId");
			partyMeetingVOList= cadrePartyMeetingManagementService.getPartyMeetingSession(partyMeetingSessionId);
			} catch (Exception e) {
			LOG.error("Exception Occured in partyMeetingMainTypeId  method, Exception - ",e); 
		}
		return Action.SUCCESS;
	 }
 
 
 public String getMemberShipIdsFromExcel(){
	
	   try{
	          File file = null;
	          idAndNameVO=new IdAndNameVO();
	          MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
	                 Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
	                 String fileUrl = "" ;
	                 while(fileParams.hasMoreElements())
	                 {
	                   String key = fileParams.nextElement();
	                       File[] files = multiPartRequestWrapper.getFiles(key);
	                       if(files != null && files.length > 0)
	                       for(File f : files)
	                       {
	                         String[] extension  =multiPartRequestWrapper.getFileNames(key)[0].split("\\.");
	                               String ext = "";
	                               if(extension.length > 1){
	                                 ext = extension[extension.length-1];
	                                 if(ext.equalsIgnoreCase("csv")){
	                                   file = f;
	                                   List<String> membershipIdList = cadrePartyMeetingManagementService.getInvitiesDetails(file);
	                                 
	                                   
	                                   for(String str:membershipIdList){
	                                	   resultString=resultString+str+",";
	                                   }
	                                  // idAndNameVO.setName(str);
									   // statusList=cadrePartyMeetingManagementService.getTdpCadreDetailsForInveetMeeting(membershipIdList);
									    
	                                 }
	                                 else{
	                                	 resultString="Please upload csv format";
									   
										//vo.se
	                                  // successMsg="The File should be in csv format. So Please upload in correct Format.";
	                                 }
	                                
	                               }
	                       }
	                 }
					 
	     }catch(Exception e){
	          LOG.error("Exception rised in savepartymeetingDetailsWithExcel",e);
	        }   
	   return Action.SUCCESS;
	}
 
 public String savepartymeetingDetails(){
	 String status="";
	 try{
	   PartyMeeting partyMeeting=cadrePartyMeetingManagementService.saveMeetingDetails(partyMeetingVO);
	   if(partyMeeting !=null){
		   status="Saved Successfully";
	   }
	   else{
		   status="Failure";
	   }
	 }
	 catch(Exception e){
         LOG.error("Exception rised in savepartymeetingDetails",e);
       }   
	   return Action.SUCCESS;
	  
	  }
 
 
 public String getTdpCadreDetailsForInveetMeeting(){
     try{
       jObj = new JSONObject(getTask());
       JSONArray memberShipNosArry = jObj.getJSONArray("memberShipNos");
       List<String> memberShipNosList = new ArrayList<String>(0);
       if(memberShipNosArry != null && memberShipNosArry.length()>0){
         for (int i = 0; i < memberShipNosArry.length(); i++) {
        	 
        	 
           memberShipNosList.add(memberShipNosArry.get(i) != null? memberShipNosArry.get(i).toString().trim():"");
         }
       }
       statusList= cadrePartyMeetingManagementService.getTdpCadreDetailsForInveetMeeting(memberShipNosList);
       } catch (Exception e) {
       LOG.error("Exception Occured in partyMeetingMainTypeId  method, Exception - ",e); 
     }
     return Action.SUCCESS;
    }
 public String getPartyMeetingTabUserDetails(){
	 try{
		 jObj = new JSONObject(getTask());
		Long partyMeetingId = jObj.getLong("partyMeetingId");
		partyMeetingVOList=cadrePartyMeetingManagementService.getPartyMeetingTabUserDetails(partyMeetingId);
	 }catch (Exception e) {
	       LOG.error("Exception Occured in getPartyMeetingTabUserDetails  method, Exception - ",e); 
	     }
	     return Action.SUCCESS;
	 
 }
 public String deletePartyMeetingDatails(){
 	try {
 		jObj = new JSONObject(getTask());
 		Long meetingId=jObj.getLong("partyMeetingId");
 		resultStatus = cadrePartyMeetingManagementService.deletePartyMeetingDatails(meetingId);
		} catch (Exception e) {
			LOG.error("Exception Occured in deletePartyMeetingDatails() method, Exception - ",e); 
		}
 	return Action.SUCCESS;	
 }
 public String getSessionsDetailsByMeetingId(){
 	
	    try {
 		jObj = new JSONObject(getTask());
 		Long meetingId=jObj.getLong("partyMeetingId");
 		partyMeetingsVOList= cadrePartyMeetingManagementService.getSessionsDetailsByMeetingId(meetingId);
		}catch (Exception e) {
			LOG.error("Exception Occured in getSessionsDetailsByMeetingId() method, Exception - ",e); 
			}
 	return Action.SUCCESS;	
 }
}


