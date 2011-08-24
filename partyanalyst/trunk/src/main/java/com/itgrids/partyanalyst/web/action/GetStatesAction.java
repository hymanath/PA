package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AnnouncementResultsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IAnnocementsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


public class GetStatesAction extends ActionSupport implements ServletRequestAware,ServletContextAware {
	private List<SelectOptionVO> statesList;
	private IStaticDataService staticDataService;
	JSONObject jObj = null;
    private List<AnnouncementResultsVO> announcementInfo;
    private String task;
	public HttpServletRequest request;
    public ServletContext context;
    private IAnnocementsService annocementsService;
    private HttpSession session;
    private long userId;
    private String windowTask;
    private int updated;
    

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


	private List<SelectOptionVO> constituenciesList;
 //   private long success;
    
    
    
//    
//    public List<AnnouncementInfo> getAnnouncementInf() {
//		return announcementInf;
//	}
//
//	public void setAnnouncementInf(List<AnnouncementInfo> announcementInf) {
//		this.announcementInf = announcementInf;
//	}

     
	public long getUserId() {
		return userId;
	}

//	public long getSuccess() {
//		return success;
//	}
//
//	public void setSuccess(long success) {
//		this.success = success;
//	}

	public String getWindowTask() {
		return windowTask;
	}

	public void setWindowTask(String windowTask) {
		this.windowTask = windowTask;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public IAnnocementsService getAnnocementsService() {
		return annocementsService;
	}

	public void setAnnocementsService(IAnnocementsService annocementsService) {
		this.annocementsService = annocementsService;
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

	

	public String execute(){	
	
		System.out.println(windowTask);
		statesList = staticDataService.getParticipatedStatesForAnElectionType(new Long(2));
		constituenciesList = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO = new SelectOptionVO(0L,"Select Constituency");
		constituenciesList.add(selectOptionVO);
		request.setAttribute("windowTask", windowTask);
		return "success";
	}
	
	public String getAnnouncementDetails(){

		String param=null;		
		param=request.getParameter("task");
		
		try {
			jObj=new JSONObject(param);
	//		System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long constituencyId = jObj.getLong("constituencyId");
		announcementInfo = annocementsService.getAllAnnouncementsInConstituency(constituencyId);
	
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
		announcementInfo = annocementsService.getAllAnnouncementsByUserId(userId);
		System.out.println(announcementInfo);
		 return Action.SUCCESS;
	}
	
//	public String deleteAnnouncement(){
//
//		String param=null;		
//		param=request.getParameter("task");
//		System.out.println(getTask());
//		
//		try {
//			jObj=new JSONObject(param);
//			System.out.println("jObj = "+jObj);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		long announcementId =jObj.getLong("announcementId");
//		
//		System.out.println(announcementId);
//		annocementsService.deleteAnnouncement(announcementId);
//		success = 1L;
//		 return Action.SUCCESS;
//	}
	
	
	public void setServletRequest(HttpServletRequest request) {
		
		this.request = request;
	}

	
	public void setServletContext(ServletContext context) {
		
		this.context = context;
	}

	public String updateAnnouncementDetails(){
		
		String param=null;		
		param=request.getParameter("task");
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long announcementId = jObj.getLong("announcementId");
		Long constituencyId = jObj.getLong("constituencyId");
		String title = jObj.getString("title");
		String announcement = jObj.getString("announcement");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		AnnouncementResultsVO announcementResultsVO = new AnnouncementResultsVO();
		announcementResultsVO.setAnnouncementsId(announcementId);
		announcementResultsVO.setConstituencyId(constituencyId);
		announcementResultsVO.setTitle(title);
		announcementResultsVO.setDiscription(announcement);
		SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_PATTERN);
		Date FromDate = null;
		Date ToDate = null;
		try {
			
			   if(fromDate!=null)
				   
			 FromDate = format.parse(fromDate);
			  
			   if(toDate!=null)
			 ToDate = format.parse(toDate);
			   
		} catch (ParseException e) {
			e.printStackTrace();
		}
		announcementResultsVO.setFromDate(FromDate);
		announcementResultsVO.setToDate(ToDate);
		annocementsService.updateAnnouncementDetails(announcementResultsVO);
		updated=1;
		 return Action.SUCCESS;
	}
	

}
