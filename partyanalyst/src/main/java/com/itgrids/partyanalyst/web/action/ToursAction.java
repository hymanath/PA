package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.ToursInputVO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.service.IToursService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ToursAction extends ActionSupport implements ServletRequestAware {

	   private final static Logger LOG = Logger.getLogger(ToursAction.class);
	   private HttpServletRequest request;
	   private HttpSession session;
	   private JSONObject jObj;
	   private String task;
	     
	   private IToursService toursService;
	   private ToursInputVO toursInputVO;
	   private ResultStatus	resultStatus;
	   private InputStream	inputStream;
		 private ToursBasicVO resultVO;
		 private List<ToursBasicVO> resultList;
	
		public JSONObject getjObj() {
			return jObj;
		}
		public void setjObj(JSONObject jObj) {
			this.jObj = jObj;
		}
		public String getTask() {
			return task;
		}
		public void setTask(String task) {
			this.task = task;
		}
		public void setServletRequest(HttpServletRequest request) {
			this.request = request;    
		}
		public void setToursService(IToursService toursService) {
			this.toursService = toursService;
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
		public ToursInputVO getToursInputVO() {
			return toursInputVO;
		}   
		public void setToursInputVO(ToursInputVO toursInputVO) {
			this.toursInputVO = toursInputVO;
		}
		public ResultStatus getResultStatus() {
			return resultStatus;
		}
		public void setResultStatus(ResultStatus resultStatus) {
			this.resultStatus = resultStatus;
		}
		public IToursService getToursService() {
			return toursService;
		}
		public ToursBasicVO getResultVO() {
			return resultVO;
		}
		public void setResultVO(ToursBasicVO resultVO) {
			this.resultVO = resultVO;
		}
		public List<ToursBasicVO> getResultList() {
			return resultList;
		}
		public void setResultList(List<ToursBasicVO> resultList) {
			this.resultList = resultList;
		}
		//Business method
		public String execute(){
			return Action.SUCCESS;
		}
		public String savingTourDtlsApplication(){
			try { 
				final HttpSession session = request.getSession();
				/*final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				if(user == null || user.getRegistrationID() == null){
					return ERROR;
				}*/
				  
				Map<File,String> mapfiles = new HashMap<File,String>();
				MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
				Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
				String fileUrl = "" ;
				List<String> filePaths = null;
				while(fileParams.hasMoreElements()){
					String key = fileParams.nextElement();
			   			
					File[] files = multiPartRequestWrapper.getFiles(key);
					filePaths = new ArrayList<String>();
					if(files != null && files.length > 0)
						for(File f : files){
							String[] extension  =multiPartRequestWrapper.getFileNames(key)[0].split("\\.");
							String ext = "";
							if(extension.length > 1){
								ext = extension[extension.length-1];
									mapfiles.put(f,ext);
								}
					   	        
							}
				}  
			     
				resultStatus = toursService.saveTourDtls(toursInputVO,1l,mapfiles);    
	             
				if(resultStatus!=null){
					if(resultStatus.getResultCode() == 0){
						inputStream = new StringBufferInputStream(resultStatus.getMessage());
					}else if(resultStatus.getResultCode() == 1){
						inputStream = new StringBufferInputStream(resultStatus.getMessage());
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception raised at savingNominatedPostProfileApplication", e);
			}
			
			return Action.SUCCESS;
		}
        public String getDesigationList(){
			try {
				jObj = new JSONObject(getTask());
				resultList = toursService.getDesigationList();
			} catch (Exception e) {
				LOG.error("Exception raised at getDesigationList()  of ToursAction", e);
			}
		    return Action.SUCCESS;
		}  
        public String getConstituenciesList(){
			try {
				jObj = new JSONObject(getTask());
				Long stateId = jObj.getLong("stateId");
				resultList = toursService.getConstituenciesList(stateId);
			} catch (Exception e) {
				LOG.error("Exception raised at getConstituenciesList()  of ToursAction", e);
			}
		    return Action.SUCCESS;
		}
        public String getCandidateList(){
		 try{
			jObj = new JSONObject(getTask());
			Long designationId = jObj.getLong("designationId");
			resultList = toursService.getCandidateList(designationId);
		  }catch (Exception e) {
			LOG.error("Exception raised at getCandidateList()  of ToursAction", e);
		}
	    return Action.SUCCESS;
       }
       public String getCandiateDetails(){
    	   try{
    		   jObj = new JSONObject(getTask());
   			   Long candidateId = jObj.getLong("candidateId");
   			   resultVO = toursService.getCandiateDetails(candidateId);
    	   }catch(Exception e){
    		   LOG.error("Exception raised at getCandiateDetails()  of ToursAction", e);  
    	   }
    	   return Action.SUCCESS;
       }
       public String getSearchMembersDetails(){
           try{
             jObj = new JSONObject(getTask());
              Long locationId = jObj.getLong("locationId");
              String searchType = jObj.getString("searchType");
              String searchValue = jObj.getString("searchValue");
              Long designationId =jObj.getLong("designationId");
              resultList = toursService.getSearchMembersDetails(locationId,searchType,searchValue,designationId);
           }catch(Exception e){
             LOG.error("Exception raised at getSearchMembersDetails()  of ToursAction", e);  
           }
           return Action.SUCCESS;
         }
}
