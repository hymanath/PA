package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.EventGenderVO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.dto.StatesEventVO;
import com.itgrids.partyanalyst.service.IMahanaduDashBoardService1;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class EventDetailsAction1 extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	private JSONObject jObj;
	private String task;
	private List<MahanaduEventVO> resultList;
	private IMahanaduDashBoardService1 mahanaduDashBoardService1;
	private StatesEventVO statesEventVO;
	private EventGenderVO eventGenderVO;
	private MahanaduEventVO mahanaduEventVO;
	
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
	public List<MahanaduEventVO> getResultList() {
		return resultList;
	}
	public void setResultList(List<MahanaduEventVO> resultList) {
		this.resultList = resultList;
	}
	
	public IMahanaduDashBoardService1 getMahanaduDashBoardService1() {
		return mahanaduDashBoardService1;
	}
	public void setMahanaduDashBoardService1(
			IMahanaduDashBoardService1 mahanaduDashBoardService1) {
		this.mahanaduDashBoardService1 = mahanaduDashBoardService1;
	}
	
	
	public StatesEventVO getStatesEventVO() {
		return statesEventVO;
	}
	public void setStatesEventVO(StatesEventVO statesEventVO) {
		this.statesEventVO = statesEventVO;
	}
	
	public EventGenderVO getEventGenderVO() {
		return eventGenderVO;
	}
	public void setEventGenderVO(EventGenderVO eventGenderVO) {
		this.eventGenderVO = eventGenderVO;
	}
	
	public MahanaduEventVO getMahanaduEventVO() {
		return mahanaduEventVO;
	}
	public void setMahanaduEventVO(MahanaduEventVO mahanaduEventVO) {
		this.mahanaduEventVO = mahanaduEventVO;
	}
	public String getLocationWiseVisitorsCount()
	{
		try{
			jObj = new JSONObject(getTask());
			List<Long> subEventIds = new ArrayList<Long>();
			Long eventId = jObj.getLong("eventId");
			//Long stateId = jObj.getLong("stateId");
			Long reportLevelId = jObj.getLong("reportLevelId");
			
			JSONArray arr = jObj.getJSONArray("subEvents");
			for(int i=0;i<arr.length();i++){
				subEventIds.add(new Long(arr.get(i).toString()));
			}
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			String dataRetrievingType = jObj.getString("dataRetrievingType");
			
			Long parentEventId = jObj.getLong("parentEventId");
			String eventType = jObj.getString("eventType");
			
			List<Long> stateIds = new ArrayList<Long>(0);
			JSONArray stateArray= jObj.getJSONArray("stateIds");
			for(int i=0;i<stateArray.length();i++){
				stateIds.add(new Long(stateArray.get(i).toString()));
			}
			String stateType = jObj.getString("stateType");
			
			List<Long> enrollmentYearIds = new ArrayList<Long>(0);
			JSONArray yearIdsArr = jObj.getJSONArray("enrollmentYearIdsArr");
			if(yearIdsArr != null && yearIdsArr.length() > 0){
				for (int i = 0; i < yearIdsArr.length(); i++) {
					enrollmentYearIds.add(Long.parseLong(yearIdsArr.get(i).toString()));
				}
			}
			
			resultList =  mahanaduDashBoardService1.LocationWiseEventAttendeeCounts(startDate,endDate,parentEventId,subEventIds,reportLevelId,stateIds,stateType,enrollmentYearIds);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String stateWiseEventAttendeeCounts(){
		
		try{
			jObj = new JSONObject(getTask());
			
			//String startDate,String endDate,Long parenteventId,List<Long> subEventIds
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			Long parenteventId = jObj.getLong("parentEventId");
			
			List<Long> subEventIds = new ArrayList<Long>();
			JSONArray arr = jObj.getJSONArray("subEventIds");
			for(int i=0;i<arr.length();i++){
				subEventIds.add(new Long(arr.get(i).toString()));
			}
			
			List<Long> enrollmentYearIds = new ArrayList<Long>(0);
			JSONArray yearIdsArr = jObj.getJSONArray("enrollmentYearIdsArr");
			if(yearIdsArr != null && yearIdsArr.length() > 0){
				for (int i = 0; i < yearIdsArr.length(); i++) {
					enrollmentYearIds.add(Long.parseLong(yearIdsArr.getString(i)));
				}
			}
			
			statesEventVO =  mahanaduDashBoardService1.stateWiseEventAttendeeCounts(startDate,endDate,parenteventId,subEventIds,false,enrollmentYearIds);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getAllImages(){
		
		try{
			jObj = new JSONObject(getTask());
			
			
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			Long parenteventId = jObj.getLong("parentEventId");
			
			List<Long> subEventIds = new ArrayList<Long>();
			JSONArray arr = jObj.getJSONArray("subEventIds");
			for(int i=0;i<arr.length();i++){
				subEventIds.add(new Long(arr.get(i).toString()));
			}
			
			List<Long> stateIds = new ArrayList<Long>();
			stateIds.add(1l);
			stateIds.add(36l);
			stateIds.add(0l);
			
			mahanaduDashBoardService1.getAllImages(parenteventId,subEventIds,startDate,endDate,stateIds);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getPublicrepresentatives(){
		
		try{
			jObj = new JSONObject(getTask());
			
			
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			Long eventId = jObj.getLong("parentEventId");
			
			List<Long> subEventIds = new ArrayList<Long>();
			JSONArray arr = jObj.getJSONArray("subEventIds");
			for(int i=0;i<arr.length();i++){
				subEventIds.add(new Long(arr.get(i).toString()));
			}
			
			List<Long> enrollmentYearIds = new ArrayList<Long>(0);
			JSONArray yearIds = jObj.getJSONArray("enrollmentYearIdsArr");
			if(yearIds != null && yearIds.length() > 0){
				for (int i = 0; i < yearIds.length(); i++) {
					enrollmentYearIds.add(Long.parseLong(yearIds.getString(i)));
				}
			}
			resultList = mahanaduDashBoardService1.getPublicrepresentatives(startDate,endDate,eventId,subEventIds,enrollmentYearIds);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String casteWiseEventAttendeeCounts()
	{
		try{
			jObj = new JSONObject(getTask());
			
			Long parentEventId = jObj.getLong("parentEventId");
			List<Long> subEventIds = new ArrayList<Long>();
			JSONArray arr = jObj.getJSONArray("subEvents");
			for(int i=0;i<arr.length();i++){
				subEventIds.add(new Long(arr.get(i).toString()));
			}
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
		
			resultList =  mahanaduDashBoardService1.casteWiseEventAttendeeCounts(startDate,endDate,parentEventId,subEventIds);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	public String ageWiseEventAttendeeCounts()
	{
		try{
			jObj = new JSONObject(getTask());
			
			Long parentEventId = jObj.getLong("parentEventId");
			List<Long> subEventIds = new ArrayList<Long>();
			JSONArray arr = jObj.getJSONArray("subEvents");
			for(int i=0;i<arr.length();i++){
				subEventIds.add(new Long(arr.get(i).toString()));
			}
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
		
			resultList =  mahanaduDashBoardService1.ageWiseEventAttendeeCounts(startDate,endDate,parentEventId,subEventIds);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String genderWiseEventAttendeeCounts()
	{
		try{
			jObj = new JSONObject(getTask());
			
			Long parentEventId = jObj.getLong("parentEventId");
			List<Long> subEventIds = new ArrayList<Long>();
			JSONArray arr = jObj.getJSONArray("subEvents");
			for(int i=0;i<arr.length();i++){
				subEventIds.add(new Long(arr.get(i).toString()));
			}
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
		
			eventGenderVO =  mahanaduDashBoardService1.genderWiseEventAttendeeCounts(startDate,endDate,parentEventId,subEventIds);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	  public String getEventDateAndSubEvent(){
		   
		  try{
				jObj = new JSONObject(getTask());			
				mahanaduEventVO = mahanaduDashBoardService1.getEventDateAndSubEvent(jObj.getLong("eventId"));
		  }catch(Exception e) {
			LOG.error(" Entered Into getEventDateAndSubEvent",e);
		  }
		  return Action.SUCCESS; 
	   }
	  public String casteCategoryWiseEventAttendeeCounts(){
		  
		  try{
				jObj = new JSONObject(getTask());
				
				Long parentEventId = jObj.getLong("parentEventId");
				List<Long> subEventIds = new ArrayList<Long>();
				JSONArray arr = jObj.getJSONArray("subEvents");
				for(int i=0;i<arr.length();i++){
					subEventIds.add(new Long(arr.get(i).toString()));
				}
				String startDate = jObj.getString("startDate");
				String endDate = jObj.getString("endDate");
				resultList=mahanaduDashBoardService1.casteCategoryWiseEventAttendeeCounts(startDate,endDate,parentEventId,subEventIds);
			
	      }catch(Exception e){
	    		LOG.error(" Entered Into casteCategoryWiseEventAttendeeCounts",e);
	      }
		  return Action.SUCCESS;
	  }
}
