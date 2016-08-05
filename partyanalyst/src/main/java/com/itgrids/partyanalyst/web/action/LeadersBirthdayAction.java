package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BirthDayDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IBirthDayDetailsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class LeadersBirthdayAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5174234213925199862L;
	private HttpServletRequest 					request;
	private HttpSession 						session;
	private String task;
	private JSONObject jObj;
	private BirthDayDetailsVO birthDayDetailsVO;
	private String status;
	private List<BirthDayDetailsVO> birthDaysList;

	private IBirthDayDetailsService birthDayDetailsService;

	
	public List<BirthDayDetailsVO> getBirthDaysList() {
		return birthDaysList;
	}

	public void setBirthDaysList(List<BirthDayDetailsVO> birthDaysList) {
		this.birthDaysList = birthDaysList;
	}

	/**
	 *
	 * @return
	 */public JSONObject getjObj() {
			return jObj;
		}

		public void setjObj(JSONObject jObj) {
			this.jObj = jObj;
		}
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public BirthDayDetailsVO getBirthDayDetailsVO() {
			return birthDayDetailsVO;
		}

		public void setBirthDayDetailsVO(BirthDayDetailsVO birthDayDetailsVO) {
			this.birthDayDetailsVO = birthDayDetailsVO;
		}

		public IBirthDayDetailsService getBirthDayDetailsService() {
			return birthDayDetailsService;
		}

		public void setBirthDayDetailsService(IBirthDayDetailsService birthDayDetailsService) {
			this.birthDayDetailsService = birthDayDetailsService;
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

		public String getTask() {
			return task;
		}

		public void setTask(String task) {
			this.task = task;
		}

		

		public void setServletRequest(HttpServletRequest request) {
			this.request = request;
		}

		public String execute() {

			return Action.SUCCESS;
		}

	
	public String getLeadersBirthDayDetails() {
		try {
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			
		jObj = new JSONObject(getTask());
		Long occasionTypeId = jObj.getLong("occasionTypeId");
		String searchType = jObj.getString("searchType");
		String memberTypeStr = jObj.getString("memberTypeGlobal");
		Long userId = regVO.getRegistrationID();
		
		if(memberTypeStr.trim().equalsIgnoreCase("0"))
			birthDaysList = birthDayDetailsService.getLeaderOccasionDetails(occasionTypeId,searchType,null,userId);
		else
		birthDaysList = birthDayDetailsService.getLeaderOccasionDetails(occasionTypeId,searchType,memberTypeStr,userId);
		
		} catch (Exception e) {
			LOG.error("Exception occured in getLeadersBirthDayDetails() of LeadersBirthdayAction", e);
		}
		return Action.SUCCESS;
	}

	public String getWishingDetails(){
		try {
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			jObj = new JSONObject(getTask());
			Long userId = regVO.getRegistrationID();
			status = birthDayDetailsService.getWishingDetails(jObj.getLong("searchId"),userId);

		} catch (Exception e) {
			LOG.error("Exception occured in getWishingDetails() of LeadersBirthdayAction", e);
		}
		return Action.SUCCESS;
		
	 }

}
