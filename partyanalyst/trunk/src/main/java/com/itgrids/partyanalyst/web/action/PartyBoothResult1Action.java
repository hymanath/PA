package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.BoothResultVO;
import com.itgrids.partyanalyst.excel.PartyBoothPerformanceVO;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyBoothResult1Action extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String task=null;
	private String partyName;
	private String electionType;
	private String electionYear;
	JSONObject jObj;
	private PartyBoothPerformanceVO result;
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	private List<SelectOptionVO> constituencyVOs;
	private HttpServletResponse response;
	
	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public List<SelectOptionVO> getConstituencyVOs() {
		return constituencyVOs;
	}

	public void setConstituencyVOs(List<SelectOptionVO> constituencyVOs) {
		this.constituencyVOs = constituencyVOs;
	}

	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}

	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
	}

	public PartyBoothPerformanceVO getResult() {
		return result;
	}

	public void setResult(PartyBoothPerformanceVO result) {
		this.result = result;
	}
	
	public void setServletResponse(HttpServletResponse response){
	    this.response = response;
	  }

	  public HttpServletResponse getServletResponse(){
	    return response;
	  }

	public String execute()throws Exception{
			
			String param=this.getTask();	
			System.out.println(param);
			if(param != null)
			{
				try {
					jObj = new JSONObject(param);
					System.out.println(jObj);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String partyId=jObj.getString("partyId");
				String electionTypeId=jObj.getString("electionType");
				String electionYear=jObj.getString("electionYear");
				System.out.println(partyId+"======="+electionTypeId+"============"+electionYear);
				constituencyVOs = partyBoothWiseResultsService.getConstituenciesForParty(new Long(partyId),new Long(electionTypeId),electionYear);
				
			}
			else
			{
				constituencyVOs = partyBoothWiseResultsService.getConstituenciesForParty(new Long(partyName),new Long(electionType),electionYear);
			}		
			
			setConstituencyVOs(constituencyVOs);
			System.out.println("Before party ************ action"+constituencyVOs.size());

			
		return Action.SUCCESS;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
