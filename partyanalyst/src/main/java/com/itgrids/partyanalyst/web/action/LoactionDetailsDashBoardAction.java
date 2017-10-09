package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.core.api.service.ILoactionDetailsDashBoardService;
import com.itgrids.partyanalyst.dto.PartyMeetingDataVO;
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
	private List<PartyMeetingDataVO> partyMeetingDataVOs;
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
	
	public List<PartyMeetingDataVO> getPartyMeetingDataVOs() {
		return partyMeetingDataVOs;
	}

	public void setPartyMeetingDataVOs(List<PartyMeetingDataVO> partyMeetingDataVOs) {
		this.partyMeetingDataVOs = partyMeetingDataVOs;
	}

	public String getMeetingTypeWiseTotalMeetings(){
		LOG.info("Entered into getMeetingTypeWiseTotalMeetings()  of LoactionDetailsDashBoardAction");
		try{
			jObj = new JSONObject(getTask());
			Long locationLevel = jObj.getLong("locationLevel");
			String fromDateStr = jObj.getString("fromDateStr");
			String toDateStr = jObj.getString("toDateStr");
			List<Long> locationIdList = new ArrayList<Long>();
			JSONArray locationIdArr=jObj.getJSONArray("locationIds");
			if(locationIdArr!=null &&  locationIdArr.length()>0){
				for( int i=0;i<locationIdArr.length();i++){
					locationIdList.add(Long.valueOf(locationIdArr.getString(i)));
				}
			}
			partyMeetingsVOs = loactionDetailsDashBoardService.getMeetingTypeWiseTotalMeetings(locationLevel,locationIdList,fromDateStr,toDateStr);
			
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
			String fromDateStr = jObj.getString("fromDateStr");
			String toDateStr = jObj.getString("toDateStr");
			List<Long> locationIdList = new ArrayList<Long>();
			JSONArray locationIdArr=jObj.getJSONArray("locationIds");
			if(locationIdArr!=null &&  locationIdArr.length()>0){
				for( int i=0;i<locationIdArr.length();i++){
					locationIdList.add(Long.valueOf(locationIdArr.getString(i)));
				}
			}
			partyMeetingsVOs = loactionDetailsDashBoardService.getMeetingLevelWiseTotalMeetings(locationLevel,locationIdList,fromDateStr,toDateStr);
			
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
			String fromDateStr = jObj.getString("fromDateStr");
			String toDateStr = jObj.getString("toDateStr");
			List<Long> locationIdList = new ArrayList<Long>();
			JSONArray locationIdArr=jObj.getJSONArray("locationIds");
			if(locationIdArr!=null &&  locationIdArr.length()>0){
				for( int i=0;i<locationIdArr.length();i++){
					locationIdList.add(Long.valueOf(locationIdArr.getString(i)));
				}
			}
			partyMeetingsVOs = loactionDetailsDashBoardService.getCommitteeMeetingStatistics(locationLevel,locationIdList,fromDateStr,toDateStr);
			
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
			String fromDateStr = jObj.getString("fromDateStr");
			String toDateStr = jObj.getString("toDateStr");
			List<Long> locationIdList = new ArrayList<Long>();
			JSONArray locationIdArr=jObj.getJSONArray("locationIds");
			if(locationIdArr!=null &&  locationIdArr.length()>0){
				for( int i=0;i<locationIdArr.length();i++){
					locationIdList.add(Long.valueOf(locationIdArr.getString(i)));
				}
			}
			partyMeetingsVOs = loactionDetailsDashBoardService.getSpecialMeetingStatistics(locationLevel,locationIdList,fromDateStr,toDateStr);
			
		}catch(Exception e){
			LOG.error("Exception raised at getSpecialMeetingStatistics() method of LoactionDetailsDashBoardAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getBelowLevelMeetingConductedCount(){
		LOG.info("Entered into getBelowLevelMeetingConductedCount()  of LoactionDetailsDashBoardAction");
		try{
			jObj = new JSONObject(getTask());
			Long locationLevel = jObj.getLong("locationLevel");
			String fromDateStr = jObj.getString("fromDateStr");
			String toDateStr = jObj.getString("toDateStr");
			List<Long> locationIdList = new ArrayList<Long>();
			JSONArray locationIdArr=jObj.getJSONArray("locationIds");
			if(locationIdArr!=null &&  locationIdArr.length()>0){
				for( int i=0;i<locationIdArr.length();i++){
					locationIdList.add(Long.valueOf(locationIdArr.getString(i)));
				}
			}
			partyMeetingsVOs = loactionDetailsDashBoardService.getBelowLevelMeetingConductedCount(locationLevel,locationIdList,fromDateStr,toDateStr);
			
		}catch(Exception e){
			LOG.error("Exception raised at getBelowLevelMeetingConductedCount() method of LoactionDetailsDashBoardAction", e);
		}
		return Action.SUCCESS;
	}
	public String getMeetingsDetails(){
		LOG.info("Entered into getMeetingsDetails()  of LoactionDetailsDashBoardAction");
		try{
			jObj = new JSONObject(getTask());
			Long belowlocationId = jObj.getLong("belowlocationId");
			Long locationLevel = jObj.getLong("locationLevel");
			String fromDateStr = jObj.getString("fromDateStr");
			String toDateStr = jObj.getString("toDateStr");
			List<Long> locationIdList = new ArrayList<Long>();
			JSONArray locationIdArr=jObj.getJSONArray("locationIds");
			if(locationIdArr!=null &&  locationIdArr.length()>0){
				for( int i=0;i<locationIdArr.length();i++){
					locationIdList.add(Long.valueOf(locationIdArr.getString(i)));
				}
			}
			String meetingType = jObj.getString("meetingType");
			partyMeetingDataVOs = loactionDetailsDashBoardService.getMeetingsDetails(belowlocationId,locationLevel,locationIdList,fromDateStr,toDateStr,meetingType);
			
		}catch(Exception e){
			LOG.error("Exception raised at getBelowLevelMeetingConductedCount() method of LoactionDetailsDashBoardAction", e);
		}
		return Action.SUCCESS;
	}
}