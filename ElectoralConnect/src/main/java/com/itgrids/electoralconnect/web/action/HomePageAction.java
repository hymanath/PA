package com.itgrids.electoralconnect.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.service.IUserService;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class HomePageAction extends ActionSupport implements ServletRequestAware{

private HttpServletRequest request;
private String passwordChanged;
private static final Logger LOG = Logger.getLogger(HomePageAction.class);
private String task;
private JSONObject jobj;
private ResultStatus resultStatus;
private IUserService userService;

public String getPasswordChanged() {
	return passwordChanged;
}

public void setPasswordChanged(String passwordChanged) {
	this.passwordChanged = passwordChanged;
}

public String getTask() {
	return task;
}

public void setTask(String task) {
	this.task = task;
}

public ResultStatus getResultStatus() {
	return resultStatus;
}

public void setResultStatus(ResultStatus resultStatus) {
	this.resultStatus = resultStatus;
}

public IUserService getUserService() {
	return userService;
}

public void setUserService(IUserService userService) {
	this.userService = userService;
}

public String execute() throws Exception {
	return SUCCESS;
}

@Override
public void setServletRequest(HttpServletRequest request) {
	this.request = request;
}

public String saveComment()
{
	try {
		LOG.debug("Entered into saveComment() method in HomePageAction Action");
		jobj = new JSONObject(getTask());
		HttpSession session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO == null)
			return Action.ERROR;
		String commentString = jobj.getString("comment");
		Long annoucementId = jobj.getLong("id");
		Long userId = regVO.getRegistrationID();
		resultStatus = userService.saveComment(userId, annoucementId, commentString);
	} catch (Exception e) {
		LOG.error("Exception raised in  saveComment() method in HomePageAction Action",e);
	}
	return Action.SUCCESS;
}
}
