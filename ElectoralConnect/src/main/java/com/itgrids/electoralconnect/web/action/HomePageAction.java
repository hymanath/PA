package com.itgrids.electoralconnect.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.electoralconnect.dto.AnnouncementVO;
import com.itgrids.electoralconnect.dto.CommentVO;
import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.service.IAnnouncementService;
import com.itgrids.electoralconnect.service.IUserService;
import com.itgrids.electoralconnect.dto.ResultStatus;
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
private List<CommentVO> commentVO;
private List<AnnouncementVO> announcementsList;
private IAnnouncementService announcementService;
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

public List<CommentVO> getCommentVO() {
	return commentVO;
}

public void setCommentVO(List<CommentVO> commentVO) {
	this.commentVO = commentVO;
}

public List<AnnouncementVO> getAnnouncementsList() {
	return announcementsList;
}

public void setAnnouncementsList(List<AnnouncementVO> announcementsList) {
	this.announcementsList = announcementsList;
}

public IAnnouncementService getAnnouncementService() {
	return announcementService;
}

public void setAnnouncementService(IAnnouncementService announcementService) {
	this.announcementService = announcementService;
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
			return "notLogged";
		String commentString = jobj.getString("comment");
		
		Long userId = regVO.getRegistrationID();
		if(jobj.getString("task").equalsIgnoreCase("commentReplySave")){
			Long commentId = jobj.getLong("id");
			resultStatus=userService.saveReplyComment(userId, commentId, commentString);
		}
		else{		
			Long annoucementId = jobj.getLong("id");
			resultStatus = userService.saveComment(userId, annoucementId, commentString);
		}
	} catch (Exception e) {
		LOG.error("Exception raised in  saveComment() method in HomePageAction Action",e);
	}
	return Action.SUCCESS;
}

public String getCommentsList()
{
	try {
		LOG.debug("Entered into getCommentsList() method in HomePageAction Action");
		jobj = new JSONObject(getTask());
		
			Long id        = jobj.getLong("id");
			int startIndex = jobj.getInt("startIndex");
			int maxIndex   = jobj.getInt("maxIndex");
			commentVO = userService.getAllCommentsCommentedByUser(id,startIndex,maxIndex);
		
			
	} catch (Exception e) {
		LOG.error("Exception raised in  getCommentsList() method in HomePageAction Action",e);
	}
	return Action.SUCCESS;
}

public String getTopAnnouncements()
{
	try {
		LOG.debug("Entered into getTopAnnouncements() method in HomePageAction Action");
		jobj = new JSONObject(getTask());
		if(jobj.getString("task").equalsIgnoreCase("topAnnouncements"))
		{
			announcementsList = announcementService.getTop5Announcements();
		}
		else if(jobj.getString("task").equalsIgnoreCase("getAnnouncementForSelected"))
		{
			Long announcementId = jobj.getLong("announcementId");
			announcementsList = announcementService.getAnnouncementById(announcementId);
		}
		else if(jobj.getString("task").equalsIgnoreCase("getAllAnnouncements")||jobj.getString("task").equalsIgnoreCase("getAllAnnouncementsForPaging"))
		{
			Long announcemetId = jobj.getLong("announcenentTypeId");
			int startRecord=jobj.getInt("startRecord");
			int maxRecord=jobj.getInt("maxRecord");
			
			announcementsList = announcementService.getAllAnnouncements(announcemetId,startRecord,maxRecord);
		}
		
		else if(jobj.getString("task").equalsIgnoreCase("getAllAnnouncementsByAnnounFileId"))
		{
			Long announcemetId = jobj.getLong("announcenentTypeId");
			announcementsList = announcementService.getAnnouncementByAnnouncementFileId(announcemetId);
		}
		
	} catch (Exception e) {
		LOG.error("Exception raised in  getTopAnnouncements() method in HomePageAction Action",e);
	}
	return Action.SUCCESS;
}

public String deleteAnnouncement()
{
	try {
		LOG.debug("Entered into deleteAnnouncement() method in AdminPageAction Action");
		jobj = new JSONObject(getTask());
		Long announcementId = jobj.getLong("announcementId");
		resultStatus = announcementService.deleteSelctedAnnoncement(announcementId);
	} catch (Exception e) {
		LOG.error("Exception raised in  deleteAnnouncement() method in AdminPageAction Action",e);
	}
	return Action.SUCCESS;
}
public String searchPage(){
	return Action.SUCCESS;
}
}
