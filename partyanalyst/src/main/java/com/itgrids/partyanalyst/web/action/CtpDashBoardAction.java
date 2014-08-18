package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ICtpDashBoardService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CtpDashBoardAction extends ActionSupport implements ServletRequestAware{

	
	private static final long serialVersionUID = -1673285714195342962L;
	public static final Logger LOG = Logger.getLogger(SurveyDashBoardAction.class);
	HttpServletRequest request;
	
	private String task;
	private JSONObject jObj;
	
	@Autowired
	private ICtpDashBoardService ctpDashBoardService;
	
	
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	
	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public String execute()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
		{
			return Action.INPUT;
		}
		
		return Action.SUCCESS;
	}
}
