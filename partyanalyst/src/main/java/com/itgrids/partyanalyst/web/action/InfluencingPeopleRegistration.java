package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class InfluencingPeopleRegistration extends ActionSupport implements
 ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ServletContext context;
	private HttpServletRequest request;
	private HttpSession session;
	
	private List<SelectOptionVO> positionsList;
	private List<SelectOptionVO> staticParties;
	private List<SelectOptionVO> influenceRange;
	private int positionSize;
	private IInfluencingPeopleService influencingPeopleService;
	private IStaticDataService staticDataService;
	private String windowTask;
	private Long influencingPersonId;
	
	public IInfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}

	public void setInfluencingPeopleService(
			IInfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public int getPositionSize() {
		return positionSize;
	}

	public void setPositionSize(int positionSize) {
		this.positionSize = positionSize;
	}

		
	public void setServletContext(ServletContext context) {
		this.context = context;		
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}	
	
	public String getWindowTask() {
		return windowTask;
	}

	public void setWindowTask(String windowTask) {
		this.windowTask = windowTask;
	}

	public Long getInfluencingPersonId() {
		return influencingPersonId;
	}

	public void setInfluencingPersonId(Long influencingPersonId) {
		this.influencingPersonId = influencingPersonId;
	}

	public String execute() throws Exception {
		positionsList = new ArrayList<SelectOptionVO>();
		staticParties = new ArrayList<SelectOptionVO>();
		influenceRange = new ArrayList<SelectOptionVO>();
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		Long userId = user.getRegistrationID();
		
		positionsList = influencingPeopleService.getAllInfluencePeoplePositions(userId);
		positionSize =  positionsList.size();
		staticParties = staticDataService.getStaticParties();
		influenceRange = influencingPeopleService.getInfluenceRange();
			
		session = request.getSession();
		session.setAttribute("staticParties", staticParties);
		session.setAttribute("influenceRange",influenceRange);
		session.setAttribute("positionsList",positionsList);
		return Action.SUCCESS;
	}

}
