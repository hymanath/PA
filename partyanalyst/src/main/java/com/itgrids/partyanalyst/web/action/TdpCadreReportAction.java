package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;
import com.itgrids.partyanalyst.dto.TdpCadreLocationWiseReportVO;
import com.itgrids.partyanalyst.service.ITdpCadreReportService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class TdpCadreReportAction extends ActionSupport implements ServletRequestAware{

	private static final Logger LOG = Logger.getLogger(TdpCadreReportAction.class);
	
	private HttpServletRequest request;
	
	private String 								task;
	private JSONObject                  		jobj;
	
	private ITdpCadreReportService  tdpCadreReportService;
	private TdpCadreLocationWiseReportVO tdpCadreLocationWiseReportVO = new TdpCadreLocationWiseReportVO();
	private SurveyTransactionVO surveyTransactionVO;
	
	public SurveyTransactionVO getSurveyTransactionVO() {
		return surveyTransactionVO;
	}

	public void setSurveyTransactionVO(SurveyTransactionVO surveyTransactionVO) {
		this.surveyTransactionVO = surveyTransactionVO;
	}

	public TdpCadreLocationWiseReportVO getTdpCadreLocationWiseReportVO() {
		return tdpCadreLocationWiseReportVO;
	}

	public void setTdpCadreLocationWiseReportVO(
			TdpCadreLocationWiseReportVO tdpCadreLocationWiseReportVO) {
		this.tdpCadreLocationWiseReportVO = tdpCadreLocationWiseReportVO;
	}

	public ITdpCadreReportService getTdpCadreReportService() {
		return tdpCadreReportService;
	}

	public void setTdpCadreReportService(
			ITdpCadreReportService tdpCadreReportService) {
		this.tdpCadreReportService = tdpCadreReportService;
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

	public JSONObject getJobj() {
		return jobj;
	}

	public void setJobj(JSONObject jobj) {
		this.jobj = jobj;
	}

	public String execute()
	{
		return Action.SUCCESS;
	}
	
	
	
	
	public String getLocationWiseDetailsForExcelReport(){
		try{
			jobj = new JSONObject(getTask());
			String constiIds       = jobj.getString("constituencyIds");
			List<Long> constituencyIdsList = new ArrayList<Long>();
			String[] constituency       = constiIds.split(",");
			for (String cosntituencyId : constituency) 
			{
				cosntituencyId = cosntituencyId.trim().replace("[","").replace("]", "").trim(); 
						
				if(cosntituencyId.trim().length()>0)
				{
					constituencyIdsList.add(Long.valueOf(cosntituencyId));
				}
				
			}			
			tdpCadreLocationWiseReportVO = tdpCadreReportService.generateExcelReportForTdpCadre(constituencyIdsList);
		}
		catch (Exception e) {
			LOG.info("Entered into getLocationWiseAsOfNowDetails() in getLocationWiseDetailsForExcelReport class");
		}
		return Action.SUCCESS;
	}
	
	public String memberShipCardReportStatus()
	{
		try
		{
		  HttpSession session=request.getSession();
		  RegistrationVO regVO=(RegistrationVO)session.getAttribute("USER");
		  if(regVO==null)
			return Action.INPUT;
		
		}
		catch(Exception e)
		{
			LOG.info("Entered into memberShipCardReportStatus() in getLocationWiseDetailsForExcelReport class");
		}
		return Action.SUCCESS;
	}
	public String getMemberShipCardDetails()
	{
		try
		{
		    jobj = new JSONObject(getTask());
		    Long districtId=jobj.getLong("district");
			String constiIds       = jobj.getString("constituencyIds");
			List<Long> constituencyIdsList = new ArrayList<Long>();
			String[] constituency       = constiIds.split(",");
			for (String cosntituencyId : constituency) 
			{
				cosntituencyId = cosntituencyId.trim().replace("[","").replace("]", "").trim(); 
						
				if(cosntituencyId.trim().length()>0)
				{
					constituencyIdsList.add(Long.valueOf(cosntituencyId));
				}
				
			}
			String fromDate=jobj.getString("fromDate");
			String  toDate=jobj.getString("toDate");
			
			surveyTransactionVO =	tdpCadreReportService.getMemberShipCardPrintDetails(districtId,constituencyIdsList,fromDate,toDate);
		}
		catch(Exception e)
		{
			LOG.info("Entered into getMemberShipCardDetails() in getLocationWiseDetailsForExcelReport class");
		}
	
		return Action.SUCCESS;	
	}
	
}
