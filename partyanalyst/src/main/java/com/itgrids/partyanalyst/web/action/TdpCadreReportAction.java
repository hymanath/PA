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
import com.itgrids.partyanalyst.dto.ZebraPrintDetailsVO;
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
	private ZebraPrintDetailsVO zebraPrintDetailsVO;

	public ZebraPrintDetailsVO getZebraPrintDetailsVO() {
		return zebraPrintDetailsVO;
	}

	public void setZebraPrintDetailsVO(ZebraPrintDetailsVO zebraPrintDetailsVO) {
		this.zebraPrintDetailsVO = zebraPrintDetailsVO;
	}

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
			Long stateTyleId = jobj.getLong("stateTyleId");
			String searchType = jobj.getString("searchType");
			
			zebraPrintDetailsVO =	tdpCadreReportService.getMemberShipCardPrintDetails(searchType,stateTyleId,constituencyIdsList,fromDate,toDate);
		}
		catch(Exception e)
		{
			LOG.info("Entered into getMemberShipCardDetails() in getLocationWiseDetailsForExcelReport class");
		}
	
		return Action.SUCCESS;	
	}
	
	public String getConstituencyDetailsInDistricts()
	{
		try
		{
		    jobj = new JSONObject(getTask());

			String districtIds       = jobj.getString("districtIds");
			List<Long> districtIdList = new ArrayList<Long>();
			String[] district       = districtIds.split(",");
			for (String districtId : district) 
			{
				districtId = districtId.trim().replace("[","").replace("]", "").trim(); 
						
				if(districtId.trim().length()>0)
				{
					districtIdList.add(Long.valueOf(districtId));
				}
				
			}
			surveyTransactionVO =	tdpCadreReportService.getConstituencyDetailsInDistricts(districtIdList);
		}
		catch(Exception e)
		{
			LOG.info("Entered into getMemberShipCardDetails() in getLocationWiseDetailsForExcelReport class");
		}
	
		return Action.SUCCESS;	
	}
	
	public String tdpCadreCardsPrintingDashBoard()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			zebraPrintDetailsVO = tdpCadreReportService.createDashBoardForPrintingCardsDetails(user.getAccessType(),user.getAccessValue(),0L);
			
		} catch (Exception e) {
			LOG.error("Exception raised in printingDashBoard method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String getPringtinDashBoardDetails()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			jobj = new JSONObject(getTask());
			Long stateTyleId = jobj.getLong("stateTyleId");
				
			zebraPrintDetailsVO = tdpCadreReportService.createDashBoardForPrintingCardsDetails(user.getAccessType(),user.getAccessValue(),stateTyleId);
			
		} catch (Exception e) {
			LOG.error("Exception raised in printingDashBoard method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	public String getCadreDetails()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			jobj = new JSONObject(getTask());
			
			zebraPrintDetailsVO = tdpCadreReportService.getCadreDetailsByStatus(jobj.getLong("Id"),jobj.getString("status"));
			
		} catch (Exception e) {
			LOG.error("Exception raised in printingDashBoard method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	public String getPrintingStatusDetails()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			jobj = new JSONObject(getTask());
			Long stateTyleId = jobj.getLong("stateTyleId");
				
			zebraPrintDetailsVO = tdpCadreReportService.dashBoardForPrintingCardsDetails(user.getAccessType(),user.getAccessValue(),stateTyleId);
			
		} catch (Exception e) {
			LOG.error("Exception raised in printingDashBoard method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
}
