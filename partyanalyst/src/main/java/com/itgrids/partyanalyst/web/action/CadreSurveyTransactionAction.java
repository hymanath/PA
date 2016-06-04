package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreTransactionVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyTransactionReportVO;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICadreSurveyTransactionService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreSurveyTransactionAction extends ActionSupport implements ServletRequestAware, ServletContextAware {


	private final static Logger LOG = Logger.getLogger(CadreSurveyTransactionAction.class);

	private static final long serialVersionUID = -5934997595739284474L;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	private JSONObject jObj;
	private String task;
	
	private SurveyTransactionVO surveyTransactionVO;
	private ICadreSurveyTransactionService cadreSurveyTransactionService;
	private List<SelectOptionVO> constituencyList = new ArrayList<SelectOptionVO>();
	private SurveyTransactionReportVO surveyTransactionReportVO;
	private CadreTransactionVO cadreTransactionVO;
	private EntitlementsHelper entitlementsHelper;
	
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public CadreTransactionVO getCadreTransactionVO() {
		return cadreTransactionVO;
	}

	public void setCadreTransactionVO(CadreTransactionVO cadreTransactionVO) {
		this.cadreTransactionVO = cadreTransactionVO;
	}

	public SurveyTransactionReportVO getSurveyTransactionReportVO() {
		return surveyTransactionReportVO;
	}

	public void setSurveyTransactionReportVO(
			SurveyTransactionReportVO surveyTransactionReportVO) {
		this.surveyTransactionReportVO = surveyTransactionReportVO;
	}

	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public void setTask(String task) {
		this.task = task;
	}
    
	public String getTask() {
		return task;
	}

	public void setCadreSurveyTransactionService(
			ICadreSurveyTransactionService cadreSurveyTransactionService) {
		this.cadreSurveyTransactionService = cadreSurveyTransactionService;
	}
	
	public SurveyTransactionVO getSurveyTransactionVO() {
		return surveyTransactionVO;
	}

	public void setSurveyTransactionVO(SurveyTransactionVO surveyTransactionVO) {
		this.surveyTransactionVO = surveyTransactionVO;
	}

	public void setServletRequest(final HttpServletRequest request)
	{
		this.request = request;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public String execute()
	{
		try {
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			if(user == null)
				return Action.INPUT;
			
			
		//	surveyTransactionVO = cadreSurveyTransactionService.getCadreSurveyTransactionDetails(null);
			//surveyTransactionReportVO = cadreSurveyTransactionService.getBasicTransactionDetails();
			
		} catch (Exception e) {
			LOG.error("Exception occured in execute() in CadreSurveyTransactionAction class.", e);
		}
		return Action.SUCCESS;
	}

	
	public String cadreTransactionReportsPage()
	{
		try {
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			if(user == null)
				return Action.INPUT;
			
		} catch (Exception e) {
			LOG.error("Exception occured in cadreTransactionReportsPage() in CadreSurveyTransactionAction class.", e);
		}
		return Action.SUCCESS;
	}
	
	public String cadreMemberShipCardDispatcher()
	{
		try {
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			if(user == null)
				return Action.INPUT;
			List<String> entitlements = null;
			if(user.getEntitlements() != null && user.getEntitlements().size()>0){
				entitlements = user.getEntitlements();
				if(entitlements.contains(IConstants.CADRE_MEMBERSHIPCARD_DISPATCHER) 
						|| entitlements.contains(IConstants.CADRE_MEMBERSHIPCARD_DISPATCHER_GROUP)){
					return Action.SUCCESS;
				}
			/*if(entitlementsHelper.checkForEntitlementToViewReport(user,IConstants.CADRE_MEMBERSHIPCARD_DISPATCHER) || 
					 entitlementsHelper.checkForEntitlementToViewReport(user,IConstants.CADRE_MEMBERSHIPCARD_DISPATCHER_GROUP))
			{
				return Action.SUCCESS;
			}*/
		}
		} catch (Exception e) {
			LOG.error("Exception occured in cadreTransactionReportsPage() in CadreSurveyTransactionAction class.", e);
		}
		return Action.ERROR;
	}
	public String getDateWiseDashboardReport()
	{
	
		try {
			
			jObj = new JSONObject(getTask());
			String searchDate = jObj.getString("searchDate");			
			//surveyTransactionVO = cadreSurveyTransactionService.getCadreSurveyTransactionDetails(searchDate);	
			
		} catch (Exception e) {
			LOG.error("Exception occured in getDateWiseDashboardReport() in CadreSurveyTransactionAction class.", e);
		}
		return Action.SUCCESS;
	}
	public String getParliamentsForState()
	{
		try {
			
			jObj = new JSONObject(getTask());
			String searchType = jObj.getString("searchType");
			Long stateTypeId = jObj.getLong("stateTypeId");
			
			constituencyList = cadreSurveyTransactionService.getParliamentsForState(searchType,stateTypeId);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getParliamentsForState() in CadreSurveyTransactionAction class.", e);
		}
		return Action.SUCCESS;
	}
	public String getDayWiseReportByDates()
	{
		try {			
			jObj = new JSONObject(getTask());
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			//surveyTransactionVO = cadreSurveyTransactionService.getDayWiseReportByDates(fromDate,toDate);
		} catch (Exception e) {
			LOG.error("Exception occured in getDayWiseReportByDates() in CadreSurveyTransactionAction class.", e);
		}
		return Action.SUCCESS;
	}
	public String getLocationWiseTransactionReport()
	{
		try {
			jObj = new JSONObject(getTask());
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			String searchType = jObj.getString("searchType");
			JSONArray locationIdsArr = jObj.getJSONArray("locationIds");
			List<Long> locationIdList = new ArrayList<Long>();
			if(locationIdsArr != null && locationIdsArr.length()>0)
			{
				for (int i = 0; i < locationIdsArr.length(); i++)
				{
					locationIdList.add(Long.valueOf(locationIdsArr.get(i).toString()));
				}
			}
			//surveyTransactionVO = cadreSurveyTransactionService.getLocationWiseTransactionDetails(fromDate,toDate,searchType,locationIdList);
		} catch (Exception e) {
			LOG.error("Exception occured in getLocationWiseTransactionReport() in CadreSurveyTransactionAction class.", e);
		}
		return Action.SUCCESS;
	}
	
	public String getSurveyTransactionDetails()
	{
		try{
			jObj = new JSONObject(getTask());
			surveyTransactionReportVO = cadreSurveyTransactionService.getBasicTransactionDetails();
		}
		catch (Exception e) {
			LOG.error("Exception occured in getSurveyTransactionDetails() in CadreSurveyTransactionAction class.", e);
		}
		return Action.SUCCESS;
	}
	
	public String getTdpCadreDetailsBySearchCriteria()
	{
		try{
			
			jObj = new JSONObject(getTask());

			String refNo = jObj.getString("refNo");
			String mobileNo = jObj.getString("mobileNo");
			
			cadreTransactionVO = cadreSurveyTransactionService.getTdpCadreDetailsBySearchCriteria(refNo,mobileNo);
			
		}
		catch (Exception e) {
			LOG.error("Exception occured in getSurveyTransactionDetails() in CadreSurveyTransactionAction class.", e);
		}
		return Action.SUCCESS;
	}
	
}
