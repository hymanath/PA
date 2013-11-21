package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dao.IKeywordDAO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.util.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class NewsPaginationAction  extends ActionSupport implements ServletRequestAware  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	private FileVO fileVO;
	private List<FileVO> fileVOList;
	private ICandidateDetailsService candidateDetailsService;
	//private INewsMonitoringService newsMonitoringService;
	private String level;
	private static final Logger log=Logger.getLogger(NewsPaginationAction.class);
	//private INewsByPagingService newsByPagingService;
	private List<SelectOptionVO> selectOptionVOList,keywordsList,partiesList;
	private LocationVO locationVO;
	private String keyword;
	private String status;
	private IKeywordDAO keywordDAO; 
	
	
	public IKeywordDAO getKeywordDAO() {
		return keywordDAO;
	}
	public void setKeywordDAO(IKeywordDAO keywordDAO) {
		this.keywordDAO = keywordDAO;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public FileVO getFileVO() {
		return fileVO;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setFileVO(FileVO fileVO) {
		this.fileVO = fileVO;
	}
	public List<FileVO> getFileVOList() {
		return fileVOList;
	}
	public void setFileVOList(List<FileVO> fileVOList) {
		this.fileVOList = fileVOList;
	}
  /*public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}*/
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
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	/*public INewsByPagingService getNewsByPagingService() {
		return newsByPagingService;
	}
	public void setNewsByPagingService(INewsByPagingService newsByPagingService) {
		this.newsByPagingService = newsByPagingService;
	}*/
	
	public List<SelectOptionVO> getSelectOptionVOList() {
		return selectOptionVOList;
	}
	public void setSelectOptionVOList(List<SelectOptionVO> selectOptionVOList) {
		this.selectOptionVOList = selectOptionVOList;
	}
	
	public LocationVO getLocationVO() {
		return locationVO;
	}
	public void setLocationVO(LocationVO locationVO) {
		this.locationVO = locationVO;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public List<SelectOptionVO> getKeywordsList() {
		return keywordsList;
	}
	public void setKeywordsList(List<SelectOptionVO> keywordsList) {
		this.keywordsList = keywordsList;
	}
	
	public List<SelectOptionVO> getPartiesList() {
		return partiesList;
	}
	public void setPartiesList(List<SelectOptionVO> partiesList) {
		this.partiesList = partiesList;
	}
	public String execute()throws Exception
	{
		session = request.getSession();
        RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
        if(user == null)
         return ERROR;
        else
		return Action.SUCCESS;
	}
	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}
	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}
	public String getNewsByPaging(){
		  log.debug("Entered into getNewsByPaging() method of NewsPaginationAction");
        session = request.getSession();
        RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
		try{
			  jObj = new JSONObject(getTask());
			}catch (Exception e) {
			  e.printStackTrace();
			  log.error("Exception Occured in ajaxHandler() method, Exception - "+e);
			}
		Long partyId=jObj.getLong("partyId");
		int first=jObj.getInt("firstResult");
		int max=jObj.getInt("maxResult");
		String queryType=jObj.getString("queryType");
		Long stateId=jObj.getLong("stateId");
		Long scopeId=jObj.getLong("scopeId");
		String level=jObj.getString("level");
		String newsType = "Public";
		 if(user.getUserAccessType()!=null)
			 if(user.getUserAccessType().equals("Admin"))
				 newsType = "";
		
		Map<String ,List<FileVO>> resultMapList = new HashMap<String,List<FileVO>>();
		
		fileVOList=new ArrayList<FileVO>();
		resultMapList=candidateDetailsService.getPhotosNewsVideosUpdateForACandidate(first, max,level,newsType);
		if(level.equalsIgnoreCase("state")){
			fileVOList=resultMapList.get("NewsGallary");
		}
		if(level.equalsIgnoreCase("district")){
			fileVOList=resultMapList.get("NewsGallaryForDist");
		}
		
		/*if(first == 1){
			fileVO =new FileVO();
			fileVO.setTitle("TITLE 1");
			fileVO.setDescription("DESC 1");
			fileVOList.add(fileVO);
		
			fileVO =new FileVO();
			fileVO.setTitle("TITLE 2");
			fileVO.setDescription("DESC 2");
			fileVOList.add(fileVO);
		}
		if(first == 40){
			
		
			fileVO =new FileVO();
			fileVO.setTitle("TITLE 3");
			fileVO.setDescription("DESC 3");
			fileVOList.add(fileVO);
		
			fileVO =new FileVO();
			fileVO.setTitle("TITLE 4");
			fileVO.setDescription("DESC 4");
			fileVOList.add(fileVO);
		}
		
		else{
			fileVO =new FileVO();
			fileVO.setTitle("TITLE 5");
			fileVO.setDescription("DESC 5");
			fileVOList.add(fileVO);
		}*/
		return Action.SUCCESS;
	}

	//@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	public String getCandidatesNews(){
		if(log.isDebugEnabled())
			log.debug("In HomePageAction's getCandidatesNews");
		
		try{
			  jObj = new JSONObject(getTask());
			}catch (Exception e) {
			  e.printStackTrace();
			  log.error("Exception Occured in ajaxHandler() method, Exception - "+e);
			}
		
		try{
			fileVOList=new ArrayList<FileVO>();
			if(jObj.getString("task").equalsIgnoreCase("getCandidatesNewsInHomePage"))
			{
			Long candidateId=jObj.getLong("candidateId");
			int frstRcrd=jObj.getInt("firstRecord");
			int maxRcrd=jObj.getInt("maxRecords");
			String type=jObj.getString("type");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			String gallaryIdsStr = jObj.getString("gallaryIds"); 
			String categoryIdsStr = jObj.getString("categoryIds");
			fileVOList=candidateDetailsService.getCandidatesNews(candidateId, frstRcrd, maxRcrd, type,fromDate,toDate,gallaryIdsStr,categoryIdsStr);
			//fileVOList=candidateDetailsService.getCandidatesNewsForHomePage(candidateId, frstRcrd, maxRcrd, type,fromDate,toDate,gallaryIdsStr,categoryIdsStr);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getCandidatesNewsInHomePagePopup"))
			{
				Long candidateId=jObj.getLong("candidateId");
				int frstRcrd=jObj.getInt("firstRecord");
				int maxRcrd=jObj.getInt("maxRecords");
				String type=jObj.getString("type");
				String fromDate = jObj.getString("fromDate");
				String toDate = jObj.getString("toDate");
				
				String categoryIdsStr = jObj.getString("categoryIds");
				//fileVOList=candidateDetailsService.getCandidatesNews(candidateId, frstRcrd, maxRcrd, type,fromDate,toDate,gallaryIdsStr,categoryIdsStr);
				fileVOList=candidateDetailsService.getCandidatesNewsForHomePage(candidateId, frstRcrd, maxRcrd, type,fromDate,toDate,categoryIdsStr);	
			}
		}
		catch (Exception e) {
			log.debug("Exception in HomePageAction's getCandidatesNews -"+e);
		}
		return SUCCESS;
	}
	
	public String getAllCategories()
	{
		try{
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
			 return ERROR;
			String newsType = "Public"; 
			if(user.getUserAccessType() != null && user.getUserAccessType().equalsIgnoreCase("Admin"))
			 newsType = "";
			selectOptionVOList = candidateDetailsService.getTotalCategoriesList(newsType);
			
		}catch (Exception e) {
         e.printStackTrace();
         log.error("Exception Occured in getAllCategories() method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
	
	public String getCategoriesList()
	{
	  try{
		selectOptionVOList = candidateDetailsService.getAllCategories();  
	  }catch (Exception e) {
		 e.printStackTrace();
	         log.error("Exception Occured in getAllCategories() method, Exception - "+e);
	 }
	  return Action.SUCCESS;
	}
	
	public String ajaxHandler()
	{
		try{
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
			 return ERROR;
			String newsType = "Public";
			if(user.getUserAccessType().equalsIgnoreCase("Admin"))
			 newsType = "";
			
			jObj = new JSONObject(getTask());
			
		if(jObj.getString("task").equalsIgnoreCase("getGallariesInCategory") || jObj.getString("task").equalsIgnoreCase("getGallariesInCategory1"))
			selectOptionVOList = candidateDetailsService.getGallariesInCategory(jObj.getLong("categoryId"));
		
		else if(jObj.getString("task").equalsIgnoreCase("getLocationScope"))
		{
		  String accessType = user.getAccessType();
		  Long accessValue = new Long(user.getAccessValue());
		  locationVO = candidateDetailsService.getLocationListForSelectedUser(accessType, accessValue, user.getRegistrationID());
		  
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getKeyWordNews"))
		 fileVOList = candidateDetailsService.getNewsForSelectedKeyword(jObj.getString("keyword"),IConstants.TDPID,newsType,jObj.getInt("startIndex"),jObj.getInt("maxIndex"));
		
		else if(jObj.getString("task").equalsIgnoreCase("getTotalKeyWords"))
		 keywordsList = candidateDetailsService.getTotalKeyWords();
		
		else if(jObj.getString("task").equalsIgnoreCase("getPartyList"))
		 partiesList = candidateDetailsService.getPartiesList();
		
		else if(jObj.getString("task").equalsIgnoreCase("getDesignationsList"))
		 selectOptionVOList = candidateDetailsService.getDesignationsList();
		 
		
		}catch (Exception e) {
			e.printStackTrace();
	         log.error("Exception Occured in ajaxHandler() method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
	
	public String checkIsKeywordExist(){
		try{
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
				return ERROR;

			if(request.getParameter("task").equalsIgnoreCase("addNewKeyword"))
				status = candidateDetailsService.isKeywordExist(user.getRegistrationID(),request.getParameter("keyword"));
			else if(request.getParameter("task").equalsIgnoreCase("mergeKeywords")){
				List<Long> keywordsList = new ArrayList<Long>();
				String keywords = request.getParameter("keywords");
				String[] options = keywords.split(",");
				
				for (String value : options) {
					keywordsList.add(Long.valueOf(value.trim()));
				}
				status = candidateDetailsService.mergeSelectedKeywords(user.getRegistrationID(),keywordsList,request.getParameter("newKeyword"));
			}
		}catch (Exception e) {
			log.debug("exception occured in checkIsKeywordExist(),newsPaginationAction class",e); 
		}
			return Action.SUCCESS;
	}	
	public String getKeywordList(){
		try{
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
				return ERROR;

			if(request.getParameter("task").equalsIgnoreCase("getKeywordsList"))
				keywordsList = candidateDetailsService.getTotalKeyWords();
			
		}catch (Exception e) {
			log.debug("exception occured in checkIsKeywordExist(),newsPaginationAction class",e); 
		}
			return Action.SUCCESS;		
	}
	
}
