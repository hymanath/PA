package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.service.ICadreRegAmountUploadService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreRegAmountUploadAction extends ActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = -4620729281316958397L;
	private static final Logger LOG = Logger
			.getLogger(CadreRegAmountUploadAction.class);
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	private ICadreRegAmountUploadService cadreRegAmountUploadService;
	private List<File> userImage = new ArrayList<File>();
	private List<String> userImageContentType = new ArrayList<String>();
	private List<String> userImageFileName = new ArrayList<String>();

	public List<File> getUserImage() {
		return userImage;
	}

	public void setUserImage(List<File> userImage) {
		this.userImage = userImage;
	}

	public List<String> getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(List<String> userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public List<String> getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(List<String> userImageFileName) {
		this.userImageFileName = userImageFileName;
	}

	public ICadreRegAmountUploadService getCadreRegAmountUploadService() {
		return cadreRegAmountUploadService;
	}

	public void setCadreRegAmountUploadService(
			ICadreRegAmountUploadService cadreRegAmountUploadService) {
		this.cadreRegAmountUploadService = cadreRegAmountUploadService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	public void setServletContext(ServletContext arg0) {

	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public String execute() {
		try{
			LOG.info("Enterd into execute() in CadreRegAmountUploadAction");
			String task=request.getParameter("task");
			String date=request.getParameter("date");
			String fileUrl=request.getParameter("fileUrl");
			if(task.equalsIgnoreCase("savingFile")){
				cadreRegAmountUploadService.getDashBoardBasicInfo(date,fileUrl);
			}
		}
		catch (Exception e) {
			LOG.error("Exception raised in execute() in CadreRegAmountUploadAction");
		}
		return Action.SUCCESS;
	}
	
	public String uploadFile()
	{
		try{
			session = request.getSession();
		}catch (Exception e) {
			LOG.error(e);
		}
		return Action.SUCCESS;
	}
}
