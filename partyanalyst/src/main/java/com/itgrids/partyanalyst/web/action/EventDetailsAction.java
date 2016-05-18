package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UserEventDetailsVO;
import com.itgrids.partyanalyst.service.IMahaNaduService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class EventDetailsAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	JSONObject jObj = null;
	private String task = null;
	private IMahaNaduService mahaNaduService;
	private ResultStatus resultStatus;
	private List<UserEventDetailsVO> subEvents;
	public List<MahanaduEventVO> resultList;
	private HttpSession session;
	private Long eventId;
	private List<IdNameVO> idnameVOList;

	
	
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public List<MahanaduEventVO> getResultList() {
		return resultList;
	}
	public void setResultList(List<MahanaduEventVO> resultList) {
		this.resultList = resultList;
	}
	
	public List<UserEventDetailsVO> getSubEvents() {
		return subEvents;
	}
	public void setSubEvents(List<UserEventDetailsVO> subEvents) {
		this.subEvents = subEvents;
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
	
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public IMahaNaduService getMahaNaduService() {
		return mahaNaduService;
	}
	public void setMahaNaduService(IMahaNaduService mahaNaduService) {
		this.mahaNaduService = mahaNaduService;
	}
	public List<IdNameVO> getIdnameVOList() {
		return idnameVOList;
	}
	public void setIdnameVOList(List<IdNameVO> idnameVOList) {
		this.idnameVOList = idnameVOList;
	}
	public String execute()
	{
		
		HttpSession session = request.getSession();
		if(session.getAttribute(IConstants.USER) == null)
			return INPUT;
		return Action.SUCCESS;
		
	}
	
	public String insertEventInfo()
	{
		try{
			
			jObj = new JSONObject(getTask());
			
			List<Long> subEventIds = new ArrayList<Long>();
			
			org.json.JSONArray arr = jObj.getJSONArray("subEvents");
			for(int i=0;i<arr.length();i++){
				subEventIds.add(new Long(arr.get(i).toString()));
			}
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			 Date eventStrDate = null;
			 Date eventEndDate = null;
			 if(startDate != null && !startDate.isEmpty()){
				 eventStrDate = format.parse(startDate);
			 }
			 
			 if(endDate != null && !endDate.isEmpty()){
				 eventEndDate = format.parse(endDate); 
			 }
			
			 Long parentEventId = jObj.getLong("parentEventId");
			 
			resultStatus = mahaNaduService.insertDataintoEventInfo1(eventStrDate,eventEndDate,parentEventId,subEventIds);
		}
		catch(Exception e)
		{
			LOG.error(" Entered Into insertEventInfo",e);
		}
		return Action.SUCCESS;
	}
	public String getSubEventDetails()
	{
		try{
			jObj = new JSONObject(getTask());
			List<Long> subEventIds = new ArrayList<Long>();
			org.json.JSONArray arr = jObj.getJSONArray("subEvents");
			for(int i=0;i<arr.length();i++)
			subEventIds.add(new Long(arr.get(i).toString()));
			
			resultList = mahaNaduService.getSubEventCount(jObj.getLong("parentEventId"),subEventIds,jObj.getString("startDate"),jObj.getString("endDate"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	
	
	public String getLocationWiseVisitorsCount()
	{
		try{
			jObj = new JSONObject(getTask());
			List<Long> subEventIds = new ArrayList<Long>();
			Long eventId = jObj.getLong("eventId");
			Long stateId = jObj.getLong("stateId");
			Long reportLevelId = jObj.getLong("reportLevelId");
			
			org.json.JSONArray arr = jObj.getJSONArray("subEvents");
			for(int i=0;i<arr.length();i++){
				subEventIds.add(new Long(arr.get(i).toString()));
			}
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			String dataRetrievingType = jObj.getString("dataRetrievingType");
			
			Long parentEventId = jObj.getLong("parentEventId");
			String eventType = jObj.getString("eventType");
			resultList =  mahaNaduService.getEventInfoByReportType(eventId,stateId,reportLevelId,subEventIds,startDate,endDate,dataRetrievingType,parentEventId,eventType);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getHourWiseSubEventsCount()
	{
		try{
			jObj = new JSONObject(getTask());
			Long parentEventId = jObj.getLong("parentEventId");
			List<Long> subEventIds = new ArrayList<Long>();
			org.json.JSONArray arr = jObj.getJSONArray("subEvents");
			for(int i=0;i<arr.length();i++)
			subEventIds.add(new Long(arr.get(i).toString()));
			if(jObj.getString("startDate").equalsIgnoreCase(jObj.getString("endDate")))
			resultList =  mahaNaduService.getHourWiseSubEventsCount(parentEventId,subEventIds,jObj.getString("startDate"));
			else
				resultList =  mahaNaduService.getDayWiseSubEventsCount(parentEventId,subEventIds,jObj.getString("startDate"),jObj.getString("endDate"));	
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	
	public String getEventMemberCount()
	{
		try{
			jObj = new JSONObject(getTask());
			Long parentEventId = jObj.getLong("parentEventId");
			//Long stateId = jObj.getLong("stateId");
		//	Long reportLevelId = jObj.getLong("reportLevelId");
			List<Long> subEventIds = new ArrayList<Long>();
			org.json.JSONArray arr = jObj.getJSONArray("subEvents");
			for(int i=0;i<arr.length();i++)
			subEventIds.add(new Long(arr.get(i).toString()));
			resultList =  mahaNaduService.getEventMembersCount(parentEventId,subEventIds,jObj.getString("startDate"),jObj.getString("endDate"));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getEventsForUser()
	{
		try{
		 jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			resultList = mahaNaduService.getEventsForUser(regVO.getRegistrationID());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	
	public String getSubEventsByParentEvent()
	{
		try{
		 jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			resultList = mahaNaduService.getSubEvent(jObj.getLong("eventId"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getSubEventMemberDetails()
	{
		try{
		 jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			resultList = mahaNaduService.getMembersDetailsBySubEvent(jObj.getLong("eventId"),jObj.getString("startDate"),jObj.getString("endDate"),jObj.getInt("startIndex"),jObj.getInt("maxIndex"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getEventAttendeeSummary(){
		try{
			LOG.debug(" Entered Into getEventAttendeeSummary");
			
			
			jObj = new JSONObject(getTask());
			List<Long> subEventIds = new ArrayList<Long>();
			Long eventId = jObj.getLong("eventId");
			Long stateId = jObj.getLong("stateId");
			Long reportLevelId = jObj.getLong("reportLevelId");
			org.json.JSONArray arr = jObj.getJSONArray("subEvents");
			for(int i=0;i<arr.length();i++){
				subEventIds.add(new Long(arr.get(i).toString()));
			}
			resultList =  mahaNaduService.getAttendeeSummaryForEvents(eventId,stateId,reportLevelId,subEventIds,jObj.getString("startDate"),jObj.getString("endDate"));
			
			
		}catch (Exception e) {
			LOG.error(" Entered Into getEventAttendeeSummary",e);
		}
		return Action.SUCCESS;
	}
	
	public String getOtherStateDeligatesCount()
	{
		try{
			
			jObj = new JSONObject(getTask());
			Long parentEventId = jObj.getLong("parentEventId");
		
			List<Long> subEventIds = new ArrayList<Long>();
			org.json.JSONArray arr = jObj.getJSONArray("subEvents");
			for(int i=0;i<arr.length();i++)
			subEventIds.add(new Long(arr.get(i).toString()));
			resultList =  mahaNaduService.getOtherStateDeligatesCount(parentEventId,subEventIds,jObj.getString("startDate"),jObj.getString("endDate"));
			
			
		}
		catch (Exception e) {
			LOG.error(" Entered Into getOtherStateDeligatesCount",e);
		}
		return Action.SUCCESS;
	}
	public String getStatewiseCount()
	{
		try{
			
			jObj = new JSONObject(getTask());
			
			resultList =  mahaNaduService.getStatewiseCount(jObj.getLong("eventId"),jObj.getString("startDate"),jObj.getString("endDate"));
			
			
		}
		catch (Exception e) {
			LOG.error(" Entered Into getOtherStateDeligatesCount",e);
		}
		return Action.SUCCESS;
	}
	
	public String getEventAttendeeSummaryList(){
		try{
			LOG.debug(" Entered Into getEventAttendeeSummary");
			
			
			jObj = new JSONObject(getTask());
			List<Long> subEventIds = new ArrayList<Long>();
			Long eventId = jObj.getLong("eventId");
			Long stateId = jObj.getLong("stateId");
			Long reportLevelId = jObj.getLong("reportLevelId");
			org.json.JSONArray arr = jObj.getJSONArray("subEvents");
			for(int i=0;i<arr.length();i++){
				subEventIds.add(new Long(arr.get(i).toString()));
			}
			resultList =  mahaNaduService.getAttendeeSummaryForEventsList(eventId,stateId,reportLevelId,subEventIds);
			
			
		}catch (Exception e) {
			LOG.error(" Entered Into getEventAttendeeSummaryList",e);
		}
		return Action.SUCCESS;
	}
	
	public String getSubEventsOfEvent(){
		try{			
			jObj = new JSONObject(getTask());			
			idnameVOList = mahaNaduService.getSubEventsOfEvent(jObj.getLong("eventId"));
		}catch (Exception e) {
			LOG.error(" Entered Into getMainEntryOfEvent",e);
		}
		return Action.SUCCESS;
	}
	
	public String getDaysUniqueAndRevisitSummary(){
		
		try{			
			jObj = new JSONObject(getTask());
			List<Long> subEventIds = new ArrayList<Long>();
			Long eventId = jObj.getLong("eventId");
			Long stateId = jObj.getLong("stateId");
			Long reportLevelId = jObj.getLong("reportLevelId");
			org.json.JSONArray arr = jObj.getJSONArray("subEvents");
			for(int i=0;i<arr.length();i++){
				subEventIds.add(new Long(arr.get(i).toString()));
			}			
			resultList = mahaNaduService.getDaysUniqueAndRevisitSummary(eventId,stateId,reportLevelId,subEventIds);
						
		}catch (Exception e) {
			LOG.error(" Entered Into getDaysUniqueAndRevisitSummary",e);
		}
		return Action.SUCCESS;		
	}
	
	public String getDayWiseVisitSummary(){
		try{
			jObj = new JSONObject(getTask());
			List<Long> subEventIds = new ArrayList<Long>();
			Long eventId = jObj.getLong("eventId");
			Long stateId = jObj.getLong("stateId");
			Long reportLevelId = jObj.getLong("reportLevelId");
			org.json.JSONArray arr = jObj.getJSONArray("subEvents");
			for(int i=0;i<arr.length();i++){
				subEventIds.add(new Long(arr.get(i).toString()));
			}			
			resultList = mahaNaduService.getDayWiseVisitSummary(eventId,stateId,reportLevelId,subEventIds);
			
		}catch (Exception e) {
			LOG.error(" Entered Into getDayWiseVisitSummary",e);
		}
		return Action.SUCCESS;
	}
	//mahanaduCadreVisitNewInfo.jsp  ajax call
	public String getSubEventsOfNewEvent(){
		try{			
			jObj = new JSONObject(getTask());			
			idnameVOList = mahaNaduService.getSubEventsOfNewEvent(jObj.getLong("eventId"));
		}catch (Exception e) {
			LOG.error(" Entered Into getSubEventsOfNewEvent",e);
		}
		return Action.SUCCESS;
	}
	
	
}
