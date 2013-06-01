package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import freemarker.template.utility.Execute;

public class InflencingCadreCountsDisplayAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest arg0) {
		
		this.request=arg0;
	}
	
	private IVoterReportService voterReportService;
	private Long locationValue;
	private Long constituencyId;
	private Long publicationDateId;
	private String typeValue;
	private String btnName ;
	private Long mainreqid;
	private String maintype;
	private String task;
	JSONObject jObj;
	private String name;
	private List<VoterVO> voters;
	private ConstituencyManagementVO constituencyManagementVO;
	private static final Logger LOG = Logger.getLogger(InflencingCadreCountsDisplayAction.class);
	
	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public IVoterReportService getVoterReportService() {
		return voterReportService;
	}


	public void setVoterReportService(IVoterReportService voterReportService) {
		this.voterReportService = voterReportService;
	}


	public Long getLocationValue() {
		return locationValue;
	}


	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}


	public Long getConstituencyId() {
		return constituencyId;
	}


	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}


	public Long getPublicationDateId() {
		return publicationDateId;
	}


	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}


	public String getTypeValue() {
		return typeValue;
	}


	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}


	public String getBtnName() {
		return btnName;
	}


	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}


	public Long getMainreqid() {
		return mainreqid;
	}


	public void setMainreqid(Long mainreqid) {
		this.mainreqid = mainreqid;
	}


	public String getMaintype() {
		return maintype;
	}


	public void setMaintype(String maintype) {
		this.maintype = maintype;
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

	
	
	public List<VoterVO> getVoters() {
		return voters;
	}


	public void setVoters(List<VoterVO> voters) {
		this.voters = voters;
	}


	public ConstituencyManagementVO getConstituencyManagementVO() {
		return constituencyManagementVO;
	}


	public void setConstituencyManagementVO(
			ConstituencyManagementVO constituencyManagementVO) {
		this.constituencyManagementVO = constituencyManagementVO;
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String execute()
	{
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
			return ERROR;
		return Action.SUCCESS;
	}

	public String getInfluencingPeopleVotersDetails()
	{
		try {
			LOG.debug("Entered Into getInfluencingPeopleVotersDetails() method in InflencingCadreCountsDisplayAction Class");
			HttpSession session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			if(user == null)
				return ERROR;
			String param;
			long userId = user.getRegistrationID();
			param = getTask();
			Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
			String order = request.getParameter("dir");
			String columnName = request.getParameter("sort");
			Integer maxRecords = Integer.parseInt(request.getParameter("results"));
			if(typeValue.equalsIgnoreCase("hamlet") || typeValue.equalsIgnoreCase("customWard"))
			{
				voters = voterReportService.showingVoterDetailsForSelectedHamlet(locationValue,userId,typeValue,btnName,startIndex,maxRecords,order,columnName);
			}
			else
			{
				voters = voterReportService.showVoterDetailsForSelcetedLevel(userId,locationValue,constituencyId,typeValue,publicationDateId,btnName,startIndex,maxRecords,order,columnName);
			}
			
			constituencyManagementVO = new ConstituencyManagementVO();
			constituencyManagementVO.setVoterDetails(voters);
			constituencyManagementVO.setVoterDetailsCount(0l);
			if(voters != null && voters.size() > 0 )
			{
				constituencyManagementVO.setVoterDetailsCount(voters.get(0).getTotalVoters());
			}
			return Action.SUCCESS;
		} catch (Exception e) {
			LOG.debug("Exception Raised in getInfluencingPeopleVotersDetails() method in InflencingCadreCountsDisplayAction Class");
			return ERROR;
		}
		
	}
}
