/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 13, 2010
 */
package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Sai Krishna
 *
 */
public class CreateLocalGroupAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(CreateLocalGroupAction.class);

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
	private IInfluencingPeopleService influencingPeopleService;
	private List<SelectOptionVO> groupScopes = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> groupCategories;
	private RegionServiceDataImp regionServiceDataImp;
	private List<SelectOptionVO> statesList = new ArrayList<SelectOptionVO>();
	
	
	public RegionServiceDataImp getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	public void setRegionServiceDataImp(RegionServiceDataImp regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	public IInfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}
	public void setInfluencingPeopleService(
			IInfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
	}
	public List<SelectOptionVO> getGroupScopes() {
		return groupScopes;
	}
	public void setGroupScopes(List<SelectOptionVO> groupScopes) {
		this.groupScopes = groupScopes;
	}
	public List<SelectOptionVO> getGroupCategories() {
		return groupCategories;
	}
	public void setGroupCategories(List<SelectOptionVO> groupCategories) {
		this.groupCategories = groupCategories;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub

	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}
	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}
	public String execute(){
		
		if(log.isDebugEnabled())
			log.debug("Create A Local Group Action ..");
		
		session = request.getSession();
		
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO==null)
			return ERROR;
		
		//Get Group Categories
		groupCategories = influencingPeopleService.getLocalGroupCategoriesList(regVO.getRegistrationID());
		session.setAttribute(ISessionConstants.USER_GROUP_CATEGORIES,groupCategories);
		
		//Get Scopes Data
		setGroupScopesData();
		
		String accessType =regVO.getAccessType();
		Long accessValue= new Long(regVO.getAccessValue());
		
		List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByDistrictID(accessValue);
		
		statesList.add(list.get(0));	
		
		session.setAttribute("statesList",statesList);
		session.setAttribute("districtsList",new ArrayList<SelectOptionVO>());
		session.setAttribute("constituenciesList",new ArrayList<SelectOptionVO>());
		session.setAttribute("mandalsList",new ArrayList<SelectOptionVO>());
		
		return Action.SUCCESS;
	}
	
	public void setGroupScopesData(){
		
		groupScopes.add(new SelectOptionVO(2l,"STATE"));
		groupScopes.add(new SelectOptionVO(3l,"DISTRICT"));
		groupScopes.add(new SelectOptionVO(4l,"CONSTITUENCY"));
		groupScopes.add(new SelectOptionVO(5l,"MANDAL"));
		groupScopes.add(new SelectOptionVO(6l,"VILLAGE"));
		groupScopes.add(new SelectOptionVO(7l,"MUNICIPAL-CORP-GMC"));
		groupScopes.add(new SelectOptionVO(8l,"WARD"));
		groupScopes.add(new SelectOptionVO(9l,"BOOTH"));
		session.setAttribute(ISessionConstants.USER_GROUP_SCOPES,groupScopes);
	}

}
