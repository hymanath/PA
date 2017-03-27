package com.itgrids.partyanalyst.web.action;


import java.io.File;
import java.text.ParseException;
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
	private HttpServletRequest 					request;
	private HttpSession 						session;
	private String 								task;
	private JSONObject							jObj;
    private List<CustomReportVO>                     customReportVO;
    private ICustomReportService			 		customReportService;
	private ResultStatus                         resultStatus;
	
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
	
	public List<CustomReportVO> getCustomReportVO() {
		return customReportVO;
	}
	public void setCustomReportVO(List<CustomReportVO> customReportVO) {
		this.customReportVO = customReportVO;
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
	public void setServletRequest(HttpServletRequest arg0) {
	}
	
    public String execute(){
		
		return Action.SUCCESS;
	}
	
	
    public String getTotalExpertedReports(){
    		String param=null;		
    		param=request.getParameter("task");
    		try {
    			jObj=new JSONObject(param);
    			LOG.info("jObj = "+jObj);
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}	
    		
    		try {
    			jObj = new JSONObject(getTask());	
    					
    			customReportVO = customReportService.getTotalExpertedReports(jObj.getLong("id"));	
    		//	Collections.sort(states);
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		
    		return SUCCESS;  
    	
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
		     
		   		resultStatus = customReportService.saveCustomReportUploadFile(mapfiles, userId);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in reportUploadForm() method, Exception - ",e); 
		}
		return Action.SUCCESS;	
	}
	
}
