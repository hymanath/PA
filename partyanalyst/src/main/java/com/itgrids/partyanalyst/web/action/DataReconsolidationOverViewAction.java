package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DataReconsolidationOverViewAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(DataReconsolidationOverViewAction.class);

	  private HttpServletRequest request;
	  private JSONObject jObj;
	  private String task;
	  
	  public void setServletRequest(HttpServletRequest request) {
			this.request = request;
		}
	  

	  public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
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


	public String execute(){
			return Action.SUCCESS;
		}
}
