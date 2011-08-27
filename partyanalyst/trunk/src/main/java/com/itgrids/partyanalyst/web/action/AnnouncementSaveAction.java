package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.AnnouncementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IAnnouncementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class AnnouncementSaveAction extends ActionSupport implements ServletContextAware, ServletRequestAware{
	
	private static final Logger log = Logger.getLogger(AnnouncementSaveAction.class);
    public HttpServletRequest request;
    public ServletContext context;
    private IAnnouncementService announcementService;
    private HttpSession session;
    private AnnouncementVO announcementVO = new AnnouncementVO();
    private AnnouncementVO resultVO;
    
    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Title field is mandatory",shortCircuit=true)
	public void setTitle(String title) {
    	announcementVO.setTitle(title);
	}
   
    public String getTitle() {
		return announcementVO.getTitle();
	}
    
    public String getMessage() {
		return announcementVO.getMessage();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Announcement field is mandatory",shortCircuit=true)
	public void setMessage(String message) {
		announcementVO.setMessage(message);
	}
    
    public String getFromDate() {
		return announcementVO.getFromDate();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "FromDate is mandatory",shortCircuit=true)
	public void setFromDate(String fromDate) {
		announcementVO.setFromDate(fromDate);
	}
		
	public String getToDate() {
		return announcementVO.getToDate();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "ToDate is mandatory",shortCircuit=true)
	public void setToDate(String toDate) {
		announcementVO.setToDate(toDate);
	}
		
	public Long getConstituency() {
		return announcementVO.getConstituency();
	}
	
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Constituency Selection")
	public void setConstituency(Long constituency) {
		announcementVO.setConstituency(constituency);
	}
	
	public String getWindowTask() {
		return announcementVO.getWindowTask();
	}

	public void setWindowTask(String windowTask) {
		announcementVO.setWindowTask(windowTask);
	}
	
	public Long getAnnouncementId() {
		return announcementVO.getAnnouncementId();
	}

	public void setAnnouncementId(Long announcementId) {
		announcementVO.setAnnouncementId(announcementId);
	}

	public IAnnouncementService getAnnouncementService() {
		return announcementService;
	}

	public void setAnnouncementService(IAnnouncementService announcementService) {
		this.announcementService = announcementService;
	}
	
	public AnnouncementVO getAnnouncementVO() {
		return announcementVO;
	}

	public void setAnnouncementVO(AnnouncementVO announcementVO) {
		this.announcementVO = announcementVO;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public AnnouncementVO getResultVO() {
		return resultVO;
	}

	public void setResultVO(AnnouncementVO resultVO) {
		this.resultVO = resultVO;
	}

	public String execute()
	{
		log.debug("Entered into execute method of Announcement Action:");
	
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		
		if(regVO == null){
			return "failure" ;
		}
		announcementVO.setUserId(regVO.getRegistrationID());
		
		if(announcementVO.getWindowTask().equalsIgnoreCase(IConstants.UPDATE_EXISTING) 
				&& (announcementVO.getAnnouncementId() == null || announcementVO.getAnnouncementId().equals(1L)))
		{
			announcementVO.setWindowTask(IConstants.NEW);
			session.setAttribute(ISessionConstants.WINDOW_TASK,IConstants.NEW);
		}
		resultVO = announcementService.saveAnnouncement(announcementVO);
		
		if(resultVO != null && resultVO.getResultStatus().getResultCode() == ResultCodeMapper.FAILURE)
			return "failure" ;
		else
			announcementVO = new AnnouncementVO();
		
		return "success";
	}
	
}
