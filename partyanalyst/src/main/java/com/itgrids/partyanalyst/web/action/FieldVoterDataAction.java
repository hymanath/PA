package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.FieldVoterDataVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IFieldVoterDataService;
import com.itgrids.partyanalyst.service.ISoundexService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class FieldVoterDataAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	private HttpSession session;
	
	private String task;
	JSONObject jObj;
	private List<SelectOptionVO> constituencyList, userAccessConstituencyList;
	@Autowired
	private ICrossVotingEstimationService crossVotingEstimationService;
	@Autowired
	private IVotersAnalysisService votersAnalysisService;
	
    private List<FieldVoterDataVO> fieldVoterDetailsList;
    
    private IFieldVoterDataService fieldVoterDataService;
    private ISoundexService soundexService;
    
    
    
    public ISoundexService getSoundexService() {
		return soundexService;
	}

	public void setSoundexService(ISoundexService soundexService) {
		this.soundexService = soundexService;
	}
    
    
    
    public IFieldVoterDataService getFieldVoterDataService() {
		return fieldVoterDataService;
	}

	public void setFieldVoterDataService(
			IFieldVoterDataService fieldVoterDataService) {
		this.fieldVoterDataService = fieldVoterDataService;
	}

	public List<FieldVoterDataVO> getFieldVoterDetailsList() {
		return fieldVoterDetailsList;
	}

	public void setFieldVoterDetailsList(
			List<FieldVoterDataVO> fieldVoterDetailsList) {
		this.fieldVoterDetailsList = fieldVoterDetailsList;
	}
	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}
	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	public List<SelectOptionVO> getUserAccessConstituencyList() {
		return userAccessConstituencyList;
	}
	public void setUserAccessConstituencyList(
			List<SelectOptionVO> userAccessConstituencyList) {
		this.userAccessConstituencyList = userAccessConstituencyList;
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
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	
	
	
	public String execute()
	{
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		 return INPUT;
		
		constituencyList = user.getUserAccessVoterConstituencies();
		if(constituencyList == null || constituencyList.isEmpty()){
			Long userID = user.getRegistrationID();
			Long electionYear = Long.valueOf(IConstants.PRESENT_ELECTION_YEAR);
			Long electionTypeId = Long.valueOf(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
			userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
			constituencyList = votersAnalysisService.getConstituencyList(userAccessConstituencyList);
			constituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
			user.setUserAccessVoterConstituencies(constituencyList);
		}
		return SUCCESS;
		
	}
	
	public String testAction()
	{
		try{
		fieldVoterDetailsList = fieldVoterDataService.getFieldVoterDataByBoothId(Long.parseLong(request.getParameter("boothId")));
		}catch(Exception e){
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
	}
	
	public String getMappedVoterDetailsByUsingSoundexByPanchayatId()
	{
		soundexService.getMappedVoterDetailsByUsingSoundexByPanchayatId(Long.parseLong(request.getParameter("panchayatId")));
		return Action.SUCCESS;
		
	}



	
}
