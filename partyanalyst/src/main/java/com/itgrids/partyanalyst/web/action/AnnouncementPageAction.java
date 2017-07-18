package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;
import com.itgrids.partyanalyst.dto.AnnouncementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IAnnouncementService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


public class AnnouncementPageAction extends ActionSupport implements ServletRequestAware,ModelDriven, Preparable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AnnouncementPageAction.class);
	private List<SelectOptionVO> statesList;
	private IStaticDataService staticDataService;
	transient private JSONObject jObj = null;
    private List<AnnouncementVO> announcementInfo;
    private String task;
    transient private HttpServletRequest request;
    private IAnnouncementService announcementService;
    transient private HttpSession session;
    private Long userId;
    private String windowTask;
    private int updated;
    private List<SelectOptionVO> constituenciesList;
    private AnnouncementVO announcementVO; 
    private String announcementId; 
    private IRegionServiceData regionServiceDataImp;

    public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	public void setRegionServiceDataImp(final IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	
	public IAnnouncementService getAnnouncementService() {
		return announcementService;
	}
	
	public void setAnnouncementService(final IAnnouncementService announcementService) {
		this.announcementService = announcementService;
	}
	
    public String getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(final String announcementId) {
		this.announcementId = announcementId;
	}
	public AnnouncementVO getAnnouncementVO() {
		return announcementVO;
	}
	public void setAnnouncementVO(final AnnouncementVO announcementVO) {
		this.announcementVO = announcementVO;
	}
	public int getUpdated() {
    	return updated;
    }
    public void setUpdated(final int updated) {
    	this.updated = updated;
    }
    
    public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}

	public void setConstituenciesList(final List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}

	public long getUserId() {
		return userId;
	}

	public String getWindowTask() {
		return windowTask;
	}

	public void setWindowTask(final String windowTask) {
		this.windowTask = windowTask;
	}

	public void setUserId(final Long userId) {
		this.userId = userId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(final String task) {
		this.task = task;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(final IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}

	public void setStatesList(final List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}

	public void setServletRequest(final HttpServletRequest request) {
		
		this.request = request;
	}

	public List<AnnouncementVO> getAnnouncementInfo() {
		return announcementInfo;
	}
	public void setAnnouncementInfo(final List<AnnouncementVO> announcementInfo) {
		this.announcementInfo = announcementInfo;
	}
	public String execute(){	
	
		session = request.getSession();
		statesList = staticDataService.getParticipatedStatesForAnElectionType(2l);
		
		if(windowTask.equalsIgnoreCase(IConstants.NEW))
		{
			constituenciesList = new ArrayList<SelectOptionVO>();
		}
		else if(windowTask.equalsIgnoreCase(IConstants.UPDATE_EXISTING))
		{
			if(announcementVO.getType().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
				constituenciesList = staticDataService.getConstituenciesByElectionTypeAndStateId(2L,announcementVO.getState()).getConstituencies();
			}else if(announcementVO.getType().equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
				constituenciesList = regionServiceDataImp.getAllParliamentConstituencies(1L,1L);
			}
		}
		final SelectOptionVO selectOptionVO = new SelectOptionVO(0L,"Select Constituency");
		constituenciesList.add(selectOptionVO);
		session.setAttribute(ISessionConstants.STATES, statesList);
		session.setAttribute(ISessionConstants.CONSTITUENCIES, constituenciesList);
		session.setAttribute(ISessionConstants.WINDOW_TASK,windowTask);
		
		return "success";
	}
	
	@SuppressWarnings("unchecked")
	public String ajaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			LOG.error("Exception rised in ajaxHandler",e);
		}
		
		if(jObj.getString("task").equalsIgnoreCase("getAnnouncementDetailsOfAConstituency"))
		{
			announcementInfo = announcementService.getAllAnnouncementsInConstituency(jObj.getLong("constituencyId"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("getUserAnnouncementDetails"))
		{
			announcementInfo = announcementService.getAllAnnouncementsByUserId(jObj.getLong("userId"));
		}
		return Action.SUCCESS;
	}
	
	public String getAnnouncementByUserId(){

		try {
			jObj=new JSONObject(getTask());
		} catch (ParseException e) {
			LOG.error("Exception rised in getAnnouncementByUserId",e);
		}
		session = request.getSession(false);
		final RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		userId = regVO.getRegistrationID();
		
		announcementInfo = announcementService.getAllAnnouncementsByUserId(userId);
		return Action.SUCCESS;
	}
	
	public String getAnnouncementByUserIdAndDate(){
		final AnnouncementVO announcementVO = new AnnouncementVO();
		try {
			jObj=new JSONObject(getTask());
		} catch (ParseException e) {
			LOG.error("Exception rised in getAnnouncementByUserIdAndDate",e);
		}
		session = request.getSession(false);
		final RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		userId = regVO.getRegistrationID();
		
		announcementVO.setConstituency(jObj.getLong("constituencyId"));
		announcementVO.setFromDate(jObj.getString("fromDate"));
		announcementVO.setToDate(jObj.getString("toDate"));
		announcementVO.setUserId(userId);
		
		announcementInfo = announcementService.searchAnnouncementDetailsByUserIdDateConstId(announcementVO);
		return Action.SUCCESS;
	}
	
	public String getStateDetails(){
		
		session = request.getSession();
		statesList = staticDataService.getParticipatedStatesForAnElectionType(2l);
		constituenciesList = new ArrayList<SelectOptionVO>();
		final SelectOptionVO selectOptionVO = new SelectOptionVO(0L,"Select Constituency");
		constituenciesList.add(selectOptionVO);
		session.setAttribute(ISessionConstants.STATES, statesList);
		session.setAttribute(ISessionConstants.CONSTITUENCIES, constituenciesList);
		return Action.SUCCESS;
	}
	
	public Object getModel() {
		return announcementVO;
	}
	
	public void prepare(){
		
		windowTask = request.getParameter("windowTask");
		announcementId = request.getParameter("announcementId");
		
		if(windowTask != null && windowTask.equalsIgnoreCase(IConstants.NEW))
		{
			announcementVO = new AnnouncementVO();
		}
		else if(windowTask != null && windowTask.equalsIgnoreCase(IConstants.UPDATE_EXISTING))
		{
			if(Long.parseLong(announcementId) > 0l)
			{
				announcementVO = announcementService.getAnnouncementDetailsByAnnouncementId(Long.parseLong(announcementId));
			}
			else{
				announcementVO = new AnnouncementVO();	
			}
		}
	}
	public String getAnnouncementDetails(){
		try {
			jObj=new JSONObject(getTask());
		} catch (ParseException e) {
			LOG.error("Exception rised in getAnnouncementDetails",e);
		}
		final DateUtilService dateUtilService = new DateUtilService();
		session = request.getSession(false);
		final RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		userId = regVO.getRegistrationID();
		
		announcementInfo = announcementService.getAllUserAnnouncementDetails(userId, dateUtilService.getCurrentDateAndTime());
		return Action.SUCCESS;
	}
	
	public String getpartyLeaderDetails(){
		try {
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			statesList = announcementService.getUserBasedAccessConstituencies(regVO.getRegistrationID());
		} catch (Exception e) {
			LOG.error("Exception rised in getpartyLeaderDetails",e);
		}
		return Action.SUCCESS;
		
	} 
	public String getBoothUserDetails(){
		try{
			jObj=new JSONObject(getTask());
			session = request.getSession();
			Long constituencyId=jObj.getLong("constituencyId");
			Long mandalId=jObj.getLong("mandalId");
			Long boothId=jObj.getLong("boothId");
			String cadreType=jObj.getString("cadreType");
			statesList = announcementService.getBoothUserDetails(constituencyId, mandalId, boothId, cadreType);
		}catch(Exception e){
			LOG.error("Exception rised in getpartyLeaderDetails",e);
		}
		return Action.SUCCESS;
	}
	
}
