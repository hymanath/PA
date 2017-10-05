package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.core.api.service.ILoactionDetailsDashBoardService;
import com.itgrids.partyanalyst.dto.PartyMeetingsVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class LoactionDetailsDashBoardAction extends ActionSupport implements ServletRequestAware {
	private final static Logger LOG = Logger.getLogger(LoactionDetailsDashBoardAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private JSONObject jObj;
	private String task;
	
	private List<PartyMeetingsVO> partyMeetingsVOs;
	
	private ILoactionDetailsDashBoardService loactionDetailsDashBoardService;
	
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

	public void setLoactionDetailsDashBoardService(
			ILoactionDetailsDashBoardService loactionDetailsDashBoardService) {
		this.loactionDetailsDashBoardService = loactionDetailsDashBoardService;
	}
	
	public List<PartyMeetingsVO> getPartyMeetingsVOs() {
		return partyMeetingsVOs;
	}

	public void setPartyMeetingsVOs(List<PartyMeetingsVO> partyMeetingsVOs) {
		this.partyMeetingsVOs = partyMeetingsVOs;
	}

	public String getMeetingTypeWiseTotalMeetings(){
		LOG.info("Entered into getMeetingTypeWiseTotalMeetings()  of LoactionDetailsDashBoardAction");
		try{
			jObj = new JSONObject(getTask());
			Long locationLevel = jObj.getLong("locationLevel");
			Long locationId = jObj.getLong("locationId");
			String fromDateStr = jObj.getString("fromDateStr");
			String toDateStr = jObj.getString("toDateStr");
			partyMeetingsVOs = loactionDetailsDashBoardService.getMeetingTypeWiseTotalMeetings(locationLevel,locationId,fromDateStr,toDateStr);
			
		}catch(Exception e){
			LOG.error("Exception raised at getMeetingTypeWiseTotalMeetings() method of LoactionDetailsDashBoardAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getMeetingLevelWiseTotalMeetings(){
		LOG.info("Entered into getMeetingLevelWiseTotalMeetings()  of LoactionDetailsDashBoardAction");
		try{
			jObj = new JSONObject(getTask());
			Long locationLevel = jObj.getLong("locationLevel");
			Long locationId = jObj.getLong("locationId");
			String fromDateStr = jObj.getString("fromDateStr");
			String toDateStr = jObj.getString("toDateStr");
			partyMeetingsVOs = loactionDetailsDashBoardService.getMeetingLevelWiseTotalMeetings(locationLevel,locationId,fromDateStr,toDateStr);
			
		}catch(Exception e){
			LOG.error("Exception raised at getMeetingLevelWiseTotalMeetings() method of LoactionDetailsDashBoardAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getCommitteeMeetingStatistics(){
		LOG.info("Entered into getCommitteeMeetingStatistics()  of LoactionDetailsDashBoardAction");
		try{
			jObj = new JSONObject(getTask());
			Long locationLevel = jObj.getLong("locationLevel");
			Long locationId = jObj.getLong("locationId");
			String fromDateStr = jObj.getString("fromDateStr");
			String toDateStr = jObj.getString("toDateStr");
			partyMeetingsVOs = loactionDetailsDashBoardService.getCommitteeMeetingStatistics(locationLevel,locationId,fromDateStr,toDateStr);
			
		}catch(Exception e){
			LOG.error("Exception raised at getCommitteeMeetingStatistics() method of LoactionDetailsDashBoardAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getSpecialMeetingStatistics(){
		LOG.info("Entered into getSpecialMeetingStatistics()  of LoactionDetailsDashBoardAction");
		try{
			jObj = new JSONObject(getTask());
			Long locationLevel = jObj.getLong("locationLevel");
			Long locationId = jObj.getLong("locationId");
			String fromDateStr = jObj.getString("fromDateStr");
			String toDateStr = jObj.getString("toDateStr");
			partyMeetingsVOs = loactionDetailsDashBoardService.getSpecialMeetingStatistics(locationLevel,locationId,fromDateStr,toDateStr);
			
		}catch(Exception e){
			LOG.error("Exception raised at getSpecialMeetingStatistics() method of LoactionDetailsDashBoardAction", e);
		}
		return Action.SUCCESS;
	}
	
}