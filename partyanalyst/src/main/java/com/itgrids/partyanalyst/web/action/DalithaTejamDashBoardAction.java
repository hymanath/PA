package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.DalithaTejamInputVo;
import com.itgrids.partyanalyst.dto.DalithaTejamVO;
import com.itgrids.partyanalyst.service.IDalithaTejamDashBoardService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DalithaTejamDashBoardAction extends ActionSupport implements	ServletRequestAware {

	private HttpServletRequest request;
	private JSONObject jObj;
	private String task;
	private IDalithaTejamDashBoardService dalithaTejamDashBoardService;
	private List<DalithaTejamVO> dalithaTejamList;
	
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
			
			final HttpSession session = request.getSession();
			
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
				
				inputVo.getLocationValue().add(Long.valueOf(jObj.getJSONArray("levelValues").get(i).toString()));
			}
			for(int i=0 ; i< jObj.getJSONArray("levelIds").length() ; i++){
				inputVo.getLocationScopeId().add(Long.valueOf(jObj.getJSONArray("levelIds").get(i).toString()));
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

	
}
