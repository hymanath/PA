package com.itgrids.partyanalyst.web.action;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TdpCadreVolunteerVO;
import com.itgrids.partyanalyst.service.ITdpCadreReportService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class TdpCadreVolunteerAction extends ActionSupport implements ServletRequestAware{
	private static final Logger LOG = Logger.getLogger(TdpCadreReportAction.class);
	
	private HttpServletRequest request;
	
	private String 	task;
	private JSONObject jobj;
	
	private ITdpCadreReportService  tdpCadreReportService;
	private InputStream inputStream;
	private TdpCadreVolunteerVO tdpCadreVolunteerVO;
	private HttpSession session;
	private ResultStatus resultStatus;
	private List<GenericVO> constituencyList;
	
	
	public List<GenericVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<GenericVO> constituencyList) {
		this.constituencyList = constituencyList;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public TdpCadreVolunteerVO getTdpCadreVolunteerVO() {
		return tdpCadreVolunteerVO;
	}

	public void setTdpCadreVolunteerVO(TdpCadreVolunteerVO tdpCadreVolunteerVO) {
		this.tdpCadreVolunteerVO = tdpCadreVolunteerVO;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
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
	
	public String execute()
	{
		
		constituencyList = tdpCadreReportService.getGHMCConstituencies();
		return Action.SUCCESS;
		
	}
	
	public String saveCadreInfo()
	{
		try {
			LOG.info("Entered into saveCadreDetails method in CadreRegistrationAction Action");
			session = request.getSession();
			resultStatus = tdpCadreReportService.saveCadreRegistration(tdpCadreVolunteerVO);
		
		} catch (Exception e) {
			LOG.error("Exception raised in saveCadreDetails method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
}
