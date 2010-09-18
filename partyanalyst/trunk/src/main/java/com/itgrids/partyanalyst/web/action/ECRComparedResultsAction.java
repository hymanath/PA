package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ComparedReportVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IElectionComparisonReportService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ECRComparedResultsAction extends ActionSupport implements
ServletRequestAware, ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSONObject jObj = null;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	private ComparedReportVO comparedResultsVO;
	private IElectionComparisonReportService electionComparisonReportService;
	public static final Logger logger = Logger.getLogger(ECRComparedResultsAction.class);
	private Long elecIdOne;
	private Long elecIdTwo;
	private Long stateOrDistrictId;
	private Long partyId;
	private String hasAlliances;
	private String electionType;
	private String elecYear1;
	private String elecYear2;
	private String locationName;

	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public void setServletContext(ServletContext context) {
		this.context = context;		
	}	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public ComparedReportVO getComparedResultsVO() {
		return comparedResultsVO;
	}

	public void setComparedResultsVO(ComparedReportVO comparedResultsVO) {
		this.comparedResultsVO = comparedResultsVO;
	}

	public IElectionComparisonReportService getElectionComparisonReportService() {
		return electionComparisonReportService;
	}

	public void setElectionComparisonReportService(
			IElectionComparisonReportService electionComparisonReportService) {
		this.electionComparisonReportService = electionComparisonReportService;
	}	
	

	public Long getElecIdOne() {
		return elecIdOne;
	}

	public void setElecIdOne(Long elecIdOne) {
		this.elecIdOne = elecIdOne;
	}

	public Long getElecIdTwo() {
		return elecIdTwo;
	}

	public void setElecIdTwo(Long elecIdTwo) {
		this.elecIdTwo = elecIdTwo;
	}

	public Long getStateOrDistrictId() {
		return stateOrDistrictId;
	}

	public void setStateOrDistrictId(Long stateOrDistrictId) {
		this.stateOrDistrictId = stateOrDistrictId;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getHasAlliances() {
		return hasAlliances;
	}

	public void setHasAlliances(String hasAlliances) {
		this.hasAlliances = hasAlliances;
	}	

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getElecYear1() {
		return elecYear1;
	}

	public void setElecYear1(String elecYear1) {
		this.elecYear1 = elecYear1;
	}

	public String getElecYear2() {
		return elecYear2;
	}

	public void setElecYear2(String elecYear2) {
		this.elecYear2 = elecYear2;
	}

	public String execute () throws Exception 
	{
		return Action.SUCCESS;		
	}
	
	public String ajaxHandler()
	{
			
			String param=null;		
			param=request.getParameter("task");
			
			
			try {
				jObj=new JSONObject(param);
				System.out.println("jObj = "+jObj);
			} catch (ParseException e) {
				e.printStackTrace();
			}		
			
			Long elecIdOne = jObj.getLong("electionIdOne");
			Long elecIdTwo = jObj.getLong("electionIdTwo");
			Long stateOrDistrictId  = jObj.getLong("stateOrDistrictId");
			Long partyId = jObj.getLong("partyId");
			String hasAlliances = jObj.getString("hasAlliance");
			
			logger.debug("Year One:"+elecIdOne);
			logger.debug("Year Two:"+elecIdTwo);
			logger.debug("District Id:"+stateOrDistrictId);
			logger.debug("PartyId Id:"+partyId);
			logger.debug("Has Alliances:"+hasAlliances);
				
			comparedResultsVO = electionComparisonReportService.getComparedElectionResults(new Long(partyId), elecIdOne, elecIdTwo, stateOrDistrictId, new Boolean(hasAlliances));
			locationName = comparedResultsVO.getLocName();
			
			return Action.SUCCESS;
		
	}

}
