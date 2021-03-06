package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class VoterFamilyInfoAction  extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	private HttpSession session;
	
	private String buildType;
	private Long publicationDateId;
	
	private Long id;
	private String type;
	private String typename;
	private String name;
	private String maintype;
	
	private Long constituencyId;
	
	private String publicationYear;
    private String requestFor;
	
	private static final Logger LOG = Logger.getLogger(VoterFamilyInfoAction.class);
	private String task;
	JSONObject jObj;
	private ConstituencyManagementVO constituencyManagementVO;
	private IVotersAnalysisService votersAnalysisService;
	
	private int startIndex;
	private String dir;
	private String sort;
	private int results;
	private Long publicationId;
	private Long boothId;
	private Long hamletId;
	private List<Long> count;
	private Long customwardId;
	private Long flagId;
	private Long locationId;
	private List<VoterVO> voterDetails;
	
	public void setVoterDetails(List<VoterVO> voterDetails) {
		this.voterDetails = voterDetails;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Long getFlagId() {
		return flagId;
	}

	public void setFlagId(Long flagId) {
		this.flagId = flagId;
	}

	public Long getCustomwardId() {
		return customwardId;
	}

	public void setCustomwardId(Long customwardId) {
		this.customwardId = customwardId;
	}

	public String getRequestFor() {
		return requestFor;
	}

	public void setRequestFor(String requestFor) {
		this.requestFor = requestFor;
	}
	
	
	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results = results;
	}

	public Long getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(Long publicationId) {
		this.publicationId = publicationId;
	}

	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	
	public Long getHamletId() {
		return hamletId;
	}

	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}

	public String getMaintype() {
		return maintype;
	}
	public void setMaintype(String maintype) {
		this.maintype = maintype;
	}
	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}
	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}
	public ConstituencyManagementVO getConstituencyManagementVO() {
		return constituencyManagementVO;
	}
	public void setConstituencyManagementVO(
			ConstituencyManagementVO constituencyManagementVO) {
		this.constituencyManagementVO = constituencyManagementVO;
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
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}
	public String getBuildType() {
		return buildType;
	}
	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}
	public Long getPublicationDateId() {
		return publicationDateId;
	}
	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	
	public List<Long> getCount() {
		return count;
	}

	public void setCount(List<Long> count) {
		this.count = count;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String execute() throws Exception
	{
		
		
		return SUCCESS;
		
	}
	
	public String voterFamilyInfoForHamlets()
	{
		return Action.SUCCESS;
		
	}
	
	public String getVoterDetailsInitialRequest(){
		
		Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
		String dir = request.getParameter("dir");
		String sort = request.getParameter("sort");
		Integer results = Integer.parseInt(request.getParameter("results"));
		
		Long publicationId = request.getParameter("publicationId") != null ?Long.parseLong(request.getParameter("publicationId")):0L;
		Long boothId = request.getParameter("boothId") != null ? Long.parseLong(request.getParameter("boothId")):0L;
		Long panchaytId = request.getParameter("panchaytId") != null? Long.parseLong(request.getParameter("panchaytId")) :0L;
		
		Long constituencyId = request.getParameter("constituencyId") != null? Long.parseLong(request.getParameter("constituencyId")) :0L;
		
		String name = request.getParameter("name") ;
		
		
		
		return SUCCESS;
	}
	
	public String getVoterDetails(){
		
		if(LOG.isDebugEnabled())	
		LOG.debug("Executing getVoterDetails() Method");	
		try{
			
		
			String param;
			param = getTask();
			
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			Long userId =  regVO.getRegistrationID();
			
			Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
			String order = request.getParameter("dir");
			String columnName = request.getParameter("sort");
			Integer maxRecords = Integer.parseInt(request.getParameter("results"));
			List<VoterVO> votersList = null;
			constituencyManagementVO = new ConstituencyManagementVO();
			
			
			
			Long publicationId = request.getParameter("publicationId") != null ?Long.parseLong(request.getParameter("publicationId")):0L;
			Long boothId = request.getParameter("boothId") != null ? Long.parseLong(request.getParameter("boothId")):0L;
			Long panchaytId = request.getParameter("panchaytId") != null? Long.parseLong(request.getParameter("panchaytId")) :0L;
			Long wardId = request.getParameter("wardId") != null? Long.parseLong(request.getParameter("wardId")) :0L;
			Long constiId = request.getParameter("constiId") != null? Long.parseLong(request.getParameter("constiId")) :0L;
			Long hamletId = 0L;
			
			if(request.getParameter("hamletId") != null)
				hamletId = Long.valueOf(request.getParameter("hamletId"));
			
				votersList = new ArrayList<VoterVO>();
				
				if(hamletId.longValue() != 0){
					votersList = votersAnalysisService.getVoterDetails(
							publicationId, null, panchaytId,hamletId, startIndex,
							maxRecords, order, columnName,userId,wardId,constiId);
					
				}else{
				
					if(boothId == 0 && panchaytId != 0)			
						votersList = votersAnalysisService.getVoterDetails(
								publicationId, null, panchaytId,null, startIndex,
								maxRecords, order, columnName,userId,wardId,constiId);
					else if(boothId != 0 && panchaytId == 0)
						votersList = votersAnalysisService.getVoterDetails(
								publicationId, boothId , null,null, startIndex, maxRecords,
								order, columnName,userId,wardId,constiId);
				}

			
			constituencyManagementVO.setVoterDetails(votersList);
			
			if(votersList !=null && votersList.size() > 0 )
			constituencyManagementVO.setVoterDetailsCount(votersList.get(0).getTotalVoters());
			
		}catch(Exception e){
			
			e.printStackTrace();
			LOG.error("Exception Occured in getVoterDetails() Method,Exception is- "+e);
			
		}
			
			return Action.SUCCESS;
			
		}
	
	public String getHamletAndBoothCount()
	{
		try {
			LOG.debug("Executing getVoterDetails() Method");
			
			String param;
			param = getTask();
			jObj = new JSONObject(param);
			count = votersAnalysisService.getCountOfHamletAndBoothsInAPanchayat(jObj.getLong("panchaytId"));
		} catch (Exception e) {
			LOG.error("Error Occured in the getHamletAndBoothCount() method in VoterFamilyInfo", e);
		}
		
		return Action.SUCCESS;
	}
	
	
}
