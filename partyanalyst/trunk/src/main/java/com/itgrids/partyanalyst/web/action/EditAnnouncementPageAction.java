package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AnnouncementInfo;
import com.itgrids.partyanalyst.dto.AnnouncementResultsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IAnnocementsService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class EditAnnouncementPageAction extends ActionSupport implements ServletRequestAware,ServletContextAware, ModelDriven, Preparable{
private long announcementId;
private String windowTask;
private ServletContext context;
private HttpServletRequest request;
private HttpSession session;
private IAnnocementsService annocementsService;
private AnnouncementInfo announcementInfo;
private List<SelectOptionVO> statesList;
private IStaticDataService staticDataService;
private IRegionServiceData regionServiceDataImp;
private List<SelectOptionVO> constituenciesList;
private String task;





public String getTask() {
	return task;
}
public void setTask(String task) {
	this.task = task;
}
public List<SelectOptionVO> getConstituenciesList() {
	return constituenciesList;
}
public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
	this.constituenciesList = constituenciesList;
}
public IRegionServiceData getRegionServiceDataImp() {
	return regionServiceDataImp;
}
public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
	this.regionServiceDataImp = regionServiceDataImp;
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
public AnnouncementInfo getAnnouncementInfo() {
	return announcementInfo;
}
public void setAnnouncementInfo(AnnouncementInfo announcementInfo) {
	this.announcementInfo = announcementInfo;
}
public IAnnocementsService getAnnocementsService() {
	return annocementsService;
}
public void setAnnocementsService(IAnnocementsService annocementsService) {
	this.annocementsService = annocementsService;
}
public long getAnnouncementId() {
	return announcementId;
}
public void setAnnouncementId(long announcementId) {
	this.announcementId = announcementId;
}
public String getWindowTask() {
	return windowTask;
}
public void setWindowTask(String windowTask) {
	this.windowTask = windowTask;
}

public void setServletRequest(HttpServletRequest request) {
	this.request = request;
	
}

public void setServletContext(ServletContext context) {
	this.context = context;
	
}
public String execute(){
	System.out.println(windowTask);			
	
	request.setAttribute("windowTask", windowTask);
	request.setAttribute("announcementInfo", announcementInfo);
	statesList = staticDataService.getParticipatedStatesForAnElectionType(new Long(2));
	session.setAttribute("constituenciesList",constituenciesList);
	
	
	return "success";
}

public Object getModel() {
	
	return announcementInfo;
}

public void prepare() throws Exception {
	session = request.getSession(false);
	announcementId =  Long.parseLong(request.getParameter("announcementId"));
	
	announcementInfo = annocementsService.getAnnouncementDetailsByAnnouncementId(announcementId);
	List annousDetails = annocementsService.getAnnouncementDetails(announcementId);
	if(annousDetails.size()>0){
	Object[] o	= (Object[])(annousDetails.get(0));
	
	announcementInfo.setConstituency((Long)o[0]);
	System.out.println((Long)o[0]);
	System.out.println(o[1].toString());
	if((o[2].toString()).equals("Assembly")){
		
	
	announcementInfo.setState((Long)o[3]);
	constituenciesList =  staticDataService.getConstituenciesByElectionTypeAndStateId(2L, (Long)o[3]).getConstituencies();
	System.out.println(o[3].toString());
	}
	else{
		constituenciesList = regionServiceDataImp.getAllParliamentConstituencies(1l, 1l);
	}
		
	
	System.out.println(o[2].toString());
	request.setAttribute("type",o[2].toString());
	session.setAttribute("constituencyid",(Long)o[0]);
	}
}

 

}
