package com.itgrids.partyanalyst.web.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.OpinionPollVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SpecialPageVO;
import com.itgrids.partyanalyst.dto.StateElectionsVO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IOpinionPollService;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.ISpecialPageService;
import com.itgrids.partyanalyst.service.IStatePageService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.util.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * A class representing to display the data related to home page
 *
 * @author  Ashok Dakavaram
 * 
 * @see     com.opensymphony.xwork2.ActionSupport
 */

@SuppressWarnings("serial")
public class HomePageAction extends ActionSupport implements ServletRequestAware,ServletContextAware,ServletResponseAware{

	
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
	private boolean notLogged ;

	private OpinionPollVO opinionPollVO;
	private QuestionsOptionsVO questionsAndChoicesPercentage;
	private Long freeUserConstituencyId;
	private List<SelectOptionVO> states;
	private List<SpecialPageVO> specialPageVOList;
	private ISpecialPageService specialPageService;
	private String homePageLoadingFirstTime;
	private List<File> fileList;
	private List<FileVO> fileVOsList;
	private HttpServletResponse response;

	public List<File> getFileList() {
		return fileList;
	}
	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}



	private List<SelectOptionVO> latestGallariesList;
	
	private Long onlineRegId;
	
	public List<SelectOptionVO> getStates() {
		return states;
	}
	public void setStates(List<SelectOptionVO> states) {
		this.states = states;
	}
	public List<SelectOptionVO> getLatestGallariesList() {
		return latestGallariesList;
	}
	public void setLatestGallariesList(List<SelectOptionVO> latestGallariesList) {
		this.latestGallariesList = latestGallariesList;
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
	
	public boolean isNotLogged() {
		return notLogged;
	}
	public void setNotLogged(boolean notLogged) {
		this.notLogged = notLogged;
	}
	
	public List<FileVO> getFileVOsList() {
		return fileVOsList;
	}
	public void setFileVOsList(List<FileVO> fileVOsList) {
		this.fileVOsList = fileVOsList;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String execute()
	{	
        request.setAttribute("notLogged",notLogged);
        session = request.getSession();
        RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
        if(user == null)
        {
			try {
				response.sendRedirect("index.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
		latestGallariesList = candidateDetailsService.getLatestgallaries();
		resultMap = candidateDetailsService.getPhotosNewsVideosUpdateForACandidate(0,10,"");
		fileList  = candidateDetailsService.getVideosForSelectedParty(IConstants.TDPID);
		
		fileVOsList = candidateDetailsService.getRecentlyUploadedNewsTitles(0, 5, "News Gallary",872L);
		return Action.SUCCESS;
	}
	
	
}
