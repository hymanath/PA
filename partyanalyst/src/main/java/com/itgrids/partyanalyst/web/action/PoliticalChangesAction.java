package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.service.IPoliticalChangesService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PoliticalChangesAction extends ActionSupport implements
		ServletContextAware, ServletRequestAware, ModelDriven, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	
	private String task = null;
	JSONObject jObj = null;
	
	private String type;
	private String localPoliticalChangeId;
	private IPoliticalChangesService politicalChangesService;
	private IInfluencingPeopleService influencingPeopleService;
	private IStaticDataService staticDataService;
	
	private List<PoliticalChangesVO> politicalChangesData;   
	private List<SelectOptionVO> informationSourcesList,staticPartiesList,effectedRangeList;
	private PoliticalChangesVO externalPersonInfo;
	private PoliticalChangesVO politicalChangesVO;
	private String name,description;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocalPoliticalChangeId() {
		return localPoliticalChangeId;
	}

	public void setLocalPoliticalChangeId(String localPoliticalChangeId) {
		this.localPoliticalChangeId = localPoliticalChangeId;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public IInfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}

	public void setInfluencingPeopleService(
			IInfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
	}
	
	public PoliticalChangesVO getExternalPersonInfo() {
		return externalPersonInfo;
	}

	public void setExternalPersonInfo(PoliticalChangesVO externalPersonInfo) {
		this.externalPersonInfo = externalPersonInfo;
	}

	public List<PoliticalChangesVO> getPoliticalChangesData() {
		return politicalChangesData;
	}

	public void setPoliticalChangesData(
			List<PoliticalChangesVO> politicalChangesData) {
		this.politicalChangesData = politicalChangesData;
	}
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public void setServletContext(ServletContext context) {
		this.setContext(context);
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
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public ServletContext getContext() {
		return context;
	}
	
	public IPoliticalChangesService getPoliticalChangesService() {
		return politicalChangesService;
	}

	public void setPoliticalChangesService(
			IPoliticalChangesService politicalChangesService) {
		this.politicalChangesService = politicalChangesService;
	}	
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}
	
	public void setPoliticalChangesVO(PoliticalChangesVO politicalChangesVO) {
		this.politicalChangesVO = politicalChangesVO;
	}

	public PoliticalChangesVO getPoliticalChangesVO() {
		return politicalChangesVO;
	}

	public String execute() throws Exception {		
		
		effectedRangeList = new ArrayList<SelectOptionVO>();
		informationSourcesList = new ArrayList<SelectOptionVO>();
		staticPartiesList = new ArrayList<SelectOptionVO>();
		HttpSession session = request.getSession();
		
		
		effectedRangeList = influencingPeopleService.getInfluenceRange();
		informationSourcesList = staticDataService.getAllInformationSources();
		staticPartiesList = staticDataService.getStaticParties();
		
		session = request.getSession();
		session.setAttribute(ISessionConstants.IMPACTED_REGIONS, effectedRangeList);
		session.setAttribute(ISessionConstants.INFO_SOURCES,informationSourcesList);
		session.setAttribute(ISessionConstants.MAIN_PARTIES,staticPartiesList);
		
		return Action.SUCCESS;
	}
	
	public void removeSessionVariablesForPoliticalChanges(){
		try {
			HttpSession session = request.getSession();
			session = request.getSession();
			session.removeAttribute(ISessionConstants.IMPACTED_REGIONS);
			session.removeAttribute(ISessionConstants.INFO_SOURCES);
			session.removeAttribute(ISessionConstants.MAIN_PARTIES);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
		
	
	
	public String getAllPoliticalChangesForTheUser(){
		String param = null;
		param = getTask();
		try {
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			Long userID = user.getRegistrationID();
			jObj = new JSONObject(param);			
			if(jObj.getString("task").equalsIgnoreCase("getAllPoliticalChangesForTheUser"))
			{	
				politicalChangesData = politicalChangesService.getAllPoliticalChanges(userID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;	
	}
	
	public void deltePoliticalChange(){
		String param = null;
		param = getTask();		
		try {
			jObj = new JSONObject(param);					
			if(jObj.getString("task").equalsIgnoreCase("deltePoliticalChange"))
			{
				politicalChangesService.deltePoliticalChangeBasedOnUser(jObj.getLong("politicalChangeId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getExternalPersonDetails(){
		String param = null;
		param = getTask();
		try {			
				jObj = new JSONObject(param);				
				if(jObj.getString("task").equalsIgnoreCase("getExternalPersonDetails") || jObj.getString("task").equalsIgnoreCase("getExternalPersonDetailsForEdit")) 
				{	
					externalPersonInfo = politicalChangesService.getExternalPersonDetails(jObj.getLong("politicalChangeId"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return Action.SUCCESS;	
	}	

	public Object getModel() {
		
		return politicalChangesVO;
	}

	public void prepare() throws Exception {
		localPoliticalChangeId = request.getParameter("localPoliticalChangeId");
		
        if( "0".equals(localPoliticalChangeId)) {
        	politicalChangesVO = new PoliticalChangesVO();
        } else if(localPoliticalChangeId != null)
        {	
        	politicalChangesVO = politicalChangesService.getDetailsBylocalPoliticalChangeId(new Long(localPoliticalChangeId));
        	
        }
		
	}
}
