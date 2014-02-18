package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.PoliticalFeedBackVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IPoliticalFeedBackService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PoliticalFeedBackAction extends ActionSupport implements ServletRequestAware {

	
	private static final long    serialVersionUID = 1L;
	private HttpServletRequest   request;
	private HttpSession          session;
	private ResultStatus         resultStatus;
	private PoliticalFeedBackVO  politicalFeedBackVO;
	private List<SelectOptionVO> constituencysList;
	List<PoliticalFeedBackVO>    politicalFeedBackVOList;
	
	private String 						task;
	private JSONObject 					jObj;
	private final static Logger LOG            = Logger.getLogger(PoliticalFeedBackAction.class); 
	
	private IPoliticalFeedBackService       politicalFeedBackService;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public void setPoliticalFeedBackService(
			IPoliticalFeedBackService politicalFeedBackService) {
		this.politicalFeedBackService = politicalFeedBackService;
	}


	public ResultStatus getResultStatus() {
		return resultStatus;
	}


	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}


	public PoliticalFeedBackVO getPoliticalFeedBackVO() {
		return politicalFeedBackVO;
	}


	public void setPoliticalFeedBackVO(PoliticalFeedBackVO politicalFeedBackVO) {
		this.politicalFeedBackVO = politicalFeedBackVO;
	}

	
	public List<SelectOptionVO> getConstituencysList() {
		return constituencysList;
	}


	public void setConstituencysList(List<SelectOptionVO> constituencysList) {
		this.constituencysList = constituencysList;
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


	public List<PoliticalFeedBackVO> getPoliticalFeedBackVOList() {
		return politicalFeedBackVOList;
	}


	public void setPoliticalFeedBackVOList(
			List<PoliticalFeedBackVO> politicalFeedBackVOList) {
		this.politicalFeedBackVOList = politicalFeedBackVOList;
	}


	public String execute()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO == null)
		{
			return Action.ERROR;
		}
		if(regVO.getUserAccessType().equalsIgnoreCase("pfb"))
		{
			constituencysList = politicalFeedBackService.getAccessParliments(regVO.getRegistrationID());
		}
		else
		{
			constituencysList = politicalFeedBackService.getAllParlimentConstituencys();
		}
		constituencysList.add(0,new SelectOptionVO(0l,"Select Constituency"));
		return Action.SUCCESS;
	}
	
	public String savePoliticalFeedForm()
	{
		try {
			LOG.debug("Entered into savePoliticalFeedForm method in PoliticalFeedBackAction Class");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
			{
				return Action.ERROR;
			}
			politicalFeedBackVO.setUserId(regVO.getRegistrationID());
			resultStatus = politicalFeedBackService.savePoliticalFeedBack(politicalFeedBackVO);
			
		} catch (Exception e) {
			LOG.error("Exception Occured savePoliticalFeedForm method in PoliticalFeedBackAction Class",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAssemblyConstituencyes()
	{
		try {
			LOG.debug("Entered into getAssemblyConstituencyes method in PoliticalFeedBackAction Class");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
			{
				return Action.ERROR;
			}
			jObj = new JSONObject(getTask());
			constituencysList = politicalFeedBackService.getAssemblyListByPCId(jObj.getLong("constituencyId"));
			
		} catch (Exception e) {
			LOG.error("Exception Occured getAssemblyConstituencyes method in PoliticalFeedBackAction Class",e);
		}
		return Action.SUCCESS;
	}
	
	public String getSelectedPolitialFeedBackDetails()
	{
		try {
			LOG.debug("Entered into getSelectedPolitialFeedBackDetails method in PoliticalFeedBackAction Class");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
			{
				return Action.ERROR;
			}
			jObj = new JSONObject(getTask());
			politicalFeedBackVOList = politicalFeedBackService.getSelectedPolitialFeedBackDetails(jObj.getString("date"),jObj.getLong("constituencyId"));
			
		} catch (Exception e) {
			LOG.error("Exception Occured getSelectedPolitialFeedBackDetails method in PoliticalFeedBackAction Class",e);
		}
		return Action.SUCCESS;
	}
}
