package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.service.IBiElectionPageService;
import com.opensymphony.xwork2.ActionSupport;

public class ByeElectionResultsAnalysisAction extends ActionSupport implements
ServletRequestAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ByeElectionResultsAnalysisAction.class);
	private ServletContext context;
	HttpServletRequest request;
	private String task;
	org.json.JSONObject jObj;
	private List<ConstituencyVO> constituencywiseResults;
	private IBiElectionPageService biElectionPageService;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	
	public void setServletContext(ServletContext context) {
		this.context = context;		
	}


	public ServletContext getContext() {
		return context;
	}


	public void setContext(ServletContext context) {
		this.context = context;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setTask(String task) {
		this.task = task;
	}


	public String getTask() {
		return task;
	}


	public void setConstituencywiseResults(List<ConstituencyVO> constituencywiseResults) {
		this.constituencywiseResults = constituencywiseResults;
	}


	public List<ConstituencyVO> getConstituencywiseResults() {
		return constituencywiseResults;
	}


	public void setBiElectionPageService(IBiElectionPageService biElectionPageService) {
		this.biElectionPageService = biElectionPageService;
	}


	public IBiElectionPageService getBiElectionPageService() {
		return biElectionPageService;
	}


	public String execute(){
		log.debug(" Inside Action ..");
		
		
		return SUCCESS;
	}
	
	public String ajaxCallHandler()
	{
		log.debug(" Inside ajaxCallHandler Action ..");
		String param=null;			    
		param = getTask();
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(jObj.getString("task").equalsIgnoreCase("getByeElectionResults"))
		{
			/*constituencywiseResults = new ArrayList<ConstituencyVO>();
			
			ConstituencyVO constituencyVO1 = new ConstituencyVO();
			ConstituencyVO constituencyVO2 = new ConstituencyVO();
			ConstituencyVO constituencyVO3 = new ConstituencyVO();
			ConstituencyVO constituencyVO4 = new ConstituencyVO();
			List<PartyResultVO> partiesResults = new ArrayList<PartyResultVO>();
			PartyResultVO incObj= new PartyResultVO();
			PartyResultVO trsObj = new PartyResultVO();
			PartyResultVO tdpObj= new PartyResultVO();
			
			incObj.setPartyName("INC");
			incObj.setVotesPercent("39.5%");
			partiesResults.add(incObj);
			tdpObj.setPartyName("TDP");
			tdpObj.setVotesPercent("39.5%");
			partiesResults.add(tdpObj);
			trsObj.setPartyName("TRS");
			trsObj.setVotesPercent("39.5%");
			partiesResults.add(trsObj);
			
			constituencyVO1.setName("Huzurabad");
			constituencyVO1.setTotalVoters2009(204056l);
			constituencyVO1.setTotalVoters2010(198999l);
			constituencyVO1.setTotalPolledVotes(146412l);
			constituencyVO1.setVotesPercent("71.75 %");
			constituencyVO1.setPartiesResults(partiesResults);
			
			constituencyVO2.setName("Siricilla");
			constituencyVO2.setTotalVoters2009(210869l);
			constituencyVO2.setTotalVoters2010(211442l);
			constituencyVO2.setTotalPolledVotes(136665l);
			constituencyVO2.setVotesPercent("64.81 %");
			constituencyVO2.setPartiesResults(partiesResults);
			
			constituencyVO3.setName("Koratla");
			constituencyVO3.setTotalVoters2009(191853l);
			constituencyVO3.setTotalVoters2010(187910l);
			constituencyVO3.setTotalPolledVotes(129293l);
			constituencyVO3.setVotesPercent("67.39 %");
			constituencyVO3.setPartiesResults(partiesResults);
			
			constituencyVO4.setName("Vemulawada");
			constituencyVO4.setTotalVoters2009(184513l);
			constituencyVO4.setTotalVoters2010(18463l);
			constituencyVO4.setTotalPolledVotes(12214l);
			constituencyVO4.setVotesPercent("66.20 %");
			constituencyVO4.setPartiesResults(partiesResults);
			
			constituencywiseResults.add(constituencyVO1);
			constituencywiseResults.add(constituencyVO2);
			constituencywiseResults.add(constituencyVO3);
			constituencywiseResults.add(constituencyVO4);*/
			String percentage = jObj.getString("percentage");
			constituencywiseResults = biElectionPageService.getAllTelanganaConstituencieswisePartiesResultsBasedOnExpectedPercentage(percentage);
		}
		return SUCCESS;
	}
	
	

}
