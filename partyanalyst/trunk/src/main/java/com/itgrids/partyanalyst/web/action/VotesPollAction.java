package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.OpinionPollVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IOpinionPollService;
import com.opensymphony.xwork2.ActionSupport;

public class VotesPollAction extends ActionSupport implements
		ServletRequestAware {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(VotesPollAction.class);
	
	private HttpServletRequest request;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	
	private IOpinionPollService opinionPollService;
	
	private OpinionPollVO opinionPollVO;
		
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

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
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

	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}
	
	public String execute () throws Exception 
	{
		return SUCCESS;
	}
	
	public String getAllPollsForTheDay()
	{
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				log.debug("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(jObj.getString("task").equals("getAllPolls")){
				opinionPollVO = opinionPollService.getAllPollsForTheDay();
			}
		}
		return SUCCESS;		
	}
	
	public String saveSelectedPoll()
	{
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				log.debug("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(jObj.getString("task").equals("saveSelectedPoll")){
				 opinionPollService.saveSelectionResultOfThePoll(jObj.getLong("questionId"),jObj.getLong("selectedPollId"));
			}
		}
		return SUCCESS;		
	}
	
}
