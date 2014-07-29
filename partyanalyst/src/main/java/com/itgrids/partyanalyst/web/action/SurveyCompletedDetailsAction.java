package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.service.ISurveyCompletedDetailsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SurveyCompletedDetailsAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{
	
	 private HttpSession session;
 	 private HttpServletRequest request;
 	 private HttpServletResponse response;
 	 private Long constituencyId;
 	 private SurveyReportVO resultVO;
 	 private String status;
     private List<SurveyReportVO> resultList;
     
     @Autowired
	 private ISurveyCompletedDetailsService surveyCompletedDetailsService;
	

	  public List<SurveyReportVO> getResultList() {
		return resultList;
	  }

	  public void setResultList(List<SurveyReportVO> resultList) {
	 	this.resultList = resultList;
	  }
	
	  public SurveyReportVO getResultVO() {
			return resultVO;
	  }

	  public void setResultVO(SurveyReportVO resultVO) {
			this.resultVO = resultVO;
	  }	
 	 
 	 public Long getConstituencyId() {
		return constituencyId;
	 }

	 public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	 }


	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getBoothStatusDetailsByConstituency()
	{
		try
		{
			resultVO = surveyCompletedDetailsService.getBoothsStatusByConstituencyId(constituencyId);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return ActionSupport.SUCCESS;
	}
	
	
	public String saveBoothStatusDetails()
	{
		try
		{
			Long locationValue = Long.parseLong(request.getParameter("locationValue"));
			Long statusId = Long.parseLong(request.getParameter("statusId"));
			String locationType = request.getParameter("locationType");
			
			status = surveyCompletedDetailsService.saveBoothStatusDetails(locationValue,statusId,locationType);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return ActionSupport.SUCCESS;
	}
	

}
