package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.BigPictureVO;
import com.itgrids.partyanalyst.dto.BoothWiseSurveyStatusDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
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
	private Long constituencyId;
	private Long userTypeId;
	private Long regionId;
	
	private String fromDate;
	private String toDate;
	
	private List<SurveyDashBoardVO> resultList;
	private List<SurveyResponceVO> collectedCasteDetails;
	private List<BoothWiseSurveyStatusDetailsVO> boothWiseStatusList;
	private String startDate;
	private String endDate;
	
	@Autowired
	private ICtpDashBoardService ctpDashBoardService;
	

	public List<BoothWiseSurveyStatusDetailsVO> getBoothWiseStatusList() {
		return boothWiseStatusList;
	}


	public void setBoothWiseStatusList(
			List<BoothWiseSurveyStatusDetailsVO> boothWiseStatusList) {
		this.boothWiseStatusList = boothWiseStatusList;
	}
	public List<SurveyResponceVO> getCollectedCasteDetails() {
		return collectedCasteDetails;
	}


	public void setCollectedCasteDetails(
			List<SurveyResponceVO> collectedCasteDetails) {
		this.collectedCasteDetails = collectedCasteDetails;
	}


	public Long getRegionId() {
		return regionId;
	}


	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	
	
	public List<SurveyDashBoardVO> getResultList() {
		return resultList;
	}


	public void setResultList(List<SurveyDashBoardVO> resultList) {
		this.resultList = resultList;
	}


	public Long getConstituencyId() {
		return constituencyId;
	}


	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}


	public Long getUserTypeId() {
		return userTypeId;
	}


	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
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
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getFromDate() {
		return fromDate;
	}


	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}


	public String getToDate() {
		return toDate;
	}


	public void setToDate(String toDate) {
		this.toDate = toDate;
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
			jObj = new JSONObject(getTask());
			bigPictureVO = ctpDashBoardService.getBigPictureDetails(jObj.getLong("stateId"));
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
			jObj = new JSONObject(getTask());
			bigPictureVO = ctpDashBoardService.getInternalVerificationSummary(jObj.getLong("stateId"),jObj.getString("fromDate"),jObj.getString("toDate"));
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
			jObj = new JSONObject(getTask());
			bigPictureVO = ctpDashBoardService.getQcVerificationSummaryReport(jObj.getLong("stateId"),jObj.getString("fromDate"),jObj.getString("toDate"));
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
			jObj = new JSONObject(getTask());
			bigPictureVO = ctpDashBoardService.getTodayTeamDetails(jObj.getLong("stateId"),jObj.getString("fromDate"),jObj.getString("toDate"));
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
			bigPictureVOList = ctpDashBoardService.getBoothWiseTeamDetails(jObj.getLong("stateId"),jObj.getLong("constituencyId"),jObj.getLong("surveyUserTypeId"),jObj.getString("fromDate"),jObj.getString("toDate"));
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
			jObj = new JSONObject(getTask());
			bigPictureVOList = ctpDashBoardService.getConstituencyWiseTeamDetails(jObj.getLong("stateId"),jObj.getLong("type"),jObj.getString("fromDate"),jObj.getString("toDate"));
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
			jObj = new JSONObject(getTask());
			bigPictureVOList = ctpDashBoardService.getConstituencyWiseQcVerificationSummary(jObj.getLong("stateId"),jObj.getString("type"),jObj.getString("fromDate"),jObj.getString("toDate"));
		} 
		catch (Exception e) 
		{
			LOG.error("Exception reised in getTodayTeamDetails", e);
		}
		return Action.SUCCESS;
	}

	
	public String getBoothWiseQcVerificationSummary()
	{
		try
		{
			jObj = new JSONObject(getTask());
			bigPictureVOList = ctpDashBoardService.getBoothWiseQcVerificationSummary(jObj.getLong("stateId"),jObj.getLong("constituencyId"),jObj.getString("type"),jObj.getString("fromDate"),jObj.getString("toDate"));
		} 
		catch (Exception e) 
		{
			LOG.error("Exception reised in getTodayTeamDetails", e);
		}
		return Action.SUCCESS;
	}
	
	public String getTodayTeamCollectedDetails()
	{
		try
		{
			jObj = new JSONObject(getTask());
			bigPictureVO = ctpDashBoardService.getTodayTeamCollectedDetails(jObj.getLong("stateId"),jObj.getString("fromDate"),jObj.getString("toDate"));
		} 
		catch (Exception e) 
		{
			LOG.error("Exception reised in getTodayTeamDetails", e);
		}
		return Action.SUCCESS;
	}
	
	public String getConstituencyWiseTeamCollectedSummary()
	{
		try
		{
			jObj = new JSONObject(getTask());
			bigPictureVOList = ctpDashBoardService.getConstituencyWiseTeamCollectedSummary(jObj.getLong("stateId"),jObj.getLong("type"),jObj.getString("fromDate"),jObj.getString("toDate"));
		} 
		catch (Exception e) 
		{
			LOG.error("Exception reised in getTodayTeamDetails", e);
		}
		return Action.SUCCESS;
	}
	
	public String getBoothWiseTeamCollectedDetailsSummary()
	{
		try
		{
			jObj = new JSONObject(getTask());
			bigPictureVOList = ctpDashBoardService.getBoothWiseTeamCollectedDetailsSummary(jObj.getLong("stateId"),jObj.getLong("constituencyId"),jObj.getLong("type"),jObj.getString("fromDate"),jObj.getString("toDate"));
		} 
		catch (Exception e) 
		{
			LOG.error("Exception reised in getTodayTeamDetails", e);
		}
		return Action.SUCCESS;
	}
	
	public String getCasteCollectedDetails()
	{
		LOG.info("Entered into the  getCasteCollectedDetails");

		try
		{
			resultList = ctpDashBoardService.getCasteCollectedDetails(regionId,userTypeId,startDate,endDate);
		} 
		catch (Exception e) 
		{
			LOG.error("Exception raised in getCasteCollectedDetails", e);
		}
		return Action.SUCCESS;
	}
	
	public String getSurveyDetailsByConstituencyId()
	{
		LOG.info("Entered into the getSurveyDetailsByConstituencyId ");

		try
		{
			resultList = ctpDashBoardService.getSurveyDetailsByConstituencyId(constituencyId,userTypeId);
		} 
		catch (Exception e) 
		{
			LOG.error("Exception raised in getSurveyDetailsByConstituencyId", e);
		}
		return Action.SUCCESS;
	}
	
	public String getBoothWiseCollectedcasteDetails()
	{
		LOG.info("Entered into the getBoothWiseCollectedcasteDetails ");

		try
		{
			collectedCasteDetails = ctpDashBoardService.getBoothWiseCollectedcasteDetails(
					constituencyId, userTypeId);
		} 
		catch (Exception e) 
		{
			LOG.error("Exception raised in getBoothWiseCollectedcasteDetails", e);
		}
		return Action.SUCCESS;
	}
	
	public String surveyConstituencieOverview()
	{
		return Action.SUCCESS;
	}
	
	public String getSurveyConstituencyStatus()
	{
		try{
			resultList = ctpDashBoardService.getCountsForDC();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	public String getAllBoothsStatusDetailsByConstituencyId()
	{
		try
		{
			boothWiseStatusList = ctpDashBoardService.getAllBoothsStatusDetailsByConstituencyId(constituencyId);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	
}
