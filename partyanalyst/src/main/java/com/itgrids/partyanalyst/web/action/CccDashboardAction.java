package com.itgrids.partyanalyst.web.action;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ResultStatus;
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
	   //Business method
	   public String execute(){
		    return Action.SUCCESS;
	   }
}
