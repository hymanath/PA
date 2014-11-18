package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.TdpCadreLocationWiseReportVO;
import com.itgrids.partyanalyst.service.ITdpCadreReportService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class TdpCadreReportAction extends ActionSupport {

	private static final Logger LOG = Logger.getLogger(TdpCadreReportAction.class);
	
	private HttpServletRequest request;
	
	private String 								task;
	private JSONObject                  		jobj;
	
	private ITdpCadreReportService  tdpCadreReportService;
	private TdpCadreLocationWiseReportVO tdpCadreLocationWiseReportVO;
	private TdpCadreLocationWiseReportVO result = new TdpCadreLocationWiseReportVO();
	
	
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
	
	public TdpCadreLocationWiseReportVO getResult() {
		return result;
	}

	public void setResult(TdpCadreLocationWiseReportVO result) {
		this.result = result;
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
			for (String string : constituency) 
			{
				constituencyIdsList.add(Long.valueOf(string));
			}			
			result = tdpCadreReportService.generateExcelReportForTdpCadre(constituencyIdsList);
		}
		catch (Exception e) {
			LOG.info("Entered into getLocationWiseAsOfNowDetails() in getLocationWiseDetailsForExcelReport class");
		}
		return Action.SUCCESS;
	}
	
	
}
