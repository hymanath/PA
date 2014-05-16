package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IAcPcWiseElectionResultService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AcPcWiseElectionResultAction extends ActionSupport implements ServletRequestAware  {

	
	
	private static final long serialVersionUID = -8021304666056628316L;
	HttpServletRequest request;
	private HttpSession session;
	private static final Logger LOG = Logger.getLogger(AcPcWiseElectionResultAction.class);
	private String task;
	JSONObject jObj;
	private List<BasicVO> resultList;
	@Autowired
	IAcPcWiseElectionResultService acPcWiseElectionResultService;
	List<SelectOptionVO> resultLists; 
	private IStaticDataService staticDataService;
	List<GenericVO> returnList;
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}


	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}


	public List<SelectOptionVO> getResultLists() {
		return resultLists;
	}


	public void setResultLists(List<SelectOptionVO> resultLists) {
		this.resultLists = resultLists;
	}


	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}
	
	
	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public List<BasicVO> getResultList() {
		return resultList;
	}


	public void setResultList(List<BasicVO> resultList) {
		this.resultList = resultList;
	}

	

	public List<GenericVO> getReturnList() {
		return returnList;
	}


	public void setReturnList(List<GenericVO> returnList) {
		this.returnList = returnList;
	}


	public String execute()
	{
		try
		{
			LOG.debug("Entered Into execute method in AcPcWiseElectionResultAction Action");
			session = request.getSession();
			RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO != null) 
			{
				if (!registrationVO.getIsAdmin().equals("true"))
					  return ERROR;
			} 
			else
				return ERROR;
			
			
			resultLists = staticDataService.getAllParliaments();
			
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised In execute method in AcPcWiseElectionResultAction Action", e);
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	public String getElectionResults()
	{
		try
		{
			LOG.debug("Entered Into getElectionResults method in AcPcWiseElectionResultAction Action");
			session = request.getSession();
			RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO != null) 
			{
				if (!registrationVO.getIsAdmin().equals("true"))
					  return ERROR;
			} 
			else
				return ERROR;
			
			jObj = new JSONObject(getTask());
						
			JSONArray parties = jObj.getJSONArray("parties");
			List<Long> partyIds = new ArrayList<Long>();
			for(int i = 0 ; i < parties.length() ; i++)
			{
				partyIds.add(new Long(parties.get(i).toString()));
			}
			resultList = acPcWiseElectionResultService.getPartyWiseComperassionResult(jObj.getLong("stateId"),jObj.getLong("electionId"),partyIds,jObj.getLong("electionScopeId"),jObj.getString("scope"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised In getElectionResults method in AcPcWiseElectionResultAction Action", e);
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	public String cbnOrModiEffect()
	{
		try
		{
			LOG.debug("Entered Into getElectionResults method in AcPcWiseElectionResultAction Action");
			session = request.getSession();
			RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO != null) 
			{
				if (!registrationVO.getIsAdmin().equals("true"))
					  return ERROR;
			} 
			else
				return ERROR;
			
			jObj = new JSONObject(getTask());
						
			/*JSONArray parties = jObj.getJSONArray("parties");
			List<Long> partyIds = new ArrayList<Long>();
			for(int i = 0 ; i < parties.length() ; i++)
			{
				partyIds.add(new Long(parties.get(i).toString()));
			}*/
			returnList = acPcWiseElectionResultService.cbnOrModiEffect(jObj.getLong("stateId"),jObj.getLong("electionId"),jObj.getLong("partyId"),jObj.getLong("electionScopeId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised In getElectionResults method in AcPcWiseElectionResultAction Action", e);
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}

}
