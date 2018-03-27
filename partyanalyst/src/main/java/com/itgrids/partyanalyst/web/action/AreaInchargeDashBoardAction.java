package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AreaInchargeDashBoardAction extends ActionSupport  implements ServletRequestAware{

	private static Logger LOG = Logger.getLogger(AreaInchargeDashBoardAction.class);
	
	private HttpServletRequest request;
	
	
	private JSONObject jObj;
	private String task;
	private ResultStatus resultStatus;
	
	
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
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

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	
	

}
