package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.service.IToursService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ToursAction extends ActionSupport implements ServletRequestAware {

	   private final static Logger LOG = Logger.getLogger(ToursAction.class);
	
		 private HttpServletRequest request;
		 private JSONObject jObj;
		 private String task;
	     
		 private IToursService toursService;
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
		public void setServletRequest(HttpServletRequest arg0) {
			this.request = request;
		}
		public void setToursService(IToursService toursService) {
			this.toursService = toursService;
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
   			   resultList = toursService.getSearchMembersDetails(locationId,searchType,searchValue);
    	   }catch(Exception e){
    		   LOG.error("Exception raised at getSearchMembersDetails()  of ToursAction", e);  
    	   }
    	   return Action.SUCCESS;
       }
}
