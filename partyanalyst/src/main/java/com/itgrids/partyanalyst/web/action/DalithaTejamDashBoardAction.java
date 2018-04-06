package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.DalithaTejamInputVo;
import com.itgrids.partyanalyst.dto.DalithaTejamVO;
import com.itgrids.partyanalyst.dto.EventLocationVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IDalithaTejamDashBoardService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DalithaTejamDashBoardAction extends ActionSupport implements	ServletRequestAware {

	private HttpServletRequest request;
	private JSONObject jObj;
	private String task;
	private IDalithaTejamDashBoardService dalithaTejamDashBoardService;
	private List<DalithaTejamVO> dalithaTejamList;
	private List<EventLocationVO> jbDataList;
	
	
	public List<EventLocationVO> getJbDataList() {
		return jbDataList;
	}

	public void setJbDataList(List<EventLocationVO> jbDataList) {
		this.jbDataList = jbDataList;
	}

	public IDalithaTejamDashBoardService getDalithaTejamDashBoardService() {
		return dalithaTejamDashBoardService;
	}

	public void setDalithaTejamDashBoardService(IDalithaTejamDashBoardService dalithaTejamDashBoardService) {
		this.dalithaTejamDashBoardService = dalithaTejamDashBoardService;
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
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	
	public List<DalithaTejamVO> getDalithaTejamList() {
		return dalithaTejamList;
	}

	public void setDalithaTejamList(List<DalithaTejamVO> dalithaTejamList) {
		this.dalithaTejamList = dalithaTejamList;
	}

	public String dalithTejamDashBoard()
	{
		try {
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
		      return "input";
		    }
			 boolean noaccess = false;
			    List<String> entitlements = null;
			    if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			      entitlements = regVO.getEntitlements();
			      if(!(entitlements.contains("DALITHA_TEJAM_ADMIN_USER_ENTITLEMENT") || entitlements.contains("DALITHA_TEJAM_ADMIN_USER_GROUP_ENTITLEMENT"))){
			        noaccess = true ;
			      }

			    if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			      noaccess = false;
			    }
			    if(noaccess){
			      return "error";
			    }
			    }    
		}catch(Exception e) {
			LOG.error("Exception raised at execute() in CoreDashBoard Action class", e);
		}
		return Action.SUCCESS;
	
	}
	
	public String getImagesFordalithatejam(){
		
		try{
			jObj = new JSONObject(getTask());
			dalithaTejamList= new ArrayList<DalithaTejamVO>();
			DalithaTejamInputVo inputVo =new DalithaTejamInputVo();
		
			for(int i=0 ; i< jObj.getJSONArray("levelValues").length() ; i++){
				
				inputVo.getLocationValues().add(Long.valueOf(jObj.getJSONArray("levelValues").get(i).toString()));
			}
			for(int i=0 ; i< jObj.getJSONArray("levelIds").length() ; i++){
				inputVo.getLocationScopeIds().add(Long.valueOf(jObj.getJSONArray("levelIds").get(i).toString()));
			}
			inputVo.setFromDate(jObj.getString("startDateStr"));
			inputVo.setToDate(jObj.getString("endDateStr"));
			inputVo.setActivityId(jObj.getLong("activityId"));
			
			dalithaTejamList= dalithaTejamDashBoardService.getLatestImages(inputVo);
		}catch(Exception e){
			LOG.error("Exception Occured In getImagesFordalithatejam method "+e);		
		}
		return Action.SUCCESS;
		
	}

	public String getDateWiseCount(){

		try{
			jObj = new JSONObject(getTask());
			dalithaTejamList= new ArrayList<DalithaTejamVO>();
			DalithaTejamInputVo inputVo =new DalithaTejamInputVo();
		
			inputVo.setLocationValue(jObj.getLong("levelValue"));
			inputVo.setLocationScopeId(jObj.getLong("levelId"));
			inputVo.setFromDate(jObj.getString("startDateStr"));
			inputVo.setToDate(jObj.getString("endDateStr"));
			inputVo.setActivityId(jObj.getLong("activityId"));
			
			dalithaTejamList= dalithaTejamDashBoardService.getDateWiseCount(inputVo);
		}catch(Exception e){
			LOG.error("Exception Occured In getImagesFordalithatejam method "+e);		
		}
		return Action.SUCCESS;
	}
	
	public String DalithTejamLocationWiseData(){
		try {
			LOG.info("Entered into activitiesDistrictWiseCohort()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long locationScopeId = jObj.getLong("locationScopeId");
			Long activityId= jObj.getLong("activityId");
			jbDataList = dalithaTejamDashBoardService.DalithTejamLocationWiseData(fromDate, toDate,locationScopeId,activityId,jObj.getLong("locationValue"));
		} catch (Exception e) {
			LOG.error("Exception raised at activitiesDistrictWiseCohort() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
		
	}
}
