package com.itgrids.electoralconnect.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.electoralconnect.dto.AnnouncementVO;
import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.service.IAnnouncementService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AnnouncementsAction extends ActionSupport implements ServletRequestAware{

	private static final Logger LOG = Logger.getLogger(AnnouncementsAction.class);
	private String task;
	private JSONObject jobj;
	private HttpServletRequest request;
	private IAnnouncementService announcementService;
	private List<AnnouncementVO> announcementVOs;
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}
	


	public IAnnouncementService getAnnouncementService() {
		return announcementService;
	}


	public void setAnnouncementService(IAnnouncementService announcementService) {
		this.announcementService = announcementService;
	}
	
	
	public List<AnnouncementVO> getAnnouncementVOs() {
		return announcementVOs;
	}


	public void setAnnouncementVOs(List<AnnouncementVO> announcementVOs) {
		this.announcementVOs = announcementVOs;
	}


	public String execute()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		try
		{
			LOG.debug("Enterd into execute() method in AnnouncementsAction Action");
			jobj=new JSONObject(getTask());
			if(user.getIsAdmin()){
				if(jobj.getString("task").equalsIgnoreCase("getAllAnnouncements"))
				{
					int startRecord  = jobj.getInt("startRecord");
					int maxRecord    = jobj.getInt("maxRecord");
					Long userId      = user.getRegistrationID();
					announcementVOs  = announcementService.getAllAnnouncementsForAdmin(startRecord, maxRecord, userId);
				}
				else if(jobj.getString("task").equalsIgnoreCase("getAllAnnouncementsBtdates"))
				{
					String startDate = jobj.getString("startDate");
					String endDate   = jobj.getString("endDate");
					int startIndex   = jobj.getInt("startRecord");
					int maxIndex     = jobj.getInt("maxRecord");
					announcementVOs  = announcementService.getAllAnnouncementsBtSelDates(startDate, endDate, startIndex, maxIndex);
				}
				else if(jobj.getString("task").equalsIgnoreCase("getAllAnnouncementsSelType"))
				{
					Long typeId      = jobj.getLong("type");
					int startIndex   = jobj.getInt("startRecord");
					int maxIndex     = jobj.getInt("maxRecord");
					announcementVOs  = announcementService.getAllAnnouncementsForSelType(typeId, startIndex, maxIndex);
				}
				
				
			}
		}
		catch (Exception e) 
		{
			LOG.debug("Exception raised in execute() method in AnnouncementsAction Action",e);
		}
		return SUCCESS;
	}
			
}
