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

	
	private static final long    		serialVersionUID = 1L;
	private HttpServletRequest   		request;
	private HttpSession          		session;
	private ResultStatus         		resultStatus;
	private PoliticalFeedBackVO  		politicalFeedBackVO;
	private List<SelectOptionVO> 		constituencysList;
	private List<PoliticalFeedBackVO>   politicalFeedBackVOList;
	private String 						status;
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
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
	
	public String getSelectedPoliticalFeedBackDetails()
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
			politicalFeedBackVO = politicalFeedBackService.getSelectedPoliticalFeedBackDetails(jObj.getLong("pfbId"));
			
		} catch (Exception e) {
			LOG.error("Exception Occured getSelectedPolitialFeedBackDetails method in PoliticalFeedBackAction Class",e);
		}
		return Action.SUCCESS;
	}
	
	public String deletedPoliticalFeedBadk()
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
			status = politicalFeedBackService.deletedPoliticalFeedBadk(jObj.getLong("pfbId"));
			
		} catch (Exception e) {
			LOG.error("Exception Occured getSelectedPolitialFeedBackDetails method in PoliticalFeedBackAction Class",e);
		}
		return Action.SUCCESS;
	}
	
	public String generateUrlForPFA()
	{
		try
		{
			LOG.info(" Entered into generateUrl() in DebateAction class. ");
			 jObj = new JSONObject(getTask());
			 HttpSession session = request.getSession();
				RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
				if(regVo == null)
					return Action.ERROR;
			Long pfbId = jObj.getLong("pfbId");
			String description = jObj.getString("description");
			Long userId = regVo.getRegistrationID();
			String path = request.getRequestURL().toString().replace("generateUrlForPFAAction.action","genereatePfbReportAction.action?");
			status = politicalFeedBackService.savePFBReportForPdf(userId,pfbId,description, path);
		}
		catch (Exception e) 
		{
			LOG.error(" Exception occured in generateUrl() in DebateAction class. ",e);
		}
		return Action.SUCCESS;
	}
	
	public String deltePfbReportDetails()
	{
		try
		{
			LOG.info(" Entered into generateUrl() in DebateAction class. ");
			jObj = new JSONObject(getTask());
			status = politicalFeedBackService.deltePfbReportDetails(jObj.getString("key"));
			if(status == null)
			{
				return Action.ERROR;
			}
		}
		catch (Exception e) 
		{
			LOG.error(" Exception occured in generateUrl() in DebateAction class. ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getPoliticatFeedBack()
	{
		try {
			LOG.debug("Entered into getPoliticatFeedBack method in PoliticalFeedBackAction Class");
			jObj = new JSONObject(getTask());
			Long count = politicalFeedBackService.getPfbDetails(jObj.getString("key"));
			if(count > 0)
			{
				politicalFeedBackVOList = politicalFeedBackService.getSelectedPolitialFeedBackDetails(jObj.getString("date"),jObj.getLong("constituencyId"));
			}
			
			else
			{
				return Action.ERROR;
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured getPoliticatFeedBack method in PoliticalFeedBackAction Class",e);
		}
		return Action.SUCCESS;
	}
}
