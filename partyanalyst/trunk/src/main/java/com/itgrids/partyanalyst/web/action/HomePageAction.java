package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.LocationwiseProblemStatusInfoVO;
import com.itgrids.partyanalyst.dto.OpinionPollVO;
import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SpecialPageVO;
import com.itgrids.partyanalyst.dto.StateElectionsVO;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IOpinionPollService;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.ISpecialPageService;
import com.itgrids.partyanalyst.service.IStatePageService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.SpecialPageService;
import com.itgrids.partyanalyst.util.IWebConstants;
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

	
	private static final org.apache.log4j.Logger log = Logger.getLogger(HomePageAction.class); 
	
	private HttpServletRequest request;
	private List<SelectOptionVO> statesList,statesListForLocalBodyElection;
	private IStaticDataService staticDataService;
	private List<StateElectionsVO> stateElections;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	private IStatePageService statePageService;
	private IRegionServiceData regionServiceDataImp;
	private ICandidateDetailsService candidateDetailsService ;
	private List<SelectOptionVO> constituenciesList;
	private IProblemManagementReportService problemManagementReportService;
	private List<ProblemBeanVO> problemsList;
	private String loginStatus;
	private ServletContext context;
	private Map<String, List<FileVO>> resultMap ;
	private String changedUserName = "false";
	private String feedback = "true"; 
    private IProblemManagementService problemManagementService;
	private Long problemCount;
	private IOpinionPollService opinionPollService;
	private IAnanymousUserService ananymousUserService;
	private QuestionsOptionsVO questionsOptionsVO;
	

	private OpinionPollVO opinionPollVO;
	private QuestionsOptionsVO questionsAndChoicesPercentage;
	private Long freeUserConstituencyId;
	private List<SelectOptionVO> states;
	private List<SpecialPageVO> specialPageVOList;
	private ISpecialPageService specialPageService;
	private String homePageLoadingFirstTime;
	
	private Long onlineRegId;
	
	public List<SelectOptionVO> getStates() {
		return states;
	}
	public void setStates(List<SelectOptionVO> states) {
		this.states = states;
	}


	private String chartProducerURL="/var/www/vsites/partyanalyst.com/httpdocs/charts/";
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	
	
	public OpinionPollVO getOpinionPollVO() {
		return opinionPollVO;
	}

	public void setOpinionPollVO(OpinionPollVO opinionPollVO) {
		this.opinionPollVO = opinionPollVO;
	}

	public IOpinionPollService getOpinionPollService() {
		return opinionPollService;
	}

	public void setOpinionPollService(IOpinionPollService opinionPollService) {
		this.opinionPollService = opinionPollService;
	}


	
	public void setResultMap(Map<String, List<FileVO>> resultMap) {
		this.resultMap = resultMap;
	}

	public Map<String, List<FileVO>> getResultMap() {
		return resultMap;
	}

	public void setCandidateDetailsService(ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getChangedUserName() {
		return changedUserName;
	}

	public void setChangedUserName(String changedUserName) {
		this.changedUserName = changedUserName;
	}

	
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

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}

	public Long getProblemCount() {
		return problemCount;
	}

	public void setProblemCount(Long problemCount) {
		this.problemCount = problemCount;
	}
	
	public Long getFreeUserConstituencyId() {
		return freeUserConstituencyId;
	}

	public void setFreeUserConstituencyId(Long freeUserConstituencyId) {
		this.freeUserConstituencyId = freeUserConstituencyId;
	}

	public IAnanymousUserService getAnanymousUserService() {
		return ananymousUserService;
	}

	public void setAnanymousUserService(IAnanymousUserService ananymousUserService) {
		this.ananymousUserService = ananymousUserService;
	}
	
	public QuestionsOptionsVO getQuestionsOptionsVO() {
		return questionsOptionsVO;
	}
	public void setQuestionsOptionsVO(QuestionsOptionsVO questionsOptionsVO) {
		this.questionsOptionsVO = questionsOptionsVO;
	}
	
	public List<SpecialPageVO> getSpecialPageVOList() {
		return specialPageVOList;
	}
	public void setSpecialPageVOList(List<SpecialPageVO> specialPageVOList) {
		this.specialPageVOList = specialPageVOList;
	}
		
	public ISpecialPageService getSpecialPageService() {
		return specialPageService;
	}
	public void setSpecialPageService(ISpecialPageService specialPageService) {
		this.specialPageService = specialPageService;
	}
	
	
	public Long getOnlineRegId() {
		return onlineRegId;
	}
	public void setOnlineRegId(Long onlineRegId) {
		this.onlineRegId = onlineRegId;
	}
	public String execute()
	{	
		
		
		request.setAttribute("feedback", feedback);
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(session.getAttribute("homePageLoadingFirstTime")== null)
			   session.setAttribute("homePageLoadingFirstTime", "true");
		else 
			session.setAttribute("homePageLoadingFirstTime", "false");
		
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
		
		problemCount = problemManagementService.getProblemsCount();
		resultMap = candidateDetailsService.getPhotosNewsVideosUpdateForACandidate(0,15);
		/*if(user != null && user.getUserType().equalsIgnoreCase(IConstants.FREE_USER)){
			freeUserConstituencyId = ananymousUserService.getUserConstituencyId(user.getRegistrationID());
		}
		*/
		
		specialPageVOList = specialPageService.getSpecialPageListForHomePage();
		
		
		
		
		String cPath = request.getContextPath();
		
						
				Cookie[] cookieArray = request.getCookies();
				
				boolean availabiity = false;
				
				
				if(cookieArray == null)
				{
					opinionPollVO = opinionPollService.getAllPollsForTheDay();
					opinionPollVO.setAvaliability(availabiity);
				
				}
				else
				{
				for(int i=0;i<cookieArray.length;i++){
					 Cookie cookie = cookieArray[i];
					 if(cookie.getName().equalsIgnoreCase("hasPolled")){
						 availabiity = true;
					 }
				}
				
				if(availabiity){
					
					opinionPollVO = opinionPollService.getDetailsOfTheLatestOpinionPoll();
					opinionPollVO.setAvaliability(availabiity);
					/*opinionPollVO = opinionPollService.getAllPollsForTheDay()
					questionsAndChoicesPercentage = opinionPollVO.getQuestionsOptionsVO();
					String chartName = "opinionPoll_questionId_"+questionsAndChoicesPercentage.getQuestionId()+".png";
					String chartPath1 = "";
					
					if(cPath.contains("PartyAnalyst"))
			        	 chartPath = context.getRealPath("/")+ "charts\\" + chartName;	
			        
			        else
					 chartPath = IWebConstants.CHART_URL_IN_SERVER + chartName;
			        questionsAndChoicesPercentage.setImagePath(chartName);
					ChartProducer.createBarChartForVotesPoll(questionsAndChoicesPercentage.getQuestion(), "", "", createDataset(questionsAndChoicesPercentage), chartPath1,"votesPoll");*/
				}
				
				else
				{
					opinionPollVO = opinionPollService.getAllPollsForTheDay();
					opinionPollVO.setAvaliability(availabiity);
			
			
				}
				
		
				}
				if(onlineRegId != null)
				{
					session.removeAttribute("loginStatus");
					session.removeAttribute("checkedTypeValue");
					session.removeAttribute("HiddenCount");
					session.removeAttribute("UserName");
					session.removeAttribute(IConstants.USER);
					session.removeAttribute(IWebConstants.FREE_USER_ROLE);
					return "cadreOnlineReg";
				} 
		
		
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
		
		if(jObj.getString("task").equalsIgnoreCase("getStates"))
		{	
			Long electionTypeId = new Long(jObj.getLong("electionType"));
			states = staticDataService.getParticipatedStatesForAnElectionType(electionTypeId);
		}
		if(jObj.getString("task").equalsIgnoreCase("getStates"))
		{	
			Long electionTypeId = new Long(jObj.getLong("electionType"));
			states = staticDataService.getParticipatedStatesForAnElectionType(electionTypeId);
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
		
		//chartPath = IWebConstants.CHART_URL_IN_SERVER;
		
		if(requestURL.contains("www.partyanalyst.com"))
			chartPath = IWebConstants.CHART_URL_IN_SERVER;
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
			imagePath = IWebConstants.STATIC_CONTENT_FOLDER_URL + "pictures" + pathSeperator + "profiles" + pathSeperator;
		else
			imagePath = context.getRealPath("/")+"pictures\\"+IConstants.PROFILE_PIC+"\\";
		
		log.info("Chart Path : " + imagePath + " In " + osDetails + " Environment ");
		
	 return imagePath;
	}
	
	
	private CategoryDataset createDataset(QuestionsOptionsVO questionsAndChoicesPercentage) {       
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(OptionVO result: questionsAndChoicesPercentage.getOptions()){        
        		dataset.addValue(new BigDecimal(result.getPercentage()), result.getOption(), result.getOption());       	
        }
        return dataset;
    }
	
	public String getQuestionAndPercentageOfVotesForChoices(){
		
		try{
		
		jObj = new JSONObject(getTask());
		}catch(Exception e){
			e.printStackTrace();			
		}
		
		Long questionId = jObj.getLong("questionId");
		
		questionsOptionsVO = opinionPollService.getQuestionAndPercentageOfVotesForChoices(questionId);
		
		return Action.SUCCESS;
		
	}	
	
	public String getAllSpecialPageListForHomePage()
	{
		try{
		jObj = new JSONObject(getTask());	
		}catch (Exception e) {
			e.printStackTrace();
		}
		specialPageVOList = specialPageService.getAllSpecialPageListForHomePage();
		return Action.SUCCESS;
	}
	
}
