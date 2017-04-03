package com.itgrids.partyanalyst.web.action;


import java.io.File;
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
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CustomReportVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.ICustomReportService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CustomReportAction extends ActionSupport implements ServletRequestAware{
	private static final Logger LOG = Logger.getLogger(CustomReportAction.class);
	private HttpServletRequest 			request;
	private HttpSession 				session;
	private String 						task;
	private JSONObject					jObj;
	
	private List<CustomReportVO>                     customReportVOList;
	private ICustomReportService			 		customReportService;
	private ResultStatus                         resultStatus;
	private CustomReportVO customReportVO;
	private String description;
	private Long reportId;
	private Long programId;
	
	
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public Long getReportId() {
		return reportId;
	}
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CustomReportVO getCustomReportVO() {
		return customReportVO;
	}
	public void setCustomReportVO(CustomReportVO customReportVO) {
		this.customReportVO = customReportVO;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public ICustomReportService getCustomReportService() {
		return customReportService;
	}
	public void setCustomReportService(ICustomReportService customReportService) {
		this.customReportService = customReportService;
	}
	public List<CustomReportVO> getCustomReportVOList() {
		return customReportVOList;
	}
	public void setCustomReportVOList(List<CustomReportVO> customReportVOList) {
		this.customReportVOList = customReportVOList;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public HttpSession getSession() {
		return session;
	}
	public String getTask() {
		return task;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String execute(){
		
		return Action.SUCCESS;
	}	
	public String getCustomReportProgram(){
		try 
		{
			jObj = new JSONObject(getTask());
			
			String startDateStr = jObj.getString("startDateStr");
			String endDateStr = jObj.getString("endDateStr");
			customReportVOList = customReportService.getCustomReportPrograms(startDateStr,endDateStr);
		} catch (Exception e){
			LOG.error("Exception occured in getCustomReportProgram in CustomReportAction class  ",e);
		}
			return Action.SUCCESS;		
}  
	
	public String getTotalExpectedReports(){
		try {
    		jObj=new JSONObject(getTask());
    		
    		customReportVO = customReportService.getTotalExpectedReports(jObj.getLong("id"));	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return Action.SUCCESS;  	
     }
    public String saveCustomReportUploadFile()
	{
		try{
			
			Long userId = 0l;
			
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVo!=null && regVo.getRegistrationID()!=null){
				userId = regVo.getRegistrationID();
			}
			
			Map<File,String> mapfiles = new HashMap<File,String>();
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
				   	            	mapfiles.put(f,ext);
				   	            }
				   	        
				   			}	
		   		}
		     
		   		resultStatus = customReportService.saveCustomReportUploadFile(mapfiles, userId,description,reportId,programId);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in reportUploadForm() method, Exception - ",e); 
		}
		return Action.SUCCESS;	
	}
	
    public String getProgramReportsDetails(){
    	try {
    		jObj = new JSONObject(getTask());
    		customReportVOList = customReportService.getProgramReportsDetails(jObj.getLong("id"));
		} catch (Exception e) {
			LOG.error("Exception Occured in getProgramReportsDetails() method, Exception - ",e); 
		}
    	return Action.SUCCESS;	
    }
    
    public String getReportFullDetails(){
    	try {
    		jObj = new JSONObject(getTask());
    		customReportVO = customReportService.getReportFullDetails(jObj.getLong("reportId"));
		} catch (Exception e) {
			LOG.error("Exception Occured in getReportFullDetails() method, Exception - ",e); 
		}
    	return Action.SUCCESS;	
    }
    public String getCustomReportProgramForreportId(){
    	try {
    		jObj = new JSONObject(getTask());
    		customReportVOList = customReportService.getCustomReportProgramForreportId(jObj.getLong("reportId"),jObj.getString("type"));
		} catch (Exception e) {
			LOG.error("Exception Occured in getCustomReportProgramForreportId() method, Exception - ",e); 
		}
    	return Action.SUCCESS;	
    }
    public String deleteCustomReportFileDetails(){
    	try {
    		jObj = new JSONObject(getTask());
    		resultStatus = customReportService.deleteCustomReportFileDetails(jObj.getLong("reportId"));
		} catch (Exception e) {
			LOG.error("Exception Occured in deleteCustomReportFileDetails() method, Exception - ",e); 
		}
    	return Action.SUCCESS;	
    }
}
