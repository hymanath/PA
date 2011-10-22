package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.LocationwiseProblemStatusInfoVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.StateElectionsVO;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStatePageService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

/**
 * A class representing to display the data related to home page
 *
 * @author  Ashok Dakavaram
 * 
 * @see     com.opensymphony.xwork2.ActionSupport
 */

@SuppressWarnings("serial")
public class HomePageAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	private HttpServletRequest request;
	private List<SelectOptionVO> statesList,statesListForLocalBodyElection;
	private IStaticDataService staticDataService;
	private List<StateElectionsVO> stateElections;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	private IStatePageService statePageService;
	private IRegionServiceData regionServiceDataImp;
	private List<SelectOptionVO> constituenciesList;
	private IProblemManagementReportService problemManagementReportService;
	private List<ProblemBeanVO> problemsList;
	private String loginStatus;
	private ServletContext context;
	private String changedUserName = "false";
	private String feedback = "true"; 

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	private static final org.apache.log4j.Logger log = Logger.getLogger(HomePageAction.class);
	
	
	
	
	public void setServletContext(ServletContext context) {
		
		this.context = context;
	}
	
	public List<SelectOptionVO> getStatesListForLocalBodyElection() {
		return statesListForLocalBodyElection;
	}
	public void setStatesListForLocalBodyElection(
			List<SelectOptionVO> statesListForLocalBodyElection) {
		this.statesListForLocalBodyElection = statesListForLocalBodyElection;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
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
	
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}


	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}	


	public List<StateElectionsVO> getStateElections() {
		return stateElections;
	}


	public void setStateElections(List<StateElectionsVO> stateElections) {
		this.stateElections = stateElections;
	}

	public HttpSession getSession() {
		return session;
	}


	public void setSession(HttpSession session) {
		this.session = session;
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

	public IStatePageService getStatePageService() {
		return statePageService;
	}


	public void setStatePageService(IStatePageService statePageService) {
		this.statePageService = statePageService;
	}

	

	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}


	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}


	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}


	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}
	
	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}


	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}


	public List<ProblemBeanVO> getProblemsList() {
		return problemsList;
	}


	public void setProblemsList(List<ProblemBeanVO> problemsList) {
		this.problemsList = problemsList;
	}


	public String execute()
	{	
		
		request.setAttribute("feedback", feedback);
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user==null){
			loginStatus = "false";
		}
		else{
			loginStatus = "true";
		}
		
		String requestURL = request.getRequestURL().toString();
		System.out.println("................ Request URL :" + requestURL);
		
		String chartPath = getChartPath(requestURL);
		String imagePath = getUserImagesPath(requestURL);
		
		if(session != null && (session.getAttribute("chartPath") == null || session.getAttribute("chartPath") == ""))
			session.setAttribute("chartPath", chartPath);
		
		if(session != null && (session.getAttribute("imagePath") == null || session.getAttribute("imagePath") == ""))
			session.setAttribute("imagePath", imagePath);
		
		statesList = staticDataService.getParticipatedStatesForAnElectionType(new Long(2));
		
		statesListForLocalBodyElection = staticDataService.getParticipatedStatesForAnElectionType(new Long(5)); 
		
		if(statesListForLocalBodyElection == null || statesListForLocalBodyElection.size() == 0)
			statesListForLocalBodyElection.add(new SelectOptionVO(0L,"Select State"));
		
		return Action.SUCCESS;
	}
	
	public String ajaxCallHandler()
	{
		
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(jObj.getString("task").equalsIgnoreCase("getRecentElectionsInState"))
		{
			Long stateId = new Long(jObj.getLong("stateId"));
			stateElections = statePageService.getStateElections(stateId);
		}
		if(jObj.getString("task").equalsIgnoreCase("getAllParliamentConstituencies"))
		{			
			constituenciesList = regionServiceDataImp.getAllParliamentConstituencies(1l, 1l);
		}
		if(jObj.getString("task").equalsIgnoreCase("statesListForLocalBodyElection"))
		{			
			statesListForLocalBodyElection = staticDataService.getParticipatedStatesForAnElectionType(new Long(5)); 
			
			if(statesListForLocalBodyElection == null || statesListForLocalBodyElection.size() == 0)
				statesListForLocalBodyElection.add(new SelectOptionVO(0L,"Select State"));
		}
		return Action.SUCCESS;
	}
	
	 /**
     * Returns the problems list in the state
     *
     * @return    List<ProblemBeanVO>
     */
	
	//Presently not being used in home page.
	public String getProblemsInState()
	{
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
				
			Long stateId = jObj.getLong("stateId");
			LocationwiseProblemStatusInfoVO locationwiseProblemStatusInfoVO = problemManagementReportService.getRecentProblemsWithInTheRegion(IConstants.STATE_LEVEL, stateId , 1l, 25);
			if(locationwiseProblemStatusInfoVO != null)
				problemsList = locationwiseProblemStatusInfoVO.getRecentProblems();
		return SUCCESS;

	}
	
	private String getChartPath(String requestURL){
		
		String chartPath     = "";
				
		String pathSeperator = System.getProperty("file.separator");
		String osDetails     = System.getProperty("os.name");
		
		//chartPath = "/var/www/vsites/partyanalyst.com/httpdocs/charts/";
		
		if(requestURL.contains("www.partyanalyst.com"))
			chartPath = pathSeperator + "var" + pathSeperator + "www" + pathSeperator + "vsites" + pathSeperator + "partyanalyst.com" + pathSeperator + "httpdocs" + pathSeperator + "charts" + pathSeperator;
		else
			chartPath = context.getRealPath("/") + "charts\\";
		
		log.info("Chart Path : " + chartPath + " In " + osDetails + " Environment ");
		
	 return chartPath;
	}
	
	private String getUserImagesPath(String requestURL){
		
		String imagePath = "";
		
		String pathSeperator = System.getProperty("file.separator");
		String osDetails     = System.getProperty("os.name");
		
		if(requestURL.contains("www.partyanalyst.com"))
			imagePath = pathSeperator + "var" + pathSeperator + "www" + pathSeperator + "vsites" + pathSeperator + "partyanalyst.com" + pathSeperator + "httpdocs" + pathSeperator + "pictures" + pathSeperator + "profiles" + pathSeperator;
		else
			imagePath = context.getRealPath("/")+"pictures\\"+IConstants.PROFILE_PIC+"\\";
		
		log.info("Chart Path : " + imagePath + " In " + osDetails + " Environment ");
		
	 return imagePath;
	}

	public String getChangedUserName() {
		return changedUserName;
	}

	public void setChangedUserName(String changedUserName) {
		this.changedUserName = changedUserName;
	}
	

}
