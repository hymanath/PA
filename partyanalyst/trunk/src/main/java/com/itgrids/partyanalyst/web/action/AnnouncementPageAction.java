package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;
import com.itgrids.partyanalyst.dto.AnnouncementResultsVO;
import com.itgrids.partyanalyst.dto.AnnouncementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IAnnouncementService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


public class AnnouncementPageAction extends ActionSupport implements ServletRequestAware,ServletContextAware,ModelDriven, Preparable{
	
	private static final long serialVersionUID = 1L;
	private List<SelectOptionVO> statesList;
	private IStaticDataService staticDataService;
	JSONObject jObj = null;
    private List<AnnouncementResultsVO> announcementInfo;
    private String task;
	private HttpServletRequest request;
    private ServletContext context;
    private IAnnouncementService announcementService;
    private HttpSession session;
    private Long userId;
    private String windowTask;
    private int updated;
    private List<SelectOptionVO> constituenciesList;
    private AnnouncementVO announcementVO; 
    private String announcementId; 

    public IAnnouncementService getAnnouncementService() {
		return announcementService;
	}
	public void setAnnouncementService(IAnnouncementService announcementService) {
		this.announcementService = announcementService;
	}
	
    public String getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(String announcementId) {
		this.announcementId = announcementId;
	}
	public AnnouncementVO getAnnouncementVO() {
		return announcementVO;
	}
	public void setAnnouncementVO(AnnouncementVO announcementVO) {
		this.announcementVO = announcementVO;
	}
	public int getUpdated() {
    	return updated;
    }
    public void setUpdated(int updated) {
    	this.updated = updated;
    }
    
    public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}

	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}

	public long getUserId() {
		return userId;
	}

	public String getWindowTask() {
		return windowTask;
	}

	public void setWindowTask(String windowTask) {
		this.windowTask = windowTask;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public List<AnnouncementResultsVO> getAnnouncementInfo() {
		return announcementInfo;
	}

	public void setAnnouncementInfo(List<AnnouncementResultsVO> announcementInfo) {
		this.announcementInfo = announcementInfo;
	}
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}

	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}

	public void setServletRequest(HttpServletRequest request) {
		
		this.request = request;
	}

	public void setServletContext(ServletContext context) {
		
		this.context = context;
	}

	public String execute(){	
	
		session = request.getSession();
		statesList = staticDataService.getParticipatedStatesForAnElectionType(new Long(2));
		constituenciesList = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO = new SelectOptionVO(0L,"Select Constituency");
		constituenciesList.add(selectOptionVO);
		session.setAttribute(ISessionConstants.STATES, statesList);
		session.setAttribute(ISessionConstants.CONSTITUENCIES, constituenciesList);
		
		return "success";
	}
	
	public String getAnnouncementDetails(){

		String param=null;		
		param=request.getParameter("task");
		
		try {
			jObj=new JSONObject(param);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long constituencyId = jObj.getLong("constituencyId");
		announcementInfo = announcementService.getAllAnnouncementsInConstituency(constituencyId);
	
	     return Action.SUCCESS;
	}
	
	public String getAnnouncementByUserId(){

		String param=null;		
		param=request.getParameter("task");
		System.out.println(getTask());
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		session = request.getSession(false);
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		userId = regVO.getRegistrationID();
		System.out.println(userId);
		announcementInfo = announcementService.getAllAnnouncementsByUserId(userId);
		System.out.println(announcementInfo);
		 return Action.SUCCESS;
	}
	
	
	public Object getModel() {
		return announcementVO;
	}
	
	public void prepare() throws Exception {
		
		windowTask = request.getParameter("windowTask");
		if(windowTask.equalsIgnoreCase(IConstants.NEW))
		{
			announcementVO = new AnnouncementVO();
		}
		else if(windowTask.equalsIgnoreCase(IConstants.UPDATE_EXISTING))
		{
			if(Long.parseLong(announcementId) > 0l)
			{
				announcementVO = announcementService.getAnnouncementDetailsByAnnouncementId(Long.parseLong(announcementId));
			}
			else
				announcementVO = new AnnouncementVO();	
		}
	}
	
}
