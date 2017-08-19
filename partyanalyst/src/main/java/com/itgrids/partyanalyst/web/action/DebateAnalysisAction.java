package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.DebateCandidateCharcVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IDebateAnalysisService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DebateAnalysisAction extends ActionSupport implements ServletRequestAware {


	private static final long serialVersionUID = 303202624274758166L;


	private static final Logger 		LOG = Logger.getLogger(DebateAnalysisAction.class); 
	
	private HttpServletRequest 			request;
	private HttpSession 				session;

	private JSONObject 					jObj;
	private String 						task;
	private DebateCandidateCharcVO candidateCharcDetails;	
	private IDebateAnalysisService debateAnalysisService;
	
	
	
	
	
	public void setServletRequest(HttpServletRequest request) {
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
		
	public DebateCandidateCharcVO getCandidateCharcDetails() {
		return candidateCharcDetails;
	}

	public void setCandidateCharcDetails(
			DebateCandidateCharcVO candidateCharcDetails) {
		this.candidateCharcDetails = candidateCharcDetails;
	}	

	public IDebateAnalysisService getDebateAnalysisService() {
		return debateAnalysisService;
	}

	public void setDebateAnalysisService(
			IDebateAnalysisService debateAnalysisService) {
		this.debateAnalysisService = debateAnalysisService;
	}

	public String execute()
	{
		return Action.SUCCESS;
	}

	
	public String getDebateCandidateCharacteristicsDetailsByParty()
	{
		try{
			LOG.info(" Entered into  getDebateCandidateCharacteristicsDetailsByParty(). ");
			HttpSession session = request.getSession();
			RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
			if(regVo == null)
				return Action.ERROR;
			 
			jObj=new JSONObject(getTask());
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			 String startDate = jObj.getString("fromDate");
			 String endDate = jObj.getString("toDate");
			
			 JSONArray channelArr = jObj.getJSONArray("channelIdArr");
			
			 List<Long> channelIds = new ArrayList<Long>(0);
				
				if( channelArr != null && channelArr.length() >0 )
				{
					for(int i=0;i<channelArr.length();i++)
					{
						if(channelArr.get(i) != null)
						{
							String channelId = channelArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
							
							if(channelId.length() > 0 && !channelId.equalsIgnoreCase("0"))
							{
								channelIds.add(Long.valueOf(channelId));
							}
						}
					}
				}
				
				if(channelIds != null && channelIds.size() == 1)
				{
					Long channelId = channelIds.get(0);
					
					if(channelId.longValue() == 0L)
					{
						channelIds = null;
					}
				}
				
				else if(channelIds.size() == 0)
				{
					channelIds = null;						
				}
				
				
			 JSONArray partyArr = jObj.getJSONArray("partyIdArr");
				
				List<Long> partyIds = new ArrayList<Long>(0);
				
				if( partyArr != null && partyArr.length() >0 )
				{
					for(int i=0;i<partyArr.length();i++)
					{
						if(partyArr.get(i) != null)
						{
							String partyId = partyArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
							
							if(partyId.length() > 0 && !partyId.equalsIgnoreCase("0"))
							{
								partyIds.add(Long.valueOf(partyId));
							}
						}
					}
				}
				
				if(partyIds != null && partyIds.size() == 1)
				{
					Long partyId = partyIds.get(0);
					
					if(partyId.longValue() == 0L)
					{
						partyIds = null;
					}
				}
				else if(partyIds.size() == 0)
				{
					partyIds = null;						
				}
				
			 JSONArray candidateArr = jObj.getJSONArray("candidateIdArr");
			 
			 List<Long> candidateIds = new ArrayList<Long>(0);
				
				if( candidateArr != null && candidateArr.length() >0 )
				{
					for(int i=0;i<candidateArr.length();i++)
					{
						if(candidateArr.get(i) != null)
						{
							String candidateId = candidateArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
							
							if(candidateId.length() > 0 && !candidateId.equalsIgnoreCase("0"))
							{
								candidateIds.add(Long.valueOf(candidateId));
							}
						}
					}
				}
				
				if(candidateIds != null && candidateIds.size() == 1)
				{
					Long candidateId = candidateIds.get(0);
					
					if(candidateId.longValue() == 0L)
					{
						candidateIds = null;
					}
				}
				
				else if(candidateIds.size() == 0)
				{
					candidateIds = null;						
				}
				Long stateId = jObj.getLong("stateId");
				candidateCharcDetails = debateAnalysisService.getPartyWiseCandidateCharacteristicsDetails(sdf.parse(startDate),sdf.parse(endDate),channelIds,partyIds,candidateIds,stateId);
			//candidateCharcDetails = debateAnalysisService.getPartyWiseCandidateCharacteristicsDetails();
		}
		catch (Exception e){
			LOG.error(" Exception occured in getDebateCandidateCharacteristicsDetailsByParty() ",e);
		}
		return Action.SUCCESS;
	}
	
	
}
