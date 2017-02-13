package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.AlertAssigningVO;
import com.itgrids.partyanalyst.dto.GovtDepartmentVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.ICccDashboardService;
import com.itgrids.partyanalyst.service.ICccDashboardService;
import com.itgrids.partyanalyst.utils.IConstants;
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
				   return "input";
			   Long userId = regVo.getRegistrationID();
			   alertAssigningVO.setUserId(userId);
			   String imageName=null;
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
			   }
			   
			   successMsg = cccDashboardService.assigningAlertToOfficer(alertAssigningVO);
			   
			} catch (Exception e) {
				LOG.error("Exception Raised in assigningAlertToOfficer() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
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
				Long userId =null;
				alertVOs = cccDashboardService.getTotalAlertGroutByDeptThenStatus(fromDate, toDate, stateId, paperIdList, chanelIdList,userId);
			}catch(Exception e){
				LOG.error("Exception occured in getTotalAlertGroutByDeptThenStatus() of CccDashboardAction",e);
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
			
				govtDeptVoList = cccDashboardService.getDistrictWiseTotalAlertsForAlert(fromDate,toDate,stateId,deptIdList,paperIdList,chanelIdList);
		  } catch (Exception e) {
			   LOG.error("Exception Raised in getDistrictWiseTotalAlertsForAlert() in CccDashboardAction",e);
		  }
			return Action.SUCCESS;
	}
		public String getStatusWiseDistrictTotalForAlert(){
			   try {
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
				
					govtDeptVoList = cccDashboardService.getStatusWiseDistrictTotalForAlert(fromDate,toDate,stateId,deptIdList,paperIdList,chanelIdList);
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
}
