package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

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
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.INewsMonitoringService;
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
	
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	
	private ICandidateDetailsService candidateDetailsService ;
	private List<SelectOptionVO> constituenciesList;
	
	private String loginStatus;
	private ServletContext context;
	private Map<String, List<FileVO>> resultMap ;
	private String changedUserName = "false";
	private String feedback = "true"; 
   
	private Long problemCount;
	
	private boolean notLogged ;

	private OpinionPollVO opinionPollVO;
//	private QuestionsOptionsVO questionsAndChoicesPercentage;
	private Long freeUserConstituencyId;
	private List<SelectOptionVO> states,candidatesList,candidatesList1;
	
	private String homePageLoadingFirstTime;
	private List<FileVO> fileList;
	private List<FileVO> fileVOsList,responseFilesList;
	private HttpServletResponse response;
	private INewsMonitoringService newsMonitoringService;
	private Long candidateId;
	private Map<Long,String> candidatesMap;
	private String fromDate;
	private String toDate;
	private String gallaryIds;
	private String categoryIds;
	private boolean tempVarable;
	private String candidateName;
	private List<SelectOptionVO> keywordsList;
	private String redirectUrl;
	
	
	private List<SelectOptionVO> gallariesList = new ArrayList<SelectOptionVO>();
	private List<FileVO> galleriesDetails = new ArrayList<FileVO>();
	
	
	public List<SelectOptionVO> getCandidatesList1() {
		return candidatesList1;
	}
	public void setCandidatesList1(List<SelectOptionVO> candidatesList1) {
		this.candidatesList1 = candidatesList1;
	}
	public List<SelectOptionVO> getGallariesList() {
		return gallariesList;
	}
	public void setGallariesList(List<SelectOptionVO> gallariesList) {
		this.gallariesList = gallariesList;
	}
	public List<FileVO> getGalleriesDetails() {
		return galleriesDetails;
	}
	public void setGalleriesDetails(List<FileVO> galleriesDetails) {
		this.galleriesDetails = galleriesDetails;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public List<FileVO> getResponseFilesList() {
		return responseFilesList;
	}
	public void setResponseFilesList(List<FileVO> responseFilesList) {
		this.responseFilesList = responseFilesList;
	}
	public Map<Long, String> getCandidatesMap() {
		return candidatesMap;
	}
	public void setCandidatesMap(Map<Long, String> candidatesMap) {
		this.candidatesMap = candidatesMap;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public INewsMonitoringService getNewsMonitoringService() {
		return newsMonitoringService;
	}
	public void setNewsMonitoringService(
			INewsMonitoringService newsMonitoringService) {
		this.newsMonitoringService = newsMonitoringService;
	}
	public List<FileVO> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}
	

	public List<SelectOptionVO> getCandidatesList() {
		return candidatesList;
	}
	public void setCandidatesList(List<SelectOptionVO> candidatesList) {
		this.candidatesList = candidatesList;
	}


	private List<SelectOptionVO> latestGallariesList,categoriesList,newsGallariesList;
	
	private Long onlineRegId;
	
	public List<SelectOptionVO> getNewsGallariesList() {
		return newsGallariesList;
	}
	public void setNewsGallariesList(List<SelectOptionVO> newsGallariesList) {
		this.newsGallariesList = newsGallariesList;
	}
	public List<SelectOptionVO> getStates() {
		return states;
	}
	public void setStates(List<SelectOptionVO> states) {
		this.states = states;
	}
	
	public List<SelectOptionVO> getCategoriesList() {
		return categoriesList;
	}
	public void setCategoriesList(List<SelectOptionVO> categoriesList) {
		this.categoriesList = categoriesList;
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

	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}


	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
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
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	public String getGallaryIds() {
		return gallaryIds;
	}
	public void setGallaryIds(String gallaryIds) {
		this.gallaryIds = gallaryIds;
	}
	public String getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}
	
	public boolean isTempVarable() {
		return tempVarable;
	}
	public void setTempVarable(boolean tempVarable) {
		this.tempVarable = tempVarable;
	}
	
	
	public List<SelectOptionVO> getKeywordsList() {
		return keywordsList;
	}
	public void setKeywordsList(List<SelectOptionVO> keywordsList) {
		this.keywordsList = keywordsList;
	}
	
	
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String execute()
	{	
        request.setAttribute("notLogged",notLogged);
        session = request.getSession();
        RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
        if(user == null){
         return ERROR;
        }else if(user.getUserRoles().contains("HOMEPAGE")){
        	return Action.SUCCESS;
        }else{
        	redirectUrl = user.getHomeUrl();
        	return "urlToRedirect";      	
        }
	}
	
	public String getResponseFileList(){
		responseFilesList=candidateDetailsService.getLatestResponsedNews();
		return Action.SUCCESS;
	}
	
	public String getGallaryList(){
		 gallariesList = candidateDetailsService.getLatestGalleries();
		return Action.SUCCESS;
	}
	
	public String getCategoryList(){
		categoriesList = candidateDetailsService.getAllCategories(); 
	return Action.SUCCESS;
	}
	
	public String getLatestNews(){
		request.setAttribute("notLogged",notLogged);
        session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
		 String newsType = "Public"; 
		 if(user.getUserAccessType()!=null)
			 if(user.getUserAccessType().equals("Admin"))
				 newsType = "";      
		fileVOsList = candidateDetailsService.getRecentlyUploadedNewsTitles(0, 5, "News Gallary",872L,newsType);
		return Action.SUCCESS;
	}
	
	public String getNews(){
		request.setAttribute("notLogged",notLogged);
        session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
		 String newsType = "Public"; 
		 if(user.getUserAccessType()!=null)
			 if(user.getUserAccessType().equals("Admin"))
				 newsType = "";      
		resultMap = candidateDetailsService.getPhotosNewsVideosUpdateForACandidate(0,10,"",newsType);
		return Action.SUCCESS;
	}
	
	public String getMoreVideos(){
		 request.setAttribute("notLogged",notLogged);
	        session = request.getSession();
	        RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
		try{	 
			 jObj = new JSONObject(getTask());
			 
		   }catch(Exception e){
				 e.printStackTrace(); 
		   }
		 
		 Long partyId = jObj.getLong("partyId");
		 int startRecord = jObj.getInt("startRecord");
		 int maxRecord = jObj.getInt("maxRecord");
		 String queryType = jObj.getString("queryType"); 
		 if(user.getUserAccessType()!=null)
			 if(user.getUserAccessType().equals("Admin"))
				 queryType = "";   
		 fileVOsList = candidateDetailsService.getAllVideosList(partyId, startRecord, maxRecord, queryType);
		 
		 
		return Action.SUCCESS;
	}
	
	public String showMoreVideos(){
		session = request.getSession();
        RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
        if(user == null)
         return ERROR;
        else
		return Action.SUCCESS;
	}
	public String getCandidatesNew(){
		if(log.isDebugEnabled())
			log.debug("In HomePageAction's getCandidates");
		try {
			candidatesList1=new ArrayList<SelectOptionVO>();
			candidatesMap = new HashMap<Long, String>(0);
			
		//	candidatesList=newsMonitoringService.getCandidates();
			//candidatesList=newsMonitoringService.getCandidatesByRemovingDots();
			candidatesList1=newsMonitoringService.getCandidatesNewsCount();
			
			
			for(SelectOptionVO vo:candidatesList1)
				candidatesMap.put(vo.getId(), vo.getName());
			
			candidatesList1.add(new SelectOptionVO(0L," Select Candidate "));
			
		} catch (Exception e) {
			log.debug("Exception in HomePageAction's getCandidates -"+e);
		} 
		
		return SUCCESS;
	}
	public static boolean ASC = true;
	public String showNewsOfCandidatePage(){
		session = request.getSession();
        RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
        if(user == null)
         return ERROR;
        else{
		if(log.isDebugEnabled())
			log.debug("In HomePageAction's getCandidates");
		try {
			candidatesMap=new TreeMap<Long, String>();
			candidatesList=new ArrayList<SelectOptionVO>();
			
			//candidatesList=newsMonitoringService.getCandidates();
			//candidatesList = newsMonitoringService.getCandidatesByRemovingDots();
			candidatesList=newsMonitoringService.getCandidatesNewsCount();
			
			for(SelectOptionVO vo:candidatesList)
				candidatesMap.put(vo.getId(), vo.getName());
			
			 
			candidatesMap = sortByComparator(candidatesMap,ASC);
			
			
			
		} catch (Exception e) {
			log.debug("Exception in HomePageAction's getCandidates -"+e);
		} 
		
		return SUCCESS;
       }
	}
	
	private static Map<Long, String> sortByComparator(Map<Long, String> unsortMap, final boolean order)
    {

        List<Entry<Long, String>> list = new LinkedList<Entry<Long, String>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<Long, String>>()
        {
            public int compare(Entry<Long, String> o1,
                    Entry<Long, String> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<Long, String> sortedMap = new LinkedHashMap<Long, String>();
        for (Entry<Long, String> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
	public String ajaxHandler()
	{
		try{
			session = request.getSession();
	        RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
	        if(user == null)
	         return ERROR;
	        
	        String queryType = "Public"; 
			 if(user.getUserAccessType()!=null)
				 if(user.getUserAccessType().equals("Admin"))
					 queryType = "";     
			 
	        jObj = new JSONObject(getTask());
	        candidatesList = candidateDetailsService.getLocationValuesByRegionScope(jObj.getString("scope"),queryType); 
	        
		}catch (Exception e) {
		 e.printStackTrace();
		 log.error(" Exception Occured in ajaxHandler() method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
	
	
	
	public String ajaxHandler1()
	{
		try{
			session = request.getSession();
	        RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
	        if(user == null)
	         return ERROR;
	        
	        String queryType = "Public"; 
			 if(user.getUserAccessType()!=null)
				 if(user.getUserAccessType().equals("Admin"))
					 queryType = "";     
			 
	        jObj = new JSONObject(getTask());
	        candidatesList = candidateDetailsService.getLocationValuesByRegionScope1(jObj.getString("scope"),queryType); 
	        
		}catch (Exception e) {
		 e.printStackTrace();
		 log.error(" Exception Occured in ajaxHandler() method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
	
}
