package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.AnnouncementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IAnnocementsService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class AnnouncementSaveAction extends ActionSupport implements ServletContextAware, ServletRequestAware{
	
	private static final Logger log = Logger.getLogger(AddNewProblemAction.class);
   
    
    private String title;
    private String message;
    private String fromdate;
    private String todate;
    private long userid;
    private long constituency;
    public HttpServletRequest request;
    public ServletContext context;
    private IAnnocementsService annocementsService;
    private RegistrationVO regVO;
    private HttpSession session;
    
    
    	

	

	public String getTitle() {
		return title;
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Title field is mandatory",shortCircuit=true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Please enter Username below 150 characters ", maxLength = "150")
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getFromdate() {
		return fromdate;
	}
	
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "FromDate is mandatory",shortCircuit=true)
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	
	
	public String getTodate() {
		return todate;
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "ToDate is mandatory",shortCircuit=true)
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getConstituency() {
		return constituency;
	}
	public void setConstituency(long constituency) {
		this.constituency = constituency;
	}
	public IAnnocementsService getAnnocementsService() {
		return annocementsService;
	}
	public void setAnnocementsService(IAnnocementsService annocementsService) {
		this.annocementsService = annocementsService;
	}
	public String getMessage() {
		return message;
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Announcement field is mandatory",shortCircuit=true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Announcement Should be below 600 characters ", maxLength = "600")
	public void setMessage(String message) {
		this.message = message;
		
	}
	public String execute(){
		log.debug("Entered into execute method of Announcement Action:");
	
		session = request.getSession(false);
		if(session == null){
			return "failure" ;
		}
			
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO == null){
			return "failure" ;
		}
		AnnouncementVO annVO = new AnnouncementVO();
	    	
		annVO.setConstituencyId(constituency);
		annVO.setUserId(regVO.getRegistrationID());
		annVO.setTitle(title);
		annVO.setMessage(message);
		annVO.setFromdate(fromdate);
		annVO.setTodate(todate);
		
		annocementsService.saveAnnouncement(annVO,regVO);
		return "success";
		
	}
	
	@Override
	public void setServletContext(ServletContext context) {
		// TODO Auto-generated method stub
		this.context = context;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	
}
