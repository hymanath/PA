package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.service.IContentManagementService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import com.itgrids.partyanalyst.dto.ContentDetailsVO;

public class ContentManagementAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware, ServletContextAware{

	private static final long serialVersionUID = 2308975660429251934L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext context;
	private HttpSession session;
	
	private JSONObject jObj;
	private String task;
	
	private IContentManagementService contentManagementService;
	private ContentDetailsVO contentDetailsVO;
	private File selectedContentDetails;
	private Long partyId;
	private Long contentId;
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public IContentManagementService getContentManagementService() {
		return contentManagementService;
	}

	public void setContentManagementService(
			IContentManagementService contentManagementService) {
		this.contentManagementService = contentManagementService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	
	
	public ContentDetailsVO getContentDetailsVO() {
		return contentDetailsVO;
	}

	public void setContentDetailsVO(ContentDetailsVO contentDetailsVO) {
		this.contentDetailsVO = contentDetailsVO;
	}
	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String execute()
	{
		
		return SUCCESS;
	}
	
	public String AjaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("getSelectedContent"))
		{
			contentDetailsVO = contentManagementService.getSelectedContentAndRelatedGalleries(
					jObj.getLong("contentId"),jObj.getString("requestFrom"),jObj.getLong("requestPageId"),"false");
			
			
			 
		}
		
		return SUCCESS;
	}
	
	
	public String getCandidateGallaries(){
		
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	
		
		if(jObj.getString("task").equalsIgnoreCase("getSelectedContent"))
        {
			contentDetailsVO = contentManagementService
					.getSelectedContentAndRelatedGalleries(
							jObj.getLong("contentId"),
							jObj.getString("requestFrom"),
							jObj.getLong("requestPageId"),
							jObj.getString("isCustomer"));

		}
		
		return SUCCESS;
	}
}
