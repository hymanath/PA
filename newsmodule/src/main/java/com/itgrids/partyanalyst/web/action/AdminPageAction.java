package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AdminPageAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private HttpSession session;
	private ICandidateDetailsService candidateDetailsService;
	private String task;
	private JSONObject jObj;
	private String result;
	private List<SelectOptionVO> selectOptionVOList,partiesList;
	
	private IStaticDataService staticDataService;
	private List<SelectOptionVO> parlConstiList,assemConstiList;
	
	
	
	/**
	 * @return the parlConstiList
	 */
	public List<SelectOptionVO> getParlConstiList() {
		return parlConstiList;
	}
	/**
	 * @param parlConstiList the parlConstiList to set
	 */
	public void setParlConstiList(List<SelectOptionVO> parlConstiList) {
		this.parlConstiList = parlConstiList;
	}
	/**
	 * @return the assemConstiList
	 */
	public List<SelectOptionVO> getAssemConstiList() {
		return assemConstiList;
	}
	/**
	 * @param assemConstiList the assemConstiList to set
	 */
	public void setAssemConstiList(List<SelectOptionVO> assemConstiList) {
		this.assemConstiList = assemConstiList;
	}
	/**
	 * @return the staticDataService
	 */
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	/**
	 * @param staticDataService the staticDataService to set
	 */
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	
	
	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}
	

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public List<SelectOptionVO> getSelectOptionVOList() {
		return selectOptionVOList;
	}
	public void setSelectOptionVOList(List<SelectOptionVO> selectOptionVOList) {
		this.selectOptionVOList = selectOptionVOList;
	}
	
	public List<SelectOptionVO> getPartiesList() {
		return partiesList;
	}
	public void setPartiesList(List<SelectOptionVO> partiesList) {
		this.partiesList = partiesList;
	}
	
	public String execute()
	{
		session = request.getSession();	
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		
		
		return Action.SUCCESS;
	}
	
	public String uploadMLCCandidateDetailsAction()
	{ 
	   session = request.getSession();	
	   RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
	   
	   if(user == null)
		return ERROR;
	   
	   selectOptionVOList = candidateDetailsService.getDesignationsList();
	   partiesList = candidateDetailsService.getPartiesList();
	   ConstituencyInfoVO constituencyInfoVO = staticDataService.getConstituenciesByElectionTypeAndStateId(2L,1L);
	   ConstituencyInfoVO parliamantConstis = staticDataService.getConstituenciesByElectionTypeAndStateId(1L,1L);
	   // districtsList =  staticDataService.getDistricts(1l);
	   parlConstiList = parliamantConstis.getConstituencies();
	   assemConstiList = constituencyInfoVO.getConstituencies();
		 
	    return Action.SUCCESS;
		
	}
	
	public String insertMLCCandidateDetails()
	{
		

		try{
			
			 session = request.getSession();	
			 RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			   
			   
			jObj = new JSONObject(getTask());
			
			Long partyId = jObj.getLong("partyId");
			String candidateName = jObj.getString("candidateName");
			String education = jObj.getString("education");
			String gender = jObj.getString("gender");
			Long designationId = jObj.getLong("designationId"); 
			Long locationId = jObj.getLong("locationId");
			Long locationValue = jObj.getLong("locationValue");
			 result   = candidateDetailsService.insertMLCCandidateDetails(partyId ,candidateName ,  education , gender,user.getRegistrationID(),designationId,locationId,locationValue);
			
			if(result.equalsIgnoreCase("success"))
				return Action.SUCCESS;
			else
				return Action.ERROR;

			
			
			}catch (Exception e) {
				e.printStackTrace();
				Log.error("Exception Occured in insertMLCCandidateDetails() method, Exception - "+e);
				return null;
			}
		
	}
	


public String insertChannelDetails()
{
	

	try{
		
		 session = request.getSession();	
		 RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		   
		   
		jObj = new JSONObject(getTask());
		
		
		String channelName = jObj.getString("channelName");
		
		 result   = candidateDetailsService.insertChannelDetails(channelName );
		
		if(result.equalsIgnoreCase("success"))
			return Action.SUCCESS;
		else
			return Action.ERROR;

		
		
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception Occured in insertChannelDetails() method, Exception - "+e);
			return null;
		}
	
}


public String insertObserverDetails()
{
	

	try{
		
		 session = request.getSession();	
		 RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		   
		   
		jObj = new JSONObject(getTask());
		
		
		String observerName = jObj.getString("observerName");
		
		 result   = candidateDetailsService.insertObserverDetails(observerName );
		
		if(result.equalsIgnoreCase("success"))
			return Action.SUCCESS;
		else
			return Action.ERROR;

		
		
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception Occured in insertObserverDetails() method, Exception - "+e);
			return null;
		}
	
}

}

