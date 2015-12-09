package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.service.IActivityService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Srishailam Pittala 
 *
 */
public class ActivityAction extends ActionSupport implements ServletRequestAware{

	private static final Logger         		LOG = Logger.getLogger(ActivityAction.class);
	private HttpServletRequest         			request;
	private HttpSession 						session;
	private IActivityService                    activityService;
	private JSONObject							jObj;
	private String 								task;
	private ActivityVO							activityVO;
	
	private BasicVO 							basicVO = new BasicVO();
	private List<IdNameVO>  					idNameVOList;
	
	
	public BasicVO getBasicVO() {
		return basicVO;
	}
	public void setBasicVO(BasicVO basicVO) {
		this.basicVO = basicVO;
	}
	public List<IdNameVO> getIdNameVOList() {
		return idNameVOList;
	}
	public void setIdNameVOList(List<IdNameVO> idNameVOList) {
		this.idNameVOList = idNameVOList;
	}
	public ActivityVO getActivityVO() {
		return activityVO;
	}
	public void setActivityVO(ActivityVO activityVO) {
		this.activityVO = activityVO;
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
	public IActivityService getActivityService() {
		return activityService;
	}
	public void setActivityService(IActivityService activityService) {
		this.activityService = activityService;
	}
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
	
	public String execute()
	{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		
		basicVO = activityService.getActivityTypeList();
		idNameVOList = activityService.getActivityLevelsList();
				
		return Action.SUCCESS;
	}
	
	public String getActivityDetailsBySearchCriteria()
	{
		
		try {
			/*RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			boolean noaccess = false;
			if(regVO == null)
				return Action.ERROR;*/
			
			jObj = new JSONObject(getTask());
			String searchType = jObj.getString("searchType");
			Long locationId = jObj.getLong("locationId");
			Long locationValue = jObj.getLong("stateId");
			Long activityLevelId = jObj.getLong("activityLevelId");
			Long activityScopeId = jObj.getLong("activityScopeId");
			String conditionType = jObj.getString("conditionType");
			String startDateStr = jObj.getString("startDate");
			String endDateStr = jObj.getString("endDate");
			
			
			SearchAttributeVO searchVO = new SearchAttributeVO();			
			searchVO.setTypeId(locationValue);
			searchVO.setSearchType(searchType);
			searchVO.setLocationId(locationId);
			searchVO.setLocationValue(locationValue);
			searchVO.getAttributesIdsList().add(activityScopeId);
			searchVO.setLevelId(activityLevelId);
			searchVO.setConditionType(conditionType);
			try {
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				searchVO.setStartDate(format.parse(startDateStr));
				searchVO.setEndDate(format.parse(endDateStr));
			} catch (Exception e) {}
			
			activityVO = activityService.getActivityDetailsBySearchCriteria(searchVO);
			
		} catch (Exception e) {
			LOG.error("Exception occured when loading getActivityDetailsBySearchCriteria() ActivityAction Controller... ",e);
		}
		return Action.SUCCESS;
	}
}
