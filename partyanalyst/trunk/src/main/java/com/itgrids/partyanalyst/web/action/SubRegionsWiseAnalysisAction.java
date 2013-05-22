package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultVO;
import com.itgrids.partyanalyst.dto.MandalInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IUserVoterService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;

public class SubRegionsWiseAnalysisAction extends ActionSupport implements ServletRequestAware{
	
	private Long id;
	private Long publicationDateId;
	private String type;
	private String publicationYear;
	private String  buildType;
	private Long constituencyId;
	private String typeName;
	private HttpServletRequest request;
	private List<MandalInfoVO> mandalInfoVOsList;
	private HttpSession session;
	JSONObject jObj = null;
	private IUserVoterService userVoterService;
	private List<ElectionWiseMandalPartyResultVO> mptcZptcElectionResultsVO;
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	
	private String ghmc;
	
	public String getGhmc() {
		return ghmc;
	}


	public void setGhmc(String ghmc) {
		this.ghmc = ghmc;
	}

	private Long parliamentConstituencyId;
	
	public Long getParliamentConstituencyId() {
		return parliamentConstituencyId;
	}


	public void setParliamentConstituencyId(Long parliamentConstituencyId) {
		this.parliamentConstituencyId = parliamentConstituencyId;
	}


	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}


	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
	}


	public List<ElectionWiseMandalPartyResultVO> getMptcZptcElectionResultsVO() {
		return mptcZptcElectionResultsVO;
	}


	public void setMptcZptcElectionResultsVO(
			List<ElectionWiseMandalPartyResultVO> mptcZptcElectionResultsVO) {
		this.mptcZptcElectionResultsVO = mptcZptcElectionResultsVO;
	}

	private String task;
	private List<SelectOptionVO> resultList;
	private IVotersAnalysisService votersAnalysisService;
	private IStaticDataService staticDataService;
	private Long mandalId;
	
	public Long getMandalId() {
		return mandalId;
	}


	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}


	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}


	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}


	public List<SelectOptionVO> getResultList() {
		return resultList;
	}


	public void setResultList(List<SelectOptionVO> resultList) {
		this.resultList = resultList;
	}


	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}


	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getPublicationDateId() {
		return publicationDateId;
	}


	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
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


	public Long getConstituencyId() {
		return constituencyId;
	}


	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public List<MandalInfoVO> getMandalInfoVOsList() {
		return mandalInfoVOsList;
	}

	public void setMandalInfoVOsList(List<MandalInfoVO> mandalInfoVOsList) {
		this.mandalInfoVOsList = mandalInfoVOsList;
	}
	public IUserVoterService getUserVoterService() {
		return userVoterService;
	}


	public void setUserVoterService(IUserVoterService userVoterService) {
		this.userVoterService = userVoterService;
	}
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}


	public String execute(){
		
		/*if(type.equalsIgnoreCase("mandal")){
		
		ElectionWiseMandalPartyResultListVO mptcZptcResultListVO = partyBoothWiseResultsService.getAllMPTCAndZPTCElectionsInfoInTehsil(new Long(id.toString().substring(1)));
		mptcZptcElectionResultsVO = mptcZptcResultListVO.getPartyWiseElectionResultsVOList();
		}*/
		//latest Parliament ConstituencyId
	  parliamentConstituencyId = staticDataService.getParliamentIdByAssembly(constituencyId);
		 
		 return Action.SUCCESS;
	 }


	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public String getCensusReportForSubLevels()
	{
		try{
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
				return ERROR;
			jObj = new JSONObject(getTask());
		mandalInfoVOsList = userVoterService.getCensusReportForSubLevels(jObj.getString("type"),jObj.getLong("id"),jObj.getLong("constituencyId"));	
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception Occured in getCensusReportForSubLevels() method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
	
	 public String getMandalsForConstituency()
	   {
		 String param = null;
		   try{
			  param = getTask();
			 jObj = new JSONObject(param);
			 if(jObj.getString("task").equalsIgnoreCase("getMandals"))
				 resultList = votersAnalysisService.getMandalsInConstituency(jObj.getLong("constituencyId")); 
			 else if(jObj.getString("task").equalsIgnoreCase("getPanchayatsByMandalId"))
			 {
				
				 resultList = staticDataService.getPanchayatiesByMandalId(new Long(jObj.getString("mandalId")));
			 }
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		   return Action.SUCCESS;
	   }
	 
}
