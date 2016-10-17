package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.service.IDataMonitoringService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DataMonitoringAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(DataMonitoringAction.class);


		private HttpServletRequest request;
		private JSONObject jObj;
		private String task;
		
		//attributes
		private IDataMonitoringService dataMonitoringService;
		
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

		
		public IDataMonitoringService getDataMonitoringService() {
			return dataMonitoringService;
		}

		public void setDataMonitoringService(
				IDataMonitoringService dataMonitoringService) {
			this.dataMonitoringService = dataMonitoringService;
		}

		public void setServletRequest(HttpServletRequest arg0) {
			// TODO Auto-generated method stub
			
		}
	
		public String execute(){
			return Action.SUCCESS;
		}

}
