package com.itgrids.partyanalyst.web.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AdvVideoVO;
import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.CustomPageVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.ImportantDatesVO;
import com.itgrids.partyanalyst.dto.MetaInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SpecialPageVO;
import com.itgrids.partyanalyst.service.IAdvVideoService;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.ISpecialPageService;
import com.itgrids.partyanalyst.service.impl.DateService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SpecialPageAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {

	private static final long serialVersionUID = -957791701984246754L;
	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ResultStatus result;
	private HttpSession session;
	private List<SpecialPageVO> specialPageList;
	private JSONObject jObj;
	private String task = null;
	private String task1 = null;
	private Long specialPageId;
	private List<String> descriptions;
	private SpecialPageVO specialPageVO;
	private List<FileVO> fileVOList;
	private List<String> pathVOList;
	private ISpecialPageService specialPageService;
	private List<CustomPageVO> customPages;
	private MetaInfoVO metaInfoVO;
	private Long contentId;
	private String isAdmin;
	private Boolean isSubscribed;
	private ICandidateDetailsService candidateDetailsService;
	private String delVideo;
	private String specilaPageText;
	private List<ImportantDatesVO> importantCandidatesList;
	private String specialPageLoadingFirstTime;
	private IAdvVideoService advVideoService;
	private List<AdvVideoVO> advVideosList;
    
    
	public List<AdvVideoVO> getAdvVideosList() {
		return advVideosList;
	}
	public void setAdvVideosList(List<AdvVideoVO> advVideosList) {
		this.advVideosList = advVideosList;
	}
	public IAdvVideoService getAdvVideoService() {
		return advVideoService;
	}
	public void setAdvVideoService(IAdvVideoService advVideoService) {
		this.advVideoService = advVideoService;
	}
	public List<ImportantDatesVO> getImportantCandidatesList() {
		return importantCandidatesList;
	}

	public void setImportantCandidatesList(
			List<ImportantDatesVO> importantCandidatesList) {
		this.importantCandidatesList = importantCandidatesList;
	}

	public String getSpecilaPageText() {
		return specilaPageText;
	}

	public void setSpecilaPageText(String specilaPageText) {
		this.specilaPageText = specilaPageText;
	}

	public String getDelVideo() {
		return delVideo;
	}

	public void setDelVideo(String delVideo) {
		this.delVideo = delVideo;
	}

	public List<String> getPathVOList() {
		return pathVOList;
	}

	public void setPathVOList(List<String> pathVOList) {
		this.pathVOList = pathVOList;
	}

	public String getTask1() {
		return task1;
	}

	public void setTask1(String task1) {
		this.task1 = task1;
	}

	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public Boolean getIsSubscribed() {
		return isSubscribed;
	}

	public void setIsSubscribed(Boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
	
	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public MetaInfoVO getMetaInfoVO() {
		return metaInfoVO;
	}

	public void setMetaInfoVO(MetaInfoVO metaInfoVO) {
		this.metaInfoVO = metaInfoVO;
	}

	public List<CustomPageVO> getCustomPages() {
		return customPages;
	}

	public void setCustomPages(List<CustomPageVO> customPages) {
		this.customPages = customPages;
	}

	public void setFileVOList(List<FileVO> fileVOList) {
		this.fileVOList = fileVOList;
	}

	public List<FileVO> getFileVOList() {
		return fileVOList;
	}

	public void setSpecialPageVO(SpecialPageVO specialPageVO) {
		this.specialPageVO = specialPageVO;
	}

	public SpecialPageVO getSpecialPageVO() {
		return specialPageVO;
	}

	public Long getSpecialPageId() {
		return specialPageId;
	}

	public void setSpecialPageId(Long specialPageId) {
		this.specialPageId = specialPageId;
	}
	
	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}
	
	public List<SpecialPageVO> getSpecialPageList() {
		return specialPageList;
	}

	public void setSpecialPageList(List<SpecialPageVO> specialPageList) {
		this.specialPageList = specialPageList;
	}

	public ISpecialPageService getSpecialPageService() {
		return specialPageService;
	}

	public void setSpecialPageService(ISpecialPageService specialPageService) {
		this.specialPageService = specialPageService;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
		this.result = result;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public String execute() {
		
		session = request.getSession();
		if(!("tdpserver".equalsIgnoreCase(IConstants.DEPLOYED_HOST))){
			if(session.getAttribute("specialPageLoadingFirstTime")== null){
				if(contentId == null){
				   session.setAttribute("specialPageLoadingFirstTime", "true");
				}
			}else{ 
				session.setAttribute("specialPageLoadingFirstTime", "false");
			}
		}else{
			session.setAttribute("specialPageLoadingFirstTime", "false");
		}
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		
		if(regVO != null){
			isAdmin = regVO.getIsAdmin();
			Long regId = regVO.getRegistrationID();
			String page="sPage";
			isSubscribed = candidateDetailsService.getSubscriptionDetails(regId,specialPageId,page);
			
		}
		
		descriptions  = specialPageService.getSpecialPageDescription(specialPageId);
		specialPageVO = specialPageService.getSpecialPageBasicDetails(specialPageId);
		fileVOList    = specialPageService.getVideoGalleryBasedOnSpecialPageId(specialPageId, 0, 20);
		customPages   = specialPageService.getCustomPagesOfASpecialPage(specialPageId);
		metaInfoVO    = specialPageService.getMetaInfoForASpecialPage(specialPageId);
		specilaPageText = specialPageService.getSpecialPageDataBySpecialPageId(specialPageId);
		advVideosList = advVideoService.getTopAdvVideosForDisplaying();	
		
	return SUCCESS;
	}
	

	public String setEmailAlertsForEvent() {
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		/*session = request.getSession();
		RegistrationVO registrationVO = (RegistrationVO) session
				.getAttribute("USER");*/
		String email = jObj.getString("emailId");

		result = specialPageService.subScribeEmailAlertForAEvent(email, jObj
				.getLong("specialPageId"));
		return Action.SUCCESS;
	}
	
	public String getSpecialPageHighLights()
	{
		try{
			jObj = new JSONObject(getTask());
			
			if(jObj.getString("task").equalsIgnoreCase("getHighLights"))
			{
				String specialPageId = jObj.getString("specialPageId");
				fileVOList = specialPageService.getSpecialPageHighLights(new Long(specialPageId));	
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	public String ajaxCallHandlerForSpecialPage(){
		
		try {
			jObj = new JSONObject(getTask());
			
			if(jObj.getString("task").equalsIgnoreCase("getNewsToDisplay"))
			{
				fileVOList = specialPageService.getNewsGalleryBasedOnSpecialPageId(jObj.getLong("specialPageId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),jObj.getString("queryType"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getNewsToDisplayForPagination"))
			{
				fileVOList = specialPageService.getNewsGalleryBasedOnSpecialPageId(jObj.getLong("specialPageId"),Integer.parseInt(request.getParameter("startIndex")),Integer.parseInt(request.getParameter("resultsCount")),jObj.getString("queryType"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getFirstThreePhotoGallaryDetail"))
			{
				fileVOList = specialPageService.getPhotoGalleryBasedOnSpecialPageId(jObj.getLong("specialPageId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getPhotoGallaryWithOutGallerySizeZero"))
			{
				fileVOList = specialPageService.getSpecialPageGallaryDetailWithOutGallerySizeZero(jObj.getLong("specialPageId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.PHOTO_GALLARY);
			}
			else if(jObj.getString("task").equalsIgnoreCase("videoGalleriesForASpecialPage")){
				
				fileVOList = specialPageService.getVideoGalleryBasedOnSpecialPageId(jObj.getLong("specialPageId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"));
			}
	        else if(jObj.getString("task").equalsIgnoreCase("getSpecialPageGallaryDetail")){
				
	        	fileVOList = specialPageService.getSpecialPageGallaryDetail(jObj.getLong("specialPageId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.VIDEO_GALLARY);
			}
	        else if(jObj.getString("task").equalsIgnoreCase("getFirstThreePhotoRecords"))
			{
	        	fileVOList = specialPageService.getFirstThreePhotoGallaryRecords(jObj.getLong("specialPageId"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
	}

	public String getLatestVideosForSpecialPage(){
		
		fileVOList = specialPageService.getVideoGalleryBasedOnSpecialPageId(jObj.getLong("specialPageId"),jObj.getInt("startIndex"),jObj.getInt("maxRecords"));
		
		return Action.SUCCESS;
	}
       public String getVideosForRemove(){
		
	// specialPageService.getExpiredVideosList();
		
		return Action.SUCCESS;
	}
       
       public String ajaxCallHandlerForRemoveYoutubeVideo(){
   		 System.out.println("insideajaxcall");
   		try {
   			jObj = new JSONObject(getTask());
   			        
   		       String stDate=jObj.getString("fromDate").trim();
   		       String edDate=jObj.getString("endDate").trim();
   		    Date startDate=null;;
   		 Date endDate=null;
   		       if(stDate !=null && edDate!=null)
   		       {
   		         startDate= getDate(stDate);
   		        endDate=   getDate(edDate);
   			//	fileVOList = specialPageService.getNewsGalleryBasedOnSpecialPageId(jObj.getLong("specialPageId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),jObj.getString("queryType"));
   			
   		   //  pathVOList=	specialPageService.getExpiredVideosList(startDate, endDate);
   	            
   		        Map<String,List<?>> result=specialPageService.getYoutubeVideosList(startDate, endDate);
   		   if(result !=null){
   		        
   	    pathVOList=(List<String>)result.get("filepaths");
   	    List<Long> preLanguageIds =( List<Long> ) result.get("languageIdsNotChecked");
   	  
   	 if( ( pathVOList ==null || pathVOList.size()<1) &&  preLanguageIds!= null)
   		specialPageService.updateLastUpdateDateInFilePaths(preLanguageIds);
   	   
   	   session=request.getSession();
   		  if(session!=null);  
   		     session.setAttribute("languageIdsToUpdate",(List<Long>) result.get("languageIdsNotChecked"));
   		}
   		   }
   		}catch (ParseException e) {
   			e.printStackTrace();
   		}
   		
       
   		
   		return Action.SUCCESS;
   		
   	}
       public String ajaxCallHandlerForDeleteYoutubeVideo(){
      		List<String> filePaths =new ArrayList<String>();
      		int[] results;
      		try {
      			jObj = new JSONObject(getTask1());
      			        
      		 JSONArray    jArray =jObj.getJSONArray("filePaths");
      		    
      		           
      		for (int i = 0; i < jArray.length(); i++) 
			{
      			filePaths.add((String)jArray.get(i));        	
								
			}     
      		    			if(filePaths.size()>0)
      		    	
      		    				if(session != null)
      		    				  results =	specialPageService.deleteExpiredVideosList(filePaths,(List<Long>)session.getAttribute("languageIdsToUpdate"));
      	         	        		      			
      		    			
      		} catch (ParseException e) {
      			e.printStackTrace();
      		}
      
      		return Action.SUCCESS;
      		
      	}
       public Date getDate(String dateStr){
    	 /* Date date=null;
    		try{
    		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
    		date = (Date)formatter.parse(dateStr);
    		
    		}catch(Exception e){
    			e.printStackTrace();
    			}    		
    		return date;*/
    	   
    	
    			  String[] dateArray =  dateStr.split("-");
    			  Calendar cal = Calendar.getInstance(); 
    			  cal.set(Integer.parseInt(dateArray[0]),Integer.parseInt(dateArray[1])-1, Integer.parseInt(dateArray[2]));
    			  return cal.getTime();
    		  
    	   }
       
       
       
       public String getImportantCandidatesInfoAction(){
    	   
    	   try {
   			jObj = new JSONObject(getTask());
   			
   		   } catch (ParseException e) {
   			e.printStackTrace();
   		  }
    	   
    	   Long electionId = jObj.getLong("electionId");
    	   
    	   importantCandidatesList = specialPageService.getImportantCandidatesInfoByElectionId(electionId);
    	   
    	   return Action.SUCCESS;
    	   
       }
}
