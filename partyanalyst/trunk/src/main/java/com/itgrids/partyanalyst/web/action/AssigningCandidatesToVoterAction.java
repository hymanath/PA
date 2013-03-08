package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author Prasad Thiragabathina
 *
 */
public class AssigningCandidatesToVoterAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	private HttpServletResponse response;
	private JSONObject jObj;
	private String task;
	private List<SelectOptionVO> selectOptionVO;
	private ICandidateDetailsService candidateDetailsService;
	private ResultStatus resultStatus;
	private Long voterId;
	private String name;
	private CandidateDetailsVO candidateDetailsVO;
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;	
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	
	
	public List<SelectOptionVO> getSelectOptionVO() {
		return selectOptionVO;
	}
	public void setSelectOptionVO(List<SelectOptionVO> selectOptionVO) {
		this.selectOptionVO = selectOptionVO;
	}
	
	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}
	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public CandidateDetailsVO getCandidateDetailsVO() {
		return candidateDetailsVO;
	}
	public void setCandidateDetailsVO(CandidateDetailsVO candidateDetailsVO) {
		this.candidateDetailsVO = candidateDetailsVO;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String execute()
	{
		return Action.SUCCESS;
	}
	/**
	 * This Methos Is Used To Mangae All Ajax Calls
	 * @author Prasad Thiragabathina
	 * @return String
	 */
	public String ajaxHandler()
	{
		Long constituencyId = null;
		String name = null;
		String gender = null;
		Long candidateId = null;
		Long voterId = null;
		
		try 
		{
			Log.debug("Entered into the ajaxHandler() method");
			jObj = new JSONObject(getTask());
			
			if(jObj.getString("task").equalsIgnoreCase("getCandidateDetailsBySearchCriteria"))
			{
				if(jObj.getString("name")!= null && jObj.getString("name").trim().length()>0 )
					name = jObj.getString("name");
				
				if(jObj.getString("gender")!= null && jObj.getString("gender").trim().length()>0 )
					gender = jObj.getString("gender");
				
				if(jObj.getString("constituencyId")!= null && jObj.getString("constituencyId").trim().length()>0 )
					constituencyId = jObj.getLong("constituencyId");
				
				selectOptionVO  = candidateDetailsService.getCandidateDetailsBySearch(gender,name,constituencyId,jObj.getLong("stateId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("saveUserCandidateRelation"))
			{
				if(jObj.getString("candidateId")!= null && jObj.getString("candidateId").trim().length()>0 )
					candidateId = jObj.getLong("candidateId");
				if(jObj.getString("voterId")!= null && jObj.getString("voterId").trim().length()>0 )
					voterId = jObj.getLong("voterId");
				resultStatus = candidateDetailsService.saveCandidateVoterDetails(candidateId,voterId);
			}
		}
		catch(Exception e)
		{
			Log.error("Exception rasied in ajaxHandler() method",e);
		}
		
		return SUCCESS;
	}
}
