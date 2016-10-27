package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.service.IToursService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ToursAction extends ActionSupport implements ServletRequestAware {

	   private final static Logger LOG = Logger.getLogger(ToursAction.class);
	
		 private HttpServletRequest request;
		 private JSONObject jObj;
		 private String task;
	     
		 private IToursService toursService;
	
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
		public void setServletRequest(HttpServletRequest arg0) {
			this.request = request;
		}
		public void setToursService(IToursService toursService) {
			this.toursService = toursService;
		}
		
		
		//Business method
		public String execute(){
			return Action.SUCCESS;
		}
		  
}
