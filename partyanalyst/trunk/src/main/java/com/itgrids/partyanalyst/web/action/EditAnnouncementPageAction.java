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
import com.itgrids.partyanalyst.service.IAnnouncementService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class EditAnnouncementPageAction extends ActionSupport implements ServletRequestAware,ServletContextAware{
private long announcementId;
private String windowTask;
private ServletContext context;
private HttpServletRequest request;
private HttpSession session;
private IAnnouncementService annocementService;
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
public IAnnouncementService getAnnocementsService() {
	return annocementService;
}
public void setAnnocementsService(IAnnouncementService annocementService) {
	this.annocementService = annocementService;
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
	
	return "success";
}

}
