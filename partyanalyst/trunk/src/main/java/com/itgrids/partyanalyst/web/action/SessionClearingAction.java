package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SessionClearingAction extends ActionSupport implements ServletRequestAware,ServletContextAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private ServletContext context;
	private HttpSession session;
	JSONObject jObj = null;
	
	private String task = null;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	public void setServletContext(ServletContext context) {
		this.context = context;		
	}
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String execute() throws Exception {
		
		return Action.SUCCESS;
		
	}
	
	public void clearingSession()
	{
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
		}
		catch (ParseException e) {
			
			e.printStackTrace();
		}
		String module = jObj.getString("module");
		String accessType = jObj.getString("accessType");
		String windowTask = jObj.getString("windowTask");
		
		System.out.println("=====");
		
		System.out.println(module);
		System.out.println(accessType);
		System.out.println(accessType);
		
		System.out.println("=====");
		
		removeSessionVariablesForAModule(module,accessType,windowTask);
	}
	
	public void removeSessionVariablesForAModule(String module,String accessType,String windowTask){
		try {
				session = request.getSession();
				
				if(module.equalsIgnoreCase("InfluencingPeople"))
				{
					session.removeAttribute("staticParties");
					session.removeAttribute("influenceRange");
					session.removeAttribute("positionsList");	
					session.removeAttribute(ISessionConstants.SOCIALCATEGORIES);
					session.removeAttribute(ISessionConstants.OCCUPATIONS);
					session.removeAttribute(ISessionConstants.GENDERS);
					
					session.removeAttribute(ISessionConstants.DISTRICTS);
					session.removeAttribute(ISessionConstants.CONSTITUENCIES);
					session.removeAttribute(ISessionConstants.MANDALS);	
					session.removeAttribute(ISessionConstants.VILLAGES);
					
					if(windowTask.equalsIgnoreCase("new"))
						session.removeAttribute(ISessionConstants.STATES);
					
				}
				if(module.equalsIgnoreCase("CadreRegistration"))
				{
					session.removeAttribute(ISessionConstants.CADRE_LEVELS_LIST);
					session.removeAttribute(ISessionConstants.LANGUAGE_OPTIONS);
					session.removeAttribute(ISessionConstants.STATES_O);
					
					session.removeAttribute(ISessionConstants.DOB_OPTIONS);
					session.removeAttribute(ISessionConstants.CADRE_LEVELS);
					session.removeAttribute(ISessionConstants.CADRETYPES);
					session.removeAttribute(ISessionConstants.GENDERS);
					session.removeAttribute(ISessionConstants.SOCIALCATEGORIES);
					session.removeAttribute(ISessionConstants.EDU_QUALIFICATIONS);
					session.removeAttribute(ISessionConstants.OCCUPATIONS);
					session.removeAttribute(ISessionConstants.LANGUAGES);
					session.removeAttribute(ISessionConstants.PARTY_COMMITTEES_FLAG);
					session.removeAttribute(ISessionConstants.CADRE_SKILLS_FLAG);
					session.removeAttribute(ISessionConstants.PARTY_TRAINING_CAMPS_FLAG);
					session.removeAttribute(ISessionConstants.COMMITTEE_DESIGNATIONS);
					
					if(windowTask.equalsIgnoreCase(IConstants.CREATE_NEW))
					{
						if(accessType.equalsIgnoreCase("MP"))
						{
							session.removeAttribute("constituenciesList");
						}
						else
						{
							session.removeAttribute(ISessionConstants.STATES);
							session.removeAttribute(ISessionConstants.DISTRICTS);
							session.removeAttribute(ISessionConstants.CONSTITUENCIES);
							session.removeAttribute(ISessionConstants.MANDALS);
							session.removeAttribute(ISessionConstants.VILLAGES);
							
							session.removeAttribute(ISessionConstants.STATES_C);
							session.removeAttribute(ISessionConstants.DISTRICTS_C);
							session.removeAttribute(ISessionConstants.CONSTITUENCIES_C);
							session.removeAttribute(ISessionConstants.MANDALS_C);
							session.removeAttribute(ISessionConstants.VILLAGES_C);
						}
						session.removeAttribute(ISessionConstants.DISTRICTS_O);
						session.removeAttribute(ISessionConstants.CONSTITUENCIES_O);
						session.removeAttribute(ISessionConstants.MANDALS_O);
						session.removeAttribute(ISessionConstants.VILLAGES_O);
					}
					else if(windowTask.equalsIgnoreCase(IConstants.UPDATE_EXISTING))
					{
						session.removeAttribute(ISessionConstants.STATES);
						session.removeAttribute(ISessionConstants.STATES_C);
						session.removeAttribute(ISessionConstants.DISTRICTS_C);
						session.removeAttribute(ISessionConstants.CONSTITUENCIES_C);
						session.removeAttribute(ISessionConstants.MANDALS_C);
						session.removeAttribute(ISessionConstants.VILLAGES_C);
						
						session.removeAttribute(ISessionConstants.DISTRICTS);
						session.removeAttribute(ISessionConstants.CONSTITUENCIES);
						session.removeAttribute(ISessionConstants.MANDALS);
						session.removeAttribute(ISessionConstants.VILLAGES);
						
					}
				}
				
				if(module.equalsIgnoreCase("PoliticalChanges"))
				{
					session.removeAttribute(ISessionConstants.IMPACTED_REGIONS);
					session.removeAttribute(ISessionConstants.INFO_SOURCES);
					session.removeAttribute(ISessionConstants.MAIN_PARTIES);
				}
				
				
			}
		catch(Exception e){
				e.printStackTrace();
			}
	}	
	

}
