package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.AlertAssigningVO;
import com.itgrids.partyanalyst.dto.GovtDepartmentVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.ICccDashboardService;
import com.itgrids.partyanalyst.service.ICccDashboardService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CccDashboardAction extends ActionSupport implements ServletRequestAware {
		
		private final static Logger LOG = Logger.getLogger(CccDashboardAction.class);
		private HttpServletRequest request;
		private HttpSession session;
		private JSONObject jObj;
		private String task;
		private ResultStatus	resultStatus;
		private InputStream	inputStream;
		private String successMsg;
		
		private List<GovtDepartmentVO> govtDeptVoList = new ArrayList<GovtDepartmentVO>();
		private ICccDashboardService cccDashboardService;
		private AlertAssigningVO alertAssigningVO;
		private List<File> imageForDisplay = new ArrayList<File>();
		private List<String> imageForDisplayContentType = new ArrayList<String>();
		private List<String> imageForDisplayFileName = new ArrayList<String>();
		private List<AlertVO> alertVOs;
	   
	    private List<IdAndNameVO> newsPaperList;
	    private List<IdAndNameVO> chanelList;
	    private List<IdAndNameVO> deptList;
	    private List<IdAndNameVO> locationLevelList;
	    private List<AlertCoreDashBoardVO> alertCoreDashBoardVOList;
	    
	   
	    public List<AlertCoreDashBoardVO> getAlertCoreDashBoardVOList() {
			return alertCoreDashBoardVOList;
		}
		public void setAlertCoreDashBoardVOList(
				List<AlertCoreDashBoardVO> alertCoreDashBoardVOList) {
			this.alertCoreDashBoardVOList = alertCoreDashBoardVOList;
		}
		public List<File> getImageForDisplay() {
			return imageForDisplay;
		}
		public void setImageForDisplay(List<File> imageForDisplay) {
			this.imageForDisplay = imageForDisplay;
		}
		public List<String> getImageForDisplayContentType() {
			return imageForDisplayContentType;
		}
		public void setImageForDisplayContentType(
				List<String> imageForDisplayContentType) {
			this.imageForDisplayContentType = imageForDisplayContentType;
		}
		public List<String> getImageForDisplayFileName() {
			return imageForDisplayFileName;
		}
		public void setImageForDisplayFileName(List<String> imageForDisplayFileName) {
			this.imageForDisplayFileName = imageForDisplayFileName;
		}
		public AlertAssigningVO getAlertAssigningVO() {
			return alertAssigningVO;
		}
		public void setAlertAssigningVO(AlertAssigningVO alertAssigningVO) {
			this.alertAssigningVO = alertAssigningVO;
		}
		public ICccDashboardService getCccDashboardService() {
			return cccDashboardService;
		}
		public void setCccDashboardService(ICccDashboardService cccDashboardService) {
			this.cccDashboardService = cccDashboardService;
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
	  
	   public ResultStatus getResultStatus() {
		   return resultStatus;
	   }
	   public void setResultStatus(ResultStatus resultStatus) {
		   this.resultStatus = resultStatus;
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
	   
	   public static Logger getLog() {
		   return LOG;
	   }
	   public List<GovtDepartmentVO> getGovtDeptVoList() {
			return govtDeptVoList;
	   }
	   public void setGovtDeptVoList(List<GovtDepartmentVO> govtDeptVoList) {
			this.govtDeptVoList = govtDeptVoList;
	   }
	   public List<IdAndNameVO> getNewsPaperList() {
			return newsPaperList;
	   }
	   public void setNewsPaperList(List<IdAndNameVO> newsPaperList) {
		   this.newsPaperList = newsPaperList;
	   }
	   public List<IdAndNameVO> getChanelList() {
		   return chanelList;
	   }
	   public void setChanelList(List<IdAndNameVO> chanelList) {
		   this.chanelList = chanelList;
	   }
	   public List<IdAndNameVO> getDeptList() {
		   return deptList;
	   }
	   public void setDeptList(List<IdAndNameVO> deptList) {
		   this.deptList = deptList;
	   }
	   
	   public List<AlertVO> getAlertVOs() {
		return alertVOs;
	}
	public void setAlertVOs(List<AlertVO> alertVOs) {
		this.alertVOs = alertVOs;
	}
	
	
	public List<IdAndNameVO> getLocationLevelList() {
		return locationLevelList;
	}
	public void setLocationLevelList(List<IdAndNameVO> locationLevelList) {
		this.locationLevelList = locationLevelList;
	}
	//Business method
	   public String execute(){
			newsPaperList = cccDashboardService.getNewsPapaerList();
			chanelList = cccDashboardService.getChannelList();
			deptList = cccDashboardService.getDeptList();  
		    return Action.SUCCESS;
	   }
	   public String alertDepartmentLogin(){
		   return Action.SUCCESS;
	   }
	   public String getDepartmentLevels(){
			try {
				jObj = new JSONObject(getTask());
		
				govtDeptVoList = cccDashboardService.getDepartmentLevels();
			} catch (Exception e) {
				LOG.error("Exception Raised in getDepartmentLevels() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		   
		public String getLocationsBasedOnLevel(){
		   try {
				jObj = new JSONObject(getTask());
				Long levelId = jObj.getLong("levelId");
			
				govtDeptVoList = cccDashboardService.getLocationsBasedOnLevel(levelId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getLocationsBasedOnLevel() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		
		public String getMandalsForConstituency(){
		   try {
				jObj = new JSONObject(getTask());
				Long constituencyId = jObj.getLong("constituencyId");
			
				govtDeptVoList = cccDashboardService.getMandalsForConstituency(constituencyId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getMandalsForConstituency() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		
		public String getLebsForConstituency(){
		   try {
				jObj = new JSONObject(getTask());
				Long constituencyId = jObj.getLong("constituencyId");
			
				govtDeptVoList = cccDashboardService.getLebsForConstituency(constituencyId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getLebsForConstituency() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		
		public String getPanchayatsMandalId(){
		   try {
				jObj = new JSONObject(getTask());
				Long constituencyId = jObj.getLong("constituencyId");
				Long mandalId = jObj.getLong("mandalId");
			
				govtDeptVoList = cccDashboardService.getPanchayatsMandalId(mandalId,constituencyId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getPanchayatsMandalId() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		   
		public String getDepartmentsByAlert(){
		   try {
				jObj = new JSONObject(getTask());
				Long alertId = jObj.getLong("alertId");
			
				govtDeptVoList = cccDashboardService.getDepartmentsByAlert(alertId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getDepartmentsByAlert() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		   
		public String getDesignationsByDepartment(){
		   try {
				jObj = new JSONObject(getTask());
				Long departmentId = jObj.getLong("departmentId");
				Long levelId = jObj.getLong("levelId");
			
				govtDeptVoList = cccDashboardService.getDesignationsByDepartment(departmentId,levelId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getDesignationsByDepartment() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		   
		public String getOfficersByDesignationAndLevel(){
		   try {
				jObj = new JSONObject(getTask());
				Long levelId = jObj.getLong("levelId");
				Long levelValue = jObj.getLong("levelValue");
				Long designationId = jObj.getLong("designationId");
			
				govtDeptVoList = cccDashboardService.getOfficersByDesignationAndLevel(levelId,levelValue,designationId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getOfficersByDesignationAndLevel() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		   
		public String assigningAlertToOfficer(){
		   try {
			   session = request.getSession();
			   RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			   if(regVo == null)
				   successMsg = "failure";
			   Long userId = regVo.getRegistrationID();
			   alertAssigningVO.setUserId(userId);
			  /* String imageName=null;
			   List<String> fileNamesList = new ArrayList<String>(0);
			   
			   for(int i=0;i<imageForDisplay.size();i++){
				   
				   		String fileType = imageForDisplayContentType.get(i).substring(imageForDisplayContentType.get(i).indexOf("/")+1, imageForDisplayContentType.get(i).length());
				   		String fileName = imageForDisplayFileName.get(i);
				   		String[] extName = imageForDisplayFileName.get(i).split("\\.");
				   		if(Pattern.matches(".*[a-zA-Z]+.*", extName[0])){
				   			imageName= UUID.randomUUID().toString()+"_"+imageForDisplayFileName.get(i);
				   			fileNamesList.add(IConstants.ALERT_DEPT_DOCUMENTS+"/"+imageName);
				   		}else{
				   			imageName= UUID.randomUUID().toString()+"."+extName[1];
				   			fileNamesList.add(IConstants.ALERT_DEPT_DOCUMENTS+"/"+imageName);
				   		}
				   		String filePath=IConstants.STATIC_CONTENT_FOLDER_PATH+"/"+IConstants.ALERT_DEPT_DOCUMENTS;
			        	 
				   		File fileToCreate = new File(filePath,imageName);
				   		FileUtils.copyFile(imageForDisplay.get(i), fileToCreate);
					  
				   		inputStream = new StringBufferInputStream("success");
		          	}
			   if(fileNamesList != null && !fileNamesList.isEmpty()){
				   alertAssigningVO.setDocumentsList(fileNamesList);
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
				
				List<String> fileNamesList = new ArrayList<String>();
				
				String destPath = saveUploadFile(mapfiles);  
				if(destPath != null && destPath.trim().length() > 0){
					String[] strArr = destPath.split(",");
					if(strArr != null && strArr.length > 0){
						for (int i = 0; i < strArr.length; i++) {
							fileNamesList.add(strArr[i]);
						}
					}
				}
				
				if(fileNamesList != null && !fileNamesList.isEmpty())
					   alertAssigningVO.setDocumentsList(fileNamesList);
			   
			   successMsg = cccDashboardService.assigningAlertToOfficer(alertAssigningVO);
			   
			} catch (Exception e) {
				LOG.error("Exception Raised in assigningAlertToOfficer() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		
		public String saveUploadFile(Map<File,String> mapfiles){
			try{
				
				String destPath = new String();
				String localPath = new String();
				StringBuilder returnPath = new StringBuilder();
				String folderName = folderCreation();
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				
				StringBuilder folderStr = new StringBuilder();
				int temp = month+1;
				String yr = String.valueOf(year); // YEAR YYYY
				String monthText = getMonthForInt(temp);
				folderStr.append(monthText).append(yr); 
				String mnthYr = folderStr.toString();//November-2016
				
				calendar.setTime(new DateUtilService().getCurrentDateAndTime());  
				int i = 0;
				for (Map.Entry<File, String> entry : mapfiles.entrySet()){
					Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
					//DB path    
					destPath = mnthYr+"/"+randomNumber+"."+entry.getValue();
					//local sys path
					localPath = folderName+"/"+randomNumber+"."+entry.getValue();  
					if(i == 0){
						returnPath.append(destPath);      
					}else{
						returnPath.append(","+destPath);  
					}
					i++;
					//ActivityService activityService = new ActivityService(); 
					//saving the file here...
					String fileCpyStts = copyFile(entry.getKey().getAbsolutePath(),localPath);
					 
					if(fileCpyStts.equalsIgnoreCase("error")){
						LOG.error(" Exception Raise in copying file");  
							throw new ArithmeticException();
					}
				}  
				return returnPath.toString();   
			}catch(Exception e){
				e.printStackTrace();  
			}
			return null;
		}
		
		public static String folderCreation()
		{
			try {
				LOG.debug(" in FolderForDocument ");
		  		
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new DateUtilService().getCurrentDateAndTime());  
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				
				String targetDirpath = IConstants.STATIC_CONTENT_FOLDER_PATH+"/"+IConstants.ALERT_DEPT_DOCUMENTS+"/"+getMonthForInt(month+1)+year;
				
				File requriredDir = new File(targetDirpath);
				
				if(!requriredDir.exists())
					requriredDir.mkdirs();
				
				return requriredDir.getAbsolutePath();
				 
			} catch (Exception e) {
				LOG.error(" Failed to Create");  
				return "FAILED";
			}
		}
		
		public static String getMonthForInt(int num) {    
			String month = "";
			DateFormatSymbols dfs = new DateFormatSymbols();
			String[] months = dfs.getMonths();
			if (num >= 1 && num <= 12 ) {
				month = months[num-1];
			}
			return month;  
	    }
		
		public String copyFile(String sourcePath,String destinationPath){
			 try{
				File destFile = new File(destinationPath);
				 if (!destFile.exists()) 
					 destFile.createNewFile();
				 File file = new File(sourcePath);
				if(file.exists()){
					FileUtils.copyFile(file,destFile);
					LOG.error("Copy success");
					return "success";
				}
			  }catch(Exception e){
				  LOG.error("Exception raised in copyFile ", e);
				  LOG.error("Copy error");
				  return "error";
			  }
			 return "failure";
			}
		   
		public String getAssignedOfficersDetailsForAlert(){
		   try {
				jObj = new JSONObject(getTask());
				Long alertId = jObj.getLong("alertId");
			
				govtDeptVoList = cccDashboardService.getAssignedOfficersDetailsForAlert(alertId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getAssignedOfficersDetailsForAlert() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		public String getTotalAlertGroupByStatusForOneDept(){
			try{
				session = request.getSession();
				jObj = new JSONObject(getTask());
				String fromDate = jObj.getString("fromDate");
				String toDate = jObj.getString("toDate");
				Long stateId = jObj.getLong("stateId");
				
				JSONArray paperIdArr = jObj.getJSONArray("paperIdArr");  
				List<Long> paperIdList = new ArrayList<Long>();
				for (int i = 0; i < paperIdArr.length(); i++){
					paperIdList.add(Long.parseLong(paperIdArr.getString(i)));        
				} 
				
				JSONArray chanelIdArr = jObj.getJSONArray("chanelIdArr");  
				List<Long> chanelIdList = new ArrayList<Long>();
				for (int i = 0; i < chanelIdArr.length(); i++){
					chanelIdList.add(Long.parseLong(chanelIdArr.getString(i)));        
				}
				Long userId = null;
				alertVOs = cccDashboardService.getTotalAlertGroupByStatusForOneDept(fromDate, toDate, stateId, paperIdList, chanelIdList,userId);
			}catch(Exception e){
				LOG.error("Exception occured in getTotalAlertGroupByStatusForOneDept() of CccDashboardAction",e);
			}
			return Action.SUCCESS;
		}
		public String getTotalAlertGroutByDeptThenStatus(){
			try{
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				Long userId = regVo.getRegistrationID();
				jObj = new JSONObject(getTask());
				String fromDate = jObj.getString("fromDate");
				String toDate = jObj.getString("toDate");
				Long stateId = jObj.getLong("stateId");
				
				JSONArray paperIdArr = jObj.getJSONArray("paperIdArr");  
				List<Long> paperIdList = new ArrayList<Long>();
				for (int i = 0; i < paperIdArr.length(); i++){
					paperIdList.add(Long.parseLong(paperIdArr.getString(i)));        
				} 
				
				JSONArray chanelIdArr = jObj.getJSONArray("chanelIdArr");  
				List<Long> chanelIdList = new ArrayList<Long>();
				for (int i = 0; i < chanelIdArr.length(); i++){
					chanelIdList.add(Long.parseLong(chanelIdArr.getString(i)));        
				}
				alertVOs = cccDashboardService.getTotalAlertGroutByDeptThenStatus(fromDate, toDate, stateId, paperIdList, chanelIdList,userId);
			}catch(Exception e){
				LOG.error("Exception occured in getTotalAlertGroutByDeptThenStatus() of CccDashboardAction",e);
			}
			return Action.SUCCESS;
		}
		public String getAlertCountLocationWiseThenStatusWise(){
			try{
				session = request.getSession();
				jObj = new JSONObject(getTask());
				String fromDate = jObj.getString("fromDate");
				String toDate = jObj.getString("toDate");
				Long stateId = jObj.getLong("stateId");
				Long govtDepartmentId = jObj.getLong("govtDepartmentId");
				Long lvlValue = jObj.getLong("lvlValue");
				
				JSONArray paperIdArr = jObj.getJSONArray("paperIdArr");  
				List<Long> paperIdList = new ArrayList<Long>();
				for (int i = 0; i < paperIdArr.length(); i++){
					paperIdList.add(Long.parseLong(paperIdArr.getString(i)));        
				} 
				
				JSONArray chanelIdArr = jObj.getJSONArray("chanelIdArr");  
				List<Long> chanelIdList = new ArrayList<Long>();
				for (int i = 0; i < chanelIdArr.length(); i++){
					chanelIdList.add(Long.parseLong(chanelIdArr.getString(i)));        
				}
				alertVOs = cccDashboardService.getAlertCountLocationWiseThenStatusWise(fromDate, toDate, stateId, paperIdList, chanelIdList,govtDepartmentId,lvlValue);
			}catch(Exception e){
				LOG.error("Exception occured in getTotalAlertGroupByStatusForOneDept() of CccDashboardAction",e);
			}
			return Action.SUCCESS;
		}
		public String getDepartmentDetails(){
			try{
				
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Exception occured in getDepartmentDetails() of CccDashboardAction",e);
			}
			return Action.SUCCESS;
		}
		
		public String getStatusWiseCommentsTracking(){
		   try {
				jObj = new JSONObject(getTask());
				Long alertId = jObj.getLong("alertId");
			
				govtDeptVoList = cccDashboardService.getStatusWiseCommentsTracking(alertId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getStatusWiseCommentsTracking() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		public String getDistrictWiseTotalAlertsForAlert(){
		   try {
			   session = request.getSession();
			   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				Long userId = regVo.getRegistrationID();
				jObj = new JSONObject(getTask());
				jObj = new JSONObject(getTask());
				String fromDate = jObj.getString("fromDate");
				String toDate = jObj.getString("toDate");
				Long stateId = jObj.getLong("stateId");
				
				JSONArray deptIdArr = jObj.getJSONArray("deptIdArr");  
				List<Long> deptIdList = new ArrayList<Long>();
				for (int i = 0; i < deptIdArr.length(); i++){
					deptIdList.add(Long.parseLong(deptIdArr.getString(i)));        
				}  
				
				JSONArray paperIdArr = jObj.getJSONArray("newsPaperIdArr");  
				List<Long> paperIdList = new ArrayList<Long>();
				for (int i = 0; i < paperIdArr.length(); i++){
					paperIdList.add(Long.parseLong(paperIdArr.getString(i)));        
				} 
				
				JSONArray chanelIdArr = jObj.getJSONArray("newChanelIdArr");  
				List<Long> chanelIdList = new ArrayList<Long>();
				for (int i = 0; i < chanelIdArr.length(); i++){
					chanelIdList.add(Long.parseLong(chanelIdArr.getString(i)));        
				}
			
				govtDeptVoList = cccDashboardService.getDistrictWiseTotalAlertsForAlert(fromDate,toDate,stateId,deptIdList,paperIdList,chanelIdList,userId);
		  } catch (Exception e) {
			   LOG.error("Exception Raised in getDistrictWiseTotalAlertsForAlert() in CccDashboardAction",e);
		  }
			return Action.SUCCESS;
	}
		public String getStatusWiseDistrictTotalForAlert(){
			   try {
				   session = request.getSession();
				   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
					Long userId = regVo.getRegistrationID();
					jObj = new JSONObject(getTask());
					jObj = new JSONObject(getTask());
					String fromDate = jObj.getString("fromDate");
					String toDate = jObj.getString("toDate");
					Long stateId = jObj.getLong("stateId");
					
					JSONArray deptIdArr = jObj.getJSONArray("deptIdArr");  
					List<Long> deptIdList = new ArrayList<Long>();
					for (int i = 0; i < deptIdArr.length(); i++){
						deptIdList.add(Long.parseLong(deptIdArr.getString(i)));        
					}  
					
					JSONArray paperIdArr = jObj.getJSONArray("newsPaperIdArr");  
					List<Long> paperIdList = new ArrayList<Long>();
					for (int i = 0; i < paperIdArr.length(); i++){
						paperIdList.add(Long.parseLong(paperIdArr.getString(i)));        
					} 
					
					JSONArray chanelIdArr = jObj.getJSONArray("newChanelIdArr");  
					List<Long> chanelIdList = new ArrayList<Long>();
					for (int i = 0; i < chanelIdArr.length(); i++){
						chanelIdList.add(Long.parseLong(chanelIdArr.getString(i)));        
					}
				
					govtDeptVoList = cccDashboardService.getStatusWiseDistrictTotalForAlert(fromDate,toDate,stateId,deptIdList,paperIdList,chanelIdList,userId);
			  } catch (Exception e) {
				   LOG.error("Exception Raised in getStatusWiseDistrictTotalForAlert() in CccDashboardAction",e);
			  }
				return Action.SUCCESS;
		}
		
		public String getInvolvedMembersInAlert(){
		   try {
				jObj = new JSONObject(getTask());
				Long alertId = jObj.getLong("alertId");
				
				govtDeptVoList = cccDashboardService.getInvolvedMembersInAlert(alertId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getInvolvedMembersInAlert() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		public String getLevelsByDeptId(){
			try {
				jObj = new JSONObject(getTask());
		
				govtDeptVoList = cccDashboardService.getLevelsByDeptId(jObj.getLong("departmentId"));
			} catch (Exception e) {
				LOG.error("Exception Raised in getLevelsByDeptId() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		
		public String getGovtDeptLevelForDeptAndUser(){
			   try {
				   session = request.getSession();
				   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
					Long userId = regVo.getRegistrationID();
					jObj = new JSONObject(getTask());
					Long departmentId = jObj.getLong("departmentId");
					
					locationLevelList = cccDashboardService.getGovtDeptLevelForDeptAndUser(departmentId,userId);
			   } catch (Exception e) {
				   LOG.error("Exception Raised in getGovtDeptLevelForDeptAndUser() in CccDashboardAction",e);
				}
				   return Action.SUCCESS;
		}
		public String getDeptIdAndNameListForUser(){
			   try {
					jObj = new JSONObject(getTask());
					Long userId = jObj.getLong("userId");
					
					deptList = cccDashboardService.getDeptIdAndNameListForUser(userId);
			   } catch (Exception e) {
				   LOG.error("Exception Raised in getDeptIdAndNameListForUser() in CccDashboardAction",e);
				}
				   return Action.SUCCESS;
		}
		
		public String getStatusWiseAlertDetails(){
			   try {
				   session = request.getSession();
				   RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				   if(regVo != null){
					    Long userId = regVo.getRegistrationID();
						jObj = new JSONObject(getTask());
						String fromDate = jObj.getString("fromDate");
						String toDate = jObj.getString("toDate");
						Long stateId = jObj.getLong("stateId");
						Long statusId = jObj.getLong("statusId");
						
						JSONArray paperIdArr = jObj.getJSONArray("paperIdArr");  
						List<Long> paperIdList = new ArrayList<Long>();
						for (int i = 0; i < paperIdArr.length(); i++){
							paperIdList.add(Long.parseLong(paperIdArr.getString(i)));        
						} 
						
						JSONArray chanelIdArr = jObj.getJSONArray("chanelIdArr");  
						List<Long> chanelIdList = new ArrayList<Long>();
						for (int i = 0; i < chanelIdArr.length(); i++){
							chanelIdList.add(Long.parseLong(chanelIdArr.getString(i)));        
						}
						
						alertVOs = cccDashboardService.getStatusWiseAlertDetails(fromDate, toDate, stateId, paperIdList, chanelIdList, userId, statusId);
				   }
				   
			   } catch (Exception e) {
				   LOG.error("Exception Raised in getStatusWiseAlertDetails() in CccDashboardAction",e);
				}
			   return Action.SUCCESS;
		}
		
		public String getAlertStatusForUser(){
		   try {
			   session = request.getSession();
			   RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			   
				Long userId = regVo.getRegistrationID();
				
				govtDeptVoList = cccDashboardService.getAlertStatusForUser(userId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getAlertStatusForUser() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		
		public String getAssignedDesignationsForUser(){
		   try {
			   session = request.getSession();
			   RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			   
				Long userId = regVo.getRegistrationID();
				
				govtDeptVoList = cccDashboardService.getAssignedDesignationsForUser(userId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getAssignedDesignationsForUser() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		
		public String getSubLevelsForUser(){
		   try {
			   session = request.getSession();
			   RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			   Long userId = regVo.getRegistrationID();
			   
			   jObj = new JSONObject(getTask());
			   Long designationId = jObj.getLong("designationId");
				
				govtDeptVoList = cccDashboardService.getSubLevelsForUser(userId,designationId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getSubLevelsForUser() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		
		public String getSubOrdinatesAlertsOverView(){
		   try {
			   session = request.getSession();
			   RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			   Long userId = regVo.getRegistrationID();
			   
			   jObj = new JSONObject(getTask());
			   Long designationId = jObj.getLong("designationId");
			   Long levelId = jObj.getLong("levelId");
			   String fromDateStr = jObj.getString("fromDate");
			   String toDateStr = jObj.getString("toDate");
				
				govtDeptVoList = cccDashboardService.getSubOrdinatesAlertsOverView(designationId,levelId,fromDateStr,toDateStr);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getSubOrdinatesAlertsOverView() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		
		public String updatingAlertInformation(){
		   try {
			   session = request.getSession();
			   RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			   if(regVo == null)
				   successMsg = "failure";
			   Long userId = regVo.getRegistrationID();
			   alertAssigningVO.setUserId(userId);
			   
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
				
				List<String> fileNamesList = new ArrayList<String>();
				
				String destPath = saveUploadFile(mapfiles);  
				if(destPath != null && destPath.trim().length() > 0){
					String[] strArr = destPath.split(",");
					if(strArr != null && strArr.length > 0){
						for (int i = 0; i < strArr.length; i++) {
							fileNamesList.add(strArr[i]);
						}
					}
				}
				
				if(fileNamesList != null && !fileNamesList.isEmpty())
					   alertAssigningVO.setDocumentsList(fileNamesList);
			   
			   successMsg = cccDashboardService.updatingAlertInformation(alertAssigningVO);
			   
			} catch (Exception e) {
				LOG.error("Exception Raised in updatingAlertInformation() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		public String getTotalAlertByStatusForOfficer(){
			   try {
				   session = request.getSession();
				   RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				   if(regVo != null){
					    Long userId = regVo.getRegistrationID();
						jObj = new JSONObject(getTask());
						String fromDate = jObj.getString("fromDate");
						String toDate = jObj.getString("toDate");
						
						
						alertVOs = cccDashboardService.getTotalAlertByStatusForOfficer(fromDate, toDate, userId);
				   }
				   
			   } catch (Exception e) {
				   LOG.error("Exception Raised in getStatusWiseAlertDetails() in CccDashboardAction",e);
				}
			   return Action.SUCCESS;
		}
		public String getTotalAlertByDeptForOfficer(){
			   try {
				   session = request.getSession();
				   RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				   if(regVo != null){
					    Long userId = regVo.getRegistrationID();
						jObj = new JSONObject(getTask());
						String fromDate = jObj.getString("fromDate");
						String toDate = jObj.getString("toDate");
						
						
						alertVOs = cccDashboardService.getTotalAlertByDeptForOfficer(fromDate, toDate, userId);
				   }
				   
			   } catch (Exception e) {
				   LOG.error("Exception Raised in getStatusWiseAlertDetails() in CccDashboardAction",e);
				}
			   return Action.SUCCESS;
		}
		//public List<AlertVO> getTotalAlertByDeptForOfficer(String fromDateStr, String toDateStr,Long userId)
		//public List<AlertVO> getTotalAlertByStatusForOfficer(String fromDateStr, String toDateStr,Long userId)
		
		public String getSubOrdinateLocationWiseAlertDetails(){
		   try {
			   session = request.getSession();
			   RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			   Long userId = regVo.getRegistrationID();
			   
			   jObj = new JSONObject(getTask());
			   Long designationId = jObj.getLong("designationId");
			   Long levelId = jObj.getLong("levelId");
			   Long levelValue = jObj.getLong("levelValue");
			   String fromDateStr = jObj.getString("fromDate");
			   String toDateStr = jObj.getString("toDate");
				
			   alertCoreDashBoardVOList = cccDashboardService.getSubOrdinateLocationWiseAlertDetails(designationId,levelId,levelValue,fromDateStr,toDateStr);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getSubOrdinateLocationWiseAlertDetails() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		
		public String getDesigAndStatusWiseAlertsCounts(){
		   try {
			   session = request.getSession();
			   RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			   Long userId = regVo.getRegistrationID();
			   
			   jObj = new JSONObject(getTask());
			   Long departmentId = jObj.getLong("departmentId");
			   Long stateId = jObj.getLong("stateId");
			   String fromDateStr = jObj.getString("fromDate");
			   String toDateStr = jObj.getString("toDate");
			   
			   JSONArray paperIdArr = jObj.getJSONArray("paperIdArr");  
				List<Long> paperIdList = new ArrayList<Long>();
				for (int i = 0; i < paperIdArr.length(); i++){
					paperIdList.add(Long.parseLong(paperIdArr.getString(i)));        
				} 
				
				JSONArray chanelIdArr = jObj.getJSONArray("chanelIdArr");  
				List<Long> chanelIdList = new ArrayList<Long>();
				for (int i = 0; i < chanelIdArr.length(); i++){
					chanelIdList.add(Long.parseLong(chanelIdArr.getString(i)));        
				}
				
			   govtDeptVoList = cccDashboardService.getDesigAndStatusWiseAlertsCounts(departmentId, stateId, fromDateStr, toDateStr, paperIdList, chanelIdList, userId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getDesigAndStatusWiseAlertsCounts() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		
		public String getDesigAndStatusWiseAlertDetails(){
		   try {
			   session = request.getSession();
			   RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			   Long userId = regVo.getRegistrationID();
			   
			   jObj = new JSONObject(getTask());
			   Long departmentId = jObj.getLong("departmentId");
			   Long stateId = jObj.getLong("stateId");
			   String fromDateStr = jObj.getString("fromDate");
			   String toDateStr = jObj.getString("toDate");
			   Long designationId = jObj.getLong("designationId");
			   Long statusId = jObj.getLong("statusId");
			   
			   JSONArray paperIdArr = jObj.getJSONArray("paperIdArr");  
				List<Long> paperIdList = new ArrayList<Long>();
				for (int i = 0; i < paperIdArr.length(); i++){
					paperIdList.add(Long.parseLong(paperIdArr.getString(i)));        
				} 
				
				JSONArray chanelIdArr = jObj.getJSONArray("chanelIdArr");  
				List<Long> chanelIdList = new ArrayList<Long>();
				for (int i = 0; i < chanelIdArr.length(); i++){
					chanelIdList.add(Long.parseLong(chanelIdArr.getString(i)));        
				}
				
				alertCoreDashBoardVOList = cccDashboardService.getDesigAndStatusWiseAlertDetails(departmentId, stateId, fromDateStr, toDateStr, paperIdList, chanelIdList, userId, designationId, statusId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getDesigAndStatusWiseAlertDetails() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
		public String getTotalAlertDtls(){
			   try {
				   session = request.getSession();
				   RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				   Long userId = regVo.getRegistrationID();
				   
				   jObj = new JSONObject(getTask());
				  
				   String fromDateStr = jObj.getString("fromDate");
				   String toDateStr = jObj.getString("toDate");
				   Long statusId = jObj.getLong("statusId");
				   Long deptId = jObj.getLong("deptId");
				   String type = jObj.getString("type");
					
				   alertCoreDashBoardVOList = cccDashboardService.getTotalAlertDtls(fromDateStr, toDateStr, userId, statusId, deptId, type);
			   } catch (Exception e) {
				   LOG.error("Exception Raised in getDesigAndStatusWiseAlertsCounts() in CccDashboardAction",e);
				}
				   return Action.SUCCESS;
		}
		public String getTotalAlertDetailsGroupByDeptThenStatus(){
			try{
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				Long userId = regVo.getRegistrationID();
				jObj = new JSONObject(getTask());
				String fromDate = jObj.getString("fromDate");
				String toDate = jObj.getString("toDate");
				Long stateId = jObj.getLong("stateId");
				Long deptId = jObj.getLong("deptId");
				Long statusId = jObj.getLong("statusId");
				String type = jObj.getString("type");
				JSONArray paperIdArr = jObj.getJSONArray("paperIdArr");  
				List<Long> paperIdList = new ArrayList<Long>();
				for (int i = 0; i < paperIdArr.length(); i++){
					paperIdList.add(Long.parseLong(paperIdArr.getString(i)));        
				} 
				
				JSONArray chanelIdArr = jObj.getJSONArray("chanelIdArr");  
				List<Long> chanelIdList = new ArrayList<Long>();
				for (int i = 0; i < chanelIdArr.length(); i++){
					chanelIdList.add(Long.parseLong(chanelIdArr.getString(i)));        
				}
				alertCoreDashBoardVOList = cccDashboardService.getTotalAlertDetailsGroupByDeptThenStatus(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,deptId,statusId);
			}catch(Exception e){
				LOG.error("Exception occured in getTotalAlertGroutByDeptThenStatus() of CccDashboardAction",e);
			}
			return Action.SUCCESS;
		}
		public String getAlertCountDetailsLocationWiseThenStatusWise(){
			try{
				session = request.getSession();  
				jObj = new JSONObject(getTask());
				String fromDate = jObj.getString("fromDate");
				String toDate = jObj.getString("toDate");
				Long stateId = jObj.getLong("stateId");
				Long govtDepartmentId = jObj.getLong("govtDepartmentId");
				Long lvlValue = jObj.getLong("lvlValue");
				Long locId = jObj.getLong("locId");
				Long statusId = jObj.getLong("statusId");
				JSONArray paperIdArr = jObj.getJSONArray("paperIdArr");  
				List<Long> paperIdList = new ArrayList<Long>();
				for (int i = 0; i < paperIdArr.length(); i++){
					paperIdList.add(Long.parseLong(paperIdArr.getString(i)));        
				} 
				
				JSONArray chanelIdArr = jObj.getJSONArray("chanelIdArr");  
				List<Long> chanelIdList = new ArrayList<Long>();
				for (int i = 0; i < chanelIdArr.length(); i++){
					chanelIdList.add(Long.parseLong(chanelIdArr.getString(i)));        
				}
				alertCoreDashBoardVOList = cccDashboardService.getAlertCountDetailsLocationWiseThenStatusWise(fromDate, toDate, stateId, paperIdList, chanelIdList,govtDepartmentId,lvlValue,locId,statusId);
			}catch(Exception e){
				LOG.error("Exception occured in getTotalAlertGroupByStatusForOneDept() of CccDashboardAction",e);
			}
			return Action.SUCCESS;
		}
		
}
