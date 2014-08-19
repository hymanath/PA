package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.BigPictureVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ICtpDashBoardService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CtpDashBoardAction extends ActionSupport implements ServletRequestAware{

	
	private static final long serialVersionUID = -1673285714195342962L;
	public static final Logger LOG = Logger.getLogger(SurveyDashBoardAction.class);
	HttpServletRequest request;
	private BigPictureVO bigPictureVO;
	private List<BigPictureVO> bigPictureVOList;
	private String task;
	private JSONObject jObj;
	
	@Autowired
	private ICtpDashBoardService ctpDashBoardService;
	
	
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

	

	public BigPictureVO getBigPictureVO() {
		return bigPictureVO;
	}


	public void setBigPictureVO(BigPictureVO bigPictureVO) {
		this.bigPictureVO = bigPictureVO;
	}


	public List<BigPictureVO> getBigPictureVOList() {
		return bigPictureVOList;
	}


	public void setBigPictureVOList(List<BigPictureVO> bigPictureVOList) {
		this.bigPictureVOList = bigPictureVOList;
	}


	public String execute()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
		{
			return Action.INPUT;
		}
		
		return Action.SUCCESS;
	}
	
	public String getBigPictureDetails()
	{
		try
		{
			bigPictureVO = ctpDashBoardService.getBigPictureDetails();
		} 
		catch (Exception e) 
		{
			LOG.error("Exception reised in getBigPictureDetails", e);
		}
		return Action.SUCCESS;
	}
	
	public String getInternalVerificationSummary()
	{
		try
		{
			bigPictureVO = ctpDashBoardService.getInternalVerificationSummary();
		} 
		catch (Exception e) 
		{
			LOG.error("Exception reised in getInternalVerificationSummary", e);
		}
		return Action.SUCCESS;
	}
	
	public String getQcVerificationSummaryReport()
	{
		try
		{
			bigPictureVO = ctpDashBoardService.getQcVerificationSummaryReport();
		} 
		catch (Exception e) 
		{
			LOG.error("Exception reised in getQcVerificationSummaryReport", e);
		}
		return Action.SUCCESS;
	}
	
	public String getTodayTeamDetails()
	{
		try
		{
			bigPictureVO = ctpDashBoardService.getTodayTeamDetails();
		} 
		catch (Exception e) 
		{
			LOG.error("Exception reised in getTodayTeamDetails", e);
		}
		return Action.SUCCESS;
	}
	
	public String getBoothWiseTeamDetails()
	{
		try
		{
			jObj = new JSONObject(getTask());
			bigPictureVOList = ctpDashBoardService.getBoothWiseTeamDetails(jObj.getLong("constituencyId"),jObj.getLong("surveyUserTypeId"));
		} 
		catch (Exception e) 
		{
			LOG.error("Exception reised in getTodayTeamDetails", e);
		}
		return Action.SUCCESS;
	}
	
	public String getConstituencyWiseTeamDetails()
	{
		try
		{
			bigPictureVOList = ctpDashBoardService.getConstituencyWiseTeamDetails();
		} 
		catch (Exception e) 
		{
			LOG.error("Exception reised in getTodayTeamDetails", e);
		}
		return Action.SUCCESS;
	}
	
	public String getConstituencyWiseQcVerificationSummary()
	{
		try
		{
			bigPictureVOList = ctpDashBoardService.getConstituencyWiseQcVerificationSummary();
		} 
		catch (Exception e) 
		{
			LOG.error("Exception reised in getTodayTeamDetails", e);
		}
		return Action.SUCCESS;
	}
	
	
}
